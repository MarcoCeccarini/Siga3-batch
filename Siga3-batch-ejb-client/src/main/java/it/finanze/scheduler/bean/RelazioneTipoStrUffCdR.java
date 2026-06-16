package it.finanze.scheduler.bean;

import java.io.Serializable;

public class RelazioneTipoStrUffCdR implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoUfficio;
	private String tipoStruttura;
	private String tipoCdR;

	public RelazioneTipoStrUffCdR() {
		// TODO Stub di costruttore generato automaticamente
	}

	public String getTipoUfficio() {
		return tipoUfficio;
	}

	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}

	public String getTipoStruttura() {
		return tipoStruttura;
	}

	public void setTipoStruttura(String tipoStruttura) {
		this.tipoStruttura = tipoStruttura;
	}

	public String getTipoCdR() {
		return tipoCdR;
	}

	public void setTipoCdR(String tipoCdR) {
		this.tipoCdR = tipoCdR;
	}

}
