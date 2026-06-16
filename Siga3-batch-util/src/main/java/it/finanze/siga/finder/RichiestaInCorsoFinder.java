package it.finanze.siga.finder;

public class RichiestaInCorsoFinder extends AmbitoFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6786253319490806047L;

	public RichiestaInCorsoFinder(String codFisUser) {
		super(codFisUser);
	}
	
	private String codiceAmbitoApplicativo;
	private String tipoRichiesta;

	public String getCodiceAmbitoApplicativo() {
		return codiceAmbitoApplicativo;
	}

	public void setCodiceAmbitoApplicativo(String codiceAmbitoApplicativo) {
		this.codiceAmbitoApplicativo = codiceAmbitoApplicativo;
	}

	public String getTipoRichiesta() {
		return tipoRichiesta;
	}

	public void setTipoRichiesta(String tipoRichiesta) {
		this.tipoRichiesta = tipoRichiesta;
	}

}
