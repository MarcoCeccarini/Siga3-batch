package it.finanze.siga.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.finanze.scheduler.bean.AssociazOperRichiAutor;
import it.finanze.scheduler.bean.CdR;
import it.finanze.scheduler.bean.NotificaOperatoriDAO;
import it.finanze.scheduler.bean.ProfiliAttiviUtente;
import it.finanze.scheduler.bean.RegistroRichieste;
import it.finanze.scheduler.bean.RelazioneCdRUfficioStrut;
import it.finanze.scheduler.bean.RichiedenteCdR;
import it.finanze.scheduler.bean.StrutturaIterDAO;
import it.finanze.scheduler.bean.Utenti;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;


@Stateless
@Slf4j
@Named("BatchHrVariazioneDatiUtenteNewDaoImpl")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BatchHrVariazioneDatiUtenteNewDaoImpl extends SigaDaoImpl{
	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		super.setEntityManager(entityManager);
	}

	public BatchHrVariazioneDatiUtenteNewDaoImpl() {
		super();
	}

	
	public Integer countUtenteByCF(String userId) throws SQLException {
		String sql = "SELECT COUNT(*) FROM UTENTI WHERE codice_fiscale = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, userId);
		return ((Number) query.getSingleResult()).intValue();
	}

	public Integer countUtenteByCFClosed(String userId) throws SQLException {
		String sql = "SELECT COUNT(*) FROM UTENTI WHERE codice_fiscale = ? AND DATA_FINE_VAL IS NOT NULL";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, userId);
		return ((Number) query.getSingleResult()).intValue();
	}

	public Utenti selectUtenteByCF(String cfGenerico) throws SQLException {
		String sql = "SELECT nome, cognome, e_mail, codice_cdr, richiedente, AUTORIZZATORE_I_LIV, AUTORIZZATORE_II_LIV, codice_fiscale " +
				"FROM UTENTI WHERE codice_fiscale = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cfGenerico);
		
			Object[] row = (Object[]) query.getSingleResult();
			return mapRowToUtenti(row);
	}

	public List<Utenti> selectAllCFUtenti() throws SQLException {
		String sql = "SELECT codice_fiscale, codice_cdr, richiedente, AUTORIZZATORE_I_LIV, AUTORIZZATORE_II_LIV " +
				"FROM UTENTI WHERE (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)"
				+" FETCH FIRST 100 ROWS ONLY";
		Query query = entityManager.createNativeQuery(sql);
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<Utenti> dtoList = new ArrayList<>();
		for (Object row : results) {
			Object[] rowArray = (Object[]) row;
			Utenti dto = new Utenti();
			dto.setCodiceFiscale((String) rowArray[0]);
			dto.setCodiceCdR((String) rowArray[1]);
			dto.setRichiedente((String) rowArray[2]);
			dto.setAutorizzatoreIliv((String) rowArray[3]);
			dto.setAutorizzatoreIIliv((String) rowArray[4]);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void insertUtentiSiga(HashMap<String, String> input) throws SQLException {
		String sql = "INSERT INTO UTENTI (CODICE_FISCALE, DATA_INIZIO_VAL, DATA_FINE_VAL, COGNOME, NOME, CODICE_CDR, " +
				"TIPO_UTENTE, RICHIEDENTE, AUTORIZZATORE_I_LIV, AUTORIZZATORE_II_LIV, E_MAIL, TELEFONO) " +
				"VALUES (?, SYSDATE, NULL, ?, ?, ?, 'I', 'NO', 'NO', 'NO', ?, ?)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		query.setParameter(2, input.get("sn"));
		query.setParameter(3, input.get("givenName"));
		query.setParameter(4, input.get("orgName"));
		query.setParameter(5, input.get("mail"));
		query.setParameter(6, input.get("telephoneNumber"));
		query.executeUpdate();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void insertStoricoCdRUtenti(HashMap<String, String> input) throws SQLException {
		String sql = "INSERT INTO STORICO_CDR_UTENTI (CODICE_FISCALE, CODICE_CDR, DATA_INIZIO_VAL, DATA_FINE_VAL) " +
				"VALUES (?, ?, SYSDATE, NULL)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		query.setParameter(2, input.get("orgName"));
		query.executeUpdate();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void insertStoricoCdRUtentiWithStartDate(HashMap<String, String> input) throws SQLException {
		String sql = "INSERT INTO STORICO_CDR_UTENTI (CODICE_FISCALE, CODICE_CDR, DATA_INIZIO_VAL, DATA_FINE_VAL) " +
				"VALUES (?, ?, TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'), NULL)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		query.setParameter(2, input.get("orgName"));
		query.setParameter(3, input.get("startDate"));
		query.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateStoricoCdrUtenti(Utenti utenti) throws SQLException {
		String sql = "UPDATE STORICO_CDR_UTENTI SET DATA_FINE_VAL = SYSDATE " +
				"WHERE CODICE_FISCALE = ? AND CODICE_CDR = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, utenti.getCodiceFiscale());
		query.setParameter(2, utenti.getCodiceCdR());
		query.executeUpdate();
	}
@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateUtentiForVerificaOperatore(Utenti utenti) throws SQLException {
		String sql = "UPDATE UTENTI SET RICHIEDENTE = ?, AUTORIZZATORE_I_LIV = ? " +
				"WHERE CODICE_FISCALE = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, utenti.getRichiedente());
		query.setParameter(2, utenti.getAutorizzatoreIliv());
		query.setParameter(3, utenti.getCodiceFiscale());
		query.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)

	public void updateUtenti(Utenti utenti) throws SQLException {
		String sql = "UPDATE UTENTI SET COGNOME = ?, NOME = ?, CODICE_CDR = ?, RICHIEDENTE = ?, " +
				"AUTORIZZATORE_I_LIV = ?, AUTORIZZATORE_II_LIV = ?, E_MAIL = ? " +
				"WHERE CODICE_FISCALE = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, utenti.getCognome());
		query.setParameter(2, utenti.getNome());
		query.setParameter(3, utenti.getCodiceCdR());
		query.setParameter(4, utenti.getRichiedente());
		query.setParameter(5, utenti.getAutorizzatoreIliv());
		query.setParameter(6, utenti.getAutorizzatoreIIliv());
		query.setParameter(7, utenti.geteMail());
		query.setParameter(8, utenti.getCodiceFiscale());
		query.executeUpdate();
	}

	public List<RichiedenteCdR> selectCdRFromRichiedenteCdRByCFRichiedente(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT CODICE_CDR FROM RICHIEDENTE_CDR WHERE (CF_RICHIEDENTE = ? OR CF_RESPONSABILE_HR = ?) " +
				"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		query.setParameter(2, input.get("userId"));
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<RichiedenteCdR> dtoList = new ArrayList<>();
		for (Object row : results) {
			RichiedenteCdR dto = new RichiedenteCdR();
			dto.setCodiceCdR((String) row);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public List<RichiedenteCdR> selectCdRFromRichiedenteCdRByCFAutorizzatore(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT CODICE_CDR FROM RICHIEDENTE_CDR WHERE CF_AUTORIZZATORE_I_LIV = ? " +
				"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<RichiedenteCdR> dtoList = new ArrayList<>();
		for (Object row : results) {
			RichiedenteCdR dto = new RichiedenteCdR();
			dto.setCodiceCdR((String) row);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public List<AssociazOperRichiAutor> selectCFAutorizzatoreByCFRichiedente(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT CODICE_FISCALE_AUTORIZZATORE FROM ASSOCIAZ_OPER_RICHI_AUTOR " +
				"WHERE CODICE_FISCALE_RICHIEDENTE = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<AssociazOperRichiAutor> dtoList = new ArrayList<>();
		for (Object row : results) {
			AssociazOperRichiAutor dto = new AssociazOperRichiAutor();
			dto.setCfAutorizzatore((String) row);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public List<AssociazOperRichiAutor> selectCFRichiedenteByCFAutorizzatore(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT CODICE_FISCALE_RICHIEDENTE FROM ASSOCIAZ_OPER_RICHI_AUTOR " +
				"WHERE CODICE_FISCALE_AUTORIZZATORE = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<AssociazOperRichiAutor> dtoList = new ArrayList<>();
		for (Object row : results) {
			AssociazOperRichiAutor dto = new AssociazOperRichiAutor();
			dto.setCfRichiedente((String) row);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public List<StrutturaIterDAO> verifyAutorizzatoreIORIILivello(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT NVL(identificativo_iter,0), TIPO_UFFICIO_RICHIEDENTE, ITER_STANDARD, TIPO_CDR_AUTORIZ_I_LIV, " +
				"CDR_AUTORIZ_I_LIV, CF_I, PREVISTO_AUTORIZZATORE_II_LIV, TIPO_CDR_AUTORIZ_II_LIV, CDR_AUTORIZ_II_LIV, CF_II, " +
				"GEST_OPER_STRUT_RICHIEDENTE, GEST_OPER_STRUT_AUTOR_I_LIV, GEST_OPER_STRUT_AUTOR_II_LIV, GRUPPO_GESTORI_OPERATORI, " +
				"CDR_GESTORI_OPERATORI, DATA_FINE_VAL, NVL(id_audit_inizio, 0), CODICE_FISCALE_INIZIO, NVL(id_audit_fine,0), " +
				"CODICE_FISCALE_FINE FROM STRUTTURA_ITER WHERE (CF_I = ? OR CF_II = ?) AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		query.setParameter(2, input.get("userId"));
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		List<StrutturaIterDAO> dtoList = new ArrayList<>();
		for (Object[] row : results) {
			dtoList.add(mapRowToStrutturaIterDAO(row));
		}
		return dtoList;
	}
@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void insertStrutturaIter(StrutturaIterDAO strutturaIter) throws SQLException {
		String sql = "INSERT INTO STRUTTURA_ITER (IDENTIFICATIVO_ITER, TIPO_UFFICIO_RICHIEDENTE, ITER_STANDARD, " +
				"TIPO_CDR_AUTORIZ_I_LIV, CDR_AUTORIZ_I_LIV, CF_I, PREVISTO_AUTORIZZATORE_II_LIV, TIPO_CDR_AUTORIZ_II_LIV, " +
				"CDR_AUTORIZ_II_LIV, CF_II, GEST_OPER_STRUT_RICHIEDENTE, GEST_OPER_STRUT_AUTOR_I_LIV, GEST_OPER_STRUT_AUTOR_II_LIV, " +
				"GRUPPO_GESTORI_OPERATORI, CDR_GESTORI_OPERATORI, DATA_INIZIO_VAL, DATA_FINE_VAL, ID_AUDIT_INIZIO, " +
				"CODICE_FISCALE_INIZIO, ID_AUDIT_FINE, CODICE_FISCALE_FINE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, " +
				"TO_DATE(?,'YYYY-MM-DD'), ?, ?, ?, ?)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, strutturaIter.getIdentificativoIter());
		query.setParameter(2, strutturaIter.getTipoUfficioRichiedente());
		query.setParameter(3, strutturaIter.getIterStandard());
		query.setParameter(4, strutturaIter.getTipoCdrAutorizILiv());
		query.setParameter(5, strutturaIter.getCdrAutorizILiv());
		query.setParameter(6, strutturaIter.getCf_I());
		query.setParameter(7, strutturaIter.getPrevistoAutorizzatoreIILiv());
		query.setParameter(8, strutturaIter.getTipoCdrAutorizIILiv());
		query.setParameter(9, strutturaIter.getCdrAutorizIILiv());
		query.setParameter(10, strutturaIter.getCf_II());
		query.setParameter(11, strutturaIter.getGestOperStrutRichiedente());
		query.setParameter(12, strutturaIter.getGestOperStrutAutorILiv());
		query.setParameter(13, strutturaIter.getGestOperStrutAutorIILiv());
		query.setParameter(14, strutturaIter.getGruppoGestoriOperatori());
		query.setParameter(15, strutturaIter.getCdrGestoriOperatori());
		query.setParameter(16, strutturaIter.getDataFineVal());
		query.setParameter(17, strutturaIter.getIdAuditInizio());
		query.setParameter(18, strutturaIter.getCodiceFiscaleInizio());
		query.setParameter(19, strutturaIter.getIdAuditFine());
		query.setParameter(20, strutturaIter.getCodiceFiscaleFine());
		query.executeUpdate();
	}

	public CdR selectDescBYOldCdR(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT DESCRIZIONE FROM CDR WHERE codice_cdr = ? " +
				"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE) " +
				"AND (RUOLI_BLOCCATI = 'NO' OR RUOLI_BLOCCATI IS NULL)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("oldCdR"));
		
		try {
			Object result = query.getSingleResult();
			CdR dto = new CdR();
			dto.setDescrizioneCdR((String) result);
			return dto;
		} catch (Exception e) {
			return null;
		}
	}

	public CdR selectDescBYNewCdR(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT DESCRIZIONE FROM CDR WHERE codice_cdr = ? " +
				"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE) " +
				"AND (RUOLI_BLOCCATI = 'NO' OR RUOLI_BLOCCATI IS NULL)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("orgName"));
		
		try {
			Object result = query.getSingleResult();
			CdR dto = new CdR();
			dto.setDescrizioneCdR((String) result);
			return dto;
		} catch (Exception e) {
			return null;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void insertAuditOperazioni(HashMap<String, String> input) throws SQLException {
		String sql = "INSERT INTO AUDIT_OPERAZIONI (ID_AUDIT, CODICE_FISCALE, CDR_AMMINISTRATORE, DATA_ORA, " +
				"TABELLA_AGGIORNATA, TESTO) VALUES (PROG_AUDIT.nextval, NULL, NULL, SYSDATE, ?, ?)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("tabellaAggiornata"));
		query.setParameter(2, input.get("testoAudit"));
		query.executeUpdate();
	}

	public Integer selectMaxIDAudit() throws SQLException {
		String sql = "SELECT MAX(id_audit) FROM AUDIT_OPERAZIONI";
		Query query = entityManager.createNativeQuery(sql);
		
		Object result = query.getSingleResult();
		if (result != null) {
			return ((Number) result).intValue();
		}
		return 0;
	}
@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateGestoreOperatore(HashMap<String, String> input) throws SQLException {
		String sql = "UPDATE GESTORI_OPERATORI SET DATA_FINE_VAL = SYSDATE, ID_AUDIT_FINE = ?, codice_fiscale_fine = NULL " +
				"WHERE codice_cdr = ? AND codice_fiscale = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, Integer.parseInt(input.get("idAuditFine")));
		query.setParameter(2, input.get("oldCdR"));
		query.setParameter(3, input.get("userId"));
		query.executeUpdate();
	}
@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateStrutturaIter(HashMap<String, String> input) throws SQLException {
		String sql = "UPDATE STRUTTURA_ITER SET DATA_FINE_VAL = SYSDATE " +
				"WHERE (CF_I = ? OR CF_II = ?) AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		query.setParameter(2, input.get("userId"));
		query.executeUpdate();
	}

	public List<RegistroRichieste> selectCdRAndCFUtenteFromRegistroRichiesteByCFAutorizzatore(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT cdr_utente, cf_utente FROM REGISTRO_RICHIESTE WHERE " +
				"((CF_AUTORIZZATORE_1 = ? AND CDR_AUTORIZZATORE_1 IS NULL) OR (CF_AUTORIZZATORE_2 = ? AND CDR_AUTORIZZATORE_2 IS NULL)) " +
				"AND (STATO_RICHIESTA = 'INS' OR STATO_RICHIESTA = 'AU-1') ORDER BY cdr_utente, cf_utente";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		query.setParameter(2, input.get("userId"));
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		List<RegistroRichieste> dtoList = new ArrayList<>();
		for (Object[] row : results) {
			RegistroRichieste dto = new RegistroRichieste();
			dto.setCdrUtente((String) row[0]);
			dto.setCfUtenteRegistroRichieste((String) row[1]);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public List<RelazioneCdRUfficioStrut> selectCodiceCdROfStrutturaAppartenenzaCF(String cdrUtenteRegistroRichieste) throws SQLException {
		String sql = "SELECT b.codice_cdr FROM RELAZIONE_CDR_UFFICIO_STRUT a, RELAZIONE_CDR_UFFICIO_STRUT b " +
				"WHERE a.codice_cdr = ? AND a.codice_struttura = b.codice_struttura " +
				"AND (a.DATA_FINE_VAL IS NULL OR a.DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cdrUtenteRegistroRichieste);
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<RelazioneCdRUfficioStrut> dtoList = new ArrayList<>();
		for (Object row : results) {
			RelazioneCdRUfficioStrut dto = new RelazioneCdRUfficioStrut();
			dto.setCodiceCdR((String) row);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public List<ProfiliAttiviUtente> selectCFProfiliAttiviUtenteByCdRAndProfile(String cdROfStrutturaAppartenenzaCF) throws SQLException {
		String sql = "SELECT CF_UTENTE FROM PROFILI_ATTIVI_UTENTE a, PROFILO b " +
				"WHERE a.codice_cdr = ? AND b.codice_profilo = a.codice_profilo " +
				"AND a.codice_profilo = 'ESG_AMM_Locale_3' " +
				"AND (a.DATA_FINE_VAL IS NULL OR a.DATA_FINE_VAL > SYSDATE) " +
				"AND (b.DATA_FINE_VAL IS NULL OR b.DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cdROfStrutturaAppartenenzaCF);
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<ProfiliAttiviUtente> dtoList = new ArrayList<>();
		for (Object row : results) {
			ProfiliAttiviUtente dto = new ProfiliAttiviUtente();
			dto.setCfUtente((String) row);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public Utenti selectEmailUtenteByCF(String cfUtente) throws SQLException {
		String sql = "SELECT E_MAIL FROM UTENTI WHERE codice_fiscale = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cfUtente);
		
		try {
			Object result = query.getSingleResult();
			Utenti dto = new Utenti();
			dto.seteMail((String) result);
			return dto;
		} catch (Exception e) {
			return null;
		}
	}
@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void setFlagAutorizzatoreIlivelloNO(String userId) throws SQLException {
		String sql = "UPDATE UTENTI SET AUTORIZZATORE_I_LIV = 'NO' WHERE codice_fiscale = ? " +
				"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, userId);
		query.executeUpdate();
	}

@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void setFlagAutorizzatoreIIlivelloNO(String userId) throws SQLException {
		String sql = "UPDATE UTENTI SET AUTORIZZATORE_II_LIV = 'NO' WHERE codice_fiscale = ? " +
				"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, userId);
		query.executeUpdate();
	}
@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void setFlagRichiedenteNO(String userId) throws SQLException {
		String sql = "UPDATE UTENTI SET RICHIEDENTE = 'NO' WHERE codice_fiscale = ? " +
				"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, userId);
		query.executeUpdate();
	}
@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateRegistroRichieste(HashMap<String, String> input) throws SQLException {
		String sql = "UPDATE REGISTRO_RICHIESTE SET CF_PRESA_IN_CARICO = NULL WHERE CF_PRESA_IN_CARICO = ?";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		query.executeUpdate();
	}

	public List<RegistroRichieste> selectCFPresaINCaricoFromRegistroRichieste(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT CF_PRESA_IN_CARICO, id_richiesta FROM REGISTRO_RICHIESTE a, ambito_applicativo b " +
				"WHERE cf_utente = ? AND a.codice_ambito = b.codice_ambito AND b.sincronizzata_cau = 'NO' " +
				"AND a.stato_richiesta = 'ES-P' AND a.cf_presa_in_carico IS NOT NULL";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		List<RegistroRichieste> dtoList = new ArrayList<>();
		for (Object[] row : results) {
			RegistroRichieste dto = new RegistroRichieste();
			dto.setCfPresaInCarico((String) row[0]);
			dto.setIdRichiesta(((Number) row[1]).intValue());
			dtoList.add(dto);
		}
		return dtoList;
	}

	public Utenti selectCFbyCFPresaInCaricoFromUtenti(String cfPresaInCarico) throws SQLException {
		String sql = "SELECT CODICE_FISCALE FROM UTENTI WHERE codice_fiscale = ? " +
				"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cfPresaInCarico);
		
		try {
			Object result = query.getSingleResult();
			Utenti dto = new Utenti();
			dto.setCodiceFiscale((String) result);
			return dto;
		} catch (Exception e) {
			return null;
		}
	}

	public RelazioneCdRUfficioStrut strutturaOld(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT codice_struttura FROM relazione_cdr_ufficio_strut WHERE codice_cdr = ? " +
				"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("oldCdR"));
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<RelazioneCdRUfficioStrut> dtoList = new ArrayList<>();
		for (Object row : results) {
			RelazioneCdRUfficioStrut dto = new RelazioneCdRUfficioStrut();
			dto.setCodiceStruttura((String) row);
			dtoList.add(dto);
		}
		return dtoList.size() == 0 ? null : dtoList.get(0);
	}

	public RelazioneCdRUfficioStrut strutturaNew(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT codice_struttura FROM relazione_cdr_ufficio_strut WHERE codice_cdr = ? " +
				"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("orgName"));
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<RelazioneCdRUfficioStrut> dtoList = new ArrayList<>();
		for (Object row : results) {
			RelazioneCdRUfficioStrut dto = new RelazioneCdRUfficioStrut();
			dto.setCodiceStruttura((String) row);
			dtoList.add(dto);
		}
		return dtoList.size() == 0 ? null : dtoList.get(0);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void insertUtentiAggiornatiHR(HashMap<String, String> input) throws SQLException {
		String sql = "INSERT INTO UTENTI_AGGIORNATI_HR (CODICE_FISCALE, TIPO_EVENTO, CODICE_CDR_OLD, CODICE_CDR_NEW, DATA_INSERIMENTO) " +
				"VALUES (?, ?, ?, ?, SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		query.setParameter(2, input.get("tipoEvento"));
		query.setParameter(3, input.get("oldCdR"));
		query.setParameter(4, input.get("orgName"));
		query.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateStoricoCdrUtentiPerCessazione(Utenti utenti) throws SQLException {
		String sql = "UPDATE STORICO_CDR_UTENTI SET DATA_FINE_VAL = SYSDATE " +
				"WHERE CODICE_FISCALE = ? AND CODICE_CDR = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, utenti.getCodiceFiscale());
		query.setParameter(2, utenti.getCodiceCdR());
		query.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateUtentiPerCessazione(String codiceFiscaleUtenti) throws SQLException {
		String sql = "UPDATE UTENTI SET DATA_FINE_VAL = SYSDATE " +
				"WHERE CODICE_FISCALE = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, codiceFiscaleUtenti);
		query.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateUtentiPerRipristino(HashMap<String, String> input) throws SQLException {
		String sql = "UPDATE UTENTI SET DATA_FINE_VAL = NULL, CODICE_CDR = ? WHERE CODICE_FISCALE = ?";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("newCdr"));
		query.setParameter(2, input.get("codiceFiscaleUtenti"));
		query.executeUpdate();
	}

	public void setFlagAutorizzatoreIlivelloNOPerCessazione(String userId) throws SQLException {
		String sql = "UPDATE UTENTI SET AUTORIZZATORE_I_LIV = 'NO' WHERE codice_fiscale = ?";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, userId);
		query.executeUpdate();
	}

	public void setFlagAutorizzatoreIIlivelloNOPerCessazione(String userId) throws SQLException {
		String sql = "UPDATE UTENTI SET AUTORIZZATORE_II_LIV = 'NO' WHERE codice_fiscale = ?";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, userId);
		query.executeUpdate();
	}

	public void setFlagRichiedenteNOPerCessazione(String userId) throws SQLException {
		String sql = "UPDATE UTENTI SET RICHIEDENTE = 'NO' WHERE codice_fiscale = ?";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, userId);
		query.executeUpdate();
	}

	public Integer verifyDelegato(String cfDelegato) throws SQLException {
		String sql = "SELECT COUNT(*) FROM DELEGHE WHERE codice_fiscale_delegato = ? " +
				"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cfDelegato);
		return ((Number) query.getSingleResult()).intValue();
	}

	public Integer countAssociazOperRichiAutor(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT COUNT(*) FROM ASSOCIAZ_OPER_RICHI_AUTOR WHERE CODICE_FISCALE_OPERATORE = ? " +
				"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		return ((Number) query.getSingleResult()).intValue();
	}

	public List<AssociazOperRichiAutor> selectCFRichiedenteEAutorizzatoreByCFOperatore(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT codice_fiscale_richiedente, codice_fiscale_autorizzatore, CODICE_CDR_RICHIEDENTE FROM ASSOCIAZ_OPER_RICHI_AUTOR " +
				"WHERE CODICE_FISCALE_OPERATORE = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		List<AssociazOperRichiAutor> dtoList = new ArrayList<>();
		for (Object[] row : results) {
			AssociazOperRichiAutor dto = new AssociazOperRichiAutor();
			dto.setCfRichiedente((String) row[0]);
			dto.setCfAutorizzatore((String) row[1]);
			dto.setCodiceCdRRichiedente((String) row[2]);
			dtoList.add(dto);
		}
		return dtoList;
	}
@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void closeAssociazOperRichiAutorByCFOperatore(HashMap<String, String> input) throws SQLException {
		String sql = "UPDATE ASSOCIAZ_OPER_RICHI_AUTOR SET DATA_FINE_VAL = SYSDATE " +
				"WHERE CODICE_FISCALE_OPERATORE = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		query.executeUpdate();
	}

	public List<RegistroRichieste> selectIdRichiestaFromRegistroRichieste(RegistroRichieste registroRichieste) throws SQLException {
		String sql = "SELECT id_richiesta FROM REGISTRO_RICHIESTE WHERE cdr_utente = ? AND cf_utente = ?";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, registroRichieste.getCdrUtenteRegistroRichieste());
		query.setParameter(2, registroRichieste.getCfUtenteRegistroRichieste());
		
		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<RegistroRichieste> dtoList = new ArrayList<>();
		for (Object row : results) {
			RegistroRichieste dto = new RegistroRichieste();
			dto.setIdRichiesta(((Number) row).intValue());
			dtoList.add(dto);
		}
		return dtoList;
	}

	public List<NotificaOperatoriDAO> utentiRichiesteDaSegnalare(HashMap<String, String> input) throws SQLException {
		String sql = "SELECT DISTINCT ST.CODICE_CDR_II_LIV_GERARCHICO, ST.CENTRALE_PERIFERICO, CDR.DESCRIZIONE, " +
				"UT.COGNOME, UT.NOME, UT.CODICE_CDR, UT.CODICE_FISCALE FROM REGISTRO_RICHIESTE RR, STATO_RICHIESTA SR, " +
				"STRUTTURA ST, RELAZIONE_CDR_UFFICIO_STRUT RCUS, UTENTI UT, CDR CDR " +
				"WHERE DATA_ESITO_FINALE IS NULL AND RR.STATO_RICHIESTA = SR.STATO AND CANCELLABILE = 'SI' " +
				"AND ((CDR_AUTORIZZATORE_1 IS NULL AND CF_AUTORIZZATORE_1 = ?) " +
				"OR (CDR_AUTORIZZATORE_2 IS NULL AND CF_AUTORIZZATORE_2 = ?)) " +
				"AND RR.CDR_UTENTE = RCUS.CODICE_CDR AND RCUS.VERTICE_STRUTTURA = ST.CODICE_CDR " +
				"AND RR.CF_UTENTE = UT.CODICE_FISCALE AND RR.CDR_UTENTE = CDR.CODICE_CDR " +
				"ORDER BY ST.CODICE_CDR_II_LIV_GERARCHICO, CDR.DESCRIZIONE, UT.COGNOME";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("cfResponsabile"));
		query.setParameter(2, input.get("cfResponsabile"));
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		List<NotificaOperatoriDAO> dtoList = new ArrayList<>();
		for (Object[] row : results) {
			NotificaOperatoriDAO dto = new NotificaOperatoriDAO();
			dto.setCodice_cdr_ii_liv_gerarchico((String) row[0]);
			dto.setCentrale_periferico((String) row[1]);
			dto.setDescr_cdr((String) row[2]);
			dto.setCognome((String) row[3]);
			dto.setNome((String) row[4]);
			dto.setCdr((String) row[5]);
			dto.setCodiceFiscaleUtente((String) row[6]);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void annullamentoRichieste(HashMap<String, String> input) throws SQLException {
		String sql = "UPDATE REGISTRO_RICHIESTE SET stato_richiesta = 'ANN', data_chiusura_richiesta = SYSDATE " +
				"WHERE cf_utente = ? AND data_chiusura_richiesta IS NULL " +
				"AND codice_ambito IN (SELECT codice_ambito FROM ambito_applicativo WHERE SINCRONIZZATA_CAU = 'SI')";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		query.executeUpdate();
	}
@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void annullamentoRichieste2(HashMap<String, String> input) throws SQLException {
		String sql = "UPDATE REGISTRO_RICHIESTE SET stato_richiesta = 'ANN', data_chiusura_richiesta = SYSDATE " +
				"WHERE cf_utente = ? AND data_chiusura_richiesta IS NULL AND codice_ambito IS NULL";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.get("userId"));
		query.executeUpdate();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void startBatchVariazioneDatiUte() throws SQLException {
		String sql = "update BATCH_HR set\r\n"
				+ "		DATA_INIZIO_UTENTI = SYSDATE,\r\n"
				+ "		DATA_FINE_UTENTI = NULL";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void endBatchVariazioneDatiUte() throws SQLException {
		String sql = "update BATCH_HR set\r\n"
				+ "		DATA_FINE_UTENTI = SYSDATE";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	

	private Utenti mapRowToUtenti(Object[] row) {
		Utenti dto = new Utenti();
		dto.setNome((String) row[0]);
		dto.setCognome((String) row[1]);
		dto.seteMail((String) row[2]);
		dto.setCodiceCdR((String) row[3]);
		dto.setRichiedente((String) row[4]);
		dto.setAutorizzatoreIliv((String) row[5]);
		dto.setAutorizzatoreIIliv((String) row[6]);
		dto.setCodiceFiscale((String) row[7]);
		return dto;
	}

	private StrutturaIterDAO mapRowToStrutturaIterDAO(Object[] row) {
		StrutturaIterDAO dto = new StrutturaIterDAO();
		dto.setIdentificativoIter(((Number) row[0]).intValue());
		dto.setTipoUfficioRichiedente((String) row[1]);
		dto.setIterStandard((String) row[2]);
		dto.setTipoCdrAutorizILiv((String) row[3]);
		dto.setCdrAutorizILiv((String) row[4]);
		dto.setCf_I((String) row[5]);
		dto.setPrevistoAutorizzatoreIILiv((String) row[6]);
		dto.setTipoCdrAutorizIILiv((String) row[7]);
		dto.setCdrAutorizIILiv((String) row[8]);
		dto.setCf_II((String) row[9]);
		dto.setGestOperStrutRichiedente((String) row[10]);
		dto.setGestOperStrutAutorILiv((String) row[11]);
		dto.setGestOperStrutAutorIILiv((String) row[12]);
		dto.setGruppoGestoriOperatori((String) row[13]);
		dto.setCdrGestoriOperatori((String) row[14]);
		dto.setDataFineVal((java.sql.Date) row[15]);
		dto.setIdAuditInizio(((Number) row[16]).intValue());
		dto.setCodiceFiscaleInizio((String) row[17]);
		dto.setIdAuditFine(((Number) row[18]).intValue());
		dto.setCodiceFiscaleFine((String) row[19]);
		return dto;
	}
}
