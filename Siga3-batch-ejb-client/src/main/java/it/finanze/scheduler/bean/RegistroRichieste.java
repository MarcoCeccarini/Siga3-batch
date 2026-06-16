package it.finanze.scheduler.bean;

import java.io.Serializable;

public class RegistroRichieste implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistroRichieste() {
		// TODO Stub di costruttore generato automaticamente
	}
    
	private String cdrUtenteRegistroRichieste;
	private String cfUtenteRegistroRichieste;
	private String cfPresaInCarico;
	private int idRichiesta;

	public String getCdrUtenteRegistroRichieste() {
		return cdrUtenteRegistroRichieste;
	}

	public void setCdrUtente(String cdrUtenteRegistroRichieste) {
		this.cdrUtenteRegistroRichieste = cdrUtenteRegistroRichieste;
	}

	public String getCfUtenteRegistroRichieste() {
		return cfUtenteRegistroRichieste;
	}

	public void setCfUtenteRegistroRichieste(String cfUtenteRegistroRichieste) {
		this.cfUtenteRegistroRichieste = cfUtenteRegistroRichieste;
	}

	public String getCfPresaInCarico() {
		return cfPresaInCarico;
	}

	public void setCfPresaInCarico(String cfPresaInCarico) {
		this.cfPresaInCarico = cfPresaInCarico;
	}

	public int getIdRichiesta() {
		return idRichiesta;
	}

	public void setIdRichiesta(int idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	
	
	
}
