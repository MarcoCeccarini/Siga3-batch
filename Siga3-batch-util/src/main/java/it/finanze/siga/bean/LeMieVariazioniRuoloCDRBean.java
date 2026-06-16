package it.finanze.siga.bean;

import java.io.Serializable;

public class LeMieVariazioniRuoloCDRBean extends BaseBean implements Serializable {
	//Struttura	Ufficio	CdR	Nome	Cognome	CF	Nome	Cognome	CF	Nome 	Cognome	CF	Dal	Al	Congome	Nome	CF	Nome	Cognome	CF
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6876333338171562094L;
	
	private String descrizione_struttura;
	private String descrizione_ufficio;
	private String descrizione_cdr;
	private String codice_cdr;
	private String cognome_res;
	private String nome_res;
	private String cf_resp;
	private String cognome_rich;
	private String nome_rich;
	private String cf_rich;
	private String cognome_aut;
	private String nome_aut;
	private String cf_aut;
	private String data_inizio_val;
	private String data_fine_val;
	private String cognome_inizio;
	private String nome_inizio;
	private String cf_inizio;
	private String cognome_fine;
	private String nome_fine;
	private String cf_fine;
	private String codFisUser;
	private String cognomeUser;
	private String nomeUser;
	private String dataOraEstrazione;
	private String intestazioneExport;
	
	public String getDescrizione_struttura() {
		return descrizione_struttura;
	}
	public void setDescrizione_struttura(String descrizione_struttura) {
		this.descrizione_struttura = descrizione_struttura;
	}
	public String getDescrizione_ufficio() {
		return descrizione_ufficio;
	}
	public void setDescrizione_ufficio(String descrizione_ufficio) {
		this.descrizione_ufficio = descrizione_ufficio;
	}
	public String getDescrizione_cdr() {
		return descrizione_cdr;
	}
	public void setDescrizione_cdr(String descrizione_cdr) {
		this.descrizione_cdr = descrizione_cdr;
	}
	public String getCognome_res() {
		return cognome_res;
	}
	public void setCognome_res(String cognome_res) {
		this.cognome_res = cognome_res;
	}
	public String getNome_res() {
		return nome_res;
	}
	public void setNome_res(String nome_res) {
		this.nome_res = nome_res;
	}
	public String getCf_resp() {
		return cf_resp;
	}
	public void setCf_resp(String cf_resp) {
		this.cf_resp = cf_resp;
	}
	public String getCognome_rich() {
		return cognome_rich;
	}
	public void setCognome_rich(String cognome_rich) {
		this.cognome_rich = cognome_rich;
	}
	public String getNome_rich() {
		return nome_rich;
	}
	public void setNome_rich(String nome_rich) {
		this.nome_rich = nome_rich;
	}
	public String getCf_rich() {
		return cf_rich;
	}
	public void setCf_rich(String cf_rich) {
		this.cf_rich = cf_rich;
	}
	public String getCognome_aut() {
		return cognome_aut;
	}
	public void setCognome_aut(String cognome_aut) {
		this.cognome_aut = cognome_aut;
	}
	public String getNome_aut() {
		return nome_aut;
	}
	public void setNome_aut(String nome_aut) {
		this.nome_aut = nome_aut;
	}
	public String getCf_aut() {
		return cf_aut;
	}
	public void setCf_aut(String cf_aut) {
		this.cf_aut = cf_aut;
	}
	public String getData_inizio_val() {
		return data_inizio_val;
	}
	public void setData_inizio_val(String data_inizio_val) {
		this.data_inizio_val = data_inizio_val;
	}
	public String getData_fine_val() {
		return data_fine_val;
	}
	public void setData_fine_val(String data_fine_val) {
		this.data_fine_val = data_fine_val;
	}
	public String getCognome_inizio() {
		return cognome_inizio;
	}
	public void setCognome_inizio(String cognome_inizio) {
		this.cognome_inizio = cognome_inizio;
	}
	public String getNome_inizio() {
		return nome_inizio;
	}
	public void setNome_inizio(String nome_inizio) {
		this.nome_inizio = nome_inizio;
	}
	public String getCf_inizio() {
		return cf_inizio;
	}
	public void setCf_inizio(String cf_inizio) {
		this.cf_inizio = cf_inizio;
	}
	public String getCognome_fine() {
		return cognome_fine;
	}
	public void setCognome_fine(String cognome_fine) {
		this.cognome_fine = cognome_fine;
	}
	public String getNome_fine() {
		return nome_fine;
	}
	public void setNome_fine(String nome_fine) {
		this.nome_fine = nome_fine;
	}
	public String getCf_fine() {
		return cf_fine;
	}
	public void setCf_fine(String cf_fine) {
		this.cf_fine = cf_fine;
	}
	public String getCodFisUser() {
		return codFisUser;
	}
	public void setCodFisUser(String codFisUser) {
		this.codFisUser = codFisUser;
	}
	public String getCognomeUser() {
		return cognomeUser;
	}
	public void setCognomeUser(String cognomeUser) {
		this.cognomeUser = cognomeUser;
	}
	public String getNomeUser() {
		return nomeUser;
	}
	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}
	public String getDataOraEstrazione() {
		return dataOraEstrazione;
	}
	public void setDataOraEstrazione(String dataOraEstrazione) {
		this.dataOraEstrazione = dataOraEstrazione;
	}
	public String getCodice_cdr() {
		return codice_cdr;
	}
	public void setCodice_cdr(String codice_cdr) {
		this.codice_cdr = codice_cdr;
	}
	public String getIntestazioneExport() {
		return intestazioneExport;
	}
	public void setIntestazioneExport(String intestazioneExport) {
		this.intestazioneExport = intestazioneExport;
	}
	
	
}
