package it.finanze.siga.iam.service;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;

//import org.eclipse.emf.ecore.xml.type.internal.DataValue.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import it.finanze.scheduler.bean.CauOrgName;
import it.finanze.scheduler.bean.CauUserIdOrgName;
import it.finanze.scheduler.bean.ProfiloOrgBatchCoerenzaBean;
import it.finanze.scheduler.bean.ProfiloRoleBatchCoerenzaBean;
import it.sogei.cau.ws.util.CauParam;
//import it.sogei.cau.ws.util.Crypto;

public class ProvisioningClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProvisioningClient.class);
	
	private static final int PAGING_LIMIT = 1000;
	
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static void log(String url, Exception e) {
		LOGGER.error("ERRORE nell'invocazione dell'url: "+url);
		LOGGER.error(e.toString());
	}
	
	private static String getToken() throws Exception {

		// TODO MCE OBSOLETE
		AuthTokenJWT authToken = null; //AuthTokenJWT.getInstance(CauParam.JWT_TOKEN_URL,  CauParam.JWT_TOKEN_CLIENT_ID, CauParam.JWT_TOKEN_ADMIN, Crypto.getInstance().decrypt(CauParam.JWT_TOKEN_ADMIN_PASSWORD));
		
		return authToken.getTokenJWT();
	}
	
	private static JsonArray getData(JsonObject jsonObject) {
		return jsonObject.get("data").getAsJsonArray();
	}
	
	private static int getCount(JsonObject jsonObject) {
		
		if (jsonObject.get("data").getAsJsonArray().size()==0)
			return 0;
		
		return Integer.parseInt(jsonObject.get("metadata").getAsJsonObject().get("count").getAsString());
	}
	
	private static JsonObject queryForObject(String url) throws Exception {
		Map<String, String> attributi = new HashMap<String, String>();
		attributi.put("Authorization", "Bearer "+getToken());
		attributi.put("Accept", "*/*");
		
		URL urlObject = new URL(URLEncoder.encode(url, StandardCharsets.UTF_8));
		String output = HttpRequestUtility.get(urlObject, attributi);
		
		return new JsonParser().parse(output).getAsJsonObject();
	}
	
	private static String getString(JsonElement object) {
		return (object!=null)?object.getAsString():null;
	}
	
	private static String getStringForExplicitEntitlement(JsonElement object) {
		return (object!=null)?object.getAsString():"";
	}
	
	private static String getString(JsonElement object, int positionFrom) {
		return (object!=null)?object.getAsString().substring(positionFrom):null;
	}
	
	private static Date getDate(JsonElement object) throws ParseException {
		return (object!=null)?simpleDateFormat.parse(object.getAsString()):null;
	}
	
	public static synchronized List<String> getAdminCAUByGroupName(String groupName, String cfUtente) throws Exception {
		List<String> result = new ArrayList<String>();	
		
		int da = 0;
		int totale = 1;
		
		while (da<totale) {
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/adminsr/api/admins/adminGroups")
					.append(cfUtente==null?String.format("?filter=(eq adminGroup.adminGroupName '%s')", groupName):String.format("?filter=(and (eq admin.adminName '%s') (eq adminGroup.adminGroupName '%s'))", cfUtente, groupName))
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(admin.adminName)").toString();
			
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);

				da+=PAGING_LIMIT;

				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext())
					result.add(getString(iterator.next().getAsJsonObject().get("admin.adminName")));
			
			} catch (Exception e) {
				log(url, e);
				throw e;
			} 
		}	
		
		return result;
	}
	
	public static synchronized String getAdminCAU(String cfUtente) throws Exception {

		String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
				.append("/adminsr/api/admins/accessRules")
				.append(String.format("?filter=(and (eq accessRule.accessRuleName 'E00_ALL') (eq admin.adminName '%s'))", cfUtente))
				.append("&further=(count)")
				.append("&output=(admin)").toString();
		
		try {		
			
			if (getCount(queryForObject(url))>0)
				return "1";			
		
		} catch (Exception e) {
			log(url, e);
			throw e;
		} 
		
		return null;
	}
	
	public static synchronized int getVerificaVisibilita(String cfUtente, String cdr) throws Exception{
		
		String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
				.append("/usersr/api/users/orgs")
				.append(String.format("?filter=(and (eq user.userName '%s') (eq r1.type 'V') (eq org.orgName 'E00_%s'))", cfUtente, cdr))
				.append("&further=(count)")
				.append("&output=(r1)").toString();
				
		try {		
			return getCount(queryForObject(url));		
		} catch (Exception e) {
			log(url, e);
			throw e;
		}  
	}
	
	public static synchronized  List<CauOrgName> getAllCdrCAU(String[] cdr) throws Exception{
		List<CauOrgName> result = new ArrayList<CauOrgName>();
		
		int da = 0;
		int totale = 1;
		
		while (da<totale) {
		
			String filtroLista="";
			if (cdr!=null && cdr.length>0) {
				filtroLista="(in org.orgName ";
				
				for (int i=0;i<cdr.length; i++)
					filtroLista+="'"+cdr[i]+"'"+(i<cdr.length-1?",":"");
				
				filtroLista+=")";
			}
			
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/orgsr/api/orgs/orgs")
					.append("?filter=(and (eqke org.orgName 'E00_.*') (neq org.orgName 'E00_entrate') (neq org.orgName 'E00_EST') "+filtroLista+")")
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(org.orgName,parent.orgName)").toString();
						
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);
				
				da+=PAGING_LIMIT;
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext()) {
					JsonObject elemento = iterator.next().getAsJsonObject();
					
					CauOrgName cauOrgName = new CauOrgName();
					cauOrgName.setOrgName(getString(elemento.get("org.orgName"), 4));
					cauOrgName.setFatherOrgName(getString(elemento.get("parent.orgName"), 4));
					result.add(cauOrgName);
				}
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			} 
		}
		
		return result;
	}
	
	public static synchronized String getCdrFatherCAU(String cdr) throws Exception{
		String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
				.append("/orgsr/api/orgs/orgs")
				.append(String.format("?filter=(eq org.orgName '%s')", cdr))
				.append("&output=(parent.orgName)").toString();
		
		try {		
			JsonArray jsonArray = getData(queryForObject(url));
			
			if (jsonArray.size()>0)
				return getString(jsonArray.get(0).getAsJsonObject().get("parent.orgName")).substring(4);
			
		} catch (Exception e) {
			log(url, e);
			throw e;
		} 
		
		return null;
	}
	
	public static synchronized int countCdrCAU(String cdr) throws Exception {
		
		String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
				.append("/orgsr/api/orgs")
				.append(String.format("?filter=(eq org.orgName '%s')", cdr))
				.append("&further=(count)")
				.append("&output=(org)").toString();
		
		try {		
			return getCount(queryForObject(url));
			
		} catch (Exception e) {
			log(url, e);
			throw e;
		} 
	}
	
	public static synchronized List<CauUserIdOrgName> getUtenteCTSA (List<String> operatori) throws Exception {
		List<CauUserIdOrgName> result = new ArrayList<CauUserIdOrgName>();	
	
		for (int i=0;i<operatori.size();i++) {
			
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/usersr/api/users/orgs")
					.append("?filter=(and (neq org.orgName 'E00_entrate') (neq org.orgName 'E00_EST') (eqke org.orgName 'E00_.*') (eq r1.type 'P')(eq user.userName '"+operatori.get(i)+"'))")
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", i, PAGING_LIMIT))
					.append("&output=(user.userName, org.orgName)").toString();
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				
				
				while (iterator.hasNext()) {
					JsonObject elemento = iterator.next().getAsJsonObject();					
					CauUserIdOrgName cauUserIdOrgName = new CauUserIdOrgName();
					cauUserIdOrgName.setUserId(getString(elemento.get("user.userName")));
					cauUserIdOrgName.setOrgName(getString(elemento.get("org.orgName")));
					result.add(cauUserIdOrgName);
				}
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			}			
		}
		
		return result;
	}
	
	public static synchronized List<CauUserIdOrgName> getAllUtentiCTSA() throws Exception {
		List<CauUserIdOrgName> result = new ArrayList<CauUserIdOrgName>();
		
		CauUserIdOrgName cauUserIdOrgName1 = new CauUserIdOrgName();
					/*
					cauUserIdOrgName1.setUserId("BBTSNM68S48F205Q"); 
					cauUserIdOrgName1.setOrgName("P04215TR");
					*/cauUserIdOrgName1.setUserId("MLONTN81E19C424X"); 
					cauUserIdOrgName1.setOrgName("E00_P0904000");
			result.add(cauUserIdOrgName1);

		if(true)return result;


		int da = 0;
		int totale = 1;
		
		while (da<totale) {
		
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/usersr/api/users/orgs")
					.append("?filter=(and (neq org.orgName 'E00_entrate') (neq org.orgName 'E00_EST') (eqke org.orgName 'E00_.*') (eq r1.type 'P'))")
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(user.userName, org.orgName)").toString();
						
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);
				
				da+=PAGING_LIMIT;
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext()) {
					JsonObject elemento = iterator.next().getAsJsonObject();
					
					CauUserIdOrgName cauUserIdOrgName = new CauUserIdOrgName();
					cauUserIdOrgName.setUserId(getString(elemento.get("user.userName")));
					cauUserIdOrgName.setOrgName(getString(elemento.get("org.orgName")));
					result.add(cauUserIdOrgName);
				}
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			} 
		}
		
		return result;
	}
	
	public static synchronized int countUtenteCTSAByCF(String cf) throws Exception {

		String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
				.append("/usersr/api/users")
				.append(String.format("?filter=(eq user.userName '%s')", cf))
				.append("&further=(count)")
				.append("&output=(user.userName)").toString();
		
		try {		
			return getCount(queryForObject(url));
			
		} catch (Exception e) {
			log(url, e);
			throw e;
		} 
		
	}
	
	public static synchronized List<Map<String, String>> getUsersConnectedToProfile() throws Exception{
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		int da = 0;
		int totale = 1;
		
		while (da<totale) {
		
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/usersr/api/users/profiles") 
					.append("?filter=(eqke profile.profileName 'EGT.*')")
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(user.userName, profile.profileName, profile.profileDescription)").toString();

			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);
				
				da+=PAGING_LIMIT;
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext()) {
					JsonObject elemento = iterator.next().getAsJsonObject();
					
					Map<String, String> mappa = new HashMap<String, String>();
					mappa.put("USER_ID", getString(elemento.get("user.userName")));
					mappa.put("JC_NAME", getString(elemento.get("profile.profileName")));
					mappa.put("DESCRIPTION", getString(elemento.get("profile.profileDescription")));
					
					result.add(mappa);
				}
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			} 
		}
		
		return result;
	}
	
	public static synchronized Map<String, String> getAccountLDAP(String userId, String managedSystemName, String managedSystemType) throws Exception{
		Map<String, String> result = new HashMap<String, String>();
		
		int da = 0;
		int totale = 1;
		
		while (da<totale) {
		
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/usersr/api/accounts")
					//.append(String.format("?filter=(and (eqke account.accountName 'cn=%s.*') (eq account.managedSystemName '%s') (eq account.managedSystemType '%s'))", userId, managedSystemName, managedSystemType))
					.append(String.format("?filter=(and (eq account.cn '%s') (eq account.managedSystemName '%s') (eq account.managedSystemType '%s'))", userId, managedSystemName, managedSystemType))
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(account.mail, account.sn, account.givenName)").toString();
			
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);
				
				da+=PAGING_LIMIT;
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext()) {
					JsonObject elemento = iterator.next().getAsJsonObject();
					
					result.put("EMAIL", getString(elemento.get("account.mail")));
					result.put("COGNOME", getString(elemento.get("account.sn")));
					result.put("NOME", getString(elemento.get("account.givenName")));
				}
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			}  
		}
		
		return result;
	}
	
	public static synchronized List<String> getAccountADSenzaGruppiGENT(String accountName, String managedSystemName, String managedSystemType, String groupName) throws Exception{
		List<String> result = new ArrayList<String>();
		
		int da = 0;
		int totale = 1;
		
		while (da<totale) {
			
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/usersr/api/accounts")
					.append(String.format("?filter=(and (eqke account.accountName '%s') (eq account.managedSystemName '%s') (eq account.managedSystemType '%s'))", accountName.replaceAll("%", ".*"), managedSystemName, managedSystemType))
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(account.accountName)").toString();
			
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);
				
				da+=PAGING_LIMIT;
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext())
					result.add(getString(iterator.next().getAsJsonObject().get("account.accountName")));			
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			} 
		}
		
		da = 0;
		totale = 1;
		
		while (da<totale) {

			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/usersr/api/accounts/groups")
					.append(String.format("?filter=(and (eqke account.accountName '%s') (eqke group.groupName '%s') (eq group.managedSystemName '%s') (eq group.managedSystemType '%s'))", accountName.replaceAll("%", ".*"), groupName, managedSystemName, managedSystemType))
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(account.accountName)").toString();
				
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);
				
				da+=PAGING_LIMIT;
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext())		
					result.remove(iterator.next().getAsJsonObject().get("account.accountName").getAsString());			
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			}  
		}
		
		return result;
	}
	
	public static synchronized int existsAccount(String userId, String managedSystemName, String managedSystemType) throws Exception {
			
		String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
				.append("/usersr/api/accounts")
				.append(String.format("?filter=(and (eq account.accountName '%s') (eq account.managedSystemName '%s') (eq account.managedSystemType '%s'))", userId, managedSystemName, managedSystemType))
				.append("&further=(count)")
				.append("&output=(account)").toString();
		
		try {		
			return getCount(queryForObject(url));
		} catch (Exception e) {
			log(url, e);
			throw e;
		} 
	}
	
	public static synchronized int isAccountConnectedToGroup(String userId, String managedSystemName, String managedSystemType, String groupName) throws Exception {
		
		String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
				.append("/usersr/api/accounts/groups")
				.append(String.format("?filter=(and (eq account.accountName '%s') (eqke group.groupName '%s') (eq group.managedSystemName '%s') (eq group.managedSystemType '%s') )", userId, groupName.replaceAll("%",".*"), managedSystemName, managedSystemType))
				.append("&further=(count)")
				.append("&output=(r1)").toString();
		System.out.println(url);
		try {		
			return getCount(queryForObject(url));
			
		} catch (Exception e) {
			System.out.println(url);
			log(url, e);
			throw e;
		} 
	}
	
	public static synchronized String getGroupDescription(String groupName, String managedSystemName, String managedSystemType) throws Exception {
		
		String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
				.append("/profilesr/api/groups")
				.append(String.format("?filter=(and (eqke group.groupName '%s') (eq group.managedSystemName '%s') (eq group.managedSystemType '%s'))", groupName.replaceAll("%", ".*"), managedSystemName, managedSystemType))
				.append("&output=(group.groupDescription)").toString();
		
		try {			
			Iterator<JsonElement> iterator = getData(queryForObject(url)).iterator();
			
			while (iterator.hasNext()) 
				return getString(iterator.next().getAsJsonObject().get("group.groupDescription"));			
			
		} catch (Exception e) {
			log(url, e);
			throw e;
		} 
		
		return "";
	}
	
	public static synchronized List<Map<String, String>> getAccountGroups(String managedSystemName, String managedSystemType, String groupName) throws Exception{
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		
		int da = 0;
		int totale = 1;
		
		while (da<totale) {
			
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/usersr/api/accounts/groups")
					.append(String.format("?filter=(and (eqke group.groupName '%s') (eq group.managedSystemName '%s') (eq group.managedSystemType '%s'))", groupName.replaceAll("%", ".*"), managedSystemName, managedSystemType))
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(account.accountName, group.groupName)").toString();
			
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);
				
				da+=PAGING_LIMIT;
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext()) {
					JsonObject elemento = iterator.next().getAsJsonObject();
					
					Map<String, String> mappa = new HashMap<String, String>();
					mappa.put("ACCOUNT_NAME", getString(elemento.get("account.accountName")));
					mappa.put("GROUP_NAME", getString(elemento.get("group.groupName")));
					
					result.add(mappa);
				}
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			} 
		}
		
		return result;
	}
	
	public static synchronized int isUserGroup(String groupName, String managedSystemName, String managedSystemType) throws Exception {
		
		String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
				.append("/profilesr/api/groups")
				.append(String.format("?filter=(and (eqke group.groupName '%s') (eq group.managedSystemName '%s') (eq group.managedSystemType '%s'))", groupName.replaceAll("%", ".*"), managedSystemName, managedSystemType))
				.append("&further=(count)")
				.append("&output=(group.groupName)").toString();
		
		try {		
			return getCount(queryForObject(url));			
		} catch (Exception e) {
			log(url, e);
			throw e;
		} 
	}
	
	public static synchronized List<ProfiloOrgBatchCoerenzaBean> getListaProfiliOrgByCf(String codiceFiscale) throws Exception{
		List<ProfiloOrgBatchCoerenzaBean> result = new ArrayList<ProfiloOrgBatchCoerenzaBean>();
		
		int da = 0;
		int totale = 1;
		
		while (da<totale) {
			
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/usersr/api/users/orgs")
					.append(String.format("?filter=(and (eq user.userName '%s') (eqke org.orgName 'E00.*') (eq r1.type 'V'))", codiceFiscale))
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(org.orgName)").toString();
			
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);
				
				da+=PAGING_LIMIT;
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext()) {
					JsonObject elemento = iterator.next().getAsJsonObject();
					
					ProfiloOrgBatchCoerenzaBean item = new ProfiloOrgBatchCoerenzaBean();
					item.setCodiceCdr(getString(elemento.get("org.orgName")));
					item.setDataUpd(new Date());
					item.setVisibilita("n");
					
					result.add(item);
				}
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			} 
		}
		
		return result;
	}
	
	public static synchronized List<ProfiloRoleBatchCoerenzaBean> getListaProfiliRoleByCf(String codiceFiscale) throws Exception{
		List<ProfiloRoleBatchCoerenzaBean> result = new ArrayList<ProfiloRoleBatchCoerenzaBean>();
		
		int da = 0;
		int totale = 1;
		
		while (da<totale) {
			
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/usersr/api/users/profiles")
					.append(String.format("?filter=(and (or (eqke profile.profileName 'E.*') (eqke profile.profileName 'T.*') (neq profile.profileName 'EPS_0000100000')) (eq profile.profileType 'ROLE') (eq user.userName '%s'))", codiceFiscale))
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(profile.profileName, r1.createDate, r1.entitlement)").toString();
			
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);
				
				da+=PAGING_LIMIT;
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext()) {
					JsonObject elemento = iterator.next().getAsJsonObject();
					
					ProfiloRoleBatchCoerenzaBean item = new ProfiloRoleBatchCoerenzaBean();
					item.setCodiceProfilo(getString(elemento.get("profile.profileName")));
					item.setDataUpd(getDate(elemento.get("r1.createDate")));
					
					String expliticEntitlement = getStringForExplicitEntitlement(elemento.get("r1.entitlement")).trim();
					
					item.setExplicitEntitlement(expliticEntitlement.equals("")?null:expliticEntitlement);
					
					result.add(item);
				}
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			} 
		}
		
		return result;
	}
	
	public static synchronized List<ProfiloRoleBatchCoerenzaBean> getListaProfiliRoleByCfNew(String codiceFiscale) throws Exception{
		List<ProfiloRoleBatchCoerenzaBean> result = new ArrayList<ProfiloRoleBatchCoerenzaBean>();
		
		int da = 0;
		int totale = 1;
		
		while (da<totale) {
			
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/usersr/api/users/profiles")
					.append(String.format("?filter=(and (or (eqke profile.profileName 'E.*') (eqke profile.profileName 'T.*')) (eq profile.profileType 'ROLE') (eq user.userName '%s'))", codiceFiscale))
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(profile.profileName, profile.profileDescription, r1.entitlement, r1.createDate)").toString();
			
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);
				
				da+=PAGING_LIMIT;
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext()) {
					JsonObject elemento = iterator.next().getAsJsonObject();
					
					ProfiloRoleBatchCoerenzaBean item = new ProfiloRoleBatchCoerenzaBean();
					item.setCodiceProfilo(getString(elemento.get("profile.profileName")));
					item.setDescrizioneProfilo(getString(elemento.get("profile.profileDescription")));
					
					String expliticEntitlement = getStringForExplicitEntitlement(elemento.get("r1.entitlement")).trim();
					
					item.setExplicitEntitlement(expliticEntitlement.equals("")?null:expliticEntitlement);
					
					if (elemento.get("r1.createDate")!=null)
						item.setDataUpd(getDate(elemento.get("r1.createDate")));
					
					result.add(item);
				}
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			} 
		}
		
		return result;
	}
	
	public static synchronized List<String> getProfiliOrg(String cfUtente, String codiceCdRPartenza) throws Exception{
		List<String> result = new ArrayList<String>();
		
		int da = 0;
		int totale = 1;
		
		while (da<totale) {
			
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("/usersr/api/users/orgs")
					.append(String.format("?filter=(and (eq org.orgName 'E00_%s') (neq r1.type 'P') (eq user.userName '%s' ))", codiceCdRPartenza, cfUtente))
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(user.userName)").toString();
			
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);
				
				da+=PAGING_LIMIT;
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext()) {
					JsonObject elemento = iterator.next().getAsJsonObject();
					
					result.add(getString(elemento.get("user.userName")));
				}
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			} 
		}
		
		return result;
	}
	
	public static synchronized List<String> getProfiliRole(String cfUtente, String codiceCdRPartenza) throws Exception{
		List<String> result = new ArrayList<String>();
		
		int da = 0;
		int totale = 1;
		
		while (da<totale) {
			
			String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
					.append("usersr/api/users/profiles")
					.append(String.format("?filter=(and (eq user.userName '%s') (eqke r1.entitlement '.*%s.*'))", cfUtente, codiceCdRPartenza))
					.append(String.format("&further=(count)&paging=(skip:%d;limit:%d)", da, PAGING_LIMIT))
					.append("&output=(profile.profileName)").toString();
			
			try {		
				JsonObject jsonObject = queryForObject(url);	
				JsonArray jsonArray = getData(jsonObject);
				
				totale = getCount(jsonObject);
				
				da+=PAGING_LIMIT;
				
				Iterator<JsonElement> iterator = jsonArray.iterator();
				
				while (iterator.hasNext()) {
					JsonObject elemento = iterator.next().getAsJsonObject();
					
					result.add(getString(elemento.get("profile.profileName")));
				}
				
			} catch (Exception e) {
				log(url, e);
				throw e;
			} 
		}
		
		return result;
	}
	
	public static synchronized int verificaPresenzaProfiloECA_0000000000(String cfUtente, String codUff) throws Exception {
		
		String url = new StringBuilder(CauParam.IAM_SERVICE_BASE_URL)
				.append("/usersr/api/users/profiles")
				.append(String.format("?filter=(and (eq user.userName '%s') (eq profile.profileName 'ECA_0000000000') (eqke r1.entitlement '.*%s.*'))", cfUtente, codUff))
				.append("&further=(count)")
				.append("&output=(r1)").toString();
		
		try {		
			return getCount(queryForObject(url));
			
		} catch (Exception e) {
			log(url, e);
			throw e;
		} 
	}
	
