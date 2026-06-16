package it.finanze.siga.finder;

import it.finanze.siga.bean.RichiestaBean;

import java.util.List;


public class InserimentoRichiesteCambioUfficioFinder extends InserimentoRichiesteBaseFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3280670637249141746L;
	
	public List<RichiestaBean> getListRichiesta() {
		return listRichiesta;
	}

	public void setListRichiesta(List<RichiestaBean> listRichiesta) {
		this.listRichiesta = listRichiesta;
	}

	private List<RichiestaBean> listRichiesta;
	
}
