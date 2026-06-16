package it.finanze.siga.finder;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class BatchAbilitazioniRichiestaFinder extends BasePaginateFinder{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1520375544109804699L;
	
	private int idRichiesta;
	private String nomeRichiesta;
	private Timestamp dataRichesta;
	private Timestamp dataProduzione;
	private String statoRichiesta;
	private String funzione;
	private String cfUtente;
	private byte[] file;
	private String nomeFile;
	
	private String flagIILiv;
	private String secondoLiv;
	private String struttura;
	private String ambito;
	private String tipo;
	private List<String> codiciProfilo;
	private List<String> codiciApplicazione;
	private List<String> cdrSel;
	private List<String> struttureAL;
	
	private String codiceProfilo;
	private String codiceApplicazione;
	private String cdr;
	
	// profili speciali PIER task 4.5.1
	private boolean profSpeciale;
	//
	// 4.5.1 II -->
	private String ruoloRichiesta;
	//
	private boolean paginazione;
	

	public List<String> getStruttureAL() {
		return struttureAL;
	}
	public void setStruttureAL(List<String> struttureAL) {
		this.struttureAL = struttureAL;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
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
	public String getAmbito() {
		return ambito;
	}
	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<String> getCodiciProfilo() {
		return codiciProfilo;
	}
	public void setCodiciProfilo(List<String> codiciProfilo) {
		this.codiciProfilo = codiciProfilo;
	}
	public List<String> getCodiciApplicazione() {
		return codiciApplicazione;
	}
	public void setCodiciApplicazione(List<String> codiciApplicazione) {
		this.codiciApplicazione = codiciApplicazione;
	}
	public List<String> getCdrSel() {
		return cdrSel;
	}
	public void setCdrSel(List<String>cdrSel) {
		this.cdrSel = cdrSel;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}
	public String getCdr() {
		return cdr;
	}
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}
	
	
	
	
	public boolean isProfSpeciale() {
		return profSpeciale;
	}
	public void setProfSpeciale(boolean profSpeciale) {
		this.profSpeciale = profSpeciale;
	}
	public String getRuoloRichiesta() {
		return ruoloRichiesta;
	}
	public void setRuoloRichiesta(String ruoloRichiesta) {
		this.ruoloRichiesta = ruoloRichiesta;
	}
	public boolean isPaginazione() {
		return paginazione;
	}
	public void setPaginazione(boolean paginazione) {
		this.paginazione = paginazione;
	}
	@Override
	public String toString() {
		return "BatchAbilitazioniRichiestaFinder [idRichiesta=" + idRichiesta
				+ ", nomeRichiesta=" + nomeRichiesta + ", dataRichesta="
				+ dataRichesta + ", dataProduzione=" + dataProduzione
				+ ", statoRichiesta=" + statoRichiesta + ", funzione="
				+ funzione + ", cfUtente=" + cfUtente + ", file="
				+ Arrays.toString(file) + ", nomeFile=" + nomeFile
				+ ", flagIILiv=" + flagIILiv + ", secondoLiv=" + secondoLiv
				+ ", struttura=" + struttura + ", ambito=" + ambito + ", tipo="
				+ tipo + ", codiciProfilo=" + codiciProfilo
				+ ", codiciApplicazione=" + codiciApplicazione + ", cdrSel="
				+ cdrSel + ", struttureAL=" + struttureAL + ", codiceProfilo="
				+ codiceProfilo + ", codiceApplicazione=" + codiceApplicazione
				+ ", cdr=" + cdr + "]";
	}
	

}
