package it.finanze.siga.finder;

import java.util.List;

public class LeMieVariazioniRuoloCDRFinder extends BasePaginateFinder  {

	private static final long serialVersionUID = -9151756646495041587L;
	private String codFisUser;
	private String ruolo;
	private String data_inizio;
	private String data_fine;
	private String data_odierna;
	private String dal;
	private String al;
	private String periodo;
	private String cognomeUser;
	private String nomeUser;
	private String cf_rich;
	private String nome_rich;
	private String cognome_rich;
	private String dataOraEstrazione;
	private List<String> codiceCDRList;
	private String cf_inizio;
	private String cf_fine;
	
	private int idAudit;
	
	//CDR SELEZIONATO 
	private String treeselected;
	private String cdrSelezionato;


	/** GETTERS AND SETTERS **/
	public LeMieVariazioniRuoloCDRFinder() {
	}
	public LeMieVariazioniRuoloCDRFinder(String codFisUser, String cognomeUser, String nomeUser) {
		//super();
		this.codFisUser = codFisUser;
		this.cognomeUser = cognomeUser;
		this.nomeUser = nomeUser;
	}

	public String getCodFisUser() {
		return codFisUser;
	}

	public void setCodFisUser(String codFisUser) {
		this.codFisUser = codFisUser;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getData_inizio() {
		return data_inizio;
	}

	public void setData_inizio(String data_inizio) {
		this.data_inizio = data_inizio;
	}

	public String getData_fine() {
		return data_fine;
	}

	public void setData_fine(String data_fine) {
		this.data_fine = data_fine;
	}

	public String getData_odierna() {
		return data_odierna;
	}

	public void setData_odierna(String data_odierna) {
		this.data_odierna = data_odierna;
	}

	public String getDal() {
		return dal;
	}

	public void setDal(String dal) {
		this.dal = dal;
	}

	public String getAl() {
		return al;
	}

	public void setAl(String al) {
		this.al = al;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getCognomeUser() {
		return cognomeUser;
	}

	public void setCognomeUser(String cognomeUser) {
		this.cognomeUser = cognomeUser;
	}

	public String getNomeUser() {
		return nomeUser;
	}

	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}
	public String getDataOraEstrazione() {
		return dataOraEstrazione;
	}
	public void setDataOraEstrazione(String dataOraEstrazione) {
		this.dataOraEstrazione = dataOraEstrazione;
	}
	public List<String> getCodiceCDRList() {
		return codiceCDRList;
	}
	public void setCodiceCDRList(List<String> codiceCDRList) {
		this.codiceCDRList = codiceCDRList;
	}
	public String getCf_rich() {
		return cf_rich;
	}
	public void setCf_rich(String cf_rich) {
		this.cf_rich = cf_rich;
	}
	public String getNome_rich() {
		return nome_rich;
	}
	public void setNome_rich(String nome_rich) {
		this.nome_rich = nome_rich;
	}
	public String getCognome_rich() {
		return cognome_rich;
	}
	public void setCognome_rich(String cognome_rich) {
		this.cognome_rich = cognome_rich;
	}
	public String getCf_inizio() {
		return cf_inizio;
	}
	public void setCf_inizio(String cf_inizio) {
		this.cf_inizio = cf_inizio;
	}
	public String getCf_fine() {
		return cf_fine;
	}
	public void setCf_fine(String cf_fine) {
		this.cf_fine = cf_fine;
	}
	public String getTreeselected() {
		return treeselected;
	}
	public void setTreeselected(String treeselected) {
		this.treeselected = treeselected;
	}
	public String getCdrSelezionato() {
		return cdrSelezionato;
	}
	public void setCdrSelezionato(String cdrSelezionato) {
		this.cdrSelezionato = cdrSelezionato;
	}
	public int getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(int idAudit) {
		this.idAudit = idAudit;
	}
	
	
	
	
}
