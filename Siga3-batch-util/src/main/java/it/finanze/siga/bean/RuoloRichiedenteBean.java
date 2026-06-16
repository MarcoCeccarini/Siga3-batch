package it.finanze.siga.bean;

import java.io.Serializable;

public class RuoloRichiedenteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1956590303132821904L;
	
	private String codiceCdr;
	private String cdrDesc;
	private String cfRichiedente;
	private String cognomeRichiedente;
	private String nomeRichiedente;
	private String cfAutorizzatoreILiv;
	private String cognomeAutorizzatoreILiv;
	private String nomeAutorizzatoreILiv;
	
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getCdrDesc() {
		return cdrDesc;
	}
	public void setCdrDesc(String cdrDesc) {
		this.cdrDesc = cdrDesc;
	}
	public String getCfRichiedente() {
		return cfRichiedente;
	}
	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}
	public String getCognomeRichiedente() {
		return cognomeRichiedente;
	}
	public void setCognomeRichiedente(String cognomeRichiedente) {
		this.cognomeRichiedente = cognomeRichiedente;
	}
	public String getNomeRichiedente() {
		return nomeRichiedente;
	}
	public void setNomeRichiedente(String nomeRichiedente) {
		this.nomeRichiedente = nomeRichiedente;
	}
	public String getCfAutorizzatoreILiv() {
		return cfAutorizzatoreILiv;
	}
	public void setCfAutorizzatoreILiv(String cfAutorizzatoreILiv) {
		this.cfAutorizzatoreILiv = cfAutorizzatoreILiv;
	}
	public String getCognomeAutorizzatoreILiv() {
		return cognomeAutorizzatoreILiv;
	}
	public void setCognomeAutorizzatoreILiv(String cognomeAutorizzatoreILiv) {
		this.cognomeAutorizzatoreILiv = cognomeAutorizzatoreILiv;
	}
	public String getNomeAutorizzatoreILiv() {
		return nomeAutorizzatoreILiv;
	}
	public void setNomeAutorizzatoreILiv(String nomeAutorizzatoreILiv) {
		this.nomeAutorizzatoreILiv = nomeAutorizzatoreILiv;
	}
	
	

}
