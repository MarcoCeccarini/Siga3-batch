package it.finanze.siga.business;

import it.finanze.siga.bean.OperatoreBean;
import it.finanze.siga.dao.impl.SigaDaoImpl;
import it.finanze.siga.finder.OperatoreFinder;
import it.finanze.siga.util.Logg;
import it.finanze.siga.util.MailSender;
import it.finanze.siga.util.bean.UtenteBean;
import it.finanze.siga.utility.properties.PropertiesReader;
import it.sogei.eaf.util.CheckException;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.mail.util.ByteArrayDataSource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.*;

/**
 * 
 * @author m.pescetelli
 * 
 */
@ApplicationScoped
@Named("MailCommonUtil")	
public class MailCommonUtil {
	
	
	@EJB
	SigaDaoImpl service;
	
	public static final String DIR_PROPERTIES = "/prod/installedApps/SIGA/AppProperties";
	public static final String FILE_PROPERTIES = "mail.properties";
	
	
	/**
	 * Metodo di invio mail semplificato e null safe su tutti i parametri passati
	 * Restituisce false in caso di valore null o errore; true in caso ovviamente vada tutto bene
	 * Questo metodo utilizza il log per specificare l'andamento e l'eventuale errore
	 * 
	 * @param lista destinatari email
	 * @param subject
	 * @param templateFileProperties name
	 * @param parametri di sostituzione testuale
	 */
	public  boolean sendMailSemplified(List<String> destinatari,List<String> destinatari_cc, 
												String subjectProperties, 
													String templateFileProperties,
														HashMap<String,String> param ) {
		
		return sendMailSemplified(destinatari, destinatari_cc, subjectProperties, templateFileProperties, param, null);
		
	}
	
	/**
	 * Metodo di invio mail semplificato e null safe su tutti i parametri passati
	 * Restituisce false in caso di valore null o errore; true in caso ovviamente vada tutto bene
	 * Questo metodo utilizza il log per specificare l'andamento e l'eventuale errore
	 * 
	 * @param lista destinatari email
	 * @param subject
	 * @param templateFileProperties name
	 * @param parametri di sostituzione testuale
	 */
	public  boolean sendMailSemplified(List<String> destinatari,List<String> destinatari_cc, 
												String subjectProperties, 
													String templateFileProperties,
														HashMap<String,String> param, ArrayList subjectParams ) {
		Logg.getLogger().info("Inizializzazione del sistema di invio Mail..");
		if(destinatari != null && destinatari.size()>0){
		
			List<String> dest = puliziaDoppioni(destinatari);
		
			PropertiesReader pr = new PropertiesReader(MailCommonUtil.DIR_PROPERTIES, MailCommonUtil.FILE_PROPERTIES);
			Properties properties = pr.getProperties();
			
			String subject = properties.getProperty(subjectProperties);
			if(subjectParams!= null){
				Object[] prms = subjectParams.toArray();
				subject = MessageFormat.format(subject,prms);
				//subject = MessageFormat.format(subject,subjectParams);
			}
			
			String keyTemplate = properties.getProperty(templateFileProperties);
			
			if(subject!=null && !subject.equalsIgnoreCase("")){
				Logg.getLogger().info("Invio Mail in corso..");
				
				if(param == null)
					param = new HashMap<String, String>();
				
				boolean invioMail =  MailSender.send(dest,destinatari_cc,subject, keyTemplate, param);
 
				if(invioMail){
					Logg.getLogger().info("Invio Mail Effettuato Con Successo");
					return true;
				}
			}else{
				Logg.getLogger().error("Errore - Non e' possibile inviare Mail, Subject non trovato");
			}
		}else{
			Logg.getLogger().error("Errore - Non e' possibile inviare Mail, destinatari non trovati");
			return false;
		}
		Logg.getLogger().error("Errore - Non e' stato possibile inviare Mail");

		return false;
	}
	
	public  boolean sendMailAssistenza(List<String> destinatari,List<String> destinatari_cc,  
			String templateFileProperties,
				HashMap<String,String> param, ArrayList subjectParams, String subject) {
		
		Logg.getLogger().info("Inizializzazione del sistema di invio Mail..");
		if (destinatari != null && destinatari.size() > 0) {
			
			List<String> dest = puliziaDoppioni(destinatari);
			
			PropertiesReader pr = new PropertiesReader(MailCommonUtil.DIR_PROPERTIES, MailCommonUtil.FILE_PROPERTIES);
			Properties properties = pr.getProperties();
			
			if(subjectParams!= null){
				subject = MessageFormat.format(subject,subjectParams);
			}
			
			String keyTemplate = properties.getProperty(templateFileProperties);
			
			if(subject!=null && !subject.equalsIgnoreCase("")){
				Logg.getLogger().info("Invio Mail in corso..");
				
				if(param == null)
					param = new HashMap<String, String>();
				
				boolean invioMail =  MailSender.send(dest,destinatari_cc,subject, keyTemplate, param);
				if(invioMail){
					Logg.getLogger().info("Invio Mail Effettuato Con Successo");
					return true;
				}
			}else{
				Logg.getLogger().error("Errore - Non e' possibile inviare Mail, Subject non trovato");
			}
		}else{
			Logg.getLogger().error("Errore - Non e' possibile inviare Mail, destinatari non trovati");
			return false;
		}
		Logg.getLogger().error("Errore - Non e' stato possibile inviare Mail");

		return false;
	}
	
