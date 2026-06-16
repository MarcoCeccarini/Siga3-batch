package it.finanze.siga.utility.properties;

import it.finanze.siga.util.Logg;
import it.finanze.siga.utility.jndi.JNDIWrapper;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesReader {

	static String dir;
	private static Logger log = Logger.getLogger(PropertiesReader.class ); 
	static {
		try {
			
			dir = (String) JNDIWrapper.getInstance().getJNDIEntry("java:comp/env/EMAIL_PATH");
			
			
			if (dir == null)
				throw new Exception("configFilePath non trovata nel web.xml");	
			
		} catch (Exception e) {
			Logg.getLogger().error(e.getMessage(), e);
		}
		
	}
	
	
	private Properties properties;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties props) {
		this.properties = props;
	}

	public PropertiesReader() {
		_init("application.properties");
	}

	public PropertiesReader(String fileProperties) {
		_init(fileProperties);
	}
	
	public PropertiesReader(String path, String fileProperties) {
		String fileName = path+"/"+fileProperties;
		try {						
			properties = new Properties();
			properties.load(new FileInputStream(fileName));
			
		} catch (Exception e) {
			Logg.getLogger().error(e.getMessage(), e);
		}
	}
	
	
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public String getProp(String key) {
		return properties.getProperty(key);
	}
	
	private void _init(String nomeProperties) {
		String fileName = dir+"/"+nomeProperties;
		try {						
			
			properties = new Properties();
			properties.load(new FileInputStream(fileName));
			
		} catch (Exception e) {
			Logg.getLogger().error(e.getMessage(), e);
		}
	}
	
}
