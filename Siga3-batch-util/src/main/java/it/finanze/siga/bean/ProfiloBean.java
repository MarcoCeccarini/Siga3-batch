package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.finanze.siga.util.Constants;
import it.finanze.siga.util.Utils;
import it.finanze.siga.util.bean.UtenteBean;

public abstract class ProfiloBean extends BaseBean implements Serializable, Comparable<ProfiloBean>, Cloneable {
	private static final long serialVersionUID = 7288574435618761882L; 
	//chiavi per il campo op
	public final static String toAddOp = "toAdd";
	public final static String toRemoveOp = "toRemove";
	public final static String attivoNoOP = "attivoNoOP";
	//valori per il campo operazione
	public final static String aggiunto = "Abilitazione";
	public final static String rimosso = "Disabilitazione";
	//per inserire nel db operazione sul profilo
	private String operazioneSulProfilo;
	private String codiceApplicazione;
	private String codiceRoleGroup;
	private String codiceProfilo;
	private String applicazioneDesc;
	private String roleGroupDesc;
	private String profiloDesc;
	private Date dataScadenza;
	private Date dataScadenzaOld;
	private String dataFineVal;
	private String dataInizioVal;
	// per prendere la data scadenza da ProfiloRichiestaBean
	private Date scadenza;
	private String dataScadenzaString;
	private String dataScadenzaAssegnatiString;
	private String tipoAbilitazione;
	private String assegnato;
	private String attivo;
	private String attivoStr;
	private String idIter;
	private String iterDesc;
	private String op;
	private boolean selected;
	private boolean unselectable;
	private boolean hideCheckbox;
	private boolean expanded;
	//campi aggiuntivi nel caso visibilita//
	private String tipoUfficio;
	private String tipoUfficioDesc;
	private String tipoCDR;
	private String tipoCDRDesc;
	private Date dataScadenzaVisibilita;
	// per formattazione data x UI
	private String dateString;
	private String operazione;
	private String tipoAbilitazioneString;
	

	private String dataScadenzaVisibilitaString;
	// per iltitolo del nodo albero in UI
	private String profileNodeTitle;
	//cambioUfficioCAT Pietro 11/09/2014
	private String explicit_entitlement;
	private Date data_inizio_val;
	private Date data_fine_val;
	private String descrizione;
	private String origineAbilitazione="";
	private String descrizioneProfiloRoot = "";
	private String cdrUffValiditaProfVisibile;
	private String cdrUffValiditaProf="";
	private String descrizioneCDR;
	private String descrizioneFinale;
	private String assegnazione;
	private String descrizioneExplicitEntitlement;
	private String raggruppamentoProfili;
	private List<ProfiloBean> listaProfiloMultiUffCDR = new ArrayList<ProfiloBean>();
	private String codiceUfficio;
	private String disponibileTitolareRuolo;
	// flag per la descrizio aggiunti
	private String flagAggiunto;
	private String flagRimosso;
	private boolean iterVerified;
	private int motivoScarto;
	private Integer idAuditInizio;
	private Integer idAuditFine;
	private String cfInizio;
	private String cfFine;
	private boolean aggiornamento;
	private String tipoUfficioSelezionato;
	private String multiUfficio;
	// campo info note per ambito applicativo applicazioni non integrate
	private String infoNote;
	private String appDescCat;
	
	//campo per inserire utente "Richiedente" che fa  richiesta del report in excel del Cruscotto Monitoraggio
	private UtenteBean utente;
	
	
	
	public UtenteBean getUtente() {
		return utente;
	}

	public void setUtente(UtenteBean utente) {
		this.utente = utente;
	}

	public String getDisponibileTitolareRuolo() {
		return disponibileTitolareRuolo;
	}

