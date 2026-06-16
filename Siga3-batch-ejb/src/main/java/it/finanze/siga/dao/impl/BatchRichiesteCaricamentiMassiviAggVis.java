package it.finanze.siga.dao.impl;

import it.finanze.scheduler.bean.CaricamentoMassivoInserimentoBean;
import it.finanze.scheduler.bean.CaricamentoMassivoRecord;
import it.finanze.scheduler.bean.CoerenzaPropertiesBean;
import it.finanze.scheduler.bean.EventoBean;
import it.finanze.scheduler.bean.Profilo;
import it.finanze.scheduler.bean.ProfiloCM;
import it.finanze.scheduler.bean.ProfiloUtenteInVisibilitaBean;
import it.finanze.scheduler.bean.ProfiloVisibilitaTracciamentoBean;
import it.finanze.scheduler.bean.RichiestaAbilitazioneDisabilitazioneBean;
import it.finanze.scheduler.bean.RichiestaVisibilitaBean;
import it.finanze.scheduler.bean.RoleGroupBean;
import it.finanze.siga.bean.CaricamentoMassivoEntity;
import it.finanze.siga.bean.FileAbilitazioneBean;
import it.finanze.siga.bean.FileAbilitazioneEcaBean;
import it.finanze.siga.bean.ProfiloRichiestaBean;
import it.finanze.siga.business.MailCommonUtil;
import it.finanze.siga.ejb.util.Utility;
import it.finanze.siga.finder.InterrogazioneCaricamentiFinder;
import it.finanze.siga.finder.ProfiloFinder;
import it.finanze.siga.finder.ProfiloUtenteInVisibilitaFinder;
import it.finanze.siga.finder.RoleGroupFinder;
import it.finanze.siga.util.Constants;
import it.finanze.siga.util.SigaCache;
import it.finanze.siga.util.Utils;
import it.finanze.siga.util.bean.UtenteBean;
import it.finanze.siga.utility.properties.PropertiesReader;
//import it.sogei.cau.ws.ServiceCauUtente;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import jakarta.mail.util.ByteArrayDataSource;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.ibatis.sqlmap.client.SqlMapClient;

public class BatchRichiesteCaricamentiMassiviAggVis  extends BatchRichiesteCaricamentiMassiviAggVisNewDaoImpl {// SigaDaoImpl{

	@Inject MailCommonUtil mailCommonUtil;

	static Logger fileLog = Logger.getLogger(BatchRichiesteCaricamentiMassiviAggVis.class);
	static final String COD_EVENTO_CAR_MASS_AGG_VIS  = "AggMasVis";
	static final String COD_EVENTO_CAR_MASS_AGG_VIS_IMM  = "AggMasVisI";
	private static final String SHEET_NAME = "report_errori";
	private static final Object[] INTESTAZIONE_ERRORI_INCOERENZE_ABILITAZIONI_ARR = {"CodiceFiscale", "CodiceCdr", "CodiceRoleGroup", "CodiceProfilo", "Azione", "MotivoScarto"};
	private static final String AZIONE_AGGIUNGI = "Aggiunta";
	private static final String AZIONE_AGGIORNAMENTO = "Aggiornamento";
	private static final String AZIONE_RIMOZIONE = "Rimozione";
	public static String DIR_PROPERTIES = "/prod/installedApps/SIGA";
	public static String FILE_PROPERTIES = "ini.properties";
	private String evento;
	private String codEvento;
	CoerenzaPropertiesBean coerenzaProperties;
	String codiceApplicazione;


	// L'oggetto che ci serve per l'inserimento o la cancellazione nella tabella PROFILI_UTENTE_IN_VISIBILITA
	ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilita;
	// Questo Oggetto ci serve quando scriviamo nella tabella PROFILI_VISIBILITA_TRACCIAMENTO
	Profilo datiProfiloInserito;

	List<ProfiloUtenteInVisibilitaBean> aggiuntaProfiliList;
	List<ProfiloUtenteInVisibilitaBean> rimozioneProfiliList;
	List<ProfiloUtenteInVisibilitaBean> profiliDaAggiungereList;
	List<ProfiloUtenteInVisibilitaBean> profiliDaRimuovereList;
	List<ProfiloVisibilitaTracciamentoBean> profVisTracBean;
	private List<FileAbilitazioneEcaBean> recordEca;

	/*public BatchRichiesteCaricamentiMassiviAggVis(SqlMapClient sqlMapClient) {
		super(sqlMapClient);
	}*/

	public void startBatch(String idCaricamento) throws Exception{

		Properties prop = new Properties();
		prop.load(new FileInputStream(Constants.FILE_PROPERTY_PATH+"/"+Constants.FILE_PROPERTY_NAME));
		String allegatiFilePath = prop.getProperty("BATCH_COERENZA_ALLEGATI_FILE_PATH");
		String logFilePathMass = prop.getProperty("BATCH_RIC_CAR_MASS_AGG_VIS_LOG_FILE_PATH");
		Utility.initFileLogger(logFilePathMass,fileLog);
		EventoBean datiEvento = new EventoBean();
		if(idCaricamento== null){
			// Ottieni le impostazioni del batch
			coerenzaProperties = selectCostantiSigaBatchCoerenza();
			if("SI".equalsIgnoreCase(coerenzaProperties.getElaborazioneCMVis())){
				fileLog.info("START... BATCH RICHIESTE CARICAMENTI MASSIVI AGGIORNAMENTO VISIBILITA' - RICHIAMATO DA SCHEDULER");
				evento = COD_EVENTO_CAR_MASS_AGG_VIS;
				codEvento = COD_EVENTO_CAR_MASS_AGG_VIS;
				datiEvento = recuperaDatiEventoSistema(codEvento);
				InterrogazioneCaricamentiFinder finder = new InterrogazioneCaricamentiFinder();
				List<CaricamentoMassivoEntity> listaCaricamenti = new ArrayList<CaricamentoMassivoEntity>();
				listaCaricamenti= getListaCaricamentiAggVis(finder);
				controllaRichiesteCaricamentoMassivoAggVis(listaCaricamenti, datiEvento, allegatiFilePath);
				//				generazioneRichiesteBatch(finder, datiEvento, allegatiFilePath);
				//				fileLog.info("asdasdas");
				////				elaborazioneProfiliAG();
			}


		}else{
			fileLog.info("START... BATCH RICHIESTE CARICAMENTI MASSIVI AGGIORNAMENTO VISIBILITA' - RICHIAMATO DA APPLICAZIONE WEB GESTIONE CARICAMENTI MASSIVI");
			evento = COD_EVENTO_CAR_MASS_AGG_VIS_IMM;
			codEvento = COD_EVENTO_CAR_MASS_AGG_VIS_IMM;
			datiEvento = recuperaDatiEventoSistema(codEvento);
			InterrogazioneCaricamentiFinder finder = new InterrogazioneCaricamentiFinder();
			finder.setIdCaricamento(idCaricamento);
			List<CaricamentoMassivoEntity> listaCaricamenti = new ArrayList<CaricamentoMassivoEntity>();
			listaCaricamenti= getListaCaricamentiAggVis(finder);
			controllaRichiesteCaricamentoMassivoAggVis(listaCaricamenti, datiEvento, allegatiFilePath);
			//			generazioneRichiesteBatch(finder, datiEvento, allegatiFilePath);
		}

	}

