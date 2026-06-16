package it.finanze.scheduler.bean;

import java.io.Serializable;

public class RelazioneCdRUfficioStrut implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codiceCdR;
	private String codiceUfficio;
	private String codiceStruttura;
	private String codiceCdRVerticeStruttura;
	private String codiceCdR2LivGerarchico;
	private String codiceCdRVerticeUfficio;	
	private String dataIniValidita;
	private String dataFineValidita;
	private String descrizioneCdR;
	private String descrizioneUfficio;
	

	public RelazioneCdRUfficioStrut() {
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


	public String getCodiceStruttura() {
		return codiceStruttura;
	}


	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
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


	public String getCodiceCdRVerticeStruttura() {
		return codiceCdRVerticeStruttura;
	}


	public void setCodiceCdRVerticeStruttura(String codiceCdRVerticeStruttura) {
		this.codiceCdRVerticeStruttura = codiceCdRVerticeStruttura;
	}


	public String getCodiceCdR2LivGerarchico() {
		return codiceCdR2LivGerarchico;
	}


	public void setCodiceCdR2LivGerarchico(String codiceCdR2LivGerarchico) {
		this.codiceCdR2LivGerarchico = codiceCdR2LivGerarchico;
	}


	public String getCodiceCdRVerticeUfficio() {
		return codiceCdRVerticeUfficio;
	}


	public void setCodiceCdRVerticeUfficio(String codiceCdRVerticeUfficio) {
		this.codiceCdRVerticeUfficio = codiceCdRVerticeUfficio;
	}


	public String getDescrizioneCdR() {
		return descrizioneCdR;
	}


	public void setDescrizioneCdR(String descrizioneCdR) {
		this.descrizioneCdR = descrizioneCdR;
	}


	public String getDescrizioneUfficio() {
		return descrizioneUfficio;
	}


	public void setDescrizioneUfficio(String descrizioneUfficio) {
		this.descrizioneUfficio = descrizioneUfficio;
	}

}
