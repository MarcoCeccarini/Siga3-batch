package it.finanze.siga.bean;

import java.io.Serializable;

public class CruscottoCaselleBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6718464185866473037L;
	
	private String cf;
	private String smtpPrimario;
	private String descrizione;
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getSmtpPrimario() {
		return smtpPrimario;
	}
	public void setSmtpPrimario(String smtpPrimario) {
		this.smtpPrimario = smtpPrimario;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	

}
