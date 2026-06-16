package it.finanze.siga.bean;

import java.io.Serializable;
import java.util.Date;

public class ProfiliAttiviUtenteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9118459428701844414L;
	
	private String nome;
	private String cognome;
	private String descrizioneCDR;
	private String cf;
	private String codApplicazione;
	private String codRoleGroup;
	private String codProfilo;
	private String codUfficio;
	private String cdr;
	private String tipoAbilitazione;
	private Integer idRichiesta;
	private Date dataScadenza;
	private Date dataInizioVal;
	private Date dataFineVal;
	private String idRichiestaVisibilita;
	private String origineAbilitazione;
	private String tipoCodUfficio;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getDescrizioneCDR() {
		return descrizioneCDR;
	}
	public void setDescrizioneCDR(String descrizioneCDR) {
		this.descrizioneCDR = descrizioneCDR;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getCodApplicazione() {
		return codApplicazione;
	}
	public void setCodApplicazione(String codApplicazione) {
		this.codApplicazione = codApplicazione;
	}
	public String getCodRoleGroup() {
		return codRoleGroup;
	}
	public void setCodRoleGroup(String codRoleGroup) {
		this.codRoleGroup = codRoleGroup;
	}
	public String getCodProfilo() {
		return codProfilo;
	}
	public void setCodProfilo(String codProfilo) {
		this.codProfilo = codProfilo;
	}
	public String getCodUfficio() {
		return codUfficio;
	}
	public void setCodUfficio(String codUfficio) {
		this.codUfficio = codUfficio;
	}
	public String getCdr() {
		return cdr;
	}
	public void setCdr(String cdr) {
		this.cdr = cdr;
	}
	public String getTipoAbilitazione() {
		return tipoAbilitazione;
	}
	public void setTipoAbilitazione(String tipoAbilitazione) {
		this.tipoAbilitazione = tipoAbilitazione;
	}
	public Integer getIdRichiesta() {
		return idRichiesta;
	}
	public void setIdRichiesta(Integer idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public Date getDataInizioVal() {
		return dataInizioVal;
	}
	public void setDataInizioVal(Date dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}
	public Date getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(Date dataFineVal) {
		this.dataFineVal = dataFineVal;
	}
	public String getIdRichiestaVisibilita() {
		return idRichiestaVisibilita;
	}
	public void setIdRichiestaVisibilita(String idRichiestaVisibilita) {
		this.idRichiestaVisibilita = idRichiestaVisibilita;
	}
	public String getOrigineAbilitazione() {
		return origineAbilitazione;
	}
	public void setOrigineAbilitazione(String origineAbilitazione) {
		this.origineAbilitazione = origineAbilitazione;
	}
	public String getTipoCodUfficio() {
		return tipoCodUfficio;
	}
	public void setTipoCodUfficio(String tipoCodUfficio) {
		this.tipoCodUfficio = tipoCodUfficio;
	}
	
	
	
	

}