	private void controllaRichiesteCaricamentoMassivoAggVis(List<CaricamentoMassivoEntity> listaCaricamenti, EventoBean datiEvento, String allegatiFilePath) throws Exception{	
		int numRecordPresenti;
		int numRecordScartati;
		int numRecordValidi;
		int numRecordEcaAggiunti;
		CaricamentoMassivoInserimentoBean cmInserimento = new CaricamentoMassivoInserimentoBean(); 


		profVisTracBean = new ArrayList<ProfiloVisibilitaTracciamentoBean>();
		profiliDaAggiungereList = new ArrayList<ProfiloUtenteInVisibilitaBean>();
		profiliDaRimuovereList = new ArrayList<ProfiloUtenteInVisibilitaBean>();

		boolean isAmministratoreCentrale = false;
		boolean isAmministratoreCentraleApplicativo = false;

		for(int i=0; i<listaCaricamenti.size(); i++){
			//			List<RichiestaAbilitazioneDisabilitazioneBean> listaRichiesteCaricamento = new ArrayList<RichiestaAbilitazioneDisabilitazioneBean>();
			List<FileAbilitazioneBean> listaFileAbilitazione = new ArrayList<FileAbilitazioneBean>();
			List<CaricamentoMassivoRecord> listRecordControllati = new ArrayList<CaricamentoMassivoRecord>();
			CaricamentoMassivoEntity caricamento = listaCaricamenti.get(i);
			numRecordPresenti = 0; // contatore dei record del file che si sta controllando
			numRecordScartati = 0;
			numRecordValidi = 0;
			numRecordEcaAggiunti = 0;

			Integer ammCentrale = isAmministratoreCentrale(caricamento.getCfAmministratore());
			if(ammCentrale != null && ammCentrale>0)
				isAmministratoreCentrale = true;
			else{
				ammCentrale = isAmministratoreCentrale2(caricamento.getCfAmministratore());
				if(ammCentrale != null && ammCentrale>0)
					isAmministratoreCentraleApplicativo = true;
			}

			if(isAmministratoreCentrale || isAmministratoreCentraleApplicativo){
				InputStream bis = new ByteArrayInputStream(caricamento.getbFileVisibilita());

				// Conto il numero di record del file Excel
				XSSFWorkbook excel = new XSSFWorkbook(bis);
				XSSFSheet sheet = excel.getSheetAt(0);
				int rownum = sheet.getLastRowNum();
				caricamento.setNumRecordFile(String.valueOf(rownum));
				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();
				List<CaricamentoMassivoRecord> listRecordFileInput = new ArrayList<CaricamentoMassivoRecord>();
				while(rowIterator.hasNext()){
					numRecordPresenti ++;
					CaricamentoMassivoRecord cmRecord = new CaricamentoMassivoRecord();
					Row row = rowIterator.next();
					XSSFRow caricamentoRow = sheet.getRow(row.getRowNum());
					XSSFCell cfCell = caricamentoRow.getCell(0);
					XSSFCell codRoleGroupCell = caricamentoRow.getCell(1);//
					XSSFCell codProfiloCell = caricamentoRow.getCell(2);
					XSSFCell codCdrCell = caricamentoRow.getCell(3);
					XSSFCell datFineValCell = caricamentoRow.getCell(4);
					XSSFCell azioneCell = caricamentoRow.getCell(5);
					if(cfCell!=null)
						cmRecord.setCodiceFiscale(cfCell.getStringCellValue());
					if(codRoleGroupCell!=null)
						cmRecord.setCodiceRoleGroup(codRoleGroupCell.getStringCellValue());
					if(codProfiloCell!=null)
						cmRecord.setCodiceProfilo(codProfiloCell.getStringCellValue());
					if(codCdrCell!=null)
						cmRecord.setCodiceCdr(codCdrCell.getStringCellValue());
		            if(datFineValCell!=null){
		                	if(datFineValCell.getCellType() == CellType.NUMERIC){ 
		                		 if (DateUtil.isCellDateFormatted(datFineValCell)) {
		                			 SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		                			 cmRecord.setDataScadenza(dateFormat.format(datFineValCell.getDateCellValue()));
		                		 }
		                	}else if (datFineValCell.getCellType() ==  CellType.STRING){
		                		cmRecord.setDataScadenza(datFineValCell.getStringCellValue());
		                	}else if (datFineValCell.getCellType() ==  CellType.BLANK){
		                		cmRecord.setDataScadenza(datFineValCell.getStringCellValue());
		                	}
		                }else
		                	cmRecord.setDataFineVal("");
					if(azioneCell!=null)
						cmRecord.setAzione(azioneCell.getStringCellValue());

//		                listRecordFileInput.add(cmRecord);
					
					
					
					listRecordFileInput.add(cmRecord);
				}
				Iterator<CaricamentoMassivoRecord> rowFileIterator = listRecordFileInput.iterator();
				int iterazione = 0;
				while(rowFileIterator.hasNext()){
					iterazione++;
					CaricamentoMassivoRecord cmRecord = rowFileIterator.next();
					String esitoControllo = controllaDuplicazioneRecord(listRecordControllati, cmRecord, iterazione);
					List<String> errorMessage = new ArrayList<String>();
					if(esitoControllo.trim().equals("")){
						listRecordControllati.add(cmRecord);
						errorMessage =controllaSingoloRecord(cmRecord, caricamento, listRecordFileInput, isAmministratoreCentrale, isAmministratoreCentraleApplicativo);
					}else{
						errorMessage.add(esitoControllo);
					}

					/*******************************************
					 * Gestione Record Z delle Specifiche
					 *******************************************/

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
						listaFileAbilitazione.add(gestisciRecordFileAbilitazione(caricamento, cmRecord, null));
						if(cmRecord.getCodiceProfilo().substring(0, 4).equalsIgnoreCase("ECA_")){
							// Scrivo su CM_VISIBILITA_FILE i record ECA Figli aggiunti
							for(int v=0; v<aggiuntaProfiliList.size(); v++){
								ProfiloUtenteInVisibilitaBean profEcaAgg = aggiuntaProfiliList.get(v);
								if(!profEcaAgg.getCodiceProfilo().substring(9).equals("00000"))
									listaFileAbilitazione.add(gestisciRecordECAAggiuntiFileAbilitazione(caricamento, cmRecord, profEcaAgg));
							}
							// Scrivo su CM_VISIBILITA_FILE i record ECA Figli rimossi
							for(int a=0; a<rimozioneProfiliList.size(); a++){
								ProfiloUtenteInVisibilitaBean profEcaAgg = rimozioneProfiliList.get(a);
								if(!profEcaAgg.getCodiceProfilo().substring(9).equals("00000"))
									listaFileAbilitazione.add(gestisciRecordECARimossiFileAbilitazione(caricamento, cmRecord, profEcaAgg));
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


				inserisciRichiesteCaricamentoMassivoAggVis(listaFileAbilitazione, profVisTracBean, profiliDaAggiungereList, profiliDaRimuovereList);
			}

			if(numRecordScartati>0){
				//presenzaErrori = true;
				String messaggio = "In allegato il file degli scarti.";
				inviaMailConAllegatoCMVisib(caricamento, allegatiFilePath, messaggio);
			}else{
				if(isAmministratoreCentrale){
					String messaggio = "Nessun record e' stato scartato.";
					inviaMailSuccessoControlli(caricamento, messaggio);
				}else{
					inviaMailErroreUtenteNonAmministratore(caricamento, "");
				}
			}
			if(profiliDaAggiungereList != null)
				numRecordEcaAggiunti = profiliDaAggiungereList.size();

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
				CMEntity.setStatoRichiesta("ESG");
			CMEntity.setIdCaricamento(caricamento.getIdCaricamento());


			updateControlloRichiestaCaricamentoMassivoVisib(CMEntity);
		}

		/*******************************************
		 * fine Gestione Record Z delle Specifiche
		 *******************************************/
	}

	private List<String> controllaSingoloRecord(CaricamentoMassivoRecord cmRecord, CaricamentoMassivoEntity caricamento, List<CaricamentoMassivoRecord> listRecordFileInput, boolean isAmministratoreCentrale, boolean isAmministratoreCentraleApplicativo) throws SQLException, ParseException{
		//		fileDaAggiungere = false;
		//		recordAggiuntiEca = new ArrayList<FileAbilitazioneEcaBean>();
		aggiuntaProfiliList = new ArrayList<ProfiloUtenteInVisibilitaBean>();
		rimozioneProfiliList = new ArrayList<ProfiloUtenteInVisibilitaBean>();
		
		datiProfiloUtenteVisibilita = new ProfiloUtenteInVisibilitaBean();
		datiProfiloUtenteVisibilita.setCfUtente(cmRecord.getCodiceFiscale());
		datiProfiloUtenteVisibilita.setCodiceProfilo(cmRecord.getCodiceProfilo());
		datiProfiloUtenteVisibilita.setCodiceCdr(cmRecord.getCodiceCdr());
		datiProfiloUtenteVisibilita.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
		codiceApplicazione = "";
		datiProfiloUtenteVisibilita.setDataFineVal(Utils.convertToDate(cmRecord.getDataScadenza(),"ddMMyyyy hh:mm:ss", true));

		String azioneTracciamento;
		List<String> errorMessage = new ArrayList<String>();
		if(!controllaCodiceFiscale(cmRecord.getCodiceFiscale())){
			String err_cf = "CF "+ cmRecord.getCodiceFiscale() + " non esistente";
			errorMessage.add(err_cf);
		}else if(controllaDisallineamentoCf(cmRecord.getCodiceFiscale())){
			String err_cf = "Il CF "+ cmRecord.getCodiceFiscale() + " risulta disallineato tra i sistemi CAU e SIGA3";
			errorMessage.add(err_cf);
		}
		List<String> err_rg = controllaCodiceRoleGroup(cmRecord.getCodiceRoleGroup(), cmRecord.getAzione());
		if(err_rg.size()>0)
			errorMessage.addAll(err_rg);

		List<String> err_prof = controllaCodiceProfilo(cmRecord.getCodiceProfilo(), cmRecord.getCodiceCdr(), cmRecord.getAzione(), isAmministratoreCentraleApplicativo, caricamento.getCfAmministratore());
		if(err_prof.size()>0)
			errorMessage.addAll(err_prof);

		List<String> err_azione = controllaAzione(cmRecord.getAzione());
		if(err_azione.size()>0)
			errorMessage.addAll(err_azione);

		// Se i controlli su Codioce Role Group e Codice Profilo sono andati a buon fine andiamo a controllare 
		// la compatibilite' tra role group e profilo
		if(err_rg.size()==0 && err_prof.size()==0 && err_azione.size()==0){
			List<String> err_prof_rg = compatibilitaProfiloRoleGroup(cmRecord.getCodiceProfilo(), cmRecord.getCodiceRoleGroup(), cmRecord.getAzione());
			if(err_prof_rg.size()>0)
				errorMessage.addAll(err_prof_rg);
		}

		HashMap<String,String> queryMap = new HashMap<String,String>();

		//queryMap.put("codFiscale",cmRecord.getCodiceFiscale().trim()); 
		queryMap.put("codFiscale",cmRecord.getCodiceFiscale()); 
		queryMap.put("codCdr",cmRecord.getCodiceCdr());
		List<RichiestaVisibilitaBean> datiRichVis = getDatiRichiestaVisibByCfCdr(queryMap);
		if(datiRichVis.size()>0){
			datiProfiloUtenteVisibilita.setIdRichiestaVisibilita(String.valueOf(datiRichVis.get(0).getIdRichiestaVisibilita()));
			datiProfiloUtenteVisibilita.setCodiceUfficio(datiRichVis.get(0).getCodiceUfficio());
			if(datiRichVis.get(0).getDataFineValidita()!=null){
				Calendar cal = Calendar.getInstance();
				cal.setTime(datiRichVis.get(0).getDataFineValidita());
				cal.set(Calendar.HOUR_OF_DAY,23);
				cal.set(Calendar.MINUTE,59);
				cal.set(Calendar.SECOND,59);
				datiProfiloUtenteVisibilita.setDataFineVal(cal.getTime());
			}
			//			else
			//				datiProfiloUtenteVisibilita.setDataFineVal(datiRichVis.get(0).getDataFineValidita());
		}
		
		// 1.3 2023 --> controllo cdr eliminato
		//List<String> err_cdr = controllaCdr(cmRecord.getCodiceCdr(), cmRecord.getCodiceFiscale(), datiRichVis);
		//if(err_cdr.size()>0)
		//	errorMessage.addAll(err_cdr);
		// 1.3 2023 <<-
		
		/* Adeguamento 10/2017 */
		List<String> err_cdr_prof = compatibilitaProfiloCdr(cmRecord.getCodiceCdr(), datiProfiloUtenteVisibilita);
		if(err_cdr_prof.size()>0)
			errorMessage.addAll(err_cdr_prof);		
		/* Fine Adeguamento 10/2017 */

		if(errorMessage.size()==0){
			if(cmRecord.getAzione().trim().equals(AZIONE_AGGIUNGI) || cmRecord.getAzione().trim().equals(AZIONE_AGGIORNAMENTO)){

				if(cmRecord.getAzione().trim().equals(AZIONE_AGGIUNGI))
					azioneTracciamento = AZIONE_AGGIUNGI;
				else
					azioneTracciamento = AZIONE_AGGIORNAMENTO;

				if(controllaCfRevocato(cmRecord.getCodiceFiscale())){
					errorMessage.add("L'utente "+cmRecord.getCodiceFiscale() + " e' stato revocato." );
				}else{
					recordEca = new ArrayList<FileAbilitazioneEcaBean>();
					Integer c = 0;
					ProfiloUtenteInVisibilitaFinder puvFinder = new ProfiloUtenteInVisibilitaFinder();
					puvFinder.setCodiceFiscale(cmRecord.getCodiceFiscale());
					puvFinder.setIdRichiestaVisibilita(String.valueOf(datiRichVis.get(0).getIdRichiestaVisibilita()));
					puvFinder.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
					puvFinder.setCodiceProfilo(cmRecord.getCodiceProfilo());
					if(!cmRecord.getCodiceProfilo().substring(0, 4).equalsIgnoreCase("ECA_")){
						c = countProfiliUtentiInVisibilita(puvFinder);
					}
					if(c>0){
						errorMessage.add("Il role group "+ cmRecord.getCodiceRoleGroup() +" e il profilo "+cmRecord.getCodiceProfilo()+" sono gie' previsti nella richiesta di visibilite'");
					}else{
						ProfiloFinder profiloFinder = new ProfiloFinder();
						List<String> cdrList = new ArrayList<String>();
						cdrList.add(cmRecord.getCodiceCdr());
						List<String> rgList = new ArrayList<String>();
						rgList.add(cmRecord.getCodiceRoleGroup());
						profiloFinder.setCdrLst(cdrList);
						profiloFinder.setRoleGroupList(rgList);
						// Sono i profili disponibili nel cdr
						List<ProfiloRichiestaBean> listaProfili  = getElencoProfiliUfficioDaCdRList(profiloFinder);

						List<ProfiloRichiestaBean> listaProfiliMatch = new ArrayList<ProfiloRichiestaBean>();
						for(int i=0; i<listaProfili.size(); i++){
							ProfiloRichiestaBean prof = listaProfili.get(i);
							if(prof.getCodiceProfilo().trim().equals(cmRecord.getCodiceProfilo())){
								listaProfiliMatch.add(prof);
								datiProfiloUtenteVisibilita.setTipoAbilitazione(prof.getTipoAbilitazione());
							}
						}

						if(listaProfiliMatch.size() == 0)
							errorMessage.add("Il role group "+ cmRecord.getCodiceRoleGroup() +" e il profilo "+cmRecord.getCodiceProfilo()+" non sono previsti per il CdR "+cmRecord.getCodiceCdr()+ " della visibilite'");
						else{
							boolean controlliSuperati = true;
							if(cmRecord.getCodiceProfilo().substring(0, 4).equalsIgnoreCase("ECA_")){
								if(cmRecord.getAzione().trim().equals(AZIONE_AGGIUNGI)){
									errorMessage.add("Per i profili CAT l'azione deve essere 'Aggiornamento'");
									controlliSuperati = false;
									aggiungi(datiProfiloUtenteVisibilita, controlliSuperati, aggiuntaProfiliList, rimozioneProfiliList, caricamento, azioneTracciamento);
								}else if(cmRecord.getAzione().trim().equals(AZIONE_AGGIORNAMENTO) && !cmRecord.getCodiceProfilo().substring(9).equals("00000")){
									errorMessage.add("Per le'aggiornamento dei profili CAT deve essere indicato il codice profilo delle'area");
									controlliSuperati = false;
									aggiungi(datiProfiloUtenteVisibilita, controlliSuperati, aggiuntaProfiliList, rimozioneProfiliList, caricamento, azioneTracciamento);
								}else if(cmRecord.getAzione().trim().equals(AZIONE_AGGIORNAMENTO) && cmRecord.getCodiceProfilo().substring(9).equals("00000")){
									// Sono i profili compresi nella richiesta di visibilite' per il cdr indicato
									List<ProfiloUtenteInVisibilitaBean> profUtInVis = getProfiliUtentiInVisibilita(puvFinder);
									// Creo lista Profili Aggiunta
									for(int a=0; a<listaProfili.size(); a++){
										ProfiloRichiestaBean profCdr = listaProfili.get(a);
										String codProf_Cdr = profCdr.getCodiceProfilo().trim();
										boolean profiloTrovato = false;
										for(int b=0; b<profUtInVis.size(); b++){
											ProfiloUtenteInVisibilitaBean profUtVis = profUtInVis.get(b);
											String codProf_UtVis = profUtVis.getCodiceProfilo().trim();
											if(codProf_Cdr.equals(codProf_UtVis))
												profiloTrovato = true;
										}
										if(!profiloTrovato)
											aggiuntaProfiliList.add(wrappaProfiloUtenteInVisibilitaBean(profCdr, cmRecord.getCodiceFiscale()));

									}
									// Creo la lista Profili Rimozione
									for(int d=0; d<profUtInVis.size(); d++){
										ProfiloUtenteInVisibilitaBean profUtVis = profUtInVis.get(d);
										String codProf_UtVis = profUtVis.getCodiceProfilo().trim();
										boolean profiloTrovato2 = false;
										for(int e=0; e<listaProfili.size(); e++){
											ProfiloRichiestaBean profCdr = listaProfili.get(e);
											String codProf_Cdr = profCdr.getCodiceProfilo().trim();
											if(codProf_UtVis.equals(codProf_Cdr))
												profiloTrovato2 = true;
										}
										if(!profiloTrovato2)
											rimozioneProfiliList.add(profUtVis);
									}



									if(aggiuntaProfiliList.size()==0 && rimozioneProfiliList.size()==0){
										errorMessage.add("per il CF "+cmRecord.getCodiceFiscale()+" e il role group "+cmRecord.getCodiceRoleGroup()+" tutti i profili delle'area sono gie' in visibilite'");
										controlliSuperati = false;
										aggiungi(datiProfiloUtenteVisibilita, controlliSuperati, aggiuntaProfiliList, rimozioneProfiliList, caricamento, azioneTracciamento);
									}else{
										for(int f=0; f<rimozioneProfiliList.size(); f++){
											ProfiloUtenteInVisibilitaBean puvb = rimozioneProfiliList.get(f);
											if(puvb.getProfiloAssegnato().trim().equalsIgnoreCase("SI")){
												errorMessage.add("Il profilo "+puvb.getCodiceProfilo()+" per il role group " +cmRecord.getCodiceRoleGroup()+ " non e' pie' tra i profili disponibili al CdR ma risulta assegnato al CF "+cmRecord.getCodiceFiscale());
												controlliSuperati = false;
												aggiungi(datiProfiloUtenteVisibilita, controlliSuperati, aggiuntaProfiliList, rimozioneProfiliList, caricamento, azioneTracciamento);
											}else{
												// Vediamo se e' in corso una richiesta di abilitazione
												List<RichiestaAbilitazioneDisabilitazioneBean> ricAbilList = controllaPresenzaRichiesteAbilitazione(puvFinder);
												if(ricAbilList.size()>0){
													for(int g=0; g<ricAbilList.size(); g++){
														RichiestaAbilitazioneDisabilitazioneBean ricG = ricAbilList.get(g);
														errorMessage.add("Per il CF "+cmRecord.getCodiceFiscale()+" e il role group "+cmRecord.getCodiceRoleGroup() + " e' in corso la richiesta " +ricG.getIdRichiesta() + " relativa al profilo "+ cmRecord.getCodiceProfilo());
														controlliSuperati = false;
														aggiungi(datiProfiloUtenteVisibilita, controlliSuperati, aggiuntaProfiliList, rimozioneProfiliList, caricamento, azioneTracciamento);
													}
												}
											}
										}
										aggiungi(datiProfiloUtenteVisibilita, controlliSuperati, aggiuntaProfiliList, rimozioneProfiliList, caricamento, azioneTracciamento);
									}
								}
							}else{
								aggiungi(datiProfiloUtenteVisibilita, controlliSuperati, null, null, caricamento, azioneTracciamento);
							}

						}
					}	
				}	
			}else if(cmRecord.getAzione().trim().equals(AZIONE_RIMOZIONE)){
				azioneTracciamento=AZIONE_RIMOZIONE;
				List<FileAbilitazioneBean> fileAbilitazioneBeanList = new ArrayList<FileAbilitazioneBean>();
				ProfiloUtenteInVisibilitaFinder puvFinder = new ProfiloUtenteInVisibilitaFinder();
				puvFinder.setCodiceFiscale(cmRecord.getCodiceFiscale());
				puvFinder.setIdRichiestaVisibilita(String.valueOf(datiRichVis.get(0).getIdRichiestaVisibilita()));
				puvFinder.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
				puvFinder.setCodiceProfilo(cmRecord.getCodiceProfilo());

				List<ProfiloUtenteInVisibilitaBean> profUtInVisR = getProfiliUtentiInVisibilitaRimozione(puvFinder);
				if(profUtInVisR==null || profUtInVisR.size()==0)
					errorMessage.add("Il role group "+cmRecord.getCodiceRoleGroup() + " e il profilo "+ cmRecord.getCodiceProfilo() + " non sono previsti nella richiesta di visibilite'");
				else{
					ProfiloUtenteInVisibilitaBean profUtVis = profUtInVisR.get(0);
					datiProfiloUtenteVisibilita.setDataInizioVal(profUtVis.getDataInizioVal());
					datiProfiloUtenteVisibilita.setDataFineVal(profUtVis.getDataFineVal());
					if(profUtVis.getProfiloAssegnato().equalsIgnoreCase("SI"))
						errorMessage.add("Il role group "+cmRecord.getCodiceRoleGroup() + " e il profilo "+ cmRecord.getCodiceProfilo() + " sono assegnati all'operatore");
					else if(profUtVis.getProfiloAssegnato().equalsIgnoreCase("NO")){
						puvFinder.setOperazioneEseguita(profUtVis.getProfiloAssegnato());
						List<String> listaId = getIdRichiesteAbilitazione(puvFinder);
						if(listaId.size()>0)
							errorMessage.add("E' in corso la richiesta di abilitazione "+listaId.get(0) + " per il role group "+cmRecord.getCodiceRoleGroup() + " e il profilo "+ cmRecord.getCodiceProfilo());
						else{
							if(cmRecord.getCodiceProfilo().substring(0, 4).equalsIgnoreCase("ECA_") && !cmRecord.getCodiceProfilo().substring(9).equals("00000"))
								errorMessage.add("Per la rimozione dei profili CAT deve essere indicato il codice profilo dell'area");
							else if(cmRecord.getCodiceProfilo().substring(0, 4).equalsIgnoreCase("ECA_") && cmRecord.getCodiceProfilo().substring(9).equals("00000")){
								ProfiloUtenteInVisibilitaFinder puvFinder2 = puvFinder;
								puvFinder2.setCodiceProfilo(null);
								List<String> listaId2 = getIdRichiesteAbilitazione(puvFinder2);
								if(listaId2.size()>0)
									errorMessage.add("E' in corso la richiesta di abilitazione "+listaId.get(0) + " che comprende i profili del role group "+cmRecord.getCodiceRoleGroup());
								else{
									Integer cc = countProfiliFigliAssegnatiAlCfPerRichVisib(puvFinder2);
									if(cc>0)
										errorMessage.add("Al CF "+cmRecord.getCodiceFiscale() + " sono assegnati profili per il role group "+cmRecord.getCodiceRoleGroup());
									else
										continuaElaborazione(cmRecord, datiRichVis.get(0), errorMessage, aggiuntaProfiliList, rimozioneProfiliList, caricamento, datiProfiloUtenteVisibilita);
								}

							}else
								continuaElaborazione(cmRecord, datiRichVis.get(0), errorMessage, aggiuntaProfiliList, rimozioneProfiliList, caricamento, datiProfiloUtenteVisibilita);
						}

					}
				}
			}
		}

		return errorMessage;
	}


	private ProfiloUtenteInVisibilitaBean wrappaProfiloUtenteInVisibilitaBean(ProfiloRichiestaBean profRichBean, String cfUtente){

		ProfiloUtenteInVisibilitaBean profOut = new ProfiloUtenteInVisibilitaBean();
		profOut.setCfUtente(cfUtente);
		profOut.setCodiceApplicazione(profRichBean.getCodiceApplicazione());
		profOut.setCodiceRoleGroup(profRichBean.getCodiceRoleGroup());
		profOut.setCodiceProfilo(profRichBean.getCodiceProfilo());
		profOut.setCodiceCdr(profRichBean.getCodiceCDR());
		profOut.setCodiceUfficio(profRichBean.getCodiceUfficio());
		profOut.setProfiloAssegnato("no");
		profOut.setIdRichiestaVisibilita(profRichBean.getIdRichiestaVisibilita());
		profOut.setDataInizioVal(new Date());
		profOut.setDataFineVal(profRichBean.getData_fine_val());
		profOut.setTipoAbilitazione(profRichBean.getTipoAbilitazione());
		return profOut;
	}

	private void aggiungi(ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilita, boolean controlliSuperati, 
			List<ProfiloUtenteInVisibilitaBean> aggiuntaProfiliList, 
			List<ProfiloUtenteInVisibilitaBean> rimozioneProfiliList, CaricamentoMassivoEntity caricamento, 
			String azioneTracciamento) throws SQLException{

		if(controlliSuperati){
			if(datiProfiloUtenteVisibilita.getCodiceProfilo().substring(0, 4).equalsIgnoreCase("ECA_")){
				// Elaborazione Profili ECA
				ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilitaFromList;

				// Inserimento Profili della lista "aggiunta"
				for(int m=0; m<aggiuntaProfiliList.size(); m++){
					datiProfiloUtenteVisibilitaFromList = aggiuntaProfiliList.get(m);

					datiProfiloUtenteVisibilitaFromList.setProfiloAssegnato("no");
					datiProfiloUtenteVisibilitaFromList.setDataInizioVal(new Date());
					datiProfiloUtenteVisibilitaFromList.setOrigineAbilitazione("CV");
					datiProfiloUtenteVisibilitaFromList.setIdRichiestaVisibilita(datiProfiloUtenteVisibilita.getIdRichiestaVisibilita());
					datiProfiloUtenteVisibilitaFromList.setExplicitEntitlement(datiProfiloUtenteVisibilita.getExplicitEntitlement());
					if(!datiProfiloUtenteVisibilitaFromList.getCodiceProfilo().substring(9).equals("00000"))
						datiProfiloUtenteVisibilitaFromList.setCodiceUfficio(null);
					else{
						if(datiProfiloUtenteVisibilita.getExplicitEntitlement().trim().equalsIgnoreCase("CDR")){
							datiProfiloUtenteVisibilitaFromList.setCodiceUfficio(null);
							datiProfiloUtenteVisibilitaFromList.setCodiceCdr(datiProfiloUtenteVisibilita.getCodiceCdr());
						}else if(datiProfiloUtenteVisibilita.getExplicitEntitlement().trim().equalsIgnoreCase("CODUFFICIO")){
							datiProfiloUtenteVisibilita.setCodiceCdr(null);
							datiProfiloUtenteVisibilitaFromList.setCodiceUfficio(datiProfiloUtenteVisibilita.getCodiceUfficio());
						}
					}
					profiliDaAggiungereList.add(datiProfiloUtenteVisibilitaFromList);

					// I profili ECA_mmmmm00000 vanno rimossi dalla lista perche' combaciano con il record letto nel file excel
					if(!datiProfiloUtenteVisibilita.getCodiceProfilo().trim().equals(datiProfiloUtenteVisibilitaFromList.getCodiceProfilo().trim())){
						// Tracciamento dei profili ECA Aggiuntivi da Aggiungere
						inserimentoProfiliVisibilitaTracciamento(datiProfiloUtenteVisibilitaFromList, caricamento, AZIONE_AGGIUNGI);
						//inserisciProfiloUtenteInVisibilita(datiProfiloUtenteVisibilitaFromList);
					}

				}
				// Rimozione Profili della lista "rimozione"
				for(int n=0; n<rimozioneProfiliList.size(); n++){
					ProfiloUtenteInVisibilitaBean profiloDaRimuovere = rimozioneProfiliList.get(n);
					ProfiloUtenteInVisibilitaBean profiloTracciamento = new ProfiloUtenteInVisibilitaBean();

					profiloDaRimuovere.setOrigineAbilitazione("CV");
					profiloDaRimuovere.setIdRichiestaVisibilita(datiProfiloUtenteVisibilita.getIdRichiestaVisibilita());
					profiloDaRimuovere.setCodiceRoleGroup(datiProfiloUtenteVisibilita.getCodiceRoleGroup());
					profiloDaRimuovere.setCfUtente(datiProfiloUtenteVisibilita.getCfUtente());
					profiliDaRimuovereList.add(profiloDaRimuovere);
					// Tracciamento dei profili ECA Aggiuntivi Da Rimuovere
					profiloTracciamento.setCodiceProfilo(profiloDaRimuovere.getCodiceProfilo());
					profiloTracciamento.setCfUtente(datiProfiloUtenteVisibilita.getCfUtente());
					profiloTracciamento.setCodiceApplicazione(datiProfiloUtenteVisibilita.getCodiceApplicazione());
					profiloTracciamento.setCodiceRoleGroup(datiProfiloUtenteVisibilita.getCodiceRoleGroup());
//					profiloTracciamento.setCodiceCdr(datiProfiloUtenteVisibilita.getCodiceCdr());
//					profiloTracciamento.setCodiceUfficio(datiProfiloUtenteVisibilita.getCodiceUfficio());
					profiloTracciamento.setDataFineVal(datiProfiloUtenteVisibilita.getDataFineVal());
					profiloTracciamento.setIdRichiestaVisibilita(datiProfiloUtenteVisibilita.getIdRichiestaVisibilita());
					profiloTracciamento.setExplicitEntitlement(datiProfiloUtenteVisibilita.getExplicitEntitlement());
					//					
					inserimentoProfiliVisibilitaTracciamento(profiloTracciamento, caricamento, AZIONE_RIMOZIONE);
					//deleteProfiloUtenteInVisibilita(datiProfiloUtenteVisibilita);
				}

				// Aggiungo in Profili Visibilite' tracciamento il profilo ECA Padre
				datiProfiloUtenteVisibilita.setProfiloAssegnato("no");
				datiProfiloUtenteVisibilita.setDataInizioVal(new Date());
				datiProfiloUtenteVisibilita.setOrigineAbilitazione("CV");
				if(datiProfiloUtenteVisibilita.getExplicitEntitlement().trim().equalsIgnoreCase("CDR")){
					datiProfiloUtenteVisibilita.setCodiceUfficio(null);
				}else if(datiProfiloUtenteVisibilita.getExplicitEntitlement().trim().equalsIgnoreCase("CODUFFICIO")){
					datiProfiloUtenteVisibilita.setCodiceCdr(null);
				}
				inserimentoProfiliVisibilitaTracciamento(datiProfiloUtenteVisibilita, caricamento, azioneTracciamento);

			}else{
				// Elaborazione Profili non ECA
				datiProfiloUtenteVisibilita.setProfiloAssegnato("no");
				datiProfiloUtenteVisibilita.setDataInizioVal(new Date());
				datiProfiloUtenteVisibilita.setOrigineAbilitazione("CV");
				if(datiProfiloUtenteVisibilita.getExplicitEntitlement().trim().equalsIgnoreCase("CDR")){
					datiProfiloUtenteVisibilita.setCodiceUfficio(null);
				}else if(datiProfiloUtenteVisibilita.getExplicitEntitlement().trim().equalsIgnoreCase("CODUFFICIO")){
					datiProfiloUtenteVisibilita.setCodiceCdr(null);
				}
				profiliDaAggiungereList.add(datiProfiloUtenteVisibilita);
				//inserisciProfiloUtenteInVisibilita(datiProfiloUtenteVisibilita);
				inserimentoProfiliVisibilitaTracciamento(datiProfiloUtenteVisibilita, caricamento, azioneTracciamento);
			}

		}

	}


	private void inserimentoProfiliVisibilitaTracciamento(ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilita, CaricamentoMassivoEntity caricamento, String azioneTracciamento) throws SQLException{
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		ProfiloVisibilitaTracciamentoBean bean = new ProfiloVisibilitaTracciamentoBean();
		bean.setCfUtente(datiProfiloUtenteVisibilita.getCfUtente());
		bean.setCodiceApplicazione(datiProfiloUtenteVisibilita.getCodiceApplicazione());
		bean.setCodiceRoleGroup(datiProfiloUtenteVisibilita.getCodiceRoleGroup());

		bean.setCodiceProfilo(datiProfiloUtenteVisibilita.getCodiceProfilo());
		if(datiProfiloUtenteVisibilita.getExplicitEntitlement()!=null){
			if(datiProfiloUtenteVisibilita.getExplicitEntitlement().trim().equalsIgnoreCase("CDR")){
				bean.setCodiceCdr(datiProfiloUtenteVisibilita.getCodiceCdr());
			}else if(datiProfiloUtenteVisibilita.getExplicitEntitlement().trim().equalsIgnoreCase("CODUFFICIO")){
				bean.setCodiceUfficio(datiProfiloUtenteVisibilita.getCodiceUfficio());
			}
		}
		bean.setIdRichiestaVisibilita(datiProfiloUtenteVisibilita.getIdRichiestaVisibilita());
		bean.setDataInizioVal(new Date(stamp.getTime()));
		bean.setDataFineVal(datiProfiloUtenteVisibilita.getDataFineVal());
		//		bean.setDataVariazione();
		bean.setCfVariazione(caricamento.getCfAmministratore());
		bean.setIdCaricamentoMassivo(Integer.valueOf(caricamento.getIdCaricamento()));
		bean.setAzione(azioneTracciamento);
		profVisTracBean.add(bean);
		//		inserisciProfiliVisibilitaTracciamento(bean);

	}


	private void continuaElaborazione(CaricamentoMassivoRecord cmRecord, RichiestaVisibilitaBean datiRichVis, List<String> errorMessage, List<ProfiloUtenteInVisibilitaBean> aggiuntaProfiliList, List<ProfiloUtenteInVisibilitaBean> rimozioneProfiliList, CaricamentoMassivoEntity caricamento, ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilitaInput) throws SQLException{

		if(cmRecord.getCodiceProfilo().substring(0, 4).equalsIgnoreCase("ECA_")){

			ProfiloUtenteInVisibilitaFinder puvFinder = new ProfiloUtenteInVisibilitaFinder();
			puvFinder.setCodiceFiscale(cmRecord.getCodiceFiscale());
			puvFinder.setIdRichiestaVisibilita(String.valueOf(datiRichVis.getIdRichiestaVisibilita()));
			puvFinder.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
			//			Integer c = countProfiliUtentiInVisibilita(puvFinder);
			Integer c = countVisibilitaPerProfili(puvFinder);
			if(c==0)
				errorMessage.add("Con la rimozione del profilo  "+ cmRecord.getCodiceProfilo() +" la richiesta di visibilite' risulterebbe priva di profili.");

		}else{
			ProfiloUtenteInVisibilitaFinder puvFinder = new ProfiloUtenteInVisibilitaFinder();
			puvFinder.setCodiceFiscale(cmRecord.getCodiceFiscale());
			puvFinder.setIdRichiestaVisibilita(String.valueOf(datiRichVis.getIdRichiestaVisibilita()));
			puvFinder.setCodiceProfilo(cmRecord.getCodiceProfilo());
			//			Integer c = countProfiliUtentiInVisibilita(puvFinder);
			Integer c = countVisibilitaPerProfili(puvFinder);
			if(c==0)
				errorMessage.add("Con la rimozione del profilo  "+ cmRecord.getCodiceProfilo() +" la richiesta di visibilite' risulterebbe priva di profili.");
		}

		if(errorMessage.size()==0){
			// Cancellazione del record da tabella PROFILI_UTENTE_IN_VISIBILITA
			ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilita = new ProfiloUtenteInVisibilitaBean(); 
			datiProfiloUtenteVisibilita.setCfUtente(cmRecord.getCodiceFiscale());
			datiProfiloUtenteVisibilita.setIdRichiestaVisibilita(String.valueOf(datiRichVis.getIdRichiestaVisibilita()));
			datiProfiloUtenteVisibilita.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
			datiProfiloUtenteVisibilita.setCodiceProfilo(cmRecord.getCodiceProfilo());
			profiliDaRimuovereList.add(datiProfiloUtenteVisibilita);
			//deleteProfiloUtenteInVisibilita(datiProfiloUtenteVisibilita);

			// Inserimento record nella tabella PROFILI_VISIBILITA_TRACCIAMENTO
			ProfiloVisibilitaTracciamentoBean profTracBean = new ProfiloVisibilitaTracciamentoBean();

			profTracBean.setCfUtente(cmRecord.getCodiceFiscale());
			profTracBean.setCodiceApplicazione(datiProfiloInserito.getCodiceApplicazione());
			profTracBean.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
			profTracBean.setCodiceProfilo(cmRecord.getCodiceProfilo());
			if(datiProfiloUtenteVisibilitaInput.getExplicitEntitlement()!=null){
				if(datiProfiloUtenteVisibilitaInput.getExplicitEntitlement().trim().equalsIgnoreCase("CDR")){
					profTracBean.setCodiceCdr(cmRecord.getCodiceCdr());
				}else if(datiProfiloUtenteVisibilitaInput.getExplicitEntitlement().trim().equalsIgnoreCase("CODUFFICIO")){
					profTracBean.setCodiceUfficio(datiRichVis.getCodiceUfficio());
				}
			}
			//			profTracBean.setCodiceCdr(cmRecord.getCodiceCdr());
			//			profTracBean.setCodiceUfficio(datiRichVis.getCodiceUfficio());
			profTracBean.setIdRichiestaVisibilita(String.valueOf(datiRichVis.getIdRichiestaVisibilita()));
			profTracBean.setCfVariazione(caricamento.getCfAmministratore());
			profTracBean.setIdCaricamentoMassivo(Integer.valueOf(caricamento.getIdCaricamento()));
			profTracBean.setDataInizioVal(datiProfiloUtenteVisibilitaInput.getDataInizioVal());
			profTracBean.setDataFineVal(datiProfiloUtenteVisibilitaInput.getDataFineVal());
			profTracBean.setAzione(cmRecord.getAzione());
			profVisTracBean.add(profTracBean);
			//			inserisciProfiliVisibilitaTracciamento(profVisTracBean);

			if(cmRecord.getCodiceProfilo().substring(0, 4).equalsIgnoreCase("ECA_") && cmRecord.getCodiceProfilo().substring(9).equals("00000")){
				List<String> listaEcaFigli = getProfiliEcaFigliVisibilita(datiProfiloUtenteVisibilita);
				if(listaEcaFigli.size()!=0){
					for(int z=0; z<listaEcaFigli.size(); z++){
						ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilitaEcaFigli = datiProfiloUtenteVisibilita;
						datiProfiloUtenteVisibilitaEcaFigli.setCodiceProfilo(listaEcaFigli.get(z));
						profiliDaRimuovereList.add(datiProfiloUtenteVisibilitaEcaFigli);
						//						deleteProfiloUtenteInVisibilita(datiProfiloUtenteVisibilitaEcaFigli);
						rimozioneProfiliList.add(datiProfiloUtenteVisibilitaEcaFigli);

						// Per ogni record facciamo un inserimento nella tabella PROFILI_VISIBILITA_TRACCIAMENTO CON:
						// Il codice profilo e' ECA_mmmmmnnnnn
						// Il codice ufficio e' null
						ProfiloVisibilitaTracciamentoBean profVisTracBean_EcaFiglio = new ProfiloVisibilitaTracciamentoBean();
						profVisTracBean_EcaFiglio.setCfUtente(cmRecord.getCodiceFiscale());
						profVisTracBean_EcaFiglio.setCodiceApplicazione(datiProfiloInserito.getCodiceApplicazione());
						profVisTracBean_EcaFiglio.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
						profVisTracBean_EcaFiglio.setCodiceProfilo(listaEcaFigli.get(z));
						profVisTracBean_EcaFiglio.setCodiceCdr(cmRecord.getCodiceCdr());
						profVisTracBean_EcaFiglio.setIdRichiestaVisibilita(String.valueOf(datiRichVis.getIdRichiestaVisibilita()));
						profVisTracBean_EcaFiglio.setCfVariazione(caricamento.getCfAmministratore());
						profVisTracBean_EcaFiglio.setIdCaricamentoMassivo(Integer.valueOf(caricamento.getIdCaricamento()));
						profVisTracBean_EcaFiglio.setDataInizioVal(datiRichVis.getDataInizioValidita());
						profVisTracBean_EcaFiglio.setDataFineVal(datiRichVis.getDataFineValidita());
						profVisTracBean_EcaFiglio.setAzione(cmRecord.getAzione());
						profVisTracBean.add(profVisTracBean_EcaFiglio);
						//						inserisciProfiliVisibilitaTracciamento(profVisTracBean_EcaFiglio);
					}
				}
			}
		}
	}

	//	private List<String> controllaDatiRichiestaVisibByCfCdr(String codiceFiscale, String codiceCdr, String descrizione)throws SQLException{
	//		List<String> messErr = new ArrayList<String>();
	//		HashMap<String,String> queryMap = new HashMap<String,String>();
	//		queryMap.put("codFiscale",codiceFiscale); 
	//		queryMap.put("codCdr",codiceCdr);
	//		List<RichiestaVisibilitaBean> datiRichVis = getDatiRichiestaVisibByCfCdr(queryMap);
	//		if(datiRichVis.size() == 0)
	//			messErr.add("le'operatore non e' in visibilite' nel CdR "+ descrizione +" ( "+codiceCdr+" )");
	//		return messErr;
	//	}

	@Deprecated
	private List<String> controllaCdr(String codCdr, String codiceFiscale, List<RichiestaVisibilitaBean> datiRichVis)throws SQLException{
		List<String> messErr = new ArrayList<String>();
		String descrCdr = getDescrizioneCdrByCodice(codCdr);
		if(descrCdr == null || descrCdr.trim().equals(""))
			messErr.add("Codice CdR "+ codCdr + " non esistente");
		else if(datiRichVis.size() == 0)
			messErr.add("Le'operatore non e' in visibilite' nel CdR "+ descrCdr +" ( "+codCdr+" )");
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
		if(super.verificaDisallineamentoCf(cf)>0){
			esito = true;
		}
		return esito;
	}
	

	private List<String> controllaCodiceRoleGroup(String codRg, String azione)throws SQLException{
		RoleGroupFinder finder = new RoleGroupFinder();
		if(azione == null)
			azione = "";
		finder.setAzione(azione.toUpperCase()); 
		finder.setCodRoleGroup(codRg);
		List<String> messErr = new ArrayList<String>();
		if(codRg == null || codRg.trim().equals(""))
			messErr.add("Codice Role Group non inserito");
		else{
			RoleGroupBean rgbean = getDatiRoleGroupById(finder);
			if(rgbean==null)
				messErr.add("Role Group "+codRg+ " non esistente");
			else{
				codiceApplicazione = rgbean.getCodiceApplicazione();
				if((rgbean.getDisponibileVisibilita()==null || rgbean.getDisponibileVisibilita().trim().equalsIgnoreCase("NO")) 
						&& (azione.trim().equals(AZIONE_AGGIORNAMENTO) || azione.trim().equals(AZIONE_AGGIUNGI)))
					messErr.add("Role Group "+codRg+ " non pue' essere dato in visibilite'");
				if(rgbean.getIdoneoVisibilita().trim().equalsIgnoreCase("NO") 
						&& (azione.trim().equals(AZIONE_AGGIORNAMENTO) || azione.trim().equals(AZIONE_AGGIUNGI)))
					messErr.add("Role Group "+codRg+ " non e' idoneo per la visibilite'");
			}
		}
		return messErr;
	}

	private List<String> controllaCodiceProfilo(String codiceProfilo, String codiceCdr, String azione, boolean isAmministratoreCentraleApplicativo, String cfAmministratore)throws SQLException{
		List<String> messErr = new ArrayList<String>();
		if(codiceProfilo == null || codiceProfilo.trim().equals(""))
			messErr.add("Profilo non inserito");
		else{
			Profilo profilo = getDatiProfiloById(codiceProfilo);
			datiProfiloInserito = profilo;
			if(profilo==null)
				messErr.add("Profilo "+codiceProfilo+ " non esistente");
			else{
				datiProfiloUtenteVisibilita.setCodiceApplicazione(profilo.getCodiceApplicazione());
				datiProfiloUtenteVisibilita.setExplicitEntitlement(profilo.getExplicitEntitlement());

				if((profilo.getDisponibileVisibilita()==null || profilo.getDisponibileVisibilita().trim().equalsIgnoreCase("NO")) 
						&& ((azione != null && azione.trim().equals(AZIONE_AGGIUNGI) 
						|| (azione != null && azione.trim().equals(AZIONE_AGGIORNAMENTO)) ) ))
					messErr.add("Il profilo "+codiceProfilo+ " non pue' essere dato in visibilite'");
				else{
					if(profilo.getCodiceProfilo().substring(0, 4).equalsIgnoreCase("ECA_") 
							&& !profilo.getCodiceProfilo().substring(9).equals("00000")){
						if(azione != null && azione.trim().equals(AZIONE_RIMOZIONE))
							messErr.add("Per la rimozione della visibilite' dei profili CAT deve essere impostato il profilo delle'area");
						else
							messErr.add("Per le'aggiornamento della visibilite' dei profili CAT deve essere impostato il profilo delle'area");
					}else{
						if(profilo.getExplicitEntitlement()==null || profilo.getExplicitEntitlement().trim().equals("")) {
							// 1.3 2023 --> controllo su PROFILO_STRUTTURA_VISIBILITA
							int visibilita = super.getProfiloVisibilitaStruttura(codiceCdr).intValue();
							if(visibilita == 0) {
								messErr.add("Il profilo "+codiceProfilo+ " non pue' essere dato in visibilite'");
							}
							//messErr.add("Il profilo "+codiceProfilo+ " non pue' essere dato in visibilite'");
							// 1.3 2023
						}else if(isAmministratoreCentraleApplicativo){
							HashMap<String,String> queryMap = new HashMap<String,String>();
							queryMap.put("cfAmministratore",cfAmministratore); 
							queryMap.put("codApplicazione",codiceApplicazione);
							queryMap.put("codProfilo",codiceProfilo);
							Integer xx = isAmministratoreCentrale3(queryMap);
							if(xx == 0){
								messErr.add("Profilo " + codiceProfilo + " non e' di competenza dell'amministratore");
							}
						}
					}
				}
			}
		}
		return messErr;
	}

	private List<String> controllaAzione(String azione)throws SQLException{
		List<String> messErr = new ArrayList<String>();
		if(azione == null || azione.trim().equals(""))
			messErr.add("Azione non inserita");
		else{
			if(!azione.trim().equals(AZIONE_AGGIUNGI) 
					&& !azione.trim().equals(AZIONE_AGGIORNAMENTO) && !azione.trim().equals(AZIONE_RIMOZIONE))
				messErr.add("Azione "+azione.toUpperCase()+ " errata");
		}
		return messErr;
	}

	private List<String> compatibilitaProfiloRoleGroup(String profilo, String roleGroup, String azione)throws SQLException{
		List<String> messErr = new ArrayList<String>();
		HashMap<String,String> queryMap = new HashMap<String,String>();
		queryMap.put("codProfilo",profilo); 
		queryMap.put("codRoleGroup",roleGroup);
		queryMap.put("azione",azione);
		Integer combinazioni = countAssocProfRoleGroup(queryMap);
		if(combinazioni == 0)
			messErr.add("Non e' prevista la combinazione role group e' profilo");
		return messErr;
	}

	public EventoBean recuperaDatiEventoSistema(String codiceEvento) throws SQLException{
		EventoBean datiEvento = new EventoBean();
		datiEvento = getEventoCM(codiceEvento);
		return datiEvento;

	}

	private FileAbilitazioneBean gestisciRecordFileAbilitazione(CaricamentoMassivoEntity caricamento, CaricamentoMassivoRecord cmRecord, String errore) throws ParseException{
		// Concateno le varie stringhe di errore
		DateFormat format = new SimpleDateFormat("ddMMyyyy");
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
		fileAbilitazione.setAzione(cmRecord.getAzione());
		fileAbilitazione.setDataFineVal(Utils.convertToDate(cmRecord.getDataScadenza(),"ddMMyyyy hh:mm:ss", true));
				
		return fileAbilitazione;

	}

	private FileAbilitazioneBean gestisciRecordECAAggiuntiFileAbilitazione(CaricamentoMassivoEntity caricamento, CaricamentoMassivoRecord cmRecord, ProfiloUtenteInVisibilitaBean profilo){

		FileAbilitazioneBean fileAbilitazione = new FileAbilitazioneBean();
		fileAbilitazione.setIdCaricamento(caricamento.getIdCaricamento());
		fileAbilitazione.setStato("Aggiunto ECA");
		fileAbilitazione.setCodiceFiscale(cmRecord.getCodiceFiscale());
		fileAbilitazione.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
		fileAbilitazione.setCodiceProfilo(profilo.getCodiceProfilo());
		fileAbilitazione.setCodiceCdr(cmRecord.getCodiceCdr());
		fileAbilitazione.setAzione(cmRecord.getAzione());

		return fileAbilitazione;

	}

	private FileAbilitazioneBean gestisciRecordECARimossiFileAbilitazione(CaricamentoMassivoEntity caricamento, CaricamentoMassivoRecord cmRecord, ProfiloUtenteInVisibilitaBean profilo){

		FileAbilitazioneBean fileAbilitazione = new FileAbilitazioneBean();
		fileAbilitazione.setIdCaricamento(caricamento.getIdCaricamento());
		fileAbilitazione.setStato("Aggiunto ECA");
		fileAbilitazione.setCodiceFiscale(cmRecord.getCodiceFiscale());
		fileAbilitazione.setCodiceRoleGroup(cmRecord.getCodiceRoleGroup());
		fileAbilitazione.setCodiceProfilo(profilo.getCodiceProfilo());
		fileAbilitazione.setCodiceCdr(cmRecord.getCodiceCdr());
		fileAbilitazione.setAzione(AZIONE_RIMOZIONE);

		return fileAbilitazione;

	}

	private void inviaMailSuccessoControlli(CaricamentoMassivoEntity caricamento, String messaggio) throws Exception{
		HashMap<String,String> param = new HashMap<String,String>();
		List<String> destinatari = new ArrayList<String>();	
		destinatari.add(getEmailAmminCaricamento(caricamento.getIdCaricamento()));
		param.put("DESCRIZIONE_RICHIESTA", caricamento.getDescrizioneRichiestaCaricamento());	
		param.put("DATA_INSERIMENTO", caricamento.getDataCaricamento());	
		param.put("MESSAGGIO", messaggio);
		String obj = "mail.oggetto.segnalazioneCMVisib.errore";
		String temp = "mail.template.segnalazioneCMVisib.errore";
		ArrayList<String> subParam = new ArrayList<String>();
		// 20/04/2023
		subParam.add(caricamento.getIdCaricamento());
		// 20/04/2023
		mailCommonUtil.sendMailSemplified(destinatari, new ArrayList<String>(), obj, temp, param, subParam);		
	}

	private void inviaMailErroreUtenteNonAmministratore(CaricamentoMassivoEntity caricamento, String messaggio) throws Exception{
		HashMap<String,String> param = new HashMap<String,String>();
		List<String> destinatari = new ArrayList<String>();	
		InterrogazioneCaricamentiFinder finder = new InterrogazioneCaricamentiFinder();
		destinatari.add(super.getEmailAmminCaricamento(caricamento.getIdCaricamento()));
		param.put("DESCRIZIONE_RICHIESTA", caricamento.getDescrizioneRichiestaCaricamento());	
		param.put("DATA_INSERIMENTO", caricamento.getDataCaricamento());	
		param.put("MESSAGGIO", messaggio);
		String obj = "mail.oggetto.segnalazioneCMVisib.errore";
		String temp = "mail.template.segnalazioneCMVisib.UtenteNonAmministratore.errore";
		ArrayList<String> subParam = new ArrayList<String>();
		subParam.add(caricamento.getIdCaricamento());
		// Lo stato della richiesta viene impostato ad "Annullata"
		//		finder.setIdCaricamento(caricamento.getIdCaricamento());
		//		finder.setStatoCaricamento("ANN");
		//		updateStatoRichiestaCaricamentoAggVis(finder);
		mailCommonUtil.sendMailSemplified(destinatari, new ArrayList<String>(), obj, temp, param, subParam);		
	}

	private void inviaMailConAllegatoCMVisib (CaricamentoMassivoEntity caricamento, String allegatiFilePath, String messaggio) throws Exception{
		// ricavo i destinatari 
		HashMap<String,String> param = new HashMap<String,String>();
		ArrayList<String> subParam = new ArrayList<String>();
		subParam.add(caricamento.getIdCaricamento());
		Map<String,ByteArrayDataSource> badsMap = new HashMap<String,ByteArrayDataSource>();
		List<FileAbilitazioneBean> listFileAbilitazione = getListaRecordScartatiAggVis(caricamento.getIdCaricamento());

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
		destinatari.add(getEmailAmminCaricamentoAggVis(caricamento.getIdCaricamento()));
		param.put("DESCRIZIONE_RICHIESTA", caricamento.getDescrizioneRichiestaCaricamento());	
		param.put("DATA_INSERIMENTO", caricamento.getDataCaricamento());	
		param.put("MESSAGGIO", messaggio);
		String obj = "mail.oggetto.segnalazioneCMVisib.errore";
		String temp = "mail.template.segnalazioneCMVisib.errore";
		mailCommonUtil.sendMailWithAttachment(destinatari, obj , temp, param,badsMap, subParam);
	}

	private String controllaDuplicazioneRecord(List<CaricamentoMassivoRecord> listRecordControllati, CaricamentoMassivoRecord cmRecord, int iterazione){
		String esito ="";
		for(int j=0; j<listRecordControllati.size(); j++){
			CaricamentoMassivoRecord record = listRecordControllati.get(j);
			if(record.getCodiceFiscale().trim().equals(cmRecord.getCodiceFiscale().trim()) && record.getCodiceRoleGroup().trim().equals(cmRecord.getCodiceRoleGroup().trim())
					&& record.getCodiceProfilo().trim().equals(cmRecord.getCodiceProfilo().trim()) && record.getCodiceCdr().trim().equals(cmRecord.getCodiceCdr().trim())
					&& record.getAzione().trim().equals(cmRecord.getAzione().trim())){
				esito = "Record gie' presente nel file" ;
			}
		}

		return esito;
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
		File file_err = new File(allegatiFilePath + "errori_caricamento_massivo_b.xlsx");
		FileOutputStream out = new FileOutputStream(new File(allegatiFilePath + "errori_caricamento_massivo_b.xlsx"));
		workbook.write(out);
		out.close();
		return file_err;
	}


	/**
	 * Inizializza il logger su file
	 * @param logFilePath: path del log
	 * @throws IOException
	 * @throws SQLException 
	 */
	private void initFileLogger(String logFilePath) throws IOException, SQLException{
		String pattern = "%d{dd/MM/yyyy HH:mm:ss} [%-5p] - %m%n";
		PatternLayout layout = new PatternLayout(pattern);	
		FileAppender appender = new FileAppender(layout,logFilePath,false);	    
		fileLog.setLevel((Level) Level.DEBUG);
		fileLog.addAppender(appender);
		fileLog.setAdditivity(false);

	}

	private boolean controllaCfRevocato(String cf) throws SQLException{
		boolean cfRevocato = false;

		PropertiesReader pr = new PropertiesReader(DIR_PROPERTIES,
				FILE_PROPERTIES);
		Properties properties = pr.getProperties();
		String mod = properties.getProperty("MOD");
		if(mod.equals("0")){
			//TEST
			UtenteBean utenteBean = getRevocheUtente(cf);
			if((utenteBean.getRevocaPostazione()!=null && utenteBean.getRevocaPostazione().equals("SI")) ||(utenteBean.getRevocaCau()!=null && utenteBean.getRevocaCau().equals("SI")))
				cfRevocato = true;
		}
			
			else{
//				ServiceCauUtente scu = new ServiceCauUtente(SigaCache.getSYS_ADMIN(), SigaCache.getSYS_AGENZIA());
//				UtenteInfoBean info= new UtenteInfoBean();
//				try {
//					info = scu.getInfo(cf);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				if (info!=null)
//					cfRevocato=true;
				UtenteBean utenteBean = getRevocheUtente(cf);
				if((utenteBean.getRevocaPostazione()!=null && utenteBean.getRevocaPostazione().equals("SI")) ||(utenteBean.getRevocaCau()!=null && utenteBean.getRevocaCau().equals("SI")))
					cfRevocato = true;
				
			}
	 
		return cfRevocato;
	}

	private List<String> compatibilitaProfiloCdr(String codiceCdr, ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilita)throws SQLException{
		List<String> messErr = new ArrayList<String>();
		String vertUffCdr = getVerticeUfficioFromCdr(codiceCdr);
//		if(!codiceCdr.trim().equals(vertUffCdr.trim())&& (datiProfiloUtenteVisibilita.getExplicitEntitlement()!=null && !datiProfiloUtenteVisibilita.getExplicitEntitlement().equalsIgnoreCase("CDR"))){
		if(!codiceCdr.equals(vertUffCdr)&& (datiProfiloUtenteVisibilita.getExplicitEntitlement()!=null && !datiProfiloUtenteVisibilita.getExplicitEntitlement().equalsIgnoreCase("CDR"))){
			messErr.add("Il profilo " + datiProfiloUtenteVisibilita.getCodiceProfilo() + " non pue' essere assegnato nel CdR " + codiceCdr);
		}
		return messErr;
	}

}
