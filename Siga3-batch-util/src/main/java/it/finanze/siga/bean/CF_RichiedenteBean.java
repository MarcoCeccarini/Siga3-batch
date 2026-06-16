package it.finanze.siga.bean;

import java.io.Serializable;

public class CF_RichiedenteBean extends BaseBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1085470738440137204L;

	private String cf_richiedente;

	public String getCf_richiedente() {
		return cf_richiedente;
	}

	public void setCf_richiedente(String cf_richiedente) {
		this.cf_richiedente = cf_richiedente;
	}

}
