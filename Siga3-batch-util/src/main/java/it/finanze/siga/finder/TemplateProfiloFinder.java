package it.finanze.siga.finder;

import it.finanze.siga.bean.ProfiliTemplateBean;

import java.io.Serializable;

public class TemplateProfiloFinder extends BaseFinder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 221172641620787340L;
	
	private Integer idTemplate;
	private ProfiliTemplateBean profiloTemplate;

	public Integer getIdTemplate() {
		return idTemplate;
	}

	public void setIdTemplate(Integer idTemplate) {
		this.idTemplate = idTemplate;
	}

	public ProfiliTemplateBean getProfiloTemplate() {
		return profiloTemplate;
	}

	public void setProfiloTemplate(ProfiliTemplateBean profiloTemplate) {
		this.profiloTemplate = profiloTemplate;
	}
	
	

}
