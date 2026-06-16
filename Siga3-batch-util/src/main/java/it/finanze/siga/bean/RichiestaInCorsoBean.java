package it.finanze.siga.bean;

import java.io.Serializable;

public class RichiestaInCorsoBean extends BaseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 325017564156271023L;
	
	private String isInCorso;

	public String getisInCorso() {
		return isInCorso;
	}

	public void setisInCorso(String inCorso) {
		this.isInCorso = inCorso;
	}
	
}
