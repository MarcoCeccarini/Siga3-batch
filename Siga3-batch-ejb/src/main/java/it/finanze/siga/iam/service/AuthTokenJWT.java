package it.finanze.siga.iam.service;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import it.finanze.siga.iam.service.AuthTokenJWT;
import it.finanze.siga.iam.service.HttpRequestUtility;

public class AuthTokenJWT {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenJWT.class);

    private static AuthTokenJWT INSTANCE;
    private String tokenUrl = null;
    private String tokenJWT = null;
    private String clientId = null;
    private String userId = null;
    private String userPassword = null;
    
    private long   tokenDate = 0;
    
    private AuthTokenJWT(String tokenUrl, String clientId, String userId, String userPassword) {
    	this.tokenUrl=tokenUrl;
    	this.clientId=clientId;
    	this.userId=userId;
    	this.userPassword=userPassword;
    }
    
    public static AuthTokenJWT getInstance(String tokenUrl, String clientId, String userId, String userPassword) {
        if(INSTANCE == null) {
        	
        	synchronized(AuthTokenJWT.class) {
        		if(INSTANCE == null) {
        			INSTANCE = new AuthTokenJWT(tokenUrl, clientId, userId, userPassword);
        		}
        	}
        }
        
        return INSTANCE;
    }

    public String getTokenJWT() throws Exception{
    	if(tokenJWT==null || !isValidToken()) {
    		tokenJWT = getJSON_REST_POST(new URL(tokenUrl));
        
    		
	        String[] chunks = tokenJWT.toString().split("\\.");
	        String payload = new String(Base64.decodeBase64(chunks[1]));
	        JsonObject jobj = new Gson().fromJson(payload, JsonObject.class);
	        tokenDate = jobj.get("exp").getAsLong();
    	}
    	
        JsonObject jsonObject = new JsonParser().parse(tokenJWT).getAsJsonObject();
               
    	return jsonObject.get("token").getAsString();
    }
    
    private boolean isValidToken(){
    	try{
    		
    		if( (tokenDate-300) > System.currentTimeMillis() / 1000L)
    			return true;
    		else
    			return false;
    		
    	}catch(Exception e){
    		return false;
    	}
    }
    
    private synchronized String getJSON_REST_POST(URL url) throws Exception{
    	if(tokenJWT==null || !isValidToken()) {  		
    		Map<String, String> attributi = new HashMap<String, String>();
    		attributi.put("accept", "application/json");
			attributi.put("content-type", "application/json; charset=utf-8");
			
			JsonObject jsonBody = new JsonObject();
			jsonBody.addProperty("clientId", clientId);
			jsonBody.addProperty("userId", userId);
			jsonBody.addProperty("userPassword", userPassword);
			jsonBody.addProperty("exp", true);	
           
            return HttpRequestUtility.post(url, attributi, jsonBody.toString());
    	}
    	else 
    		return tokenJWT;
    	
	}
     

    public static void main(String[] arg) {
    	AuthTokenJWT authToken = AuthTokenJWT.getInstance("http://26.0.155.37:3004/tokenw",  "SIGA3", "siga3Admin", "Password0");
    	try {
			System.out.println("TOKEN: "+authToken.getTokenJWT());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
