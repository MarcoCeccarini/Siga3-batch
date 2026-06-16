package it.finanze.siga.finder;

public class InterrProfiliOperatoreFinder extends BasePaginateFinder  {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -7241887476891339760L;
	/**
	 * cf utente loggato in sessione
	 */
	private String codFisUser;	
	private String idRich;
	
	/** GETTERS AND SETTERS **/

	public InterrProfiliOperatoreFinder(String codFisUser) {
		super();
		this.codFisUser = codFisUser;
	}

	public String getCodFisUser() {
		return codFisUser;
	}

	public void setCodFisUser(String codFisUser) {
		this.codFisUser = codFisUser;
	}

	public String getIdRich() {
		return idRich;
	}

	public void setIdRich(String idRich) {
		this.idRich = idRich;
	}
	
	

}
