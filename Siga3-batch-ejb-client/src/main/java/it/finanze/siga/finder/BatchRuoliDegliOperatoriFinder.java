package it.finanze.siga.finder;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class BatchRuoliDegliOperatoriFinder extends BasePaginateFinder{

	private static final long serialVersionUID = 1880454781408977036L;

	private String funzione;
	private String cfUtente;
	private int idRichiesta;
	private String nomeRichiesta;
	private Timestamp dataRichiesta;
	private Timestamp dataProduzione;
	private byte[] file;
	private String statoRichiesta;
	private String nomeFile;
	private String flagIILiv;
	private String secondoLiv;
	private String struttura;
	private Date dataAnnull;
	private List<String> cdrSel;
	private String cdr;
	
	private List<String> codiciProfilo;
	private List<String> codiciApplicazione;
	private List<String> struttureAL;
	
	private String codiceProfilo;
	private String codiceApplicazione;
	
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
	public Timestamp getDataRichiesta() {
		return dataRichiesta;
	}
	public void setDataRichiesta(Timestamp dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}
	public Timestamp getDataProduzione() {
		return dataProduzione;
	}
	public void setDataProduzione(Timestamp dataProduzione) {
		this.dataProduzione = dataProduzione;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getStatoRichiesta() {
		return statoRichiesta;
	}
	public void setStatoRichiesta(String statoRichiesta) {
		this.statoRichiesta = statoRichiesta;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
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
	public Date getDataAnnull() {
		return dataAnnull;
	}
	public void setDataAnnull(Date dataAnnull) {
		this.dataAnnull = dataAnnull;
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
	public List<String> getCdrSel() {
		return cdrSel;
	}
	public void setCdrSel(List<String> cdrSel) {
		this.cdrSel = cdrSel;
	}
	public String getCdr() {
		return cdr;
	}
	public void setCdr(String cdr) {
		this.cdr = cdr;
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
	public List<String> getStruttureAL() {
		return struttureAL;
	}
	public void setStruttureAL(List<String> struttureAL) {
		this.struttureAL = struttureAL;
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
	
}
