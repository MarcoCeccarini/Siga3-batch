package it.finanze.scheduler.bean;

import java.io.Serializable;
//import java.util.Date;
import java.sql.Date;

public class RichiedenteCdR implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cfResponsabileHR;
	private String cfRichiedente;
	private String cfAutorizzatoreIliv;
	private String cfAutorizzatoreIIliv;
	private String motivazioneRevoca;
	private Date dataIniValidita;
	//private String dataFineValidita;
	private Date dataFineValidita;
	private int idAuditInizio;
	private String codiceFiscaleInizio;
	private int idAuditFine;
	private String codiceFiscaleFine;
	private String codiceCdR;
	
	

	public RichiedenteCdR() {
		// TODO Stub di costruttore generato automaticamente
	}



	public String getCfResponsabileHR() {
		return cfResponsabileHR;
	}



	public void setCfResponsabileHR(String cfResponsabileHR) {
		this.cfResponsabileHR = cfResponsabileHR;
	}



	public String getCfRichiedente() {
		return cfRichiedente;
	}



	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}



	public String getCfAutorizzatoreIliv() {
		return cfAutorizzatoreIliv;
	}



	public void setCfAutorizzatoreIliv(String cfAutorizzatoreIliv) {
		this.cfAutorizzatoreIliv = cfAutorizzatoreIliv;
	}



	public String getMotivazioneRevoca() {
		return motivazioneRevoca;
	}



	public void setMotivazioneRevoca(String motivazioneRevoca) {
		this.motivazioneRevoca = motivazioneRevoca;
	}



	public Date getDataIniValidita() {
		return dataIniValidita;
	}



	public void setDataIniValidita(Date dataIniValidita) {
		this.dataIniValidita = dataIniValidita;
	}



	public Date getDataFineValidita() {
		return dataFineValidita;
	}



	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}



	public int getIdAuditInizio() {
		return idAuditInizio;
	}



	public void setIdAuditInizio(int idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}



	public String getCodiceFiscaleInizio() {
		return codiceFiscaleInizio;
	}



	public void setCodiceFiscaleInizio(String codiceFiscaleInizio) {
		this.codiceFiscaleInizio = codiceFiscaleInizio;
	}



	public int getIdAuditFine() {
		return idAuditFine;
	}



	public void setIdAuditFine(int idAuditFine) {
		this.idAuditFine = idAuditFine;
	}



	public String getCodiceFiscaleFine() {
		return codiceFiscaleFine;
	}



	public void setCodiceFiscaleFine(String codiceFiscaleFine) {
		this.codiceFiscaleFine = codiceFiscaleFine;
	}



	public String getCodiceCdR() {
		return codiceCdR;
	}



	public void setCodiceCdR(String codiceCdR) {
		this.codiceCdR = codiceCdR;
	}



	public String getCfAutorizzatoreIIliv() {
		return cfAutorizzatoreIIliv;
	}



	public void setCfAutorizzatoreIIliv(String cfAutorizzatoreIIliv) {
		this.cfAutorizzatoreIIliv = cfAutorizzatoreIIliv;
	}

}
