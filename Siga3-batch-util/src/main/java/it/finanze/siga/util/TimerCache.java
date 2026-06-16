package it.finanze.siga.util;

public class TimerCache {
	
	private static TimerCache instance = new TimerCache();
	
	private TimerCache(){};
	
	public static TimerCache getInstance(){
		return instance;
	}

	private static boolean LOCAL = true;
 
	private static String MOD = null;
	private static String urlWSAnagrafica = null;

	private static String AGENZIA = null;
	private static String CT_AGENZIA = null;
	private static String CT_APPLICAZIONE = null;
	
 

	public static String getMOD() {
		return MOD;
	}

	public static void setMOD(String mod) {
		MOD = mod;
	}
	public static void setUrlWSAnagrafica(String urlWSAnagrafica) {
		TimerCache.urlWSAnagrafica = urlWSAnagrafica;
	}
	public static String getUrlWSAnagrafica() {
		return urlWSAnagrafica;
	}
	public static void setAGENZIA(String aGENZIA) {
		AGENZIA = aGENZIA;
	}
	public static String getAGENZIA() {
		return AGENZIA;
	}
	public static void setCT_AGENZIA(String cT_AGENZIA) {
		CT_AGENZIA = cT_AGENZIA;
	}
	public static String getCT_AGENZIA() {
		return CT_AGENZIA;
	}
	public static void setCT_APPLICAZIONE(String cT_APPLICAZIONE) {
		CT_APPLICAZIONE = cT_APPLICAZIONE;
	}
	public static String getCT_APPLICAZIONE() {
		return CT_APPLICAZIONE;
	}

	public static boolean isLOCAL() {
		return LOCAL;
	}

	public static void setLOCAL(boolean lOCAL) {
		LOCAL = lOCAL;
	}

	 
}
