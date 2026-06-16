package it.finanze.siga.util.tree;

import java.util.ArrayList;
import java.util.List;

import it.finanze.siga.bean.AbilitazioneBean;
import it.finanze.siga.bean.ProfiloBean;
import it.finanze.siga.bean.StrutturaUfficioCDRBean;
import it.finanze.siga.util.Constants;
import it.finanze.siga.util.Logg;

public class Tree {
	
	/**
	 * Caso carica da template
	 * @param elenco
	 * @return lista profili tree
	 */
	public static List<ProfiloTreeBean> createTreeFromList(List<ProfiloBean> elenco){
		return createTreeFromList(elenco, Constants.profilazione_standard);
	}
	
	public static List<ProfiloTreeBean> createAbilitazioniTreeFromList(List<AbilitazioneBean> elenco){
		return createAbilitazioniTreeFromList(elenco, Constants.profilazione_standard);
	}
	
	
	/**
	 * Non usato
	 */
	public static List<StrutturaUfficioCDRTreeBean> createTreeFromList2(List<StrutturaUfficioCDRBean> elenco){
		return createTreeFromList2(elenco, Constants.profilazione_standard);
	}
	
	
	public static List<ProfiloTreeBean> createAbilitazioniTreeFromList(List<AbilitazioneBean> elenco, int profilazione){
		List<ProfiloTreeBean> tree = new ArrayList<ProfiloTreeBean>();
		
		
		String currRoleGroup="", tempRoleGroup=null, lastAddedRoleGroup=null;
		ProfiloTreeBean rg=null;
		// lista profili del rg
		List<ProfiloTreeBean> rgProfiloList = null;
		
		// almeno una aggiunta di rg al tree
		boolean oneAdd = false;
		
		/* se lista mono-profilo */
		if(elenco.size()==1){
			AbilitazioneBean abilitazioneBean = elenco.get(0);
			rgProfiloList = new ArrayList<ProfiloTreeBean>();
			rg = createNewRoleGroup(abilitazioneBean, profilazione);
			addProfileBeanToRoleGroup(abilitazioneBean, rgProfiloList, profilazione);
			// impacchetto il prec rg
			rg.setChildren(rgProfiloList);
			tree.add(rg);
		}
		/* se lista con almeno due profili */
		else{
			for (AbilitazioneBean abBean : elenco) {
				
				
				// curr rg
				tempRoleGroup = abBean.getCodiceRoleGroup();
			
				// se rg uguale al curr add profile
				if(tempRoleGroup!= null && tempRoleGroup.equalsIgnoreCase(currRoleGroup)){
					addProfileBeanToRoleGroup(abBean, rgProfiloList, profilazione);
				}
				// altrimenti
				else{
					// se rg non inizializzato lo inizializzo
					if(rg==null){
						// nuovo role group
						rgProfiloList = new ArrayList<ProfiloTreeBean>();
						rg = createNewRoleGroup(abBean, profilazione);
						// setto codice ultimo rg aggiunto all'albero
						lastAddedRoleGroup = tempRoleGroup;
						
						// add prof to existing rg
						addProfileBeanToRoleGroup(abBean, rgProfiloList, profilazione);
						
					}
					// altrimenti finalizzo il precedente e inizializzo il nuovo rg e add prof
					else{
						// se stesso rg aggiungo item
						if(tempRoleGroup!= null && tempRoleGroup.equals(currRoleGroup)){
							// add prof to existing rg
							addProfileBeanToRoleGroup(abBean, rgProfiloList, profilazione);
						}
						// altrimenti impacchetto e creo un nuovo rg
						else{
//							// ordino lista profili
//							rgProfiloList = Utils.ordinaElencoProfiliPerProfiloDesc(rgProfiloList);
							// impacchetto il prec rg
							rg.setChildren(rgProfiloList);
							// aggiorno l'albero
							tree.add(rg);
							// setto codice ultimo rg aggiunto all'albero
							lastAddedRoleGroup = currRoleGroup;
							
							// almeno una aggiunta di rg al tree
							oneAdd = true;
							
							// nuovo role group
							rgProfiloList = new ArrayList<ProfiloTreeBean>();
							rg = createNewRoleGroup(abBean, profilazione);
							
							// add prof to existing rg
							addProfileBeanToRoleGroup(abBean, rgProfiloList, profilazione);
						}
					}
				}
				// setto il rg curr x confronto sopra
				currRoleGroup = abBean.getCodiceRoleGroup();
			}
			
			// se l'ultimo rg aggiunto è diverso da quello rimasto aggiungo ultimo role group
			if (lastAddedRoleGroup != null && !lastAddedRoleGroup.equals("")
					&& !lastAddedRoleGroup.equals(tempRoleGroup)) {
//				// ordino lista profili
//				rgProfiloList = Utils.ordinaElencoProfiliPerProfiloDesc(rgProfiloList);
				// impacchetto il prec rg
				rg.setChildren(rgProfiloList);
				// aggiorno l'albero
				tree.add(rg);
			}
			
			// x quando ho più profili dello stesso codice role group  
			if(lastAddedRoleGroup!=null && !lastAddedRoleGroup.equals("") && !oneAdd){
//				// ordino lista profili
//				rgProfiloList = Utils.ordinaElencoProfiliPerProfiloDesc(rgProfiloList);
				// impacchetto il prec rg
				rg.setChildren(rgProfiloList);
				// aggiorno l'albero
				tree.add(rg);
			}
			
		}
		
		return tree;
	}
	
