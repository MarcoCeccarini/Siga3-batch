package it.finanze.scheduler.bean;


import java.io.Serializable;

public class ProfiloCM extends Profilo implements Serializable{

	private static final long serialVersionUID = 90446420361379741L;
	private String codiceAmbito;
	
	public String getCodiceAmbito() {
		return codiceAmbito;
	}
	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}

	
	

}
