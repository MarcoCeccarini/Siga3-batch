package it.finanze.scheduler.bean;

import java.io.Serializable;

public class ProfiliScaduti extends ProfiliAttiviUtente implements Serializable{


	private static final long serialVersionUID = -3859830372390263753L;
	private String codiceAmbito;

	public String getCodiceAmbito() {
		return codiceAmbito;
	}

	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
	
}
