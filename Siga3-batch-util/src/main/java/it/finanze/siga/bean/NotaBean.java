package it.finanze.siga.bean;

import java.io.Serializable;

public class NotaBean extends BaseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4808173513755502708L;
	
	private long id;
	private String testo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}

}
