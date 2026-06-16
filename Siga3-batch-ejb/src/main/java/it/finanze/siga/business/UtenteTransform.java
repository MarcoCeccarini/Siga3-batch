package it.finanze.siga.business;

import it.finanze.siga.bean.CDRBean;
import it.finanze.siga.bean.DelegaBean;
import it.finanze.siga.bean.OperatoreBean;
import it.finanze.siga.dao.impl.SigaDaoImpl;
import it.finanze.siga.finder.CDRFinder;
import it.finanze.siga.finder.DelegaFinder;
import it.finanze.siga.finder.OperatoreFinder;
import it.finanze.siga.service.SIGAServiceProxy;
import it.finanze.siga.util.Logg;
import it.finanze.siga.util.Ruolo;
import it.finanze.siga.util.SigaCache;
import it.finanze.siga.util.bean.UtenteBean;
import it.sogei.eaf.util.CheckException;
import jakarta.inject.Inject;

import java.util.*;


public class UtenteTransform {

	@Inject
	SigaDaoImpl service;

	@Inject DelegheBusiness delegheBusiness;
	/**Metodo per riempire tutti i dati del UtenteBean dato un codice fiscale
	 * 
	 * @author Andrea
	 * 
	 * @param cf
	 * @return UtenteBean
	 * @
	 */
	public UtenteBean setUtente(String cf) {
		
		
		UtenteBean utente = new UtenteBean();
		HashMap<String,HashSet<String>> matrice = new HashMap<String,HashSet<String>>();
		HashMap<String,HashSet<String>> matricePerRichieste = new HashMap<String,HashSet<String>>();
 
		HashMap <String,String> funzioni = new HashMap<String, String>();
		
		DelegaFinder delegato = new DelegaFinder();
		DelegaBean bean ;
		
		//Creo gli hashset per ogni ruolo che poi aggiungero' alla mappa
		HashSet<String> richiedente = new HashSet<String>();
		HashSet<String> autorizzatoreI = new HashSet<String>();
		HashSet<String> autorizzatoreII = new HashSet<String>();
		HashSet<String> amministratoreCentale = new HashSet<String>();
		HashSet<String> amministratoreApplicativo  = new HashSet<String>();
		HashSet<String> amministratoreRegionale = new HashSet<String>();
		HashSet<String> amministratoreLocale = new HashSet<String>();
		HashSet<String> auditorTotale = new HashSet<String>();
		HashSet<String> auditorCentrale = new HashSet<String>();
		HashSet<String> auditorRegionale = new HashSet<String>();
		HashSet<String> gestore = new HashSet<String>();		
		
		HashSet<String> responsabile = new HashSet<String>();
		OperatoreFinder finder = new OperatoreFinder(cf);
		List<CDRBean> listResponsabile = service.getResponsabileByCF(finder);
		if (listResponsabile!=null && !listResponsabile.isEmpty()){
			responsabile.add("SI"); 
			matrice.put(Ruolo.RESPONSABILE_CDR, responsabile);			
		}
		
		// Inizio Ricerca ruoli Delegabili
		OperatoreBean operatore = delegheBusiness.getOperatore(cf);
		
		if(operatore.getRichiedente().equals("SI"))richiedente.add(cf);
		if(operatore.getAutorizzazioneLivelloI().equals("SI")) autorizzatoreI.add(cf);
		if(operatore.getAutorizzazioneLivelloII().equals("SI")) autorizzatoreII.add(cf);
		
		
		delegato.setCodiceFiscaleDelegato(cf);
		
		List<DelegaBean> listDelegato = service.getElencoDeleghe(delegato);
		operatore=service.getDatiUtente(finder);
		
		
		
		List <String> gestOperatore= service.getRuoliGestoriOperatori(cf);
		
		Iterator<DelegaBean> iter = listDelegato.iterator();
		while(iter.hasNext()){
			
			bean = iter.next();
			
			if(bean.getRuoloDelegato().equals("RI")){
				
				richiedente.add(bean.getCodiceFiscaleDelegante());
			}
			if(bean.getRuoloDelegato().equals("A1")){
				
				autorizzatoreI.add(bean.getCodiceFiscaleDelegante());
			}
			if(bean.getRuoloDelegato().equals("A2")){
				
				autorizzatoreII.add(bean.getCodiceFiscaleDelegante());
			}
			
		}
		//Assegno i ruoli delegabili alla matrice utente
		if(!autorizzatoreI.isEmpty())	matrice.put(Ruolo.AUTORIZZATORE_I, autorizzatoreI);
		if(!autorizzatoreII.isEmpty()) matrice.put(Ruolo.AUTORIZZATORE_II, autorizzatoreII);
		
		
		//Creare Metodo SetFunzioni! ;)
		if(!richiedente.isEmpty()){
			matrice.put(Ruolo.RICHIEDENTE, richiedente);
			
			if(this.abilitatoCambioUfficio(cf)) funzioni.put("CAMBIO_UFFICIO", "SI");
			
		}
		
		//Inizio assegnazione per i ruoli non delegabili
		
		List<String> codiciRuoli = this.getProfiliAttivi(cf);
		
		Iterator<String> iter2 = codiciRuoli.iterator();
		
		while(iter2.hasNext()){
			
			String codice = iter2.next();
			
			String valore = Ruolo.getRuoloByCodice(codice);
			
			if (valore.equals(Ruolo.AMMINISTRATORE_CENTRALE)&&(amministratoreCentale.isEmpty())){
					
					amministratoreCentale.add("SI");
					matrice.put(Ruolo.AMMINISTRATORE_CENTRALE, amministratoreCentale);
			}	
			
			if ((valore.equals(Ruolo.AMMINISTRATORE_REGIONALE))&&(amministratoreRegionale.isEmpty())){
				
				amministratoreRegionale.add(this.getStrutturaIILiv(cf));
				matrice.put(Ruolo.AMMINISTRATORE_REGIONALE, amministratoreRegionale);
				
			}
			if ((valore.equals(Ruolo.AMMINISTRATORE_LOCALE))&&(amministratoreLocale.isEmpty())){
				
				amministratoreLocale.addAll(this.getCodiceAmmLocale(cf));
				matrice.put(Ruolo.AMMINISTRATORE_LOCALE, amministratoreLocale);

			}
			if ((valore.equals(Ruolo.AUDITOR_CENTRALE_TOTALE))&&(auditorTotale.isEmpty())){
				auditorTotale.add("SI");
				matrice.put(Ruolo.AUDITOR_CENTRALE_TOTALE, auditorTotale);

			}
			if ((valore.equals(Ruolo.AUDITOR_CENTRALE))&&(auditorCentrale.isEmpty())){
				auditorCentrale.add("SI");
				matrice.put(Ruolo.AUDITOR_CENTRALE, auditorCentrale);

			}
			if ((valore.equals(Ruolo.AUDITOR_REGIONALE))&&auditorRegionale.isEmpty()){
				auditorRegionale.add(this.getStrutturaIILiv(cf));
				matrice.put(Ruolo.AUDITOR_REGIONALE, auditorRegionale);

			}
			if (!gestOperatore.isEmpty() &&(gestore.isEmpty())){
				gestore.add("SI"); // q3
				matrice.put(Ruolo.GESTORE, gestore);

			}
			
			
			
		}
		
		if (!gestOperatore.isEmpty() &&(gestore.isEmpty())){
			gestore.add("SI"); // q3
			matrice.put(Ruolo.GESTORE, gestore);

		}
		

		
		
		// Controllo dell amministratore Applicativo ed inserimento dei codici 
		List<String> ammApp = this.getAmmCentraleApp(cf);
		if(!ammApp.isEmpty()){
			
			Iterator<String> iter3 = ammApp.iterator();
			while(iter3.hasNext()){				
				amministratoreApplicativo.add(iter3.next());
			}
			matrice.put(Ruolo.AMMINISTRATORE_CENTRALE_APPLICATIVO, amministratoreApplicativo);
		}
		
		
		matricePerRichieste=(HashMap<String, HashSet<String>>) matrice.clone();
		
		if (matrice.containsKey(Ruolo.AMMINISTRATORE_CENTRALE_APPLICATIVO) || matrice.containsKey(Ruolo.AMMINISTRATORE_CENTRALE))
		{ 
			
			HashSet<String> richiedente_rich = new HashSet<String>();
			HashSet<String> autorizzatoreI_rich = new HashSet<String>();
			
			if (!matrice.containsKey(Ruolo.RICHIEDENTE)){		
				richiedente_rich.add(cf);
				matricePerRichieste.put(Ruolo.RICHIEDENTE, richiedente_rich);
			}

			if (!matrice.containsKey(Ruolo.AUTORIZZATORE_I)){
				autorizzatoreI_rich.add(cf);	
				matricePerRichieste.put(Ruolo.AUTORIZZATORE_I, autorizzatoreI_rich);

			}

		}
 
		
		utente.setCustomProfile(matricePerRichieste);
		utente.setNome(operatore.getNome());
		utente.setCognome(operatore.getCognome());
		utente.setProfiloLst(matrice);
		utente.setFunzioni(funzioni);
		utente.setCodiceCDRUser(this.getCDRUser(cf));
		utente.setCodFiscaleUtente(cf);
		
		return utente;
	}
	
 
	
	
	
