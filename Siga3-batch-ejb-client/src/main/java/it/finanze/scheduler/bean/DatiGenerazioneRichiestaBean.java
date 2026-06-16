package it.finanze.scheduler.bean;

import java.io.Serializable;

public class DatiGenerazioneRichiestaBean implements Serializable{

	
	private static final long serialVersionUID = -123805631049730042L;
	private Long sequence;
	private Long idRichiestaVisibilita;
	private String cf;
	private String priorita;
	private String evento;
	private String richiestaVisibileRuoliSiga;
	private String codiceAmbitoApplicativo;
	private String richiedente;
	private String autorizzatore;
	private String presaVisione;
	private String archiviazione;
	private String oraInizioElaborazioniRichiesta;
	private String oraFineElaborazioniRichieste;
	private String codiceRoleGroup;
	private String codiceProfilo;
	private String codiceUfficio;
	private String codiceCdr;
	private String descrCdr;
	private String azione;
	private String notaRichiesta;	
	private String aggiornaSistAutorizzazione;
	private String cdrUtente;
	private String codApplicazione;
	private String idRichiestaGenerata;
	private String descrizioneApplicazione;
	private String nomeOperatore;
	private String cognomeOperatore;
	private String idRichiestaPau;
	
	public Long getSequence() {
		return sequence;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	public Long getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(Long idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getPriorita() {
		return priorita;
	}
	public void setPriorita(String priorita) {
		this.priorita = priorita;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getRichiestaVisibileRuoliSiga() {
		return richiestaVisibileRuoliSiga;
	}
	public void setRichiestaVisibileRuoliSiga(String richiestaVisibileRuoliSiga) {
		this.richiestaVisibileRuoliSiga = richiestaVisibileRuoliSiga;
	}
	public String getCodiceAmbitoApplicativo() {
		return codiceAmbitoApplicativo;
	}
	public void setCodiceAmbitoApplicativo(String codiceAmbitoApplicativo) {
		this.codiceAmbitoApplicativo = codiceAmbitoApplicativo;
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
	public String getOraInizioElaborazioniRichiesta() {
		return oraInizioElaborazioniRichiesta;
	}
	public void setOraInizioElaborazioniRichiesta(
			String oraInizioElaborazioniRichiesta) {
		this.oraInizioElaborazioniRichiesta = oraInizioElaborazioniRichiesta;
	}
	public String getOraFineElaborazioniRichieste() {
		return oraFineElaborazioniRichieste;
	}
	public void setOraFineElaborazioniRichieste(String oraFineElaborazioniRichieste) {
		this.oraFineElaborazioniRichieste = oraFineElaborazioniRichieste;
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
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public String getNotaRichiesta() {
		return notaRichiesta;
	}
	public void setNotaRichiesta(String notaRichiesta) {
		this.notaRichiesta = notaRichiesta;
	}
	public String getAggiornaSistAutorizzazione() {
		return aggiornaSistAutorizzazione;
	}
	public void setAggiornaSistAutorizzazione(String aggiornaSistAutorizzazione) {
		this.aggiornaSistAutorizzazione = aggiornaSistAutorizzazione;
	}
	public String getCdrUtente() {
		return cdrUtente;
	}
	public void setCdrUtente(String cdrUtente) {
		this.cdrUtente = cdrUtente;
	}
	public String getCodApplicazione() {
		return codApplicazione;
	}
	public void setCodApplicazione(String codApplicazione) {
		this.codApplicazione = codApplicazione;
	}
	public String getIdRichiestaGenerata() {
		return idRichiestaGenerata;
	}
	public void setIdRichiestaGenerata(String idRichiestaGenerata) {
		this.idRichiestaGenerata = idRichiestaGenerata;
	}
	public String getDescrCdr() {
		return descrCdr;
	}
	public void setDescrCdr(String descrCdr) {
		this.descrCdr = descrCdr;
	}
	public String getDescrizioneApplicazione() {
		return descrizioneApplicazione;
	}
	public void setDescrizioneApplicazione(String descrizioneApplicazione) {
		this.descrizioneApplicazione = descrizioneApplicazione;
	}
	public String getNomeOperatore() {
		return nomeOperatore;
	}
	public void setNomeOperatore(String nomeOperatore) {
		this.nomeOperatore = nomeOperatore;
	}
	public String getCognomeOperatore() {
		return cognomeOperatore;
	}
	public void setCognomeOperatore(String cognomeOperatore) {
		this.cognomeOperatore = cognomeOperatore;
	}
	public String getIdRichiestaPau() {
		return idRichiestaPau;
	}
	public void setIdRichiestaPau(String idRichiestaPau) {
		this.idRichiestaPau = idRichiestaPau;
	}
	
}
