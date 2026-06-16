package it.finanze.scheduler.bean;

import java.io.Serializable;

public class Deleghe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cfDelegante;
	private String cfDelegato;
	private String codiceCdRDelegante;
	private String codiceCdRDelegato;
	private String ruoloDelegato;
	private String dataScadenza;
	private String motivazioneRevoca;
	private String dataIniValidita;
	private String dataFineValidita;
	private String idAuditInizio;
	private String codiceFiscaleInizio;
	private String idAuditFine;
	private String codiceFiscaleFine;
	
	
	
	

	public Deleghe() {
		// TODO Stub di costruttore generato automaticamente
	}





	public String getCfDelegante() {
		return cfDelegante;
	}





	public void setCfDelegante(String cfDelegante) {
		this.cfDelegante = cfDelegante;
	}





	public String getCfDelegato() {
		return cfDelegato;
	}





	public void setCfDelegato(String cfDelegato) {
		this.cfDelegato = cfDelegato;
	}





	public String getCodiceCdRDelegante() {
		return codiceCdRDelegante;
	}





	public void setCodiceCdRDelegante(String codiceCdRDelegante) {
		this.codiceCdRDelegante = codiceCdRDelegante;
	}





	public String getCodiceCdRDelegato() {
		return codiceCdRDelegato;
	}





	public void setCodiceCdRDelegato(String codiceCdRDelegato) {
		this.codiceCdRDelegato = codiceCdRDelegato;
	}





	public String getRuoloDelegato() {
		return ruoloDelegato;
	}





	public void setRuoloDelegato(String ruoloDelegato) {
		this.ruoloDelegato = ruoloDelegato;
	}





	public String getDataScadenza() {
		return dataScadenza;
	}





	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}





	public String getMotivazioneRevoca() {
		return motivazioneRevoca;
	}





	public void setMotivazioneRevoca(String motivazioneRevoca) {
		this.motivazioneRevoca = motivazioneRevoca;
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





	public void getDataFineValidita(String getDataFineValidita) {
		this.dataFineValidita = getDataFineValidita;
	}





	public String getIdAuditInizio() {
		return idAuditInizio;
	}





	public void setIdAuditInizio(String idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}





	public String getCodiceFiscaleInizio() {
		return codiceFiscaleInizio;
	}





	public void setCodiceFiscaleInizio(String codiceFiscaleInizio) {
		this.codiceFiscaleInizio = codiceFiscaleInizio;
	}





	public String getIdAuditFine() {
		return idAuditFine;
	}





	public void setIdAuditFine(String idAuditFine) {
		this.idAuditFine = idAuditFine;
	}





	public String getCodiceFiscaleFine() {
		return codiceFiscaleFine;
	}





	public void setCodiceFiscaleFine(String codiceFiscaleFine) {
		this.codiceFiscaleFine = codiceFiscaleFine;
	}

}
