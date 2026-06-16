package it.finanze.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

public class AdminGroupTipoUffCdr implements Serializable{

	private static final long serialVersionUID = -337167584482650005L;
	private String codiceApplicazione;
	private String codiceRoleGroup;
	private String codiceProfilo;
	private String tipoUfficio;
	private String tipologiaCdr;
	private String tipoProfilazione;
	private String regione;
	private String provincia;
	private Date dataInizioVal;
	private Date dataFineVal;
	private Integer idAuditInizio;
	private String codiceFiscaleInizio;
	private Integer idAuditFine;
	private String codiceFiscaleFine;
	private String codiceUfficio;
	
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
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
	public String getTipoUfficio() {
		return tipoUfficio;
	}
	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
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
	public Integer getIdAuditInizio() {
		return idAuditInizio;
	}
	public void setIdAuditInizio(Integer idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}
	public String getCodiceFiscaleInizio() {
		return codiceFiscaleInizio;
	}
	public void setCodiceFiscaleInizio(String codiceFiscaleInizio) {
		this.codiceFiscaleInizio = codiceFiscaleInizio;
	}
	public Integer getIdAuditFine() {
		return idAuditFine;
	}
	public void setIdAuditFine(Integer idAuditFine) {
		this.idAuditFine = idAuditFine;
	}
	public String getCodiceFiscaleFine() {
		return codiceFiscaleFine;
	}
	public void setCodiceFiscaleFine(String codiceFiscaleFine) {
		this.codiceFiscaleFine = codiceFiscaleFine;
	}
	public String getTipologiaCdr() {
		return tipologiaCdr;
	}
	public void setTipologiaCdr(String tipologiaCdr) {
		this.tipologiaCdr = tipologiaCdr;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getTipoProfilazione() {
		return tipoProfilazione;
	}
	public void setTipoProfilazione(String tipoProfilazione) {
		this.tipoProfilazione = tipoProfilazione;
	}
	
	
}
