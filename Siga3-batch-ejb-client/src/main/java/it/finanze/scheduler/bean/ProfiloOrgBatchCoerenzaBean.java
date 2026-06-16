package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class ProfiloOrgBatchCoerenzaBean implements Serializable{

	private static final long serialVersionUID = 6402843411253776515L;
	private String codiceCdr;
	private String visibilita;
	private Date dataUpd;
	
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getVisibilita() {
		return visibilita;
	}
	public void setVisibilita(String visibilita) {
		this.visibilita = visibilita;
	}
	public Date getDataUpd() {
		return dataUpd;
	}
	public void setDataUpd(Date dataUpd) {
		this.dataUpd = dataUpd;
	}
	
	
}