	/** 
	 * Trasformo elenco profili in elenco profili tree (che e' sempre una lista ma dara' vita all'albero)
	 * 
	 * @param List<ProfiloBean> list
	 * @param profilazione: per distinguere il tipo di albero da creare
	 * @return List<ProfiloTreeBean> tree
	 */
	public static List<ProfiloTreeBean> createTreeFromList(List<ProfiloBean> elenco, int profilazione){
		List<ProfiloTreeBean> tree = new ArrayList<ProfiloTreeBean>();
		
		
		String currRoleGroup="", tempRoleGroup=null, lastAddedRoleGroup=null;
		ProfiloTreeBean rg=null;
		// lista profili del rg
		List<ProfiloTreeBean> rgProfiloList = null;
		
		// almeno una aggiunta di rg al tree
		boolean oneAdd = false;
		
		/* se lista mono-profilo */
		if(elenco.size()==1){
			ProfiloBean profiloBean = elenco.get(0);
			rgProfiloList = new ArrayList<ProfiloTreeBean>();
			rg = createNewRoleGroup(profiloBean, profilazione);
			addProfileBeanToRoleGroup(profiloBean, rgProfiloList, profilazione);
			// impacchetto il prec rg
			rg.setChildren(rgProfiloList);
			tree.add(rg);
		}
		/* se lista con almeno due profili */
		else{
			for (ProfiloBean profiloBean : elenco) {
				
				
				// curr rg
				tempRoleGroup = profiloBean.getCodiceRoleGroup();
			
				// se rg uguale al curr add profile
				if(tempRoleGroup!= null && tempRoleGroup.equalsIgnoreCase(currRoleGroup)){
					addProfileBeanToRoleGroup(profiloBean, rgProfiloList, profilazione);
				}
				// altrimenti
				else{
					// se rg non inizializzato lo inizializzo
					if(rg==null){
						// nuovo role group
						rgProfiloList = new ArrayList<ProfiloTreeBean>();
						rg = createNewRoleGroup(profiloBean, profilazione);
						// setto codice ultimo rg aggiunto all'albero
						lastAddedRoleGroup = tempRoleGroup;
						
						// add prof to existing rg
						addProfileBeanToRoleGroup(profiloBean, rgProfiloList, profilazione);
						
					}
					// altrimenti finalizzo il precedente e inizializzo il nuovo rg e add prof
					else{
						// se stesso rg aggiungo item
						if(tempRoleGroup!= null && tempRoleGroup.equals(currRoleGroup)){
							// add prof to existing rg
							addProfileBeanToRoleGroup(profiloBean, rgProfiloList, profilazione);
						}
						// altrimenti impacchetto e creo un nuovo rg
						else{
//							// ordino lista profili
//							rgProfiloList = Utils.ordinaElencoProfiliPerProfiloDesc(rgProfiloList);
							// impacchetto il prec rg
							rg.setChildren(rgProfiloList);
							// aggiorno l'albero
							tree.add(rg);
							// setto codice ultimo rg aggiunto all'albero
							lastAddedRoleGroup = currRoleGroup;
							
							// almeno una aggiunta di rg al tree
							oneAdd = true;
							
							// nuovo role group
							rgProfiloList = new ArrayList<ProfiloTreeBean>();
							rg = createNewRoleGroup(profiloBean, profilazione);
							
							// add prof to existing rg
							addProfileBeanToRoleGroup(profiloBean, rgProfiloList, profilazione);
						}
					}
				}
				// setto il rg curr x confronto sopra
				currRoleGroup = profiloBean.getCodiceRoleGroup();
			}
			
			// se l'ultimo rg aggiunto è diverso da quello rimasto aggiungo ultimo role group
			if (lastAddedRoleGroup != null && !lastAddedRoleGroup.equals("")
					&& !lastAddedRoleGroup.equals(tempRoleGroup)) {
//				// ordino lista profili
//				rgProfiloList = Utils.ordinaElencoProfiliPerProfiloDesc(rgProfiloList);
				// impacchetto il prec rg
				rg.setChildren(rgProfiloList);
				// aggiorno l'albero
				tree.add(rg);
			}
			
			// x quando ho più profili dello stesso codice role group  
			if(lastAddedRoleGroup!=null && !lastAddedRoleGroup.equals("") && !oneAdd){
//				// ordino lista profili
//				rgProfiloList = Utils.ordinaElencoProfiliPerProfiloDesc(rgProfiloList);
				// impacchetto il prec rg
				rg.setChildren(rgProfiloList);
				// aggiorno l'albero
				tree.add(rg);
			}
			
		}
		
		return tree;
	}
	
