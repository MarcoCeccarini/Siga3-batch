package it.finanze.scheduler.bean;

import java.io.Serializable;

public class NotificaOperatoriDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String codiceFiscaleUtente;
	private String cognome;
	private String nome;
	private String codiceStruttura;
	private String oggettoEmail;
	private String emailAmministratore;
	private String codice_cdr_ii_liv_gerarchico;
	private String centrale_periferico;
	private String cdr;
	private String descr_cdr;
	
	
	
	public NotificaOperatoriDAO() {
		// TODO Stub di costruttore generato automaticamente
	}


	public String getCodiceFiscaleUtente() {
		return codiceFiscaleUtente;
	}


	public void setCodiceFiscaleUtente(String codiceFiscaleUtente) {
		this.codiceFiscaleUtente = codiceFiscaleUtente;
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


	public String getCodiceStruttura() {
		return codiceStruttura;
	}


	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}


	public String getOggettoEmail() {
		return oggettoEmail;
	}


	public void setOggettoEmail(String oggettoEmail) {
		this.oggettoEmail = oggettoEmail;
	}


	public String getEmailAmministratore() {
		return emailAmministratore;
	}


	public void setEmailAmministratore(String emailAmministratore) {
		this.emailAmministratore = emailAmministratore;
	}


	public String getCodice_cdr_ii_liv_gerarchico() {
		return codice_cdr_ii_liv_gerarchico;
	}


	public void setCodice_cdr_ii_liv_gerarchico(String codice_cdr_ii_liv_gerarchico) {
		this.codice_cdr_ii_liv_gerarchico = codice_cdr_ii_liv_gerarchico;
	}


	public String getCentrale_periferico() {
		return centrale_periferico;
	}


	public void setCentrale_periferico(String centrale_periferico) {
		this.centrale_periferico = centrale_periferico;
	}


	public String getCdr() {
		return cdr;
	}


	public void setCdr(String cdr) {
		this.cdr = cdr;
	}


	public String getDescr_cdr() {
		return descr_cdr;
	}


	public void setDescr_cdr(String descr_cdr) {
		this.descr_cdr = descr_cdr;
	}

}