//	private static void trustEveryone() { 
//    try { 
//            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){ 
//                    public boolean verify(String hostname, SSLSession session) { 
//                            return true; 
//                    }}); 
//            SSLContext context = SSLContext.getInstance("SSL"); 
//            context.init(null, new X509TrustManager[]{new X509TrustManager(){ 
//                    public void checkClientTrusted(X509Certificate[] chain, 
//                                    String authType) throws CertificateException {} 
//                    public void checkServerTrusted(X509Certificate[] chain, 
//                                    String authType) throws CertificateException {} 
//                    public X509Certificate[] getAcceptedIssuers() { 
//                            return new X509Certificate[0]; 
//                    }}}, new SecureRandom()); 
//            HttpsURLConnection.setDefaultSSLSocketFactory( 
//                            context.getSocketFactory()); 
//    } catch (Exception e) { // should never happen 
//            e.printStackTrace(); 
//    } 
//	}
//	public static void main(String arg[]) {
//	
//	
//	try {
//		
//		trustEveryone();
//			
//		
//		//CauParam.getInstance();
//		CauParam.JWT_TOKEN_URL="https://apicast-smac.sogei.it/tokenw/api/token/generate";
//		CauParam.IAM_SERVICE_BASE_URL="https://apicast-smac.sogei.it";
//		CauParam.JWT_TOKEN_CLIENT_ID="SIGA3";
//		CauParam.JWT_TOKEN_ADMIN="siga3Admin";
//		CauParam.JWT_TOKEN_ADMIN_PASSWORD="D29Q7BEeGHI+d25NVltxPQ==";
//		
//		List<String> utenti=new ArrayList<String>();		
//		utenti.add("NPLDRA67T22H703U");			
//		
//		Iterator itCAU=getUtenteCTSA(utenti).iterator();
//		
//		while (itCAU.hasNext()){
//			try{
//				/*CauUserIdOrgName cauUserIdOrgName = (CauUserIdOrgName)itCAU.next();
//				String userId = cauUserIdOrgName.getUserId();
//				String orgNameCAU = cauUserIdOrgName.getOrgName();
//				String orgName = orgNameCAU.substring(4);
//				System.out.println("userId? "+userId);
//				System.out.println("orgName? "+orgName);*/
//				System.out.println("AAAA");
//			}
//			catch(Exception e0)
//			{
//				e0.printStackTrace();
//			}
//		}
			
