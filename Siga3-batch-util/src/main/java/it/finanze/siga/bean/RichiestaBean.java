package it.finanze.siga.bean;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.apache.struts.upload.FormFile;

public class RichiestaBean implements Serializable, Cloneable { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2781084256492422360L;

 	List<ProfiloRichiestaBean> listProfili;
	List<ProfiloRichiestaBean> abilitati;
	List<ProfiloRichiestaBean> disabilitati;
	List<InterrProfiliOperatoreBean> profiloRisultante;

	private Integer idRichiesta;
	private Integer idRichiestaSeq;

	private String tipoRichiesta;
	private Integer codiceAmbito;
	private String descrAmbito;
	private String sincronizzata;
	private Integer idIter;
	private String codiceApp;
	private Date dataEsecuzione;
	private String statoRichiesta;
	private String descrStato;
	private String cancellabile;
	private Date dataChiusuraRichiesta;
	private String cfChiusuraRichiesta;
	
	private String ruoloChiusura;
	private String noteChiusuraRichiesta;
	private String esitoFinale;
	private String descrEsitoFinale;
	private Date dataEsitoFinale;
	private Date dataPresaVisione;
	private CDRBean cdrVisibilita;	
 	private Date dataRichiesta;
	
	private String cfRichiedente;
	private String cdrRichiedente;
	private String descrCdrRichiedente;
	private String cfRichiedenteEffettivo; 
	private String nomeRichiedenteEffettivo; 
	private String cognomeRichiedenteEffettivo; 
	private String noteRichiedente;
	private String nomeRichiedente;
	private String cognomeRichiedente;
	private String cognomeNomeRichiedente;
	private String cognomeNomeRichiedenteEffettivo;
	private String emailRichiedente;

	
	private String cdrUtente;
	private String descrCdrUtente;
	private String cfUtente;
	private String nomeUtente;
	private String cognomeUtente;
	private String cognomeNomeUtente;
	private String cfUteLogin;
	private String emailUtente;

	
 	private String nomeAutorizzatoreI;
	private String cognomeAutorizzatoreI;
 	private String cdrAutorizzatoreI;
 	private String descrCdrAutorizzatoreI;
 	private String cfAutorizzatoreI;
 	private String cfAutorizzatoreIEffettivo;
 	private String nomeAutorizzatoreIEffettivo;
 	private String cognomeAutorizzatoreIEffettivo;
	private String noteAutorizzatoreI;
	private Date dataAutorizzazioneI;
	private String cognomeNomeAutorizzatoreI;
	private String cognomeNomeAutorizzatoreIEffettivo;
	
	
	private String nomeAutorizzatoreII;
	private String cognomeAutorizzatoreII;
	private String cdrAutorizzatoreII;
	private String descrCdrAutorizzatoreII;
 	private String cfAutorizzatoreII;
 	private String cfAutorizzatoreIIEffettivo;
 	private String nomeAutorizzatoreIIEffettivo;
 	private String cognomeAutorizzatoreIIEffettivo;
	private String noteAutorizzatoreII;
	private Date dataAutorizzazioneII;
	private String cognomeNomeAutorizzatoreII;
	private String cognomeNomeAutorizzatoreIIEffettivo;

	
	private String nomeGestoreOperatori;
	private String cognomeGestoreOperatori;
	private String strutturaGestoreOperatori;
	private String cdrGestoreOperatori;
	
	private String descrCdrGestoreOperatori;
	private String descrStruttGestoreOperatori;
	private String codiceStruttGestore;
	private String cfGestoreOperatoriEffettivo;
	private String nomeGestoreOperatoriEffettivo;
	private String cognomeGestoreOperatoriEffettivo;
	private String noteGestore;
	private String cognomeNomeGestore;
	private String cognomeNomeGestoreEffettivo;
	
	private String cfPresaInCarico;	
	private String nomePresaInCarico;
	private String cognomePresaInCarico;
	private String cognomeNomePresaInCarico;
	
	// visualizzazione popup attori fase
	//private String cfAttoreFase;
	private List<AttoreBean> attoriFase;
	
	// richiesta visibilita'	
	private String cdrVisibilitaCodice;
	private java.util.Date dataFineValidita;
	
	 private String descrUffProvenienza;
	 private String cdrUffProvenienza;
	
	 private String descrUffDestinazione;
	 private String cdrUffDestinazione;
	 
	 private String cdrCodeVisibilita;
	 private String descrCdrVisibilita;
	 private String descrCdr;
	 
	 private Integer idAudit;
	 private boolean rewrite = false;
	 private String nomeChiusuraRichiesta; 
	 private String cognomeChiusuraRichiesta; 
	 
	 private String descrTipoRichiesta;
	 
	 // Profile Report
	 private String roleGroupDescPR;
	 private String profiloDescPR;
	 private java.util.Date dataScadenzaPR;
	 private String tipoAbilitazionecPR;
	 private String tipoOperazionePR;
	 //
	 
