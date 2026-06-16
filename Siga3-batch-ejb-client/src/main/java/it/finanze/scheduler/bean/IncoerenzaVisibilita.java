package it.finanze.scheduler.bean;

import java.io.Serializable;

public class IncoerenzaVisibilita implements Serializable{

	private static final long serialVersionUID = -6335334860997794625L;
	private String codiceFiscale;
	private String incoerenzaCau = "NO";
	private String incoerenzaHr = "NO";
	private String incoerenzaCea = "NO";
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getIncoerenzaCau() {
		return incoerenzaCau;
	}
	public void setIncoerenzaCau(String incoerenzaCau) {
		this.incoerenzaCau = incoerenzaCau;
	}
	public String getIncoerenzaHr() {
		return incoerenzaHr;
	}
	public void setIncoerenzaHr(String incoerenzaHr) {
		this.incoerenzaHr = incoerenzaHr;
	}
	public String getIncoerenzaCea() {
		return incoerenzaCea;
	}
	public void setIncoerenzaCea(String incoerenzaCea) {
		this.incoerenzaCea = incoerenzaCea;
	}
	
}
