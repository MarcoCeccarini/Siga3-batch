package it.finanze.siga.finder;

public class UtenteEntrateFinder {
	private String codiceCDR;
	private double soglia;
	public double getSoglia() {
		return soglia;
	}
	public void setSoglia(double soglia) {
		this.soglia = soglia;
	}
	public String getCodiceCDR() {
		return codiceCDR;
	}
	public void setCodiceCDR(String codiceCDR) {
		this.codiceCDR = codiceCDR;
	}
}
