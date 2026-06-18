package it.finanze.siga.dao.impl;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import javax.naming.directory.DirContext;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;

import it.finanze.siga.util.Logged;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import it.finanze.scheduler.bean.AssociazOperRichiAutor;
import it.finanze.scheduler.bean.CauUserIdOrgName;
import it.finanze.scheduler.bean.MailUtility;
import it.finanze.scheduler.bean.NotificaOperatoriDAO;
import it.finanze.scheduler.bean.RegistroRichieste;
import it.finanze.scheduler.bean.RelazioneCdRUfficioStrut;
import it.finanze.scheduler.bean.RichiedenteCdR;
import it.finanze.scheduler.bean.StrutturaIterDAO;
import it.finanze.scheduler.bean.TabellaBatchHrBean;
import it.finanze.scheduler.bean.UserDAO;
import it.finanze.scheduler.bean.Utenti;
import it.finanze.scheduler.bean.UtilDataAccess;
import it.finanze.siga.bean.OperatoreBean;
import it.finanze.siga.business.DelegheBusiness;
import it.finanze.siga.business.MailCommonUtil;
import it.finanze.siga.business.MetodiComuni;
import it.finanze.siga.ejb.IBatchHrVariazioneDatiUtenteNew;
import it.finanze.siga.ejb.util.Utility;
import it.finanze.siga.finder.OperatoreFinder;
import it.finanze.siga.iam.service.ProvisioningClient;
import it.finanze.siga.util.Constants;
import it.finanze.siga.util.SigaCache;
import it.finanze.siga.util.bean.UtenteBean;
import it.finanze.siga.utility.properties.PropertiesReader;
import it.sogei.cau.ws.ServiceCauAmmLocale;
import it.sogei.eaf.util.CheckException;