	// Albero a due livelli fissi
	// Struttura albero: solo il primo nodo può avere anche uffici e cdr sotto. 
	//
	// struttura II liv - struttura 
	//							- ufficio 
	//									- cdr
	//					- ufficio 
	//							- cdr
	//					- cdr
	// struttura II liv - struttura 
	//							- ufficio 
	//									- cdr
	// struttura II liv - struttura 
	//							- ufficio 
	//									- cdr
	/**
	 * Non usato
	 */
	public static List<StrutturaUfficioCDRTreeBean> createTreeFromList2(List<StrutturaUfficioCDRBean> elenco, int profilazione){
		List<StrutturaUfficioCDRTreeBean> tree = new ArrayList<StrutturaUfficioCDRTreeBean>();
		
		String curr_Nodo_I_Liv="", temp_Nodo_I_Liv=null, lastAdded_Nodo_I_Liv=null;
		String curr_Nodo_II_Liv="", temp_Nodo_II_Liv=null, lastAdded_Nodo_II_Liv=null;
		String curr_Nodo_III_Liv="", temp_Nodo_III_Liv=null, lastAdded_Nodo_III_Liv=null;
		StrutturaUfficioCDRTreeBean struttura_II_Liv = null;
		List<StrutturaUfficioCDRTreeBean> struttura_II_Liv_List = null;
		
		// nodo II liv
		StrutturaUfficioCDRTreeBean struttura = null;
		StrutturaUfficioCDRTreeBean ufficio = null;
		StrutturaUfficioCDRTreeBean cdr = null;
		
		// almeno una aggiunta di rg al tree
		boolean oneAdd = false;
		
		/* se lista mono-elemento */ ///////// NON USATO
		if(elenco.size()==1){
			
		}
		
		/* se lista con almeno due elemento */
		else{
			for (StrutturaUfficioCDRBean profiloBean : elenco) {
				// tempElement
				temp_Nodo_I_Liv = profiloBean.getCodiceStruttura_II_Liv();
				temp_Nodo_II_Liv = profiloBean.getCodiceStruttura();
				temp_Nodo_III_Liv = profiloBean.getCodiceUfficio();
				
				// se tempElement uguale al currElement add object_I_LIV
				if(temp_Nodo_I_Liv.equalsIgnoreCase(curr_Nodo_I_Liv)){
					addStrutturaToStrutturaIILiv(profiloBean, struttura_II_Liv_List);
				}
				else{
					// se object_I_LIV non inizializzato lo inizializzo
					if(struttura_II_Liv==null){
						// nuovo role group
						struttura_II_Liv_List = new ArrayList<StrutturaUfficioCDRTreeBean>();
						struttura_II_Liv = createNewStruttura_II_Liv(profiloBean.getCDRDesc(), profilazione);
						
						// setto codice ultimo rg aggiunto all'albero
						lastAdded_Nodo_I_Liv = temp_Nodo_I_Liv;
						
					}
					// altrimenti finalizzo il precedente e inizializzo il nuovo rg e add prof
					else{
						// se stesso rg aggiungo item
						if(temp_Nodo_I_Liv.equals(curr_Nodo_I_Liv)){
							// add II liv al I liv
							addStrutturaToStrutturaIILiv(profiloBean, struttura_II_Liv_List);
						}
						// altrimenti impacchetto e creo un nuovo rg
						else{
							// impacchetto il prec rg
							struttura_II_Liv.setChildren(struttura_II_Liv_List);
							// aggiorno l'albero
							tree.add(struttura_II_Liv);
							// setto codice ultimo rg aggiunto all'albero
							lastAdded_Nodo_I_Liv = curr_Nodo_I_Liv;
							
							// almeno una aggiunta di rg al tree
							oneAdd = true;
							
							// nuovo role group
							struttura_II_Liv_List = new ArrayList<StrutturaUfficioCDRTreeBean>();
							struttura_II_Liv = createNewStruttura_II_Liv(profiloBean.getCDRDesc(), profilazione);
							
							// add prof to existing rg
							addStrutturaToStrutturaIILiv(profiloBean, struttura_II_Liv_List);
						}
					}
				}
				// setto il rg curr x confronto sopra
				curr_Nodo_I_Liv = profiloBean.getCodiceStruttura_II_Liv();
				// curr II liv
				curr_Nodo_II_Liv =profiloBean.getCodiceStruttura();
				// curr III liv
				curr_Nodo_III_Liv = profiloBean.getCodiceUfficio();
			}
			
			// se l'ultimo rg aggiunto è diverso da quello rimasto aggiungo ultimo role group
			if(lastAdded_Nodo_I_Liv!=null && !lastAdded_Nodo_I_Liv.equals("") 
					&& !lastAdded_Nodo_I_Liv.equals(temp_Nodo_I_Liv)){
				// impacchetto il prec rg
				struttura_II_Liv.setChildren(struttura_II_Liv_List);
				// aggiorno l'albero
				tree.add(struttura_II_Liv);
			}
			
			// x quando ho più profili dello stesso codice role group  
			if(lastAdded_Nodo_I_Liv!=null && !lastAdded_Nodo_I_Liv.equals("") && !oneAdd){
				// impacchetto il prec rg
				struttura_II_Liv.setChildren(struttura_II_Liv_List);
				// aggiorno l'albero
				tree.add(struttura_II_Liv);
			}
			
		}
		
		
		return tree;
	}
	
