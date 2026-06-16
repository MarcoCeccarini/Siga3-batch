package it.finanze.siga.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.finanze.scheduler.bean.ProfiliAttiviUtente;
import it.finanze.scheduler.bean.ProfiloCM;
import it.finanze.scheduler.bean.ProfiloRichiestaBean;
import it.finanze.scheduler.bean.RichiedenteCdR;
import it.finanze.scheduler.bean.RichiestaAbilitazioneDisabilitazioneBean;
import it.finanze.scheduler.bean.RichiestaBatchBean;
import it.finanze.scheduler.bean.Ufficio;
import it.finanze.scheduler.bean.UtenteInVisibilitaCM;
import it.finanze.siga.bean.AllegatoCaricamentoMassivoEntity;
import it.finanze.siga.bean.CaricamentoMassivoEntity;
import it.finanze.siga.bean.FileAbilitazioneBean;
import it.finanze.siga.finder.DocumentoFinder;
import it.finanze.siga.finder.InterrogazioneCaricamentiFinder;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
@Named("BatchRichiesteCaricamentiMassiviNewDaoImpl")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BatchRichiesteCaricamentiMassiviNewDaoImpl extends SigaDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        super.setEntityManager(entityManager);
    }

	
	public List<CaricamentoMassivoEntity> getListaCaricamentiPerControlli(InterrogazioneCaricamentiFinder finder) throws SQLException {
		try {
			StringBuilder sql = new StringBuilder("SELECT "
					+ "			CMR.ID_CARICAMENTO,"
					+ "			TO_CHAR(CMR.DATA_ORA_CARICAMENTO, 'DD/MM/YYYY')AS DATA_CARICAMENTO, "
					+ "			CMR.DESCRIZIONE_RICHIESTA, "
					+ "			CMR.STATO_RICHIESTA,"
					+ "			UT.CODICE_FISCALE AS CF_RICHIEDENTE, "
					+ "			UT.NOME AS NOME_RICHIEDENTE, "
					+ "			UT.COGNOME AS COGNOME_RICHIEDENTE,"
					+ "			UT2.CODICE_FISCALE AS CF_AUTOR,"
					+ "			UT2.NOME AS NOME_AUTOR, "
					+ "			UT2.COGNOME AS COGNOME_AUTOR,"
					+ "			CMR.CODICE_AMBITO,"
					+ "			AA.DESCRIZIONE AS DESCR_AMBITO,"
					+ "      		CMS.DESCRIZIONE AS DESCR_STATO,"
					+ "      		CMR.FILE_ABILITAZIONI "
					+ "		FROM "
					+ "		  	CM_RICHIESTA CMR, "
					+ "  			UTENTI UT, "
					+ "  			UTENTI UT2,"
					+ "  			AMBITO_APPLICATIVO AA,"
					+ "        	CM_STATI CMS"
					+ "		WHERE"
					+ "        	DATA_ORA_CANCELLAZIONE IS NULL"
					+ "        	AND DATA_ORA_CONTROLLI_FORMALI IS NULL");
					if (finder.getIdCaricamento() != null && !finder.getIdCaricamento().isEmpty()) 
					sql.append("	     AND ID_CARICAMENTO = ?1");
					sql.append("	        AND CMR.CF_RICHIEDENTE = UT.CODICE_FISCALE"
					+ "		  	AND CMR.CF_AUTORIZZATORE_I_LIV = UT2.CODICE_FISCALE"
					+ "		  	AND CMR.STATO_RICHIESTA = CMS.STATO"
					+ "		  	AND CMR.CODICE_AMBITO = AA.CODICE_AMBITO"
					+ "		  	AND (UT.DATA_FINE_VAL IS NULL OR UT.DATA_FINE_VAL > SYSDATE)"
					+ "		ORDER BY CMR.ID_CARICAMENTO ASC");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			
			query.setParameter(1, finder.getIdCaricamento());
			
			List<Object[]> results = query.getResultList();
			List<CaricamentoMassivoEntity> list = new ArrayList<>();
			for (Object[] row : results) {
				CaricamentoMassivoEntity entity = new CaricamentoMassivoEntity();
				if (row[0] != null) entity.setIdCaricamento(row[0].toString());
				if (row[1] != null) entity.setDataCaricamento(row[1].toString());
				if (row[2] != null) entity.setDescrizioneRichiestaCaricamento(row[2].toString());
				if (row[3] != null) entity.setStatoRichiestaCaricamento(row[3].toString());
				if (row[4] != null) entity.setCfRichiedente(row[4].toString());
				if (row[5] != null) entity.setNomeRichiedente(row[5].toString());
				if (row[6] != null) entity.setCognomeRichiedente(row[6].toString());
				if (row[7] != null) entity.setCfAutorizzatore(row[7].toString());
				if (row[8] != null) entity.setNomeAutorizzatore(row[8].toString());
				if (row[9] != null) entity.setCognomeAutorizzatore(row[9].toString());
				if (row[10] != null) entity.setIdAmbito(row[10].toString());
				if (row[11] != null) entity.setDescrizioneAmbito(row[11].toString());
				if (row[12] != null) entity.setStatoRichiestaCaricamento(row[12].toString());
				if (row[13] != null) entity.setbFileAbilitazioni((byte[])row[13]);
				list.add(entity);
			}
			return list;
		} catch (Exception e) {
			throw new SQLException("Error fetching getListaCaricamentiPerControlli: " + e.getMessage(), e);
		}
	}
			
	public List<CaricamentoMassivoEntity> getListaCaricamentiControllati(InterrogazioneCaricamentiFinder finder) throws SQLException {
		try {
			StringBuilder sql = new StringBuilder("SELECT DISTINCT"
					+ "			ID_CARICAMENTO,"
					+ "			SEQUENCE_RICHIESTE_GENERATE,"
					+ "			CODICE_AMBITO,"
					+ "			NOTE_RICHIEDENTE_OPERATORE, "
					+ "			CF_RICHIEDENTE, "
					+ "			CF_AUTORIZZATORE_I_LIV,"
					+ "			DESCRIZIONE_RICHIESTA  "
					+ "		FROM"
					+ "		  CM_RICHIESTA"
					+ "		WHERE"
					+ "			STATO_RICHIESTA = 'CON'"
					+ "			AND NUMERO_RECORD_VALIDI > 0");
					if (finder.getIdCaricamento() != null && !finder.getIdCaricamento().isEmpty()) 
						sql.append(" AND ID_CARICAMENTO = ?1");
						sql.append(" ORDER BY SEQUENCE_RICHIESTE_GENERATE");
			
			Query query = entityManager.createNativeQuery(sql.toString());
				query.setParameter(1, finder.getIdCaricamento());
			
			List<Object[]> results = query.getResultList();
			List<CaricamentoMassivoEntity> list = new ArrayList<>();
			for (Object[] row : results) {
				CaricamentoMassivoEntity entity = new CaricamentoMassivoEntity();
				if (row[0] != null) entity.setIdCaricamento(row[0].toString());
				if (row[1] != null) entity.setSequenceRichiesteGenerate(((Number) row[1]).longValue());
				if (row[2] != null) entity.setIdAmbito(row[2].toString());
				if (row[3] != null) entity.setNoteRichiedente(row[3].toString());
				if (row[4] != null) entity.setCfRichiedente(row[4].toString());
				if (row[5] != null) entity.setCfAutorizzatore(row[5].toString());
				if (row[6] != null) entity.setDescrStatoRichiestaCaricamento(row[6].toString());
				
				list.add(entity);
			}
			return list;
		} catch (Exception e) {
			throw new SQLException("Error fetching getListaCaricamentiControllati: " + e.getMessage(), e);
		}
	}
