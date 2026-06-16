package it.finanze.siga.bean;

import java.io.Serializable;

public class ComunicazioneTipoStrutturaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6828398904397141105L;
	
    private String codiceCdr;
    private String descrizione;
    private String tipoCdr;
    private String provincia;
    private String regione;
    private String codiceCdrPadreGerarchia;
    private String idMessaggio;
    private String tipologia;
    
	public String getCodiceCdr() {
		return codiceCdr;
	}
	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getTipoCdr() {
		return tipoCdr;
	}
	public void setTipoCdr(String tipoCdr) {
		this.tipoCdr = tipoCdr;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getCodiceCdrPadreGerarchia() {
		return codiceCdrPadreGerarchia;
	}
	public void setCodiceCdrPadreGerarchia(String codiceCdrPadreGerarchia) {
		this.codiceCdrPadreGerarchia = codiceCdrPadreGerarchia;
	}
	public String getIdMessaggio() {
		return idMessaggio;
	}
	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
    
	
    
    
    

}
