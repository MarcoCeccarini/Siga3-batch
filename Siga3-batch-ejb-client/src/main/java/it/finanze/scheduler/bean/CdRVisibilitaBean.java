package it.finanze.scheduler.bean;

import java.io.Serializable;

public class CdRVisibilitaBean extends CdR implements Serializable{


	private static final long serialVersionUID = 6542882714148938542L;
	private String codUfficio;
	
	public String getCodUfficio() {
		return codUfficio;
	}
	public void setCodUfficio(String codUfficio) {
		this.codUfficio = codUfficio;
	}

	
}
