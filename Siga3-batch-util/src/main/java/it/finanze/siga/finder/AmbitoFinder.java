package it.finanze.siga.finder;


public class AmbitoFinder extends CDRFinder {
	
	public AmbitoFinder(String codFisUser) {
		super(codFisUser);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 4770858901931430257L;
	
	private String codAmbito;
	private String codiceCDR;
	
	/**
	 * dati dell'utente in sessione
	 */
	private String codiceCDRUser;
	
	
	
	/**
	 * @return the codAmbito
	 */
	public String getCodAmbito() {
		return codAmbito;
	}

	/**
	 * @param codAmbito the codAmbito to set
	 */
	public void setCodAmbito(String codAmbito) {
		this.codAmbito = codAmbito;
	}
	/**
	 * codice fiscale operatore
	 */
	private String codFis;
	
	public String getCodFis() {
		return codFis;
	}

	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}

	public String getCodiceCDRUser() {
		return codiceCDRUser;
	}

	public void setCodiceCDRUser(String codiceCDRUser) {
		this.codiceCDRUser = codiceCDRUser;
	}

	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	

	private String cdrPertinenza;
	private String cdrDescrizione;
	private String cdrPadreGerarchia;
	
	public String getCdrPertinenza() {
		return cdrPertinenza;
	}

	public void setCdrPertinenza(String cdrPertinenza) {
		this.cdrPertinenza = cdrPertinenza;
	}

	public String getCdrDescrizione() {
		return cdrDescrizione;
	}

	public void setCdrDescrizione(String cdrDescrizione) {
		this.cdrDescrizione = cdrDescrizione;
	}

	public String getCdrPadreGerarchia() {
		return cdrPadreGerarchia;
	}

	public void setCdrPadreGerarchia(String cdrPadreGerarchia) {
		this.cdrPadreGerarchia = cdrPadreGerarchia;
	}

	
	
	

}
