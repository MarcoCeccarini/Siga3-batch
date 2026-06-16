package it.finanze.siga.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import it.finanze.siga.bean.CDRBean;
import it.finanze.siga.bean.DelegaBean;
import it.finanze.siga.bean.GetAutorizzatore_I_LivBean;
import it.finanze.siga.bean.OperatoreBean;
import it.finanze.siga.bean.RichiedenteCDRBean;
import it.finanze.siga.dao.impl.SigaDaoImpl;
import it.finanze.siga.finder.AssociazOperRichiAutorFinder;
import it.finanze.siga.finder.DelegaFinder;
import it.finanze.siga.finder.GetAutorizzatore_I_LivFinder;
import it.finanze.siga.finder.OperatoreFinder;
import it.finanze.siga.finder.RegistroRichiesteFinder;
import it.finanze.siga.finder.RichiedenteCDRFinder;
import it.finanze.siga.finder.StrutturaPerIterFinder;
import it.finanze.siga.util.Logg;
import it.finanze.siga.util.Ruolo;
import it.finanze.siga.util.SigaCache;
import it.finanze.siga.util.bean.UtenteBean;
import it.finanze.siga.utility.properties.PropertiesReader;
import it.sogei.cau.ws.ServiceCau;
import it.sogei.cau.ws.ServiceCauAmmLocale;
import it.sogei.cau.ws.ServiceCauUtente;
import it.sogei.cau.ws.bean.AutenticazioneCauBean;
import it.sogei.cau.ws.input.utente.Nominativo_InsertService;
import it.sogei.cau.ws.input.utente.Utente_InsertService;
import it.sogei.cau.ws.input.utente.Utente_UpdateService;
import it.sogei.cau.ws.util.CauParam;
import it.sogei.eaf.util.CheckException;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * 
 * @author f.rachiele
 * 
 */

