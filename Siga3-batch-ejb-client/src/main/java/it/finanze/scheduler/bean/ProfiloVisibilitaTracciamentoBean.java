package it.finanze.scheduler.bean;

import it.finanze.siga.util.bean.UtenteBean;

import java.io.Serializable;
import java.util.Date;

public class ProfiloVisibilitaTracciamentoBean extends ProfiloUtenteInVisibilitaBean implements Serializable{

	private static final long serialVersionUID = 5026045320574613885L;
	private Date dataVariazione;
	private Integer idCaricamentoMassivo;
	private Integer idAudit;
	private String azione;
	private String cfVariazione;
	
	private UtenteBean utente;
	private String profiloDesc;
	private String rgDesc;

	
	public Date getDataVariazione() {
		return dataVariazione;
	}
	public void setDataVariazione(Date dataVariazione) {
		this.dataVariazione = dataVariazione;
	}
	public Integer getIdCaricamentoMassivo() {
		return idCaricamentoMassivo;
	}
	public void setIdCaricamentoMassivo(Integer idCaricamentoMassivo) {
		this.idCaricamentoMassivo = idCaricamentoMassivo;
	}
	public Integer getIdAudit() {
		return idAudit;
	}
	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}
	public String getAzione() {
		return azione;
	}
	public void setAzione(String azione) {
		this.azione = azione;
	}
	public String getCfVariazione() {
		return cfVariazione;
	}
	public void setCfVariazione(String cfVariazione) {
		this.cfVariazione = cfVariazione;
	}
	public UtenteBean getUtente() {
		return utente;
	}
	public void setUtente(UtenteBean utente) {
		this.utente = utente;
	}
	public String getProfiloDesc() {
		return profiloDesc;
	}
	public void setProfiloDesc(String profiloDesc) {
		this.profiloDesc = profiloDesc;
	}
	public String getRgDesc() {
		return rgDesc;
	}
	public void setRgDesc(String rgDesc) {
		this.rgDesc = rgDesc;
	}
	
	
}
