package it.finanze.siga.bean;

import java.io.Serializable;

public class UfficioUnicoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	
	private String codiceUfficio;
	private String descrizioneUfficio;
	
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getDescrizioneUfficio() {
		return descrizioneUfficio;
	}
	public void setDescrizioneUfficio(String descrizioneUfficio) {
		this.descrizioneUfficio = descrizioneUfficio;
	}
	
	

}



