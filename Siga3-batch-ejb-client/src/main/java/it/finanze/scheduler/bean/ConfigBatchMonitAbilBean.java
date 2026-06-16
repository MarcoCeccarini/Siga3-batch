package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class ConfigBatchMonitAbilBean implements Serializable{

	private static final long serialVersionUID = -9012983020746815105L;
	
	private String frequenza;
	private int antInvioReminder;
	private int ritInvioReport;
	private Date dataInizioVal;
	private Date dataFineVal;
	
	
	// campi di servizio
	private String cadenza;
	private String numPeriodo;
	private String periodo;
	private String intervallo;
	private String scadenza;
	
	
	public String getFrequenza() {
		return frequenza;
	}
	public void setFrequenza(String frequenza) {
		this.frequenza = frequenza;
	}
	public int getAntInvioReminder() {
		return antInvioReminder;
	}
	public void setAntInvioReminder(int antInvioReminder) {
		this.antInvioReminder = antInvioReminder;
	}
	public int getRitInvioReport() {
		return ritInvioReport;
	}
	public void setRitInvioReport(int ritInvioReport) {
		this.ritInvioReport = ritInvioReport;
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
	public String getNumPeriodo() {
		return numPeriodo;
	}
	public void setNumPeriodo(String numPeriodo) {
		this.numPeriodo = numPeriodo;
	}
	public String getCadenza() {
		return cadenza;
	}
	public void setCadenza(String cadenza) {
		this.cadenza = cadenza;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getIntervallo() {
		return intervallo;
	}
	public void setIntervallo(String intervallo) {
		this.intervallo = intervallo;
	}
	public String getScadenza() {
		return scadenza;
	}
	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}
	
	
	
}
