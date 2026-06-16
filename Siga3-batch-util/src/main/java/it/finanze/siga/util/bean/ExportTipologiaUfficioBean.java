package it.finanze.siga.util.bean;

import java.io.Serializable;

public class ExportTipologiaUfficioBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7330622693080572007L;
	
	private String tipoUfficio;
	private String descTipoUfficio;
	
	

	public String getTipoUfficio() {
		return tipoUfficio;
	}
	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}
	public String getDescTipoUfficio() {
		return descTipoUfficio;
	}
	public void setDescTipoUfficio(String descTipoUfficio) {
		this.descTipoUfficio = descTipoUfficio;
	}
	

}
