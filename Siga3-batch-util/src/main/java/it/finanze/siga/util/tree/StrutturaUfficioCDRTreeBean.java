package it.finanze.siga.util.tree;


import java.io.Serializable;
import java.util.List;

public class StrutturaUfficioCDRTreeBean extends BaseTreeBean2 implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7226485768457070875L;
	
	private String codiceStruttura;
	private String codiceUfficio;
	private String codiceCDR;
	
	private String strutturaDesc;
	private String ufficioDesc;
	private String CDRDesc;
	
	private List<StrutturaUfficioCDRTreeBean> children;
	private StrutturaUfficioCDRTreeBean padre;
	
	// per ordinamento
	private int ordinamento;
	private int livello; 
	

	@Override
	public String toString() {
		return "\nStrutturaUfficioCDRTreeBean [codiceStruttura="
				+ codiceStruttura + ", codiceUfficio=" + codiceUfficio
				+ ", codiceCDR=" + codiceCDR + ", strutturaDesc="
				+ strutturaDesc + ", ufficioDesc=" + ufficioDesc + ", CDRDesc="
				+ CDRDesc + ", ordinamento=" + ordinamento + ", children=" + children + "]";
	}


	/** GETTERS AND SETTERS **/
	public int getOrdinamento() {
		return ordinamento;
	}


	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}
	
	public List<StrutturaUfficioCDRTreeBean> getChildren() {
		return children;
	}


	public void setChildren(List<StrutturaUfficioCDRTreeBean> children) {
		this.children = children;
	}

	
	public String getCodiceStruttura() {
		return codiceStruttura;
	}
	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}
	public String getCodiceUfficio() {
		return codiceUfficio;
	}
	
	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public String getStrutturaDesc() {
		return strutturaDesc;
	}
	public void setStrutturaDesc(String strutturaDesc) {
		this.strutturaDesc = strutturaDesc;
	}
	public String getUfficioDesc() {
		return ufficioDesc;
	}
	public void setUfficioDesc(String ufficioDesc) {
		this.ufficioDesc = ufficioDesc;
	}
	public String getCDRDesc() {
		return CDRDesc;
	}
	public void setCDRDesc(String cDRDesc) {
		CDRDesc = cDRDesc;
	}


	public StrutturaUfficioCDRTreeBean getPadre() {
		return padre;
	}


	public void setPadre(StrutturaUfficioCDRTreeBean padre) {
		this.padre = padre;
	}


	public int getLivello() {
		return livello;
	}


	public void setLivello(int livello) {
		this.livello = livello;
	}

}
