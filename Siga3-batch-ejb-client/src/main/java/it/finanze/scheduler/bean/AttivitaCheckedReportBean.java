package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class AttivitaCheckedReportBean implements Serializable{

	private static final long serialVersionUID = -9012983020746815105L;
	
	private String cfUtente;
	private String codiceCdr;
	private String ruolo;
	private String codiceCdrPadreGerarchia;
	private String codiceCdrVertSt;
	private String codiceCdr2LivGerarchico;
	private String nomeAttivita;
	private Date dataInvioReminder;
	private String checkEffettuato;
	private Date dataCheck;
	private Date dataReport;
	private String nota;
	
	private String descCdr;
	private String descCdrPadreGerarchia;
	private String descCdrVertSt;
	private String descCdr2LivGerarchico;
	private String cognome;
	private String nome;
	
	private String anno;
	private String periodoRif;
	private String periodo;
	private String[] lstCodiceCdr;
	
	
	public String getCfUtente() {
		return cfUtente;
	}
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public String getCodiceCdrPadreGerarchia() {
		return codiceCdrPadreGerarchia;
	}
	public void setCodiceCdrPadreGerarchia(String codiceCdrPadreGerarchia) {
		this.codiceCdrPadreGerarchia = codiceCdrPadreGerarchia;
	}
	public String getCodiceCdrVertSt() {
		return codiceCdrVertSt;
	}
	public void setCodiceCdrVertSt(String codiceCdrVertSt) {
		this.codiceCdrVertSt = codiceCdrVertSt;
	}
	public String getCodiceCdr2LivGerarchico() {
		return codiceCdr2LivGerarchico;
	}
	public void setCodiceCdr2LivGerarchico(String codiceCdr2LivGerarchico) {
		this.codiceCdr2LivGerarchico = codiceCdr2LivGerarchico;
	}
	public String getNomeAttivita() {
		return nomeAttivita;
	}
	public void setNomeAttivita(String nomeAttivita) {
		this.nomeAttivita = nomeAttivita;
	}
	public Date getDataInvioReminder() {
		return dataInvioReminder;
	}
	public void setDataInvioReminder(Date dataInvioReminder) {
		this.dataInvioReminder = dataInvioReminder;
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
	public Date getDataReport() {
		return dataReport;
	}
	public void setDataReport(Date dataReport) {
		this.dataReport = dataReport;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getDescCdr() {
		return descCdr;
	}
	public void setDescCdr(String descCdr) {
		this.descCdr = descCdr;
	}
	public String getDescCdrPadreGerarchia() {
		return descCdrPadreGerarchia;
	}
	public void setDescCdrPadreGerarchia(String descCdrPadreGerarchia) {
		this.descCdrPadreGerarchia = descCdrPadreGerarchia;
	}
	public String getDescCdrVertSt() {
		return descCdrVertSt;
	}
	public void setDescCdrVertSt(String descCdrVertSt) {
		this.descCdrVertSt = descCdrVertSt;
	}
	public String getDescCdr2LivGerarchico() {
		return descCdr2LivGerarchico;
	}
	public void setDescCdr2LivGerarchico(String descCdr2LivGerarchico) {
		this.descCdr2LivGerarchico = descCdr2LivGerarchico;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getPeriodoRif() {
		return periodoRif;
	}
	public void setPeriodoRif(String periodoRif) {
		this.periodoRif = periodoRif;
	}
	public String[] getLstCodiceCdr() {
		return lstCodiceCdr;
	}
	public void setLstCodiceCdr(String[] lstCodiceCdr) {
		this.lstCodiceCdr = lstCodiceCdr;
	}


	
	


}