	/**Metodo che dato un cf torna tutti i codici profilo, per decodificarli basta usare il metodo
	 * getRuoloByCodice della classe Ruolo
	 * 
	 * @author Andrea
	 * @param cf
	 * @return List<String> codiciProfilo
	 * @
	 */
	private List<String> getProfiliAttivi(String cf){
		
		List<String> codiciProfilo= null ;
		
		try{
			
			codiciProfilo= service.getProfiliAttivi(cf);
			
		}catch(Exception e){
			Logg.getLogger(this.getClass()).error("Errore nel metodo getProfiliAttivi",e);
			throw new RuntimeException(e);
			
		}
		
		return codiciProfilo;
	}
	



	
	/**Metodo per il controllo dell'abilitazione dell'utente al CambioUfficio
	 * 
	 * @author Andrea
	 * @param cf
	 * @return boolean
	 * @
	 */
	private boolean abilitatoCambioUfficio(String cf){
		
		boolean controllo = false;
		
		try{
			
			
			controllo = service.abilitatoCambioUfficio(cf);
			
		}catch ( Exception e){
			
			Logg.getLogger(this.getClass()).error("Errore nel metodo abilitatoCambioUfficio",e);
			throw new RuntimeException(e);
		}
		
		return controllo;
	}
	
	
	
