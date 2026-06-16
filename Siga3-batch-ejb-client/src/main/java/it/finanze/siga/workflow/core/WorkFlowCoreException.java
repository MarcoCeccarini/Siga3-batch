package it.finanze.siga.workflow.core;

public class WorkFlowCoreException extends Exception {

	private static final long serialVersionUID = 1825849399399083800L;
	
	public static String ERR_PRESA_IN_CARICO = "La richiesta risulta presa in carico da un altro utente.";
	public static String ERR_NO_PRESA_IN_CARICO = "La richiesta non risulta presa in carico da nessun utente.";
	public static String ERR_NO_AUTORIZZATO_AUT_I = "L'utente non dispone delle autorizzazioni (AUT_I) necessarie per autorizzare la richiesta.";
	public static String ERR_NO_AUTORIZZATO_AUT_II = "L'utente non dispone delle autorizzazioni (AUT_II) necessarie per autorizzare la richiesta.";
	public static String ERR_NO_AUTORIZZATO_GESTORE = "L'utente non dispone delle autorizzazioni (GEST_OP) necessarie per autorizzare la richiesta.";
	public static String ERR_NO_OPERATORE = "L'utente non dispone delle autorizzazioni necessarie per prendere visione della richiesta.";
	public static String ERR_NO_RICHIEDENTE = "L'utente non dispone delle autorizzazioni necessarie per archiviare la richiesta.";
	public static String ERR_RICHIESTA = "Richiesta non valida.";
	public static String ERR_TIPO_RICHIESTA = "Tipo richiesta non valido.";
	
	public WorkFlowCoreException() {
	}
	
	public WorkFlowCoreException(String message) {
		super(message);
	}
	
}
