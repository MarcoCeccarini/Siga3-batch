package it.finanze.siga.bean;

import java.io.Serializable;

public class AssegnazioneGestoriOperatoriEntity extends CDRBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3943401200562207597L;

	
	private String tipoStruttura;
	private Integer numeroGestori;
	private String centralePeriferico;
	private String assegnazione;
	private String assegnazioneLabel;
	private String codiceCDR;
	private String codiceApplicazione;
	
	
	
	public String getAssegnazioneLabel() {
		return assegnazioneLabel;
	}
	public void setAssegnazioneLabel(String assegnazioneLabel) {
		this.assegnazioneLabel = assegnazioneLabel;
	}
	public String getTipoStruttura() {
		return tipoStruttura;
	}
	public void setTipoStruttura(String tipoStruttura) {
		this.tipoStruttura = tipoStruttura;
	}
	public String getCentralePeriferico() {
		return centralePeriferico;
	}
	public void setCentralePeriferico(String centralePeriferico) {
		this.centralePeriferico = centralePeriferico;
	}
	public Integer getNumeroGestori() {
		return numeroGestori;
	}
	public void setNumeroGestori(Integer numeroGestori) {
		this.numeroGestori = numeroGestori;
	}
	public String getAssegnazione() {
		return assegnazione;
	}
	public void setAssegnazione(String assegnazione) {
		this.assegnazione = assegnazione;
	}
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}
	
	
}
