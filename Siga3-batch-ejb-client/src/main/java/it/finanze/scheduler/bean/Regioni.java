package it.finanze.scheduler.bean;
import java.io.Serializable;

public class Regioni implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String siglaCapoluogoRegione;
	private String regione;
	
	

	public Regioni() {
		// TODO Stub di costruttore generato automaticamente
	}



	public String getSiglaCapoluogoRegione() {
		return siglaCapoluogoRegione;
	}



	public void setSiglaCapoluogoRegione(String siglaCapoluogoRegione) {
		this.siglaCapoluogoRegione = siglaCapoluogoRegione;
	}



	public String getRegione() {
		return regione;
	}



	public void setRegione(String regione) {
		this.regione = regione;
	}

}
