package it.finanze.scheduler.bean;

import java.io.Serializable;

public class CdR implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codiceCdR;
	private String descrizioneCdR;
	private String tipoCdR;
	private String codiceCdRPadreGerarchia;
	private String regione;
	private String provincia;
	private String codiceCdRVisibile;
	private String ruoliBloccati;
	//private Date dataIniValidita;
	private String dataIniValidita;
	private String dataFineValidita;
	private String cdrEsistenteSuLdap;
	private String tipoCdRHr;

	

	public CdR() {
		// TODO Stub di costruttore generato automaticamente
	}


	public String getCodiceCdR() {
		return codiceCdR;
	}


	public void setCodiceCdR(String codiceCdR) {
		this.codiceCdR = codiceCdR;
	}


	public String getDescrizioneCdR() {
		return descrizioneCdR;
	}


	public void setDescrizioneCdR(String descrizioneCdR) {
		this.descrizioneCdR = descrizioneCdR;
	}


	public String getTipoCdR() {
		return tipoCdR;
	}


	public void setTipoCdR(String tipoCdR) {
		this.tipoCdR = tipoCdR;
	}


	public String getCodiceCdRPadreGerarchia() {
		return codiceCdRPadreGerarchia;
	}


	public void setCodiceCdRPadreGerarchia(String codiceCdRPadreGerarchia) {
		this.codiceCdRPadreGerarchia = codiceCdRPadreGerarchia;
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


	public String getCodiceCdRVisibile() {
		return codiceCdRVisibile;
	}


	public void setCodiceCdRVisibile(String codiceCdRVisibile) {
		this.codiceCdRVisibile = codiceCdRVisibile;
	}


	public String getRuoliBloccati() {
		return ruoliBloccati;
	}


	public void setRuoliBloccati(String ruoliBloccati) {
		this.ruoliBloccati = ruoliBloccati;
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


	public String getCdrEsistenteSuLdap() {
		return cdrEsistenteSuLdap;
	}


	public void setCdrEsistenteSuLdap(String cdrEsistenteSuLdap) {
		this.cdrEsistenteSuLdap = cdrEsistenteSuLdap;
	}


	public String getTipoCdRHr() {
		return tipoCdRHr;
	}


	public void setTipoCdRHr(String tipoCdRHr) {
		this.tipoCdRHr = tipoCdRHr;
	}

/*
	public Date getDataIniValidita() {
		return dataIniValidita;
	}


	public void setDataIniValidita(Date dataIniValidita) {
		this.dataIniValidita = dataIniValidita;
	}
*/

	

}
