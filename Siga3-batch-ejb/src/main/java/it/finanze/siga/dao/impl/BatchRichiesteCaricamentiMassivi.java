package it.finanze.siga.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import it.finanze.scheduler.bean.CaricamentoMassivoRecord;
import it.finanze.scheduler.bean.CoerenzaPropertiesBean;
import it.finanze.scheduler.bean.EventoBean;
import it.finanze.scheduler.bean.ProfiliAttiviUtente;
import it.finanze.scheduler.bean.ProfiloCM;
import it.finanze.scheduler.bean.ProfiloRichiestaBean;
import it.finanze.scheduler.bean.RegistroRichiesteBatchBean;
import it.finanze.scheduler.bean.RichiedenteCdR;
import it.finanze.scheduler.bean.RichiestaAbilitazioneDisabilitazioneBean;
import it.finanze.scheduler.bean.RichiestaBatchBean;
import it.finanze.scheduler.bean.RichiestaBatchDaElaborareBean;
import it.finanze.scheduler.bean.Ufficio;
import it.finanze.scheduler.bean.UtenteInVisibilitaCM;
import it.finanze.siga.bean.AllegatoCaricamentoMassivoEntity;
import it.finanze.siga.bean.CaricamentoMassivoEntity;
import it.finanze.siga.bean.FileAbilitazioneBean;
import it.finanze.siga.bean.FileAbilitazioneEcaBean;
import it.finanze.siga.bean.OperatoreBean;
import it.finanze.siga.business.MailCommonUtil;
import it.finanze.siga.ejb.IBatchRichiesteCaricamentiMassiviNew;
import it.finanze.siga.ejb.util.Utility;
import it.finanze.siga.finder.DocumentoFinder;
import it.finanze.siga.finder.InterrogazioneCaricamentiFinder;
import it.finanze.siga.finder.OperatoreFinder;
import it.finanze.siga.util.Constants;
import it.finanze.siga.util.SigaCache;
import it.finanze.siga.util.Utils;
import it.finanze.siga.util.bean.UtenteBean;
import it.finanze.siga.utility.properties.PropertiesReader;
import it.sogei.cau.ws.ServiceCauUtente;
import it.sogei.cau.ws.bean.UtenteInfoBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.mail.util.ByteArrayDataSource;
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BatchRichiesteCaricamentiMassivi extends BatchRichiesteCaricamentiMassiviDao implements IBatchRichiesteCaricamentiMassiviNew {

	static Logger fileLog = Logger.getLogger(BatchRichiesteCaricamentiMassivi.class);
	static final String COD_EVENTO_CAR_MASS  = "CarMass";
	static final String COD_EVENTO_CAR_MASS_IMM  = "CarMassImm";
	
	static final String COD_AMBITO_NI  = "5";
	static final String COD_EVENTO_CAR_MASS_NI  = "CarMassNI";
	static final String COD_EVENTO_CAR_MASS_IMM_NI  = "CarMassImmNI";
	
	static final String RICHIEDENTE_AC  = "CM";
	static final String NESSUNA_NOTA_INSERITA  = "*Nessuna nota inserita*";
	public static String DIR_PROPERTIES = "/prod/installedApps/SIGA";
	public static String FILE_PROPERTIES = "ini.properties";
	
	
	CoerenzaPropertiesBean coerenzaProperties;
	private Long id_richiesta_visibilita;
	private String profileEntitlement;
	private String codice_ufficio;
	private String codice_cdr;
	private String evento;
	private String codEvento;
	private boolean presenzaErrori = false;
	static final String INTESTAZIONE_ERRORI_INCOERENZE_ABILITAZIONI = "CodiceFiscale,CodiceCdr,CodiceRoleGroup,CodiceProfilo,Azione,MotivoScarto";
	static final String SEPARATORE_COLONNE = ",";
	private static final String SHEET_NAME = "report_errori";
	private static final Object[] INTESTAZIONE_ERRORI_INCOERENZE_ABILITAZIONI_ARR = 
		{"CodiceFiscale", "CodiceCdr", "CodiceRoleGroup", "CodiceProfilo", "Azione", "MotivoScarto"};
	
	// Variabili da aggiunfere per la gestione dei profili ECA
	private List<FileAbilitazioneEcaBean> recordAggiuntiEca;
	private boolean fileDaAggiungere = false;

	@Inject MailCommonUtil mailCommonUtil;
//	public BatchRichiesteCaricamentiMassivi(SqlMapClient sqlMapClient) {
//		super(sqlMapClient);
//	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void startBatch(String idCaricamento) throws Exception{
		
		Properties prop = new Properties();
		prop.load(new FileInputStream(Constants.FILE_PROPERTY_PATH+"/"+Constants.FILE_PROPERTY_NAME));
		String logFilePath = prop.getProperty("BATCH_RIC_CAR_MASS_LOG_FILE_PATH");
		String allegatiFilePath = prop.getProperty("BATCH_COERENZA_ALLEGATI_FILE_PATH");
	 	
		Utility.initFileLogger(logFilePath,fileLog);
		
		EventoBean datiEvento = new EventoBean();
		if(idCaricamento != null && idCaricamento.equals("")){
			fileLog.info("START... BATCH RICHIESTE CARICAMENTI MASSIVI - RICHIAMATO DA SCHEDULER");
			// Ottieni le impostazioni del batch
			coerenzaProperties = getPropertiesBatch();
			if("SI".equalsIgnoreCase(coerenzaProperties.getElaborazioneCM())){
				fileLog.info("START... BATCH RICHIESTE CARICAMENTI MASSIVI - RICHIAMATO DA SCHEDULER");
				//evento = COD_EVENTO_CAR_MASS;
				//codEvento = COD_EVENTO_CAR_MASS;
				//datiEvento = recuperaDatiEventoSistema(codEvento);
				InterrogazioneCaricamentiFinder finder = new InterrogazioneCaricamentiFinder();
				List<CaricamentoMassivoEntity> listaCaricamenti = new ArrayList<CaricamentoMassivoEntity>();
				listaCaricamenti= getListaCaricamentiPerControlli(finder);
				// 4.5.3 2024 -->
				String ambito = listaCaricamenti.get(0).getIdAmbito();
				if(COD_AMBITO_NI.equals(ambito)) {
					evento = COD_EVENTO_CAR_MASS_NI;
					codEvento = COD_EVENTO_CAR_MASS_NI;
				}else {
					evento = COD_EVENTO_CAR_MASS;
					codEvento = COD_EVENTO_CAR_MASS;
				}
				datiEvento = recuperaDatiEventoSistema(codEvento);
				// 4.5.3 2024 <--
				controllaGenerazioneRichiesteCaricamentoMassivo(listaCaricamenti, datiEvento, allegatiFilePath);
				generazioneRichiesteBatch(finder, datiEvento, allegatiFilePath);
				fileLog.info("asdasdas");
//				elaborazioneProfiliAG();
			}
			
				
		}else{
//			List<AdminGroupTipoUffCdr> listProfiliAG = getProfiliDiAdminGroup();
			fileLog.info("START... BATCH RICHIESTE CARICAMENTI MASSIVI - RICHIAMATO DA APPLICAZIONE WEB GESTIONE CARICAMENTI MASSIVI");
			//evento = COD_EVENTO_CAR_MASS_IMM;
			//codEvento = COD_EVENTO_CAR_MASS_IMM;
			//datiEvento = recuperaDatiEventoSistema(codEvento);
			InterrogazioneCaricamentiFinder finder = new InterrogazioneCaricamentiFinder();
			finder.setIdCaricamento(idCaricamento);
			List<CaricamentoMassivoEntity> listaCaricamenti = new ArrayList<CaricamentoMassivoEntity>();
			listaCaricamenti= getListaCaricamentiPerControlli(finder);
			
			// 4.5.3 2024 -->
			String ambito = listaCaricamenti.get(0).getIdAmbito();
			if(COD_AMBITO_NI.equals(ambito)) {
				evento = COD_EVENTO_CAR_MASS_IMM_NI;
				codEvento = COD_EVENTO_CAR_MASS_IMM_NI;
			}else {
				evento = COD_EVENTO_CAR_MASS_IMM;
				codEvento = COD_EVENTO_CAR_MASS_IMM;
			}
			datiEvento = recuperaDatiEventoSistema(codEvento);
			// 4.5.3 2024 <--
			
			controllaGenerazioneRichiesteCaricamentoMassivo(listaCaricamenti, datiEvento, allegatiFilePath);
			generazioneRichiesteBatch(finder, datiEvento, allegatiFilePath);
		}
		fileLog.info("END... BATCH RICHIESTE CARICAMENTI MASSIVI");
	}
	
	private void controllaGenerazioneRichiesteCaricamentoMassivo(List<CaricamentoMassivoEntity> listaCaricamenti, EventoBean datiEvento, String allegatiFilePath) throws Exception{	
		int numRecordPresenti;
		int numRecordScartati;
		int numRecordValidi;
		int numRecordEcaAggiunti;
//		CaricamentoMassivoInserimentoBean cmInserimento = new CaricamentoMassivoInserimentoBean(); 
		
		for(int i=0; i<listaCaricamenti.size(); i++){
			List<RichiestaAbilitazioneDisabilitazioneBean> listaRichiesteCaricamento = new ArrayList<RichiestaAbilitazioneDisabilitazioneBean>();
			List<FileAbilitazioneBean> listaFileAbilitazione = new ArrayList<FileAbilitazioneBean>();
			CaricamentoMassivoEntity caricamento = listaCaricamenti.get(i);
			numRecordPresenti = 0;
			numRecordScartati = 0;
			numRecordValidi = 0;
			numRecordEcaAggiunti = 0;
			// stacco la sequence
			long sequenceEvento = getSequenceFromRichiesteBatch();
			
			
			InputStream bis = new ByteArrayInputStream(caricamento.getbFileAbilitazioni());
			
			// Conto il numero di record del file Excel
			XSSFWorkbook excel = new XSSFWorkbook(bis);
			XSSFSheet sheet = excel.getSheetAt(0);
			int rownum = sheet.getLastRowNum();
			caricamento.setNumRecordFile(String.valueOf(rownum));
			//Iterator<Row> rowIterator = sheet.iterator();
			//rowIterator.next();
			List<CaricamentoMassivoRecord> listRecordFileInput = new ArrayList<CaricamentoMassivoRecord>();
			
			// 4.5.3 2024 -->
			if(COD_AMBITO_NI.equals(caricamento.getIdAmbito())) {
				listRecordFileInput = leggiRecordInputNonIntegrate(sheet);
			}else {
				listRecordFileInput = leggiRecordInput(sheet);
			}
			
			/*while(rowIterator.hasNext()){
				numRecordPresenti ++;
				CaricamentoMassivoRecord cmRecord = new CaricamentoMassivoRecord();
				Row row = rowIterator.next();
                XSSFRow caricamentoRow = sheet.getRow(row.getRowNum());
                XSSFCell cfCell = caricamentoRow.getCell(0);
                XSSFCell codRoleGroupCell = caricamentoRow.getCell(1);
                XSSFCell codProfiloCell = caricamentoRow.getCell(2);
                XSSFCell codCdrCell = caricamentoRow.getCell(3);
                XSSFCell dataScadenzaCell = caricamentoRow.getCell(4);
                XSSFCell azioneCell = caricamentoRow.getCell(5);
                
                if(cfCell!=null)
                	cmRecord.setCodiceFiscale(cfCell.getStringCellValue());
                if(codRoleGroupCell!=null)
                	cmRecord.setCodiceRoleGroup(codRoleGroupCell.getStringCellValue());
                if(codProfiloCell!=null)
                	cmRecord.setCodiceProfilo(codProfiloCell.getStringCellValue());
                if(codCdrCell!=null)
                	cmRecord.setCodiceCdr(codCdrCell.getStringCellValue());
                if(dataScadenzaCell!=null){
                	if(dataScadenzaCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC ){
                		 if (DateUtil.isCellDateFormatted(dataScadenzaCell)) {
                			 SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
                			 cmRecord.setDataScadenza(dateFormat.format(dataScadenzaCell.getDateCellValue()));
                		 }
                	}else if (dataScadenzaCell.getCellType() == XSSFCell.CELL_TYPE_STRING){
                		cmRecord.setDataScadenza(dataScadenzaCell.getStringCellValue());
                	}else if (dataScadenzaCell.getCellType() == XSSFCell.CELL_TYPE_BLANK){
                		cmRecord.setDataScadenza(dataScadenzaCell.getStringCellValue());
                	}
                }else {
                	cmRecord.setDataScadenza("");
                }
			
                if(azioneCell!=null) {
                	cmRecord.setAzione(azioneCell.getStringCellValue());
                }
                 listRecordFileInput.add(cmRecord);
			}*/
			// 4.5.3 2024 <--
			
			
			Iterator<CaricamentoMassivoRecord> rowFileIterator = listRecordFileInput.iterator();
			
			while(rowFileIterator.hasNext()){
				CaricamentoMassivoRecord cmRecord = rowFileIterator.next();
//				numRecordPresenti ++;
//				CaricamentoMassivoRecord cmRecord = new CaricamentoMassivoRecord();
//				Row row = rowIterator.next();
//                XSSFRow caricamentoRow = sheet.getRow(row.getRowNum());
//                XSSFCell cfCell = caricamentoRow.getCell(0);
//                XSSFCell codRoleGroupCell = caricamentoRow.getCell(1);
//                XSSFCell codProfiloCell = caricamentoRow.getCell(2);
//                XSSFCell codCdrCell = caricamentoRow.getCell(3);
//                XSSFCell azioneCell = caricamentoRow.getCell(4);
//                cmRecord.setCodiceFiscale(cfCell.getStringCellValue());
//                cmRecord.setCodiceRoleGroup(codRoleGroupCell.getStringCellValue());
//                cmRecord.setCodiceProfilo(codProfiloCell.getStringCellValue());
//                cmRecord.setCodiceCdr(codCdrCell.getStringCellValue());
//                cmRecord.setAzione(azioneCell.getStringCellValue());
                List<String> errorMessage =controllaSingoloRecord(cmRecord, caricamento, listRecordFileInput);
                if(errorMessage.size()>0){
                	numRecordScartati ++;
                	// Scrivi record 0 - Record di scarto
                	String errore = "";
                	for(int x=0; x<errorMessage.size(); x++){
                		 errore = errore + " - " + errorMessage.get(x);
                	}
                	listaFileAbilitazione.add(gestisciRecordFileAbilitazione(caricamento, cmRecord, errore));
                }else{
                	numRecordValidi ++;
                	// Scrivi record di acquisizione
                	listaRichiesteCaricamento.add(gestioneRecordZ(cmRecord, errorMessage, sequenceEvento));
                	listaFileAbilitazione.add(gestisciRecordFileAbilitazione(caricamento, cmRecord, null));
                	if(fileDaAggiungere && recordAggiuntiEca!=null && recordAggiuntiEca.size()>0){
                		for(int a=0; a<recordAggiuntiEca.size(); a++){
                			// I record Aggiuntivi relativi al Profilo Eca ECA_xxxxx00000
                			// vengono inseriti solo se non e' presente un record per la chiave
                			// idCaricamento - codiceFiscaleUtente - codiceRoleGroup - codiceProfilo - codiceCdr
                			recordAggiuntiEca.get(a).setIdCaricamento(caricamento.getIdCaricamento());
                			int profiliEcaAggiuntiviSuDb = verificaPresenzaProfiloEcaAggiuntivo(recordAggiuntiEca.get(a));
                			if(profiliEcaAggiuntiviSuDb == 0 && !verificaPresenzaProfiloInLista(listaFileAbilitazione,recordAggiuntiEca.get(a))){
                				listaFileAbilitazione.add(recordAggiuntiEca.get(a));
                    			listaRichiesteCaricamento.add(gestioneRecordZEca(recordAggiuntiEca.get(a), sequenceEvento));
                    			numRecordEcaAggiunti++;
                			}
                		}
                	}
                }
			}
			
			/**********************************************************
			 * Va a scrivere con la transazione 
			 * il contenuto delle liste sulle tabelle 
			 * La transazione e' gestita nel metodo 
			 * inserisciRichiesteCaricamentoMassivo()
			 *************************************************************/
			inserisciRichiesteCaricamentoMassivo(listaRichiesteCaricamento, listaFileAbilitazione);
			if(numRecordScartati>0){
				presenzaErrori = true;
				inviaMailConAllegatoCM(caricamento, allegatiFilePath);
			}
			
			fileLog.info("************************** REC LETTI =   " + numRecordPresenti);
			fileLog.info("************************** REC SCARTATI =   " + numRecordScartati);
			fileLog.info("************************** REC VALIDI =   " + numRecordValidi);
			fileLog.info("************************** REC ECA AGGIUNTI =   " + numRecordEcaAggiunti);
			
			CaricamentoMassivoEntity CMEntity = new CaricamentoMassivoEntity();
			CMEntity.setNumRecordFile(String.valueOf(numRecordPresenti));
			CMEntity.setNumRecordValidi(String.valueOf(numRecordValidi));
			CMEntity.setNumRecordScartati(String.valueOf(numRecordScartati));
			CMEntity.setNumRecordEcaAggiunti(String.valueOf(numRecordEcaAggiunti));
			if(numRecordValidi==0)
				CMEntity.setStatoRichiesta("SCA");
			else if(numRecordValidi>0)
				CMEntity.setStatoRichiesta("CON");
			CMEntity.setIdCaricamento(caricamento.getIdCaricamento());
			CMEntity.setSequenceRichiesteGenerate(sequenceEvento);
			updateControlloRichiestaCaricamentoMassivo(CMEntity);
		}
	}
	
	
	// 4.5.3 2024 -->
	private List<CaricamentoMassivoRecord> leggiRecordInputNonIntegrate(XSSFSheet sheet) {
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		//int numRecordPresenti = 0;
		
		List<CaricamentoMassivoRecord> listRecordFileInput = new ArrayList<CaricamentoMassivoRecord>();
		
		while(rowIterator.hasNext()){
			//numRecordPresenti ++;
			CaricamentoMassivoRecord cmRecord = new CaricamentoMassivoRecord();
			Row row = rowIterator.next();
            XSSFRow caricamentoRow = sheet.getRow(row.getRowNum());
            XSSFCell cfCell = caricamentoRow.getCell(0);
            XSSFCell codRoleGroupCell = caricamentoRow.getCell(1);
            XSSFCell codProfiloCell = caricamentoRow.getCell(2);
            XSSFCell azioneCell = caricamentoRow.getCell(3);
            
            if(cfCell!=null)
            	cmRecord.setCodiceFiscale(cfCell.getStringCellValue());
            if(codRoleGroupCell!=null)
            	cmRecord.setCodiceRoleGroup(codRoleGroupCell.getStringCellValue());
            if(codProfiloCell!=null)
            	cmRecord.setCodiceProfilo(codProfiloCell.getStringCellValue());
            if(azioneCell!=null) {
            	cmRecord.setAzione(azioneCell.getStringCellValue());
            }
             listRecordFileInput.add(cmRecord);
		}
		
		return listRecordFileInput;
	}
	// 4.5.3 2024 --<

	private List<CaricamentoMassivoRecord> leggiRecordInput(XSSFSheet sheet) {
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		//int numRecordPresenti = 0;
		
		List<CaricamentoMassivoRecord> listRecordFileInput = new ArrayList<CaricamentoMassivoRecord>();
		
		while(rowIterator.hasNext()){
			//numRecordPresenti ++;
			CaricamentoMassivoRecord cmRecord = new CaricamentoMassivoRecord();
			Row row = rowIterator.next();
            XSSFRow caricamentoRow = sheet.getRow(row.getRowNum());
            XSSFCell cfCell = caricamentoRow.getCell(0);
            XSSFCell codRoleGroupCell = caricamentoRow.getCell(1);
            XSSFCell codProfiloCell = caricamentoRow.getCell(2);
            XSSFCell codCdrCell = caricamentoRow.getCell(3);
            XSSFCell dataScadenzaCell = caricamentoRow.getCell(4);
            XSSFCell azioneCell = caricamentoRow.getCell(5);
            
            if(cfCell!=null)
            	cmRecord.setCodiceFiscale(cfCell.getStringCellValue());
            if(codRoleGroupCell!=null)
            	cmRecord.setCodiceRoleGroup(codRoleGroupCell.getStringCellValue());
            if(codProfiloCell!=null)
            	cmRecord.setCodiceProfilo(codProfiloCell.getStringCellValue());
            if(codCdrCell!=null)
            	cmRecord.setCodiceCdr(codCdrCell.getStringCellValue());
            if(dataScadenzaCell!=null){
            	if(dataScadenzaCell.getCellType() == CellType.NUMERIC){
            		 if (DateUtil.isCellDateFormatted(dataScadenzaCell)) {
            			 SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            			 cmRecord.setDataScadenza(dateFormat.format(dataScadenzaCell.getDateCellValue()));
            		 }
            	}else if (dataScadenzaCell.getCellType() == CellType.STRING){
            		cmRecord.setDataScadenza(dataScadenzaCell.getStringCellValue());
            	}else if (dataScadenzaCell.getCellType() == CellType.BLANK){
            		cmRecord.setDataScadenza(dataScadenzaCell.getStringCellValue());
            	}
            }else {
            	cmRecord.setDataScadenza("");
            }
		
            if(azioneCell!=null) {
            	cmRecord.setAzione(azioneCell.getStringCellValue());
            }
             listRecordFileInput.add(cmRecord);
		}
		return listRecordFileInput;
	}

	private int verificaPresenzaProfiloEcaAggiuntivo(FileAbilitazioneEcaBean datiRecord) throws SQLException{
		int numeroProfili = 0;
		
		InterrogazioneCaricamentiFinder finder = new InterrogazioneCaricamentiFinder();
		finder.setIdCaricamento(datiRecord.getIdCaricamento());
		finder.setCodiceFiscaleUtente(datiRecord.getCodiceFiscale());
		finder.setCodiceRoleGroup(datiRecord.getCodiceRoleGroup());
		finder.setCodiceProfilo(datiRecord.getCodiceProfilo());
		finder.setCodiceCdr(datiRecord.getCodiceCdr());
		finder.setDataScadenza(datiRecord.getDataScadenza());
		numeroProfili= verificaEsistenzaProfiloEca(finder);
		return numeroProfili;
	}
	
	private RichiestaAbilitazioneDisabilitazioneBean gestioneRecordZEca(FileAbilitazioneEcaBean fileAbilitazioneEcaBean, long sequenceEvento){
		// Concateno le varie stringhe di errore
		RichiestaAbilitazioneDisabilitazioneBean richiestaBatch = new RichiestaAbilitazioneDisabilitazioneBean();
		Date dataElaborazioneCorrente = Utils.getDateTimestamp();
    	richiestaBatch.setSequence(sequenceEvento);
    	richiestaBatch.setDataEvento(dataElaborazioneCorrente);
		richiestaBatch.setCodiceFiscale(fileAbilitazioneEcaBean.getCodiceFiscale());
		richiestaBatch.setEvento(evento);
		richiestaBatch.setRoleGroup(fileAbilitazioneEcaBean.getCodiceRoleGroup());
		richiestaBatch.setCodiceProfilo(fileAbilitazioneEcaBean.getCodiceProfilo());
		richiestaBatch.setAzione(fileAbilitazioneEcaBean.getAzione());
		richiestaBatch.setCodiceUfficio(fileAbilitazioneEcaBean.getCodiceUfficio());
		richiestaBatch.setCodiceCdr(fileAbilitazioneEcaBean.getCodiceCdr());
		richiestaBatch.setDataScadenza(fileAbilitazioneEcaBean.getDataScadenza());
//		richiestaBatch.setCodiceUfficio(codice_ufficio);
//		richiestaBatch.setCodiceCdr(codice_cdr);
		richiestaBatch.setIdRichiestaVisibilita(id_richiesta_visibilita);
		return richiestaBatch;
	}
	
	private RichiestaAbilitazioneDisabilitazioneBean gestioneRecordZ(CaricamentoMassivoRecord cmRecord, List<String> errorMessage, long sequenceEvento) throws ParseException{
		// Concateno le varie stringhe di errore
		RichiestaAbilitazioneDisabilitazioneBean richiestaBatch = new RichiestaAbilitazioneDisabilitazioneBean();
		Date dataElaborazioneCorrente = Utils.getDateTimestamp();
		richiestaBatch.setSequence(sequenceEvento);
    	richiestaBatch.setDataEvento(dataElaborazioneCorrente);
		richiestaBatch.setCodiceFiscale(cmRecord.getCodiceFiscale());
		richiestaBatch.setEvento(evento);
		richiestaBatch.setRoleGroup(cmRecord.getCodiceRoleGroup());
		richiestaBatch.setCodiceProfilo(cmRecord.getCodiceProfilo());
		richiestaBatch.setAzione(cmRecord.getAzione());
		
		// 4.5.3 2024 -->
		if(cmRecord.getDataScadenza()!=null) { // verificare ambito
			String dt = cmRecord.getDataScadenza();
			dt = dt.replace("/","");
			richiestaBatch.setDataScadenza(Utils.convertToDate(dt, "ddMMyyyy hh:mm:ss", true));
		}
		// 4.5.3 2024 <--
		
//		if(cmRecord.getDataScadenza()!=null && !cmRecord.getDataScadenza().trim().equals("")){
//			if(Utility.isValidDate(cmRecord.getDataScadenza())){
//				richiestaBatch.setDataScadenza(Utils.convertToDate(cmRecord.getDataScadenza(), "ddMMyyyy hh:mm:ss", true));
//			}
		richiestaBatch.setCodiceUfficio(codice_ufficio);
		richiestaBatch.setCodiceCdr(codice_cdr);
		richiestaBatch.setCdrVisibilita(cmRecord.getCodiceCdr());
		richiestaBatch.setIdRichiestaVisibilita(id_richiesta_visibilita);
//		if(richiestaBatch.getDataScadenza()!=null && !cmRecord.getDataScadenza().trim().equals("")){
//			if(Utility.isValidDate(cmRecord.getDataScadenza())){
//				richiestaBatch.setDataScadenza(Utils.convertToDate(cmRecord.getDataScadenza(), "ddMMyyyy hh:mm:ss", true));
//				
			    
		
			
		return richiestaBatch;
	}
	
	private FileAbilitazioneBean gestisciRecordFileAbilitazione(CaricamentoMassivoEntity caricamento, CaricamentoMassivoRecord cmRecord, String errore) throws ParseException{
		// Concateno le varie stringhe di errore
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		FileAbilitazioneBean fileAbilitazione = new FileAbilitazioneBean();
		fileAbilitazione.setIdCaricamento(caricamento.getIdCaricamento());
		if(errore == null)
			fileAbilitazione.setStato("Acquisito");
		else{
			fileAbilitazione.setStato("Scartato");
			fileAbilitazione.setMotivazioneScarto(errore);
		}
		fileAbilitazione.setCodiceFiscale(cmRecord.getCodiceFiscale());
		fileAbilitazione.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
		fileAbilitazione.setCodiceProfilo(cmRecord.getCodiceProfilo());
		fileAbilitazione.setCodiceCdr(cmRecord.getCodiceCdr());
//		fileAbilitazione.setDataScadenza(cmRecord.getDataScadenza());
		
		if(!COD_AMBITO_NI.equals(caricamento.getIdAmbito())) {
			String dt = cmRecord.getDataScadenza();
			dt = dt.replace("/","");
			fileAbilitazione.setDataScadenza(Utils.convertToDate(dt, "ddMMyyyy hh:mm:ss", true));
		}
		fileAbilitazione.setAzione(cmRecord.getAzione());

//		if(cmRecord.getDataScadenza()!=null && !cmRecord.getDataScadenza().trim().equals("")){
//			if(Utility.isValidDate(cmRecord.getDataScadenza())){
//				fileAbilitazione.setDataScadenza(Utils.convertToDate(cmRecord.getDataScadenza(), "ddMMyyyy hh:mm:ss", true));
//			}		
		
	
		
		return fileAbilitazione;
    	
	}
	
	private List<String> controllaSingoloRecord(CaricamentoMassivoRecord cmRecord, CaricamentoMassivoEntity caricamento, List<CaricamentoMassivoRecord> listRecordFileInput) throws SQLException, ParseException{
		fileDaAggiungere = false;

		recordAggiuntiEca = new ArrayList<FileAbilitazioneEcaBean>();
		List<String> errorMessage = new ArrayList<String>();
		if(!controllaCodiceFiscale(cmRecord.getCodiceFiscale())){
			String err_cf = "CF "+ cmRecord.getCodiceFiscale() + " non esistente";
			errorMessage.add(err_cf);
		}else if(controllaDisallineamentoCf(cmRecord.getCodiceFiscale())){
			String err_cf = "Il CF "+ cmRecord.getCodiceFiscale() + " risulta disallineato tra i sistemi CAU e SIGA3";
			errorMessage.add(err_cf);
		}
		if(!controllaCodiceRoleGroup(cmRecord.getCodiceRoleGroup())){
			String err_codrg = "Role group "+ cmRecord.getCodiceRoleGroup() + " non esistente";
			errorMessage.add(err_codrg);
		}
		List<String> err_prof = controllaCodiceProfilo(cmRecord.getCodiceProfilo(), caricamento.getIdAmbito(), cmRecord.getCodiceRoleGroup(), cmRecord.getAzione());
		if(err_prof.size()>0)
			errorMessage.addAll(err_prof);
		
		if(cmRecord.getDataScadenza() != null && !cmRecord.getDataScadenza().trim().equals("")){
			List<String> err_data = new ArrayList<String>();
			err_data = controllaDataScadenza(cmRecord);
			if(err_data.size()>0)
				errorMessage.addAll(err_data);
		}
		
		
		if(errorMessage.size() == 0){
			List<ProfiloCM> listProfili = getProfiliCM(cmRecord.getCodiceProfilo());
			ProfiloCM profilo = null;
			if(listProfili != null && listProfili.size()>0){
				profilo = listProfili.get(0);
//				se il profilo non e' verificato non ha senso che faccia i controlli sull'azione associatagli
				List<String> err_azione = controllaAzione(cmRecord, profilo, listRecordFileInput, caricamento.getIdCaricamento());
				if(err_azione.size()>0)
					errorMessage.addAll(err_azione);
			}
		}
		

		return errorMessage;
	}
	
	private List<String> controllaAzione(CaricamentoMassivoRecord cmRecord, ProfiloCM profilo, List<CaricamentoMassivoRecord> listRecordFileInput, String idCaricamento)throws SQLException, ParseException{
		List<String> messErr = new ArrayList<String>();
		if(cmRecord.getAzione() == null || cmRecord.getAzione().trim().equals(""))
			messErr.add("Abilitazione/Disabilitazione non inserita");
		else{
			String azione = cmRecord.getAzione().trim().toUpperCase();
			if(!azione.equals("ABILITAZIONE") && !azione.equals("DISABILITAZIONE"))
				messErr.add("Azione "+azione+ " non esistente");
			else{
				if(azione.equals("ABILITAZIONE")){
					cmRecord.setAzione("Abilitazione");
					id_richiesta_visibilita = 0L;
					profileEntitlement = null;
			 
					// 5.4.3 2024 --> per test locale
					//if(controllaCfRevocato(cmRecord.getCodiceFiscale())){
					if(1==2) { // 5.4.3 2024 --> rimuovere!!!!!!
						messErr.add("L'utente "+cmRecord.getCodiceFiscale() + " e' stato revocato." );
					}else{
						if(cmRecord.getCodiceCdr()==null || cmRecord.getCodiceCdr().trim().equals("") || cmRecord.getCodiceCdr().replace("0", "").trim().equals("")){
							/****************************************
							 * Siamo nel caso di ASSEGNAZIONE
							 ***************************************/
							// Vediamo se il profilo ha explict entitlement
							Ufficio datiUfficio = getDatiUfficioCM(cmRecord.getCodiceFiscale());
							if(profilo.getExplicitEntitlement()!=null && !profilo.getExplicitEntitlement().equals("")){
								profileEntitlement = profilo.getExplicitEntitlement();
								if(profilo.getExplicitEntitlement().trim().equals("CDR")){
									codice_cdr = datiUfficio.getCodiceCdR(); 
									codice_ufficio = null;
								}else if(profilo.getExplicitEntitlement().trim().equals("CODUFFICIO")){
									codice_ufficio = datiUfficio.getCodiceUfficio();
									codice_cdr = null;
								}
							}else{
								codice_ufficio = null;
								codice_cdr = null;
								/******************************************************************
								 * Sulle Specifiche questa parte corrisponde a 
								 * - Gestione Record A0
								 * - Gestione Record A1
								 ******************************************************************/
								
								
								// Effettuiamo i controlli per i profili ECA Figli ECA_xxxxx00nnn
								if(profilo.getCodiceProfilo().substring(0, 4).equals("ECA_") && !profilo.getCodiceProfilo().substring(9).equals("00000")){
									String cod = profilo.getCodiceProfilo().substring(6, 9);
									// Vediamo se nel file di caricamento e' presente il profilo Padre e che 
									// anche per tale profilo l'azione sia "Abilitazione"
									Iterator<CaricamentoMassivoRecord> rowFileIterator = listRecordFileInput.iterator();
									boolean padreTrovato = false;
									while(rowFileIterator.hasNext()){
										CaricamentoMassivoRecord singoloRecordCaricamento = rowFileIterator.next();
										String codProfRecFile = singoloRecordCaricamento.getCodiceProfilo();
										if(codProfRecFile != null && codProfRecFile.length()>11 && codProfRecFile.substring(11).equals("000") && codProfRecFile.substring(0, 11).equals("ECA_00"+cod+"00")){
											// Ho identificato il padre all'interno del file
											if(singoloRecordCaricamento.getCodiceFiscale() != null && singoloRecordCaricamento.getCodiceFiscale().trim().equalsIgnoreCase(cmRecord.getCodiceFiscale().trim())){
												if(singoloRecordCaricamento.getAzione()!=null && !singoloRecordCaricamento.getAzione().trim().equals("") && singoloRecordCaricamento.getAzione().trim().equalsIgnoreCase("DISABILITAZIONE"))
													messErr.add("Presente record di disabilitazione per il profilo "+codProfRecFile);
												else
													padreTrovato=true;
											}
										}
									}
									if(!padreTrovato){
										HashMap<String,String> queryMap = new HashMap<String,String>();
										String codProfiloPadre = "ECA_00"+cod+"00000";
										queryMap.put("codFiscale",cmRecord.getCodiceFiscale()); 
										queryMap.put("codProfiloPadre",codProfiloPadre);
										String codProfiloPadreDb = getProfiloEcaPadreInPau(queryMap);
										if(codProfiloPadreDb==null || codProfiloPadreDb.trim().equals("")){
											FileAbilitazioneEcaBean recordDaAggiungere = new FileAbilitazioneEcaBean();
											recordDaAggiungere.setStato("Aggiunto ECA");
											recordDaAggiungere.setCodiceProfilo("ECA_00"+cod+"00000");
											recordDaAggiungere.setCodiceFiscale(cmRecord.getCodiceFiscale());
											recordDaAggiungere.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
     										recordDaAggiungere.setCodiceCdr(cmRecord.getCodiceCdr());
     										
											String dt = cmRecord.getDataScadenza();
											dt = dt.replace("/","");
											recordDaAggiungere.setDataScadenza(Utils.convertToDate(dt, "ddMMyyyy hh:mm:ss", true));
//											recordDaAggiungere.setDataScadenza(cmRecord.getDataScadenza());
											recordDaAggiungere.setAzione(cmRecord.getAzione());
											recordDaAggiungere.setCodiceUfficio(datiUfficio.getCodiceUfficio());
											recordAggiuntiEca.add(recordDaAggiungere);
											fileDaAggiungere = true;
											// Va inserito un record su richieste_batch e uno su cm_file_abilitaizoni
											// controllando che non ce ne sia gie' uno con stessa tripla cf - codice role group - codie profilo - codice Cdr
											
											//messErr.add("Per l'utente non risulta assegnata l'abilitazione "+codProfiloPadre);
										}
									}
								}
							}
							List<String> erroriRecordA = gestioneRecordA(cmRecord);
							messErr.addAll(erroriRecordA);
						}else{
							/****************************************
							 * Siamo nel caso di VISIBILITA'
							 * 
							 * ossia cmRecord.getCodiceCdr()!=null
							 ***************************************/
							fileLog.info("*********************************** " +cmRecord.getCodiceProfilo()); 
							HashMap<String,String> queryMap = new HashMap<String,String>();
							queryMap.put("codFiscale",cmRecord.getCodiceFiscale()); 
							queryMap.put("codCdr",cmRecord.getCodiceCdr());
							UtenteInVisibilitaCM utCM = getUtenteVisibilitaCM(queryMap);
							if(utCM == null){
								messErr.add("L'operatore non e' in visibilite' nel CdR indicato");
							}else{
								profileEntitlement = profilo.getExplicitEntitlement();
								id_richiesta_visibilita = Long.parseLong(utCM.getIdRichiestaVisibilita());
								if(profileEntitlement==null){
									codice_ufficio = null;
									codice_cdr = null;
									
									// Effettuiamo i controlli per i profili ECA Figli ECA_xxxxx00nnn
									if(cmRecord.getCodiceProfilo().substring(0, 4).equals("ECA_") && !cmRecord.getCodiceProfilo().substring(9).equals("00000")){
										String cod = cmRecord.getCodiceProfilo().substring(6, 9);
										// Vediamo se nel file di caricamento e' presente il profilo Padre e che 
										// anche per tale profilo l'azione sia "Abilitazione"
										Iterator<CaricamentoMassivoRecord> rowFileIterator = listRecordFileInput.iterator();
										boolean padreTrovato = false;
										while(rowFileIterator.hasNext()){
											CaricamentoMassivoRecord singoloRecordCaricamento = rowFileIterator.next();
											String codProfRecFile = singoloRecordCaricamento.getCodiceProfilo();
											if(codProfRecFile != null && codProfRecFile.length()>11 && codProfRecFile.substring(11).equals("000") && codProfRecFile.substring(0, 11).equals("ECA_00"+cod+"00")){
												// Ho identificato il padre
												// Ora verifico che i Cdr Corrispondano
												if(singoloRecordCaricamento.getCodiceCdr()!=null && !singoloRecordCaricamento.getCodiceCdr().trim().equals("") && singoloRecordCaricamento.getCodiceCdr().trim().equalsIgnoreCase(cmRecord.getCodiceCdr())){
													if(singoloRecordCaricamento.getAzione().trim().equalsIgnoreCase("DISABILITAZIONE"))
														messErr.add("Presente record di disabilitazione per il profilo "+"ECA_00"+cod+"00000" + " per il CdR " +cmRecord.getCodiceCdr());
													else{
														padreTrovato = true;
														fileLog.info("XXXXXXXXXXXX PADRE TROVATO " + codProfRecFile + " DEL FIGLIO " + cmRecord.getCodiceProfilo());
													}
												}
//												else
//													messErr.add("Non esiste il profilo "+"ECA_00"+cod+"00000" + " per il CdR " +cmRecord.getCodiceCdr());
											}
										}
										if(!padreTrovato){
											fileLog.info("XXXXXXXXXXXX PADRE NON TROVATO  DEL FIGLIO " + cmRecord.getCodiceProfilo());
											HashMap<String,String> queryMap2 = new HashMap<String,String>();
											String codProfiloPadre = "ECA_00"+cod+"00000";
											queryMap2.put("codFiscale",cmRecord.getCodiceFiscale()); 
											queryMap2.put("codProfiloPadre",codProfiloPadre);
											queryMap2.put("idRichiestaVisibilita",String.valueOf(id_richiesta_visibilita));
											String codProfiloPadreDb = getProfiloEcaPadreInPau(queryMap2);
											if(codProfiloPadreDb==null || codProfiloPadreDb.trim().equals("")){
												// Va aggiunto anche il profilo padre ECA_00xxx00000
												FileAbilitazioneEcaBean recordDaAggiungere = new FileAbilitazioneEcaBean();
												recordDaAggiungere.setStato("Aggiunto ECA");
												recordDaAggiungere.setCodiceProfilo("ECA_00"+cod+"00000");
												recordDaAggiungere.setCodiceFiscale(cmRecord.getCodiceFiscale());
												recordDaAggiungere.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
//												recordDaAggiungere.setCodiceCdr(cmRecord.getCodiceCdr());
												String dt = cmRecord.getDataScadenza();
												dt = dt.replace("/","");
												recordDaAggiungere.setDataScadenza(Utils.convertToDate(dt, "ddMMyyyy hh:mm:ss", true));
												recordDaAggiungere.setAzione(cmRecord.getAzione());
												recordDaAggiungere.setCodiceUfficio(utCM.getCodiceUfficio());
												recordAggiuntiEca.add(recordDaAggiungere);
												fileDaAggiungere = true;
//												messErr.add("Non esiste il profilo "+codProfiloPadre+ " per il CdR " +cmRecord.getCodiceCdr());
											}
										}
									}
								}else if(profileEntitlement!= null && profileEntitlement.trim().equals("CDR")){
									codice_cdr = cmRecord.getCodiceCdr(); 
									codice_ufficio = null;
								}else if(profileEntitlement!=null && profileEntitlement.trim().equals("CODUFFICIO")){
									codice_ufficio = utCM.getCodiceUfficio();
									codice_cdr = null;
								}
								List<String> erroriRecordB = gestioneRecordB(cmRecord, utCM);
								messErr.addAll(erroriRecordB);
							}
						}
					}
					
				}else if(azione.equals("DISABILITAZIONE")){
					cmRecord.setAzione("Disabilitazione");
					id_richiesta_visibilita = 0L;
					if(cmRecord.getCodiceCdr()==null || cmRecord.getCodiceCdr().trim().equals("") || cmRecord.getCodiceCdr().replace("0", "").trim().equals("")){
						HashMap<String,String> queryMap = new HashMap<String,String>();
						queryMap.put("codFiscale",cmRecord.getCodiceFiscale()); 
						queryMap.put("codRoleGroup",cmRecord.getCodiceRoleGroup());
						queryMap.put("codProfilo",cmRecord.getCodiceProfilo());
						ProfiliAttiviUtente pau = getProfiloAttivoUtenteCM(queryMap);
						if(pau == null){
							messErr.add("L'abilitazione da rimuovere non e' tra quelle assegnate all'operatore nel CdR di assegnazione");
						}else{
							String idRegistroRichiesta = getIdRegistroRichiestaCM(queryMap);
							if(idRegistroRichiesta != null)
								messErr.add("E' in corso la richiesta "+idRegistroRichiesta +" per stesso profilo per il CdR di assegnazione");
							else{
								codice_cdr = pau.getCodiceCdR(); 
								codice_ufficio = pau.getCodiceUfficio();
								
								// Effettuiamo i controlli per i profili ECA Padre ECA_xxxxx00000
								// Per i quali va verificato che sul file vengano disabilitati anche tutti i figli
								if(cmRecord.getCodiceProfilo().substring(0, 4).equals("ECA_") && cmRecord.getCodiceProfilo().substring(9).equals("00000")){
									HashMap<String,String> queryMap2 = new HashMap<String,String>();
									queryMap2.put("codFiscale",cmRecord.getCodiceFiscale()); 
									queryMap2.put("codProfilo",cmRecord.getCodiceProfilo().substring(0, 11));
									List<String> codiciProfEcaDaAggiungere = new ArrayList<String>();
									List<String> codiciProfEca = getCodProfiloDaProfiliAttiviUtenteEca(queryMap2);
//									String cod = cmRecord.getCodiceProfilo().substring(6, 9);
									for(int f=0; f<codiciProfEca.size(); f++){
										String cpe = codiciProfEca.get(f);
										boolean profiloPresente = false;
										Iterator<CaricamentoMassivoRecord> rowFileIterator = listRecordFileInput.iterator();
										int numFigliInFile = 0;
										while(rowFileIterator.hasNext()){
											CaricamentoMassivoRecord singoloRecordCaricamento = rowFileIterator.next();
											String codProfRecFile = singoloRecordCaricamento.getCodiceProfilo();
											if(codProfRecFile != null && codProfRecFile.length()>11 && codProfRecFile.equals(cpe) 
													&& cmRecord.getCodiceFiscale().equalsIgnoreCase(singoloRecordCaricamento.getCodiceFiscale()) 
													&& (singoloRecordCaricamento.getCodiceCdr()==null || singoloRecordCaricamento.getCodiceCdr().trim().equals("")) 
													&& cmRecord.getAzione().equalsIgnoreCase(singoloRecordCaricamento.getAzione())){
												profiloPresente=true;
											}
										}
										if(!profiloPresente)
											codiciProfEcaDaAggiungere.add(cpe);
									}
									
									
									for(int t=0; t<codiciProfEcaDaAggiungere.size(); t++){
										// Va aggiunto il secondo pezzo per scrivere i record Aggiuntivi del Profilo ECA
										FileAbilitazioneEcaBean recordDaAggiungere = new FileAbilitazioneEcaBean();
										recordDaAggiungere.setStato("Aggiunto ECA");
										recordDaAggiungere.setCodiceProfilo(codiciProfEcaDaAggiungere.get(t));
										recordDaAggiungere.setCodiceFiscale(cmRecord.getCodiceFiscale());
										recordDaAggiungere.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
										String dt = cmRecord.getDataScadenza();
										dt = dt.replace("/","");
										recordDaAggiungere.setDataScadenza(Utils.convertToDate(dt, "ddMMyyyy hh:mm:ss", true));
//										recordDaAggiungere.setCodiceCdr(cmRecord.getCodiceCdr());
										recordDaAggiungere.setAzione(cmRecord.getAzione());
//										recordDaAggiungere.setCodiceUfficio(codProfiloPadreDb);
										recordAggiuntiEca.add(recordDaAggiungere);
										fileDaAggiungere = true;
									}
									
									
									/********************************************************************
									 * Vecchia Sezione "Gestione Record D1" Precedente a 10/12/2015
									 
									Integer pauEca = countProfiliAttiviUtenteEca(queryMap2);
									
									String cod = cmRecord.getCodiceProfilo().substring(6, 9);
									
									Iterator<CaricamentoMassivoRecord> rowFileIterator = listRecordFileInput.iterator();
									int numFigliInFile = 0;
									while(rowFileIterator.hasNext()){
										CaricamentoMassivoRecord singoloRecordCaricamento = rowFileIterator.next();
										String codProfRecFile = singoloRecordCaricamento.getCodiceProfilo();
										if(codProfRecFile != null && codProfRecFile.length()>11 && codProfRecFile.substring(0, 11).equals("ECA_00"+cod+"00")){
											if(cmRecord.getCodiceFiscale().equalsIgnoreCase(singoloRecordCaricamento.getCodiceFiscale()) && (singoloRecordCaricamento.getCodiceCdr()==null || singoloRecordCaricamento.getCodiceCdr().trim().equals("")))
												numFigliInFile ++;
										}
									}
									if(pauEca != numFigliInFile){
										messErr.add("Disabilitazione dl profilo " + cmRecord.getCodiceProfilo() + " senza contestuale disabilitazione di tutte le linee");
									}
									**************************************************************************/
									
								}
							}
						}
					}else{
						// codiceCdr != null e codiceCdr != ""
						HashMap<String,String> queryMap = new HashMap<String,String>();
						queryMap.put("codFiscale",cmRecord.getCodiceFiscale()); 
						queryMap.put("codCdr",cmRecord.getCodiceCdr());
						UtenteInVisibilitaCM utCM = getUtenteVisibilitaCM(queryMap);
						if(utCM == null){
							messErr.add("L'operatore non e' in visibilite' nel CdR indicato");
						}else{
							HashMap<String,String> queryMap2 = new HashMap<String,String>();
							queryMap.put("codFiscale",cmRecord.getCodiceFiscale()); 
							queryMap.put("codRoleGroup",cmRecord.getCodiceRoleGroup());
							queryMap.put("codProfilo",cmRecord.getCodiceProfilo());
							queryMap.put("idRichiestaVisibilita",utCM.getIdRichiestaVisibilita());
							ProfiliAttiviUtente pau = getProfiloAttivoUtenteCM(queryMap);
							if(pau == null){
								messErr.add("L'abilitazione da rimuovere non e' tra quelle assegnate all'operatore nel CdR della visibilite'");
							}else{
								String idRegistroRichiesta = getIdRegistroRichiestaCM(queryMap);
								if(idRegistroRichiesta != null)
									messErr.add("E' in corso la richiesta "+idRegistroRichiesta +" per stesso profilo per il CdR di visibilite'");
								else{
									codice_cdr = pau.getCodiceCdR(); 
									codice_ufficio = pau.getCodiceUfficio();
									id_richiesta_visibilita = Long.parseLong(utCM.getIdRichiestaVisibilita());
									
									// Effettuiamo i controlli per i profili ECA Padre ECA_xxxxx00000
									// Per i quali va verificato che sul file vengano disabilitati anche tutti i figli
									if(cmRecord.getCodiceProfilo().substring(0, 4).equals("ECA_") && cmRecord.getCodiceProfilo().substring(9).equals("00000")){
										HashMap<String,String> queryMap3 = new HashMap<String,String>();
										queryMap3.put("codFiscale",cmRecord.getCodiceFiscale()); 
										queryMap3.put("codProfilo",cmRecord.getCodiceProfilo().substring(0, 11));
										queryMap3.put("idRichiestaVisibilita",String.valueOf(id_richiesta_visibilita));
										
										List<String> codiciProfEcaDaAggiungere = new ArrayList<String>();
										List<String> codiciProfEca = getCodProfiloDaProfiliAttiviUtenteEca(queryMap3);
										
										
										for(int f=0; f<codiciProfEca.size(); f++){
											String cpe = codiciProfEca.get(f);
											boolean profiloPresente = false;
											Iterator<CaricamentoMassivoRecord> rowFileIterator = listRecordFileInput.iterator();
											int numFigliInFile = 0;
											while(rowFileIterator.hasNext()){
												CaricamentoMassivoRecord singoloRecordCaricamento = rowFileIterator.next();
												String codProfRecFile = singoloRecordCaricamento.getCodiceProfilo();
												if(codProfRecFile != null && codProfRecFile.length()>11 && codProfRecFile.equals(cpe) && cmRecord.getCodiceFiscale().equalsIgnoreCase(singoloRecordCaricamento.getCodiceFiscale()) && cmRecord.getCodiceCdr().trim().equalsIgnoreCase(singoloRecordCaricamento.getCodiceCdr())){
													profiloPresente=true;
												}
											}
											if(!profiloPresente)
												codiciProfEcaDaAggiungere.add(cpe);
										}
										
										
										for(int t=0; t<codiciProfEcaDaAggiungere.size(); t++){
											// Va aggiunto il secondo pezzo per scrivere i record Aggiuntivi del Profilo ECA
											FileAbilitazioneEcaBean recordDaAggiungere = new FileAbilitazioneEcaBean();
											recordDaAggiungere.setStato("Aggiunto ECA");
											recordDaAggiungere.setCodiceProfilo(codiciProfEcaDaAggiungere.get(t));
											recordDaAggiungere.setCodiceFiscale(cmRecord.getCodiceFiscale());
											recordDaAggiungere.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
											recordDaAggiungere.setCodiceCdr(cmRecord.getCodiceCdr());
											
											String dt = cmRecord.getDataScadenza();
											dt = dt.replace("/","");
											recordDaAggiungere.setDataScadenza(Utils.convertToDate(dt, "ddMMyyyy hh:mm:ss", true));
											
											recordDaAggiungere.setAzione(cmRecord.getAzione());
//											recordDaAggiungere.setCodiceUfficio(codProfiloPadreDb);
											recordAggiuntiEca.add(recordDaAggiungere);
											fileDaAggiungere = true;
										}
										
										/****************************************************************
										 * Vecchia Sezione "Gestione Record D1" Precedente a 10/12/2015
										 
										Integer pauEca = countProfiliAttiviUtenteEca(queryMap3);
										
										// Contiamo i Profili ECA Figli all'interno del File Di caricamento
										String cod = cmRecord.getCodiceProfilo().substring(6, 9);
										Iterator<CaricamentoMassivoRecord> rowFileIterator = listRecordFileInput.iterator();
										int numFigliInFile = 0;
										while(rowFileIterator.hasNext()){
											CaricamentoMassivoRecord singoloRecordCaricamento = rowFileIterator.next();
											String codProfRecFile = singoloRecordCaricamento.getCodiceProfilo();
											if(codProfRecFile != null && codProfRecFile.length()>11 && codProfRecFile.substring(0, 11).equals("ECA_00"+cod+"00")){
												if(cmRecord.getCodiceFiscale().equalsIgnoreCase(singoloRecordCaricamento.getCodiceFiscale()) && cmRecord.getCodiceCdr().trim().equalsIgnoreCase(singoloRecordCaricamento.getCodiceCdr()) )
													numFigliInFile ++;
											}
										}
										if(pauEca != numFigliInFile){
											messErr.add("Disabilitazione dl profilo " + cmRecord.getCodiceProfilo() + " senza contestuale disabilitazione di tutte le linee");
										}
										*/
									}
								}
							}
						}
					}
				}
			}
		}
		return messErr;
	}
	
	private List<String> controllaCodiceProfilo(String codiceProfilo, String idAmbito, String codiceRoleGroup, String azione)throws SQLException{
		List<String> messErr = new ArrayList<String>();
		if(codiceProfilo == null || codiceProfilo.trim().equals(""))
			messErr.add("Profilo non inserito");
		else{
			List<ProfiloCM> listProfili = getProfiliCM(codiceProfilo);
			if(listProfili==null || listProfili.size()==0)
				messErr.add("Profilo "+codiceProfilo+ " non esistente");
			else{
				boolean ambitoOk = false;
				for(int k=0; k<listProfili.size(); k++){
					ProfiloCM pCM = listProfili.get(k);
					if(pCM.getCodiceAmbito().equals(idAmbito))
						ambitoOk = true;
				}
				if(!ambitoOk)
					messErr.add("Il profilo "+codiceProfilo+ " non appartiene all'ambito "+ getDescrizioneAmbito(idAmbito));
				HashMap<String,String> queryMap = new HashMap<String,String>();
				queryMap.put("codRoleGroup",codiceRoleGroup); 
				queryMap.put("codProfilo",codiceProfilo);
				if(azione!=null)
					queryMap.put("azione",azione.toUpperCase());
				List<String> codiciApp = getCodiciAppCM(queryMap);
				if(codiciApp == null || codiciApp.size()==0)
					messErr.add("Non e' prevista la combinazione role group profilo");
			}
		}
		
		return messErr;
	}
	
	private List<String> controllaDataScadenza(CaricamentoMassivoRecord cmRecord){
		List<String> messErr = new ArrayList<String>();
		String dataScadenza = cmRecord.getDataScadenza();
		if(dataScadenza != null && !dataScadenza.trim().equals("")){
			if(!Utility.isValidDate(cmRecord.getDataScadenza()))
				messErr.add("La data di scadenza inserita non e' una data valida " + cmRecord.getDataScadenza());
			else{
				Calendar cal = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				DateFormat format = new SimpleDateFormat("ddMMyyyy");
				Date date;
				try {
					date = format.parse(dataScadenza);
					cal.setTime(date);
					cal2.add(Calendar.DATE, 7);
					if(cal.before(cal2))
						messErr.add("Data di scadenza errata");
				} catch (ParseException e) {
					messErr.add("La data di scadenza inserita non e' una data valida " + cmRecord.getDataScadenza() );
				}
				
			}
		}
		return messErr;
	}
	
	private boolean controllaCodiceFiscale(String cf)throws SQLException{
		boolean esito = false;
		if(cf == null || cf.trim().equals(""))
			return esito;
		if(verificaEsistenzaCf(cf)>0){
			esito = true;
		}
		return esito;
	}
	
	private boolean controllaDisallineamentoCf(String cf)throws SQLException{
		boolean esito = false;
		if(verificaDisallineamentoCf(cf)>0){
			esito = true;
		}
		return esito;
	}
	
	private boolean controllaCodiceRoleGroup(String codRg)throws SQLException{
		boolean esito = false;
		if(codRg == null || codRg.trim().equals(""))
			return esito;
		if(verificaEsistenzaRG(codRg)>0){
			esito = true;
		}
		return esito;
	}
	
	private EventoBean recuperaDatiEventoSistema(String codiceEvento)throws SQLException{
		EventoBean datiEvento = new EventoBean();
		datiEvento = getEventoCM(codiceEvento);
		return datiEvento;
		
	}
	
	private List<String> gestioneRecordA(CaricamentoMassivoRecord cmRecord)throws SQLException{
		List<String> erroriRecordA = new ArrayList<String>();
		HashMap<String,String> queryMap = new HashMap<String,String>();
		queryMap.put("codRoleGroup",cmRecord.getCodiceRoleGroup()); 
		queryMap.put("codProfilo",cmRecord.getCodiceProfilo());
		queryMap.put("codFiscale",cmRecord.getCodiceFiscale());
		String codiceProfilo = getProfDaProfiloAttivoCM(queryMap);
		if(codiceProfilo!=null && !codiceProfilo.trim().equals(""))
			erroriRecordA.add("L'abilitazione e' tra quelle gie' assegnate all'operatore del CdR di assegnazione");
		String idRegistroRichiesta = getIdRegistroRichiestaCM(queryMap);
		if(idRegistroRichiesta != null)
			erroriRecordA.add("E' in corso la richiesta "+idRegistroRichiesta +" per stesso profilo per il CdR di assegnazione");
		
		return erroriRecordA;
		
	}
	
	private List<String> gestioneRecordB(CaricamentoMassivoRecord cmRecord, UtenteInVisibilitaCM utCM)throws SQLException{
		List<String> erroriRecordB = new ArrayList<String>();
		HashMap<String,String> queryMap = new HashMap<String,String>();
		queryMap.put("codRoleGroup",cmRecord.getCodiceRoleGroup()); 
		queryMap.put("codProfilo",cmRecord.getCodiceProfilo());
		queryMap.put("codFiscale",cmRecord.getCodiceFiscale());
		queryMap.put("idRichiestaVisibilita",utCM.getIdRichiestaVisibilita());
		String profiloAssegnato = getProfiloAssegnatoCM(queryMap);
		if(profiloAssegnato == null){
			erroriRecordB.add("L'abilitazione non e' tra quelle previste nella richiesta di visibilita");
		}else if(profiloAssegnato.trim().equalsIgnoreCase("SI")){
			erroriRecordB.add("L'abilitazione e' tra quelle gie' assegnate all'operatore nel CdR di visibilita");
		}else{
			String idRegistroRichiesta = getIdRegistroRichiestaCM(queryMap);
			if(idRegistroRichiesta != null)
				erroriRecordB.add("E' in corso la richiesta "+idRegistroRichiesta +" per stesso profilo per il CdR di visibilite'");
		}
		return erroriRecordB;
		
	}
	
	private void generazioneRichiesteBatch(InterrogazioneCaricamentiFinder finder, EventoBean datiEvento, String allegatiFilePath) throws Exception{
		
		List<CaricamentoMassivoEntity> listaCaricamenti = new ArrayList<CaricamentoMassivoEntity>();
		listaCaricamenti= getListaCaricamentiControllati(finder);
		for(int i=0; i<listaCaricamenti.size(); i++){
			CaricamentoMassivoEntity cmRichEntity = listaCaricamenti.get(i);
			
			// Recupero gli allegati del richiedente relativi a quella richiesta di caricamento
			List<AllegatoCaricamentoMassivoEntity> listAllegatiRic = new ArrayList<AllegatoCaricamentoMassivoEntity>();
			finder.setTipoAllegato("RI");
			listAllegatiRic = getElencoAllegatiCaricamento(finder);
			
			
			Long sequence = cmRichEntity.getSequenceRichiesteGenerate();
			List<RichiestaBatchBean> richiesteBatch = new ArrayList<RichiestaBatchBean>();
			richiesteBatch = getListaRichiesteBatchBySequence(sequence);
			for(int c=0;c<richiesteBatch.size(); c++){
				// Inserisco un record in REGISTRO_RICHIESTA
				long idRichiesta = getIdRichFromSequenceIdRichiesta();
				RichiestaBatchBean richBatch = richiesteBatch.get(c);
				RegistroRichiesteBatchBean regRichBean = new RegistroRichiesteBatchBean();
				regRichBean.setIdCm(Integer.parseInt(cmRichEntity.getIdCaricamento()));
				regRichBean.setIdRichiesta(idRichiesta);
				regRichBean.setCfRichiedente(cmRichEntity.getCfRichiedente());
				regRichBean.setCfRichiedenteEffettivo(cmRichEntity.getCfRichiedente());
				regRichBean.setCfAutorizzatore1(cmRichEntity.getCfAutorizzatore());
				regRichBean.setCfAutorizzatore1Effettivo(cmRichEntity.getCfAutorizzatore());
				if(cmRichEntity.getNoteRichiedente()==null || cmRichEntity.getNoteRichiedente().trim().equals(""))
					regRichBean.setNoteRichiedente(NESSUNA_NOTA_INSERITA);
				else
					regRichBean.setNoteRichiedente(cmRichEntity.getNoteRichiedente());
				regRichBean.setNotaGenerazioneRichiesta(cmRichEntity.getNoteRichiedente());
				regRichBean.setCodiceAmbitoAutorizzazione(cmRichEntity.getIdAmbito());
				regRichBean.setCodiceAmbito(cmRichEntity.getIdAmbito());
				regRichBean.setCfUtente(richBatch.getCf());
				/******************************************************************************************
				 * Modifica 15/02/2018
				 * Se sulla tabella richieste_batch (richBatch) il campo codice_cdr_visibilita e' null
				 * impostiamo il cod cdr utente recuperandolo dal cod cf dell'utente, 
				 * altrimenti in cdr_utente di REGISTRO_RICHIESTE scriviamo il codice_cdr_visibilita della 
				 * tabella richieste_batch
				 ******************************************************************************************/
				if(richBatch.getCdrVisibilita()!=null && !richBatch.getCdrVisibilita().trim().equals(""))
					regRichBean.setCdrUtente(richBatch.getCdrVisibilita());
				else
					regRichBean.setCdrUtente(getCodCdrByCf(richBatch.getCf()));
				
				
				regRichBean.setRichiedenteAc(RICHIEDENTE_AC);
				regRichBean.setNotaAutorizzatore1(NESSUNA_NOTA_INSERITA);
				
				if(datiEvento.getArchiviazione()!=null && datiEvento.getArchiviazione().trim().equals("SI")){
					/**************************************************************
					 * Modifica di 02/2018
					 * I dati dell'archiviazione si ricavano in modo differente 
					 * a secondo se il campo CODICE_CDR_VISIBILITA della tabella 
					 * RICHIESTE_BATCH e' valorizzato o meno
					 *************************************************************/
					if(richBatch.getCdrVisibilita()==null || richBatch.getCdrVisibilita().trim().equals("")){
						String cfRichiedente = getCfRichAutor(richBatch.getCf());
						if(cfRichiedente != null && !cfRichiedente.trim().equals("")){
							regRichBean.setCfArchiviazione(cfRichiedente);
						}else{
							RichiedenteCdR datiRichiedente = getDatiRichiedenteCdr(richBatch.getCf());
							if(datiRichiedente.getCfRichiedente()!=null){
								regRichBean.setCfArchiviazione(datiRichiedente.getCfRichiedente());
								regRichBean.setCdrArchiviazione(datiRichiedente.getCodiceCdR());
							}else{
								OperatoreFinder opFin = new OperatoreFinder(richBatch.getCf());
								OperatoreBean opBean = getDatiUtente(opFin);
								/*************************************************************************
								 * MEV 05/2019 - doc specifiche "caricamenti massivi v18 11 mar 2019.docx"
								 *************************************************************************/
								RichiedenteCdR datiResp = getDatiResponsabileCdrSuperiore(opBean.getCdr());
								if(datiResp != null){
									regRichBean.setCdrArchiviazione(datiResp.getCodiceCdR());
									regRichBean.setCfArchiviazione(datiResp.getCfResponsabileHR());
								}else{
									RichiedenteCdR respStrut = getDatiResponsabileStruttura(datiRichiedente.getCodiceCdR());
									if(respStrut!=null){
										regRichBean.setCdrArchiviazione(respStrut.getCodiceCdR());
										regRichBean.setCfArchiviazione(respStrut.getCfResponsabileHR());
									}else{
										regRichBean.setCfArchiviazione(cmRichEntity.getCfRichiedente());
									}
								}
							}
						}
					}else{
						RichiedenteCdR datiRichiedente = new RichiedenteCdR();
						datiRichiedente = getDatiRichiedenteCdrByCdrVis(richBatch.getCdrVisibilita());
						if(datiRichiedente.getCfRichiedente()!=null){
							regRichBean.setCfArchiviazione(datiRichiedente.getCfRichiedente());
							regRichBean.setCdrArchiviazione(richBatch.getCdrVisibilita());
						}else{
							/*************************************************************************
							 * MEV 05/2019 - doc specifiche "caricamenti massivi v18 11 mar 2019.docx"
							 *************************************************************************/
							RichiedenteCdR datiResp = getDatiResponsabileCdrSuperiore(richBatch.getCdrVisibilita());
							if(datiResp != null){
								regRichBean.setCdrArchiviazione(datiResp.getCodiceCdR());
								regRichBean.setCfArchiviazione(datiResp.getCfResponsabileHR());
							}else{
								RichiedenteCdR respStrut = getDatiResponsabileStruttura(richBatch.getCdrVisibilita());
								if(respStrut!=null){
									regRichBean.setCdrArchiviazione(respStrut.getCodiceCdR());
									regRichBean.setCfArchiviazione(respStrut.getCfResponsabileHR());
								}else{
									regRichBean.setCfArchiviazione(cmRichEntity.getCfRichiedente());
								}
							}
						}
					}
				}else if(datiEvento.getArchiviazione()!=null && datiEvento.getArchiviazione().trim().equals("NO")){
					/*************************************************************************
					 * MEV 05/2019 - doc specifiche "caricamenti massivi v18 11 mar 2019.docx"
					 *************************************************************************/
					regRichBean.setCfArchiviazione(cmRichEntity.getCfRichiedente());
					regRichBean.setCfRichiedente(cmRichEntity.getCfRichiedente());
				}
				
				SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); 
				String dataOdiernaString = dt.format(new Date());
				Date dataOdierna = dt.parse(dataOdiernaString);
				regRichBean.setTipoRichiesta("P");
				regRichBean.setRichiedenteAc("SI");
				regRichBean.setCancellaUtenteEsterno("NO");
				regRichBean.setStatoRichiesta("LAV");
				regRichBean.setPresaVisione(datiEvento.getPresaVisione());
				regRichBean.setDataRichiesta(dataOdierna);
				regRichBean.setDataAutorizzazione1(dataOdierna); 
				regRichBean.setDataEsitoFinale(new Date());
				regRichBean.setEsitoFinale("AU-D");
				regRichBean.setCodiceEvento(richBatch.getEvento());
				regRichBean.setRichiestaVisibileRuoliSiga(datiEvento.getRichiestaVisibileRuoliSiga());
				regRichBean.setAggiornaSistemaAutorizzazione(datiEvento.getAggiornaSistAutorizzazione());
				regRichBean.setCodiceEvento(codEvento);
				regRichBean.setRichiedenteAc(RICHIEDENTE_AC);
				inserisciRegistroRichiesta(regRichBean);
				
				// Inserisco i documenti del richiedente in DOCUMENTI_RICHIESTA 
				DocumentoFinder docFinder;
				for(int j=0; j<listAllegatiRic.size(); j++){
					AllegatoCaricamentoMassivoEntity allegato = listAllegatiRic.get(j);
					docFinder = new DocumentoFinder((int)(long)idRichiesta);
					docFinder.setbFile(allegato.getbFileAllegato());
					docFinder.setFileName(allegato.getNomeFile());
					inserisciFile(docFinder);
				}
				
				// Recupero i profili relativi alla chiave Sequence, Cf, Id_Richiesta_Profili_Attivi_Utente
				// Per ogni profilo effettuo una scrittura su PROFILO_RICHIESTE
				InterrogazioneCaricamentiFinder finder2 = new InterrogazioneCaricamentiFinder();
				finder2.setCodiceFiscaleUtente(richBatch.getCf());
				finder2.setSequence(richBatch.getSequence());
				finder2.setIdRichiesta(richBatch.getIdRichiestaVisibilita());
				List<RichiestaBatchBean> listaProfili = getProfiliPerRichiesta(finder2);
				for(int j=0; j<listaProfili.size(); j++){
					RichiestaBatchBean datiProfilo = listaProfili.get(j);
					// Inserisco un record in PROFILO_RICHIESTA
					ProfiloRichiestaBean profRichBean = new ProfiloRichiestaBean();
					profRichBean.setIdRichiesta(idRichiesta);
					profRichBean.setCodiceRoleGroup(datiProfilo.getCodRoleGroup());
					ProfiloRichiestaBean prof = getCodApplicazione(datiProfilo.getCodProfilo());
					profRichBean.setCodiceApplicazione(prof.getCodiceApplicazione());
					profRichBean.setCodiceProfilo(datiProfilo.getCodProfilo());
					profRichBean.setCodiceUfficio(datiProfilo.getCodUfficio());
					profRichBean.setCodiceCdr(datiProfilo.getCodCdr());
					profRichBean.setDataScadenza(datiProfilo.getDataScadenza());
//					profRichBean.setDataScadenza(Utils.convertString2DateAndTime(datiProfilo.getDataScadenza(),"ddMMyyyy hh:mm:ss", true));
					profRichBean.setTipoAbilitazione("O");
					if(datiProfilo.getAzione()!=null)
						profRichBean.setOperazioneSuProfilo(datiProfilo.getAzione().trim());
					profRichBean.setOperazioneEseguita("no");
					/*****/			  
					//richBatch.setDataScadenza(datiProfilo.getDataScadenza());
					//cmRecord.setDataScadenza
					// Modifica Richiesta il 22/12/2016 - Se la richiesta e' per il cdr di appartenenza ( datiProfilo.getIdRichiestaVisibilita = 0)
					// su tabella PROFILI_RICHIESTA il campo ID_RICHIESTA_VISIBILITA deve essere impostato a null
					if(datiProfilo.getIdRichiestaVisibilita()!=null && datiProfilo.getIdRichiestaVisibilita()!=0)
						profRichBean.setIdRichiestaVisibilita(String.valueOf(datiProfilo.getIdRichiestaVisibilita()));
					profRichBean.setOrigineAbilitazione(RICHIEDENTE_AC);
					inserimentoProfiloRichiesta(profRichBean);
				}
				
				RichiestaBatchDaElaborareBean ricBatchDaElab = new RichiestaBatchDaElaborareBean();
				ricBatchDaElab.setIdRichiesta(idRichiesta);
				ricBatchDaElab.setSequence(richBatch.getSequence());
				ricBatchDaElab.setPriorita(datiEvento.getPriorita());
				ricBatchDaElab.setCodiceEvento(codEvento);
				ricBatchDaElab.setNumeroProfili(listaProfili.size());
				ricBatchDaElab.setAmbienteDestinazione(cmRichEntity.getIdAmbito());
				ricBatchDaElab.setAggAmbienteDestinazione(datiEvento.getAggiornaSistAutorizzazione());
				ricBatchDaElab.setDataCreazione( new Date());
				ricBatchDaElab.setOrarioInizioElab(datiEvento.getOrarioInElab());
				ricBatchDaElab.setOrarioFineElab(datiEvento.getOrarioFineElab());
				super.inserisciRichiestaBatchDaElab(ricBatchDaElab);	
			}
			// Aggiorno CM_RICHIESTE
			CaricamentoMassivoEntity cmEntity = new CaricamentoMassivoEntity();
			cmEntity.setStatoRichiesta("GEN");
			cmEntity.setIdCaricamento(cmRichEntity.getIdCaricamento());
			updateGenerazioneRichiestaCaricamentoMassivo(cmEntity);
			eliminaRichiesteBatch(String.valueOf(sequence));
			inviaMailSuccessoGenerazioneRichiesete(cmRichEntity);
		}
	}
	
	private void inviaMailSuccessoGenerazioneRichiesete(CaricamentoMassivoEntity cmRichEntity) throws Exception{
		List<String> destinatari = new ArrayList<String>();	
		HashMap<String,String> param = new HashMap<String,String>();
		destinatari.add(getEmailAmminCaricamento(cmRichEntity.getIdCaricamento()));
		String obj = "mail.oggetto.segnalazioneCM.success";
		String temp = "mail.template.segnalazioneCM.success";
		param.put("DESCRIZIONE_RICHIESTA", cmRichEntity.getDescrizioneRichiestaCaricamento());	
		ArrayList<String> subParam = new ArrayList<String>();
		subParam.add(cmRichEntity.getIdCaricamento());
		mailCommonUtil.sendMailSemplified(destinatari, new ArrayList<String>(), obj, temp, param, subParam);		
	}
	
	private void inviaMailConAllegatoCM (CaricamentoMassivoEntity caricamento, String allegatiFilePath) throws Exception{
		// ricavo i destinatari 
		HashMap<String,String> param = new HashMap<String,String>();
		ArrayList<String> subParam = new ArrayList<String>();
		subParam.add(caricamento.getIdCaricamento());
		Map<String,ByteArrayDataSource> badsMap = new HashMap<String,ByteArrayDataSource>();
		List<FileAbilitazioneBean> listFileAbilitazione = getListaRecordScartati(caricamento.getIdCaricamento());
		
		if(listFileAbilitazione!=null && !listFileAbilitazione.isEmpty()){
//			incoerenzeAbilitazioni = true;
			
			 File file_err = generaXlsx(allegatiFilePath, listFileAbilitazione);
		    
//			File file_err = new File(allegatiFilePath + "errori_caricamento_massivo.txt");
//			FileWriter fw = new FileWriter(file_err,false); 
//			PrintWriter pw = new PrintWriter(fw);		
//			pw.println(INTESTAZIONE_ERRORI_INCOERENZE_ABILITAZIONI);
//			for (Iterator<FileAbilitazioneBean> iterator = listFileAbilitazione.iterator(); iterator.hasNext();) {
//				FileAbilitazioneBean fileAbBean = (FileAbilitazioneBean) iterator.next();
//				String fileLine = getFileLineFromFileAbilitazioneBean(fileAbBean);
//				pw.println(fileLine);
//			}
//			pw.close();
		    ByteArrayDataSource ds_err = mailCommonUtil.getByteArrayDataSourceFromFile(file_err);
			badsMap.put("errori_caricamento_massivo.xlsx", ds_err);				
		}
		
		List<String> destinatari = new ArrayList<String>();	
		destinatari.add(getEmailAmminCaricamento(caricamento.getIdCaricamento()));
		param.put("NUMERO_RICHIESTA", caricamento.getIdCaricamento());	
		param.put("DATA_INSERIMENTO", caricamento.getDataCaricamento());	
		String obj = "mail.oggetto.segnalazioneCM.errore";
		String temp = "mail.template.segnalazioneCM.errore";
		mailCommonUtil.sendMailWithAttachment(destinatari, obj , temp, param,badsMap, subParam);
	}

	/**
	 * @param allegatiFilePath
	 * @param listFileAbilitazione
	 * @return File
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private File generaXlsx(String allegatiFilePath, List<FileAbilitazioneBean> listFileAbilitazione)
			throws FileNotFoundException, IOException {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		 XSSFSheet sheet = workbook.createSheet(SHEET_NAME);
		 Map<String, Object[]> data = new TreeMap<String, Object[]>();
		 data.put("1", INTESTAZIONE_ERRORI_INCOERENZE_ABILITAZIONI_ARR);
		 int keyNumber = 2;
//		 int keyNumber = 1;
//		 XSSFFont font= workbook.createFont();
//		 font.setFontName("Arial");
//		 font.setBold(true);
//		 font.setItalic(true);
//		 Row rowtTitle = sheet.createRow(0);
//		 CellStyle style = rowtTitle.getRowStyle();
//		 style.setFont(font);
//		 int count = 0;
//		 for (Object obj :  INTESTAZIONE_ERRORI_INCOERENZE_ABILITAZIONI_ARR){	            	
//		       Cell cell = rowtTitle.createCell(count++);
//		       cell.setCellValue((String)obj);
//		    }
		 
		 for (Iterator<FileAbilitazioneBean> iterator = listFileAbilitazione.iterator(); iterator.hasNext();) {
			FileAbilitazioneBean fileAbBean = (FileAbilitazioneBean) iterator.next();
			Object[]obj = {fileAbBean.getCodiceFiscale(), fileAbBean.getCodiceCdr(), fileAbBean.getCodiceRoleGroup(),
					fileAbBean.getCodiceProfilo(), fileAbBean.getAzione(), fileAbBean.getMotivazioneScarto()};
			 data.put(String.valueOf(keyNumber++), obj);
		}
		
		Set<String> keyset = data.keySet();
		int rownum = 0;
//		int rownum = 1;
		
		for (String key : keyset){
		    Row row = sheet.createRow(rownum++);
		    Object [] objArr = data.get(key);
		    int cellnum = 0;
		    for (Object obj : objArr){	            	
		       Cell cell = row.createCell(cellnum++);
		       cell.setCellValue((String)obj);
		    }
		}
		File file_err = new File(allegatiFilePath + "errori_caricamento_massivo.xlsx");
		FileOutputStream out = new FileOutputStream(new File(allegatiFilePath + "errori_caricamento_massivo.xlsx"));
		workbook.write(out);
		out.close();
		return file_err;
	}
	
	private String getFileLineFromFileAbilitazioneBean(FileAbilitazioneBean fileAbBean) {
		String fileLine = "";			
		fileLine += Utils.getEmptyOrNotNullString(fileAbBean.getCodiceFiscale()) + SEPARATORE_COLONNE;
		fileLine += Utils.getEmptyOrNotNullString(fileAbBean.getCodiceCdr()) + SEPARATORE_COLONNE;
		fileLine += Utils.getEmptyOrNotNullString(fileAbBean.getCodiceRoleGroup()) + SEPARATORE_COLONNE;
		fileLine += Utils.getEmptyOrNotNullString(fileAbBean.getCodiceProfilo()) + SEPARATORE_COLONNE;
		fileLine += Utils.getEmptyOrNotNullDate(fileAbBean.getDataScadenza()) + SEPARATORE_COLONNE;
		fileLine += Utils.getEmptyOrNotNullString(fileAbBean.getAzione()) + SEPARATORE_COLONNE;
		fileLine += Utils.getEmptyOrNotNullString(fileAbBean.getMotivazioneScarto());
		return fileLine;
	}
	
	private boolean verificaPresenzaProfiloInLista(List<FileAbilitazioneBean> listaFileAbilitazione, FileAbilitazioneEcaBean recordDaAggiungere){
		boolean profiloPresente = false;
		
		for(int i=0; i<listaFileAbilitazione.size(); i++){
			FileAbilitazioneBean recLista = listaFileAbilitazione.get(i);
			if(recordDaAggiungere.getCodiceCdr()==null){
				if(recLista.getIdCaricamento().equals(recordDaAggiungere.getIdCaricamento()) 
						&& recLista.getCodiceFiscale().equals(recordDaAggiungere.getCodiceFiscale())
						&& recLista.getCodiceRoleGroup().equals(recordDaAggiungere.getCodiceRoleGroup())
						&& recLista.getCodiceProfilo().equals(recordDaAggiungere.getCodiceProfilo())
/***/				    && recLista.getDataScadenza().equals(recordDaAggiungere.getDataScadenza())
						&& recLista.getCodiceCdr() == null)
						return true;
			}else{
				if(recLista.getIdCaricamento().equals(recordDaAggiungere.getIdCaricamento()) 
						&& recLista.getCodiceFiscale().equals(recordDaAggiungere.getCodiceFiscale())
						&& recLista.getCodiceRoleGroup().equals(recordDaAggiungere.getCodiceRoleGroup())
						&& recLista.getCodiceProfilo().equals(recordDaAggiungere.getCodiceProfilo())
/***/					&& recLista.getDataScadenza().equals(recordDaAggiungere.getDataScadenza())
						&& recLista.getCodiceCdr() !=null && recLista.getCodiceCdr().equals(recordDaAggiungere.getCodiceCdr()))
						return true;
			}
			
		}
		return profiloPresente;
	}
	/**
	 * Legge le impostazioni dalla tabella BATCH
	 * @return
	 */
	private CoerenzaPropertiesBean getPropertiesBatch(){
		CoerenzaPropertiesBean coerenzaProperties = new CoerenzaPropertiesBean();
		coerenzaProperties = selectCostantiSigaBatchCoerenza();		
		return coerenzaProperties;
	}


	private boolean controllaCfRevocato(String cf) throws SQLException{
		boolean cfRevocato = false;
		
		PropertiesReader pr = new PropertiesReader(DIR_PROPERTIES, FILE_PROPERTIES);
		Properties properties = pr.getProperties();
		String mod = properties.getProperty("MOD");
		if(mod.equals("0")){
			//TEST
			UtenteBean utenteBean = getRevocheUtente(cf);
			if((utenteBean.getRevocaPostazione()!=null && utenteBean.getRevocaPostazione().equals("SI")) ||(utenteBean.getRevocaCau()!=null && utenteBean.getRevocaCau().equals("SI")))
				cfRevocato = true;
		}else{
			ServiceCauUtente scu = new ServiceCauUtente(SigaCache.getSYS_ADMIN(), SigaCache.getSYS_AGENZIA());
			UtenteInfoBean info= new UtenteInfoBean();
			try {
				info = scu.getInfo(cf);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (info!=null)
				cfRevocato=true;
		}
		return cfRevocato;
	}


}
