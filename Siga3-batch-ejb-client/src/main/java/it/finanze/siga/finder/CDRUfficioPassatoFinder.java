package it.finanze.siga.finder;

public class CDRUfficioPassatoFinder extends AmbitoFinder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4007909608377456685L;

	public CDRUfficioPassatoFinder(String codice_ufficio) {
		super(codice_ufficio);
	}
	
	private String codice_ufficio;
	public String getCodice_ufficio() {
		return codice_ufficio;
	}

	public void setCodice_ufficio(String codice_ufficio) {
		this.codice_ufficio = codice_ufficio;
	}


}
