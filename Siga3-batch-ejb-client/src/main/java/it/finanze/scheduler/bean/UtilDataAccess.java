package it.finanze.scheduler.bean;


import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import it.finanze.siga.ejb.SigaDao;
import jakarta.inject.Inject;
import jakarta.resource.spi.security.PasswordCredential;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;

import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;

import com.ibatis.common.logging.LogFactory;
import com.ibatis.sqlmap.client.SqlMapClient;
//import com.ibm.wsspi.security.auth.callback.Constants;
//import com.ibm.wsspi.security.auth.callback.WSMappingCallbackHandlerFactory;

import it.finanze.siga.service.SIGAServiceProxy;
import it.finanze.siga.util.SigaCache;

 
public class UtilDataAccess {
	
	public static String CAU = "CAU";
	public static String SIGA = "SIGA";
	//public static String MEDODI_COMUNI_SERVICE = "http://alafauce:9084/SIGAWeb/MetodiComuniService";
	//public static String MEDODI_COMUNI_SERVICE = "http://26.2.84.99:9083/SIGAWeb/MetodiComuniService";
	//public static String DELEGHE_BUSINESS_SERVICE = "http://alafauce:9084/SIGAWeb/DelegheBusinessService";
	//public static String DELEGHE_BUSINESS_SERVICE = "http://26.2.84.99:9083/SIGAWeb/DelegheBusinessService";
	//public static String AMMINISTRATORE_LOCALE = "http://scicinelli-01:9083/CauWS/services/AmmLocaleWS";
	//public static String AMMINISTRATORE_LOCALE = "";
	public static String ADMIN = "BLTBNC84C59E648O";
	public static String ADMIN_CF = "SYS_ADMIN";
	public static String AGENZIA = "EE";
	public static String AMM_LOCALE_CHARACTERISTICS = "<input><ufficio name=\"ALL\"/><admin_group name=\"E00_Admin_Ufficio\"/></input>";
	//public static String RESOURCE_CAU = "it/finanze/siga/xml/SqlMapConfigCAU.xml";
 	public static String _ldapContextFactory = "com.sun.jndi.ldap.LdapCtxFactory";
	public static String _ldapUrl = "";
	public static String DIR_PROPERTIES = "/prod/installedApps/SIGA";
	public static String DIR_CAU_PROPERTIES = "/prod/installedApps/SIGA/configFilePath";
	public static String FILE_PROPERTIES = "ini.properties";
	public static String LDAP_URL="LDAP_URL";
	private static final SqlMapClient SQLMAP_CAU;
	public static String CONTROLLO_LDAP_URL="ldapUrl";
	
	//public static String FILE_CAU_PROPERTIES = "CauParam.cfg";

	public static final String VAR_CAU_AMM_LOCALE = "END_POINT_WS_AMM_LOCALE";

 
	private static Logger logger = null;
	static Properties prop = new Properties();
//	static Properties propCAU = new Properties();
	@Inject
	static SigaDao service = null;

	 
	static {
		try {
			//service = new SIGAServiceProxy(SigaCache.isLOCAL());
			String controlloLdap = "" ; // service.getValoreCostante(CONTROLLO_LDAP_URL);
			logger = Logger.getLogger(UtilDataAccess.class);
			logger.debug("-- Inizio configurazione i-batis --");
			// LETTURA CONFIGURAZIONE PER LPAD
			prop.load(new FileInputStream(DIR_PROPERTIES+"/"+FILE_PROPERTIES));
		//	propCAU.load(new FileInputStream(DIR_CAU_PROPERTIES+"/"+FILE_CAU_PROPERTIES));
			//Reader readerCAU = Resources.getResourceAsReader("it/finanze/siga/xml/SqlMapConfigCAU.xml");
			String ldapUrl=prop.getProperty(LDAP_URL);
			//controllo se le stringhe di connessione siano uguali su db e sul file di properties
			if (ldapUrl.equals(controlloLdap))
			_ldapUrl =ldapUrl;
			
			String adminCF = prop.getProperty(ADMIN_CF);
			ADMIN = adminCF;
			
//			String ammLocale = propCAU.getProperty(VAR_CAU_AMM_LOCALE);
//			AMMINISTRATORE_LOCALE = ammLocale;
			
		//	logger.debug("-- Letto il file " + RESOURCE_CAU);
			SQLMAP_CAU = null; //SqlMapClientBuilder.buildSqlMapClient(readerCAU, prop);
			LogFactory.selectLog4JLogging();
			logger.debug("-- Fine configurazione i-batis");
		} catch (Exception e) {
			logger.error("ERRORE NELLA CONGIGURAZIONE DI I-BATIS. L'ERRORE VIENE STAMAPATO TRAMITE e.printStackTrace()");
			e.printStackTrace();
			throw new RuntimeException ("Error initializing UtilDataAccess class. Cause: " + e);
		}
	}
	