	/**Metodo che dato un cf Amministratore/Auditor regionale, torna il codice struttura regionale
	 * 
	 * @author Andrea
	 * @param cf
	 * @return Codice Struttura
	 * @
	 */
	public String getStrutturaIILiv(String cf){
		
		try{
			
			
			return service.getStrutturaIILiv(cf);
			
		}catch(Exception e){
			
			Logg.getLogger(this.getClass()).error("Errore nel metodo getStrutturaIILiv",e);
			throw new RuntimeException(e);
		}	
		
	}
	
	
	
	/**Metodo che dato un cf AmministratoreLocale torna tutti i codici struttura
	 * 
	 * @author Andrea
	 * @param cf
	 * @return
	 * @
	 */
	public List<String> getCodiceAmmLocale ( String cf){
		
		try{
			
			
			return service.getCodiceAmmLocale(cf);
			
		}catch(Exception e){
			Logg.getLogger(this.getClass()).error("Errore nel metodo getCodiceAmmLocale",e);
			throw new RuntimeException(e);
			
		}		
	}
	
	/**Metodo che dato il cf AmmnistratoreCentraleApplicativo torna tutti i codici applicazione
	 * 
	 * @author Andrea
	 * @param cf
	 * @return List<String> codiciAppliazioni
	 * @
	 */
	public List<String> getAmmCentraleApp(String cf){
		
		try{
			
			
			return service.getAmmCentraleApp(cf);
			
		}catch(Exception e){
			Logg.getLogger(this.getClass()).error("Errore nel metodo getAmmCentraleApp",e);
			throw new RuntimeException(e);
			
		}
		
	}
	
	
	
