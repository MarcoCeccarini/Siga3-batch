package it.finanze.siga.finder;

import it.finanze.siga.bean.CDRBean;

import java.util.Date;
import java.util.List;
public class OperatoreFinder extends AmbitoFinder {

	public OperatoreFinder(String codFisUser) {
		super(codFisUser);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6430267358866330799L;

	private String cfUtente;
	private String cdrUser;
	private String cognome;
	private String nome;
	private String richiedente;
	private String autorizzatoreILivello;
	private String nomeDelegato;
	private String cognomeDelegato;
	
	private String cdrDelega;
	/*------------------*/
	private String tipoCdr;
	private String cdrPadreGerarchia;
	private String cfRichiedente;
	private String cfDelegante;
	private String cdrPertinenza;
	private String cdrDescrizione; //
	private String codiceCdr;
	private String descrizione;
	
	public String getCodiceCdr() {
		return codiceCdr;
	}

	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public String getCdrPertinenza() {
		return cdrPertinenza;
	}

	public void setCdrPertinenza(String cdrPertinenza) {
		this.cdrPertinenza = cdrPertinenza;
	}

	public String getCfDelegante() {
		return cfDelegante;
	}

	public void setCfDelegante(String cfDelegante) {
		this.cfDelegante = cfDelegante;
	}

	private String flag_richiedente;
	private String flag_autorizzazioneLivelloI;
	private String codiceTipoOperatore;
	private Date dataInizioVal;
	private String codiceApplicazione;

	private String flagRevocaCAU;
	private String flagRevocaPost;
	/**
	 * di CDRBean
	 */
	private String flagDescrizione;
	private String flag;
	private String cfUtenteLoggato;
	/**
	 * per i codici CDR
	 */
	private List<String> codiceCDRList;
	private List<String> codiceCDRLPertinenzaList;
	private List<String> codiceCDRListB;
	private List<String> codiceCDRAll;
	/**
	 * Per la lista di cdr:
	 */
	private List<CDRBean> cdrList;
	private String cfDelegato;
	/*** per gestione richieste */
	private List<String> cfOperatoreList;
	private String cfOperatoreSingolo;
	private boolean flagRichiestaAbilitazioni;
	private String cdrAuditorReg;
	private String flagCdrDisallCauSiga;
	private List<List<String>> cfOperatoreListMultipla;
	private String usaCfOperatoreListMultipla = "false";
	private String usaCdrOperatoreListMultipla = "false";

	/**
	 * lista operatori separata
	 */
	private List<String> cfOperatoreList1;

	private List<String> cfOperatoreList2;
	private List<String> cfOperatoreList3;
	private List<String> cfOperatoreList4;
	private List<String> cfOperatoreList5;

	/**
	 * liste cdr divise
	 */

	private List<String> cdrOperatoreList1;
	private List<String> cdrOperatoreList2;
	private List<String> cdrOperatoreList3;
	private List<String> cdrOperatoreList4;
	private List<String> cdrOperatoreList5;

	public List<List<String>> getCfOperatoreListMultipla() {
		return cfOperatoreListMultipla;
	}

	public void setCfOperatoreListMultipla(List<List<String>> cfOperatoreListMultipla) {
		this.cfOperatoreListMultipla = cfOperatoreListMultipla;
	}

	public String getUsaCfOperatoreListMultipla() {
		return usaCfOperatoreListMultipla;
	}

	public void setUsaCfOperatoreListMultipla(String usaCfOperatoreListMultipla) {
		this.usaCfOperatoreListMultipla = usaCfOperatoreListMultipla;
	}

	public List<String> getCfOperatoreList1() {
		return cfOperatoreList1;
	}

	public void setCfOperatoreList1(List<String> cfOperatoreList1) {
		this.cfOperatoreList1 = cfOperatoreList1;
	}

	public List<String> getCfOperatoreList2() {
		return cfOperatoreList2;
	}

	public void setCfOperatoreList2(List<String> cfOperatoreList2) {
		this.cfOperatoreList2 = cfOperatoreList2;
	}

	public List<String> getCfOperatoreList3() {
		return cfOperatoreList3;
	}

	public void setCfOperatoreList3(List<String> cfOperatoreList3) {
		this.cfOperatoreList3 = cfOperatoreList3;
	}

	public List<String> getCfOperatoreList4() {
		return cfOperatoreList4;
	}

	public void setCfOperatoreList4(List<String> cfOperatoreList4) {
		this.cfOperatoreList4 = cfOperatoreList4;
	}

	public List<String> getCfOperatoreList5() {
		return cfOperatoreList5;
	}

	public void setCfOperatoreList5(List<String> cfOperatoreList5) {
		this.cfOperatoreList5 = cfOperatoreList5;
	}

	public boolean isFlagRichiestaAbilitazioni() {
		return flagRichiestaAbilitazioni;
	}

	public void setFlagRichiestaAbilitazioni(boolean flagRichiestaAbilitazioni) {
		this.flagRichiestaAbilitazioni = flagRichiestaAbilitazioni;
	}

	/** GETTERS AND SETTERS **/
	public List<CDRBean> getCdrList() {
		return cdrList;
	}

	public void setCdrList(List<CDRBean> cdrList) {
		this.cdrList = cdrList;
	}

	public List<String> getCodiceCDRList() {
		return codiceCDRList;
	}

	public void setCodiceCDRList(List<String> codiceCDRList) {
		this.codiceCDRList = codiceCDRList;
	}
	
	public List<String> getCodiceCDRPertinenzaList() {
		return codiceCDRLPertinenzaList;
	}

	public void setCodiceCDRLPertinenzaList(List<String> codiceCDRLPertinenzaList) {
		this.codiceCDRLPertinenzaList = codiceCDRLPertinenzaList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlagDescrizione() {
		return flagDescrizione;
	}

	public void setFlagDescrizione(String flagDescrizione) {
		this.flagDescrizione = flagDescrizione;
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

	public String getCfDelegato() {
		return cfDelegato;
	}

	public void setCfDelegato(String cfDelegato) {
		this.cfDelegato = cfDelegato;
	}

	public String getRichiedente() {
		return richiedente;
	}

	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}

	public String getAutorizzatoreILivello() {
		return autorizzatoreILivello;
	}

	public void setAutorizzatoreILivello(String autorizzatoreILivello) {
		this.autorizzatoreILivello = autorizzatoreILivello;
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

	public String getCodiceTipoOperatore() {
		return codiceTipoOperatore;
	}

	public void setCodiceTipoOperatore(String codiceTipoOperatore) {
		this.codiceTipoOperatore = codiceTipoOperatore;
	}

	public Date getDataInizioVal() {
		return dataInizioVal;
	}

	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}

	public List<String> getCfOperatoreList() {
		return cfOperatoreList;
	}

	public void setCfOperatoreList(List<String> cfOperatoreList) {
		this.cfOperatoreList = cfOperatoreList;
	}

	public String getCfOperatoreSingolo() {
		return cfOperatoreSingolo;
	}

	public void setCfOperatoreSingolo(String cfOperatoreSingolo) {
		this.cfOperatoreSingolo = cfOperatoreSingolo;
	}

	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}

	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}

