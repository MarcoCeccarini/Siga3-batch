package it.finanze.siga.bean;

import java.io.Serializable;

public class UffCdrAppartBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5776421173099841996L;
	/**
	 * 
	 */

	private String codice_ufficio;
	private String descrizione_tipo_ufficio;
	public String getCodice_ufficio() {
		return codice_ufficio;
	}
	public void setCodice_ufficio(String codice_ufficio) {
		this.codice_ufficio = codice_ufficio;
	}
	public String getDescrizione_tipo_ufficio() {
		return descrizione_tipo_ufficio;
	}
	public void setDescrizione_tipo_ufficio(String descrizione_tipo_ufficio) {
		this.descrizione_tipo_ufficio = descrizione_tipo_ufficio;
	}
			
}