	/**
	 * Aggiunge un profilo al role group attuale
	 * 
	 * @param fromBean il ProfiloBean attuale
	 * @param toList la lista di profili del role group
	 * @param profilazione il tipo di albero che si vuole creare
	 */
	private static void addProfileBeanToRoleGroup(ProfiloBean fromBean, List<ProfiloTreeBean> toList,
				int profilazione){
		ProfiloTreeBean bean = new ProfiloTreeBean();
		bean.setSelected(fromBean.isAttivo());
		bean.setAttivo(fromBean.getAttivo());
		bean.setAssegnato(fromBean.getAssegnato());
		bean.setFlagAggiunto(fromBean.getFlagAggiunto());
		bean.setFlagRimosso(fromBean.getFlagRimosso());
		bean.setUnselectable(fromBean.isUnselectable());
		
		bean.setStartSelected(fromBean.isAttivo());
		bean.setCodiceRoleGroup(fromBean.getCodiceRoleGroup());
		bean.setCodiceProfilo(fromBean.getCodiceProfilo());
		// altri campi
		bean.setRoleGroupDesc(fromBean.getRoleGroupDesc());
		bean.setProfiloDesc(fromBean.getProfiloDesc());
		bean.setApplicazioneDesc(fromBean.getApplicazioneDesc());
		bean.setDataScadenza(fromBean.getDataScadenza());
		bean.setDataScadenzaVisibilita(fromBean.getDataScadenzaVisibilita());
		bean.setTipoAbilitazione(fromBean.getTipoAbilitazione());
		bean.setCodiceApplicazione(fromBean.getCodiceApplicazione());
		bean.setOp(fromBean.getOp());
		bean.setInfoNote(fromBean.getInfoNote());
		//bean.setSelected(fromBean.isSelected());
		
		bean.setHideCheckbox(fromBean.isHideCheckbox());
		bean.setExpanded(fromBean.isExpanded());
		bean.setExplicit_entitlement(fromBean.getExplicit_entitlement());
		bean.setOrigineAbilitazione(fromBean.getOrigineAbilitazione());	
		bean.setDisponibileTitolareRuolo(fromBean.getDisponibileTitolareRuolo());
		
		bean.setAppDescCat(fromBean.getAppDescCat());
		
		switch (profilazione) {
		case Constants.profilazione_carica_da_operatore:
			bean.setHideCheckbox(true);
			break;
		case Constants.profilazione_carica_da_operatore_con_icone_rimossi_e_aggiunti:
		case Constants.profilazione_carica_da_template_con_icone_rimossi_e_aggiunti:{
			bean.setIcon(impostaIcona(fromBean));
			bean.setHideCheckbox(true);
			break;
		}
		default:
			break;
		}
		bean.setTitle(fromBean.getProfileNodeTitle());
		toList.add(bean);
	}
	