@Logged
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BatchHrVariazioneDatiUtenteImpl  extends BatchHrVariazioneDatiUtenteDao implements IBatchHrVariazioneDatiUtenteNew{ // BatchHrVariazioneDatiUtenteDao implements IBatchHrVariazioneDatiUtenteNew{{ // BatchHrVariazioneDatiUtenteNewDaoImpl implements IBatchHrVariazioneDatiUtenteNew{

	@Inject MetodiComuni metodiComuni;
	@Inject MailCommonUtil mailCommonUtil;
	@Inject DelegheBusiness delegheBusiness;
	@EJB(beanName = "BatchHrVariazioneDatiUtenteDao")
	private BatchHrVariazioneDatiUtenteDao dao;
	static Logger fileLog = Logger.getLogger(BatchHrVariazioneDatiUtenteImpl.class);
	public static String DIR_PROPERTIES = "/prod/installedApps/SIGA";
	public static String FILE_PROPERTIES = "ini.properties";
 
//	public BatchHrVariazioneDatiUtenteImpl(SqlMapClient sqlMapClient) {
//		super(sqlMapClient);
//		// TODO Auto-generated constructor stub
//	}

	private  void verificaRichiedenteCdR(HashMap<String , String> input, String motivazione) throws Exception  {

		Iterator it2 = null;

		try {
			it2 = selectCdRFromRichiedenteCdRByCFRichiedente(input).iterator();

			String codiceCdRRichiedente = "";
			String userId = input.get("userId");

			String ruoloRevocato = "R";
			UtenteBean utente = new UtenteBean();
			utente.setCodFiscaleUtente("");
			while (it2.hasNext()) {
				RichiedenteCdR richiedenteCdR = (RichiedenteCdR) it2.next();
				codiceCdRRichiedente = richiedenteCdR.getCodiceCdR();

				// richiamo la funzione di replay passando i parametri
				// codiceCdRRichiedente e userId
				fileLog.debug("VERIFICA RICHIEDENTE CDR -"+  input.get("userId") + "CODICE CDR: "+ codiceCdRRichiedente);
				metodiComuni.ruoliRichiedenteEAutorizzatore(userId, "", "",codiceCdRRichiedente, ruoloRevocato, motivazione,utente);

			}
		} catch (CheckException e) {
			fileLog.error(e.getMessage(),e);
			e.printStackTrace();
		}

	}

	private  void verificaAutorizzatoreCdR(HashMap<String , String> input,  String motivazione) throws SQLException 
	{

		Iterator it2=null;
		it2 = selectCdRFromRichiedenteCdRByCFAutorizzatore(input).iterator();

		String codiceCdRAutorizzatore = "";
		String userId = input.get("userId");
		String ruoloRevocato = "A";
		UtenteBean utente = new UtenteBean();
		utente.setCodFiscaleUtente("");
		while (it2.hasNext()){
			RichiedenteCdR richiedenteCdR = (RichiedenteCdR)it2.next();
			codiceCdRAutorizzatore = richiedenteCdR.getCodiceCdR();

			fileLog.debug("      VERIFICA AUTORIZZATORE CDR - "+ input.get("userId")+"CODICE CDR AUTORIZZATORE: "+codiceCdRAutorizzatore);
			// richiamo la funzione di replay passando i parametri codiceCdRAutorizzatore e userId	

			try {
				metodiComuni.ruoliRichiedenteEAutorizzatore(userId, "", "", codiceCdRAutorizzatore, ruoloRevocato, motivazione, utente);
			} catch (Exception e) {
				fileLog.error(e.getMessage(),e);
				e.printStackTrace();
			}

		}

	}

	private void verificaRichiedenteGruppo(HashMap<String , String> input,String motivazione) throws SQLException  {

		Iterator it2=null;

		try {
			it2 = selectCFAutorizzatoreByCFRichiedente(input).iterator();
			String cfAutorizzatore = "";
			String userId = input.get("userId");
			String ruoloRevocato = "R";
			UtenteBean utente = new UtenteBean();
			utente.setCodFiscaleUtente("");
			while (it2.hasNext()){
				AssociazOperRichiAutor autorizzatore = (AssociazOperRichiAutor)it2.next();
				cfAutorizzatore = autorizzatore.getCfAutorizzatore();

				fileLog.debug( "VERIFICA RICHIEDENTE GRUPPO - "  + input.get("userId")+" CF AUTORIZZATORE: "+cfAutorizzatore);
				// richiamo la funzione di replay passando i parametri cfAutorizzatore e userId (cfRichiedente)	
				try {
					metodiComuni.ruoliRichiedenteEAutorizzatore(userId, "", cfAutorizzatore, "", ruoloRevocato, motivazione, utente);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					fileLog.info( e.getMessage());
					throw new CheckException(e);
				}

			}
		} catch (CheckException e) {
			fileLog.error(e.getMessage(),e);
			e.printStackTrace();
		}

	}

	private void verificaAutorizzatoreGruppo(HashMap<String , String> input,   String motivazione) throws SQLException  {


		//try {
		Iterator it2=null;

		try {
			it2 = selectCFRichiedenteByCFAutorizzatore(input).iterator();
			fileLog.debug("");
			String cfRichiedente = "";
			String userId = input.get("userId");
			String ruoloRevocato = "A";
			UtenteBean utente = new UtenteBean();
			utente.setCodFiscaleUtente("");
			while (it2.hasNext()){
				AssociazOperRichiAutor richiedente = (AssociazOperRichiAutor)it2.next();
				cfRichiedente = richiedente.getCfRichiedente();

				fileLog.debug("VERIFICA AUTORIZZATORE GRUPPO - " +input.get("userId")+ " CF RICHIEDENTE: "+cfRichiedente);
				// richiamo la funzione di replay passando i parametri cfRichiedente e userId (cfAutorizzatore)	
				try {
					metodiComuni.ruoliRichiedenteEAutorizzatore(userId, "", cfRichiedente, "", ruoloRevocato, motivazione, utente);
				} catch (Exception e) {
					fileLog.info( e.getMessage());
					throw new CheckException(e);
				}

			}
		} catch (CheckException e) {
fileLog.error(e.getMessage(),e);
			e.printStackTrace();
		}

	}

	private void AutorizzatoreIORIILivello(HashMap<String,String> input, Utenti utentiSiga,  ServiceCauAmmLocale  service, String motivazione) throws Exception  {

		//try {
		fileLog.debug("VERIFICA AUTORIZZATORE PRIMO O SECONDO LIVELLO TAB  struttura_per_iter");
		// verifico sulla tabella struttura_per_iter tramite se il cf e' autorizzatore ( di primo o secondo livello )
		List struttutaIterList=new ArrayList<StrutturaIterDAO>();

		struttutaIterList = verifyAutorizzatoreIORIILivello(input);

		if (struttutaIterList.size()>0){ 

			fileLog.debug("CF " +input.get("userId")+"IS AUTORIZZATORE");
			// MODIFICA DEL 10-03-2015
			Iterator itStruttutaIter = struttutaIterList.iterator();
			//per il cf che stiamo trattando imposto la data fina validite' sulla tabella struttura per iter
			fileLog.debug("AGGIORNO LA DATA FINE VALIDITA SULLA TABELLA STRUTTURA PER ITER");
			dao.updateStrutturaIter(input);

			while (itStruttutaIter.hasNext()){

				StrutturaIterDAO strutturaIter = (StrutturaIterDAO)itStruttutaIter.next();

				if (strutturaIter.getCf_I()!=null && strutturaIter.getCf_I().equalsIgnoreCase(input.get("userId").toString())){
					fileLog.debug(" SET CF_I = null");
					strutturaIter.setCf_I(null);
				}
				if (strutturaIter.getCf_II()!=null && strutturaIter.getCf_II().equalsIgnoreCase(input.get("userId").toString())){
					fileLog.debug(" SET CF_II = null");
					strutturaIter.setCf_II(null);
				}

				fileLog.debug(" INSERT STRUTTURA ITER" + strutturaIter.getIdentificativoIter() );
				dao.insertStrutturaIter(strutturaIter);
			}


			HashMap<String, String> in= new HashMap<String, String>();

			in.put("cdrResp", input.get("cdr"));
			in.put("cfResponsabile",  input.get("userId"));
			OperatoreBean autorizzatore= getDatiUtente2(new OperatoreFinder(input.get("userId")));
			List<Utenti> richiesteInCorso=countRegistroRichieste(in);
			if (!richiesteInCorso.isEmpty()){

				List <NotificaOperatoriDAO> cdr= utentiRichiesteDaSegnalare(in);
				Iterator it=cdr.iterator();
				String cdr_last=null;
				String operatori="";
				String cdr_ii_liv_gerarchico="";

				String centralePeriferico="";
				NotificaOperatoriDAO temp= null;
				List<String> listEmail=new ArrayList<String>();
				HashMap<String,String> param = new HashMap<String,String>();
				param.put("CDR",  input.get("cdr")+" - "+getDescrCdrByCodCDR(input.get("cdr")));
				param.put("AUTORIZZATORE", autorizzatore.getCognome() + " "+autorizzatore.getNome() );
				while (it.hasNext()){

					if (temp!=null){
						String descr_cdr=getDescrCdrByCodCDR(temp.getCdr());
						operatori+="<li>"+temp.getCognome()+" "+temp.getNome()+" ("+temp.getCodiceFiscaleUtente()+") - "+descr_cdr+"</li>";
						cdr_ii_liv_gerarchico=temp.getCodice_cdr_ii_liv_gerarchico();
						cdr_last=temp.getCodice_cdr_ii_liv_gerarchico();
						centralePeriferico=temp.getCentrale_periferico();
					}

					temp=(NotificaOperatoriDAO) it.next();
					String cdr_curr=temp.getCodice_cdr_ii_liv_gerarchico();

					if (cdr_last!=null &&  !cdr_last.equals(cdr_curr)){

						param.put("OPERATORI", operatori );

						String template = "mail.template.batch.perditaRuoloAutorizzatore"; 		 
						String obj = "mail.oggetto.batch.perditaRuoloAutorizzatore";
						if(centralePeriferico.equals("C"))	
							listEmail= selectEmailAmministratoriCentrali();
						else if(centralePeriferico.equals("P"))
							listEmail= selectEmailUtenteByCdRIILivGerarchico(cdr_ii_liv_gerarchico);

						fileLog.debug("INVIO MAIL PERDITA RUOLO AUTORIZZATORE");
						mailCommonUtil.sendMailSemplified(listEmail,new ArrayList<String>(), obj , template , param);
						operatori="";
					}

					if(it.hasNext()==false){
						String descr_cdr=getDescrCdrByCodCDR(temp.getCdr());
						operatori+="<li>"+temp.getCognome()+" "+temp.getNome()+" ("+temp.getCodiceFiscaleUtente()+") - "+descr_cdr+"</li>";
						cdr_last=temp.getCodice_cdr_ii_liv_gerarchico();
						param.put("OPERATORI", operatori );
						String template = "mail.template.batch.perditaRuoloAutorizzatore"; 		 
						String obj = "mail.oggetto.batch.perditaRuoloAutorizzatore";
						if(temp.getCentrale_periferico().equals("C"))	
							listEmail= selectEmailAmministratoriCentrali();
						else if(temp.getCentrale_periferico().equals("P"))
							listEmail= selectEmailUtenteByCdRIILivGerarchico(temp.getCodice_cdr_ii_liv_gerarchico());

						fileLog.debug("INVIO MAIL PERDITA RUOLO AUTORIZZATORE");
						mailCommonUtil.sendMailSemplified(listEmail,new ArrayList<String>(), obj , template , param);
						operatori="";

					}
				}
			}
		}// verifico se il cf mantiene il ruolo di autorizzatore di primo e secondo livello

		boolean verificaRuoloAmministratore1 = false;
		boolean verificaRuoloAmministratore2 = false;
		String autorizzazioneILiv = input.get("autorizzatoreIliv").toString();
		String autorizzazioneIILiv = input.get("autorizzatoreIIliv").toString();
		String userId = input.get("userId").toString();
		String richiedente = input.get("richiedente").toString();
		fileLog.debug("          autorizzazioneILiv: "+autorizzazioneILiv);
		fileLog.debug("          autorizzazioneIILiv: "+autorizzazioneIILiv);
		fileLog.debug("          userId: "+userId);
		fileLog.debug("          richiedente: "+richiedente);
		String tipoAutorizzatore = "";
		boolean verificaMantenimentoRuoloA1 = true;
		boolean verificaMantenimentoRuoloA2 = true;
		boolean verificaMantenimentoRuoloRichiedente = true;
		String ruolo ="";
		String azione = "R";
		String motivoRevoca = motivazione;
		String cfDelegante = userId;
		java.util.Date xmlGregorianCalendar= new java.util.Date();
		Timestamp ts_now = new Timestamp(xmlGregorianCalendar.getTime());

		if(motivazione.equalsIgnoreCase("MDTE")){	
			fileLog.debug("          MOBILITA'");

			if ((autorizzazioneILiv != null & autorizzazioneILiv.equalsIgnoreCase("SI"))||(autorizzazioneIILiv != null & autorizzazioneIILiv.equalsIgnoreCase("SI")||(richiedente != null & richiedente.equalsIgnoreCase("SI")))){


				fileLog.debug("IL PRECEDENTE RESPONSABILE: "+userId+
						" E' AUTORIZZATORE E/O RICHIEDENTE; CHIAMO LA FUNZIONE PER LA VERIFICA DEL MANTENIMENTO DEi RUOLI");


				//chiamo la funzione di reply 'verifica del mantenimento del ruolo di autorizzatore'
				try {
					verificaMantenimentoRuoloA1 = metodiComuni.isAutorizzatoreILiv(userId);
				} catch (CheckException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fileLog.debug("          RISPOSTA DEL SERVIZIO isAutorizzatoreILiv: "+ verificaMantenimentoRuoloA1);
				tipoAutorizzatore = "A1";


				//	XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
				if (!verificaMantenimentoRuoloA1){

					// se restituisce no
					// imposto il flag autorizzatore 1 livello a NO
					//MODIFICA DEL 11-03-2015 ( eseguo update alla fine )
					//sqlMapClientSiga.update("SigaVariazioneDatiUtenti.setFlagAutorizzatoreIlivelloNO", userId);
					utentiSiga.setAutorizzatoreIliv("NO");
					fileLog.debug("          IMPOSTO A NO IL FLAG AUTORIZZAZIONE 1 LIVELLO");

					ruolo = tipoAutorizzatore;
					//chiamo la funzione di gestione deleghe per la revoca delle stesse
					fileLog.debug("          CHIAMO LA FUNZIONE DI GESTIONE DELEGHE PER LA REVOCA DELLE STESSE");
					delegheBusiness.delega(azione, cfDelegante, "", ruolo, xmlGregorianCalendar, motivoRevoca, "", null, "", null);
					// verificaRuoloAmministratore1 = true;
				}
				verificaMantenimentoRuoloA2 = metodiComuni.isAutorizzatoreIILiv(userId);
				//chiamo la funzione di replay 'verifica del mantenimento del ruolo di autorizzatore'
				fileLog.debug("          RISPOSTA DEL SERVIZIO isAutorizzatoreIILiv: "+ verificaMantenimentoRuoloA2);

				if (!verificaMantenimentoRuoloA2){

					// se restituisce no
					// imposto il flag autorizzatore 2 livello a NO
					//MODIFICA DEL 11-03-2015 ( eseguo update alla fine )
					// sqlMapClientSiga.update("SigaVariazioneDatiUtenti.setFlagAutorizzatoreIIlivelloNO", userId);
					utentiSiga.setAutorizzatoreIIliv("NO");
					fileLog.debug("          IMPOSTO A NO IL FLAG AUTORIZZAZIONE 2 LIVELLO");
					ruolo = tipoAutorizzatore;
					//chiamo la funzione di gestione deleghe per la revoca delle stesse
					fileLog.debug("          CHIAMO LA FUNZIONE DI GESTIONE DELEGHE PER LA REVOCA DELLE STESSE");
					delegheBusiness.delega(azione, cfDelegante, "", ruolo, xmlGregorianCalendar, motivoRevoca, "", null, "", null);
					//verificaRuoloAmministratore2 = true;
				}
				try {
					if( metodiComuni.verificaMantenimentoRuoloRichiedente(userId,input.get("cdr"), "").equals("NO"))
						verificaMantenimentoRuoloRichiedente =false;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					fileLog.error(e.getMessage(),e);
					e.printStackTrace();
				}

				fileLog.debug("          RISPOSTA DEL SERVIZIO isRichiedente: "+ verificaMantenimentoRuoloRichiedente);

				if (!verificaMantenimentoRuoloRichiedente){

					utentiSiga.setRichiedente("NO");
					fileLog.debug("          IMPOSTO A NO IL FLAG RICHIEDENTE");

					fileLog.debug("          CHIAMO LA FUNZIONE DI GESTIONE DELEGHE PER LA REVOCA DELLE STESSE");
					ruolo = "RI";
					delegheBusiness.delega(azione, cfDelegante, "", ruolo, xmlGregorianCalendar, motivoRevoca, "", null, "", null);
				}
				if ( !verificaMantenimentoRuoloA1 & !verificaMantenimentoRuoloA2 & !verificaMantenimentoRuoloRichiedente ){

					fileLog.debug("          PERDITA RUOLO AMMINISTRATORE: CHIAMO IL SERVIZIO PER LA REVOCA DEL RUOLO DI AMMINISTRATORE CAU");
					// TODO MCE OBSOLETE
					//service.destituire(userId);
					
					//wsLocale.deleteAdmin(UtilDataAccess.ADMIN, userId, UtilDataAccess.AGENZIA);
				}
			}
		}else if(motivazione.equalsIgnoreCase("CDTE")){
			fileLog.debug("          CESSAZIONE");

			if((autorizzazioneILiv != null & autorizzazioneILiv.equalsIgnoreCase("SI"))){
				// MODIFICA DEL 11-03-2015 ( eseguo update alla fine )
				// sqlMapClientSiga.update("SigaVariazioneDatiUtenti.setFlagAutorizzatoreIlivelloNOPerCessazione", userId);
				utentiSiga.setAutorizzatoreIliv("NO");
				fileLog.debug("          IMPOSTO A NO IL FLAG AUTORIZZAZIONE 1 LIVELLO");
				ruolo = "A1";
				//chiamo la funzione di gestione deleghe per la revoca delle stesse
				fileLog.debug("          CHIAMO LA FUNZIONE DI GESTIONE DELEGHE PER LA REVOCA DELLE STESSE");
				delegheBusiness.delega(azione, cfDelegante, "", ruolo, xmlGregorianCalendar, motivoRevoca, "", null, "", null);
				verificaRuoloAmministratore1 = true;
			}
			if((autorizzazioneIILiv != null & autorizzazioneIILiv.equalsIgnoreCase("SI"))){

				// MODIFICA DEL 11-03-2015 ( eseguo update alla fine )
				//sqlMapClientSiga.update("SigaVariazioneDatiUtenti.setFlagAutorizzatoreIIlivelloNOPerCessazione", userId);
				utentiSiga.setAutorizzatoreIIliv("NO");
				fileLog.debug("          IMPOSTO A NO IL FLAG AUTORIZZAZIONE 2 LIVELLO");
				ruolo = "A2";
				//chiamo la funzione di gestione deleghe per la revoca delle stesse
				fileLog.debug("          CHIAMO LA FUNZIONE DI GESTIONE DELEGHE PER LA REVOCA DELLE STESSE");
				delegheBusiness.delega(azione, cfDelegante, "", ruolo, xmlGregorianCalendar, motivoRevoca, "", null, "", null);
				verificaRuoloAmministratore2 = true;
			}
			if ( verificaRuoloAmministratore1 || verificaRuoloAmministratore2 || richiedente.equalsIgnoreCase("SI") ){
				//chiamare il servizio per la revoca del ruolo di amministratore CAU
				fileLog.debug("          POICHE' L'UTENTE CESSATO ERA AMMINISTRATORE, CHIAMO IL SERVIZIO PER LA REVOCA DEL RUOLO DI AMMINISTRATORE CAU");
				// TODO MCE OBSOLETE
				//service.destituire(userId);
//				deleteAdmin(UtilDataAccess.ADMIN, userId, UtilDataAccess.AGENZIA);
				// MODIFICA DEL 11-03-2015 ( eseguo update alla fine )
				//sqlMapClientSiga.update("SigaVariazioneDatiUtenti.setFlagRichiedenteNOPerCessazione", userId);
				utentiSiga.setRichiedente("NO");
			}
		}

	}

	private void verificaDelegato(HashMap input, String motivazionePerDelegato) throws SQLException, CheckException{


		String azione = "R";
		String cfDelegato = input.get("userId").toString();
		String ruolo = "";
		String motivoRevoca = motivazionePerDelegato;


		fileLog.debug("VERIFICA DELEGATO - " +  input.get("userId"));
		// se e' un delegato
		if ((Integer)verifyDelegato(cfDelegato)>0){
			java.util.Date xmlGregorianCalendar= new java.util.Date();
			Timestamp ts_now = new Timestamp(xmlGregorianCalendar.getTime());

			delegheBusiness.delega(azione, "", cfDelegato, ruolo, xmlGregorianCalendar, motivoRevoca, "", null, "", null);

		}
	}

	private void verificaOperatoreAssociatoARichiedenteEAutorizzatore( HashMap<String,String> input) throws CheckException, SQLException{

		fileLog.debug("VERIFICA OPERATORE ASSOCIATO");
		int numeroOperatori = 0;
		numeroOperatori = (Integer)countAssociazOperRichiAutor(input);
		if (numeroOperatori==1){
			boolean verificaRichiedente = true;
			boolean verificaAutorizzatore = true;
			String richiedente = "";
			String autorizzatoreIliv = "";
			String cfGenerico = "";
			Utenti utentiForOperatore = null;
			AssociazOperRichiAutor richiedenteEAutorizzatore = (AssociazOperRichiAutor)selectCFRichiedenteEAutorizzatoreByCFOperatore(input);

			dao.closeAssociazOperRichiAutorByCFOperatore(input);

			cfGenerico = richiedenteEAutorizzatore.getCfRichiedente();
			utentiForOperatore = (Utenti)selectUtenteByCF(cfGenerico);
			if (utentiForOperatore!=null){
				if(richiedenteEAutorizzatore.getCfRichiedente()!=null){

					//inputForUtenti.put("codiceFiscale", richiedenteEAutorizzatore.getCfRichiedente());

					try {
						richiedente =   metodiComuni.verificaMantenimentoRuoloRichiedente(richiedenteEAutorizzatore.getCfRichiedente(),richiedenteEAutorizzatore.getCodiceCdRRichiedente(), "");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (richiedente.equals("SI")){
						verificaRichiedente=true;

					}
					else if(richiedente.equals("NO")){
						verificaRichiedente=false;
					}

				}
				if(richiedenteEAutorizzatore.getCfAutorizzatore()!=null){
					verificaAutorizzatore = metodiComuni.isAutorizzatoreILiv(richiedenteEAutorizzatore.getCfAutorizzatore());
					if (verificaAutorizzatore){
						autorizzatoreIliv = "SI";
					}
					else if(!verificaAutorizzatore){
						autorizzatoreIliv = "NO";	

					}
				}


				if((richiedenteEAutorizzatore.getCfRichiedente().equalsIgnoreCase(richiedenteEAutorizzatore.getCfAutorizzatore()))&&(!verificaRichiedente||!verificaAutorizzatore)){
					utentiForOperatore.setRichiedente(richiedente);
					utentiForOperatore.setAutorizzatoreIliv(autorizzatoreIliv);
					//sqlMapClientSiga.update("SigaVariazioneDatiUtenti.updateUtentiForVerificaOperatore", utentiForOperatore);
				}else if (!(richiedenteEAutorizzatore.getCfRichiedente().equalsIgnoreCase(richiedenteEAutorizzatore.getCfAutorizzatore()))){

					if (!verificaRichiedente){
						utentiForOperatore.setRichiedente(richiedente);
					}
					if (!verificaAutorizzatore){
						utentiForOperatore.setAutorizzatoreIliv(autorizzatoreIliv);
					}

				}

				dao.updateUtentiForVerificaOperatore(utentiForOperatore);

			}
		}else if (numeroOperatori>1)
		{
			//lanciare eccezione;
			//throw new it.finanze.siga.util.Exception_Exception(null,null);
			//throw new java.lang.Exception();

			throw new CheckException("pie' records presenti in tabella");

		}

	}

	private void trattamentoRichieste(HashMap input) throws SQLException {

		fileLog.debug("TRATTAMENTO RICHIESTE");
		ArrayList <String> dest = new ArrayList<String>();

		String oggetto = "Notifica Chiusura Richiesta";
		String intestazione = "Notifica Chiusura richiesta per CF_Utente: "+input.get("userId");
		String testoEmail = "Notifica Chiusura richiesta per CF_Utente: "+input.get("userId");
		fileLog.debug("          testoEmail: "+testoEmail);

		dao.annullamentoRichieste(input);
		dao.annullamentoRichieste2(input);

		Iterator it5 = selectCFPresaINCaricoFromRegistroRichieste(input).iterator();

		while(it5.hasNext()){
			RegistroRichieste registroRichieste = (RegistroRichieste)it5.next();

			String cfPresaInCarico = registroRichieste.getCfPresaInCarico();
			Integer intgIdRichiesta = new Integer(registroRichieste.getIdRichiesta());
			testoEmail = intestazione +",ID Richiesta: "+ intgIdRichiesta.toString();
			fileLog.debug("TRATTAMENTO RICHIESTE - CF PRESA IN CARICO: "+cfPresaInCarico);
			Utenti utenti = (Utenti)selectCFbyCFPresaInCaricoFromUtenti(cfPresaInCarico);
			dest.add(utenti.geteMail());

			fileLog.debug("TRATTAMENTO RICHIESTE - INVIO MAIL: "+utenti.geteMail());
			try{
				MailUtility.sendMail(dest, oggetto, testoEmail);
			} catch (MessagingException e) {
				fileLog.error(e.getMessage(),e);
				// TODO Blocco catch generato automaticamente
				e.printStackTrace();
			}

		}

	}

	private void gestoreOperatori(HashMap input)throws CheckException{

		try {
			RelazioneCdRUfficioStrut relazioneCdrUfficioStrut= new RelazioneCdRUfficioStrut();
			relazioneCdrUfficioStrut = (RelazioneCdRUfficioStrut)strutturaOld(input);

			fileLog.debug("GESTORE OPERATORI");
			String codiceStrutturaOld = relazioneCdrUfficioStrut.getCodiceStruttura();
			relazioneCdrUfficioStrut = (RelazioneCdRUfficioStrut)strutturaNew(input);
			String codiceStrutturaNew = relazioneCdrUfficioStrut.getCodiceStruttura();
			if (!codiceStrutturaOld.equalsIgnoreCase(codiceStrutturaNew)){

				input.put("tabellaAggiornata", "gestori_operatori");

				if (!getRuoliGestoriOperatori((String)input.get("userId")).isEmpty()){

					dao.insertAuditOperazioni(input);
					fileLog.debug(" GESTORE OPERATORI - INSERITO: "+(String)input.get("userId"));
				}
				int idAuditFine = selectMaxIDAudit();


				fileLog.debug(" GESTORE OPERATORI - AGGIORNAMENTO idAuditFine: "+idAuditFine);
				Integer objectIdAuditFine = new Integer (idAuditFine);

				fileLog.debug("GESTORE OPERATORI - AGGIORNAMENTO objectIdAuditFine: "+objectIdAuditFine);
				input.put("idAuditFine", String.valueOf(objectIdAuditFine));
				dao.updateGestoreOperatore(input);

			}
		} catch (Exception e) {
			fileLog.error(e.getMessage(),e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void startBatch(String verifica ) throws Exception, CheckException {
 
		Properties prop = new Properties();
		prop.load(new FileInputStream(Constants.FILE_PROPERTY_PATH+"/"+Constants.FILE_PROPERTY_NAME));
		
		String logVariazioneDatiUtente = prop.getProperty("BATCH_HR_VARIAZIONE_DATI_UTENTE_LOG_FILE_PATH");
		
		// TASK 2.9 2023 log per verifica solo variazione CdR: -->
		boolean verificaAll = true;
		if(verifica!=null && verifica.equalsIgnoreCase("NO")) {
			verificaAll = false;
		}
		
		
		if(!verificaAll) {
			logVariazioneDatiUtente = prop.getProperty("BATCH_HR_VARIAZIONE_DATI_UTENTE_CDR_LOG_FILE_PATH");
		}
		 // 2.9 2023 <<--
		
		Utility.initFileLogger(logVariazioneDatiUtente,fileLog);

		ServiceCauAmmLocale service = new ServiceCauAmmLocale(UtilDataAccess.ADMIN, UtilDataAccess.AGENZIA);
		
		//Reader readerCAU = Resources.getResourceAsReader("it/finanze/siga/xml/SqlMapConfigCAU.xml");
		SqlMapClient sqlMapClientCAU = null; //SqlMapClientBuilder.buildSqlMapClient(readerCAU, prop);
		//readerCAU.close();
		UserDAO userDao= new UserDAO();
		DirContext dctx=UtilDataAccess.getDirContext();
//		DirContext dctx = null;
		TabellaBatchHrBean settaggiBatch = selectSettingsBatch();

		
		if (settaggiBatch.getDataFineResponsabiliCdr()!=null)
		{
			fileLog.debug("INIZIO ELABORAZIONE BATCH_HR_VARIAZIONE_DATI_UTENTE ");
			
			// TASK 2.9 2023 solo se verifica completa: -->
			if(verificaAll) {
				dao.startBatchVariazioneDatiUte();
			}
			// 2.9 2023 <--
			
			
			String userId = null;
			String orgName = null;
			String utentiCauScartatiPerSQLException = "";
			String utentiCauScartatiPerException = "";
			String utentiCauScartatiPerException_Exception = "";
			String utentiCauScartatiPerNamingException = "";
			String utentiCauScartatiPerCheckException_Exception = "";

			try {
				fileLog.info("   RECUPERO L'AMBIENTE IN CUI STA GIRANDO IL BATCH ");
				PropertiesReader pr = new PropertiesReader(DIR_PROPERTIES, FILE_PROPERTIES);
				Properties properties = pr.getProperties();
				String mod =  properties.getProperty("MOD");
				fileLog.info("   RECUPERO LA LISTA DEI CDR CHE DEVONO ESSERE UTILIZZATI COME FILTRO DELL'ESECUZIONE  ");
				String cfListFiltro =  properties.getProperty("filtriCf");
				List<String> filtriCf = new ArrayList<String>();
				if(cfListFiltro!=null){
					filtriCf = getListaCfFiltri(cfListFiltro);
				}
				
				
				Iterator itCAU = null;
				
				// TASK 2.9 2023 -->
				if(!verificaAll) { // solo verifica CDR -->
					fileLog.debug("Lista CF utenti SIGA con CDR disallineato...");
					// recupero lista CF degli utenti SIGA con cdr disallineato
					List<String> listaUtDisall = selectUtentiByCdrDisall();
					
					if(listaUtDisall!=null && !listaUtDisall.isEmpty()) {
						fileLog.debug("CF presenti, chiamata servizio CAU per verifica...");
						itCAU = ProvisioningClient.getUtenteCTSA(listaUtDisall).iterator();
						fileLog.debug("ChiamataServizio CAU effettuata, utenti recuperati - " + itCAU==null?"nessuno":"OK");
					}
				}else {
					itCAU = ProvisioningClient.getAllUtentiCTSA().iterator();
				}
				


//				if (CauParam.getInstance().WEB_SERVICES_ON==0)
//					itCAU = sqlMapClientCAU.queryForList("CTSAVariazioneDatiUtenti.selectAllUtentiCTSA").iterator();
//				else
				//	itCAU = ProvisioningClient.getAllUtentiCTSA().iterator();
				// TASK 2.9 2023 <--
				
				int numeroCFCauElaborati = 0;
				int numeroCFSigaElaborati = 0;
				int numeroCFCauTotali = 0;
				int numeroCFSigaTotali = 0;

				int numeroCFSigaScartatiPerSQLException = 0;
				int numeroCFSigaScartatiPerException = 0;
				int numeroCFSigaScartatiPerException_Exception = 0;
				int numeroCFSigaScartatiPerCheckException_Exception = 0;

				int numeroCFCauScartatiPerSQLException = 0;
				int numeroCFCauScartatiPerException = 0;
				int numeroCFCauScartatiPerException_Exception = 0;
				int numeroCFCauScartatiPerNamingException = 0;
				int numeroCFCauScartatiPerCheckException_Exception = 0;

				int numeroUtentiPresentiSoloSuCau = 0;
				int numeroUtentiPresentiSuCauESiga = 0;
				int numeroUtentiPresentiSuCauESigaConCDRDiverso = 0;
				int numeroUtentiPresentiSuCauESigaConCDRUguale = 0;

				String motivazione;
				String flagRichiedente;
				String flagAutorizzatore1;
				String motivazionePerDelegato;
				String testoAudit;
				String flagAutorizzatore2;		    


				fileLog.debug("SELEZIONO GLI UTENTI CAU ");
				if(itCAU != null) {
				while (itCAU.hasNext()){
					try{

						motivazione = "";
						flagRichiedente = "";
						flagAutorizzatore1 ="";
						flagAutorizzatore2 ="";
						motivazionePerDelegato = "";
						testoAudit = "";
						userId = "";
						orgName = "";
						String cfGenerico ="";

						CauUserIdOrgName cauUserIdOrgName = (CauUserIdOrgName)itCAU.next();
						userId = cauUserIdOrgName.getUserId();
						String orgNameCAU = cauUserIdOrgName.getOrgName();
						orgName = orgNameCAU.substring(4);
						
						// MEV 02/2019
						// Aggiunto filtro sui cf da controllare presi da file di properties
						if(!verificaAll||(mod!=null && mod.trim().equals("3")) || (mod!=null && !mod.trim().equals("3") && filtriCf.contains(userId) )){
							HashMap<String , ArrayList<String>> hashMapUserFromCTSALdap = userDao.getUserFromLDAP(userId, dctx);
							//fileLog.debug("       RICAVATI I DATI DELL'UTENTE DA LDAP ");
							HashMap<String , String> input = new HashMap<String, String>();
							String sn = (hashMapUserFromCTSALdap.get("sn")==null || ((ArrayList<String>)hashMapUserFromCTSALdap.get("sn")).size()==0) ? "":
								((ArrayList<String>)hashMapUserFromCTSALdap.get("sn")).get(0);
	
							input.put("sn", sn);
							//fileLog.debug("          sn: "+sn);
	
							String givenName = (hashMapUserFromCTSALdap.get("givenName")==null || ((ArrayList<String>)hashMapUserFromCTSALdap.get("givenName")).size()==0) ? "":
								((ArrayList<String>)hashMapUserFromCTSALdap.get("givenName")).get(0);
	
							input.put("givenName", givenName);
							//fileLog.debug("          givenName: "+givenName);
	
							String mail = (hashMapUserFromCTSALdap.get("mail")==null || ((ArrayList<String>)hashMapUserFromCTSALdap.get("mail")).size()==0) ? "":
								((ArrayList<String>)hashMapUserFromCTSALdap.get("mail")).get(0);
	
							String telephoneNumber = (hashMapUserFromCTSALdap.get("telephoneNumber")==null || ((ArrayList<String>)hashMapUserFromCTSALdap.get("telephoneNumber")).size()==0) ? "":
								((ArrayList<String>)hashMapUserFromCTSALdap.get("telephoneNumber")).get(0);	
	
							if(!SigaCache.getMOD().equals("3"))
								input.put("mail", mail+"prova");
							else
								input.put("mail", mail);
							//fileLog.debug("          mail: "+mail);
							input.put("userId", userId);
							input.put("orgName", orgName);
							input.put("telephoneNumber", telephoneNumber);
							//fileLog.debug("          userId: "+userId);
	
							//fileLog.debug("          orgName: "+orgName);
	
							Integer numeroUtentiSiga = countUtenteByCF(userId);
	
							Utenti utentiSiga = (Utenti)selectUtenteByCF(userId);
							
						 
	
	// TODO MCE MOCK
							if (true || numeroUtentiSiga.intValue() == 0  && countUtenteByCFClosed(userId)==0){
								//utente presente su ctsa e non su siga
	
								fileLog.debug("UTENTE NON PRESENTE SU SIGA CF: "+userId);
								//cauUserIdOrgName.seteMail(mail);
								//inserisco il nuovo utente sulla tabella utenti di siga
								//sqlMapClientSiga.insert("SigaVariazioneDatiUtenti.insertUtentiSiga", cauUserIdOrgName);
								fileLog.debug("INSERIMENTO SULLA TABELLA UTENTI CF: " + userId);
								dao.insertUtentiSiga(input);
	
								//inserisco il nuovo utente sulla tabella storico cdr utenti di siga
								//sqlMapClientSiga.insert("SigaVariazioneDatiUtenti.insertStoricoCdRUtenti", cauUserIdOrgName);
								fileLog.debug("INSERIMENTO SULLA TABELLA STORICO CDR UTENTI CF: " + userId + " CDR " + orgName);
								//insertStoricoCdRUtenti(input);
								input.put("tipoEvento", "I");
								input.put("oldCdR", null);
	
								fileLog.debug("INSERIMENTO SULLA TABELLA STORICO UTENTI AGGIORANTI HR CF: " + userId + " CDR " + orgName);
								dao.insertUtentiAggiornatiHR_Var(input);
								numeroUtentiPresentiSoloSuCau ++;
	
							}   
	
							if(numeroUtentiSiga.intValue() == 0 && countUtenteByCFClosed(userId)!=0) {
								HashMap inputRipristino = new HashMap();
								inputRipristino.put("codiceFiscaleUtenti", userId);
								inputRipristino.put("newCdr", orgName);
	
								fileLog.debug("RIPRISTINO UTENTE CF: " + userId + " CDR " + orgName);
								dao.updateUtentiPerRipristino(inputRipristino);
								input.put("tipoEvento", "I");
								fileLog.debug("INSERIMENTO SULLA TABELLA STORICO CDR UTENTI CF: " + userId + " CDR " + orgName);
								dao.insertStoricoCdRUtenti(input);
								fileLog.debug("INSERIMENTO SULLA TABELLA STORICO UTENTI AGGIORANTI HR CF: " + userId + " CDR " + orgName);
								dao.insertUtentiAggiornatiHR_Var(input);
	
							}
							else if (numeroUtentiSiga.intValue() != 0 && countUtenteByCFClosed(userId)==0)
	
							{
								// utente presente su siga e ctsa ( stesso codice fiscale ): confronto i dati anagrafici
								//MODIFICA DEL 11-03-2015 ( modifica per inserimento nuovo metodo)
								cfGenerico = userId;
								String nomeUtenteSiga = "";
								String cognomeUtenteSiga ="";
								String eMailUtenteSiga  = "";
	
								if (utentiSiga.getNome()!= null){
									nomeUtenteSiga = utentiSiga.getNome();
								}
	
								if (utentiSiga.getCognome()!= null){
									cognomeUtenteSiga = utentiSiga.getCognome();
								}
	
								if (utentiSiga.geteMail()!= null){
									eMailUtenteSiga = utentiSiga.geteMail();
								}
	
								String nomeUtenteCTSA = input.get("givenName");
								String cognomeUtenteCTSA = input.get("sn");
								String eMailUtenteCTSA = input.get("mail");
								boolean nome = false;
								boolean cognome = false;
								boolean email = false;
								// verifico l'anagrafica dell'utente
								if ((nomeUtenteCTSA!=null && !nomeUtenteCTSA.equals(""))&&(!nomeUtenteSiga.equalsIgnoreCase(nomeUtenteCTSA))){
	
									fileLog.debug("nome utente su siga: "+nomeUtenteSiga+ "diverso da ctsa: "+nomeUtenteCTSA);
									utentiSiga.setNome(nomeUtenteCTSA);
									nome = true;
								}
								if ((cognomeUtenteCTSA!=null && !cognomeUtenteCTSA.equals(""))&&(!cognomeUtenteSiga.equalsIgnoreCase(cognomeUtenteCTSA))){
	
									fileLog.debug("cognome utente su siga: "+cognomeUtenteSiga+ "diverso da ctsa: "+cognomeUtenteCTSA);
									utentiSiga.setCognome(cognomeUtenteCTSA);
									cognome = true;
								}
								if (!eMailUtenteSiga.equalsIgnoreCase(eMailUtenteCTSA)){
	
									fileLog.debug("email utente su siga: "+eMailUtenteSiga+ "diversa da ctsa: "+eMailUtenteCTSA);
									utentiSiga.seteMail(eMailUtenteCTSA);
									email = true;
								}
	
								String codiceCdRSiga = utentiSiga.getCodiceCdR();
								//	fileLog.debug("          codiceCdRSiga: "+codiceCdRSiga);
								String codiceFiscaleSiga = utentiSiga.getCodiceFiscale();
	
								//	fileLog.debug("          codiceFiscaleSiga: "+codiceFiscaleSiga);
								//	fileLog.debug("          confronto il CdR di ctsa: "+orgName+" con quello della tabella utenti ( filtrata per codice Fiscale ): "+codiceCdRSiga);
								numeroUtentiPresentiSuCauESiga ++;
								// confronto il CdR di ctsa con quello della tabella utenti ( filtrata per codice Fiscale )
								if (!orgName.equalsIgnoreCase(codiceCdRSiga)){
	
									fileLog.debug(" IL CDR DI CTSA E QUELLO DELLA TABELLA UTENTI SONO DIVERSI");
									motivazione = "MDTE";
									// il cdr di ctsa e quello della tabella utenti sono diversi
									// imposto la data fine validite' = null per il record con il vecchio cdr 
									fileLog.debug("AGGIORNAMENTO STORICO CDR UTENTI imposto la data fine validite' = null per il record con il vecchio cdr "+utentiSiga.getCodiceCdR() );
									dao.updateStoricoCdrUtenti(utentiSiga);
	
									fileLog.debug("INSERMIMENTO STORICO CDR UTENTI sostituisco il codice cdr di siga con quello di ctsa " + orgName);
									// il cdr di ctsa e siga sono diversi: sostituisco il codice cdr di siga con quello di ctsa
									dao.insertStoricoCdRUtenti(input);
	
	
									input.put("oldCdR", codiceCdRSiga);
									//aggiorno il Cdr sulla tabella Utenti con il nuovo CdR di ctsa
									fileLog.debug("aggiorno il Cdr sulla tabella Utenti con il nuovo CdR di ctsa");
	
									//MODIFICA DEL 11-03-2015 ( eseguo tutti gli update alla fine )
									//sqlMapClientSiga.update("SigaVariazioneDatiUtenti.updateUtenti",input);
									utentiSiga.setCodiceCdR(orgName);
									input.put("cdr",orgName);
									input.put("tipoEvento", "M");
									fileLog.debug("inserito utente sulla tabella utenti aggiornati HR con tipo evento = M " );
									dao.insertUtentiAggiornatiHR_Var(input);
									numeroUtentiPresentiSuCauESigaConCDRDiverso ++;
	
									// riduzione codice: inserire nuovamente per la versione integrale
									//-----------------------------------------------------------------------------------------
									flagRichiedente = utentiSiga.getRichiedente();
	
									fileLog.debug("flagRichiedente: "+flagRichiedente);
									if (flagRichiedente.equalsIgnoreCase("SI")){
	
										verificaRichiedenteCdR(input,motivazione);		
									}
									flagAutorizzatore1 = utentiSiga.getAutorizzatoreIliv();
									fileLog.debug("flagAutorizzatore1: "+flagAutorizzatore1);
									if (flagAutorizzatore1.equalsIgnoreCase("SI")){
	
										verificaAutorizzatoreCdR(input,motivazione);
									}
									verificaRichiedenteGruppo(input,motivazione);
									verificaAutorizzatoreGruppo(input,motivazione);
	
									// verifica se il cf e' autorizzatore di 1 o 2 livello perche' definito in struttura per iter
									flagAutorizzatore2 = utentiSiga.getAutorizzatoreIIliv();
									fileLog.debug("flagAutorizzatore2: "+flagAutorizzatore2);
									input.put("autorizzatoreIliv", flagAutorizzatore1);
									input.put("autorizzatoreIIliv", flagAutorizzatore2);
									input.put("richiedente", flagRichiedente);
									AutorizzatoreIORIILivello(input,utentiSiga,service,motivazione);
	
									motivazionePerDelegato = "MDTO";
									verificaDelegato(input, motivazionePerDelegato);
									verificaOperatoreAssociatoARichiedenteEAutorizzatore(input);
									//rimozione del trattamneto richieste a seguito dell'introduziojne del bath delle mobilite'
									//trattamentoRichieste(input);
									testoAudit = "Revoca per mobilite' del ruolo di gestore operatore";
									fileLog.debug("          testoAudit: "+testoAudit);
									input.put("testoAudit", testoAudit);
									gestoreOperatori(input);
	
								}else{
	
									numeroUtentiPresentiSuCauESigaConCDRUguale ++;
								}
								// MODIFICA DEL 11-03-2005 ( eseguo update utenti alla fine )
								if (nome||cognome||email||!orgName.equalsIgnoreCase(codiceCdRSiga)){
									fileLog.debug("AGGIORNAMENTO TABELLA UTENTE PER VARIAZIONE COGNOME, NOME, EMAIL");
									dao.updateUtenti(utentiSiga);
	
								}
	
								OperatoreFinder operatoreFinder = new OperatoreFinder(userId);
								operatoreFinder.setFlagCdrDisallCauSiga("NO");
								dao.updateFlagUtenteCdrDisallienato(operatoreFinder);
							}
							numeroCFCauElaborati ++;
							
						} // Fine Filtro Test

					}catch (CheckException  e) {
						dctx.close();
						fileLog.debug("Exception_Exception di utenti presenti su cau: " +e);
						fileLog.debug("  Message Exception_Exception di utenti presenti su cau: " +e.getMessage());
						fileLog.debug("    StackTrace Exception_Exception di utenti presenti su cau: " +e.getStackTrace().toString());
						numeroCFCauScartatiPerException_Exception ++; 
						utentiCauScartatiPerException_Exception = utentiCauScartatiPerException_Exception + " CF: "+userId +", CdR: "+orgName +" -- ";

					}

					numeroCFCauTotali ++;
				}
				} else {
					fileLog.debug("NESSUN UTENTE CAU da VERIFICARE");
				}
				// --> 2.9 2023 <--
				
				fileLog.debug("FINE ELABORAZIONE: numero di CFCau totali = "+numeroCFCauTotali);
				fileLog.debug(" FINE ELABORAZIONE: numero di CFCau lavorati = "+numeroCFCauElaborati);
				fileLog.debug("    FINE ELABORAZIONE: numero di CFCau presenti solo su CAU = "+numeroUtentiPresentiSoloSuCau);
				fileLog.debug("    FINE ELABORAZIONE: numero di CFCau lavorati presenti su cau e siga = "+numeroUtentiPresentiSuCauESiga);
				fileLog.debug("        FINE ELABORAZIONE: numero di CFCau lavorati presenti su cau e siga con cdr diverso = "+numeroUtentiPresentiSuCauESigaConCDRDiverso);
				fileLog.debug("        FINE ELABORAZIONE: numero di CFCau lavorati presenti su cau e siga con cdr uguale = "+numeroUtentiPresentiSuCauESigaConCDRUguale);
				fileLog.debug(" FINE ELABORAZIONE: numero di CFCau scartati per SQLException : "+numeroCFCauScartatiPerSQLException + utentiCauScartatiPerSQLException);
				fileLog.debug(" FINE ELABORAZIONE: numero di CFCau scartati per NamingException : "+numeroCFCauScartatiPerNamingException + utentiCauScartatiPerNamingException);
				fileLog.debug(" FINE ELABORAZIONE: numero di CFCau scartati per Exception_Exception : "+numeroCFCauScartatiPerException_Exception + utentiCauScartatiPerException_Exception );
				fileLog.debug(" FINE ELABORAZIONE: numero di CFCau scartati per CheckException_Exception : "+numeroCFCauScartatiPerCheckException_Exception + utentiCauScartatiPerCheckException_Exception );
				fileLog.debug(" FINE ELABORAZIONE: numero di CFCau scartati per Exception : "+numeroCFCauScartatiPerException + utentiCauScartatiPerException );

				
				// 2.9 2023 solo se tutte le verifiche --> 
				if(verificaAll) {
				// 2.9 2023 <--
					int numeroUtentiPresentiSoloSuSiga = 0;
					String utentiSigaScartatiPerSQLException = "";
					String utentiSigaScartatiPerException = "";
					String utentiSigaScartatiPerException_Exception = "";
					String utentiSigaScartatiPerCheckException_Exception = "";
	
					String oldCdR = null;
					String codiceFiscaleUtenti = null;
	
					
					// verifico se ci sono utenti siga che non sono pie' presenti su CAU
					// TODO MCE BYPASS
					final int PAGE_SIZE_SIGA = 3;
					int pageOffsetSiga = 0;
					boolean hasMorePagesSiga = true;
					while (hasMorePagesSiga) {
					List<Utenti> pageSiga = selectAllCFUtenti(pageOffsetSiga, PAGE_SIZE_SIGA);
					hasMorePagesSiga = false ; // pageSiga.size() == PAGE_SIZE_SIGA;
					Iterator itSiga = pageSiga.iterator();

					while (itSiga.hasNext()){
	
						try{	
							motivazione = "";
							flagRichiedente = "";
							flagAutorizzatore1 ="";
							flagAutorizzatore2 ="";
							motivazionePerDelegato = "";
							testoAudit = "";
							oldCdR = "";
							codiceFiscaleUtenti="";
	
							//Utenti utenti = (Utenti)itSiga.next();
							Utenti utentiSiga = (Utenti)itSiga.next();
							codiceFiscaleUtenti = utentiSiga.getCodiceFiscale();
							oldCdR = utentiSiga.getCodiceCdR();
							// try{
							
							
							/*********************************
							 * FILTRO PER FARE I TEST
							 ********************************/
	//						if(codiceFiscaleUtenti.trim().equals("DDIFBA69A06E230J")){ --- vecchia versione del filtro
								// MEV 02/2019
								// Aggiunto filtro sui cf da controllare presi da file di properties
							if(!verificaAll|| (mod!=null && mod.trim().equals("3")) || (mod!=null && !mod.trim().equals("3") && filtriCf.contains(codiceFiscaleUtenti) )){
								// TODO MCE MOCK
								Integer numeroUtentiCau = (true) ? 1 : ProvisioningClient.countUtenteCTSAByCF(codiceFiscaleUtenti);
	
								if(numeroUtentiCau.intValue()==0){
									fileLog.debug("UTENTE PRESENTE SU SIGA MA NON PIU' PRESENTE SU CAU");
									HashMap input = new HashMap();
									//MODIFICA DEL 11-03-2015 ( eseguo update alla fine )
									//sqlMapClientSiga.update("SigaVariazioneDatiUtenti.updateStoricoCdrUtentiPerCessazione", codiceFiscaleUtenti);
									fileLog.debug("AGGIORNAMENTO UTENTI PER CESSAZIONE CF: "+codiceFiscaleUtenti);
									dao.updateUtentiPerCessazione(codiceFiscaleUtenti);
									input.put("userId", codiceFiscaleUtenti);
									input.put("tipoEvento", "C");
									//input.put("oldCdR", null);
									input.put("oldCdR", oldCdR);
									input.put("orgName", null);
									motivazione = "CDTE";
									fileLog.debug("INSERIMENTO IN TABELLA UTENTI AGGIORANTI HR PER CESSAZIONE CF: "+codiceFiscaleUtenti);
									dao.insertUtentiAggiornatiHR_Var(input);
	
									flagRichiedente = utentiSiga.getRichiedente();
									fileLog.debug("flagRichiedente: "+flagRichiedente);
									flagAutorizzatore2 = utentiSiga.getAutorizzatoreIIliv();
									fileLog.debug("flagAutorizzatore2: "+flagAutorizzatore2);
									if (flagRichiedente.equalsIgnoreCase("SI")){
	
										verificaRichiedenteCdR(input,motivazione);		
	
									}
									flagAutorizzatore1 = utentiSiga.getAutorizzatoreIliv();
									fileLog.debug("flagAutorizzatore1: "+flagAutorizzatore1);
									if (flagAutorizzatore1.equalsIgnoreCase("SI")){
	
										verificaAutorizzatoreCdR(input,motivazione);
	
									}
									verificaRichiedenteGruppo(input,motivazione);
									verificaAutorizzatoreGruppo(input,motivazione);
	
									input.put("autorizzatoreIliv", flagAutorizzatore1);
									input.put("autorizzatoreIIliv", flagAutorizzatore2);
									input.put("richiedente", flagRichiedente);
	
	
									AutorizzatoreIORIILivello(input,utentiSiga,service,motivazione);
	
									// MODICA DEL 11-03-2015 ( eseguo update alla fine )
									fileLog.debug("AGGIORNAMENTO STORICO CDR UTENTI PER CESSAZIONE CF: "+codiceFiscaleUtenti);
									dao.updateStoricoCdrUtentiPerCessazione(utentiSiga);
									dao.aggiornaFlagUtente(utentiSiga);
	
									motivazionePerDelegato = "CDTO";
									verificaDelegato(input, motivazionePerDelegato);
									verificaOperatoreAssociatoARichiedenteEAutorizzatore(input);
									//rimozione del trattamneto richieste a seguito dell'introduziojne del bath delle mobilite'
									trattamentoRichieste(input);
	
									if (!getRuoliGestoriOperatori(codiceFiscaleUtenti).isEmpty()){
										testoAudit = "Revoca per cessazione del ruolo di gestore operatore";
										fileLog.debug("testoAudit: "+testoAudit);
										input.put("testoAudit", testoAudit);
										String tabellaAggiornata = "gestori_operatori";
										input.put("tabellaAggiornata", tabellaAggiornata);
										dao.insertAuditOperazioni(input);
										int idAuditFine = (Integer)selectMaxIDAudit();
										Integer objectIdAuditFine = idAuditFine;
										input.put("idAuditFine", String.valueOf(objectIdAuditFine));
										fileLog.debug("AGGIORNAMENTO GESTORE OPERATORE PER CESSAZIONE CF: "+codiceFiscaleUtenti);
										dao.updateGestoreOperatore(input);
									}
									//andare a visibilite' e profili posseduti
									//template(input, sqlMapClientSiga);
	
									numeroUtentiPresentiSoloSuSiga ++;
								}
	
								numeroCFSigaElaborati ++;
							}
							
							
							
	
						}catch (Exception e) {
							dctx.close();
							fileLog.debug("Exception_Exception di utenti non pie' presenti su cau: " +e);
							fileLog.debug("  Message Exception_Exception di utenti non pie' presenti su cau: " +e.getMessage());
							fileLog.debug("    StackTrace Exception_Exception di utenti non pie' presenti su cau: " +e.getStackTrace().toString());
							numeroCFSigaScartatiPerException_Exception ++; 
							utentiSigaScartatiPerException_Exception = utentiSigaScartatiPerException_Exception + " CF: "+userId +", CdR: "+orgName +" -- ";
							fileLog.info( e.getMessage(),e);
							throw new CheckException(e);
						}
	
						numeroCFSigaTotali ++;
					} // inner while
					pageOffsetSiga += PAGE_SIZE_SIGA;
					} // pages while


					fileLog.debug("FINE ELABORAZIONE: numero di CFSiga totali ="+numeroCFSigaTotali);
					fileLog.debug(" FINE ELABORAZIONE: numero di CFSiga lavorati ="+numeroCFSigaElaborati);
					fileLog.debug("   FINE ELABORAZIONE: numero di CFSiga non pie' presenti su cau: "+numeroUtentiPresentiSoloSuSiga);
					fileLog.debug(" FINE ELABORAZIONE: numero di CFSiga scartati per SQLException di utenti non pie' presenti su cau: "+numeroCFSigaScartatiPerSQLException + utentiSigaScartatiPerSQLException);
					fileLog.debug(" FINE ELABORAZIONE: numero di CFSiga scartati per Exception di utenti non pie' presenti su cau: "+numeroCFSigaScartatiPerException + utentiSigaScartatiPerException);
					fileLog.debug(" FINE ELABORAZIONE: numero di CFSiga scartati per Exception_Exception di utenti non pie' presenti su cau: "+numeroCFSigaScartatiPerException_Exception + utentiSigaScartatiPerException_Exception);
					fileLog.debug(" FINE ELABORAZIONE: numero di CFSiga scartati per CheckException_Exception di utenti non pie' presenti su cau: "+numeroCFSigaScartatiPerCheckException_Exception + utentiSigaScartatiPerCheckException_Exception);
					
				}// --> 2.9 2023 <--
				
				dctx.close();
				dao.endBatchVariazioneDatiUte();

			} catch (SQLException e) {
				// TODO Blocco catch generato automaticamente
				e.printStackTrace();
				dctx.close();

				fileLog.debug("ERRORE RCUPERO DATI: " + e.getMessage());
				
				ArrayList <String> dest = new ArrayList <String>();
				String oggetto = "[SIGA3] Batch Variazione Dati Utente";
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				String testoEmail = "Errore durante l'elaborazione con exception: \n"+ errors.toString();
				dest.add(settaggiBatch.getEmailSegnalazioniSogei());
				try {
					MailUtility.sendMail(dest,oggetto,testoEmail);
					fileLog.debug("INVIO MAIL FALLITO PER ERRORE DI ESECUZIONE DEL BATCH");
				} catch (MessagingException e1) {
					// TODO Blocco catch generato automaticamente
					e1.printStackTrace();
					fileLog.debug("INVIO MAIL FALLITO PER ERRORE DI ESECUZIONE DEL BATCH");
				}

				fileLog.info( e.getMessage());
				throw new CheckException(e);
			}
		}

		else {
			ArrayList <String> dest = new ArrayList <String>();
			dest.add(settaggiBatch.getEmailSegnalazioniSogei());
			String oggetto = "[SIGA3] Batch Variazione Dati Utente" + (verificaAll?"":" - verifica solo CDR");
			
			String testoEmail = "Mancato start del Batch Variazione Dati Utente per via dell'elaborazione din corso del Bacth Responsabili CDR  ";
			dctx.close();
			
			try {
				MailUtility.sendMail(dest,oggetto,testoEmail);
				fileLog.debug("INVIO MAIL PER MANCATO START DEL BATCH");
			} catch (MessagingException e1) {
				// TODO Blocco catch generato automaticamente
				e1.printStackTrace();
				dctx.close();
				fileLog.debug("INVIO MAIL PER MANCATO START DEL BATCH");
			}

		}
		fileLog.debug("FINE ELABORAZIONE BATCH_HR_VARIAZIONE_DATI_UTENTE ");
	}
		
	private List<String> getListaCfFiltri(String cfListFiltro){
		
		String[] cfArray = cfListFiltro.split(";");
		List<String> filtriCf = Arrays.asList(cfArray);
		
		return filtriCf;
	}

}
