package it.finanze.siga.finder;

/**
 * // check aut I LIV:
// input:
:IDENTIFICATIVO_ITER        1
:CF_OPERATORE_SELEZIONATO   CSQQQN71LVVV273I
:CODICE_CDR_RICHIEDENTE     C2008100
:CODICE_PROFILO_PASSATO     T01_02_VCR04
// otuput:
// 1 autorizzatore I LIV
*			
*			
// auth II LIV:
// input:
:IDENTIFICATIVO_ITER  1
:CDR_AUTORIZZ_I_LIV   E1430100
:CDR_SELEZIONATO      E1430100
// otuput:
// 1 autorizzatore II LIV
 */
public class CheckAutorizzatoriBaseFinder extends BaseFinder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2258624780153552020L;
	
	private String codFis;
	private String codFisUser;
	private String codiceCDRUser;
	private String CDRAutorizzatoreILiv;
	private String codiceCDR;
	private String flag;
	private String[] removeCaseFromQueryAutorizzatore;
	
	private boolean soloPerAbilitazioniCAT;
	private String cdROperatore;
		
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCodFis() {
		return codFis;
	}
	public void setCodFis(String codFis) {
		this.codFis = codFis;
	}
	
	public String getCodFisUser() {
		return codFisUser;
	}
	public void setCodFisUser(String codFisUser) {
		this.codFisUser = codFisUser;
	}
	public String getCodiceCDRUser() {
		return codiceCDRUser;
	}
	public void setCodiceCDRUser(String codiceCDRUser) {
		this.codiceCDRUser = codiceCDRUser;
	}
	public String getCDRAutorizzatoreILiv() {
		return CDRAutorizzatoreILiv;
	}
	public void setCDRAutorizzatoreILiv(String cDRAutorizzatoreILiv) {
		CDRAutorizzatoreILiv = cDRAutorizzatoreILiv;
	}
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
	public boolean isSoloPerAbilitazioniCAT() {
		return soloPerAbilitazioniCAT;
	}
	public void setSoloPerAbilitazioniCAT(boolean soloPerAbilitazioniCAT) {
		this.soloPerAbilitazioniCAT = soloPerAbilitazioniCAT;
	}
	public String getCdROperatore() {
		return cdROperatore;
	}
	public void setCdROperatore(String cdROperatore) {
		this.cdROperatore = cdROperatore;
	}
	public String[] getRemoveCaseFromQueryAutorizzatore() {
		return removeCaseFromQueryAutorizzatore;
	}
	public void setRemoveCaseFromQueryAutorizzatore(
			String[] removeCaseFromQueryAutorizzatore) {
		this.removeCaseFromQueryAutorizzatore = removeCaseFromQueryAutorizzatore;
	}
	
	
	
}
