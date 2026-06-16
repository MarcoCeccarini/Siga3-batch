package it.finanze.siga.dao.impl;

import it.finanze.scheduler.bean.AssociazOperRichiAutor;
import it.finanze.scheduler.bean.NotificaOperatoriDAO;
import it.finanze.scheduler.bean.RegistroRichieste;
import it.finanze.scheduler.bean.RelazioneCdRUfficioStrut;
import it.finanze.scheduler.bean.RichiedenteCdR;
import it.finanze.scheduler.bean.StrutturaIterDAO;
import it.finanze.scheduler.bean.TabellaBatchHrBean;
import it.finanze.scheduler.bean.Utenti;
import it.finanze.siga.bean.OperatoreBean;
import it.finanze.siga.finder.OperatoreFinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;

import org.apache.log4j.Logger;

@Stateless
@Slf4j
@Named("BatchHrVariazioneDatiUtenteDao")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BatchHrVariazioneDatiUtenteDao extends SigaDaoImpl{

    private static final Logger log = Logger.getLogger(BatchHrVariazioneDatiUtenteDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public TabellaBatchHrBean selectSettingsBatch() {
        TabellaBatchHrBean bean = new TabellaBatchHrBean();
        try {
            String sql =
                "SELECT BH.DATA_INIZIO_RESPONSABILI_CDR, BH.DATA_FINE_RESPONSABILI_CDR, " +
                "BH.DATA_INIZIO_UTENTI, BH.DATA_FINE_UTENTI, BH.DATA_INIZIO_EMAIL_RUOLI, " +
                "BH.DATA_FINE_EMAIL_RUOLI, B.VALORE_COSTANTE AS EMAIL_SEGNALAZIONI_SOGEI " +
                "FROM BATCH_HR BH, COSTANTI_SIGA B WHERE B.NOME_COSTANTE = 'emailSegnalazioniSogei'";
            Query q = entityManager.createNativeQuery(sql);
            Object[] row = (Object[]) q.getSingleResult();
            if (row[0] != null) bean.setDataInizioResponsabiliCdr((java.util.Date) row[0]);
            if (row[1] != null) bean.setDataFineResponsabiliCdr((java.util.Date) row[1]);
            if (row[2] != null) bean.setDataInizioUtenti((java.util.Date) row[2]);
            if (row[3] != null) bean.setDataFineUtenti((java.util.Date) row[3]);
            if (row[4] != null) bean.setDataInizioEmailRuoli((java.util.Date) row[4]);
            if (row[5] != null) bean.setDataFineEmailRuoli((java.util.Date) row[5]);
            if (row[6] != null) bean.setEmailSegnalazioniSogei(row[6].toString());
        } catch (Exception e) {
            log.error("Errore nel metodo selectSettingsBatch", e);
            throw new RuntimeException(e);
        }
        return bean;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void startBatchVariazioneDatiUte() {
        try {
            Query q = entityManager.createNativeQuery(
                "UPDATE BATCH_HR SET DATA_INIZIO_UTENTI = SYSDATE, DATA_FINE_UTENTI = NULL");
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo startBatchVariazioneDatiUte", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void endBatchVariazioneDatiUte() {
        try {
            Query q = entityManager.createNativeQuery(
                "UPDATE BATCH_HR SET DATA_FINE_UTENTI = SYSDATE");
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo endBatchVariazioneDatiUte", e);
            throw new RuntimeException(e);
        }
    }

    public Integer countUtenteByCF(String userId) {
        try {
            Query q = entityManager.createNativeQuery(
                "SELECT COUNT(*) FROM UTENTI WHERE CODICE_FISCALE = ?1 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)");
            q.setParameter(1, userId);
            return ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            log.error("Errore nel metodo countUtenteByCF", e);
            throw new RuntimeException(e);
        }
    }

    public Integer countUtenteByCFClosed(String userId) {
        try {
            Query q = entityManager.createNativeQuery(
                "SELECT COUNT(*) FROM UTENTI WHERE CODICE_FISCALE = ?1 AND DATA_FINE_VAL IS NOT NULL");
            q.setParameter(1, userId);
            return ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            log.error("Errore nel metodo countUtenteByCFClosed", e);
            throw new RuntimeException(e);
        }
    }

    public Utenti selectUtenteByCF(String cfGenerico) {
        try {
            String sql =
                "SELECT NOME, COGNOME, E_MAIL, CODICE_CDR, RICHIEDENTE, " +
                "AUTORIZZATORE_I_LIV, AUTORIZZATORE_II_LIV, CODICE_FISCALE " +
                "FROM UTENTI WHERE CODICE_FISCALE = ?1 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, cfGenerico);
            Object[] row = (Object[]) q.getSingleResult();
            Utenti utenti = new Utenti();
            utenti.setNome(row[0] != null ? row[0].toString() : null);
            utenti.setCognome(row[1] != null ? row[1].toString() : null);
            utenti.seteMail(row[2] != null ? row[2].toString() : null);
            utenti.setCodiceCdR(row[3] != null ? row[3].toString() : null);
            utenti.setRichiedente(row[4] != null ? row[4].toString() : null);
            utenti.setAutorizzatoreIliv(row[5] != null ? row[5].toString() : null);
            utenti.setAutorizzatoreIIliv(row[6] != null ? row[6].toString() : null);
            utenti.setCodiceFiscale(row[7] != null ? row[7].toString() : null);
            return utenti;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo selectUtenteByCF", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> selectUtentiByCdrDisall() {
        List<String> lista = new ArrayList<String>();
        try {
            Query q = entityManager.createNativeQuery(
                "SELECT CODICE_FISCALE FROM UTENTI WHERE CDR_DISALL_CAU_SIGA = 'SI' " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)");
            List<Object> rows = q.getResultList();
            for (Object row : rows) {
                if (row != null) lista.add(row.toString());
            }
        } catch (Exception e) {
            log.error("Errore nel metodo selectUtentiByCdrDisall", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<Utenti> selectAllCFUtenti() {
        List<Utenti> lista = new ArrayList<Utenti>();
        try {
            String sql =
                "SELECT CODICE_FISCALE, CODICE_CDR, RICHIEDENTE, AUTORIZZATORE_I_LIV, AUTORIZZATORE_II_LIV " +
                "FROM UTENTI WHERE (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            List<Object[]> rows = q.getResultList();
            for (Object[] row : rows) {
                Utenti utenti = new Utenti();
                utenti.setCodiceFiscale(row[0] != null ? row[0].toString() : null);
                utenti.setCodiceCdR(row[1] != null ? row[1].toString() : null);
                utenti.setRichiedente(row[2] != null ? row[2].toString() : null);
                utenti.setAutorizzatoreIliv(row[3] != null ? row[3].toString() : null);
                utenti.setAutorizzatoreIIliv(row[4] != null ? row[4].toString() : null);
                lista.add(utenti);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo selectAllCFUtenti", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertUtentiSiga(HashMap<String, String> input) {
        try {
            String sql =
                "INSERT INTO UTENTI (CODICE_FISCALE, DATA_INIZIO_VAL, DATA_FINE_VAL, COGNOME, NOME, " +
                "CODICE_CDR, TIPO_UTENTE, RICHIEDENTE, AUTORIZZATORE_I_LIV, AUTORIZZATORE_II_LIV, E_MAIL, TELEFONO) " +
                "VALUES (?1, SYSDATE, null, ?2, ?3, ?4, 'I', 'NO', 'NO', 'NO', ?5, ?6)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            q.setParameter(2, input.get("sn"));
            q.setParameter(3, input.get("givenName"));
            q.setParameter(4, input.get("orgName"));
            q.setParameter(5, input.get("mail"));
            q.setParameter(6, input.get("telephoneNumber"));
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo insertUtentiSiga", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertStoricoCdRUtenti(HashMap<String, String> input) {
        try {
            String sql =
                "INSERT INTO STORICO_CDR_UTENTI (CODICE_FISCALE, CODICE_CDR, DATA_INIZIO_VAL, DATA_FINE_VAL) " +
                "VALUES (?1, ?2, SYSDATE, null)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            q.setParameter(2, input.get("orgName"));
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo insertStoricoCdRUtenti", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertUtentiAggiornatiHR_Var(HashMap<String, String> input) {
        try {
            String sql =
                "INSERT INTO UTENTI_AGGIORNATI_HR (CODICE_FISCALE, TIPO_EVENTO, CODICE_CDR_OLD, CODICE_CDR_NEW, DATA_INSERIMENTO) " +
                "VALUES (?1, ?2, ?3, ?4, SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            q.setParameter(2, input.get("tipoEvento"));
            q.setParameter(3, input.get("oldCdR"));
            q.setParameter(4, input.get("orgName"));
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo insertUtentiAggiornatiHR_Var", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateUtentiPerRipristino(HashMap<String, String> input) {
        try {
            String sql =
                "UPDATE UTENTI SET DATA_FINE_VAL = null, CODICE_CDR = ?1 WHERE CODICE_FISCALE = ?2";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("newCdr"));
            q.setParameter(2, input.get("codiceFiscaleUtenti"));
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo updateUtentiPerRipristino", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateStoricoCdrUtenti(Utenti utentiSiga) {
        try {
            String sql =
                "UPDATE STORICO_CDR_UTENTI SET DATA_FINE_VAL = SYSDATE " +
                "WHERE CODICE_FISCALE = ?1 AND CODICE_CDR = ?2 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, utentiSiga.getCodiceFiscale());
            q.setParameter(2, utentiSiga.getCodiceCdR());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo updateStoricoCdrUtenti", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateUtenti(Utenti utentiSiga) {
        try {
            boolean hasCdrDisall = utentiSiga.getFlagUtenteCdrDisallienato() != null
                    && !utentiSiga.getFlagUtenteCdrDisallienato().isEmpty();
            boolean hasDataFine = utentiSiga.getDataFineValidita() != null
                    && !utentiSiga.getDataFineValidita().isEmpty();
            StringBuilder sql = new StringBuilder(
                "UPDATE UTENTI SET COGNOME = ?1, NOME = ?2, CODICE_CDR = ?3, " +
                "RICHIEDENTE = ?4, AUTORIZZATORE_I_LIV = ?5, AUTORIZZATORE_II_LIV = ?6, E_MAIL = ?7"
            );
            int idx = 8;
            if (hasCdrDisall) {
                sql.append(", CDR_DISALL_CAU_SIGA = ?").append(idx++);
            }
            if (hasDataFine) {
                sql.append(", DATA_FINE_VAL = null");
            }
            sql.append(" WHERE CODICE_FISCALE = ?").append(idx);
            if (!hasDataFine) {
                sql.append(" AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)");
            }
            Query q = entityManager.createNativeQuery(sql.toString());
            q.setParameter(1, utentiSiga.getCognome());
            q.setParameter(2, utentiSiga.getNome());
            q.setParameter(3, utentiSiga.getCodiceCdR());
            q.setParameter(4, utentiSiga.getRichiedente());
            q.setParameter(5, utentiSiga.getAutorizzatoreIliv());
            q.setParameter(6, utentiSiga.getAutorizzatoreIIliv());
            q.setParameter(7, utentiSiga.geteMail());
            int p = 8;
            if (hasCdrDisall) q.setParameter(p++, utentiSiga.getFlagUtenteCdrDisallienato());
            q.setParameter(p, utentiSiga.getCodiceFiscale());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo updateUtenti", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateFlagUtenteCdrDisallienato(OperatoreFinder in) {
        try {
            String sql =
                "UPDATE UTENTI SET CDR_DISALL_CAU_SIGA = ?1 WHERE CODICE_FISCALE = ?2";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, in.getFlagCdrDisallCauSiga());
            q.setParameter(2, in.getCodFisUser());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo updateFlagUtenteCdrDisallienato", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateUtentiPerCessazione(String codiceFiscaleUtenti) {
        try {
            String sql =
                "UPDATE UTENTI SET DATA_FINE_VAL = SYSDATE WHERE CODICE_FISCALE = ?1 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, codiceFiscaleUtenti);
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo updateUtentiPerCessazione", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateStoricoCdrUtentiPerCessazione(Utenti utentiSiga) {
        try {
            String sql =
                "UPDATE STORICO_CDR_UTENTI SET DATA_FINE_VAL = SYSDATE " +
                "WHERE CODICE_FISCALE = ?1 AND CODICE_CDR = ?2 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, utentiSiga.getCodiceFiscale());
            q.setParameter(2, utentiSiga.getCodiceCdR());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo updateStoricoCdrUtentiPerCessazione", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void aggiornaFlagUtente(Utenti ute) {
        try {
            List<String> setClauses = new ArrayList<String>();
            List<Object> paramValues = new ArrayList<Object>();
            if (ute.getRichiedente() != null && !ute.getRichiedente().isEmpty()) {
                paramValues.add(ute.getRichiedente());
                setClauses.add("RICHIEDENTE = ?" + paramValues.size());
            }
            if (ute.getAutorizzatoreIliv() != null && !ute.getAutorizzatoreIliv().isEmpty()) {
                paramValues.add(ute.getAutorizzatoreIliv());
                setClauses.add("AUTORIZZATORE_I_LIV = ?" + paramValues.size());
            }
            if (ute.getAutorizzatoreIIliv() != null && !ute.getAutorizzatoreIIliv().isEmpty()) {
                paramValues.add(ute.getAutorizzatoreIIliv());
                setClauses.add("AUTORIZZATORE_II_LIV = ?" + paramValues.size());
            }
            if (setClauses.isEmpty()) return;
            paramValues.add(ute.getCodiceFiscale());
            String sql = "UPDATE UTENTI SET " + join(setClauses) + " WHERE CODICE_FISCALE = ?" + paramValues.size();
            Query q = entityManager.createNativeQuery(sql);
            for (int i = 0; i < paramValues.size(); i++) {
                q.setParameter(i + 1, paramValues.get(i));
            }
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo aggiornaFlagUtente", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateUtentiForVerificaOperatore(Utenti utentiForOperatore) {
        try {
            String sql =
                "UPDATE UTENTI SET RICHIEDENTE = ?1, AUTORIZZATORE_I_LIV = ?2 " +
                "WHERE CODICE_FISCALE = ?3 AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, utentiForOperatore.getRichiedente());
            q.setParameter(2, utentiForOperatore.getAutorizzatoreIliv());
            q.setParameter(3, utentiForOperatore.getCodiceFiscale());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo updateUtentiForVerificaOperatore", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<RichiedenteCdR> selectCdRFromRichiedenteCdRByCFRichiedente(HashMap<String, String> input) {
        List<RichiedenteCdR> lista = new ArrayList<RichiedenteCdR>();
        try {
            String sql =
                "SELECT CODICE_CDR FROM RICHIEDENTE_CDR " +
                "WHERE (CF_RICHIEDENTE = ?1 OR CF_RESPONSABILE_HR = ?1) " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            List<Object> rows = q.getResultList();
            for (Object row : rows) {
                RichiedenteCdR r = new RichiedenteCdR();
                r.setCodiceCdR(row != null ? row.toString() : null);
                lista.add(r);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo selectCdRFromRichiedenteCdRByCFRichiedente", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<RichiedenteCdR> selectCdRFromRichiedenteCdRByCFAutorizzatore(HashMap<String, String> input) {
        List<RichiedenteCdR> lista = new ArrayList<RichiedenteCdR>();
        try {
            String sql =
                "SELECT CODICE_CDR FROM RICHIEDENTE_CDR " +
                "WHERE CF_AUTORIZZATORE_I_LIV = ?1 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            List<Object> rows = q.getResultList();
            for (Object row : rows) {
                RichiedenteCdR r = new RichiedenteCdR();
                r.setCodiceCdR(row != null ? row.toString() : null);
                lista.add(r);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo selectCdRFromRichiedenteCdRByCFAutorizzatore", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<AssociazOperRichiAutor> selectCFAutorizzatoreByCFRichiedente(HashMap<String, String> input) {
        List<AssociazOperRichiAutor> lista = new ArrayList<AssociazOperRichiAutor>();
        try {
            String sql =
                "SELECT CODICE_FISCALE_AUTORIZZATORE FROM ASSOCIAZ_OPER_RICHI_AUTOR " +
                "WHERE CODICE_FISCALE_RICHIEDENTE = ?1 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            List<Object> rows = q.getResultList();
            for (Object row : rows) {
                AssociazOperRichiAutor a = new AssociazOperRichiAutor();
                a.setCfAutorizzatore(row != null ? row.toString() : null);
                lista.add(a);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo selectCFAutorizzatoreByCFRichiedente", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<AssociazOperRichiAutor> selectCFRichiedenteByCFAutorizzatore(HashMap<String, String> input) {
        List<AssociazOperRichiAutor> lista = new ArrayList<AssociazOperRichiAutor>();
        try {
            String sql =
                "SELECT CODICE_FISCALE_RICHIEDENTE FROM ASSOCIAZ_OPER_RICHI_AUTOR " +
                "WHERE CODICE_FISCALE_AUTORIZZATORE = ?1 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            List<Object> rows = q.getResultList();
            for (Object row : rows) {
                AssociazOperRichiAutor a = new AssociazOperRichiAutor();
                a.setCfRichiedente(row != null ? row.toString() : null);
                lista.add(a);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo selectCFRichiedenteByCFAutorizzatore", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<StrutturaIterDAO> verifyAutorizzatoreIORIILivello(HashMap<String, String> input) {
        List<StrutturaIterDAO> lista = new ArrayList<StrutturaIterDAO>();
        try {
            String sql =
                "SELECT NVL(IDENTIFICATIVO_ITER,0), TIPO_UFFICIO_RICHIEDENTE, ITER_STANDARD, " +
                "TIPO_CDR_AUTORIZ_I_LIV, CDR_AUTORIZ_I_LIV, CF_I, PREVISTO_AUTORIZZATORE_II_LIV, " +
                "TIPO_CDR_AUTORIZ_II_LIV, CDR_AUTORIZ_II_LIV, CF_II, GEST_OPER_STRUT_RICHIEDENTE, " +
                "GEST_OPER_STRUT_AUTOR_I_LIV, GEST_OPER_STRUT_AUTOR_II_LIV, GRUPPO_GESTORI_OPERATORI, " +
                "CDR_GESTORI_OPERATORI, DATA_FINE_VAL, NVL(ID_AUDIT_INIZIO,0), CODICE_FISCALE_INIZIO, " +
                "NVL(ID_AUDIT_FINE,0), CODICE_FISCALE_FINE " +
                "FROM STRUTTURA_ITER " +
                "WHERE (CF_I = ?1 OR CF_II = ?1) AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            List<Object[]> rows = q.getResultList();
            for (Object[] row : rows) {
                StrutturaIterDAO s = new StrutturaIterDAO();
                s.setIdentificativoIter(row[0] != null ? ((Number) row[0]).intValue() : 0);
                s.setTipoUfficioRichiedente(row[1] != null ? row[1].toString() : null);
                s.setIterStandard(row[2] != null ? row[2].toString() : null);
                s.setTipoCdrAutorizILiv(row[3] != null ? row[3].toString() : null);
                s.setCdrAutorizILiv(row[4] != null ? row[4].toString() : null);
                s.setCf_I(row[5] != null ? row[5].toString() : null);
                s.setPrevistoAutorizzatoreIILiv(row[6] != null ? row[6].toString() : null);
                s.setTipoCdrAutorizIILiv(row[7] != null ? row[7].toString() : null);
                s.setCdrAutorizIILiv(row[8] != null ? row[8].toString() : null);
                s.setCf_II(row[9] != null ? row[9].toString() : null);
                s.setGestOperStrutRichiedente(row[10] != null ? row[10].toString() : null);
                s.setGestOperStrutAutorILiv(row[11] != null ? row[11].toString() : null);
                s.setGestOperStrutAutorIILiv(row[12] != null ? row[12].toString() : null);
                s.setGruppoGestoriOperatori(row[13] != null ? row[13].toString() : null);
                s.setCdrGestoriOperatori(row[14] != null ? row[14].toString() : null);
                if (row[15] != null) s.setDataFineVal((java.sql.Date) row[15]);
                s.setIdAuditInizio(row[16] != null ? ((Number) row[16]).intValue() : 0);
                s.setCodiceFiscaleInizio(row[17] != null ? row[17].toString() : null);
                s.setIdAuditFine(row[18] != null ? ((Number) row[18]).intValue() : 0);
                s.setCodiceFiscaleFine(row[19] != null ? row[19].toString() : null);
                lista.add(s);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo verifyAutorizzatoreIORIILivello", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateStrutturaIter(HashMap<String, String> input) {
        try {
            String sql =
                "UPDATE STRUTTURA_ITER SET DATA_FINE_VAL = SYSDATE " +
                "WHERE (CF_I = ?1 OR CF_II = ?1) AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo updateStrutturaIter", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertStrutturaIter(StrutturaIterDAO s) {
        try {
            String sql =
                "INSERT INTO STRUTTURA_ITER (IDENTIFICATIVO_ITER, TIPO_UFFICIO_RICHIEDENTE, ITER_STANDARD, " +
                "TIPO_CDR_AUTORIZ_I_LIV, CDR_AUTORIZ_I_LIV, CF_I, PREVISTO_AUTORIZZATORE_II_LIV, " +
                "TIPO_CDR_AUTORIZ_II_LIV, CDR_AUTORIZ_II_LIV, CF_II, GEST_OPER_STRUT_RICHIEDENTE, " +
                "GEST_OPER_STRUT_AUTOR_I_LIV, GEST_OPER_STRUT_AUTOR_II_LIV, GRUPPO_GESTORI_OPERATORI, " +
                "CDR_GESTORI_OPERATORI, DATA_INIZIO_VAL, DATA_FINE_VAL, ID_AUDIT_INIZIO, " +
                "CODICE_FISCALE_INIZIO, ID_AUDIT_FINE, CODICE_FISCALE_FINE) " +
                "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, " +
                "SYSDATE, ?16, ?17, ?18, ?19, ?20)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, s.getIdentificativoIter());
            q.setParameter(2, s.getTipoUfficioRichiedente());
            q.setParameter(3, s.getIterStandard());
            q.setParameter(4, s.getTipoCdrAutorizILiv());
            q.setParameter(5, s.getCdrAutorizILiv());
            q.setParameter(6, s.getCf_I());
            q.setParameter(7, s.getPrevistoAutorizzatoreIILiv());
            q.setParameter(8, s.getTipoCdrAutorizIILiv());
            q.setParameter(9, s.getCdrAutorizIILiv());
            q.setParameter(10, s.getCf_II());
            q.setParameter(11, s.getGestOperStrutRichiedente());
            q.setParameter(12, s.getGestOperStrutAutorILiv());
            q.setParameter(13, s.getGestOperStrutAutorIILiv());
            q.setParameter(14, s.getGruppoGestoriOperatori());
            q.setParameter(15, s.getCdrGestoriOperatori());
            q.setParameter(16, s.getDataFineVal());
            q.setParameter(17, s.getIdAuditInizio());
            q.setParameter(18, s.getCodiceFiscaleInizio());
            q.setParameter(19, s.getIdAuditFine());
            q.setParameter(20, s.getCodiceFiscaleFine());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo insertStrutturaIter", e);
            throw new RuntimeException(e);
        }
    }

    public OperatoreBean getDatiUtente2(OperatoreFinder operatoreFinder) {
        try {
            String sql =
                "SELECT UT.NOME, UT.COGNOME, UT.CODICE_FISCALE, UT.TIPO_UTENTE, " +
                "UT.AUTORIZZATORE_I_LIV, UT.AUTORIZZATORE_II_LIV, UT.RICHIEDENTE, UT.E_MAIL, " +
                "CDR.CODICE_CDR, CDR.DESCRIZIONE, UT.FLG_RICH, UT.FLG_AUT " +
                "FROM UTENTI_UNIFICATI UT, CDR CDR " +
                "WHERE UT.CODICE_FISCALE = ?1 AND CDR.CODICE_CDR = UT.CODICE_CDR " +
                "AND (UT.DATA_FINE_VAL IS NULL OR UT.DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, operatoreFinder.getCodFisUser());
            Object[] row = (Object[]) q.getSingleResult();
            OperatoreBean bean = new OperatoreBean();
            bean.setNome(row[0] != null ? row[0].toString() : null);
            bean.setCognome(row[1] != null ? row[1].toString() : null);
            bean.setCf(row[2] != null ? row[2].toString() : null);
            bean.setTipoUtente(row[3] != null ? row[3].toString() : null);
            bean.setAutorizzazioneLivelloI(row[4] != null ? row[4].toString() : null);
            bean.setAutorizzazioneLivelloII(row[5] != null ? row[5].toString() : null);
            bean.setRichiedente(row[6] != null ? row[6].toString() : null);
            bean.setEmail(row[7] != null ? row[7].toString() : null);
            bean.setCdr(row[8] != null ? row[8].toString() : null);
            bean.setDescrizioneCDR(row[9] != null ? row[9].toString() : null);
            bean.setFlag_richiedente(row[10] != null ? row[10].toString() : null);
            bean.setFlag_autorizzazioneLivelloI(row[11] != null ? row[11].toString() : null);
            return bean;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getDatiUtente2", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Utenti> countRegistroRichieste(HashMap<String, String> input) {
        List<Utenti> lista = new ArrayList<Utenti>();
        try {
            String sql =
                "SELECT DISTINCT UT.COGNOME, UT.NOME, UT.CODICE_FISCALE " +
                "FROM REGISTRO_RICHIESTE RR, STATO_RICHIESTA SR, UTENTI UT " +
                "WHERE RR.STATO_RICHIESTA = SR.STATO AND SR.CANCELLABILE = 'SI' " +
                "AND UT.CODICE_FISCALE = RR.CF_UTENTE " +
                "AND ((CDR_AUTORIZZATORE_1 IS NULL AND CF_AUTORIZZATORE_1 = ?1) " +
                "OR (CDR_AUTORIZZATORE_2 IS NULL AND CF_AUTORIZZATORE_2 = ?1))";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("cfResponsabile"));
            List<Object[]> rows = q.getResultList();
            for (Object[] row : rows) {
                Utenti u = new Utenti();
                u.setCognome(row[0] != null ? row[0].toString() : null);
                u.setNome(row[1] != null ? row[1].toString() : null);
                u.setCodiceFiscale(row[2] != null ? row[2].toString() : null);
                lista.add(u);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo countRegistroRichieste", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<NotificaOperatoriDAO> utentiRichiesteDaSegnalare(HashMap<String, String> input) {
        List<NotificaOperatoriDAO> lista = new ArrayList<NotificaOperatoriDAO>();
        try {
            String sql =
                "SELECT DISTINCT ST.CODICE_CDR_II_LIV_GERARCHICO, ST.CENTRALE_PERIFERICO, " +
                "CDR.DESCRIZIONE, UT.COGNOME, UT.NOME, UT.CODICE_CDR, UT.CODICE_FISCALE " +
                "FROM REGISTRO_RICHIESTE RR, STATO_RICHIESTA SR, STRUTTURA ST, " +
                "RELAZIONE_CDR_UFFICIO_STRUT RCUS, UTENTI UT, CDR CDR " +
                "WHERE DATA_ESITO_FINALE IS NULL AND RR.STATO_RICHIESTA = SR.STATO " +
                "AND CANCELLABILE = 'SI' " +
                "AND ((CDR_AUTORIZZATORE_1 IS NULL AND CF_AUTORIZZATORE_1 = ?1) " +
                "OR (CDR_AUTORIZZATORE_2 IS NULL AND CF_AUTORIZZATORE_2 = ?1)) " +
                "AND RR.CDR_UTENTE = RCUS.CODICE_CDR " +
                "AND RCUS.VERTICE_STRUTTURA = ST.CODICE_CDR " +
                "AND RR.CF_UTENTE = UT.CODICE_FISCALE " +
                "AND RR.CDR_UTENTE = CDR.CODICE_CDR " +
                "ORDER BY ST.CODICE_CDR_II_LIV_GERARCHICO, CDR.DESCRIZIONE, UT.COGNOME";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("cfResponsabile"));
            List<Object[]> rows = q.getResultList();
            for (Object[] row : rows) {
                NotificaOperatoriDAO n = new NotificaOperatoriDAO();
                n.setCodice_cdr_ii_liv_gerarchico(row[0] != null ? row[0].toString() : null);
                n.setCentrale_periferico(row[1] != null ? row[1].toString() : null);
                n.setDescr_cdr(row[2] != null ? row[2].toString() : null);
                n.setCognome(row[3] != null ? row[3].toString() : null);
                n.setNome(row[4] != null ? row[4].toString() : null);
                n.setCdr(row[5] != null ? row[5].toString() : null);
                n.setCodiceFiscaleUtente(row[6] != null ? row[6].toString() : null);
                lista.add(n);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo utentiRichiesteDaSegnalare", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    public String getDescrCdrByCdr(String cdr) {
        try {
            if (cdr == null) return null;
            Query q = entityManager.createNativeQuery(
                "SELECT DESCRIZIONE FROM CDR WHERE CODICE_CDR = ?1 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)");
            q.setParameter(1, cdr);
            Object result = q.getSingleResult();
            return result != null ? result.toString() : null;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getDescrCdrByCdr", e);
            throw new RuntimeException(e);
        }
    }

    public String getDescrizioneCdr(String codCdr) {
        try {
            Query q = entityManager.createNativeQuery(
                "SELECT DESCRIZIONE FROM CDR WHERE CODICE_CDR = ?1");
            q.setParameter(1, codCdr);
            Object result = q.getSingleResult();
            return result != null ? result.toString() : null;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getDescrizioneCdr", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> selectEmailAmministratoriCentrali() {
        List<String> lista = new ArrayList<String>();
        try {
            String sql =
                "SELECT E_MAIL FROM PROFILI_ATTIVI_UTENTE a, UTENTI b " +
                "WHERE a.CODICE_PROFILO = 'ESG_AMM_Centrale_3' AND a.CF_UTENTE = b.CODICE_FISCALE " +
                "AND (a.DATA_FINE_VAL IS NULL OR a.DATA_FINE_VAL > SYSDATE) " +
                "AND (b.DATA_FINE_VAL IS NULL OR b.DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            List<Object> rows = q.getResultList();
            for (Object row : rows) {
                if (row != null) lista.add(row.toString());
            }
        } catch (Exception e) {
            log.error("Errore nel metodo selectEmailAmministratoriCentrali", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<String> selectEmailUtenteByCdRIILivGerarchico(String struttura) {
        List<String> lista = new ArrayList<String>();
        try {
            String sql =
                "SELECT E_MAIL FROM UTENTI a, RELAZIONE_CDR_UFFICIO_STRUT b, STRUTTURA c, PROFILI_ATTIVI_UTENTE d " +
                "WHERE a.CODICE_CDR = b.CODICE_CDR AND b.CODICE_STRUTTURA = c.CODICE_STRUTTURA " +
                "AND c.CODICE_CDR_II_LIV_GERARCHICO = ?1 AND a.CODICE_FISCALE = d.CF_UTENTE " +
                "AND d.CODICE_PROFILO = 'ESG_AMM_Regionale_3' " +
                "AND (a.DATA_FINE_VAL IS NULL OR a.DATA_FINE_VAL > SYSDATE) " +
                "AND (b.DATA_FINE_VAL IS NULL OR b.DATA_FINE_VAL > SYSDATE) " +
                "AND (c.DATA_FINE_VAL IS NULL OR c.DATA_FINE_VAL > SYSDATE) " +
                "AND (d.DATA_FINE_VAL IS NULL OR d.DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, struttura);
            List<Object> rows = q.getResultList();
            for (Object row : rows) {
                if (row != null) lista.add(row.toString());
            }
        } catch (Exception e) {
            log.error("Errore nel metodo selectEmailUtenteByCdRIILivGerarchico", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Integer verifyDelegato(String cfDelegato) {
        try {
            Query q = entityManager.createNativeQuery(
                "SELECT COUNT(*) FROM DELEGHE WHERE CODICE_FISCALE_DELEGATO = ?1 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)");
            q.setParameter(1, cfDelegato);
            return ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            log.error("Errore nel metodo verifyDelegato", e);
            throw new RuntimeException(e);
        }
    }

    public Integer countAssociazOperRichiAutor(HashMap<String, String> input) {
        try {
            Query q = entityManager.createNativeQuery(
                "SELECT COUNT(*) FROM ASSOCIAZ_OPER_RICHI_AUTOR " +
                "WHERE CODICE_FISCALE_OPERATORE = ?1 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)");
            q.setParameter(1, input.get("userId"));
            return ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            log.error("Errore nel metodo countAssociazOperRichiAutor", e);
            throw new RuntimeException(e);
        }
    }

    public AssociazOperRichiAutor selectCFRichiedenteEAutorizzatoreByCFOperatore(HashMap<String, String> input) {
        try {
            String sql =
                "SELECT CODICE_FISCALE_RICHIEDENTE, CODICE_FISCALE_AUTORIZZATORE, CODICE_CDR_RICHIEDENTE " +
                "FROM ASSOCIAZ_OPER_RICHI_AUTOR WHERE CODICE_FISCALE_OPERATORE = ?1 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            Object[] row = (Object[]) q.getSingleResult();
            AssociazOperRichiAutor a = new AssociazOperRichiAutor();
            a.setCfRichiedente(row[0] != null ? row[0].toString() : null);
            a.setCfAutorizzatore(row[1] != null ? row[1].toString() : null);
            a.setCodiceCdRRichiedente(row[2] != null ? row[2].toString() : null);
            return a;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo selectCFRichiedenteEAutorizzatoreByCFOperatore", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void closeAssociazOperRichiAutorByCFOperatore(HashMap<String, String> input) {
        try {
            String sql =
                "UPDATE ASSOCIAZ_OPER_RICHI_AUTOR SET DATA_FINE_VAL = SYSDATE " +
                "WHERE CODICE_FISCALE_OPERATORE = ?1 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo closeAssociazOperRichiAutorByCFOperatore", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void annullamentoRichieste(HashMap<String, String> input) {
        try {
            String sql =
                "UPDATE REGISTRO_RICHIESTE SET STATO_RICHIESTA = 'ANN', DATA_CHIUSURA_RICHIESTA = SYSDATE " +
                "WHERE REGISTRO_RICHIESTE.CF_UTENTE = ?1 " +
                "AND REGISTRO_RICHIESTE.DATA_CHIUSURA_RICHIESTA IS NULL " +
                "AND REGISTRO_RICHIESTE.CODICE_AMBITO IN " +
                "(SELECT CODICE_AMBITO FROM AMBITO_APPLICATIVO WHERE SINCRONIZZATA_CAU = 'SI')";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo annullamentoRichieste", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void annullamentoRichieste2(HashMap<String, String> input) {
        try {
            String sql =
                "UPDATE REGISTRO_RICHIESTE SET STATO_RICHIESTA = 'ANN', DATA_CHIUSURA_RICHIESTA = SYSDATE " +
                "WHERE REGISTRO_RICHIESTE.CF_UTENTE = ?1 " +
                "AND REGISTRO_RICHIESTE.DATA_CHIUSURA_RICHIESTA IS NULL " +
                "AND REGISTRO_RICHIESTE.CODICE_AMBITO IS NULL";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo annullamentoRichieste2", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<RegistroRichieste> selectCFPresaINCaricoFromRegistroRichieste(HashMap<String, String> input) {
        List<RegistroRichieste> lista = new ArrayList<RegistroRichieste>();
        try {
            String sql =
                "SELECT CF_PRESA_IN_CARICO, ID_RICHIESTA " +
                "FROM REGISTRO_RICHIESTE a, AMBITO_APPLICATIVO b " +
                "WHERE CF_UTENTE = ?1 AND a.CODICE_AMBITO = b.CODICE_AMBITO " +
                "AND b.SINCRONIZZATA_CAU = 'NO' AND a.STATO_RICHIESTA = 'ES-P' " +
                "AND a.CF_PRESA_IN_CARICO IS NOT NULL";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("userId"));
            List<Object[]> rows = q.getResultList();
            for (Object[] row : rows) {
                RegistroRichieste r = new RegistroRichieste();
                r.setCfPresaInCarico(row[0] != null ? row[0].toString() : null);
                r.setIdRichiesta(row[1] != null ? ((Number) row[1]).intValue() : 0);
                lista.add(r);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo selectCFPresaINCaricoFromRegistroRichieste", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Utenti selectCFbyCFPresaInCaricoFromUtenti(String cfPresaInCarico) {
        try {
            String sql =
                "SELECT CODICE_FISCALE FROM UTENTI WHERE CODICE_FISCALE = ?1 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, cfPresaInCarico);
            Object result = q.getSingleResult();
            Utenti u = new Utenti();
            u.setCodiceFiscale(result != null ? result.toString() : null);
            return u;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo selectCFbyCFPresaInCaricoFromUtenti", e);
            throw new RuntimeException(e);
        }
    }

    public RelazioneCdRUfficioStrut strutturaOld(HashMap<String, String> input) {
        try {
            String sql =
                "SELECT CODICE_STRUTTURA FROM RELAZIONE_CDR_UFFICIO_STRUT " +
                "WHERE CODICE_CDR = ?1 AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("oldCdR"));
            Object result = q.getSingleResult();
            RelazioneCdRUfficioStrut r = new RelazioneCdRUfficioStrut();
            r.setCodiceStruttura(result != null ? result.toString() : null);
            return r;
        } catch (NoResultException e) {
            return new RelazioneCdRUfficioStrut();
        } catch (Exception e) {
            log.error("Errore nel metodo strutturaOld", e);
            throw new RuntimeException(e);
        }
    }

    public RelazioneCdRUfficioStrut strutturaNew(HashMap<String, String> input) {
        try {
            String sql =
                "SELECT CODICE_STRUTTURA FROM RELAZIONE_CDR_UFFICIO_STRUT " +
                "WHERE CODICE_CDR = ?1 AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("orgName"));
            Object result = q.getSingleResult();
            RelazioneCdRUfficioStrut r = new RelazioneCdRUfficioStrut();
            r.setCodiceStruttura(result != null ? result.toString() : null);
            return r;
        } catch (NoResultException e) {
            return new RelazioneCdRUfficioStrut();
        } catch (Exception e) {
            log.error("Errore nel metodo strutturaNew", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getRuoliGestoriOperatori(String cf) {
        List<String> lista = new ArrayList<String>();
        try {
            Query q = entityManager.createNativeQuery(
                "SELECT CODICE_APPLICAZIONE FROM GESTORI_OPERATORI " +
                "WHERE CODICE_FISCALE = ?1 AND DATA_FINE_VAL IS NULL");
            q.setParameter(1, cf);
            List<Object> rows = q.getResultList();
            for (Object row : rows) {
                if (row != null) lista.add(row.toString());
            }
        } catch (Exception e) {
            log.error("Errore nel metodo getRuoliGestoriOperatori", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insertAuditOperazioni(HashMap<String, String> input) {
        try {
            String sql =
                "INSERT INTO AUDIT_OPERAZIONI (ID_AUDIT, CODICE_FISCALE, CDR_AMMINISTRATORE, DATA_ORA, " +
                "TABELLA_AGGIORNATA, TESTO) VALUES (PROG_AUDIT.nextval, null, null, SYSDATE, ?1, ?2)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("tabellaAggiornata"));
            q.setParameter(2, input.get("testoAudit"));
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo insertAuditOperazioni", e);
            throw new RuntimeException(e);
        }
    }
 
    public Integer selectMaxIDAudit() {
        try {
            Query q = entityManager.createNativeQuery("SELECT MAX(ID_AUDIT) FROM AUDIT_OPERAZIONI");
            Object result = q.getSingleResult();
            return result != null ? ((Number) result).intValue() : 0;
        } catch (Exception e) {
            log.error("Errore nel metodo selectMaxIDAudit", e);
            throw new RuntimeException(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateGestoreOperatore(HashMap<String, String> input) {
        try {
            String sql =
                "UPDATE GESTORI_OPERATORI SET DATA_FINE_VAL = SYSDATE, ID_AUDIT_FINE = ?1, " +
                "CODICE_FISCALE_FINE = null " +
                "WHERE CODICE_CDR = ?2 AND CODICE_FISCALE = ?3 " +
                "AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, input.get("idAuditFine"));
            q.setParameter(2, input.get("oldCdR"));
            q.setParameter(3, input.get("userId"));
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo updateGestoreOperatore", e);
            throw new RuntimeException(e);
        }
    }

    private String join(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
