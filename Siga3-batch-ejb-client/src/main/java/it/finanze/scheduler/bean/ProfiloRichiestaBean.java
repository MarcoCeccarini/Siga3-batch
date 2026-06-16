package it.finanze.scheduler.bean;

import it.finanze.siga.util.Constants;

import java.io.Serializable;
import java.util.Date;

public class ProfiloRichiestaBean implements Serializable{
	
	private static final long serialVersionUID = -8901993491353667240L;
	private Long idRichiesta;
	private String codiceApplicazione;
	private String codiceRoleGroup;
	private String codiceProfilo;
	private String codiceUfficio;
	private String codiceCdr;
	private String tipoAbilitazione;
	private String operazioneSuProfilo;
	private String operazioneEseguita;
	private Date dataScadenza;
	private String idRichiestaVisibilita;
	private String origineAbilitazione;
	private String tipoAbilitazioneString;
	
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
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
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getTipoAbilitazione() {
		return tipoAbilitazione;
	}
	public void setTipoAbilitazione(String tipoAbilitazione) {
		this.tipoAbilitazione = tipoAbilitazione;
	}
	public String getOperazioneSuProfilo() {
		return operazioneSuProfilo;
	}
	public void setOperazioneSuProfilo(String operazioneSuProfilo) {
		this.operazioneSuProfilo = operazioneSuProfilo;
	}
	public String getOperazioneEseguita() {
		return operazioneEseguita;
	}
	public void setOperazioneEseguita(String operazioneEseguita) {
		this.operazioneEseguita = operazioneEseguita;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public String getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(String idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public String getOrigineAbilitazione() {
		return origineAbilitazione;
	}
	public void setOrigineAbilitazione(String origineAbilitazione) {
		this.origineAbilitazione = origineAbilitazione;
	}
	public Long getIdRichiesta() {
		return idRichiesta;
	}
	public void setIdRichiesta(Long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	
	public String getTipoAbilitazioneString() {
		if(tipoAbilitazione.equalsIgnoreCase(Constants.O))
			tipoAbilitazioneString = "";
		else if(tipoAbilitazione.equalsIgnoreCase(Constants.S))
			tipoAbilitazioneString = Constants.ExtraUfficio;
		else
			tipoAbilitazioneString = "";
		return tipoAbilitazioneString;
	}
}
