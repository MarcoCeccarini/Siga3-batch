package it.finanze.siga.util;

import it.finanze.siga.util.bean.ElencoBean;
import it.finanze.siga.util.finder.PaginateFinder;
import it.finanze.siga.util.forms.PaginateForm;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PaginazioneUtils {

	//@Deprecated
	public static void gestioneRequestPaginazione(ActionMapping mapping, ActionForm form, HttpServletRequest request, ElencoBean elenco) {
		
		PaginateForm paginateForm = (PaginateForm) form;	
		
		request.setAttribute("action", mapping.getPath());
		request.setAttribute("actionForm", mapping.getName());
		request.setAttribute("hiddenProperties", (getActionUrl(form)).get("hiddenProperties") );
    	request.setAttribute("actionUrl", mapping.getPath()+".do"+(getActionUrl(form)).get("actionUrl"));
    	request.setAttribute("numeroElementi", elenco.getNumeroElementi());
    	request.setAttribute("currentPage", paginateForm.getPagina());
    	request.setAttribute("elementsPerPage", paginateForm.getRecordPerPagina());
    	request.setAttribute("totalePagine", getTotalePagine(elenco.getNumeroElementi(), paginateForm.getRecordPerPagina()));
	}

	//@Deprecated
	public static Map<String, Object> getActionUrl(Object obj){
		
		Class<?> aClass = obj.getClass();
		StringBuffer strb = new StringBuffer();
		boolean flagFirst = true;
		
		Map<String, Object> toReturn = new HashMap<String, Object>();
		List<String> hiddenProperties = new ArrayList<String>();
		
		try {
			
			for (PropertyDescriptor pd : Introspector.getBeanInfo(aClass).getPropertyDescriptors()) {
							
				try {
					if (pd.getReadMethod() != null 
							&& !"class".equalsIgnoreCase(pd.getName())
							&& !"multipartRequestHandler".equalsIgnoreCase(pd.getName())
							&& !"servlet".equalsIgnoreCase(pd.getName())
							&& !"servletWrapper".equalsIgnoreCase(pd.getName())
							&& !"pagina".equalsIgnoreCase(pd.getName())
							) {
						
						if(pd.getReadMethod().invoke(obj) != null){
							
							if(flagFirst){
								strb.append("?");
								flagFirst = false;
							} else {
								strb.append("&");
							}
							
							if (pd.getReadMethod().invoke(obj) instanceof String[]){
								String[] values = (String[]) pd.getReadMethod().invoke(obj);
								for (int i = 0; i < values.length; i++) {
									strb.append(pd.getName());
									strb.append("=");														
									strb.append(values[i]+(i==values.length-1?"":"&"));
								}
							}
							else{
								strb.append(pd.getName());
								strb.append("=");														
								strb.append(pd.getReadMethod().invoke(obj));
							}
							
							if(!"recordPerPagina".equalsIgnoreCase(pd.getName()))
								hiddenProperties.add(pd.getName());
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		
		toReturn.put("actionUrl", strb.toString());
		toReturn.put("hiddenProperties", hiddenProperties);
		
		
		return toReturn;
	}
	
	private static HashSet<String> chiaviGlobaliDaFiltare = new HashSet<String>(Arrays.asList(new String[] { "class", "multipartRequestHandler", "servlet", "servletWrapper", "pagina", "recordPerPagina" }));
	
	/* Overload */
	public static void gestioneRequestPaginazione(ActionMapping mapping, ActionForm form, HttpServletRequest request, ElencoBean elenco, HashSet<String> chiaviDaFiltrare) {
		
		PaginateForm paginateForm = (PaginateForm) form;	
		
		request.setAttribute("action", mapping.getPath());
		request.setAttribute("actionForm", mapping.getName());
		request.setAttribute("hiddenProperties", (getActionUrl(form)).get("hiddenProperties") );
    	request.setAttribute("actionUrl", mapping.getPath()+".do"+(getActionUrl(form, chiaviDaFiltrare)).get("actionUrl"));
    	request.setAttribute("numeroElementi", elenco.getNumeroElementi());
    	request.setAttribute("currentPage", paginateForm.getPagina());
    	request.setAttribute("elementsPerPage", paginateForm.getRecordPerPagina());
    	request.setAttribute("totalePagine", getTotalePagine(elenco.getNumeroElementi(), paginateForm.getRecordPerPagina()));
	}	

	/* Overload */
	public static Map<String, Object> getActionUrl(Object obj, HashSet<String> chiaviDaFiltrare){
		
		Class<?> aClass = obj.getClass();
		StringBuffer strb = new StringBuffer();
		boolean flagFirst = true;
		
		Map<String, Object> toReturn = new HashMap<String, Object>();
		List<String> hiddenProperties = new ArrayList<String>();
		
		try {
			
			for (PropertyDescriptor pd : Introspector.getBeanInfo(aClass).getPropertyDescriptors()) {
							
				try {
					if (pd.getReadMethod() != null && !chiaviGlobaliDaFiltare.contains(pd.getName()) && !chiaviDaFiltrare.contains(pd.getName())) {
						
						if(pd.getReadMethod().invoke(obj) != null){
							
							if(flagFirst){
								strb.append("?");
								flagFirst = false;
							} else {
								strb.append("&");
							}
							
							if (pd.getReadMethod().invoke(obj) instanceof String[]){
								String[] values = (String[]) pd.getReadMethod().invoke(obj);
								for (int i = 0; i < values.length; i++) {
									strb.append(pd.getName());
									strb.append("=");														
									strb.append(values[i]+(i==values.length-1?"":"&"));
								}
							}
							else{
								strb.append(pd.getName());
								strb.append("=");														
								strb.append(pd.getReadMethod().invoke(obj));
							}
							
							if(!"recordPerPagina".equalsIgnoreCase(pd.getName()))
								hiddenProperties.add(pd.getName());
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		
		toReturn.put("actionUrl", strb.toString());
		toReturn.put("hiddenProperties", hiddenProperties);
		return toReturn;
	}	
	
	
    public static String getTotalePagine(Integer totaleElementi, String recordPerPagina) {
		
    	int totalePagine;
		int itemsPerPagina = Integer.parseInt(recordPerPagina);
		
    	totalePagine = totaleElementi/itemsPerPagina;
		if(totaleElementi%itemsPerPagina != 0) totalePagine++;
		
		return String.valueOf(totalePagine);
	}

    
	public static List<Object> subList(PaginateFinder finder, List<Object> lista){
		
		if(lista==null) return null;
		
		if (finder.getPagina() == null)
			return lista;
		
		int fine = ((lista.size() > finder.getFine()) ? finder.getFine() : lista.size());
		int inizio = finder.getInizio();
		
		List<Object> toReturn = null;
		
		try{
			toReturn = lista.subList(inizio, fine);
		} catch(IllegalArgumentException e){
			 e.printStackTrace();
		} catch(IndexOutOfBoundsException e){
			 e.printStackTrace();
		}
		
		return toReturn; 
	}

    	
}
