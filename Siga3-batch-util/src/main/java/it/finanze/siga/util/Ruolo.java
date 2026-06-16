package it.finanze.siga.util;

import java.util.HashMap;

public class Ruolo {
	
	public static final String RICHIEDENTE		="RICHIEDENTE";
	public static final String AUTORIZZATORE_I	="AUTORIZZATORE_I";
	public static final String AUTORIZZATORE_II	="AUTORIZZATORE_II";
	
	public static final String OPERATORE		="OPERATORE";
	public static final String GESTORE			="GESTORE";
	public static final String RESPONSABILE_CDR = "RESPONSABILE_CDR";
	
	public static final String AMMINISTRATORE_CENTRALE = "AMMINISTRATORE_CENTRALE";
	public static final String AMMINISTRATORE_CENTRALE_APPLICATIVO ="AMMINISTRATORE_CENTRALE_APPLICATIVO";
	public static final String AMMINISTRATORE_REGIONALE ="AMMINISTRATORE_REGIONALE";
	public static final String AMMINISTRATORE_LOCALE = "AMMINISTRATORE_LOCALE";
	
	public static final String AUDITOR_CENTRALE_TOTALE = "AUDITOR_CENTRALE_TOTALE";
	public static final String AUDITOR_CENTRALE = "AUDITOR_CENTRALE";
	public static final String AUDITOR_REGIONALE = "AUDITOR_REGIONALE";	
	
	
	public static final String RICHIEDENTE_DESCR="RICHIEDENTE";
	public static final String RESPONSABILE_CDR_DESCR = "RESPONSABILE";
	public static final String AUTORIZZATORE_I_DESCR="AUTORIZZATORE I LIVELLO";
	public static final String AUTORIZZATORE_II_DESCR="AUTORIZZATORE II LIVELLO";
	public static final String OPERATORE_DESCR="OPERATORE";
	public static final String GESTORE_DESCR="GESTORE";
	public static final String AMMINISTRATORE_CENTRALE_DESCR = "AMMINISTRATORE CENTRALE";
	public static final String AMMINISTRATORE_CENTRALE_APPLICATIVO_DESCR ="AMMINISTRATORE CENTRALE APPL.";
	public static final String AMMINISTRATORE_REGIONALE_DESCR ="AMMINISTRATORE REGIONALE";
	public static final String AMMINISTRATORE_LOCALE_DESCR = "AMMINISTRATORE LOCALE";
	public static final String AUDITOR_CENTRALE_TOTALE_DESCR = "AUDITOR CENTRALE TOT.";
	public static final String AUDITOR_CENTRALE_DESCR = "AUDITOR CENTRALE";
	public static final String AUDITOR_REGIONALE_DESCR = "AUDITOR REGIONALE";
	
	
	public static HashMap <String,String> ruoliMap;


	private static HashMap<String, String> getRuoliMap() {
		ruoliMap = new HashMap<String, String>();
		
		ruoliMap.put("ESG_AMM_Centrale_3",AMMINISTRATORE_CENTRALE);
		ruoliMap.put("ESG_AMM_Regionale_3",AMMINISTRATORE_REGIONALE);
		ruoliMap.put("ESG_AMM_Locale_3",AMMINISTRATORE_LOCALE);
		ruoliMap.put("ESG_AUDIT_Centrale_Tot_3",AUDITOR_CENTRALE_TOTALE);
		ruoliMap.put("ESG_AUDIT_Centrale_3",AUDITOR_CENTRALE);
		ruoliMap.put("ESG_AUDIT_Regionale_3",AUDITOR_REGIONALE);
		ruoliMap.put("ESG_GEST_Operatori",GESTORE);
		
		
		return ruoliMap;
	}
	
	
	public static String getRuoloByCodice(String codice){
		
		HashMap<String, String> map = getRuoliMap();
		
		if(map.containsKey(codice)) return map.get(codice);
		
		else return "";
	}
	
	
}
