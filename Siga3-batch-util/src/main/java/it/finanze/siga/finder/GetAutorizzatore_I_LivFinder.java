package it.finanze.siga.finder;


public class GetAutorizzatore_I_LivFinder extends BasePaginateFinder  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9085212060435213990L;
	/**
	 * cf utente loggato in sessione
	 */
	private String codFis;
	/**
	 * cf dell'operatore
	 */
	private String codFisOper;
	private String codiceCDR;
	
	private boolean isRichiedenteVerticeStruttura;
	private boolean isResponsabileHrRichiesta;
	private boolean isAmministratoreCentrale;
	private String[] removeCaseFromQueryAutorizzatore;
	
	/** GETTERS AND SETTERS **/
	public String getCodFisOper() {
		return codFisOper;
	}
	public void setCodFisOper(String codFisOper) {
		this.codFisOper = codFisOper;
	}
	public String getCodFis() {
		return codFis;
	}
	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public boolean isRichiedenteVerticeStruttura() {
		return isRichiedenteVerticeStruttura;
	}
	public void setRichiedenteVerticeStruttura(boolean isRichiedenteVerticeStruttura) {
		this.isRichiedenteVerticeStruttura = isRichiedenteVerticeStruttura;
	}
	public boolean isResponsabileHrRichiesta() {
		return isResponsabileHrRichiesta;
	}
	public void setResponsabileHrRichiesta(boolean isResponsabileHrRichiesta) {
		this.isResponsabileHrRichiesta = isResponsabileHrRichiesta;
	}
	public boolean isAmministratoreCentrale() {
		return isAmministratoreCentrale;
	}
	public void setAmministratoreCentrale(boolean isAmministratoreCentrale) {
		this.isAmministratoreCentrale = isAmministratoreCentrale;
	}
	public String[] getRemoveCaseFromQueryAutorizzatore() {
		return removeCaseFromQueryAutorizzatore;
	}
	public void setRemoveCaseFromQueryAutorizzatore(
			String[] removeCaseFromQueryAutorizzatore) {
		this.removeCaseFromQueryAutorizzatore = removeCaseFromQueryAutorizzatore;
	}
	

}
