package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class VisibilitaCoerenza implements Serializable{

	private static final long serialVersionUID = 6296668003739257573L;
	private String codiceFiscale;
	private String cdrVisibilita;
	private String codiceUfficioCdrVisibilita;
	private String visibilitaCau;
	private String visibilitaHr;
	private String visibilitaCea;
	private Date dataAssociazioneProfiloUtente;
	private String visibilitaSiga;
	private String profiliCauEsistenti;
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCdrVisibilita() {
		return cdrVisibilita;
	}
	public void setCdrVisibilita(String cdrVisibilita) {
		this.cdrVisibilita = cdrVisibilita;
	}
	public String getCodiceUfficioCdrVisibilita() {
		return codiceUfficioCdrVisibilita;
	}
	public void setCodiceUfficioCdrVisibilita(String codiceUfficioCdrVisibilita) {
		this.codiceUfficioCdrVisibilita = codiceUfficioCdrVisibilita;
	}
	public String getVisibilitaCau() {
		return visibilitaCau;
	}
	public void setVisibilitaCau(String visibilitaCau) {
		this.visibilitaCau = visibilitaCau;
	}
	public String getVisibilitaHr() {
		return visibilitaHr;
	}
	public void setVisibilitaHr(String visibilitaHr) {
		this.visibilitaHr = visibilitaHr;
	}
	public String getVisibilitaCea() {
		return visibilitaCea;
	}
	public void setVisibilitaCea(String visibilitaCea) {
		this.visibilitaCea = visibilitaCea;
	}
	public Date getDataAssociazioneProfiloUtente() {
		return dataAssociazioneProfiloUtente;
	}
	public void setDataAssociazioneProfiloUtente(Date dataAssociazioneProfiloUtente) {
		this.dataAssociazioneProfiloUtente = dataAssociazioneProfiloUtente;
	}
	public String getVisibilitaSiga() {
		return visibilitaSiga;
	}
	public void setVisibilitaSiga(String visibilitaSiga) {
		this.visibilitaSiga = visibilitaSiga;
	}
	public String getProfiliCauEsistenti() {
		return profiliCauEsistenti;
	}
	public void setProfiliCauEsistenti(String profiliCauEsistenti) {
		this.profiliCauEsistenti = profiliCauEsistenti;
	}
	
}
