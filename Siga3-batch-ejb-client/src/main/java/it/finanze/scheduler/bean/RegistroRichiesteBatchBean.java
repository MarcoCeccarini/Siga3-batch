package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class RegistroRichiesteBatchBean implements Serializable{
	
	private static final long serialVersionUID = 8174252945938523909L;
	private Integer idCm;
	
	private Long idRichiesta;
	private String tipoRichiesta;
	private String codiceAmbito;
	private String cfUtente;
	private String cdrUtente;
	private String cfRichiedente;
	private String cfRichiedenteEffettivo;
	private String noteRichiedente;
	private Date dataRichiesta;
	private Date dataAutorizzazione1;
	private String cfAutorizzatore1;
	private String cfAutorizzatore1Effettivo;
	private String statoRichiesta;
	private String richiedenteAc;
	private String presaVisione;
	private String cfArchiviazione;
	private String cfArchiviazioneEffettivo;
	private String cdrArchiviazione;
	private String aggiornaSistemaAutorizzazione;
	private String codiceEvento;
	private String richiestaVisibileRuoliSiga;
	private String cancellaUtenteEsterno;
	private String notaGenerazioneRichiesta;
	private String notaAutorizzatore1;
	private String codiceAmbitoAutorizzazione;
	private Date dataEsitoFinale;
	private String esitoFinale;
	private Long idIter;
	
	public String getTipoRichiesta() {
		return tipoRichiesta;
	}
	public void setTipoRichiesta(String tipoRichiesta) {
		this.tipoRichiesta = tipoRichiesta;
	}
	public String getCodiceAmbito() {
		return codiceAmbito;
	}
	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
	public String getCfUtente() {
		return cfUtente;
	}
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}
	public String getCdrUtente() {
		return cdrUtente;
	}
	public void setCdrUtente(String cdrUtente) {
		this.cdrUtente = cdrUtente;
	}
	public String getCfRichiedente() {
		return cfRichiedente;
	}
	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}
	public String getCfRichiedenteEffettivo() {
		return cfRichiedenteEffettivo;
	}
	public void setCfRichiedenteEffettivo(String cfRichiedenteEffettivo) {
		this.cfRichiedenteEffettivo = cfRichiedenteEffettivo;
	}
	public String getNoteRichiedente() {
		return noteRichiedente;
	}
	public void setNoteRichiedente(String noteRichiedente) {
		this.noteRichiedente = noteRichiedente;
	}
	public Date getDataRichiesta() {
		return dataRichiesta;
	}
	public void setDataRichiesta(Date dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}
	public String getCfAutorizzatore1() {
		return cfAutorizzatore1;
	}
	public void setCfAutorizzatore1(String cfAutorizzatore1) {
		this.cfAutorizzatore1 = cfAutorizzatore1;
	}
	public String getStatoRichiesta() {
		return statoRichiesta;
	}
	public void setStatoRichiesta(String statoRichiesta) {
		this.statoRichiesta = statoRichiesta;
	}
	public String getRichiedenteAc() {
		return richiedenteAc;
	}
	public void setRichiedenteAc(String richiedenteAc) {
		this.richiedenteAc = richiedenteAc;
	}
	public String getPresaVisione() {
		return presaVisione;
	}
	public void setPresaVisione(String presaVisione) {
		this.presaVisione = presaVisione;
	}
	public String getCfArchiviazione() {
		return cfArchiviazione;
	}
	public void setCfArchiviazione(String cfArchiviazione) {
		this.cfArchiviazione = cfArchiviazione;
	}
	public String getAggiornaSistemaAutorizzazione() {
		return aggiornaSistemaAutorizzazione;
	}
	public void setAggiornaSistemaAutorizzazione(
			String aggiornaSistemaAutorizzazione) {
		this.aggiornaSistemaAutorizzazione = aggiornaSistemaAutorizzazione;
	}
	public String getCodiceEvento() {
		return codiceEvento;
	}
	public void setCodiceEvento(String codiceEvento) {
		this.codiceEvento = codiceEvento;
	}
	public String getRichiestaVisibileRuoliSiga() {
		return richiestaVisibileRuoliSiga;
	}
	public void setRichiestaVisibileRuoliSiga(String richiestaVisibileRuoliSiga) {
		this.richiestaVisibileRuoliSiga = richiestaVisibileRuoliSiga;
	}
	public String getCancellaUtenteEsterno() {
		return cancellaUtenteEsterno;
	}
	public void setCancellaUtenteEsterno(String cancellaUtenteEsterno) {
		this.cancellaUtenteEsterno = cancellaUtenteEsterno;
	}
	public String getNotaGenerazioneRichiesta() {
		return notaGenerazioneRichiesta;
	}
	public void setNotaGenerazioneRichiesta(String notaGenerazioneRichiesta) {
		this.notaGenerazioneRichiesta = notaGenerazioneRichiesta;
	}
	public String getCodiceAmbitoAutorizzazione() {
		return codiceAmbitoAutorizzazione;
	}
	public void setCodiceAmbitoAutorizzazione(String codiceAmbitoAutorizzazione) {
		this.codiceAmbitoAutorizzazione = codiceAmbitoAutorizzazione;
	}
	public Long getIdRichiesta() {
		return idRichiesta;
	}
	public void setIdRichiesta(Long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	public String getCfAutorizzatore1Effettivo() {
		return cfAutorizzatore1Effettivo;
	}
	public void setCfAutorizzatore1Effettivo(String cfAutorizzatore1Effettivo) {
		this.cfAutorizzatore1Effettivo = cfAutorizzatore1Effettivo;
	}
	public String getCdrArchiviazione() {
		return cdrArchiviazione;
	}
	public void setCdrArchiviazione(String cdrArchiviazione) {
		this.cdrArchiviazione = cdrArchiviazione;
	}
	public Date getDataAutorizzazione1() {
		return dataAutorizzazione1;
	}
	public void setDataAutorizzazione1(Date dataAutorizzazione1) {
		this.dataAutorizzazione1 = dataAutorizzazione1;
	}
	public Date getDataEsitoFinale() {
		return dataEsitoFinale;
	}
	public void setDataEsitoFinale(Date dataEsitoFinale) {
		this.dataEsitoFinale = dataEsitoFinale;
	}
	public String getEsitoFinale() {
		return esitoFinale;
	}
	public void setEsitoFinale(String esitoFinale) {
		this.esitoFinale = esitoFinale;
	}
	public String getNotaAutorizzatore1() {
		return notaAutorizzatore1;
	}
	public void setNotaAutorizzatore1(String notaAutorizzatore1) {
		this.notaAutorizzatore1 = notaAutorizzatore1;
	}
	public String getCfArchiviazioneEffettivo() {
		return cfArchiviazioneEffettivo;
	}
	public void setCfArchiviazioneEffettivo(String cfArchiviazioneEffettivo) {
		this.cfArchiviazioneEffettivo = cfArchiviazioneEffettivo;
	}
	public Long getIdIter() {
		return idIter;
	}
	public void setIdIter(Long idIter) {
		this.idIter = idIter;
	}
	
	
	public Integer getIdCm() {
		return idCm;
	}

	public void setIdCm(Integer idCm) {
		this.idCm = idCm;
	}
	
}