	public void setDisponibileTitolareRuolo(String disponibileTitolareRuolo) {
		this.disponibileTitolareRuolo = disponibileTitolareRuolo;
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	/**
	 * @return the descrizioneExplicitEntitlement
	 */
	public String getDescrizioneExplicitEntitlement() {
		return descrizioneExplicitEntitlement;
	}

	/**
	 * @param descrizioneExplicitEntitlement the descrizioneExplicitEntitlement to set
	 */
	public void setDescrizioneExplicitEntitlement(
			String descrizioneExplicitEntitlement) {
		this.descrizioneExplicitEntitlement = descrizioneExplicitEntitlement;
	}

	public ProfiloBean() {
	}

	/** GETTERS AND SETTERS **/
	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isHideCheckbox() {
		return hideCheckbox;
	}

	public void setHideCheckbox(boolean hideCheckbox) {
		this.hideCheckbox = hideCheckbox;
	}

	public boolean isUnselectable() {
		return unselectable;
	}

	public void setUnselectable(boolean unselectable) {
		this.unselectable = unselectable;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getOperazione() {
		return operazione;
	}

	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}

	public String getDateString(){
		if(dataScadenza!=null)
			return Utils.dateStringFromDate(dataScadenza);
		else
			return "";
	}

	public String getTipoUfficio() {
		return tipoUfficio;
	}
	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}
	public String getTipoUfficioDesc() {
		return tipoUfficioDesc;
	}
	public void setTipoUfficioDesc(String tipoUfficioDesc) {
		this.tipoUfficioDesc = tipoUfficioDesc;
	}
	public String getTipoCDR() {
		return tipoCDR;
	}
	public void setTipoCDR(String tipoCDR) {
		this.tipoCDR = tipoCDR;
	}
	public String getTipoCDRDesc() {
		return tipoCDRDesc;
	}
	public void setTipoCDRDesc(String tipoCDRDesc) {
		this.tipoCDRDesc = tipoCDRDesc;
	}
	public String getIdIter() {
		return idIter;
	}
	public void setIdIter(String idIter) {
		this.idIter = idIter;
	}
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
	public String getApplicazioneDesc() {
		return applicazioneDesc;
	}
	public void setApplicazioneDesc(String applicazioneDesc) {
		this.applicazioneDesc = applicazioneDesc;
	}
	public String getRoleGroupDesc() {
		return roleGroupDesc!=null?roleGroupDesc:"";
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
		this.attivoStr = attivo;
	}

	public boolean isAttivo(){
		if(attivo==null)
			return false;
		if(attivo.equalsIgnoreCase("SI"))
			return true;
		else if(attivo.equalsIgnoreCase("NO"))
			return false;
		else
			throw new RuntimeException("attivo deve essere SI o NO !!!!");
	}

	@Override
	public int compareTo(ProfiloBean o) {
		if(this.codiceApplicazione.compareToIgnoreCase(o.codiceApplicazione) > 0)
			return 1;
		else if(this.codiceApplicazione.compareToIgnoreCase(o.codiceApplicazione) < 0)
			return -1;
		else if(this.codiceRoleGroup.compareToIgnoreCase(o.codiceRoleGroup) > 0)
			return 1;
		else if(this.codiceRoleGroup.compareToIgnoreCase(o.codiceRoleGroup) < 0)
			return -1;
		else if (this.codiceProfilo.compareToIgnoreCase(o.codiceProfilo) > 0 )
			return 1;
		else if (this.codiceProfilo.compareToIgnoreCase(o.codiceProfilo) < 0 )
			return -1;
		else 
			return 0;
	}

	public String getProfileNodeTitle_OLD(){
		if(profileNodeTitle!=null && profileNodeTitle.length()>0){
			return profileNodeTitle;
		}

		StringBuilder response = new StringBuilder("");
		if(getProfiloDesc()!=null)
			response.append(getProfiloDesc());

		StringBuilder responseToRight = new StringBuilder("");
		String intermezzo = "";
		if(dataScadenza!=null){
			responseToRight.append(dataScadenza!=null?getDateString():"");
			intermezzo = " - ";
		}
		if(tipoAbilitazione!=null && tipoAbilitazione.length()>0 && tipoAbilitazione.equalsIgnoreCase("S")){
			responseToRight.append(intermezzo).append("Speciale");
		}
		if(tipoAbilitazione!=null || dataScadenza!=null)
			response.append("<span class='profile-element-on-the-right'>"+responseToRight+"</span>");

		// Logg.getLogger().debug("getProfileNodeTitle(): "+response);

		return response.toString();
	}

