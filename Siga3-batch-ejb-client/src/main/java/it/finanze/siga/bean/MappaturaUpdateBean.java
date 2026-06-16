package it.finanze.siga.bean;

import java.util.List;

public class MappaturaUpdateBean {

	List<MappaturaProfiloUfficioBean> insertCdrUffList;
	List<MappaturaProfiloUfficioBean> insertTipoCdrUffList;
	List<MappaturaProfiloUfficioBean> deleteList;
	List<MappaturaProfiloUfficioBean> updateList;
	
	public List<MappaturaProfiloUfficioBean> getInsertCdrUffList() {
		return insertCdrUffList;
	}
	public void setInsertCdrUffList(
			List<MappaturaProfiloUfficioBean> insertCdrUffList) {
		this.insertCdrUffList = insertCdrUffList;
	}
	public List<MappaturaProfiloUfficioBean> getInsertTipoCdrUffList() {
		return insertTipoCdrUffList;
	}
	public void setInsertTipoCdrUffList(
			List<MappaturaProfiloUfficioBean> insertTipoCdrUffList) {
		this.insertTipoCdrUffList = insertTipoCdrUffList;
	}
	public List<MappaturaProfiloUfficioBean> getDeleteList() {
		return deleteList;
	}
	public void setDeleteList(List<MappaturaProfiloUfficioBean> deleteList) {
		this.deleteList = deleteList;
	}
	public List<MappaturaProfiloUfficioBean> getUpdateList() {
		return updateList;
	}
	public void setUpdateList(List<MappaturaProfiloUfficioBean> updateList) {
		this.updateList = updateList;
	}
	
}