	 private String cdrRichiedenteEffettivo;
	 private String cdrAutorizzatoreIEffettivo;
	 private String cdrAutorizzatoreIIEffettivo;
	 private String cdrGestoreOperatoriEffettivo;
	 private String intestazioneExport;
	 
	 //archiviazione
	 private String cfArchiviazione;
	 private String cfArchiviazioneEffettivo;
	 private String cognomeArchiviazioneEffettivo;
	 private String nomeArchiviazioneEffettivo;
	 private String cognomeArchiviazione;
	 private String nomeArchiviazione;
	 private String cdrArchiviazione;
	 private String descCdrArchiviazione;
	 private String cognomeNomeArchiviazioneEffettivo;
	 private String cognomeNomeArchiviazione;	 
	 private String noteArchiviazione;
	 
 	 private String cancellaUtente;
 	 
	 private String sistemaAutorizz;
	 
	//flag generazione richieste bath
		private String richiedenteAc;
		private String presaVisione;
		private String aggiornaSistemaAutorizzativo;
		private String codEvento;
		private String visRuoliSiga;
		private String cancUteEsterno;
		private String noteGenRichiesta;
 
	 //tipoAbilitazione
	 private String tipoAbilitazione;
	 
	// campi per L'AC
	private String origineAbilitazione;
	private String flagRichiestaAut;
	private String flag;
	private String esecutivitaCAU;
	
	private String origineRichiesta;
	
	private String origineRichiestaDesc;
	
	private String altroUfficioInteressato;
	private String descrFaseSuccessiva;
	private String nomeFaseSuccessiva;
	
	private Integer idAuditFine;
	
	private String flagGestorePresainCarico;
	
	public String getFlagGestorePresainCarico() {
		return flagGestorePresainCarico;
	}

	public void setFlagGestorePresainCarico(String flagGestorePresainCarico) {
		this.flagGestorePresainCarico = flagGestorePresainCarico;
	}

	public String getOrigineRichiestaDesc() {
		return origineRichiestaDesc;
	}

	public void setOrigineRichiestaDesc(String origineRichiestaDesc) {
		this.origineRichiestaDesc = origineRichiestaDesc;
	}

	public String getOrigineRichiesta() {
		return origineRichiesta;
	}

	public void setOrigineRichiesta(String origineRichiesta) {
		this.origineRichiesta = origineRichiesta;
	}

	public String getIntestazioneExport() {
		return intestazioneExport;
	}

	public void setIntestazioneExport(String intestazioneExport) {
		this.intestazioneExport = intestazioneExport;
	}

	public String getDescrTipoRichiesta() {
		
		String descr="";
		
		if (this.tipoRichiesta.equals("P"))
			descr= "Profilazione";
		if (this.tipoRichiesta.equals("C"))
			descr="Cambio Ufficio";
		if (this.tipoRichiesta.equals("V"))
			descr= "Visibilita'";
		if (this.tipoRichiesta.equals("R"))
			descr= "Revoca della Visibilita'";
		
		return descr;
	}

	/**
	 * @return the descrCdr
	 */
	public String getDescrCdr() {
		return descrCdr;
	}

	/**
	 * @param descrCdr the descrCdr to set
	 */
	public void setDescrCdr(String descrCdr) {
		this.descrCdr = descrCdr;
	}
	/**
	 * per salvare piu' file della richiesta
	 */
	private Set<FormFile> formFile;
	
	/** estrazione dei file dalla richiesta **/
	
	private List <FileBean> listaFiles;
	
	public boolean isRewrite() {
		return rewrite;
	}
	
	public void setRewrite(boolean rewrite) {
		this.rewrite = rewrite;
	}
 
	public List<FileBean> getListaFiles() {
		return listaFiles;
	}
	public void setListaFiles(List<FileBean> listaFiles) {
		this.listaFiles = listaFiles;
	}
	/** GETTERS AND SETTERS **/
	public String getDescrEsitoFinale() {
		return descrEsitoFinale;
	}
	public void setDescrEsitoFinale(String descrEsitoFinale) {
		this.descrEsitoFinale = descrEsitoFinale;
	}
	public String getDescrAmbito() {
		return descrAmbito;
	}
	
	public void setDescrAmbito(String descrAmbito) {
		this.descrAmbito = descrAmbito;
	}
	
	public CDRBean getCdrVisibilita() {
		return cdrVisibilita;
	}
	
	public void setCdrVisibilita(CDRBean cdrVisibilita) {
		this.cdrVisibilita = cdrVisibilita;
	}
	
	public Set<FormFile> getFormFile() {
		return formFile;
	}

	public void setFormFile(Set<FormFile> formFile) {
		this.formFile = formFile;
	}
	
