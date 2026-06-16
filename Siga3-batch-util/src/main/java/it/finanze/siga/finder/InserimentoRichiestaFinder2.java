package it.finanze.siga.finder;

import it.finanze.siga.bean.RichiestaBean;



public class InserimentoRichiestaFinder2 extends InserimentoRichiesteBaseFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 420443942745744508L;
	
	RichiestaBean richiesta;

	public RichiestaBean getRichiesta() {
		return richiesta;
	}

	public void setRichiesta(RichiestaBean richiesta) {
		this.richiesta = richiesta;
	}

}
