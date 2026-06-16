package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts.upload.FormFile;

public class CaricamentoMassivoEntity implements Serializable{


	private static final long serialVersionUID = 3765054944785162659L;
	
	// Dati per Elenco Caricamenti
	public String idCaricamento;
	public String dataCaricamento;
	public String descrizioneRichiestaCaricamento;
	public String statoRichiestaCaricamento;
	public String descrStatoRichiestaCaricamento;
	public String cfRichiedente;
	public String nomeRichiedente;
	public String cognomeRichiedente;
	public String cfAutorizzatore;
	public String nomeAutorizzatore;
	public String cognomeAutorizzatore;
	
	// Dati per il Dettaglio Caricamento
	public String idAmbito;
	public String descrizioneAmbito;
	public String anno;
	public String cfAmministratore;
	public String nomeAmministratore;
	public String cognomeAmministratore;
	public String noteRichiedente;
	public String noteAmministratore;
	public String oraCaricamento;
	public String dataCancellazione;
	public String oraCancellazione;
	public String dataControlliFormali;
	public String oraControlliFormali;
	public String dataCreazioneRichiesta;
	public String oraCreazioneRichiesta;
	public String dataTermineElaborazioneRichiesta;
	public String oraTermineElaborazioneRichiesta;
	public String numRecordFile;
	public String numRecordScartati;
	public String numRecordValidi;	
	public String numRecordEcaAggiunti;
	private String nomeFileCaricato;
	private FormFile fileAbilitazioni;
	private Set<FormFile> listaAllegatiRichiedente = new LinkedHashSet<FormFile> ();	
	private Set<FormFile> listaAllegatiAmministratore = new LinkedHashSet<FormFile> ();
	private long idAudit;
	private long id;
	private int idAmb;
	private byte[] bFileAbilitazioni;
	private String statoRichiesta;
	public boolean elaborazioneImmediata;
	
	public List<AllegatoCaricamentoMassivoEntity> allegatiRichOut;
	public List<AllegatoCaricamentoMassivoEntity> allegatiAmmOut;
	public String allegatiRichOutJson;
	public String allegatiAmmOutJson;
	public Long sequenceRichiesteGenerate;
	private boolean elaborazioneImmediataAbled;
	private int numMaxRecElabImm;
	private Date dataElaborazione;
	
	// Per aggiornamenti massivi visibilita
	private FormFile fileVisibilita;
	private byte[] bFileVisibilita;
	
	// Per aggiornamenti massivi richieste visibilita
	// Reply 05/2018
	private FormFile fileRichVisibilita;
	private byte[] bFileRichVisibilita;
	
	
	public String numInserimenti;
	public String numVisibilitaScartate;
	public String numVisibilitaValide;
	public String numVisibilitaGenerate;
	public String numRevoche;
	public String numRevocheScartate;
	public String numRevocheValide;
	public String numRevocheGenerate;
	public String numAzioniErrate;
	public String codiceEvento;
	
	// Per caricamenti massivi mappature abilitazioni uffici
	// Reply 02/2021
	private FormFile fileRichMappature;
	private byte[] bFileRichMappature;
	
