package it.finanze.scheduler.bean;

import it.sogei.eaf.util.CheckException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;

public class UserDAO {

	/**
	 * @param args
	 * @throws NamingException 
	 */
	
	String ldapContextFactory = UtilDataAccess._ldapContextFactory;
	String ldapUrl = UtilDataAccess._ldapUrl;
	static LDAPBatch ldapBatch=null;

	public UserDAO( ) {
		
		HashMap<String, String> ldapConfig = new LinkedHashMap<String, String>();
		ldapConfig.put("CONTEXT_FACTORY", ldapContextFactory);
		ldapConfig.put("URL", ldapUrl);
		this.ldapBatch = new LDAPBatch(ldapConfig);
	}

	public static HashMap<String , ArrayList<String>> getUserFromLDAP(String codiceFiscale, DirContext dctx ) throws CheckException, NamingException {
		
		HashMap<String , ArrayList<String>> hashMap = null;
		String[] attributeFilter = { "cdr", "mail", "sn", "givenName", "telephoneNumber" };
		hashMap = (HashMap)ldapBatch.getUserAttributes(codiceFiscale, attributeFilter,dctx);
 
		return hashMap;
		
	}
	
public static HashMap<String , ArrayList<String>> getUserFromLDAP2(String codiceFiscale, DirContext dctx ) throws CheckException, NamingException {
		
		HashMap<String , ArrayList<String>> hashMap = null;
		String[] attributeFilter = { "cn", "cdrAssignment", "ou", "tipoResponsabile" };
		
		/// AGGIUNTA PER PROVA CONNESSIONE LDAP
		if (dctx==null)
		{
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
			//String filter = "(&(cn=GNTSNT68B12F892K*))";
			String filter = "(&(cn=NPLDRA67T22H703U*))";
			
			NamingEnumeration results = dctx.search( base, filter, sc );
			
		}
		/// FINE AGGIUNTA PER PROVA CONNESSIONE LDAP
		
		hashMap = (HashMap)ldapBatch.getUserAttributes(codiceFiscale, attributeFilter,dctx);
 
		return hashMap;
		
	}

}
