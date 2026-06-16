package it.finanze.siga.bean;

import java.io.Serializable;

public class CDRBean extends BaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3124420008331788861L;
	
	private String descrizioneCDR;
	private String codiceCDR;
	private String flagDescrizione;
	private String flag;
	
	private String codiceUfficio;
	private String codiceCdrVerticeUfficio;
	private String descrizioneUfficio;
	private String codiceStruttura;
	private String codiceCdrVerticeStruttura;
	private String descrizioneStruttura;
	private String codiceCdr2LivelloGerarchico;
	private String descrizioneCdr2LivelloGerarchico;
	private String tipoUtente;
	private boolean soloAbilitazioniCAT;
	private String tipoUfficio;
	private String tipoCdR;
	private String regione;
	private String provincia;
	
	
	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public String getCodiceCdrVerticeUfficio() {
		return codiceCdrVerticeUfficio;
	}

	public void setCodiceCdrVerticeUfficio(String codiceCdrVerticeUfficio) {
		this.codiceCdrVerticeUfficio = codiceCdrVerticeUfficio;
	}

	public String getDescrizioneUfficio() {
		return descrizioneUfficio;
	}

	public void setDescrizioneUfficio(String descrizioneUfficio) {
		this.descrizioneUfficio = descrizioneUfficio;
	}

	public String getCodiceStruttura() {
		return codiceStruttura;
	}

	public void setCodiceStruttura(String codiceStruttura) {
		this.codiceStruttura = codiceStruttura;
	}

	public String getCodiceCdrVerticeStruttura() {
		return codiceCdrVerticeStruttura;
	}

	public void setCodiceCdrVerticeStruttura(String codiceCdrVerticeStruttura) {
		this.codiceCdrVerticeStruttura = codiceCdrVerticeStruttura;
	}

	public String getDescrizioneStruttura() {
		return descrizioneStruttura;
	}

	public void setDescrizioneStruttura(String descrizioneStruttura) {
		this.descrizioneStruttura = descrizioneStruttura;
	}

	public String getCodiceCdr2LivelloGerarchico() {
		return codiceCdr2LivelloGerarchico;
	}

	public void setCodiceCdr2LivelloGerarchico(String codiceCdr2LivelloGerarchico) {
		this.codiceCdr2LivelloGerarchico = codiceCdr2LivelloGerarchico;
	}

	public String getDescrizioneCdr2LivelloGerarchico() {
		return descrizioneCdr2LivelloGerarchico;
	}

	public void setDescrizioneCdr2LivelloGerarchico(
			String descrizioneCdr2LivelloGerarchico) {
		this.descrizioneCdr2LivelloGerarchico = descrizioneCdr2LivelloGerarchico;
	}

	public String getDescrizioneCDR() {
		return descrizioneCDR;
	}

	public void setDescrizioneCDR(String descrizioneCDR) {
		this.descrizioneCDR = descrizioneCDR;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlagDescrizione() {
		return flagDescrizione;
	}

	public void setFlagDescrizione(String flagDescrizione) {
		this.flagDescrizione = flagDescrizione;
	}

	public String getCodiceCDR() {
		return codiceCDR;
	}

	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}

	public String getTipoUtente() {
		return tipoUtente;
	}

	public void setTipoUtente(String tipoUtente) {
		this.tipoUtente = tipoUtente;
	}

	public boolean isSoloAbilitazioniCAT() {
		return soloAbilitazioniCAT;
	}

	public void setSoloAbilitazioniCAT(boolean soloAbilitazioniCAT) {
		this.soloAbilitazioniCAT = soloAbilitazioniCAT;
	}

	public String getTipoUfficio() {
		return tipoUfficio;
	}

	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}

	public String getTipoCdR() {
		return tipoCdR;
	}

	public void setTipoCdR(String tipoCdR) {
		this.tipoCdR = tipoCdR;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	
	

}
