package it.finanze.siga.bean;

import java.io.Serializable;

public class OperatoreEsternoBean implements Serializable{

	private static final long serialVersionUID = 1104645589256895830L;
	private String codiceFiscale;
	private String organizzazione;
	private String cognome;
	private String nome;
	private String dataScadenza;
	private String cdrDescr;
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getOrganizzazione() {
		return organizzazione;
	}
	public void setOrganizzazione(String organizzazione) {
		this.organizzazione = organizzazione;
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
	public String getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public String getCdrDescr() {
		return cdrDescr;
	}
	public void setCdrDescr(String cdrDescr) {
		this.cdrDescr = cdrDescr;
	}
	
	
}
