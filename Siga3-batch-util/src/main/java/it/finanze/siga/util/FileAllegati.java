package it.finanze.siga.util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.mail.util.ByteArrayDataSource;

import it.finanze.siga.bean.RecordAggiornamenti;

public class FileAllegati {
	
 

	public Map<String, ByteArrayDataSource> creaMapAllegatiInserimento(
			List<RecordAggiornamenti> recordAggiornati, String dirAllegati) {
		Map<String,ByteArrayDataSource> badsMap = new HashMap<String,ByteArrayDataSource>();

		FileWriter fileWriter = null;
		String COMMA_DELIMITER = ";";
		String NEW_LINE_SEPARATOR = "\n";
		
		try {
			String intestazione = "";
			fileWriter = new FileWriter(dirAllegati+"/ReportPopolamento.csv");
			fileWriter.append("Allineamento Profili - Role Group  da CAU a Siga - data elaborazione: " + getCurrentTime());
			fileWriter.append(NEW_LINE_SEPARATOR);
			intestazione = "APPLICAZIONE;PROFILO;ROLE_GROUP;DESCRIZIONE;AZIONE;NOTA";
			fileWriter.append(intestazione);
			fileWriter.append(NEW_LINE_SEPARATOR);
			if(recordAggiornati != null && recordAggiornati.size()!=0){
			    for (RecordAggiornamenti record : recordAggiornati) {
			    	fileWriter.append("\t" + record.getApplicazione());
			        fileWriter.append(COMMA_DELIMITER);
			        fileWriter.append("\t" + record.getProfilo());
			        fileWriter.append(COMMA_DELIMITER);
			        fileWriter.append("\t" + record.getRoleGroup());
			        fileWriter.append(COMMA_DELIMITER);
		            fileWriter.append(record.getDescrizione());
		            fileWriter.append(COMMA_DELIMITER);
		            fileWriter.append(record.getAzione());
		            fileWriter.append(COMMA_DELIMITER);
		            fileWriter.append(record.getNota());
			        fileWriter.append(NEW_LINE_SEPARATOR);
			    }
			}
	
		} catch (Exception e) {
		 
	        e.printStackTrace();
	    } finally {
	        try {
	        	fileWriter.flush();
		        fileWriter.close();
		    } catch (IOException e) {
		      System.out.println("Error while flushing/closing fileWriter !!!");
		        e.printStackTrace();
		    }
	    }
		try{
			File file_inco_ab = new File(dirAllegati+"/ReportPopolamento.csv"); 
			ByteArrayDataSource fileInserimentiByteArray = getByteArrayDataSourceFromFile(file_inco_ab);
			badsMap.put("fileInserimenti.csv", fileInserimentiByteArray);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return badsMap;
	}
	
	
	public static ByteArrayDataSource getByteArrayDataSourceFromFile(File file) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        FileInputStream fis = new FileInputStream(file);
        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum); 
        }
        fis.close();
        byte[] bytes = bos.toByteArray();			
	    ByteArrayDataSource ds = new ByteArrayDataSource(bytes, "application/octet-stream");
	    return ds;
	}
	
	public void eliminaFileCsv(String dirAllegati, String key){
		File file_inco_ab = new File(dirAllegati + key); 
	    file_inco_ab.delete();
	}
	
	private String getCurrentTime(){
		String currentTime = "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		currentTime = formatter.format(new Date());
		return currentTime;
	}
}
