package it.finanze.siga.bean;

import java.io.Serializable;

public class TipoCdrBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tipoCdr;
	private String descrizione;
	public String getTipoCdr() {
		return tipoCdr;
	}
	public void setTipoCdr(String tipoCdr) {
		this.tipoCdr = tipoCdr;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