	public String getProfileNodeTitle(){
		if(profileNodeTitle!=null && profileNodeTitle.length()>0){
			return profileNodeTitle;
		}

		StringBuilder response = new StringBuilder("");

		if(getProfiloDesc()!=null){
			response.append("<span>" + getProfiloDesc() + "</span>");
		}

		StringBuilder responseToRight = new StringBuilder("");
		StringBuilder responseToRight2 = new StringBuilder("");
		StringBuilder responseToRightDesc = new StringBuilder("");
		
//		------------ Introduzione della descrizione nell'albero dei profili -----------------------------------------
		if(this.getDescrizioneExplicitEntitlement()!=null){
			responseToRightDesc.append(this.getDescrizioneExplicitEntitlement());
			response.append("<span class='profile-element-on-the-right'>"+responseToRightDesc.toString()+"</span>");
		}
//		---------------------------------------------------------------------------------------------------------------
		
		if(tipoAbilitazione!=null && tipoAbilitazione.length()>0 && tipoAbilitazione.equalsIgnoreCase("S")){
			responseToRight2.append("Abilitazione Extra-Ufficio");
			response.append("<span class='profile-element-on-the-right'>"+responseToRight2.toString()+"</span>");
		}else{
			responseToRight2.append("Ordinario");
			response.append("<span class='profile-element-on-the-right' style='display:none'>"+responseToRight2.toString()+"</span>");
		}
		if(dataScadenza!=null && "SI".equals(attivo)){
			String dateString = "";
			
			if(dataScadenza!=null)
				dateString = Utils.dateStringFromDate(dataScadenza);
			
			responseToRight.append(dateString != null && !"".equalsIgnoreCase(dateString) ? dateString : "");
			response.append("<span id='dataScad' class='profile-element-on-the-right'>"+"Fino al: "+responseToRight.toString()+"</span>");
		}else{
			responseToRight.append("nessuna data");
			response.append("<span id='dataScad' class='profile-element-on-the-right' style='display:none;'>"+responseToRight.toString()+"</span>");
		}
	
		
		return response.toString();
	}

