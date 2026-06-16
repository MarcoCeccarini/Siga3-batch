package it.finanze.siga.bean;

import java.io.Serializable;

public class Explicit_entitlementBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6402660482782115262L;
	/**
	 * 
	 */
	private String explicit_entitlement;

	public String getExplicit_entitlement() {
		return explicit_entitlement;
	}

	public void setExplicit_entitlement(String explicit_entitlement) {
		this.explicit_entitlement = explicit_entitlement;
	}			
}