@TransactionAttribute(TransactionAttributeType.REQUIRED)	
	public void updateControlloRichiestaCaricamentoMassivo(CaricamentoMassivoEntity cmEntity) throws SQLException {
		try {
			String sql = "UPDATE"
					+ "		    CM_RICHIESTA"
					+ "		SET"
					+ "			DATA_ORA_CONTROLLI_FORMALI = CURRENT_TIMESTAMP, "
					+ "			STATO_RICHIESTA = ?1,"
					+ "			NUMERO_RECORD_FILE = ?2,"
					+ "			NUMERO_RECORD_SCARTATI = ?3,"
					+ "			NUMERO_RECORD_VALIDI = ?4,"
					+ "			NUMERO_RECORD_ECA_AGGIUNTI = ?5,"
					+ "			SEQUENCE_RICHIESTE_GENERATE = ?6"
					+ "		WHERE"
					+ "			ID_CARICAMENTO = ?7";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cmEntity.getStatoRichiesta());
			query.setParameter(2, cmEntity.getNumRecordFile());
			query.setParameter(3, cmEntity.getNumRecordScartati());
			query.setParameter(4, cmEntity.getNumRecordValidi());
			query.setParameter(5, cmEntity.getNumRecordEcaAggiunti());
			query.setParameter(6, cmEntity.getSequenceRichiesteGenerate());
			query.setParameter(7, cmEntity.getIdCaricamento());
			query.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Error updating updateControlloRichiestaCaricamentoMassivo: " + e.getMessage(), e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateGenerazioneRichiestaCaricamentoMassivo(CaricamentoMassivoEntity cmEntity) throws SQLException {
		try {
			String sql = "UPDATE CM_RICHIESTA SET "
					+ " DATA_ORA_GENERAZIONE_RICHIESTE = CURRENT_TIMESTAMP,"
					+ "STATO_RICHIESTA = ?1 WHERE ID_CARICAMENTO = ?2";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cmEntity.getStatoRichiesta());
			query.setParameter(2, cmEntity.getIdCaricamento());
			query.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Error updating updateGenerazioneRichiestaCaricamentoMassivo: " + e.getMessage(), e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserisciRichiesteCaricamentoMassivo(List<RichiestaAbilitazioneDisabilitazioneBean> listaRichiesteCaricamento, List<FileAbilitazioneBean> listaFileAbilitazione) throws SQLException {
		try {
			if (listaRichiesteCaricamento != null) {
				for (RichiestaAbilitazioneDisabilitazioneBean radb : listaRichiesteCaricamento) {
					inserisciScadenzeRichiestaBatch(radb);
				}
			}
			if (listaFileAbilitazione != null) {
				for (FileAbilitazioneBean fab : listaFileAbilitazione) {
					inserisciFileAbilitazione(fab);
				}
			}
		} catch (Exception e) {
			throw new SQLException("Error in inserisciRichiesteCaricamentoMassivo: " + e.getMessage(), e);
		}
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)	
	public void inserisciScadenzeRichiestaBatch(RichiestaAbilitazioneDisabilitazioneBean richiestaBean) throws SQLException {
		try {
			StringBuilder sql = new StringBuilder("INSERT INTO RICHIESTE_BATCH (CF, AZIONE, SEQUENCE");
			List<Object> params = new ArrayList<>();
			params.add(richiestaBean.getCodiceFiscale());
			params.add(richiestaBean.getAzione());
			params.add(richiestaBean.getSequence());
			
			int paramIndex = 4;
			
			if (richiestaBean.getIdRichiesta() != 0L) {
				sql.append(", ID_RICHIESTA_GENERATA");
			}
			if (richiestaBean.getEvento() != null && !richiestaBean.getEvento().isEmpty()) {
				sql.append(", EVENTO");
			}
			if (richiestaBean.getDataEvento() != null) {
				sql.append(", DATA_EVENTO");
			}
			if (richiestaBean.getDataCreazione() != null) {
				sql.append(", DATA_CREAZIONE");
			}
			if (richiestaBean.getCdrVisibilita() != null && !richiestaBean.getCdrVisibilita().isEmpty()) {
				sql.append(", CODICE_CDR_VISIBILITA");
			}
			if (richiestaBean.getRoleGroup() != null && !richiestaBean.getRoleGroup().isEmpty()) {
				sql.append(", CODICE_ROLE_GROUP");
			}
			if (richiestaBean.getCodiceProfilo() != null && !richiestaBean.getCodiceProfilo().isEmpty()) {
				sql.append(", CODICE_PROFILO");
			}
			if (richiestaBean.getCodiceUfficio() != null && !richiestaBean.getCodiceUfficio().isEmpty()) {
				sql.append(", CODICE_UFFICIO");
			}
			if (richiestaBean.getCodiceCdr() != null && !richiestaBean.getCodiceCdr().isEmpty()) {
				sql.append(", CODICE_CDR");
			}
			if (richiestaBean.getIdRichiestaVisibilita() != 0) {
				sql.append(", ID_RICHIESTA_VISIBILITA");
			}
			if (richiestaBean.getTipoUtente() != null && !richiestaBean.getTipoUtente().isEmpty()) {
				sql.append(", TIPO_UTENTE");
			}
			if (richiestaBean.getIdRichiestaPau() != null && richiestaBean.getIdRichiestaPau() != 0L) {
				sql.append(", ID_RICHIESTA_PAU");
			}
			if (richiestaBean.getFlagCancellazioneUtenteEsterno() != null && !richiestaBean.getFlagCancellazioneUtenteEsterno().isEmpty()) {
				sql.append(", CANCELLAZIONE_UTENTE_ESTERNO");
			}
			
			sql.append(") VALUES (?1, ?2, ?3");
			
			if (richiestaBean.getIdRichiesta() != 0L) {
				sql.append(", ?").append(paramIndex++);
				params.add(richiestaBean.getIdRichiesta());
			}
			if (richiestaBean.getEvento() != null && !richiestaBean.getEvento().isEmpty()) {
				sql.append(", ?").append(paramIndex++);
				params.add(richiestaBean.getEvento());
			}
			if (richiestaBean.getDataEvento() != null) {
				sql.append(", ?").append(paramIndex++);
				params.add(new Timestamp(richiestaBean.getDataEvento().getTime()));
			}
			if (richiestaBean.getDataCreazione() != null) {
				sql.append(", ?").append(paramIndex++);
				params.add(new Timestamp(richiestaBean.getDataCreazione().getTime()));
			}
			if (richiestaBean.getCdrVisibilita() != null && !richiestaBean.getCdrVisibilita().isEmpty()) {
				sql.append(", ?").append(paramIndex++);
				params.add(richiestaBean.getCdrVisibilita());
			}
			if (richiestaBean.getRoleGroup() != null && !richiestaBean.getRoleGroup().isEmpty()) {
				sql.append(", ?").append(paramIndex++);
				params.add(richiestaBean.getRoleGroup());
			}
			if (richiestaBean.getCodiceProfilo() != null && !richiestaBean.getCodiceProfilo().isEmpty()) {
				sql.append(", ?").append(paramIndex++);
				params.add(richiestaBean.getCodiceProfilo());
			}
			if (richiestaBean.getCodiceUfficio() != null && !richiestaBean.getCodiceUfficio().isEmpty()) {
				sql.append(", ?").append(paramIndex++);
				params.add(richiestaBean.getCodiceUfficio());
			}
			if (richiestaBean.getCodiceCdr() != null && !richiestaBean.getCodiceCdr().isEmpty()) {
				sql.append(", ?").append(paramIndex++);
				params.add(richiestaBean.getCodiceCdr());
			}
			if (richiestaBean.getIdRichiestaVisibilita() != 0) {
				sql.append(", ?").append(paramIndex++);
				params.add(richiestaBean.getIdRichiestaVisibilita());
			}
			if (richiestaBean.getTipoUtente() != null && !richiestaBean.getTipoUtente().isEmpty()) {
				sql.append(", ?").append(paramIndex++);
				params.add(richiestaBean.getTipoUtente());
			}
			if (richiestaBean.getIdRichiestaPau() != null && richiestaBean.getIdRichiestaPau() != 0L) {
				sql.append(", ?").append(paramIndex++);
				params.add(richiestaBean.getIdRichiestaPau());
			}
			if (richiestaBean.getFlagCancellazioneUtenteEsterno() != null && !richiestaBean.getFlagCancellazioneUtenteEsterno().isEmpty()) {
				sql.append(", ?").append(paramIndex++);
				params.add(richiestaBean.getFlagCancellazioneUtenteEsterno());
			}
			
			sql.append(")");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i + 1, params.get(i));
			}
			
			query.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Error in inserisciScadenzeRichiestaBatch: " + e.getMessage(), e);
		}
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)	
	public void inserisciFileAbilitazione(FileAbilitazioneBean fab) throws SQLException {
		try {
			StringBuilder sql = new StringBuilder("INSERT INTO CM_FILE_ABILITAZIONI (ID_CARICAMENTO, STATO");
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
			throw new SQLException("Error in inserisciFileAbilitazione: " + e.getMessage(), e);
		}
	}
	

	

	public Ufficio getDatiUfficioCM(String codiceFiscale) throws SQLException {
		Ufficio ufficio = new Ufficio();
		try {
			String sql = "		SELECT"
					+ "			RCUS.CODICE_UFFICIO, "
					+ "  			UT.CODICE_CDR "
					+ "		FROM"
					+ "  			UTENTI UT, "
					+ "  			RELAZIONE_CDR_UFFICIO_STRUT RCUS "
					+ "		WHERE"
					+ "  			UT.CODICE_FISCALE = ?1 "
					+ "  			AND UT.CODICE_CDR = RCUS.CODICE_CDR"
					+ "  			AND UT.DATA_FINE_VAL IS NULL"
					+ "  			AND RCUS.DATA_FINE_VAL IS NULL";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, codiceFiscale);
			Object result = query.getSingleResult();
			if (result instanceof Object[]) {
				Object[] row = (Object[]) result;
				if (row[0] != null) ufficio.setCodiceUfficio(row[0].toString());
				if (row[1] != null) ufficio.setCodiceCdR(row[1].toString());
			}
			return ufficio;
		} catch (Exception e) {
			throw new SQLException("Error fetching getDatiUfficioCM: " + e.getMessage(), e);
		}
	}

	public UtenteInVisibilitaCM getUtenteVisibilitaCM(HashMap<String, String> queryMap) throws SQLException {
		UtenteInVisibilitaCM utente = null;
		try {
			String sql = "SELECT"
					+ "  			UV.ID_RICHIESTA_VISIBILITA,"
					+ "  			RCUS.CODICE_UFFICIO"
					+ "		FROM "
					+ "  			UTENTE_IN_VISIBILITA UV, "
					+ "  			RELAZIONE_CDR_UFFICIO_STRUT RCUS"
					+ "		WHERE"
					+ " 	 		UV.CF_UTENTE = ?1"
					+ "  			AND UV.CODICE_CDR = ?2 "
					+ "  			AND RCUS.CODICE_CDR = UV.CODICE_CDR"
					+ "  			AND UV.DATA_INIZIO_VAL IS NOT NULL"
					+ "  			AND UV.DATA_REVOCA IS NULL"
					+ "  			AND (UV.DATA_FINE_VAL IS NULL OR UV.DATA_FINE_VAL > SYSDATE)"
					+ "  			AND (RCUS.DATA_FINE_VAL IS NULL OR UV.DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, queryMap.get("codFiscale"));
			query.setParameter(2, queryMap.get("codCdr"));
			Object result = query.getSingleResult();
			if (result instanceof Object[]) {
				Object[] row = (Object[]) result;
				utente = new UtenteInVisibilitaCM();
				if (row[0] != null) utente.setIdRichiestaVisibilita(row[0].toString());
				if (row[1] != null) utente.setCodiceUfficio(row[1].toString());
			}
			return utente;
		} catch (Exception e) {
			throw new SQLException("Error fetching getUtenteVisibilitaCM: " + e.getMessage(), e);
		}
	}

	public ProfiliAttiviUtente getProfiloAttivoUtenteCM(HashMap<String, String> queryMap) throws SQLException {
		ProfiliAttiviUtente profilo = null;
		try {
			StringBuilder sql = new StringBuilder("SELECT DISTINCT"
					+ "		  CODICE_UFFICIO,"
					+ "		  CODICE_CDR"
					+ "		FROM"
					+ "		  PROFILI_ATTIVI_UTENTE"
					+ "		WHERE"
					+ "		  CF_UTENTE = ?1"
					+ "		  AND CODICE_PROFILO = ?2"
					+ "		  AND CODICE_ROLE_GROUP = ?3"
					+ " AND (ID_RICHIESTA_VISIBILITA IS NULL OR ID_RICHIESTA_VISIBILITA = '0')"
					+ "  AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter(1, queryMap.get("codFiscale"));
			query.setParameter(2, queryMap.get("codProfilo"));
			query.setParameter(3, queryMap.get("codRoleGroup"));
			
			Object result = query.getSingleResult();
			if (result instanceof Object[]) {
				Object[] row = (Object[]) result;
				profilo = new ProfiliAttiviUtente();
				if (row[0] != null) profilo.setCodiceCdR(row[0].toString());
				if (row[1] != null) profilo.setCodiceUfficio(row[1].toString());
			}
			return profilo;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new SQLException("Error fetching getProfiloAttivoUtenteCM: " + e.getMessage(), e);
		}
	}

	public String getIdRegistroRichiestaCM(HashMap<String, String> queryMap) throws SQLException {
		try {
			String sql = "SELECT"
					+ "		  RR.ID_RICHIESTA"
					+ "		FROM"
					+ "		  PROFILI_RICHIESTA PR,"
					+ "		  REGISTRO_RICHIESTE RR"
					+ "		WHERE"
					+ "		  CF_UTENTE = ?1 "
					+ "		  AND RR.ID_RICHIESTA = PR.ID_RICHIESTA"
					+ "		  AND CODICE_PROFILO = ?2 ";
				if( queryMap.get("idRichiestaVisibilita") != null && !queryMap.get("idRichiestaVisibilita").isEmpty())	
							sql+= "	 AND ID_RICHIESTA_VISIBILITA = ?3 ";
				if( queryMap.get("idRichiestaVisibilita") == null || ( queryMap.get("idRichiestaVisibilita") != null && queryMap.get("idRichiestaVisibilita").isEmpty()))
					sql += "		 AND  	(ID_RICHIESTA_VISIBILITA IS NULL OR ID_RICHIESTA_VISIBILITA = 0)";
					sql+= "		  AND RR.DATA_ESITO_FINALE IS NULL"
					+ "		  AND RR.DATA_CHIUSURA_RICHIESTA IS NULL";
			
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, queryMap.get("codFiscale"));
			query.setParameter(2, queryMap.get("codProfilo"));
			query.setParameter(3, queryMap.get("idRichiestaVisibilita"));
			Object result = query.getSingleResult();
			return result != null ? result.toString() : null;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new SQLException("Error fetching getIdRegistroRichiestaCM: " + e.getMessage(), e);
		}
	}

	public List<String> getCodProfiloDaProfiliAttiviUtenteEca(HashMap<String, String> queryMap) throws SQLException {
		List<String> profili = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder("SELECT CODICE_PROFILO "
					+ " FROM PROFILI_ATTIVI_UTENTE "
					+ "WHERE CF_UTENTE = ?1 AND UPPER(CODICE_PROFILO) LIKE '%'|| UPPER(?2) ||'%'");
			if( queryMap.get("idRichiestaVisibilita") != null && !queryMap.get("idRichiestaVisibilita").isEmpty())	
				sql.append( "	 AND ID_RICHIESTA_VISIBILITA = ?3 ");
	if( queryMap.get("idRichiestaVisibilita") == null || ( queryMap.get("idRichiestaVisibilita") != null && queryMap.get("idRichiestaVisibilita").isEmpty()))
		sql.append( "		 AND  	(ID_RICHIESTA_VISIBILITA IS NULL OR ID_RICHIESTA_VISIBILITA = 0)");
			
			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter(1, queryMap.get("codFiscale"));
			query.setParameter(2, queryMap.get("codProfilo") );
			
			if( queryMap.get("idRichiestaVisibilita") != null && !queryMap.get("idRichiestaVisibilita").isEmpty())	{
				sql.append(" AND ID_RICHIESTA_VISIBILITA = ?3");
				query.setParameter(3, Long.parseLong(queryMap.get("idRichiestaVisibilita")));
			}
			
			List<Object> results = query.getResultList();
			for (Object result : results) {
				if (result != null) profili.add(result.toString());
			}
			return profili;
		} catch (Exception e) {
			throw new SQLException("Error fetching getCodProfiloDaProfiliAttiviUtenteEca: " + e.getMessage(), e);
		}
	}

	public String getProfiloEcaPadreInPau(HashMap<String, String> queryMap) throws SQLException {
		try {
			String sql = "SELECT CODICE_PROFILO FROM PROFILI_ATTIVI_UTENTE WHERE CF_UTENTE = ?1 AND CODICE_PROFILO = ?2";
			
			if (queryMap.get("idRichiestaVisibilita") != null) {
				sql += " AND ID_RICHIESTA_VISIBILITA = ?3";
			}	
			
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, queryMap.get("codFiscale"));
			query.setParameter(2, queryMap.get("codProfiloPadre"));
			
			if (queryMap.get("idRichiestaVisibilita") != null) {
				query.setParameter(3, Long.parseLong(queryMap.get("idRichiestaVisibilita")));
			}	
			
			
			Object result = query.getSingleResult();
			return result != null ? result.toString() : null;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new SQLException("Error fetching getProfiloEcaPadreInPau: " + e.getMessage(), e);
		}
	}

	public List<ProfiloCM> getProfiliCM(String codiceProfilo) throws SQLException {
		List<ProfiloCM> profili = new ArrayList<>();
		try {
			String sql = "SELECT"
					+ "		  PR.CODICE_PROFILO,"
					+ "		  PR.CODICE_APPLICAZIONE, "
					+ "		  PR.EXPLICIT_ENTITLEMENT, "
					+ "		  APP.CODICE_AMBITO_APPLICATIVO"
					+ "		FROM"
					+ "		  PROFILO PR, "
					+ "		  APPLICAZIONI APP"
					+ "		WHERE"
					+ "		  PR.CODICE_PROFILO = ?1"
					+ "		  AND PR.CODICE_APPLICAZIONE = APP.CODICE_APPLICAZIONE";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, codiceProfilo);
			List<Object[]> results = query.getResultList();
			for (Object[] row : results) {
				ProfiloCM profilo = new ProfiloCM();
				if (row[0] != null) profilo.setCodiceProfilo(row[0].toString());
				if (row[1] != null) profilo.setCodiceApplicazione(row[1].toString());
				if (row[2] != null) profilo.setExplicitEntitlement(row[2].toString());
				if (row[3] != null) profilo.setCodiceAmbito(row[3].toString());
				profili.add(profilo);
			}
			return profili;
		} catch (Exception e) {
			throw new SQLException("Error fetching getProfiliCM: " + e.getMessage(), e);
		}
	}

	public String getDescrizioneAmbito(String idAmbito) throws SQLException {
		try {
			String sql = "SELECT DESCRIZIONE FROM AMBITO_APPLICATIVO WHERE CODICE_AMBITO = ?1";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, idAmbito);
			Object result = query.getSingleResult();
			return result != null ? result.toString() : "";
		} catch (NoResultException e) {
			return "";
		} catch (Exception e) {
			throw new SQLException("Error fetching getDescrizioneAmbito: " + e.getMessage(), e);
		}
	}

	public List<String> getCodiciAppCM(HashMap<String, String> queryMap) throws SQLException {
		List<String> codici = new ArrayList<>();
		try {
			String sql = "SELECT DISTINCT "
					+ "			RAPRG.CODICE_APPLICAZIONE "
					+ "		FROM "
					+ "			RELAZIONE_APPL_PROF_ROLE_GROUP RAPRG,"
					+ "			APPLICAZIONI APP "
					+ "		WHERE"
					+ "  			RAPRG.CODICE_PROFILO = ?1"
					+ "  			AND RAPRG.CODICE_ROLE_GROUP = ?2 ";
					if("ABILITAZIONE".equals(queryMap.get("azione")))
					sql+="  				AND (RAPRG.DATA_FINE_VAL IS NULL OR RAPRG.DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, queryMap.get("codProfilo"));
			query.setParameter(2, queryMap.get("codRoleGroup"));
			List<Object> results = query.getResultList();
			for (Object result : results) {
				if (result != null) codici.add(result.toString());
			}
			return codici;
		} catch (Exception e) {
			throw new SQLException("Error fetching getCodiciAppCM: " + e.getMessage(), e);
		}
	}

	

	public int verificaEsistenzaRG(String codRg) throws SQLException {
		try {
			String sql = "SELECT COUNT(*) FROM ROLE_GROUP WHERE CODICE_ROLE_GROUP = ?1";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, codRg);
			Object result = query.getSingleResult();
			return ((Number) result).intValue();
		} catch (Exception e) {
			throw new SQLException("Error verifying rg: " + e.getMessage(), e);
		}
	}

	public String getProfDaProfiloAttivoCM(HashMap<String, String> queryMap) throws SQLException {
		try {
			String sql = "SELECT DISTINCT"
					+ "		  CODICE_PROFILO"
					+ "		FROM"
					+ "		  PROFILI_ATTIVI_UTENTE"
					+ "		WHERE"
					+ "		  CF_UTENTE = ?1 "
					+ "		  AND CODICE_PROFILO = ?2 "
					+ "		  AND (ID_RICHIESTA_VISIBILITA IS NULL OR ID_RICHIESTA_VISIBILITA = '0')";
			
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, queryMap.get("codFiscale"));
			query.setParameter(2, queryMap.get("codProfilo"));
			Object result = query.getSingleResult();
			return result != null ? result.toString() : null;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new SQLException("Error fetching getProfDaProfiloAttivoCM: " + e.getMessage(), e);
		}
	}

	public String getProfiloAssegnatoCM(HashMap<String, String> queryMap) throws SQLException {
		try {
			String sql = "SELECT"
					+ "		  PROFILO_ASSEGNATO"
					+ "		FROM"
					+ "		  PROFILI_UTENTE_IN_VISIBILITA"
					+ "		WHERE"
					+ "		  CF_UTENTE = ?1 "
					+ "		  AND CODICE_PROFILO = ?2 "
					+ "		  AND ID_RICHIESTA_VISIBILITA = ?3 ";
			
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, queryMap.get("codFiscale"));
			query.setParameter(2, queryMap.get("codProfilo"));
			query.setParameter(3, queryMap.get("idRichiestaVisibilita"));
			Object result = query.getSingleResult();
			return result != null ? result.toString() : null;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new SQLException("Error fetching getProfiloAssegnatoCM: " + e.getMessage(), e);
		}
	}

	public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamento(InterrogazioneCaricamentiFinder finder) throws SQLException {
		List<AllegatoCaricamentoMassivoEntity> allegati = new ArrayList<>();
		try {
			String sql = "SELECT DOCUMENTO, TIPO, NOME_FILE, ID_ALLEGATO, ID_CARICAMENTO"
					+ "		FROM"
					+ "			CM_ALLEGATI"
					+ "		WHERE"
					+ "			ID_CARICAMENTO = ?1 ";
					if(finder.getTipoAllegato()!=null && !finder.getTipoAllegato().isEmpty())
					sql+= "	        AND	TIPO = ?2 ";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, finder.getIdCaricamento());
			
			if(finder.getTipoAllegato()!=null && !finder.getTipoAllegato().isEmpty())
			query.setParameter(2, finder.getTipoAllegato());
			
			List<Object[]> results = query.getResultList();
			for (Object[] row : results) {
				AllegatoCaricamentoMassivoEntity allegato = new AllegatoCaricamentoMassivoEntity();
				if (row[0] != null) allegato.setbFileAllegato((byte[]) row[0]);
				if (row[1] != null) allegato.setTipo(row[1].toString());
				if (row[2] != null) allegato.setNomeFile(row[2].toString());
				if (row[3] != null) allegato.setIdAllegato(row[3].toString());
				if (row[4] != null) allegato.setIdCaricamento(row[4].toString());
				
				allegati.add(allegato);
			}
			return allegati;
		} catch (Exception e) {
			throw new SQLException("Error fetching getElencoAllegatiCaricamento: " + e.getMessage(), e);
		}
	}

	public List<RichiestaBatchBean> getListaRichiesteBatchBySequence(long sequence) throws SQLException {
		List<RichiestaBatchBean> richieste = new ArrayList<>();
		try {
			String sql = "SELECT DISTINCT"
					+ "  			RB.CF,"
					+ "  			RB.SEQUENCE, "
					+ "  			RB.ID_RICHIESTA_VISIBILITA,"
					+ "  			RB.CODICE_CDR_VISIBILITA"
					+ "		FROM"
					+ "  			RICHIESTE_BATCH RB"
					+ "		WHERE"
					+ "  			SEQUENCE = ?1 "
					+ "  			AND (ID_RICHIESTA_GENERATA IS NULL OR ID_RICHIESTA_GENERATA = '0')";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, sequence);
			List<Object[]> results = query.getResultList();
			for (Object[] row : results) {
				RichiestaBatchBean richiesta = new RichiestaBatchBean();
				if (row[0] != null) richiesta.setCf(row[0].toString());
				if (row[1] != null) richiesta.setSequence(((Number) row[1]).longValue());
				if (row[2] != null) richiesta.setIdRichiestaVisibilita(((Number) row[2]).intValue());				
				if (row[3] != null) richiesta.setCdrVisibilita(row[3].toString());

				richieste.add(richiesta);
			}
			return richieste;
		} catch (Exception e) {
			throw new SQLException("Error fetching getListaRichiesteBatchBySequence: " + e.getMessage(), e);
		}
	}

	

	

	

	public RichiedenteCdR getDatiResponsabileCdrSuperiore(String codiceCdr) throws SQLException {
		RichiedenteCdR responsabile = new RichiedenteCdR();
		try {
			String sql = "SELECT "
					+ "                  RC.CODICE_CDR, "
					+ "                  RC.CF_RESPONSABILE_HR"
					+ "            FROM"
					+ "                  RICHIEDENTE_CDR RC, "
					+ "                  RELAZIONE_CDR_UFFICIO_STRUT RCUSUT, "
					+ "                  CDR CDR, "
					+ "                  RELAZIONE_CDR_UFFICIO_STRUT RCUSSTR"
					+ "            WHERE "
					+ "                  (RC.DATA_FINE_VAL IS NULL  OR RC.DATA_FINE_VAL > SYSDATE )"
					+ "                  AND (RCUSUT.DATA_FINE_VAL IS NULL OR RCUSUT.DATA_FINE_VAL > SYSDATE )"
					+ "                  AND CDR.CODICE_CDR = ?1 "
					+ "                  AND CDR.CODICE_CDR_PADRE_GERARCHIA = RC.CODICE_CDR"
					+ "                  AND CDR.CODICE_CDR = RCUSUT.CODICE_CDR"
					+ "                  AND RCUSUT.VERTICE_STRUTTURA = RCUSSTR.VERTICE_STRUTTURA"
					+ "                  AND CDR.CODICE_CDR_PADRE_GERARCHIA = RCUSSTR.CODICE_CDR"
					+ "                  AND RC.CF_RESPONSABILE_HR IS NOT NULL ";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, codiceCdr);
			Object result = query.getSingleResult();
			if (result instanceof Object[]) {
				Object[] row = (Object[]) result;
				if (row[0] != null) responsabile.setCodiceCdR(row[0].toString());
				if (row[1] != null) responsabile.setCfResponsabileHR(row[1].toString());
			}
			return responsabile;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new SQLException("Error fetching getDatiResponsabileCdrSuperiore: " + e.getMessage(), e);
		}
	}

	public RichiedenteCdR getDatiResponsabileStruttura(String codiceCdr) throws SQLException {
		RichiedenteCdR responsabile = new RichiedenteCdR();
		try {
			String sql = "SELECT "
					+ "                  RC.CODICE_CDR, "
					+ "                  RC.CF_RESPONSABILE_HR"
					+ "            FROM"
					+ "                  RICHIEDENTE_CDR RC, "
					+ "                  RELAZIONE_CDR_UFFICIO_STRUT RCUSUT"
					+ "            WHERE "
					+ "                  (RC.DATA_FINE_VAL IS NULL  OR RC.DATA_FINE_VAL > SYSDATE )"
					+ "                  AND (RCUSUT.DATA_FINE_VAL IS NULL OR RCUSUT.DATA_FINE_VAL > SYSDATE )"
					+ "                  AND RCUSUT.CODICE_CDR = ?1 "
					+ "                  AND RCUSUT.VERTICE_STRUTTURA = RC.CODICE_CDR"
					+ "                  AND RC.CF_RESPONSABILE_HR IS NOT NULL    ";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, codiceCdr);
			Object result = query.getSingleResult();
			if (result instanceof Object[]) {
				Object[] row = (Object[]) result;
				if (row[0] != null) responsabile.setCodiceCdR(row[0].toString());
				if (row[1] != null) responsabile.setCfResponsabileHR(row[1].toString());

			}
			return responsabile;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new SQLException("Error fetching getDatiResponsabileStruttura: " + e.getMessage(), e);
		}
	}

	public RichiedenteCdR getDatiRichiedenteCdrByCdrVis(String codiceCdr) throws SQLException {
		RichiedenteCdR richiedente = new RichiedenteCdR();
		try {
			String sql = "SELECT "
					+ "  			RC.CF_RICHIEDENTE"
					+ "		FROM "
					+ "  			RICHIEDENTE_CDR RC"
					+ "		WHERE "
					+ "  			RC.CODICE_CDR = ?1 "
					+ "  			AND (RC.DATA_FINE_VAL IS NULL OR RC.DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, codiceCdr);
			Object result = query.getSingleResult();
			if (result instanceof Object[]) {
				Object[] row = (Object[]) result;
				if (row[0] != null) richiedente.setCfRichiedente(row[0].toString());
			}
			return richiedente;
		} catch (NoResultException e) {
			return richiedente;
		} catch (Exception e) {
			throw new SQLException("Error fetching getDatiRichiedenteCdrByCdrVis: " + e.getMessage(), e);
		}
	}

