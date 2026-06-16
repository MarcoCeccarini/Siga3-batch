package it.finanze.siga.bean;

import it.finanze.siga.util.bean.UtenteBean;

import java.util.Date;

public class AssociazioniOraBean extends OperatoreBean{

	private static final long serialVersionUID = 2701153962288297932L;
	
	private String richiedenteNome;
	private String richiedenteCognome;
	private String richiedenteCdr;
	private String richiedenteCf;
	private String richiedenteDescCdr;
	private String autorizzatoreNome;
	private String autorizzatoreCognome;
	private String autorizzatoreCdr;
	private String autorizzatoreDescCdr;
	private String autorizzatoreCf;  
	private String ridefinito;
	
	private UtenteBean amministratoreInizio;
	private String ammInizioStr;
	private UtenteBean amministratoreFine;
	private String ammFineStr;
	private Date dataInizioVal;
	private Integer idAuditInizio;
	private Date datafineVal;
	private Integer idAuditFine;
	
	private String cdr;
	private String descCdr;
	
	public String getRichiedenteNome() {
		return richiedenteNome;
	}
	public String getRichiedenteCognome() {
		return richiedenteCognome;
	}
	public String getRichiedenteCdr() {
		return richiedenteCdr;
	}
	public String getRichiedenteCf() {
		return richiedenteCf;
	}
	public String getRichiedenteDescCdr() {
		return richiedenteDescCdr;
	}
	public String getAutorizzatoreNome() {
		return autorizzatoreNome;
	}
	public String getAutorizzatoreCognome() {
		return autorizzatoreCognome;
	}
	public String getAutorizzatoreCdr() {
		return autorizzatoreCdr;
	}
	public String getAutorizzatoreDescCdr() {
		return autorizzatoreDescCdr;
	}
	public String getAutorizzatoreCf() {
		return autorizzatoreCf;
	}
	public String getCdr() {
		return cdr;
	}
	public String getDescCdr() {
		return descCdr;
	}
	public void setRichiedenteNome(String richiedenteNome) {
		this.richiedenteNome = richiedenteNome;
	}
	public void setRichiedenteCognome(String richiedenteCognome) {
		this.richiedenteCognome = richiedenteCognome;
	}
	public void setRichiedenteCdr(String richiedenteCdr) {
		this.richiedenteCdr = richiedenteCdr;
	}
	public void setRichiedenteCf(String richiedenteCf) {
		this.richiedenteCf = richiedenteCf;
	}
	public void setRichiedenteDescCdr(String richiedenteDescCdr) {
		this.richiedenteDescCdr = richiedenteDescCdr;
	}
	public void setAutorizzatoreNome(String autorizzatoreNome) {
		this.autorizzatoreNome = autorizzatoreNome;
	}
	public void setAutorizzatoreCognome(String autorizzatoreCognome) {
		this.autorizzatoreCognome = autorizzatoreCognome;
	}
	public void setAutorizzatoreCdr(String autorizzatoreCdr) {
		this.autorizzatoreCdr = autorizzatoreCdr;
	}
	public void setAutorizzatoreDescCdr(String autorizzatoreDescCdr) {
		this.autorizzatoreDescCdr = autorizzatoreDescCdr;
	}
	public void setAutorizzatoreCf(String autorizzatoreCf) {
		this.autorizzatoreCf = autorizzatoreCf;
	}
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}
	public void setDescCdr(String descCdr) {
		this.descCdr = descCdr;
	}
	public String getRidefinito() {
		return ridefinito;
	}
	public void setRidefinito(String ridefinito) {
		this.ridefinito = ridefinito;
	}
	public UtenteBean getAmministratoreInizio() {
		return amministratoreInizio;
	}
	public void setAmministratoreInizio(UtenteBean amministratoreInizio) {
		this.amministratoreInizio = amministratoreInizio;
	}
	public UtenteBean getAmministratoreFine() {
		return amministratoreFine;
	}
	public void setAmministratoreFine(UtenteBean amministratoreFine) {
		this.amministratoreFine = amministratoreFine;
	}
	public Date getDataInizioVal() {
		return dataInizioVal;
	}
	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}
	public Integer getIdAuditInizio() {
		return idAuditInizio;
	}
	public void setIdAuditInizio(Integer idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}
	public Date getDatafineVal() {
		return datafineVal;
	}
	public void setDatafineVal(Date datafineVal) {
		this.datafineVal = datafineVal;
	}
	public Integer getIdAuditFine() {
		return idAuditFine;
	}
	public void setIdAuditFine(Integer idAuditFine) {
		this.idAuditFine = idAuditFine;
	}
	public String getAmmInizioStr() {
		String str = null;
		
		if(amministratoreInizio != null)
			str = amministratoreInizio.getCognome() + " " + amministratoreInizio.getNome() + " (" + amministratoreInizio.getCodFiscaleUtente() + ")";
		return str;
	}
	public void setAmmInizioStr(String ammInizioStr) {
		this.ammInizioStr = ammInizioStr;
	}
	public String getAmmFineStr() {
		String str = null;
		
		if(amministratoreFine != null)
			str = amministratoreFine.getCognome() + " " + amministratoreFine.getNome() + " (" + amministratoreFine.getCodFiscaleUtente() + ")";
		return str;
	}
	public void setAmmFineStr(String ammFineStr) {
		this.ammFineStr = ammFineStr;
	}
 	
	
	
}