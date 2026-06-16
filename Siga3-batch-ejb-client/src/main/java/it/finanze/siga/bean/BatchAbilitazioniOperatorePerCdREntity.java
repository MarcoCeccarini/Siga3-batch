package it.finanze.siga.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class BatchAbilitazioniOperatorePerCdREntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 790439237187216449L;
	
	private String secondoLivello;
	private String verticeStruttura;
	private String codiceStruttura;
	private String codiceCdR;
	private String codiceAmbitoApplicativo;
	private String tipoProfilazione;
	private String codiceApplicazione;
	private String codiceRoleGroup;
	private String codiceProfilo;
	private Timestamp dataElaborazione;
	private int contatore;
	
	public String getSecondoLivello() {
		return secondoLivello;
	}
	public void setSecondoLivello(String secondoLivello) {
		this.secondoLivello = secondoLivello;
	}
	public String getVerticeStruttura() {
		return verticeStruttura;
	}
	public void setVerticeStruttura(String verticeStruttura) {
		this.verticeStruttura = verticeStruttura;
	}
	public String getCodiceStruttura() {
		return codiceStruttura;
	}
	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}
	public String getCodiceCdR() {
		return codiceCdR;
	}
	public void setCodiceCdR(String codiceCdR) {
		this.codiceCdR = codiceCdR;
	}
	public String getCodiceAmbitoApplicativo() {
		return codiceAmbitoApplicativo;
	}
	public void setCodiceAmbitoApplicativo(String codiceAmbitoApplicativo) {
		this.codiceAmbitoApplicativo = codiceAmbitoApplicativo;
	}
	public String getTipoProfilazione() {
		return tipoProfilazione;
	}
	public void setTipoProfilazione(String tipoProfilazione) {
		this.tipoProfilazione = tipoProfilazione;
	}
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getCodiceProfilo() {
		return codiceProfilo;
	}
	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}
	public Timestamp getDataElaborazione() {
		return dataElaborazione;
	}
	public void setDataElaborazione(Timestamp dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}
	public int getContatore() {
		return contatore;
	}
	public void setContatore(int contatore) {
		this.contatore = contatore;
	}
	
	
	
}
