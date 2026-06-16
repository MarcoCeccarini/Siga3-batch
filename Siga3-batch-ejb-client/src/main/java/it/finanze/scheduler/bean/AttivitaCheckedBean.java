package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class AttivitaCheckedBean implements Serializable{

	private static final long serialVersionUID = -9012983020746815105L;
	
	private String cfUtente;
	private Date dataInvioReminder;
	private Date dataReport;
	private String checkEffettuato;
	private String nota;
	
	private Date dataCheck;
	private String nomeAttivita;
	
	private String anno;
	private String periodoRif;
	private Date dataScadenza;
	
	
	
	public String getCfUtente() {
		return cfUtente;
	}
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}
	public Date getDataInvioReminder() {
		return dataInvioReminder;
	}
	public void setDataInvioReminder(Date dataInvioReminder) {
		this.dataInvioReminder = dataInvioReminder;
	}
	public Date getDataReport() {
		return dataReport;
	}
	public void setDataReport(Date dataReport) {
		this.dataReport = dataReport;
	}
	public String getCheckEffettuato() {
		return checkEffettuato;
	}
	public void setCheckEffettuato(String checkEffettuato) {
		this.checkEffettuato = checkEffettuato;
	}
	public Date getDataCheck() {
		return dataCheck;
	}
	public void setDataCheck(Date dataCheck) {
		this.dataCheck = dataCheck;
	}
	public String getNomeAttivita() {
		return nomeAttivita;
	}
	public void setNomeAttivita(String nomeAttivita) {
		this.nomeAttivita = nomeAttivita;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public String getPeriodoRif() {
		return periodoRif;
	}
	public void setPeriodoRif(String periodoRif) {
		this.periodoRif = periodoRif;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	
	


}
