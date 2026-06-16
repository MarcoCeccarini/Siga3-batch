package it.finanze.scheduler.bean;

import java.io.Serializable;

public class MappaturaAbilitazioniUfficioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3153008723132383483L;

	private String tipologiaUfficio;
	private String ambitoRegionale;
	private String ambitoProvinciale;
	private String codiceUfficio;
	private String codiceCdr;
	private String codiceRoleGroup;
	private String codAbilitazione;
	private String azione;
	
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
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getCodAbilitazione() {
		return codAbilitazione;
	}
	public void setCodAbilitazione(String codAbilitazione) {
		this.codAbilitazione = codAbilitazione;
	}
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
}
