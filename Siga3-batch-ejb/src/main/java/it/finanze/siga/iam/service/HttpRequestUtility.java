package it.finanze.siga.iam.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpRequestUtility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtility.class);
	
	private static final String POST = "POST";
	private static final String GET = "GET";
	
	public static String post(URL url, Map<String, String> attributi, String body) throws Exception {
		return request(POST, url, attributi, body);
	}
	
	public static String get(URL url, Map<String, String> attributi) throws Exception {
		return request(GET, url, attributi, null);
	}
	
	private static String request(String method, URL url, Map<String, String> attributi, String body) throws Exception{
    	
			HttpURLConnection conn=null;
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setReadTimeout(60 * 1000);
	        conn.setRequestMethod(method);
	        
	        for (Entry<String, String> entry:attributi.entrySet()) 
	        conn.setRequestProperty(entry.getKey(), entry.getValue());
	        
	        if (body!=null) {
		        conn.setDoOutput(true);       
		        
		        OutputStream os = conn.getOutputStream();
	            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");    
	            osw.write(body);
	           
	            osw.flush();
	            osw.close();  
	        }
	        
	        if (conn.getResponseCode() != 200) {
	        	LOGGER.error("[SERVICE-REST:"+url.toString()+"][METHOD:"+method+"][ERROR:"+conn.getResponseCode()+" - "+conn.getResponseMessage()+"]");
	            throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode() + " HTTP Error message : " + conn.getResponseMessage());
	        }
	        
	        StringBuilder responseOutput = new StringBuilder();
	        InputStreamReader in = new InputStreamReader(conn.getInputStream());
	        BufferedReader br = new BufferedReader(in);
	        String output;
	        while ((output = br.readLine()) != null)
	        	responseOutput.append(output);
	        
	        LOGGER.debug("[SERVICE:"+url.toString()+"][METHOD:\"+method+\"][OUTPUT:"+responseOutput.toString()+"]");
	        
	        
	        return responseOutput.toString();
    	
	}
}