//			
////			for (ProfiloOrgBatchCoerenzaBean p:getListaProfiliOrgByCf("DVDPLA73L42E388V"))
////			System.out.println(p.getCodiceCdr()+" "+p.getVisibilita());
////			
////			for (ProfiloRoleBatchCoerenzaBean p: getListaProfiliRoleByCf("DVDPLA73L42E388V"))
////				System.out.println(p.getCodiceProfilo()+" "+p.getExplicitEntitlement());
//
////			for (ProfiloRoleBatchCoerenzaBean p: getListaProfiliRoleByCfNew("DVDPLA73L42E388V"))
////				System.out.println(p.getCodiceProfilo()+" "+p.getExplicitEntitlement());
//
//			for (String s:getProfiliOrg("DVDPLA73L42E388V", "P10042T3"))
//				System.out.println(s);
//				
////			System.out.println(getAdminCAUByGroupName("ES3_Admin_Appl", null));
////			System.out.println(getAdminCAU("CLNNCL76S30G793N"));
////			System.out.println(getVerificaVisibilita("BAINNN62L24L682G","C0401500"));
////			System.out.println(getAllCdrCAU(null).size());		
////			System.out.println(getCdrFatherCAU("E00_P17071PC"));
////			System.out.println(countCdrCAU("E00_B5703000"));
////			System.out.println(getAllUtentiCTSA().size());
////			System.out.println(getUsersConnectedToProfile());	
////			System.out.println(getAccountLDAP("LFCNDR81S19H501V", CauParam.MANAGED_SYSTEM_NAME_ODSEE, CauParam.MANAGED_SYSTEM_TYPE_ODSEE));		
////			System.out.println(existsAccount("cn=LFCNDR81S19H501V,ou=sogei,o=finanze,c=it", CauParam.MANAGED_SYSTEM_NAME_ODSEE, CauParam.MANAGED_SYSTEM_TYPE_ODSEE));	
////			
////			System.out.println(isAccountConnectedToGroup("PRNPRZ50P58H211L", CauParam.MANAGED_SYSTEM_NAME_CT, CauParam.MANAGED_SYSTEM_TYPE_CT, ""));
////			
////			System.out.println(getGroupDescription(".*D_Ele-USER_USERTYPE_user.*", CauParam.MANAGED_SYSTEM_NAME_CT, CauParam.MANAGED_SYSTEM_TYPE_CT));
////			System.out.println(getAccountGroups(CauParam.MANAGED_SYSTEM_NAME_CT, CauParam.MANAGED_SYSTEM_TYPE_CT, ".*D_Ele-USER_USERTYPE_user.*"));
////			System.out.println(isUserGroup(".*D_Ele-USER_USERTYPE_user.*", CauParam.MANAGED_SYSTEM_NAME_CT, CauParam.MANAGED_SYSTEM_TYPE_CT));
////			System.out.println(getAccountADSenzaGruppiGENT(".*ADM.*", CauParam.MANAGED_SYSTEM_NAME_CT, CauParam.MANAGED_SYSTEM_TYPE_CT,"CN=GENTGR.*"));
//			
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
