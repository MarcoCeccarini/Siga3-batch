package it.finanze.scheduler.bean;

import java.io.Serializable;

public class RichiedenteAutorizzatoreORABean implements Serializable{


	private static final long serialVersionUID = -4292570263601500237L;
	private String tipoRichiedente;
	private String tipoAutorizzatore;
	private String tipoResponsabile;
	private String cfRichiedente;
	private String cfAutorizzatore;
	private String cfResponsabile;
	private String cognome;
	private String nome;
	private String email;
	private String descrizione;
	
	public String getTipoRichiedente() {
		return tipoRichiedente;
	}
	public void setTipoRichiedente(String tipoRichiedente) {
		this.tipoRichiedente = tipoRichiedente;
	}
	public String getCfRichiedente() {
		return cfRichiedente;
	}
	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}
	public String getCfAutorizzatore() {
		return cfAutorizzatore;
	}
	public void setCfAutorizzatore(String cfAutorizzatore) {
		this.cfAutorizzatore = cfAutorizzatore;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTipoAutorizzatore() {
		return tipoAutorizzatore;
	}
	public void setTipoAutorizzatore(String tipoAutorizzatore) {
		this.tipoAutorizzatore = tipoAutorizzatore;
	}
	public String getTipoResponsabile() {
		return tipoResponsabile;
	}
	public void setTipoResponsabile(String tipoResponsabile) {
		this.tipoResponsabile = tipoResponsabile;
	}
	public String getCfResponsabile() {
		return cfResponsabile;
	}
	public void setCfResponsabile(String cfResponsabile) {
		this.cfResponsabile = cfResponsabile;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
}