	public  boolean sendMailAssistenza(List<String> destinatari,List<String> destinatari_cc,  
			String templateFileProperties,
				HashMap<String,String> param, ArrayList subjectParams, String subject, String sender) {
		
		Logg.getLogger().info("Inizializzazione del sistema di invio Mail..");
		if (destinatari != null && destinatari.size() > 0) {
			
			List<String> dest = puliziaDoppioni(destinatari);
			
			PropertiesReader pr = new PropertiesReader(MailCommonUtil.DIR_PROPERTIES, MailCommonUtil.FILE_PROPERTIES);
			Properties properties = pr.getProperties();
			
			if(subjectParams!= null){
				subject = MessageFormat.format(subject,subjectParams);
			}
			
			String keyTemplate = properties.getProperty(templateFileProperties);
			
			if(subject!=null && !subject.equalsIgnoreCase("")){
				Logg.getLogger().info("Invio Mail in corso..");
				
				if(param == null)
					param = new HashMap<String, String>();
				
				boolean invioMail =  MailSender.send(dest,destinatari_cc,subject, keyTemplate, param, sender);
				if(invioMail){
					Logg.getLogger().info("Invio Mail Effettuato Con Successo");
					return true;
				}
			}else{
				Logg.getLogger().error("Errore - Non e' possibile inviare Mail, Subject non trovato");
			}
		}else{
			Logg.getLogger().error("Errore - Non e' possibile inviare Mail, destinatari non trovati");
			return false;
		}
		Logg.getLogger().error("Errore - Non e' stato possibile inviare Mail");

		return false;
	}

	/**
	 * Questo Metodo pulisce la lista passata, dagli elementi doppi
	 * */
	private  List<String> puliziaDoppioni(List<String> destinatari) {
		HashMap<String, String> clearMailList = new HashMap<String, String>();
		List<String> listCleared = new ArrayList<String>();
		for(int i=0; i<destinatari.size(); i++){
			String destinatario = destinatari.get(i);
			if(!clearMailList.containsKey(destinatario)){
				clearMailList.put(destinatario, destinatario);
				listCleared.add(destinatario);
			}
			
		}
		
		return listCleared;
	}

