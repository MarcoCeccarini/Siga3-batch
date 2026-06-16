package it.finanze.siga.finder;

public class ProfiliOperatVisibFinder extends AmbitoFinder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1069260403494338083L;

	public ProfiliOperatVisibFinder(String codFisUser) {
		super(codFisUser);
	}

	private String codiceUfficio;
	private String isRevoca;

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public String getIsRevoca() {
		return isRevoca;
	}

	public void setIsRevoca(String isRevoca) {
		this.isRevoca = isRevoca;
	}
	
	
}
