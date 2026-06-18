package it.finanze.siga.dao.impl;

import it.finanze.scheduler.bean.CoerenzaPropertiesBean;
import it.finanze.scheduler.bean.EventoBean;
import it.finanze.scheduler.bean.ProfiliAttiviUtente;
import it.finanze.scheduler.bean.ProfiloCM;
import it.finanze.scheduler.bean.RegistroRichiesteBatchBean;
import it.finanze.scheduler.bean.RichiedenteCdR;
import it.finanze.scheduler.bean.RichiestaBatchBean;
import it.finanze.scheduler.bean.RichiestaBatchDaElaborareBean;
import it.finanze.scheduler.bean.RichiestaAbilitazioneDisabilitazioneBean;
import it.finanze.scheduler.bean.Ufficio;
import it.finanze.scheduler.bean.UtenteInVisibilitaCM;
import it.finanze.siga.bean.AllegatoCaricamentoMassivoEntity;
import it.finanze.siga.bean.CaricamentoMassivoEntity;
import it.finanze.siga.bean.CostantiSiga;
import it.finanze.siga.bean.FileAbilitazioneBean;
import it.finanze.siga.finder.DocumentoFinder;
import it.finanze.siga.finder.InterrogazioneCaricamentiFinder;
import it.finanze.siga.util.bean.UtenteBean;

import java.sql.Blob;
import java.text.SimpleDateFormat;
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

import it.finanze.siga.util.Logged;

