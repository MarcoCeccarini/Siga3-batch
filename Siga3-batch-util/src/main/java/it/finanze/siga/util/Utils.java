package it.finanze.siga.util;

import it.finanze.siga.bean.AbilitazioneBean;


import it.finanze.siga.bean.AmbitoBean;
import it.finanze.siga.bean.AutorizzatoreBean;
import it.finanze.siga.bean.CDRBean;
import it.finanze.siga.bean.Flags;
import it.finanze.siga.bean.GetAutorizzatore_I_LivBean;
import it.finanze.siga.bean.InterrProfiliOperatoreBean;
import it.finanze.siga.bean.IterBean;
import it.finanze.siga.bean.OperatoreBean;
import it.finanze.siga.bean.ProfiloBean;
import it.finanze.siga.bean.ProfiloRichiestaBean;
import it.finanze.siga.bean.RichiestaBean;
import it.finanze.siga.bean.StrutturaUfficioCDRExtBean;
import it.finanze.siga.bean.UffDestinazBean;
import it.finanze.siga.util.bean.ElencoBean;
import it.finanze.siga.util.bean.ModuloAppBean;
import it.finanze.siga.util.tree.ProfiloTreeBean;
import it.finanze.siga.utility.properties.PropertiesReader;
import it.sogei.eaf.stack.ElementStack;
import it.sogei.eaf.stack.Stack;
import it.sogei.eaf.stack.StackUtil;
import it.sogei.eaf.util.CheckException;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;





public class Utils {

	private final static String TITLE_1 = "coloumn1Title";
	private final static String TITLE_2 = "coloumn2Title";
	private final static String TITLE_3 = "coloumn3Title";
	private final static String TITLE_4 = "coloumn4Title";
	private final static String TITLE_5 = "coloumn5Title";
	private final static String TITLE_6 = "coloumn6Title";
		
	public static int numeroRecord;
	
	private final static String TITLE_MAP_1 = "colMap1Title";
	private final static String TITLE_MAP_2 = "colMap2Title";
	private final static String TITLE_MAP_3 = "colMap3Title";
	private final static String TITLE_MAP_4 = "colMap4Title";
	private final static String TITLE_MAP_5 = "colMap5Title";
	private final static String TITLE_MAP_6 = "colMap6Title";
	private final static String TITLE_MAP_7 = "colMap7Title";
	private final static String TITLE_MAP_8 = "colMap8Title";
	
	
	
	private final static String INTEST_1 = "intest1Title";
	private final static String INTEST_2 = "intest2Title";
	private final static String INTEST_3 = "intest3Title";
	private final static String INTEST_4 = "intest4Title";
	private final static String INTEST_5 = "intest5Title";
	private final static String INTEST_6 = "intest6Title";
	
	
	private final static String TITLEINT_MAP_1 = "intColMap1Title";
	private final static String TITLEINT_MAP_2 = "intColMap2Title";
	private final static String TITLEINT_MAP_3 = "intColMap3Title";
	private final static String TITLEINT_MAP_4 = "intColMap4Title";
	private final static String TITLEINT_MAP_5 = "intColMap5Title";
	private final static String TITLEINT_MAP_6 = "intColMap6Title";
	private final static String TITLEINT_MAP_7 = "intColMap7Title";
	private final static String TITLEINT_MAP_8 = "intColMap8Title";
	
		

	private Utils() {
	};
	

	
	
	
	

	public static ModuloAppBean initAppModule(String cf) {
		ModuloAppBean moduloApp = new ModuloAppBean();
		moduloApp.setCodFiscaleUtente(cf);
		return moduloApp;
	}

	private static void aggiungiDelegante(HashSet<String> struttura, String ruolo) {
		Logg.getLogger().info(Constants.TO_IMPLEMENT);
	}

	public static List<LabelValueBean> transformCDRListToLabelValueList(List<CDRBean> list) {
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();

		for (CDRBean bean : list) {
			LabelValueBean newBean = new LabelValueBean(bean.getDescrizioneCDR(), bean.getCodiceCDR());
			result.add(newBean);
		}

		return result;
	}

	public static List<OperatoreBean> rimuoviOperatoreSelezionatoDaLista(List<OperatoreBean> fromList, String cfOperatore) {
		if (fromList != null && fromList.size() > 0) {
			Iterator<OperatoreBean> itr = fromList.iterator();
			while (itr.hasNext()) {
				if (itr.next().getCf().equalsIgnoreCase(cfOperatore)) {
					Logg.getLogger().info("RIMUOVO OPERATORE DA LISTA: NON POSSO CARICARE DA ME STESSO");
					itr.remove();
					break;
				}
			}
		}
		return fromList;
	}

	public static List<LabelValueBean> orderListByPropertySelectedValue(List<LabelValueBean> fromList, String value) {
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();

		if (value != null) {
			LabelValueBean newFirstBean = null;
			LabelValueBean firstBean = fromList.get(0);
			if (value.equals(firstBean.getValue())) {
				// OK do nothing
				result = new ArrayList<LabelValueBean>(fromList);
			} else {
				Iterator<LabelValueBean> itr = fromList.iterator();
				while (itr.hasNext()) {
					LabelValueBean bean = itr.next();
					if (bean.getValue().equalsIgnoreCase(value)) {
						newFirstBean = bean;
						itr.remove();
					}
				}
			}
			result.add(newFirstBean);
			result.addAll(fromList);
		}

		return result;
	}

	public static List<AmbitoBean> orderListByPropertySelectedValueAB(List<AmbitoBean> fromList, String value) {
		List<AmbitoBean> result = new ArrayList<AmbitoBean>();

		if (value != null && (fromList != null && fromList.size() > 0)) {
			AmbitoBean newFirstBean = null;
			AmbitoBean firstBean = fromList.get(0);
			if (firstBean != null) {
				if (value.equals(firstBean.getCodiceAmbito())) {
					// OK do nothing
					result = new ArrayList<AmbitoBean>(fromList);
				} else {
					Iterator<AmbitoBean> itr = fromList.iterator();
					while (itr.hasNext()) {
						AmbitoBean bean = itr.next();
						if (bean.getCodiceAmbito().equalsIgnoreCase(value)) {
							newFirstBean = bean;
							itr.remove();
						}
					}
					result.add(newFirstBean);
					result.addAll(fromList);
				}
			}
		}
		return result;
	}

	// public static List<LabelValueBean>
	// transformAmbitoListToLabelValueList(List<AmbitoBean> list){
	// List<LabelValueBean> result = new ArrayList<LabelValueBean>();
	//
	// for (AmbitoBean bean : list) {
	// if(bean!=null){
	// LabelValueBean newBean = new LabelValueBean(bean.getDescrizione(),
	// bean.getCodiceAmbito());
	// result.add(newBean);
	// }
	// }
	//
	// return result;
	// }
	/**
	 * Trasforma una lista di CdRBean in una LabelValueBean
	 * 
	 * @param list
	 * @return
	 */
	public static List<LabelValueBean> transformCdRListToLabelValueList(List<CDRBean> list) {
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();
		for (CDRBean bean : list) {
			LabelValueBean newBean = new LabelValueBean(bean.getDescrizioneCDR(), bean.getCodiceCDR());
			result.add(newBean);
		}
		return result;
	}

	public static List<LabelValueBean> transformUfficioListToLabelValueList(List<UffDestinazBean> list) {
		List<LabelValueBean> result = new ArrayList<LabelValueBean>();

		for (UffDestinazBean bean : list) {
			LabelValueBean newBean = new LabelValueBean(bean.getCodiceUfficio() + " - " + bean.getDescrizioneUfficio(), bean.getCodiceUfficio() + " - " + bean.getDescrizioneUfficio());
			result.add(newBean);
		}

		return result;
	}

	public static <T> List<Serializable> toListSerializable(List<T> list) {
		ElencoBean risultato = new ElencoBean();
		risultato.setNumeroElementi(list.size());
		List<Serializable> listaRisultato = new ArrayList<Serializable>();
		for (Object o : list)
			listaRisultato.add((Serializable) o);
		risultato.setElenco(listaRisultato);
		return listaRisultato;
	}

	public static <T> ElencoBean toElencoBean(List<T> list) {
		ElencoBean risultato = new ElencoBean();
		risultato.setNumeroElementi(list.size());
		List<Serializable> listaRisultato = new ArrayList<Serializable>();
		for (Object o : list)
			listaRisultato.add((Serializable) o);
		risultato.setElenco(listaRisultato);
		return risultato;
	}

	public static List<IterBean> raggruppoProfiliSenzaIterNellaListIter(List<IterBean> listIter) {
		List<ProfiloBean> profiliSenzaIterList = new ArrayList<ProfiloBean>();
		IterBean iterProfiliSenzaIter;
		ListIterator<IterBean> itr = listIter.listIterator();
		while (itr.hasNext()) {
			IterBean iter = itr.next();
			if (iter != null && (iter.getId() == null || iter.getId().equals("0"))) {
				profiliSenzaIterList.addAll(iter.getProfiloList());
				itr.remove();
			}
		}
		if (profiliSenzaIterList.size() > 0) {
			iterProfiliSenzaIter = new IterBean();
			iterProfiliSenzaIter.setId("0");
			iterProfiliSenzaIter.setProfiloList(profiliSenzaIterList);
			listIter.add(iterProfiliSenzaIter);
		}
		return listIter;
	}

	/**
	 * Raggruppo i profili per iter e applicazione è usato nel caso di
	 * AmbitoApplicativo == NonSincronizzato (5)
	 * 
	 * @param Riceve
	 *            una lista di profili Bean listFrom
	 * @return Return una lista di IterBean
	 */
	public static List<IterBean> raggruppoProfiliPerIterEApplicazioni(List<ProfiloBean> listFrom) {
		List<IterBean> listIter = new ArrayList<IterBean>();
		IterBean iter = null;
		boolean inserito = false;

		for (ProfiloBean profiloBean : listFrom) {
			inserito = false;
			iter = new IterBean();
			iter.setId(profiloBean.getIdIter());
			iter.setCodApplicazione(profiloBean.getCodiceApplicazione());
			Iterator<IterBean> i = listIter.iterator();
			while (i.hasNext()) {
				IterBean iterBean = (IterBean) i.next();
				if (iterBean.getId().equals(iter.getId()) && iterBean.getCodApplicazione().equals(iter.getCodApplicazione())) {
					iterBean.getProfiloList().add(profiloBean);
					inserito = true;
					break;
				}
			}
			if (!inserito) {
				iter.setProfiloList(new ArrayList<ProfiloBean>());
				iter.getProfiloList().add(profiloBean);
				listIter.add(iter);
			}
		}
		return listIter;
	}

