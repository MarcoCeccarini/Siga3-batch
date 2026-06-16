package it.finanze.siga.business;

import it.finanze.siga.bean.AllegatoBean;
import it.finanze.siga.bean.CDRBean;
import it.finanze.siga.bean.DelegaBean;
import it.finanze.siga.bean.OperatoreBean;
import it.finanze.siga.dao.impl.SigaDaoImpl;
import it.finanze.siga.finder.AuditFinder;
import it.finanze.siga.finder.DelegaFinder;
import it.finanze.siga.finder.DocumentoFinder;
import it.finanze.siga.finder.OperatoreFinder;
import it.finanze.siga.service.SIGAService;
import it.finanze.siga.service.SIGAServiceProxy;
import it.finanze.siga.util.Logg;
import it.finanze.siga.util.SigaCache;
import it.finanze.siga.util.Utils;
import it.finanze.siga.util.bean.UtenteBean;
import it.sogei.eaf.util.CheckException;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.*;

@ApplicationScoped
@Named("DelegheBusiness")	 
public class DelegheBusiness {
	@EJB
	SigaDaoImpl service;

	@Inject
	MailCommonUtil mailCommonUtil;

	@EJB
	MetodiComuni metodiComuni;

	private  int idAudit=0;
	public static final  String RICHIEDENTE = "RI";
	public static final  String AUTORIZZATORE_I_LIV = "A1";
	public static final  String AUTORIZZATORE_II_LIV = "A2";
	private static final  String RIGA_AMMINISTRATORE = "La delega e' stata impostata dall'amministratore ";
	private static final  String RIGA_AMMINISTRATORE_REVOCA = " ha revocato ";
	private static final  String RIGA_REVOCA = " e' stata revocata ";
	
	
	/**
	 * Metodo che a seconda dell'azione richiesta,ne invoca un altro o piu' di uno
	 * !Richiamare  dal batch solamente questo metodo!
	 * 
	 * @author Andrea
	 * @param azione puo essere A = Assegna, R=Revoca, V=Variazione data scadenza, S=Sostituzione Delega 
	 * @param cfDelegante
	 * @param cfDelegato
	 * @param ruolo puo' essere A1 = Autorizzatore I Liv, A2 = Autortizzatore II livello, RI = Richiedere 
	 * @param dataScadenza
	 * @param motivoRevoca
	 * @param cfUtente
	 * @return void
	 * @
	 */
	public  void delega (String azione, String cfDelegante, String cfDelegato, String ruolo,
			Date dataScadenza, String motivoRevoca,String cfUtente, UtenteBean utente, String cdrOperazione, String nota){
		
		if(utente == null){
			utente = new UtenteBean();
			utente.setCodFiscaleUtente("");
		}
			
		
		if(azione.equals("A")) assegnazione(azione,cfDelegante,cfDelegato,ruolo,cfUtente,dataScadenza, utente, cdrOperazione, nota);
		if(azione.equals("R")) revoca(azione,cfDelegante, cfDelegato,ruolo,motivoRevoca,cfUtente,dataScadenza, utente, cdrOperazione);
		if(azione.equals("V")) variazioneDataScadenza(cfDelegante,cfDelegato,ruolo,cfUtente,dataScadenza, utente, cdrOperazione, nota);
		if(azione.equals("S")) sostituzioneDelegato(cfDelegante,cfDelegato,ruolo,cfUtente,dataScadenza, utente, cdrOperazione, nota);
	}

	// gestione allegati
	public  void insIdAllegatiDelega (String cfDelegante, String cfDelegato, String ruolo, Integer idDelega) {
				
		DelegaBean finder = new DelegaBean();

		finder.setCodiceFiscaleDelegante(cfDelegante);
		finder.setCodiceFiscaleDelegato(cfDelegato);
		finder.setRuoloDelegato(ruolo);
		finder.setIdDelega(idDelega);
		
		try{
			
			
			service.insIdAllegatiDelega(finder);
			
			

		} catch (Exception e) {
			Logg.getLogger().error("Errore nell'inserimento insIdAllegatiDelega: ", e);
			throw new RuntimeException(e);
		}

	}
	