	public static SqlMapClient getSqlMapClient(String name){
		if( name.equals(CAU) ){
			return SQLMAP_CAU;
		}  
		return null;
	}
	
	
	public static DirContext getDirContext( ) throws NamingException  
	{
		PasswordCredential credentials=null;
		DirContext dctx = null;
		HashMap<String, String> ldapConfig = new LinkedHashMap<String, String>();
		ldapConfig.put("CONTEXT_FACTORY", _ldapContextFactory);
		ldapConfig.put("URL", _ldapUrl);
		LDAPBatch ldapBatch = new LDAPBatch(ldapConfig);
		try {
			if(!SigaCache.getMOD().equals("1"))
				{
					credentials = getJ2CData("LDAP_AUTH");
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		if(dctx==null){
			
			Hashtable env = new Hashtable ();
			
			env.put(Context.INITIAL_CONTEXT_FACTORY, ldapConfig.get("CONTEXT_FACTORY"));
			env.put(Context.PROVIDER_URL,  ldapConfig.get("URL"));
			// TODO MCE 4TEST ldap://10.50.4.30:10389
			env.put(Context.PROVIDER_URL,  "ldap://10.50.4.30:10389/ou=entrate,o=finanze,c=it");
			env.put("java.naming.ldap.version", "3");
			env.put("com.sun.jndi.ldap.connect.pool", "true");
			env.put("com.sun.jndi.ldap.connect.pool.maxsize", "20");
			env.put("com.sun.jndi.ldap.connect.pool.prefsize", "10");
			env.put("com.sun.jndi.ldap.connect.pool.timeout", "300000");			
			env.put(Context.SECURITY_AUTHENTICATION,"simple");
			
			/* /commentare se si abilita test
			if(!SigaCache.getMOD().equals("1"))
			{
				env.put(Context.SECURITY_PRINCIPAL,"cn="+credentials.getUserName()+",cn=Directory Administrators,o=finanze,c=it");
				env.put(Context.SECURITY_CREDENTIALS, credentials.getPassword() );
			}
			else */
			{
				/*
				 * Solo per versione di test
				 */
				env.put(Context.SECURITY_PRINCIPAL,"cn=siga_ent,cn=Directory Administrators,o=finanze,c=it");
				env.put(Context.SECURITY_CREDENTIALS, "entPwdSiga" );	 
			}
			
			//System.out.println("URL?"+ldapConfig.get("URL"));
		//	System.out.println("uname"+credentials.getUserName());
		//	System.out.println("pwd"+credentials.getPassword());

			dctx = new InitialDirContext(env);
		} 
		return dctx;	
	}

	//RECUPERO CREDENZIALI LDAP DALLA RISORSA J2C
	public static PasswordCredential getJ2CData(String j2cAlias) throws Exception {
		String methodName = "getJ2CData";
		DirContext dctx = null;
		PasswordCredential result = null;
		try {
			Map map = new HashMap();
			//map.put(Constants.MAPPING_ALIAS, j2cAlias);
			CallbackHandler callbackHandler = null ; //WSMappingCallbackHandlerFactory.getInstance().getCallbackHandler(map, null);

			LoginContext loginContext = new LoginContext("DefaultPrincipalMapping", callbackHandler);
			loginContext.login();

			Subject subject = loginContext.getSubject();
			Set credentials = subject.getPrivateCredentials();

			result = (PasswordCredential) credentials.iterator().next();

		} catch(Exception e) {
			e.printStackTrace();
 			throw new Exception("Unable to get credentials");
		}

		return result;
	}
}
