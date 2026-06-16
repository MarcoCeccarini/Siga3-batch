package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.List;

public class ExportRuoliDiUnCdrBean implements Serializable{

	
	private static final long serialVersionUID = 6514812024098088226L;
	private String codiceCDR;
	private String descrizioneCdr;
	private String richiedenteExcel;
	private String cfRichiedenteExcel;
	private String responsabileExcel;
	private String cfResponsabileExcel;
	private String autorizzatoreExcel;
	private String cfAutorizzatoreExcel;
	private OperatoreBean responsabileHR;
	private OperatoreBean responsabileHrSv;
	private OperatoreBean richiedente;
	private OperatoreBean autorizzatoreILiv;
	private List<RuoloRichiedenteBean> ruoliRichiedente;	
	private List<RuoloAutorizzatore_II_LivelloBean> ruoliAutorizzatore;	
	private List<RuoloAmministratoreAuditorBean> ruoliAmmAuditor;
	private List<RuoloDelegheBean> ruoliDeleghe;
	private String cfOperatore;
	private String nome;
	private String cognome;
	
	// true Aautorizzatore I Liv presente
	// false Autorizzatore I Liv non presente, sul DB null
	private boolean autorizzatorePrimoLivelloPresente;
	
	// Serve quando non e' presente l'autorizzatore di primo livello, e nel caso di ripristino
	private OperatoreBean autorizzatoreILivNuovoStandard; 
	
	
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public String getDescrizioneCdr() {
		return descrizioneCdr;
	}
	public void setDescrizioneCdr(String descrizioneCdr) {
		this.descrizioneCdr = descrizioneCdr;
	}
	public OperatoreBean getResponsabileHR() {
		return responsabileHR;
	}
	public void setResponsabileHR(OperatoreBean responsabileHR) {
		this.responsabileHR = responsabileHR;
	}
	public OperatoreBean getResponsabileHrSv() {
		return responsabileHrSv;
	}
	public void setResponsabileHrSv(OperatoreBean responsabileHrSv) {
		this.responsabileHrSv = responsabileHrSv;
	}
	public OperatoreBean getRichiedente() {
		return richiedente;
	}
	public void setRichiedente(OperatoreBean richiedente) {
		this.richiedente = richiedente;
	}
	public OperatoreBean getAutorizzatoreILiv() {
		return autorizzatoreILiv;
	}
	public void setAutorizzatoreILiv(OperatoreBean autorizzatoreILiv) {
		this.autorizzatoreILiv = autorizzatoreILiv;
	}
	public boolean isAutorizzatorePrimoLivelloPresente() {
		return autorizzatorePrimoLivelloPresente;
	}
	public void setAutorizzatorePrimoLivelloPresente(
			boolean autorizzatorePrimoLivelloPresente) {
		this.autorizzatorePrimoLivelloPresente = autorizzatorePrimoLivelloPresente;
	}
	public OperatoreBean getAutorizzatoreILivNuovoStandard() {
		return autorizzatoreILivNuovoStandard;
	}
	public void setAutorizzatoreILivNuovoStandard(
			OperatoreBean autorizzatoreILivNuovoStandard) {
		this.autorizzatoreILivNuovoStandard = autorizzatoreILivNuovoStandard;
	}
	public String getRichiedenteExcel() {
		return richiedenteExcel;
	}
	public void setRichiedenteExcel(String richiedenteExcel) {
		this.richiedenteExcel = richiedenteExcel;
	}
	public String getCfRichiedenteExcel() {
		return cfRichiedenteExcel;
	}
	public void setCfRichiedenteExcel(String cfRichiedenteExcel) {
		this.cfRichiedenteExcel = cfRichiedenteExcel;
	}
	public String getResponsabileExcel() {
		return responsabileExcel;
	}
	public void setResponsabileExcel(String responsabileExcel) {
		this.responsabileExcel = responsabileExcel;
	}
	public String getCfResponsabileExcel() {
		return cfResponsabileExcel;
	}
	public void setCfResponsabileExcel(String cfResponsabileExcel) {
		this.cfResponsabileExcel = cfResponsabileExcel;
	}
	public String getAutorizzatoreExcel() {
		return autorizzatoreExcel;
	}
	public void setAutorizzatoreExcel(String autorizzatoreExcel) {
		this.autorizzatoreExcel = autorizzatoreExcel;
	}
	public String getCfAutorizzatoreExcel() {
		return cfAutorizzatoreExcel;
	}
	public void setCfAutorizzatoreExcel(String cfAutorizzatoreExcel) {
		this.cfAutorizzatoreExcel = cfAutorizzatoreExcel;
	}
	public List<RuoloRichiedenteBean> getRuoliRichiedente() {
		return ruoliRichiedente;
	}
	public void setRuoliRichiedente(List<RuoloRichiedenteBean> ruoliRichiedente) {
		this.ruoliRichiedente = ruoliRichiedente;
	}
	public List<RuoloAutorizzatore_II_LivelloBean> getRuoliAutorizzatore() {
		return ruoliAutorizzatore;
	}
	public void setRuoliAutorizzatore(
			List<RuoloAutorizzatore_II_LivelloBean> ruoliAutorizzatore) {
		this.ruoliAutorizzatore = ruoliAutorizzatore;
	}
	public List<RuoloAmministratoreAuditorBean> getRuoliAmmAuditor() {
		return ruoliAmmAuditor;
	}
	public void setRuoliAmmAuditor(
			List<RuoloAmministratoreAuditorBean> ruoliAmmAuditor) {
		this.ruoliAmmAuditor = ruoliAmmAuditor;
	}
	public List<RuoloDelegheBean> getRuoliDeleghe() {
		return ruoliDeleghe;
	}
	public void setRuoliDeleghe(List<RuoloDelegheBean> ruoliDeleghe) {
		this.ruoliDeleghe = ruoliDeleghe;
	}
	public String getCfOperatore() {
		return cfOperatore;
	}
	public void setCfOperatore(String cfOperatore) {
		this.cfOperatore = cfOperatore;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
}
