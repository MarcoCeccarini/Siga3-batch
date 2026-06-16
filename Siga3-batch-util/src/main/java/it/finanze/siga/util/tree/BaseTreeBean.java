package it.finanze.siga.util.tree;

import java.io.Serializable;

import it.finanze.siga.bean.BaseBean;

public class BaseTreeBean extends BaseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5289197498563614303L;
	
	private String title;
	private boolean hideCheckbox;
	private boolean expanded;
	/**
	 * è true quando seleziono il profilo nell'albero dei profili.
	 */
	private boolean selected;
	private boolean unselectable;
	
	
	@Override
	public String toString() {
		return "\nBaseTreeBean [title=" + title + ", hideCheckbox="
				+ hideCheckbox + ", expanded=" + expanded + ", selected="
				+ selected + ", unselectable=" + unselectable + "]";
	}
	/** GETTERS AND SETTERS **/
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isHideCheckbox() {
		return hideCheckbox;
	}
	public void setHideCheckbox(boolean hideCheckbox) {
		this.hideCheckbox = hideCheckbox;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public boolean isUnselectable() {
		return unselectable;
	}
	public void setUnselectable(boolean unselectable) {
		this.unselectable = unselectable;
	}

}
