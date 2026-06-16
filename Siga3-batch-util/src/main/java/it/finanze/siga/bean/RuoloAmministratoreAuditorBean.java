package it.finanze.siga.bean;

import java.io.Serializable;

public class RuoloAmministratoreAuditorBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7742905681720332662L;
	
	private String codiceFiscaleAmmAud;
	private String cognomeAmmAud;
	private String nomeAmmAud;
	private String ruoloAmmAud;
	private String codiceCdrAmmAud;
	private String descrizioneCdrAmmAud;
	
	public String getCodiceFiscaleAmmAud() {
		return codiceFiscaleAmmAud;
	}
	public void setCodiceFiscaleAmmAud(String codiceFiscale) {
		this.codiceFiscaleAmmAud = codiceFiscale;
	}
	public String getCognomeAmmAud() {
		return cognomeAmmAud;
	}
	public void setCognomeAmmAud(String cognome) {
		this.cognomeAmmAud = cognome;
	}
	public String getNomeAmmAud() {
		return nomeAmmAud;
	}
	public void setNomeAmmAud(String nome) {
		this.nomeAmmAud = nome;
	}
	public String getRuoloAmmAud() {
		return ruoloAmmAud;
	}
	public void setRuoloAmmAud(String ruolo) {
		this.ruoloAmmAud = ruolo;
	}
	public String getCodiceCdrAmmAud() {
		return codiceCdrAmmAud;
	}
	public void setCodiceCdrAmmAud(String codiceCdr) {
		this.codiceCdrAmmAud = codiceCdr;
	}
	public String getDescrizioneCdrAmmAud() {
		return descrizioneCdrAmmAud;
	}
	public void setDescrizioneCdrAmmAud(String descrizioneCdr) {
		this.descrizioneCdrAmmAud = descrizioneCdr;
	}
	
	

}
