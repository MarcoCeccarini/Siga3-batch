package it.finanze.siga.finder;

import it.finanze.siga.bean.IterBean;

import java.util.List;

public class CheckAutorizzatoriProfiloFinder extends CheckAutorizzatoriBaseFinder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2926812258777510890L;
	
	private List<IterBean> listIter;
	
	private String sincronizzataCAU;
	private String flag;
	

	public String getSincronizzataCAU() {
		return sincronizzataCAU;
	}

	public void setSincronizzataCAU(String sincronizzataCAU) {
		this.sincronizzataCAU = sincronizzataCAU;
	}

	public List<IterBean> getListIter() {
		return listIter;
	}

	public void setListIter(List<IterBean> listIter) {
		this.listIter = listIter;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
