package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;

public class FileAbilitazioneBean implements Serializable{

	private static final long serialVersionUID = 8475011330818076354L;
	private String idCaricamento;
	private String stato;
	private String motivazioneScarto;
	private String codiceFiscale;
	private String codiceRoleGroup;
	private String codiceProfilo;
	private String codiceCdr;
	private String azione;
	private Date dataScadenza;
	private Date dataFineVal;
//	private String roleGroupDesc;
//	private String profiloDesc;
//	private String cdrDesc;
//	private String nome;
//	private String cognome;
	
	public String getIdCaricamento() {
		return idCaricamento;
	}
	public void setIdCaricamento(String idCaricamento) {
		this.idCaricamento = idCaricamento;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public String getMotivazioneScarto() {
		return motivazioneScarto;
	}
	public void setMotivazioneScarto(String motivazioneScarto) {
		this.motivazioneScarto = motivazioneScarto;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza =  dataScadenza;
	}
	public Date getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal =  dataFineVal;
	}
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}

}
