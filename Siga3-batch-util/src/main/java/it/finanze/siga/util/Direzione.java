package it.finanze.siga.util;

public class Direzione {

	/*stato per i filtri*/
	public static final String TUTTI = "-1";

	public static final String SICUREZZA_INF = "1";
	public static final String LUOGO_LAVORO = "2";
	public static final String PROT_DATO_PERS = "3";
	public static final String PATRIMONIO_AZ = "4";

	
	public static String getDescrizione(String direzione){
		String ritorno = "";
		
		if(direzione != null){
			if (direzione.equals("1")){
				ritorno = "Sicurezza informatica";
			} else if (direzione.equals("2")){
				ritorno = "Luogo di lavoro";
			} else if (direzione.equals("3")){
				ritorno = "Protezione dati personali";
			} else if (direzione.equals("4")){
				ritorno = "Patrimonio aziendale";
			} else {
				ritorno = null;
			}
		}
		else{
			ritorno = null;
		}
		
		return ritorno;
	}
}
