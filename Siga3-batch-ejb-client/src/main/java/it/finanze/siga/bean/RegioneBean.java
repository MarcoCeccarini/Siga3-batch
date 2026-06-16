package it.finanze.siga.bean;

import java.io.Serializable;

public class RegioneBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String descrizione;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}

