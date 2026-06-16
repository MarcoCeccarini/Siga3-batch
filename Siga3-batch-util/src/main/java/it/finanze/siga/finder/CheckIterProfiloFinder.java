package it.finanze.siga.finder;

import it.finanze.siga.util.tree.ProfiloTreeBean;

import java.util.List;

public class CheckIterProfiloFinder extends CheckIterBaseProfiloFinder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2602050866933030856L;
	
	/**
	 * lista da cui andare a prendere le coppie di codice role-group e profilo
	 * da provare in combinazione con gli altri valori
	 */
	private List<ProfiloTreeBean> listProfiloTreeBean;
	
	public List<ProfiloTreeBean> getListProfiloTreeBean() {
		return listProfiloTreeBean;
	}
	public void setListProfiloTreeBean(List<ProfiloTreeBean> listProfiloTreeBean) {
		this.listProfiloTreeBean = listProfiloTreeBean;
	}
	
}