	/**Metodo per prendere il codiceCDR dell utente in sessione
	 * 
	 * @author Andrea
	 * @param cf
	 * @return codiceCDR
	 * @
	 */
	public String getCDRUser (String cf){
		
		try{
			
			
			OperatoreFinder finder = new OperatoreFinder(cf);
			OperatoreBean datiUtente = service.getDatiUtente2(finder);
			
			return datiUtente.getCdr();
			
		}catch(Exception e){
			
			Logg.getLogger(this.getClass()).error("Errore nel metodo getAmmCentraleApp",e);
			throw new RuntimeException(e);
			
		}
	}
	
	
	public UtenteBean setServiziAndDelegantiUtente(UtenteBean input)  {

		
		UtenteBean newUtente = input;
		HashMap<String, String> serviziTotali = new HashMap<String, String>();
		List<String> serviziRuoli = new ArrayList<String>();
		List<String> listaRuoli = new ArrayList<String>();
		List<String> listaDeleganti = new ArrayList<String>();
		List<String> listaDelegantiAsRic = new ArrayList<String>();
		String[] menuRichieste = {"RICINS","CAMUFF","VISUFF","REVVIS"};
	 
		List<String> listaDelegantiTotali = new ArrayList<String>();
		String listaDelegheAutI="";
		String listaDelegheAutII= "";
		String listaDelegheRich ="";
		 
		
		HashMap<String, HashSet<String>> profiloLst = newUtente.getProfiloLst();

		for (Map.Entry<String, HashSet<String>> entry : profiloLst.entrySet()) {

			Logg.loggSyso(entry.getKey() + "/" + entry.getValue());

			listaRuoli.add(entry.getKey());
			
			if (entry.getKey().equals("AUTORIZZATORE_I")){
				listaDelegheAutI=entry.getValue().toString();
			 
			}
			
			if (entry.getKey().equals("AUTORIZZATORE_II")){
				listaDelegheAutII=entry.getValue().toString();
			}
			
			if (entry.getKey().equals("RICHIEDENTE")){
				listaDelegheRich=entry.getValue().toString();
				listaDelegantiAsRic.addAll(entry.getValue());
			}
			
			if(!listaDelegheRich.isEmpty() ||!listaDelegheAutII.isEmpty() || !listaDelegheAutI.isEmpty() ){
			listaDeleganti.addAll(entry.getValue());
			if(listaDeleganti.contains(","))
			listaDeleganti=Arrays.asList(serviziRuoli.get(0).split(","));
			
			}
			
			
			serviziRuoli = service.getServiziUtente(entry.getKey());
			serviziRuoli = Arrays.asList(serviziRuoli.get(0).split(","));

			Iterator<String> it = serviziRuoli.iterator();

			while (it.hasNext()) {

				String servizio = it.next();
				serviziTotali.put(servizio, entry.getKey() + servizio);

			}
			
		if(!listaDelegheRich.isEmpty() || !listaDelegheAutII.isEmpty() || !listaDelegheAutI.isEmpty() ){	
			Iterator<String> itDel = listaDeleganti.iterator();

			while (itDel.hasNext()) {

				String delegato = itDel.next();
				listaDelegantiTotali.add(delegato);
				 
			}
		}
	 

		}

		newUtente.setDelegheAutI(listaDelegheAutI);
		newUtente.setDelegheAutII(listaDelegheAutII);
		newUtente.setDelegheRich(listaDelegheRich);
		newUtente.setListaDelegantiAsRich(listaDelegantiAsRic);
		newUtente.setListaDeleganti(listaDelegantiTotali);
		newUtente.setListaRuoli(listaRuoli);
		
		for (Map.Entry<String, String> entry : serviziTotali.entrySet()){
 		}
		
		
		if(newUtente.isResponsabileCdr() && !newUtente.isRichiedente()){
			
			CDRFinder finder = new CDRFinder(newUtente.getCodFiscaleUtente());
			List<CDRBean> listaCdr = service.getElencoCDRResp(finder);
			
			if(listaCdr != null && listaCdr.size() == 0){
//			if(true){
				
				Iterator i = serviziTotali.entrySet().iterator();
				
				while(i.hasNext()){
					Map.Entry<String, String> entry = (Map.Entry<String, String>)i.next();
					
					for(String servizio : menuRichieste){
						if(servizio.equalsIgnoreCase(entry.getKey()))
							i.remove();
					}
				}
			}
		}
		
		for (Map.Entry<String, String> entry : serviziTotali.entrySet()){
 		}
		
		newUtente.setListaServizi(serviziTotali);

		return newUtente;
	}

