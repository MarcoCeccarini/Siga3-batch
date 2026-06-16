package it.finanze.scheduler.bean;

import java.io.Serializable;

public class RichiestaVisibilitaBean extends RichiestaUtenteInVisibilitaBean implements Serializable {

	private static final long serialVersionUID = 3735418829694692442L;
	private String codiceUfficio;

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	
	
}
