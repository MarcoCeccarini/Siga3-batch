package it.finanze.siga.util;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.slf4j.MDC;

/**
 * Per i log, estende log4j, di default usa il livello INFO, oppure accetta un livello e un messaggio
 * 
 * LIVELLI: (Dal più grave al meno grave)
 * 
 * FATAL
 * ERROR
 * WARNING
 * INFO
 * DEBUG
 * 
 * @author FA
 *
 */
public class Logg extends Logger {
	
	private static Logger logger = Logger.getLogger("it.finanze.siga");
	
	private Logg(String name) {
		super(name);
	}

	public static Logger getLogger(){
		return logger;
	}
	
	/** MESSAGI DEFAULT LIVELLO INFO **/
	public static void loggSyso(String log){
		logger.info(log);
	}
	
	public static void loggSyso(String title, String log){
		logger.info(new StringBuilder().append(title).append(": ").append(log));
	}
	
	public static void loggSysoInline(String title, String log){
		loggSyso(title, log);
	}
	
	public static void loggSysoAcapo(String title, String log){
		logger.info(new StringBuilder().append(title).append(":\n").append(log));
	}
	
	public static void loggSysoObject(Object o){
		logger.info(new StringBuilder().append("object").append(": ").append(o));
	}
	
	
	
	/** MESSAGI CON LIVELLO **/
	public static void loggSyso(String log, Priority priority){
		logger.log(priority, log);
	}
	
	public static void loggSyso(String title, String log, Priority priority){
		logger.log(priority, new StringBuilder().append(title).append(": ").append(log));
	}
	
	public static void loggSysoInline(String title, String log, Priority priority){
		loggSyso(title, log, priority);
	}
	
	public static void loggSysoAcapo(String title, String log, Priority priority){
		logger.log(priority, new StringBuilder().append(title).append(":\n").append(log));
	}
	
	public static void loggSysoObject(Object o, Priority priority){
		logger.log(priority, new StringBuilder().append("object").append(": ").append(o));
	}
	
	
	public static void setLogUser(String user) {
		try {
			MDC.put("user", user);
//			logVal.put("user", user);
		} catch (Exception ex) {
		}
	}

	public static void unsetLogUser() {
		try {
			MDC.remove("user");
//			logVal.remove("user");
		} catch (Exception ex) {
		}
	}

	public static void setLogIP(String ip) {
		try {
			MDC.put("ip", ip);
//			logVal.put("ip", ip);
		} catch (Exception ex) {
		}
	}

	public static void unsetLogIP() {
		try {
			MDC.remove("ip");
//			logVal.remove("ip");
		} catch (Exception ex) {
		}
	}

	public static void setAuthUser(String uauth) {
		try {
			MDC.put("uauth", uauth);
//			logVal.put("uauth", uauth);
		} catch (Exception ex) {
		}
	}

	public static void unsetAuthUser() {
		try {
			MDC.remove("uauth");
//			logVal.remove("uauth");
		} catch (Exception ex) {
		}
	}
	
	

}
