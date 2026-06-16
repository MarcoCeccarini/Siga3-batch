package it.finanze.siga.ejb.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Utility {


	public static void initFileLogger(final String logFilePath,  final Logger fileLog) throws IOException{
		if(fileLog.getAppender(logFilePath)==null) {//evitiamo la moltiplicazione degli appender che scrivono pie' righe sullo stesso file di log
			String pattern = "%d{MM-dd-yyyy HH:mm:ss,SSS} %c - %M - %m%n";
			PatternLayout layout = new PatternLayout(pattern);
			FileAppender appender = new FileAppender(layout,logFilePath,false);	    
			appender.setName(logFilePath);
			fileLog.setLevel((Level) Level.DEBUG);
			fileLog.addAppender(appender);	
			fileLog.setAdditivity(false);
		}
	}

	public static String formattaData(Date data){
		String dataString = "";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dataString = df.format(data);
		return dataString;
	}

	public static String formattaDataPerExcel(Date data){
		String dataString = "";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		dataString = df.format(data);
		return dataString;
	}


	public static boolean isValidDate(String inDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	public static String getDataOraStr(Date date, String formato){
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(formato);
		String dataOra_Str = sdf.format(date);
		return dataOra_Str;

	}



}
