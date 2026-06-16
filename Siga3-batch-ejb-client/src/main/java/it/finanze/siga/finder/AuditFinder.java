package it.finanze.siga.finder;

import java.util.Date;

public class AuditFinder extends BasePaginateFinder {

	private static final long serialVersionUID = 6430267358866330799L;
	private Integer progAudit;
	public Integer getProgAudit() {
		return progAudit;
	}
	public void setProgAudit(Integer progAudit) {
		this.progAudit = progAudit;
	}
	private String cf;
	private String CDRAmministratore;
	private Date dataOra;
	private String tabAggiornata;
	private String testo;
	private String cdrRuolo;
	private String ruoloOperazione;
	
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	
	public Date getDataOra() {
		return dataOra;
	}
	public void setDataOra(Date dataOra) {
		this.dataOra = dataOra;
	}
	public String getTabAggiornata() {
		return tabAggiornata;
	}
	public void setTabAggiornata(String tabAggiornata) {
		this.tabAggiornata = tabAggiornata;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public String getCDRAmministratore() {
		return CDRAmministratore;
	}
	public void setCDRAmministratore(String cDRAmministratore) {
		CDRAmministratore = cDRAmministratore;
	}
	public String getCdrRuolo() {
		return cdrRuolo;
	}
	public void setCdrRuolo(String cdrRuolo) {
		this.cdrRuolo = cdrRuolo;
	}
	public String getRuoloOperazione() {
		return ruoloOperazione;
	}
	public void setRuoloOperazione(String ruoloOperazione) {
		this.ruoloOperazione = ruoloOperazione;
	}
	
	
	
	
}