	/**
	 * Raggruppo i profili per iter è usato in tutti i casi tranne nel caso di
	 * AmbitoApplicativo == NonSincronizzato (5)
	 * 
	 * @param Riceve
	 *            una lista di profili Bean listFrom
	 * @return Return una lista di IterBean
	 */
	public static List<IterBean> raggruppoProfiliPerIter(List<ProfiloBean> listFrom) {

		// riordino in base a iterId se no potrebbe non venire bene il
		// raggruppamento
		Collections.sort(listFrom, new Comparator<ProfiloBean>() {
			@Override
			public int compare(ProfiloBean object1, ProfiloBean object2) {
				if (object1 == null || object2 == null)
					return 0;
				else {
					if (object1.getIdIter() == null || object2.getIdIter() == null)
						return 0;
					else
						return object1.getIdIter().compareTo(object2.getIdIter());
				}
			}
		});

		// raggruppare i profili per iter
		List<IterBean> listIter = new ArrayList<IterBean>();
		String currIter = null, tempIter = null, lastAdded = null;
		IterBean iter = null;
		boolean unicoIter = true;

		// raggruppo profili per iter - soluzione 2
		for (ProfiloBean profiloBean : listFrom) {
			tempIter = profiloBean.getIdIter();
			if (currIter != null && !tempIter.equalsIgnoreCase(currIter)) {
				unicoIter = false;
			}
			if (tempIter != null && tempIter.equalsIgnoreCase(currIter)) {
				iter.getProfiloList().add(profiloBean);
			} else {
				if (iter == null) {
					// inserire controllo x quando ho item nn verif e verif
					iter = new IterBean();
					iter.setId(profiloBean.getIdIter());
					iter.setProfiloList(new ArrayList<ProfiloBean>());
					iter.getProfiloList().add(profiloBean);
				} else {
					if (tempIter != null && tempIter.equalsIgnoreCase(currIter)) {
						iter.getProfiloList().add(profiloBean);
					} else {
						listIter.add(iter);
						lastAdded = currIter;
						// inserire controllo x quando ho item nn verif e verif
						iter = new IterBean();
						iter.setId(profiloBean.getIdIter());
						iter.setProfiloList(new ArrayList<ProfiloBean>());
						iter.getProfiloList().add(profiloBean);
					}
				}
			}
			currIter = profiloBean.getIdIter();
		}
		// se l'ultimo aggiunto Ã¨ diverso da quello rimasto aggiungo
		if ((lastAdded != null && !lastAdded.equals("") && !lastAdded.equals(tempIter)) || unicoIter) {
			listIter.add(iter);
		}

		return listIter;
	}

