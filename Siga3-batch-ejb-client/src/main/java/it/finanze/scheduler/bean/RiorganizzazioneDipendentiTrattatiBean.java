package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class RiorganizzazioneDipendentiTrattatiBean implements Serializable{


	private static final long serialVersionUID = 5887432739599286887L;
	private String codiceFiscale;
	private String codiceCdrPartenza;
	private String codiceCdrDestinazione;
	private String codiceApplicazione;
	private String codiceRoleGroup;
	private String codiceProfilo;
	private String codiceCdrAbilitazione;
	private String idRichiestaVisibilita;
	private Date dataSegnalazione;
	private String segnalato;
	
	
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCodiceCdrPartenza() {
		return codiceCdrPartenza;
	}
	public void setCodiceCdrPartenza(String codiceCdrPartenza) {
		this.codiceCdrPartenza = codiceCdrPartenza;
	}
	public String getCodiceCdrDestinazione() {
		return codiceCdrDestinazione;
	}
	public void setCodiceCdrDestinazione(String codiceCdrDestinazione) {
		this.codiceCdrDestinazione = codiceCdrDestinazione;
	}
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
	public String getCodiceCdrAbilitazione() {
		return codiceCdrAbilitazione;
	}
	public void setCodiceCdrAbilitazione(String codiceCdrAbilitazione) {
		this.codiceCdrAbilitazione = codiceCdrAbilitazione;
	}
	public String getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(String idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public String getSegnalato() {
		return segnalato;
	}
	public void setSegnalato(String segnalato) {
		this.segnalato = segnalato;
	}
	public Date getDataSegnalazione() {
		return dataSegnalazione;
	}
	public void setDataSegnalazione(Date dataSegnalazione) {
		this.dataSegnalazione = dataSegnalazione;
	}

	
}