	public String numMappature;
	public String numMappatureScartate;
	public String numMappatureValide;
	public String numMappatureGenerate;
	private String idAuditCaricamento;
		
	
	public String getCodiceEvento() {
		return codiceEvento;
	}
	public void setCodiceEvento(String codiceEvento) {
		this.codiceEvento = codiceEvento;
	}
	public String getNumRecordEcaAggiunti() {
		return numRecordEcaAggiunti;
	}
	public void setNumRecordEcaAggiunti(String numRecordEcaAggiunti) {
		this.numRecordEcaAggiunti = numRecordEcaAggiunti;
	}
	public boolean isElaborazioneImmediataAbled() {
		return elaborazioneImmediataAbled;
	}
	public void setElaborazioneImmediataAbled(boolean elaborazioneImmediataAbled) {
		this.elaborazioneImmediataAbled = elaborazioneImmediataAbled;
	}
	public String getIdCaricamento() {
		return idCaricamento;
	}
	public void setIdCaricamento(String idCaricamento) {
		this.idCaricamento = idCaricamento;
	}
	public String getDataCaricamento() {
		return dataCaricamento;
	}
	public void setDataCaricamento(String dataCaricamento) {
		this.dataCaricamento = dataCaricamento;
	}
	public String getDescrizioneRichiestaCaricamento() {
		return descrizioneRichiestaCaricamento;
	}
	public void setDescrizioneRichiestaCaricamento(
			String descrizioneRichiestaCaricamento) {
		this.descrizioneRichiestaCaricamento = descrizioneRichiestaCaricamento;
	}
	public String getStatoRichiestaCaricamento() {
		return statoRichiestaCaricamento;
	}
	public void setStatoRichiestaCaricamento(String statoRichiestaCaricamento) {
		this.statoRichiestaCaricamento = statoRichiestaCaricamento;
	}
	public String getCfRichiedente() {
		return cfRichiedente;
	}
	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}
	public String getNomeRichiedente() {
		return nomeRichiedente;
	}
	public void setNomeRichiedente(String nomeRichiedente) {
		this.nomeRichiedente = nomeRichiedente;
	}
	public String getCognomeRichiedente() {
		return cognomeRichiedente;
	}
	public void setCognomeRichiedente(String cognomeRichiedente) {
		this.cognomeRichiedente = cognomeRichiedente;
	}
	public String getCfAutorizzatore() {
		return cfAutorizzatore;
	}
	public void setCfAutorizzatore(String cfAutorizzatore) {
		this.cfAutorizzatore = cfAutorizzatore;
	}
	public String getNomeAutorizzatore() {
		return nomeAutorizzatore;
	}
	public void setNomeAutorizzatore(String nomeAutorizzatore) {
		this.nomeAutorizzatore = nomeAutorizzatore;
	}
	public String getCognomeAutorizzatore() {
		return cognomeAutorizzatore;
	}
	public void setCognomeAutorizzatore(String cognomeAutorizzatore) {
		this.cognomeAutorizzatore = cognomeAutorizzatore;
	}
	public String getDescrStatoRichiestaCaricamento() {
		return descrStatoRichiestaCaricamento;
	}
	public void setDescrStatoRichiestaCaricamento(
			String descrStatoRichiestaCaricamento) {
		this.descrStatoRichiestaCaricamento = descrStatoRichiestaCaricamento;
	}
	public String getIdAmbito() {
		return idAmbito;
	}
	public void setIdAmbito(String idAmbito) {
		this.idAmbito = idAmbito;
	}
	public String getDescrizioneAmbito() {
		return descrizioneAmbito;
	}
	public void setDescrizioneAmbito(String descrizioneAmbito) {
		this.descrizioneAmbito = descrizioneAmbito;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getNomeFileCaricato() {
		return nomeFileCaricato;
	}
	public void setNomeFileCaricato(String nomeFileCaricato) {
		this.nomeFileCaricato = nomeFileCaricato;
	}
	public FormFile getFileAbilitazioni() {
		return fileAbilitazioni;
	}
	public void setFileAbilitazioni(FormFile fileAbilitazioni) {
		this.fileAbilitazioni = fileAbilitazioni;
	}
	public Set<FormFile> getListaAllegatiRichiedente() {
		return listaAllegatiRichiedente;
	}
	public void setListaAllegatiRichiedente(Set<FormFile> listaAllegatiRichiedente) {
		this.listaAllegatiRichiedente = listaAllegatiRichiedente;
	}
	public Set<FormFile> getListaAllegatiAmministratore() {
		return listaAllegatiAmministratore;
	}
	public void setListaAllegatiAmministratore(
			Set<FormFile> listaAllegatiAmministratore) {
		this.listaAllegatiAmministratore = listaAllegatiAmministratore;
	}
	public byte[] getbFileAbilitazioni() {
		return bFileAbilitazioni;
	}
	public void setbFileAbilitazioni(byte[] bFileAbilitazioni) {
		this.bFileAbilitazioni = bFileAbilitazioni;
	}
	public String getStatoRichiesta() {
		return statoRichiesta;
	}
	public void setStatoRichiesta(String statoRichiesta) {
		this.statoRichiesta = statoRichiesta;
	}
	public int getIdAmb() {
		return idAmb;
	}
	public void setIdAmb(int idAmb) {
		this.idAmb = idAmb;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(long idAudit) {
		this.idAudit = idAudit;
	}
	public String getCfAmministratore() {
		return cfAmministratore;
	}
	public void setCfAmministratore(String cfAmministratore) {
		this.cfAmministratore = cfAmministratore;
	}
	public String getNomeAmministratore() {
		return nomeAmministratore;
	}
	public void setNomeAmministratore(String nomeAmministratore) {
		this.nomeAmministratore = nomeAmministratore;
	}
	public String getCognomeAmministratore() {
		return cognomeAmministratore;
	}
	public void setCognomeAmministratore(String cognomeAmministratore) {
		this.cognomeAmministratore = cognomeAmministratore;
	}
	public String getNoteRichiedente() {
		return noteRichiedente;
	}
	public void setNoteRichiedente(String noteRichiedente) {
		this.noteRichiedente = noteRichiedente;
	}
	public String getOraCaricamento() {
		return oraCaricamento;
	}
	public void setOraCaricamento(String oraCaricamento) {
		this.oraCaricamento = oraCaricamento;
	}
	public String getDataCancellazione() {
		return dataCancellazione;
	}
	public void setDataCancellazione(String dataCancellazione) {
		this.dataCancellazione = dataCancellazione;
	}
	public String getOraCancellazione() {
		return oraCancellazione;
	}
	public void setOraCancellazione(String oraCancellazione) {
		this.oraCancellazione = oraCancellazione;
	}
	public String getDataControlliFormali() {
		return dataControlliFormali;
	}
	public void setDataControlliFormali(String dataControlliFormali) {
		this.dataControlliFormali = dataControlliFormali;
	}
	public String getOraControlliFormali() {
		return oraControlliFormali;
	}
	public void setOraControlliFormali(String oraControlliFormali) {
		this.oraControlliFormali = oraControlliFormali;
	}
	public String getDataCreazioneRichiesta() {
		return dataCreazioneRichiesta;
	}
	public void setDataCreazioneRichiesta(String dataCreazioneRichiesta) {
		this.dataCreazioneRichiesta = dataCreazioneRichiesta;
	}
	public String getOraCreazioneRichiesta() {
		return oraCreazioneRichiesta;
	}
	public void setOraCreazioneRichiesta(String oraCreazioneRichiesta) {
		this.oraCreazioneRichiesta = oraCreazioneRichiesta;
	}
	public String getDataTermineElaborazioneRichiesta() {
		return dataTermineElaborazioneRichiesta;
	}
	public void setDataTermineElaborazioneRichiesta(
			String dataTermineElaborazioneRichiesta) {
		this.dataTermineElaborazioneRichiesta = dataTermineElaborazioneRichiesta;
	}
	public String getOraTermineElaborazioneRichiesta() {
		return oraTermineElaborazioneRichiesta;
	}
	public void setOraTermineElaborazioneRichiesta(
			String oraTermineElaborazioneRichiesta) {
		this.oraTermineElaborazioneRichiesta = oraTermineElaborazioneRichiesta;
	}
	public String getNumRecordFile() {
		return numRecordFile;
	}
	public void setNumRecordFile(String numRecordFile) {
		this.numRecordFile = numRecordFile;
	}
	public String getNumRecordScartati() {
		return numRecordScartati;
	}
	public void setNumRecordScartati(String numRecordScartati) {
		this.numRecordScartati = numRecordScartati;
	}
	public String getNumRecordValidi() {
		return numRecordValidi;
	}
	public void setNumRecordValidi(String numRecordValidi) {
		this.numRecordValidi = numRecordValidi;
	}
	public String getNoteAmministratore() {
		return noteAmministratore;
	}
	public void setNoteAmministratore(String noteAmministratore) {
		this.noteAmministratore = noteAmministratore;
	}
	public boolean isElaborazioneImmediata() {
		return elaborazioneImmediata;
	}
	public void setElaborazioneImmediata(boolean elaborazioneImmediata) {
		this.elaborazioneImmediata = elaborazioneImmediata;
	}
	public List<AllegatoCaricamentoMassivoEntity> getAllegatiRichOut() {
		return allegatiRichOut;
	}
	public void setAllegatiRichOut(
			List<AllegatoCaricamentoMassivoEntity> allegatiRichOut) {
		this.allegatiRichOut = allegatiRichOut;
	}
	public List<AllegatoCaricamentoMassivoEntity> getAllegatiAmmOut() {
		return allegatiAmmOut;
	}
	public void setAllegatiAmmOut(
			List<AllegatoCaricamentoMassivoEntity> allegatiAmmOut) {
		this.allegatiAmmOut = allegatiAmmOut;
	}
	public String getAllegatiRichOutJson() {
		return allegatiRichOutJson;
	}
	public void setAllegatiRichOutJson(String allegatiRichOutJson) {
		this.allegatiRichOutJson = allegatiRichOutJson;
	}
	public String getAllegatiAmmOutJson() {
		return allegatiAmmOutJson;
	}
	public void setAllegatiAmmOutJson(String allegatiAmmOutJson) {
		this.allegatiAmmOutJson = allegatiAmmOutJson;
	}
	public Long getSequenceRichiesteGenerate() {
		return sequenceRichiesteGenerate;
	}
	public void setSequenceRichiesteGenerate(Long sequenceRichiesteGenerate) {
		this.sequenceRichiesteGenerate = sequenceRichiesteGenerate;
	}
	public int getNumMaxRecElabImm() {
		return numMaxRecElabImm;
	}
	public void setNumMaxRecElabImm(int numMaxRecElabImm) {
		this.numMaxRecElabImm = numMaxRecElabImm;
	}
	public FormFile getFileVisibilita() {
		return fileVisibilita;
	}
	public void setFileVisibilita(FormFile fileVisibilita) {
		this.fileVisibilita = fileVisibilita;
	}
	public byte[] getbFileVisibilita() {
		return bFileVisibilita;
	}
	public void setbFileVisibilita(byte[] bFileVisibilita) {
		this.bFileVisibilita = bFileVisibilita;
	}
	public Date getDataElaborazione() {
		return dataElaborazione;
	}
	public void setDataElaborazione(Date dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}
	public FormFile getFileRichVisibilita() {
		return fileRichVisibilita;
	}
	public void setFileRichVisibilita(FormFile fileRichVisibilita) {
		this.fileRichVisibilita = fileRichVisibilita;
	}
	public byte[] getbFileRichVisibilita() {
		return bFileRichVisibilita;
	}
	public void setbFileRichVisibilita(byte[] bFileRichVisibilita) {
		this.bFileRichVisibilita = bFileRichVisibilita;
	}
	public String getNumInserimenti() {
		return numInserimenti;
	}
	public void setNumInserimenti(String numInserimenti) {
		this.numInserimenti = numInserimenti;
	}
	public String getNumVisibilitaScartate() {
		return numVisibilitaScartate;
	}
	public void setNumVisibilitaScartate(String numVisibilitaScartate) {
		this.numVisibilitaScartate = numVisibilitaScartate;
	}
	public String getNumVisibilitaValide() {
		return numVisibilitaValide;
	}
	public void setNumVisibilitaValide(String numVisibilitaValide) {
		this.numVisibilitaValide = numVisibilitaValide;
	}
	public String getNumVisibilitaGenerate() {
		return numVisibilitaGenerate;
	}
	public void setNumVisibilitaGenerate(String numVisibilitaGenerate) {
		this.numVisibilitaGenerate = numVisibilitaGenerate;
	}
	public String getNumRevocheScartate() {
		return numRevocheScartate;
	}
	public void setNumRevocheScartate(String numRevocheScartate) {
		this.numRevocheScartate = numRevocheScartate;
	}
	
	public String getNumRevocheGenerate() {
		return numRevocheGenerate;
	}
	public void setNumRevocheGenerate(String numRevocheGenerate) {
		this.numRevocheGenerate = numRevocheGenerate;
	}
	public String getNumRevoche() {
		return numRevoche;
	}
	public void setNumRevoche(String numRevoche) {
		this.numRevoche = numRevoche;
	}
	public String getNumRevocheValide() {
		return numRevocheValide;
	}
	public void setNumRevocheValide(String numRevocheValide) {
		this.numRevocheValide = numRevocheValide;
	}
	public String getNumAzioniErrate() {
		return numAzioniErrate;
	}
	public void setNumAzioniErrate(String numAzioniErrate) {
		this.numAzioniErrate = numAzioniErrate;
	}
	public FormFile getFileRichMappature() {
		return fileRichMappature;
	}
	public void setFileRichMappature(FormFile fileRichMappature) {
		this.fileRichMappature = fileRichMappature;
	}
	public byte[] getbFileRichMappature() {
		return bFileRichMappature;
	}
	public void setbFileRichMappature(byte[] bFileRichMappature) {
		this.bFileRichMappature = bFileRichMappature;
	}
	public String getNumMappatureScartate() {
		return numMappatureScartate;
	}
	public void setNumMappatureScartate(String numMappatureScartate) {
		this.numMappatureScartate = numMappatureScartate;
	}
	public String getNumMappatureValide() {
		return numMappatureValide;
	}
	public void setNumMappatureValide(String numMappatureValide) {
		this.numMappatureValide = numMappatureValide;
	}
	public String getNumMappatureGenerate() {
		return numMappatureGenerate;
	}
	public void setNumMappatureGenerate(String numMappatureGenerate) {
		this.numMappatureGenerate = numMappatureGenerate;
	}
	public String getNumMappature() {
		return numMappature;
	}
	public void setNumMappature(String numMappature) {
		this.numMappature = numMappature;
	}
	public String getIdAuditCaricamento() {
		return idAuditCaricamento;
	}
	public void setIdAuditCaricamento(String idAuditCaricamento) {
		this.idAuditCaricamento = idAuditCaricamento;
	}
	
	
}
