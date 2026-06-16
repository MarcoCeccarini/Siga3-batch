package it.finanze.siga.bean;

import java.io.Serializable;

public class GetAutorizzatore_I_LivBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4520886517359817448L;
	/**
	 * 
	 */
	
	
	private String nome;
	private String cognome;
	private String codice_fiscale;
	private String codice_cdr;
	
	
	/** GETTERS AND SETTERS **/
	public String getUfficioDestinazione() {
		return ufficioDestinazione;
	}
	public void setUfficioDestinazione(String ufficioDestinazione) {
		this.ufficioDestinazione = ufficioDestinazione;
	}
	private String ufficioDestinazione;
	
	public String getCodice_fiscale() {
		return codice_fiscale;
	}
	public String getCodice_cdr() {
		return codice_cdr;
	}
	public void setCodice_cdr(String codice_cdr) {
		this.codice_cdr = codice_cdr;
	}
	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
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
	
}
