package it.finanze.siga.finder;

public class InserimentoRichiesteBaseFinder extends BaseFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5812228870656151610L;
	
	/**
	 * Dati operatore
	 */
	private String cf;
	private String codiceCDR;
	private String codiceUfficio;
	private String flag;
	private String cfUtente;
	private String cdrUtente;
	private String origineAbilitazione;
	private String flagRichiestaAut;
	private String flagPresaVisione;
	private String flagEsecutivitaCAU;
	private String nome;
	private String cognome;


	/**
	 * Quando si effettua una richiesta per un utente in visibilità bisogna
	 * andare e inserire anche id della richiesta della visibilità
	 */
	private String idRichiestaVisibilit; 
	
	
	/** GETTERS AND SETTERS **/
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getIdRichiestaVisibilit() {
		return idRichiestaVisibilit;
	}
	public void setIdRichiestaVisibilit(String idRichiestaVisibilit) {
		this.idRichiestaVisibilit = idRichiestaVisibilit;
	}
	public String getCfUtente() {
		return cfUtente;
	}
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}
	public String getCdrUtente() {
		return cdrUtente;
	}
	public void setCdrUtente(String cdrUtente) {
		this.cdrUtente = cdrUtente;
	}
	public String getOrigineAbilitazione() {
		return origineAbilitazione;
	}
	public void setOrigineAbilitazione(String origineAbilitazione) {
		this.origineAbilitazione = origineAbilitazione;
	}
	public String getFlagRichiestaAut() {
		return flagRichiestaAut;
	}
	public void setFlagRichiestaAut(String flagRichiestaAut) {
		this.flagRichiestaAut = flagRichiestaAut;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getFlagPresaVisione() {
		return flagPresaVisione;
	}
	public void setFlagPresaVisione(String flagPresaVisione) {
		this.flagPresaVisione = flagPresaVisione;
	}
	public String getFlagEsecutivitaCAU() {
		return flagEsecutivitaCAU;
	}
	public void setFlagEsecutivitaCAU(String flagEsecutivitaCAU) {
		this.flagEsecutivitaCAU = flagEsecutivitaCAU;
	}
	
	
}
