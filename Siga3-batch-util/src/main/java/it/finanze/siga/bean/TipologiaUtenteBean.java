package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TipologiaUtenteBean implements Serializable {

	private static final long serialVersionUID = -1611477497378810321L;
	
	private String codice;
	private Date dataInizioVal;
	private Date dataFineVal;
	private String descrizione;
	private String codiceAmbito;
	private String codiceRoleGroup;
	private String codiceProfilo;
	private String flagSenzaLimitazione;
	private String ambitoDescr;
	private String roleGroupDescr;
	private String profiloDescr;
	private Date dataInizioValOeTu;
	private String dataInizioValOeTuStr;
	private List<String> listaProfiliNonDisp = new ArrayList<String>();;
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public Date getDataInizioVal() {
		return dataInizioVal;
	}
	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}
	public Date getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal = dataFineVal;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCodiceAmbito() {
		return codiceAmbito;
	}
	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getFlagSenzaLimitazione() {
		return flagSenzaLimitazione;
	}
	public void setFlagSenzaLimitazione(String flagSenzaLimitazione) {
		this.flagSenzaLimitazione = flagSenzaLimitazione;
	}
	public String getAmbitoDescr() {
		return ambitoDescr;
	}
	public void setAmbitoDescr(String ambitoDescr) {
		this.ambitoDescr = ambitoDescr;
	}
	public String getProfiloDescr() {
		return profiloDescr;
	}
	public void setProfiloDescr(String profiloDescr) {
		this.profiloDescr = profiloDescr;
	}
	public String getRoleGroupDescr() {
		return roleGroupDescr;
	}
	public void setRoleGroupDescr(String roleGroupDescr) {
		this.roleGroupDescr = roleGroupDescr;
	}
	public List<String> getListaProfiliNonDisp() {
		return listaProfiliNonDisp;
	}
	public void setListaProfiliNonDisp(List<String> listaProfiliNonDisp) {
		this.listaProfiliNonDisp = listaProfiliNonDisp;
	}
	public Date getDataInizioValOeTu() {
		return dataInizioValOeTu;
	}
	public void setDataInizioValOeTu(Date dataInizioValOeTu) {
		this.dataInizioValOeTu = dataInizioValOeTu;
	}
	public String getDataInizioValOeTuStr() {
		return dataInizioValOeTuStr;
	}
	public void setDataInizioValOeTuStr(String dataInizioValOeTuStr) {
		this.dataInizioValOeTuStr = dataInizioValOeTuStr;
	}

	
	
}
