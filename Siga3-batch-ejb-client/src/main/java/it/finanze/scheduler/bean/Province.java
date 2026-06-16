package it.finanze.scheduler.bean;

import java.io.Serializable;

public class Province implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String siglaCapoluogoProvincia;
	private String provincia;

	public Province() {
		// TODO Stub di costruttore generato automaticamente
	}

	public String getSiglaCapoluogoProvincia() {
		return siglaCapoluogoProvincia;
	}

	public void setSiglaCapoluogoProvincia(String siglaCapoluogoProvincia) {
		this.siglaCapoluogoProvincia = siglaCapoluogoProvincia;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

}
