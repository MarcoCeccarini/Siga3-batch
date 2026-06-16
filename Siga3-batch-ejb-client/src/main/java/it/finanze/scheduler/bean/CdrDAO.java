package it.finanze.scheduler.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import jakarta.mail.MessagingException;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import org.apache.log4j.Logger;

public class CdrDAO {

	static Logger fileLog = Logger.getLogger(CdrDAO.class);
	String ldapContextFactory = UtilDataAccess._ldapContextFactory;
	String ldapUrl = UtilDataAccess._ldapUrl;
	static LDAPBatch ldapBatch=null;

	public CdrDAO( ) {

		HashMap<String, String> ldapConfig = new LinkedHashMap<String, String>();
		ldapConfig.put("CONTEXT_FACTORY", ldapContextFactory);
		ldapConfig.put("URL", ldapUrl);
		this.ldapBatch = new LDAPBatch(ldapConfig);
	}


	public static String getResponsabileFromLDAP( String cdr, DirContext dctx )throws NamingException {

		HashMap<String , ArrayList<String>> hashMap;

		String[] attributeFilter = { "responsabile"};

		hashMap = ldapBatch.getAttributes("(&(objectClass=mdfOrganization)(ou=" + cdr + "))", attributeFilter,dctx); // attributeFilter --> select ou=" + cdr + " --> where

		ArrayList<String> resultList=null;
		System.out.println("IN getResponsabileFromLDAP");

		if (hashMap==null){
			ArrayList <String> dest = new ArrayList <String>();
			String oggetto = "AllineamentoResponsabileCdR";
			String testoEmail = "Non e' stata trovata nessuna corrispondenza sul LDAP per il CdR " + cdr ;
			try {
				MailUtility.sendMail(dest,oggetto,testoEmail);

			} catch (MessagingException e1) {
				// TODO Blocco catch generato automaticamente
				e1.printStackTrace();
			}
		}

		if(hashMap!=null)
			resultList = (ArrayList<String>)hashMap.get("responsabile");

		String responsabile = null;
		if(resultList!=null && resultList.size()>0){
			System.out.println("resultList!=null && resultList.size()>0   getResponsabileFromLDAP");
			responsabile = resultList.get(0);
		}
		
		System.out.println("OUT getResponsabileFromLDAP");


		return responsabile;
	}

	public static HashMap <String,ArrayList<String>> getCdrAttibutesFromLDAP(String cdr, DirContext dctx)throws NamingException{

		HashMap<String , ArrayList<String>> hashMap;

		String[] attributeFilter = { "attributeCustom14", "attributeCustom15", "codSede", "codufficio", "dataInValid", "departmentNumber", "st", "ouDescription" };		
		 
		hashMap = ldapBatch.getAttributes("(&(objectClass=mdfOrganization)(ou=" + cdr + "))", attributeFilter, dctx);
		return hashMap;
	}


}
