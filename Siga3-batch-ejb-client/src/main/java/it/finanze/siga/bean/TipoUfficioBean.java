package it.finanze.siga.bean;

import java.io.Serializable;

public class TipoUfficioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tipoUfficio;
	private String descrizioneTipoUfficio;
	public String getTipoUfficio() {
		return tipoUfficio;
	}
	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}
	public String getDescrizioneTipoUfficio() {
		return descrizioneTipoUfficio;
	}
	public void setDescrizioneTipoUfficio(String descrizioneTipoUfficio) {
		this.descrizioneTipoUfficio = descrizioneTipoUfficio;
	}
}
