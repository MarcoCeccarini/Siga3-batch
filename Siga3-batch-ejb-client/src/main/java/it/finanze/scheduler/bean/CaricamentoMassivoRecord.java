package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class CaricamentoMassivoRecord implements Serializable{


	private static final long serialVersionUID = 3665440500056462920L;
	public String codiceFiscale;
	public String codiceRoleGroup;
	public String codiceProfilo;
	public String codiceCdr;
	public String codiceUfficio;
	public String azione;
	public String codiceCdrVis;
	
	// Attributi per Caricamenti Massivi Richieste di Visibilita
	public String dataScadenza;
	public String dataFineVal;

	
	// Attributi per Caricamenti Massivi Mappatura Abilitazioni Uffici
	public String tipologiaUfficio;
	public String ambitoRegionale;
	public String ambitoProvinciale;
	public String codAbilitazione;
	
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public String getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public String getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(String dataFineVal) {
		this.dataFineVal = dataFineVal;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getCodiceCdrVis() {
		return codiceCdrVis;
	}
	public void setCodiceCdrVis(String codiceCdrVis) {
		this.codiceCdrVis = codiceCdrVis;
	}
	public String getTipologiaUfficio() {
		return tipologiaUfficio;
	}
	public void setTipologiaUfficio(String tipologiaUfficio) {
		this.tipologiaUfficio = tipologiaUfficio;
	}
	public String getAmbitoRegionale() {
		return ambitoRegionale;
	}
	public void setAmbitoRegionale(String ambitoRegionale) {
		this.ambitoRegionale = ambitoRegionale;
	}
	public String getAmbitoProvinciale() {
		return ambitoProvinciale;
	}
	public void setAmbitoProvinciale(String ambitoProvinciale) {
		this.ambitoProvinciale = ambitoProvinciale;
	}
	public String getCodAbilitazione() {
		return codAbilitazione;
	}
	public void setCodAbilitazione(String codAbilitazione) {
		this.codAbilitazione = codAbilitazione;
	}
	
}