	/**
	 * Trasforma una lista di 'codici' dei ruoli dell'utente, in una lista con
	 * le descrizioni da mostrare a video
	 * 
	 * @param listaRuoli
	 * @return
	 */
	public List<String> setlistRuoliDescr(List<String> listaRuoli) {
		List<String> descr = new ArrayList<String>();

		Iterator<String> i = listaRuoli.iterator();

		while (i.hasNext()) {
			String ruolo = i.next();

			if (ruolo.equals("RICHIEDENTE"))
				descr.add(Ruolo.RICHIEDENTE_DESCR);
			if (ruolo.equals("AUTORIZZATORE_I"))
				descr.add(Ruolo.AUTORIZZATORE_I_DESCR);
			if (ruolo.equals("AUTORIZZATORE_II"))
				descr.add(Ruolo.AUTORIZZATORE_II_DESCR);
			if (ruolo.equals("OPERATORE"))
				descr.add(Ruolo.OPERATORE_DESCR);
			if (ruolo.equals("GESTORE"))
				descr.add(Ruolo.GESTORE_DESCR);
			if (ruolo.equals("AMMINISTRATORE_CENTRALE"))
				descr.add(Ruolo.AMMINISTRATORE_CENTRALE_DESCR);
			if (ruolo.equals("AMMINISTRATORE_CENTRALE_APPLICATIVO"))
				descr.add(Ruolo.AMMINISTRATORE_CENTRALE_APPLICATIVO_DESCR);
			if (ruolo.equals("AMMINISTRATORE_REGIONALE"))
				descr.add(Ruolo.AMMINISTRATORE_REGIONALE_DESCR);
			if (ruolo.equals("AMMINISTRATORE_LOCALE"))
				descr.add(Ruolo.AMMINISTRATORE_LOCALE_DESCR);
			if (ruolo.equals("AUDITOR_CENTRALE_TOTALE"))
				descr.add(Ruolo.AUDITOR_CENTRALE_TOTALE_DESCR);
			if (ruolo.equals("AUDITOR_CENTRALE"))
				descr.add(Ruolo.AUDITOR_CENTRALE_DESCR);
			if (ruolo.equals("AUDITOR_REGIONALE"))
				descr.add(Ruolo.AUDITOR_REGIONALE_DESCR);
			if(ruolo.equals("RESPONSABILE_CDR" ))
				descr.add(Ruolo.RESPONSABILE_CDR_DESCR);
		}
		return descr;
	}
}