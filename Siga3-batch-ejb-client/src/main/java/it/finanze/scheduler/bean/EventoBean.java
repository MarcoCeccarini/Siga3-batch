package it.finanze.scheduler.bean;

import java.io.Serializable;

public class EventoBean implements Serializable{

	private static final long serialVersionUID = 3479041909702212810L;
	private String priorita;
	private String richiedente;
	private String autorizzatore;
	private String notaRichiesta;
	private String richiestaVisibileRuoliSiga;
	private String presaVisione;
	private String archiviazione;
	private String orarioInElab;
	private String orarioFineElab;
	private String orarioInizioRichieste;
	private String orarioFineRichieste;
	private String aggiornaSistAutorizzazione;

	private String cauMobCriterio;
	private String hrMobCriterio;
	private String caMobCriterio;
	private String nonIntegrateMobCriterio;
 
	// Controlli Online Su Caricamenti Massivi
	private Long numMaxRecordControlliImmediati;
	private Long numMaxRecordElaborazioneImmediata;
	private Integer numMaxRecord;
	
	private String codiceEvento;
	
	
	
	public Integer getNumMaxRecord() {
		return numMaxRecord;
	}
	public void setNumMaxRecord(Integer numMaxRecord) {
		this.numMaxRecord = numMaxRecord;
	}

	public String getPriorita() {
		return priorita;
	}
	public void setPriorita(String priorita) {
		this.priorita = priorita;
	}
	public String getRichiedente() {
		return richiedente;
	}
	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}
	public String getAutorizzatore() {
		return autorizzatore;
	}
	public void setAutorizzatore(String autorizzatore) {
		this.autorizzatore = autorizzatore;
	}
	public String getNotaRichiesta() {
		return notaRichiesta;
	}
	public void setNotaRichiesta(String notaRichiesta) {
		this.notaRichiesta = notaRichiesta;
	}
	public String getRichiestaVisibileRuoliSiga() {
		return richiestaVisibileRuoliSiga;
	}
	public void setRichiestaVisibileRuoliSiga(String richiestaVisibileRuoliSiga) {
		this.richiestaVisibileRuoliSiga = richiestaVisibileRuoliSiga;
	}
	public String getPresaVisione() {
		return presaVisione;
	}
	public void setPresaVisione(String presaVisione) {
		this.presaVisione = presaVisione;
	}
	public String getArchiviazione() {
		return archiviazione;
	}
	public void setArchiviazione(String archiviazione) {
		this.archiviazione = archiviazione;
	}
	public String getOrarioInElab() {
		return orarioInElab;
	}
	public void setOrarioInElab(String orarioInElab) {
		this.orarioInElab = orarioInElab;
	}
	public String getOrarioFineElab() {
		return orarioFineElab;
	}
	public void setOrarioFineElab(String orarioFineElab) {
		this.orarioFineElab = orarioFineElab;
	}
	public String getAggiornaSistAutorizzazione() {
		return aggiornaSistAutorizzazione;
	}
	public void setAggiornaSistAutorizzazione(String aggiornaSistAutorizzazione) {
		this.aggiornaSistAutorizzazione = aggiornaSistAutorizzazione;
	}
	public Long getNumMaxRecordControlliImmediati() {
		return numMaxRecordControlliImmediati;
	}
	public void setNumMaxRecordControlliImmediati(
			Long numMaxRecordControlliImmediati) {
		this.numMaxRecordControlliImmediati = numMaxRecordControlliImmediati;
	}
	public Long getNumMaxRecordElaborazioneImmediata() {
		return numMaxRecordElaborazioneImmediata;
	}
	public void setNumMaxRecordElaborazioneImmediata(
			Long numMaxRecordElaborazioneImmediata) {
		this.numMaxRecordElaborazioneImmediata = numMaxRecordElaborazioneImmediata;
	}
	public String getOrarioInizioRichieste() {
		return orarioInizioRichieste;
	}
	public void setOrarioInizioRichieste(String orarioInizioRichieste) {
		this.orarioInizioRichieste = orarioInizioRichieste;
	}
	public String getOrarioFineRichieste() {
		return orarioFineRichieste;
	}
	public void setOrarioFineRichieste(String orarioFineRichieste) {
		this.orarioFineRichieste = orarioFineRichieste;
	}
	public String getCauMobCriterio() {
		return cauMobCriterio;
	}
	public String getHrMobCriterio() {
		return hrMobCriterio;
	}
	public String getCaMobCriterio() {
		return caMobCriterio;
	}
	public void setCauMobCriterio(String cauMobCriterio) {
		this.cauMobCriterio = cauMobCriterio;
	}
	public void setHrMobCriterio(String hrMobCriterio) {
		this.hrMobCriterio = hrMobCriterio;
	}
	public void setCaMobCriterio(String caMobCriterio) {
		this.caMobCriterio = caMobCriterio;
	}
	public String getNonIntegrateMobCriterio() {
		return nonIntegrateMobCriterio;
	}
	public void setNonIntegrateMobCriterio(String nonIntegrateMobCriterio) {
		this.nonIntegrateMobCriterio = nonIntegrateMobCriterio;
	}
	public String getCodiceEvento() {
		return codiceEvento;
	}
	public void setCodiceEvento(String codiceEvento) {
		this.codiceEvento = codiceEvento;
	}
	

}
