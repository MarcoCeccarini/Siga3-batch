package it.finanze.siga.bean;

import java.io.Serializable;

public class RichDlgSessBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 623510189168938433L;
	/**
	 * 
	 */
	
	private String cf_richiedente;
	private String codice_cdr;
	
	public String getCf_richiedente() {
		return cf_richiedente;
	}
	public void setCf_richiedente(String cf_richiedente) {
		this.cf_richiedente = cf_richiedente;
	}
	public String getCodiceCdr() {
		return codice_cdr;
	}
	public void setCodiceCdr(String codice_cdr) {
		this.codice_cdr = codice_cdr;
	}
		
}
