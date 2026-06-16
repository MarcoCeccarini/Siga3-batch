package it.finanze.siga.finder;

import java.util.List;


public class ProfAttUte_x_Uff_di_provenienzFinder extends BasePaginateFinder  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8486473214490481443L;
	
	private String codUffDestinaz;
	private String codUffCATProvenienza;
	private String codFis;
	private String codiceAmbito;
	private String codiceCDR;
	private List<String> codiciProfiloList;
	private String profiliCatDestinazSi;
	
	public String getCodFis() {
		return codFis;
	}
	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}
	public String getCodUffDestinaz() {
		return codUffDestinaz;
	}
	public void setCodUffDestinaz(String codUffDestinaz) {
		this.codUffDestinaz = codUffDestinaz;
	}
	public String getCodUffCATProvenienza() {
		return codUffCATProvenienza;
	}
	public void setCodUffCATProvenienza(String codUffCATProvenienza) {
		this.codUffCATProvenienza = codUffCATProvenienza;
	}
	public String getCodiceAmbito() {
		return codiceAmbito;
	}
	public void setCodiceAmbito(String codiceAmbito) {
		this.codiceAmbito = codiceAmbito;
	}
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public List<String> getCodiciProfiloList() {
		return codiciProfiloList;
	}
	public void setCodiciProfiloList(List<String> codiciProfiloList) {
		this.codiciProfiloList = codiciProfiloList;
	}
	public String getProfiliCatDestinazSi() {
		return profiliCatDestinazSi;
	}
	public void setProfiliCatDestinazSi(String profiliCatDestinazSi) {
		this.profiliCatDestinazSi = profiliCatDestinazSi;
	}
	
	

}