//	public void inserisciRegistroRichiesta(RegistroRichiesteBatchBean regRichBetchBean) throws SQLException {
//
//		StringBuilder sql = new StringBuilder();
//		StringBuilder values = new StringBuilder();
//		List<Object> params = new ArrayList<>();
//		int paramIndex = 1;
//
//		sql.append("INSERT INTO REGISTRO_RICHIESTE (");
//		sql.append("ID_RICHIESTA, TIPO_RICHIESTA, CODICE_AMBITO, CF_UTENTE, CDR_UTENTE, ");
//		sql.append("CF_RICHIEDENTE, CF_RICHIEDENTE_EFFETTIVO, NOTE_RICHIEDENTE, DATA_RICHIESTA, ");
//		sql.append("CF_AUTORIZZATORE_1, CF_AUTORIZZATORE_1_EFFETTIVO");
//
//		values.append("?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++);
//		values.append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++);
//		values.append(", ?").append(paramIndex++).append(", ?").append(paramIndex++);
//
//		params.add(regRichBetchBean.getIdRichiesta());
//		params.add(regRichBetchBean.getTipoRichiesta());
//		params.add(regRichBetchBean.getCodiceAmbito());
//		params.add(regRichBetchBean.getCfUtente());
//		params.add(regRichBetchBean.getCdrUtente());
//		params.add(regRichBetchBean.getCfRichiedente());
//		params.add(regRichBetchBean.getCfRichiedenteEffettivo());
//		params.add(regRichBetchBean.getNoteRichiedente());
//		params.add(regRichBetchBean.getDataRichiesta());
//		params.add(regRichBetchBean.getCfAutorizzatore1());
//		params.add(regRichBetchBean.getCfAutorizzatore1Effettivo());
//
//		if (regRichBetchBean.getNotaAutorizzatore1() != null && !regRichBetchBean.getNotaAutorizzatore1().isEmpty()) {
//			sql.append(", NOTE_AUTORIZZATORE_1");
//			values.append(", ?").append(paramIndex++);
//			params.add(regRichBetchBean.getNotaAutorizzatore1());
//		}
//
//		sql.append(", STATO_RICHIESTA, RICHIEDENTE_AC, PRESA_VISIONE");
//		values.append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++);
//		params.add(regRichBetchBean.getStatoRichiesta());
//		params.add(regRichBetchBean.getRichiedenteAc());
//		params.add(regRichBetchBean.getPresaVisione());
//
//		if (regRichBetchBean.getCfArchiviazione() != null && !regRichBetchBean.getCfArchiviazione().isEmpty()) {
//			sql.append(", CF_ARCHIVIAZIONE");
//			values.append(", ?").append(paramIndex++);
//			params.add(regRichBetchBean.getCfArchiviazione());
//		}
//
//		if (regRichBetchBean.getCfArchiviazioneEffettivo() != null && !regRichBetchBean.getCfArchiviazioneEffettivo().isEmpty()) {
//			sql.append(", CF_ARCHIVIAZIONE_EFFETTIVO");
//			values.append(", ?").append(paramIndex++);
//			params.add(regRichBetchBean.getCfArchiviazioneEffettivo());
//		}
//
//		if (regRichBetchBean.getDataEsitoFinale() != null) {
//			sql.append(", DATA_ESITO_FINALE");
//			values.append(", ?").append(paramIndex++);
//			params.add(regRichBetchBean.getDataEsitoFinale());
//		}
//
//		if (regRichBetchBean.getEsitoFinale() != null && !regRichBetchBean.getEsitoFinale().isEmpty()) {
//			sql.append(", ESITO_FINALE");
//			values.append(", ?").append(paramIndex++);
//			params.add(regRichBetchBean.getEsitoFinale());
//		}
//
//		sql.append(", AGGIORNA_SISTEMA_AUTORIZZ, CODICE_EVENTO, RICHIESTA_VISIBILE_RUOLI_SIGA, ");
//		sql.append("CANCELLA_UTENTE_ESTERNO, NOTA_GENERAZIONE_RICHIESTA, CODICE_AMBITO_AUTORIZZAZIONE, ");
//		sql.append("DATA_AUTORIZZAZIONE_1");
//
//		values.append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++);
//		values.append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++);
//		values.append(", ?").append(paramIndex++);
//
//		params.add(regRichBetchBean.getAggiornaSistemaAutorizzazione());
//		params.add(regRichBetchBean.getCodiceEvento());
//		params.add(regRichBetchBean.getRichiestaVisibileRuoliSiga());
//		params.add(regRichBetchBean.getCancellaUtenteEsterno());
//		params.add(regRichBetchBean.getNotaGenerazioneRichiesta());
//		params.add(regRichBetchBean.getCodiceAmbitoAutorizzazione());
//		params.add(regRichBetchBean.getDataAutorizzazione1());
//
//		if (regRichBetchBean.getIdIter() != null) {
//			sql.append(", ITER_ID");
//			values.append(", ?").append(paramIndex++);
//			params.add(regRichBetchBean.getIdIter());
//		}
//
//		sql.append(", CDR_ARCHIVIAZIONE) VALUES (");
//		values.append(", ?").append(paramIndex++);
//		params.add(regRichBetchBean.getCdrArchiviazione());
//
//		sql.append(values).append(")");
//
//		try {
//			Query query = entityManager.createNativeQuery(sql.toString());
//			for (int i = 0; i < params.size(); i++) {
//				query.setParameter(i + 1, params.get(i));
//			}
//			query.executeUpdate();
//	}
//		} catch (Exception e) {
	//			throw new SQLException(e);
