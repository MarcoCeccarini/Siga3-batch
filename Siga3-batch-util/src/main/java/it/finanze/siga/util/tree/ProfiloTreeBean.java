package it.finanze.siga.util.tree;

import it.finanze.siga.util.Utils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class ProfiloTreeBean extends BaseTreeBean2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7305340252921870674L;
	
	/**
	 * per campi profilo
	 */
	// test compattazione json
	//	@SerializedName("codRG")
	private String codiceRoleGroup;
	private String codiceProfilo;
	private String roleGroupDesc;
	private String profiloDesc;
	private Date dataScadenza;
	private Date dataScadenzaVisibilita;
	private String tipoAbilitazione;
	private String attivo;
	private String codiceApplicazione;
	private String applicazioneDesc;
	private String op;
	private String explicit_entitlement="";
	private String origineAbilitazione="";
	private String disponibileTitolareRuolo;
	
	private String assegnato;
	private String flagAggiunto;
	private String flagRimosso;
	private Date dataScadenzaOld;
	private String dataScadenzaString;
	private Date data_inizio_val;
	private Date data_fine_val;
	private String cfUtente;
	private String codiceCdr;
	private String codiceUfficio;
	private String idRichiestaVisibilita;
	private String infoNote;
	private String appDescCat;

	private String tooltipDesc;
	/**
	 * per verifica iter 
	 */
	private boolean iterVerified;
	private String idIter;
	/**
	 * icona bianca di default: mi serve di mantenere le icone su albero per il progress durante il caricamento.
	 * ma noin voglio mostrare icone x item, quindi uso questa icona bianca che scompare.
	 */
	//private String icon = Constants.ICON_BLANK_POINT_PATH;
	private String icon;
	private List<ProfiloTreeBean> children;
	
	@Override
	public String toString() {
		return super.toString() 
				+ "ProfiloTreeBean [codiceRoleGroup=" + codiceRoleGroup
				+ ", codiceProfilo=" + codiceProfilo + ", roleGroupDesc="
				+ roleGroupDesc + ", profiloDesc=" + profiloDesc
				+ ", dataScadenza=" + dataScadenza + ", dataScadenzaVisibilita=" + dataScadenzaVisibilita + ", tipoAbilitazione="
				+ tipoAbilitazione + ", attivo=" + attivo
				+ ", codiceApplicazione=" + codiceApplicazione
				+ ", applicazioneDesc=" + applicazioneDesc + ", op=" + op
				+ ", iterVerified=" + iterVerified + ", idIter=" + idIter
				+ ", children=" + children + ", explicit_entitlement=" + explicit_entitlement 
				+ ", disponibileTitolareRuolo=" + disponibileTitolareRuolo
				+ ", origineAbilitazione="+origineAbilitazione
				+ ", assegnato=" + assegnato + ",flagAggiunto=" + flagAggiunto + ",flagRimosso="+ flagRimosso 
				+ ", tooltipDesc=" + tooltipDesc  +"]";
	}

	/* CONSTRUCTORS */
	public ProfiloTreeBean() {
		super();
	}
	
	/* GETTERS AND SETTERS */
	
	
	public String getIcon() {
		return icon;
	}

	public String getDisponibileTitolareRuolo() {
		return disponibileTitolareRuolo;
	}

	public void setDisponibileTitolareRuolo(String disponibileTitolareRuolo) {
		this.disponibileTitolareRuolo = disponibileTitolareRuolo;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
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

	public String getApplicazioneDesc() {
		return applicazioneDesc;
	}

	public void setApplicazioneDesc(String applicazioneDesc) {
		this.applicazioneDesc = applicazioneDesc;
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

	public String getAttivo() {
		return attivo;
	}

	public void setAttivo(String attivo) {
		this.attivo = attivo;
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
	
	public boolean isIterVerified() {
		return iterVerified;
	}
	public void setIterVerified(boolean iterVerified) {
		this.iterVerified = iterVerified;
	}
	public List<ProfiloTreeBean> getChildren() {
		return children;
	}
	public void setChildren(List<ProfiloTreeBean> children) {
		this.children = children;
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

	public String getOrigineAbilitazione() {
		return origineAbilitazione;
	}

	public void setOrigineAbilitazione(String origineAbilitazione) {
		this.origineAbilitazione = origineAbilitazione;
	}


	public String getAssegnato() {
		return assegnato;
	}

	public void setAssegnato(String assegnato) {
		this.assegnato = assegnato;
	}

	public String getFlagAggiunto() {
		return flagAggiunto;
	}

	public void setFlagAggiunto(String flagAggiunto) {
		this.flagAggiunto = flagAggiunto;
	}

	public String getFlagRimosso() {
		return flagRimosso;
	}

	public void setFlagRimosso(String flagRimosso) {
		this.flagRimosso = flagRimosso;
	}

	public Date getDataScadenzaOld() {
		return dataScadenzaOld;
	}

	public void setDataScadenzaOld(Date dataScadenzaOld) {
		this.dataScadenzaOld = dataScadenzaOld;
	}

	public String getDataScadenzaString() {		
		return Utils.dateStringFromDate(this.dataScadenza);
	}

	public void setDataScadenzaString(String dataScadenzaString) {
		this.dataScadenzaString = dataScadenzaString;
	}

	public Date getData_inizio_val() {
		return data_inizio_val;
	}

	public void setData_inizio_val(Date data_inizio_val) {
		this.data_inizio_val = data_inizio_val;
	}

	public Date getData_fine_val() {
		return data_fine_val;
	}

	public void setData_fine_val(Date data_fine_val) {
		this.data_fine_val = data_fine_val;
	}

	public String getCfUtente() {
		return cfUtente;
	}

	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}

	public String getCodiceCdr() {
		return codiceCdr;
	}

	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public String getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}

	public void setIdRichiestaVisibilita(String idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}

	public String getTooltipDesc() {
		return tooltipDesc;
	}

	public void setTooltipDesc(String tooltipDesc) {
		this.tooltipDesc = tooltipDesc;
	}

	public String getInfoNote() {
		return infoNote;
	}

	public void setInfoNote(String infoNote) {
		this.infoNote = infoNote;
	}

	public String getAppDescCat() {
		return appDescCat;
	}

	public void setAppDescCat(String appDescCat) {
		this.appDescCat = appDescCat;
	}
	
	
}
