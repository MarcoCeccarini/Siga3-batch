package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class RichiestaAbilitazioneDisabilitazioneBean implements Serializable{

	
	private static final long serialVersionUID = -7405558086142298526L;
	private long idRichiesta;
	private long idRichiestaVisibilita;
	private String codiceFiscale;
	private String codiceProfilo;
	private String codiceCdr;
	private String roleGroup;
	private String codiceUfficio;
	private String azione;
	private String cdrVisibilita;
	private Date dataProfilo;
	private String flagCancellazioneUtenteEsterno;
	private String evento;
	private String tipoUtente;
	private long sequence;
	private Date dataEvento;
	private Date dataCreazione;
	private Date dataScadenza;
	private Long idRichiestaPau;
	
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
	public String getRoleGroup() {
		return roleGroup;
	}
	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public Date getDataProfilo() {
		return dataProfilo;
	}
	public void setDataProfilo(Date dataProfilo) {
		this.dataProfilo = dataProfilo;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCdrVisibilita() {
		return cdrVisibilita;
	}
	public void setCdrVisibilita(String cdrVisibilita) {
		this.cdrVisibilita = cdrVisibilita;
	}
	public long getIdRichiesta() {
		return idRichiesta;
	}
	public void setIdRichiesta(long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	public String getFlagCancellazioneUtenteEsterno() {
		return flagCancellazioneUtenteEsterno;
	}
	public void setFlagCancellazioneUtenteEsterno(
			String flagCancellazioneUtenteEsterno) {
		this.flagCancellazioneUtenteEsterno = flagCancellazioneUtenteEsterno;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	public Date getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}
	public long getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(long idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public Date getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public String getTipoUtente() {
		return tipoUtente;
	}
	public void setTipoUtente(String tipoUtente) {
		this.tipoUtente = tipoUtente;
	}
	public Long getIdRichiestaPau() {
		return idRichiestaPau;
	}
	public void setIdRichiestaPau(Long idRichiestaPau) {
		this.idRichiestaPau = idRichiestaPau;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date string) {
		this.dataScadenza = string;
	}

		
}


	

