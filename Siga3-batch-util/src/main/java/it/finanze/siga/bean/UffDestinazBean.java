package it.finanze.siga.bean;

import java.io.Serializable;

public class UffDestinazBean extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3124420008331788861L;
	
	private String descrizioneUfficio;
	private String codiceUfficio;
	private String codiceCDR;
	
	public String getDescrizioneUfficio() {
		return descrizioneUfficio;
	}

	public void setDescrizioneUfficio(String descrizioneUfficio) {
		this.descrizioneUfficio = descrizioneUfficio;
	}

	public String getCodiceCDR() {
		return codiceCDR;
	}

	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	
	
}
