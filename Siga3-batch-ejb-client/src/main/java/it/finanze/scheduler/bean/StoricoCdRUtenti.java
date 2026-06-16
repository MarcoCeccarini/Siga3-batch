package it.finanze.scheduler.bean;

import java.io.Serializable;

public class StoricoCdRUtenti implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codiceFiscale;
	private String codiceCdR;
	private String dataIniValidita;
	private String dataFineValidita;

	public StoricoCdRUtenti() {
		
		// TODO Stub di costruttore generato automaticamente
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceCdR() {
		return codiceCdR;
	}

	public void setCodiceCdR(String codiceCdR) {
		this.codiceCdR = codiceCdR;
	}

	public String getDataIniValidita() {
		return dataIniValidita;
	}

	public void setDataIniValidita(String dataIniValidita) {
		this.dataIniValidita = dataIniValidita;
	}

	public String getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

}
