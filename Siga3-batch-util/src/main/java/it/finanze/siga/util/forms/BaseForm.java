package it.finanze.siga.util.forms;

import it.sogei.eaf.action.CustomActionForm;

public class BaseForm extends CustomActionForm {

	
	private static final long serialVersionUID = -9159726020986407146L;
	protected String inputForward;
	protected String service;

	/**
	 * @return the inputForward
	 */
	public String getInputForward() {
		return inputForward;
	}

	/**
	 * @param inputForward the inputForward to set
	 */
	public void setInputForward(String inputForward) {
		this.inputForward = inputForward;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	
}
