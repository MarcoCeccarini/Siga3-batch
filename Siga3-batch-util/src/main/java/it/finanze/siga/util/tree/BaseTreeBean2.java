package it.finanze.siga.util.tree;

import java.io.Serializable;

public class BaseTreeBean2 extends BaseTreeBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8034158695309962056L;
	
	private boolean startSelected;
	
	
	@Override
	public String toString() {
		return super.toString() + "BaseTreeBean2 [startSelected=" + startSelected + "]";
	}

	/** GETTERS AND SETTERS **/
	public boolean isStartSelected() {
		return startSelected;
	}

	public void setStartSelected(boolean startSelected) {
		this.startSelected = startSelected;
	}
	
	

}
