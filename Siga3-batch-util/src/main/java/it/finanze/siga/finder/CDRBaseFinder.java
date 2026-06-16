package it.finanze.siga.finder;

import java.util.List;

public class CDRBaseFinder extends BaseFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4619488225574771464L;
	
	private String codiceCDR;
	private String visibilita;
	private String codFisc;
	private String codiceCDRIILiv;
	
	private List<String> codiceStrutturaList;
	private List<String> codiceVerticeStrutturaList;
	private List<String> codiceUfficioList;
	private List<String> codiciCdrList;
	
	private String CDRVisibile;
	private int cdrChiusi;
	
	public String getCodiceCDR() {
		return codiceCDR;
	}

	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}

	public List<String> getCodiceStrutturaList() {
		return codiceStrutturaList;
	}

	public void setCodiceStrutturaList(List<String> codiceStrutturaList) {
		this.codiceStrutturaList = codiceStrutturaList;
	}
	public void reset(){
		codiceCDR=null;
		codiceStrutturaList=null;
		codiceUfficioList=null;
		visibilita=null;
		codiciCdrList=null;
		codiceVerticeStrutturaList = null;
	}

	public String getVisibilita() {
		return visibilita;
	}

	public void setVisibilita(String visibilita) {
		this.visibilita = visibilita;
	}

	public List<String> getCodiceUfficioList() {
		return codiceUfficioList;
	}

	public void setCodiceUfficioList(List<String> codiceUfficioList) {
		this.codiceUfficioList = codiceUfficioList;
	}

	public List<String> getCodiciCdrList() {
		return codiciCdrList;
	}

	public void setCodiciCdrList(List<String> codiciCdrList) {
		this.codiciCdrList = codiciCdrList;
	}

	public String getCodFisc() {
		return codFisc;
	}

	public void setCodFisc(String codFisc) {
		this.codFisc = codFisc;
	}

	public String getCDRVisibile() {
		return CDRVisibile;
	}

	public void setCDRVisibile(String cDRVisibile) {
		CDRVisibile = cDRVisibile;
	}

	public String getCodiceCDRIILiv() {
		return codiceCDRIILiv;
	}

	public void setCodiceCDRIILiv(String codiceCDRIILiv) {
		this.codiceCDRIILiv = codiceCDRIILiv;
	}

	public List<String> getCodiceVerticeStrutturaList() {
		return codiceVerticeStrutturaList;
	}

	public void setCodiceVerticeStrutturaList(
			List<String> codiceVerticeStrutturaList) {
		this.codiceVerticeStrutturaList = codiceVerticeStrutturaList;
	}

	public int getCdrChiusi() {
		return cdrChiusi;
	}

	public void setCdrChiusi(int cdrChiusi) {
		this.cdrChiusi = cdrChiusi;
	}
	
	

}
