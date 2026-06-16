package it.finanze.siga.bean;

public class UtentiEntrateBean {
	private String utente;
	private String richiedente;
	private String autorizzatore;
	private String cdr;		
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public String getRichiedente() {
		return richiedente;
	}
	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}
	public String getAutorizzatore() {
		return autorizzatore;
	}
	public void setAutorizzatore(String autorizzatore) {
		this.autorizzatore = autorizzatore;
	}
	public String getCdr() {
		return cdr;
	}
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}
}
