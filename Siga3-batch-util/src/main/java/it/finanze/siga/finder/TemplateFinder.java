package it.finanze.siga.finder;

import it.finanze.siga.bean.ProfiloBean;
import it.finanze.siga.bean.TemplateBean;

import java.io.Serializable;
import java.util.List;

public class TemplateFinder extends BasePaginateFinder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7269138150896600128L;
	
	private String descrizione;
	private String codiceCDR;
	private String codiceAmbitoApplicativo;
	private TemplateBean template= new TemplateBean();
	private TemplateProfiloFinder templateProfiloFinder = new TemplateProfiloFinder();
	private List<ProfiloBean> lstProfiliBean;
	// 4.5.6 II 
	private String cfAmm;
	// <--
	
	public String getCodiceAmbitoApplicativo() {
		return codiceAmbitoApplicativo;
	}

	public void setCodiceAmbitoApplicativo(String codiceAmbitoApplicativo) {
		this.codiceAmbitoApplicativo = codiceAmbitoApplicativo;
	}

	public String getCodiceCDR() {
		return codiceCDR;
	}

	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public TemplateBean getTemplate() {
		return template;
	}

	public void setTemplate(TemplateBean template) {
		this.template = template;
	}

	public TemplateProfiloFinder getTemplateProfiloFinder() {
		return templateProfiloFinder;
	}

	public void setTemplateProfiloFinder(TemplateProfiloFinder templateProfiloFinder) {
		this.templateProfiloFinder = templateProfiloFinder;
	}

	public List<ProfiloBean> getLstProfiliBean() {
		return lstProfiliBean;
	}

	public void setLstProfiliBean(List<ProfiloBean> lstProfiliBean) {
		this.lstProfiliBean = lstProfiliBean;
	}

	public String getCfAmm() {
		return cfAmm;
	}

	public void setCfAmm(String cfAmm) {
		this.cfAmm = cfAmm;
	}
	
	
}
