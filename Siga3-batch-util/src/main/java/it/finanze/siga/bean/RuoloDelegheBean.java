package it.finanze.siga.bean;

import java.io.Serializable;

public class RuoloDelegheBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2639358156503290857L;
	
	private String codiceFiscaleDelegante;
	private String cognomeDelegante;
	private String nomeDelegante;
	private String nomeRuolo;
	private String codiceFiscaleDelegato;
	private String cognomeDelegato;
	private String nomeDelegato;
	
	public String getCodiceFiscaleDelegante() {
		return codiceFiscaleDelegante;
	}
	public void setCodiceFiscaleDelegante(String codiceFiscaleDelegante) {
		this.codiceFiscaleDelegante = codiceFiscaleDelegante;
	}
	public String getCognomeDelegante() {
		return cognomeDelegante;
	}
	public void setCognomeDelegante(String cognomeDelegante) {
		this.cognomeDelegante = cognomeDelegante;
	}
	public String getNomeDelegante() {
		return nomeDelegante;
	}
	public void setNomeDelegante(String nomeDelegante) {
		this.nomeDelegante = nomeDelegante;
	}
	public String getNomeRuolo() {
		return nomeRuolo;
	}
	public void setNomeRuolo(String nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}
	public String getCodiceFiscaleDelegato() {
		return codiceFiscaleDelegato;
	}
	public void setCodiceFiscaleDelegato(String codiceFiscaleDelegato) {
		this.codiceFiscaleDelegato = codiceFiscaleDelegato;
	}
	public String getCognomeDelegato() {
		return cognomeDelegato;
	}
	public void setCognomeDelegato(String cognomeDelegato) {
		this.cognomeDelegato = cognomeDelegato;
	}
	public String getNomeDelegato() {
		return nomeDelegato;
	}
	public void setNomeDelegato(String nomeDelegato) {
		this.nomeDelegato = nomeDelegato;
	}

	
}
