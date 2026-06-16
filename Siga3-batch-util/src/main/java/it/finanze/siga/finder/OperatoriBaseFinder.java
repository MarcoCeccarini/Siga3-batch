package it.finanze.siga.finder;

public class OperatoriBaseFinder extends BaseFinder {

	private static final long serialVersionUID = 1L;
	private String cf,cognome,nome,cdr;

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

	/**
	 * @return the cdr
	 */
	public String getCdr() {
		return cdr;
	}

	/**
	 * @param cdr the cdr to set
	 */
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}
}
