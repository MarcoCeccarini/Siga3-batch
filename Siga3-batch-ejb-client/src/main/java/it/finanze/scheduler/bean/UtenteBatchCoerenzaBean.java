package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.List;

public class UtenteBatchCoerenzaBean implements Serializable{
	
	private static final long serialVersionUID = 7849884711170589049L;
	private String codiceFiscale;
	private String codiceCdr;
	private String codiceUfficio;
	private String coerenzaCau;
	private String coerenzaHr;
	private String coerenzaCea;
	private String ufficioCatPrimario;
	private List<String> codiciUfficioCAT;
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getCoerenzaCau() {
		return coerenzaCau;
	}
	public void setCoerenzaCau(String coerenzaCau) {
		this.coerenzaCau = coerenzaCau;
	}
	public String getCoerenzaHr() {
		return coerenzaHr;
	}
	public void setCoerenzaHr(String coerenzaHr) {
		this.coerenzaHr = coerenzaHr;
	}
	public String getCoerenzaCea() {
		return coerenzaCea;
	}
	public void setCoerenzaCea(String coerenzaCea) {
		this.coerenzaCea = coerenzaCea;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getUfficioCatPrimario() {
		return ufficioCatPrimario;
	}
	public void setUfficioCatPrimario(String ufficioCatPrimario) {
		this.ufficioCatPrimario = ufficioCatPrimario;
	}
	public List<String> getCodiciUfficioCAT() {
		return codiciUfficioCAT;
	}
	public void setCodiciUfficioCAT(List<String> codiciUfficioCAT) {
		this.codiciUfficioCAT = codiciUfficioCAT;
	}
	
	
}
