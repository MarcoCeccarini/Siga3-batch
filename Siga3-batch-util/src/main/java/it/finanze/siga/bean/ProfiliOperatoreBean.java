package it.finanze.siga.bean;

import java.util.List;

public class ProfiliOperatoreBean {
	
	private String codiceCdrOperatore;
	private String descrCdrOperatore;
	private String descrcCodUffPrimCATOperatore;
	private String descrCdrVisOperatore;
	private String cfOperatore;
	private String nomeOperatore;
	private String cognomeOperatore;
	private String dataScadenzaVis;
	
	List<InterrProfiliOperatoreBean> listProfili;
	
	public String getDescrCdrOperatore() {
		return descrCdrOperatore;
	}
	public void setDescrCdrOperatore(String descrCdrOperatore) {
		this.descrCdrOperatore = descrCdrOperatore;
	}
	public String getDescrCdrVisOperatore() {
		return descrCdrVisOperatore;
	}
	public void setDescrCdrVisOperatore(String descrCdrVisOperatore) {
		this.descrCdrVisOperatore = descrCdrVisOperatore;
	}
	public String getCfOperatore() {
		return cfOperatore;
	}
	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
	}
	public String getNomeOperatore() {
		return nomeOperatore;
	}
	public void setNomeOperatore(String nomeOperatore) {
		this.nomeOperatore = nomeOperatore;
	}
	public String getCognomeOperatore() {
		return cognomeOperatore;
	}
	public void setCognomeOperatore(String cognomeOperatore) {
		this.cognomeOperatore = cognomeOperatore;
	}
	public List<InterrProfiliOperatoreBean> getListProfili() {
		return listProfili;
	}
	public void setListProfili(List<InterrProfiliOperatoreBean> listProfili) {
		this.listProfili = listProfili;
	}
	public String getDataScadenzaVis() {
		return dataScadenzaVis;
	}
	public void setDataScadenzaVis(String dataScadenzaVis) {
		this.dataScadenzaVis = dataScadenzaVis;
	}
	public String getCodiceCdrOperatore() {
		return codiceCdrOperatore;
	}
	public void setCodiceCdrOperatore(String codiceCdrOperatore) {
		this.codiceCdrOperatore = codiceCdrOperatore;
	}
	public String getDescrcCodUffPrimCATOperatore() {
		return descrcCodUffPrimCATOperatore;
	}
	public void setDescrcCodUffPrimCATOperatore(
			String descrcCodUffPrimCATOperatore) {
		this.descrcCodUffPrimCATOperatore = descrcCodUffPrimCATOperatore;
	}

	
}
