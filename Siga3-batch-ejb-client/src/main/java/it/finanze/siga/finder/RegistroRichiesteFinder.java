package it.finanze.siga.finder;

import java.io.Serializable;
import java.util.Date;

public class RegistroRichiesteFinder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idRichiesta;
	private String tipoRichiesta;
	private String codiceAmbito;
	private String cfUtente;
	private String cdrUtente;
	private int iterID;
	private String cfRichiedente; 
	private String cfRichiedenteEffettivo; 
	private String cdrRichiedente;
 	private String noteRichiedente; 
	private Date dataRichiesta; 
	private String cdrAutorizzatore1; 
	private String cfAutorizzatore1; 
	private String cfAutorizzatore1Effettivo; 
	private String noteAutorizzatore1;
	private Date dataAutorizzatore1;
	private String cdrAutorizzatore2;
	private String cfAutorizzatore2;
	private String cfAutorizzatore2Effettivo;
	private String noteAutorizzatore2;
	private Date dataAutorizzazione2;
	private String strutturaGestoreOperatori;
	private String cdrGestoreOperatori;
	private String cfGestoreEffettivo; 
	private String noteGestore; 
	private Date dataEsecuzione; 
	private String esitoFinale; 
	private Date dataEsitoFinale; 
	private Date dataPresaVisione; 
	private String	statoRichiesta; 
	private Date dataChiusuraRichiesta; 
	private String cfChiusuraRichiesta; 
	private String noteChiusuraRichiesta;
	private String cfPresaInCarico;
	private String cfArchiviazioneEffettivo;
	private String cfArchiviazione;
	private String cdrArchiviazione;
	private String operazione;

	
	private String cfAutorizzatore1Nuovo;
	private String cdrAutorizzatore1Nuovo;
	
	public int getIdRichiesta() {
		return idRichiesta;
	}
	public void setIdRichiesta(int idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
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
	public int getIterID() {
		return iterID;
	}
	public void setIterID(int iterID) {
		this.iterID = iterID;
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
	public String getCdrRichiedente() {
		return cdrRichiedente;
	}
	public void setCdrRichiedente(String cdrRichiedente) {
		this.cdrRichiedente = cdrRichiedente;
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
	public String getCdrAutorizzatore1() {
		return cdrAutorizzatore1;
	}
	public void setCdrAutorizzatore1(String cdrAutorizzatore1) {
		this.cdrAutorizzatore1 = cdrAutorizzatore1;
	}
	public String getCfAutorizzatore1() {
		return cfAutorizzatore1;
	}
	public void setCfAutorizzatore1(String cfAutorizzatore1) {
		this.cfAutorizzatore1 = cfAutorizzatore1;
	}
	public String getCfAutorizzatore1Effettivo() {
		return cfAutorizzatore1Effettivo;
	}
	public void setCfAutorizzatore1Effettivo(String cfAutorizzatore1Effettivo) {
		this.cfAutorizzatore1Effettivo = cfAutorizzatore1Effettivo;
	}
	public String getNoteAutorizzatore1() {
		return noteAutorizzatore1;
	}
	public void setNoteAutorizzatore1(String noteAutorizzatore1) {
		this.noteAutorizzatore1 = noteAutorizzatore1;
	}
	public Date getDataAutorizzatore1() {
		return dataAutorizzatore1;
	}
	public void setDataAutorizzatore1(Date dataAutorizzatore1) {
		this.dataAutorizzatore1 = dataAutorizzatore1;
	}
	public String getCdrAutorizzatore2() {
		return cdrAutorizzatore2;
	}
	public void setCdrAutorizzatore2(String cdrAutorizzatore2) {
		this.cdrAutorizzatore2 = cdrAutorizzatore2;
	}
	public String getCfAutorizzatore2() {
		return cfAutorizzatore2;
	}
	public void setCfAutorizzatore2(String cfAutorizzatore2) {
		this.cfAutorizzatore2 = cfAutorizzatore2;
	}
	public String getCfAutorizzatore2Effettivo() {
		return cfAutorizzatore2Effettivo;
	}
	public void setCfAutorizzatore2Effettivo(String cfAutorizzatore2Effettivo) {
		this.cfAutorizzatore2Effettivo = cfAutorizzatore2Effettivo;
	}
	public String getNoteAutorizzatore2() {
		return noteAutorizzatore2;
	}
	public void setNoteAutorizzatore2(String noteAutorizzatore2) {
		this.noteAutorizzatore2 = noteAutorizzatore2;
	}
	public Date getDataAutorizzazione2() {
		return dataAutorizzazione2;
	}
	public void setDataAutorizzazione2(Date dataAutorizzazione2) {
		this.dataAutorizzazione2 = dataAutorizzazione2;
	}
	public String getStrutturaGestoreOperatori() {
		return strutturaGestoreOperatori;
	}
	public void setStrutturaGestoreOperatori(String strutturaGestoreOperatori) {
		this.strutturaGestoreOperatori = strutturaGestoreOperatori;
	}
	public String getCdrGestoreOperatori() {
		return cdrGestoreOperatori;
	}
	public void setCdrGestoreOperatori(String cdrGestoreOperatori) {
		this.cdrGestoreOperatori = cdrGestoreOperatori;
	}
	public String getCfGestoreEffettivo() {
		return cfGestoreEffettivo;
	}
	public void setCfGestoreEffettivo(String cfGestoreEffettivo) {
		this.cfGestoreEffettivo = cfGestoreEffettivo;
	}
	public String getNoteGestore() {
		return noteGestore;
	}
	public void setNoteGestore(String noteGestore) {
		this.noteGestore = noteGestore;
	}
	public Date getDataEsecuzione() {
		return dataEsecuzione;
	}
	public void setDataEsecuzione(Date dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}
	public String getEsitoFinale() {
		return esitoFinale;
	}
	public void setEsitoFinale(String esitoFinale) {
		this.esitoFinale = esitoFinale;
	}
	public Date getDataEsitoFinale() {
		return dataEsitoFinale;
	}
	public void setDataEsitoFinale(Date dataEsitoFinale) {
		this.dataEsitoFinale = dataEsitoFinale;
	}
	public Date getDataPresaVisione() {
		return dataPresaVisione;
	}
	public void setDataPresaVisione(Date dataPresaVisione) {
		this.dataPresaVisione = dataPresaVisione;
	}
	public String getStatoRichiesta() {
		return statoRichiesta;
	}
	public void setStatoRichiesta(String statoRichiesta) {
		this.statoRichiesta = statoRichiesta;
	}
	public Date getDataChiusuraRichiesta() {
		return dataChiusuraRichiesta;
	}
	public void setDataChiusuraRichiesta(Date dataChiusuraRichiesta) {
		this.dataChiusuraRichiesta = dataChiusuraRichiesta;
	}
	public String getCfChiusuraRichiesta() {
		return cfChiusuraRichiesta;
	}
	public void setCfChiusuraRichiesta(String cfChiusuraRichiesta) {
		this.cfChiusuraRichiesta = cfChiusuraRichiesta;
	}
	public String getNoteChiusuraRichiesta() {
		return noteChiusuraRichiesta;
	}
	public void setNoteChiusuraRichiesta(String noteChiusuraRichiesta) {
		this.noteChiusuraRichiesta = noteChiusuraRichiesta;
	}
	public String getCfPresaInCarico() {
		return cfPresaInCarico;
	}
	public void setCfPresaInCarico(String cfPresaInCarico) {
		this.cfPresaInCarico = cfPresaInCarico;
	}
	public String getCfAutorizzatore1Nuovo() {
		return cfAutorizzatore1Nuovo;
	}
	public void setCfAutorizzatore1Nuovo(String cfAutorizzatore1Nuovo) {
		this.cfAutorizzatore1Nuovo = cfAutorizzatore1Nuovo;
	}
	public String getCdrAutorizzatore1Nuovo() {
		return cdrAutorizzatore1Nuovo;
	}
	public void setCdrAutorizzatore1Nuovo(String cdrAutorizzatore1Nuovo) {
		this.cdrAutorizzatore1Nuovo = cdrAutorizzatore1Nuovo;
	}
	public String getCfArchiviazioneEffettivo() {
		return cfArchiviazioneEffettivo;
	}
	public void setCfArchiviazioneEffettivo(String cfArchiviazioneEffettivo) {
		this.cfArchiviazioneEffettivo = cfArchiviazioneEffettivo;
	}
	public String getOperazione() {
		return operazione;
	}
	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}
	public String getCfArchiviazione() {
		return cfArchiviazione;
	}
	public void setCfArchiviazione(String cfArchiviazione) {
		this.cfArchiviazione = cfArchiviazione;
	}
	public String getCdrArchiviazione() {
		return cdrArchiviazione;
	}
	public void setCdrArchiviazione(String cdrArchiviazione) {
		this.cdrArchiviazione = cdrArchiviazione;
	}
	
	
}
