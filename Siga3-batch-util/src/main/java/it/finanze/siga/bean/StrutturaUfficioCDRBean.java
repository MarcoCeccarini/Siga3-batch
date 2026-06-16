package it.finanze.siga.bean;

import java.io.Serializable;

public class StrutturaUfficioCDRBean extends BaseBean implements Serializable {
	
	private static final long serialVersionUID = -7226485768457070875L;
	
	private String codiceStruttura_II_Liv;
	private String codiceStruttura;
	private String codiceUfficio;
	private String codiceCDR;
	private String codiceCDRPertinenza; //
	private String strutturaDesc;
	private String ufficioDesc;
	private String strutturaDesc_II_Liv;
	
	// stessa desc x tutti
	private String CDRDesc;
	
	// x ordinamento
	// nuova struttura II liv
	private String confronto;
	// nuovo ufficio
	private String confronto2;
	// nuoco cdr
	private String confronto3;
	
	// per ordinamento
	private int ordinamento;
	private String verticeStruttura;
	private String verticeUfficio;
	

	@Override
	public String toString() {
		return "StrutturaUfficioCDRBean [codiceStruttura_II_Liv=" + codiceStruttura_II_Liv + ", codiceStruttura="
				+ codiceStruttura + ", codiceUfficio=" + codiceUfficio + ", codiceCDR=" + codiceCDR
				+ ", strutturaDesc=" + strutturaDesc + ", ufficioDesc=" + ufficioDesc + ", strutturaDesc_II_Liv="
				+ strutturaDesc_II_Liv + ", CDRDesc=" + CDRDesc + ", confronto=" + confronto + ", confronto2="
				+ confronto2 + ", confronto3=" + confronto3 + ", ordinamento=" + ordinamento + "]";
	}


	/** GETTERS AND SETTERS **/
	public int getOrdinamento() {
		return ordinamento;
	}


	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}
	
	public String getConfronto() {
		return confronto;
	}


	public void setConfronto(String confronto) {
		this.confronto = confronto;
	}


	public String getConfronto2() {
		return confronto2;
	}


	public void setConfronto2(String confronto2) {
		this.confronto2 = confronto2;
	}


	public String getConfronto3() {
		return confronto3;
	}


	public void setConfronto3(String confronto3) {
		this.confronto3 = confronto3;
	}
	
	public String getCodiceStruttura_II_Liv() {
		return codiceStruttura_II_Liv;
	}

	public void setCodiceStruttura_II_Liv(String codiceStruttura_II_Liv) {
		this.codiceStruttura_II_Liv = codiceStruttura_II_Liv;
	}

	public String getStrutturaDesc_II_Liv() {
		return strutturaDesc_II_Liv;
	}


	public void setStrutturaDesc_II_Liv(String strutturaDesc_II_Liv) {
		this.strutturaDesc_II_Liv = strutturaDesc_II_Liv;
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
	public String getCodiceCDRPertinenza() {
		return codiceCDRPertinenza;
	}
	public void setCodiceCDRPertinenza(String codiceCDRPertinenza) {
		this.codiceCDRPertinenza = codiceCDRPertinenza;
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


	public String getVerticeStruttura() {
		return verticeStruttura;
	}


	public void setVerticeStruttura(String verticeStruttura) {
		this.verticeStruttura = verticeStruttura;
	}


	public String getVerticeUfficio() {
		return verticeUfficio;
	}


	public void setVerticeUfficio(String verticeUfficio) {
		this.verticeUfficio = verticeUfficio;
	}
}
