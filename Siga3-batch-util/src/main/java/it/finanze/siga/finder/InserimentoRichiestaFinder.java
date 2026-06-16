package it.finanze.siga.finder;

import it.finanze.siga.bean.IterBean;

import java.util.Date;
import java.util.List;


public class InserimentoRichiestaFinder extends BaseFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -360720958893271784L;
	
	private List<IterBean> listIter;
	
	private Date DATA_AUTOIRIZZAZIONE_1;
	private Date DATA_ESECUZIONE;
	
	public List<IterBean> getListIter() {
		return listIter;
	}

	public void setListIter(List<IterBean> listIter) {
		this.listIter = listIter;
	}

	public Date getDATA_AUTOIRIZZAZIONE_1() {
		return DATA_AUTOIRIZZAZIONE_1;
	}

	public void setDATA_AUTOIRIZZAZIONE_1(Date dATA_AUTOIRIZZAZIONE_1) {
		DATA_AUTOIRIZZAZIONE_1 = dATA_AUTOIRIZZAZIONE_1;
	}

	public Date getDATA_ESECUZIONE() {
		return DATA_ESECUZIONE;
	}

	public void setDATA_ESECUZIONE(Date dATA_ESECUZIONE) {
		DATA_ESECUZIONE = dATA_ESECUZIONE;
	}

}
