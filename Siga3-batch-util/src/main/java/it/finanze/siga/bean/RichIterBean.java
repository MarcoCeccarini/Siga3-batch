package it.finanze.siga.bean;

import java.io.Serializable;

public class RichIterBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7566981725012675573L;
	
	private String tipoUffRich;
	private Integer idIter;
	
	public String getTipoUffRich() {
		return tipoUffRich;
	}
	public void setTipoUffRich(String tipoUffRich) {
		this.tipoUffRich = tipoUffRich;
	}
	public Integer getIdIter() {
		return idIter;
	}
	public void setIdIter(Integer idIter) {
		this.idIter = idIter;
	}

	
	
}
