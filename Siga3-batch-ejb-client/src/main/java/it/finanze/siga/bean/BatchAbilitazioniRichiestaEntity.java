package it.finanze.siga.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class BatchAbilitazioniRichiestaEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7534481244150516436L;
	
	private int idRichiesta;
	private String nomeRichiesta;
	private Timestamp dataRichesta;
	private Timestamp dataProduzione;
	private String statoRichiesta;
	private String funzione;
	private String cfUtente;
	private Date dataAnnullamento;
	private byte[] file;
	private String nomeFile;
	
	private String flagIILiv;
	private String secondoLiv;
	private String struttura;
	private String ambitoApplicativo;
	private String tipoAbilitazione;
	// 4.5.1 II -->
	private String ruoloRichiesta;
	// <<
	private List<String> filtriCdR;
	private List<String> filtriRoleGroup;
	private List<String> filtriProfili;
	private List<String> cdrInputLst;
	
	private String intestazione;
	
	
	
	public String getIntestazione() {
		return intestazione;
	}
	public void setIntestazione(String intestazione) {
		this.intestazione = intestazione;
	}
	public int getIdRichiesta() {
		return idRichiesta;
	}
	public void setIdRichiesta(int idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	public String getNomeRichiesta() {
		return nomeRichiesta;
	}
	public void setNomeRichiesta(String nomeRichiesta) {
		this.nomeRichiesta = nomeRichiesta;
	}
	public Timestamp getDataRichesta() {
		return dataRichesta;
	}
	public void setDataRichesta(Timestamp dataRichesta) {
		this.dataRichesta = dataRichesta;
	}
	public Timestamp getDataProduzione() {
		return dataProduzione;
	}
	public void setDataProduzione(Timestamp dataProduzione) {
		this.dataProduzione = dataProduzione;
	}
	public String getStatoRichiesta() {
		return statoRichiesta;
	}
	public void setStatoRichiesta(String statoRichiesta) {
		this.statoRichiesta = statoRichiesta;
	}
	public String getFunzione() {
		return funzione;
	}
	public void setFunzione(String funzione) {
		this.funzione = funzione;
	}
	public String getCfUtente() {
		return cfUtente;
	}
	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}
	public Date getDataAnnullamento() {
		return dataAnnullamento;
	}
	public void setDataAnnullamento(Date dataAnnullamento) {
		this.dataAnnullamento = dataAnnullamento;
	}
	public String getFlagIILiv() {
		return flagIILiv;
	}
	public void setFlagIILiv(String flagIILiv) {
		this.flagIILiv = flagIILiv;
	}

	public String getSecondoLiv() {
		return secondoLiv;
	}
	public void setSecondoLiv(String secondoLiv) {
		this.secondoLiv = secondoLiv;
	}
	public String getStruttura() {
		return struttura;
	}
	public void setStruttura(String struttura) {
		this.struttura = struttura;
	}
	public String getAmbitoApplicativo() {
		return ambitoApplicativo;
	}
	public void setAmbitoApplicativo(String ambitoApplicativo) {
		this.ambitoApplicativo = ambitoApplicativo;
	}
	public String getTipoAbilitazione() {
		return tipoAbilitazione;
	}
	public void setTipoAbilitazione(String tipoAbilitazione) {
		this.tipoAbilitazione = tipoAbilitazione;
	}
	public String getRuoloRichiesta() {
		return ruoloRichiesta;
	}
	public void setRuoloRichiesta(String ruoloRichiesta) {
		this.ruoloRichiesta = ruoloRichiesta;
	}
	public List<String> getFiltriCdR() {
		return filtriCdR;
	}
	public void setFiltriCdR(List<String> filtriCdR) {
		this.filtriCdR = filtriCdR;
	}
	public List<String> getFiltriRoleGroup() {
		return filtriRoleGroup;
	}
	public void setFiltriRoleGroup(List<String> filtriRoleGroup) {
		this.filtriRoleGroup = filtriRoleGroup;
	}
	public List<String> getFiltriProfili() {
		return filtriProfili;
	}
	public void setFiltriProfili(List<String> filtriProfili) {
		this.filtriProfili = filtriProfili;
	}
	public List<String> getCdrInputLst() {
		return cdrInputLst;
	}
	public void setCdrInputLst(List<String> cdrInputLst) {
		this.cdrInputLst = cdrInputLst;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	
	
	

}
