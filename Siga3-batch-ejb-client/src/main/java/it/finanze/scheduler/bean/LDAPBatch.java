package it.finanze.scheduler.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;

public class LDAPBatch {
	
	DirContext dctx = null;

	private static Logger logger = Logger.getLogger(LDAPBatch.class);

	private HashMap<String, String> ldapConfig = new LinkedHashMap<String, String>();

	public LDAPBatch(HashMap<String, String> ldapConfig) {

		this.ldapConfig = ldapConfig;
	}

	public HashMap<String , ArrayList<String>> getUserAttributes(String codiceFiscale, String[] attributeFilter, DirContext dctx) throws NamingException

	{	
		//DirContext dctx = getDirContext();
		HashMap<String , ArrayList<String>> result = new HashMap<String, ArrayList<String>>();

		try 
		{
			
			if (dctx==null)
				dctx=UtilDataAccess.getDirContext();

			// BASE
			String base = "";//initParameters.getIniParameter("BASESEARCH_VALUE");//"ou=entrate";

			// SCOPE
			SearchControls sc = new SearchControls();
			//String[] attributeFilter = { "mail","codufficio","descCodSede","livello","sn", "givenName","initials" };
			sc.setReturningAttributes( attributeFilter );
			sc.setSearchScope( SearchControls.SUBTREE_SCOPE );

			// SEACRH FILTER
			//String filter = "(&(sn=T*))";
			String filter = "(&(cn="+codiceFiscale+"))";
			
			NamingEnumeration results = dctx.search( base, filter, sc );
			while(results.hasMore())
			{
				SearchResult sr = (SearchResult)results.next();
				Attributes attrs = sr.getAttributes();

				result = new HashMap<String , ArrayList<String>>();

				for (NamingEnumeration ae = attrs.getAll(); ae.hasMore();) 
				{
					Attribute attr = (Attribute) ae.next();

					ArrayList<String> values = new ArrayList<String>();

					for (NamingEnumeration e = attr.getAll(); e.hasMore(); ){
						values.add((String)e.next());
					}
					result.put(attr.getID(), values);

				}
			}
		 
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
			if (dctx!=null)
				try {
					dctx.close();
				} catch (NamingException e1) { e.printStackTrace();}
			//throw new Exception("Errore LDAP"+e.getMessage());
			throw e;
		}
		return result;
	}



	public HashMap<String , ArrayList<String>> getAttributes(String filter, String[] attributeFilter,  DirContext dctx) throws NamingException

	{	
		if (dctx==null)
			dctx=UtilDataAccess.getDirContext();

		HashMap<String , ArrayList<String>> result = null;	

		try 
		{
			String base = "";
			SearchControls sc = new SearchControls();
			sc.setReturningAttributes( attributeFilter );
			sc.setSearchScope( SearchControls.SUBTREE_SCOPE );
		
			NamingEnumeration results = dctx.search( base, filter, sc );
			while(results.hasMore())
			{
				SearchResult sr = (SearchResult)results.next();
				Attributes attrs = sr.getAttributes();

				result = new HashMap<String , ArrayList<String>>();

				for (NamingEnumeration ae = attrs.getAll(); ae.hasMore();) 
				{
					Attribute attr = (Attribute) ae.next();

					ArrayList<String> values = new ArrayList<String>();

					for (NamingEnumeration e = attr.getAll(); e.hasMore(); ){
						values.add((String)e.next());
					}
					result.put(attr.getID(), values);

				}
			}
			 
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
			if (dctx!=null)
				try {
					dctx.close();
				} catch (NamingException e1) {

					e.printStackTrace();
				}
			throw e;
		}
		return result;

	}



	//////////////////////////////////////////////////////////
//
//	private DirContext getDirContext() throws NamingException  
//	{
//		javax.resource.spi.security.PasswordCredential credentials=null;
//		DirContext dctx = null;
//		try {
//			credentials = getJ2CData("LDAP_AUTH");
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
// 
//		if(dctx==null){
//			
//			Hashtable env = new Hashtable ();
//			
//			env.put(Context.INITIAL_CONTEXT_FACTORY, this.ldapConfig.get("CONTEXT_FACTORY"));
//			env.put(Context.PROVIDER_URL, this.ldapConfig.get("URL"));
//			env.put("java.naming.ldap.version", "3");
//			env.put("com.sun.jndi.ldap.connect.pool", "true");
//			env.put("com.sun.jndi.ldap.connect.pool.maxsize", "20");
//			env.put("com.sun.jndi.ldap.connect.pool.prefsize", "10");
//			env.put("com.sun.jndi.ldap.connect.pool.timeout", "300000");			
//			env.put(Context.SECURITY_AUTHENTICATION,"simple");
//			env.put(Context.SECURITY_PRINCIPAL,"cn="+credentials.getUserName()+",cn=Directory Administrators,o=finanze,c=it");
//			env.put(Context.SECURITY_CREDENTIALS, credentials.getPassword() );	 
//
//			dctx = new InitialDirContext(env);
//		} 
//		return dctx;	
//	}
//
//	//RECUPERO CREDENZIALI LDAP DALLA RISORSA J2C
//	public static javax.resource.spi.security.PasswordCredential getJ2CData(String j2cAlias) throws Exception {
//		String methodName = "getJ2CData";
//		DirContext dctx = null;
//		javax.resource.spi.security.PasswordCredential result = null;
//		try {
//			Map map = new HashMap();
//			map.put(Constants.MAPPING_ALIAS, j2cAlias);
//			CallbackHandler callbackHandler = WSMappingCallbackHandlerFactory.getInstance().getCallbackHandler(map, null);
//
//			LoginContext loginContext = new LoginContext("DefaultPrincipalMapping", callbackHandler);
//			loginContext.login();
//
//			Subject subject = loginContext.getSubject();
//			Set credentials = subject.getPrivateCredentials();
//
//			result = (PasswordCredential) credentials.iterator().next();
//
//		} catch(Exception e) {
//			e.printStackTrace();
//			logger.error("APPLICATION ERROR: cannot load credentials for j2calias = " + j2cAlias);
//			throw new Exception("Unable to get credentials");
//		}
//
//		return result;
//	}

}
