package it.finanze.siga.util.tree;

import it.finanze.siga.bean.StrutturaUfficioCDRBean;
import it.finanze.siga.bean.StrutturaUfficioCDRExtBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Funzione:
 * Si occupa di gestire il numero di livelli del tree e la variabile di appoggio listNN
 * Per le operazioni sul tree usa il tree engine TreeEngineSingleton
 * 
 * Uso:
 * Istanziare il TreeManager. Di default gestisce fino a 4 livelli annidati.
 * Se si vuole altrimenti, specificare il numero di livelli prima dell'uso.
 * 
 * @author FA
 *
 */
public class TreeManager {
	
	/**
	 * numero livelli di default
	 */
	private int NUM_LIVELLI = 4;
	/**
	 * livello corrente
	 */
	private int currLevel = 1;
	/**
	 * x vedere se ritorno al I liv e posso provare ad aggiungere il ramo
	 */
	private boolean ILivHasChanged;
	
	/**
	 * se prima entrata per questo ramo di albero
	 */
	private boolean firstEntranceForBranch = true;
	
	public boolean isFirstEntranceForBranch() {
		return firstEntranceForBranch;
	}

	public void setFirstEntranceForBranch(boolean firstEntranceForBranch) {
		this.firstEntranceForBranch = firstEntranceForBranch;
	}

	public boolean isILivHasChanged() {
		return ILivHasChanged;
	}

	public void setILivHasChanged(boolean iLivHasChanged) {
		ILivHasChanged = iLivHasChanged;
	}

	public int getCurrLevel() {
		return currLevel;
	}

	public void setCurrLevel(int currLevel) {
		this.currLevel = currLevel;
	}

	private List<List<StrutturaUfficioCDRTreeBean>> listNN;
	
	public static TreeManager newInstance(){
		TreeManager treeManager = new TreeManager();
		return treeManager;
	}
	
	public void init(int numLevels){
		NUM_LIVELLI = numLevels;
	}
	
	public TreeManager andInit(int numLevels){
		NUM_LIVELLI = numLevels;
		return this;
	}
	
	public void initListNN(){
		listNN = new ArrayList<List<StrutturaUfficioCDRTreeBean>>(NUM_LIVELLI);
		for (int i = 0; i < NUM_LIVELLI; i++) {
			listNN.add(i, new ArrayList<StrutturaUfficioCDRTreeBean>());
		}
	}
	
	public void clearListNN(){
		initListNN();
	}
	
	public List<List<StrutturaUfficioCDRTreeBean>> listNN() {
		return listNN;
	}

	public void setListNN(List<List<StrutturaUfficioCDRTreeBean>> listNN) {
		this.listNN = listNN;
	}
	
//	public List<StrutturaUfficioCDRTreeBean> createTreeFromList(List<StrutturaUfficioCDRBean> elenco){
//		TreeEngineSingleton treeEngineSingleton = TreeEngineSingleton.getInstance();
//		return treeEngineSingleton.createTreeFromList(elenco, NUM_LIVELLI, this); 
//	}
	/**
	 * crea una struttura dati che rappresenta l'albero, data una lista  
	 * @param elenco StrutturaUfficioCDRExtBean
	 * @return List<StrutturaUfficioCDRTreeBean>
	 */
	public List<StrutturaUfficioCDRTreeBean> createTreeFromListCDRExtBean(List<StrutturaUfficioCDRExtBean> elenco){
		List<StrutturaUfficioCDRTreeBean> tree = new ArrayList<StrutturaUfficioCDRTreeBean>();
		StrutturaUfficioCDRTreeBean nodoPrecedente = new StrutturaUfficioCDRTreeBean(); 
		StrutturaUfficioCDRTreeBean nodoDaInserire = new StrutturaUfficioCDRTreeBean();
		
		for (StrutturaUfficioCDRExtBean struttUffCDR : elenco) {
			nodoDaInserire = converti(struttUffCDR);
			//primo livello lo inserisco 
			if(nodoDaInserire.getLivello() == 1){
				tree.add(nodoDaInserire);
			}
			else if(nodoDaInserire.getLivello() == nodoPrecedente.getLivello()+1 ){
				//scendi di livello				
				nodoPrecedente.getChildren().add(nodoDaInserire);
			}
			else{
				inserisciNodoLivelloSuperiore(tree,nodoDaInserire);
			}
			nodoPrecedente = nodoDaInserire;
		}
		tree = this.orderTree(tree);
		return  tree;
	}
	
