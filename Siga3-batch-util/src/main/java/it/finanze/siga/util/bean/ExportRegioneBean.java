package it.finanze.siga.util.bean;

import java.io.Serializable;

public class ExportRegioneBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2749834189236660818L;
	
	private String siglaRegione;
	private String regione;
	
	
	public String getSiglaRegione() {
		return siglaRegione;
	}
	public void setSiglaRegione(String siglaRegione) {
		this.siglaRegione = siglaRegione;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	
	

}