	private static void addProfileBeanToRoleGroup(AbilitazioneBean fromBean, List<ProfiloTreeBean> toList,
			int profilazione){
	ProfiloTreeBean bean = new ProfiloTreeBean();
	bean.setSelected(false);
	bean.setAttivo("SI");
	//bean.setAssegnato("NO");
	bean.setFlagAggiunto("NO");
	bean.setFlagRimosso("NO");
	bean.setUnselectable(false);
	
	bean.setStartSelected(false);
	bean.setCodiceRoleGroup(fromBean.getCodiceRoleGroup());
	bean.setCodiceProfilo(fromBean.getCodiceProfilo());
	// altri campi
	bean.setRoleGroupDesc(fromBean.getRoleGroupDesc());
	bean.setProfiloDesc(fromBean.getProfiloDesc());
	bean.setApplicazioneDesc(fromBean.getApplicazioneDesc());
	//bean.setDataScadenza(fromBean.getDataScadenza());
	//bean.setDataScadenzaVisibilita(fromBean.getDataScadenzaVisibilita());
	//bean.setTipoAbilitazione(fromBean.getTipoAbilitazione());
	bean.setCodiceApplicazione(fromBean.getCodiceApplicazione());
	//bean.setOp(fromBean.getOp());
	//bean.setInfoNote(fromBean.getInfoNote());
	//bean.setSelected(fromBean.isSelected());
	
	//bean.setHideCheckbox(fromBean.isHideCheckbox());
	//bean.setExpanded(fromBean.isExpanded());
	//bean.setExplicit_entitlement(fromBean.getExplicit_entitlement());
	//bean.setOrigineAbilitazione(fromBean.getOrigineAbilitazione());	
	//bean.setDisponibileTitolareRuolo(fromBean.getDisponibileTitolareRuolo());
	
	//bean.setAppDescCat(fromBean.getAppDescCat());
	
	switch (profilazione) {
	case Constants.profilazione_carica_da_operatore:
		bean.setHideCheckbox(true);
		break;
	case Constants.profilazione_carica_da_operatore_con_icone_rimossi_e_aggiunti:
	case Constants.profilazione_carica_da_template_con_icone_rimossi_e_aggiunti:{
		//bean.setIcon(impostaIcona(fromBean));
		bean.setHideCheckbox(true);
		break;
	}
	default:
		break;
	}
	bean.setTitle(fromBean.getProfiloDesc());
	toList.add(bean);
}
	