	/**
	 * Metodo ricorsivo, per salire di livello quando c'Ã¨ da inserire un nuovo nodo nei padri
	 * 
	 * @param tree albero o sotto albero 
	 * @param nodoDaInserire nodo che andra inserito 
	 */
	private void inserisciNodoLivelloSuperiore(	List<StrutturaUfficioCDRTreeBean> tree, StrutturaUfficioCDRTreeBean nodoDaInserire) {
		
		if(tree.size() == 0){
		 
		}
		StrutturaUfficioCDRTreeBean padre = tree.get(tree.size()-1);
		if(nodoDaInserire.getLivello() == padre.getLivello()+1 ){
			//scendi di livello				
			padre.getChildren().add(nodoDaInserire);
		}else{
			inserisciNodoLivelloSuperiore(padre.getChildren(), nodoDaInserire);
		}
	}
	
	/**
	 * Converte un bean StrutturaUfficioCDRExtBean in uno di tipo StrutturaUfficioCDRTreeBean
	 * @param strut
	 * @return
	 */
	private StrutturaUfficioCDRTreeBean converti(StrutturaUfficioCDRExtBean strut) {
		StrutturaUfficioCDRTreeBean s = new StrutturaUfficioCDRTreeBean();
		
		s.setCDRDesc(strut.getCDRDesc());
		s.setCodiceCDR(strut.getCodiceCDR());
		s.setCodiceStruttura(strut.getCodiceStruttura());
		s.setCodiceUfficio(strut.getCodiceUfficio());
		s.setStrutturaDesc(strut.getStrutturaDesc());
		s.setUfficioDesc(strut.getUfficioDesc());
		s.setOrdinamento(strut.getOrdinamento());
		s.setLivello(strut.getLivello());
		s.setChildren(new ArrayList<StrutturaUfficioCDRTreeBean>());
		//Aggiunto per inibire la possibilita di selezionare un cdr già associato
		s.setUnselectable(strut.isUnselectable());
		s.setSelected(strut.isSelected());
		
		s.setTitle(strut.getCDRDesc());
		return s;
	}

	/**
	 * confronto tra record attuale e precedente per vedere se ho un nuovo nodo o se va sotto al precedente
	 * 
	 * @param curr
	 * @param last
	 * @return veritÃ : se va sotto al precedente o lo devo aggiungere come nuovo nodo (non sotto al prec o si se Ã¨ di 1 liv o altri liv)
	 */
	public boolean nuovoNodoPariLivello(String curr, String last){
		//return curr.equals(last);
		if(curr.compareTo("1")==0){
			// nuovo nodo
						return true;
		}
			// aggiungo al precedente
						return false;
	}
	
	public boolean isNodoPariLivelloAlPrec(StrutturaUfficioCDRBean curr, StrutturaUfficioCDRBean last, int level){
		boolean response = false;
		
		if(level==1){
			String currCode = curr.getConfronto();
			if(currCode.compareTo("1")==0){
				response = true;
			}else{
				response = false;
			}
		}else if(level==2){
			String currCode = curr.getConfronto2();
			if(currCode.compareTo("1")==0){
				response = true;
			}else{
				response = false;
			}
		}else if(level==3){
			String currCode = curr.getConfronto3();
			if(currCode.compareTo("1")==0){
				response = true;
			}else{
				response = false;
			}
		}else if(level==4){
			response = true;
		}
		
		return response;
	}

	/**
	 * Chiama ricorsivamente i figli del tree e poi i figli dei figli e li ordina in base al campo ordinamento.
	 * 
	 * @param tree
	 * @return tree ordinato
	 */
	public List<StrutturaUfficioCDRTreeBean> orderTree(List<StrutturaUfficioCDRTreeBean> tree) {
		Collections.sort(tree, new Comparator<Object>() {
			public int compare(Object o1, Object o2){
				StrutturaUfficioCDRTreeBean object1 = (StrutturaUfficioCDRTreeBean) o1;
				StrutturaUfficioCDRTreeBean object2 = (StrutturaUfficioCDRTreeBean) o2;
					return (object1.getOrdinamento()-object2.getOrdinamento());
			}
		});
		for (StrutturaUfficioCDRTreeBean bean : tree) 
			// se un nodo ha figli ordinali, finchÃ© ogni figlio ha figli, ordinali
			if(bean.getChildren()!=null)
				
				orderTree(bean.getChildren());
		
		return tree;
		
	}
	
}
