package it.finanze.siga.util;

public class Stato {

	/*stato per i filtri*/
	public static final String TUTTI = "-1";

	public static final String IN_LAVORAZIONE = "0";
	public static final String ATTESA_BUDGET_DC = "1";
	public static final String ATTESA_BUDGET_DR = "2";
	public static final String ATTESA_ASSEGNAZIONE_UTENTI = "3";
	public static final String COMPILAZIONE_APERTA = "4";
	public static final String COMPILAZIONE_CHIUSA = "5";

	public static String getDescrizione(String stato){
		String ritorno = "";
		
		if(stato != null){
			if (stato.equals("0")){
				ritorno = "In lavorazione";
			} else if (stato.equals("1")){
				ritorno = "Attesa budget direzione centrale";
			} else if (stato.equals("1")){
				ritorno = "Attesa budget direzione regionale";
			} else if (stato.equals("1")){
				ritorno = "Attesa assegnazione utenti";
			} else if (stato.equals("1")){
				ritorno = "Compilazione aperta";
			} else if (stato.equals("1")){
				ritorno = "Compilazione chiusa";
			} else {
				ritorno = null;
			}
		}
		else{
			ritorno = null;
		}
		
		return ritorno;
	}
	
	public static String getValue(String stato) {
		String ritorno = "";
		
		if (stato != null) {
			if ( stato.equals(IN_LAVORAZIONE) ) {
				ritorno = "0";
			} else if ( stato.equals(ATTESA_BUDGET_DC) ) {
				ritorno = "1";
			} else if ( stato.equals(ATTESA_BUDGET_DR) ) {
				ritorno = "2";
			} else if ( stato.equals(ATTESA_ASSEGNAZIONE_UTENTI) ) {
				ritorno = "3";
			} else if ( stato.equals(COMPILAZIONE_APERTA) ) {
				ritorno = "4";
			} else if ( stato.equals(COMPILAZIONE_CHIUSA) ) {
				ritorno = "5";
			} else {
				ritorno = null;
			}
		} else {
			ritorno = null;
		}
		return ritorno;
	}
}
