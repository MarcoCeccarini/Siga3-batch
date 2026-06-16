package it.finanze.scheduler.bean;

import java.io.Serializable;

public class Ufficio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codiceCdR;
	private String codiceUfficio;
	private String tipoUfficio;
	private String regione;
	private String provincia;
	private String dataIniValidita;
	private String dataFineValidita;
	

	public Ufficio() {
		// TODO Stub di costruttore generato automaticamente
	}


	public String getCodiceCdR() {
		return codiceCdR;
	}


	public void setCodiceCdR(String codiceCdR) {
		this.codiceCdR = codiceCdR;
	}


	public String getCodiceUfficio() {
		return codiceUfficio;
	}


	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}


	public String getTipoUfficio() {
		return tipoUfficio;
	}


	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}


	public String getRegione() {
		return regione;
	}


	public void setRegione(String regione) {
		this.regione = regione;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
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
