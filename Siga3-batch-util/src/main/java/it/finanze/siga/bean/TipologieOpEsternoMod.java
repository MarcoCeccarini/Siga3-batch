package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TipologieOpEsternoMod implements Serializable {

	private static final long serialVersionUID = 5591924000087931427L;	
	
	private List<String> codiciTipologieAdd = new ArrayList<String>();
	private List<String> codiciTipologieRemove = new ArrayList<String>();
	private List<String> listTipologieAddedDesc = new ArrayList<String>();
	private List<String> listTipologieRemovedDesc = new ArrayList<String>();
	
	public List<String> getCodiciTipologieAdd() {
		return codiciTipologieAdd;
	}
	public void setCodiciTipologieAdd(List<String> codiciTipologieAdd) {
		this.codiciTipologieAdd = codiciTipologieAdd;
	}
	public List<String> getCodiciTipologieRemove() {
		return codiciTipologieRemove;
	}
	public void setCodiciTipologieRemove(List<String> codiciTipologieRemove) {
		this.codiciTipologieRemove = codiciTipologieRemove;
	}
	public List<String> getListTipologieAddedDesc() {
		return listTipologieAddedDesc;
	}
	public void setListTipologieAddedDesc(List<String> listTipologieAddedDesc) {
		this.listTipologieAddedDesc = listTipologieAddedDesc;
	}
	public List<String> getListTipologieRemovedDesc() {
		return listTipologieRemovedDesc;
	}
	public void setListTipologieRemovedDesc(List<String> listTipologieRemovedDesc) {
		this.listTipologieRemovedDesc = listTipologieRemovedDesc;
	}


	
}