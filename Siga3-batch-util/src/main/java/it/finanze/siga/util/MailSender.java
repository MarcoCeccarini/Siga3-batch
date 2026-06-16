package it.finanze.siga.util;

import it.finanze.siga.bean.BatchMailBean;
import it.finanze.siga.utility.properties.PropertiesReader;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.activation.DataHandler;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class MailSender {
	
	private static String ENCODING = Charsets.ISO_8859_1.toString();
	private static String DIR_PROPERTIES = "/prod/installedApps/SIGA/AppProperties";
	private static String FILE_PROPERTIES = "mail.properties";
	private static String MAIL_MITTENTE = "mail.mittente";
	private static String MAIL_TEST = "mail.test";
	private static String MAIL_CCN = "mail.ccn";
	private static String DIR_TEMPLATE_PROPERTIES = DIR_PROPERTIES + "/template";
	static Logger fileLog = Logger.getLogger(MailSender.class);

	
	/**
	 * Invia una mail (Il mail smtp host e' in mail.properties)
	 * @param recipients: destinatari
	 * @param subject: oggetto della mail
	 * @param template: nome del file del template
	 * @param paramereters
	 * @return
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static boolean send(List<String> recipients,
			List<String> recipients_cc,String subject, String template, 
			HashMap<String,String> parameters) {
		
		PropertiesReader pr = new PropertiesReader(DIR_PROPERTIES, FILE_PROPERTIES);
		Properties properties = pr.getProperties();		

	    // Mittente
	    String from = properties.getProperty(MAIL_MITTENTE);

	
	    // Get the default Session object.
	    Session session = Session.getDefaultInstance(properties);
	    
	    try{
	       // Create a default MimeMessage object.
	       MimeMessage message = new MimeMessage(session);
	
	       // Set From: header field of the header.
	       message.setFrom(new InternetAddress(from));
	       
			if (!SigaCache.getMOD().equals("3")){
				StringBuilder sb = new StringBuilder();
				if ( recipients.size()>0){
					sb.append("<strong>A:</strong> ");
					for (int i = 0; i < recipients.size(); i++) {
						sb.append("<em>"+recipients.get(i)+"</em>").append("; ");
					}
					sb.append("<br />");
				}
				sb.append("<br />");
				parameters.put("TEST_A_CC", sb.toString());
				recipients = new ArrayList<String>(Arrays.asList(properties.getProperty(MAIL_TEST, "alafauce@sogei.it, " +
						"ptorricelli@sogei.it").split("\\s*,\\s*")));
			}
			else{
				parameters.put("TEST_A_CC", "");
			}
       
	       
	       for (Iterator<String> iterator = recipients.iterator(); iterator.hasNext();) {
	    	   String to = (String) iterator.next();
			   // Set To: header field of the header.
			   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	       }
	       // IN CC
	       for (Iterator<String> iterator = recipients_cc.iterator(); iterator.hasNext();) {
	    	   String cc = (String) iterator.next();
			   message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
	       } 
	       
	       
	       String ccn=properties.getProperty(MAIL_CCN);
	       if (ccn!=null && !ccn.equals("") && subject.equals("mail.oggetto.richiestaAbilitazione.iter"))
		   message.addRecipient(Message.RecipientType.BCC,new InternetAddress(ccn));

	       message.setContent(getValue(parameters, FileUtils.readFileToString(
	    		   new File(DIR_TEMPLATE_PROPERTIES + "/" + template), ENCODING)), "text/html");
	       // Set Subject: header field
	       message.setSubject(subject);
	
	       // Now set the actual message
	       //message.setText(text);
	
	       // Send message
	       Transport.send(message);
	       fileLog.info("Sent message successfully....");
	       
	    }catch (MessagingException mex) {
	       mex.printStackTrace();
	       return false;
	    } catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	    return true;    
	}
	
	public static boolean send(List<String> recipients,
			List<String> recipients_cc,String subject, String template, 
			HashMap<String,String> parameters, String sender) {
		
		PropertiesReader pr = new PropertiesReader(DIR_PROPERTIES, FILE_PROPERTIES);
		Properties properties = pr.getProperties();		

	    // Get the default Session object.
	    Session session = Session.getDefaultInstance(properties);
	
	    try{
	       // Create a default MimeMessage object.
	       MimeMessage message = new MimeMessage(session);
	
	       // Set From: header field of the header.
	       message.setFrom(new InternetAddress(sender));
	       
			if (!SigaCache.getMOD().equals("3")){
				StringBuilder sb = new StringBuilder();
				if ( recipients.size()>0){
					sb.append("<strong>A:</strong> ");
					for (int i = 0; i < recipients.size(); i++) {
						sb.append("<em>"+recipients.get(i)+"</em>").append("; ");
					}
					sb.append("<br />");
				}
				sb.append("<br />");
				parameters.put("TEST_A_CC", sb.toString());
				recipients = new ArrayList<String>(Arrays.asList(properties.getProperty(MAIL_TEST, "alafauce@sogei.it, " +
						"ptorricelli@sogei.it").split("\\s*,\\s*")));
			}
			else{
				parameters.put("TEST_A_CC", "");
			}
       
	       
	       for (Iterator<String> iterator = recipients.iterator(); iterator.hasNext();) {
	    	   String to = (String) iterator.next();
			   // Set To: header field of the header.
			   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	       }
	       // IN CC
	       for (Iterator<String> iterator = recipients_cc.iterator(); iterator.hasNext();) {
	    	   String cc = (String) iterator.next();
			   message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
	       } 

	       message.setContent(getValue(parameters, FileUtils.readFileToString(
	    		   new File(DIR_TEMPLATE_PROPERTIES + "/" + template), ENCODING)), "text/html");
	       // Set Subject: header field
	       message.setSubject(subject);
	
	       // Now set the actual message
	       //message.setText(text);
	
	       // Send message
	       Transport.send(message);
	       fileLog.info("Sent message successfully....");
	       
	    }catch (MessagingException mex) {
	       mex.printStackTrace();
	       return false;
	    } catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	    return true;    
	}
	
	private static String getValue(HashMap<String,String> hm, String stringa) throws SQLException{
		Matcher m = Pattern.compile("\\$\\{([a-zA-Z_0-9]+)\\}").matcher(stringa);
		StringBuffer buffer = new StringBuffer();
		while(m.find()){
			String value = hm.get(m.group(1));
			m.appendReplacement(buffer, value != null ? Matcher.quoteReplacement(value): "");
		}
		m.appendTail(buffer);
		return buffer.toString();
	}
	
	
	public static boolean sendWithAttachment(List<String> recipients,String subject, String template, HashMap<String,String> parameters,Map<String, ByteArrayDataSource> badsMap) {
		
		PropertiesReader pr = new PropertiesReader(DIR_PROPERTIES, FILE_PROPERTIES);
		Properties properties = pr.getProperties();
	    // Mittente
	    String from = properties.getProperty(MAIL_MITTENTE);	
	    // Get the default Session object.
	    Session session = Session.getDefaultInstance(properties);
	
	    try{
	       // Create a default MimeMessage object.
	       MimeMessage message = new MimeMessage(session);
	       // Set From: header field of the header.
	       message.setFrom(new InternetAddress(from));	       
	       for (Iterator<String> iterator = recipients.iterator(); iterator.hasNext();) {
	    	   String to = (String) iterator.next();
			   // Set To: header field of the header.
			   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	       }
	       // Set Subject: header field
	       message.setSubject(subject);	       
	       
	       Multipart multipart = new MimeMultipart();
	       
	       // creates body part for the message
	       MimeBodyPart messageBodyPart = new MimeBodyPart();
	       messageBodyPart.setContent(getValue(parameters, FileUtils.readFileToString(new File(DIR_TEMPLATE_PROPERTIES + "/" + template), ENCODING)), "text/html");
	       	       
	       // adds parts to the multipart
	       multipart.addBodyPart(messageBodyPart);
	       

	       // allega
	       for (Entry<String, ByteArrayDataSource> entry : badsMap.entrySet()) {
	    	    String key = entry.getKey();
	    	    ByteArrayDataSource value = entry.getValue();	  
		    	MimeBodyPart attachPart = new MimeBodyPart();
			    attachPart.setDataHandler(new DataHandler(value));
			    attachPart.setFileName(key);
			    multipart.addBodyPart(attachPart);
	    	}
	
	       // sets the multipart as message's content
	       message.setContent(multipart);
	       
	       // Send message
	       Transport.send(message);
	       fileLog.info("Sent message successfully....");

	    }catch (MessagingException mex) {
	       mex.printStackTrace();
	       return false;
	    } catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	    return true;    
	}
	
	public static boolean sendSingleTrasnaction(List<BatchMailBean> senderList) {
		
		PropertiesReader pr = new PropertiesReader(DIR_PROPERTIES, FILE_PROPERTIES);
		Properties properties = pr.getProperties();
		 // Mittente
	    String from = properties.getProperty(MAIL_MITTENTE);
		
		
		Session session = Session.getDefaultInstance(properties);
		Transport tr;
		try {
			tr = session.getTransport();
			tr.connect();
			fileLog.info("Inizio invio elenco delle mail");
			for (Iterator<BatchMailBean> iterator = senderList.iterator(); iterator.hasNext();) {
				BatchMailBean sender = (BatchMailBean) iterator.next();
				try{
					fileLog.info("Inizio elaborazione singola email");
			       // Create a default MimeMessage object.
			       MimeMessage message = new MimeMessage(session);
			
			       // Set From: header field of the header.
			       message.setFrom(new InternetAddress(from));
			       
					if (!SigaCache.getMOD().equals("3")){
						StringBuilder sb = new StringBuilder();
						if ( sender.a.size()>0){
							sb.append("<strong>A:</strong> ");
							for (int i = 0; i < sender.a.size(); i++) {
								sb.append("<em>"+sender.a.get(i)+"</em>").append("; ");
							}
							sb.append("<br />");
						}
						sb.append("<br />");
						sender.param.put("TEST_A_CC", sb.toString());
						sender.a = new ArrayList<String>(Arrays.asList(properties.getProperty(MAIL_TEST, "alafauce@sogei.it, " +
								"ptorricelli@sogei.it").split("\\s*,\\s*")));
					}
					else{
						sender.param.put("TEST_A_CC", "");
					}
		       
					List<String> destinatari = sender.a;
			       for (Iterator<String> iterator_2 = destinatari.iterator(); iterator_2.hasNext();) {
			    	   String to = (String) iterator_2.next();
					   // Set To: header field of the header.
					   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			       }
			       // IN CC
			       for (Iterator<String> iterator_2 = sender.cc.iterator(); iterator_2.hasNext();) {
			    	   String cc = (String) iterator_2.next();
					   message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
			       } 
			       fileLog.info("Creazione del contenuto della mail");
			       message.setContent(getValue(sender.param, FileUtils.readFileToString(new File(DIR_TEMPLATE_PROPERTIES + "/" + properties.getProperty(sender.template)), ENCODING)), "text/html");
			       // Set Subject: header field
			       message.setSubject(properties.getProperty(sender.oggetto));
			
			       // Now set the actual message
			       //message.setText(text);
			
			       // Send message
			       fileLog.info("Inizio spedizione mail");
			       tr.send(message);
			       fileLog.info("Sent message successfully....");
			       
			    }catch (MessagingException mex) {
			       mex.printStackTrace();
			       return false;
			    } catch (SQLException e) {
					e.printStackTrace();
					return false;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
				
			}
			tr.close();
		} catch (NoSuchProviderException e1) {
			e1.printStackTrace();
			return false;
		} catch (MessagingException e1) {
			// TODO Blocco catch generato automaticamente
			e1.printStackTrace();
			return false;
		}
	
	return true;
	}
}