	public  Integer inserisciAllegatiDelega (List<DocumentoFinder> lstAll) {

		Integer keyIdDelega=null;
		try{
			
			if(lstAll!=null && !lstAll.isEmpty()) {
				
				
				keyIdDelega = service.keyIdDelega();
				int inseriti=0;
				for (DocumentoFinder alleg : lstAll) {
					alleg.setIdTab(keyIdDelega);
					service.inserisciAllegatiDelega(alleg);
					inseriti++;
				}
				Logg.getLogger().info("Inseriti nr. "+ inseriti +" allegati delega");
			}

		} catch (Exception e) {
			Logg.getLogger().error("Errore in inserimento allegati delega inserisciAllegatiDelega: ", e);
			throw new RuntimeException(e);
		}
		
		return keyIdDelega;
	}
	
	public  List<AllegatoBean> getAllegatiDelega(Integer idDelega) {
		
		List<AllegatoBean> allegati = new ArrayList<AllegatoBean>();
		try {
			
		
			allegati = service.getAllegatiDelega(idDelega);
		

		} catch (Exception e) {
			Logg.getLogger().error("Errore in recupero allegati delega getAllegatiDelega: " + idDelega, e);
			throw new RuntimeException(e);
			
		}
	
		return allegati;
	}
	
	// <-- fine gestione allegati
	
	
	/**
	 * Metodo per l'assegnazione di una delega
	 * 
	 * @author Andrea
	 * @param cfDelegante
	 * @param cfDelegato
	 * @param ruolo
	 * @param cfUtente
	 * @param dataScadenza
	 * @
	 */
	private  void assegnazione(String azione, String cfDelegante, String cfDelegato, String ruolo, String cfUtente,
			Date dataScadenza, UtenteBean utenteLoggato, String cdrOperazione, String nota){
		
		DelegaFinder finder = new DelegaFinder();
		OperatoreBean delegante = getOperatore(cfDelegante);
		OperatoreBean delegato = getOperatore(cfDelegato);
		List <DelegaBean> list;
		boolean batch=true;
		UtenteBean utente = new UtenteBean();
		
		finder.setCdrDelegante(delegante.getCdr());
		finder.setCdrDelegato(delegato.getCdr());
		finder.setCodiceFiscaleDelegante(delegante.getCf());
		finder.setCodiceFiscaleDelegato(delegato.getCf());
		finder.setDataScadenza(dataScadenza);
		finder.setRuoloDelegato(ruolo);
		finder.setNota(nota);
		
		if((cfUtente!=null)&&(!cfUtente.equals(""))){
			finder.setCfInizio(cfUtente);
			UtenteTransform transform = new UtenteTransform();
			utente = transform.setUtente(cfUtente);
			batch=false;
		}
		
		try{
			
			list= service.getElencoDeleghe(finder);
			if(list.isEmpty()){
				if((!batch) && (utente.isAmministratore()) && (!cfDelegante.equals(cfUtente))){
					if(!azione.equals("V")){
						idAudit = inserisciAudit(azione,cfDelegante,cfDelegato,utente,ruolo,dataScadenza, utenteLoggato, cdrOperazione);
					}
					finder.setIdAuditInizio(idAudit);
				}else {
					//se non devo scrivere l'Audit allora non dovro neanche nadare a scrivere il cf inizio
					finder.setCfInizio("");
				}
				service.inserisciDelega(finder);
				try {
					controlloCAU(delegato, "A");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			Logg.getLogger().error(e.getMessage());
			throw new RuntimeException("errore nell'assegnazione, DelegaBusiness : ",e);
		}
	}
	
	private  void revoca(String azione,String cfDelegante, String cfDelegato, String ruolo, String motivoRevoca,
			String cfUtente,Date dataScadenza, UtenteBean utenteLoggato, String cdrOperazione){
		DelegaFinder finder = new DelegaFinder();
		List<DelegaBean> list ;
		DelegaBean bean;
		OperatoreBean delegato;
		boolean batch = true;
		UtenteBean utente = new UtenteBean();
		
		finder.setCodiceFiscaleDelegante(cfDelegante);
		finder.setCodiceFiscaleDelegato(cfDelegato);
		finder.setRuoloDelegato(ruolo);
		finder.setMotivoRevoca(motivoRevoca);
		
		if((cfUtente!=null)&&(!cfUtente.equals(""))){
			finder.setCfFine(cfUtente);
			UtenteTransform transform = new UtenteTransform();
			utente = transform.setUtente(cfUtente);
			batch=false;
		}
		// TEMP TODO:
		
		try{
			
			
			list = service.getElencoDeleghe(finder);
			
			if(!list.isEmpty()){
				if((!batch)&&(utente.isAmministratore())&&(!cfDelegante.equals(cfUtente))){
					if(azione.equals("R")||azione.equals("S")){
						idAudit = inserisciAudit("R",cfDelegante,cfDelegato,utente,ruolo,list.get(0).getDataScadenza(), utenteLoggato, cdrOperazione);
					}else{
						idAudit = inserisciAudit("V",cfDelegante,cfDelegato,utente,ruolo,dataScadenza, utenteLoggato, cdrOperazione);
					}
					finder.setIdAuditFine(idAudit);
				}
				else{
					// se non devo scrivere il valore dell'audit allora non
					// dovro neanche andare a srivere il valore del cf chiusura
					// perche non e' un autotizzatore
					finder.setCfFine("");
				}
				service.rimozioneDelega(finder);
				Iterator<DelegaBean> iter = list.iterator();
				while(iter.hasNext()){
					bean= (DelegaBean) iter.next();
					if(bean.getRuoloDelegato().equals("RI")){
//						aggiornaRichiesteDeleghe(cfDelegato);
					}
					//else{
						delegato= getOperatore(bean.getCodiceFiscaleDelegato());
						try {
							controlloCAU(delegato,"RI");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					//}
				}
			}
		}catch(Exception e){
			Logg.getLogger().error("Errore nel metodo revoca",e);
			throw new RuntimeException(e);
		}
	}
	
	private  void sostituzioneDelegato (String cfDelegante, String cfNuovoDelegato, String ruolo, String cfUtente, 
			Date dataScadenza, UtenteBean utente, String cdrOperazione, String nota)
			{
		DelegaFinder finder = new DelegaFinder();
		finder.setCodiceFiscaleDelegante(cfDelegante);
		finder.setRuoloDelegato(ruolo);
		
		List<DelegaBean> list;
		try{
			
			
			list= service.getElencoDeleghe(finder);
			
			if(!list.isEmpty()){
				revoca("S",cfDelegante,list.get(0).getCodiceFiscaleDelegato(),ruolo,"REVC",cfUtente,null, utente, cdrOperazione);
				assegnazione("S",cfDelegante,cfNuovoDelegato,ruolo,cfUtente,dataScadenza, utente, cdrOperazione, nota);
			}
		}catch(Exception e){
			Logg.getLogger().error("Errore nel metodo sostituzione Delegato",e);
			throw new RuntimeException(e);
		}
	}
	
	/**Metodo per la modifica della data Scadenza della delega
	 * 
	 * @author Andrea
	 * @param cfDelegante
	 * @param cfDelegato
	 * @param ruolo
	 * @param cfUtente
	 * @param nuovaDataScadenza
	 * @
	 */
	private  void variazioneDataScadenza(String cfDelegante, String cfDelegato,
			String ruolo, String cfUtente, Date nuovaDataScadenza, UtenteBean utente, String cdrOperazione, String nota)  {
		try{
			revoca("V",cfDelegante,cfDelegato,ruolo,null,cfUtente,nuovaDataScadenza, utente, cdrOperazione);
			assegnazione("V",cfDelegante,cfDelegato,ruolo,cfUtente,nuovaDataScadenza, utente, cdrOperazione, nota);
		}catch(Exception e){
			Logg.getLogger().error("Errore nel metodo Variazione Data Scadenza",e);
			throw new RuntimeException(e);
		}
	}
	
	/** 
	 * Metodo per ottenere da un codice fiscale tutti  dati dell'operatore
	 * 
	 * @author Andrea
	 * @param cf Operatore
	 * @return OperatoreBean
	 * @
	 */
	public  OperatoreBean getOperatore(String cf){
		
		OperatoreFinder operatore = new OperatoreFinder(cf);
		OperatoreBean bean = new OperatoreBean();
		
		try{

			bean =service.getDatiUtente2(operatore);
		}catch(Exception e){
			Logg.getLogger().error(e.getMessage(), e);
			throw new RuntimeException ("Errore nel metodo GetOperatore",e);
		}
		return bean;
	}
	
	/**
	 * Metodo per richiamare il servizio del controllo se uno dei due operatori
	 * e' un amministratore CAU - in attesa del servizio
	 * 
	 * @author Andrea
	 * @param  delegante
	 * @param  delegato
	 * @throws Exception 
	 */
	public  void controlloCAU (OperatoreBean delegato, String azione)throws Exception{
		
		if(("NO".equals(delegato.getRichiedente())) &&
				("NO".equals(delegato.getAutorizzazioneLivelloII())) && 
				("NO".equals(delegato.getAutorizzazioneLivelloI()))){
			
			if(azione.equals("A")) {
				metodiComuni.CreaAmministratoreCAU(delegato);
			}else if(azione.equals("RI")){
				try {

					DelegaFinder deleg = new DelegaFinder();
					deleg.setCodiceFiscaleDelegato(delegato.getCf());
					int count = service.countAltreDeleghe(deleg);
					if(count == 0){
						metodiComuni.CancellaAmministratoreCAU(delegato);
					}
				} catch (Exception e) {
					Logg.getLogger().error(e.getMessage(), e);
					throw new RuntimeException ("Errore nel metodo controlloCAU",e);
				}
			}
			
			
			
//			OperatoreFinder deleg = new OperatoreFinder("");
//			deleg.setCfDelegato(delegato.getCf());
//			boolean chiamaServizio = true;
//			try{
//				
//				List<OperatoreBean> listDeleghe = service.getOperatoriPerNominativo(deleg);
//				for (OperatoreBean operatoreBean : listDeleghe) {
//					if(("A1".equals(operatoreBean.getRuolo()))
//							||("A2".equals(operatoreBean.getRuolo()))
//							||("RI".equals(operatoreBean.getRuolo()))){
//						chiamaServizio = false;
//						break;
//					}						
//				}			
//				if(azione=="A") {
//					metodiComuni.CreaAmministratoreCAU(delegato);
//				}				
//				if(azione=="RI" && chiamaServizio){//in caso di RI=rimozione chiamare servizio per cancellare il CF del delegato come amministratore CAU
//					metodiComuni.CancellaAmministratoreCAU(delegato);
//					
//				}
//				
//			
//			}catch(Exception e){
//				
//				Logg.getLogger().error(e.getMessage(), e);
//				throw new CheckException ("Errore nel metodo controlloCAU",e);
//			}
		}
	}
	
	/**Metodo per l'inserimento nella tabella Audit
	 * 
	 * @author Andrea
	 * @param azione
	 * @param cfDelegante
	 * @param cfDelegato
	 * @param utente
	 * @param ruolo
	 * @param dataScadenza
	 * @return
	 * @
	 */
	public  int inserisciAudit(String azione,String cfDelegante, String cfDelegato, 
			UtenteBean utente,String ruolo, Date dataScadenza, UtenteBean utenteLoggato, String cdrOperazione)
			{
		AuditFinder auditFinder = new AuditFinder();
		
		int idAudit;
		String testo="";
		String r = "";
		r = Utils.converRuolo(ruolo);

		auditFinder.setCf(utente.getCodFiscaleUtente());
		auditFinder.setCDRAmministratore(utente.getCodiceCDRUser());
		auditFinder.setTabAggiornata("DELEGHE");
		
		OperatoreBean delegante = service.getDatiUtente2(new OperatoreFinder(cfDelegante));
		if(delegante ==  null )
			delegante = new OperatoreBean();
		
		OperatoreBean delegato = service.getDatiUtente2(new OperatoreFinder(cfDelegato));
		if(delegato ==  null )
			delegato = new OperatoreBean();
		
		if((azione.equals("A"))||(azione.equals("S"))){
			testo += "Delegato ruolo "+r+" "+delegante.toStringCognomeNomeCF()+" a "+delegato.toStringCognomeNomeCF()+" fino al "+Utils.dateStringFromDate(dataScadenza);
		}
		if(azione.equals("R")){
			testo += "Revocata delega ruolo "+r+" "+delegante.toStringCognomeNomeCF()+" a "+delegato.toStringCognomeNomeCF();
		}
		if(azione.equals("V")){
			testo += "Delega ruolo " + r + " " +delegante.toStringCognomeNomeCF()+" a "+delegato.toStringCognomeNomeCF()+": nuova data scadenza uguale a "+Utils.dateStringFromDate(dataScadenza);
		}
		
		auditFinder.setTesto(testo);
		
		List<CDRBean> gerarchia = service.getGerarchiaCdR(cdrOperazione);
		List<String> codiciAmmLocale = service.getCodiceAmmLocale(utenteLoggato.getCodFiscaleUtente());
		if(gerarchia != null){
			Map<String, String> ruoloUtenteLoggato = metodiComuni.getRuoloAmministatore(
					utenteLoggato, gerarchia,codiciAmmLocale);
			if(ruoloUtenteLoggato != null){
				for (Map.Entry<String, String> entry : ruoloUtenteLoggato.entrySet()){
					auditFinder.setRuoloOperazione(entry.getKey());
					auditFinder.setCdrRuolo(entry.getValue());
				}
			}
		}

		
		try{
			idAudit = service.inserisciAUDIT(auditFinder);			
		}catch(Exception e){
			
			Logg.getLogger().error("Errore nel metodo inserisciAudit",e);
			throw new RuntimeException(e);
		}
		return idAudit;
	}
	
	/**Metodo per l'aggiornamento della tabella Registro Richieste
	 * 
	 * @author Andrea
	 * @param cfDelegato
	 * @
	 */
	public  void  aggiornaRichiesteDeleghe(String cfDelegato){
		try{
			
			service.aggiornaRichiesteDeleghe(cfDelegato);
		}catch (Exception e){
			Logg.getLogger().error("Errore nel metodo aggiornaRichiesteDeleghe",e);
			throw new RuntimeException(e);
		}
	}
	
	public  List<OperatoreBean> getDelegabiliByCdr (String cdr, boolean isInDeleghe) {
		try{
//			if(!cdr.startsWith("'"))
//				cdr="'"+cdr+"'";
			Map<String, Object> input = new HashMap<String, Object>();
			input.put("cdr", cdr);
			input.put("isInDeleghe", String.valueOf(isInDeleghe));
			if(!cdr.isEmpty() && cdr.contains(",")){
				//abbiamo delegabili da ricercare in pie' codici CdR(selezionato Tutti dalla tendina) 26/06/2018
				String [] cdrLst = cdr.replaceAll("'", "").split(",");
				input.put("codiciCdr", cdrLst);
			}
			
			return  service.getDelegabiliByCdr(input);
		}catch(Exception e){
			Logg.getLogger().error("Errore nel metodo aggiornaRichiesteDeleghe",e);
			throw new RuntimeException(e);
		}
	}
	
	public  List<OperatoreBean> getDelegabiliByCdrORA (String cdr) {
		try{
			if(!cdr.startsWith("'"))
				cdr="'"+cdr+"'";
			
			return  service.getDelegabiliByCdrORA(cdr);
		}catch(Exception e){
			Logg.getLogger().error("Errore nel metodo aggiornaRichiesteDeleghe",e);
			throw new RuntimeException(e);
		}
	}
	
	public  String getStrutturaByCDR(String cdr){
		try{
			
			return service.getStrutturaByCDR(cdr);
		}catch(Exception e){
			Logg.getLogger().error("Errore nel metodo getStrutturaByCDR",e);
			throw new RuntimeException(e);
		}
	}

	public  List<OperatoreBean> getDelegantiByCDR(String cdr) {
		try{
			
			return service.getDelegantiByCDR(cdr);
		}catch(Exception e){
			Logg.getLogger().error("Errore nel metodo getDelegantiByCDR",e);
			throw new RuntimeException(e);
		}
	}
	
	public  List<String> getElencoStruttRegionali(String strutturaIILiv) {
		try{
			
			return service.getElencoStruttRegionali(strutturaIILiv);
		}catch(Exception e){
			Logg.getLogger().error("Errore nel metodo getElencoStruttRegionali",e);
			throw new RuntimeException(e);
		}
	}
	
	public  void inviaMailAssegnazioneDelega(UtenteBean utente,String cdrUtente, OperatoreBean delegato, String cfDelegante, String ruolo,
			String dataScadenza)  {
		
		String subject = "";
		String template = "";
		String cfDelegato = delegato.getCf();
		List<String> destinatari = new ArrayList<String>();
		List<String> destinatari_cc = new ArrayList<String>();
		HashMap<String, String> param = new HashMap<String, String>();
		

		subject = "mail.oggetto.assegnazione.delega";
		template = "mail.template.file.assegnazione.delega";
		// il destinatario e' il delegato
		destinatari.addAll(mailCommonUtil.ricavaEmailByCf(cfDelegato));
		// in copia il delegante
		destinatari_cc.addAll(mailCommonUtil.ricavaEmailByCf(cfDelegante));
		// e l'amministratore se lo user e' un amministratore
		if (utente != null && utente.isAmministratore()) {
//			
			param.put("RIGA_AMMINISTRATORE", RIGA_AMMINISTRATORE + utente.getNome() + " " + utente.getCognome() + ".");
		} else {
//			param.put("DESCRIZIONE_UTENTE", "Titolare del ruolo");
		}
//		if (utente != null) {
//			param.put("NOME", utente.getNome());
//			param.put("COGNOME", utente.getCognome());
//			param.put("CDR", cdrUtente);
//		}

		OperatoreFinder finderDelegante = new OperatoreFinder(cfDelegante);
		OperatoreBean delegante = service.getDatiUtente2(finderDelegante);
		param.put("DELEGANTE", delegante.getNome() + " " + delegante.getCognome());
		param.put("RUOLO", ruolo);
		param.put("DATA", dataScadenza);
		param.put("DELEGATO", delegato.getNome() + " " + delegato.getCognome());

		// Invio Mail
		mailCommonUtil.sendMailSemplified(destinatari, destinatari_cc, subject,	template, param);

	}

	
	public  void inviaMailRevocaDelega(UtenteBean utente,String cdrUtente, OperatoreBean delegato, String cfDelegante, 
			String ruolo)  {
		
		String subject = "";
		String template = "";
		String cfDelegato = delegato.getCf();
		List<String> destinatari = new ArrayList<String>();
		List<String> destinatari_cc = new ArrayList<String>();
		HashMap<String, String> param = new HashMap<String, String>();
		

		subject = "mail.oggetto.rimozione.delega";
		template = "mail.template.file.rimozione.delega";
		// il destinatario e' il delegato
		destinatari.addAll(mailCommonUtil.ricavaEmailByCf(cfDelegato));
		// in copia il delegante
		destinatari_cc.addAll(mailCommonUtil.ricavaEmailByCf(cfDelegante));
		
		OperatoreFinder finderDelegante = new OperatoreFinder(cfDelegante);
		OperatoreBean delegante = service.getDatiUtente2(finderDelegante);
		// e l'amministratore se lo user e' un amministratore
		if (utente != null && utente.isAmministratore()) {
			param.put("RIGA1", "l'amministratore " + utente.getNome() + " " + utente.getCognome() + " ha revocata");
			param.put("RIGA2", " per conto di " + delegante.getNome() + " " + delegante.getCognome() + ".");
		} else {
			param.put("RIGA1", delegante.getNome() + " " + delegante.getCognome() + " ha revocata");
			param.put("RIGA2", ".");
		}
		

		
		param.put("RUOLO", ruolo);
		param.put("DELEGATO", delegato.getNome() + " " + delegato.getCognome());

		// Invio Mail
		mailCommonUtil.sendMailSemplified(destinatari, destinatari_cc, subject,	template, param);

	}
	
	/**
	 * @param utente
	 * @param service
	 * @param delega
	 * @param delegante
	 * @param delegato
	 * @param d
	 */
	public  void inviaMailProrogaDelega(UtenteBean utente,
			 String ruolo,
			OperatoreBean delegante, OperatoreBean delegato, Date d) {
		/* Gestione invio email */
		HashMap<String,String> param = new HashMap<String,String>();
		// il destinatario e' il delegato
		List<String> destinatari = new ArrayList<String>();
		destinatari.addAll(mailCommonUtil.ricavaEmailByCf(delegato.getCf()));
		// in copia il delegante 
		List<String> destinatari_cc = new ArrayList<String>();
		destinatari_cc.addAll(mailCommonUtil.ricavaEmailByCf(delegante.getCf()));
		// e l'amministratore se lo user e' un amministratore				
		if(utente!=null && utente.isAmministratore()){
			param.put("DELEGANTE", "l'amministratore " + utente.getNome() + " " + utente.getCognome());
			param.put("RIGA_AMMINISTRATORE", " per conto di " + delegante.getNome() + " " + delegante.getCognome());
		}else{
			param.put("DELEGANTE", delegante.getNome() + " " + delegante.getCognome());
		}
		param.put("DELEGATO", delegato.getNome() + " " + delegato.getCognome());
		
		param.put("DATA", Utils.dateStringFromDate(d));
		param.put("RUOLO", ruolo);
		
		//Invio Mail
		mailCommonUtil.sendMailSemplified(destinatari,destinatari_cc, "mail.oggetto.modifica.scadenza.delega", 
				"mail.template.file.modifica.scadenza.delega", param);
	}
	
}