@Logged
@Stateless
@Slf4j
@Named("BatchRichiesteCaricamentiMassiviDao")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BatchRichiesteCaricamentiMassiviDao  extends SigaDaoImpl{

    private static final Logger log = Logger.getLogger(BatchRichiesteCaricamentiMassiviDao.class);

    @PersistenceContext
    private EntityManager entityManager;

public Long getSequenceFromRichiesteBatch() {
        try {
            Query q = entityManager.createNativeQuery("SELECT SEQUENCE_RICH_BATCH.nextval FROM dual");
            return ((Number) q.getSingleResult()).longValue();
        } catch (Exception e) {
            log.error("Errore nel metodo getSequenceFromRichiesteBatch", e);
            throw new RuntimeException(e);
        }
    }

    public Long getIdRichFromSequenceIdRichiesta() {
        try {
            Query q = entityManager.createNativeQuery("SELECT ID_RICHIESTA.nextval FROM dual");
            return ((Number) q.getSingleResult()).longValue();
        } catch (Exception e) {
            log.error("Errore nel metodo getIdRichFromSequenceIdRichiesta", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<CaricamentoMassivoEntity> getListaCaricamentiPerControlli(InterrogazioneCaricamentiFinder finder) {
        List<CaricamentoMassivoEntity> lista = new ArrayList<CaricamentoMassivoEntity>();
        try {
            StringBuilder sql = new StringBuilder(
                "SELECT CMR.ID_CARICAMENTO, TO_CHAR(CMR.DATA_ORA_CARICAMENTO, 'DD/MM/YYYY') AS DATA_CARICAMENTO, " +
                "CMR.DESCRIZIONE_RICHIESTA, CMR.STATO_RICHIESTA, " +
                "UT.CODICE_FISCALE AS CF_RICHIEDENTE, UT.NOME AS NOME_RICHIEDENTE, UT.COGNOME AS COGNOME_RICHIEDENTE, " +
                "UT2.CODICE_FISCALE AS CF_AUTOR, UT2.NOME AS NOME_AUTOR, UT2.COGNOME AS COGNOME_AUTOR, " +
                "CMR.CODICE_AMBITO, AA.DESCRIZIONE AS DESCR_AMBITO, CMS.DESCRIZIONE AS DESCR_STATO, CMR.FILE_ABILITAZIONI " +
                "FROM CM_RICHIESTA CMR, UTENTI UT, UTENTI UT2, AMBITO_APPLICATIVO AA, CM_STATI CMS " +
                "WHERE DATA_ORA_CANCELLAZIONE IS NULL AND DATA_ORA_CONTROLLI_FORMALI IS NULL"
            );
            int paramIdx = 1;
            if (finder.getIdCaricamento() != null && !finder.getIdCaricamento().isEmpty()) {
                sql.append(" AND CMR.ID_CARICAMENTO = ?").append(paramIdx++);
            }
            sql.append(" AND CMR.CF_RICHIEDENTE = UT.CODICE_FISCALE")
               .append(" AND CMR.CF_AUTORIZZATORE_I_LIV = UT2.CODICE_FISCALE")
               .append(" AND CMR.STATO_RICHIESTA = CMS.STATO")
               .append(" AND CMR.CODICE_AMBITO = AA.CODICE_AMBITO")
               .append(" AND (UT.DATA_FINE_VAL IS NULL OR UT.DATA_FINE_VAL > SYSDATE)")
               .append(" ORDER BY CMR.ID_CARICAMENTO ASC");
            Query q = entityManager.createNativeQuery(sql.toString());
            int p = 1;
            if (finder.getIdCaricamento() != null && !finder.getIdCaricamento().isEmpty()) {
                q.setParameter(p++, finder.getIdCaricamento());
            }
            List<Object[]> rows = q.getResultList();
            for (Object[] row : rows) {
                CaricamentoMassivoEntity entity = new CaricamentoMassivoEntity();
                entity.setIdCaricamento(row[0] != null ? row[0].toString() : null);
                entity.setDataCaricamento(row[1] != null ? row[1].toString() : null);
                entity.setDescrizioneRichiestaCaricamento(row[2] != null ? row[2].toString() : null);
                entity.setStatoRichiestaCaricamento(row[3] != null ? row[3].toString() : null);
                entity.setCfRichiedente(row[4] != null ? row[4].toString() : null);
                entity.setNomeRichiedente(row[5] != null ? row[5].toString() : null);
                entity.setCognomeRichiedente(row[6] != null ? row[6].toString() : null);
                entity.setCfAutorizzatore(row[7] != null ? row[7].toString() : null);
                entity.setNomeAutorizzatore(row[8] != null ? row[8].toString() : null);
                entity.setCognomeAutorizzatore(row[9] != null ? row[9].toString() : null);
                entity.setIdAmbito(row[10] != null ? row[10].toString() : null);
                entity.setDescrizioneAmbito(row[11] != null ? row[11].toString() : null);
                entity.setDescrStatoRichiestaCaricamento(row[12] != null ? row[12].toString() : null);
                entity.setbFileAbilitazioni(row[13] != null ? (byte[]) row[13] : null);
                /*if (row[13] != null) {
                    try {
                        Blob blob = (Blob) row[13];
                        entity.setbFileAbilitazioni(blob.getBytes(1, (int) blob.length()));
                    } catch (Exception ex) {
                        log.error("Errore lettura BLOB FILE_ABILITAZIONI", ex);
                        throw new RuntimeException(ex);
                    }
                }*/
                lista.add(entity);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            log.error("Errore nel metodo getListaCaricamentiPerControlli", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<ProfiloCM> getProfiliCM(String codiceProfilo) {
        List<ProfiloCM> lista = new ArrayList<ProfiloCM>();
        try {
            String sql =
                "SELECT PR.CODICE_PROFILO, PR.CODICE_APPLICAZIONE, PR.EXPLICIT_ENTITLEMENT, APP.CODICE_AMBITO_APPLICATIVO " +
                "FROM PROFILO PR, APPLICAZIONI APP " +
                "WHERE PR.CODICE_PROFILO = ?1 AND PR.CODICE_APPLICAZIONE = APP.CODICE_APPLICAZIONE";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, codiceProfilo);
            List<Object[]> rows = q.getResultList();
            for (Object[] row : rows) {
                ProfiloCM profilo = new ProfiloCM();
                profilo.setCodiceProfilo(row[0] != null ? row[0].toString() : null);
                profilo.setCodiceApplicazione(row[1] != null ? row[1].toString() : null);
                profilo.setExplicitEntitlement(row[2] != null ? row[2].toString() : null);
                profilo.setCodiceAmbito(row[3] != null ? row[3].toString() : null);
                lista.add(profilo);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo getProfiliCM", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Ufficio getDatiUfficioCM(String codiceFiscale) {
        try {
            String sql =
                "SELECT RCUS.CODICE_UFFICIO, UT.CODICE_CDR " +
                "FROM UTENTI UT, RELAZIONE_CDR_UFFICIO_STRUT RCUS " +
                "WHERE UT.CODICE_FISCALE = ?1 " +
                "AND UT.CODICE_CDR = RCUS.CODICE_CDR " +
                "AND UT.DATA_FINE_VAL IS NULL AND RCUS.DATA_FINE_VAL IS NULL";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, codiceFiscale);
            Object[] row = (Object[]) q.getSingleResult();
            Ufficio ufficio = new Ufficio();
            ufficio.setCodiceUfficio(row[0] != null ? row[0].toString() : null);
            ufficio.setCodiceCdR(row[1] != null ? row[1].toString() : null);
            return ufficio;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getDatiUfficioCM", e);
            throw new RuntimeException(e);
        }
    }

    public String getIdRegistroRichiestaCM(HashMap<String, String> input) {
        try {
            String idRichVis = (String) input.get("idRichiestaVisibilita");
            StringBuilder sql = new StringBuilder(
                "SELECT RR.ID_RICHIESTA FROM PROFILI_RICHIESTA PR, REGISTRO_RICHIESTE RR " +
                "WHERE CF_UTENTE = ?1 AND RR.ID_RICHIESTA = PR.ID_RICHIESTA AND CODICE_PROFILO = ?2"
            );
            if (idRichVis != null && !idRichVis.isEmpty()) {
                sql.append(" AND ID_RICHIESTA_VISIBILITA = ?3");
            } else {
                sql.append(" AND (ID_RICHIESTA_VISIBILITA IS NULL OR ID_RICHIESTA_VISIBILITA = 0)");
            }
            sql.append(" AND RR.DATA_ESITO_FINALE IS NULL AND RR.DATA_CHIUSURA_RICHIESTA IS NULL");
            Query q = entityManager.createNativeQuery(sql.toString());
            q.setParameter(1, (String) input.get("codFiscale"));
            q.setParameter(2, (String) input.get("codProfilo"));
            if (idRichVis != null && !idRichVis.isEmpty()) {
                q.setParameter(3, idRichVis);
            }
            Object result = q.getSingleResult();
            return result != null ? result.toString() : null;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getIdRegistroRichiestaCM", e);
            throw new RuntimeException(e);
        }
    }

    public UtenteInVisibilitaCM getUtenteVisibilitaCM(HashMap<String, String> input) {
        try {
            String sql =
                "SELECT UV.ID_RICHIESTA_VISIBILITA, RCUS.CODICE_UFFICIO " +
                "FROM UTENTE_IN_VISIBILITA UV, RELAZIONE_CDR_UFFICIO_STRUT RCUS " +
                "WHERE UV.CF_UTENTE = ?1 AND UV.CODICE_CDR = ?2 " +
                "AND RCUS.CODICE_CDR = UV.CODICE_CDR " +
                "AND UV.DATA_INIZIO_VAL IS NOT NULL AND UV.DATA_REVOCA IS NULL " +
                "AND (UV.DATA_FINE_VAL IS NULL OR UV.DATA_FINE_VAL > SYSDATE) " +
                "AND (RCUS.DATA_FINE_VAL IS NULL OR UV.DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, (String) input.get("codFiscale"));
            q.setParameter(2, (String) input.get("codCdr"));
            Object[] row = (Object[]) q.getSingleResult();
            UtenteInVisibilitaCM utente = new UtenteInVisibilitaCM();
            utente.setIdRichiestaVisibilita(row[0] != null ? row[0].toString() : null);
            utente.setCodiceUfficio(row[1] != null ? row[1].toString() : null);
            return utente;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getUtenteVisibilitaCM", e);
            throw new RuntimeException(e);
        }
    }

    public String getProfiloAssegnatoCM(HashMap<String, String> input) {
        try {
            String sql =
                "SELECT PROFILO_ASSEGNATO FROM PROFILI_UTENTE_IN_VISIBILITA " +
                "WHERE CF_UTENTE = ?1 AND CODICE_PROFILO = ?2 AND ID_RICHIESTA_VISIBILITA = ?3";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, (String) input.get("codFiscale"));
            q.setParameter(2, (String) input.get("codProfilo"));
            q.setParameter(3, (String) input.get("idRichiestaVisibilita"));
            Object result = q.getSingleResult();
            return result != null ? result.toString() : null;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getProfiloAssegnatoCM", e);
            throw new RuntimeException(e);
        }
    }

    public ProfiliAttiviUtente getProfiloAttivoUtenteCM(HashMap<String, String> input) {
        try {
            String idRichVis = (String) input.get("idRichiestaVisibilita");
            StringBuilder sql = new StringBuilder(
                "SELECT DISTINCT CODICE_UFFICIO, CODICE_CDR FROM PROFILI_ATTIVI_UTENTE " +
                "WHERE CF_UTENTE = ?1 AND CODICE_PROFILO = ?2 AND CODICE_ROLE_GROUP = ?3"
            );
            if (idRichVis != null && !idRichVis.isEmpty()) {
                sql.append(" AND ID_RICHIESTA_VISIBILITA = ?4");
            } else {
                sql.append(" AND (ID_RICHIESTA_VISIBILITA IS NULL OR ID_RICHIESTA_VISIBILITA = '0')");
            }
            sql.append(" AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)");
            Query q = entityManager.createNativeQuery(sql.toString());
            q.setParameter(1, (String) input.get("codFiscale"));
            q.setParameter(2, (String) input.get("codProfilo"));
            q.setParameter(3, (String) input.get("codRoleGroup"));
            if (idRichVis != null && !idRichVis.isEmpty()) {
                q.setParameter(4, idRichVis);
            }
            Object[] row = (Object[]) q.getSingleResult();
            ProfiliAttiviUtente pau = new ProfiliAttiviUtente();
            pau.setCodiceUfficio(row[0] != null ? row[0].toString() : null);
            pau.setCodiceCdR(row[1] != null ? row[1].toString() : null);
            return pau;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getProfiloAttivoUtenteCM", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getCodiciAppCM(HashMap<String, String> input) {
        List<String> lista = new ArrayList<String>();
        try {
            String azione = (String) input.get("azione");
            StringBuilder sql = new StringBuilder(
                "SELECT DISTINCT RAPRG.CODICE_APPLICAZIONE " +
                "FROM RELAZIONE_APPL_PROF_ROLE_GROUP RAPRG, APPLICAZIONI APP " +
                "WHERE RAPRG.CODICE_PROFILO = ?1 AND RAPRG.CODICE_ROLE_GROUP = ?2"
            );
            if ("ABILITAZIONE".equals(azione)) {
                sql.append(" AND (RAPRG.DATA_FINE_VAL IS NULL OR RAPRG.DATA_FINE_VAL > SYSDATE)");
            }
            Query q = entityManager.createNativeQuery(sql.toString());
            q.setParameter(1, (String) input.get("codProfilo"));
            q.setParameter(2, (String) input.get("codRoleGroup"));
            List<Object> rows = q.getResultList();
            for (Object row : rows) {
                if (row != null) lista.add(row.toString());
            }
        } catch (Exception e) {
            log.error("Errore nel metodo getCodiciAppCM", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    public String getProfDaProfiloAttivoCM(HashMap<String, String> input) {
        try {
            String sql =
                "SELECT DISTINCT CODICE_PROFILO FROM PROFILI_ATTIVI_UTENTE " +
                "WHERE CF_UTENTE = ?1 AND CODICE_PROFILO = ?2 " +
                "AND (ID_RICHIESTA_VISIBILITA IS NULL OR ID_RICHIESTA_VISIBILITA = '0')";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, (String) input.get("codFiscale"));
            q.setParameter(2, (String) input.get("codProfilo"));
            Object result = q.getSingleResult();
            return result != null ? result.toString() : null;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getProfiloDaProfiloAttivoCM", e);
            throw new RuntimeException(e);
        }
    }

    public String getProfiloEcaPadreInPau(HashMap<String,String> input) {
        try {
            String idRichVis = (String) input.get("idRichiestaVisibilita");
            StringBuilder sql = new StringBuilder(
                "SELECT * FROM (SELECT CODICE_PROFILO FROM PROFILI_ATTIVI_UTENTE " +
                "WHERE CF_UTENTE = ?1 AND CODICE_PROFILO = ?2"
            );
            if (idRichVis != null && !idRichVis.isEmpty()) {
                sql.append(" AND ID_RICHIESTA_VISIBILITA = ?3");
            }
            sql.append(") WHERE ROWNUM = 1");
            Query q = entityManager.createNativeQuery(sql.toString());
            q.setParameter(1, (String) input.get("codFiscale"));
            q.setParameter(2, (String) input.get("codProfiloPadre"));
            if (idRichVis != null && !idRichVis.isEmpty()) {
                q.setParameter(3, idRichVis);
            }
            Object result = q.getSingleResult();
            return result != null ? result.toString() : null;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getProfiloEcaPadreInPau", e);
            throw new RuntimeException(e);
        }
    }

    public Integer countProfiliAttiviUtenteEca(HashMap<String, Object> input) {
        try {
            String idRichVis = (String) input.get("idRichiestaVisibilita");
            StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) FROM PROFILI_ATTIVI_UTENTE " +
                "WHERE CF_UTENTE = ?1 AND UPPER(CODICE_PROFILO) LIKE '%'||UPPER(?2)||'%'"
            );
            if (idRichVis != null && !idRichVis.isEmpty()) {
                sql.append(" AND ID_RICHIESTA_VISIBILITA = ?3");
            } else {
                sql.append(" AND (ID_RICHIESTA_VISIBILITA IS NULL OR ID_RICHIESTA_VISIBILITA = 0)");
            }
            Query q = entityManager.createNativeQuery(sql.toString());
            q.setParameter(1, (String) input.get("codFiscale"));
            q.setParameter(2, (String) input.get("codProfilo"));
            if (idRichVis != null && !idRichVis.isEmpty()) {
                q.setParameter(3, idRichVis);
            }
            return ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            log.error("Errore nel metodo countProfiliAttiviUtenteEca", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getCodProfiloDaProfiliAttiviUtenteEca(HashMap<String, String> input) {
        List<String> lista = new ArrayList<String>();
        try {
            String idRichVis = (String) input.get("idRichiestaVisibilita");
            StringBuilder sql = new StringBuilder(
                "SELECT CODICE_PROFILO FROM PROFILI_ATTIVI_UTENTE " +
                "WHERE CF_UTENTE = ?1 AND UPPER(CODICE_PROFILO) LIKE '%'||UPPER(?2)||'%'"
            );
            if (idRichVis != null && !idRichVis.isEmpty()) {
                sql.append(" AND ID_RICHIESTA_VISIBILITA = ?3");
            } else {
                sql.append(" AND (ID_RICHIESTA_VISIBILITA IS NULL OR ID_RICHIESTA_VISIBILITA = 0)");
            }
            Query q = entityManager.createNativeQuery(sql.toString());
            q.setParameter(1, (String) input.get("codFiscale"));
            q.setParameter(2, (String) input.get("codProfilo"));
            if (idRichVis != null && !idRichVis.isEmpty()) {
                q.setParameter(3, idRichVis);
            }
            List<Object> rows = q.getResultList();
            for (Object row : rows) {
                if (row != null) lista.add(row.toString());
            }
        } catch (Exception e) {
            log.error("Errore nel metodo getCodProfiloDaProfiliAttiviUtenteEca", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    public Integer countProfiliEca(InterrogazioneCaricamentiFinder finder) {
        try {
            StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) FROM CM_FILE_ABILITAZIONI " +
                "WHERE ID_CARICAMENTO = ?1 AND CODICE_FISCALE = ?2 " +
                "AND CODICE_ROLE_GROUP = ?3 AND CODICE_PROFILO = ?4"
            );
            int paramIdx = 5;
            if (finder.getCodiceCdr() != null && !finder.getCodiceCdr().isEmpty()) {
                sql.append(" AND CODICE_CDR = ?").append(paramIdx++);
            } else {
                sql.append(" AND CODICE_CDR IS NULL");
            }
            Query q = entityManager.createNativeQuery(sql.toString());
            q.setParameter(1, finder.getIdCaricamento());
            q.setParameter(2, finder.getCodiceFiscaleUtente());
            q.setParameter(3, finder.getCodiceRoleGroup());
            q.setParameter(4, finder.getCodiceProfilo());
            if (finder.getCodiceCdr() != null && !finder.getCodiceCdr().isEmpty()) {
                q.setParameter(5, finder.getCodiceCdr());
            }
            return ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            log.error("Errore nel metodo countProfiliEca", e);
            throw new RuntimeException(e);
        }
    }

    public void inserisciRichiesteCaricamentoMassivo(
            List<RichiestaAbilitazioneDisabilitazioneBean> listaRichiesteCaricamento,
            List<FileAbilitazioneBean> listaFileAbilitazione) {
        try {
            if (listaRichiesteCaricamento != null) {
                for (RichiestaAbilitazioneDisabilitazioneBean bean : listaRichiesteCaricamento) {
                    inserisciScadenzeRichiestaBatch(bean);
                }
            }
            if (listaFileAbilitazione != null) {
                for (FileAbilitazioneBean bean : listaFileAbilitazione) {
                    inserisciFileAbilitazione(bean);
                }
            }
        } catch (Exception e) {
            log.error("Errore nel metodo inserisciRichiesteCaricamentoMassivo", e);
            throw new RuntimeException(e);
        }
    }

    private void inserisciScadenzeRichiestaBatch(RichiestaAbilitazioneDisabilitazioneBean bean) {
        try {
            StringBuilder cols = new StringBuilder("INSERT INTO RICHIESTE_BATCH (CF, AZIONE, SEQUENCE");
            StringBuilder vals = new StringBuilder("VALUES(?1, ?2, ?3");
            int idx = 4;
            if (bean.getIdRichiesta() != 0) {
                cols.append(", ID_RICHIESTA_GENERATA"); vals.append(", ?").append(idx++);
            }
            if (bean.getEvento() != null && !bean.getEvento().isEmpty()) {
                cols.append(", EVENTO"); vals.append(", ?").append(idx++);
            }
            if (bean.getDataEvento() != null) {
                cols.append(", DATA_EVENTO"); vals.append(", ?").append(idx++);
            }
            if (bean.getDataCreazione() != null) {
                cols.append(", DATA_CREAZIONE"); vals.append(", ?").append(idx++);
            }
            if (bean.getCdrVisibilita() != null && !bean.getCdrVisibilita().isEmpty()) {
                cols.append(", CODICE_CDR_VISIBILITA"); vals.append(", ?").append(idx++);
            }
            if (bean.getRoleGroup() != null && !bean.getRoleGroup().isEmpty()) {
                cols.append(", CODICE_ROLE_GROUP"); vals.append(", ?").append(idx++);
            }
            if (bean.getCodiceProfilo() != null && !bean.getCodiceProfilo().isEmpty()) {
                cols.append(", CODICE_PROFILO"); vals.append(", ?").append(idx++);
            }
            if (bean.getCodiceUfficio() != null && !bean.getCodiceUfficio().isEmpty()) {
                cols.append(", CODICE_UFFICIO"); vals.append(", ?").append(idx++);
            }
            if (bean.getCodiceCdr() != null && !bean.getCodiceCdr().isEmpty()) {
                cols.append(", CODICE_CDR"); vals.append(", ?").append(idx++);
            }
            if (bean.getIdRichiestaVisibilita() != 0) {
                cols.append(", ID_RICHIESTA_VISIBILITA"); vals.append(", ?").append(idx++);
            }
            if (bean.getTipoUtente() != null && !bean.getTipoUtente().isEmpty()) {
                cols.append(", TIPO_UTENTE"); vals.append(", ?").append(idx++);
            }
            if (bean.getDataScadenza() != null) {
                cols.append(", DATA_SCADENZA"); vals.append(", ?").append(idx++);
            }
            if (bean.getIdRichiestaPau() != null) {
                cols.append(", ID_RICHIESTA_PAU"); vals.append(", ?").append(idx++);
            }
            if (bean.getFlagCancellazioneUtenteEsterno() != null && !bean.getFlagCancellazioneUtenteEsterno().isEmpty()) {
                cols.append(", CANCELLAZIONE_UTENTE_ESTERNO"); vals.append(", ?").append(idx++);
            }
            cols.append(") ");
            vals.append(")");
            Query q = entityManager.createNativeQuery(cols.toString() + vals.toString());
            int p = 1;
            q.setParameter(p++, bean.getCodiceFiscale());
            q.setParameter(p++, bean.getAzione());
            q.setParameter(p++, bean.getSequence());
            if (bean.getIdRichiesta() != 0) q.setParameter(p++, bean.getIdRichiesta());
            if (bean.getEvento() != null && !bean.getEvento().isEmpty()) q.setParameter(p++, bean.getEvento());
            if (bean.getDataEvento() != null) q.setParameter(p++, bean.getDataEvento());
            if (bean.getDataCreazione() != null) q.setParameter(p++, bean.getDataCreazione());
            if (bean.getCdrVisibilita() != null && !bean.getCdrVisibilita().isEmpty()) q.setParameter(p++, bean.getCdrVisibilita());
            if (bean.getRoleGroup() != null && !bean.getRoleGroup().isEmpty()) q.setParameter(p++, bean.getRoleGroup());
            if (bean.getCodiceProfilo() != null && !bean.getCodiceProfilo().isEmpty()) q.setParameter(p++, bean.getCodiceProfilo());
            if (bean.getCodiceUfficio() != null && !bean.getCodiceUfficio().isEmpty()) q.setParameter(p++, bean.getCodiceUfficio());
            if (bean.getCodiceCdr() != null && !bean.getCodiceCdr().isEmpty()) q.setParameter(p++, bean.getCodiceCdr());
            if (bean.getIdRichiestaVisibilita() != 0) q.setParameter(p++, bean.getIdRichiestaVisibilita());
            if (bean.getTipoUtente() != null && !bean.getTipoUtente().isEmpty()) q.setParameter(p++, bean.getTipoUtente());
            if (bean.getDataScadenza() != null) q.setParameter(p++, bean.getDataScadenza());
            if (bean.getIdRichiestaPau() != null) q.setParameter(p++, bean.getIdRichiestaPau());
            if (bean.getFlagCancellazioneUtenteEsterno() != null && !bean.getFlagCancellazioneUtenteEsterno().isEmpty()) q.setParameter(p++, bean.getFlagCancellazioneUtenteEsterno());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo inserisciScadenzeRichiestaBatch", e);
            throw new RuntimeException(e);
        }
    }

    public void inserisciFileAbilitazione(FileAbilitazioneBean bean) {
        try {
            StringBuilder cols = new StringBuilder("INSERT INTO CM_FILE_ABILITAZIONI (ID_CARICAMENTO, STATO");
            StringBuilder vals = new StringBuilder("VALUES(?1, ?2");
            int idx = 3;
            if (bean.getMotivazioneScarto() != null && !bean.getMotivazioneScarto().isEmpty()) {
                cols.append(", MOTIVAZIONE_SCARTO"); vals.append(", ?").append(idx++);
            }
            if (bean.getCodiceFiscale() != null && !bean.getCodiceFiscale().isEmpty()) {
                cols.append(", CODICE_FISCALE"); vals.append(", ?").append(idx++);
            }
            if (bean.getCodiceRoleGroup() != null && !bean.getCodiceRoleGroup().isEmpty()) {
                cols.append(", CODICE_ROLE_GROUP"); vals.append(", ?").append(idx++);
            }
            if (bean.getCodiceProfilo() != null && !bean.getCodiceProfilo().isEmpty()) {
                cols.append(", CODICE_PROFILO"); vals.append(", ?").append(idx++);
            }
            if (bean.getCodiceCdr() != null && !bean.getCodiceCdr().isEmpty()) {
                cols.append(", CODICE_CDR"); vals.append(", ?").append(idx++);
            }
            if (bean.getAzione() != null && !bean.getAzione().isEmpty()) {
                cols.append(", AZIONE"); vals.append(", ?").append(idx++);
            }
            if (bean.getDataScadenza() != null) {
                cols.append(", DATA_SCADENZA"); vals.append(", ?").append(idx++);
            }
            cols.append(") ");
            vals.append(")");
            Query q = entityManager.createNativeQuery(cols.toString() + vals.toString());
            int p = 1;
            q.setParameter(p++, bean.getIdCaricamento());
            q.setParameter(p++, bean.getStato());
            if (bean.getMotivazioneScarto() != null && !bean.getMotivazioneScarto().isEmpty()) q.setParameter(p++, bean.getMotivazioneScarto());
            if (bean.getCodiceFiscale() != null && !bean.getCodiceFiscale().isEmpty()) q.setParameter(p++, bean.getCodiceFiscale());
            if (bean.getCodiceRoleGroup() != null && !bean.getCodiceRoleGroup().isEmpty()) q.setParameter(p++, bean.getCodiceRoleGroup());
            if (bean.getCodiceProfilo() != null && !bean.getCodiceProfilo().isEmpty()) q.setParameter(p++, bean.getCodiceProfilo());
            if (bean.getCodiceCdr() != null && !bean.getCodiceCdr().isEmpty()) q.setParameter(p++, bean.getCodiceCdr());
            if (bean.getAzione() != null && !bean.getAzione().isEmpty()) q.setParameter(p++, bean.getAzione());
            if (bean.getDataScadenza() != null) q.setParameter(p++, bean.getDataScadenza());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo inserisciFileAbilitazione", e);
            throw new RuntimeException(e);
        }
    }

    public void updateControlloRichiestaCaricamentoMassivo(CaricamentoMassivoEntity entity) {
        try {
            String sql =
                "UPDATE CM_RICHIESTA SET DATA_ORA_CONTROLLI_FORMALI = CURRENT_TIMESTAMP, " +
                "STATO_RICHIESTA = ?1, NUMERO_RECORD_FILE = ?2, NUMERO_RECORD_SCARTATI = ?3, " +
                "NUMERO_RECORD_VALIDI = ?4, NUMERO_RECORD_ECA_AGGIUNTI = ?5, SEQUENCE_RICHIESTE_GENERATE = ?6 " +
                "WHERE ID_CARICAMENTO = ?7";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, entity.getStatoRichiesta());
            q.setParameter(2, entity.getNumRecordFile());
            q.setParameter(3, entity.getNumRecordScartati());
            q.setParameter(4, entity.getNumRecordValidi());
            q.setParameter(5, entity.getNumRecordEcaAggiunti());
            q.setParameter(6, entity.getSequenceRichiesteGenerate());
            q.setParameter(7, entity.getIdCaricamento());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo updateControlloRichiestaCaricamentoMassivo", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<CaricamentoMassivoEntity> getListaCaricamentiControllati(InterrogazioneCaricamentiFinder finder) {
        List<CaricamentoMassivoEntity> lista = new ArrayList<CaricamentoMassivoEntity>();
        try {
            StringBuilder sql = new StringBuilder(
                "SELECT DISTINCT ID_CARICAMENTO, SEQUENCE_RICHIESTE_GENERATE, CODICE_AMBITO, " +
                "NOTE_RICHIEDENTE_OPERATORE, CF_RICHIEDENTE, CF_AUTORIZZATORE_I_LIV, DESCRIZIONE_RICHIESTA " +
                "FROM CM_RICHIESTA WHERE STATO_RICHIESTA = 'CON' AND NUMERO_RECORD_VALIDI > 0"
            );
            int paramIdx = 1;
            if (finder.getIdCaricamento() != null && !finder.getIdCaricamento().isEmpty()) {
                sql.append(" AND ID_CARICAMENTO = ?").append(paramIdx++);
            }
            sql.append(" ORDER BY SEQUENCE_RICHIESTE_GENERATE");
            Query q = entityManager.createNativeQuery(sql.toString());
            if (finder.getIdCaricamento() != null && !finder.getIdCaricamento().isEmpty()) {
                q.setParameter(1, finder.getIdCaricamento());
            }
            List<Object[]> rows = q.getResultList();
            for (Object[] row : rows) {
                CaricamentoMassivoEntity entity = new CaricamentoMassivoEntity();
                entity.setIdCaricamento(row[0] != null ? row[0].toString() : null);
                entity.setSequenceRichiesteGenerate(row[1] != null ? ((Number) row[1]).longValue() : null);
                entity.setIdAmbito(row[2] != null ? row[2].toString() : null);
                entity.setNoteRichiedente(row[3] != null ? row[3].toString() : null);
                entity.setCfRichiedente(row[4] != null ? row[4].toString() : null);
                entity.setCfAutorizzatore(row[5] != null ? row[5].toString() : null);
                entity.setDescrizioneRichiestaCaricamento(row[6] != null ? row[6].toString() : null);
                lista.add(entity);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo getListaCaricamentiControllati", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<AllegatoCaricamentoMassivoEntity> getElencoAllegatiCaricamento(InterrogazioneCaricamentiFinder finder) {
        List<AllegatoCaricamentoMassivoEntity> lista = new ArrayList<AllegatoCaricamentoMassivoEntity>();
        try {
            StringBuilder sql = new StringBuilder(
                "SELECT DOCUMENTO, TIPO, NOME_FILE, ID_ALLEGATO, ID_CARICAMENTO FROM CM_ALLEGATI " +
                "WHERE ID_CARICAMENTO = ?1"
            );
            int paramIdx = 2;
            if (finder.getTipoAllegato() != null && !finder.getTipoAllegato().isEmpty()) {
                sql.append(" AND TIPO = ?").append(paramIdx++);
            }
            Query q = entityManager.createNativeQuery(sql.toString());
            q.setParameter(1, finder.getIdCaricamento());
            if (finder.getTipoAllegato() != null && !finder.getTipoAllegato().isEmpty()) {
                q.setParameter(2, finder.getTipoAllegato());
            }
            List<Object[]> rows = q.getResultList();
            for (Object[] row : rows) {
                AllegatoCaricamentoMassivoEntity entity = new AllegatoCaricamentoMassivoEntity();
                if (row[0] != null) {
                    try {
                        Blob blob = (Blob) row[0];
                        entity.setbFileAllegato(blob.getBytes(1, (int) blob.length()));
                    } catch (Exception ex) {
                        log.error("Errore lettura BLOB DOCUMENTO", ex);
                        throw new RuntimeException(ex);
                    }
                }
                entity.setTipo(row[1] != null ? row[1].toString() : null);
                entity.setNomeFile(row[2] != null ? row[2].toString() : null);
                entity.setIdAllegato(row[3] != null ? row[3].toString() : null);
                entity.setIdCaricamento(row[4] != null ? row[4].toString() : null);
                lista.add(entity);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            log.error("Errore nel metodo getElencoAllegatiCaricamento", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    public List<RichiestaBatchBean> getListaRichiesteBatchBySequence(Long sequence) {
        List<RichiestaBatchBean> lista = new ArrayList<RichiestaBatchBean>();
        try {
            String sql =
                "SELECT DISTINCT RB.CF, RB.SEQUENCE, RB.ID_RICHIESTA_VISIBILITA, RB.CODICE_CDR_VISIBILITA " +
                "FROM RICHIESTE_BATCH RB " +
                "WHERE SEQUENCE = ?1 AND (ID_RICHIESTA_GENERATA IS NULL OR ID_RICHIESTA_GENERATA = '0')";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, sequence);
            List<Object[]> rows = q.getResultList();
            for (Object[] row : rows) {
                RichiestaBatchBean bean = new RichiestaBatchBean();
                bean.setCf(row[0] != null ? row[0].toString() : null);
                bean.setSequence(row[1] != null ? ((Number) row[1]).longValue() : 0L);
                bean.setIdRichiestaVisibilita(row[2] != null ? ((Number) row[2]).intValue() : null);
                bean.setCdrVisibilita(row[3] != null ? row[3].toString() : null);
                lista.add(bean);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo getListaRichiesteBatchBySequence", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void inserisciRegistroRichiesta(RegistroRichiesteBatchBean bean) {
        try {
            StringBuilder cols = new StringBuilder(
                "INSERT INTO REGISTRO_RICHIESTE (ID_CM, ID_RICHIESTA, TIPO_RICHIESTA, CODICE_AMBITO, CF_UTENTE, CDR_UTENTE, " +
                "CF_RICHIEDENTE, CF_RICHIEDENTE_EFFETTIVO, NOTE_RICHIEDENTE, DATA_RICHIESTA, " +
                "CF_AUTORIZZATORE_1, CF_AUTORIZZATORE_1_EFFETTIVO"
            );
            StringBuilder vals = new StringBuilder("VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12");
            int idx = 13;
            if (bean.getNotaAutorizzatore1() != null && !bean.getNotaAutorizzatore1().isEmpty()) {
                cols.append(", NOTE_AUTORIZZATORE_1"); vals.append(", ?").append(idx++);
            }
            cols.append(", STATO_RICHIESTA, RICHIEDENTE_AC, PRESA_VISIONE");
            vals.append(", ?").append(idx++).append(", ?").append(idx++).append(", ?").append(idx++);
            if (bean.getCfArchiviazione() != null && !bean.getCfArchiviazione().isEmpty()) {
                cols.append(", CF_ARCHIVIAZIONE"); vals.append(", ?").append(idx++);
            }
            if (bean.getCfArchiviazioneEffettivo() != null && !bean.getCfArchiviazioneEffettivo().isEmpty()) {
                cols.append(", CF_ARCHIVIAZIONE_EFFETTIVO"); vals.append(", ?").append(idx++);
            }
            if (bean.getDataEsitoFinale() != null) {
                cols.append(", DATA_ESITO_FINALE"); vals.append(", ?").append(idx++);
            }
            if (bean.getEsitoFinale() != null && !bean.getEsitoFinale().isEmpty()) {
                cols.append(", ESITO_FINALE"); vals.append(", ?").append(idx++);
            }
            cols.append(", AGGIORNA_SISTEMA_AUTORIZZ, CODICE_EVENTO, RICHIESTA_VISIBILE_RUOLI_SIGA, " +
                        "CANCELLA_UTENTE_ESTERNO, NOTA_GENERAZIONE_RICHIESTA, CODICE_AMBITO_AUTORIZZAZIONE, DATA_AUTORIZZAZIONE_1");
            vals.append(", ?").append(idx++).append(", ?").append(idx++).append(", ?").append(idx++)
                .append(", ?").append(idx++).append(", ?").append(idx++).append(", ?").append(idx++)
                .append(", ?").append(idx++);
            if (bean.getIdIter() != null) {
                cols.append(", ITER_ID"); vals.append(", ?").append(idx++);
            }
            cols.append(", CDR_ARCHIVIAZIONE");
            vals.append(", ?").append(idx++);
            cols.append(") ");
            vals.append(")");
            Query q = entityManager.createNativeQuery(cols.toString() + vals.toString());
            int p = 1;
            q.setParameter(p++, bean.getIdCm());
            q.setParameter(p++, bean.getIdRichiesta());
            q.setParameter(p++, bean.getTipoRichiesta());
            q.setParameter(p++, bean.getCodiceAmbito());
            q.setParameter(p++, bean.getCfUtente());
            q.setParameter(p++, bean.getCdrUtente());
            q.setParameter(p++, bean.getCfRichiedente());
            q.setParameter(p++, bean.getCfRichiedenteEffettivo());
            q.setParameter(p++, bean.getNoteRichiedente());
            q.setParameter(p++, bean.getDataRichiesta());
            q.setParameter(p++, bean.getCfAutorizzatore1());
            q.setParameter(p++, bean.getCfAutorizzatore1Effettivo());
            if (bean.getNotaAutorizzatore1() != null && !bean.getNotaAutorizzatore1().isEmpty()) q.setParameter(p++, bean.getNotaAutorizzatore1());
            q.setParameter(p++, bean.getStatoRichiesta());
            q.setParameter(p++, bean.getRichiedenteAc());
            q.setParameter(p++, bean.getPresaVisione());
            if (bean.getCfArchiviazione() != null && !bean.getCfArchiviazione().isEmpty()) q.setParameter(p++, bean.getCfArchiviazione());
            if (bean.getCfArchiviazioneEffettivo() != null && !bean.getCfArchiviazioneEffettivo().isEmpty()) q.setParameter(p++, bean.getCfArchiviazioneEffettivo());
            if (bean.getDataEsitoFinale() != null) q.setParameter(p++, bean.getDataEsitoFinale());
            if (bean.getEsitoFinale() != null && !bean.getEsitoFinale().isEmpty()) q.setParameter(p++, bean.getEsitoFinale());
            q.setParameter(p++, bean.getAggiornaSistemaAutorizzazione());
            q.setParameter(p++, bean.getCodiceEvento());
            q.setParameter(p++, bean.getRichiestaVisibileRuoliSiga());
            q.setParameter(p++, bean.getCancellaUtenteEsterno());
            q.setParameter(p++, bean.getNotaGenerazioneRichiesta());
            q.setParameter(p++, bean.getCodiceAmbitoAutorizzazione());
            q.setParameter(p++, bean.getDataAutorizzazione1());
            if (bean.getIdIter() != null) q.setParameter(p++, bean.getIdIter());
            q.setParameter(p++, bean.getCdrArchiviazione());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo inserisciRegistroRichiesta", e);
            throw new RuntimeException(e);
        }
    }

    public void inserisciFile(DocumentoFinder docFinder) {
        try {
            Query seqQ = entityManager.createNativeQuery("SELECT PROG_DOCUMENTO.nextval FROM dual");
            int progDoc = ((Number) seqQ.getSingleResult()).intValue();
            docFinder.setProgDoc(progDoc);
            String sql =
                "INSERT INTO DOCUMENTI_RICHIESTA (ID_RICHIESTA, PROG_DOCUMENTO, DOCUMENTO, NOME_FILE) " +
                "VALUES(?1, ?2, ?3, ?4)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, docFinder.getIdTab());
            q.setParameter(2, docFinder.getProgDoc());
            q.setParameter(3, docFinder.getbFile());
            q.setParameter(4, docFinder.getFileName());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo inserisciFile", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<RichiestaBatchBean> getProfiliPerRichiesta(InterrogazioneCaricamentiFinder finder) {
        List<RichiestaBatchBean> lista = new ArrayList<RichiestaBatchBean>();
        try {
            String sql =
                "SELECT DISTINCT RB.CF, RB.SEQUENCE, RB.ID_RICHIESTA_VISIBILITA, RB.CODICE_ROLE_GROUP, " +
                "RB.CODICE_PROFILO, RB.CODICE_UFFICIO, RB.CODICE_CDR, RB.DATA_SCADENZA, RB.AZIONE " +
                "FROM RICHIESTE_BATCH RB " +
                "WHERE SEQUENCE = ?1 AND ID_RICHIESTA_VISIBILITA = ?2 AND CF = ?3 " +
                "AND (ID_RICHIESTA_GENERATA IS NULL OR ID_RICHIESTA_GENERATA = '0')";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, finder.getSequence());
            q.setParameter(2, finder.getIdRichiesta());
            q.setParameter(3, finder.getCodiceFiscaleUtente());
            List<Object[]> rows = q.getResultList();
            for (Object[] row : rows) {
                RichiestaBatchBean bean = new RichiestaBatchBean();
                bean.setCf(row[0] != null ? row[0].toString() : null);
                bean.setSequence(row[1] != null ? ((Number) row[1]).longValue() : 0L);
                bean.setIdRichiestaVisibilita(row[2] != null ? ((Number) row[2]).intValue() : null);
                bean.setCodRoleGroup(row[3] != null ? row[3].toString() : null);
                bean.setCodProfilo(row[4] != null ? row[4].toString() : null);
                bean.setCodUfficio(row[5] != null ? row[5].toString() : null);
                bean.setCodCdr(row[6] != null ? row[6].toString() : null);
                if (row[7] != null) {
                    bean.setDataScadenza((java.util.Date) row[7]);
                }
                bean.setAzione(row[8] != null ? row[8].toString() : null);
                lista.add(bean);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo getProfiliPerRichiesta", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    public it.finanze.scheduler.bean.ProfiloRichiestaBean getCodApplicazione(String codiceProfilo) {
        try {
            String sql =
                "SELECT DISTINCT P.CODICE_APPLICAZIONE, A.CODICE_AMBITO_APPLICATIVO " +
                "FROM PROFILO P, APPLICAZIONI A " +
                "WHERE P.CODICE_PROFILO = ?1 AND P.CODICE_APPLICAZIONE = A.CODICE_APPLICAZIONE";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, codiceProfilo);
            Object[] row = (Object[]) q.getSingleResult();
            it.finanze.scheduler.bean.ProfiloRichiestaBean result = new it.finanze.scheduler.bean.ProfiloRichiestaBean();
            result.setCodiceApplicazione(row[0] != null ? row[0].toString() : null);
            return result;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getCodApplicazione", e);
            throw new RuntimeException(e);
        }
    }

    public String getCodCdrByCf(String cf) {
        try {
            String sql =
                "SELECT CODICE_CDR FROM UTENTI " +
                "WHERE CODICE_FISCALE = ?1 AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, cf);
            Object result = q.getSingleResult();
            return result != null ? result.toString() : null;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getCodCdrByCf", e);
            throw new RuntimeException(e);
        }
    }

    public String getCfRichAutor(String cf) {
        try {
            String sql =
                "SELECT CODICE_FISCALE_RICHIEDENTE FROM ASSOCIAZ_OPER_RICHI_AUTOR " +
                "WHERE CODICE_FISCALE_OPERATORE = ?1 AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, cf);
            Object result = q.getSingleResult();
            return result != null ? result.toString() : null;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getCfRichAutor", e);
            throw new RuntimeException(e);
        }
    }

    public RichiedenteCdR getDatiRichiedenteCdr(String cf) {
        try {
            String sql =
                "SELECT UT.CODICE_CDR, RC.CF_RICHIEDENTE " +
                "FROM UTENTI UT, RICHIEDENTE_CDR RC " +
                "WHERE UT.CODICE_FISCALE = ?1 " +
                "AND (RC.DATA_FINE_VAL IS NULL OR RC.DATA_FINE_VAL > SYSDATE) " +
                "AND (UT.DATA_FINE_VAL IS NULL OR UT.DATA_FINE_VAL > SYSDATE) " +
                "AND UT.CODICE_CDR = RC.CODICE_CDR";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, cf);
            Object[] row = (Object[]) q.getSingleResult();
            RichiedenteCdR result = new RichiedenteCdR();
            result.setCodiceCdR(row[0] != null ? row[0].toString() : null);
            result.setCfRichiedente(row[1] != null ? row[1].toString() : null);
            return result;
        } catch (NoResultException e) {
            return new RichiedenteCdR();
        } catch (Exception e) {
            log.error("Errore nel metodo getDatiRichiedenteCdr", e);
            throw new RuntimeException(e);
        }
    }

    public RichiedenteCdR getDatiResponsabileCdrSuperiore(String cdr) {
        try {
            String sql =
                "SELECT RC.CODICE_CDR, RC.CF_RESPONSABILE_HR " +
                "FROM RICHIEDENTE_CDR RC, RELAZIONE_CDR_UFFICIO_STRUT RCUSUT, CDR CDR, RELAZIONE_CDR_UFFICIO_STRUT RCUSSTR " +
                "WHERE (RC.DATA_FINE_VAL IS NULL OR RC.DATA_FINE_VAL > SYSDATE) " +
                "AND (RCUSUT.DATA_FINE_VAL IS NULL OR RCUSUT.DATA_FINE_VAL > SYSDATE) " +
                "AND CDR.CODICE_CDR = ?1 " +
                "AND CDR.CODICE_CDR_PADRE_GERARCHIA = RC.CODICE_CDR " +
                "AND CDR.CODICE_CDR = RCUSUT.CODICE_CDR " +
                "AND RCUSUT.VERTICE_STRUTTURA = RCUSSTR.VERTICE_STRUTTURA " +
                "AND CDR.CODICE_CDR_PADRE_GERARCHIA = RCUSSTR.CODICE_CDR " +
                "AND RC.CF_RESPONSABILE_HR IS NOT NULL";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, cdr);
            Object[] row = (Object[]) q.getSingleResult();
            RichiedenteCdR result = new RichiedenteCdR();
            result.setCodiceCdR(row[0] != null ? row[0].toString() : null);
            result.setCfResponsabileHR(row[1] != null ? row[1].toString() : null);
            return result;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getDatiResponsabileCdrSuperiore", e);
            throw new RuntimeException(e);
        }
    }

    public RichiedenteCdR getDatiResponsabileStruttura(String cdr) {
        try {
            String sql =
                "SELECT RC.CODICE_CDR, RC.CF_RESPONSABILE_HR " +
                "FROM RICHIEDENTE_CDR RC, RELAZIONE_CDR_UFFICIO_STRUT RCUSUT " +
                "WHERE (RC.DATA_FINE_VAL IS NULL OR RC.DATA_FINE_VAL > SYSDATE) " +
                "AND (RCUSUT.DATA_FINE_VAL IS NULL OR RCUSUT.DATA_FINE_VAL > SYSDATE) " +
                "AND RCUSUT.CODICE_CDR = ?1 " +
                "AND RCUSUT.VERTICE_STRUTTURA = RC.CODICE_CDR " +
                "AND RC.CF_RESPONSABILE_HR IS NOT NULL";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, cdr);
            Object[] row = (Object[]) q.getSingleResult();
            RichiedenteCdR result = new RichiedenteCdR();
            result.setCodiceCdR(row[0] != null ? row[0].toString() : null);
            result.setCfResponsabileHR(row[1] != null ? row[1].toString() : null);
            return result;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getDatiResponsabileStruttura", e);
            throw new RuntimeException(e);
        }
    }

    public RichiedenteCdR getDatiRichiedenteCdrByCdrVis(String cdrVis) {
        try {
            String sql =
                "SELECT RC.CF_RICHIEDENTE FROM RICHIEDENTE_CDR RC " +
                "WHERE RC.CODICE_CDR = ?1 AND (RC.DATA_FINE_VAL IS NULL OR RC.DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, cdrVis);
            Object result = q.getSingleResult();
            RichiedenteCdR bean = new RichiedenteCdR();
            bean.setCfRichiedente(result != null ? result.toString() : null);
            return bean;
        } catch (NoResultException e) {
            return new RichiedenteCdR();
        } catch (Exception e) {
            log.error("Errore nel metodo getDatiRichiedenteCdrByCdrVis", e);
            throw new RuntimeException(e);
        }
    }

    public void inserimentoProfiloRichiesta(it.finanze.scheduler.bean.ProfiloRichiestaBean bean) {
        try {
            String sql =
                "INSERT INTO PROFILI_RICHIESTA (ID_RICHIESTA, CODICE_APPLICAZIONE, CODICE_ROLE_GROUP, CODICE_PROFILO, " +
                "CODICE_UFFICIO, CODICE_CDR, TIPO_ABILITAZIONE, OPERAZIONE_SUL_PROFILO, OPERAZIONE_ESEGUITA, " +
                "DATA_SCADENZA, ID_RICHIESTA_VISIBILITA, ORIGINE_ABILITAZIONE) " +
                "VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, bean.getIdRichiesta());
            q.setParameter(2, bean.getCodiceApplicazione());
            q.setParameter(3, bean.getCodiceRoleGroup());
            q.setParameter(4, bean.getCodiceProfilo());
            q.setParameter(5, bean.getCodiceUfficio());
            q.setParameter(6, bean.getCodiceCdr());
            q.setParameter(7, bean.getTipoAbilitazione());
            q.setParameter(8, bean.getOperazioneSuProfilo());
            q.setParameter(9, bean.getOperazioneEseguita());
            q.setParameter(10, bean.getDataScadenza());
            q.setParameter(11, bean.getIdRichiestaVisibilita());
            q.setParameter(12, bean.getOrigineAbilitazione());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo inserimentoProfiloRichiesta", e);
            throw new RuntimeException(e);
        }
    }

    public void inserisciRichiestaBatchDaElaborare(RichiestaBatchDaElaborareBean bean) {
        try {
            String sql =
                "INSERT INTO RIC_BATCH_DA_ELAB (ID_RICHIESTA, PRIORITA, CODICE_EVENTO, AMBIENTE_DI_DESTINAZIONE, " +
                "AGGIORNAMENTO_AMB_DI_DEST, DATA_CREAZIONE, NUMERO_PROFILI, ORARIO_INIZIO_ELABORAZIONE, " +
                "ORARIO_FINE_ELABORAZIONE, SEQUENCE, IN_LAVORAZIONE) " +
                "VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, bean.getIdRichiesta());
            q.setParameter(2, bean.getPriorita());
            q.setParameter(3, bean.getCodiceEvento());
            q.setParameter(4, bean.getAmbienteDestinazione());
            q.setParameter(5, bean.getAggAmbienteDestinazione());
            q.setParameter(6, bean.getDataCreazione());
            q.setParameter(7, bean.getNumeroProfili());
            q.setParameter(8, bean.getOrarioInizioElab());
            q.setParameter(9, bean.getOrarioFineElab());
            q.setParameter(10, bean.getSequence());
            q.setParameter(11, bean.getInLavorazione());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo inserisciRichiestaBatchDaElaborare", e);
            throw new RuntimeException(e);
        }
    }

    public void updateGenerazioneRichiestaCaricamentoMassivo(CaricamentoMassivoEntity entity) {
        try {
            String sql =
                "UPDATE CM_RICHIESTA SET DATA_ORA_GENERAZIONE_RICHIESTE = CURRENT_TIMESTAMP, " +
                "STATO_RICHIESTA = ?1 WHERE ID_CARICAMENTO = ?2";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, entity.getStatoRichiesta());
            q.setParameter(2, entity.getIdCaricamento());
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo updateGenerazioneRichiestaCaricamentoMassivo", e);
            throw new RuntimeException(e);
        }
    }

    public void eliminaRichiesteBatch(String sequence) {
        try {
            String sql = "DELETE FROM RICHIESTE_BATCH WHERE SEQUENCE = ?1";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, sequence);
            q.executeUpdate();
        } catch (Exception e) {
            log.error("Errore nel metodo eliminaRichiesteBatch", e);
            throw new RuntimeException(e);
        }
    }

    public String getEmailAmminCaricamento(String idCaricamento) {
        try {
            String sql =
                "SELECT UT.E_MAIL FROM UTENTI UT, CM_RICHIESTA CMR " +
                "WHERE CMR.ID_CARICAMENTO = ?1 AND CMR.CF_AMMINISTRATORE = UT.CODICE_FISCALE";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, idCaricamento);
            Object result = q.getSingleResult();
            return result != null ? result.toString() : null;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getEmailAmminCaricamento", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<FileAbilitazioneBean> getListaRecordScartati(String idCaricamento) {
        List<FileAbilitazioneBean> lista = new ArrayList<FileAbilitazioneBean>();
        try {
            String sql =
                "SELECT MOTIVAZIONE_SCARTO, CODICE_FISCALE, CODICE_ROLE_GROUP, CODICE_PROFILO, CODICE_CDR, AZIONE " +
                "FROM CM_FILE_ABILITAZIONI " +
                "WHERE ID_CARICAMENTO = ?1 AND STATO = 'Scartato'";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, idCaricamento);
            List<Object[]> rows = q.getResultList();
            for (Object[] row : rows) {
                FileAbilitazioneBean bean = new FileAbilitazioneBean();
                bean.setMotivazioneScarto(row[0] != null ? row[0].toString() : null);
                bean.setCodiceFiscale(row[1] != null ? row[1].toString() : null);
                bean.setCodiceRoleGroup(row[2] != null ? row[2].toString() : null);
                bean.setCodiceProfilo(row[3] != null ? row[3].toString() : null);
                bean.setCodiceCdr(row[4] != null ? row[4].toString() : null);
                bean.setAzione(row[5] != null ? row[5].toString() : null);
                lista.add(bean);
            }
        } catch (Exception e) {
            log.error("Errore nel metodo getListaRecordScartati", e);
            throw new RuntimeException(e);
        }
        return lista;
    }

    public String getDescrizioneAmbito(String codAmbito) {
        try {
            String sql = "SELECT DESCRIZIONE FROM AMBITO_APPLICATIVO WHERE CODICE_AMBITO = ?1";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, codAmbito);
            Object result = q.getSingleResult();
            return result != null ? result.toString() : null;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getDescrizioneAmbito", e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public CoerenzaPropertiesBean selectCostantiSigaBatchCoerenza() {
        CoerenzaPropertiesBean coerenzaProperties = new CoerenzaPropertiesBean();
        try {
            String sql =
                "SELECT CS.NOME_COSTANTE, CS.VALORE_COSTANTE, CS.NOTA, CS.DATA_ULTIMA_ELABORAZIONE " +
                "FROM COSTANTI_SIGA CS";
            Query q = entityManager.createNativeQuery(sql);
            List<Object[]> rows = q.getResultList();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Object[] row : rows) {
                CostantiSiga c = new CostantiSiga();
                c.setNome(row[0] != null ? row[0].toString() : null);
                c.setValore(row[1] != null ? row[1].toString() : null);
                c.setNota(row[2] != null ? row[2].toString() : null);
                if (row[3] != null) {
                    c.setDataUltimaElaborazione((java.util.Date) row[3]);
                }
                if (c.getNome() == null) continue;
                String nome = c.getNome().trim();
                String valore = c.getValore();
                String dataStr = c.getDataUltimaElaborazione() != null ? sdf.format(c.getDataUltimaElaborazione()) : null;
                if ("BatchScadenzeProfiliObsoleti".equals(nome)) {
                    if (valore != null) coerenzaProperties.setProfiliElaborabili(valore);
                    if (dataStr != null) coerenzaProperties.setUltimaElaborazioneProfili(dataStr);
                } else if ("BatchScadenzeProfiliScadutiAdminGroup".equals(nome)) {
                    if (valore != null) coerenzaProperties.setAdminGroupElaborabili(valore);
                    if (dataStr != null) coerenzaProperties.setUltimaElaborazioneAdminGroup(dataStr);
                } else if ("BatchScadenzeProfiliScadutiOperatore".equals(nome)) {
                    if (valore != null) coerenzaProperties.setProfiliOperatoreElaborabili(valore);
                    if (dataStr != null) coerenzaProperties.setUltimaElaborazioneProfiliOperatore(dataStr);
                } else if ("BatchScadenzeVisibilitaScadute".equals(nome)) {
                    if (valore != null) coerenzaProperties.setVisibilitaElaborabili(valore);
                    if (dataStr != null) coerenzaProperties.setUltimaElaborazioneVisibilita(dataStr);
                } else if ("BatchScadenzeTermineServizioOperatoriEsterni".equals(nome)) {
                    if (valore != null) coerenzaProperties.setEsterniElaborabili(valore);
                    if (dataStr != null) coerenzaProperties.setUltimaElaborazioneEsterni(dataStr);
                } else if ("BatchScadenzeProfiliVisibilita".equals(nome)) {
                    if (valore != null) coerenzaProperties.setProfiliVisibilitaElaborabili(valore);
                    if (dataStr != null) coerenzaProperties.setUltimaElaborazioneProfiliVisibilita(dataStr);
                } else if ("emailSegnalazioniSogei".equals(nome)) {
                    if (valore != null) coerenzaProperties.setEmailSegnalazioniSogei(valore);
                } else if ("emailAgenziaSegnalaCau".equals(nome)) {
                    if (valore != null) coerenzaProperties.setEmailSegnalazioniAgenzia(valore);
                } else if ("emailAgenziaSegnalaCea".equals(nome)) {
                    if (valore != null) coerenzaProperties.setEmailSegnalazioniAgenziaCea(valore);
                } else if ("emailAgenziaSegnalaHr".equals(nome)) {
                    if (valore != null) coerenzaProperties.setEmailSegnalazioniAgenziaHr(valore);
                } else if ("ElaborazioneCm".equals(nome)) {
                    if (valore != null) coerenzaProperties.setElaborazioneCM(valore);
                } else if ("ElaborazioneCmVisibilita".equals(nome)) {
                    if (valore != null) coerenzaProperties.setElaborazioneCMVis(valore);
                } else if ("ElaborazioneCmRichiesteVisibilita".equals(nome)) {
                    if (valore != null) coerenzaProperties.setElaborazioneCMRichVis(valore);
                } else if ("BatchScadenzeDeleghe".equals(nome)) {
                    if (valore != null) coerenzaProperties.setDelegheElaborabili(valore);
                    if (dataStr != null) coerenzaProperties.setUltimaElaborazioneScadenzaDeleghe(dataStr);
                } else if ("CessazioneElaborabile".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCessazioneElaborabile(valore);
                } else if ("CoerenzaCaElaborabile".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCoerenzaCaElaborabile(valore);
                } else if ("CoerenzaCaTuttiUtenti".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCoerenzaCaTuttiUtenti(valore);
                } else if ("CoerenzaCauElaborabile".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCoerenzaCauElaborabile(valore);
                    if (dataStr != null) coerenzaProperties.setUltimaElaborazioneCoerenzaCau(dataStr);
                } else if ("CoerenzaCauTuttiUtenti".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCoerenzaCauTuttiUtenti(valore);
                } else if ("CoerenzaCodiceSecondoLivello".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCoerenzaCodiceSecondoLivello(valore);
                } else if ("CoerenzaHrElaborabile".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCoerenzaHrElaborabile(valore);
                } else if ("CoerenzaHrTuttiUtenti".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCoerenzaHrTuttiUtenti(valore);
                } else if ("CoerenzaOaNumMaxRisposteErrate".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCoerenzaOaNumMaxRisposteErrate(valore);
                } else if ("CoerenzaOaNumMaxUtenti".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCoerenzaOaNumMaxUtenti(valore);
                } else if ("CoerenzaVerificaVisibilita".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCoerenzaVerificaVisibilita(valore);
                } else if ("ElaborazioneAbilitazioniCdr".equals(nome)) {
                    if (valore != null) coerenzaProperties.setElaborazioneAbilitazCdr(valore);
                } else if ("ElaborazioneAbilitazioniOper".equals(nome)) {
                    if (valore != null) coerenzaProperties.setElaborazioneAbilitazOper(valore);
                } else if ("FlagRegime".equals(nome)) {
                    if (valore != null) coerenzaProperties.setFlagRegime(valore);
                } else if ("MobilitaElaborabile".equals(nome)) {
                    if (valore != null) coerenzaProperties.setMobilitaElaborabile(valore);
                } else if ("StatisticaAbilitazioniOperatori".equals(nome)) {
                    if (valore != null) coerenzaProperties.setStatisticaAbilitazioniOperatori(valore);
                } else if ("CoerenzaVerificaExplicitEntitlement".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCoerenzaVerificaExplicitEntitlement(valore);
                } else if ("CoerenzaAgenziaInvioEmail".equals(nome)) {
                    if (valore != null) coerenzaProperties.setCoerenzaAgenziaInvioEmail(valore);
                } else if ("RiorganizzazioniAggiornaCdrVisibilita".equals(nome)) {
                    if (valore != null) coerenzaProperties.setElaborazioneRiorganizzazioneAggiornaCdrVisibilita(valore);
                } else if ("RiorganizzazioniAggiornaCdrEsterni".equals(nome)) {
                    if (valore != null) coerenzaProperties.setElaborazioneRiorganizzazioneAggiornaCdrEsterni(valore);
                } else if ("RiorganizzazioneReportDisabilitazioni".equals(nome)) {
                    if (valore != null) coerenzaProperties.setElaborazioneRiorganizzazioneReportDisabilitazioni(valore);
                } else if ("ExportRuoliDiUnCdr".equals(nome)) {
                    if (valore != null) coerenzaProperties.setExportRuoliDiUnCdr(valore);
                } else if ("BatchMailScadenzeOperatoriEsterni".equals(nome)) {
                    if (valore != null) coerenzaProperties.setEmailScadenzeOpertoriEsterni(valore);
                } else if ("ElaborazioneRuoliOperatori".equals(nome)) {
                    if (valore != null) coerenzaProperties.setElaborazioneRuoliOper(valore);
                }
            }
        } catch (Exception e) {
            log.error("Errore nel metodo selectCostantiSigaBatchCoerenza", e);
            throw new RuntimeException(e);
        }
        return coerenzaProperties;
    }

    public EventoBean getEventoCM(String codiceEvento) {
        try {
            String sql =
                "SELECT PRIORITA, ORA_INIZIO_ELAB_RICHIESTE, ORA_FINE_ELAB_RICHIESTE, " +
                "NUM_MAX_REC_CONT_IMMEDIATI, NUM_MAX_REC_ELAB_IMMEDIATA, RICHIESTA_VISIBILE_RUOLI_SIGA, " +
                "PRESA_VISIONE, ARCHIVIAZIONE, AGGIORNA_SIST_AUTORIZZAZIONE, NUM_MAX_REC, " +
                "ORA_INIZIO_CREA_RICHIESTE, ORA_FINE_CREA_RICHIESTE " +
                "FROM EVENTI_SISTEMA WHERE CODICE_EVENTO = ?1";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, codiceEvento);
            Object[] row = (Object[]) q.getSingleResult();
            EventoBean bean = new EventoBean();
            bean.setPriorita(row[0] != null ? row[0].toString() : null);
            bean.setOrarioInElab(row[1] != null ? row[1].toString() : null);
            bean.setOrarioFineElab(row[2] != null ? row[2].toString() : null);
            bean.setNumMaxRecordControlliImmediati(row[3] != null ? ((Number) row[3]).longValue() : null);
            bean.setNumMaxRecordElaborazioneImmediata(row[4] != null ? ((Number) row[4]).longValue() : null);
            bean.setRichiestaVisibileRuoliSiga(row[5] != null ? row[5].toString() : null);
            bean.setPresaVisione(row[6] != null ? row[6].toString() : null);
            bean.setArchiviazione(row[7] != null ? row[7].toString() : null);
            bean.setAggiornaSistAutorizzazione(row[8] != null ? row[8].toString() : null);
            bean.setNumMaxRecord(row[9] != null ? ((Number) row[9]).intValue() : null);
            bean.setOrarioInizioRichieste(row[10] != null ? row[10].toString() : null);
            bean.setOrarioFineRichieste(row[11] != null ? row[11].toString() : null);
            return bean;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getEventoCM", e);
            throw new RuntimeException(e);
        }
    }

    public UtenteBean getRevocheUtente(String cf) {
        try {
            String sql =
                "SELECT REVOCA_POSTAZIONE, REVOCA_CAU FROM UTENTI " +
                "WHERE CODICE_FISCALE = ?1 AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, cf);
            Object[] row = (Object[]) q.getSingleResult();
            UtenteBean bean = new UtenteBean();
            bean.setRevocaPostazione(row[0] != null ? row[0].toString() : null);
            bean.setRevocaCau(row[1] != null ? row[1].toString() : null);
            return bean;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.error("Errore nel metodo getRevocheUtente", e);
            throw new RuntimeException(e);
        }
    }

    public Integer verificaEsistenzaCf(String cf) {
        try {
            String sql =
                "SELECT COUNT(*) FROM UTENTI WHERE CODICE_FISCALE = ?1 AND DATA_FINE_VAL IS NULL";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, cf);
            return ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            log.error("Errore nel metodo verificaEsistenzaCf", e);
            throw new RuntimeException(e);
        }
    }

    public Integer verificaDisallineamentoCf(String cf) {
        try {
            String sql =
                "SELECT COUNT(*) FROM UTENTI " +
                "WHERE CODICE_FISCALE = ?1 AND CDR_DISALL_CAU_SIGA = 'SI' AND DATA_FINE_VAL IS NULL";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, cf);
            return ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            log.error("Errore nel metodo verificaDisallineamentoCf", e);
            throw new RuntimeException(e);
        }
    }

    public Integer verificaEsistenzaRG(String codRg) {
        try {
            String sql = "SELECT COUNT(*) FROM ROLE_GROUP WHERE CODICE_ROLE_GROUP = ?1";
            Query q = entityManager.createNativeQuery(sql);
            q.setParameter(1, codRg);
            return ((Number) q.getSingleResult()).intValue();
        } catch (Exception e) {
            log.error("Errore nel metodo verificaEsistenzaRG", e);
            throw new RuntimeException(e);
        }
    }
}
