package it.finanze.scheduler.bean;

import java.io.Serializable;

public class UfficiPilota implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String cdr2Liv;
	private String pilotaAvviato;
	private String delegheElaborate;
	private String associazioniOra;
	/**
	 * @return the cdr2Liv
	 */
	public String getCdr2Liv() {
		return cdr2Liv;
	}
	/**
	 * @param cdr2Liv the cdr2Liv to set
	 */
	public void setCdr2Liv(String cdr2Liv) {
		this.cdr2Liv = cdr2Liv;
	}
	/**
	 * @return the pilotaAvviato
	 */
	public String getPilotaAvviato() {
		return pilotaAvviato;
	}
	/**
	 * @param pilotaAvviato the pilotaAvviato to set
	 */
	public void setPilotaAvviato(String pilotaAvviato) {
		this.pilotaAvviato = pilotaAvviato;
	}
	/**
	 * @return the delegheElaborate
	 */
	public String getDelegheElaborate() {
		return delegheElaborate;
	}
	/**
	 * @param delegheElaborate the delegheElaborate to set
	 */
	public void setDelegheElaborate(String delegheElaborate) {
		this.delegheElaborate = delegheElaborate;
	}
	/**
	 * @return the associazioniOra
	 */
	public String getAssociazioniOra() {
		return associazioniOra;
	}
	/**
	 * @param associazioniOra the associazioniOra to set
	 */
	public void setAssociazioniOra(String associazioniOra) {
		this.associazioniOra = associazioniOra;
	}
	
}
