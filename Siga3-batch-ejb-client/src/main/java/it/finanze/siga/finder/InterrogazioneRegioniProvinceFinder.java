package it.finanze.siga.finder;

public class InterrogazioneRegioniProvinceFinder extends BasePaginateFinder{

	private static final long serialVersionUID = -7359042353592068983L;
	private String codiceRegione ;
	private String codiceProvincia;
	private String descrizioneRegione ;
	private String descrizioneProvincia;
	
	public String getCodiceRegione() {
		return codiceRegione;
	}
	public void setCodiceRegione(String codiceRegione) {
		this.codiceRegione = codiceRegione;
	}
	public String getCodiceProvincia() {
		return codiceProvincia;
	}
	public void setCodiceProvincia(String codiceProvincia) {
		this.codiceProvincia = codiceProvincia;
	}
	public String getDescrizioneRegione() {
		return descrizioneRegione;
	}
	public void setDescrizioneRegione(String descrizioneRegione) {
		this.descrizioneRegione = descrizioneRegione;
	}
	public String getDescrizioneProvincia() {
		return descrizioneProvincia;
	}
	public void setDescrizioneProvincia(String descrizioneProvincia) {
		this.descrizioneProvincia = descrizioneProvincia;
	}
	
	
}
