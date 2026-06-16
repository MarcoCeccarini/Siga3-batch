package it.finanze.siga.bean;

import java.io.Serializable;

public class IterProfiliApplicazioniBean extends BaseBean implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -8850420784623121623L;
	private String codice_applicazione;
	private String data_fine_val;
	private String data_inizio_val;
	private String ambito;
	private String applicazione;
	private String profilo;
	private String identificativo_iter;
	private String descrizione_iter;
	private String tipo_ufficio_richiedente;
	private String tipo_ufficio_richiedente_descrizione;
	private String autorizz_I_liv;
	private String autorizz_II_liv;
	private String strutt_gest_op;
	
	public String getCodice_applicazione() {
		return codice_applicazione;
	}
	public void setCodice_applicazione(String codice_applicazione) {
		this.codice_applicazione = codice_applicazione;
	}
	public String getData_fine_val() {
		return data_fine_val;
	}
	public void setData_fine_val(String data_fine_val) {
		this.data_fine_val = data_fine_val;
	}
	public String getData_inizio_val() {
		return data_inizio_val;
	}
	public void setData_inizio_val(String data_inizio_val) {
		this.data_inizio_val = data_inizio_val;
	}
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	public String getApplicazione() {
		return applicazione;
	}
	public void setApplicazione(String applicazione) {
		this.applicazione = applicazione;
	}
	public String getProfilo() {
		return profilo;
	}
	public void setProfilo(String profilo) {
		this.profilo = profilo;
	}
	public String getIdentificativo_iter() {
		return identificativo_iter;
	}
	public void setIdentificativo_iter(String identificativo_iter) {
		this.identificativo_iter = identificativo_iter;
	}
	public String getDescrizione_iter() {
		return descrizione_iter;
	}
	public void setDescrizione_iter(String descrizione_iter) {
		this.descrizione_iter = descrizione_iter;
	}
	public String getTipo_ufficio_richiedente() {
		return tipo_ufficio_richiedente;
	}
	public void setTipo_ufficio_richiedente(String tipo_ufficio_richiedente) {
		this.tipo_ufficio_richiedente = tipo_ufficio_richiedente;
	}
	public String getAutorizz_I_liv() {
		return autorizz_I_liv;
	}
	public void setAutorizz_I_liv(String autorizz_I_liv) {
		this.autorizz_I_liv = autorizz_I_liv;
	}
	public String getAutorizz_II_liv() {
		return autorizz_II_liv;
	}
	public void setAutorizz_II_liv(String autorizz_II_liv) {
		this.autorizz_II_liv = autorizz_II_liv;
	}
	public String getStrutt_gest_op() {
		return strutt_gest_op;
	}
	public void setStrutt_gest_op(String strutt_gest_op) {
		this.strutt_gest_op = strutt_gest_op;
	}
	public String getTipo_ufficio_richiedente_descrizione() {
		return tipo_ufficio_richiedente_descrizione;
	}
	public void setTipo_ufficio_richiedente_descrizione(
			String tipo_ufficio_richiedente_descrizione) {
		this.tipo_ufficio_richiedente_descrizione = tipo_ufficio_richiedente_descrizione;
	}

}