	/**
	 * Trasforma una stringa con formato gg/mm/aaaa in Date <br>
	 * Il metodo e' null safe
	 * 
	 * @return Date
	 */
	public static Date setDateFromDateString(String formDate) {
		if (formDate == null || formDate.equals(""))
			return null;
		String anno, mese, giorno;
		int giornoInt, meseInt, annoInt;
		String[] gma = formDate.split("/");
		giorno = gma[0];
		mese = gma[1];
		anno = gma[2];
		giornoInt = Integer.parseInt(giorno);
		meseInt = Integer.parseInt(mese);
		annoInt = Integer.parseInt(anno);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, annoInt);
		c.set(Calendar.MONTH, meseInt - 1);
		c.set(Calendar.DAY_OF_MONTH, giornoInt);
		return c.getTime();
	}

	/**
	 * Trafsforma una data tipo Tue Nov 25 00:00:00 CET 2014 in 25/11/2014 <br>
	 * 
	 * Il metodo e' null safe
	 * 
	 * @param fromDate
	 *            Data da trasformare
	 * @return data in formato stringa, se la data in ingresso e' null
	 *         restituisce un stringa vuota
	 */
	public static String dateStringFromDate(Date fromDate) {
		if (fromDate == null)
			return "";
		int giornoInt, meseInt, annoInt;
		giornoInt = fromDate.getDate();
		meseInt = fromDate.getMonth();
		annoInt = fromDate.getYear();
		meseInt++;
		annoInt += 1900;
		StringBuilder dateString = new StringBuilder().append(giornoInt).append("/").append(meseInt).append("/").append(annoInt);
		return dateString.toString();
	}

	/**
	 * Trafsforma una data tipo Tue Nov 25 11:12:43 CET 2014 in 25/11/2014 11:12
	 * 
	 * @param fromDate
	 * @return data in formato stringa
	 */
	public static String dateToString(Date data) {

		int anno, mesi, giorni, ore, minuti, secondi;

		anno = data.getYear();
		mesi = data.getMonth();
		giorni = data.getDate();
		ore = data.getHours();
		minuti = data.getMinutes();
		mesi++;
		anno += 1900;

		StringBuilder orario = new StringBuilder().append(giorni).append("/").append(mesi).append("/").append(anno).append(" ").append(ore).append(":").append(minuti);

		return orario.toString();
	}

	public static Date StringToDate(String data) {

		int anno, mesi, giorni, ore, minuti, secondi;
		Date date = new Date();

		String[] temp = data.split(" ");

		String[] gma = temp[0].split("/");
		String[] oms = temp[1].split(":");

		date.setDate(Integer.parseInt(gma[0]));
		date.setMonth(Integer.parseInt(gma[1]) - 1);
		date.setYear(Integer.parseInt(gma[2]) - 1900);
		date.setHours(Integer.parseInt(oms[0]));
		date.setMinutes(Integer.parseInt(oms[1]));

		return date;

	}

	public static Date convertToDate(String dateStr, String format, boolean addHours) throws ParseException {
		Date date = null;

		if (dateStr != null && !dateStr.equals("")) {
			if (addHours)
				dateStr += " 23:59:59";

			date = new SimpleDateFormat(format).parse(dateStr);
		}
		return date;
	}
	

	public static String convertToString(Date date, String format) throws ParseException {

		String dateStr = "";

		if (date != null) {
			dateStr = new SimpleDateFormat(format).format(date);
		}
		return dateStr;
	}

	/*
	 * I campi coinvolti nella tabella PROFILI_RICHIESTA sono : ID_RICHIESTA,
	 * CODICE_APPLICAZIONE, CODICE_ROLE_GROUP, CODICE_PROFILO, CODICE_UFFICIO,
	 * CODICE_CDR, TIPO_ABILITAZIONE, OPERAZIONE_SUL_PROFILO,
	 * OPERAZIONE_ESEGUITA, DATA_SCADENZA Il campo STRUTTURA_GESTORE o
	 * CF_GESTORE_EFFETTIVO saranno alternativamente valorizzati solo in caso di
	 * applicazioni non Integrate (il valore del campo SINCRONIZZATO_CAU nella
	 * tabella APPLICAZIONI Ã¨ pari a â€˜NOâ€™) Attenzione â€“
	 * nei casi da 1 a 3 della sezione GESTORI OPERATORI PER APPLICAZIONI NON
	 * INTEGRATE, si imposta nella tabella REGISTRO_RICHIESTE
	 * STRUTTURA_GESTORE_OPERATORI e a null il CDR_GESTORE_OPERATORI, nel caso 4
	 * a null sia STRUTTURA_GESTORE_OPERATORI che il CDR_GESTORE_OPERATORI, nel
	 * caso 5 si imposta CDR_GESTORE_OPERATORI e a null il
	 * STRUTTURA_GESTORE_OPERATORI Lo stato della richiesta sulla tabella
	 * REGISTRO-RICHIESTE Ã¨ sempre INS e sarÃ  interrogato il modulo di
	 * gestione delle richieste che si preoccupa di : chiamare il motore di
	 * workflow aggiornare la base dati di siga con le variazioni di stato
	 * associare i profili al dipendente (tabelle profili attivi e storico
	 * richieste) chiamare i servizi cau di profilazione degli utenti Nel
	 * chiamare il gestore delle richieste dobbiamo passargli: id-richiesta, cf
	 * di chi sta facendo lâ€™operazione.
	 */

	/*
	 * I campi coinvolti nella tabella REGISTRO_RICHIESTE sono: ID_RICHIESTA,
	 * TIPO_RICHIESTA (=P), ITER_ID, DATA_RICHIESTA CODICE_AMBITO, CODICE_APP,
	 * CF_UTENTE, CDR_UTENTE, CF_RICHIEDENTE, CDR_RICHIEDENTE, NOTE_RICHIEDENTE,
	 * CDR_AUTORIZZATORE_1, CF_AUTORIZZATORE_1, CDR_AUTORIZZATORE_2,
	 * CF_AUTORIZZATORE_2, STRUTTURA_GESTORE_OPERATORI, CDR_GESTORE_OPERATORI,
	 * STATO_RICHIESTA ESITO FINALE (da impostare a allâ€™atto di creazione
	 * della richiesta), DATA ESITO FINALE (da impostare a â€œnullâ€e'
	 * allâ€™atto di creazione della richiesta), DATA PRESA VISIONE (da
	 * impostare a â€œnullâ€e' allâ€™atto di creazione della
	 * richiesta),
	 */
	public static List<ProfiloRichiestaBean> convertFrom(List<ProfiloRichiestaBean> fromList, OperatoreBean moduloApp) {
		List<ProfiloRichiestaBean> newList = new ArrayList<ProfiloRichiestaBean>();
		for (ProfiloRichiestaBean bean : fromList) {
			ProfiloRichiestaBean newBean = convertFrom(bean, moduloApp);
			newList.add(newBean);
		}
		return newList;
	}

	private static ProfiloRichiestaBean convertFrom(ProfiloRichiestaBean fromBean, OperatoreBean operatoreBean) {
		ProfiloRichiestaBean newBean = new ProfiloRichiestaBean();

		// id richiesta lo setto con la sequence
		newBean.setCodiceApplicazione(fromBean.getCodiceApplicazione());
		newBean.setCodiceRoleGroup(fromBean.getCodiceRoleGroup());
		newBean.setCodiceProfilo(fromBean.getCodiceProfilo());
		newBean.setApplicazioneDesc(fromBean.getApplicazioneDesc());
		newBean.setRoleGroupDesc(fromBean.getRoleGroupDesc());
		newBean.setProfiloDesc(fromBean.getProfiloDesc());
		newBean.setDataScadenza(fromBean.getDataScadenza());
		newBean.setTipoAbilitazione(fromBean.getTipoAbilitazione());
		newBean.setTipoUfficio(fromBean.getTipoUfficio());
		newBean.setOperazione(fromBean.getOperazione());
		newBean.setCodiceUfficio(fromBean.getCodiceUfficio());

		return newBean;
	}

	public static List<RichiestaBean> convertFrom(List<IterBean> fromList, ModuloAppBean moduloApp) throws ParseException {
		List<RichiestaBean> newList = new ArrayList<RichiestaBean>();
		for (IterBean iterBean : fromList) {
			RichiestaBean newBean = convertFrom(iterBean, moduloApp);
			newList.add(newBean);
		}
		return newList;
	}

	private static RichiestaBean convertFrom(IterBean iterBean, ModuloAppBean moduloApp) throws ParseException {
		RichiestaBean newBean = new RichiestaBean();
		boolean delegato = false;
		String cfDelegante = moduloApp.getDeleganteRichUtente();

		// id richiesta lo setto con la sequence
		newBean.setTipoRichiesta("P");
		newBean.setIdIter(Integer.parseInt(iterBean.getId()));
		newBean.setDataRichiesta(new java.sql.Date((Calendar.getInstance().getTimeInMillis())));
		newBean.setCodiceAmbito(Integer.parseInt(moduloApp.getCodiceAmbitoApplicativo()));
		newBean.setCodiceApp(iterBean.getProfiloList().get(0).getCodiceApplicazione());
		newBean.setCfUtente(moduloApp.getCf());

		if (moduloApp.getCdrAssegnazione() != null && !moduloApp.getCdrAssegnazione().equals(""))
			newBean.setCdrUtente(moduloApp.getCdrAssegnazione());
		else
			newBean.setCdrUtente(moduloApp.getCdr());

		if (cfDelegante != null && !"".equalsIgnoreCase(cfDelegante)) {
			delegato = true;
		}

		// se caso puntuale cdr richiedente va a NULL altrimenti ci va il cdr
		// operatore selezionato
		String moduloAppFlag = moduloApp.getFlag();
		Logg.getLogger().info("ModuloAppFlag: " + moduloAppFlag);

		if (delegato) {
			Logg.getLogger().info("CASO DELEGATO: CF_RICHIEDENTE = CF_DELEGANTE");
			newBean.setCfRichiedente(cfDelegante);
		}

		if (Utils.isPuntualita(moduloAppFlag)) {
			if (delegato) {
				Logg.getLogger().info("CASO NON PUNTUALITA' - DELEGATO: Imposto CDR_RICHIEDENTE = CDR DI DOVE IL DELEGANTE E' RICHIEDENTE ");
				newBean.setCdrRichiedente(moduloApp.getCdrDeleganteRichUtente());
			} else {
				Logg.getLogger().info("CASO PUNTUALITA' - TITOLARE DEL RUOLO: CDR_RICHIEDENTE IS NULL");
				newBean.setCdrRichiedente(null);
			}
		} else {
			Logg.getLogger().info("CASO NON PUNTUALITA' : Imposto CDR_RICHIEDENTE = CDR_OPERATORE_SELEZIONATO");
			newBean.setCdrRichiedente(moduloApp.getCdr());
		}

		// cf utente effettivo -> cf utente in sessione
		newBean.setCfRichiedenteEffettivo(moduloApp.getCodFiscaleUtente());

		// se in struttura iter cf I liv != null setCfAutorizzatoreI a null
		// se in struttura iter cf I liv == nul 3 casi in struttura iter:
		// controllo su 3 campi:
		// ITER_STANDARD
		// TIPO_CDR_AUTORIZ_I_LIV
		// CDR_AUTORIZ_I_LIV
		// se CDR_AUTORIZ_I_LIV != null tengo quello
		// se TIPO_CDR_AUTORIZ_I_LIV != null
		if (iterBean.getAutorizzatore_I_Liv() != null) {
			newBean.setCfAutorizzatoreI(iterBean.getAutorizzatore_I_Liv().getCf());
			/* se iter standard cdr auth I liv va a null */
			/**
			 * commentato da Filippo dopo le segnalazioni del 20/11/2014
			 */
			// if(iterBean.getId().compareToIgnoreCase("0")!=0)
			newBean.setCdrAutorizzatoreI(iterBean.getAutorizzatore_I_Liv().getCdr());
		}
		if (iterBean.getAutorizzatore_II_Liv() != null) {
			newBean.setCfAutorizzatoreII(iterBean.getAutorizzatore_II_Liv().getCf());
			newBean.setCdrAutorizzatoreII(iterBean.getAutorizzatore_II_Liv().getCdr());
		}
		if (iterBean.getGestoreOperatori() != null) {
			Logg.getLogger().info("Ho un Gestore Operatori => setto i parametri rtichiesti");
			/* setta variabili a seconda di quale dei 4 casi */
			newBean.setCfGestoreOperatoriEffettivo(iterBean.getGestoreOperatori().getCf());
			newBean.setCdrGestoreOperatori(iterBean.getGestoreOperatori().getCdr());
			newBean.setStrutturaGestoreOperatori(iterBean.getGestoreOperatori().getStruttura());
		}

		newBean.setStatoRichiesta("INS");
		newBean.setEsitoFinale(null);
		newBean.setDataEsitoFinale(null);
		newBean.setDataPresaVisione(null);

		newBean.setListProfili(convertFromPBList(iterBean.getProfiloList(), moduloApp));

		newBean.setFormFile(iterBean.getMyFormFiles());

		newBean.setNoteRichiedente(iterBean.getNota());
		newBean.setTipoAbilitazione(newBean.getTipoAbilitazione() == null || "".equals(newBean.getTipoAbilitazione()) ? Constants.O : newBean.getTipoAbilitazione());
		return newBean;
	}

	private static List<ProfiloRichiestaBean> convertFromPBList(List<ProfiloBean> fromList, ModuloAppBean moduloApp) throws ParseException {
		List<ProfiloRichiestaBean> newList = new ArrayList<ProfiloRichiestaBean>();
		ProfiloRichiestaBean newBean = new ProfiloRichiestaBean();
		for (ProfiloBean profiloBean : fromList) {
			newBean = (ProfiloRichiestaBean) profiloBean;
			String scadenza = convertToString(profiloBean.getDataScadenza(), "dd/MM/yyyy");
			newBean.setDataScadenza(convertToDate(scadenza, "dd/MM/yyyy hh:mm:ss", true));
			newBean.setCfUtenteLoggato(moduloApp.getCodFiscaleUtente());
			newBean.setCfUtente(moduloApp.getCf());
			newList.add(newBean);
		}
		return newList;
	}

	/** LISTE **/
	public static List<ProfiloBean> prepareListForCaricaDaTemplate(List<ProfiloBean> risultatoList) {
		for (ProfiloBean profiloBean : risultatoList) {
			profiloBean.setUnselectable(true);
			profiloBean.setHideCheckbox(false);
			profiloBean.setExpanded(true);
		}
		return risultatoList;
	}

	public static List<ProfiloBean> prepareListDaAggiungereForCaricaDaTemplate(List<ProfiloBean> risultatoList) {
		for (ProfiloBean profiloBean : risultatoList) {
			profiloBean.setOp(ProfiloBean.toAddOp);
			profiloBean.setOperazione(ProfiloBean.aggiunto);
			profiloBean.setSelected(true);
			/*
			 * per attivarli nell'albero in quanto su questi profili va fatta op
			 * di abilitazione
			 */
			profiloBean.setAttivo("SI");
		}
		return risultatoList;
	}

	public static List<ProfiloBean> prepareListDaRimuovereForCaricaDaTemplate(List<ProfiloBean> risultatoList) {
		for (ProfiloBean profiloBean : risultatoList) {
			profiloBean.setOp(ProfiloBean.toRemoveOp);
			profiloBean.setOperazione(ProfiloBean.rimosso);
			/*
			 * per disattivarli nell'albero in quanto su questi profili non va
			 * fatta nessuna op
			 */
			profiloBean.setAttivo("NO");
		}
		return risultatoList;
	}

	public static List<ProfiloBean> prepareListAttiviForCaricaDaTemplate(List<ProfiloBean> risultatoList) {
		for (ProfiloBean profiloBean : risultatoList) {
			profiloBean.setOp(ProfiloBean.attivoNoOP);
		}
		return risultatoList;
	}

	public static List<ProfiloBean> prepareListNonAttiviForCaricaDaTemplate(List<ProfiloBean> risultatoList) {
		for (ProfiloBean profiloBean : risultatoList) {
			profiloBean.setOp(ProfiloBean.attivoNoOP);
			/*
			 * per disattivarli nell'albero in quanto su questi profili non va
			 * fatta nessuna op
			 */
			profiloBean.setAttivo("NO");
		}
		return risultatoList;
	}

	public static List<ProfiloBean> prepareListAmmissibiliForCaricaDaTemplate(List<ProfiloBean> risultatoList) {
		return prepareListDisattivaProfili(risultatoList);
	}

	public static List<ProfiloBean> prepareListDisattivaProfili(List<ProfiloBean> risultatoList) {
		for (ProfiloBean profiloBean : risultatoList) {
			profiloBean.setAttivo("NO");
		}
		return risultatoList;
	}

	/**
	 * Metodo di TEST
	 * 
	 * @param risultatoList
	 * @return lista profili attivi
	 */
	public static List<ProfiloBean> prepareListAttivaProfili(List<ProfiloBean> risultatoList) {
		for (ProfiloBean profiloBean : risultatoList) {
			profiloBean.setAttivo("SI");
		}
		return risultatoList;
	}

	/**
	 * Rende tutti i profili della lista selezionabili
	 * 
	 * @param risultatoList
	 * @return
	 */
	public static List<ProfiloBean> selezionabili(List<ProfiloBean> risultatoList) {
		for (ProfiloBean profiloBean : risultatoList) {
			profiloBean.setSelected(true);
			profiloBean.setUnselectable(false);
		}
		return risultatoList;
	}

	/**
	 * Rende tutti i profili della lista non selezionabili
	 * 
	 * @param risultatoList
	 * @return
	 */
	public static List<ProfiloBean> setListUnselectable(List<ProfiloBean> risultatoList) {
		for (ProfiloBean profiloBean : risultatoList) {
			profiloBean.setSelected(false);
			profiloBean.setUnselectable(true);
		}
		return risultatoList;
	}

	/**
	 * Flagga i profili da attivare
	 * 
	 * @param risultatoList
	 * @return
	 */
	public static List<ProfiloBean> setListSelectedByCodes(List<String> codiciProfiliDaAttivare, List<ProfiloBean> risultatoList) {
		for (ProfiloBean profiloBean : risultatoList) {
			if (codiciProfiliDaAttivare.contains(profiloBean.getCodiceProfilo())) {
				profiloBean.setAttivo("SI");
			}
		}
		return risultatoList;
	}

	/**
	 * Setta node.data.op a "Disabilitazione" o "Abilitazione" Flagga i profili
	 * da attivare
	 * 
	 * @param risultatoList
	 * @return
	 */
	public static List<ProfiloBean> setListDisabledByCodesEnabled(List<String> codiciDaNonDisabilitare, List<ProfiloBean> risultatoList) {
		for (ProfiloBean profiloBean : risultatoList) {
			if (!codiciDaNonDisabilitare.contains(profiloBean.getCodiceProfilo())) {
				profiloBean.setOp(ProfiloBean.rimosso);
			} else {

				profiloBean.setOp(ProfiloBean.aggiunto);
			}
		}
		return risultatoList;
	}

	/**
	 * Setta tutti i node.data.op a "Abilitazione" Flagga i profili da attivare
	 * 
	 * @param risultatoList
	 * @return
	 */
	public static List<ProfiloBean> setListEnabled(List<ProfiloBean> risultatoList) {
		for (ProfiloBean profiloBean : risultatoList) {
			profiloBean.setSelected(false);
			profiloBean.setUnselectable(true);
			profiloBean.setOp(ProfiloBean.aggiunto);
		}
		return risultatoList;
	}

	public static List<ProfiloBean> prepareListForNonAmmissibili(List<ProfiloBean> nonAmmissibili) {
		for (ProfiloBean profiloBean : nonAmmissibili) {
			profiloBean.setProfileNodeTitle(new StringBuilder(profiloBean.getProfileNodeTitle()).append("<span class='profile-element-on-the-right'><i>" + Constants.PROFILO_NON_RICHIEDIBILE_X_CDR + "</i></span>").toString().toString());
		}
		return nonAmmissibili;
	}

	public static List<ProfiloBean> differenzaListe(List<ProfiloBean> minuendo, List<ProfiloBean> sottraendoList) {
		List<ProfiloBean> differenza = new ArrayList<ProfiloBean>(minuendo);
		differenza.removeAll(sottraendoList);
		return differenza;
	}

	public static List<ProfiloBean> differenzaListe2(List<ProfiloBean> minuendo, List<ProfiloBean> sottraendoList) {
		List<ProfiloBean> differenza = new ArrayList<ProfiloBean>(minuendo);
		ListIterator<ProfiloBean> listItr = differenza.listIterator();
		while (listItr.hasNext()) {
			ProfiloBean p = listItr.next();
			for (ProfiloBean profiloBean : sottraendoList) {
				if (p.compareTo(profiloBean) == 0) {
					listItr.remove();
					Logg.getLogger().info("OGGETTO TROVATO E RIMOSSO");
					break;
				}
			}
		}
		return differenza;
	}

	public static <T> List<T> intersectT(List<T> a, List<T> b) {
		List<T> lstIntersectAB = new ArrayList<T>(a);
		lstIntersectAB.retainAll(b);
		return lstIntersectAB;
	}

	public List<ProfiloBean> intersect(List<ProfiloBean> a, List<ProfiloBean> b) {
		List<ProfiloBean> lstIntersectAB = new ArrayList<ProfiloBean>(a);
		lstIntersectAB.retainAll(b);
		return lstIntersectAB;
	}

	public static List<ProfiloBean> intersect2(List<ProfiloBean> a, List<ProfiloBean> b) {
		List<ProfiloBean> lstIntersectAB = new ArrayList<ProfiloBean>();
		for (ProfiloBean profiloBean : a) {
			for (ProfiloBean profiloBean1 : b) {
				if (profiloBean1.compareTo(profiloBean) == 0) {
					Logg.getLogger().info("TROVATO UN BEAN INTERSEZIONE");
					lstIntersectAB.add(profiloBean1);
					break;
				}
			}
		}
		return lstIntersectAB;
	}

	public static void assegnaListeAttualiAggiuntiERimossi(List<ProfiloTreeBean> listConverted, List<ProfiloTreeBean> listProfileAdded, List<ProfiloTreeBean> listProfileRemoved, List<ProfiloTreeBean> listProfiliAttuali, int profilazione) {
		switch (profilazione) {
		case Constants.profilazione_standard:
			assegnaListeAttualiAggiuntiERimossi(listConverted, listProfileAdded, listProfileRemoved, listProfiliAttuali);
			break;
		case Constants.profilazione_carica_da_operatore:
		case Constants.profilazione_carica_da_template:
		case Constants.profilazione_solo_visualizzazione:
			assegnaListeAttualiAggiuntiERimossiCDT(listConverted, listProfileAdded, listProfileRemoved, listProfiliAttuali);
			break;

		default:
			break;
		}
	}

	/**
	 * Assegna le liste varie sulla base delle selezioni fatte (campi
	 * startSelected e selected)
	 * 
	 * @param listConverted
	 * @param listProfileAdded
	 * @param listProfileRemoved
	 * @param listProfiliAttuali
	 */
	public static void assegnaListeAttualiAggiuntiERimossi(List<ProfiloTreeBean> listConverted, List<ProfiloTreeBean> listProfileAdded, List<ProfiloTreeBean> listProfileRemoved, List<ProfiloTreeBean> listProfiliAttuali) {
		for (ProfiloTreeBean profiloTreeBean : listConverted) {
			// ///////// NEW IMPL /////////////////
			boolean selected = profiloTreeBean.isSelected();
			boolean startSelected = profiloTreeBean.isStartSelected();
			/* se startSelected allora il profilo fa parte dei profili attuali */
			if (startSelected) {
				listProfiliAttuali.add(profiloTreeBean);
			}
			/*
			 * se startSelected && selected il profilo inizia selezionato e
			 * rimane selezionato quindi non Ã¨p stato rimosso: non facciamo
			 * nulla, ma lo includiamo solo nei profili attuali (vedi sopra)
			 */
			// if(startSelected && selected){
			// do nothing
			// }
			/*
			 * se Ã¨ selezionato e non era inizialmente selezionato vuol dire
			 * che Ã¨ stato aggiunto
			 */
			if (selected && !startSelected) {
				listProfileAdded.add(profiloTreeBean);
			}
			/*
			 * se parte selezionato e non Ã¨ selezionato vuol dire che Ã¨
			 * stato deselezionato e quindi va rimosso
			 */
			if (startSelected && !selected) {
				listProfileRemoved.add(profiloTreeBean);
			}
		}
	}

	/**
	 * Assegna le liste in base al campo op: questo contiene l'operazione da
	 * fare sul bean.
	 * 
	 * @param listConverted
	 * @param listProfileAdded
	 * @param listProfileRemoved
	 * @param listProfiliAttuali
	 */
	public static void assegnaListeAttualiAggiuntiERimossiCDT(List<ProfiloTreeBean> listConverted, List<ProfiloTreeBean> listProfileAdded, List<ProfiloTreeBean> listProfileRemoved, List<ProfiloTreeBean> listProfiliAttuali) {
		for (ProfiloTreeBean profiloTreeBean : listConverted) {
			if (profiloTreeBean.getOp() == null) {
				// do nothing
				Logg.getLogger().info("profiloTreeBean.getOp() IS NULL");
			} else if (profiloTreeBean.getOp().equals(ProfiloBean.toAddOp)) {
				listProfileAdded.add(profiloTreeBean);
			} else if (profiloTreeBean.getOp().equals(ProfiloBean.toRemoveOp)) {
				listProfileRemoved.add(profiloTreeBean);
				// se è da rimuovere aggiungilo a quelli che ora sono attivi
				listProfiliAttuali.add(profiloTreeBean);
			} else if (profiloTreeBean.getOp().equals(ProfiloBean.attivoNoOP)) {
				listProfiliAttuali.add(profiloTreeBean);
			}
		}
	}

	public static AutorizzatoreBean convert(GetAutorizzatore_I_LivBean fromBean) {
		AutorizzatoreBean newBean = new AutorizzatoreBean();

		newBean.setCognome(fromBean.getCognome());
		newBean.setNome(fromBean.getNome());
		newBean.setCdr(fromBean.getCodice_cdr());
		newBean.setCf(fromBean.getCodice_fiscale());

		return newBean;
	}

	public static String treeCharOrEmptyByString(String stringa) {
		if (stringa != null && stringa.length() > 0)
			if (stringa.length() > 2)
				// stringa = stringa.substring(0, 3);
				return stringa;
			else
				stringa = "";
		return stringa;
	}

	public static String twoCharOrEmptyByString(String stringa) {
		if (stringa != null && stringa.length() > 0)
			if (stringa.length() > 1) {
				if (stringa.length() > 2)
					// stringa = stringa.substring(0, 3);
					return stringa;
			} else
				stringa = "";
		return stringa;
	}

	public static List<ProfiloBean> ordinaElencoProfiliConISenzaRoleGroupInFondo(List<ProfiloBean> elenco) {
		List<ProfiloBean> noRoleGroupList = new ArrayList<ProfiloBean>();

		// ricavo i bean senza rg; li metto da parte e li tolgo da elenco
		if (elenco != null) {
			ListIterator<ProfiloBean> itr = elenco.listIterator();
			while (itr.hasNext()) {
				ProfiloBean bean = itr.next();
				// aggiunto la condizione che metto il profilo tra quelli senza
				// role group se ha descrizione NULL
				if (bean.getRoleGroupDesc() == null || bean.getRoleGroupDesc().equals("")) {
					bean.setCodiceRoleGroup(Constants.CODICE_ROLE_GROUP_NO_ROLE_GROUP);
					noRoleGroupList.add(bean);
					itr.remove();
					Logg.getLogger().info("TROVATO UN BEAN SENZA RG");
				}
			}
			// riaggiungo i bean senza rg
			elenco.addAll(noRoleGroupList);
		}
		return elenco;
	}

	public static List<ProfiloBean> ordinaElencoProfiliPerRoleGroup(List<ProfiloBean> elenco) {
		Collections.sort(elenco, new Comparator<ProfiloBean>() {
			@Override
			public int compare(ProfiloBean object1, ProfiloBean object2) {
				if (object1 == null || object2 == null)
					return 0;
				else
					return object1.getRoleGroupDesc().compareToIgnoreCase(object2.getRoleGroupDesc());
			}
		});
		return elenco;
	}

	public static List<ProfiloTreeBean> ordinaElencoProfiliPerProfiloDesc(List<ProfiloTreeBean> elenco) {
		Collections.sort(elenco, new Comparator<ProfiloTreeBean>() {

			@Override
			public int compare(ProfiloTreeBean object1, ProfiloTreeBean object2) {
				if (object1 == null || object2 == null)
					return 0;
				else
					return object1.getProfiloDesc().compareToIgnoreCase(object2.getProfiloDesc());
			}

		});
		return elenco;
	}

	// public static List<ProfiloBean>
	// ordinaElencoProfiliPerApplicazioniRoleGroupProfili(List<ProfiloBean>
	// elenco){
	public static List<ProfiloBean> ordinaElencoProfiliPerRoleGroupProfili(List<ProfiloBean> elenco) {
		Collections.sort(elenco, new Comparator<ProfiloBean>() {
			@Override
			public int compare(ProfiloBean object1, ProfiloBean object2) {

				if (object1 == null || object2 == null)
					return 0;
				else {
					if (object1.getRoleGroupDesc()!=null && object2.getRoleGroupDesc()!=null && object1.getRoleGroupDesc().compareToIgnoreCase(object2.getRoleGroupDesc()) != 0) {
						return object1.getRoleGroupDesc().compareToIgnoreCase(object2.getRoleGroupDesc());
					} else if (object1.getProfiloDesc()!=null && object2.getProfiloDesc()!=null && object1.getProfiloDesc().compareToIgnoreCase(object2.getProfiloDesc()) != 0) {
						return object1.getProfiloDesc().compareToIgnoreCase(object2.getProfiloDesc());
					}
					return 0;
				}
			}
		});
		return elenco;
	}
	
	public static List<AbilitazioneBean> ordinaElencoPerRoleGroupProfili(List<AbilitazioneBean> elenco) {
		Collections.sort(elenco, new Comparator<AbilitazioneBean>() {
			@Override
			public int compare(AbilitazioneBean object1, AbilitazioneBean object2) {

				if (object1 == null || object2 == null)
					return 0;
				else {
					if (object1.getRoleGroupDesc().compareToIgnoreCase(object2.getRoleGroupDesc()) != 0) {
						return object1.getRoleGroupDesc().compareToIgnoreCase(object2.getRoleGroupDesc());
					} else if (object1.getProfiloDesc().compareToIgnoreCase(object2.getProfiloDesc()) != 0) {
						return object1.getProfiloDesc().compareToIgnoreCase(object2.getProfiloDesc());
					}
					return 0;
				}
			}
		});
		return elenco;
	}

	public static List<ProfiloBean> ordinaElencoProfiliRoleGroupProfili(List<ProfiloBean> elenco) {
		Collections.sort(elenco, new Comparator<ProfiloBean>() {
			@Override
			public int compare(ProfiloBean object1, ProfiloBean object2) {
				if (object1 == null || object2 == null)
					return 0;
				else {
					if (object1.getRoleGroupDesc().compareToIgnoreCase(object2.getRoleGroupDesc()) != 0) {
						return object1.getRoleGroupDesc().compareToIgnoreCase(object2.getRoleGroupDesc());
					} else if (object1.getProfiloDesc().compareToIgnoreCase(object2.getProfiloDesc()) != 0) {
						return object1.getProfiloDesc().compareToIgnoreCase(object2.getProfiloDesc());
					}
					return 0;
				}
			}
		});
		return elenco;
	}

	public static List<ProfiloBean> filtraProfiliByCodUfficio(List<ProfiloBean> elenco, String codUfficio) {

		if (elenco != null) {
			ListIterator<ProfiloBean> itr = elenco.listIterator();
			while (itr.hasNext()) {
				ProfiloBean bean = itr.next();
				if (bean != null && bean.getExplicit_entitlement() != null && bean.getExplicit_entitlement().equals(codUfficio)) {
					itr.remove();
					Logg.getLogger().info("RIMOSSO PROFILO CON EXPLICIT ETITLEMENT con COD UFFICIO = " + codUfficio);
				}
			}
		}
		return elenco;
	}

	/**
	 * Formatta il titolo dei profili dell'albero:
	 * 
	 * Uso: Se il profilo è speciale e il titolo è: Titolo Profilo Il titolo
	 * deve diventare Titolo Profilo Speciale con Speciale in fondo a destra
	 * 
	 * @param stringa
	 * @return titolo formattato
	 */

	/**
	 * 
	 * Formatta il titolo dei profili dell'albero:
	 * 
	 * Uso: Se il profilo è speciale e il titolo è: Titolo Profilo Il titolo
	 * deve diventare Titolo Profilo Speciale - 13/04/2014 con Speciale + data
	 * scadenza in fondo a destra
	 * 
	 * @param numeroCaratteri
	 *            il numero di caratteri complessivi di tutta la stringa
	 * @param stringa
	 *            la stringa da formattare
	 * @param stringaADestra
	 *            ciò che va a detsra
	 * @return stringa formattata
	 */
	public static String calcolaStringaFormattata(int numeroSpazi, String stringa, String stringaADestra) {

		Formatter formatter = new Formatter(new StringBuilder(), Locale.ITALY);
		int rightPadding = numeroSpazi - stringa.length();
		StringBuilder spazi = new StringBuilder("");
		for (int i = 0; i < rightPadding; i++) {
			spazi.append("&nbsp;");
		}
		String patternForRightPadding1 = "%1$s";
		String patternForRightPadding2 = "%2$s";
		formatter.format(patternForRightPadding1 + spazi + patternForRightPadding2, stringa, stringaADestra);
		String ris = formatter.toString();
		formatter.close();
		return ris;
	}

	public static String getFlagFromCDRList(List<CDRBean> elencoCDR, String cdr) {
		String flag = null;
		for (CDRBean bean : elencoCDR) {
			if (bean.getCodiceCDR() != null && bean.getCodiceCDR().equalsIgnoreCase(cdr)) {
				flag = bean.getFlag();
				break;
			}
		}
		return flag;
	}

	public static Set<String> getFlagsFromCDRList(List<CDRBean> elencoCDR, String cdr) {
		Set<String> flags = new HashSet<String>();
		if (elencoCDR != null) {
			for (CDRBean bean : elencoCDR) {
				if (bean.getCodiceCDR() != null && bean.getCodiceCDR().equalsIgnoreCase(cdr)) {
					flags.add(bean.getFlag());
				}
			}
		}
		return flags;
	}

	public static String getFlagFromCDRListByCDR(List<CDRBean> elencoCDR, String cdr) {
		String flag = null;
		for (CDRBean bean : elencoCDR) {
			if (bean.getCodiceCDR() != null && bean.getCodiceCDR().equalsIgnoreCase(cdr)) {
				flag = bean.getFlag();
				break;
			}
		}
		return flag;
	}

	/**
	 * Dato un arry di elementi restituisce un array contente solo elementi non
	 * duplicati
	 * 
	 * @param s
	 * @return
	 */
	public static String[] rimuoviDuplicati(String[] s) {
		Set<String> noDuplicate = new HashSet<String>();

		for (int i = 0; i < s.length; i++) {
			noDuplicate.add(s[i]);
		}
		String[] senzaDuplicati = new String[noDuplicate.size()];
		int i = 0;
		for (String string : noDuplicate) {
			senzaDuplicati[i] = string;
			i++;
		}
		return senzaDuplicati;
	}

	public static List<CDRBean> eliminaDupicatiPerCDR(List<CDRBean> lista) {
		for (int i = 0; i < lista.size(); i++) {
			CDRBean currObj = (CDRBean) lista.get(i);
			for (int k = i + 1; k < lista.size(); k++) {
				CDRBean tmpObj = (CDRBean) lista.get(k);
				if (currObj.getCodiceCDR().equalsIgnoreCase((tmpObj.getCodiceCDR()))) {
					lista.remove(k);
					k--;
				}
			}
		}
		return lista;
	}

	public static boolean verifySelectedCdrOpeAssociati(List<CDRBean> lista, String cdrSelezionato) {
		boolean result = false;

		for (int i = 0; i < lista.size(); i++) {
			CDRBean currObj = (CDRBean) lista.get(i);

			if (currObj.getCodiceCDR().equals(cdrSelezionato) && (currObj.getFlag().equals(Flags.CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_INT) || (currObj.getFlag().equals(Flags.CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO_INT))))
				result = true;

		}

		return result;
	}

	public static List<CDRBean> eliminaDupicatiByCDR(List<CDRBean> lista, String cdr) {
		if (lista != null && !lista.isEmpty() && cdr != null && !cdr.equals("")) {
			Iterator<CDRBean> itr = lista.iterator();
			while (itr.hasNext()) {
				CDRBean bean = itr.next();
				if (bean.getCodiceCDR().equals(cdr)) {
					itr.remove();
				}
			}
		}
		return lista;
	}

	public static List<ProfiloBean> eliminaDupicatiProfili(List<ProfiloBean> lista) {
		for (int i = 0; i < lista.size(); i++) {
			ProfiloBean currObj = (ProfiloBean) lista.get(i);
			for (int k = i + 1; k < lista.size(); k++) {
				ProfiloBean tmpObj = (ProfiloBean) lista.get(k);
				if (currObj.equals(tmpObj)) {
					lista.remove(k);
					k--;
				}
			}
		}
		return lista;
	}

	public static boolean isCDRInCDRList(List<CDRBean> elencoCDR, String cdr) {
		boolean response = false;
		for (CDRBean bean : elencoCDR) {
			if (bean.getCodiceCDR() != null && bean.getCodiceCDR().equalsIgnoreCase(cdr)) {
				response = true;
				break;
			}
		}
		return response;

	}

	public static String getDescrizioneCDRFromCDRList(List<CDRBean> elencoCDR, String codiceCDR) {
		for (CDRBean bean : elencoCDR) {
			if (bean.getCodiceCDR() != null && bean.getCodiceCDR().equalsIgnoreCase(codiceCDR)) {
				return bean.getDescrizioneCDR();
			}
		}
		return "";

	}

	public static boolean isCDRNonPuntuale(String flagCDR) {
		int flagInt = 0;
		if (flagCDR != null && !flagCDR.equals("")) // il cdr preso dalla query
													// deve avere il flag)
			flagInt = Integer.parseInt(flagCDR);
		if (flagInt != Flags.CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_INT && flagInt != Flags.CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_DELEGATO_INT && flagInt != Flags.CDR_DI_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_DELEGATO_INT && flagInt != Flags.CDR_OPERATORI_ASSOCIATI_PUNTUALMENTE_IN_VISIBILITA_INT) {
			return true;
		}
		return false;
	}

	public static boolean isCDRPuntuale(String flagCDR) {
		return (!isCDRNonPuntuale(flagCDR));
	}

	public static boolean isPuntualita(String flagCDR) {
		return isCDRPuntuale(flagCDR);
	}

	public static List<CDRBean> findCDRSubListPuntualita(List<CDRBean> fromList) {
		List<CDRBean> newList = new ArrayList<CDRBean>();
		for (CDRBean cdrBean : fromList) {
			if (isCDRPuntuale(cdrBean.getFlag())) {
				newList.add(cdrBean);
			}
		}
		return newList;
	}

	public static List<OperatoreBean> convertToList(List<Serializable> fromList, List<CDRBean> elencoCDR) {
		List<OperatoreBean> newList = new ArrayList<OperatoreBean>();
		for (Serializable o : fromList) {
			OperatoreBean s = (OperatoreBean) o;

			/* ricavarare il flag cdr dal cdr operatore */
			// ricavo cdr da operatore scelto
			String cdr = s.getCdr();
			String flag = null;
			flag = Utils.getFlagFromCDRList(elencoCDR, cdr);
			s.setFlag(flag);

			newList.add(s);
		}
		return newList;
	}

	public static List<ProfiloBean> convertToListProfiloBean(List<Serializable> fromList) {
		List<ProfiloBean> newList = new ArrayList<ProfiloBean>();
		for (Serializable o : fromList) {
			ProfiloBean s = (ProfiloBean) o;

			newList.add(s);
		}
		return newList;
	}

	public static List<IterBean> convertFromList(List<Serializable> fromList) {
		List<IterBean> newList = new ArrayList<IterBean>();
		for (Serializable object : fromList) {
			IterBean bean = (IterBean) object;
			newList.add(bean);
		}
		return newList;
	}

	public static List<OperatoreBean> convertToOperatoreBeanList(List<Serializable> fromList) {
		List<OperatoreBean> newList = new ArrayList<OperatoreBean>();
		for (Serializable o : fromList) {
			OperatoreBean s = (OperatoreBean) o;

			newList.add(s);
		}
		return newList;
	}

	/**
	 * cerca sincronizzataCAU dell'ambito in lista ambiti e lo setta in
	 * moduloApp
	 * 
	 * @param fromList
	 * @param codiceAmbito
	 * @param moduloApp
	 */
	public static void findAndSetSincronizzataCAUByAmbitoList(List<AmbitoBean> fromList, String codiceAmbito, ModuloAppBean moduloApp) {
		for (AmbitoBean ambitoBean : fromList) {
			if (ambitoBean.getCodiceAmbito().equals(codiceAmbito)) {
				moduloApp.setSincronizzataCAU(ambitoBean.getCodiceAmbito());
				break;
			}
		}
	}

	public static boolean isRichiedentePuntuale(String flag) {
		return isCDRPuntuale(flag);
	}

	public static List<AmbitoBean> aggiornaAmbitoList(List<AmbitoBean> elencoAmbito) {
		if (elencoAmbito != null && elencoAmbito.size() > 0) {
			for (AmbitoBean ambitoBean : elencoAmbito) {
				if (ambitoBean.getSelezionabile().equalsIgnoreCase("0")) {
					ambitoBean.setDescrizioneConcettuale(Constants.AMBITO_NON_RICHIEDIBILE);
				}
			}
		}
		return elencoAmbito;
	}

	public static boolean isAlmenoUnElementoSelezionabile(List<AmbitoBean> elencoAmbito) {
		boolean isAlmenoUnElementoSelezionabile = false;
		if (elencoAmbito != null && elencoAmbito.size() > 0) {
			for (AmbitoBean ambitoBean : elencoAmbito) {
				if (ambitoBean.getSelezionabile().equals("1")) {
					isAlmenoUnElementoSelezionabile = true;
					break;
				}
			}
		}
		return isAlmenoUnElementoSelezionabile;
	}

	public static List<CDRBean> filtraELencoPerFlags(List<CDRBean> elenco, int[] flags) {
		if (elenco != null && elenco.size() > 0) {
			Iterator itr = elenco.iterator();
			while (itr.hasNext()) {
				CDRBean bean = (CDRBean) itr.next();
				if (bean != null) {
					if (!isFlagInFlags(Integer.parseInt(bean.getFlag()), flags)) {
						itr.remove();
					}
				}
			}
		}
		return elenco;
	}

	private static boolean isFlagInFlags(int flag, int[] flags) {
		boolean trovato = false;
		for (int i = 0; i < flags.length; i++) {
			if (flags[i] == flag) {
				trovato = true;
				break;
			}
		}
		return trovato;
	}

	/**
	 * @author da.meloni
	 * @param modalitaAccesso
	 * @return String metodo attraverso il quale viene passato un titolo
	 *         dinamico alla pagina questo metodo viene usato per pagine che
	 *         fanno in pratica le stesse cose e si differenziano per altre
	 *         poche cose(tra cui appunto il titoloPagina del tiles-defs.xml)
	 **/
	public static String passDynamicTitleRegistro(int modalitaAccesso) {
		/**
		 * di default nel tiles defs il titoloPagina e' impostato inizialmente
		 * con "Registro"
		 **/
		String dynamicTitle = "";
		switch (modalitaAccesso) {
		case CostantiSessione.REGISTRO_VARIAZIONI_RUOLI_CDR:
			dynamicTitle = "Ruoli di un CDR";
			break;
		case CostantiSessione.REGISTRO_DELEGHE:
			dynamicTitle = "Deleghe";
			break;
		case CostantiSessione.REGISTRO_ASSOCIAZIONI_ORA:
			dynamicTitle = "delle associazioni rich. aut I liv. operatori";
			break;
		case CostantiSessione.REGISTRO_RICHIESTE_DI_UN_UFFICIO:
			dynamicTitle = "";// "delle richieste di un Ufficio";
			break;

		default:
			break;
		}
		return dynamicTitle;
	}

	public static List<StrutturaUfficioCDRExtBean> rimuoviCDRExtDuplicati(List<StrutturaUfficioCDRExtBean> listConDuplicati) {
		TreeSet treeSet = new TreeSet();
		List listaSenzaDuplicati = new ArrayList<StrutturaUfficioCDRExtBean>();

		for (StrutturaUfficioCDRExtBean x : listConDuplicati) {

			if (treeSet.add(x.getCodiceCDR())) {
				listaSenzaDuplicati.add(x);
			}
		}
		/* Lista pulita senza duplicati */
		return listaSenzaDuplicati;
	}

	/**
	 * Serve per convertire la sigla del ruolo in una stringa <BR>
	 * es. R -> Richiedente
	 * 
	 * @param ruolo
	 * @return
	 */
	public static String converRuolo(String ruolo) {
		if (ruolo.equalsIgnoreCase("RI"))
			return Constants.RI;
		else if (ruolo.equalsIgnoreCase("A1"))
			return Constants.A1;
		else if (ruolo.equalsIgnoreCase("A2"))
			return Constants.A2;
		return "Ruolo non trovato";

	}

	/**
	 * Valida la dimensione e il tipo di file caricato.
	 * 
	 * @param f
	 *            file caricato dall'utente
	 * @return <strong>stringa</strong> che identifica il parametro (da
	 *         iniettare in pagina)<br>
	 *         che permette di visualizzare il messagio di errore in
	 *         <strong>messages.jsp</strong>. La stringa sare' vuota se il file
	 *         caricato passa la validazione.
	 */
	public static String validazioneFileCaricato(FormFile f) {
		String errorMsg = "";
		if (f != null) {
			int fileSize = f.getFileSize();
			String fileContentType = f.getContentType();
			if (fileSize == 0) {
				errorMsg = "fileVuoto";
			} else if (fileSize > 3145728) { // 3 MB
				errorMsg = "fileDimensioneMassimaSuperata";
			} else if (!Constants.PDF_TYPE.equals(fileContentType) && !Constants.PLAIN_TEXT_TYPE.equals(fileContentType) && !Constants.CSV_TYPE.equals(fileContentType) && !Constants.P7MX.equals(fileContentType) && !Constants.P7M.equals(fileContentType) &&
			// !Constants.IMAGE_TIFF_TYPE.equals(fileContentType)&&
			// !Constants.IMAGE_BMP_TYPE.equals(fileContentType)&&
			// !Constants.IMAGE_PNG_TYPE.equals(fileContentType)&&
			// !Constants.IMAGE_JPEG_TYPE.equals(fileContentType)&&
			// !Constants.IMAGE_GIF_TYPE.equals(fileContentType)&&
					!Constants.OFFICE_DOC_TYPE.equals(fileContentType) && !Constants.OFFICE_DOCX_TYPE.equals(fileContentType) && !Constants.OFFICE_XLS_TYPE.equals(fileContentType) && !Constants.OFFICE_XSLX_TYPE.equals(fileContentType) &&
					// !Constants.OFFICE_PPT_TYPE.equals(fileContentType)&&
					// !Constants.OFFICE_PPTX_TYPE.equals(fileContentType)&&
					!Constants.MSG_TYPE.equals(fileContentType)) {
				errorMsg = "fileFormatoNonSupportato";
			}
		} else {
			errorMsg = "fileVuoto";
		}
		return errorMsg;
	}

	/**
	 * Valida la dimensione e verifica il formato excel.
	 * 
	 * @param f
	 *            file caricato dall'utente
	 * @return <strong>stringa</strong> che identifica il parametro (da
	 *         iniettare in pagina)<br>
	 *         che permette di visualizzare il messagio di errore in
	 *         <strong>messages.jsp</strong>. La stringa sare' vuota se il file
	 *         caricato passa la validazione.
	 */
	public static String validazioneFileExcel(FormFile f) {
		String errorMsg = "";
		if (f != null) {
			int fileSize = f.getFileSize();
			String fileContentType = f.getContentType();
			if (fileSize == 0) {
				errorMsg = "fileVuoto";
			} else if (fileSize > 3145728) { // 3 MB
				errorMsg = "fileDimensioneMassimaSuperata";
			} else if (!Constants.OFFICE_XLS_TYPE.equals(fileContentType) && !Constants.OFFICE_XSLX_TYPE.equals(fileContentType)) {
				errorMsg = "fileFormatoNonSupportato";
			}
		} else {
			errorMsg = "fileVuoto";
		}
		return errorMsg;
	}

	/**
	 * metodo per validare il contenuto del file abilitazione caricamenti
	 * massivi
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return String error
	 * @author Virginia
	 */
	public static String validazioneContenutoFile(FormFile file, int maxRecord, PropertiesReader pr) throws FileNotFoundException, IOException {

		// String errorMsg = "";
		InputStream bis = new ByteArrayInputStream(file.getFileData());
		XSSFWorkbook excel = new XSSFWorkbook(bis);
		try {
			XSSFSheet sheet = excel.getSheetAt(0);
			XSSFRow firstRow = sheet.getRow(0);

			XSSFCell title1 = firstRow.getCell(0);
			XSSFCell title2 = firstRow.getCell(1);
			XSSFCell title3 = firstRow.getCell(2);
			XSSFCell title4 = firstRow.getCell(3);
			XSSFCell title5 = firstRow.getCell(4);
			
			XSSFCell title6 = firstRow.getCell(5);

//			if (isTitoloNonCorretto(pr, title1, TITLE_1) || isTitoloNonCorretto(pr, title2, TITLE_2) || isTitoloNonCorretto(pr, title3, TITLE_3) || isTitoloNonCorretto(pr, title4, TITLE_4) || isTitoloNonCorretto(pr, title5, TITLE_5)) {
			
     		if (isTitoloNonCorretto(pr, title1, TITLE_1) || isTitoloNonCorretto(pr, title2, TITLE_2) || isTitoloNonCorretto(pr, title3, TITLE_3) || isTitoloNonCorretto(pr, title4, TITLE_4) || isTitoloNonCorretto(pr, title5, TITLE_5) || isTitoloNonCorretto(pr, title6, TITLE_6)) {
				
				return "titoloColonna";
			} else {
				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();
				int countRecords = 0;
				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					int countColoumn = 0;

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (cell != row.getCell(0) && cell != row.getCell(1) && cell != row.getCell(2) && cell != row.getCell(3) && cell != row.getCell(4) && cell != row.getCell(5) && cell.getStringCellValue() != null && !cell.getStringCellValue().equals("")) {
							countColoumn++;
						}
					}

					if (countColoumn > 0) {
						return "numeroColonne";
					} else if (countColoumn == 0) {
						countRecords++;
						if (countRecords > maxRecord) {
							return "numeroRecord";
						}
					}
				}
				numeroRecord = countRecords;
			}
			return "";
		} finally {
			if (excel != null)
				excel.close();
		}
	}
	
	// 2.5.3 2024 -->
	public static String validazioneContenutoFileNonInt(FormFile file, int maxRecord, PropertiesReader pr) throws FileNotFoundException, IOException {

		// String errorMsg = "";
		InputStream bis = new ByteArrayInputStream(file.getFileData());
		XSSFWorkbook excel = new XSSFWorkbook(bis);
		try {
			XSSFSheet sheet = excel.getSheetAt(0);
			XSSFRow firstRow = sheet.getRow(0);

			XSSFCell title1 = firstRow.getCell(0);
			XSSFCell title2 = firstRow.getCell(1);
			XSSFCell title3 = firstRow.getCell(2);
			XSSFCell title4 = firstRow.getCell(3);
			
     		if (isTitoloNonCorretto(pr, title1, TITLE_1) || isTitoloNonCorretto(pr, title2, TITLE_2) || isTitoloNonCorretto(pr, title3, TITLE_3) || isTitoloNonCorretto(pr, title4, TITLE_6)) {
				
				return "titoloColonna";
			} else {
				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();
				int countRecords = 0;
				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					int countColoumn = 0;

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (cell != row.getCell(0) && cell != row.getCell(1) && cell != row.getCell(2) && cell != row.getCell(3) && cell != row.getCell(4) && cell != row.getCell(5) && cell.getStringCellValue() != null && !cell.getStringCellValue().equals("")) {
							countColoumn++;
						}
					}

					if (countColoumn > 0) {
						return "numeroColonne";
					} else if (countColoumn == 0) {
						countRecords++;
						if (countRecords > maxRecord) {
							return "numeroRecord";
						}
					}
				}
				numeroRecord = countRecords;
			}
			return "";
		} finally {
			if (excel != null)
				excel.close();
		}
	}
	// 2.5.3 2024 <--
	
	
	public static String validazioneContFileCarMassVis(FormFile file, int maxRecord, PropertiesReader pr) throws FileNotFoundException, IOException {

		// String errorMsg = "";
		InputStream bis = new ByteArrayInputStream(file.getFileData());
		XSSFWorkbook excel = new XSSFWorkbook(bis);
		try {
			XSSFSheet sheet = excel.getSheetAt(0);
			XSSFRow firstRow = sheet.getRow(0);

			XSSFCell titolo1 = firstRow.getCell(0);
			XSSFCell titolo2 = firstRow.getCell(1);
			XSSFCell titolo3 = firstRow.getCell(2);
			XSSFCell titolo4 = firstRow.getCell(3);
			XSSFCell titolo5 = firstRow.getCell(4);
			
			XSSFCell titolo6 = firstRow.getCell(5);
		
     		if (isTitoloNonCorretto(pr, titolo1, INTEST_1) || isTitoloNonCorretto(pr, titolo2, INTEST_2) || isTitoloNonCorretto(pr, titolo3, INTEST_3) || isTitoloNonCorretto(pr, titolo4, INTEST_4) || isTitoloNonCorretto(pr, titolo5, INTEST_5) || isTitoloNonCorretto(pr, titolo6, INTEST_6)) {
				
				return "titoloColonna";
			} else {
				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();
				int countRecords = 0;
				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					int countColoumn = 0;

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						if (cell != row.getCell(0) && cell != row.getCell(1) && cell != row.getCell(2) && cell != row.getCell(3) && cell != row.getCell(4) && cell != row.getCell(5) && cell.getStringCellValue() != null && !cell.getStringCellValue().equals("")) {
							countColoumn++;
						}
					}

					if (countColoumn > 0) {
						return "numeroColonne";
					} else if (countColoumn == 0) {
						countRecords++;
						if (countRecords > maxRecord) {
							return "numeroRecord";
						}
					}
				}
				numeroRecord = countRecords;
			}
			return "";
		} finally {
			if (excel != null)
				excel.close();
		}
	}
	

	private static boolean isTitoloNonCorretto(PropertiesReader pr, XSSFCell titolo, String nomeProperty) {
		boolean nonCorretto = titolo == null || !titolo.getStringCellValue().trim().equals(pr.getProp(nomeProperty).trim());
		if (titolo != null && nonCorretto)
			Logg.getLogger().warn("il titolo '" + titolo.getStringCellValue().trim() + "' non corrispondente con la property '" + nomeProperty + "' di valore '" + pr.getProp(nomeProperty) + "'");
		return nonCorretto;
	}

	/**
	 * metodo per validare il contenuto del file abilitazione caricamenti
	 * massivi visibilite'
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return String error
	 * @author Reply
	 */
	public static String validazioneContenutoFileVisibilita(FormFile file, int maxRecord, PropertiesReader pr) throws FileNotFoundException, IOException {

		InputStream bis = new ByteArrayInputStream(file.getFileData());
		XSSFWorkbook excel = new XSSFWorkbook(bis);

		XSSFSheet sheet = excel.getSheetAt(0);
		XSSFRow firstRow = sheet.getRow(0);

		XSSFCell title1 = firstRow.getCell(0);
		XSSFCell title2 = firstRow.getCell(1);
		XSSFCell title3 = firstRow.getCell(2);
		XSSFCell title4 = firstRow.getCell(3);
		XSSFCell title5 = firstRow.getCell(4);
		XSSFCell title6 = firstRow.getCell(5);

		if (isTitoloNonCorretto(pr, title1, TITLE_1) || isTitoloNonCorretto(pr, title2, TITLE_4) || isTitoloNonCorretto(pr, title3, TITLE_2) || isTitoloNonCorretto(pr, title4, TITLE_3) || isTitoloNonCorretto(pr, title5, TITLE_6) || isTitoloNonCorretto(pr, title6, TITLE_5)) {

			return "titoloColonna";
		} else {
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			int countRecords = 0;
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int countColoumn = 0;

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (cell != row.getCell(0) && cell != row.getCell(1) && cell != row.getCell(2) && cell != row.getCell(3) && cell != row.getCell(4) && cell != row.getCell(5) && cell.getStringCellValue() != null && !cell.getStringCellValue().equals("")) {
						countColoumn++;
					}
				}

				if (countColoumn > 0) {
					return "numeroColonne";
				} else if (countColoumn == 0) {
					countRecords++;
					if (countRecords > maxRecord) {
						return "numeroRecord";
					}
				}
			}
			numeroRecord = countRecords;
		}

		return "";
	}
	
	/**********************************************************************
	 * metodo per validare il contenuto del file abilitazione caricamenti
	 * massivi mappatura abilitazioni ufficio
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return String error
	 * @author Reply
	 ***********************************************************************/
	public static String validazioneContenutoFileMappatura(FormFile file, int maxRecord, PropertiesReader pr) throws FileNotFoundException, IOException {

		InputStream bis = new ByteArrayInputStream(file.getFileData());
		XSSFWorkbook excel = new XSSFWorkbook(bis);

		XSSFSheet sheet = excel.getSheetAt(0);
		XSSFRow firstRow = sheet.getRow(0);

		XSSFCell title1 = firstRow.getCell(0);
		XSSFCell title2 = firstRow.getCell(1);
		XSSFCell title3 = firstRow.getCell(2);
		XSSFCell title4 = firstRow.getCell(3);
		XSSFCell title5 = firstRow.getCell(4);
		XSSFCell title6 = firstRow.getCell(5);
		XSSFCell title7 = firstRow.getCell(6);
		XSSFCell title8 = firstRow.getCell(7);

		if (isTitoloNonCorretto(pr, title1, TITLE_MAP_1) || 
				isTitoloNonCorretto(pr, title2, TITLE_MAP_2) || 
				isTitoloNonCorretto(pr, title3, TITLE_MAP_3) || 
				isTitoloNonCorretto(pr, title4, TITLE_MAP_4) || 
				isTitoloNonCorretto(pr, title5, TITLE_MAP_5) || 
				isTitoloNonCorretto(pr, title6, TITLE_MAP_6) ||
				isTitoloNonCorretto(pr, title7, TITLE_MAP_7) ||
				isTitoloNonCorretto(pr, title8, TITLE_MAP_8) ) {

			return "titoloColonna";
		} else {
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			int countRecords = 0;
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int countColoumn = 0;

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (cell != row.getCell(0) && cell != row.getCell(1) && cell != row.getCell(2) && cell != row.getCell(3) && cell != row.getCell(4) && cell != row.getCell(5)  && cell != row.getCell(6) && cell != row.getCell(7) && cell.getStringCellValue() != null && !cell.getStringCellValue().equals("")) {
						countColoumn++;
					}
				}

				if (countColoumn > 0) {
					return "numeroColonne";
				} else if (countColoumn == 0) {
					countRecords++;
					if (countRecords > maxRecord) {
						return "numeroRecord";
					}
				}
			}
			numeroRecord = countRecords;
		}

		return "";
	}
	
	
	public static String validazioneContenutoFileMappaturaCarMassVis(FormFile file, int maxRecord, PropertiesReader pr) throws FileNotFoundException, IOException {

		InputStream bis = new ByteArrayInputStream(file.getFileData());
		XSSFWorkbook excel = new XSSFWorkbook(bis);

		XSSFSheet sheet = excel.getSheetAt(0);
		XSSFRow firstRow = sheet.getRow(0);

		XSSFCell titolo1 = firstRow.getCell(0);
		XSSFCell titolo2 = firstRow.getCell(1);
		XSSFCell titolo3 = firstRow.getCell(2);
		XSSFCell titolo4 = firstRow.getCell(3);
		XSSFCell titolo5 = firstRow.getCell(4);
		XSSFCell titolo6 = firstRow.getCell(5);
		XSSFCell titolo7 = firstRow.getCell(6);
		XSSFCell titolo8 = firstRow.getCell(7);

		if (isTitoloNonCorretto(pr, titolo1, TITLEINT_MAP_1) || 
				isTitoloNonCorretto(pr, titolo2, TITLEINT_MAP_2) || 
				isTitoloNonCorretto(pr, titolo3, TITLEINT_MAP_3) || 
				isTitoloNonCorretto(pr, titolo4, TITLEINT_MAP_4) || 
				isTitoloNonCorretto(pr, titolo5, TITLEINT_MAP_5) || 
				isTitoloNonCorretto(pr, titolo6, TITLEINT_MAP_6) ||
				isTitoloNonCorretto(pr, titolo7, TITLEINT_MAP_7) ||
				isTitoloNonCorretto(pr, titolo8, TITLEINT_MAP_8) ) {

			return "titoloColonna";
		} else {
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			int countRecords = 0;
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int countColoumn = 0;

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					if (cell != row.getCell(0) && cell != row.getCell(1) && cell != row.getCell(2) && cell != row.getCell(3) && cell != row.getCell(4) && cell != row.getCell(5)  && cell != row.getCell(6) && cell != row.getCell(7) && cell.getStringCellValue() != null && !cell.getStringCellValue().equals("")) {
						countColoumn++;
					}
				}

				if (countColoumn > 0) {
					return "numeroColonne";
				} else if (countColoumn == 0) {
					countRecords++;
					if (countRecords > maxRecord) {
						return "numeroRecord";
					}
				}
			}
			numeroRecord = countRecords;
		}

		return "";
	}

	/**
	 * Trascodifica del codice della richiesta
	 * 
	 * @param codice
	 * @return
	 */
	public static String transcodificaTipoRichiesta(String codice) {
		String result = "";
		if (Constants.RICHIESTA_PROFILAZIONE.equalsIgnoreCase(codice)) {
			result = "Profilazione";
		} else if (Constants.RICHIESTA_CAMBIO_UFFICIO.equalsIgnoreCase(codice)) {
			result = "Cambio Uff.";
		} else if (Constants.RICHIESTA_VISIBILITA.equalsIgnoreCase(codice)) {
			result = "Visibilita'";
		} else if (Constants.RICHIESTA_REVOCA_VISIBILITA.equalsIgnoreCase(codice)) {
			result = "Revoca";
		}
		return result;
	}

	/**
	 * Ritorna la stringa "cognome nome (cf)"
	 * 
	 * @param nome
	 * @param cognome
	 * @param cf
	 * @return
	 */
	public static String getNominativoCompleto(String nome, String cognome, String cf) {
		String labelNominativo = "";
		if (Constants.checkNotEmptyAndNotNullProperty(cognome)) {
			labelNominativo = labelNominativo + cognome + " ";
		}
		if (Constants.checkNotEmptyAndNotNullProperty(nome)) {
			labelNominativo = labelNominativo + nome + " ";
		}
		if (Constants.checkNotEmptyAndNotNullProperty(cf)) {
			labelNominativo += "(" + cf + ")";
		}
		return labelNominativo;

	}

	/**
	 * Ritorna la data e ora di sistema nel formato passato
	 * 
	 * @return
	 */
	public static String getDataOra(String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(formato);
		Date date = new Date();
		String dataOra_Str = sdf.format(date);
		return dataOra_Str;

	}

	public static String getDataOra(String formato, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(formato);
		String dataOra_Str = sdf.format(date);
		return dataOra_Str;

	}

	/**
	 * Ritorna la data e ora di sistema nel formato "dd-MM-yyyy HH.mm.ss"
	 * 
	 * @return
	 */
	public static String getDataOra() {
		return getDataOra("dd-MM-yyyy_HH-mm-ss");
	}

	/**
	 * Converte una stringa in Date
	 * 
	 * @param dataStr
	 *            : dd/MM/yyyy
	 * @return
	 */
	public static Date convertStringtoDate(String dataStr) {
		if (dataStr != null && !("").equalsIgnoreCase(dataStr)) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date d = formatter.parse(dataStr);
				return d;
			} catch (ParseException e) {
				// TODO Blocco catch generato automaticamente
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
//	public static Date convertDatetoString(String strData) {
//		if (strData != null && !("").equalsIgnoreCase(strData)) {
//			SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
//			    try {
//			        Date d= formatter.parse(date.toString());
//			    } catch (ParseException e) {
//			        // handle exception
//			    	e.printStackTrace();
//				}
//		}
//		return null;
//	}
	

	

	/**
	 * Converte una stringa in Date
	 * 
	 * @param dataStr
	 *            : yyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public static Date convertString2DateAndTime(String dataStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = formatter.parse(dataStr);
		return d;
	}

	/**
	 * Ritorna la data corrente nel formato yyyy/MM/dd HH:mm:ss
	 * 
	 * @return
	 */
	public static Date getDateTimestamp() {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date getDateTimestamp = new Date(stamp.getTime());
		return getDateTimestamp;
	}

	/**
	 * Trasforma una Data in una stringa nel formato dd-MM-yyyy HH:mm:ss
	 * 
	 * @param d
	 * @return
	 */
	public static String convertDateTime2String(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("dd-MM-yyyy HH:mm:ss");
		String s = sdf.format(d);
		return s;
	}

	public static String convertDateTime2StringYYYYMMDDHHmmss(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		String s = sdf.format(d);
		return s;
	}

	/**
	 * Converte una Data in una stringa nel formato dd/MM/yyyy
	 * 
	 * @param d
	 * @return
	 */
	public static String convertDate2String(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("dd/MM/yyyy");
		String s = sdf.format(d);
		return s;
	}

	public static boolean isValidEmail(String email) {
		boolean isValid = false;
		try {
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			isValid = true;
		} catch (AddressException e) {
			Logg.getLogger().error(e.toString());
		}
		return isValid;
	}

	/**
	 * Questa funzione valida numeri di telefono nel seguente formato: - Almeno
	 * una cifra numerica 0-9 - Pue' o no esserci una + iniziale - I numeri
	 * possono essere separati da spazi bianchi in numero e formato qualsiasi
	 * 
	 * @param number
	 *            Il Numero di telefono/fax da validare
	 * @return Restituisce TRUE se il numero e' stato validato correttamente
	 */
	public static boolean isValidTelFaxNumber(String number) {
		if (number == null) {
			return false;
		}

		String patternSpace = "\\s+";
		String replaceStr = "";
		Pattern pattern = Pattern.compile(patternSpace);
		Matcher matcher = pattern.matcher(number);
		number = matcher.replaceAll(replaceStr);

		patternSpace = "\\-+";
		replaceStr = "";
		pattern = Pattern.compile(patternSpace);
		matcher = pattern.matcher(number);
		number = matcher.replaceAll(replaceStr);

		// i caratteri / non vengono presi in considerazione
		patternSpace = "\\/+";
		replaceStr = "";
		pattern = Pattern.compile(patternSpace);
		matcher = pattern.matcher(number);
		number = matcher.replaceAll(replaceStr);

		Pattern p = Pattern.compile("^(\\+)?[0-9]+$");
		Matcher m = p.matcher(number);
		boolean matchFound = m.matches();
		return matchFound;
	}

	/***
	 * aggiorna le breadcrumb non mostrando lo stesso livello
	 */
	public static boolean checkLevelBreadCrumb(HttpServletRequest request, String label) {
		boolean alreadyAbilit = false;
		Stack stackLabel;
		try {
			stackLabel = (Stack) new Object(); // StackUtil.getStack(request);
			if (stackLabel != null) {
				// for(int i =0; i<stackLabel.size(); i++){
				ElementStack elem = stackLabel.getElement(stackLabel.size());
				if (elem != null) {
					if (elem.getTestoLink().endsWith(label)) {
						alreadyAbilit = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alreadyAbilit;

	}

	// /**
	// * il metodo riformatta la lista dei profili richiesti:
	// * per codice profilo ECA setta a tutti i profili del role group la
	// descrizione del padre
	// * per quelli non ECA controlla il campo EXPLICIT_ENTITLMENT
	// * @param listaProfiliRichiesti
	// * @return
	// */
	public static void reBuildListaProfiliRichiesti(List<ProfiloBean> listProfiloBeanRichiesta, OperatoreBean operatoreBean, String descUfficio) {

		String explicitEnt = "";
		for (ProfiloBean profiloB : listProfiloBeanRichiesta) {

			explicitEnt = profiloB.getExplicit_entitlement();
			if (explicitEnt == null || "".equals(explicitEnt)) {
				profiloB.setDescrizioneProfiloRoot("");
			} else if (explicitEnt.equals(Constants.EXPLICIT_ENT_CDR)) {
				profiloB.setDescrizioneProfiloRoot(operatoreBean.getDescrizione());
			} else
				profiloB.setDescrizioneProfiloRoot(descUfficio);
		}
	}

	/***
	 * compatta la lista dei profili nel menu Il mio profilo. visualizza
	 * un'unica entry per i profili con diversi CDR o Uffici I profili sono
	 * quelli non 'ECA'
	 * 
	 * @param elencoProfiliOperatore
	 * @return
	 */
	public static List<ProfiloBean> compattaElencoProfiliOperatoreRiepilogo(List<ProfiloBean> listProfiloBeanRichiesta, String codiceUfficio, String codiceCdr) {
		List<ProfiloBean> elencoProfiliOperatoreComp = new ArrayList<ProfiloBean>();
		List<ProfiloBean> elencoProfiliOperatoreDaCompattare = new ArrayList<ProfiloBean>();
		String descrizioneRuolo = "";
		String codRuolo = "";
		boolean trovato = false;
		for (ProfiloBean profili : listProfiloBeanRichiesta) {
			// escludo i profili ECA ai quali e' stata anteposta la stringa
			// Stessi Uffici
			if (profili.getDescrizioneProfiloRoot() != null && !"".equals(profili.getDescrizioneProfiloRoot().trim()) && !profili.getDescrizioneProfiloRoot().startsWith("Stessi Uffici")) {
				descrizioneRuolo = profili.getDescrizione();
				codRuolo = profili.getCodiceRoleGroup();
				// riciclo sulla lista per recuperare gli stessi profili
				if (elencoProfiliOperatoreDaCompattare.size() > 0 && !elencoProfiliOperatoreDaCompattare.get(0).getDescrizioneProfiloRoot().equals(descrizioneRuolo)) {
					elencoProfiliOperatoreDaCompattare.clear();
					trovato = false;
				}
				if (!trovato) {
					for (ProfiloBean profiliMulti : listProfiloBeanRichiesta) {
						if (profiliMulti.getDescrizioneProfiloRoot() != null && !"".equals(profili.getDescrizioneProfiloRoot().trim()) && !profiliMulti.getDescrizioneProfiloRoot().startsWith("Stessi Uffici")) {
							if (profiliMulti.getCodiceRoleGroup().equals(codRuolo) && profiliMulti.getDescrizione().equals(descrizioneRuolo)) {
								elencoProfiliOperatoreDaCompattare.add(profiliMulti);
							}

						}
					}
					if (elencoProfiliOperatoreDaCompattare != null && elencoProfiliOperatoreDaCompattare.size() > 1) {
						ProfiloBean elenco; // = new ProfiloBean();
						elenco = elencoProfiliOperatoreDaCompattare.get(0);
						// TODO:chiedere di questa doppio if
						if (elenco != null)
							elenco.setCdrUffValiditaProfVisibile(Constants.PREX_DESCR_MULTIUFFICIO);
						else if (elenco != null)
							elenco.setCdrUffValiditaProfVisibile(Constants.PREX_DESCR_MULTICDR);

						elencoProfiliOperatoreComp.add(elenco);
						trovato = true;
					} else if (elencoProfiliOperatoreDaCompattare != null && elencoProfiliOperatoreDaCompattare.size() == 1) {
						elencoProfiliOperatoreComp.add(elencoProfiliOperatoreDaCompattare.get(0));
						elencoProfiliOperatoreDaCompattare.clear();
						trovato = false;
					} else {
						elencoProfiliOperatoreDaCompattare.clear();
						trovato = false;
					}
				}
			} else {
				elencoProfiliOperatoreComp.add(profili);
				elencoProfiliOperatoreDaCompattare.clear();
				trovato = false;
			}
		}

		return elencoProfiliOperatoreComp;

	}

	public static List<String> retainAll(List<String> lista1, List<String> lista2) {
		List<String> commonList = new ArrayList<String>();
		for (int i = 0; i < lista1.size(); i++) {
			String val = lista1.get(i);
			for (int j = 0; j < lista2.size(); j++) {
				if (val.equals(lista2.get(j)))
					commonList.add(val);
			}
		}
		return commonList;
	}

	/***
	 * compatta la lista dei profili nel menu Il mio profilo. visualizza
	 * un'unica entry per i profili con diversi CDR o Uffici I profili sono
	 * quelli non 'ECA'
	 * 
	 * @param elencoProfiliOperatore
	 * @return
	 */
	public static List<InterrProfiliOperatoreBean> compattaElencoProfiliOperatore(List<InterrProfiliOperatoreBean> elencoProfiliOperatoreIn) {
		List<InterrProfiliOperatoreBean> elencoProfiliOperatoreComp = new ArrayList<InterrProfiliOperatoreBean>();
		List<InterrProfiliOperatoreBean> elencoProfiliOperatoreDaCompattare = new ArrayList<InterrProfiliOperatoreBean>();
		String descrizioneRuolo = "";
		String codRuolo = "";
		boolean trovato = false;
		for (InterrProfiliOperatoreBean profili : elencoProfiliOperatoreIn) {
			// escludo i profili ECA ai quali e' stata anteposta la stringa
			// Stessi Uffici
			if (profili.getCdrUffValiditaProf() != null && !"".equals(profili.getCdrUffValiditaProf().trim()) && !profili.getCdrUffValiditaProf().startsWith("Stessi Uffici")) {
				descrizioneRuolo = profili.getProfilo();
				codRuolo = profili.getCodRuolo();
				// riciclo sulla lista per recuperare gli stessi profili
				if (elencoProfiliOperatoreDaCompattare.size() > 0 && !elencoProfiliOperatoreDaCompattare.get(0).getProfilo().equals(descrizioneRuolo)) {
					elencoProfiliOperatoreDaCompattare.clear();
					trovato = false;
				}
				if (!trovato) {
					for (InterrProfiliOperatoreBean profiliMulti : elencoProfiliOperatoreIn) {
						if (profiliMulti.getCdrUffValiditaProf() != null && !"".equals(profili.getCdrUffValiditaProf().trim()) && !profiliMulti.getCdrUffValiditaProf().startsWith("Stessi Uffici")) {
							if (profiliMulti.getCodRuolo().equals(codRuolo) && profiliMulti.getProfilo().equals(descrizioneRuolo)) {
								elencoProfiliOperatoreDaCompattare.add(profiliMulti);
							}

						}
					}
					if (elencoProfiliOperatoreDaCompattare != null && elencoProfiliOperatoreDaCompattare.size() > 1) {
						InterrProfiliOperatoreBean elenco = new InterrProfiliOperatoreBean();
						elenco = elencoProfiliOperatoreDaCompattare.get(0);
						if (elenco.getCodUfficio() != null)
							elenco.setCdrUffValiditaProfVisibile(Constants.PREX_DESCR_MULTIUFFICIO);
						else if (elenco != null)
							elenco.setCdrUffValiditaProfVisibile(Constants.PREX_DESCR_MULTICDR);

						elencoProfiliOperatoreComp.add(elenco);
						trovato = true;
					} else if (elencoProfiliOperatoreDaCompattare != null && elencoProfiliOperatoreDaCompattare.size() == 1) {
						elencoProfiliOperatoreComp.add(elencoProfiliOperatoreDaCompattare.get(0));
						elencoProfiliOperatoreDaCompattare.clear();
						trovato = false;
					} else {
						elencoProfiliOperatoreDaCompattare.clear();
						trovato = false;
					}
				}
			} else {
				elencoProfiliOperatoreComp.add(profili);
				elencoProfiliOperatoreDaCompattare.clear();
				trovato = false;
			}
		}

		return elencoProfiliOperatoreComp;
	}

	/**
	 * Ritorna la stringa passata se not null altrimenti una stringa vuota
	 * 
	 * @param stringa
	 * @return
	 */
	public static String getEmptyOrNotNullString(String stringa) {
		if (stringa != null) {
			return stringa;
		} else {
			return "";
		}
	}
	
	public static Date getEmptyOrNotNullDate(Date data) {
		if (data != null) {
			return data;
		} else {
			return data;
		}
	}
	

	public static void disabilitaLineeECA(List<ProfiloBean> elenco) {
		for (Iterator<ProfiloBean> iterator = elenco.iterator(); iterator.hasNext();) {
			ProfiloBean profiloBean = (ProfiloBean) iterator.next();
			if (isLineaECA(profiloBean)) {
				// profiloBean.setHideCheckbox(true);
				profiloBean.setUnselectable(true);
			}
		}
	}

	/**
	 * Verifica se il profilo e' una linea ECA ovvero se il codice del profilo
	 * e' nel formato ECA_00xxxyyyyy [yyyyy != 00000]
	 * 
	 * @param profiloBean
	 * @return
	 */
	public static boolean isLineaECA(ProfiloBean profiloBean) {
		boolean result = false;
		String codiceProfilo = profiloBean.getCodiceProfilo();
		if (!"".equals(codiceProfilo) && codiceProfilo.length() == 14 && codiceProfilo.startsWith("ECA_00") && !codiceProfilo.endsWith("00000")) {
			result = true;
		}
		return result;
	}

	public static boolean isAreaECA(ProfiloBean profiloBean) {
		boolean result = false;
		String codiceProfilo = profiloBean.getCodiceProfilo();
		if (!"".equals(codiceProfilo) && codiceProfilo.length() == 14 && codiceProfilo.startsWith("ECA_00") && codiceProfilo.endsWith("00000")) {
			result = true;
		}
		return result;
	}

	/**
	 * restituisce il numero di volte in cui deve essere eseguita una query dato
	 * un numero di limite e un numero di elementi di una lista Pue' servire in
	 * caso di query lente o di IN oracle con pie' di mille elementi(che fa
	 * andare oracle in errore)
	 * 
	 * @param limitIN
	 *            limite di elementi permessi
	 * @param lstSize
	 *            numero di elementi di una lista
	 * @return countQueryExecute
	 */
	public static int getCountQueryExecute(Integer limitIN, Integer lstSize) {
		int countQueryExecute = 0;
		Double countAppoggio = new Double(0);
		if (lstSize > 0) {
			countAppoggio = (lstSize.doubleValue() / limitIN.doubleValue());
			countQueryExecute = countAppoggio.intValue();
			if (countAppoggio > countQueryExecute) {
				countQueryExecute++;
			}
			// System.out.println("numero di volte in cui eseguiamo la query "+countQueryExecute);
		}
		return countQueryExecute;

	}









}