	public String getProfileNodeTitle_NEW(){
		if(profileNodeTitle!=null && profileNodeTitle.length()>0){
			return profileNodeTitle;
		}

		StringBuilder response = new StringBuilder("");
		if(getProfiloDesc()!=null)
			response.append(getProfiloDesc());

		String intermezzo = " - ";
		StringBuilder responseToRight = new StringBuilder("");
		boolean isDataScadenza = false, isTipoAbilitazioneSpeciale = false;
		if(dataScadenza!=null){
			responseToRight.append(getDateString());
			isDataScadenza = true;
		}else{
			responseToRight.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		response.append("<span class='profile-element-on-the-right-2'>" + responseToRight.toString() + "</span>");
		responseToRight = new StringBuilder("");
		if(tipoAbilitazione!=null && tipoAbilitazione.length()>0 && tipoAbilitazione.equalsIgnoreCase("S")){
			isTipoAbilitazioneSpeciale = true;
			responseToRight.append(getDateString());
		}else{
			responseToRight.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		if(isDataScadenza && isTipoAbilitazioneSpeciale)
			response.append(intermezzo);

		response.append("<span class='profile-element-on-the-right'>"+responseToRight+"</span>");

		//Logg.getLogger().debug("getProfileNodeTitle(): "+response);

		return response.toString();
	}

	// setta il titolo solo nel casodi profilo non richiedibile
	public void setProfileNodeTitle(String title){
		this.profileNodeTitle= title;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceApplicazione == null) ? 0 : codiceApplicazione.hashCode());
		result = prime * result + ((codiceProfilo == null) ? 0 : codiceProfilo.hashCode());
		result = prime * result + ((codiceRoleGroup == null) ? 0 : codiceRoleGroup.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfiloBean other = (ProfiloBean) obj;
		if (codiceApplicazione == null) {
			if (other.codiceApplicazione != null)
				return false;
		} else if (!codiceApplicazione.equals(other.codiceApplicazione))
			return false;
		if (codiceProfilo == null) {
			if (other.codiceProfilo != null)
				return false;
		} else if (!codiceProfilo.equals(other.codiceProfilo))
			return false;
		if (codiceRoleGroup == null) {
			if (other.codiceRoleGroup != null)
				return false;
		} else if (!codiceRoleGroup.equals(other.codiceRoleGroup))
			return false;
		return true;
	}


	public Date getData_inizio_val() {
		return data_inizio_val;
	}


	public void setData_inizio_val(Date data_inizio_val) {
		this.data_inizio_val = data_inizio_val;
	}


	public String getExplicit_entitlement() {
		return explicit_entitlement;
	}


	public void setExplicit_entitlement(String explicit_entitlement) {
		this.explicit_entitlement = explicit_entitlement;
	}


	public Date getData_fine_val() {
		return data_fine_val;
	}


	public void setData_fine_val(Date data_fine_val) {
		this.data_fine_val = data_fine_val;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getAttivoStr() {
		return attivoStr;
	}
	public void setAttivoStr(String attivoStr) {
		this.attivoStr = attivoStr;
	}
	public String getOperazioneSulProfilo() {
		if(operazione.compareToIgnoreCase(ProfiloBean.aggiunto)==0){
			operazioneSulProfilo = Constants.ABILITA; 
		}else if(operazione.compareToIgnoreCase(ProfiloBean.rimosso)==0){
			operazioneSulProfilo = Constants.DISABILITA;
		}
		return operazioneSulProfilo;
	}
	public void setOperazioneSulProfilo(String operazioneSulProfilo) {
		this.operazioneSulProfilo = operazioneSulProfilo;
	}

	public String getCostanteABILITA(){
		return Constants.ABILITA;
	}

	public String getCostanteDISABILITA(){
		return Constants.DISABILITA;
	}

	/**
	 * Mostra Ordinario o Speciale se O o S
	 * @return Ordinario o Speciale
	 */
	public String getTipoAbilitazioneString() {
		
		if(tipoAbilitazione == null)
			System.out.println(codiceProfilo);
		
		if(tipoAbilitazione != null && tipoAbilitazione.equalsIgnoreCase(Constants.O))
			tipoAbilitazioneString = "";
		else if(tipoAbilitazione != null && tipoAbilitazione.equalsIgnoreCase(Constants.S))
			tipoAbilitazioneString = Constants.ExtraUfficio;
		else
			tipoAbilitazioneString = "";
		return tipoAbilitazioneString;
	}
	@Override
	public String toString() {
		return "ProfiloBean [operazioneSulProfilo=" + operazioneSulProfilo
				+ ", codiceApplicazione=" + codiceApplicazione
				+ ", codiceRoleGroup=" + codiceRoleGroup + ", codiceProfilo="
				+ codiceProfilo + ", applicazioneDesc=" + applicazioneDesc
				+ ", roleGroupDesc=" + roleGroupDesc + ", profiloDesc="
				+ profiloDesc + ", dataScadenza=" + dataScadenza
				+ ", dataScadenzaString=" + dataScadenzaString
				+ ", dataScadenzaAssegnatiString="
				+ dataScadenzaAssegnatiString + ", tipoAbilitazione="
				+ tipoAbilitazione + ", attivo=" + attivo + ", attivoStr="
				+ attivoStr + ", idIter=" + idIter + ", op=" + op
				+ ", selected=" + selected + ", unselectable=" + unselectable
				+ ", hideCheckbox=" + hideCheckbox + ", expanded=" + expanded
				+ ", tipoUfficio=" + tipoUfficio + ", tipoUfficioDesc="
				+ tipoUfficioDesc + ", tipoCDR=" + tipoCDR + ", tipoCDRDesc="
				+ tipoCDRDesc + ", dataScadenzaVisibilita="
				+ dataScadenzaVisibilita + ", dateString=" + dateString
				+ ", operazione=" + operazione + ", tipoAbilitazioneString="
				+ tipoAbilitazioneString + ", dataScadenzaVisibilitaString="
				+ dataScadenzaVisibilitaString + ", profileNodeTitle="
				+ profileNodeTitle + ", explicit_entitlement="
				+ explicit_entitlement + ", data_inizio_val=" + data_inizio_val
				+ ", data_fine_val=" + data_fine_val + ", descrizione="
				+ descrizione + ", origineAbilitazione=" + origineAbilitazione
				+ ", descrizioneProfiloRoot=" + descrizioneProfiloRoot
				+ ", cdrUffValiditaProfVisibile=" + cdrUffValiditaProfVisibile
				+ ", cdrUffValiditaProf=" + cdrUffValiditaProf
				+ ", descrizioneCDR=" + descrizioneCDR + ", descrizioneFinale="
				+ descrizioneFinale + ", assegnazione=" + assegnazione 
				+ ", disponibileTitolareRuolo=" + disponibileTitolareRuolo
				+ ",assegnato="  + assegnato 	+",flagAggiunto=" + flagAggiunto
				+ ",flagRimosso=" + flagRimosso
				+"]";
	}
	
	public Object clone() {
	    try {
	      return super.clone();
	    }
	    catch(CloneNotSupportedException e) {
	      return null;
	    }
	}

	public Date getDataScadenzaVisibilita() {
		return dataScadenzaVisibilita;
	}

	public void setDataScadenzaVisibilita(Date dataScadenzaVisibilita) {
		this.dataScadenzaVisibilita = dataScadenzaVisibilita;		
	}
	
	public String getDataScadenzaVisibilitaString() {
		dataScadenzaVisibilitaString =  Utils.dateStringFromDate(this.dataScadenzaVisibilita);
		return dataScadenzaVisibilitaString;
	}

	public void setDataScadenzaVisibilitaString(
			String dataScadenzaVisibilitaString) {
		this.dataScadenzaVisibilitaString = dataScadenzaVisibilitaString;
	}
	
	public String getDataScadenzaString() {		
		return Utils.dateStringFromDate(this.dataScadenza);
	}

	public void setDataScadenzaString(String dataScadenzaString) {
		this.dataScadenzaString = dataScadenzaString;
	}

	public String getDataScadenzaAssegnatiString() {
		return dataScadenzaAssegnatiString;
	}

	public void setDataScadenzaAssegnatiString(String dataScadenzaAssegnatiString) {
		this.dataScadenzaAssegnatiString = dataScadenzaAssegnatiString;
	}

	public String getDescrizioneProfiloRoot() {
		return descrizioneProfiloRoot;
	}

	public void setDescrizioneProfiloRoot(String descrizioneProfiloRoot) {
		this.descrizioneProfiloRoot = descrizioneProfiloRoot;
	}

	public String getOrigineAbilitazione() {
		return origineAbilitazione;
	}

	public void setOrigineAbilitazione(String origineAbilitazione) {
		this.origineAbilitazione = origineAbilitazione;
	}

	public String getCdrUffValiditaProfVisibile() {
		return cdrUffValiditaProfVisibile;
	}

	public void setCdrUffValiditaProfVisibile(String cdrUffValiditaProfVisibile) {
		this.cdrUffValiditaProfVisibile = cdrUffValiditaProfVisibile;
	}

	public String getCdrUffValiditaProf() {
		return cdrUffValiditaProf;
	}

	public void setCdrUffValiditaProf(String cdrUffValiditaProf) {
		this.cdrUffValiditaProf = cdrUffValiditaProf;
	}

	public String getDescrizioneCDR() {
		return descrizioneCDR;
	}

	public void setDescrizioneCDR(String descrizioneCDR) {
		this.descrizioneCDR = descrizioneCDR;
	}

	public String getDescrizioneFinale() {
		return descrizioneFinale;
	}

	public void setDescrizioneFinale(String descrizioneFinale) {
		this.descrizioneFinale = descrizioneFinale;
	}

	public String getAssegnazione() {
		return assegnazione;
	}

	public void setAssegnazione(String assegnazione) {
		this.assegnazione = assegnazione;
	}

	public String getRaggruppamentoProfili() {
		return raggruppamentoProfili;
	}

	public void setRaggruppamentoProfili(String raggruppamentoProfili) {
		this.raggruppamentoProfili = raggruppamentoProfili;
	}

	public List<ProfiloBean> getListaProfiloMultiUffCDR() {
		return listaProfiloMultiUffCDR;
	}
	public void addToListaProfiloMultiUffCDR(ProfiloBean profiloMultiUffCDR) {
		this.listaProfiloMultiUffCDR.add(profiloMultiUffCDR);
	}
	
	public void setListaProfiloMultiUffCDR(List<ProfiloBean> listaProfiloMultiUffCDR) {
		this.listaProfiloMultiUffCDR = listaProfiloMultiUffCDR;
	}

	public Date getScadenza() {
		return scadenza;
	}

	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
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

	public boolean isIterVerified() {
		return iterVerified;
	}

	public void setIterVerified(boolean iterVerified) {
		this.iterVerified = iterVerified;
	}

	
	public void setTipoAbilitazioneString(String tipoAbilitazioneString) {
		this.tipoAbilitazioneString = tipoAbilitazioneString;
	}

	public String getMotivoScarto() {
		String motivoDesc = "";
		switch (motivoScarto) {
		case 1:
			motivoDesc = "l'abilitazione risulta essere extra-ufficio, ma non a associata ad alcun iter autorizzativo.";
			break;
		case 2:
			motivoDesc = "l'abilitazione risulta essere associata all'iter autorizzativo <i>" + this.getIterDesc() + "</i> (id = "
					+ this.getIdIter()
					+ ") , ma il CdR della richiesta non fa parte della tipologia ufficio del Richiedente definita per l'iter.";
			break;
		default:
			motivoDesc = "";
			break;
		}
		return motivoDesc;
	}

	public void setMotivoScarto(int motivoScarto) {
		this.motivoScarto = motivoScarto;
	}

	public String getIterDesc() {
		return iterDesc;
	}

	public void setIterDesc(String iterDesc) {
		this.iterDesc = iterDesc;
	}

	public Integer getIdAuditInizio() {
		return idAuditInizio;
	}

	public void setIdAuditInizio(Integer idAuditInizio) {
		this.idAuditInizio = idAuditInizio;
	}

	public Integer getIdAuditFine() {
		return idAuditFine;
	}

	public void setIdAuditFine(Integer idAuditFine) {
		this.idAuditFine = idAuditFine;
	}

	public String getCfInizio() {
		return cfInizio;
	}

	public void setCfInizio(String cfInizio) {
		this.cfInizio = cfInizio;
	}

	public String getCfFine() {
		return cfFine;
	}

	public void setCfFine(String cfFine) {
		this.cfFine = cfFine;
	}

	public boolean isAggiornamento() {
		return aggiornamento;
	}

	public void setAggiornamento(boolean aggiornamento) {
		this.aggiornamento = aggiornamento;
	}
	
	public boolean isMultiUfficio() {
		if(multiUfficio != null && multiUfficio.equalsIgnoreCase("SI"))
			return true;
		else
			return false;
	}
	public void setMultiUfficio(String multiUfficio) {
		this.multiUfficio = multiUfficio;
	}

	public String getTipoUfficioSelezionato() {
		return tipoUfficioSelezionato;
	}

	public void setTipoUfficioSelezionato(String tipoUfficioSelezionato) {
		this.tipoUfficioSelezionato = tipoUfficioSelezionato;
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

	public String getDataFineVal() {
		return dataFineVal;
	}

	public void setDataFineVal(String dataFineVal) {
		this.dataFineVal = dataFineVal;
	}

	public String getDataInizioVal() {
		return dataInizioVal;
	}

	public void setDataInizioVal(String dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}

	
}
