package it.finanze.siga.bean;

import java.io.Serializable;

/**
 * @author Admin
 *
 */
public class RecordCatalogoBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6457443895122483068L;
	private String codiceRoleGroup;
	private String roleGroupDesc;
	private String codiceProfilo;
	private String profiloDesc;
	private String applicazione;
	private String codiceApplicazione;
	private String funzione;
	private int idFunzione;
	private String area;
	private String riferimento;
	private String ufficiValidita;
	private String raggruppamentoFunzionale;
	private String codiceRaggrFunzionale;
	private String obsoleto;
	
	public String toString(){
		return "CODICE ROLE_GROUP: " + codiceRoleGroup + " ROLE_GROUP: " + roleGroupDesc 
				+ " CODICE PROFILO: " + codiceProfilo +  " PROFILO: " + profiloDesc
				+ " CODICE_APP: " + codiceApplicazione + " APPLICAZIONE: " + applicazione
				+ " CODICE RF: " + codiceRaggrFunzionale + " RAGGR FUNZIONALE: "
				+ " ID_FUNZIONE: " + idFunzione + " FUNZIONE: " + funzione
				+ " UFFICI: " + ufficiValidita + " OBSOLETO: " + obsoleto;
	}
	
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getRoleGroupDesc() {
		return roleGroupDesc;
	}
	public void setRoleGroupDesc(String roleGroupDesc) {
		this.roleGroupDesc = roleGroupDesc;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public String getProfiloDesc() {
		return profiloDesc;
	}
	public void setProfiloDesc(String profiloDesc) {
		this.profiloDesc = profiloDesc;
	}
	public String getApplicazione() {
		return applicazione;
	}
	public void setApplicazione(String applicazione) {
		this.applicazione = applicazione;
	}
	public String getFunzione() {
		return funzione;
	}
	public void setFunzione(String funzione) {
		this.funzione = funzione;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRiferimento() {
		return riferimento;
	}
	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}
	public String getUfficiValidita() {
		return ufficiValidita;
	}
	public void setUfficiValidita(String ufficiValidita) {
		this.ufficiValidita = ufficiValidita;
	}
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}
	public String getRaggruppamentoFunzionale() {
		return raggruppamentoFunzionale;
	}
	public void setRaggruppamentoFunzionale(String raggruppamentoFunzionale) {
		this.raggruppamentoFunzionale = raggruppamentoFunzionale;
	}
	public String getCodiceRaggrFunzionale() {
		return codiceRaggrFunzionale;
	}
	public void setCodiceRaggrFunzionale(String codiceRaggrFunzionale) {
		this.codiceRaggrFunzionale = codiceRaggrFunzionale;
	}
	public int getIdFunzione() {
		return idFunzione;
	}
	public void setIdFunzione(int idFunzione) {
		this.idFunzione = idFunzione;
	}
	
	public String getObsoleto() {
		
		if(obsoleto == null || obsoleto.equals(""))
			return "NO";
		else
			return "SI";
	}

	public void setObsoleto(String obsoleto) {
		this.obsoleto = obsoleto;
	}
	
	
}