	public String getCdrUser() {
		return cdrUser;
	}

	public void setCdrUser(String cdrUser) {
		this.cdrUser = cdrUser;
	}

	public String getCfUtenteLoggato() {
		return cfUtenteLoggato;
	}

	public void setCfUtenteLoggato(String cfUtenteLoggato) {
		this.cfUtenteLoggato = cfUtenteLoggato;
	}

	public String getCdrAuditorReg() {
		return cdrAuditorReg;
	}

	public void setCdrAuditorReg(String cdrAuditorReg) {
		this.cdrAuditorReg = cdrAuditorReg;
	}

	public String getCfUtente() {
		return cfUtente;
	}

	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;

	}

	public String getFlagRevocaCAU() {
		return flagRevocaCAU;
	}

	public void setFlagRevocaCAU(String flagRevocaCAU) {
		this.flagRevocaCAU = flagRevocaCAU;
	}

	public String getFlagRevocaPost() {
		return flagRevocaPost;
	}

	public void setFlagRevocaPost(String flagRevocaPost) {
		this.flagRevocaPost = flagRevocaPost;
	}

	public List<String> getCodiceCDRListB() {
		return codiceCDRListB;
	}

	public void setCodiceCDRListB(List<String> codiceCDRListB) {
		this.codiceCDRListB = codiceCDRListB;
	}

	public List<String> getCodiceCDRAll() {
		return codiceCDRAll;
	}

	public void setCodiceCDRAll(List<String> codiceCDRAll) {
		this.codiceCDRAll = codiceCDRAll;
	}

	public String getFlagCdrDisallCauSiga() {
		return flagCdrDisallCauSiga;
	}

	public void setFlagCdrDisallCauSiga(String flagCdrDisallCauSiga) {
		this.flagCdrDisallCauSiga = flagCdrDisallCauSiga;
	}

	public List<String> getCdrOperatoreList1() {
		return cdrOperatoreList1;
	}

	public void setCdrOperatoreList1(List<String> cdrOperatoreList1) {
		this.cdrOperatoreList1 = cdrOperatoreList1;
	}

	public List<String> getCdrOperatoreList2() {
		return cdrOperatoreList2;
	}

	public void setCdrOperatoreList2(List<String> cdrOperatoreList2) {
		this.cdrOperatoreList2 = cdrOperatoreList2;
	}

	public List<String> getCdrOperatoreList3() {
		return cdrOperatoreList3;
	}

	public void setCdrOperatoreList3(List<String> cdrOperatoreList3) {
		this.cdrOperatoreList3 = cdrOperatoreList3;
	}

	public List<String> getCdrOperatoreList4() {
		return cdrOperatoreList4;
	}

	public void setCdrOperatoreList4(List<String> cdrOperatoreList4) {
		this.cdrOperatoreList4 = cdrOperatoreList4;
	}

	public List<String> getCdrOperatoreList5() {
		return cdrOperatoreList5;
	}

	public void setCdrOperatoreList5(List<String> cdrOperatoreList5) {
		this.cdrOperatoreList5 = cdrOperatoreList5;
	}

	public String getUsaCdrOperatoreListMultipla() {
		return usaCdrOperatoreListMultipla;
	}

	public void setUsaCdrOperatoreListMultipla(String usaCdrOperatoreListMultipla) {
		this.usaCdrOperatoreListMultipla = usaCdrOperatoreListMultipla;
	}

}
