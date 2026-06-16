package it.finanze.scheduler.bean;

public class ChiamataWsCauOutputBean {

	private String messaggioErrore;
	private boolean esitoPositivo;
	
	public String getMessaggioErrore() {
		return messaggioErrore;
	}
	public void setMessaggioErrore(String messaggioErrore) {
		this.messaggioErrore = messaggioErrore;
	}
	public boolean isEsitoPositivo() {
		return esitoPositivo;
	}
	public void setEsitoPositivo(boolean esitoPositivo) {
		this.esitoPositivo = esitoPositivo;
	} 
	
	
}
