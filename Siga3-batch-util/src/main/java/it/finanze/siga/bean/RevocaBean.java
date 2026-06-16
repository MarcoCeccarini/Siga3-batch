package it.finanze.siga.bean;

import java.io.Serializable;
import java.sql.Date;

public class RevocaBean implements Serializable, Cloneable { 

	private static final long serialVersionUID = 1L;
	private String cfUtente;
	private String cfOperatoreInizio;
	private String cfOperatoreFine;
	private String flagRevocaCAU;
	private String flagRevocaPost;
	private String tipoRevoca;

	private Date dataInizioRevoca;
	private Date dataFineRevoca;

	private int idAuditInizio;
	private int idAuditFine;
	private String msgAudit;
	private String tabAudit;

	
	public String getCfUtente() {
		return cfUtente;
	}
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}
	public String getCfOperatoreInizio() {
		return cfOperatoreInizio;
	}
	public void setCfOperatoreInizio(String cfOperatoreInizio) {
		this.cfOperatoreInizio = cfOperatoreInizio;
	}
	public String getCfOperatoreFine() {
		return cfOperatoreFine;
	}
	public void setCfOperatoreFine(String cfOperatoreFine) {
		this.cfOperatoreFine = cfOperatoreFine;
	}
	public String getFlagRevocaCAU() {
		return flagRevocaCAU;
	}
	public void setFlagRevocaCAU(String flagRevocaCAU) {
		this.flagRevocaCAU = flagRevocaCAU;
	}
	public String getFlagRevocaPost() {
		return flagRevocaPost;
	}
	public void setFlagRevocaPost(String flagRevocaPost) {
		this.flagRevocaPost = flagRevocaPost;
	}
	 
	public int getIdAuditInizio() {
		return idAuditInizio;
	}
	public void setIdAuditInizio(int idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}
	public int getIdAuditFine() {
		return idAuditFine;
	}
	public void setIdAuditFine(int idAuditFine) {
		this.idAuditFine = idAuditFine;
	}
	public Date getDataInizioRevoca() {
		return dataInizioRevoca;
	}
	public void setDataInizioRevoca(Date dataInizioRevoca) {
		this.dataInizioRevoca = dataInizioRevoca;
	}
	public Date getDataFineRevoca() {
		return dataFineRevoca;
	}
	public void setDataFineRevoca(Date dataFineRevoca) {
		this.dataFineRevoca = dataFineRevoca;
	}
	public String getTipoRevoca() {
		return tipoRevoca;
	}
	public void setTipoRevoca(String tipoRevoca) {
		this.tipoRevoca = tipoRevoca;
	}
	public String getMsgAudit() {
		return msgAudit;
	}
	public void setMsgAudit(String msgAudit) {
		this.msgAudit = msgAudit;
	}
	public String getTabAudit() {
		return tabAudit;
	}
	public void setTabAudit(String tabAudit) {
		this.tabAudit = tabAudit;
	}
	 
	
}