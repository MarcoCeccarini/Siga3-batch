package it.finanze.siga.bean;

import java.util.List;

public class GestoriOperatoriUpdateBean {

	List<GestoreOperatoriBean> insertList;
	List<GestoreOperatoriBean> delelteList;
	public List<GestoreOperatoriBean> getInsertList() {
		return insertList;
	}
	public void setInsertList(List<GestoreOperatoriBean> insertList) {
		this.insertList = insertList;
	}
	public List<GestoreOperatoriBean> getDelelteList() {
		return delelteList;
	}
	public void setDelelteList(List<GestoreOperatoriBean> delelteList) {
		this.delelteList = delelteList;
	}
	
	
}
