package it.finanze.siga.finder;


public class ProfiliAttiviUtente_x_Ufficio_di_provenienzaFinder extends BasePaginateFinder  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7154669088846716544L;
	
	private String codiceUfficio;
	
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getCodFis() {
		return codFis;
	}
	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}
	private String codFis;
	
	
/*	
	public profiliAttiviUtente_x_Ufficio_di_provenienzaFinder(String codiceCdrOperatore) {
		super();
		this.codiceCdrOperatore = codiceCdrOperatore;
	}
*/

}
