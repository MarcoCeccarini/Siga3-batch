package it.finanze.siga.bean;

import java.io.Serializable;

public class TipoAbilitazioneBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1880770281393132942L;
	private String codiceAbilitazione;
	private String tipoAbilitazione;
	private String descrizioneStampe;
	public String getCodiceAbilitazione() {
		return codiceAbilitazione;
	}
	public void setCodiceAbilitazione(String codiceAbilitazione) {
		this.codiceAbilitazione = codiceAbilitazione;
	}
	public String getTipoAbilitazione() {
		return tipoAbilitazione;
	}
	public void setTipoAbilitazione(String tipoAbilitazione) {
		this.tipoAbilitazione = tipoAbilitazione;
	}
	public String getDescrizioneStampe() {
		return descrizioneStampe;
	}
	public void setDescrizioneStampe(String descrizioneStampe) {
		this.descrizioneStampe = descrizioneStampe;
	}
	
	
	
}