//		}
@TransactionAttribute(TransactionAttributeType.REQUIRED)	
	public void inserisciFile(DocumentoFinder docFinder) throws SQLException {
		try {
			String sqlSeq = "SELECT PROG_DOCUMENTO.nextval AS progDoc FROM dual";
			Query querySeq = entityManager.createNativeQuery(sqlSeq);
			Integer seq = ((Number)querySeq.getSingleResult()).intValue();
			
			String sql = "INSERT INTO DOCUMENTI_RICHIESTA"
					+ "		("
					+ "		ID_RICHIESTA,"
					+ "		PROG_DOCUMENTO,"
					+ "		DOCUMENTO,"
					+ "		NOME_FILE"
					+ "		)"
					+ "		VALUES"
					+ "		( "
					+ "		?1, "
					+ "		?2, "
					+ "		?3, "
					+ "		?4 "
					+ "		)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, docFinder.getIdTab());
			query.setParameter(2, seq);
			query.setParameter(3, docFinder.getbFile());
			query.setParameter(4, docFinder.getFileName());
			
			query.executeUpdate();
		} catch (Exception e) {
			throw new SQLException("Error inserting inserisciFile: " + e.getMessage(), e);
		}
	}

	public List<RichiestaBatchBean> getProfiliPerRichiesta(InterrogazioneCaricamentiFinder finder) throws SQLException {
		List<RichiestaBatchBean> profili = new ArrayList<>();
		try {
			String sql = "SELECT DISTINCT"
					+ "  			RB.CF,"
					+ "  			RB.SEQUENCE, "
					+ "  			RB.ID_RICHIESTA_VISIBILITA, "
					+ "  			RB.CODICE_ROLE_GROUP,"
					+ "  			RB.CODICE_PROFILO,"
					+ "  			RB.CODICE_UFFICIO,"
					+ "  			RB.CODICE_CDR,"
					+ "  			RB.AZIONE"
					+ "		FROM"
					+ "  			RICHIESTE_BATCH RB"
					+ "		WHERE"
					+ "  			SEQUENCE = ?1 "
					+ "  			AND ID_RICHIESTA_VISIBILITA = ?2 "
					+ "  			AND CF = ?3 "
					+ "  			AND (ID_RICHIESTA_GENERATA IS NULL OR ID_RICHIESTA_GENERATA = '0')";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, finder.getSequence());
			query.setParameter(2, finder.getIdRichiesta());
			query.setParameter(3, finder.getCodiceFiscaleUtente());
			List<Object[]> results = query.getResultList();
			for (Object[] row : results) {
				RichiestaBatchBean profilo = new RichiestaBatchBean();
				if (row[0] != null) profilo.setCf(row[0].toString());
				if (row[1] != null) profilo.setSequence(((Number) row[1]).intValue());
				if (row[2] != null) profilo.setIdRichiestaVisibilita(((Number) row[2]).intValue());
				if (row[3] != null) profilo.setCodRoleGroup(row[3].toString());
				if (row[4] != null) profilo.setCodProfilo(row[4].toString());
				if (row[5] != null) profilo.setCodUfficio(row[5].toString());
				if (row[6] != null) profilo.setCodCdr(row[6].toString());
				if (row[7] != null) profilo.setAzione(row[7].toString());
				profili.add(profilo);
			}
			return profili;
		} catch (Exception e) {
			throw new SQLException("Error fetching getProfiliPerRichiesta: " + e.getMessage(), e);
		}
	}

	public ProfiloRichiestaBean getCodApplicazione(String codiceProfilo) throws SQLException {
		ProfiloRichiestaBean profilo = new ProfiloRichiestaBean();
		try {
			String sql = "SELECT DISTINCT P.CODICE_APPLICAZIONE as codiceApplicazione , A.CODICE_AMBITO_APPLICATIVO as codiceAmbito "
					+ " FROM PROFILO P, APPLICAZIONI A   "
					+ " WHERE  "
					+ "	P.CODICE_PROFILO = ?1  AND P.CODICE_APPLICAZIONE= A.CODICE_APPLICAZIONE";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, codiceProfilo);
			Object result = query.getSingleResult();
			if (result instanceof Object[]) {
				Object[] row = (Object[]) result;
				if (row[0] != null) profilo.setCodiceApplicazione(row[0].toString());
			}
			return profilo;
		} catch (NoResultException e) {
			return profilo;
		} catch (Exception e) {
			throw new SQLException("Error fetching getCodApplicazione: " + e.getMessage(), e);
		}
	}

	

	
	

	public List<FileAbilitazioneBean> getListaRecordScartati(String idCaricamento) throws SQLException {
		List<FileAbilitazioneBean> record = new ArrayList<>();
		try {
			String sql = "SELECT "
					+ "			MOTIVAZIONE_SCARTO,"
					+ "			CODICE_FISCALE,"
					+ "  			CODICE_ROLE_GROUP,"
					+ "  			CODICE_PROFILO,"
					+ "  			CODICE_CDR,"
					+ "  			AZIONE"
					+ "		FROM"
					+ "		  	CM_FILE_ABILITAZIONI"
					+ "		WHERE"
					+ "		  	ID_CARICAMENTO = ?1 "
					+ "		  	AND STATO = 'Scartato'";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, idCaricamento);
			List<Object[]> results = query.getResultList();
			for (Object[] row : results) {
				FileAbilitazioneBean file = new FileAbilitazioneBean();
				if (row[0] != null) file.setMotivazioneScarto(row[0].toString());
				if (row[1] != null) file.setCodiceFiscale(row[1].toString());
				if (row[2] != null) file.setCodiceRoleGroup(row[2].toString());
				if (row[3] != null) file.setCodiceProfilo(row[3].toString());
				if (row[4] != null) file.setCodiceCdr(row[4].toString());
				if (row[5] != null) file.setAzione(row[5].toString());
				record.add(file);
			}
			return record;
		} catch (Exception e) {
			throw new SQLException("Error fetching getListaRecordScartati: " + e.getMessage(), e);
		}
	}

	
}