	public Integer getCodiceAmbito() {
		return codiceAmbito;
	}

	public void setCodiceAmbito(Integer codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
 
 
	public List<ProfiloRichiestaBean> getListProfili() {
		return listProfili;
	}

	public void setListProfili(List<ProfiloRichiestaBean> listProfili) {
		this.listProfili = listProfili;
	}

	public Integer getIdRichiesta() {
		return idRichiesta;
	}

	public void setIdRichiesta(Integer idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getTipoRichiesta() {
		return tipoRichiesta;
	}

	public void setTipoRichiesta(String tipoRichiesta) {
		this.tipoRichiesta = tipoRichiesta;
	}

 

	public String getCdrUtente() {
		return cdrUtente;
	}

	public void setCdrUtente(String cdrUtente) {
		this.cdrUtente = cdrUtente;
	}

	public String getCfUtente() {
		return cfUtente;
	}

	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}

	public Integer getIdIter() {
		return idIter;
	}

	public void setIdIter(Integer idIter) {
		this.idIter = idIter;
	}

	public String getCodiceApp() {
		return codiceApp;
	}

	public void setCodiceApp(String codiceApp) {
		this.codiceApp = codiceApp;
	}

	public String getCfRichiedente() {
		return cfRichiedente;
	}

	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}

	public String getCdrRichiedente() {
		return cdrRichiedente;
	}

