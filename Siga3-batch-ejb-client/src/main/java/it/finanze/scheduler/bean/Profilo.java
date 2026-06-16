package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class Profilo implements Serializable{

	private static final long serialVersionUID = 2586701494727025456L;
	private String codiceApplicazione;
	private String codiceProfilo;
	private String descrizione;
	private String explicitEntitlement;
	private Date dataInizioVal;
	private Date dataFineVal;
	private String disponibileVisibilita;
	
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getExplicitEntitlement() {
		return explicitEntitlement;
	}
	public void setExplicitEntitlement(String explicitEntitlement) {
		this.explicitEntitlement = explicitEntitlement;
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
	public String getDisponibileVisibilita() {
		return disponibileVisibilita;
	}
	public void setDisponibileVisibilita(String disponibileVisibilita) {
		this.disponibileVisibilita = disponibileVisibilita;
	}
	
}
