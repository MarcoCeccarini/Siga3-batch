package it.finanze.scheduler.bean;

import java.io.Serializable;

public class RispostaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6465713343081781179L;
	
	private int codiceRitorno;
	private String messaggioRitorno;
	
	
	public RispostaBean(int codiceRitorno, String messaggioRitorno) {
		super();
		this.codiceRitorno = codiceRitorno;
		this.messaggioRitorno = messaggioRitorno;
	}
	public int getCodiceRitorno() {
		return codiceRitorno;
	}
	public void setCodiceRitorno(int codiceRitorno) {
		this.codiceRitorno = codiceRitorno;
	}
	public String getMessaggioRitorno() {
		return messaggioRitorno;
	}
	public void setMessaggioRitorno(String messaggioRitorno) {
		this.messaggioRitorno = messaggioRitorno;
	}
	
	

}
