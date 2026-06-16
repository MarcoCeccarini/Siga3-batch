package it.finanze.siga.workflow.core;

import it.finanze.siga.bean.CaricamentoMassivoEntity;
import it.finanze.siga.bean.OperatoreBean;
import it.finanze.siga.bean.RichiedenteCDRBean;
import it.finanze.siga.bean.RichiestaBean;
import it.finanze.siga.ejb.SigaDao;
import it.finanze.siga.finder.OperatoreFinder;
import it.finanze.siga.finder.RichiedenteCDRFinder;
import it.finanze.siga.util.Constants;
import it.finanze.siga.util.Logg;
import it.finanze.siga.util.SigaCache;
import it.finanze.siga.util.StatoRichiesta;
import it.finanze.siga.utility.properties.PropertiesReader;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.inject.Inject;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class WorkFlowMail {

	private static String ENCODING = Charsets.ISO_8859_1.toString();
	private static String DIR_PROPERTIES = "/prod/installedApps/SIGA/AppProperties";
	private static String DIR_TEMPLATE_PROPERTIES = DIR_PROPERTIES + "/template";
	private static String FILE_PROPERTIES = "mail.properties";
	private static String MAIL_MITTENTE = "mail.mittente";
	private static String MAIL_ASSISTENZA = "mail.assistenza";
	private static String MAIL_TEST = "mail.test";
	private static String MAIL_URL = "mail.url";

	public static String ERRORE_GENERICO = "mail.oggettoERR0";
	public static String ERRORE_ELABORAZIONE_RICHIESTA = "mail.oggettoERR1";
	public static String ERRORE_CANCELLA_UTENTE = "mail.oggettoERR2";
@Inject static SigaDao service;
	public static boolean send(Object object, HashMap<String, String> args) {
		try{
			PropertiesReader pr = new PropertiesReader(DIR_PROPERTIES, FILE_PROPERTIES);
			Properties properties = pr.getProperties();
			InternetAddress[] a=null, cc=null;
			String oggetto=null, template=null;
			HashMap<String,String> param = null;		
			OperatoreBean ub;
			List<WorkFlowMailBean> senderList = new ArrayList<WorkFlowMailBean>();
			if (object instanceof RichiestaBean){
				RichiestaBean richiesta = (RichiestaBean)object;

				if ((richiesta.getRichiedenteAc()!=null && richiesta.getRichiedenteAc().equals("AS"))||  (richiesta.getOrigineAbilitazione()!=null && richiesta.getOrigineAbilitazione().equals("AS")))
					return true;

				if (args!=null && args.get("stackTrace")!=null){
					param = new HashMap<String,String>();
					template = properties.getProperty("mail.templateERR");
					String messaggio=null;
					if (args.get("errore").equals(ERRORE_ELABORAZIONE_RICHIESTA)){
						oggetto = properties.getProperty(ERRORE_ELABORAZIONE_RICHIESTA);
						messaggio = "La richiesta avente id <strong>"+richiesta.getIdRichiesta()+"</strong> ha generato un'eccezione durante la sua esecuzione.";
					}else if (args.get("errore").equals(ERRORE_CANCELLA_UTENTE)){
						oggetto = properties.getProperty(ERRORE_CANCELLA_UTENTE);
						messaggio = "La cancellazione dell'utente <strong>"+richiesta.getCfUtente()+"</strong> ha generato un'eccezione durante la sua esecuzione.";

					}else{
						oggetto = properties.getProperty(ERRORE_GENERICO);
						messaggio = "L'applicazione SIGA 3.0 ha generato un'eccezione durante la sua esecuzione.";
					}												
					param.put("MESSAGGIO", messaggio);
					param.put("ECCEZIONE", args.get("stackTrace"));
					a = InternetAddress.parse(properties.getProperty(MAIL_ASSISTENZA));
					senderList.add(new WorkFlowMailBean(a, cc, oggetto, template, param));
				}
				else if (richiesta.getStatoRichiesta().equals(StatoRichiesta.RIFIUTATA_AUT_I)||richiesta.getStatoRichiesta().equals(StatoRichiesta.RIFIUTATA_AUT_II)){
					//Richiesta rifiutata (A:Richiedente)
					param = new HashMap<String,String>();
					oggetto = properties.getProperty("mail.oggetto2");
					template = properties.getProperty("mail.template4");		
					ub = service.getDatiUtente(new OperatoreFinder(service.getCFRichiedenteWFM(richiesta)));
					param.put("ID", String.valueOf(richiesta.getIdRichiesta()));
					param.put("NOME_RICHIEDENTE", ub.getNome());
					param.put("COGNOME_RICHIEDENTE", ub.getCognome());
					a = InternetAddress.parse(ub.getEmail());
					if (richiesta.getStatoRichiesta().equals(StatoRichiesta.RIFIUTATA_AUT_II)){		
						param.put("LIVELLO_AUTORIZZATORE", "II");
						ub = service.getDatiUtente(new OperatoreFinder(richiesta.getCfAutorizzatoreIIEffettivo()));
						param.put("NOME_AUTORIZZATORE", ub.getNome());
						param.put("COGNOME_AUTORIZZATORE", ub.getCognome());
						param.put("NOTA_AUTORIZZATORE", richiesta.getNoteAutorizzatoreII());
					}
					else{
						param.put("LIVELLO_AUTORIZZATORE", "I");
						ub = service.getDatiUtente(new OperatoreFinder(richiesta.getCfAutorizzatoreIEffettivo()));
						param.put("NOME_AUTORIZZATORE", ub.getNome());
						param.put("COGNOME_AUTORIZZATORE", ub.getCognome());
						param.put("NOTA_AUTORIZZATORE", richiesta.getNoteAutorizzatoreI());
					}
					senderList.add(new WorkFlowMailBean(a, cc, oggetto, template, param));
				}
				else if (richiesta.getStatoRichiesta().equals(StatoRichiesta.ESEGUITA) || 
						(richiesta.getTipoRichiesta().equalsIgnoreCase(Constants.RICHIESTA_VISIBILITA) && richiesta.getStatoRichiesta().equals(StatoRichiesta.ARCHIVIATA))){					
					if (!richiesta.getTipoRichiesta().equalsIgnoreCase(Constants.RICHIESTA_VISIBILITA) && (richiesta.getPresaVisione()==null || richiesta.getPresaVisione().equalsIgnoreCase("SI"))){
						//Eseguita profilazione (A:Operatore, CC:Richiedente, Richiedenti altri CDR [profilazione con profili ECA])
						param = new HashMap<String,String>();
						oggetto = properties.getProperty("mail.oggetto0");
						ub = service.getDatiUtente(new OperatoreFinder(richiesta.getCfUtente()));
						HashSet<String> aHS = new HashSet<String>();
						aHS.add(ub.getEmail());
						param.put("COGNOME_OPERATORE", ub.getCognome());
						param.put("NOME_OPERATORE", ub.getNome());
						if (richiesta.isSincronizzata()){
							template = properties.getProperty("mail.template1");
							paramfillerTipoRichiesta(param, richiesta.getCodiceAmbito());
						}
						else{
							template = properties.getProperty("mail.template0");
							ub = service.getDatiUtente(new OperatoreFinder(richiesta.getCfGestoreOperatoriEffettivo()));							
							param.put("COGNOME_GESTORE", ub.getCognome());
							param.put("NOME_GESTORE", ub.getNome());
						}
						param.put("ID", String.valueOf(richiesta.getIdRichiesta()));									
						String emailRichiedente = service.getDatiUtente(new OperatoreFinder(service.getCFRichiedenteWFM(richiesta))).getEmail();
						HashSet<String> ccHS = new HashSet<String>();
						if (!aHS.contains(emailRichiedente))
							ccHS.add(emailRichiedente);
						if (richiesta.getTipoRichiesta().equals(Constants.RICHIESTA_PROFILAZIONE) && richiesta.getCodiceAmbito()==1){
							//Recupera richiedenti di altri CDR nel caso di profilazioni con profili ECA
							List<String> listRichiedenti = service.getCFRichiedentiECAAltriCDRWFM(richiesta);
							List<String> listEmailAltroUff = service.getEmailAltroUfficioInteressato(richiesta);
							RichiestaBean rb = new RichiestaBean();
							rb.setIdRichiesta(richiesta.getIdRichiesta());
							for (Iterator<String> iterator = listRichiedenti.iterator(); iterator.hasNext();) {
								String richiedente = iterator.next();							
								rb.setCfArchiviazione(richiedente);
								ub = service.getDatiUtente(new OperatoreFinder(service.getCFRichiedenteVisibilitaWFM(rb)));
								String email = ub.getEmail();
								if (!aHS.contains(email)&&!ccHS.contains(email))
									ccHS.add(email);
							}

							if(!listEmailAltroUff.isEmpty()){
								for(String email:listEmailAltroUff ){
									if (!aHS.contains(email)&&!ccHS.contains(email))
									ccHS.add(email);	
								}	
							}
							
							int countProfiliGestoreDirete=service.countProfiliAsGestoreDiRete(richiesta.getIdRichiesta());
							String mailGestoreDiRete= "Le modifiche relative alle abilitazioni a Gestore di rete," +
									" presenti nella richiesta, non sono ancora operative. A breve ricever&agrave; una nuova " +
									"comunicazione relativa alla conclusione delle attivit&agrave; tecniche di modifica tutt'ora in corso.";
							
							if( countProfiliGestoreDirete>0)
								param.put("PROFILI_GESTORE_DI_RETE",mailGestoreDiRete);
							
						}						
						a = InternetAddress.parse(StringUtils.join(aHS.toArray(),","));
						cc = InternetAddress.parse(StringUtils.join(ccHS.toArray(),","));

						senderList.add(new WorkFlowMailBean(a, cc, oggetto, template, param));
					//19/03/2019 modifica richiesta da ALAFAUCE (mail del 18/03/2019 16:23)
					//} else{
					} else if ((richiesta.getCodiceAmbito()==null||richiesta.getCodiceAmbito()!=5)&&(!richiesta.getCfArchiviazione().equals(SigaCache.getSYS_ADMIN()) || richiesta.getPresaVisione().equalsIgnoreCase("SI"))){
						//Assegnazione in visibilita'
						param = new HashMap<String,String>();
						oggetto = properties.getProperty("mail.oggetto1");						
						ub = service.getDatiUtente(new OperatoreFinder(richiesta.getCfUtente()));
						param.put("NOME_OPERATORE", ub.getNome());
						param.put("COGNOME_OPERATORE", ub.getCognome());
						param.put("UFFICIO", richiesta.getCdrVisibilita().getDescrizioneCDR());
						String emailOperatore = ub.getEmail();
						String cdrOperatore = ub.getCdr();
						param.put("UFFICIO_OPERATORE", service.getDescrCdrByCdr(richiesta.getCdrUtente()));
						param.put("ID", String.valueOf(richiesta.getIdRichiesta()));
						//Assegnazione in visibilita' (A:Richiedente del CDR di assegnazione, CC:Operatore)
						HashSet<String> aHS = new HashSet<String>();
						RichiedenteCDRFinder rCDRf = new RichiedenteCDRFinder();
						List<RichiedenteCDRBean> listRichiedente = new ArrayList<RichiedenteCDRBean>();
						ub = service.getDatiUtente(new OperatoreFinder(service.getCFRichiedenteWFM(richiesta)));							
						aHS.add(ub.getEmail());
						param.put("NOME_RICHIEDENTE_V1", ub.getNome());
						param.put("COGNOME_RICHIEDENTE_V1", ub.getCognome());
						template = properties.getProperty("mail.template2");
						if (!aHS.contains(emailOperatore))
							cc = InternetAddress.parse(emailOperatore);
						a = InternetAddress.parse(StringUtils.join(aHS.toArray(),","));
						senderList.add(new WorkFlowMailBean(a, cc, oggetto, template, param));
						//Assegnazione in visibilita' (A:Richiedente del CDR di visbilite', CC:Operatore)
						aHS = new HashSet<String>();
						rCDRf = new RichiedenteCDRFinder();
						rCDRf.setCodiceCDR(richiesta.getCdrVisibilita().getCodiceCDR());
						listRichiedente = service.getRichiedenteCDR(rCDRf);
						if (listRichiedente.size()>0 && listRichiedente.get(0).getCfRichiedente()!=null){
							RichiestaBean rb = new RichiestaBean();
							rb.setIdRichiesta(richiesta.getIdRichiesta());
							rb.setCfArchiviazione(listRichiedente.get(0).getCfRichiedente());
							ub = service.getDatiUtente(new OperatoreFinder(service.getCFRichiedenteVisibilitaWFM(rb)));
							param.put("NOME_RICHIEDENTE_V2", ub.getNome());
							param.put("COGNOME_RICHIEDENTE_V2", ub.getCognome());
							aHS.add(ub.getEmail());	
						}
						if (aHS.size()>0){
							template = properties.getProperty("mail.template5");
							if (!aHS.contains(emailOperatore))
								cc = InternetAddress.parse(emailOperatore);						
							a = InternetAddress.parse(StringUtils.join(aHS.toArray(),","));
							senderList.add(new WorkFlowMailBean(a, cc, oggetto, template, param));
						}
					}				
				}
				else if (richiesta.getStatoRichiesta().equals(StatoRichiesta.ESEGUITA_NEG)){
					//Eseguita negativamente (A:Richiedente)
					param = new HashMap<String,String>();
					oggetto = properties.getProperty("mail.oggetto5");				
					template = properties.getProperty("mail.template3");
					param.put("ID", String.valueOf(richiesta.getIdRichiesta()));
					ub = service.getDatiUtente(new OperatoreFinder(service.getCFRichiedenteWFM(richiesta)));
					param.put("COGNOME_RICHIEDENTE", ub.getCognome());
					param.put("NOME_RICHIEDENTE", ub.getNome());
					a = InternetAddress.parse(ub.getEmail());					
					ub = service.getDatiUtente(new OperatoreFinder(args.get("cfOperatore")));
					param.put("COGNOME_GESTORE", ub.getCognome());
					param.put("NOME_GESTORE", ub.getNome());					
					param.put("NOTE_GESTORE", richiesta.getNoteGestore());
					senderList.add(new WorkFlowMailBean(a, cc, oggetto, template, param));
				}
				else if (richiesta.getStatoRichiesta().equals(StatoRichiesta.ARCHIVIATA)){
					if (richiesta.getTipoRichiesta().equalsIgnoreCase(Constants.RICHIESTA_REVOCA_VISIBILITA)){
						//Revoca della visibilite'. (A:Richiedente effettivo, Richiedente CDR di assegnazione, Richiedente CDR di visibilite', CC:Operatore)
						param = new HashMap<String,String>();
						oggetto = properties.getProperty("mail.oggetto3");
						template = properties.getProperty("mail.template7");
						ub = service.getDatiUtente(new OperatoreFinder(richiesta.getCfUtente()));
						param.put("NOME_OPERATORE", ub.getNome());
						param.put("COGNOME_OPERATORE", ub.getCognome());
						String emailOperatore = ub.getEmail();
						String cdrOperatore = ub.getCdr();
						param.put("CDR_DESC_VISIBILITA", richiesta.getCdrVisibilita().getDescrizioneCDR());					
						HashSet<String> aHS = new HashSet<String>();
						ub = service.getDatiUtente(new OperatoreFinder(richiesta.getCfArchiviazione()));
						if (!aHS.contains(ub.getEmail()))
							aHS.add(ub.getEmail());		
						param.put("NOME_RICHIEDENTE_REVOCA", ub.getNome());
						param.put("COGNOME_RICHIEDENTE_REVOCA", ub.getCognome());
						RichiedenteCDRFinder rCDRf = new RichiedenteCDRFinder();
						List<RichiedenteCDRBean> listRichiedente = null;
						if (richiesta.getRichiedenteAc()!=null && richiesta.getRichiedenteAc().equalsIgnoreCase("SI")){							
							rCDRf.setCodiceCDR(cdrOperatore);
							listRichiedente = service.getRichiedentiAC(rCDRf);
							if (listRichiedente.size()>0){
								ub = service.getDatiUtente(new OperatoreFinder(listRichiedente.get(0).getCfRichiedente()));
								if (!aHS.contains(ub.getEmail()))
									aHS.add(ub.getEmail());	
							}
							rCDRf = new RichiedenteCDRFinder();
							rCDRf.setCodiceCDR(richiesta.getCdrVisibilita().getCodiceCDR());
							listRichiedente = service.getRichiedentiAC(rCDRf);
							if (listRichiedente.size()>0){
								ub = service.getDatiUtente(new OperatoreFinder(listRichiedente.get(0).getCfRichiedente()));
								if (!aHS.contains(ub.getEmail()))
									aHS.add(ub.getEmail());	
							}
						}else{
							rCDRf.setCodiceCDR(cdrOperatore);
							rCDRf.setCfOperatore(richiesta.getCfUtente());
							listRichiedente = service.getRichiedentePuntualeCDR(rCDRf);
							if (listRichiedente.size()==0)
								listRichiedente = service.getRichiedenteCDR(rCDRf);
							if (listRichiedente.size()>0){
								ub = service.getDatiUtente(new OperatoreFinder(service.getCFRichiedenteWFM(richiesta)));
								if (!aHS.contains(ub.getEmail()))
									aHS.add(ub.getEmail());	
							}
							rCDRf = new RichiedenteCDRFinder();
							rCDRf.setCodiceCDR(richiesta.getCdrVisibilita().getCodiceCDR());
							listRichiedente = service.getRichiedenteCDR(rCDRf);
							if (listRichiedente.size()>0){
								RichiestaBean rb = new RichiestaBean();
								rb.setIdRichiesta(richiesta.getIdRichiesta());
								rb.setCfArchiviazione(listRichiedente.get(0).getCfRichiedente());
								ub = service.getDatiUtente(new OperatoreFinder(service.getCFRichiedenteVisibilitaWFM(rb)));
								if (!aHS.contains(ub.getEmail()))
									aHS.add(ub.getEmail());	
							}	
						}
						if (!aHS.contains(emailOperatore))
							cc = InternetAddress.parse(emailOperatore);	
						a = InternetAddress.parse(StringUtils.join(aHS.toArray(),","));
						senderList.add(new WorkFlowMailBean(a, cc, oggetto, template, param));
					}
				}
			}
			else if (object instanceof CaricamentoMassivoEntity){
				//Esecuzione richieste relative al caricamento massivo (A:Amministratore)
				param = new HashMap<String,String>();
				CaricamentoMassivoEntity caricamentoMassivoEntity = (CaricamentoMassivoEntity)object;
				if (caricamentoMassivoEntity.getCodiceEvento().equalsIgnoreCase("CarMass")||caricamentoMassivoEntity.getCodiceEvento().equalsIgnoreCase("CarMassImm")){
					param.put("TIPO_CARICAMENTO_OGGETTO", "massivo");
					param.put("TIPO_CARICAMENTO_TESTO", "profilazione");
				}else if (caricamentoMassivoEntity.getCodiceEvento().equalsIgnoreCase("CarMassNI")||caricamentoMassivoEntity.getCodiceEvento().equalsIgnoreCase("CarMassImmNI")){
						param.put("TIPO_CARICAMENTO_OGGETTO", "massivo app non integrate");
						param.put("TIPO_CARICAMENTO_TESTO", "profilazione");	
				}else{
					param.put("TIPO_CARICAMENTO_OGGETTO", "massivo visibilit\u00E0");
					param.put("TIPO_CARICAMENTO_TESTO", "visibilit&agrave; e/o revoca visibilit&agrave;");
				}
				oggetto = properties.getProperty("mail.oggetto4");
				template = properties.getProperty("mail.template8");				
				ub = service.getDatiUtente(new OperatoreFinder(caricamentoMassivoEntity.getCfAmministratore()));
				param.put("ID_CARICAMENTO", caricamentoMassivoEntity.getIdCaricamento());	
				param.put("DESC_RICHIESTA_CARICAMENTO", caricamentoMassivoEntity.getDescrizioneRichiestaCaricamento());					
				a = InternetAddress.parse(ub.getEmail());
				senderList.add(new WorkFlowMailBean(a, cc, oggetto, template, param));
			}
			Session session = Session.getDefaultInstance(properties);
			Transport tr = session.getTransport();
			tr.connect();
			for (Iterator<WorkFlowMailBean> iterator = senderList.iterator(); iterator.hasNext();) {
				WorkFlowMailBean sender = (WorkFlowMailBean) iterator.next();
				a = sender.a;
				cc = sender.cc;
				oggetto = sender.oggetto;
				param = sender.param;
				template = sender.template;
				oggetto = getValue(param, oggetto);
				if (a==null || template==null)
					continue;
				param.put("URL_SIGA", properties.getProperty(MAIL_URL, "#"));
				if (!SigaCache.getMOD().equals("3")){
					StringBuilder sb = new StringBuilder();
					if (a.length>0){
						sb.append("<strong>A:</strong> ");
						for (int i = 0; i < a.length; i++) {
							sb.append("<em>"+a[i].getAddress()+"</em>").append("; ");
						}
						sb.append("<br />");
					}
					if (cc!=null && cc.length>0){
						sb.append("<strong>CC:</strong> ");
						for (int i = 0; i < cc.length; i++) {
							sb.append("<em>"+cc[i].getAddress()+"</em>").append("; ");
						}
						sb.append("<br />");
					}
					sb.append("<br />");
					param.put("TEST_A_CC", sb.toString());
					a = InternetAddress.parse(properties.getProperty(MAIL_TEST, "alafauce@sogei.it, ptorricelli@sogei.it")); 
					cc = null;
				}
				else
					param.put("TEST_A_CC", "");
				MimeMessage message = new MimeMessage(session);
				message.setSubject(oggetto);
				message.setContent(getValue(param, FileUtils.readFileToString(new File(DIR_TEMPLATE_PROPERTIES + "/" + template), ENCODING)), "text/html");
				message.setFrom(new InternetAddress(properties.getProperty(MAIL_MITTENTE)));			
				message.addRecipients(Message.RecipientType.TO, a);
				if (cc!=null)
					message.addRecipients(Message.RecipientType.CC, cc);			
				message.saveChanges();
				tr.sendMessage(message, message.getAllRecipients());
			}
			tr.close();
		} catch (Throwable e){
			e.printStackTrace();
			Logg.getLogger().error(e.getMessage(), e);
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

	private static void paramfillerTipoRichiesta(HashMap<String,String> param, int codAmbito){
		if (codAmbito==4)
			param.put("TIPO_RICHIESTA", "di cambio ufficio CAT ");
		else
			param.put("TIPO_RICHIESTA", "di profilazione ");
	}

}
