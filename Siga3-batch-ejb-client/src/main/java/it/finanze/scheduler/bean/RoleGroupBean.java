package it.finanze.scheduler.bean;

import java.io.Serializable;

public class RoleGroupBean implements Serializable{


	private static final long serialVersionUID = 4523091951537435090L;
	private String codiceRoleGroup;
	private String disponibileVisibilita;
	private String idoneoVisibilita;
	private String codiceApplicazione;
	private String descrizioneRoleGroup;
	private String nota;
	private String flagNota;
	private String dataInizioVal;
	private String dataFineVal;
	
	public String getCodiceRoleGroup() {
		return codiceRoleGroup;
	}
	public void setCodiceRoleGroup(String codiceRoleGroup) {
		this.codiceRoleGroup = codiceRoleGroup;
	}
	public String getDisponibileVisibilita() {
		return disponibileVisibilita;
	}
	public void setDisponibileVisibilita(String disponibileVisibilita) {
		this.disponibileVisibilita = disponibileVisibilita;
	}
	public String getIdoneoVisibilita() {
		return idoneoVisibilita;
	}
	public void setIdoneoVisibilita(String idoneoVisibilita) {
		this.idoneoVisibilita = idoneoVisibilita;
	}
	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}
	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}
	public String getDescrizioneRoleGroup() {
		return descrizioneRoleGroup;
	}
	public void setDescrizioneRoleGroup(String descrizioneRoleGroup) {
		this.descrizioneRoleGroup = descrizioneRoleGroup;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getFlagNota() {
		return flagNota;
	}
	public void setFlagNota(String flagNota) {
		this.flagNota = flagNota;
	}
	public String getDataInizioVal() {
		return dataInizioVal;
	}
	public void setDataInizioVal(String dataInizioVal) {
		this.dataInizioVal = dataInizioVal;
	}
	public String getDataFineVal() {
		return dataFineVal;
	}
	public void setDataFineVal(String dataFineVal) {
		this.dataFineVal = dataFineVal;
	}
	
	
}
