package it.finanze.scheduler.bean;

import java.io.Serializable;

public class Utenti implements Serializable {

	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;
	private String codiceFiscale;
	private String dataIniValidita;
	private String dataFineValidita;
	private String cognome;
	private String nome;
	private String codiceCdR;
	private String tipoUtente;
	private String richiedente;
	private String autorizzatoreIliv;
	private String autorizzatoreIIliv;
	private String eMail;
	private String descrizioneCdR;
	private String flagUtenteCdrDisallienato;

	

	public Utenti() {
		// TODO Stub di costruttore generato automaticamente
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDataIniValidita() {
		return dataIniValidita;
	}

	public void setDataIniValidita(String dataIniValidita) {
		this.dataIniValidita = dataIniValidita;
	}

	public String getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(String dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
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

	public String getCodiceCdR() {
		return codiceCdR;
	}

	public void setCodiceCdR(String codiceCdR) {
		this.codiceCdR = codiceCdR;
	}

	public String getTipoUtente() {
		return tipoUtente;
	}

	public void setTipoUtente(String tipoUtente) {
		this.tipoUtente = tipoUtente;
	}

	public String getRichiedente() {
		return richiedente;
	}

	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}

	public String getAutorizzatoreIliv() {
		return autorizzatoreIliv;
	}

	public void setAutorizzatoreIliv(String autorizzatoreIliv) {
		this.autorizzatoreIliv = autorizzatoreIliv;
	}

	public String getAutorizzatoreIIliv() {
		return autorizzatoreIIliv;
	}

	public void setAutorizzatoreIIliv(String autorizzatoreIIliv) {
		this.autorizzatoreIIliv = autorizzatoreIIliv;
	}

	public String geteMail() {
		return this.eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getDescrizioneCdR() {
		return descrizioneCdR;
	}

	public void setDescrizioneCdR(String descrizioneCdR) {
		this.descrizioneCdR = descrizioneCdR;
	}

	public String getFlagUtenteCdrDisallienato() {
		return flagUtenteCdrDisallienato;
	}

	public void setFlagUtenteCdrDisallienato(String flagUtenteCdrDisallienato) {
		this.flagUtenteCdrDisallienato = flagUtenteCdrDisallienato;
	}
	
}
