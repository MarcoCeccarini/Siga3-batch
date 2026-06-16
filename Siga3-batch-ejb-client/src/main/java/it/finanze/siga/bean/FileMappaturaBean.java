package it.finanze.siga.bean;

import java.io.Serializable;

public class FileMappaturaBean implements Serializable{

	private static final long serialVersionUID = 3387935142134507771L;
	private String idCaricamento;
	private String stato;
	private String motivazioneScarto;
	private String tipologiaUfficio;
	private String ambitoRegionale;
	private String ambitoProvinciale;
	private String codiceUfficio;
	private String codiceRoleGroup;
	private String codiceProfilo;
	private String codiceCdr;
	private String azione;
	
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
	public String getTipologiaUfficio() {
		return tipologiaUfficio;
	}
	public void setTipologiaUfficio(String tipologiaUfficio) {
		this.tipologiaUfficio = tipologiaUfficio;
	}
	public String getAmbitoRegionale() {
		return ambitoRegionale;
	}
	public void setAmbitoRegionale(String ambitoRegionale) {
		this.ambitoRegionale = ambitoRegionale;
	}
	public String getAmbitoProvinciale() {
		return ambitoProvinciale;
	}
	public void setAmbitoProvinciale(String ambitoProvinciale) {
		this.ambitoProvinciale = ambitoProvinciale;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
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
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	
	
}
