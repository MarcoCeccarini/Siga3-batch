package it.finanze.siga.util.bean;

import java.io.Serializable;

public class ExportProvinceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6486041156545830953L;
	
	private String siglaProvincia;
	private String provincia;
	
	
	public String getSiglaProvincia() {
		return siglaProvincia;
	}
	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	

}
