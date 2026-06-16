package it.finanze.scheduler.bean;

import it.finanze.siga.utility.properties.PropertiesReader;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.*;

public class MailUtility {

	private static String DIR_PROPERTIES = "/prod/installedApps/SIGA/AppProperties";
	private static String FILE_PROPERTIES = "mail.properties";
	private static String MAIL_MITTENTE = "mail.mittente";
	private static String MAIL_ASSISTENZA = "mail.assistenza";
	private static String HOST = "mail.smtp.host";

 	public static void sendMail (ArrayList <String> dest, String oggetto, String testoEmail)
			throws MessagingException
			{
	 
		PropertiesReader pr = new PropertiesReader(DIR_PROPERTIES, FILE_PROPERTIES);
		Properties properties = pr.getProperties();		

		// Mittente

		String mitt=   properties.getProperty(MAIL_MITTENTE);
		// Creazione di una mail session
		Properties props = new Properties();
		props.put("mail.smtp.host", properties.getProperty(HOST));
		Session session = Session.getDefaultInstance(props);

		// Creazione del messaggio da inviare
		MimeMessage message = new MimeMessage(session);
		message.setSubject(oggetto);
		message.setText(testoEmail);

		// Aggiunta degli indirizzi del mittente e del destinatario
		InternetAddress fromAddress = new InternetAddress(mitt);

		String destinatario = "";
		if(dest.size()!=0){

			for (int i=0; i<dest.size();i++){

				destinatario = destinatario + dest.get(i).toString();
				if (i<(dest.size()-1))
					destinatario = destinatario +",";

			}}

		else 
			destinatario=properties.getProperty(MAIL_ASSISTENZA);

		//InternetAddress toAddress = new InternetAddress(destinatario);
		message.setFrom(fromAddress);
		//message.setRecipient(Message.RecipientType.TO, toAddress);
		message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
		// Invio del messaggio
		Transport.send(message);

			}

	public static void sendMail(String emailResponsabile, String emailRichiedente, String oggetto, String testoEmail )throws MessagingException {

		PropertiesReader pr = new PropertiesReader(DIR_PROPERTIES, FILE_PROPERTIES);
		Properties properties = pr.getProperties();		
		//String testoEmail = "Il batch AllineamentoResponsabileCdR non e' startato";
		//String oggetto = "AllineamentoResponsabileCdR";
		String mitt=   properties.getProperty(MAIL_MITTENTE);
		String dest = emailRichiedente;
		String copiaCarbone = emailResponsabile;
		Properties props = new Properties();
		//props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.host",properties.getProperty(HOST));
		Session session = Session.getDefaultInstance(props);

		// Creazione del messaggio da inviare
		MimeMessage message = new MimeMessage(session);
		message.setSubject(oggetto);
		message.setText(testoEmail);
		InternetAddress fromAddress = new InternetAddress(mitt);
		InternetAddress toAddress = new InternetAddress(dest);
		InternetAddress ccAddress = new InternetAddress(copiaCarbone);
		message.setFrom(fromAddress);
		message.setRecipient(Message.RecipientType.TO, toAddress);
		if (!dest.equalsIgnoreCase(copiaCarbone)){
			message.setRecipient(Message.RecipientType.CC, ccAddress);
		}
		// Invio del messaggio
		Transport.send(message);
	}
 
}
