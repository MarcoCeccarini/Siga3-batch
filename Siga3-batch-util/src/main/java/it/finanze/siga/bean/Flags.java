package it.finanze.siga.bean;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Gli int corrispondono rispettivamente agli String.
 * Sono utilizzabili sia come costanti che come funzioni.
 * Per ogni funzione posso ricavare la String dall'int e viceversa.
 */
public class Flags {
	
	private static Map<String, Integer> functions;
	
	/* STRING */
	public final static String CDR_UTENTE_LOGGATO_STR = "CDR UTENTE LOGGATO";
	public final static String CDR_DI_CUI_E_RESPONSABILE_STR = "CDR DI CUI E` RESPONSABILE";
	public final static String CDR_DI_CUI_E_RICHIEDENTE_STR = "CDR DI CUI E` RICHIEDENTE";
	public final static String CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_STR = "CDR DI OPERATORI ASSOCIATI PUNTUALMENTE";
	
	public final static String CDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_STR = "CDR DI OPERATORI ASSOCIATI PUNTUALMENTE IN VISIBILITA`";
	public final static String CDR_DI_CUI_E_RICHIEDENTE_DELEGATO_STR = "CDR DI CUI E` RICHIEDENTE DELEGATO";
	public final static String CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO_STR = "CDR DI OPERATORI ASSOCIATI PUNTUALMENTE DELEGATO";
	public final static String CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO_STR = "CDR DI OPERATORI ASSOCIATI PUNTUALMENTE IN VISIBILITA` DELEGATO";
	
	/* INT */
	public final static int CDR_UTENTE_LOGGATO_INT = 0;
	public final static int CDR_DI_CUI_E_RESPONSABILE_INT = 1;
	public final static int CDR_DI_CUI_E_RICHIEDENTE_INT = 2;
	public final static int CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_INT = 3;
	
	public final static int CDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_INT = 4;
	public final static int CDR_DI_CUI_E_RICHIEDENTE_DELEGATO_INT = 5;
	public final static int CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO_INT = 6;
	public final static int CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO_INT = 7;	
	public static final int RICHIESTA_AC = 8;
	public static final int RICHIESTA_AUTORIZZATA_AC = 9;
	
	

	// SOLO FLAGS //
	/**  GESTORE OPERATORI **/
	public final static String CASO_1 ="CASO_1";
	public final static String CASO_2 ="CASO_2";
	public final static String CASO_3 ="CASO_3";
	public final static String CASO_4 ="CASO_4";

	
	
	static {
		functions = new java.util.HashMap<String, Integer>();
		functions.put(CDR_UTENTE_LOGGATO_STR, CDR_UTENTE_LOGGATO_INT);
		functions.put(CDR_DI_CUI_E_RESPONSABILE_STR, CDR_DI_CUI_E_RESPONSABILE_INT);
		functions.put(CDR_DI_CUI_E_RICHIEDENTE_STR, CDR_DI_CUI_E_RICHIEDENTE_INT);
		functions.put(CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_STR, CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_INT);
		functions.put(CDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_STR, CDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_INT);
		functions.put(CDR_DI_CUI_E_RICHIEDENTE_DELEGATO_STR, CDR_DI_CUI_E_RICHIEDENTE_DELEGATO_INT);
		functions.put(CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO_STR, CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO_INT);
		functions.put(CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO_STR, CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO_INT);
	}
	
	public static String getStringFunctionFromInt(int function){
		if(functions.containsValue(CDR_UTENTE_LOGGATO_STR))
			for (Entry<String, Integer> entry : functions.entrySet()) {
				if(entry.getValue().equals(CDR_UTENTE_LOGGATO_STR))
					return entry.getKey();
			}
		return "";
	}
	
	public static int getIntFunctionFromString(String function){
		if(functions.containsKey(function))
			return functions.get(function);
		else
			return -1;
	}
	
}
