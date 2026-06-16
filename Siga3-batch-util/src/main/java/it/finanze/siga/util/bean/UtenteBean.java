package it.finanze.siga.util.bean;

import it.finanze.siga.util.Constants;
import it.finanze.siga.util.Ruolo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class UtenteBean implements Serializable {

	private final static List<String> listaCFAbilitazioniCATAltriUffici=Arrays.asList("BRTMRT77C66D086R",
			"MPRLRD63A14G902U","CPZMSM57M28H501C","NGRLNA64M16A745W","CTRGPP64L10L840M",
			"GZZGCH55B14C421S","CVDLGU58S69L157Q","PRENRT57L44L049N","CSGCRM71M58L304R",
			"PCLMNL67T41F965H","GRPRND55B51G872F","MRNRNG64A52F965R","TCCRRT60E22F839O");
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2204976684970246007L;
	
	private String codFiscaleUtente;
	/**
	 * uso questa dicitura per non confonderlo col codiceCDR dell'operatore
	 * che viene identificato proprio con codiceCDR, quando sono passati insieme.
	 */
	private boolean cfAbilitato = false;
	private String codiceCDRUser;
	private String nome;
	private String cognome;
	private String codiceAgenzia;
	private String codUfficio;
	private String password;
	private String username;
	private String mail;
	private HashMap<String, String> funzioni;
	private HashMap<String, HashSet<String>> profiloLst = new HashMap<String, HashSet<String>>();
	
	private HashMap<String, HashSet<String>> customProfile = new HashMap<String, HashSet<String>>();
//	private boolean titolareRuolo;
	private HashMap listaServizi = new HashMap();
	private HashMap listaServiziRichieste = new HashMap();
	private List <String> listaRuoli;
	private String delegheAutI;
	private String delegheAutII ;
	private String delegheRich ;
	private List<String> listaDeleganti ;
	private List<String> listaDelegantiAsRich ;
	
	private String tipoUtente;
	private String tipoUtenteStr;

	private String cdrAssDesc;
	private String cdrVisDesc;

	// Revoche
	private String revocaPostazione;
	private String revocaCau;

	
	// Getters dei ruoli
	public boolean isAmministratoreCentrale() {
			return profiloLst.containsKey(Ruolo.AMMINISTRATORE_CENTRALE);
	}
	
	public boolean isRichiedente(){
		return profiloLst.containsKey(Ruolo.RICHIEDENTE);
	}
	public boolean isResponsabileCdr(){
		return profiloLst.containsKey(Ruolo.RESPONSABILE_CDR);
	 
	}
	
	public boolean isAutorizzatoreI(){
		return profiloLst.containsKey(Ruolo.AUTORIZZATORE_I);
	}
	
	public boolean isAutorizzatoreII(){
		return profiloLst.containsKey(Ruolo.AUTORIZZATORE_II);
	}
	
	public boolean isOperatore(){
		return profiloLst.containsKey(Ruolo.OPERATORE);
	}
	
	public boolean isGestoreOp(){
		return profiloLst.containsKey(Ruolo.GESTORE);
	}
	
	public boolean isCfAbilitato() {
		return cfAbilitato;
	}

	public void setCfAbilitato(boolean cfAbilitato) {
		this.cfAbilitato = cfAbilitato;
	}

	public boolean isAmministratoreApplicativo(){
		
		return profiloLst.containsKey(Ruolo.AMMINISTRATORE_CENTRALE_APPLICATIVO);
	}
	
	public boolean isAmministratoreRegionale(){
		
		return profiloLst.containsKey(Ruolo.AMMINISTRATORE_REGIONALE);
	}
	
	public boolean isAmministratoreLocale(){
		
		return profiloLst.containsKey(Ruolo.AMMINISTRATORE_LOCALE);
	}
	
	public boolean isAuditorTotale(){
		
		return profiloLst.containsKey(Ruolo.AUDITOR_CENTRALE_TOTALE);
	}
	
	public boolean isAuditorCentrale(){
		
		return profiloLst.containsKey(Ruolo.AUDITOR_CENTRALE);
	}
	
	public boolean isAuditorRegionale(){
		
		return profiloLst.containsKey(Ruolo.AUDITOR_REGIONALE);
	}
	
	public boolean isAuditor(){
		if(isAuditorCentrale() || isAuditorRegionale() || isAuditorTotale())
			return true;
		return false;
	}
	
	public boolean isAmministratore(){
		
		if(isAmministratoreCentrale() || isAmministratoreRegionale() || isAmministratoreLocale()){
			
			return true;
			
		}else return false;
		
	}
	
	public boolean isOnlyAutorizzatore(){
		
		if((!isAmministratore() && !isAuditor() && !isRichiedente() && !isResponsabileCdr() && !isOperatore() && !isGestoreOp() && !isAmministratoreApplicativo()) 
				&& (isAutorizzatoreI() || isAutorizzatoreII())){
			
			return true;
			
		}else return false;
		
	}
	
	public boolean isOnlyCentrale(){
		
		if(isAmministratoreCentrale() && !isAmministratoreRegionale() && !isAmministratoreLocale() && !isAuditor() && !isRichiedente() && !isResponsabileCdr() && !isOperatore() && !isGestoreOp() && !isAmministratoreApplicativo() && !isAutorizzatoreI() && !isAutorizzatoreII() ){
			
			return true;
			
		}else return false;
		
	}
	
//	tale metodoa usato sulla funzionalita Abilitazioni disponibili nei cdr; 
//	i ruoli di gestore, autorizzatore etc. non sono rivelanti
public boolean isOnlyRichiedenteResponsabile(){
		
		if((!isAmministratore() && !isAuditor()
				&& !isAmministratoreApplicativo()) 
				&& (isResponsabileCdr() || isRichiedente())){
			
			return true;
			
		}else return false;
		
	}
	
	public boolean isOnlyOperatore(){
		
		if((!isAmministratore() && !isAuditor() && !isRichiedente() && !isResponsabileCdr() && isOperatore() && !isGestoreOp() && !isAmministratoreApplicativo()) 
				&& (!isAutorizzatoreI() || !isAutorizzatoreII())){
			
			return true;
			
		}else return false;
		
	}
 
 
	
	//Fine ricerca dei ruoli
	
	
	
	public String getCodiceCDRUser() {
		return codiceCDRUser;
	}
	
	
	public void setCodiceCDRUser(String codiceCDR) {
		this.codiceCDRUser = codiceCDR;
	}

	public String getCodFiscaleUtente() {
		return codFiscaleUtente;
	}
	public void setCodFiscaleUtente(String codFiscaleUtente) {
		this.codFiscaleUtente = codFiscaleUtente;
	}
	public String getCodiceAgenzia() {
		return codiceAgenzia;
	}
	public void setCodiceAgenzia(String codiceAgenzia) {
		this.codiceAgenzia = codiceAgenzia;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCodUfficio() {
		return codUfficio;
	}
	public void setCodUfficio(String codUfficio) {
		this.codUfficio = codUfficio;
	}
	public void setFunzioni(HashMap<String, String> funzioni) {
		this.funzioni = funzioni;
	}
	public HashMap<String, String> getFunzioni() {
		return funzioni;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public HashMap<String, HashSet<String>> getProfiloLst() {
		return profiloLst;
	}
	public void setProfiloLst(HashMap<String, HashSet<String>> profiloLst) {
		this.profiloLst = profiloLst;
	}
 
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}


//	public boolean isTitolareRuolo() {
//		return titolareRuolo;
//	}
//
//
//	public void setTitolareRuolo(boolean titolareRuolo) {
//		this.titolareRuolo = titolareRuolo;
//	}


	public HashMap<String, String> getListaServizi() {
		return listaServizi;
	}


	public void setListaServizi(HashMap<String, String> listaServizi) {
		this.listaServizi = listaServizi;
	}
	
	
	public List<String> getListaRuoli() {
		return listaRuoli;
	}


	public void setListaRuoli(List<String> listaRuoli) {
		this.listaRuoli = listaRuoli;
	}


	public List<String> getListaDeleganti() {
		return listaDeleganti;
	}

	public void setListaDeleganti(List<String> listaDeleganti) {
		this.listaDeleganti = listaDeleganti;
	}

	public String getDelegheAutI() {
		return delegheAutI;
	}

	public void setDelegheAutI(String delegheAutI) {
		this.delegheAutI = delegheAutI;
	}

	public String getDelegheAutII() {
		return delegheAutII;
	}

	public void setDelegheAutII(String delegheAutII) {
		this.delegheAutII = delegheAutII;
	}

	public String getDelegheRich() {
		return delegheRich;
	}

	public void setDelegheRich(String delegheRich) {
		this.delegheRich = delegheRich;
	}

	public HashMap<String, HashSet<String>> getCustomProfile() {
		return customProfile;
	}

	public void setCustomProfile(HashMap<String, HashSet<String>> customProfile) {
		this.customProfile = customProfile;
	}


	public String getCdrAssDesc() {
		return cdrAssDesc;
	}

	public void setCdrAssDesc(String cdrAssDesc) {
		this.cdrAssDesc = cdrAssDesc;
	}

	public String getCdrVisDesc() {
		return cdrVisDesc;
	}

	public void setCdrVisDesc(String cdrVisDesc) {
		this.cdrVisDesc = cdrVisDesc;
	}
	public String getRevocaPostazione() {
		return revocaPostazione;
	}

	public void setRevocaPostazione(String revocaPostazione) {
		this.revocaPostazione = revocaPostazione;
	}

	public String getRevocaCau() {
		return revocaCau;
	}

	public void setRevocaCau(String revocaCau) {
		this.revocaCau = revocaCau;
	}


	public List<String> getListaDelegantiAsRich() {
		return listaDelegantiAsRich;
	}

	public void setListaDelegantiAsRich(List<String> listaDelegantiAsRich) {
		this.listaDelegantiAsRich = listaDelegantiAsRich;
	}
	public String getTipoUtente() {
		return tipoUtente;
	}

	public void setTipoUtente(String tipoUtente) {
		this.tipoUtente = tipoUtente;
	}

	public String getTipoUtenteStr() {
		if(tipoUtente.equals(Constants.TIPO_UTENTE_ESTERNO))
			return Constants.ESTERNO;
		else
			return Constants.INTERNO;
	}
	
	public boolean isAbilitatoCATAltriUffici() {		
		boolean ris = true; //tutti abilitati
		return ris;
	}
	
	private String cdrPertinenza;
	
	public String getCdrPertinenza() {
		return cdrPertinenza;
	}
	public void setCrPertinenza(String cdrPertinenza) {
		this.cdrPertinenza = cdrPertinenza;
	}
	
	private String cdrDescrizione;
	
	public String getCdrDescrizione() {
		return cdrDescrizione;
	}
	public void setCdrDescrizione(String cdrDescrizione) {
		this.cdrDescrizione = cdrDescrizione;
	}
	
	private String cdrPadreGerarchia;
	
	public String getCdrPadreGerarchia() {
		return cdrPadreGerarchia;
	}
	public void setCdrPadreGerarchia(String cdrPadreGerarchia) {
		this.cdrPadreGerarchia = cdrPadreGerarchia;
	}
	
	
	
}
