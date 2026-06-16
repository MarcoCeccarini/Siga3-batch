package it.finanze.siga.finder;

public class UtenteSogeiFinder extends BaseFinder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codiceFiscale;
	private String cognome;
	private String nome;
	
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
