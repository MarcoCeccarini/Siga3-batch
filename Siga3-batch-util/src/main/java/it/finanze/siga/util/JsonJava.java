package it.finanze.siga.util;

import it.finanze.siga.bean.ProfiloBean;
import it.finanze.siga.bean.ProfiloRichiestaBean;
import it.finanze.siga.util.tree.ProfiloTreeBean;
import it.finanze.siga.util.tree.ProfiloTreeBeanRequest;
import it.finanze.siga.util.tree.ProfiloTreeSubBeanDeserializer;
import it.finanze.siga.util.tree.StrutturaUfficioCDRTreeBean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class JsonJava {

	static JsonSerializer<Date> ser = new JsonSerializer<Date>() {
		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext 
				context) {
			Logg.getLogger().info("IN JsonSerializer<Date>");
			return src == null ? null : new JsonPrimitive(src.getTime());
		}
	};

	static JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
		@Override
		public Date deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			Logg.getLogger().info("IN JsonDeserializer<Date>");
			return json == null ? null : new Date(json.getAsLong());
		}
	};

	public static String jsonTreeStringByList(List<ProfiloTreeBean> list){
		Logg.getLogger().info("IN jsonTreeStringByList()");
		//Gson gson = new Gson();
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("dd/MM/yyyy");
//		builder.registerTypeAdapter(Date.class, ser)
//		.registerTypeAdapter(Date.class, deser);
		builder.registerTypeAdapter(ProfiloTreeSubBeanDeserializer.class, new ProfiloTreeSubBeanDeserializer());
		Gson gson = builder.create();

		String jsonTreeString = gson.toJson(list);
		gson = null;
		return jsonTreeString;
	}
	
	public static String jsonTreeStringBySUCList(List<StrutturaUfficioCDRTreeBean> list){
		Logg.getLogger().info("IN jsonTreeStringByList()");
		//Gson gson = new Gson();
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("dd/MM/yyyy");
//		builder.registerTypeAdapter(Date.class, ser)
//		.registerTypeAdapter(Date.class, deser);
		builder.registerTypeAdapter(ProfiloTreeSubBeanDeserializer.class, new ProfiloTreeSubBeanDeserializer());
		Gson gson = builder.create();

		String jsonTreeString = gson.toJson(list);
		gson = null;
		return jsonTreeString;
	}

	public static String jsonTreeStringByListOfProfiloTreeBeanRequest(List<ProfiloTreeBeanRequest> list){
		Logg.getLogger().info("IN jsonTreeStringByList()");
		//Gson gson = new Gson();
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("dd/MM/yyyy");
//		builder.registerTypeAdapter(Date.class, ser)
//		.registerTypeAdapter(Date.class, deser);
		builder.registerTypeAdapter(ProfiloTreeSubBeanDeserializer.class, new ProfiloTreeSubBeanDeserializer());
		Gson gson = builder.create();

		String jsonTreeString = gson.toJson(list);
		gson = null;
		return jsonTreeString;
	}

	public static ProfiloTreeBean treeFromJson(String jsonTree){
		Logg.getLogger().info("IN toJson()");
		//Gson gson = new Gson();
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("dd/MM/yyyy");
//		builder.registerTypeAdapter(Date.class, ser)
//		.registerTypeAdapter(Date.class, deser);
		builder.registerTypeAdapter(ProfiloTreeSubBeanDeserializer.class, new ProfiloTreeSubBeanDeserializer());
		Gson gson = builder.create();

		ProfiloTreeBean tree = gson.fromJson(jsonTree, ProfiloTreeBean.class);
		gson = null;
		return tree;
	}

	public static List<ProfiloTreeBean> treeChildrenFromJson(String jsonTree){
		Logg.getLogger().debug("IN treeChildrenFromJson()");
		//Gson gson = new Gson();
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("dd/MM/yyyy");
//		builder.registerTypeAdapter(Date.class, ser)
//		.registerTypeAdapter(Date.class, deser);
		builder.registerTypeAdapter(ProfiloTreeSubBeanDeserializer.class, new ProfiloTreeSubBeanDeserializer());
		Gson gson = builder.create();

		Type listType = new TypeToken<List<ProfiloTreeBean>>(){}.getType();
		List<ProfiloTreeBean> treeChildren = gson.fromJson(jsonTree, listType);
		gson = null;
		return treeChildren;
	}

	public static List<ProfiloTreeBeanRequest> treeChildrenOfProfiloTreeBeanRequestFromJson(String jsonTree){
		Logg.getLogger().debug("IN treeChildrenFromJson()");
		//Gson gson = new Gson();
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("dd/MM/yyyy");
//		builder.registerTypeAdapter(Date.class, ser)
//		.registerTypeAdapter(Date.class, deser);
		builder.registerTypeAdapter(ProfiloTreeSubBeanDeserializer.class, new ProfiloTreeSubBeanDeserializer());
		Gson gson = builder.create();		
		Type listType = new TypeToken<List<ProfiloTreeBeanRequest>>(){}.getType();
		List<ProfiloTreeBeanRequest> treeChildren = gson.fromJson(jsonTree, listType);
		gson = null;
		return treeChildren;
	}

	public static List<ProfiloTreeBean> treeChildrenOfProfiloTreeBeanFromJson(String jsonTree){
		Logg.getLogger().debug("IN treeChildrenOfProfiloTreeBeanFromJson()");
		//Gson gson = new Gson();
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("dd/MM/yyyy");
//		builder.registerTypeAdapter(Date.class, ser)
//		.registerTypeAdapter(Date.class, deser);
		builder.registerTypeAdapter(ProfiloTreeSubBeanDeserializer.class, new ProfiloTreeSubBeanDeserializer());
		Gson gson = builder.create();

		Type listType = new TypeToken<List<ProfiloTreeBean>>(){}.getType();
		List<ProfiloTreeBean> treeChildren = gson.fromJson(jsonTree, listType);
		gson = null;
		return treeChildren;
	}

	/**
	 * Converte una lista di ProfiloTreeBeanRequest in ProfiloTreeBean
	 * @param fromList
	 * @return list of ProfiloTreeBean
	 */
	public static List<ProfiloTreeBean> convertList(List<ProfiloTreeBeanRequest> fromList){
		List<ProfiloTreeBean> list = new ArrayList<ProfiloTreeBean>();
		for (ProfiloTreeBeanRequest profiloTreeBeanRequest : fromList) {
			ProfiloTreeBean bean = new ProfiloTreeBean();
			bean = convertBean(profiloTreeBeanRequest);
			list.add(bean);
		}
		return list;
	}

	private static ProfiloTreeBean convertBean(ProfiloTreeBeanRequest beanFrom){
		ProfiloTreeBean bean = null;
		if(beanFrom!=null){
			bean = new ProfiloTreeBean();
			bean.setTitle(beanFrom.getTitle());
			bean.setExpanded(beanFrom.isExpanded());
			bean.setHideCheckbox(beanFrom.isHideCheckbox());
			bean.setSelected(beanFrom.isSelected());
			if(beanFrom.getChildren()!=null){
				List<ProfiloTreeBean> children = new ArrayList<ProfiloTreeBean>();
				for (ProfiloTreeBeanRequest ptbr : beanFrom.getChildren()) {
					ProfiloTreeBean bean2 = convertBean(ptbr);
					children.add(bean2);
				}
				bean.setChildren(children);
			}
			if(beanFrom.getData()!=null){
				bean.setIterVerified(beanFrom.getData().isIterVerified());
				bean.setStartSelected(beanFrom.getData().isStartSelected());
				bean.setIdIter(beanFrom.getData().getIdIter());
				bean.setCodiceRoleGroup(beanFrom.getData().getCodiceRoleGroup()!=null?beanFrom.getData().getCodiceRoleGroup():null);
				bean.setCodiceProfilo(beanFrom.getData().getCodiceProfilo()!=null?beanFrom.getData().getCodiceProfilo():null);
				/////// RICORDARSI DI COPIARE GLI ALTRI CAMPI
				bean.setExplicit_entitlement(beanFrom.getData().getExplicit_entitlement());
				bean.setProfiloDesc(beanFrom.getData().getProfiloDesc());
				bean.setRoleGroupDesc(beanFrom.getData().getRoleGroupDesc());
				bean.setTipoAbilitazione(beanFrom.getData().getTipoAbilitazione());
				bean.setInfoNote(beanFrom.getData().getInfoNote());
				if(beanFrom.getData().getDataScadenza()!=null){
					// workaround per il problema: Unparsable date "" -> la libreria GSON se ha un json con data = '' va in errore
					// per ovviare cio' al posto di una data = '' viene inserito il valore 01/01/1900
					if (beanFrom.getData().getDataScadenza().toString().equals(("Mon Jan 01 00:00:00 CET 1900"))){
						bean.setDataScadenza(null);	
					}else{
						bean.setDataScadenza(beanFrom.getData().getDataScadenza());	
					}										
				}				
				if(beanFrom.getData().getDataScadenzaVisibilita()!=null){
					if (beanFrom.getData().getDataScadenzaVisibilita().toString().equals(("Mon Jan 01 00:00:00 CET 1900"))){
						bean.setDataScadenzaVisibilita(null);	
					}else{
						bean.setDataScadenzaVisibilita(beanFrom.getData().getDataScadenzaVisibilita());	
					}										
				}
				
				bean.setCodiceApplicazione(beanFrom.getData().getCodiceApplicazione());
				bean.setOp(beanFrom.getData().getOp());
			}

		}
		return bean;
	}

	public static List<ProfiloBean> convertListIntoProfiloBeanList(List<ProfiloTreeBean> fromList, int profilazione){
		List<ProfiloBean> list = new ArrayList<ProfiloBean>();
		for (ProfiloTreeBean profiloTreeBean : fromList) {
			ProfiloBean bean = new ProfiloRichiestaBean();
			bean = convertBean(profiloTreeBean, profilazione);
			list.add(bean);
		}
		return list;
	}
	
	private static ProfiloBean convertBean(ProfiloTreeBean beanFrom, int profilazione){
		ProfiloBean bean = null;
		if(beanFrom!=null){
			bean = new ProfiloRichiestaBean();
			bean.setApplicazioneDesc(beanFrom.getApplicazioneDesc());
			bean.setAttivo(beanFrom.getAttivo());
			bean.setCodiceApplicazione(beanFrom.getCodiceApplicazione());
			bean.setCodiceProfilo(beanFrom.getCodiceProfilo());
			bean.setCodiceRoleGroup(beanFrom.getCodiceRoleGroup());
			bean.setDataScadenza(beanFrom.getDataScadenza());
			bean.setDataScadenzaVisibilita(beanFrom.getDataScadenzaVisibilita());
			bean.setProfiloDesc(beanFrom.getProfiloDesc());
			bean.setRoleGroupDesc(beanFrom.getRoleGroupDesc());
			bean.setTipoAbilitazione(beanFrom.getTipoAbilitazione());
			bean.setIdIter(beanFrom.getIdIter());
			bean.setOp(beanFrom.getOp());
			bean.setExplicit_entitlement(beanFrom.getExplicit_entitlement());
			bean.setInfoNote(beanFrom.getInfoNote());
			switch (profilazione) {
			case Constants.profilazione_standard:
				bean.setOperazione(calcOperation(beanFrom.isSelected(), beanFrom.isStartSelected()));
				break;
			case Constants.profilazione_carica_da_template:
			case Constants.profilazione_carica_da_operatore:
				bean.setOperazione(calcOperationCDT(beanFrom.getOp()));
				break;
			default:
				break;
			}

			if(beanFrom.getChildren()!=null){
				throw new RuntimeException("Se questo bean ha children non è un child x questo albero (profili)!");
			}
		}
		return bean;
	}
	
	private static String calcOperation(boolean selected, boolean startSelected){
		String operation = "";
		if(startSelected && !selected){
			operation = ProfiloBean.rimosso;
		}else if(!startSelected && selected){
			operation = ProfiloBean.aggiunto;
		}
		return operation;
	}
	
	private static String calcOperationCDT(String op){
		String operation = "";
		if(op==null)
			return operation;
		else if(op.equals(ProfiloBean.toAddOp)){
			operation = ProfiloBean.aggiunto;
		}else if(op.equals(ProfiloBean.toRemoveOp)){
			operation = ProfiloBean.rimosso;
		}
		return operation;
	}
	
}
