package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;

public class ProfAttUte_x_Uff_di_provenienzBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8496698008679738073L;


	@Override
	public String toString() {
		return "ProfAttUte_x_Uff_di_provenienzBean [ca_1=" + ca_1 + ", crg_1="
				+ crg_1 + ", cp_1=" + cp_1 + ", app_desc=" + app_desc
				+ ", rg_desc=" + rg_desc + ", pr_desc=" + pr_desc
				+ ", tipo_profilazione=" + tipo_profilazione
				+ ", scadenza_profilo=" + scadenza_profilo + ", data_scadenza="
				+ data_scadenza + ", numeroElementi=" + numeroElementi  
				+ ", codice_ufficio=" + codice_ufficio
				+ ", codice_cdr=" + codice_cdr
				+ ", tipo_abilitazione=" + tipo_abilitazione
				+ ", operazione_sul_profilo=" + operazione_sul_profilo												
				+ ", ufficio_destinazione=" + ufficio_destinazione + "]";
	}

	/**
	 * 
	 */
	//private static final long serialVersionUID = 3124420008331788861L;
/*
select PAU.CODICE_APPLICAZIONE as CA_1, PAU.CODICE_ROLE_GROUP as CRG_1, PAU.CODICE_PROFILO as CP_1, 
APP.DESCRIZIONE as APP_DESC, RG.DESCRIZIONE AS RG_DESC, PR.DESCRIZIONE AS PR_DESC, AGCU.TIPO_PROFILAZIONE, PAU.DATA_SCADENZA as SCADENZA_PROFILO 
 */
	
	private String ca_1;
	private String crg_1;
	private String cp_1;
	private String app_desc;
	private String rg_desc;
	private String pr_desc;
	private String tipo_profilazione;
	private String scadenza_profilo;
	private Date data_scadenza;
	private String codice_ufficio;
	private String codice_cdr;
	private String tipo_abilitazione;
	private String operazione_sul_profilo;
	private String ufficio_destinazione;
	private String TipoUfficio;
	
	
	public Date getData_scadenza() {
		return data_scadenza;
	}
	public void setData_scadenza(Date data_scadenza) {
		this.data_scadenza = data_scadenza;
	}
	public String getCa_1() {
		return ca_1;
	}
	public void setCa_1(String ca_1) {
		this.ca_1 = ca_1;
	}
	public String getCrg_1() {
		return crg_1;
	}
	public void setCrg_1(String crg_1) {
		this.crg_1 = crg_1;
	}
	public String getCp_1() {
		return cp_1;
	}
	public void setCp_1(String cp_1) {
		this.cp_1 = cp_1;
	}
	public String getApp_desc() {
		return app_desc;
	}
	public void setApp_desc(String app_desc) {
		this.app_desc = app_desc;
	}
	public String getRg_desc() {
		return rg_desc;
	}
	public void setRg_desc(String rg_desc) {
		this.rg_desc = rg_desc;
	}
	public String getPr_desc() {
		return pr_desc;
	}
	public void setPr_desc(String pr_desc) {
		this.pr_desc = pr_desc;
	}
	public String getTipo_profilazione() {
		return tipo_profilazione;
	}
	public void setTipo_profilazione(String tipo_profilazione) {
		this.tipo_profilazione = tipo_profilazione;
	}
	public String getScadenza_profilo() {
		return scadenza_profilo;
	}
	public void setScadenza_profilo(String scadenza_profilo) {
		this.scadenza_profilo = scadenza_profilo;
	}
	
	private int numeroElementi;
	
	public int getNumeroElementi() {
		return numeroElementi;
	}
	public void setNumeroElementi(int numeroElementi) {
		this.numeroElementi = numeroElementi;
	}
	public String getCodice_ufficio() {
		return codice_ufficio;
	}
	public void setCodice_ufficio(String codice_ufficio) {
		this.codice_ufficio = codice_ufficio;
	}
	public String getCodice_cdr() {
		return codice_cdr;
	}
	public void setCodice_cdr(String codice_cdr) {
		this.codice_cdr = codice_cdr;
	}
	public String getTipo_abilitazione() {
		return tipo_abilitazione;
	}
	public void setTipo_abilitazione(String tipo_abilitazione) {
		this.tipo_abilitazione = tipo_abilitazione;
	}
	public String getOperazione_sul_profilo() {
		return operazione_sul_profilo;
	}
	public void setOperazione_sul_profilo(String operazione_sul_profilo) {
		this.operazione_sul_profilo = operazione_sul_profilo;
	}
	public String getUfficio_destinazione() {
		return ufficio_destinazione;
	}
	public void setUfficio_destinazione(String ufficio_destinazione) {
		this.ufficio_destinazione = ufficio_destinazione;
	}
	public String getTipoUfficio() {
		return TipoUfficio;
	}
	public void setTipoUfficio(String tipoUfficio) {
		TipoUfficio = tipoUfficio;
	}
	
}
