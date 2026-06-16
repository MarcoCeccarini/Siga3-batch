package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;

public class OperatoreEsternoTipoUtenteBean implements Serializable {

	private static final long serialVersionUID = 5109374566041183411L;
	private String cfOperatoreEsterno;
	private String codiceTipologiaUtente;
	private Date dataInizioValOperatoreEsterno;
	private Date dataInizioValTipologiaUtente;
	private Date dataInizioVal;
	private Date dataFineVal;
	private String cdrDesc;
	private String nome;
	private String cognome;
	private String tipologiaDesc;
	private Integer idAuditInizio;
	private Integer idAuditFine;
	
	public String getCfOperatoreEsterno() {
		return cfOperatoreEsterno;
	}
	public void setCfOperatoreEsterno(String cfOperatoreEsterno) {
		this.cfOperatoreEsterno = cfOperatoreEsterno;
	}
	public String getCodiceTipologiaUtente() {
		return codiceTipologiaUtente;
	}
	public void setCodiceTipologiaUtente(String codiceTipologiaUtente) {
		this.codiceTipologiaUtente = codiceTipologiaUtente;
	}
	public Date getDataInizioValOperatoreEsterno() {
		return dataInizioValOperatoreEsterno;
	}
	public void setDataInizioValOperatoreEsterno(Date dataInizioValOperatoreEsterno) {
		this.dataInizioValOperatoreEsterno = dataInizioValOperatoreEsterno;
	}
	public Date getDataInizioValTipologiaUtente() {
		return dataInizioValTipologiaUtente;
	}
	public void setDataInizioValTipologiaUtente(Date dataInizioValTipologiaUtente) {
		this.dataInizioValTipologiaUtente = dataInizioValTipologiaUtente;
	}
	public Date getDataInizioVal() {
		return dataInizioVal;
	}
	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}
	public Integer getIdAuditInizio() {
		return idAuditInizio;
	}
	public void setIdAuditInizio(Integer idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}
	public Integer getIdAuditFine() {
		return idAuditFine;
	}
	public void setIdAuditFine(Integer idAuditFine) {
		this.idAuditFine = idAuditFine;
	}
	public Date getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal = dataFineVal;
	}
	public String getCdrDesc() {
		return cdrDesc;
	}
	public void setCdrDesc(String cdrDesc) {
		this.cdrDesc = cdrDesc;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getTipologiaDesc() {
		return tipologiaDesc;
	}
	public void setTipologiaDesc(String tipologiaDesc) {
		this.tipologiaDesc = tipologiaDesc;
	}

	


}
