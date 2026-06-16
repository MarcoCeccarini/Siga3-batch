package it.finanze.siga.bean;

import it.finanze.siga.util.Constants;
import it.finanze.siga.util.Logg;
import it.finanze.siga.util.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class OperatoreBean implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3435857228038162088L;

	private String cognome;
	private String nome;
	private String cognomeNome;
	private String cdr;
	private String descrizione;
	private String descrizioneCDR;
	private String tipoUtente;
	private String autorizzazioneLivelloI;
	private String nomeAutorizzatore;
	private String cognomeAutorizzatore;
	private String autorizzazioneLivelloII;
	private String richiedente;
	private String nomeRichiedente;
	private String cognomeRichiedente;
	private String cf;
	private String ruolo;
	private String codProfilo;
	private String[] codiciTipoOperatore;
	private List<String> listaTipologieOperatoreDesc = new ArrayList<String>();
	private List<AllegatoBean> listAllegati = new ArrayList<AllegatoBean>();
	private List<TipologiaUtenteBean> listTipologieAttive = new ArrayList<TipologiaUtenteBean>();
	private String codiceTipoOperatore;
	private List<String> listaCdRFinder = new ArrayList<String>();
	private String flag_richiedente;
	private String flag_autorizzazioneLivelloI;
	private String passwordScaduta;
	private Date dataFineVal;
	private String[] codiciToRemove;
	private String[] codiciTipologieBloccate;
	private String codiceApplicazione;
	private String tipologiaDato;
	private String cdrDelega;
	private String cdrPertinenza;
	private String cdrDescrizione;


	/*------------------*/
	private String tipoCdr;
	private String cdrPadreGerarchia;
	private String cfRichiedente;
	/**
	 * cf del deleagnte
	 */

	private String cfDelegato;
	private String codiceUfficio;
	private String codiceCDRUser;
	private String codice_struttura;
	private String descrizione_ufficio;
	private String email;
	private String nomeDelegato;
	private String cognomeDelegato;
	private Date dataFineDelega;
	private Date dataScadenza;
	private Date dataInizioVal;
	private String dataInizioValStr;
	private String dataInizioValGGMMAAAA;
	private String dataInizioString;
	private String dataFineDelegaGGMMAAAA;
	private String dataScadenzaGGMMAAAA;
	private String cfAssIni;

	private String nota;
	private Integer idDelega;
	
	// dati operatore esterno

	private String tel;
	private String fax;
	private String agenziaTel;
	private String agenziaFax;
	private String agenziaStanza;
	private String organizzazione;
	private String mansione;
	private String note;
	private String noteCancellazione;

	private String cdrAppartenenza;
	private String cdrAppartenenzaDesc;
	private String cdrCompetenza;

	private String cdrDescAut;
	private String cdrDescRich;
	private String flagCdrDisallCauSiga;


	private boolean responsabile;
	private Integer idAudit;
	private String flagRevocaPostazione;
	private String flagRevocaCAU;

	List<IterBean> listIterList;

	private String tipologiaEMansioneExport;

	public String getTipologiaEMansioneExport() {
		if(tipoUtente!=null && tipoUtente.equalsIgnoreCase("I")){
			tipologiaEMansioneExport = Constants.INTERNO; 
			return tipologiaEMansioneExport;
		}else if (tipoUtente!=null && tipoUtente.equalsIgnoreCase("E")){
			tipologiaEMansioneExport = Constants.ESTERNO;
			if(mansione!=null && !mansione.trim().equals(""))
				tipologiaEMansioneExport = tipologiaEMansioneExport + " - " + mansione.trim();
			return tipologiaEMansioneExport;
		}else 
			return "";
	}


	public void setTipologiaEMansioneExport(String tipologiaEMansioneExport) {
		this.tipologiaEMansioneExport = tipologiaEMansioneExport;
	}


	public String getCdrDescAut() {
		return cdrDescAut;
	}
	public String getCdrDescRich() {
		return cdrDescRich;
	}
	public void setCdrDescAut(String cdrDescAut) {
		this.cdrDescAut = cdrDescAut;
	}
	public void setCdrDescRich(String cdrDescRich) {
		this.cdrDescRich = cdrDescRich;
	}
	public String getCdrAppartenenzaDesc() {
		return cdrAppartenenzaDesc;
	}
	public void setCdrAppartenenzaDesc(String cdrAppartenenzaDesc) {
		this.cdrAppartenenzaDesc = cdrAppartenenzaDesc;
	}
	public String getCdrAppartenenza() {
		return cdrAppartenenza;
	}
	public void setCdrAppartenenza(String cdrAppartenenza) {
		this.cdrAppartenenza = cdrAppartenenza;
	}
	public String getCdrCompetenza() {
		return cdrCompetenza;
	}
	public void setCdrCompetenza(String cdrCompetenza) {
		this.cdrCompetenza = cdrCompetenza;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAgenziaTel() {
		return agenziaTel;
	}
	public void setAgenziaTel(String agenziaTel) {
		this.agenziaTel = agenziaTel;
	}
	public String getAgenziaFax() {
		return agenziaFax;
	}
	public void setAgenziaFax(String agenziaFax) {
		this.agenziaFax = agenziaFax;
	}
	public String getAgenziaStanza() {
		return agenziaStanza;
	}
	public void setAgenziaStanza(String agenziaStanza) {
		this.agenziaStanza = agenziaStanza;
	}
	public String getOrganizzazione() {
		return organizzazione;
	}
	public void setOrganizzazione(String organizzazione) {
		this.organizzazione = organizzazione;
	}
	public String getMansione() {
		return mansione;
	}
	public void setMansione(String mansione) {
		this.mansione = mansione;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setTipoUtenteString(String tipoUtenteString) {
		this.tipoUtenteString = tipoUtenteString;
	}
	@Override
	public String toString() {
		return "OperatoreBean [cognome=" + cognome + ", nome=" + nome
				+ ", cognomeNome=" + cognomeNome + ", cdr=" + cdr
				+ ", descrizione=" + descrizione + ", descrizioneCDR="
				+ descrizioneCDR + ", tipoUtente=" + tipoUtente
				+ ", autorizzazioneLivelloI=" + autorizzazioneLivelloI
				+ ", autorizzazioneLivelloII=" + autorizzazioneLivelloII
				+ ", richiedente=" + richiedente + ", cf=" + cf + ", ruolo="
				+ ruolo + ", cfDelegato=" + cfDelegato + ", codiceUfficio="
				+ codiceUfficio + ", codiceCDRUser=" + codiceCDRUser
				+ ", codice_struttura=" + codice_struttura
				+ ", descrizione_ufficio=" + descrizione_ufficio + ", email="
				+ email + ", nomeDelegato=" + nomeDelegato
				+ ", cognomeDelegato=" + cognomeDelegato + ", dataFineDelega="
				+ dataFineDelega + ", dataScadenza=" + dataScadenza
				+ ", dataInizioVal=" + dataInizioVal
				+ ", dataInizioValGGMMAAAA=" + dataInizioValGGMMAAAA
				+ ", dataInizioString=" + dataInizioString
				+ ", dataFineDelegaGGMMAAAA=" + dataFineDelegaGGMMAAAA
				+ ", dataScadenzaGGMMAAAA=" + dataScadenzaGGMMAAAA + ", flag="
				+ flag + ", tipoUtenteString=" + tipoUtenteString + ", stato="
				+ stato + ", direzione=" + direzione
				+ ", codiceAmbitoApplicativo=" + codiceAmbitoApplicativo + "]";
	}
	/**
	 * Restituisce il cognome nome e codice fiscale delle persona 
	 * 
	 * @return es. Baudo Pippo (BDMPPP86P13J962P)
	 */
	public String toStringCognomeNomeCF() {
		return cognome  + " " + nome + "("+cf+")"; 
	}

	/** GETTERS AND SETTERS **/
	public String getDataScadenzaGGMMAAAA() {
		if (dataScadenza == null)
			return "";
		dataScadenzaGGMMAAAA = it.finanze.siga.util.Utils.dateStringFromDate(dataScadenza);
		return dataScadenzaGGMMAAAA;
	}

	public String getDataInizioString() {

		if(dataInizioVal==null) return "";
		dataInizioString = Utils.dateToString(dataInizioVal);
		Logg.getLogger().info(dataInizioString);
		return dataInizioString;

	}

	public void setDataInizioString(String dataInizioString) {
		this.dataInizioString = dataInizioString;
	}

	public void setDataScadenzaGGMMAAAA(String dataScadenzaGGMMAAAA) {
		this.dataScadenzaGGMMAAAA = dataScadenzaGGMMAAAA;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public String getDescrizione_ufficio() {
		return descrizione_ufficio;
	}

	public void setDescrizione_ufficio(String descrizione_ufficio) {
		this.descrizione_ufficio = descrizione_ufficio;
	}

	public String getCodice_struttura() {
		return codice_struttura;
	}

	public void setCodice_struttura(String codice_struttura) {
		this.codice_struttura = codice_struttura;
	}

	/**
	 * per gli 8 casi del CDR
	 */
	private String flag;

	/** utility fileds **/
	private String tipoUtenteString;

	/**
	 * campi per la paginazione lato server - ora non usata
	 */
	private String stato;
	private String direzione;

	/**
	 * campo aggiuntivo per query
	 */

	/**
	 * per tenere memoria del codice ambito
	 */
	private String codiceAmbitoApplicativo;
	private String descrizioneAmbitoApplicativo;

	/** utility methods **/
	public String getTipoUtenteString(){
		// NEW IMPL
		if(flag!=null && !flag.equals("") && ((flag.equalsIgnoreCase("4") || flag.equalsIgnoreCase("7")))){
			tipoUtenteString = Constants.VISIBILITA;
		}
		else if(tipoUtente!=null && tipoUtente.equalsIgnoreCase("I"))
			tipoUtenteString = Constants.INTERNO;
		else if (tipoUtente!=null && tipoUtente.equalsIgnoreCase("E"))
			tipoUtenteString = Constants.ESTERNO;
		else 
			tipoUtenteString = "";
		return tipoUtenteString;
	}

	public String getCodiceAmbitoApplicativo() {
		return codiceAmbitoApplicativo;
	}

	public void setCodiceAmbitoApplicativo(String codiceAmbitoApplicativo) {
		this.codiceAmbitoApplicativo = codiceAmbitoApplicativo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getDirezione() {
		return direzione;
	}

	public void setDirezione(String direzione) {
		this.direzione = direzione;
	}

	public OperatoreBean() {
		super();
	}

	public OperatoreBean(String cognome, String nome, String cdr, String tipoUtente, String cf) {
		super();
		this.cognome = cognome;
		this.nome = nome;
		this.cdr = cdr;
		this.tipoUtente = tipoUtente;
		this.cf = cf;
		this.descrizione="";
	}

	public OperatoreBean(String cognome, String nome, String cognomeNome,
			String cdr, String tipoUtente, String cf) {
		this( cognome,  nome,  cdr,  tipoUtente, cf);
		this.cognomeNome = cognomeNome;
	}

	public OperatoreBean(String cognome, String nome, String cognomeNome,
			String cdr, String tipoUtente, String cf, String descrizione) {
		this( cognome,  nome,  cdr,  tipoUtente, cf, cognomeNome);
		this.descrizione = descrizione;
	}

	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognomeNome() {
		return cognomeNome;
	}
	public void setCognomeNome(String cognomeNome) {
		this.cognomeNome = cognomeNome;
	}
	public String getCdr() {
		return cdr;
	}
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}

	public String getCdrDelega() {
		return cdrDelega;
	}
	public void setCdrDelega(String cdrDelega) {
		this.cdrDelega = cdrDelega;
	}

	public String getCdrPertinenza() {
		return cdrPertinenza;
	}
	public void setCdrPertinenza(String cdrPertinenza) {
		this.cdrPertinenza = cdrPertinenza;
	}

	public String getCdrDescrizione() {
		return cdrDescrizione;
	}

	public void setCdrDescrizione(String cdrDescrizione) {
		this.cdrDescrizione = cdrDescrizione;
	}

	public String getTipoCdr() {
		return tipoCdr;
	}
	public void setTipoCdr(String tipoCdr) {
		this.tipoCdr = tipoCdr;
	}

	public String getCdrPadreGerarchia() {
		return cdrPadreGerarchia;
	}
	public void setCdrPadreGerarchia(String cdrPadreGerarchia) {
		this.cdrPadreGerarchia = cdrPadreGerarchia;
	}

	public String getCfRichiedente() {
		return cfRichiedente;
	}
	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}

	public String getTipoUtente() {
		return tipoUtente;
	}
	public void setTipoUtente(String tipoUtente) {
		this.tipoUtente = tipoUtente;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getNomeDelegato() {
		return nomeDelegato;
	}

	public void setNomeDelegato(String nomeDelegato) {
		this.nomeDelegato = nomeDelegato;
	}

	public String getCognomeDelegato() {
		return cognomeDelegato;
	}

	public void setCognomeDelegato(String cognomeDelegato) {
		this.cognomeDelegato = cognomeDelegato;
	}

	public Date getDataFineDelega() {
		return dataFineDelega;
	}

	public void setDataFineDelega(Date dataFineDelega) {
		this.dataFineDelega = dataFineDelega;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getDataFineDelegaGGMMAAAA() {
		if (dataFineDelega == null)
			return "";
		dataFineDelegaGGMMAAAA = it.finanze.siga.util.Utils.dateStringFromDate(dataFineDelega);
		return dataFineDelegaGGMMAAAA;
	}

	public void setDataFineDelegaGGMMAAAA(String dataFineDelegaGGMMAAAA) {
		this.dataFineDelegaGGMMAAAA = dataFineDelegaGGMMAAAA;
	}

	public Date getDataInizioVal() {
		return dataInizioVal;
	}

	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}

	public String getDescrizioneCDR() {
		return descrizioneCDR;
	}

	public void setDescrizioneCDR(String descrizioneCDR) {
		this.descrizioneCDR = descrizioneCDR;
	}
	public String getCfDelegato() {
		return cfDelegato;
	}

	public void setCfDelegato(String cfDelegato) {
		this.cfDelegato = cfDelegato;
	}

	public String getDataInizioValGGMMAAAA() {		
		if (dataInizioVal == null)
			return "";
		dataInizioValGGMMAAAA = it.finanze.siga.util.Utils.dateStringFromDate(dataInizioVal);
		return dataInizioValGGMMAAAA;
	}

	public void setDataInizioValGGMMAAAA(String dataInizioValGGMMAAAA) {
		this.dataInizioValGGMMAAAA = dataInizioValGGMMAAAA;
	}

	public String getAutorizzazioneLivelloI() {
		return autorizzazioneLivelloI;
	}

	public void setAutorizzazioneLivelloI(String autorizzazioneLivelloI) {
		this.autorizzazioneLivelloI = autorizzazioneLivelloI;
	}

	public String getAutorizzazioneLivelloII() {
		return autorizzazioneLivelloII;
	}

	public void setAutorizzazioneLivelloII(String autorizzazioneLivelloII) {
		this.autorizzazioneLivelloII = autorizzazioneLivelloII;
	}

	public String getRichiedente() {
		return richiedente;
	}

	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}

	public String getCodiceCDRUser() {
		return codiceCDRUser;
	}

	public String getFlag_richiedente() {
		return flag_richiedente;
	}

	public void setFlag_richiedente(String flag_richiedente) {
		this.flag_richiedente = flag_richiedente;
	}

	public String getFlag_autorizzazioneLivelloI() {
		return flag_autorizzazioneLivelloI;
	}

	public void setFlag_autorizzazioneLivelloI(String flag_autorizzazioneLivelloI) {
		this.flag_autorizzazioneLivelloI = flag_autorizzazioneLivelloI;
	}

	public void setCodiceCDRUser(String codiceCDRUser) {
		this.codiceCDRUser = codiceCDRUser;
	}

	public String getDescrizioneAmbitoApplicativo() {
		return descrizioneAmbitoApplicativo;
	}

	public void setDescrizioneAmbitoApplicativo(
			String descrizioneAmbitoApplicativo) {
		this.descrizioneAmbitoApplicativo = descrizioneAmbitoApplicativo;
	}
	public String[] getCodiciTipoOperatore() {
		return codiciTipoOperatore;
	}
	public void setCodiciTipoOperatore(String[] codiciTipoOperatore) {
		this.codiciTipoOperatore = codiciTipoOperatore;
	}
	public String getCodiceTipoOperatore() {
		return codiceTipoOperatore;
	}
	public void setCodiceTipoOperatore(String codiceTipoOperatore) {
		this.codiceTipoOperatore = codiceTipoOperatore;
	}
	public String getPasswordScaduta() {
		return passwordScaduta;
	}
	public void setPasswordScaduta(String passwordScaduta) {
		this.passwordScaduta = passwordScaduta;
	}
	public List<String> getListaTipologieOperatoreDesc() {
		return listaTipologieOperatoreDesc;
	}
	public void setListaTipologieOperatoreDesc(List<String> listaTipologieOperatoreDesc) {
		this.listaTipologieOperatoreDesc = listaTipologieOperatoreDesc;
	}
	public Date getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal = dataFineVal;
	}
	public List<AllegatoBean> getListAllegati() {
		return listAllegati;
	}
	public void setListAllegati(List<AllegatoBean> listAllegati) {
		this.listAllegati = listAllegati;
	}
	public String getDataInizioValStr() {
		return dataInizioValStr;
	}
	public void setDataInizioValStr(String dataInizioValStr) {
		this.dataInizioValStr = dataInizioValStr;
	}
	public String getNoteCancellazione() {
		return noteCancellazione;
	}
	public void setNoteCancellazione(String noteCancellazione) {
		this.noteCancellazione = noteCancellazione;
	}
	public List<String> getListaCdRFinder() {
		return listaCdRFinder;
	}
	public void setListaCdRFinder(List<String> listaCdRFinder) {
		this.listaCdRFinder = listaCdRFinder;
	}
	public List<TipologiaUtenteBean> getListTipologieAttive() {
		return listTipologieAttive;
	}
	public void setListTipologieAttive(List<TipologiaUtenteBean> listTipologieAttive) {
		this.listTipologieAttive = listTipologieAttive;
	}
	public String[] getCodiciToRemove() {
		return codiciToRemove;
	}
	public void setCodiciToRemove(String[] codiciToRemove) {
		this.codiciToRemove = codiciToRemove;
	}
	public String[] getCodiciTipologieBloccate() {
		return codiciTipologieBloccate;
	}
	public void setCodiciTipologieBloccate(String[] codiciTipologieBloccate) {
		this.codiciTipologieBloccate = codiciTipologieBloccate;
	}
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}
	public String getTipologiaDato() {
		return tipologiaDato;
	}

	public void setTipologiaDato(String tipologiaDato) {
		this.tipologiaDato = tipologiaDato;
	}
	public String getNomeAutorizzatore() {
		return nomeAutorizzatore;
	}

	public String getNomeRichiedente() {
		return nomeRichiedente;
	}
	public String getCognomeRichiedente() {
		return cognomeRichiedente;
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
	public void setNomeRichiedente(String nomeRichiedente) {
		this.nomeRichiedente = nomeRichiedente;
	}
	public void setCognomeRichiedente(String cognomeRichiedente) {
		this.cognomeRichiedente = cognomeRichiedente;
	}
	public String getCfAssIni() {
		return cfAssIni;
	}

	public void setCfAssIni(String cfAssIni) {
		this.cfAssIni = cfAssIni;
	}

	
	
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Integer getIdDelega() {
		return idDelega;
	}

	public void setIdDelega(Integer idDelega) {
		this.idDelega = idDelega;
	}


	
	public String getFlagCdrDisallCauSiga() {
		return flagCdrDisallCauSiga;
	}
	public void setFlagCdrDisallCauSiga(String flagCdrDisallCauSiga) {
		this.flagCdrDisallCauSiga = flagCdrDisallCauSiga;
	}

	public Integer getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}

	public String getFlagRevocaPostazione() {
		return flagRevocaPostazione;
	}
	public void setFlagRevocaPostazione(String flagRevocaPostazione) {
		this.flagRevocaPostazione = flagRevocaPostazione;
	}
	public String getFlagRevocaCAU() {
		return flagRevocaCAU;
	}
	public void setFlagRevocaCAU(String flagRevocaCAU) {
		this.flagRevocaCAU = flagRevocaCAU;
	}
	public List<IterBean> getListIterList() {
		return listIterList;
	}
	public void setListIterList(List<IterBean> listIterList) {
		this.listIterList = listIterList;
	}
	public boolean isResponsabile() {
		return responsabile;
	}
	public void setResponsabile(boolean responsabile) {
		this.responsabile = responsabile;
	}
	public String getCodProfilo() {
		return codProfilo;
	}
	public void setCodProfilo(String codProfilo) {
		this.codProfilo = codProfilo;
	}

	public Object clone() throws CloneNotSupportedException
	{
		return (OperatoreBean)super.clone();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agenziaFax == null) ? 0 : agenziaFax.hashCode());
		result = prime * result + ((agenziaStanza == null) ? 0 : agenziaStanza.hashCode());
		result = prime * result + ((agenziaTel == null) ? 0 : agenziaTel.hashCode());
		result = prime * result + ((autorizzazioneLivelloI == null) ? 0 : autorizzazioneLivelloI.hashCode());
		result = prime * result + ((autorizzazioneLivelloII == null) ? 0 : autorizzazioneLivelloII.hashCode());
		result = prime * result + ((cdr == null) ? 0 : cdr.hashCode());
		result = prime * result + ((cdrAppartenenza == null) ? 0 : cdrAppartenenza.hashCode());
		result = prime * result + ((cdrAppartenenzaDesc == null) ? 0 : cdrAppartenenzaDesc.hashCode());
		result = prime * result + ((cdrCompetenza == null) ? 0 : cdrCompetenza.hashCode());
		result = prime * result + ((cdrDelega == null) ? 0 : cdrDelega.hashCode());
		result = prime * result + ((cdrDescAut == null) ? 0 : cdrDescAut.hashCode());
		result = prime * result + ((cdrDescRich == null) ? 0 : cdrDescRich.hashCode());
		result = prime * result + ((cdrDescrizione == null) ? 0 : cdrDescrizione.hashCode());
		result = prime * result + ((cdrPadreGerarchia == null) ? 0 : cdrPadreGerarchia.hashCode());
		result = prime * result + ((cdrPertinenza == null) ? 0 : cdrPertinenza.hashCode());
		result = prime * result + ((cf == null) ? 0 : cf.hashCode());
		result = prime * result + ((cfAssIni == null) ? 0 : cfAssIni.hashCode());
		result = prime * result + ((cfDelegato == null) ? 0 : cfDelegato.hashCode());
		result = prime * result + ((cfRichiedente == null) ? 0 : cfRichiedente.hashCode());
		result = prime * result + ((codProfilo == null) ? 0 : codProfilo.hashCode());
		result = prime * result + ((codiceAmbitoApplicativo == null) ? 0 : codiceAmbitoApplicativo.hashCode());
		result = prime * result + ((codiceApplicazione == null) ? 0 : codiceApplicazione.hashCode());
		result = prime * result + ((codiceCDRUser == null) ? 0 : codiceCDRUser.hashCode());
		result = prime * result + ((codiceTipoOperatore == null) ? 0 : codiceTipoOperatore.hashCode());
		result = prime * result + ((codiceUfficio == null) ? 0 : codiceUfficio.hashCode());
		result = prime * result + ((codice_struttura == null) ? 0 : codice_struttura.hashCode());
		result = prime * result + Arrays.hashCode(codiciTipoOperatore);
		result = prime * result + Arrays.hashCode(codiciTipologieBloccate);
		result = prime * result + Arrays.hashCode(codiciToRemove);
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((cognomeAutorizzatore == null) ? 0 : cognomeAutorizzatore.hashCode());
		result = prime * result + ((cognomeDelegato == null) ? 0 : cognomeDelegato.hashCode());
		result = prime * result + ((cognomeNome == null) ? 0 : cognomeNome.hashCode());
		result = prime * result + ((cognomeRichiedente == null) ? 0 : cognomeRichiedente.hashCode());
		result = prime * result + ((dataFineDelega == null) ? 0 : dataFineDelega.hashCode());
		result = prime * result + ((dataFineDelegaGGMMAAAA == null) ? 0 : dataFineDelegaGGMMAAAA.hashCode());
		result = prime * result + ((dataFineVal == null) ? 0 : dataFineVal.hashCode());
		result = prime * result + ((dataInizioString == null) ? 0 : dataInizioString.hashCode());
		result = prime * result + ((dataInizioVal == null) ? 0 : dataInizioVal.hashCode());
		result = prime * result + ((dataInizioValGGMMAAAA == null) ? 0 : dataInizioValGGMMAAAA.hashCode());
		result = prime * result + ((dataInizioValStr == null) ? 0 : dataInizioValStr.hashCode());
		result = prime * result + ((dataScadenza == null) ? 0 : dataScadenza.hashCode());
		result = prime * result + ((dataScadenzaGGMMAAAA == null) ? 0 : dataScadenzaGGMMAAAA.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result
				+ ((descrizioneAmbitoApplicativo == null) ? 0 : descrizioneAmbitoApplicativo.hashCode());
		result = prime * result + ((descrizioneCDR == null) ? 0 : descrizioneCDR.hashCode());
		result = prime * result + ((descrizione_ufficio == null) ? 0 : descrizione_ufficio.hashCode());
		result = prime * result + ((direzione == null) ? 0 : direzione.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((flag == null) ? 0 : flag.hashCode());
		result = prime * result + ((flagCdrDisallCauSiga == null) ? 0 : flagCdrDisallCauSiga.hashCode());
		result = prime * result + ((flagRevocaCAU == null) ? 0 : flagRevocaCAU.hashCode());
		result = prime * result + ((flagRevocaPostazione == null) ? 0 : flagRevocaPostazione.hashCode());
		result = prime * result + ((flag_autorizzazioneLivelloI == null) ? 0 : flag_autorizzazioneLivelloI.hashCode());
		result = prime * result + ((flag_richiedente == null) ? 0 : flag_richiedente.hashCode());
		result = prime * result + ((idAudit == null) ? 0 : idAudit.hashCode());
		result = prime * result + ((listAllegati == null) ? 0 : listAllegati.hashCode());
		result = prime * result + ((listIterList == null) ? 0 : listIterList.hashCode());
		result = prime * result + ((listTipologieAttive == null) ? 0 : listTipologieAttive.hashCode());
		result = prime * result + ((listaCdRFinder == null) ? 0 : listaCdRFinder.hashCode());
		result = prime * result + ((listaTipologieOperatoreDesc == null) ? 0 : listaTipologieOperatoreDesc.hashCode());
		result = prime * result + ((mansione == null) ? 0 : mansione.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((nomeAutorizzatore == null) ? 0 : nomeAutorizzatore.hashCode());
		result = prime * result + ((nomeDelegato == null) ? 0 : nomeDelegato.hashCode());
		result = prime * result + ((nomeRichiedente == null) ? 0 : nomeRichiedente.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((noteCancellazione == null) ? 0 : noteCancellazione.hashCode());
		result = prime * result + ((organizzazione == null) ? 0 : organizzazione.hashCode());
		result = prime * result + ((passwordScaduta == null) ? 0 : passwordScaduta.hashCode());
		result = prime * result + (responsabile ? 1231 : 1237);
		result = prime * result + ((richiedente == null) ? 0 : richiedente.hashCode());
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
		result = prime * result + ((stato == null) ? 0 : stato.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((tipoCdr == null) ? 0 : tipoCdr.hashCode());
		result = prime * result + ((tipoUtente == null) ? 0 : tipoUtente.hashCode());
		result = prime * result + ((tipoUtenteString == null) ? 0 : tipoUtenteString.hashCode());
		result = prime * result + ((tipologiaDato == null) ? 0 : tipologiaDato.hashCode());
		result = prime * result + ((tipologiaEMansioneExport == null) ? 0 : tipologiaEMansioneExport.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperatoreBean other = (OperatoreBean) obj;
		if (agenziaFax == null) {
			if (other.agenziaFax != null)
				return false;
		} else if (!agenziaFax.equals(other.agenziaFax))
			return false;
		if (agenziaStanza == null) {
			if (other.agenziaStanza != null)
				return false;
		} else if (!agenziaStanza.equals(other.agenziaStanza))
			return false;
		if (agenziaTel == null) {
			if (other.agenziaTel != null)
				return false;
		} else if (!agenziaTel.equals(other.agenziaTel))
			return false;
		if (autorizzazioneLivelloI == null) {
			if (other.autorizzazioneLivelloI != null)
				return false;
		} else if (!autorizzazioneLivelloI.equals(other.autorizzazioneLivelloI))
			return false;
		if (autorizzazioneLivelloII == null) {
			if (other.autorizzazioneLivelloII != null)
				return false;
		} else if (!autorizzazioneLivelloII.equals(other.autorizzazioneLivelloII))
			return false;
		if (cdr == null) {
			if (other.cdr != null)
				return false;
		} else if (!cdr.equals(other.cdr))
			return false;
		if (cdrAppartenenza == null) {
			if (other.cdrAppartenenza != null)
				return false;
		} else if (!cdrAppartenenza.equals(other.cdrAppartenenza))
			return false;
		if (cdrAppartenenzaDesc == null) {
			if (other.cdrAppartenenzaDesc != null)
				return false;
		} else if (!cdrAppartenenzaDesc.equals(other.cdrAppartenenzaDesc))
			return false;
		if (cdrCompetenza == null) {
			if (other.cdrCompetenza != null)
				return false;
		} else if (!cdrCompetenza.equals(other.cdrCompetenza))
			return false;
		if (cdrDelega == null) {
			if (other.cdrDelega != null)
				return false;
		} else if (!cdrDelega.equals(other.cdrDelega))
			return false;
		if (cdrDescAut == null) {
			if (other.cdrDescAut != null)
				return false;
		} else if (!cdrDescAut.equals(other.cdrDescAut))
			return false;
		if (cdrDescRich == null) {
			if (other.cdrDescRich != null)
				return false;
		} else if (!cdrDescRich.equals(other.cdrDescRich))
			return false;
		if (cdrDescrizione == null) {
			if (other.cdrDescrizione != null)
				return false;
		} else if (!cdrDescrizione.equals(other.cdrDescrizione))
			return false;
		if (cdrPadreGerarchia == null) {
			if (other.cdrPadreGerarchia != null)
				return false;
		} else if (!cdrPadreGerarchia.equals(other.cdrPadreGerarchia))
			return false;
		if (cdrPertinenza == null) {
			if (other.cdrPertinenza != null)
				return false;
		} else if (!cdrPertinenza.equals(other.cdrPertinenza))
			return false;
		if (cf == null) {
			if (other.cf != null)
				return false;
		} else if (!cf.equals(other.cf))
			return false;
		if (cfAssIni == null) {
			if (other.cfAssIni != null)
				return false;
		} else if (!cfAssIni.equals(other.cfAssIni))
			return false;
		if (cfDelegato == null) {
			if (other.cfDelegato != null)
				return false;
		} else if (!cfDelegato.equals(other.cfDelegato))
			return false;
		if (cfRichiedente == null) {
			if (other.cfRichiedente != null)
				return false;
		} else if (!cfRichiedente.equals(other.cfRichiedente))
			return false;
		if (codProfilo == null) {
			if (other.codProfilo != null)
				return false;
		} else if (!codProfilo.equals(other.codProfilo))
			return false;
		if (codiceAmbitoApplicativo == null) {
			if (other.codiceAmbitoApplicativo != null)
				return false;
		} else if (!codiceAmbitoApplicativo.equals(other.codiceAmbitoApplicativo))
			return false;
		if (codiceApplicazione == null) {
			if (other.codiceApplicazione != null)
				return false;
		} else if (!codiceApplicazione.equals(other.codiceApplicazione))
			return false;
		if (codiceCDRUser == null) {
			if (other.codiceCDRUser != null)
				return false;
		} else if (!codiceCDRUser.equals(other.codiceCDRUser))
			return false;
		if (codiceTipoOperatore == null) {
			if (other.codiceTipoOperatore != null)
				return false;
		} else if (!codiceTipoOperatore.equals(other.codiceTipoOperatore))
			return false;
		if (codiceUfficio == null) {
			if (other.codiceUfficio != null)
				return false;
		} else if (!codiceUfficio.equals(other.codiceUfficio))
			return false;
		if (codice_struttura == null) {
			if (other.codice_struttura != null)
				return false;
		} else if (!codice_struttura.equals(other.codice_struttura))
			return false;
		if (!Arrays.equals(codiciTipoOperatore, other.codiciTipoOperatore))
			return false;
		if (!Arrays.equals(codiciTipologieBloccate, other.codiciTipologieBloccate))
			return false;
		if (!Arrays.equals(codiciToRemove, other.codiciToRemove))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (cognomeAutorizzatore == null) {
			if (other.cognomeAutorizzatore != null)
				return false;
		} else if (!cognomeAutorizzatore.equals(other.cognomeAutorizzatore))
			return false;
		if (cognomeDelegato == null) {
			if (other.cognomeDelegato != null)
				return false;
		} else if (!cognomeDelegato.equals(other.cognomeDelegato))
			return false;
		if (cognomeNome == null) {
			if (other.cognomeNome != null)
				return false;
		} else if (!cognomeNome.equals(other.cognomeNome))
			return false;
		if (cognomeRichiedente == null) {
			if (other.cognomeRichiedente != null)
				return false;
		} else if (!cognomeRichiedente.equals(other.cognomeRichiedente))
			return false;
		if (dataFineDelega == null) {
			if (other.dataFineDelega != null)
				return false;
		} else if (!dataFineDelega.equals(other.dataFineDelega))
			return false;
		if (dataFineDelegaGGMMAAAA == null) {
			if (other.dataFineDelegaGGMMAAAA != null)
				return false;
		} else if (!dataFineDelegaGGMMAAAA.equals(other.dataFineDelegaGGMMAAAA))
			return false;
		if (dataFineVal == null) {
			if (other.dataFineVal != null)
				return false;
		} else if (!dataFineVal.equals(other.dataFineVal))
			return false;
		if (dataInizioString == null) {
			if (other.dataInizioString != null)
				return false;
		} else if (!dataInizioString.equals(other.dataInizioString))
			return false;
		if (dataInizioVal == null) {
			if (other.dataInizioVal != null)
				return false;
		} else if (!dataInizioVal.equals(other.dataInizioVal))
			return false;
		if (dataInizioValGGMMAAAA == null) {
			if (other.dataInizioValGGMMAAAA != null)
				return false;
		} else if (!dataInizioValGGMMAAAA.equals(other.dataInizioValGGMMAAAA))
			return false;
		if (dataInizioValStr == null) {
			if (other.dataInizioValStr != null)
				return false;
		} else if (!dataInizioValStr.equals(other.dataInizioValStr))
			return false;
		if (dataScadenza == null) {
			if (other.dataScadenza != null)
				return false;
		} else if (!dataScadenza.equals(other.dataScadenza))
			return false;
		if (dataScadenzaGGMMAAAA == null) {
			if (other.dataScadenzaGGMMAAAA != null)
				return false;
		} else if (!dataScadenzaGGMMAAAA.equals(other.dataScadenzaGGMMAAAA))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (descrizioneAmbitoApplicativo == null) {
			if (other.descrizioneAmbitoApplicativo != null)
				return false;
		} else if (!descrizioneAmbitoApplicativo.equals(other.descrizioneAmbitoApplicativo))
			return false;
		if (descrizioneCDR == null) {
			if (other.descrizioneCDR != null)
				return false;
		} else if (!descrizioneCDR.equals(other.descrizioneCDR))
			return false;
		if (descrizione_ufficio == null) {
			if (other.descrizione_ufficio != null)
				return false;
		} else if (!descrizione_ufficio.equals(other.descrizione_ufficio))
			return false;
		if (direzione == null) {
			if (other.direzione != null)
				return false;
		} else if (!direzione.equals(other.direzione))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (flag == null) {
			if (other.flag != null)
				return false;
		} else if (!flag.equals(other.flag))
			return false;
		if (flagCdrDisallCauSiga == null) {
			if (other.flagCdrDisallCauSiga != null)
				return false;
		} else if (!flagCdrDisallCauSiga.equals(other.flagCdrDisallCauSiga))
			return false;
		if (flagRevocaCAU == null) {
			if (other.flagRevocaCAU != null)
				return false;
		} else if (!flagRevocaCAU.equals(other.flagRevocaCAU))
			return false;
		if (flagRevocaPostazione == null) {
			if (other.flagRevocaPostazione != null)
				return false;
		} else if (!flagRevocaPostazione.equals(other.flagRevocaPostazione))
			return false;
		if (flag_autorizzazioneLivelloI == null) {
			if (other.flag_autorizzazioneLivelloI != null)
				return false;
		} else if (!flag_autorizzazioneLivelloI.equals(other.flag_autorizzazioneLivelloI))
			return false;
		if (flag_richiedente == null) {
			if (other.flag_richiedente != null)
				return false;
		} else if (!flag_richiedente.equals(other.flag_richiedente))
			return false;
		if (idAudit == null) {
			if (other.idAudit != null)
				return false;
		} else if (!idAudit.equals(other.idAudit))
			return false;
		if (listAllegati == null) {
			if (other.listAllegati != null)
				return false;
		} else if (!listAllegati.equals(other.listAllegati))
			return false;
		if (listIterList == null) {
			if (other.listIterList != null)
				return false;
		} else if (!listIterList.equals(other.listIterList))
			return false;
		if (listTipologieAttive == null) {
			if (other.listTipologieAttive != null)
				return false;
		} else if (!listTipologieAttive.equals(other.listTipologieAttive))
			return false;
		if (listaCdRFinder == null) {
			if (other.listaCdRFinder != null)
				return false;
		} else if (!listaCdRFinder.equals(other.listaCdRFinder))
			return false;
		if (listaTipologieOperatoreDesc == null) {
			if (other.listaTipologieOperatoreDesc != null)
				return false;
		} else if (!listaTipologieOperatoreDesc.equals(other.listaTipologieOperatoreDesc))
			return false;
		if (mansione == null) {
			if (other.mansione != null)
				return false;
		} else if (!mansione.equals(other.mansione))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (nomeAutorizzatore == null) {
			if (other.nomeAutorizzatore != null)
				return false;
		} else if (!nomeAutorizzatore.equals(other.nomeAutorizzatore))
			return false;
		if (nomeDelegato == null) {
			if (other.nomeDelegato != null)
				return false;
		} else if (!nomeDelegato.equals(other.nomeDelegato))
			return false;
		if (nomeRichiedente == null) {
			if (other.nomeRichiedente != null)
				return false;
		} else if (!nomeRichiedente.equals(other.nomeRichiedente))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (noteCancellazione == null) {
			if (other.noteCancellazione != null)
				return false;
		} else if (!noteCancellazione.equals(other.noteCancellazione))
			return false;
		if (organizzazione == null) {
			if (other.organizzazione != null)
				return false;
		} else if (!organizzazione.equals(other.organizzazione))
			return false;
		if (passwordScaduta == null) {
			if (other.passwordScaduta != null)
				return false;
		} else if (!passwordScaduta.equals(other.passwordScaduta))
			return false;
		if (responsabile != other.responsabile)
			return false;
		if (richiedente == null) {
			if (other.richiedente != null)
				return false;
		} else if (!richiedente.equals(other.richiedente))
			return false;
		if (ruolo == null) {
			if (other.ruolo != null)
				return false;
		} else if (!ruolo.equals(other.ruolo))
			return false;
		if (stato == null) {
			if (other.stato != null)
				return false;
		} else if (!stato.equals(other.stato))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (tipoCdr == null) {
			if (other.tipoCdr != null)
				return false;
		} else if (!tipoCdr.equals(other.tipoCdr))
			return false;
		if (tipoUtente == null) {
			if (other.tipoUtente != null)
				return false;
		} else if (!tipoUtente.equals(other.tipoUtente))
			return false;
		if (tipoUtenteString == null) {
			if (other.tipoUtenteString != null)
				return false;
		} else if (!tipoUtenteString.equals(other.tipoUtenteString))
			return false;
		if (tipologiaDato == null) {
			if (other.tipologiaDato != null)
				return false;
		} else if (!tipologiaDato.equals(other.tipologiaDato))
			return false;
		if (tipologiaEMansioneExport == null) {
			if (other.tipologiaEMansioneExport != null)
				return false;
		} else if (!tipologiaEMansioneExport.equals(other.tipologiaEMansioneExport))
			return false;
		return true;
	}



}
