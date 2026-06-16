package it.finanze.siga.finder;

import java.util.Date;

public class AttivitaAmministratoriFinder extends BasePaginateFinder{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2950152892154509014L;
	
	private String tabAggiornata,
				   cfAmministratore,
				   nomeCognome,
				   codiceCdr;
	
	

	public String getCodiceCdr() {
		return codiceCdr;
	}

	public void setCodiceCdr(String codiceCdr) {
		this.codiceCdr = codiceCdr;
	}

	public String getNomeCognome() {
		return nomeCognome;
	}

	public void setNomeCognome(String nome, String cognome) {
		this.nomeCognome = nome + " " + cognome;
	}

	private Date   dataDa,
				   dataA;

	public String getTabAggiornata() {
		return tabAggiornata;
	}

	public void setTabAggiornata(String tabAggiornata) {
		this.tabAggiornata = tabAggiornata;
	}

	public String getCfAmministratore() {
		return cfAmministratore;
	}

	public void setCfAmministratore(String cfAmministratore) {
		this.cfAmministratore = cfAmministratore;
	}

	public Date getDataDa() {
		return dataDa;
	}

	public void setDataDa(Date dataDa) {
		this.dataDa = dataDa;
	}

	public Date getDataA() {
		return dataA;
	}

	public void setDataA(Date dataA) {
		this.dataA = dataA;
	}
	
	
		
}
