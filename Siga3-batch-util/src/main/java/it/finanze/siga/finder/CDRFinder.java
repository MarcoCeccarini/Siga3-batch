package it.finanze.siga.finder;


public class CDRFinder extends BasePaginateFinder  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1216717690250639746L;
	
	/**
	 * cf utente loggato in sessione
	 */
	private String codFisUser;
	private String cdr;
	private String struttura;
	
	private boolean isProfiliOperatore;
	private boolean ufficiCATProfiliAssegnati;
	private boolean soloAbilitazioniCAT;
	private boolean soloAbilitazioni;
	private String codiceProfilo;
	private String ruolo;
	
	
	/** GETTERS AND SETTERS **/
	public String getCdr() {
		return cdr;
	}

	public void setCdr(String cdr) {
		this.cdr = cdr;
	}

	public CDRFinder(String codFisUser) {
		super();
		this.codFisUser = codFisUser;
	}

	public String getCodFisUser() {
		return codFisUser;
	}

	public void setCodFisUser(String codFisUser) {
		this.codFisUser = codFisUser;
	}

	public String getStruttura() {
		return struttura;
	}

	public void setStruttura(String struttura) {
		this.struttura = struttura;
	}

	public boolean isProfiliOperatore() {
		return isProfiliOperatore;
	}

	public void setProfiliOperatore(boolean isProfiliOperatore) {
		this.isProfiliOperatore = isProfiliOperatore;
	}

	public boolean isUfficiCATProfiliAssegnati() {
		return ufficiCATProfiliAssegnati;
	}

	public void setUfficiCATProfiliAssegnati(boolean ufficiCATProfiliAssegnati) {
		this.ufficiCATProfiliAssegnati = ufficiCATProfiliAssegnati;
	}

	public boolean isSoloAbilitazioniCAT() {
		return soloAbilitazioniCAT;
	}

	public void setSoloAbilitazioniCAT(boolean soloAbilitazioniCAT) {
		this.soloAbilitazioniCAT = soloAbilitazioniCAT;
	}

	public boolean isSoloAbilitazioni() {
		return soloAbilitazioni;
	}

	public void setSoloAbilitazioni(boolean soloAbilitazioni) {
		this.soloAbilitazioni = soloAbilitazioni;
	}

	public String getCodiceProfilo() {
		return codiceProfilo;
	}

	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	
}
