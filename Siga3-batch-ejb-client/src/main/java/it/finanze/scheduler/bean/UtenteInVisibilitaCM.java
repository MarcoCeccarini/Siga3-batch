package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class UtenteInVisibilitaCM extends UtenteInVisibilita implements Serializable {


	private static final long serialVersionUID = -4019913463272234835L;
	private String codiceUfficio;
	private Date dataInizioVal;

	public UtenteInVisibilitaCM() {
		// TODO Stub di costruttore generato automaticamente
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public Date getDataInizioVal() {
		return dataInizioVal;
	}

	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}
	


}
