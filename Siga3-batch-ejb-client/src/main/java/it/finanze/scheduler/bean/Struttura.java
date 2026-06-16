package it.finanze.scheduler.bean;

import java.io.Serializable;


public class Struttura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codiceCdR;
	private String codiceStruttura;
	private String tipoStrutura;
	private String codiceCdRIILivGerarchico;
	private String regione;
	private String provincia;
	private String dataIniValidita;
	private String dataFineValidita;
	private String centralePeriferico;
	
	
	

	public Struttura() {
		// TODO Stub di costruttore generato automaticamente
	}




	public String getCodiceCdR() {
		return codiceCdR;
	}




	public void setCodiceCdR(String codiceCdR) {
		this.codiceCdR = codiceCdR;
	}
 


	public String getTipoStrutura() {
		return tipoStrutura;
	}




	public void setTipoStrutura(String tipoStrutura) {
		this.tipoStrutura = tipoStrutura;
	}




	public String getCodiceCdRIILivGerarchico() {
		return codiceCdRIILivGerarchico;
	}




	public void setCodiceCdRIILivGerarchico(String codiceCdRIILivGerarchico) {
		this.codiceCdRIILivGerarchico = codiceCdRIILivGerarchico;
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




	public String getCodiceStruttura() {
		return codiceStruttura;
	}




	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}




	public String getDataFineValidita() {
		return dataFineValidita;
	}




	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}




	public String getCentralePeriferico() {
		return centralePeriferico;
	}




	public void setCentralePeriferico(String centralePeriferico) {
		this.centralePeriferico = centralePeriferico;
	}

 

}