package it.finanze.siga.finder;

public class UtenteFinder extends BaseFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -917185527664381822L;
	
	private String cf;
	private String cognome;
	private String nome;
	private String controllaValiditaUtente;
	private String codiceProfilo;

	
	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getControllaValiditaUtente() {
		return controllaValiditaUtente;
	}

	public void setControllaValiditaUtente(String controllaValiditaUtente) {
		this.controllaValiditaUtente = controllaValiditaUtente;
	}

	public String getCodiceProfilo() {
		return codiceProfilo;
	}

	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}

}