	/**
	 * Ricava l'indirizzo email dalla tabella UTENTI usando il cf e se non vuoto e non nullo lo restituisce in una List
	 * utile per l'utilizzo rimanendo sempre null safe
	 * @param cf
	 * @return
	 * @author m.pescetelli 
	 */
	public  List<String> ricavaEmailByCf(String cf){
		List<String> listaEmail = new ArrayList<String>();
		OperatoreFinder finder = new OperatoreFinder(cf);
		try {
			OperatoreBean operatore = service.getDatiUtente2(finder);
			String email = operatore.getEmail();
			if(email != null && !"".equals(email)){
				listaEmail.add(email);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}				
		return listaEmail;
	}
	
	/**
	 * Ricava l'indirizzo di tutti gli utentei con il ruolo selezionato 
	 * es. Amministratore cerntrale, locale e 
	 * @param ruolo es. per amministratore centrale: CostantiSessione.AMMINISTRATORE_CENTRALE
	 * @param service
	 * @return Lista di stringhe con le mail di tutti gli amministratori centrali 
	 * @author f.rachiele
	 */
	public  List<String> ricavaEmailByRuolo(String ruolo){
		List<String> listaEmail = new ArrayList<String>();		
		try {
			listaEmail = service.getEmailPerRuolo(ruolo);
		} catch (Exception e) {			
			e.printStackTrace();
		}				
		return listaEmail;
	}
	
	/**
	 * Data una lista di CF aggiunge i rispettivi indirizzi mail alla lista destinatari.
	 * @param listaCF
	 * @param destinatari
	 * @param service
	 * @throws SQLException 
	 */
	public  void aggiungiDestinatari(List<String> listaCF, List<String> destinatari){
		for (Iterator<String> iterator = listaCF.iterator(); iterator.hasNext();) {
			String cf = (String) iterator.next();
			OperatoreFinder finder = new OperatoreFinder(cf);
			OperatoreBean operatore;
			try {
				operatore = service.getDatiUtente2(finder);
				String email = operatore.getEmail();
				if (email != null && !"".equals(email)) {
					destinatari.add(email);
				}
			} catch (Exception e) {
				Logg.getLogger().error("Errore - " + e.getMessage());
			}

		}
	}
	
	public  boolean sendMailWithAttachment(	List<String> destinatari,
			String subjectProperties, String templateFileProperties,
			HashMap<String, String> param, Map<String, ByteArrayDataSource> badsMap) {
		
		return sendMailWithAttachment(destinatari, subjectProperties, templateFileProperties, param, badsMap, null);
		
	}
	
	
	public  boolean sendMailWithAttachment(	List<String> destinatari,
													String subjectProperties, String templateFileProperties,
													HashMap<String, String> param, Map<String, ByteArrayDataSource> badsMap, ArrayList subjectParams) {
		Logg.getLogger().info("Inizializzazione del sistema di invio Mail..");
		if (destinatari != null && destinatari.size() > 0) {
			List<String> dest = puliziaDoppioni(destinatari);

			PropertiesReader pr = new PropertiesReader(MailCommonUtil.DIR_PROPERTIES,MailCommonUtil.FILE_PROPERTIES);
			Properties properties = pr.getProperties();

			String subject = properties.getProperty(subjectProperties);
			if(subjectParams!= null){
				Object[] prms = subjectParams.toArray();
				subject = MessageFormat.format(subject, prms);
				//subject = MessageFormat.format(subject,subjectParams);
			}
			String keyTemplate = properties.getProperty(templateFileProperties);

			if (subject != null && !subject.equalsIgnoreCase("")) {
				Logg.getLogger().info("Invio Mail in corso..");

				if (param == null)
					param = new HashMap<String, String>();

				boolean invioMail = MailSender.sendWithAttachment(dest, subject, keyTemplate,param,badsMap);
				if (invioMail) {
					Logg.getLogger().info("Invio Mail Effettuato Con Successo");
					return true;
				}
			} else {
				Logg.getLogger().error("Errore - Non e' possibile inviare Mail, Subject non trovato");
			}
		} else {
			Logg.getLogger().error("Errore - Non e' possibile inviare Mail, destinatari non trovati");
			return false;
		}
		Logg.getLogger().error("Errore - Non e' stato possibile inviare Mail");
		
		
		
		
		return false;
	}
	
	
	/**
	 * Dato un filee ritorna il ByteArrayDataSource utile per l'invio di un allegato
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public  ByteArrayDataSource getByteArrayDataSourceFromFile(File file) throws IOException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        FileInputStream fis = new FileInputStream(file);
        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum); 
        }
        byte[] bytes = bos.toByteArray();
        fis.close();
	    ByteArrayDataSource ds = new ByteArrayDataSource(bytes, "application/octet-stream");
	    return ds;
	}

	public  List<String> determinaEmail(UtenteBean utente, String cdrIn) throws CheckException {

		List<String> listaEmail = new ArrayList<String>();

		try {
			// Se tra i ruoli e' presente ESG_AMM_Centrale_3 usciamo, le'elenco
			// delle email e' vuoto e cose' non dobbiamo mandare email
			if (utente.isAmministratoreCentrale()
					&& !utente.isAmministratoreLocale()
					&& !utente.isAmministratoreRegionale()) {

				return listaEmail;
			}

			// Se ha solo il ruolo ESG_AMM_Regionale_3 mandiamo la email agli AC
			// cose' individuati .
			if (!utente.isAmministratoreCentrale()
					&& !utente.isAmministratoreLocale()
					&& utente.isAmministratoreRegionale()) {
				listaEmail = service.getEmailAmmCentrali();
			}
			// Se ha solo il ruolo ESG_AMM_Locale_3 mandiamo la email agli AR
			// cose' individuati .
			if (!utente.isAmministratoreCentrale()
					&& utente.isAmministratoreLocale()
					&& !utente.isAmministratoreRegionale()) {

				String cdr = "";
				cdr = service.getStrutturaIILivByCdr(cdrIn);
				listaEmail = service.getEmailStrutturaIILiv(cdr);

				if (listaEmail.isEmpty()) {
					listaEmail = service.getEmailAmmCentrali();
				}

			}

			if (!utente.isAmministratoreCentrale()
					&& utente.isAmministratoreLocale()
					&& utente.isAmministratoreRegionale()) {

				OperatoreBean input = new OperatoreBean();
				input.setCf(utente.getCodFiscaleUtente());
				input.setCdr(cdrIn);

				String cdr = "";
				cdr = service.verificaStrutture(input);

				if ( cdr != null && !cdr.equals("")) {
					listaEmail = service.getEmailStrutturaIILiv(cdr);
				}
				
				else{
					listaEmail = service.getEmailAmmCentrali();
				}
				
			}

		} catch (Exception e) {
			Logg.getLogger().error("Errore - " + e.getMessage());
		}

		return listaEmail;
	}
	
	public  List<String> ricavaMailSovraordinati(String cdr){
		List<String> listaEmail = new ArrayList<String>();		
		try {
			listaEmail = service.ricavaMailSovraordinati(cdr);
		} catch (Exception e) {			
			e.printStackTrace();
		}				
		return listaEmail;
	}

}