package it.finanze.siga.finder;

import java.util.List;

public class RegistroDelleMieAssociazioniPuntualiFinder extends BasePaginateFinder  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -626440562160424297L;
	
	private String codFisUser;
	private String cognomeUser;
	private String nomeUser;
	private String ruolo;
	private String data_inizio;
	private String data_fine;
	private String data_odierna;
	private String dal;
	private String al;
	private String periodo;
	private String dataOraEstrazione;
	private List<String> codiceCDRList;
	private String cf_inizio;
	private String cf_operatore;
	
	
	/** GETTERS AND SETTERS **/
	public RegistroDelleMieAssociazioniPuntualiFinder(String codFisUser) {
		super();
		this.codFisUser = codFisUser;
	}

	public String getCodFisUser() {
		return codFisUser;
	}

	public void setCodFisUser(String codFisUser) {
		this.codFisUser = codFisUser;
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

	public String getCf_inizio() {
		return cf_inizio;
	}

	public void setCf_inizio(String cf_inizio) {
		this.cf_inizio = cf_inizio;
	}

	public String getCf_operatore() {
		return cf_operatore;
	}

	public void setCf_operatore(String cf_operatore) {
		this.cf_operatore = cf_operatore;
	}
	
}