@Stateless
@Named("MetodiComuni")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MetodiComuni {
	final  String SI = "SI";
	final  String NO = "NO";
	public  String DIR_PROPERTIES = "/prod/installedApps/SIGA";
	public  String FILE_PROPERTIES = "ini.properties";
	 
	@EJB
	SigaDaoImpl service;

	@Inject
	DelegheBusiness delegheBusiness;

	/**
	 * 
	 * @param cfOggettoRevoca
	 *            Codice fiscale oggetto della revoca
	 * @param CDR
	 *            Codice CdR in cui e' richiedente. Questo parametro e'
	 *            valorizzato nel caso di invocazione del metodo da parte della
	 *            funzionalita'e' "Modifica ruoli di un CdR");
	 * @param cfAutorizzatore
	 *            CF dell'autorizzatore a cui e' associato (nel caso di revoca
	 *            del ruolo di richiedente per l'associazione puntuale
	 *            e'operatori-richiedenti-autorizzatori"). Questo parametro e'
	 *            valorizzato nel caso di invocazione del metodo da parte della
	 *            funzionalita'e' "Richiedente di un gruppo di operatori")
	 * @return L'output e' costituito da: SI se conserva il ruolo, No se perde il
	 *         ruolo (perch'e' stato revocato l'ultimo in suo possesso di quella
	 *         tipologia).
	 * @throws Exception
	 */
	public  String verificaMantenimentoRuoloRichiedente(String cfOggettoRevoca,String cdr, String cfAutorizzatore) throws Exception {
		Logg.loggSyso("IN: verificaMantenimentoRuoloRichiedente()");		

		if (cfOggettoRevoca == null || cfOggettoRevoca.isEmpty()) {
			throw new Exception("Stringa vuota...");
		}

		// Se tutti e tre i parametri sono nulli errore!!!
		if (!cfOggettoRevoca.isEmpty() && !cdr.isEmpty() && !cfAutorizzatore.isEmpty()) {
			throw new Exception("Tutte e tre piene...");
		}

		String result = null;
		
		OperatoreBean responsabile = null;

		OperatoreFinder finderResponsabile = new OperatoreFinder(cfOggettoRevoca);
		RichiedenteCDRFinder finderRichiedente = new RichiedenteCDRFinder();

		try {
			
			responsabile = service.getDatiUtente2(finderResponsabile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		int numeroRespCdrFigli = service.verificaResponsabileCdrFigli(cfOggettoRevoca);

		if (numeroRespCdrFigli > 0)
			return SI; 
		// CASO 1
		// Se l'utente non e' richiedente restituiamo NO!
		  if (responsabile.getRichiedente().equals(NO))
			return NO;
		
	
		if (cdr != null && !cdr.isEmpty()) {
			Logg.loggSyso("IN: CASO 1 - Funzione verifica mantenimento dipendente.");
			finderRichiedente.setCfRichiedente(cfOggettoRevoca);
			finderRichiedente.setCodiceCDR(cdr);
			int i = service.verificaIsRichiedente(finderRichiedente);
			if (i > 0)
				return SI;
			AssociazOperRichiAutorFinder finderAssocia = new AssociazOperRichiAutorFinder();
			finderAssocia.setCfRichiedente(cfOggettoRevoca);
			int numeroAssociazioni = service.getNumeroAssociazioni(finderAssocia);
			if (numeroAssociazioni > 0)
				return SI;
			else
				return NO;
		}
		// CASO 2
		if (cfAutorizzatore != null && !cfAutorizzatore.isEmpty()) {
			Logg.loggSyso("IN: Caso 2 Funzione verifica mantenimento dipendente.");
			finderRichiedente.setCfRichiedente(cfOggettoRevoca);
			finderRichiedente.setCodiceCDR(cdr);
			int i = service.verificaIsRichiedente(finderRichiedente);
			if (i > 0)
				return SI;
			AssociazOperRichiAutorFinder finderAssocia = new AssociazOperRichiAutorFinder();
			finderAssocia.setCfRichiedente(cfOggettoRevoca);
			finderAssocia.setCfAutorizzatore(cfAutorizzatore);
			int numeroAssociazioni = service.getNumeroRichieste(finderAssocia);
			if (numeroAssociazioni > 0)
				return SI;
			else
				return NO;
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * @param cfOggettoRevoca
	 *            Codice fiscale oggetto della revoca
	 * @param CDR
	 *            Codice CdR in cui e' richiedente. Questo parametro e'
	 *            valorizzato nel caso di invocazione del metodo da parte della
	 *            funzionalita'e' "Modifica ruoli di un CdR");
	 * @param cfAutorizzatore
	 *            CF dell'autorizzatore a cui e' associato (nel caso di revoca
	 *            del ruolo di richiedente per l'associazione puntuale
	 *            e'operatori-richiedenti-autorizzatori"). Questo parametro e'
	 *            valorizzato nel caso di invocazione del metodo da parte della
	 *            funzionalita'e' "Richiedente di un gruppo di operatori")
	 * @return L'output e' costituito da: SI se conserva il ruolo, No se perde il
	 *         ruolo (perch'e' stato revocato l'ultimo in suo possesso di quella
	 *         tipologia).
	 * @throws Exception
	 * 
	 * MEV 03/2019 - Metodo adeguato per il caso di associazione Richiedente Autorizzatore I Livello di un operatore
	 * (Utilizzato solo per questa funzionalite')
	 */
	public  String verificaMantenimentoRuoloRichiedenteAORA(String cfOggettoRevoca,String cdr, String cfAutorizzatore) throws Exception {
		Logg.loggSyso("IN: verificaMantenimentoRuoloRichiedente()");		

		if (cfOggettoRevoca == null || cfOggettoRevoca.isEmpty()) {
			throw new Exception("Stringa vuota...");
		}

		// Se tutti e tre i parametri sono nulli errore!!!
		if (!cfOggettoRevoca.isEmpty() && !cdr.isEmpty() && !cfAutorizzatore.isEmpty()) {
			throw new Exception("Tutte e tre piene...");
		}

		String result = null;
		
		OperatoreBean responsabile = null;

		OperatoreFinder finderResponsabile = new OperatoreFinder(cfOggettoRevoca);
		RichiedenteCDRFinder finderRichiedente = new RichiedenteCDRFinder();

		try {
			
			responsabile = service.getDatiUtente2(finderResponsabile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		int numeroRespCdrFigli = service.verificaResponsabileCdrFigli(cfOggettoRevoca);

		if (numeroRespCdrFigli > 0)
			return SI; 
		// CASO 1
		// Se l'utente non e' richiedente restituiamo NO!
		  if (responsabile.getRichiedente().equals(NO))
			return NO;
		
	
		if (cdr != null && !cdr.isEmpty()) {
			Logg.loggSyso("IN: CASO 1 - Funzione verifica mantenimento dipendente.");
			finderRichiedente.setCfRichiedente(cfOggettoRevoca);
//			finderRichiedente.setCodiceCDR(cdr);
			int i = service.verificaIsRichiedente(finderRichiedente);
			if (i > 0)
				return SI;
			AssociazOperRichiAutorFinder finderAssocia = new AssociazOperRichiAutorFinder();
			finderAssocia.setCfRichiedente(cfOggettoRevoca);
			int numeroAssociazioni = service.getNumeroAssociazioni(finderAssocia);
			if (numeroAssociazioni > 0)
				return SI;
			else
				return NO;
		}
		// CASO 2
		if (cfAutorizzatore != null && !cfAutorizzatore.isEmpty()) {
			Logg.loggSyso("IN: Caso 2 Funzione verifica mantenimento dipendente.");
			finderRichiedente.setCfRichiedente(cfOggettoRevoca);
			finderRichiedente.setCodiceCDR(cdr);
			int i = service.verificaIsRichiedente(finderRichiedente);
			if (i > 0)
				return SI;
			AssociazOperRichiAutorFinder finderAssocia = new AssociazOperRichiAutorFinder();
			finderAssocia.setCfRichiedente(cfOggettoRevoca);
			finderAssocia.setCfAutorizzatore(cfAutorizzatore);
			int numeroAssociazioni = service.getNumeroRichieste(finderAssocia);
			if (numeroAssociazioni > 0)
				return SI;
			else
				return NO;
		}
		
		return result;
	}
	/**
	 * 
	 * @param cfOggettoRevoca Corrisponde al cfPrecedente al quale stiamo rimuovendo 
	 * @param tipoAutorizzatore Indica se operiamo su Autorizzatore oppure Richiedente 
	 * @param cdrResponsabile 
	 * @param cdrAutorizzatore
	 * @param cfRichiedente
	 * @param identificativoIter
	 * @param tipoUfficioRichiednte
	 * @return
	 * @throws Exception
	 */
	public  String verificaMantenimentoRuoloAutorizzatore(String cfOggettoRevoca, String tipoAutorizzatore,
			String cdrResponsabile, String cdrAutorizzatore, String cfRichiedente, String identificativoIter, String tipoUfficioRichiednte) throws Exception {
		if (tipoAutorizzatore.equals("A1")){
			return verificaMantenimentoRuoloAutorizzatorePrimoLivello(cfOggettoRevoca,cdrResponsabile, cdrAutorizzatore, cfRichiedente);
		}else
			if(tipoAutorizzatore.equals("A2")){
				return verificaMantenimentoRuoloAutorizzatoreSecondoLivello(cfOggettoRevoca,cdrResponsabile,identificativoIter,tipoUfficioRichiednte);
			}
		throw new Exception("Parametro tipo autorizzazione non corretto");		
	}
	

	private  String verificaMantenimentoRuoloAutorizzatoreSecondoLivello(String cfOggettoRevoca, String cdrResponsabile, String identificativoIter, String tipoUfficioRichiednte) throws Exception {
		
		OperatoreBean operatoreOggettoRevoca = null;
		
		if (cfOggettoRevoca == null || cfOggettoRevoca.isEmpty()) {
			throw new Exception("Stringa vuota...");
		}
			
		OperatoreFinder finderResponsabile = new OperatoreFinder(cfOggettoRevoca);
			
		try {
			
			operatoreOggettoRevoca = service.getDatiUtente2(finderResponsabile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (operatoreOggettoRevoca.getAutorizzazioneLivelloII().equals(NO))
			return NO;
		
		//Revoca del ruolo A2 come responsabile_HR per il CDR 
		if (cdrResponsabile != null && !cdrResponsabile.isEmpty()){
			//Step 4.1
			RichiedenteCDRFinder richiedenteCDRAutFinder = new RichiedenteCDRFinder();
			richiedenteCDRAutFinder.setCfResponsabileHR(cfOggettoRevoca);
			List<RichiedenteCDRBean> listCdrAutorizzI = service.getCdrAutorizzatoreIliv(richiedenteCDRAutFinder);
			for (RichiedenteCDRBean richiedenteCDRBean : listCdrAutorizzI) {
				String cdr = richiedenteCDRBean.getCodiceCDR();
				if(	!cdr.equalsIgnoreCase(cdrResponsabile)){
					StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
					strutturaFinder.setCdrAutorizIILiv(cdr);
					List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
					if (! struttura.isEmpty())
						return SI;	
				}
			}
			
			//Step 4.2
			StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
			strutturaFinder.setCfII(cfOggettoRevoca);
			List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
			if (! struttura.isEmpty())
				return SI;	
			else{
				for (RichiedenteCDRBean richiedenteCDRBean : listCdrAutorizzI) {
					String cdr = richiedenteCDRBean.getCodiceCDR();
					if(	!cdr.equalsIgnoreCase(cdrResponsabile)){
						StrutturaPerIterFinder strutturaFinder2 = new StrutturaPerIterFinder();
						strutturaFinder2.setCdrAutorizIILiv(cdr);
						List<StrutturaPerIterFinder> struttura2 = service.getStrutturaIter2(strutturaFinder2);
						if (! struttura2.isEmpty())
							return SI;	
					}
				}
			}
			
			
			return NO;
			
		}//Revoca del ruolo A2 senza CDR
			else {
				//Step 5.1
				RichiedenteCDRFinder richiedenteCDRAutFinder = new RichiedenteCDRFinder();
				richiedenteCDRAutFinder.setCfResponsabileHR(cfOggettoRevoca);
				List<RichiedenteCDRBean> listCdrAutorizzI = service.getCdrAutorizzatoreIliv(richiedenteCDRAutFinder);
				for (RichiedenteCDRBean richiedenteCDRBean : listCdrAutorizzI) {
					String cdr = richiedenteCDRBean.getCodiceCDR();
					StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
					strutturaFinder.setCdrAutorizIILiv(cdr);
					List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
					if (! struttura.isEmpty())
						return SI;	
				}
				
				//Step 4.2
				StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
				strutturaFinder.setCfII(cfOggettoRevoca);
				strutturaFinder.setIdentificativoIter(identificativoIter);
				strutturaFinder.setTipoUfficioRichiedente(tipoUfficioRichiednte);
				List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
				if (! struttura.isEmpty())
					return SI;				
				return NO;
		}
	}

	private  String verificaMantenimentoRuoloAutorizzatorePrimoLivello(
			String cfOggettoRevoca, String cdrResponsabile,
			String cdrAutorizzatore, String cfRichiedente) throws Exception {
		
		OperatoreBean operatoreOggettoRevoca = null;
		OperatoreFinder finderResponsabile = new OperatoreFinder(cfOggettoRevoca);
		try {
			
			operatoreOggettoRevoca = service.getDatiUtente2(finderResponsabile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (operatoreOggettoRevoca.getAutorizzazioneLivelloI().equals("NO"))
			return NO;
//		Revoca del ruolo A1 come responsabile_HR per il CDR
		if(cdrResponsabile != null && !cdrResponsabile.isEmpty() ){
			//Step 1.1
			int risposta = service.step1(cdrResponsabile);
			if (risposta == 0 )
				return SI;
			
			//Step 1.2
			AssociazOperRichiAutorFinder finderAssocia = new AssociazOperRichiAutorFinder();
			finderAssocia.setCfAutorizzatore(cfOggettoRevoca);
			int numeroAssociazioni = service.getNumeroRichieste(finderAssocia);
			if (numeroAssociazioni > 0)
				return SI;
			
			//Step 1.3
			RichiedenteCDRFinder finderRichiedente = new RichiedenteCDRFinder();
			finderRichiedente.setCfAutorizzatoreILiv(cfOggettoRevoca);
			int i = service.verificaIsRichiedente(finderRichiedente);
			if (i > 0)
				return SI;
			
			//Step 1.4
			RichiedenteCDRFinder richiedenteCDRAutFinder = new RichiedenteCDRFinder();
			richiedenteCDRAutFinder.setCfResponsabileHR(cfOggettoRevoca);
			List<RichiedenteCDRBean> listCdrAutorizzI = service.getCdrAutorizzatoreIliv(richiedenteCDRAutFinder);
			for (RichiedenteCDRBean richiedenteCDRBean : listCdrAutorizzI) {
				String cdr = richiedenteCDRBean.getCodiceCDR(); 
				if(	!cdr.equalsIgnoreCase(cdrResponsabile)){
					risposta = service.step1(cdr);
					if (risposta > 0 )
						return SI;
				}
			}
			
			//Step 1.5
			for (RichiedenteCDRBean richiedenteCDRBean : listCdrAutorizzI) {
				String cdr = richiedenteCDRBean.getCodiceCDR(); 
				if(	!cdr.equalsIgnoreCase(cdrResponsabile)){
					StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
					strutturaFinder.setCdrAutorizILiv(cdr);
					List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
					if (! struttura.isEmpty())
						return SI;
				}
			}
			
			//Step 1.6
			StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
			strutturaFinder.setCfI(cfOggettoRevoca);
			List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
			if (! struttura.isEmpty())
				return SI;	
			
			return NO;	
		}else if(cdrAutorizzatore != null && !cdrAutorizzatore.isEmpty()){
				//Revoca del ruolo A1 come autorizzatore di un CDR
				//Step 2.1
				AssociazOperRichiAutorFinder finderAssocia = new AssociazOperRichiAutorFinder();
				finderAssocia.setCfAutorizzatore(cfOggettoRevoca);
				int numeroAssociazioni = service.getNumeroRichieste(finderAssocia);
				if (numeroAssociazioni > 0)
					return SI;
				
				//Step 2.2
				RichiedenteCDRFinder finderRichiedente = new RichiedenteCDRFinder();
				finderRichiedente.setCfAutorizzatoreILiv(cfOggettoRevoca);
				finderRichiedente.setCodiceCDR(cdrAutorizzatore);
				int i = service.verificaIsRichiedente(finderRichiedente);
				if (i > 0)
					return SI;
				
				//Step 2.3
				RichiedenteCDRFinder richiedenteCDRAutFinder = new RichiedenteCDRFinder();
				richiedenteCDRAutFinder.setCfResponsabileHR(cfOggettoRevoca);
				List<RichiedenteCDRBean> listCdrAutorizzI = service.getCdrAutorizzatoreIliv(richiedenteCDRAutFinder);
				for (RichiedenteCDRBean richiedenteCDRBean : listCdrAutorizzI) {
					String cdr = richiedenteCDRBean.getCodiceCDR(); 
					int risposta = service.step1(cdr);
					if (risposta > 0 )
						return SI;
				}
				
				//Step 2.4
				for (RichiedenteCDRBean richiedenteCDRBean : listCdrAutorizzI) {
					String cdr = richiedenteCDRBean.getCodiceCDR(); 
					StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
					strutturaFinder.setCdrAutorizILiv(cdr);
					List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
					if (! struttura.isEmpty())
						return SI;
				}
				
				//Step 2.5
				StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
				strutturaFinder.setCfI(cfOggettoRevoca);
				List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
				if (! struttura.isEmpty())
					return SI;				
				
				return NO;				
			}else if(cfRichiedente != null && !cfRichiedente.isEmpty()){
					//Revoca del ruolo A1 come autorizzatore di gruppo_operatori_richiedenti_autorizzatori
					
					//Step 3.1
					AssociazOperRichiAutorFinder finderAssocia = new AssociazOperRichiAutorFinder();
					finderAssocia.setCfAutorizzatore(cfOggettoRevoca);
					finderAssocia.setCfNONRichiedente(cfRichiedente);
					int numeroAssociazioni = service.getNumeroRichieste(finderAssocia);
					if (numeroAssociazioni > 0)
						return SI;
					
					//Step 3.2
					RichiedenteCDRFinder finderRichiedente = new RichiedenteCDRFinder();
					finderRichiedente.setCfAutorizzatoreILiv(cfOggettoRevoca);
					int i = service.verificaIsRichiedente(finderRichiedente);
					if (i > 0)
						return SI;
					
					//Step 3.3
					RichiedenteCDRFinder richiedenteCDRAutFinder = new RichiedenteCDRFinder();
					richiedenteCDRAutFinder.setCfResponsabileHR(cfOggettoRevoca);
					List<RichiedenteCDRBean> listCdrAutorizzI = service.getCdrAutorizzatoreIliv(richiedenteCDRAutFinder);
					for (RichiedenteCDRBean richiedenteCDRBean : listCdrAutorizzI) {
						String cdr = richiedenteCDRBean.getCodiceCDR(); 
						int risposta = service.step1(cdr);
						if (risposta > 0 )
							return SI;
					}
					
					//Step 3.4
					for (RichiedenteCDRBean richiedenteCDRBean : listCdrAutorizzI) {
						String cdr = richiedenteCDRBean.getCodiceCDR(); 
						StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
						strutturaFinder.setCdrAutorizILiv(cdr);
						List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
						if (! struttura.isEmpty())
							return SI;
					}
					
					//Step 3.5
					StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
					strutturaFinder.setCfI(cfOggettoRevoca);
					List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
					if (! struttura.isEmpty())
						return SI;	
					
					return NO;	
				}
		throw new Exception("Errore inserimento parametri");
	}

	/**
	 * 
	 * @param cfPrecedente
	 * @param cfNuovo
	 * @param cfAltro per gruppo di operatori-richiedente-autorizzatore
	 * @param cdr
	 * @param ruoloRevoca R=richiedente, A= autorizzatore I livello
	 * @param motivoRevoca null, C=cessazione, M= mobilita'e', R= revoca (E' il valore che impostate voi)
	 * @param cfUtenteInSessione cf di chi opera la revoca 
	 * @throws Exception
	 */
	public  void ruoliRichiedenteEAutorizzatore(String cfPrecedente,String cfNuovo, String cfAltro, String cdr, String ruoloRevoca,
			String motivoRevoca, UtenteBean utente) throws Exception {
		if(ruoloRevoca.equals("R")){
			ruoliRichiedente(cfPrecedente, cfNuovo, cfAltro, cdr, motivoRevoca, utente);
			return;
		}else
		if(ruoloRevoca.equals("A")){
			ruoliAutorizzatore(cfPrecedente, cfNuovo, cfAltro, cdr, motivoRevoca, utente);
			return;
		}
		throw new Exception("Parametri non passati correttamente: ruoloRevoca (R/A)" + ruoloRevoca);		
	}
	
	private  void ruoliAutorizzatore(String cfPrecedente, String cfNuovo,
			String cfAltro, String cdr, String motivoRevoca,
			UtenteBean utente) throws Exception {
		
		
		String cfUtenteInSessione = utente.getCodFiscaleUtente();
		if (cdr == null || cdr.isEmpty()) {
			if (cfNuovo == null || cfNuovo.isEmpty()) {
				//CASO 4: Revoca Autorizzatori ddi un grupppo di operatori
				service.revocaAutorizzatoreDiUnGruppoDiOperatori(cfPrecedente, cfAltro, motivoRevoca, cfUtenteInSessione);				
				} else {
					//CASO 3: Sostituzione autorizzatore di un gruppo di operatori
					if(verificaCreazioneAmministratoreCAU(cfNuovo)){
						service.sostituzioneAutorizzatoreDiUnGruppoDiOperatori(cfPrecedente,cfNuovo,cfAltro,cfUtenteInSessione);
					}				
				}
			verificaPerCfAltro(cfAltro, delegheBusiness.RICHIEDENTE,motivoRevoca);

		} else {
			if (cfNuovo == null || cfNuovo.isEmpty()) {
				//CASO 2: Ripristino Autorizzatore
				//if(verificaCreazioneAmministratoreCAU(cfPrecedente,service)){
					service.ripristinoAutorizzatoreDaHR(cfPrecedente, cdr, motivoRevoca, utente);
				//}
			} else {
				//CASO 1: Sostituzione Autorizzatore
				if(verificaCreazioneAmministratoreCAU(cfNuovo)){
					service.sostituzioneDellAutorizzatoreDiUnCDR(cfPrecedente, cfNuovo, cdr, motivoRevoca, utente);
				}				
			}
		}
		if (!(cfUtenteInSessione == null || "".equals(cfUtenteInSessione)) && (cfPrecedente != null && !cfPrecedente.equals("")))
			verificaCfAutorizzatoreRimana(cfPrecedente, cdr, cfUtenteInSessione, motivoRevoca);
	}

	private  void ruoliRichiedente(String cfPrecedente, String cfNuovo,
			String cfAltro, String cdr, String motivoRevoca, UtenteBean utente) throws CheckException, Exception {
		RegistroRichiesteFinder registroFinder = new RegistroRichiesteFinder();
		registroFinder.setCdrUtente(cdr);
		registroFinder.setCfRichiedente(cfPrecedente);
		String cfUtenteInSessione = utente.getCodFiscaleUtente();
		if (cdr == null || cdr.isEmpty()) {
			if (cfNuovo == null || cfNuovo.isEmpty()) {
				//CASO 4
				service.revocaRichiedentiDiUnGruppoDiOperatori(cfPrecedente, cfAltro);
			} else {
				//CASO 3
				if(verificaCreazioneAmministratoreCAU(cfNuovo)){
					service.sostituzioneRichiedenteDiUnGruppoDiOperatori(cfPrecedente, cfNuovo, cfAltro);
					
				}
			}
			verificaPerCfAltro(cfAltro, delegheBusiness.AUTORIZZATORE_I_LIV,motivoRevoca);
			
		} else {
			// Annullamento delle richieste non ancora autorizzate con
			// cdr_utente = CDR e CF_richiedente = CF_precedente (nella versione del 25 novebre non va fatta)
//				service.annullamentoRichiesteNonAutorizzate(registroFinder);
			if (cfNuovo == null || cfNuovo.isEmpty()) {
				// CASO RIPRISTINO CASO 2
				//commento la funzione crea amministratore del 09/01/2018 visto che il cfPrecedente oggtto di Mobilita/Cessazione non va creato 
				//if(verificaCreazioneAmministratoreCAU(cfPrecedente, service)){ 
//					verificaCreazioneAmministratoreCAU(cfPrecedente, service);
				//Prendo il vecchio richiedente
				RichiedenteCDRFinder richiedenteCDRFinder = new RichiedenteCDRFinder();
				richiedenteCDRFinder.setCodiceCDR(cdr);		
				List<RichiedenteCDRBean> listRich = service.getRichiedenteCDR(richiedenteCDRFinder);
				RichiedenteCDRBean risultato;
				if(listRich.size()>0){
					risultato = listRich.get(0);
					if(risultato.getCfResponsabileHR()!=null)
						verificaCreazioneAmministratoreCAU(risultato.getCfResponsabileHR());
				}
				
					service.ripristinoResponsabileHR(cfPrecedente,cdr,motivoRevoca,utente);
				//}
			} else {
				// Caso SOSTITUZIONE CASO 1
				// Aggiornamento del richiedente del cdr con il cfNuovo
				// update della vecchia riga e inserimento della nuova
				if(verificaCreazioneAmministratoreCAU(cfNuovo)){
					service.sostituzioneDelRichiedenteDiUnCDR(cfPrecedente,cfNuovo,cdr,motivoRevoca,utente);
					
				}					
			}
			//Verifica se cf_precedente rimane richiedente con la funzione che vi ho dato, solo con web app e non da batch
			if (!(cfPrecedente == null || "".equals(cfPrecedente)) )
				if (!(cfUtenteInSessione == null || "".equals(cfUtenteInSessione)) )
					verificaCfPrecedenteRimane(cfPrecedente, cdr, cfUtenteInSessione);	
		}
	}

	/**
	 * Verifica se cf_altro rimane come richiedente con il solito metodo
	 * verifica richiedente. 
	 * Se perde il ruolo impostazione a NO del flag
	 * richiedente su tabella utenti e se ora sono = NO i tre flag di utenti
	 * chiamata del servizio di revoca del ruolo di amministratore CAU.
	 * Rimozione delle eventuali deleghe di richidente in cui figura come
	 * delegante. (MODIFICA DEL 16/03/2015)
	 * 
	 * @param cfAltro
	 * @param ruolo
	 * @throws SQLException 
	 */
	private  void verificaPerCfAltro(String cfAltro, String ruolo,String motivoRevoca) throws SQLException {
		

		try {
			

			boolean richiedete = isRichiedente(cfAltro);
			if (!richiedete){
				
				boolean gestioneAmmOK = verificaCancellazioneAmministratoreCAU(cfAltro,"RI");
				if (gestioneAmmOK){
					//impostazione a NO del flag_richiedente sulla tabella utenti 
					OperatoreFinder operatoreFinder = new OperatoreFinder(cfAltro);
					operatoreFinder.setRichiedente("NO");
					service.aggiornaFlagRuolo(operatoreFinder);					
					//Se non e' piu' richiedente, rimozione delle deleghe mediante la funzione che vi ho dato.
					delegheBusiness.delega("R", cfAltro, "", ruolo, null, motivoRevoca, "", null, "", null);				
				}
			}
			
				
		} catch (Exception e) {
			// TODO Blocco catch generato automaticamente
			e.printStackTrace();
		}
		
	}
	/**
	 * Verifica se il Cf passato come parametro e' un Richiedente 
	 * @param cf Codice Fiscale della persona da verificare 
	 * @return restituisce treu se il cf e' Richiedente 
	 * @throws CheckException
	 */
	public  boolean isRichiedente (String cf) throws CheckException  {
		
		RichiedenteCDRFinder finderRichiedente = new RichiedenteCDRFinder();

		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finderRichiedente.setCfRichiedente(cf);
		
		
		int i = service.verificaIsRichiedente(finderRichiedente);
		if (i > 0)
			return true;
		
		return false;
	}

	/**
	 * Verifica se il Cf passato come parametro e' un Autorizzatore di I Livello 
	 * @param cf Codice Fiscale della persona da verificare 
	 * @return restituisce treu se il cf e' Autorizzatore di I Livello 
	 * @throws CheckException
	 */
	public  boolean isAutorizzatoreILiv(String cf) throws CheckException {
		RichiedenteCDRFinder finderRichiedente = new RichiedenteCDRFinder();

		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * Accediamo alla tabella richiedente_cdr per il CF in parola e
		 * verifichiamo se risulta aut I liv. Se > 0 per un cdr, vuol dire che e'
		 * un autorizzatore primo livello, impostiamo a SI l'output per aut I
		 * liv.
		 **/
		finderRichiedente.setCfAutorizzatoreILiv(cf);
		int i = service.verificaIsRichiedente(finderRichiedente);
		if (i > 0)
			return true;
		
		/**
		 * Accediamo alla tabella richiedente_cdr per il CF in parola e ricaviamo tutti i cdr di cui e'e' responsabile.
		 */
		RichiedenteCDRFinder richiedenteCDRAutFinder = new RichiedenteCDRFinder();			
		richiedenteCDRAutFinder.setCfResponsabileHR(cf);
		List<RichiedenteCDRBean> listCdrAutorizzI = service.getCdrAutorizzatoreIliv(richiedenteCDRAutFinder);
		for (RichiedenteCDRBean richiedenteCDRBean : listCdrAutorizzI) {
			String cdr = richiedenteCDRBean.getCodiceCDR();
			int risposta = service.step1(cdr);
			if (risposta > 0 )
				return true;			
		}
		
		/**
		 * Accediamo alla tabella Associazione
		 * operatori-richiedenti-autorizzatori e vediamo se il CF risulta come
		 * autorizzatore. Se risulta, vuol dire che e'e' un autorizzatore primo
		 * livello (come step 1.2)
		 */
		AssociazOperRichiAutorFinder finderAssocia = new AssociazOperRichiAutorFinder();
		finderAssocia.setCfAutorizzatore(cf);
		int numeroAssociazioni = service.getNumeroRichieste(finderAssocia);
		if (numeroAssociazioni > 0)
			return true;
		
		/**
		 * Abbiamo sempre disponibile la lista dei CdR di cui e' responsabile.
		 * Andiamo sulla tabella "struttura per iter" e verifichiamo se almeno
		 * un CdR risulta nel campo "cdr autorizzatore I Liv". Se risulta vuol dire
		 * che e' un autorizzatore primo livello, return true (come step 1.5)
		 */
		for (RichiedenteCDRBean richiedenteCDRBean : listCdrAutorizzI) {
			String cdr = richiedenteCDRBean.getCodiceCDR(); 
			StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
			strutturaFinder.setCdrAutorizILiv(cdr);
			List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
			if (! struttura.isEmpty())
				return true;
		}
		
		/**
		 * Andiamo sulla tabella "struttura per iter" e verifichiamo se almeno
		 * un tipo_CdR risulta nel campo "tipo_cdr autorizzatore I Liv". Se risulta
		 * vuol dire che e' un autorizzatore primo livello
		 */
		for (RichiedenteCDRBean richiedenteCDRBean : listCdrAutorizzI) {
			String cdr = richiedenteCDRBean.getCodiceCDR();
			int risp = service.getStrutturaIterByCdr(cdr);
			if(risp > 0 )
				return true;
		}

		/**
		 * Verifichiamo se nella tabella "struttura per iter" il suo codice
		 * fiscale risulta nel campo "cf I Liv". Se risulta usciamo con SI
		 */
		StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
		strutturaFinder.setCfI(cf);
		List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
		if (! struttura.isEmpty())
			return true;
		return false;
	}
	
	/**
	 * Verifica se il Cf passato come parametro e' un Autorizzatore di II Livello 
	 * @param cf Codice Fiscale della persona da verificare 
	 * @return restituisce treu se il cf e' Autorizzatore di II Livello 
	 * @throws CheckException
	 */
	public  boolean isAutorizzatoreIILiv(String cf) throws CheckException {
		

		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * Abbiamo sempre disponibile la lista dei CdR di cui e' responsabile.
		 */
		RichiedenteCDRFinder richiedenteCDRAutFinder = new RichiedenteCDRFinder();			
		richiedenteCDRAutFinder.setCfResponsabileHR(cf);
		List<RichiedenteCDRBean> listCdrResponsabile = service.getCdrAutorizzatoreIliv(richiedenteCDRAutFinder);
		
		/**
		 * Per ciascun CdR andiamo sulla tabella "struttura per iter" e
		 * verifichiamo se almeno un CdR risulta nel campo "cdr autorizzatore
		 * II Liv". Se risulta vuol dire che e' un autorizzatore secondo livello
		 */
		for (RichiedenteCDRBean richiedenteCDRBean : listCdrResponsabile) {
			String cdr = richiedenteCDRBean.getCodiceCDR();
			StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
			strutturaFinder.setCdrAutorizIILiv(cdr);
			List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
			if (! struttura.isEmpty())
				return true;
		}
		
		/**
		 * Andiamo sulla tabella "struttura per iter" e verifichiamo se almeno
		 * un tipo_CdR risulta nel campo "tipo_cdr autorizzatore II Liv". Se
		 * risulta vuol dire che e' un autorizzatore secondo livello, impostiamo
		 * a SI l'output per aut iI liv. e usciamo.
		 */
		for (RichiedenteCDRBean richiedenteCDRBean : listCdrResponsabile) {
			String cdr = richiedenteCDRBean.getCodiceCDR();
			int risp = service.getStrutturaIterByCdrIILivello(cdr);
			if(risp > 0 )
				return true;
		}
		
		/**
		 * Nella tabella struttura per iter verifichiamo se il suo codice
		 * fiscale risulta nel campo cf II
		 */
		StrutturaPerIterFinder strutturaFinder = new StrutturaPerIterFinder();
		strutturaFinder.setCfII(cf);
		List<StrutturaPerIterFinder> struttura = service.getStrutturaIter(strutturaFinder);
		if (! struttura.isEmpty())
			return true;
		return false;
	}

	/**
	 * Metodo che va a modificare entrambi i ruoli di un cdr (richiedente e
	 * Autorizzatore), in un primo momento era stato implementato andando a
	 * chiamare in modo sequenziale i due metodi, ma questo comportava la
	 * creazione di due idAudit. In questo modo ho solo un audit. 
	 * 
	 * @param cfPrecedenteRichiedente
	 * @param cfNuovoRichiedente
	 * @param cfPrecedenteAutorizzatore
	 * @param cfNuovoAutorizzatore
	 * @param cdr
	 * @param cfUtenteLoggato
	 * @throws Exception 
	 */
	public  void modificaEntrambi(String cfPrecedenteRichiedente,	String cfNuovoRichiedente, String cfPrecedenteAutorizzatore,
			String cfNuovoAutorizzatore, String cdr, UtenteBean utente) throws Exception {
		// rimuovo entrambi i ruoli 
		String cfUtenteLoggato = utente.getCodFiscaleUtente();
		if(verificaCreazioneAmministratoreCAU(cfNuovoRichiedente) && verificaCreazioneAmministratoreCAU(cfNuovoAutorizzatore)){
			service.modificaEntrambi(cfPrecedenteRichiedente, cfNuovoRichiedente, cfPrecedenteAutorizzatore, cfNuovoAutorizzatore, cdr, utente);
		}
		if(cfPrecedenteRichiedente != null && !cfPrecedenteRichiedente.equals("") && (!cfPrecedenteRichiedente.equals(cfNuovoRichiedente)))
			verificaCfPrecedenteRimane(cfPrecedenteRichiedente, cdr, cfUtenteLoggato);	
		
		if(cfPrecedenteAutorizzatore != null && !cfPrecedenteAutorizzatore.equals("") && (!cfPrecedenteAutorizzatore.equals(cfNuovoAutorizzatore)) )
			verificaCfAutorizzatoreRimana(cfPrecedenteAutorizzatore, cdr, cfUtenteLoggato, "R");
	}

	/**
	 * Metodo che va a Ripristinare entrambi i ruoli di un cdr (richiedente e
	 * Autorizzatore), in un primo momento era stato implementato andando a
	 * chiamare in modo sequenziale i due metodi, ma questo comportava la
	 * creazione di due idAudit. In questo modo ho solo un audit. 
	 * 
	 * @param cfPrecedenteRichiedente
	 * @param cfPrecedenteAutorizzatore
	 * @param cfNuovoRichiedente
	 * @param cdr
	 * @param cfUtenteLoggato
	 * @throws Exception
	 */
	public  void ripristinaEntrambi(String cfPrecedenteRichiedente,String cfPrecedenteAutorizzatore, String cfNuovoRichiedente,
			String cdr, UtenteBean utente) throws Exception {
		// rimuovo entrambi i ruoli 
		String cfUtenteLoggato = utente.getCodFiscaleUtente();
		if(verificaCreazioneAmministratoreCAU(cfPrecedenteRichiedente) && verificaCreazioneAmministratoreCAU(cfPrecedenteRichiedente)){
			service.ripristinaEntrambi(cfPrecedenteRichiedente,cfPrecedenteAutorizzatore, cfNuovoRichiedente, cdr, utente);	
			
		}
		verificaCfPrecedenteRimane(cfPrecedenteRichiedente, cdr, cfUtenteLoggato);	
		verificaCfAutorizzatoreRimana(cfPrecedenteAutorizzatore, cdr, cfUtenteLoggato, "R");
	}	
	
	public  GetAutorizzatore_I_LivBean getAutorizzatore_I_Liv_Per_Ripristino(GetAutorizzatore_I_LivFinder finder) throws Exception {
		
		//return service.getAutorizzatore_I_Liv_Per_Ripristino(finder);
		return service.getAutorizzatore_I_Liv_Per_Ripristino(finder);
	}	
	
	/**
	 * 	Verifica se cf_precedente rimane richiedente con la funzione che vi ho dato  
	 * 
	 * @param cfPrecedenteRichiedente
	 * @param cdr
	 * @param cfUtenteLoggato
	 * @param service
	 * @throws Exception
	 * @throws CheckException
	 */
	private  void verificaCfPrecedenteRimane(	String cfPrecedenteRichiedente, String cdr, String cfUtenteLoggato) throws Exception, CheckException {
		String risultato = verificaMantenimentoRuoloRichiedente(cfPrecedenteRichiedente, cdr, "");
		if(risultato.equals("NO")){			
			
			boolean gestioneAmmOK = verificaCancellazioneAmministratoreCAU(cfPrecedenteRichiedente,"RI");
			
			if (gestioneAmmOK){
				OperatoreFinder operatoreFinder = new OperatoreFinder(cfPrecedenteRichiedente);
				operatoreFinder.setRichiedente("NO");
				service.aggiornaFlagRuolo(operatoreFinder);				
				//Se non e' piu' richiedente, rimozione delle deleghe mediante la funzione che vi ho dato.
				delegheBusiness.delega("R", cfPrecedenteRichiedente, "", "RI", null, "", cfUtenteLoggato, null, "", null);				
			}			
		}
	}
	/**
	 * Verifica se cf_precedente rimane autorizzatore I Liv con la funzione che vi ho dato.  Se non e' piu' autorizzatore I liv:<BR>
	 *		impostazione a NO del flag autorizzatore 1 liv sulla tabella utenti<BR>  
	 *		rimozione delle deleghe mediante la funzione che vi ho dato<BR>
	 *		revoca del ruolo di amministratore CAU	<BR> 
	 * @param cfPrecedente
	 * @param cdr
	 * @param cfUtenteInSessione
	 * @param service
	 * @throws Exception
	 * @throws CheckException
	 */
	private  void verificaCfAutorizzatoreRimana(String cfPrecedente, String cdr, String cfUtenteInSessione, String motivoRevoca)throws Exception, CheckException {
		String r = verificaMantenimentoRuoloAutorizzatore(cfPrecedente, "A1", "",cdr, "","","");
		if(r.equals(NO)){
			//Se e' un batch a chiamare il metodo non deve settare a no il flag (lo fara' in un secondo momento)
//			if (!(cfUtenteInSessione == null || "".equals(cfUtenteInSessione)) ){			
			boolean gestioneAmmOK = verificaCancellazioneAmministratoreCAU(cfPrecedente,"A1");
			if (gestioneAmmOK){			
				OperatoreFinder operatoreFinder = new OperatoreFinder(cfPrecedente);
				operatoreFinder.setAutorizzatoreILivello(NO);
				service.aggiornaFlagRuolo(operatoreFinder);
//			}
				delegheBusiness.delega("R", cfPrecedente, "", "A1",null, motivoRevoca, cfUtenteInSessione, null, "", null);
			
			}
//			}
		}
	}//ruoliAutorizzatore
	
 		
	public  void CreaAmministratoreCAU(OperatoreBean utente) throws Exception{
		
		String [] adminGroup=new String[2];
		adminGroup[0]=SigaCache.getSYS_ADMIN_GROUP();
		adminGroup[1]=SigaCache.getSYS_ADMIN_GROUP_MIGRAZIONE();
		
		
		if (!SigaCache.getMOD().equals("3")){ //Solo in caso di test - inseriamento utente CAU
			ServiceCauUtente scu = new ServiceCauUtente(SigaCache.getSYS_ADMIN(), SigaCache.getSYS_AGENZIA());
			Utente_InsertService uicb = new Utente_InsertService();
			uicb.setUserId(utente.getCf());
	        Nominativo_InsertService nominativoCau = new Nominativo_InsertService();
	        nominativoCau.setCognome(utente.getCognome());
	        nominativoCau.setNome(utente.getNome());
	        uicb.setNominativo(nominativoCau);
	        uicb.setUfficio(utente.getCdr());
			/* forza abilitazione utente */
			uicb.setRevoked(false);

			scu.inserisci(uicb);
		}		
		ServiceCauAmmLocale scal = new ServiceCauAmmLocale(SigaCache.getSYS_ADMIN(), SigaCache.getSYS_AGENZIA());
		
		HashMap<String, String> input = new HashMap<String, String>();
		input.put("cdr", utente.getCdr());
//		String pilotaAvviato= service.getPilotaAvviato(input);		
//		if(pilotaAvviato==null || pilotaAvviato.equals("SI"))
		// 06/09/23 Con il NUOVO IAM non e' pie' necessaria la creazione di Amministratori CAU
		// anzi, per NON consentire l'accesso alla vecchia Consolle non devono essere pie' creati  
		//	scal.crea(utente.getCf(), SigaCache.getSYS_UFFICIO(),adminGroup);	
 
	}
	
	public  void CancellaAmministratoreCAU(OperatoreBean utente)  throws Exception{
		ServiceCauAmmLocale scal = new ServiceCauAmmLocale(SigaCache.getSYS_ADMIN(), SigaCache.getSYS_AGENZIA());
		String [] adminGroup=new String[1];
		adminGroup[0]=SigaCache.getSYS_ADMIN_GROUP();
		//adminGroup[1]=SigaCache.getSYS_ADMIN_GROUP_MIGRAZIONE();
		scal.setAdmingGroup(utente.getCf(), SigaCache.getSYS_UFFICIO(), adminGroup, ServiceCauAmmLocale.DISCONNETTI);		
	}
	
	public  AutenticazioneCauBean inserisciUtenteCAU(OperatoreBean utente, String cfUtente) throws Exception{
		AutenticazioneCauBean acb = null;
		ServiceCauUtente scu = new ServiceCauUtente(cfUtente, SigaCache.getSYS_AGENZIA());
		ServiceCauUtente scuSYS_ADMIN = new ServiceCauUtente(SigaCache.getSYS_ADMIN(), SigaCache.getSYS_AGENZIA());
		setTrustedUtente(scuSYS_ADMIN, cfUtente, true);					
 
		Utente_InsertService uicb = new Utente_InsertService();
		uicb.setUserId(utente.getCf());
		
		if (utente.getEmail()!=null)
			uicb.setEMail(utente.getEmail());
        
		Nominativo_InsertService nominativoCau = new Nominativo_InsertService();
        nominativoCau.setCognome(utente.getCognome());
        nominativoCau.setNome(utente.getNome());
        uicb.setNominativo(nominativoCau);
        uicb.setUfficio(utente.getCdr());
		/* forza abilitazione utente */
		uicb.setRevoked(false);
		HashSet<String> profili = new HashSet<String>();
		profili.add(SigaCache.getSYS_PROFILE());
		
		acb = scu.inserisci(uicb, profili);
		
		setTrustedUtente(scuSYS_ADMIN, cfUtente, false);
		return acb;
	}
	
	
	public  void deleteUtenteCAU(OperatoreBean utente) throws Exception{
		HashMap<String,String> movimentazioni = new HashMap<String,String>();
		ServiceCauUtente scu = new ServiceCauUtente(SigaCache.getSYS_ADMIN(), SigaCache.getSYS_AGENZIA());
		movimentazioni.put(SigaCache.getSYS_PROFILE(), ServiceCau.DISCONNETTI);		
		scu.profila(utente.getCf(), utente.getCdr(), movimentazioni);
		scu.delete(utente.getCf(), utente.getCdr());
	}
	
	
	/**
	 * Se per utente,  i tre flag del DB e' prima dell'operazione che si sta effettuando - valgono NO 
	 * allora si chiama il metodo di creazione dell'amministratore CAU 
	 * @param service 
	 * @param operatore
	 * @return
	 * @throws SQLException
	 * @throws CheckException 
	 */
	private  boolean verificaCreazioneAmministratoreCAU(String cf) throws SQLException, CheckException {
		boolean gestioneOK = true;
		OperatoreFinder finderRichiedente = new OperatoreFinder(cf);
		OperatoreBean operatore = service.getDatiUtente2(finderRichiedente);		
		CauParam.getInstance();
		if("NO".equals(operatore.getRichiedente()) && 
		   "NO".equals(operatore.getAutorizzazioneLivelloI()) && 
		   "NO".equals(operatore.getAutorizzazioneLivelloII())){			
			if(CauParam.WEB_SERVICES_ON==1){
				try {
					CreaAmministratoreCAU(operatore);
				} catch (Exception e) {
					e.printStackTrace();
					gestioneOK = false;
				}
			}
			else{
				gestioneOK = true;
			}
		}
		return gestioneOK;
	}
	
	public  boolean isDelegato(String cf, String ruolo) throws CheckException{
	
	DelegaFinder delegaFinder = new DelegaFinder();
	boolean delegato=false;
	delegaFinder.setCodiceFiscaleDelegato(cf);
	if (ruolo!=null && !ruolo.equals(""))
	delegaFinder.setRuoloDelegato(ruolo);
	List<DelegaBean> listDeleghe = service.getElencoDeleghe(delegaFinder );
	if (listDeleghe!=null && !listDeleghe.isEmpty())
			delegato=true;
	
	return delegato;
	}
	
	/**
	 * Il metodo verifica se chiamare il metodo di cancellazione dell'amministratore CAU
	 * La cancellazione viene eseguita se per utente,  i tre flag del DB dopo l'operazione 
	 * che si sta effettuando valgono NO e se l'utente non ha deleghe attive. 
	 * @param cf
	 * @param service
	 * @param ruolo: ruolo che si sta revocando
	 * @return
	 * @throws SQLException
	 * @throws CheckException
	 */
	private  boolean verificaCancellazioneAmministratoreCAU(String cf,String ruolo) throws SQLException, CheckException {
		boolean gestioneOK = true;
		boolean flagNO = false; // indica se i 3 flag dell'utente diventeranno "NO" dopo l'elaborazione
		boolean nessunaDelega = false; // indica se non esiste nessuna delega attiva
		OperatoreFinder finderRichiedente = new OperatoreFinder(cf);
		OperatoreBean operatore = service.getDatiUtente2(finderRichiedente);		
		CauParam.getInstance();
		
		// verifico i flag
		if("RI".equals(ruolo)){
			if("NO".equals(operatore.getAutorizzazioneLivelloI()) && 
			   "NO".equals(operatore.getAutorizzazioneLivelloII())){
				flagNO = true;
			}			
		}else if ("A1".equals(ruolo)){
			if("NO".equals(operatore.getRichiedente()) && 
			   "NO".equals(operatore.getAutorizzazioneLivelloII())){
				flagNO = true;
			}			
		}
		// verifico le deleghe
		DelegaFinder finder = new DelegaFinder();
		finder.setCodiceFiscaleDelegato(cf);
		List<DelegaBean> listDeleghe = service.getElencoDeleghe(finder);
		if(listDeleghe!=null && listDeleghe.isEmpty()){
			nessunaDelega = true;
		}
		
		if(flagNO && nessunaDelega){			
			if(CauParam.WEB_SERVICES_ON==1){
				try {
					CancellaAmministratoreCAU(operatore);
				} catch (Exception e) {
					e.printStackTrace();
					gestioneOK = false;
				}
			}
			else{
				gestioneOK = true;
			}
		}
		return gestioneOK;
	}
	
	public  Map<String, String> getRuoloAmministatore(UtenteBean utente,
			List<CDRBean> gerarchia, List<String> codiciAmmLocale) {

		boolean isAmministratoreLocale = utente.isAmministratoreLocale();
		boolean isAmministratoreRegionale = utente.isAmministratoreRegionale();
		boolean isAmministratoreCentrale = utente.isAmministratoreCentrale() || utente.isAmministratoreApplicativo();
		Map<String, String> ruolo = new HashMap<String, String>();
		
		try {
			if(isAmministratoreLocale && codiciAmmLocale != null){
				boolean trovato = false;
				for(CDRBean cdr : gerarchia){
					for(String codiceStruttura : codiciAmmLocale){
						if(!trovato && (cdr.getCodiceStruttura().equals(codiceStruttura) 
								|| cdr.getCodiceCDR().equals(codiceStruttura))){
							ruolo.put(Ruolo.AMMINISTRATORE_LOCALE, cdr.getCodiceCDR());
							trovato = true;
						}
					}
				}
			}

			if((ruolo == null || ruolo.isEmpty()) && isAmministratoreRegionale){
				List<String> codiciStrutture = new ArrayList<String>();
				for (Map.Entry<String, HashSet<String>> entry : utente.getProfiloLst().entrySet()){
					if(entry.getKey().equals(Ruolo.AMMINISTRATORE_REGIONALE))
						codiciStrutture.addAll(entry.getValue());
				}

				for(CDRBean cdr : gerarchia){
					for(String codiceStruttura : codiciStrutture){
						if(cdr.getCodiceStruttura().equals(codiceStruttura) || cdr.getCodiceCDR().equals(codiceStruttura)){
							ruolo.put(Ruolo.AMMINISTRATORE_REGIONALE, cdr.getCodiceCDR());
							break;
						}
					}
				}
			}
			
			if((ruolo == null || ruolo.isEmpty()) && isAmministratoreCentrale){
				ruolo = new HashMap<String, String>();
				ruolo.put(Ruolo.AMMINISTRATORE_CENTRALE, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ruolo;
	}
	
	public  boolean isOperatoreRevocato(String cf) throws Exception{
		boolean isRevocato = false;
	
		
		PropertiesReader pr = new PropertiesReader(DIR_PROPERTIES, FILE_PROPERTIES);
		Properties properties = pr.getProperties();
		String mod = properties.getProperty("MOD");
		if(mod.equals("0")){
			//TEST
			UtenteBean utenteBean = service.getRevocheUtente(cf);
			if((utenteBean.getRevocaPostazione() != null && utenteBean.getRevocaPostazione().equals("SI")) 
					||(utenteBean.getRevocaCau() != null && utenteBean.getRevocaCau().equals("SI")))
				isRevocato = true;
		}else{
//			ServiceCauUtente scu = new ServiceCauUtente(SigaCache.getSYS_ADMIN(), SigaCache.getSYS_AGENZIA());
//			UtenteInfoBean info=scu.getInfo(cf);
//			if (info!=null)
//			 isRevocato=true;
			
			UtenteBean utenteBean = service.getRevocheUtente(cf);
			if((utenteBean.getRevocaPostazione() != null && utenteBean.getRevocaPostazione().equals("SI")) 
					||(utenteBean.getRevocaCau() != null && utenteBean.getRevocaCau().equals("SI")))
				isRevocato = true;
			
		}
		return isRevocato;
	}


private  void setTrustedUtente(ServiceCauUtente scuTemp, String cfOperatore, boolean trusted) {
		Utente_UpdateService uucb = new Utente_UpdateService();
		uucb.setUserId(cfOperatore);
		uucb.setTrusted(trusted);
		try {
			scuTemp.aggiorna(uucb);
		} catch (Exception e) {
			e.printStackTrace();
			Logg.getLogger().error(e.getMessage(), e);
		}
}

}