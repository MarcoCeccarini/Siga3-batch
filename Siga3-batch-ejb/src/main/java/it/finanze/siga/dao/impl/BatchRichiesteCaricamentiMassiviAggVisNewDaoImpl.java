package it.finanze.siga.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import it.finanze.scheduler.bean.ProfiloUtenteInVisibilitaBean;
import it.finanze.scheduler.bean.ProfiloVisibilitaTracciamentoBean;
import it.finanze.scheduler.bean.RichiestaAbilitazioneDisabilitazioneBean;
import it.finanze.scheduler.bean.RichiestaVisibilitaBean;
import it.finanze.siga.bean.CaricamentoMassivoEntity;
import it.finanze.siga.bean.FileAbilitazioneBean;
import it.finanze.siga.bean.ProfiloRichiestaBean;
import it.finanze.siga.finder.InterrogazioneCaricamentiFinder;
import it.finanze.siga.finder.ProfiloFinder;
import it.finanze.siga.finder.ProfiloUtenteInVisibilitaFinder;
import it.sogei.eaf.util.CheckException;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
@Named("BatchRichiesteCaricamentiMassiviAggVisNewDaoImpl")
public class BatchRichiesteCaricamentiMassiviAggVisNewDaoImpl extends SigaDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		//super.setEntityManager(entityManager);
	}

	public BatchRichiesteCaricamentiMassiviAggVisNewDaoImpl() {
		super();
	}

	public List<CaricamentoMassivoEntity> getListaCaricamentiAggVis(InterrogazioneCaricamentiFinder finder) throws SQLException {
		List<CaricamentoMassivoEntity> lista = new ArrayList<CaricamentoMassivoEntity>();
		try {
			String sql = "SELECT \r\n"
					+ "			CMVR.ID_CARICAMENTO,\r\n"
					+ "			TO_CHAR(CMVR.DATA_ORA_CARICAMENTO, 'DD/MM/YYYY HH24:MI:SS')AS DATA_CARICAMENTO, \r\n"
					+ "			CMVR.DESCRIZIONE_RICHIESTA, \r\n"
					+ "			CMVR.STATO_RICHIESTA,\r\n"
					+ "			UT.CODICE_FISCALE AS CF_RICHIEDENTE, \r\n"
					+ "			UT.NOME AS NOME_RICHIEDENTE, \r\n"
					+ "			UT.COGNOME AS COGNOME_RICHIEDENTE,\r\n"
					+ "      		CMS.DESCRIZIONE AS DESCR_STATO,\r\n"
					+ "      		CMVR.FILE_VISIBILITA,\r\n"
					+ "      		CMVR.CF_AMMINISTRATORE \r\n"
					+ "		FROM \r\n"
					+ "		  	CM_AGG_VISIBILITA_RICHIESTA CMVR, \r\n"
					+ "  			UTENTI UT, \r\n"
					+ "        	CM_STATI CMS\r\n"
					+ "		WHERE\r\n"
					+ "        	DATA_ORA_CANCELLAZIONE IS NULL\r\n"
					+ "        	AND DATA_ORA_ELABORAZIONE IS NULL\r\n"
					+ "	        and	ID_CARICAMENTO = ?1 \r\n"
					+ "	        AND CMVR.CF_RICHIEDENTE = UT.CODICE_FISCALE\r\n"
					+ "		  	AND CMVR.STATO_RICHIESTA = CMS.STATO\r\n"
					+ "		  	AND (UT.DATA_FINE_VAL IS NULL OR UT.DATA_FINE_VAL > SYSDATE)\r\n"
					+ "		ORDER BY CMVR.ID_CARICAMENTO ASC";
			
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, finder.getIdCaricamento());
			
			
			List<Object[]> results = query.getResultList();
			
			for (Object[] row : results) {
				CaricamentoMassivoEntity entity = new CaricamentoMassivoEntity();
				entity.setIdCaricamento(row[0] != null ? row[0].toString() : "");
				entity.setDataCaricamento(row[1] != null ? row[1].toString() : "");
				entity.setDescrizioneRichiestaCaricamento(row[2] != null ? row[2].toString() : "");
				entity.setStatoRichiesta(row[3] != null ? row[3].toString() : "");
				entity.setCfRichiedente(row[4] != null ? row[4].toString() : "");
				entity.setNomeRichiedente(row[5] != null ? row[5].toString() : "");
				entity.setCognomeRichiedente(row[6] != null ? row[6].toString() : "");
				entity.setDescrStatoRichiestaCaricamento(row[7] != null ? row[7].toString() : "");
				entity.setbFileVisibilita((byte[]) row[8]);
				entity.setCfAmministratore(row[9] != null ? row[9].toString() : "");
				
				lista.add(entity);
			}
			return lista;
		} catch (Exception e) {
			throw new SQLException("Error fetching getListaCaricamentiAggVis: " + e.getMessage(), e);
		}
	}

	public Integer isAmministratoreCentrale(String cf) throws SQLException {
		try {
			String sql = "SELECT \r\n"
					+ "			COUNT(*)\r\n"
					+ "		FROM \r\n"
					+ "			PROFILI_ATTIVI_UTENTE \r\n"
					+ "		WHERE \r\n"
					+ "			CF_UTENTE = ?1 \r\n"
					+ "			AND CODICE_APPLICAZIONE = 'ESG'\r\n"
					+ "			AND CODICE_PROFILO = 'ESG_AMM_Centrale_3'\r\n"
					+ "		    AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);
			Object result = query.getSingleResult();
			return ((Number) result).intValue();
		} catch (Exception e) {
			throw new SQLException("Error in isAmministratoreCentrale: " + e.getMessage(), e);
		}
	}

	public Integer isAmministratoreCentrale2(String cf) throws SQLException {
		try {
			String sql = "	SELECT \r\n"
					+ "			COUNT(*)\r\n"
					+ "		FROM \r\n"
					+ "			AMMINISTRATORI_CENTRALI\r\n"
					+ "		WHERE \r\n"
					+ "			CODICE_FISCALE = ?1 \r\n"
					+ "		    AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);
			Object result = query.getSingleResult();
			return ((Number) result).intValue();
		} catch (Exception e) {
			throw new SQLException("Error in isAmministratoreCentrale2: " + e.getMessage(), e);
		}
	}

	public Integer countAssocProfRoleGroup(HashMap<String, String> queryMap) throws SQLException {
		try {
			String codProfilo = queryMap.get("codProfilo");
			String codRoleGroup = queryMap.get("codRoleGroup");
			String azione = queryMap.get("azione");
			
			String sql = "SELECT \r\n"
					+ "			COUNT(*)\r\n"
					+ "		FROM \r\n"
					+ "			RELAZIONE_APPL_PROF_ROLE_GROUP RAPRG\r\n"
					+ "		WHERE \r\n"
					+ "			RAPRG.CODICE_PROFILO = ?1 \r\n"
					+ "  			AND RAPRG.CODICE_ROLE_GROUP = ?2 \r\n";
				if("Rimozione".equals(azione)) {	
					sql+= "	AND (RAPRG.DATA_FINE_VAL IS NULL OR RAPRG.DATA_FINE_VAL > SYSDATE)\r\n";
				}
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, codProfilo);
			query.setParameter(2, codRoleGroup);
			Object result = query.getSingleResult();
			return ((Number) result).intValue();
		} catch (Exception e) {
			throw new SQLException("Error in countAssocProfRoleGroup: " + e.getMessage(), e);
		}
	}

	public List<RichiestaVisibilitaBean> getDatiRichiestaVisibByCfCdr(HashMap<String, String> queryMap) throws SQLException {
		List<RichiestaVisibilitaBean> lista = new ArrayList<RichiestaVisibilitaBean>();
		try {
			String cf = queryMap.get("codFiscale");
			String cdr = queryMap.get("codCdr");
			
			String sql = "SELECT \r\n"
					+ "			UV.ID_RICHIESTA_VISIBILITA, \r\n"
					+ "			RCUS.CODICE_UFFICIO, \r\n"
					+ "			UV.DATA_FINE_VAL\r\n"
					+ "		FROM \r\n"
					+ "			UTENTE_IN_VISIBILITA UV,  \r\n"
					+ "			RELAZIONE_CDR_UFFICIO_STRUT RCUS\r\n"
					+ "		WHERE \r\n"
					+ "			CF_UTENTE = ?1 \r\n"
					+ "			AND UV.CODICE_CDR = ?2 \r\n"
					+ "			AND RCUS.CODICE_CDR = UV.CODICE_CDR\r\n"
					+ "			AND (UV.DATA_FINE_VAL   IS NULL OR UV.DATA_FINE_VAL > SYSDATE)\r\n"
					+ "			AND (RCUS.DATA_FINE_VAL IS NULL  OR RCUS.DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);
			query.setParameter(2, cdr);
			List<Object[]> results = query.getResultList();
			
			for (Object[] row : results) {
				RichiestaVisibilitaBean bean = new RichiestaVisibilitaBean();
				bean.setIdRichiestaVisibilita(row[0] != null ? ((Number) row[0]).longValue() : 0L);
				bean.setCodiceUfficio(row[1] != null ? row[1].toString() : "");
				if (row[2] != null) {
					bean.setDataFineValidita(new Date(((Timestamp) row[2]).getTime()));
				}
				lista.add(bean);
			}
			return lista;
		} catch (Exception e) {
			throw new SQLException("Error in getDatiRichiestaVisibByCfCdr: " + e.getMessage(), e);
		}
	}

	
	

	public String getVerticeUfficioFromCdr(String codiceCdr) throws SQLException {
		try {
			String sql = "SELECT \r\n"
					+ "			VERTICE_UFFICIO \r\n"
					+ "		FROM \r\n"
					+ "			RELAZIONE_CDR_UFFICIO_STRUT \r\n"
					+ "		WHERE \r\n"
					+ "			CODICE_CDR = ?1 \r\n"
					+ "			AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, codiceCdr);
			Object result = query.getSingleResult();
			return result != null ? result.toString() : "";
		} catch (NoResultException e) {
			return "";
		} catch (Exception e) {
			throw new SQLException("Error in getVerticeUfficioFromCdr: " + e.getMessage(), e);
		}
	}

	public Integer countProfiliUtentiInVisibilita(ProfiloUtenteInVisibilitaFinder finder) throws SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM PROFILI_UTENTE_IN_VISIBILITA WHERE CF_UTENTE = ?1 AND ID_RICHIESTA_VISIBILITA = ?2 AND CODICE_ROLE_GROUP = ?3 AND CODICE_PROFILO = ?4"
					+ " AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, finder.getCodiceFiscale());
			query.setParameter(2, finder.getIdRichiestaVisibilita());
			query.setParameter(3, finder.getCodiceRoleGroup());
			query.setParameter(4, finder.getCodiceProfilo());
			Object result = query.getSingleResult();
			return ((Number) result).intValue();
		} catch (Exception e) {
			throw new SQLException("Error in countProfiliUtentiInVisibilita: " + e.getMessage(), e);
		}
	}

	public List<ProfiloRichiestaBean> getElencoProfiliUfficioDaCdRList(ProfiloFinder finder) throws SQLException {
		List<ProfiloRichiestaBean> lista = new ArrayList<ProfiloRichiestaBean>();
		try {
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<>();
			int paramIndex = 1;

			sql.append("SELECT DISTINCT CODICE_APPLICAZIONE, CODICE_ROLE_GROUP, DESCRIZIONE, CODICE_AMBITO_APPLICATIVO, ");
			sql.append("AMBITO_DESC, CODICE_PROFILO, PROFILO_DESC, TIPO_ABILITAZIONE, COUNT (*) AS NUMERO_PROFILI FROM ( ");

			// PART 1
			sql.append("SELECT DISTINCT AGCU.CODICE_APPLICAZIONE AS CODICE_APPLICAZIONE, ");
			sql.append("AGCU.CODICE_ROLE_GROUP AS CODICE_ROLE_GROUP, APP.DESCRIZIONE AS DESCRIZIONE, ");
			sql.append("APP.CODICE_AMBITO_APPLICATIVO AS CODICE_AMBITO_APPLICATIVO, AA.DESCRIZIONE AS AMBITO_DESC, ");
			sql.append("AGCU.CODICE_PROFILO AS CODICE_PROFILO, PR.DESCRIZIONE AS PROFILO_DESC, AGCU.TIPO_PROFILAZIONE AS TIPO_ABILITAZIONE ");
			sql.append("FROM ADMIN_GROUP_CDR_UFF AGCU, AMBITO_APPLICATIVO AA, APPLICAZIONI APP, cdr cdr, PROFILO PR, relazione_cdr_ufficio_strut rcus ");
			sql.append("WHERE (AGCU.DATA_FINE_VAL IS NULL OR AGCU.DATA_FINE_VAL > SYSDATE) ");
			sql.append("AND (APP.DATA_FINE_VAL IS NULL OR APP.DATA_FINE_VAL > SYSDATE) ");
			sql.append("AND (rcus.DATA_FINE_VAL IS NULL OR rcus.DATA_FINE_VAL > SYSDATE) ");
			sql.append("AND (PR.DATA_FINE_VAL IS NULL OR PR.DATA_FINE_VAL > SYSDATE) ");
			sql.append("AND (cdr.DATA_FINE_VAL IS NULL OR cdr.DATA_FINE_VAL > SYSDATE) ");

			if (finder.getRoleGroupList() != null && !finder.getRoleGroupList().isEmpty()) {
				sql.append("AND AGCU.CODICE_ROLE_GROUP IN (");
				for (int i = 0; i < finder.getRoleGroupList().size(); i++) {
					sql.append("?").append(paramIndex++);
					params.add(finder.getRoleGroupList().get(i));
					if (i < finder.getRoleGroupList().size() - 1) sql.append(", ");
				}
				sql.append(") ");
			}

			if (finder.getCodiceAmbitoApplicativo() != null && !finder.getCodiceAmbitoApplicativo().isEmpty()) {
				sql.append("AND APP.CODICE_AMBITO_APPLICATIVO = ?").append(paramIndex++);
				params.add(finder.getCodiceAmbitoApplicativo());
			}

			if (finder.getTipoAbilitazione() != null && !finder.getTipoAbilitazione().isEmpty()) {
				sql.append("AND AGCU.TIPO_PROFILAZIONE = ?").append(paramIndex++);
				params.add(finder.getTipoAbilitazione());
			}

			sql.append("AND APP.CODICE_APPLICAZIONE = AGCU.CODICE_APPLICAZIONE ");
			
			if (finder.getCdrLst() != null && !finder.getCdrLst().isEmpty()) {
				sql.append("AND rcus.codice_cdr IN (");
				for (int i = 0; i < finder.getCdrLst().size(); i++) {
					sql.append("?").append(paramIndex++);
					params.add(finder.getCdrLst().get(i));
					if (i < finder.getCdrLst().size() - 1) sql.append(", ");
				}
				sql.append(") ");
			}

			if (finder.getCodFis() != null && !finder.getCodFis().isEmpty()) {
				sql.append("AND ( (AGCU.CODICE_PROFILO IN (SELECT AC.CODICE_PROFILO FROM AMMINISTRATORI_CENTRALI AC WHERE AGCU.CODICE_APPLICAZIONE = AC.CODICE_APPLICAZIONE AND AC.CODICE_FISCALE = ?").append(paramIndex++);
				params.add(finder.getCodFis());
				sql.append(" AND AC.CODICE_PROFILO IS NOT NULL AND (AC.DATA_FINE_VAL IS NULL OR AC.DATA_FINE_VAL > SYSDATE))) ");
				sql.append("OR (AGCU.CODICE_APPLICAZIONE IN (SELECT AC.CODICE_APPLICAZIONE FROM AMMINISTRATORI_CENTRALI AC WHERE AGCU.CODICE_APPLICAZIONE = AC.CODICE_APPLICAZIONE AND AC.CODICE_FISCALE = ?").append(paramIndex++);
				params.add(finder.getCodFis());
				sql.append(" AND AC.CODICE_PROFILO IS NULL AND (AC.DATA_FINE_VAL IS NULL OR AC.DATA_FINE_VAL > SYSDATE))) ) ");
			}

			sql.append("AND rcus.codice_cdr = cdr.codice_cdr ");
			sql.append("AND ((agcu.codice_cdr = rcus.codice_cdr) OR (agcu.codice_ufficio = rcus.codice_ufficio AND cdr.profilazione_bloccata = 'NO')) ");
			sql.append("AND AA.CODICE_AMBITO = APP.CODICE_AMBITO_APPLICATIVO ");
			sql.append("AND AGCU.CODICE_PROFILO = PR.CODICE_PROFILO ");

			sql.append("UNION ");

			// PART 2
			sql.append("SELECT DISTINCT AGTUC.CODICE_APPLICAZIONE AS CODICE_APPLICAZIONE, AGTUC.CODICE_ROLE_GROUP AS CODICE_ROLE_GROUP, ");
			sql.append("APP.DESCRIZIONE AS DESCRIZIONE, APP.CODICE_AMBITO_APPLICATIVO AS CODICE_AMBITO_APPLICATIVO, AA.DESCRIZIONE AS AMBITO_DESC, ");
			sql.append("AGTUC.CODICE_PROFILO AS CODICE_PROFILO, PR.DESCRIZIONE AS PROFILO_DESC, AGTUC.TIPO_PROFILAZIONE AS TIPO_ABILITAZIONE ");
			sql.append("FROM ADMIN_GROUP_TIPO_UFF_CDR AGTUC, APPLICAZIONI APP, AMBITO_APPLICATIVO AA, CDR cdr, relazione_cdr_ufficio_strut rcus, ufficio uff, PROFILO PR ");
			sql.append("WHERE (AGTUC.DATA_FINE_VAL IS NULL OR AGTUC.DATA_FINE_VAL > SYSDATE) ");
			sql.append("AND (APP.DATA_FINE_VAL IS NULL OR APP.DATA_FINE_VAL > SYSDATE) ");
			sql.append("AND (rcus.DATA_FINE_VAL IS NULL OR rcus.DATA_FINE_VAL > SYSDATE) ");
			sql.append("AND (cdr.DATA_FINE_VAL IS NULL OR cdr.DATA_FINE_VAL > SYSDATE) ");
			sql.append("AND (uff.DATA_FINE_VAL IS NULL OR uff.DATA_FINE_VAL > SYSDATE) ");
			sql.append("AND (PR.DATA_FINE_VAL IS NULL OR PR.DATA_FINE_VAL > SYSDATE) ");
			sql.append("AND AA.CODICE_AMBITO = APP.CODICE_AMBITO_APPLICATIVO ");
			sql.append("AND AGTUC.CODICE_PROFILO = PR.CODICE_PROFILO ");

			if (finder.getCodiceAmbitoApplicativo() != null && !finder.getCodiceAmbitoApplicativo().isEmpty()) {
				sql.append("AND APP.CODICE_AMBITO_APPLICATIVO = ?").append(paramIndex++);
				params.add(finder.getCodiceAmbitoApplicativo());
			}

			if (finder.getTipoAbilitazione() != null && !finder.getTipoAbilitazione().isEmpty()) {
				sql.append("AND AGTUC.TIPO_PROFILAZIONE = ?").append(paramIndex++);
				params.add(finder.getTipoAbilitazione());
			}

			if (finder.getRoleGroupList() != null && !finder.getRoleGroupList().isEmpty()) {
				sql.append("AND AGTUC.CODICE_ROLE_GROUP IN (");
				for (int i = 0; i < finder.getRoleGroupList().size(); i++) {
					sql.append("?").append(paramIndex++);
					params.add(finder.getRoleGroupList().get(i));
					if (i < finder.getRoleGroupList().size() - 1) sql.append(", ");
				}
				sql.append(") ");
			}

			sql.append("AND APP.CODICE_APPLICAZIONE = AGTUC.CODICE_APPLICAZIONE ");

			if (finder.getCdrLst() != null && !finder.getCdrLst().isEmpty()) {
				sql.append("AND rcus.codice_cdr IN (");
				for (int i = 0; i < finder.getCdrLst().size(); i++) {
					sql.append("?").append(paramIndex++);
					params.add(finder.getCdrLst().get(i));
					if (i < finder.getCdrLst().size() - 1) sql.append(", ");
				}
				sql.append(") ");
			}

			if (finder.getCodFis() != null && !finder.getCodFis().isEmpty()) {
				sql.append("AND ( (AGTUC.CODICE_PROFILO IN (SELECT AC.CODICE_PROFILO FROM AMMINISTRATORI_CENTRALI AC WHERE AGTUC.CODICE_APPLICAZIONE = AC.CODICE_APPLICAZIONE AND AC.CODICE_FISCALE = ?").append(paramIndex++);
				params.add(finder.getCodFis());
				sql.append(" AND AC.CODICE_PROFILO IS NOT NULL AND (AC.DATA_FINE_VAL IS NULL OR AC.DATA_FINE_VAL > SYSDATE))) ");
				sql.append("OR (AGTUC.CODICE_APPLICAZIONE IN (SELECT AC.CODICE_APPLICAZIONE FROM AMMINISTRATORI_CENTRALI AC WHERE AGTUC.CODICE_APPLICAZIONE = AC.CODICE_APPLICAZIONE AND AC.CODICE_FISCALE = ?").append(paramIndex++);
				params.add(finder.getCodFis());
				sql.append(" AND AC.CODICE_PROFILO IS NULL AND (AC.DATA_FINE_VAL IS NULL OR AC.DATA_FINE_VAL > SYSDATE))) ) ");
			}

			sql.append("AND rcus.codice_cdr = cdr.codice_cdr AND profilazione_bloccata = 'NO' ");
			sql.append("AND ((agtuc.tipo_ufficio = uff.tipo_ufficio AND vertice_ufficio = uff.codice_cdr) OR (agtuc.tipologia_cdr = cdr.tipo_cdr)) ");
			sql.append("AND ((agtuc.regione IS NULL AND agtuc.provincia IS NULL) OR (agtuc.regione = cdr.regione AND agtuc.provincia IS NULL) OR (agtuc.regione IS NULL AND agtuc.provincia = cdr.provincia)) ");

			sql.append(") GROUP BY CODICE_APPLICAZIONE, CODICE_ROLE_GROUP, DESCRIZIONE, CODICE_PROFILO, PROFILO_DESC, CODICE_AMBITO_APPLICATIVO, AMBITO_DESC, TIPO_ABILITAZIONE ");
			sql.append("ORDER BY CODICE_AMBITO_APPLICATIVO, DESCRIZIONE, PROFILO_DESC");

			Query query = entityManager.createNativeQuery(sql.toString());
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i + 1, params.get(i));
			}

			List<Object[]> resultsRaw = query.getResultList();
			for (Object[] row : resultsRaw) {
				ProfiloRichiestaBean bean = new ProfiloRichiestaBean();
				bean.setCodiceApplicazione(row[0] != null ? row[0].toString() : "");
				bean.setCodiceRoleGroup(row[1] != null ? row[1].toString() : "");
				bean.setRoleGroupDesc(row[2] != null ? row[2].toString() : "");
				bean.setCodiceAmbito(row[3] != null ? Integer.valueOf( row[3].toString()) : null);
				bean.setDescrAmbitoAppl(row[4] != null ? row[4].toString() : "");
				bean.setCodiceProfilo(row[5] != null ? row[5].toString() : "");
				bean.setProfiloDesc(row[6] != null ? row[6].toString() : "");
				bean.setTipoAbilitazione(row[7] != null ? row[7].toString() : "");
				bean.setNumeroProfiliPerRG(row[8] != null ? ((Number) row[8]).intValue() : 0);
				bean.setDescrizione(row[6] != null ? row[6].toString() : "");
				lista.add(bean);
			}
			return lista;
		} catch (Exception e) {
			throw new SQLException("Error in getElencoProfiliUfficioDaCdRList: " + e.getMessage(), e);
		}
	}

	public List<ProfiloUtenteInVisibilitaBean> getProfiliUtentiInVisibilita(ProfiloUtenteInVisibilitaFinder finder) throws SQLException {
		List<ProfiloUtenteInVisibilitaBean> lista = new ArrayList<ProfiloUtenteInVisibilitaBean>();
		try {
			String sql = "SELECT \r\n"
					+ "			CODICE_PROFILO,\r\n"
					+ "			PROFILO_ASSEGNATO\r\n"
					+ "		FROM \r\n"
					+ "			PROFILI_UTENTE_IN_VISIBILITA\r\n"
					+ "		WHERE\r\n"
					+ "			CODICE_ROLE_GROUP = ?1\r\n"
					+ "	        AND	CF_UTENTE = ?2 \r\n"
					+ "	        AND	ID_RICHIESTA_VISIBILITA = ?3 \r\n"
					+ "			AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, finder.getCodiceRoleGroup());
			query.setParameter(2, finder.getCodiceFiscale());
			query.setParameter(3, finder.getIdRichiestaVisibilita());
			List<Object[]> results = query.getResultList();
			
			for (Object[] row : results) {
				ProfiloUtenteInVisibilitaBean bean = new ProfiloUtenteInVisibilitaBean();
				bean.setCodiceProfilo(row[0] != null ? row[0].toString() : "");
				bean.setProfiloAssegnato(row[1] != null ? row[1].toString() : "");
				lista.add(bean);
			}
			return lista;
		} catch (Exception e) {
			throw new SQLException("Error in getProfiliUtentiInVisibilita: " + e.getMessage(), e);
		}
	}

	public List<ProfiloUtenteInVisibilitaBean> getProfiliUtentiInVisibilitaRimozione(ProfiloUtenteInVisibilitaFinder finder) throws SQLException {
		return getProfiliUtentiInVisibilita(finder);
	}

	public List<RichiestaAbilitazioneDisabilitazioneBean> controllaPresenzaRichiesteAbilitazione(ProfiloUtenteInVisibilitaFinder finder) throws SQLException {
		List<RichiestaAbilitazioneDisabilitazioneBean> lista = new ArrayList<RichiestaAbilitazioneDisabilitazioneBean>();
		try {
			String sql = "SELECT \r\n"
					+ "			ID_RICHIESTA, \r\n"
					+ "			CODICE_PROFILO\r\n"
					+ "		FROM \r\n"
					+ "			PROFILI_RICHIESTA\r\n"
					+ "		WHERE \r\n"
					+ "			ID_RICHIESTA_VISIBILITA = ?1 \r\n"
					+ "			AND CODICE_ROLE_GROUP = ?2 \r\n"
					+ "			AND CODICE_PROFILO = ?3 \r\n"
					+ "			AND OPERAZIONE_ESEGUITA = 'NO'";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, finder.getIdRichiestaVisibilita());
			query.setParameter(2, finder.getCodiceRoleGroup());
			query.setParameter(3, finder.getCodiceProfilo());
			List<Object[]> results = query.getResultList();
			
			for (Object[] row : results) {
				RichiestaAbilitazioneDisabilitazioneBean bean = new RichiestaAbilitazioneDisabilitazioneBean();
				bean.setIdRichiesta(((Number)row[0]).longValue());
				bean.setCodiceProfilo((String)row[1]);
				lista.add(bean);
			}
			return lista;
		} catch (Exception e) {
			throw new SQLException("Error in controllaPresenzaRichiesteAbilitazione: " + e.getMessage(), e);
		}
	}

	

	public List<String> getProfiliEcaFigliVisibilita(ProfiloUtenteInVisibilitaBean datiProfiloUtenteVisibilita) throws SQLException {
		List<String> lista = new ArrayList<String>();
		try {
			String sql = "SELECT \r\n"
					+ "			CODICE_PROFILO\r\n"
					+ "		FROM \r\n"
					+ "			PROFILI_UTENTE_IN_VISIBILITA\r\n"
					+ "		WHERE\r\n"
					+ "			CODICE_ROLE_GROUP = ?1\r\n"
					+ "			AND ID_RICHIESTA_VISIBILITA = ?2\r\n"
					+ "			AND CF_UTENTE = ?3\r\n"
					+ "			AND CODICE_PROFILO != ?4";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, datiProfiloUtenteVisibilita.getCodiceRoleGroup());
			query.setParameter(2, datiProfiloUtenteVisibilita.getIdRichiestaVisibilita());
			query.setParameter(3, datiProfiloUtenteVisibilita.getCfUtente());
			query.setParameter(4, datiProfiloUtenteVisibilita.getCodiceProfilo());
			List<Object[]> results = query.getResultList();
			
			for (Object[] row : results) {
				lista.add(row[0] != null ? row[0].toString() : "");
			}
			return lista;
		} catch (Exception e) {
			throw new SQLException("Error in getProfiliEcaFigliVisibilita: " + e.getMessage(), e);
		}
	}

	public void inserisciRichiesteCaricamentoMassivoAggVis(List<FileAbilitazioneBean> listaFileAbilitazione, List<ProfiloVisibilitaTracciamentoBean> profVisTracBean, List<ProfiloUtenteInVisibilitaBean> profiliDaAggiungereList, List<ProfiloUtenteInVisibilitaBean> profiliDaRimuovereList) throws SQLException, CheckException {
			// Ciclo che gestisce la scrittura su DB !!

			if (listaFileAbilitazione != null && listaFileAbilitazione.size() > 0) {
				for (int j = 0; j < listaFileAbilitazione.size(); j++) {
					FileAbilitazioneBean fileAbilitazione = listaFileAbilitazione.get(j);
					inserisciFileVisibilita(fileAbilitazione);
				}
			}

			if (profVisTracBean != null && profVisTracBean.size() > 0) {
				for (int j = 0; j < profVisTracBean.size(); j++) {
					ProfiloVisibilitaTracciamentoBean profTracciamento = profVisTracBean.get(j);
					inserisciProfiliVisibilitaTracciamento(profTracciamento);
				}
			}

			if (profiliDaAggiungereList != null && profiliDaAggiungereList.size() > 0) {
				for (int j = 0; j < profiliDaAggiungereList.size(); j++) {
					ProfiloUtenteInVisibilitaBean profAggiungere = profiliDaAggiungereList.get(j);
					inserisciProfiloUtenteInVisibilita(profAggiungere);
				}
			}

			if (profiliDaRimuovereList != null && profiliDaRimuovereList.size() > 0) {
				for (int j = 0; j < profiliDaRimuovereList.size(); j++) {
					ProfiloUtenteInVisibilitaBean profRimuovere = profiliDaRimuovereList.get(j);
					deleteProfiloUtenteInVisibilita(profRimuovere);
				}
			}

	}
	
	private void inserisciFileVisibilita(FileAbilitazioneBean fab) throws SQLException {
		try {
			StringBuilder sql = new StringBuilder("INSERT INTO CM_AGG_VISIBILITA_FILE (ID_CARICAMENTO, STATO");
			List<Object> params = new ArrayList<>();
			params.add(fab.getIdCaricamento() != null ? Long.parseLong(fab.getIdCaricamento()) : null);
			params.add(fab.getStato());
			
			if (fab.getMotivazioneScarto() != null) sql.append(", MOTIVAZIONE_SCARTO");
			if (fab.getCodiceFiscale() != null) sql.append(", CODICE_FISCALE");
			if (fab.getCodiceRoleGroup() != null) sql.append(", CODICE_ROLE_GROUP");
			if (fab.getCodiceProfilo() != null) sql.append(", CODICE_PROFILO");
			if (fab.getCodiceCdr() != null) sql.append(", CODICE_CDR");
			if (fab.getAzione() != null) sql.append(", AZIONE");
			
			sql.append(") VALUES (?1, ?2");
			int paramIdx = 3;
			if (fab.getMotivazioneScarto() != null) { sql.append(", ?").append(paramIdx++); params.add(fab.getMotivazioneScarto()); }
			if (fab.getCodiceFiscale() != null) { sql.append(", ?").append(paramIdx++); params.add(fab.getCodiceFiscale()); }
			if (fab.getCodiceRoleGroup() != null) { sql.append(", ?").append(paramIdx++); params.add(fab.getCodiceRoleGroup()); }
			if (fab.getCodiceProfilo() != null) { sql.append(", ?").append(paramIdx++); params.add(fab.getCodiceProfilo()); }
			if (fab.getCodiceCdr() != null) { sql.append(", ?").append(paramIdx++); params.add(fab.getCodiceCdr()); }
			if (fab.getAzione() != null) { sql.append(", ?").append(paramIdx++); params.add(fab.getAzione()); }
			sql.append(")");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i + 1, params.get(i));
			}
			query.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Error in inserisciFileVisibilita: " + e.getMessage(), e);
		}
	}

	public void inserisciProfiliVisibilitaTracciamento(ProfiloVisibilitaTracciamentoBean pvtb) throws SQLException {
		try {
			StringBuilder sql = new StringBuilder("INSERT INTO CM_VISIBILITA_TRACCIAMENTO (CF_UTENTE, CODICE_APPLICAZIONE, CODICE_ROLE_GROUP, CODICE_PROFILO, CODICE_CDR, CODICE_UFFICIO, ID_RICHIESTA_VISIBILITA, DATA_INIZIO_VAL, DATA_FINE_VAL, DATA_VARIAZIONE, CF_VARIAZIONE, AZIONE");
			List<Object> params = new ArrayList<>();
			params.add(pvtb.getCfUtente());
			params.add(pvtb.getCodiceApplicazione());
			params.add(pvtb.getCodiceRoleGroup());
			params.add(pvtb.getCodiceProfilo());
			params.add(pvtb.getCodiceCdr());
			params.add(pvtb.getCodiceUfficio());
			params.add(pvtb.getIdRichiestaVisibilita() != null ? Long.parseLong(pvtb.getIdRichiestaVisibilita()) : null);
			params.add(pvtb.getDataInizioVal());
			params.add(pvtb.getDataFineVal());
			params.add(new java.util.Date()); // SYSDATE
			params.add(pvtb.getCfVariazione());
			params.add(pvtb.getAzione());
			
			if (pvtb.getIdCaricamentoMassivo() != null) sql.append(", ID_CARICAMENTO_MASSIVO");
			if (pvtb.getIdAudit() != null) sql.append(", ID_AUDIT");
			
			sql.append(") VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12");
			int paramIdx = 13;
			if (pvtb.getIdCaricamentoMassivo() != null) { sql.append(", ?").append(paramIdx++); params.add(pvtb.getIdCaricamentoMassivo()); }
			if (pvtb.getIdAudit() != null) { sql.append(", ?").append(paramIdx++); params.add(pvtb.getIdAudit()); }
			sql.append(")");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i + 1, params.get(i));
			}
			query.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Error in inserisciProfiliVisibilitaTracciamento: " + e.getMessage(), e);
		}
	}

	public void inserisciProfiloUtenteInVisibilita(ProfiloUtenteInVisibilitaBean puiv) throws SQLException {
		try {
			String sql = "INSERT INTO PROFILI_UTENTE_IN_VISIBILITA (CF_UTENTE, CODICE_APPLICAZIONE, CODICE_PROFILO, CODICE_CDR, CODICE_UFFICIO, CODICE_ROLE_GROUP, PROFILO_ASSEGNATO, ID_RICHIESTA_VISIBILITA, DATA_INIZIO_VAL, DATA_FINE_VAL, TIPO_ABILITAZIONE, ORIGINE_ABILITAZIONE) " +
					"VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, puiv.getCfUtente());
			query.setParameter(2, puiv.getCodiceApplicazione());
			query.setParameter(3, puiv.getCodiceProfilo());
			query.setParameter(4, puiv.getCodiceCdr());
			query.setParameter(5, puiv.getCodiceUfficio());
			query.setParameter(6, puiv.getCodiceRoleGroup());
			query.setParameter(7, puiv.getProfiloAssegnato());
			query.setParameter(8, puiv.getIdRichiestaVisibilita() != null ? Long.parseLong(puiv.getIdRichiestaVisibilita()) : null);
			query.setParameter(9, new java.util.Date()); // SYSDATE
			query.setParameter(10, puiv.getDataFineVal());
			query.setParameter(11, puiv.getTipoAbilitazione());
			query.setParameter(12, puiv.getOrigineAbilitazione());
			query.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Error in inserisciProfiloUtenteInVisibilita: " + e.getMessage(), e);
		}
	}

	public void deleteProfiloUtenteInVisibilita(ProfiloUtenteInVisibilitaBean puiv) throws SQLException {
		try {
			String sql = "DELETE FROM PROFILI_UTENTE_IN_VISIBILITA WHERE CF_UTENTE = ?1 AND CODICE_ROLE_GROUP = ?2 AND CODICE_PROFILO = ?3 AND ID_RICHIESTA_VISIBILITA = ?4";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, puiv.getCfUtente());
			query.setParameter(2, puiv.getCodiceRoleGroup());
			query.setParameter(3, puiv.getCodiceProfilo());
			query.setParameter(4, puiv.getIdRichiestaVisibilita() != null ? Long.parseLong(puiv.getIdRichiestaVisibilita()) : null);
			query.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Error in deleteProfiloUtenteInVisibilita: " + e.getMessage(), e);
		}
	}

	public void updateControlloRichiestaCaricamentoMassivoVisib(CaricamentoMassivoEntity CMEntity) throws SQLException {
		try {
			String sql = "UPDATE\r\n"
					+ "		    CM_AGG_VISIBILITA_RICHIESTA\r\n"
					+ "		SET\r\n"
					+ "			DATA_ORA_ELABORAZIONE = CURRENT_TIMESTAMP, \r\n"
					+ "			STATO_RICHIESTA = ?1,\r\n"
					+ "			NUMERO_RECORD_FILE = ?2,\r\n"
					+ "			NUMERO_RECORD_SCARTATI = ?3,\r\n"
					+ "			NUMERO_RECORD_VALIDI = ?4,\r\n"
					+ "			NUMERO_RECORD_ECA_AGGIUNTI = ?5\r\n"
					+ "		WHERE\r\n"
					+ "			ID_CARICAMENTO = ?6";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, CMEntity.getStatoRichiesta());
			query.setParameter(2, CMEntity.getNumRecordFile());
			query.setParameter(3, CMEntity.getNumRecordScartati());
			query.setParameter(4, CMEntity.getNumRecordValidi());
			query.setParameter(5, CMEntity.getNumRecordEcaAggiunti());
			query.setParameter(6, CMEntity.getIdCaricamento());
			query.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Error in updateControlloRichiestaCaricamentoMassivoVisib: " + e.getMessage(), e);
		}
	}

	

	public List<FileAbilitazioneBean> getListaRecordScartatiAggVis(String idCaricamento) throws SQLException {
		List<FileAbilitazioneBean> lista = new ArrayList<FileAbilitazioneBean>();
		try {
			String sql = "SELECT \r\n"
					+ "			MOTIVAZIONE_SCARTO,\r\n"
					+ "			CODICE_FISCALE,\r\n"
					+ "  			CODICE_ROLE_GROUP,\r\n"
					+ "  			CODICE_PROFILO,\r\n"
					+ "  			CODICE_CDR,\r\n"
					+ "  			AZIONE\r\n"
					+ "		FROM\r\n"
					+ "		  	CM_AGG_VISIBILITA_FILE\r\n"
					+ "		WHERE\r\n"
					+ "		  	ID_CARICAMENTO = ?1 \r\n"
					+ "		  	AND STATO = 'Scartato'";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, idCaricamento);
			List<Object[]> results = query.getResultList();
			
			for (Object[] row : results) {
				FileAbilitazioneBean bean = new FileAbilitazioneBean();

				bean.setMotivazioneScarto(row[0] != null ? row[0].toString() : "");
				bean.setCodiceFiscale(row[1] != null ? row[1].toString() : "");
				bean.setCodiceRoleGroup(row[2] != null ? row[2].toString() : "");
				bean.setCodiceProfilo(row[3] != null ? row[3].toString() : "");
				bean.setCodiceCdr(row[4] != null ? row[4].toString() : "");
				bean.setAzione(row[5] != null ? row[5].toString() : "");
				
				lista.add(bean);
			}
			return lista;
		} catch (Exception e) {
			throw new SQLException("Error in getListaRecordScartatiAggVis: " + e.getMessage(), e);
		}
	}
	
	
	public List<String> getIdRichiesteAbilitazione(ProfiloUtenteInVisibilitaFinder puvFinder) throws SQLException {
		List<String> lista = new ArrayList<String>();
		try {
			StringBuilder sql = new StringBuilder("SELECT ID_RICHIESTA FROM PROFILI_RICHIESTA WHERE ID_RICHIESTA_VISIBILITA = ?1 AND CODICE_ROLE_GROUP = ?2 AND OPERAZIONE_ESEGUITA = ?3");
			List<Object> params = new ArrayList<>();
			params.add(puvFinder.getIdRichiestaVisibilita());
			params.add(puvFinder.getCodiceRoleGroup());
			params.add(puvFinder.getOperazioneEseguita());
			
			if (puvFinder.getCodiceProfilo() != null && !puvFinder.getCodiceProfilo().isEmpty()) {
				sql.append(" AND CODICE_PROFILO = ?4");
				params.add(puvFinder.getCodiceProfilo());
			}
			
			Query query = entityManager.createNativeQuery(sql.toString());
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i + 1, params.get(i));
			}
			
			List<Object> results = query.getResultList();
			for (Object result : results) {
				if (result != null) {
					lista.add(result.toString());
				}
			}
			return lista;
		} catch (Exception e) {
			throw new SQLException("Error in getIdRichiesteAbilitazione: " + e.getMessage(), e);
		}
	}

	public Integer countProfiliFigliAssegnatiAlCfPerRichVisib(ProfiloUtenteInVisibilitaFinder puvFinder) throws SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM PROFILI_ATTIVI_UTENTE WHERE CF_UTENTE = ?1 AND ID_RICHIESTA_VISIBILITA = ?2 AND CODICE_ROLE_GROUP = ?3";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, puvFinder.getCodiceFiscale());
			query.setParameter(2, puvFinder.getIdRichiestaVisibilita());
			query.setParameter(3, puvFinder.getCodiceRoleGroup());
			Object result = query.getSingleResult();
			return result != null ? ((Number) result).intValue() : 0;
		} catch (NoResultException e) {
			return 0;
		} catch (Exception e) {
			throw new SQLException("Error in countProfiliFigliAssegnatiAlCfPerRichVisib: " + e.getMessage(), e);
		}
	}
	
	public Integer countVisibilitaPerProfili(ProfiloUtenteInVisibilitaFinder puvFinder) throws SQLException {
		try {
			StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM PROFILI_UTENTE_IN_VISIBILITA WHERE CF_UTENTE = ?1 AND ID_RICHIESTA_VISIBILITA = ?2");
			List<Object> params = new ArrayList<>();
			params.add(puvFinder.getCodiceFiscale());
			params.add(puvFinder.getIdRichiestaVisibilita());
			int paramIndex = 3;
			
			if (puvFinder.getCodiceRoleGroup() != null && !puvFinder.getCodiceRoleGroup().isEmpty()) {
				sql.append(" AND CODICE_ROLE_GROUP <> ?").append(paramIndex);
				params.add(puvFinder.getCodiceRoleGroup());
				paramIndex++;
			}
			
			if (puvFinder.getCodiceProfilo() != null && !puvFinder.getCodiceProfilo().isEmpty()) {
				sql.append(" AND CODICE_PROFILO <> ?").append(paramIndex);
				params.add(puvFinder.getCodiceProfilo());
			}
			
			Query query = entityManager.createNativeQuery(sql.toString());
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i + 1, params.get(i));
			}
			
			Object result = query.getSingleResult();
			return result != null ? ((Number) result).intValue() : 0;
		} catch (NoResultException e) {
			return 0;
		} catch (Exception e) {
			throw new SQLException("Error in countVisibilitaPerProfili: " + e.getMessage(), e);
		}
	}
	
	public String getDescrizioneCdrByCodice(String codCdr) throws SQLException {
		try {
			String sql = "SELECT DESCRIZIONE FROM CDR WHERE CODICE_CDR = ?1 AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, codCdr);
			Object result = query.getSingleResult();
			return result != null ? result.toString() : null;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new SQLException("Error in getDescrizioneCdrByCodice: " + e.getMessage(), e);
		}
	}
	
	
	public String getEmailAmminCaricamentoAggVis(String idCaricamento) throws SQLException {
		try {
			String sql = "SELECT \r\n"
					+ "  			UT.E_MAIL\r\n"
					+ "		FROM \r\n"
					+ "  			UTENTI UT, \r\n"
					+ "  			CM_AGG_VISIBILITA_RICHIESTA CMR\r\n"
					+ "		WHERE\r\n"
					+ "  			CMR.ID_CARICAMENTO = ?1 \r\n"
					+ "  			AND CMR.CF_AMMINISTRATORE = UT.CODICE_FISCALE";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, idCaricamento);
			Object result = query.getSingleResult();
			return result != null ? result.toString() : null;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new SQLException("Error in getEmailAmminCaricamentoAggVis: " + e.getMessage(), e);
		}
	}
	
	
	
	
	
	
	
	
	
	public Integer isAmministratoreCentrale3(HashMap<String, String> queryMap) throws SQLException {
		try {
			String cf = queryMap.get("cfAmministratore");
			String app = queryMap.get("codApplicazione");
			String profilo = queryMap.get("codProfilo");
			
			String sql = "SELECT \r\n"
					+ "			COUNT(*)\r\n"
					+ "		FROM \r\n"
					+ "			AMMINISTRATORI_CENTRALI\r\n"
					+ "		WHERE \r\n"
					+ "			CODICE_FISCALE = ?1\r\n"
					+ "			AND (\r\n"
					+ "				(CODICE_APPLICAZIONE = ?2  AND CODICE_PROFILO IS NULL)\r\n"
					+ "				OR (CODICE_APPLICAZIONE = ?2 AND CODICE_PROFILO = ?3 )\r\n"
					+ "			)\r\n"
					+ "		    AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);
			query.setParameter(2, app);
			query.setParameter(3, profilo);
			Object result = query.getSingleResult();
			return ((Number) result).intValue();
		} catch (Exception e) {
			throw new SQLException("Error in countAssocProfRoleGroup: " + e.getMessage(), e);
		}
	}
	
	
	public Integer getProfiloVisibilitaStruttura(String cdr) throws SQLException {
		try {
			String sql = "SELECT COUNT(rcus.CODICE_CDR)\r\n"
					+ "	FROM\r\n"
					+ "	PROFILO_STRUTTURA_VISIBILITA psv \r\n"
					+ "	INNER JOIN RELAZIONE_CDR_UFFICIO_STRUT rcus \r\n"
					+ "		ON psv.CODICE_STRUTTURA = rcus.CODICE_STRUTTURA\r\n"
					+ "	WHERE rcus.CODICE_CDR = ?1"
					+ "	AND (rcus.data_FIne_VAL IS NULL OR rcus.data_FIne_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cdr);
			Object result = query.getSingleResult();
			return result != null ? (Integer) result : null;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new SQLException("Error in getProfioAbilitaStruttura: " + e.getMessage(), e);
		}
	}
	
	

}
