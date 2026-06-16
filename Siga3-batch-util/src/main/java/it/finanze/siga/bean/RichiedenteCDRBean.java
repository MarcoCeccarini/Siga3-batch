package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;


public class RichiedenteCDRBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3819434300755868984L;
	
	private String codiceCDR;
	private String cdrDesc;
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
	
	/**
	 *campi utili per le vista  
	 */
	private String nomeResponsabile;
	private String cognomeResponsabile;
	private String nomeRichiedente;
	private String cognomeRichiedente;
	private String nomeAutorizzatoreILiv;
	private String cognomeAutorizzatoreILiv;
	private String nomeAmministratoreInizio;
	private String cognomeAmministratoreInizio;
	private String nomeAmministratoreFine;
	private String cognomeAmministratoreFine;
	private String cdrRichiedente;
	private String cdrAutorizzatore;
	
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
	public String getNomeResponsabile() {
		return nomeResponsabile;
	}
	public void setNomeResponsabile(String nomeResponsabile) {
		this.nomeResponsabile = nomeResponsabile;
	}
	public String getCognomeResponsabile() {
		return cognomeResponsabile;
	}
	public void setCognomeResponsabile(String cognomeResponsabile) {
		this.cognomeResponsabile = cognomeResponsabile;
	}
	public String getNomeRichiedente() {
		return nomeRichiedente;
	}
	public void setNomeRichiedente(String nomeRichiedente) {
		this.nomeRichiedente = nomeRichiedente;
	}
	public String getCognomeRichiedente() {
		return cognomeRichiedente;
	}
	public void setCognomeRichiedente(String cognomeRichiedente) {
		this.cognomeRichiedente = cognomeRichiedente;
	}
	public String getNomeAutorizzatoreILiv() {
		return nomeAutorizzatoreILiv;
	}
	public void setNomeAutorizzatoreILiv(String nomeAutorizzatoreILiv) {
		this.nomeAutorizzatoreILiv = nomeAutorizzatoreILiv;
	}
	public String getCognomeAutorizzatoreILiv() {
		return cognomeAutorizzatoreILiv;
	}
	public void setCognomeAutorizzatoreILiv(String cognomeAutorizzatoreILiv) {
		this.cognomeAutorizzatoreILiv = cognomeAutorizzatoreILiv;
	}
	public String getNomeAmministratoreInizio() {
		return nomeAmministratoreInizio;
	}
	public void setNomeAmministratoreInizio(String nomeAmministratoreInizio) {
		this.nomeAmministratoreInizio = nomeAmministratoreInizio;
	}
	public String getCognomeAmministratoreInizio() {
		return cognomeAmministratoreInizio;
	}
	public void setCognomeAmministratoreInizio(String cognomeAmministratoreInizio) {
		this.cognomeAmministratoreInizio = cognomeAmministratoreInizio;
	}
	public String getNomeAmministratoreFine() {
		return nomeAmministratoreFine;
	}
	public void setNomeAmministratoreFine(String nomeAmministratoreFine) {
		this.nomeAmministratoreFine = nomeAmministratoreFine;
	}
	public String getCognomeAmministratoreFine() {
		return cognomeAmministratoreFine;
	}
	public void setCognomeAmministratoreFine(String cognomeAmministratoreFine) {
		this.cognomeAmministratoreFine = cognomeAmministratoreFine;
	}
	public String getCdrDesc() {
		return cdrDesc;
	}
	public void setCdrDesc(String cdrDesc) {
		this.cdrDesc = cdrDesc;
	}
	public String getCdrRichiedente() {
		return cdrRichiedente;
	}
	public void setCdrRichiedente(String cdrRichiedente) {
		this.cdrRichiedente = cdrRichiedente;
	}
	public String getCdrAutorizzatore() {
		return cdrAutorizzatore;
	}
	public void setCdrAutorizzatore(String cdrAutorizzatore) {
		this.cdrAutorizzatore = cdrAutorizzatore;
	}
	
	
}
