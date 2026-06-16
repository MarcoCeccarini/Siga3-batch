package it.finanze.siga.finder;

import java.io.Serializable;
import java.util.Date;

public class RichiedenteCDRFinder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cfOperatore;
	private String codiceCDR;
	private String cfResponsabileHR;
	private String cfRichiedente;
	private String cfAutorizzatoreILiv;
	private String motivazioneRevoca;
	private Date dataInizioVal;
	private Date dataFineVal;
	private int IdAuditInizio;
	private String cfInizio;
	private int IdAuditFine;
	private String cfFine;
	
	boolean inserimento;
	
	private String cfRichiedentePrecedente; 
	
	public String getCfOperatore() {
		return cfOperatore;
	}
	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
	}	
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
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
	public String getCfAutorizzatoreILiv() {
		return cfAutorizzatoreILiv;
	}
	public void setCfAutorizzatoreILiv(String cfAutorizzatoreILiv) {
		this.cfAutorizzatoreILiv = cfAutorizzatoreILiv;
	}
	public String getMotivazioneRevoca() {
		return motivazioneRevoca;
	}
	public void setMotivazioneRevoca(String motivazioneRevoca) {
		this.motivazioneRevoca = motivazioneRevoca;
	}
	public Date getDataInizioVal() {
		return dataInizioVal;
	}
	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}
	public Date getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal = dataFineVal;
	}
	public int getIdAuditInizio() {
		return IdAuditInizio;
	}
	public void setIdAuditInizio(int idAuditInizio) {
		IdAuditInizio = idAuditInizio;
	}
	public String getCfInizio() {
		return cfInizio;
	}
	public void setCfInizio(String cfInizio) {
		this.cfInizio = cfInizio;
	}
	public int getIdAuditFine() {
		return IdAuditFine;
	}
	public void setIdAuditFine(int idAuditFine) {
		IdAuditFine = idAuditFine;
	}
	public String getCfFine() {
		return cfFine;
	}
	public void setCfFine(String cfFine) {
		this.cfFine = cfFine;
	}
	public String getCfRichiedentePrecedente() {
		return cfRichiedentePrecedente;
	}
	public void setCfRichiedentePrecedente(String cfRichiedentePrecedente) {
		this.cfRichiedentePrecedente = cfRichiedentePrecedente;
	}
	public boolean isInserimento() {
		return inserimento;
	}
	public void setInserimento(boolean inserimento) {
		this.inserimento = inserimento;
	}
	
	
}
