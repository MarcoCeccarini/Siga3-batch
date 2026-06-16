package it.finanze.siga.finder;

import it.finanze.siga.bean.RichiestaBean;

import java.util.List;


public class InserimentoRichiesteFinder extends InserimentoRichiesteBaseFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3280670637249141746L;
	private List<RichiestaBean> listRichiesta;
	private String codiceAmbitoAplicativo; 
	private boolean flagVisibilitaAC;
	private String altroUfficioInteressato;
	private String cdrAssegnazione;

	
	public boolean isFlagVisibilitaAC() {
		return flagVisibilitaAC;
	}

	public void setFlagVisibilitaAC(boolean flagVisibilitaAC) {
		this.flagVisibilitaAC = flagVisibilitaAC;
	}

	public List<RichiestaBean> getListRichiesta() {
		return listRichiesta;
	}

	public void setListRichiesta(List<RichiestaBean> listRichiesta) {
		this.listRichiesta = listRichiesta;
	}

	public String getCodiceAmbitoAplicativo() {
		return codiceAmbitoAplicativo;
	}

	public void setCodiceAmbitoAplicativo(String codiceAmbitoAplicativo) {
		this.codiceAmbitoAplicativo = codiceAmbitoAplicativo;
	}

	public String getAltroUfficioInteressato() {
		return altroUfficioInteressato;
	}

	public void setAltroUfficioInteressato(String altroUfficioInteressato) {
		this.altroUfficioInteressato = altroUfficioInteressato;
	}

	public String getCdrAssegnazione() {
		return cdrAssegnazione;
	}

	public void setCdrAssegnazione(String cdrAssegnazione) {
		this.cdrAssegnazione = cdrAssegnazione;
	}

	
	
	
}
