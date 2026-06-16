package it.finanze.siga.util;

import java.util.ArrayList;
import java.util.List;

import it.finanze.siga.bean.OperatoreBean;
import it.finanze.siga.bean.StrutturaUfficioCDRBean;
import it.finanze.siga.util.bean.ModuloAppBean;
import it.finanze.siga.util.bean.UtenteBean;
import it.finanze.siga.util.tree.ProfiloTreeBean;
import it.finanze.siga.util.tree.StrutturaUfficioCDRTreeBean;
import it.finanze.siga.util.tree.TreeEngineSingleton;

public class Mocks {
	
	public static List<StrutturaUfficioCDRTreeBean> createTreeStruttUffCDRTB_1(){
		List<StrutturaUfficioCDRTreeBean> list = new ArrayList<StrutturaUfficioCDRTreeBean>();
		
		StrutturaUfficioCDRBean tempBean;
		StrutturaUfficioCDRTreeBean tempTreeBean;
		List<StrutturaUfficioCDRTreeBean> children, children1, children2;
		
		
		// uffici
		children1 = new ArrayList<StrutturaUfficioCDRTreeBean>();
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5410000");
		tempBean.setCodiceStruttura("L7A");
		tempBean.setCodiceUfficio("L7A");
		tempBean.setCodiceCDR("B5412500");
		tempBean.setCDRDesc("DESC 12");
		tempTreeBean = new StrutturaUfficioCDRTreeBean();
		tempTreeBean = TreeEngineSingleton.convert(tempBean);
		children1.add(tempTreeBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5410000");
		tempBean.setCodiceStruttura("L7A");
		tempBean.setCodiceUfficio("L7A");
		tempBean.setCodiceCDR("B5411200");
		tempBean.setCDRDesc("DESC 13");
		tempTreeBean = new StrutturaUfficioCDRTreeBean();
		tempTreeBean = TreeEngineSingleton.convert(tempBean);
		children1.add(tempTreeBean);
		
		// uffici
		children2 = new ArrayList<StrutturaUfficioCDRTreeBean>();
				tempBean = new StrutturaUfficioCDRBean();
				tempBean.setCodiceStruttura_II_Liv("B5410000");
				tempBean.setCodiceStruttura("L7A");
				tempBean.setCodiceUfficio("L7A");
				tempBean.setCodiceCDR("B5412500");
				tempBean.setCDRDesc("DESC 12");
				tempTreeBean = new StrutturaUfficioCDRTreeBean();
				tempTreeBean = TreeEngineSingleton.convert(tempBean);
				children2.add(tempTreeBean);
				
				tempBean = new StrutturaUfficioCDRBean();
				tempBean.setCodiceStruttura_II_Liv("B5410000");
				tempBean.setCodiceStruttura("L7A");
				tempBean.setCodiceUfficio("L7A");
				tempBean.setCodiceCDR("B5411200");
				tempBean.setCDRDesc("DESC 13");
				tempTreeBean = new StrutturaUfficioCDRTreeBean();
				tempTreeBean = TreeEngineSingleton.convert(tempBean);
				children2.add(tempTreeBean);
		
		// strutture
		children = new ArrayList<StrutturaUfficioCDRTreeBean>();
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5410000");
		tempBean.setCodiceStruttura("L7A");
		tempBean.setCodiceUfficio("L7A");
		tempBean.setCodiceCDR("B5412500");
		tempBean.setCDRDesc("DESC 12");
		tempTreeBean = new StrutturaUfficioCDRTreeBean();
		tempTreeBean = TreeEngineSingleton.convert(tempBean);
		tempTreeBean.setChildren(children1);
		children.add(tempTreeBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5410000");
		tempBean.setCodiceStruttura("L7A");
		tempBean.setCodiceUfficio("L7A");
		tempBean.setCodiceCDR("B5411200");
		tempBean.setCDRDesc("DESC 13");
		tempTreeBean = new StrutturaUfficioCDRTreeBean();
		tempTreeBean = TreeEngineSingleton.convert(tempBean);
		tempTreeBean.setChildren(children2);
		children.add(tempTreeBean);
		
		// struttura II liv (1° liv albero)
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5410000");
		tempBean.setCodiceStruttura("L7A");
		tempBean.setCodiceUfficio("L7A");
		tempBean.setCodiceCDR("B5410000");
		tempBean.setCDRDesc("DIREZIONE CENTRALE ACCERTAMENTO");
		tempTreeBean = new StrutturaUfficioCDRTreeBean();
		tempTreeBean = TreeEngineSingleton.convert(tempBean);
		tempTreeBean.setChildren(children);
		list.add(tempTreeBean);
		
		return list;
	}
	
	public static List<StrutturaUfficioCDRBean> createTreeStruttUffCDR_1(){
		List<StrutturaUfficioCDRBean> list = new ArrayList<StrutturaUfficioCDRBean>();
		
		StrutturaUfficioCDRBean tempBean;
		
		// struttura II liv - 1
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5410000");
		tempBean.setCodiceStruttura("L7A");
		tempBean.setCodiceUfficio("L7A");
		tempBean.setCodiceCDR("B5410000");
		tempBean.setCDRDesc("DIREZIONE CENTRALE ACCERTAMENTO");
		list.add(tempBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5410000");
		tempBean.setCodiceStruttura("L7A");
		tempBean.setCodiceUfficio("L7A");
		tempBean.setCodiceCDR("B5412500");
		tempBean.setCDRDesc("DESC 12");
		list.add(tempBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5410000");
		tempBean.setCodiceStruttura("L7A");
		tempBean.setCodiceUfficio("L7A");
		tempBean.setCodiceCDR("B5411200");
		tempBean.setCDRDesc("DESC 13");
		list.add(tempBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5410000");
		tempBean.setCodiceStruttura("L7A");
		tempBean.setCodiceUfficio("L7A");
		tempBean.setCodiceCDR("B5410900");
		tempBean.setCDRDesc("DESC 14");
		list.add(tempBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5410000");
		tempBean.setCodiceStruttura("L7A");
		tempBean.setCodiceUfficio("L7A");
		tempBean.setCodiceCDR("B5411300");
		tempBean.setCDRDesc("DESC 15");
		list.add(tempBean);
		
		return list;
	}
	
	public static List<StrutturaUfficioCDRBean> createTreeStruttUffCDR_2(){
		List<StrutturaUfficioCDRBean> list = new ArrayList<StrutturaUfficioCDRBean>();
		
		StrutturaUfficioCDRBean tempBean;
		
		// struttura II liv - 2
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5710000");
		tempBean.setCodiceStruttura("L7E");
		tempBean.setCodiceUfficio("L7E");
		tempBean.setCodiceCDR("B5710000");
		tempBean.setCDRDesc("DIREZIONE CENTRALE DEL PERSONALE");
		list.add(tempBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5710000");
		tempBean.setCodiceStruttura("L7E");
		tempBean.setCodiceUfficio("L7E");
		tempBean.setCodiceCDR("B0203000");
		tempBean.setCDRDesc("DESC 2 2");
		list.add(tempBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5710000");
		tempBean.setCodiceStruttura("L7E");
		tempBean.setCodiceUfficio("L7E");
		tempBean.setCodiceCDR("B0203010");
		tempBean.setCDRDesc("DESC 2 3");
		list.add(tempBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5710000");
		tempBean.setCodiceStruttura("L7E");
		tempBean.setCodiceUfficio("L7E");
		tempBean.setCodiceCDR("B5713800");
		tempBean.setCDRDesc("DESC 2 4");
		list.add(tempBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5710000");
		tempBean.setCodiceStruttura("L7E");
		tempBean.setCodiceUfficio("L7E");
		tempBean.setCodiceCDR("B5702000");
		tempBean.setCDRDesc("DESC 2 5");
		list.add(tempBean);
		
		return list;
	}
	
	public static List<StrutturaUfficioCDRBean> createTreeStruttUffCDR_3(){
		List<StrutturaUfficioCDRBean> list = new ArrayList<StrutturaUfficioCDRBean>();
		
		StrutturaUfficioCDRBean tempBean;
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5730000");
		tempBean.setCodiceStruttura("L7E");
		tempBean.setCodiceUfficio("L7E");
		tempBean.setCodiceCDR("B5720000");
		tempBean.setCDRDesc("DIREZIONE CENTRALE DEL PERSONALE 3");
		list.add(tempBean);
		
		return list;
	}
	
	public static List<StrutturaUfficioCDRBean> createTreeStruttUffCDR_4(){
		List<StrutturaUfficioCDRBean> list = new ArrayList<StrutturaUfficioCDRBean>();
		
		StrutturaUfficioCDRBean tempBean;
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5750000");
		tempBean.setCodiceStruttura("L7E");
		tempBean.setCodiceUfficio("L7E");
		tempBean.setCodiceCDR("B5720006");
		tempBean.setCDRDesc("DIREZIONE CENTRALE DEL PERSONALE 4");
		list.add(tempBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5750000");
		tempBean.setCodiceStruttura("L7E");
		tempBean.setCodiceUfficio("L89");
		tempBean.setCodiceCDR("B5720007");
		tempBean.setCDRDesc("4 2");
		list.add(tempBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5750000");
		tempBean.setCodiceStruttura("L7E");
		tempBean.setCodiceUfficio("L90");
		tempBean.setCodiceCDR("B5720008");
		tempBean.setCDRDesc("4 3");
		list.add(tempBean);
		
		return list;
	}
	
	public static List<StrutturaUfficioCDRBean> createTreeStruttUffCDR_5(){
		List<StrutturaUfficioCDRBean> list = new ArrayList<StrutturaUfficioCDRBean>();
		
		StrutturaUfficioCDRBean tempBean;
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5790000");
		tempBean.setCodiceStruttura("L7E");
		tempBean.setCodiceUfficio("L7E");
		tempBean.setCodiceCDR("B5890000");
		tempBean.setCDRDesc("DIREZIONE CENTRALE DEL PERSONALE 5");
		list.add(tempBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5790000");
		tempBean.setCodiceStruttura("L7G");
		tempBean.setCodiceUfficio("L7F");
		tempBean.setCodiceCDR("B5890001");
		tempBean.setCDRDesc(" 5 2");
		list.add(tempBean);
		
		tempBean = new StrutturaUfficioCDRBean();
		tempBean.setCodiceStruttura_II_Liv("B5790000");
		tempBean.setCodiceStruttura("L7H");
		tempBean.setCodiceUfficio("L7K");
		tempBean.setCodiceCDR("B5890002");
		tempBean.setCDRDesc(" 5 3");
		list.add(tempBean);
		
		return list;
		
	}
	
	public static List<StrutturaUfficioCDRBean> createTreeStruttUffCDR_1_list(){
		List<StrutturaUfficioCDRBean> list = new ArrayList<StrutturaUfficioCDRBean>();
		
		// struttura II liv - 1
		list.addAll(createTreeStruttUffCDR_1());
		
		return list;
	}
	
	public static List<StrutturaUfficioCDRBean> createTreeStruttUffCDR_2_list(){
		List<StrutturaUfficioCDRBean> list = new ArrayList<StrutturaUfficioCDRBean>();
		
		// struttura II liv - 1
		list.addAll(createTreeStruttUffCDR_1());
		
		// struttura II liv - 2
		list.addAll(createTreeStruttUffCDR_2());
		
		return list;
	}
	
	public static List<StrutturaUfficioCDRBean> createTreeStruttUffCDR_3_list(){
		List<StrutturaUfficioCDRBean> list = new ArrayList<StrutturaUfficioCDRBean>();
		
		// struttura II liv - 1
		list.addAll(createTreeStruttUffCDR_2_list());
		
		// struttura II liv - 3
		list.addAll(createTreeStruttUffCDR_3());
		
		return list;
	}
	
	public static List<StrutturaUfficioCDRBean> createTreeStruttUffCDR_4_list(){
		List<StrutturaUfficioCDRBean> list = new ArrayList<StrutturaUfficioCDRBean>();
		
		// struttura II liv - 1
		list.addAll(createTreeStruttUffCDR_3_list());
		
		// struttura II liv - 3
		list.addAll(createTreeStruttUffCDR_4());
		
		return list;
	}
	
	public static List<StrutturaUfficioCDRBean> createTreeStruttUffCDR_5_list(){
		List<StrutturaUfficioCDRBean> list = new ArrayList<StrutturaUfficioCDRBean>();
		
		// struttura II liv - 1
		list.addAll(createTreeStruttUffCDR_4_list());
		
		// struttura II liv - 3
		list.addAll(createTreeStruttUffCDR_5());
		
		return list;
	}
	
	/**
	 *  FAKE TREE AFTER CHECK ITER
	 * @return tree
	 */
	public static List<ProfiloTreeBean> createTree(){
		ProfiloTreeBean ptb1 = new ProfiloTreeBean();
		ptb1.setTitle("Role group 1");
		ptb1.setHideCheckbox(true);
		ptb1.setExpanded(true);
		List<ProfiloTreeBean> children1 = new ArrayList<ProfiloTreeBean>();
		ProfiloTreeBean children11 = new ProfiloTreeBean();
		children11.setTitle("Utente della DCA");
		children11.setStartSelected(false);
		children11.setSelected(false);
		ProfiloTreeBean children12 = new ProfiloTreeBean();
		children12.setTitle("Utente dell Ufficio Territoriale");
		// inizio selezionato
		children12.setStartSelected(true);
		children12.setSelected(true);
		// iter ok
		children12.setIterVerified(true);
		children1.add(children11);
		children1.add(children12);
		ptb1.setChildren(children1);
		ProfiloTreeBean ptb2 = new ProfiloTreeBean();
		ptb2.setTitle("Role group 2");
		ptb2.setHideCheckbox(true);
		ProfiloTreeBean ptb3 = new ProfiloTreeBean();
		ptb3.setTitle("Role group 3");
		ptb3.setHideCheckbox(true);
		// trasformo albero in json
		List<ProfiloTreeBean> tree = new ArrayList<ProfiloTreeBean>();
		tree.add(ptb1);
		tree.add(ptb2);
		tree.add(ptb3);
		return tree;
	}
	
	public static List<Object> createListOperatori(){
		List<Object> lista =  new ArrayList<Object>();
		lista.add(new OperatoreBean("cognome1","nome1","cdr1","tipo1","cf1"));
		lista.add(new OperatoreBean("cognome2","nome2","cdr2","tipo2","cf1"));
		lista.add(new OperatoreBean("cognome3","nome3","cdr3","tipo3","cf1"));
		lista.add(new OperatoreBean("cognome4","nome4","cdr4","tipo4","cf1"));
		lista.add(new OperatoreBean("cognome5","nome5","cdr5","tipo5","cf1"));
		lista.add(new OperatoreBean("cognome6","nome6","cdr6","tipo6","cf1"));
		lista.add(new OperatoreBean("cognome7","nome7","cdr7","tipo7","cf1"));
		lista.add(new OperatoreBean("cognome8","nome8","cdr8","tipo8","cf1"));
		lista.add(new OperatoreBean("cognome9","nome9","cdr9","tipo9","cf1"));
		lista.add(new OperatoreBean("cognome10","nome0","cdr0","tipo0","cf1"));
		lista.add(new OperatoreBean("cognome11","nome1","cdr1","tipo1","cf1"));
		lista.add(new OperatoreBean("cognome12","nome2","cdr2","tipo2","cf1"));
		return lista;
	}
	
	public static class Session{
		
		private static Mocks.Session instance = new Mocks.Session();
		
		public static Mocks.Session getInstance(){
			return instance;
		}
		
		private Session() {}

		public static Object getAttribute(String key){
			Object obj = null;
			
			if(key.equalsIgnoreCase(CostantiSessione.UTENTE_BEAN)){
				UtenteBean object = new UtenteBean();
				String cf = "SMMMMN52EDDD224S";
				object.setCodFiscaleUtente(cf);
				obj = object;
			}
				
			else if(key.equalsIgnoreCase(CostantiSessione.OPERATORE_BEAN))
				obj = new OperatoreBean();
			
			else if(key.equalsIgnoreCase(CostantiSessione.MODULO_APP_BEAN))
				obj = new ModuloAppBean();
			
			return obj;
		}
	}
	
	public static Mocks.Session getSession(){
		return Mocks.Session.getInstance();
	}
	
}
