package it.finanze.siga.bean;

import java.util.Date;

public class InserimentoGestoreBean {

	
	
	String codiceApplicazione;
	String codiceStruttura;
	String codiceCDR;
	String cf;
	Date dataInizioVal;
	Date dataFineVal;
	Integer idAuditInizio;
	String cfInizio;
	Integer idAuditFine;
	String cfFine;
	
	
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}
	public String getCodiceStruttura() {
		return codiceStruttura;
	}
	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
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
	public Integer getIdAuditInizio() {
		return idAuditInizio;
	}
	public void setIdAuditInizio(Integer idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}
	public String getCfInizio() {
		return cfInizio;
	}
	public void setCfInizio(String cfInizio) {
		this.cfInizio = cfInizio;
	}
	public Integer getIdAuditFine() {
		return idAuditFine;
	}
	public void setIdAuditFine(Integer idAuditFine) {
		this.idAuditFine = idAuditFine;
	}
	public String getCfFine() {
		return cfFine;
	}
	public void setCfFine(String cfFine) {
		this.cfFine = cfFine;
	}	
}