package it.finanze.siga.finder;


public class RichDlgSessFinder extends BaseFinder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1692640789478681050L;

	/*
	public RichDlgSessFinder(String codFisUser) {
		super(codFisUser);
	}*/
	/**
	 * 
	 */
	
	private String codiceCDR;
	
	/**
	 * dati dell'utente in sessione
	 */
	private String codiceCDRUser;
	
	/**
	 * codice fiscale operatore
	 */
	private String codFis;
	private String codFisUtSessione;
	
	public String getCodFisUtSessione() {
		return codFisUtSessione;
	}

	public void setCodFisUtSessione(String codFisUtSessione) {
		this.codFisUtSessione = codFisUtSessione;
	}
	
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

}
