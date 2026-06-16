package it.finanze.siga.util.tree;

import it.finanze.siga.bean.BaseBean;

import java.io.Serializable;
import java.util.Date;

/**
 * per il fancy tree di profili.
 * 
 * Esempio che si vuole ottenere trasformando in json:
 * 
String jsonTree_4 = 
	"[{\"title\": \"Role Group 1\", \"hideCheckbox\": true, \"expanded\": true, \"children\":["+
		"{\"title\": \"Utente della DCA\", \"selected\": true, \"startSelected\": true},"+
		"{\"title\": \"Utente dell Ufficio Territoriale\", \"startSelected\": false}"+
	  "]},"+
    "{\"title\": \"Role Group 2\", \"hideCheckbox\": true},"+
	"{\"title\": \"Role Group 3\", \"hideCheckbox\": true}"+
"]";
 *	
 */
public class ProfiloTreeSubBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3963893640539990122L;
	
	/**
	 * per campi profilo
	 */
	private String codiceRoleGroup;
	private String codiceProfilo;
	/**
	 * è true quando visualizzando l'albero dei profili
	 */
	private boolean startSelected;
	/**
	 * altri campi per profilo
	 */
	private String roleGroupDesc;
	private String profiloDesc;
	private Date dataScadenza;
	private Date dataScadenzaVisibilita;
	private String tipoAbilitazione;
	private String codiceApplicazione;
	private String op;
	private String explicit_entitlement;
	private String origineAbilitazione;
	private String infoNote;
	
	
	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}

	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}

	/**
	 * per campi profilo
	 * per verifica iter 
	 */
	private boolean iterVerified;
	private String idIter;
	
	public ProfiloTreeSubBean(Date date) {
		dataScadenza = date;
	}

	public ProfiloTreeSubBean(Date date, Date date2) {
		dataScadenza = date;
		dataScadenzaVisibilita = date2;
	}

	public String getRoleGroupDesc() {
		return roleGroupDesc;
	}

	public void setRoleGroupDesc(String roleGroupDesc) {
		this.roleGroupDesc = roleGroupDesc;
	}

	public String getProfiloDesc() {
		return profiloDesc;
	}

	public void setProfiloDesc(String profiloDesc) {
		this.profiloDesc = profiloDesc;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getTipoAbilitazione() {
		return tipoAbilitazione;
	}

	public void setTipoAbilitazione(String tipoAbilitazione) {
		this.tipoAbilitazione = tipoAbilitazione;
	}

	public String getIdIter() {
		return idIter;
	}

	public void setIdIter(String idIter) {
		this.idIter = idIter;
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

	public boolean isStartSelected() {
		return startSelected;
	}

	public void setStartSelected(boolean startSelected) {
		this.startSelected = startSelected;
	}

	public boolean isIterVerified() {
		return iterVerified;
	}

	public void setIterVerified(boolean iterVerified) {
		this.iterVerified = iterVerified;
	}

	@Override
	public String toString() {
		return "ProfiloTreeSubBean [codiceRoleGroup=" + codiceRoleGroup
				+ ", codiceProfilo=" + codiceProfilo + ", startSelected="
				+ startSelected + ", iterVerified=" + iterVerified + ", explicit_entitlement=" + explicit_entitlement + ", origineAbilitazione="+origineAbilitazione+ ", infoNote="+infoNote+"]";
	}

	public Date getDataScadenzaVisibilita() {
		return dataScadenzaVisibilita;
	}

	public void setDataScadenzaVisibilita(Date dataScadenzaVisibilita) {
		this.dataScadenzaVisibilita = dataScadenzaVisibilita;
	}

	public String getExplicit_entitlement() {
		return explicit_entitlement;
	}

	public void setExplicit_entitlement(String explicit_entitlement) {
		this.explicit_entitlement = explicit_entitlement;
	}

	public String getInfoNote() {
		return infoNote;
	}

	public void setInfoNote(String infoNote) {
		this.infoNote = infoNote;
	}
	
}