	/**
	 * imposta icona a seconda che il profilo sia da aggiungere o rimuovere
	 * @param fromBean
	 * @return path dell'icona da inserire
	 */
	public static String impostaIcona(ProfiloBean fromBean){
		String icon = "";
		if(fromBean.getOp().equalsIgnoreCase(ProfiloBean.toRemoveOp)){
			icon = (Constants.ICON_CROSS_RED_PATH);
		}else if(fromBean.getOp().equalsIgnoreCase(ProfiloBean.toAddOp)){
			icon = (Constants.ICON_CHECK_GREEN_PATH);
		}else{
			Logg.getLogger().info(Constants.NON_DEVO_MAI_STARE_QUI);
		}
		return icon;
		
	}
	

	
	/**
	 * Crea un nuovo role group 
	 * 
	 * @param fromBean il ProfiloBean attuale dell'elenco di profili
	 * @param profilazione il tipo di role group dipende dal tipo di albero che si vuole creare
	 * @return
	 */
	private static ProfiloTreeBean createNewRoleGroup(ProfiloBean fromBean, int profilazione){
		ProfiloTreeBean rg = new ProfiloTreeBean();
		/* nodo fittizio per profili non associati a nessun role group */
		if(fromBean.getRoleGroupDesc()==null || fromBean.getRoleGroupDesc().equals("")){
			rg.setTitle(Constants.RG_TITLE_FOR_NO_RG_PROFILES);
		}else{
			rg.setTitle(fromBean.getRoleGroupDesc());
		}
		rg.setFlagAggiunto(fromBean.getFlagAggiunto());
		rg.setFlagRimosso(fromBean.getFlagRimosso());
		rg.setAssegnato(fromBean.getAssegnato());
		rg.setDisponibileTitolareRuolo(fromBean.getDisponibileTitolareRuolo());
		rg.setExplicit_entitlement(fromBean.getExplicit_entitlement());
		rg.setHideCheckbox(false);
		switch (profilazione) {
		case Constants.profilazione_carica_da_operatore:
			rg.setExpanded(true);
			rg.setUnselectable(true);
			rg.setHideCheckbox(true);
			break;
		case Constants.profilazione_carica_da_template:
		case Constants.profilazione_solo_visualizzazione:
			rg.setExpanded(true);
			rg.setUnselectable(true);
			break;
			
		case Constants.profilazione_carica_da_operatore_con_icone_rimossi_e_aggiunti:
		case Constants.profilazione_carica_da_template_con_icone_rimossi_e_aggiunti:{
			rg.setHideCheckbox(true);
			rg.setExpanded(true);
			rg.setUnselectable(true);
			rg.setIcon(impostaIcona(fromBean));
			break;
		}
		
		case Constants.profilazione_standard:
			rg.setExpanded(false);
			break;
			
		default:
			break;
		}
		return rg;
	}
	
