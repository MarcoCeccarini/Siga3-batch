package it.finanze.siga.finder;

public class OrganizzazioniFinder extends BasePaginateFinder{


	private static final long serialVersionUID = 2004754338543583964L;
	
	private String[] cdrListFiltroCau;
	private String[] cdrListFiltroSiga;
	
	public String[] getCdrListFiltroCau() {
		return cdrListFiltroCau;
	}
	public void setCdrListFiltroCau(String[] cdrListFiltroCau) {
		this.cdrListFiltroCau = cdrListFiltroCau;
	}
	public String[] getCdrListFiltroSiga() {
		return cdrListFiltroSiga;
	}
	public void setCdrListFiltroSiga(String[] cdrListFiltroSiga) {
		this.cdrListFiltroSiga = cdrListFiltroSiga;
	}
}
