package it.finanze.siga.util.tree;

import it.finanze.siga.bean.StrutturaUfficioCDRBean;
import it.finanze.siga.util.Constants;
import it.finanze.siga.util.Logg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TreeEngineSingleton {
	
	private static TreeEngineSingleton instance = new TreeEngineSingleton();
	
	private TreeEngineSingleton(){};
	
	public static TreeEngineSingleton getInstance(){
		return instance;
	}
	
	public static StrutturaUfficioCDRTreeBean convert(StrutturaUfficioCDRBean fromBean){
		// codici
		StrutturaUfficioCDRTreeBean bean = new StrutturaUfficioCDRTreeBean();
		bean.setCodiceStruttura(fromBean.getCodiceStruttura());
		bean.setCodiceUfficio(fromBean.getCodiceUfficio());
		bean.setCodiceCDR(fromBean.getCodiceCDR());
		bean.setOrdinamento(fromBean.getOrdinamento());
		// descrizioni
		// tutte le descrizioni venmgono dal campo CDR perché tutti sono CDR
		bean.setStrutturaDesc(fromBean.getCDRDesc());
		bean.setUfficioDesc(fromBean.getCDRDesc());
		bean.setCDRDesc(fromBean.getCDRDesc());
		// tree node
		bean.setTitle(fromBean.getCDRDesc());
		bean.setStartSelected(false);
		bean.setSelected(false);
		bean.setUnselectable(false);
		bean.setHideCheckbox(false);
		bean.setExpanded(false);
		return bean;
	}
	
	////// NEW ALGO 5 //////
	public static List<StrutturaUfficioCDRTreeBean> createTreeFromList(List<StrutturaUfficioCDRBean> elenco, 
			int NUM_LIVELLI, TreeManager treeManager){
		return createTreeFromList(elenco, Constants.profilazione_standard, NUM_LIVELLI, treeManager);
	}
	
	public static List<StrutturaUfficioCDRTreeBean> createTreeFromList_OLD(List<StrutturaUfficioCDRBean> elenco, 
			int profilazione, int NUM_LIVELLI, TreeManager treeManager){
		List<StrutturaUfficioCDRTreeBean> tree = new ArrayList<StrutturaUfficioCDRTreeBean>();
		
		String[] lastN = new String[NUM_LIVELLI];
		String[] currN = new String[NUM_LIVELLI];  
		Arrays.fill(lastN, "");
		Arrays.fill(currN, "");
		
		treeManager.initListNN();
		
		for (StrutturaUfficioCDRBean bean : elenco) {
			gestisciElemento(bean, 1, currN, lastN, NUM_LIVELLI, tree, treeManager);
		}
		
		checkAddRamo(NUM_LIVELLI, lastN, NUM_LIVELLI, tree, treeManager);
		
		return tree;
	}
	
	public static List<StrutturaUfficioCDRTreeBean> createTreeFromList(List<StrutturaUfficioCDRBean> elenco, 
			int profilazione, int NUM_LIVELLI, TreeManager treeManager){
		List<StrutturaUfficioCDRTreeBean> tree = new LinkedList<StrutturaUfficioCDRTreeBean>();
		
		int livelloPrecedente = 0;
		
		for (StrutturaUfficioCDRBean bean : elenco) {
			livelloPrecedente = gestisciTree(bean, tree, livelloPrecedente);
		}
		
		/* ordinamento */
		tree = treeManager.orderTree(tree);
		
		return tree;
	}
	
	private static int gestisciTree_OLD(StrutturaUfficioCDRBean bean, List<StrutturaUfficioCDRTreeBean> tree, int livelloPrecedente){
		LinkedList<StrutturaUfficioCDRTreeBean> linkedTree = (LinkedList<StrutturaUfficioCDRTreeBean>) tree;
		String confronto, confronto2, confronto3;
		StrutturaUfficioCDRTreeBean newBean;
		confronto = bean.getConfronto();
		confronto2 = bean.getConfronto2();
		confronto3 = bean.getConfronto3();
		newBean = convert(bean);
		// caso nuovo nodo I liv
		if(confronto.compareTo("1")==0){
			// aggiungi nodo alla lista
			livelloPrecedente = 1;
			linkedTree.add(newBean);
		}
		// caso nuova struttura
		else if(confronto2.compareTo("1")==0 && confronto3.compareTo("1")==0){
			// aggiungi elemento all'ultimo nodo del livello precedente
			livelloPrecedente = 2;
			
			// add to liv 1
			// caso se albero vuoto
			if(linkedTree.size() == 0){
				linkedTree.add(newBean);
			}
			else if(linkedTree.getLast().getChildren()==null){
				linkedTree.getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
				linkedTree.getLast().getChildren().add(newBean);
			}else{
				linkedTree.getLast().getChildren().add(newBean);
			}
			
		}
		// nuovo ufficio
		else if(confronto2.compareTo("0")==0 && confronto3.compareTo("1")==0){
			livelloPrecedente = 3;
			// caso se albero vuoto
			if(linkedTree.size() == 0){
				linkedTree.add(newBean);
			}
			else if((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()==null){
				linkedTree.getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
				linkedTree.getLast().getChildren().add(newBean);
			}
			else if(((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()==null){
				((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
				((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren().add(newBean);
			}else{
				((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren().add(newBean);
			}
		}else{
			// aggiungi cdr a elemento precedente
			if(livelloPrecedente==1){
				// add to liv 1
				if(linkedTree.getLast().getChildren()==null){
					linkedTree.getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
					linkedTree.getLast().getChildren().add(newBean);
				}else{
					linkedTree.getLast().getChildren().add(newBean);
				}
			}else if(livelloPrecedente==2){
				// add a liv 2
				if(((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()==null){
					((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
					((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren().add(newBean);
				}else{
					((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren().add(newBean);
				}
			}else if(livelloPrecedente==3){
				// add to liv 3
				if(((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().getChildren() == null){
					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().getChildren().add(newBean);
				}else{
					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().getChildren().add(newBean);
				}
			}
			else{
				Logg.getLogger().info("caso particolare in cui inizio l'albero con un cdr ");
				// caso se albero vuoto
				if(linkedTree.size() == 0){
					linkedTree.add(newBean);
				}
			}
			// uguale al penultimo caso (livello 3)
//			else {
//				// add to liv 3
//				if(((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().getChildren() == null){
//					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
//					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().getChildren().add(newBean);
//				}else{
//					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().getChildren().add(newBean);
//				}
//			}
				
		}
		
		return livelloPrecedente;
		
	}
	
	/**
	 * Crea la struttura dell'albero. Per ogni elemento va a vedere dove aggiungerlo nella gerarchia dell'albero.
	 * 
	 * @param bean il nodo.
	 * @param tree l'albero.
	 * @param livelloAttuale: il livello attuale assegnato.
	 * @return lastLivello: l'ultimo livello assegnato.
	 */
	private static int gestisciTree(StrutturaUfficioCDRBean bean, List<StrutturaUfficioCDRTreeBean> tree, int livelloAttuale){
		LinkedList<StrutturaUfficioCDRTreeBean> linkedTree = (LinkedList<StrutturaUfficioCDRTreeBean>) tree;
		String confronto, confronto2, confronto3;
		StrutturaUfficioCDRTreeBean newBean;
		int lastLivello = livelloAttuale;
		boolean newLiv1 = false, newLiv2=false, newLiv3 = false, newLiv4=false;
		confronto = bean.getConfronto();
		confronto2 = bean.getConfronto2();
		confronto3 = bean.getConfronto3();
		if(confronto.compareTo("1")==0){
			newLiv1 = true;
		}else if(confronto.compareTo("0")==0 && confronto2.compareTo("1")==0){
			newLiv2 = true;
		}else if(confronto.compareTo("0")==0 && confronto2.compareTo("0")==0 && confronto3.compareTo("1")==0){
			newLiv3 = true;
		}else{
			newLiv4 = true;
		}
		newBean = convert(bean);
		// caso nuovo nodo I liv - nuova struttura II liv
		if(newLiv1){
			// aggiungi nodo alla lista
			livelloAttuale = 1;
			linkedTree.add(newBean);
			lastLivello = 1;
			// ultimo aggiunto è nodo di tipo 1
		}
		// caso nuovo nodo II liv - nuova struttura
		else if(newLiv2){
			// aggiungi elemento all'ultimo nodo del livello precedente
			livelloAttuale = 2;
			// add to liv 1
			// caso se albero vuoto
			if(linkedTree.size() == 0){
				linkedTree.add(newBean);
			}
			else if(lastLivello==1 || newLiv2){
				// add sotto
				if(linkedTree.getLast().getChildren()==null){
					linkedTree.getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
					linkedTree.getLast().getChildren().add(newBean);
				}else{
					linkedTree.getLast().getChildren().add(newBean);
				}
			}else if(lastLivello==2){
				// add affianco
				if(((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()==null){
					((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
					((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren().add(newBean);
				}else{
					((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren().add(newBean);
				}
			}
			lastLivello = 2;
			
		}
		// caso nuovo nodo III liv - nuova ufficio
		else if(newLiv3){
			livelloAttuale = 3;
			// caso se albero vuoto
			if(linkedTree.size() == 0){
				linkedTree.add(newBean);
				lastLivello = 3;
			}
			else if(lastLivello==2 || newLiv3){
				// add sotto
				if(((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren())==null){
					linkedTree.getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
					((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).add(newBean);
				}else{
					((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).add(newBean);
				}
				lastLivello = 2;
			}else if(lastLivello == 3){
				// add affianco
				if(((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()) == null){
					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren())).getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren())).getLast().getChildren().add(newBean);
				}else{
					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren())).getLast().getChildren().add(newBean);
				}
				lastLivello = 3;
			}
		}
		// caso nuovo nodo IV liv - nuovo CDR
		else if(newLiv4){
			// aggiungi cdr a elemento precedente
			if(livelloAttuale==1){
				// add to liv 1
				if(linkedTree.getLast().getChildren()==null){
					linkedTree.getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
					linkedTree.getLast().getChildren().add(newBean);
				}else{
					linkedTree.getLast().getChildren().add(newBean);
				}
				lastLivello = 2;
			}else if(livelloAttuale==2){
				// add a liv 2
				if((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()==null){
					linkedTree.getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
					linkedTree.getLast().getChildren().add(newBean);
				}else if(((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()==null){
					((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
					((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren().add(newBean);
				}else{
					((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren().add(newBean);
				}
				lastLivello = 2;
			}else if(livelloAttuale==3){
				// add to liv 3
				if(((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().getChildren() == null){
					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().getChildren().add(newBean);
				}else{
					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().getChildren().add(newBean);
				}
				lastLivello = 4;
			}
			else{
				Logg.getLogger().info("caso particolare in cui inizio l'albero con un cdr ");
				// caso se albero vuoto
				if(linkedTree.size() == 0){
					linkedTree.add(newBean);
					lastLivello = 1;
				}
				else 
				// add to liv 3
				if(((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().getChildren() == null){
					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().setChildren(new LinkedList<StrutturaUfficioCDRTreeBean>());
					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().getChildren().add(newBean);
				}else{
					((LinkedList<StrutturaUfficioCDRTreeBean>) ((LinkedList<StrutturaUfficioCDRTreeBean>)linkedTree.getLast().getChildren()).getLast().getChildren()).getLast().getChildren().add(newBean);
				}
				lastLivello = 4;
			}
		} else{
			Logg.getLogger().info("NESSUN LIVELLO X ALBERO");
		}
		
		return lastLivello;
		
	}
	
	/**
	 * Funzione ricorsiva che gestisce ogni record dell'elenco ricevuto dal db. Gestisce significa che a seconda
	 * del tipo di nodo lo aggiunge nel posto giusto nella gerarchia dell'albero da creare, cioè come fratello
	 * o come figlio di un dato nodo.
	 * 
	 * @param bean
	 * @param level
	 * @param currN
	 * @param lastN
	 * @param NUM_LIVELLI
	 * @param tree
	 * @param treeManager
	 */
	private static void gestisciElemento(StrutturaUfficioCDRBean bean, int level, String[] currN, String[] lastN,
			int NUM_LIVELLI, List<StrutturaUfficioCDRTreeBean> tree, TreeManager treeManager){
		int index = level-1;
		currN[index] = getLevelCode(bean, level);
		if(!(currN[index].equals(lastN[index]))){
			if(level==1){
				checkAddRamo(NUM_LIVELLI, lastN, NUM_LIVELLI, tree, treeManager);
			}
			creaNodo(bean, level, treeManager);
			lastN[index] = getLevelCode(bean, level);
		}else{
			lastN[index] = getLevelCode(bean, level);
			if(level < NUM_LIVELLI)
				gestisciElemento(bean, level+1, currN, lastN, NUM_LIVELLI, tree, treeManager);
		}
	}
	
	/**
	 * da eseguire per tutti i record della query.
	 * per ogni blocco di record che va da 1 a 4 liv deve aggiungere quella porzione di ramo all'albero 
	 * 
	 * @param bean
	 * @param level
	 * @param currN
	 * @param lastN
	 * @param NUM_LIVELLI
	 * @param tree
	 * @param treeManager
	 */
	private static void gestisciElemento_NEW_2(StrutturaUfficioCDRBean currBean, StrutturaUfficioCDRBean lastBean, int level, String[] currN, String[] lastN,
			int NUM_LIVELLI, List<StrutturaUfficioCDRTreeBean> tree, TreeManager treeManager){
		// ALGORITMO
		// prendi elemento corrente
		// stabilisci livello
		// confronta con il precedente
		// in base al risultato crea nodo dove e aggiorna livello
		
		// prendo indice: uno in meno del level
		int index = level-1;
		// prendo codice corrente da confrontare in base al livello attuale
		currN[index] = getLevelCode_NEW(currBean, level);
		// confronto elemento col pre3cedente x vedere se devo creare un nodo di pari livello o sotto al prec
		// il primo nmodo è un nodo di I livello
		if(treeManager.isNodoPariLivelloAlPrec(currBean, lastBean, level)){
			// crea nuovo nodo pari livello
			creaNodo(currBean, level, treeManager);
			// verifica se devo aggiungere il ramo all'albero e resettare il ramo temporaneo
			// se nodo di livello 1 senza figli deve aggiungere solo esso se non ho finito di aggiornare
//			if(level==1){
//				// verifica se devbo aggiungere questo ramo (potrei essere tornato a liv 1 oppure no se ho iniziato ora)
//				checkAddRamo(NUM_LIVELLI, lastN, NUM_LIVELLI, tree, treeManager);
//			}
		}else{
			// crea nuovo nodo sotto al livello
			// aumento il livello temporaneo
			level++;
			treeManager.setCurrLevel(level);
			// creo nodo nel nuovo livello
			creaNodo(currBean, level, treeManager);
		}
		
		// vedi se è il caso di aggiungere il ramo se level è 1 (ma solo se ci sono tornato)
		if(level==1){
			// verificza se è la prima entrata x questo ramo
			if(treeManager.isFirstEntranceForBranch()){
				treeManager.setFirstEntranceForBranch(false);
			}else{
				// verifica se devbo aggiungere questo ramo (potrei essere tornato a liv 1 oppure no se ho iniziato ora)
				checkAddRamo(NUM_LIVELLI, lastN, NUM_LIVELLI, tree, treeManager);
			}
			
		}
		
		// prendo ultimo indice
		lastN[index] = getLevelCode_NEW(currBean, level);
		
	}
	
	private static void gestisciElemento_NEW(StrutturaUfficioCDRBean bean, int level, String[] currN, String[] lastN,
			int NUM_LIVELLI, List<StrutturaUfficioCDRTreeBean> tree, TreeManager treeManager){
		boolean firstEntrance = true;
		// prendi elemento corrente
		// stabilisci livello
		// confronta con il precedente
		// in base al risultato crea nodo dove e aggiorna livello
		
		// indice in base al livello
		int index = level-1;
		// indice corrente in base a cui confrontare
		currN[index] = getLevelCode_NEW(bean, level);
		// confronto col precedente
		if(!(treeManager.nuovoNodoPariLivello(currN[index], lastN[index]))){
			// se sono tornato al primo livello verifico se devo aggiungere il ramo 
			if(level==1 && treeManager.isILivHasChanged()){
				checkAddRamo(NUM_LIVELLI, lastN, NUM_LIVELLI, tree, treeManager);
				treeManager.setILivHasChanged(false);
			}else{
				if(level!=1){
					treeManager.setILivHasChanged(true);
				}
			}
			// creo il nodo sotto al nodo precedente
			creaNodo(bean, treeManager.getCurrLevel()+1, treeManager);
			// aggiorno corrente
			lastN[index] = getLevelCode_NEW(bean, level);
		}else{
			// creo il nodo allo stesso livello del precdedente
			// se prima volta che entro in questo metodo
			creaNodo(bean, treeManager.getCurrLevel(), treeManager);
//			if(treeManager.isFirstEntranceForBranch()){
//				creaNodo(bean, treeManager.getCurrLevel(), treeManager);
//			}else{
//				creaNodo(bean, treeManager.getCurrLevel()+1, treeManager);
//			}
			// aggiorno corrente
			lastN[index] = getLevelCode_NEW(bean, level);
			// aggiorno il livello se non è la prima entrata
//			if(!treeManager.isFirstEntranceForBranch())
//				treeManager.setCurrLevel(level+1);
			// se il livello è minore del num livelli previsti richiamo il metodo (e se ho ancora elementi!)
			// ha senso ??
//			if(treeManager.getCurrLevel() < NUM_LIVELLI){
//				// che bean sto richiamando ??
//				gestisciElemento_NEW(bean, level+1, currN, lastN, NUM_LIVELLI, tree, treeManager);
//			}
		}
		treeManager.setFirstEntranceForBranch(false);
	}
	
	private static String getLevelCode(StrutturaUfficioCDRBean bean, int level){
		switch (level) {
		case 1:
			return bean.getCodiceStruttura_II_Liv();
		case 2:
			return bean.getCodiceStruttura();
		case 3:
			return bean.getCodiceUfficio();
		case 4:
			return bean.getCodiceCDR();
		default:
			return null;
		}
	}
	
	private static String getLevelCode_NEW(StrutturaUfficioCDRBean bean, int level){
		switch (level) {
		case 1:
			return bean.getConfronto();
		case 2:
			return bean.getConfronto2();
		case 3:
			return bean.getConfronto3();
		case 4:
			return bean.getCodiceCDR();
		default:
			return null;
		}
	}
	
	/**
	 * Verifica se ho finito di calcolare un ramo dell'albero e lo posso così aggiungere all'albero.
	 * 
	 * @param level
	 * @param lastN
	 * @param NUM_LIVELLI
	 * @param tree
	 * @param treeManager
	 */
	private static void checkAddRamo(int level, String[] lastN, 
			int NUM_LIVELLI, List<StrutturaUfficioCDRTreeBean> tree, TreeManager treeManager){
		int index = level-1;
		if(treeManager.listNN() != null && treeManager.listNN().get(index) != null && !lastN[index].equals("") && index > 0){
			List<StrutturaUfficioCDRTreeBean> list = treeManager.listNN().get(index-1);
			ListIterator<StrutturaUfficioCDRTreeBean> itr = list.listIterator();
			if(itr.hasNext()){
				StrutturaUfficioCDRTreeBean element = null;
				while(itr.hasNext()){
					element = itr.next();
				}
				if(element!=null)
					element.setChildren(treeManager.listNN().get(index));
			}
		}
		if(level == 1){
			if(treeManager.listNN() != null ){
				List<StrutturaUfficioCDRTreeBean> list = treeManager.listNN().get(0);
				tree.addAll(list);
				if(list!=null && list.size()>0){
					treeManager.setFirstEntranceForBranch(true);
				}
				treeManager.clearListNN();
			}
		}else{
			checkAddRamo(level-1, lastN, NUM_LIVELLI, tree, treeManager);
		}
	}
	
	private static StrutturaUfficioCDRTreeBean convertNodo(StrutturaUfficioCDRBean bean, int level){
		return convert(bean);
	}
	
	private static void addNodo(StrutturaUfficioCDRTreeBean bean, List<StrutturaUfficioCDRTreeBean> list){
		list.add(bean);
	}
	
	private static void creaNodo(StrutturaUfficioCDRBean bean, int level, TreeManager treeManager){
		int index = level-1;
		addNodo(convertNodo(bean, level), treeManager.listNN().get(index));
	}
	
}