	private static ProfiloTreeBean createNewRoleGroup(AbilitazioneBean fromBean, int profilazione){
		ProfiloTreeBean rg = new ProfiloTreeBean();
		/* nodo fittizio per profili non associati a nessun role group */
		if(fromBean.getRoleGroupDesc()==null || fromBean.getRoleGroupDesc().equals("")){
			rg.setTitle(Constants.RG_TITLE_FOR_NO_RG_PROFILES);
		}else{
			rg.setTitle(fromBean.getRoleGroupDesc());
		}
		/*rg.setFlagAggiunto(fromBean.getFlagAggiunto());
		rg.setFlagRimosso(fromBean.getFlagRimosso());
		rg.setAssegnato(fromBean.getAssegnato());
		rg.setDisponibileTitolareRuolo(fromBean.getDisponibileTitolareRuolo());
		rg.setExplicit_entitlement(fromBean.getExplicit_entitlement());*/
		rg.setHideCheckbox(false);
		switch (profilazione) {
		case Constants.profilazione_carica_da_operatore:
			rg.setExpanded(true);
			rg.setUnselectable(true);
			rg.setHideCheckbox(true);
			break;
		case Constants.profilazione_carica_da_template:
		case Constants.profilazione_solo_visualizzazione:
			rg.setExpanded(true);
			rg.setUnselectable(true);
			break;
			
		case Constants.profilazione_carica_da_operatore_con_icone_rimossi_e_aggiunti:
		case Constants.profilazione_carica_da_template_con_icone_rimossi_e_aggiunti:{
			rg.setHideCheckbox(true);
			rg.setExpanded(true);
			rg.setUnselectable(true);
			//rg.setIcon(impostaIcona(fromBean));
			break;
		}
		
		case Constants.profilazione_standard:
			rg.setExpanded(false);
			break;
			
		default:
			break;
		}
		return rg;
	}
	
	/**
	 * elenco profili dal tree bean di ProfiloTreeBean; x verifica iter
	 * @param tree
	 * @return
	 */
	public static List<ProfiloTreeBean> convertTreeToList(List<ProfiloTreeBean> tree){
		List<ProfiloTreeBean> risultato = new ArrayList<ProfiloTreeBean>();
		for (ProfiloTreeBean profiloTreeBean : tree) {
			try {
				List<ProfiloTreeBean> list = profiloTreeBean.getChildren();
				if(list!=null){
					for (ProfiloTreeBean profiloTreeBean2 : list) {
						risultato.add(profiloTreeBean2);
					}
				}
			} catch (Exception e) {
				Logg.getLogger().info("IN convertTreeToList, errore ");
				Logg.getLogger().error("IN convertTreeToList, errore: " + e.getMessage(),e);
			}
		}
		return risultato;
	}
	
	/**
	 * Crea un nodo struttura di II Livello
	 * 
	 * @param desc
	 * @param profilazione
	 * @return
	 */
	private static StrutturaUfficioCDRTreeBean createNewStruttura_II_Liv(String desc, int profilazione){
		StrutturaUfficioCDRTreeBean rg = new StrutturaUfficioCDRTreeBean();
		rg.setTitle(desc);
		rg.setHideCheckbox(false);
		switch (profilazione) {
		case Constants.profilazione_carica_da_operatore:
		case Constants.profilazione_carica_da_template:
		case Constants.profilazione_solo_visualizzazione:
			rg.setExpanded(true);
			rg.setUnselectable(true);
			break;
			
		case Constants.profilazione_standard:
			rg.setExpanded(false);
			break;
			
		default:
			break;
		}
		return rg;
	}
	
	private static void addStrutturaToStrutturaIILiv(StrutturaUfficioCDRBean fromBean, List<StrutturaUfficioCDRTreeBean> toList){
		StrutturaUfficioCDRTreeBean bean = new StrutturaUfficioCDRTreeBean();
		
		// codici
		bean.setCodiceStruttura(fromBean.getCodiceStruttura());
		bean.setCodiceUfficio(fromBean.getCodiceUfficio());
		bean.setCodiceCDR(fromBean.getCodiceCDR());
		// descrizioni
		// tutte le descrizioni venmgono dal campo CDR perché tutti sono CDR
//		bean.setStrutturaDesc(fromBean.getStrutturaDesc());
//		bean.setUfficioDesc(fromBean.getUfficioDesc());
		bean.setStrutturaDesc(fromBean.getCDRDesc());
		bean.setUfficioDesc(fromBean.getCDRDesc());
		bean.setCDRDesc(fromBean.getCDRDesc());
		// tree node
		bean.setTitle(fromBean.getCDRDesc());
		bean.setStartSelected(false);
		bean.setSelected(false);
		bean.setUnselectable(false);
		bean.setHideCheckbox(false);
		bean.setExpanded(true);
		
		toList.add(bean);
	}

}
