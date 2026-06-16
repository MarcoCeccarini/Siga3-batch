package it.finanze.siga.bean;

import java.io.Serializable;


public class OperatoriInVisibilitaBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2410985879312103070L;
	
	private String cognome;
	private String nome;
	private String cdr;
	private String cf;
	private String descrizione;
	private String flag;
	private String cdr_di_appartenenza;
	private String desc_cdr_di_appartenenza;
	private String cdr_in_visibilita;
	private String desc_cdr_in_visibilita;
	private String cf_utente;
	private String codiceUfficio;
	private String codiceStruttura;
	private String data_fine_val;
	private String tipoUtente;
	private Long idRichiestaVisibilita;
	private boolean nonRevocabile;
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCdr_di_appartenenza() {
		return cdr_di_appartenenza;
	}
	public void setCdr_di_appartenenza(String cdr_di_appartenenza) {
		this.cdr_di_appartenenza = cdr_di_appartenenza;
	}
	public String getDesc_cdr_di_appartenenza() {
		return desc_cdr_di_appartenenza;
	}
	public void setDesc_cdr_di_appartenenza(String desc_cdr_di_appartenenza) {
		this.desc_cdr_di_appartenenza = desc_cdr_di_appartenenza;
	}
	public String getCdr_in_visibilita() {
		return cdr_in_visibilita;
	}
	public void setCdr_in_visibilita(String cdr_in_visibilita) {
		this.cdr_in_visibilita = cdr_in_visibilita;
	}
	public String getDesc_cdr_in_visibilita() {
		return desc_cdr_in_visibilita;
	}
	public void setDesc_cdr_in_visibilita(String desc_cdr_in_visibilita) {
		this.desc_cdr_in_visibilita = desc_cdr_in_visibilita;
	}
	public String getCf_utente() {
		return cf_utente;
	}
	public void setCf_utente(String cf_utente) {
		this.cf_utente = cf_utente;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getData_fine_val() {
		return data_fine_val;
	}
	public void setData_fine_val(String data_fine_val) {
		this.data_fine_val = data_fine_val;
	}
	public String getTipoUtente() {
		return tipoUtente;
	}
	public void setTipoUtente(String tipoUtente) {
		this.tipoUtente = tipoUtente;
	}
	public String getCodiceStruttura() {
		return codiceStruttura;
	}
	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}
	public Long getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(Long idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public String getCdr() {
		return cdr;
	}
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public boolean isNonRevocabile() {
		return nonRevocabile;
	}
	public void setNonRevocabile(boolean nonRevocabile) {
		this.nonRevocabile = nonRevocabile;
	}	

	
}