	public void setCdrRichiedente(String cdrRichiedente) {
		this.cdrRichiedente = cdrRichiedente;
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dataRichiesta!=null)
		sdf.format(dataRichiesta);
		return dataRichiesta;
	}
	public String getStringDataRichiesta() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dataRichiesta!=null)
			return sdf.format(dataRichiesta);
		else
			return "";
	}

	public void setDataRichiesta(Date dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}

	public String getCdrAutorizzatoreI() {
		return cdrAutorizzatoreI;
	}

	public void setCdrAutorizzatoreI(String cdrAutorizzatoreI) {
		this.cdrAutorizzatoreI = cdrAutorizzatoreI;
	}

	public String getCfAutorizzatoreI() {
		return cfAutorizzatoreI;
	}

	public void setCfAutorizzatoreI(String cfAutorizzatoreI) {
		this.cfAutorizzatoreI = cfAutorizzatoreI;
	}

	public String getCfAutorizzatoreIEffettivo() {
		return cfAutorizzatoreIEffettivo;
	}

	public void setCfAutorizzatoreIEffettivo(String cfAutorizzatoreIEffettivo) {
		this.cfAutorizzatoreIEffettivo = cfAutorizzatoreIEffettivo;
	}

	public String getNoteAutorizzatoreI() {
		return noteAutorizzatoreI;
	}

	public void setNoteAutorizzatoreI(String noteAutorizzatoreI) {
		this.noteAutorizzatoreI = noteAutorizzatoreI;
	}

	public Date getDataAutorizzazioneI() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dataAutorizzazioneI!=null)
		sdf.format(dataAutorizzazioneI);
		return dataAutorizzazioneI;
		
		
	}

	public void setDataAutorizzazioneI(Date dataAutorizzazioneI) {
		this.dataAutorizzazioneI = dataAutorizzazioneI;
	}

	public String getCdrAutorizzatoreII() {
		return cdrAutorizzatoreII;
	}

	public void setCdrAutorizzatoreII(String cdrAutorizzatoreII) {
		this.cdrAutorizzatoreII = cdrAutorizzatoreII;
	}

	public String getCfAutorizzatoreII() {
		return cfAutorizzatoreII;
	}

	public void setCfAutorizzatoreII(String cfAutorizzatoreII) {
		this.cfAutorizzatoreII = cfAutorizzatoreII;
	}

	public String getCfAutorizzatoreIIEffettivo() {
		return cfAutorizzatoreIIEffettivo;
	}

	public void setCfAutorizzatoreIIEffettivo(String cfAutorizzatoreIIEffettivo) {
		this.cfAutorizzatoreIIEffettivo = cfAutorizzatoreIIEffettivo;
	}

	public String getNoteAutorizzatoreII() {
		return noteAutorizzatoreII;
	}

	public void setNoteAutorizzatoreII(String noteAutorizzatoreII) {
		this.noteAutorizzatoreII = noteAutorizzatoreII;
	}

	public Date getDataAutorizzazioneII() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dataAutorizzazioneII!=null)
		sdf.format(dataAutorizzazioneII);
		return dataAutorizzazioneII;
	}

	public void setDataAutorizzazioneII(Date dataAutorizzazioneII) {
		this.dataAutorizzazioneII = dataAutorizzazioneII;
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

	public String getCfGestoreOperatoriEffettivo() {
		return cfGestoreOperatoriEffettivo;
	}

	public void setCfGestoreOperatoriEffettivo(String cfGestoreOperatoriEffettivo) {
		this.cfGestoreOperatoriEffettivo = cfGestoreOperatoriEffettivo;
	}

	public String getNoteGestore() {
		return noteGestore;
	}

	public void setNoteGestore(String noteGestore) {
		this.noteGestore = noteGestore;
	}

	public Date getDataEsecuzione() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dataEsecuzione!=null)
		sdf.format(dataEsecuzione);
		
		return dataEsecuzione;
	}

	public void setDataEsecuzione(Date dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}

	public String getStatoRichiesta() {
		return statoRichiesta;
	}

	public void setStatoRichiesta(String statoRichiesta) {
		this.statoRichiesta = statoRichiesta;
	}

	public Date getDataChiusuraRichiesta() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dataChiusuraRichiesta!=null)
		sdf.format(dataChiusuraRichiesta);
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

	public String getEsitoFinale() {
		return esitoFinale;
	}

	public void setEsitoFinale(String esitoFinale) {
		this.esitoFinale = esitoFinale;
	}

	public Date getDataEsitoFinale() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dataEsitoFinale!=null)
		sdf.format(dataEsitoFinale);
		return dataEsitoFinale;
	}

	public void setDataEsitoFinale(Date dataEsitoFinale) {
		this.dataEsitoFinale = dataEsitoFinale;
	}

	public Date getDataPresaVisione() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dataPresaVisione!=null)
		sdf.format(dataPresaVisione);
		return dataPresaVisione;
	}

	public void setDataPresaVisione(Date dataPresaVisione) {
		this.dataPresaVisione = dataPresaVisione;
	}

	public String getCfPresaInCarico() {
		return cfPresaInCarico;
	}

	public void setCfPresaInCarico(String cfPresaInCarico) {
		this.cfPresaInCarico = cfPresaInCarico;
	}


	public String getDescrStato() {
		return descrStato;
	}

	public void setDescrStato(String descrStato) {
		this.descrStato = descrStato;
	}

	public String getNomeRichiedente() {
		return nomeRichiedente;
	}



	public String getCognomeRichiedente() {
		return cognomeRichiedente;
	}



	public String getNomeAutorizzatoreI() {
		return nomeAutorizzatoreI;
	}



	public String getCognomeAutorizzatoreI() {
		return cognomeAutorizzatoreI;
	}



	public String getNomeAutorizzatoreII() {
		return nomeAutorizzatoreII;
	}

	

	public String getCognomeAutorizzatoreII() {
		return cognomeAutorizzatoreII;
	}

	

	public String getNomeGestoreOperatori() {
		return nomeGestoreOperatori;
	}

	

	public String getCognomeGestoreOperatori() {
		return cognomeGestoreOperatori;
	}



	public String getNomePresaInCarico() {
		return nomePresaInCarico;
	}


	public String getCognomePresaInCarico() {
		return cognomePresaInCarico;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getCognomeUtente() {
		return cognomeUtente;
	}

	public void setCognomeUtente(String cognomeUtente) {
		this.cognomeUtente = cognomeUtente;
	}

	public String getDescrCdrUtente() {
		return descrCdrUtente;
	}

	public void setDescrCdrUtente(String descrCdrUtente) {
		this.descrCdrUtente = descrCdrUtente;
	}

	public String getDescrCdrRichiedente() {
		return descrCdrRichiedente;
	}

	public void setDescrCdrRichiedente(String descrCdrRichiedente) {
		this.descrCdrRichiedente = descrCdrRichiedente;
	}

	public String getDescrCdrAutorizzatoreI() {
		return descrCdrAutorizzatoreI;
	}

	public void setDescrCdrAutorizzatoreI(String descrCdrAutorizzatoreI) {
		this.descrCdrAutorizzatoreI = descrCdrAutorizzatoreI;
	}

	public String getDescrCdrAutorizzatoreII() {
		return descrCdrAutorizzatoreII;
	}

	public void setDescrCdrAutorizzatoreII(String descrCdrAutorizzatoreII) {
		this.descrCdrAutorizzatoreII = descrCdrAutorizzatoreII;
	}

	public String getDescrCdrGestoreOperatori() {
		return descrCdrGestoreOperatori;
	}

	public void setDescrCdrGestoreOperatori(String descrCdrGestoreOperatori) {
		this.descrCdrGestoreOperatori = descrCdrGestoreOperatori;
	}

	public void setNomeRichiedente(String nomeRichiedente) {
		this.nomeRichiedente = nomeRichiedente;
	}

	public void setCognomeRichiedente(String cognomeRichiedente) {
		this.cognomeRichiedente = cognomeRichiedente;
	}

	public void setNomeAutorizzatoreI(String nomeAutorizzatoreI) {
		this.nomeAutorizzatoreI = nomeAutorizzatoreI;
	}

	public void setCognomeAutorizzatoreI(String cognomeAutorizzatoreI) {
		this.cognomeAutorizzatoreI = cognomeAutorizzatoreI;
	}

	public void setNomeAutorizzatoreII(String nomeAutorizzatoreII) {
		this.nomeAutorizzatoreII = nomeAutorizzatoreII;
	}

	public void setCognomeAutorizzatoreII(String cognomeAutorizzatoreII) {
		this.cognomeAutorizzatoreII = cognomeAutorizzatoreII;
	}

	public void setNomeGestoreOperatori(String nomeGestoreOperatori) {
		this.nomeGestoreOperatori = nomeGestoreOperatori;
	}

	public void setCognomeGestoreOperatori(String cognomeGestoreOperatori) {
		this.cognomeGestoreOperatori = cognomeGestoreOperatori;
	}

	public void setNomePresaInCarico(String nomePresaInCarico) {
		this.nomePresaInCarico = nomePresaInCarico;
	}

	public void setCognomePresaInCarico(String cognomePresaInCarico) {
		this.cognomePresaInCarico = cognomePresaInCarico;
	}
 

	public String getCancellabile() {
		return cancellabile;
	}

	public void setCancellabile(String cancellabile) {
		this.cancellabile = cancellabile;
	}
	public String getCdrVisibilitaCodice() {
		return cdrVisibilitaCodice;
	}
	public void setCdrVisibilitaCodice(String cdrVisibilitaCodice) {
		this.cdrVisibilitaCodice = cdrVisibilitaCodice;
	}
	


	public List<AttoreBean> getAttoriFase() {
		return attoriFase;
	}

	public void setAttoriFase(List<AttoreBean> attoriFase) {
		this.attoriFase = attoriFase;
	}

	public java.util.Date getDataFineValidita() {		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(dataFineValidita!=null)
			sdf.format(dataFineValidita);		
		return dataFineValidita;
	}
	public void setDataFineValidita(java.util.Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	public String getNomeRichiedenteEffettivo() {
		return nomeRichiedenteEffettivo;
	}
	public void setNomeRichiedenteEffettivo(String nomeRichiedenteEffettivo) {
		this.nomeRichiedenteEffettivo = nomeRichiedenteEffettivo;
	}
	public String getCognomeRichiedenteEffettivo() {
		return cognomeRichiedenteEffettivo;
	}
	public void setCognomeRichiedenteEffettivo(String cognomeRichiedenteEffettivo) {
		this.cognomeRichiedenteEffettivo = cognomeRichiedenteEffettivo;
	}
	public String getNomeAutorizzatoreIEffettivo() {
		return nomeAutorizzatoreIEffettivo;
	}
	public void setNomeAutorizzatoreIEffettivo(String nomeAutorizzatoreIEffettivo) {
		this.nomeAutorizzatoreIEffettivo = nomeAutorizzatoreIEffettivo;
	}
	public String getCognomeAutorizzatoreIEffettivo() {
		return cognomeAutorizzatoreIEffettivo;
	}
	public void setCognomeAutorizzatoreIEffettivo(
			String cognomeAutorizzatoreIEffettivo) {
		this.cognomeAutorizzatoreIEffettivo = cognomeAutorizzatoreIEffettivo;
	}
	public String getNomeAutorizzatoreIIEffettivo() {
		return nomeAutorizzatoreIIEffettivo;
	}
	public void setNomeAutorizzatoreIIEffettivo(String nomeAutorizzatoreIIEffettivo) {
		this.nomeAutorizzatoreIIEffettivo = nomeAutorizzatoreIIEffettivo;
	}
	public String getCognomeAutorizzatoreIIEffettivo() {
		return cognomeAutorizzatoreIIEffettivo;
	}
	public void setCognomeAutorizzatoreIIEffettivo(
			String cognomeAutorizzatoreIIEffettivo) {
		this.cognomeAutorizzatoreIIEffettivo = cognomeAutorizzatoreIIEffettivo;
	}
	public String getNomeGestoreOperatoriEffettivo() {
		return nomeGestoreOperatoriEffettivo;
	}
	public void setNomeGestoreOperatoriEffettivo(
			String nomeGestoreOperatoriEffettivo) {
		this.nomeGestoreOperatoriEffettivo = nomeGestoreOperatoriEffettivo;
	}
	public String getCognomeGestoreOperatoriEffettivo() {
		return cognomeGestoreOperatoriEffettivo;
	}
	public void setCognomeGestoreOperatoriEffettivo(
			String cognomeGestoreOperatoriEffettivo) {
		this.cognomeGestoreOperatoriEffettivo = cognomeGestoreOperatoriEffettivo;
	}
 
 
	public String getDescrUffProvenienza() {
		return descrUffProvenienza;
	}
	public void setDescrUffProvenienza(String descrUffProvenienza) {
		this.descrUffProvenienza = descrUffProvenienza;
	}
	public String getCdrUffProvenienza() {
		return cdrUffProvenienza;
	}
	public void setCdrUffProvenienza(String cdrUffProvenienza) {
		this.cdrUffProvenienza = cdrUffProvenienza;
	}
	public String getDescrUffDestinazione() {
		return descrUffDestinazione;
	}
	public void setDescrUffDestinazione(String descrUffDestinazione) {
		this.descrUffDestinazione = descrUffDestinazione;
	}
	public String getCdrUffDestinazione() {
		return cdrUffDestinazione;
	}
	public void setCdrUffDestinazione(String cdrUffDestinazione) {
		this.cdrUffDestinazione = cdrUffDestinazione;
	}
 
	public String getCdrCodeVisibilita() {
		return cdrCodeVisibilita;
	}
	public void setCdrCodeVisibilita(String cdrCodeVisibilita) {
		this.cdrCodeVisibilita = cdrCodeVisibilita;
	}
	public String getDescrCdrVisibilita() {
		return descrCdrVisibilita;
	}
	public void setDescrCdrVisibilita(String descrCdrVisibilita) {
		this.descrCdrVisibilita = descrCdrVisibilita;
	}
	
 	public List<ProfiloRichiestaBean> getAbilitati() {
		return abilitati;
	}
	public void setAbilitati(List<ProfiloRichiestaBean> abilitati) {
		this.abilitati = abilitati;
	}
	public List<ProfiloRichiestaBean> getDisabilitati() {
		return disabilitati;
	}
	public void setDisabilitati(List<ProfiloRichiestaBean> disabilitati) {
		this.disabilitati = disabilitati;
	}
	
	public String getCognomeNomeRichiedente() {
		return cognomeRichiedente+" "+ nomeRichiedente;
	}

	public String getCognomeNomeUtente() {
		return cognomeUtente+" "+nomeUtente;
	}
	
	public String getCognomeNomeAutorizzatoreI() {
		return cognomeAutorizzatoreI+" "+nomeAutorizzatoreI;
	}
	 
	public String getCognomeNomeAutorizzatoreII() {
		return cognomeAutorizzatoreII+" "+nomeAutorizzatoreII;
	}
 
	public String getCognomeNomePresaInCarico() {
		return cognomePresaInCarico+" "+nomePresaInCarico;
	}
	public String getCognomeNomeRichiedenteEffettivo() {
		return cognomeRichiedenteEffettivo+" "+nomeRichiedenteEffettivo;
	}
	public String getCognomeNomeAutorizzatoreIEffettivo() {
		return cognomeAutorizzatoreIEffettivo+" "+nomeAutorizzatoreIEffettivo;
	}
	public String getCognomeNomeAutorizzatoreIIEffettivo() {
		return cognomeAutorizzatoreIIEffettivo+" "+nomeAutorizzatoreIIEffettivo;
	}
	public String getCognomeNomeGestoreEffettivo() {
		return cognomeGestoreOperatoriEffettivo+" "+nomeGestoreOperatoriEffettivo;
	}
	public String getCognomeNomeGestore() {
		return cognomeGestoreOperatori+" "+nomeGestoreOperatori;

	}
	public String getCognomeNomeArchiviazione() {
		return cognomeArchiviazione+" "+nomeArchiviazione;
	}
	public String getCognomeNomeArchiviazioneEffettivo() {
		return cognomeArchiviazioneEffettivo+" "+nomeArchiviazioneEffettivo;
	}
	 public Integer getIdAudit() {
		return idAudit;
	}
	 
	 public void setIdAudit(Integer idAudit) {
			this.idAudit = idAudit;
	}
		 
	 
	public String getCognomeChiusuraRichiesta() {
		return cognomeChiusuraRichiesta;
	}

	public void setCognomeChiusuraRichiesta(String cognomeChiusuraRichiesta) {
		this.cognomeChiusuraRichiesta = cognomeChiusuraRichiesta;
	}

	public String getNomeChiusuraRichiesta() {
		return nomeChiusuraRichiesta;
	}

	public void setNomeChiusuraRichiesta(String nomeChiusuraRichiesta) {
		this.nomeChiusuraRichiesta = nomeChiusuraRichiesta;
	}

	public String getRoleGroupDescPR() {
		return roleGroupDescPR;
	}

	public void setRoleGroupDescPR(String roleGroupDescPR) {
		this.roleGroupDescPR = roleGroupDescPR;
	}
	
	public Object clone() {
	    try {
	      return super.clone();
	    }
	    catch(CloneNotSupportedException e) {
	      return null;
	    }
	  }

	public String getProfiloDescPR() {
		return profiloDescPR;
	}

	public void setProfiloDescPR(String profiloDescPR) {
		this.profiloDescPR = profiloDescPR;
	}

	public java.util.Date getDataScadenzaPR() {
		return dataScadenzaPR;
	}

	public void setDataScadenzaPR(java.util.Date dataScadenzaPR) {
		this.dataScadenzaPR = dataScadenzaPR;
	}

	public String getTipoAbilitazionecPR() {
		return tipoAbilitazionecPR;
	}

	public void setTipoAbilitazionecPR(String tipoAbilitazionecPR) {
		this.tipoAbilitazionecPR = tipoAbilitazionecPR;
	}

	public String getTipoOperazionePR() {
		return tipoOperazionePR;
	}

	public void setTipoOperazionePR(String tipoOperazionePR) {
		this.tipoOperazionePR = tipoOperazionePR;
	}

	public String getCdrRichiedenteEffettivo() {
		return cdrRichiedenteEffettivo;
	}

	public void setCdrRichiedenteEffettivo(String cdrRichiedenteEffettivo) {
		this.cdrRichiedenteEffettivo = cdrRichiedenteEffettivo;
	}

	public String getCdrAutorizzatoreIEffettivo() {
		return cdrAutorizzatoreIEffettivo;
	}

	public void setCdrAutorizzatoreIEffettivo(String cdrAutorizzatoreIEffettivo) {
		this.cdrAutorizzatoreIEffettivo = cdrAutorizzatoreIEffettivo;
	}

	public String getCdrAutorizzatoreIIEffettivo() {
		return cdrAutorizzatoreIIEffettivo;
	}

	public void setCdrAutorizzatoreIIEffettivo(
			String cdrAutorizzatoreIIEffettivo) {
		this.cdrAutorizzatoreIIEffettivo = cdrAutorizzatoreIIEffettivo;
	}

	public String getCdrGestoreOperatoriEffettivo() {
		return cdrGestoreOperatoriEffettivo;
	}

	public void setCdrGestoreOperatoriEffettivo(
			String cdrGestoreOperatoriEffettivo) {
		this.cdrGestoreOperatoriEffettivo = cdrGestoreOperatoriEffettivo;
	}

	public String getCfUteLogin() {
		return cfUteLogin;
	}

	public void setCfUteLogin(String cfUteLogin) {
		this.cfUteLogin = cfUteLogin;
	}
	
	public String getCfArchiviazione() {
		return cfArchiviazione;
	}

	public void setCfArchiviazione(String cfArchiviazione) {
		this.cfArchiviazione = cfArchiviazione;
	}

	public String getCfArchiviazioneEffettivo() {
		return cfArchiviazioneEffettivo;
	}

	public void setCfArchiviazioneEffettivo(String cfArchiviazioneEffettivo) {
		this.cfArchiviazioneEffettivo = cfArchiviazioneEffettivo;
	}

	public String getCognomeArchiviazioneEffettivo() {
		return cognomeArchiviazioneEffettivo;
	}

	public void setCognomeArchiviazioneEffettivo(
			String cognomeArchiviazioneEffettivo) {
		this.cognomeArchiviazioneEffettivo = cognomeArchiviazioneEffettivo;
	}

	public String getNomeArchiviazioneEffettivo() {
		return nomeArchiviazioneEffettivo;
	}

	public void setNomeArchiviazioneEffettivo(String nomeArchiviazioneEffettivo) {
		this.nomeArchiviazioneEffettivo = nomeArchiviazioneEffettivo;
	}

	public String getCdrArchiviazione() {
		return cdrArchiviazione;
	}

	public void setCdrArchiviazione(String cdrArchiviazione) {
		this.cdrArchiviazione = cdrArchiviazione;
	}

	public String getDescCdrArchiviazione() {
		return descCdrArchiviazione;
	}

	public void setDescCdrArchiviazione(String descCdrArchiviazione) {
		this.descCdrArchiviazione = descCdrArchiviazione;
	}

	public String getNoteArchiviazione() {
		return noteArchiviazione;
	}

	public void setNoteArchiviazione(String noteArchiviazione) {
		this.noteArchiviazione = noteArchiviazione;
	}

	public String getCognomeArchiviazione() {
		return cognomeArchiviazione;
	}

	public void setCognomeArchiviazione(String cognomeArchiviazione) {
		this.cognomeArchiviazione = cognomeArchiviazione;
	}

	public String getNomeArchiviazione() {
		return nomeArchiviazione;
	}

	public void setNomeArchiviazione(String nomeArchiviazione) {
		this.nomeArchiviazione = nomeArchiviazione;
	}

	public String getTipoAbilitazione() {
		return tipoAbilitazione;
	}

	public void setTipoAbilitazione(String tipoAbilitazione) {
		this.tipoAbilitazione = tipoAbilitazione;
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

	public String getAggiornaSistemaAutorizzativo() {
		return aggiornaSistemaAutorizzativo;
	}

	public void setAggiornaSistemaAutorizzativo(String aggiornaSistemaAutorizzativo) {
		this.aggiornaSistemaAutorizzativo = aggiornaSistemaAutorizzativo;
	}

	public String getCodEvento() {
		return codEvento;
	}

	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	public String getVisRuoliSiga() {
		return visRuoliSiga;
	}

	public void setVisRuoliSiga(String visRuoliSiga) {
		this.visRuoliSiga = visRuoliSiga;
	}

	public String getCancUteEsterno() {
		return cancUteEsterno;
	}

	public void setCancUteEsterno(String cancUteEsterno) {
		this.cancUteEsterno = cancUteEsterno;
	}

	public String getNoteGenRichiesta() {
		return noteGenRichiesta;
	}

	public void setNoteGenRichiesta(String noteGenRichiesta) {
		this.noteGenRichiesta = noteGenRichiesta;
	}
 
	public boolean isSincronizzata() {
		return  Boolean.parseBoolean(sincronizzata);
	}

	public String getSincronizzata() {
		return sincronizzata;
	}

	public void setSincronizzata(String sincronizzata) {
		this.sincronizzata = sincronizzata;
	}
	public void setDescrTipoRichiesta(String descrTipoRichiesta) {
		this.descrTipoRichiesta = descrTipoRichiesta;
	}
 

	 public String getCancellaUtente() {
		 return cancellaUtente;
	 }

	 public void setCancellaUtente(String cancellaUtente) {
		 this.cancellaUtente = cancellaUtente;
	 }

	public String getSistemaAutorizz() {
		return sistemaAutorizz;
	}

	public void setSistemaAutorizz(String sistemaAutorizz) {
		this.sistemaAutorizz = sistemaAutorizz;
	}

 

	public String getOrigineAbilitazione() {
		return origineAbilitazione;
	}

	public void setOrigineAbilitazione(String origineAbilitazione) {
		this.origineAbilitazione = origineAbilitazione;
	}

	public String getFlagRichiestaAut() {
		return flagRichiestaAut;
	}

	public void setFlagRichiestaAut(String flagRichiestaAut) {
		this.flagRichiestaAut = flagRichiestaAut;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getIdRichiestaSeq() {
		return idRichiestaSeq;
	}

	public void setIdRichiestaSeq(Integer idRichiestaSeq) {
		this.idRichiestaSeq = idRichiestaSeq;
	}

	

	public String getRuoloChiusura() {
		return ruoloChiusura;
	}

	public void setRuoloChiusura(String ruoloChiusura) {
		this.ruoloChiusura = ruoloChiusura;
	}

	public List<InterrProfiliOperatoreBean> getProfiloRisultante() {
		return profiloRisultante;
	}

	public void setProfiloRisultante(List<InterrProfiliOperatoreBean> profiloRisultante) {
		this.profiloRisultante = profiloRisultante;
	}

	public String getDescrStruttGestoreOperatori() {
		return descrStruttGestoreOperatori;
	}

	public void setDescrStruttGestoreOperatori(String descrStruttGestoreOperatori) {
		this.descrStruttGestoreOperatori = descrStruttGestoreOperatori;
	}

	public String getCodiceStruttGestore() {
		return codiceStruttGestore;
	}

	public void setCodiceStruttGestore(String codiceStruttGestore) {
		this.codiceStruttGestore = codiceStruttGestore;
	}

	public String getEmailUtente() {
		return emailUtente;
	}

	public void setEmailUtente(String emailUtente) {
		this.emailUtente = emailUtente;
	}

	public String getEmailRichiedente() {
		return emailRichiedente;
	}

	public void setEmailRichiedente(String emailRichiedente) {
		this.emailRichiedente = emailRichiedente;
	}

	public String getAltroUfficioInteressato() {
		return altroUfficioInteressato;
	}

	public void setAltroUfficioInteressato(String altroUfficioInteressato) {
		this.altroUfficioInteressato = altroUfficioInteressato;
	}

	public Integer getIdAuditFine() {
		return idAuditFine;
	}

	public void setIdAuditFine(Integer idAuditFine) {
		this.idAuditFine = idAuditFine;
	}

	public String getDescrFaseSuccessiva() {
		return descrFaseSuccessiva;
	}

	public void setDescrFaseSuccessiva(String descrFaseSuccessiva) {
		this.descrFaseSuccessiva = descrFaseSuccessiva;
	}

	public String getNomeFaseSuccessiva() {
		return nomeFaseSuccessiva;
	}

	public void setNomeFaseSuccessiva(String nomeFaseSuccessiva) {
		this.nomeFaseSuccessiva = nomeFaseSuccessiva;
	}

	public String getEsecutivitaCAU() {
		return esecutivitaCAU;
	}

	public void setEsecutivitaCAU(String esecutivitaCAU) {
		this.esecutivitaCAU = esecutivitaCAU;
	}

	

	
	
}
