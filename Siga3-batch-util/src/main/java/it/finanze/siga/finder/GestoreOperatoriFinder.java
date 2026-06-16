package it.finanze.siga.finder;

import java.io.Serializable;
import java.util.List;

public class GestoreOperatoriFinder extends BaseFinder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5255162153858675963L;
	
	private String codiceApplicazione, idIter, cdrAutorizz_I_Liv, cdrAutorizz_II_Liv,
		codFisUser;

	private String codiceStruttura;
	private String verticeStruttura;
	private List<String> codiceCdrLst;
	private String codiceCDR;
	private String codicCDRutente;
	private Integer idAudit;
	private boolean inserimento;
	
	
	
	public String getCodicCDRutente() {
		return codicCDRutente;
	}

	public void setCodicCDRutente(String codicCDRutente) {
		this.codicCDRutente = codicCDRutente;
	}

	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}

	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}

	public String getIdIter() {
		return idIter;
	}

	public void setIdIter(String idIter) {
		this.idIter = idIter;
	}

	public String getCdrAutorizz_I_Liv() {
		return cdrAutorizz_I_Liv;
	}

	public void setCdrAutorizz_I_Liv(String cdrAutorizz_I_Liv) {
		this.cdrAutorizz_I_Liv = cdrAutorizz_I_Liv;
	}

	public String getCdrAutorizz_II_Liv() {
		return cdrAutorizz_II_Liv;
	}

	public void setCdrAutorizz_II_Liv(String cdrAutorizz_II_Liv) {
		this.cdrAutorizz_II_Liv = cdrAutorizz_II_Liv;
	}

	public String getCodFisUser() {
		return codFisUser;
	}

	public void setCodFisUser(String codFisUser) {
		this.codFisUser = codFisUser;
	}

	public String getCodiceStruttura() {
		return codiceStruttura;
	}

	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}

	public String getCodiceCDR() {
		return codiceCDR;
	}

	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}

	public Integer getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Integer idAudit) {
		this.idAudit = idAudit;
	}

	public boolean isInserimento() {
		return inserimento;
	}

	public void setInserimento(boolean inserimento) {
		this.inserimento = inserimento;
	}

	public String getVerticeStruttura() {
		return verticeStruttura;
	}

	public void setVerticeStruttura(String verticeStruttura) {
		this.verticeStruttura = verticeStruttura;
	}

	public List<String> getCodiceCdrLst() {
		return codiceCdrLst;
	}

	public void setCodiceCdrLst(List<String> codiceCdrLst) {
		this.codiceCdrLst = codiceCdrLst;
	}
	
	
}