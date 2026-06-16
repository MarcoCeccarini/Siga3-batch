package it.finanze.siga.finder;


public class RichiesteProfilazioneFinder extends BasePaginateFinder  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1216717690250639746L;
	
	/**
	 * cf utente loggato in sessione
	 */
	private String cf;
	private String codice_cdr;
	
	public String getCodice_cdr() {
		return codice_cdr;
	}

	public void setCodice_cdr(String codice_cdr) {
		this.codice_cdr = codice_cdr;
	}

	public RichiesteProfilazioneFinder(String cf) {
		super();
		this.cf = cf;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

}
