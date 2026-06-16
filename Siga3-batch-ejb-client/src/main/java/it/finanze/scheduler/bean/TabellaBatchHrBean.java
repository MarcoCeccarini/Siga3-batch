package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class TabellaBatchHrBean implements Serializable{
	

	private static final long serialVersionUID = 3169112529182360081L;
	private String emailSegnalazioniSogei;
	private Date dataInizioUtenti;
	private Date dataFineUtenti;
	private Date dataInizioResponsabiliCdr;
	private Date dataFineResponsabiliCdr;
	private Date dataInizioEmailRuoli;
	private Date dataFineEmailRuoli;
	
	
	public String getEmailSegnalazioniSogei() {
		return emailSegnalazioniSogei;
	}
	public void setEmailSegnalazioniSogei(String emailSegnalazioniSogei) {
		this.emailSegnalazioniSogei = emailSegnalazioniSogei;
	}
	public Date getDataFineUtenti() {
		return dataFineUtenti;
	}
	public void setDataFineUtenti(Date dataFineUtenti) {
		this.dataFineUtenti = dataFineUtenti;
	}
	public Date getDataInizioResponsabiliCdr() {
		return dataInizioResponsabiliCdr;
	}
	public void setDataInizioResponsabiliCdr(Date dataInizioResponsabiliCdr) {
		this.dataInizioResponsabiliCdr = dataInizioResponsabiliCdr;
	}
	public Date getDataInizioEmailRuoli() {
		return dataInizioEmailRuoli;
	}
	public void setDataInizioEmailRuoli(Date dataInizioEmailRuoli) {
		this.dataInizioEmailRuoli = dataInizioEmailRuoli;
	}
	public Date getDataInizioUtenti() {
		return dataInizioUtenti;
	}
	public void setDataInizioUtenti(Date dataInizioUtenti) {
		this.dataInizioUtenti = dataInizioUtenti;
	}
	public Date getDataFineResponsabiliCdr() {
		return dataFineResponsabiliCdr;
	}
	public void setDataFineResponsabiliCdr(Date dataFineResponsabiliCdr) {
		this.dataFineResponsabiliCdr = dataFineResponsabiliCdr;
	}
	public Date getDataFineEmailRuoli() {
		return dataFineEmailRuoli;
	}
	public void setDataFineEmailRuoli(Date dataFineEmailRuoli) {
		this.dataFineEmailRuoli = dataFineEmailRuoli;
	}
}
