package it.finanze.siga.dao.impl;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.finanze.scheduler.bean.CoerenzaPropertiesBean;
import it.finanze.scheduler.bean.EventoBean;
import it.finanze.scheduler.bean.Profilo;
import it.finanze.scheduler.bean.ProfiloRichiestaBean;
import it.finanze.scheduler.bean.RegistroRichiesteBatchBean;
import it.finanze.scheduler.bean.RichiedenteCdR;
import it.finanze.scheduler.bean.RichiestaBatchDaElaborareBean;
import it.finanze.scheduler.bean.RoleGroupBean;
import it.finanze.scheduler.bean.TabellaBatchHrBean;
import it.finanze.scheduler.bean.Utenti;
import it.finanze.siga.bean.*;
import it.finanze.siga.business.MetodiComuni;
//import it.finanze.siga.bean.ProfiloRichiestaBean;
import it.finanze.siga.finder.*;
import it.finanze.siga.util.Constants;
import it.finanze.siga.util.Utils;
import it.finanze.siga.util.bean.ElencoBean;
import it.finanze.siga.util.bean.UtenteBean;
import it.sogei.eaf.util.CheckException;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;

/**
 * JPA implementation of SIGA Service with native queries.
 * This implementation migrates specific methods from MyBatis to JPA.
 */


@Slf4j
@Stateless
@Named("SigaDaoImpl")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class SigaDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private MetodiComuni metodiComuni;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/* TOD MCE ???
    public void before(ServiceInputBean input)  {
        log.log(Level.INFO, "=>BEFORE " + input.getServiceClass() + "." + input.getServiceMethod());
        log.log(Level.INFO, "=>BEFORE CALLER" + input.getCallerClass() + "." + input.getCallerMethod());
    }

    public int after(ServiceInputBean input, Exception exception) throws CheckException {
        log.log(Level.INFO, "=>AFTER " + input.getServiceClass() + "." + input.getServiceMethod());
        if (exception != null) {
            log.log(Level.SEVERE, "=>ERRORE " + input.getServiceClass() + "." + input.getServiceMethod() + exception.getMessage());
        }
        return ServiceBaseInterface.THROW_EXCEPTION;
    }
    /*

    public void destroy() {
        entityManager = null;
    }*/


	// metodicoumi:
	// SigaDAOQueryID.GET_DATI_UTENTE_2 = "getDatiUtente2"
	public OperatoreBean getDatiUtente2(OperatoreFinder finder) {
		try {
			Object[] row = (Object[]) entityManager.createNativeQuery(
				"SELECT UT.NOME, UT.COGNOME, UT.CODICE_FISCALE FROM UTENTI_UNIFICATI UT, CDR CDR " +
				"WHERE UT.CODICE_FISCALE = ?1 AND CDR.CODICE_CDR = UT.CODICE_CDR " +
				"AND (UT.DATA_FINE_VAL IS NULL OR UT.DATA_FINE_VAL > SYSDATE)")
				.setParameter(1, finder.getCodFisUser()).getSingleResult();
			OperatoreBean bean = new OperatoreBean();
			bean.setNome((String) row[0]);
			bean.setCognome((String) row[1]);
			bean.setCf((String) row[2]);
			return bean;
		} catch (jakarta.persistence.NoResultException e) {
			return null;
		} catch (Exception e) {
			log.error("Errore nel metodo getDatiUtente2", e);
			throw new RuntimeException(e);
		}
	}

	// SigaDAOQueryID.VERIFICA_RESPONSABILE_CDR_FIGLI = "verificaResponsabileCdrFigli"
	public int verificaResponsabileCdrFigli(String cfResponsabile) {
		try {
			return ((Number) entityManager.createNativeQuery(
				"SELECT COUNT(*) FROM RICHIEDENTE_CDR RIC, CDR CDR " +
				"WHERE CF_RESPONSABILE_HR = ?1 " +
				"AND RIC.CODICE_CDR = CDR.CODICE_CDR_PADRE_GERARCHIA " +
				"AND RIC.DATA_FINE_VAL IS NULL " +
				"AND CDR.DATA_FINE_VAL IS NULL")
				.setParameter(1, cfResponsabile).getSingleResult()).intValue();
		} catch (Exception e) {
			log.error("Errore nel metodo verificaResponsabileCdrFigli", e);
			throw new RuntimeException(e);
		}
	}

	// SigaDAOQueryID.VERIFICA_IS_RICHIEDENTE = "verificaIsRichiedente"
	public int verificaIsRichiedente(RichiedenteCDRFinder finder) {
		try {
			StringBuilder sql = new StringBuilder(
				"SELECT COUNT(*) TOT FROM RICHIEDENTE_CDR RICHIEDENTE " +
				"WHERE (RICHIEDENTE.DATA_FINE_VAL IS NULL OR RICHIEDENTE.DATA_FINE_VAL > SYSDATE)");
			int p = 1;
			if (finder.getCfRichiedente() != null && !finder.getCfRichiedente().isEmpty()) {
				sql.append(" AND RICHIEDENTE.CF_RICHIEDENTE = ?").append(p++);
			}
			if (finder.getCodiceCDR() != null && !finder.getCodiceCDR().isEmpty()) {
				sql.append(" AND RICHIEDENTE.CODICE_CDR != ?").append(p++);
			}
			if (finder.getCfAutorizzatoreILiv() != null && !finder.getCfAutorizzatoreILiv().isEmpty()) {
				sql.append(" AND RICHIEDENTE.CF_AUTORIZZATORE_I_LIV = ?").append(p++);
			}
			Query q = entityManager.createNativeQuery(sql.toString());
			p = 1;
			if (finder.getCfRichiedente() != null && !finder.getCfRichiedente().isEmpty()) {
				q.setParameter(p++, finder.getCfRichiedente());
			}
			if (finder.getCodiceCDR() != null && !finder.getCodiceCDR().isEmpty()) {
				q.setParameter(p++, finder.getCodiceCDR());
			}
			if (finder.getCfAutorizzatoreILiv() != null && !finder.getCfAutorizzatoreILiv().isEmpty()) {
				q.setParameter(p++, finder.getCfAutorizzatoreILiv());
			}
			return ((Number) q.getSingleResult()).intValue();
		} catch (Exception e) {
			log.error("Errore nel metodo verificaIsRichiedente", e);
			throw new RuntimeException(e);
		}
	}

	// SigaDAOQueryID.GET_NUMERO_ASSOCIAZIONI = "getNumeroAssociazioni"
	public int getNumeroAssociazioni(AssociazOperRichiAutorFinder finder) {
		try {
			return ((Number) entityManager.createNativeQuery(
				"SELECT COUNT(*) FROM ASSOCIAZ_OPER_RICHI_AUTOR ASSOCIAZIONE " +
				"WHERE ASSOCIAZIONE.CODICE_FISCALE_RICHIEDENTE = ?1 " +
				"AND (ASSOCIAZIONE.DATA_FINE_VAL IS NULL OR ASSOCIAZIONE.DATA_FINE_VAL > SYSDATE)")
				.setParameter(1, finder.getCfRichiedente()).getSingleResult()).intValue();
		} catch (Exception e) {
			log.error("Errore nel metodo getNumeroAssociazioni", e);
			throw new RuntimeException(e);
		}
	}

	// SigaDAOQueryID.GET_NUMERO_ASSOCIAZIONI_RICH_AUT = "getNumeroAssociazioniRichOrAut"
	public int getNumeroRichieste(AssociazOperRichiAutorFinder finder) {
		try {
			StringBuilder sql = new StringBuilder(
				"SELECT COUNT(*) FROM ASSOCIAZ_OPER_RICHI_AUTOR ASSOCIAZIONE " +
				"WHERE (ASSOCIAZIONE.DATA_FINE_VAL IS NULL OR ASSOCIAZIONE.DATA_FINE_VAL > SYSDATE)");
			int p = 1;
			if (finder.getCfOperatore() != null && !finder.getCfOperatore().isEmpty()) {
				sql.append(" AND ASSOCIAZIONE.CODICE_FISCALE_OPERATORE != ?").append(p++);
			}
			if (finder.getCfRichiedente() != null && !finder.getCfRichiedente().isEmpty()) {
				sql.append(" AND ASSOCIAZIONE.CODICE_FISCALE_RICHIEDENTE = ?").append(p++);
			}
			if (finder.getCfNONRichiedente() != null && !finder.getCfNONRichiedente().isEmpty()) {
				sql.append(" AND ASSOCIAZIONE.CODICE_FISCALE_RICHIEDENTE != ?").append(p++);
			}
			if (finder.getCfAutorizzatore() != null && !finder.getCfAutorizzatore().isEmpty()) {
				sql.append(" AND ASSOCIAZIONE.CODICE_FISCALE_AUTORIZZATORE = ?").append(p++);
			}
			Query q = entityManager.createNativeQuery(sql.toString());
			p = 1;
			if (finder.getCfOperatore() != null && !finder.getCfOperatore().isEmpty()) {
				q.setParameter(p++, finder.getCfOperatore());
			}
			if (finder.getCfRichiedente() != null && !finder.getCfRichiedente().isEmpty()) {
				q.setParameter(p++, finder.getCfRichiedente());
			}
			if (finder.getCfNONRichiedente() != null && !finder.getCfNONRichiedente().isEmpty()) {
				q.setParameter(p++, finder.getCfNONRichiedente());
			}
			if (finder.getCfAutorizzatore() != null && !finder.getCfAutorizzatore().isEmpty()) {
				q.setParameter(p++, finder.getCfAutorizzatore());
			}
			return ((Number) q.getSingleResult()).intValue();
		} catch (Exception e) {
			log.error("Errore nel metodo getNumeroRichieste", e);
			throw new RuntimeException(e);
		}
	}

	// SigaDAOQueryID.GET_CDR_DA_RICHIEDENTE_CDR = "getCdrDaRichiesteCDR"
	@SuppressWarnings("unchecked")
	public List<RichiedenteCDRBean> getCdrAutorizzatoreIliv(RichiedenteCDRFinder finder) {
		List<RichiedenteCDRBean> result = new ArrayList<RichiedenteCDRBean>();
		try {
			StringBuilder sql = new StringBuilder(
				"SELECT CODICE_CDR FROM RICHIEDENTE_CDR " +
				"WHERE (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)");
			int p = 1;
			if (finder.getCfAutorizzatoreILiv() != null && !finder.getCfAutorizzatoreILiv().isEmpty()) {
				sql.append(" AND CF_AUTORIZZATORE_I_LIV = ?").append(p++);
			}
			if (finder.getCfResponsabileHR() != null && !finder.getCfResponsabileHR().isEmpty()) {
				sql.append(" AND CF_RESPONSABILE_HR = ?").append(p++);
			}
			Query q = entityManager.createNativeQuery(sql.toString());
			p = 1;
			if (finder.getCfAutorizzatoreILiv() != null && !finder.getCfAutorizzatoreILiv().isEmpty()) {
				q.setParameter(p++, finder.getCfAutorizzatoreILiv());
			}
			if (finder.getCfResponsabileHR() != null && !finder.getCfResponsabileHR().isEmpty()) {
				q.setParameter(p++, finder.getCfResponsabileHR());
			}
			for (Object row : q.getResultList()) {
				RichiedenteCDRBean bean = new RichiedenteCDRBean();
				bean.setCodiceCDR((String) row);
				result.add(bean);
			}
		} catch (Exception e) {
			log.error("Errore nel metodo getCdrAutorizzatoreIliv", e);
			throw new RuntimeException(e);
		}
		return result;
	}

	// SigaDAOQueryID.GET_STRUTTURA_ITER = "getStrutturaIter"
	@SuppressWarnings("unchecked")
	public List<StrutturaPerIterFinder> getStrutturaIter(StrutturaPerIterFinder finder) {
		List<StrutturaPerIterFinder> result = new ArrayList<StrutturaPerIterFinder>();
		try {
			StringBuilder sql = new StringBuilder(
				"SELECT SI.CDR_AUTORIZ_I_LIV, SI.CDR_AUTORIZ_II_LIV, SI.CF_I, SI.CF_II " +
				"FROM STRUTTURA_ITER SI " +
				"WHERE (SI.DATA_FINE_VAL IS NULL OR SI.DATA_FINE_VAL > SYSDATE)");
			int p = 1;
			if (finder.getCdrAutorizILiv() != null && !finder.getCdrAutorizILiv().isEmpty()) {
				sql.append(" AND SI.CDR_AUTORIZ_I_LIV = ?").append(p++);
			}
			if (finder.getCfI() != null && !finder.getCfI().isEmpty()) {
				sql.append(" AND SI.CF_I = ?").append(p++);
			}
			if (finder.getCfII() != null && !finder.getCfII().isEmpty()) {
				sql.append(" AND SI.CF_II = ?").append(p++);
			}
			if (finder.getCdrAutorizIILiv() != null && !finder.getCdrAutorizIILiv().isEmpty()) {
				sql.append(" AND SI.CDR_AUTORIZ_II_LIV = ?").append(p++);
			}
			if (finder.getIdentificativoIter() != null && !finder.getIdentificativoIter().isEmpty()) {
				sql.append(" AND SI.IDENTIFICATIVO_ITER != ?").append(p++);
			}
			if (finder.getTipoUfficioRichiedente() != null && !finder.getTipoUfficioRichiedente().isEmpty()) {
				sql.append(" AND SI.TIPO_UFFICIO_RICHIEDENTE != ?").append(p++);
			}
			Query q = entityManager.createNativeQuery(sql.toString());
			p = 1;
			if (finder.getCdrAutorizILiv() != null && !finder.getCdrAutorizILiv().isEmpty()) {
				q.setParameter(p++, finder.getCdrAutorizILiv());
			}
			if (finder.getCfI() != null && !finder.getCfI().isEmpty()) {
				q.setParameter(p++, finder.getCfI());
			}
			if (finder.getCfII() != null && !finder.getCfII().isEmpty()) {
				q.setParameter(p++, finder.getCfII());
			}
			if (finder.getCdrAutorizIILiv() != null && !finder.getCdrAutorizIILiv().isEmpty()) {
				q.setParameter(p++, finder.getCdrAutorizIILiv());
			}
			if (finder.getIdentificativoIter() != null && !finder.getIdentificativoIter().isEmpty()) {
				q.setParameter(p++, finder.getIdentificativoIter());
			}
			if (finder.getTipoUfficioRichiedente() != null && !finder.getTipoUfficioRichiedente().isEmpty()) {
				q.setParameter(p++, finder.getTipoUfficioRichiedente());
			}
			for (Object[] row : (List<Object[]>) q.getResultList()) {
				StrutturaPerIterFinder s = new StrutturaPerIterFinder();
				s.setCdrAutorizILiv((String) row[0]);
				s.setCdrAutorizIILiv((String) row[1]);
				s.setCfI((String) row[2]);
				s.setCfII((String) row[3]);
				result.add(s);
			}
		} catch (Exception e) {
			log.error("Errore nel metodo getStrutturaIter", e);
			throw new RuntimeException(e);
		}
		return result;
	}

	// SigaDAOQueryID.GET_STRUTTURA_ITER_2 = "getStrutturaIter2"
	@SuppressWarnings("unchecked")
	public List<StrutturaPerIterFinder> getStrutturaIter2(StrutturaPerIterFinder finder) {
		List<StrutturaPerIterFinder> result = new ArrayList<StrutturaPerIterFinder>();
		try {
			List<Object[]> rows = entityManager.createNativeQuery(
				"SELECT SI.CDR_AUTORIZ_I_LIV, SI.CDR_AUTORIZ_II_LIV, SI.CF_I, SI.CF_II " +
				"FROM STRUTTURA_ITER SI, STRUTTURA ST " +
				"WHERE (SI.DATA_FINE_VAL IS NULL OR SI.DATA_FINE_VAL > SYSDATE) " +
				"AND (ST.DATA_FINE_VAL IS NULL OR ST.DATA_FINE_VAL > SYSDATE) " +
				"AND ST.CODICE_CDR = ?1 " +
				"AND SI.TIPO_CDR_AUTORIZ_II_LIV = ST.TIPO_STRUTTURA")
				.setParameter(1, finder.getCdrAutorizIILiv()).getResultList();
			for (Object[] row : rows) {
				StrutturaPerIterFinder s = new StrutturaPerIterFinder();
				s.setCdrAutorizILiv((String) row[0]);
				s.setCdrAutorizIILiv((String) row[1]);
				s.setCfI((String) row[2]);
				s.setCfII((String) row[3]);
				result.add(s);
			}
		} catch (Exception e) {
			log.error("Errore nel metodo getStrutturaIter2", e);
			throw new RuntimeException(e);
		}
		return result;
	}

	// SigaDAOQueryID.CONTROLLO_RUOLO_AMMINISTRATORE = "controlloRuoloAmministratore"
	public int step1(String cdr) {
		try {
			return ((Number) entityManager.createNativeQuery(
				"SELECT count(*) FROM CDR A, CDR B, RELAZIONE_CDR_UFFICIO_STRUT C, RELAZIONE_CDR_UFFICIO_STRUT D " +
				"WHERE (A.DATA_FINE_VAL IS NULL OR A.DATA_FINE_VAL > SYSDATE) " +
				"AND (B.DATA_FINE_VAL IS NULL OR B.DATA_FINE_VAL > SYSDATE) " +
				"AND (C.DATA_FINE_VAL IS NULL OR C.DATA_FINE_VAL > SYSDATE) " +
				"AND (D.DATA_FINE_VAL IS NULL OR D.DATA_FINE_VAL > SYSDATE) " +
				"AND A.CODICE_CDR = ?1 " +
				"AND A.CODICE_CDR = C.CODICE_CDR " +
				"AND C.CODICE_STRUTTURA = D.CODICE_STRUTTURA " +
				"AND D.CODICE_CDR = B.CODICE_CDR_PADRE_GERARCHIA " +
				"AND B.CODICE_CDR_PADRE_GERARCHIA = ?1 " +
				"AND A.DATA_FINE_VAL IS NULL AND B.DATA_FINE_VAL IS NULL AND C.DATA_FINE_VAL IS NULL AND D.DATA_FINE_VAL IS NULL")
				.setParameter(1, cdr).getSingleResult()).intValue();
		} catch (Exception e) {
			log.error("Errore nel metodo step1", e);
			throw new RuntimeException(e);
		}
	}

	// SigaDAOQueryID.GET_STRUTTURA_ITER_BY_CDR = "getStrutturaIterByCdr"
	public int getStrutturaIterByCdr(String cdr) {
		try {
			return ((Number) entityManager.createNativeQuery(
				"WITH VERTICE AS (" +
				"    SELECT CDR.CODICE_CDR FROM CDR" +
				"    LEFT JOIN STRUTTURA STR1 ON CDR.CODICE_CDR = STR1.CODICE_CDR" +
				"    LEFT JOIN STRUTTURA STR2 ON CDR.CODICE_CDR_PADRE_GERARCHIA = STR2.CODICE_CDR" +
				"    WHERE STR1.CODICE_STRUTTURA != STR2.CODICE_STRUTTURA" +
				") " +
				"SELECT COUNT(*) FROM CDR, STRUTTURA_ITER SI, VERTICE " +
				"WHERE CDR.CODICE_CDR = ?1 " +
				"AND CDR.CODICE_CDR IN (SELECT CODICE_CDR FROM VERTICE) " +
				"AND SI.TIPO_CDR_AUTORIZ_I_LIV = CDR.TIPO_CDR " +
				"AND (CDR.DATA_FINE_VAL IS NULL OR CDR.DATA_FINE_VAL > SYSDATE) " +
				"AND (SI.DATA_FINE_VAL IS NULL OR SI.DATA_FINE_VAL > SYSDATE)")
				.setParameter(1, cdr).getSingleResult()).intValue();
		} catch (Exception e) {
			log.error("Errore nel metodo getStrutturaIterByCdr", e);
			throw new RuntimeException(e);
		}
	}

	// SigaDAOQueryID.GET_STRUTTURA_ITER_BY_CDR_II_LIVELLO = "getStrutturaIterByCdrIILivello"
	public int getStrutturaIterByCdrIILivello(String cdr) {
		try {
			return ((Number) entityManager.createNativeQuery(
				"WITH VERTICE AS (" +
				"    SELECT CDR.CODICE_CDR FROM CDR" +
				"    LEFT JOIN STRUTTURA STR1 ON CDR.CODICE_CDR = STR1.CODICE_CDR" +
				"    LEFT JOIN STRUTTURA STR2 ON CDR.CODICE_CDR_PADRE_GERARCHIA = STR2.CODICE_CDR" +
				"    WHERE STR1.CODICE_STRUTTURA != STR2.CODICE_STRUTTURA" +
				") " +
				"SELECT COUNT(*) FROM CDR, STRUTTURA_ITER SI, VERTICE " +
				"WHERE CDR.CODICE_CDR = ?1 " +
				"AND CDR.CODICE_CDR IN (SELECT CODICE_CDR FROM VERTICE) " +
				"AND SI.TIPO_CDR_AUTORIZ_II_LIV = CDR.TIPO_CDR " +
				"AND (CDR.DATA_FINE_VAL IS NULL OR CDR.DATA_FINE_VAL > SYSDATE) " +
				"AND (SI.DATA_FINE_VAL IS NULL OR SI.DATA_FINE_VAL > SYSDATE)")
				.setParameter(1, cdr).getSingleResult()).intValue();
		} catch (Exception e) {
			log.error("Errore nel metodo getStrutturaIterByCdrIILivello", e);
			throw new RuntimeException(e);
		}
	}

	// SigaDAOQueryID.AGGIORNA_FLAG_RUOLO = "aggiornaFlagRuolo"
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void aggiornaFlagRuolo(OperatoreFinder finder) {
		try {
			StringBuilder sql = new StringBuilder("UPDATE UTENTI SET E_MAIL = E_MAIL");
			int p = 1;
			if (finder.getRichiedente() != null && !finder.getRichiedente().isEmpty()) {
				sql.append(", RICHIEDENTE = ?").append(p++);
			}
			if (finder.getAutorizzatoreILivello() != null && !finder.getAutorizzatoreILivello().isEmpty()) {
				sql.append(", AUTORIZZATORE_I_LIV = ?").append(p++);
			}
			sql.append(" WHERE CODICE_FISCALE = ?").append(p++);
			if (finder.getRichiedente() != null && !finder.getRichiedente().isEmpty()) {
				sql.append(" AND RICHIEDENTE != ?").append(p++);
			}
			if (finder.getAutorizzatoreILivello() != null && !finder.getAutorizzatoreILivello().isEmpty()) {
				sql.append(" AND AUTORIZZATORE_I_LIV != ?").append(p++);
			}
			Query q = entityManager.createNativeQuery(sql.toString());
			p = 1;
			if (finder.getRichiedente() != null && !finder.getRichiedente().isEmpty()) {
				q.setParameter(p++, finder.getRichiedente());
			}
			if (finder.getAutorizzatoreILivello() != null && !finder.getAutorizzatoreILivello().isEmpty()) {
				q.setParameter(p++, finder.getAutorizzatoreILivello());
			}
			q.setParameter(p++, finder.getCodFisUser());
			if (finder.getRichiedente() != null && !finder.getRichiedente().isEmpty()) {
				q.setParameter(p++, finder.getRichiedente());
			}
			if (finder.getAutorizzatoreILivello() != null && !finder.getAutorizzatoreILivello().isEmpty()) {
				q.setParameter(p++, finder.getAutorizzatoreILivello());
			}
			q.executeUpdate();
		} catch (Exception e) {
			log.error("Errore nel metodo aggiornaFlagRuolo", e);
			throw new RuntimeException(e);
		}
	}

	// SigaDAOQueryID.GET_REVOCHE_UTENTE = "getRevocheUtente"
	public UtenteBean getRevocheUtente(String cf) {
		try {
			Object[] row = (Object[]) entityManager.createNativeQuery(
				"SELECT REVOCA_POSTAZIONE, REVOCA_CAU FROM UTENTI " +
				"WHERE CODICE_FISCALE = ?1 AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)")
				.setParameter(1, cf).getSingleResult();
			UtenteBean bean = new UtenteBean();
			bean.setRevocaPostazione((String) row[0]);
			bean.setRevocaCau((String) row[1]);
			return bean;
		} catch (jakarta.persistence.NoResultException e) {
			return null;
		} catch (Exception e) {
			log.error("Errore nel metodo getRevocheUtente", e);
			throw new RuntimeException(e);
		}
	}

	// SigaDAOQueryID.GET_ELENCO_DELEGHE = "getElencoDeleghe"
	@SuppressWarnings("unchecked")
	public List<DelegaBean> getElencoDeleghe(DelegaFinder finder) {
		List<DelegaBean> result = new ArrayList<DelegaBean>();
		try {
			StringBuilder sql = new StringBuilder(
				"SELECT DE.CDR_DEL_DELEGANTE, DE.CDR_DEL_DELEGATO, DE.CODICE_FISCALE_DELEGANTE, " +
				"DE.CODICE_FISCALE_DELEGATO, DE.CODICE_FISCALE_INIZIO, DE.DATA_INIZIO_VAL, " +
				"DE.DATA_SCADENZA, DE.RUOLO_DELEGATO, DE.ID_DELEGA, DE.NOTA " +
				"FROM DELEGHE DE " +
				"WHERE (DE.DATA_FINE_VAL IS NULL OR DE.DATA_FINE_VAL > SYSDATE)");
			int p = 1;
			if (finder.getCodiceFiscaleDelegante() != null && !finder.getCodiceFiscaleDelegante().isEmpty()) {
				sql.append(" AND DE.CODICE_FISCALE_DELEGANTE = ?").append(p++);
			}
			if (finder.getCodiceFiscaleDelegato() != null && !finder.getCodiceFiscaleDelegato().isEmpty()) {
				sql.append(" AND DE.CODICE_FISCALE_DELEGATO = ?").append(p++);
			}
			if (finder.getRuoloDelegato() != null && !finder.getRuoloDelegato().isEmpty()) {
				sql.append(" AND DE.RUOLO_DELEGATO = ?").append(p++);
			}
			Query q = entityManager.createNativeQuery(sql.toString());
			p = 1;
			if (finder.getCodiceFiscaleDelegante() != null && !finder.getCodiceFiscaleDelegante().isEmpty()) {
				q.setParameter(p++, finder.getCodiceFiscaleDelegante());
			}
			if (finder.getCodiceFiscaleDelegato() != null && !finder.getCodiceFiscaleDelegato().isEmpty()) {
				q.setParameter(p++, finder.getCodiceFiscaleDelegato());
			}
			if (finder.getRuoloDelegato() != null && !finder.getRuoloDelegato().isEmpty()) {
				q.setParameter(p++, finder.getRuoloDelegato());
			}
			for (Object[] row : (List<Object[]>) q.getResultList()) {
				DelegaBean bean = new DelegaBean();
				bean.setCdrDelegante((String) row[0]);
				bean.setCdrDelegato((String) row[1]);
				bean.setCodiceFiscaleDelegante((String) row[2]);
				bean.setCodiceFiscaleDelegato((String) row[3]);
				bean.setCfInizio((String) row[4]);
				bean.setDataInizioVal((java.util.Date) row[5]);
				bean.setDataScadenza((java.util.Date) row[6]);
				bean.setRuoloDelegato((String) row[7]);
				bean.setIdDelega(row[8] != null ? ((Number) row[8]).intValue() : null);
				bean.setNota((String) row[9]);
				result.add(bean);
			}
		} catch (Exception e) {
			log.error("Errore nel metodo getElencoDeleghe", e);
			throw new RuntimeException(e);
		}
		return result;
	}
	//metodicomuni.	

	public List<String> selectUtentiByCdrDisall()  {
		log.info("IN: SIGAServiceJpaImpl -> selectUtentiByCdrDisall");

		try {
			String sql =
					"select CODICE_FISCALE as codiceFiscale" +
							" from UTENTI" +
							" where CDR_DISALL_CAU_SIGA = 'SI'" +
							" and ( DATA_FINE_VAL is null" +
							" or DATA_FINE_VAL > SYSDATE)";

			Query query = entityManager.createNativeQuery(sql);

			List<String> result = query.getResultList();
			return result;


		} catch (Exception e) {
			log.error("=>ERRORE metodo selectUtentiByCdrDisall : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	public GetAutorizzatore_I_LivBean getAutorizzatore_I_Liv_Per_Ripristino(GetAutorizzatore_I_LivFinder finder) {
		try {
			String sql =
				"WITH autorizzatore_I_livello AS ( " +
				"  SELECT DISTINCT 1 PIPPO, UT.CODICE_FISCALE, RC.CODICE_CDR AS CDRAUT " +
				"  FROM RICHIEDENTE_CDR RC, STRUTTURA S, UTENTI UT " +
				"  WHERE (RC.DATA_FINE_VAL IS NULL OR RC.DATA_FINE_VAL > SYSDATE) " +
				"    AND (S.DATA_FINE_VAL IS NULL OR S.DATA_FINE_VAL > SYSDATE) " +
				"    AND (UT.DATA_FINE_VAL IS NULL OR UT.DATA_FINE_VAL > SYSDATE) " +
				"    AND RC.CODICE_CDR = ?1 " +
				"    AND RC.CODICE_CDR = S.CODICE_CDR " +
				"    AND UT.CODICE_FISCALE = RC.CF_RESPONSABILE_HR " +
				"  UNION " +
				"  SELECT DISTINCT 3 PIPPO, UT.CODICE_FISCALE, RCPADRE.CODICE_CDR AS CDRAUT " +
				"  FROM RICHIEDENTE_CDR RC, UTENTI UT, CDR PADRE, RICHIEDENTE_CDR RCPADRE " +
				"  WHERE RC.CODICE_CDR = ?1 " +
				"    AND (RC.DATA_FINE_VAL IS NULL OR RC.DATA_FINE_VAL > SYSDATE) " +
				"    AND (RCPADRE.DATA_FINE_VAL IS NULL OR RCPADRE.DATA_FINE_VAL > SYSDATE) " +
				"    AND PADRE.CODICE_CDR = RC.CODICE_CDR " +
				"    AND RCPADRE.CODICE_CDR = PADRE.CODICE_CDR_PADRE_GERARCHIA " +
				"    AND UT.CODICE_FISCALE = RCPADRE.CF_RESPONSABILE_HR " +
				"  ORDER BY PIPPO " +
				") " +
				"SELECT UT.COGNOME, UT.NOME, UT.CODICE_FISCALE, CDRAUT AS CODICE_CDR " +
				"FROM autorizzatore_I_livello, UTENTI UT " +
				"WHERE autorizzatore_I_livello.CODICE_FISCALE = UT.CODICE_FISCALE " +
				"  AND (UT.DATA_FINE_VAL IS NULL OR UT.DATA_FINE_VAL > SYSDATE) " +
				"  AND rownum = 1";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, finder.getCodiceCDR());
			Object[] row = (Object[]) query.getSingleResult();
			GetAutorizzatore_I_LivBean bean = new GetAutorizzatore_I_LivBean();
			bean.setCognome((String) row[0]);
			bean.setNome((String) row[1]);
			bean.setCodice_fiscale((String) row[2]);
			bean.setCodice_cdr((String) row[3]);
			return bean;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			log.error("Errore nel metodo getAutorizzatore_I_Liv_Per_Ripristino", e);
			throw new RuntimeException(e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void insIdAllegatiDelega(DelegaBean delegaBean) {
		try {
			String sql = "UPDATE DELEGHE SET ID_DELEGA = ?1 " +
					"WHERE (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE) " +
					"AND CODICE_FISCALE_DELEGANTE = ?2 " +
					"AND CODICE_FISCALE_DELEGATO = ?3 " +
					"AND RUOLO_DELEGATO = ?4";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, delegaBean.getIdDelega());
			query.setParameter(2, delegaBean.getCodiceFiscaleDelegante());
			query.setParameter(3, delegaBean.getCodiceFiscaleDelegato());
			query.setParameter(4, delegaBean.getRuoloDelegato());
			query.executeUpdate();
		} catch (Exception e) {
			log.error("Errore nel metodo insIdAllegatiDelega", e);
			throw new RuntimeException(e);
		}
	}

	public int keyIdDelega() {
		try {
			Query query = entityManager.createNativeQuery("SELECT ID_DELEGA.nextval AS idDelega FROM dual");
			return ((Number) query.getSingleResult()).intValue();
		} catch (Exception e) {
			log.error("Errore nel metodo keyIdDelega", e);
			throw new RuntimeException(e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserisciAllegatiDelega(DocumentoFinder docFinder) {
		try {
			Query seqQuery = entityManager.createNativeQuery("SELECT PROG_DOCUMENTO.nextval FROM dual");
			int progDoc = ((Number) seqQuery.getSingleResult()).intValue();
			docFinder.setProgDoc(progDoc);

			String sql = "INSERT INTO DOCUMENTI_DELEGHE (ID_DELEGA, PROG_DOCUMENTO, ALLEGATO, NOME_FILE) " +
					"VALUES (?1, ?2, ?3, ?4)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, docFinder.getIdTab());
			query.setParameter(2, progDoc);
			query.setParameter(3, docFinder.getbFile());
			query.setParameter(4, docFinder.getFileName());
			query.executeUpdate();
		} catch (Exception e) {
			log.error("Errore nel metodo inserisciAllegatiDelega", e);
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<AllegatoBean> getAllegatiDelega(Integer idDelega) {
		List<AllegatoBean> result = new ArrayList<AllegatoBean>();
		try {
			String sql = "SELECT PROG_DOCUMENTO, NOME_FILE, ALLEGATO FROM DOCUMENTI_DELEGHE WHERE ID_DELEGA = ?1";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, idDelega);
			List<Object[]> rows = query.getResultList();
			for (Object[] row : rows) {
				AllegatoBean bean = new AllegatoBean();
				bean.setProgAll(row[0] != null ? ((Number) row[0]).intValue() : null);
				bean.setFileName((String) row[1]);
				if (row[2] != null) {
					if (row[2] instanceof byte[]) {
						bean.setbFile((byte[]) row[2]);
					} else if (row[2] instanceof Blob) {
						Blob blob = (Blob) row[2];
						bean.setbFile(blob.getBytes(1, (int) blob.length()));
					}
				}
				result.add(bean);
			}
		} catch (Exception e) {
			log.error("Errore nel metodo getAllegatiDelega", e);
			throw new RuntimeException(e);
		}
		return result;
	}

    

	/**
	 * Get emails by role from PROFILI_ATTIVI_UTENTE and UTENTI tables.
	 * Migrated from MyBatis query: getEmailPerRuolo
	 *
	 * @param ruolo The role code (e.g., 'ESG_AMM_Centrale_3')
	 * @return List of email addresses
	 * @throws CheckException if error occurs
	 */
	public List<String> getEmailPerRuolo(String ruolo)  {
		log.info("IN: SIGAServiceJpaImpl -> getEmailPerRuolo");

		try {
			String sql =
					"SELECT DISTINCT U.E_MAIL " +
							"FROM PROFILI_ATTIVI_UTENTE PAU " +
							"LEFT JOIN UTENTI U ON PAU.CF_UTENTE = U.CODICE_FISCALE " +
							"WHERE " +
							"    (PAU.DATA_FINE_VAL IS NULL OR PAU.DATA_FINE_VAL > SYSDATE) " +
							"    AND U.E_MAIL IS NOT NULL " +
							"    AND PAU.CODICE_PROFILO = ?1";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter("ruolo", ruolo);

			@SuppressWarnings("unchecked")
			List<String> lista = query.getResultList();

			return lista != null ? lista : new ArrayList<String>();

		} catch (Exception e) {
			log.error("=>ERRORE getEmailPerRuolo() : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get II level structure code by CDR.
	 * Migrated from MyBatis query: getStrutturaIILivByCdr
	 *
	 * @param input CDR code
	 * @return II level structure code
	 * @throws CheckException if error occurs
	 */
	public String getStrutturaIILivByCdr(String input)  {
		log.info("IN: SIGAServiceJpaImpl -> getStrutturaIILivByCdr");

		try {
			String sql =
					"SELECT CODICE_CDR_II_LIV_GERARCHICO " +
							"FROM RELAZIONE_CDR_UFFICIO_STRUT RCUS, STRUTTURA STR " +
							"WHERE " +
							"    RCUS.CODICE_CDR = ?1 " +
							"    AND RCUS.CODICE_STRUTTURA = STR.CODICE_STRUTTURA " +
							"    AND RCUS.DATA_FINE_VAL IS NULL";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter("cdrCode", input);

			String result = (String) query.getSingleResult();
			return result;

		} catch (NoResultException e) {
			log.info("No result found for CDR: " + input);
			return null;
		} catch (Exception e) {
			log.error("=>ERRORE metodo getStrutturaIILivByCdr(input) : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get emails of central administrators.
	 * Migrated from MyBatis query: getEmailAmmCentrali
	 *
	 * @return List of email addresses
	 * @throws CheckException if error occurs
	 */
	public List<String> getEmailAmmCentrali()  {
		log.info("IN: SIGAServiceJpaImpl -> getEmailAmmCentrali");

		try {
			String sql =
					"SELECT E_MAIL " +
							"FROM UTENTI U, PROFILI_ATTIVI_UTENTE PAU " +
							"WHERE " +
							"    U.CODICE_FISCALE = PAU.CF_UTENTE " +
							"    AND CODICE_PROFILO = 'ESG_AMM_Centrale_3' " +
							"    AND (PAU.DATA_FINE_VAL IS NULL OR PAU.DATA_FINE_VAL > SYSDATE)";

			Query query = entityManager.createNativeQuery(sql);

			@SuppressWarnings("unchecked")
			List<String> result = query.getResultList();

			return result != null ? result : new ArrayList<String>();

		} catch (Exception e) {
			log.error("=>ERRORE metodo getEmailAmmCentrali() : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get emails of II level structure administrators.
	 * Migrated from MyBatis query: getEmailStrutturaIILiv
	 *
	 * @param input II level structure code
	 * @return List of email addresses
	 * @throws CheckException if error occurs
	 */
	public List<String> getEmailStrutturaIILiv(String input)  {
		log.info("IN: SIGAServiceJpaImpl -> getEmailStrutturaIILiv");

		try {
			String sql =
					"SELECT E_MAIL " +
							"FROM " +
							"    UTENTI UT, " +
							"    PROFILI_ATTIVI_UTENTE PAU, " +
							"    RELAZIONE_CDR_UFFICIO_STRUT RCUS, " +
							"    STRUTTURA STR " +
							"WHERE " +
							"    PAU.CODICE_PROFILO = 'ESG_AMM_Regionale_3' " +
							"    AND PAU.CF_UTENTE = UT.CODICE_FISCALE " +
							"    AND UT.CODICE_CDR = RCUS.CODICE_CDR " +
							"    AND RCUS.CODICE_STRUTTURA = STR.CODICE_STRUTTURA " +
							"    AND STR.CODICE_CDR_II_LIV_GERARCHICO = ?1 " +
							"    AND RCUS.DATA_FINE_VAL IS NULL " +
							"    AND (PAU.DATA_FINE_VAL IS NULL OR PAU.DATA_FINE_VAL > SYSDATE)";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, input);

			@SuppressWarnings("unchecked")
			List<String> result = query.getResultList();

			return result != null ? result : new ArrayList<String>();

		} catch (Exception e) {
			log.error("=>ERRORE metodo getEmailStrutturaIILiv(input) : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Verify structures for a given operator.
	 * Migrated from MyBatis query: verificaStrutture
	 *
	 * @param input OperatoreBean with cdr and cf
	 * @return II level hierarchical structure code
	 * @throws CheckException if error occurs
	 */
	public String verificaStrutture(OperatoreBean input)  {
		log.info("IN: SIGAServiceJpaImpl -> verificaStrutture");

		try {
			String sql =
					"SELECT DISTINCT STR.CODICE_CDR_II_LIV_GERARCHICO " +
							"FROM " +
							"    RELAZIONE_CDR_UFFICIO_STRUT RCUS, " +
							"    PROFILI_ATTIVI_UTENTE PAU, " +
							"    RELAZIONE_CDR_UFFICIO_STRUT RCUSAR, " +
							"    STRUTTURA STR " +
							"WHERE " +
							"    RCUS.CODICE_CDR = ?1 " +
							"    AND RCUS.DATA_FINE_VAL IS NULL " +
							"    AND PAU.CODICE_PROFILO = 'ESG_AMM_Locale_3' " +
							"    AND PAU.CODICE_CDR = RCUSAR.CODICE_CDR " +
							"    AND RCUS.CODICE_STRUTTURA = RCUSAR.CODICE_STRUTTURA " +
							"    AND RCUSAR.DATA_FINE_VAL IS NULL " +
							"    AND RCUS.CODICE_STRUTTURA = STR.CODICE_STRUTTURA " +
							"    AND STR.DATA_FINE_VAL IS NULL " +
							"    AND (PAU.DATA_FINE_VAL IS NULL OR PAU.DATA_FINE_VAL > SYSDATE) " +
							"    AND PAU.CF_UTENTE = ?2 ";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, input.getCdr());
			query.setParameter(2, input.getCf());

			String result = (String) query.getSingleResult();
			return result;

		} catch (NoResultException e) {
			log.info("No result found for CDR: " + input.getCdr() + ", CF: " + input.getCf());
			return null;
		} catch (Exception e) {
			log.error("=>ERRORE metodo verificaStrutture(input) : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get emails of hierarchically superior administrators.
	 * Migrated from MyBatis query: ricavaMailSovraordinati
	 *
	 * @param cdr CDR code
	 * @return List of email addresses
	 * @throws CheckException if error occurs
	 */
	public List<String> ricavaMailSovraordinati(String cdr)  {
		log.info("IN: SIGAServiceJpaImpl -> ricavaMailSovraordinati");

		try {
			String sql =
					"SELECT E_MAIL " +
							"FROM " +
							"    UTENTI UT, " +
							"    PROFILI_ATTIVI_UTENTE PAU, " +
							"    RELAZIONE_CDR_UFFICIO_STRUT RCUSUT, " +
							"    STRUTTURA STRUT, " +
							"    RELAZIONE_CDR_UFFICIO_STRUT RCUSAMREG, " +
							"    STRUTTURA STRAMREG " +
							"WHERE " +
							"    RCUSUT.CODICE_CDR = :cdr " +
							"    AND RCUSUT.CODICE_STRUTTURA = STRUT.CODICE_STRUTTURA " +
							"    AND STRUT.CODICE_CDR_II_LIV_GERARCHICO = STRAMREG.CODICE_CDR_II_LIV_GERARCHICO " +
							"    AND PAU.CODICE_PROFILO = 'ESG_AMM_Regionale_3' " +
							"    AND PAU.CF_UTENTE = UT.CODICE_FISCALE " +
							"    AND UT.CODICE_CDR = RCUSAMREG.CODICE_CDR " +
							"    AND UT.DATA_FINE_VAL IS NULL " +
							"    AND RCUSAMREG.CODICE_STRUTTURA = STRAMREG.CODICE_STRUTTURA";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter("cdr", cdr);

			@SuppressWarnings("unchecked")
			List<String> lista = query.getResultList();

			return lista != null ? lista : new ArrayList<String>();

		} catch (Exception e) {
			log.error("=>ERRORE ricavaMailSovraordinati() : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	// mce 4 metodi comuni refacotr
	//begin

	


	public List<RichiedenteCDRBean> getRichiedenteCDR(RichiedenteCDRFinder richiedenteCDRFinder){
		log.info( "IN: SIGADaoImplJpaNew -> getRichiedenteCDR");

		try {
			String sql = "SELECT CDR.CODICE_CDR, CDR.CF_RESPONSABILE_HR, CDR.CF_RICHIEDENTE, " +
					"CDR.CF_AUTORIZZATORE_I_LIV, UTENTE_HR.NOME, UTENTE_HR.COGNOME, UTENTE_HR.CODICE_CDR " +
					"FROM RICHIEDENTE_CDR CDR " +
					"LEFT JOIN UTENTI UTENTE_HR ON UTENTE_HR.CODICE_FISCALE = CDR.CF_RESPONSABILE_HR " +
					"WHERE CDR.CODICE_CDR = ? " +
					"AND (CDR.DATA_FINE_VAL IS NULL OR CDR.DATA_FINE_VAL > SYSDATE)";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, richiedenteCDRFinder.getCodiceCDR());

			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();
			List<RichiedenteCDRBean> richiedenteList = new java.util.ArrayList<>();

			for (Object[] row : results) {
				RichiedenteCDRBean richiedente = new RichiedenteCDRBean();
				richiedente.setCodiceCDR((String) row[0]);
				richiedente.setCfResponsabileHR((String) row[1]);
				richiedente.setCfRichiedente((String) row[2]);
				richiedente.setCfAutorizzatoreILiv((String) row[3]);
				// Note: NOME, COGNOME, CODICE_CDR from UTENTE_HR are not mapped in RichiedenteCDRBean
				// They might be additional fields or used elsewhere
				richiedenteList.add(richiedente);
			}

			return richiedenteList;
		} catch (Exception e) {
			log.error( "Error in getRichiedenteCDR: " + e.getMessage());
			throw e;
		}
	}


	


	
@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void revocaAutorizzatoreDiUnGruppoDiOperatori(String cfPrecedente, String cfAltro, String motivoRevoca, String cfUtenteInSessione){
		log.info( "IN: SIGADaoImplJpaNew -> revocaAutorizzatoreDiUnGruppoDiOperatori");

		try {
			// 1. Cancel non-authorized requests where cf_richiedente = cfPrecedente and cf_autorizzatore_1 = cfAltro
			String annullamentoQuery = "UPDATE REGISTRO_RICHIESTE RR SET " +
					"RR.DATA_CHIUSURA_RICHIESTA = CURRENT_TIMESTAMP, " +
					"RR.STATO_RICHIESTA = 'ANN' " +
					"WHERE RR.CDR_RICHIEDENTE IS NULL " +
					"AND RR.CF_ARCHIVIAZIONE = ? " +
					"AND RR.CF_AUTORIZZATORE_1 = ? " +
					"AND (RR.DATA_CHIUSURA_RICHIESTA IS NULL OR RR.DATA_CHIUSURA_RICHIESTA > SYSDATE) " +
					"AND RR.DATA_ESITO_FINALE IS NULL";

			entityManager.createNativeQuery(annullamentoQuery)
					.setParameter(1, cfPrecedente)
					.setParameter(2, cfAltro)
					.executeUpdate();

			// 2. Update registro_richieste to set CF_ARCHIVIAZIONE and CDR_RICHIEDENTE
			String updateRegistroQuery = "UPDATE REGISTRO_RICHIESTE RR SET " +
					"RR.CF_ARCHIVIAZIONE = (" +
					"    SELECT RCDR.CF_RICHIEDENTE " +
					"    FROM RICHIEDENTE_CDR RCDR " +
					"    WHERE RR.CDR_UTENTE = RCDR.CODICE_CDR " +
					"    AND (RCDR.DATA_FINE_VAL IS NULL OR RCDR.DATA_FINE_VAL > SYSDATE)" +
					"), " +
					"RR.CDR_RICHIEDENTE = RR.CDR_UTENTE " +
					"WHERE (" +
					"    RR.CDR_ARCHIVIAZIONE IS NULL " +
					"    AND RR.CF_ARCHIVIAZIONE = ? " +
					"    AND RR.CF_AUTORIZZATORE_1 = ? " +
					"    AND (RR.DATA_CHIUSURA_RICHIESTA IS NULL OR RR.DATA_CHIUSURA_RICHIESTA > SYSDATE)" +
					")";

			entityManager.createNativeQuery(updateRegistroQuery)
					.setParameter(1, cfAltro)
					.setParameter(2, cfPrecedente)
					.executeUpdate();

			// 3. Update associaz_oper_ricchi_autor to set DATA_FINE_VAL
			String updateAssociazioniQuery = "UPDATE ASSOCIAZ_OPER_RICHI_AUTOR ASSOCIAZIONE SET " +
					"ASSOCIAZIONE.DATA_FINE_VAL = SYSDATE " +
					"WHERE ASSOCIAZIONE.CODICE_FISCALE_RICHIEDENTE = ? " +
					"AND ASSOCIAZIONE.CODICE_FISCALE_AUTORIZZATORE = ? " +
					"AND (ASSOCIAZIONE.DATA_FINE_VAL IS NULL OR ASSOCIAZIONE.DATA_FINE_VAL > SYSDATE)";

			entityManager.createNativeQuery(updateAssociazioniQuery)
					.setParameter(1, cfAltro)
					.setParameter(2, cfPrecedente)
					.executeUpdate();

		} catch (Exception e) {
			log.error( "Error in revocaAutorizzatoreDiUnGruppoDiOperatori: " + e.getMessage(), e);
			throw e;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void sostituzioneAutorizzatoreDiUnGruppoDiOperatori(String cfPrecedente, String cfNuovo, String cfAltro, String cfUtenteInSessione){
		log.info( "IN: SIGADaoImplJpaNew -> sostituzioneAutorizzatoreDiUnGruppoDiOperatori");

		try {
			// 1. Update registro_richieste to set new authorizer and clear presa_in_carico
			String updateRegistroQuery = "UPDATE REGISTRO_RICHIESTE REGISTRO SET " +
					"REGISTRO.CF_AUTORIZZATORE_1 = ?, " +
					"REGISTRO.CF_PRESA_IN_CARICO = NULL " +
					"WHERE (REGISTRO.DATA_CHIUSURA_RICHIESTA IS NULL OR REGISTRO.DATA_CHIUSURA_RICHIESTA > SYSDATE) " +
					"AND REGISTRO.CF_AUTORIZZATORE_1 = ? " +
					"AND REGISTRO.CF_ARCHIVIAZIONE = ? " +
					"AND REGISTRO.STATO_RICHIESTA = 'INS'";

			entityManager.createNativeQuery(updateRegistroQuery)
					.setParameter(1, cfNuovo)
					.setParameter(2, cfPrecedente)
					.setParameter(3, cfAltro)
					.executeUpdate();

			// 2. Get associations that need to be updated
			String selectAssociazioniQuery = "SELECT " +
					"CODICE_FISCALE_OPERATORE, " +
					"CODICE_CDR_OPERATORE, " +
					"CODICE_FISCALE_RICHIEDENTE, " +
					"CODICE_CDR_RICHIEDENTE, " +
					"CODICE_FISCALE_AUTORIZZATORE, " +
					"CODICE_CDR_AUTORIZZATORE " +
					"FROM ASSOCIAZ_OPER_RICHI_AUTOR " +
					"WHERE DATA_FINE_VAL IS NULL " +
					"AND CODICE_FISCALE_RICHIEDENTE = ? " +
					"AND CODICE_FISCALE_AUTORIZZATORE = ?";

			@SuppressWarnings("unchecked")
			List<Object[]> associazioni = entityManager.createNativeQuery(selectAssociazioniQuery)
					.setParameter(1, cfAltro)
					.setParameter(2, cfPrecedente)
					.getResultList();

			// 3. For each association, insert new record with new authorizer
			String insertAssociazioneQuery = "INSERT INTO ASSOCIAZ_OPER_RICHI_AUTOR (" +
					"CODICE_FISCALE_OPERATORE, " +
					"CODICE_CDR_OPERATORE, " +
					"CODICE_FISCALE_RICHIEDENTE, " +
					"CODICE_CDR_RICHIEDENTE, " +
					"CODICE_FISCALE_AUTORIZZATORE, " +
					"CODICE_CDR_AUTORIZZATORE, " +
					"DATA_INIZIO_VAL, " +
					"DATA_FINE_VAL, " +
					"ID_AUDIT_INIZIO, " +
					"CODICE_FISCALE_INIZIO, " +
					"ID_AUDIT_FINE, " +
					"CODICE_FISCALE_FINE) " +
					"VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, null, ?, ?, null, null)";

			for (Object[] ass : associazioni) {
				entityManager.createNativeQuery(insertAssociazioneQuery)
						.setParameter(1, ass[0]) // cfOperatore
						.setParameter(2, ass[1]) // codiceCdr
						.setParameter(3, ass[2]) // cfRichiedente
						.setParameter(4, ass[3]) // cdrRichiedente
						.setParameter(5, cfNuovo) // cfAutorizzatore (new)
						.setParameter(6, ass[5]) // cdrAutorizzatore
						.setParameter(7, 0) // idAuditInizio (placeholder)
						.setParameter(8, cfUtenteInSessione) // cfInizio
						.executeUpdate();
			}

			// 4. Update old associations to set DATA_FINE_VAL
			String updateAssociazioniQuery = "UPDATE ASSOCIAZ_OPER_RICHI_AUTOR SET " +
					"DATA_FINE_VAL = SYSDATE " +
					"WHERE DATA_FINE_VAL IS NULL " +
					"AND CODICE_FISCALE_RICHIEDENTE = ? " +
					"AND CODICE_FISCALE_AUTORIZZATORE = ?";

			entityManager.createNativeQuery(updateAssociazioniQuery)
					.setParameter(1, cfAltro)
					.setParameter(2, cfPrecedente)
					.executeUpdate();

		} catch (Exception e) {
			log.error( "Error in sostituzioneAutorizzatoreDiUnGruppoDiOperatori: " + e.getMessage(), e);
			throw e;
		}
	}


	public void ripristinoAutorizzatoreDaHR(String cfPrecedente, String cdr, String motivoRevoca, UtenteBean utente){
		log.info( "IN: SIGADaoImplJpaNew -> ripristinoAutorizzatoreDaHR");

		// This method involves complex business logic for restoring authorizers from HR
		// It requires analysis of the original implementation which includes:
		// 1. Finding requests with specific criteria
		// 2. Determining new authorizers using getAutorizzatore_I_Liv_Per_Ripristino
		// 3. Updating requests with new authorizers
		// 4. Updating richiedente_cdr table
		// 5. Creating audit records
		// This is a complex multi-step process that requires careful analysis of dependencies

		throw new UnsupportedOperationException("Method ripristinoAutorizzatoreDaHR requires complex business logic analysis - not yet migrated to JPA");
	}


	public void sostituzioneDellAutorizzatoreDiUnCDR(String cfPrecedente, String cfNuovo, String cdr, String motivoRevoca, UtenteBean utente){
		log.info( "IN: SIGADaoImplJpaNew -> sostituzioneDellAutorizzatoreDiUnCDR");

		// This method involves complex business logic for substituting authorizers of a CDR
		// It requires analysis of the original implementation which includes:
		// 1. Updating registro_richieste with new authorizer
		// 2. Creating audit records
		// 3. Updating richiedente_cdr table
		// 4. Setting flags on users table
		// This is a complex multi-step process that requires careful analysis of dependencies

		throw new UnsupportedOperationException("Method sostituzioneDellAutorizzatoreDiUnCDR requires complex business logic analysis - not yet migrated to JPA");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void revocaRichiedentiDiUnGruppoDiOperatori(String cfPrecedente, String cfAltro){
		log.info( "IN: SIGADaoImplJpaNew -> revocaRichiedentiDiUnGruppoDiOperatori");

		try {
			// 1. Cancel non-authorized requests where cf_richiedente = cfPrecedente and cf_autorizzatore_1 = cfAltro
			String annullamentoQuery = "UPDATE REGISTRO_RICHIESTE RR SET " +
					"RR.DATA_CHIUSURA_RICHIESTA = CURRENT_TIMESTAMP, " +
					"RR.STATO_RICHIESTA = 'ANN' " +
					"WHERE RR.CDR_RICHIEDENTE IS NULL " +
					"AND RR.CF_ARCHIVIAZIONE = ? " +
					"AND RR.CF_AUTORIZZATORE_1 = ? " +
					"AND (RR.DATA_CHIUSURA_RICHIESTA IS NULL OR RR.DATA_CHIUSURA_RICHIESTA > SYSDATE) " +
					"AND RR.DATA_ESITO_FINALE IS NULL";

			entityManager.createNativeQuery(annullamentoQuery)
					.setParameter(1, cfPrecedente)
					.setParameter(2, cfAltro)
					.executeUpdate();

			// 2. Update registro_richieste to set CF_ARCHIVIAZIONE and CDR_RICHIEDENTE
			String updateRegistroQuery = "UPDATE REGISTRO_RICHIESTE RR SET " +
					"RR.CF_ARCHIVIAZIONE = (" +
					"    SELECT RCDR.CF_RICHIEDENTE " +
					"    FROM RICHIEDENTE_CDR RCDR " +
					"    WHERE RR.CDR_UTENTE = RCDR.CODICE_CDR " +
					"    AND (RCDR.DATA_FINE_VAL IS NULL OR RCDR.DATA_FINE_VAL > SYSDATE)" +
					"), " +
					"RR.CDR_RICHIEDENTE = RR.CDR_UTENTE " +
					"WHERE (" +
					"    RR.CDR_ARCHIVIAZIONE IS NULL " +
					"    AND RR.CF_ARCHIVIAZIONE = ? " +
					"    AND RR.CF_AUTORIZZATORE_1 = ? " +
					"    AND (RR.DATA_CHIUSURA_RICHIESTA IS NULL OR RR.DATA_CHIUSURA_RICHIESTA > SYSDATE)" +
					")";

			entityManager.createNativeQuery(updateRegistroQuery)
					.setParameter(1, cfPrecedente)
					.setParameter(2, cfAltro)
					.executeUpdate();

			// 3. Update associaz_oper_ricchi_autor to set DATA_FINE_VAL
			String updateAssociazioniQuery = "UPDATE ASSOCIAZ_OPER_RICHI_AUTOR ASSOCIAZIONE SET " +
					"ASSOCIAZIONE.DATA_FINE_VAL = SYSDATE " +
					"WHERE ASSOCIAZIONE.CODICE_FISCALE_RICHIEDENTE = ? " +
					"AND ASSOCIAZIONE.CODICE_FISCALE_AUTORIZZATORE = ? " +
					"AND (ASSOCIAZIONE.DATA_FINE_VAL IS NULL OR ASSOCIAZIONE.DATA_FINE_VAL > SYSDATE)";

			entityManager.createNativeQuery(updateAssociazioniQuery)
					.setParameter(1, cfPrecedente)
					.setParameter(2, cfAltro)
					.executeUpdate();

		} catch (Exception e) {
			log.error( "Error in revocaRichiedentiDiUnGruppoDiOperatori: " + e.getMessage(), e);
			throw e;
		}
	}


	public void sostituzioneRichiedenteDiUnGruppoDiOperatori(String cfPrecedente, String cfNuovo, String cfAltro){
		log.info( "IN: SIGADaoImplJpaNew -> sostituzioneRichiedenteDiUnGruppoDiOperatori");

		// TODO: Implement complex business logic for substituting requesters for a group of operators
		// This likely involves updating multiple tables and complex business rules
		// SQL queries not found in XML files - requires analysis of original implementation
		throw new UnsupportedOperationException("Method not migrated to JPA yet - requires complex business logic analysis");
	}


	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void ripristinoResponsabileHR(String cfPrecedente, String cdr, String motivoRevoca, UtenteBean utente) throws SQLException {
		try {
			String cfUtenteInSessione = utente.getCodFiscaleUtente();

			Object[] respRow = (Object[]) entityManager.createNativeQuery(
				"SELECT CF_RESPONSABILE_HR, DATA_INIZIO_VAL, DATA_FINE_VAL FROM RICHIEDENTE_CDR " +
				"WHERE CODICE_CDR = ?1 AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)")
				.setParameter(1, cdr).getSingleResult();
			String cfResponsabileCDR = (String) respRow[0];
			Date dataInizioValResp = (Date) respRow[1];
			Date dataFineValResp = (Date) respRow[2];

			if (cfResponsabileCDR != null && !cfResponsabileCDR.equals(cfPrecedente)) {
				entityManager.createNativeQuery(
					"UPDATE REGISTRO_RICHIESTE SET DATA_CHIUSURA_RICHIESTA = CURRENT_TIMESTAMP, STATO_RICHIESTA = 'ANN' " +
					"WHERE (DATA_CHIUSURA_RICHIESTA IS NULL OR DATA_CHIUSURA_RICHIESTA > SYSDATE) " +
					"AND CDR_ARCHIVIAZIONE = ?1 AND CF_ARCHIVIAZIONE = ?2 AND DATA_ESITO_FINALE IS NULL")
					.setParameter(1, cdr).setParameter(2, cfPrecedente).executeUpdate();
			}

			Object[] richRow = (Object[]) entityManager.createNativeQuery(
				"SELECT CDR.CODICE_CDR, CDR.CF_RESPONSABILE_HR, CDR.CF_RICHIEDENTE, CDR.CF_AUTORIZZATORE_I_LIV " +
				"FROM RICHIEDENTE_CDR CDR " +
				"WHERE CDR.CODICE_CDR = ?1 AND (CDR.DATA_FINE_VAL IS NULL OR CDR.DATA_FINE_VAL > SYSDATE)")
				.setParameter(1, cdr).getSingleResult();
			String cfResponsabileHR = (String) richRow[1];
			String cfRichiedente = (String) richRow[2];
			String cfAutorizzatoreILiv = (String) richRow[3];

			if (!(cfUtenteInSessione == null || "".equals(cfUtenteInSessione))) {
				String descrizioneCDR = (String) entityManager.createNativeQuery(
					"SELECT DESCRIZIONE FROM CDR WHERE CODICE_CDR = ?1 AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)")
					.setParameter(1, cdr).getSingleResult();

				String richiedenteVecchioStr = "non definito";
				OperatoreBean richiedenteOp = getDatiUtente2(cfRichiedente);
				if (richiedenteOp != null) {
					richiedenteVecchioStr = richiedenteOp.toStringCognomeNomeCF();
				}
				OperatoreBean responsabileOp = getDatiUtente2(cfResponsabileHR);

				String testoAudit = Constants.RI + " del " + descrizioneCDR + " Ripristino da: " +
					richiedenteVecchioStr + " a " + responsabileOp.toStringCognomeNomeCF();

				String ruoloOp = "AMMINISTRATORE_CENTRALE";
				String cdrRuoloOp = null;
				List<CDRBean> gerarchia = getGerarchiaCdR(cdr);
				List<String> codiciAmmLocale = getCodiceAmmLocale(utente.getCodFiscaleUtente());
				if (gerarchia != null) {
					Map<String, String> ruolo = metodiComuni.getRuoloAmministatore(utente, gerarchia, codiciAmmLocale);
					if (ruolo != null) {
						for (Map.Entry<String, String> entry : ruolo.entrySet()) {
							ruoloOp = entry.getKey();
							cdrRuoloOp = entry.getValue();
						}
					}
				}

				int insertedID = ((Number) entityManager.createNativeQuery(
					"SELECT PROG_AUDIT.nextval FROM dual").getSingleResult()).intValue();
				entityManager.createNativeQuery(
					"INSERT INTO AUDIT_OPERAZIONI (ID_AUDIT, CODICE_FISCALE, CDR_AMMINISTRATORE, DATA_ORA, " +
					"TABELLA_AGGIORNATA, TESTO, RUOLO_OPERAZIONE, CDR_RUOLO) VALUES (?1, ?2, ?3, SYSDATE, ?4, ?5, ?6, ?7)")
					.setParameter(1, insertedID).setParameter(2, cfUtenteInSessione).setParameter(3, cdr)
					.setParameter(4, "RICHIEDENTE_CDR").setParameter(5, testoAudit)
					.setParameter(6, ruoloOp).setParameter(7, cdrRuoloOp).executeUpdate();

				entityManager.createNativeQuery(
					"UPDATE RICHIEDENTE_CDR SET MOTIVAZIONE_REVOCA = ?1, DATA_FINE_VAL = SYSDATE, " +
					"ID_AUDIT_FINE = ?2, CODICE_FISCALE_FINE = ?3 WHERE CODICE_CDR = ?4 AND DATA_FINE_VAL IS NULL")
					.setParameter(1, motivoRevoca).setParameter(2, insertedID)
					.setParameter(3, cfUtenteInSessione).setParameter(4, cdr).executeUpdate();

				entityManager.createNativeQuery(
					"INSERT INTO RICHIEDENTE_CDR (CODICE_CDR, CF_RESPONSABILE_HR, CF_RICHIEDENTE, " +
					"CF_AUTORIZZATORE_I_LIV, DATA_INIZIO_VAL, ID_AUDIT_INIZIO, CODICE_FISCALE_INIZIO) " +
					"VALUES (?1, ?2, ?3, ?4, SYSDATE, ?5, ?6)")
					.setParameter(1, cdr).setParameter(2, cfResponsabileHR).setParameter(3, cfResponsabileHR)
					.setParameter(4, cfAutorizzatoreILiv).setParameter(5, insertedID)
					.setParameter(6, cfUtenteInSessione).executeUpdate();

			} else {
				Date dataOraDB = (Date) entityManager.createNativeQuery(
					"SELECT SYSDATE FROM DUAL").getSingleResult();
				boolean dateUguali = Utils.dateStringFromDate(dataInizioValResp)
					.equals(Utils.dateStringFromDate(dataOraDB));

				if (dateUguali && dataFineValResp == null) {
					entityManager.createNativeQuery(
						"UPDATE RICHIEDENTE_CDR SET CF_RICHIEDENTE = ?1 WHERE CODICE_CDR = ?2 AND DATA_FINE_VAL IS NULL")
						.setParameter(1, cfResponsabileHR).setParameter(2, cdr).executeUpdate();
				}
				if (!dateUguali || dataFineValResp != null) {
					entityManager.createNativeQuery(
						"UPDATE RICHIEDENTE_CDR SET MOTIVAZIONE_REVOCA = ?1, DATA_FINE_VAL = SYSDATE, " +
						"CODICE_FISCALE_FINE = ?2 WHERE CODICE_CDR = ?3 AND DATA_FINE_VAL IS NULL")
						.setParameter(1, motivoRevoca).setParameter(2, cfUtenteInSessione)
						.setParameter(3, cdr).executeUpdate();

					entityManager.createNativeQuery(
						"INSERT INTO RICHIEDENTE_CDR (CODICE_CDR, CF_RESPONSABILE_HR, CF_RICHIEDENTE, " +
						"CF_AUTORIZZATORE_I_LIV, DATA_INIZIO_VAL, CODICE_FISCALE_INIZIO) " +
						"VALUES (?1, ?2, ?3, ?4, SYSDATE, ?5)")
						.setParameter(1, cdr).setParameter(2, cfResponsabileHR).setParameter(3, cfResponsabileHR)
						.setParameter(4, cfAutorizzatoreILiv).setParameter(5, cfUtenteInSessione).executeUpdate();
				}
			}

			entityManager.createNativeQuery(
				"UPDATE UTENTI SET E_MAIL = E_MAIL, RICHIEDENTE = ?1 WHERE CODICE_FISCALE = ?2 AND RICHIEDENTE != ?1")
				.setParameter(1, "SI").setParameter(2, cfResponsabileHR).executeUpdate();

			entityManager.createNativeQuery(
				"UPDATE REGISTRO_RICHIESTE SET CF_ARCHIVIAZIONE = ?1 WHERE DATA_CHIUSURA_RICHIESTA IS NULL " +
				"AND DATA_ESITO_FINALE IS NOT NULL AND CDR_ARCHIVIAZIONE = ?2 AND CF_ARCHIVIAZIONE = ?3")
				.setParameter(1, cfResponsabileHR).setParameter(2, cdr).setParameter(3, cfPrecedente).executeUpdate();

		} catch (Exception e) {
			log.error("Errore nel metodo ripristinoResponsabileHR", e);
			throw new SQLException(e);
		}
	}

	private OperatoreBean getDatiUtente2(String cf) {
		try {
			Object[] row = (Object[]) entityManager.createNativeQuery(
				"SELECT UT.NOME, UT.COGNOME, UT.CODICE_FISCALE FROM UTENTI_UNIFICATI UT, CDR CDR " +
				"WHERE UT.CODICE_FISCALE = ?1 AND CDR.CODICE_CDR = UT.CODICE_CDR " +
				"AND (UT.DATA_FINE_VAL IS NULL OR UT.DATA_FINE_VAL > SYSDATE)")
				.setParameter(1, cf).getSingleResult();
			OperatoreBean bean = new OperatoreBean();
			bean.setNome((String) row[0]);
			bean.setCognome((String) row[1]);
			bean.setCf((String) row[2]);
			return bean;
		} catch (jakarta.persistence.NoResultException e) {
			return null;
		} catch (Exception e) {
			log.error("Errore nel metodo getDatiUtente2", e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CDRBean> getGerarchiaCdR(String cdr) {
		List<CDRBean> result = new ArrayList<CDRBean>();
		try {
			List<Object[]> rows = entityManager.createNativeQuery(
				"SELECT VERTICE.CODICE_CDR, RCUS.CODICE_STRUTTURA FROM " +
				"(SELECT ROWNUM AS NUMRIGA, C.* FROM CDR C " +
				"WHERE (C.DATA_FINE_VAL IS NULL OR C.DATA_FINE_VAL > SYSDATE) " +
				"START WITH C.CODICE_CDR = ?1 " +
				"CONNECT BY C.CODICE_CDR = PRIOR C.CODICE_CDR_PADRE_GERARCHIA " +
				"ORDER BY ROWNUM DESC) VERTICE, RELAZIONE_CDR_UFFICIO_STRUT RCUS " +
				"WHERE VERTICE.CODICE_CDR = RCUS.CODICE_CDR " +
				"AND (RCUS.DATA_FINE_VAL IS NULL OR RCUS.DATA_FINE_VAL > SYSDATE) " +
				"ORDER BY NUMRIGA DESC")
				.setParameter(1, cdr).getResultList();
			for (Object[] row : rows) {
				CDRBean bean = new CDRBean();
				bean.setCodiceCDR((String) row[0]);
				bean.setCodiceStruttura((String) row[1]);
				result.add(bean);
			}
		} catch (Exception e) {
			log.error("Errore nel metodo getGerarchiaCdR", e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<String> getCodiceAmmLocale(String cf) {
		try {
			return entityManager.createNativeQuery(
				"SELECT DISTINCT RCUS.CODICE_STRUTTURA FROM PROFILI_ATTIVI_UTENTE PA, RELAZIONE_CDR_UFFICIO_STRUT RCUS " +
				"WHERE (PA.CF_UTENTE = ?1 AND (PA.DATA_FINE_VAL IS NULL OR PA.DATA_FINE_VAL > SYSDATE)) " +
				"AND PA.CODICE_PROFILO = 'ESG_AMM_Locale_3' AND RCUS.CODICE_CDR = PA.CODICE_CDR " +
				"AND (RCUS.DATA_FINE_VAL IS NULL OR RCUS.DATA_FINE_VAL > SYSDATE)")
				.setParameter(1, cf).getResultList();
		} catch (Exception e) {
			log.error("Errore nel metodo getCodiceAmmLocale", e);
			return new ArrayList<String>();
		}
	}


	public void sostituzioneDelRichiedenteDiUnCDR(String cfPrecedente, String cfNuovo, String cdr, String motivoRevoca, UtenteBean utente){
		log.info( "IN: SIGADaoImplJpaNew -> sostituzioneDelRichiedenteDiUnCDR");

		// TODO: Implement complex business logic for substituting requester of a CDR
		// This likely involves updating multiple tables and complex business rules
		// SQL queries not found in XML files - requires analysis of original implementation
		throw new UnsupportedOperationException("Method not migrated to JPA yet - requires complex business logic analysis");
	}


	


	public StrutturaIterEntityBean getStrutturaIterEntityBean(BaseFinder in){
		log.info( "IN: SIGADaoImplJpaNew -> getStrutturaIterEntityBean");

		// TODO: Implement the actual business logic for getStrutturaIterEntityBean
		// SQL queries not found in XML files - requires analysis of original implementation
		throw new UnsupportedOperationException("Method not migrated to JPA yet - requires business logic analysis");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificaEntrambi(String cfPrecedenteRichiedente, String cfNuovoRichiedente,
								 String cfPrecedenteAutorizzatore, String cfNuovoAutorizzatore,
								 String cdr, UtenteBean utente)  {
		log.info( "IN: SIGADaoImplJpaNew -> modificaEntrambi");

		try {
			String cfUtente = utente.getCodFiscaleUtente();

			String getRichiedenteSql = "SELECT CDR.CODICE_CDR, CDR.CF_RESPONSABILE_HR, CDR.CF_RICHIEDENTE, " +
					"CDR.CF_AUTORIZZATORE_I_LIV " +
					"FROM RICHIEDENTE_CDR CDR " +
					"WHERE CDR.CODICE_CDR = ? " +
					"AND (CDR.DATA_FINE_VAL IS NULL OR CDR.DATA_FINE_VAL > SYSDATE)";

			Query getRichiedenteQuery = entityManager.createNativeQuery(getRichiedenteSql);
			getRichiedenteQuery.setParameter(1, cdr);

			@SuppressWarnings("unchecked")
			List<Object[]> richiedenteResult = getRichiedenteQuery.getResultList();

			if (richiedenteResult.isEmpty()) {
				throw new RuntimeException("No richiedente found for CDR: " + cdr);
			}

			Object[] richiedenteRow = richiedenteResult.get(0);
			String cfResponsabileHR = (String) richiedenteRow[1];

			String annullamentoSql = "UPDATE REGISTRO_RICHIESTE REGISTRO " +
					"SET REGISTRO.DATA_CHIUSURA_RICHIESTA = CURRENT_TIMESTAMP, " +
					"    REGISTRO.STATO_RICHIESTA = 'ANN' " +
					"WHERE (REGISTRO.DATA_CHIUSURA_RICHIESTA IS NULL " +
					"       OR REGISTRO.DATA_CHIUSURA_RICHIESTA > SYSDATE) " +
					"AND REGISTRO.CDR_ARCHIVIAZIONE = ? " +
					"AND REGISTRO.CF_ARCHIVIAZIONE = ? " +
					"AND REGISTRO.DATA_ESITO_FINALE IS NULL";

			entityManager.createNativeQuery(annullamentoSql)
					.setParameter(1, cdr)
					.setParameter(2, cfPrecedenteRichiedente)
					.executeUpdate();

			String aggiornamentoRegistroSql = "UPDATE REGISTRO_RICHIESTE REGISTRO " +
					"SET REGISTRO.CF_AUTORIZZATORE_1 = ?, " +
					"    REGISTRO.CF_PRESA_IN_CARICO = NULL " +
					"WHERE (REGISTRO.DATA_CHIUSURA_RICHIESTA IS NULL " +
					"       OR REGISTRO.DATA_CHIUSURA_RICHIESTA > SYSDATE) " +
					"AND REGISTRO.CDR_AUTORIZZATORE_1 = ? " +
					"AND REGISTRO.CF_AUTORIZZATORE_1 = ? " +
					"AND REGISTRO.CF_ARCHIVIAZIONE = ? " +
					"AND REGISTRO.STATO_RICHIESTA = 'INS'";

			entityManager.createNativeQuery(aggiornamentoRegistroSql)
					.setParameter(1, cfNuovoAutorizzatore)
					.setParameter(2, cdr)
					.setParameter(3, cfPrecedenteAutorizzatore)
					.setParameter(4, cfPrecedenteRichiedente)
					.executeUpdate();

			String aggiornaFlagAutorizzatoreSql = "UPDATE UTENTI SET AUTORIZZATORE_I_LIV = ? " +
					"WHERE CODICE_FISCALE = ? " +
					"AND AUTORIZZATORE_I_LIV != ?";

			entityManager.createNativeQuery(aggiornaFlagAutorizzatoreSql)
					.setParameter(1, "SI")
					.setParameter(2, cfNuovoAutorizzatore)
					.setParameter(3, "SI")
					.executeUpdate();

			String aggiornaRichiedenteCdrSql = "UPDATE RICHIEDENTE_CDR " +
					"SET MOTIVAZIONE_REVOCA = 'R', " +
					"    DATA_FINE_VAL = SYSDATE, " +
					"    CODICE_FISCALE_FINE = ? " +
					"WHERE CODICE_CDR = ? " +
					"AND DATA_FINE_VAL IS NULL";

			entityManager.createNativeQuery(aggiornaRichiedenteCdrSql)
					.setParameter(1, cfUtente)
					.setParameter(2, cdr)
					.executeUpdate();

			String inserisciRichiedenteCdrSql = "INSERT INTO RICHIEDENTE_CDR (" +
					"CODICE_CDR, CF_RESPONSABILE_HR, CF_RICHIEDENTE, " +
					"CF_AUTORIZZATORE_I_LIV, DATA_INIZIO_VAL, " +
					"CODICE_FISCALE_INIZIO) " +
					"VALUES (?, ?, ?, ?, SYSDATE, ?)";

			entityManager.createNativeQuery(inserisciRichiedenteCdrSql)
					.setParameter(1, cdr)
					.setParameter(2, cfResponsabileHR)
					.setParameter(3, cfNuovoRichiedente)
					.setParameter(4, cfNuovoAutorizzatore)
					.setParameter(5, cfUtente)
					.executeUpdate();

			String aggiornaFlagRichiedenteSql = "UPDATE UTENTI SET RICHIEDENTE = ? " +
					"WHERE CODICE_FISCALE = ? " +
					"AND RICHIEDENTE != ?";

			entityManager.createNativeQuery(aggiornaFlagRichiedenteSql)
					.setParameter(1, "SI")
					.setParameter(2, cfNuovoRichiedente)
					.setParameter(3, "SI")
					.executeUpdate();

			String sostituzioneRichiedenteSql = "UPDATE REGISTRO_RICHIESTE " +
					"SET CF_ARCHIVIAZIONE = ? " +
					"WHERE DATA_CHIUSURA_RICHIESTA IS NULL " +
					"AND DATA_ESITO_FINALE IS NOT NULL " +
					"AND CDR_ARCHIVIAZIONE = ? " +
					"AND CF_ARCHIVIAZIONE = ?";

			entityManager.createNativeQuery(sostituzioneRichiedenteSql)
					.setParameter(1, cfNuovoRichiedente)
					.setParameter(2, cdr)
					.setParameter(3, cfPrecedenteRichiedente)
					.executeUpdate();

		} catch (Exception e) {
			log.error("Error in modificaEntrambi: " + e.getMessage(), e);
			throw new RuntimeException("Error modifying both requester and authorizer", e);
		}
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void ripristinaEntrambi(String cfPrecedenteRichiedente, String cfPrecedenteAutorizzatore,
								   String cfNuovoRichiedente, String cdr, UtenteBean utente)  {
		log.info( "IN: SIGADaoImplJpaNew -> ripristinaEntrambi");

		try {
			String cfUtenteLoggato = utente.getCodFiscaleUtente();

			String getCfResponsabileSql = "SELECT CF_RESPONSABILE_HR, DATA_INIZIO_VAL, DATA_FINE_VAL " +
					"FROM RICHIEDENTE_CDR " +
					"WHERE CODICE_CDR = ? " +
					"AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";

			Query getCfResponsabileQuery = entityManager.createNativeQuery(getCfResponsabileSql);
			getCfResponsabileQuery.setParameter(1, cdr);

			@SuppressWarnings("unchecked")
			List<Object[]> cfResponsabileResult = getCfResponsabileQuery.getResultList();

			if (cfResponsabileResult.isEmpty()) {
				throw new RuntimeException("No richiedente found for CDR: " + cdr);
			}

			Object[] cfRow = cfResponsabileResult.get(0);
			String cfResponsabileCDR = (String) cfRow[0];

			if (!cfResponsabileCDR.equals(cfPrecedenteRichiedente)) {
				String annullamentoSql = "UPDATE REGISTRO_RICHIESTE REGISTRO " +
						"SET REGISTRO.DATA_CHIUSURA_RICHIESTA = CURRENT_TIMESTAMP, " +
						"    REGISTRO.STATO_RICHIESTA = 'ANN' " +
						"WHERE (REGISTRO.DATA_CHIUSURA_RICHIESTA IS NULL " +
						"       OR REGISTRO.DATA_CHIUSURA_RICHIESTA > SYSDATE) " +
						"AND REGISTRO.CDR_ARCHIVIAZIONE = ? " +
						"AND REGISTRO.CF_ARCHIVIAZIONE = ? " +
						"AND REGISTRO.DATA_ESITO_FINALE IS NULL";

				entityManager.createNativeQuery(annullamentoSql)
						.setParameter(1, cdr)
						.setParameter(2, cfPrecedenteRichiedente)
						.executeUpdate();
			}

			String getRichiedenteSql = "SELECT CDR.CODICE_CDR, CDR.CF_RESPONSABILE_HR, CDR.CF_RICHIEDENTE, " +
					"CDR.CF_AUTORIZZATORE_I_LIV " +
					"FROM RICHIEDENTE_CDR CDR " +
					"WHERE CDR.CODICE_CDR = ? " +
					"AND (CDR.DATA_FINE_VAL IS NULL OR CDR.DATA_FINE_VAL > SYSDATE)";

			Query getRichiedenteQuery = entityManager.createNativeQuery(getRichiedenteSql);
			getRichiedenteQuery.setParameter(1, cdr);

			@SuppressWarnings("unchecked")
			List<Object[]> richiedenteResult = getRichiedenteQuery.getResultList();

			if (richiedenteResult.isEmpty()) {
				throw new RuntimeException("No richiedente found for CDR: " + cdr);
			}

			Object[] richiedenteRow = richiedenteResult.get(0);
			String cfRichiedente = (String) richiedenteRow[2];
			String cfResponsabileHR = (String) richiedenteRow[1];

			String aggiornaRichiedenteCdrSql = "UPDATE RICHIEDENTE_CDR " +
					"SET MOTIVAZIONE_REVOCA = 'R', " +
					"    DATA_FINE_VAL = SYSDATE, " +
					"    CODICE_FISCALE_FINE = ? " +
					"WHERE CODICE_CDR = ? " +
					"AND DATA_FINE_VAL IS NULL";

			entityManager.createNativeQuery(aggiornaRichiedenteCdrSql)
					.setParameter(1, cfUtenteLoggato)
					.setParameter(2, cdr)
					.executeUpdate();

			String inserisciRichiedenteCdrSql = "INSERT INTO RICHIEDENTE_CDR (" +
					"CODICE_CDR, CF_RESPONSABILE_HR, CF_RICHIEDENTE, " +
					"CF_AUTORIZZATORE_I_LIV, DATA_INIZIO_VAL, " +
					"CODICE_FISCALE_INIZIO) " +
					"VALUES (?, ?, ?, NULL, SYSDATE, ?)";

			entityManager.createNativeQuery(inserisciRichiedenteCdrSql)
					.setParameter(1, cdr)
					.setParameter(2, cfResponsabileHR)
					.setParameter(3, cfResponsabileHR)
					.setParameter(4, cfUtenteLoggato)
					.executeUpdate();

			String aggiornaFlagRichiedenteSql = "UPDATE UTENTI SET RICHIEDENTE = ? " +
					"WHERE CODICE_FISCALE = ? " +
					"AND RICHIEDENTE != ?";

			entityManager.createNativeQuery(aggiornaFlagRichiedenteSql)
					.setParameter(1, "SI")
					.setParameter(2, cfResponsabileHR)
					.setParameter(3, "SI")
					.executeUpdate();

			String sostituzioneRichiedenteSql = "UPDATE REGISTRO_RICHIESTE " +
					"SET CF_ARCHIVIAZIONE = ? " +
					"WHERE DATA_CHIUSURA_RICHIESTA IS NULL " +
					"AND DATA_ESITO_FINALE IS NOT NULL " +
					"AND CDR_ARCHIVIAZIONE = ? " +
					"AND CF_ARCHIVIAZIONE = ?";

			entityManager.createNativeQuery(sostituzioneRichiedenteSql)
					.setParameter(1, cfResponsabileHR)
					.setParameter(2, cdr)
					.setParameter(3, cfPrecedenteRichiedente)
					.executeUpdate();

			String getRegistroRichiestePerCaso2Sql = "SELECT ID_RICHIESTA, CDR_UTENTE " +
					"FROM REGISTRO_RICHIESTE " +
					"WHERE (DATA_CHIUSURA_RICHIESTA IS NULL " +
					"       OR DATA_CHIUSURA_RICHIESTA > SYSDATE) " +
					"AND STATO_RICHIESTA = 'INS' " +
					"AND CDR_AUTORIZZATORE_1 = ? " +
					"AND CF_AUTORIZZATORE_1 = ?";

			Query getRegistroQuery = entityManager.createNativeQuery(getRegistroRichiestePerCaso2Sql);
			getRegistroQuery.setParameter(1, cdr);
			getRegistroQuery.setParameter(2, cfPrecedenteAutorizzatore);

			@SuppressWarnings("unchecked")
			List<Object[]> registroResults = getRegistroQuery.getResultList();

			for (Object[] registro : registroResults) {
				String cdrUtente = (String) registro[1];

				String cambioAutorizzatoreSql = "UPDATE REGISTRO_RICHIESTE " +
						"SET CF_AUTORIZZATORE_1 = ?, " +
						"    CDR_AUTORIZZATORE_1 = ? " +
						"WHERE (DATA_CHIUSURA_RICHIESTA IS NULL " +
						"       OR DATA_CHIUSURA_RICHIESTA > SYSDATE) " +
						"AND STATO_RICHIESTA = 'INS' " +
						"AND CDR_AUTORIZZATORE_1 = ? " +
						"AND CF_AUTORIZZATORE_1 = ?";

				entityManager.createNativeQuery(cambioAutorizzatoreSql)
						.setParameter(1, cfResponsabileHR)
						.setParameter(2, cdr)
						.setParameter(3, cdr)
						.setParameter(4, cfPrecedenteAutorizzatore)
						.executeUpdate();
			}

		} catch (Exception e) {
			log.error("Error in ripristinaEntrambi: " + e.getMessage(), e);
			throw new RuntimeException("Error restoring both requester and authorizer", e);
		}
	}



	public CoerenzaPropertiesBean selectCostantiSigaBatchCoerenza() {
		log.info("IN: SIGADaoImplJpaNew -> selectCostantiSigaBatchCoerenza");

		CoerenzaPropertiesBean coerenzaProperties = new CoerenzaPropertiesBean();
		List<CostantiSiga> costanti = new java.util.ArrayList<CostantiSiga>();

		try {
			String sql = "SELECT CS.NOME_COSTANTE, CS.VALORE_COSTANTE, CS.NOTA, CS.DATA_ULTIMA_ELABORAZIONE " +
					"FROM COSTANTI_SIGA CS";

			Query query = entityManager.createNativeQuery(sql);
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();

			for (Object[] row : results) {
				CostantiSiga costantiSiga = new CostantiSiga();
				costantiSiga.setNome((String) row[0]);
				costantiSiga.setValore((String) row[1]);
				costantiSiga.setNota((String) row[2]);
				costantiSiga.setDataUltimaElaborazione((java.util.Date) row[3]);
				costanti.add(costantiSiga);
			}

			for (int i = 0; i < costanti.size(); i++) {
				CostantiSiga recordCostante = costanti.get(i);
				if (recordCostante.getNome().trim().equals("BatchScadenzeProfiliObsoleti")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setProfiliElaborabili(recordCostante.getValore());
					if (recordCostante.getDataUltimaElaborazione() != null)
						coerenzaProperties.setUltimaElaborazioneProfili(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(recordCostante.getDataUltimaElaborazione()));
				} else if (recordCostante.getNome().trim().equals("BatchScadenzeProfiliScadutiAdminGroup")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setAdminGroupElaborabili(recordCostante.getValore());
					if (recordCostante.getDataUltimaElaborazione() != null)
						coerenzaProperties.setUltimaElaborazioneAdminGroup(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(recordCostante.getDataUltimaElaborazione()));
				} else if (recordCostante.getNome().trim().equals("BatchScadenzeProfiliScadutiOperatore")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setProfiliOperatoreElaborabili(recordCostante.getValore());
					if (recordCostante.getDataUltimaElaborazione() != null)
						coerenzaProperties.setUltimaElaborazioneProfiliOperatore(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(recordCostante.getDataUltimaElaborazione()));
				} else if (recordCostante.getNome().trim().equals("BatchScadenzeVisibilitaScadute")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setVisibilitaElaborabili(recordCostante.getValore());
					if (recordCostante.getDataUltimaElaborazione() != null)
						coerenzaProperties.setUltimaElaborazioneVisibilita(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(recordCostante.getDataUltimaElaborazione()));
				} else if (recordCostante.getNome().trim().equals("BatchScadenzeTermineServizioOperatoriEsterni")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setEsterniElaborabili(recordCostante.getValore());
					if (recordCostante.getDataUltimaElaborazione() != null)
						coerenzaProperties.setUltimaElaborazioneEsterni(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(recordCostante.getDataUltimaElaborazione()));
				} else if (recordCostante.getNome().trim().equals("BatchScadenzeProfiliVisibilita")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setProfiliVisibilitaElaborabili(recordCostante.getValore());
					if (recordCostante.getDataUltimaElaborazione() != null)
						coerenzaProperties.setUltimaElaborazioneProfiliVisibilita(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(recordCostante.getDataUltimaElaborazione()));
				} else if (recordCostante.getNome().trim().equals("emailSegnalazioniSogei")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setEmailSegnalazioniSogei(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("emailAgenziaSegnalaCau")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setEmailSegnalazioniAgenzia(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("emailAgenziaSegnalaCea")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setEmailSegnalazioniAgenziaCea(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("emailAgenziaSegnalaHr")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setEmailSegnalazioniAgenziaHr(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("ElaborazioneCm")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setElaborazioneCM(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("ElaborazioneCmVisibilita")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setElaborazioneCMVis(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("ElaborazioneCmRichiesteVisibilita")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setElaborazioneCMRichVis(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("BatchScadenzeDeleghe")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setDelegheElaborabili(recordCostante.getValore());
					if (recordCostante.getDataUltimaElaborazione() != null)
						coerenzaProperties.setUltimaElaborazioneScadenzaDeleghe(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(recordCostante.getDataUltimaElaborazione()));
				} else if (recordCostante.getNome().trim().equals("CessazioneElaborabile")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setCessazioneElaborabile(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("CoerenzaCaElaborabile")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setCoerenzaCaElaborabile(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("CoerenzaCaTuttiUtenti")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setCoerenzaCaTuttiUtenti(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("CoerenzaCauElaborabile")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setCoerenzaCauElaborabile(recordCostante.getValore());
					if (recordCostante.getDataUltimaElaborazione() != null)
						coerenzaProperties.setUltimaElaborazioneCoerenzaCau(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(recordCostante.getDataUltimaElaborazione()));
				} else if (recordCostante.getNome().trim().equals("CoerenzaCauTuttiUtenti")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setCoerenzaCauTuttiUtenti(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("CoerenzaCodiceSecondoLivello")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setCoerenzaCodiceSecondoLivello(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("CoerenzaHrElaborabile")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setCoerenzaHrElaborabile(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("CoerenzaHrTuttiUtenti")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setCoerenzaHrTuttiUtenti(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("CoerenzaOaNumMaxRisposteErrate")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setCoerenzaOaNumMaxRisposteErrate(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("CoerenzaOaNumMaxUtenti")) {
					if (recordCostante.getValore() != null) //TODO MCE BONIFICA
						coerenzaProperties.setCoerenzaOaNumMaxUtenti(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("CoerenzaVerificaVisibilita")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setCoerenzaVerificaVisibilita(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("ElaborazioneAbilitazioniCdr")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setElaborazioneAbilitazCdr(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("ElaborazioneAbilitazioniOper")) {
					if (recordCostante.getValore() != null) {
						coerenzaProperties.setElaborazioneAbilitazOper(recordCostante.getValore());
					}
				} else if (recordCostante.getNome().trim().equals("FlagRegime")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setFlagRegime(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("MobilitaElaborabile")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setMobilitaElaborabile(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("StatisticaAbilitazioniOperatori")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setStatisticaAbilitazioniOperatori(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("CoerenzaVerificaExplicitEntitlement")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setCoerenzaVerificaExplicitEntitlement(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("CoerenzaAgenziaInvioEmail")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setCoerenzaAgenziaInvioEmail(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("RiorganizzazioniAggiornaCdrVisibilita")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setElaborazioneRiorganizzazioneAggiornaCdrVisibilita(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("RiorganizzazioniAggiornaCdrEsterni")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setElaborazioneRiorganizzazioneAggiornaCdrEsterni(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("RiorganizzazioneReportDisabilitazioni")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setElaborazioneRiorganizzazioneReportDisabilitazioni(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("ExportRuoliDiUnCdr")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setExportRuoliDiUnCdr(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("BatchMailScadenzeOperatoriEsterni")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setEmailScadenzeOpertoriEsterni(recordCostante.getValore());
				} else if (recordCostante.getNome().trim().equals("ElaborazioneRuoliOperatori")) {
					if (recordCostante.getValore() != null)
						coerenzaProperties.setElaborazioneRuoliOper(recordCostante.getValore());
				}
			}
		} catch (Exception e) {
			log.error("Error in selectCostantiSigaBatchCoerenza: " + e.getMessage(), e);
			throw e;
		}
		return coerenzaProperties;
	}


	public List<String> getAmmCentraleApp(String cf)  {
		log.info("IN: SIGAServiceJpaImpl -> getAmmCentraleApp");

		try {
			String sql =
					"SELECT"
							+ "		A.CODICE_APPLICAZIONE"
							+ "		FROM"
							+ "		AMMINISTRATORI_CENTRALI A"
							+ "		WHERE"
							+ "		CODICE_FISCALE = ?1 "
							+ "		AND (A.DATA_FINE_VAL IS NULL OR"
							+ "		A.DATA_FINE_VAL > SYSDATE)";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);

			@SuppressWarnings("unchecked")
			List<String> result = query.getResultList();

			return result != null ? result : new ArrayList<String>();

		} catch (Exception e) {
			log.error("=>ERRORE metodo getEmailAmmCentrali() : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}



	public String getEmailBatch()  {
		log.info("IN: SIGAServiceJpaImpl -> getEmailBatch");

		try {
			String sql = "SELECT EMAIL_SEGNALAZIONI_SOGEI FROM BATCH_HR";

			Query query = entityManager.createNativeQuery(sql);

			@SuppressWarnings("unchecked")
			String result = (String) query.getSingleResult();

			return result ;

		} catch (Exception e) {
			log.error("=>ERRORE metodo getEmailAmmCentrali() : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public List<TipoAbilitazioneBean> getTipoAbilitazione() {

		String sql = "SELECT DESCRIZIONE_PER_STAMPE, CODICE_ABILITAZIONE, TIPO_ABILITAZIONE "
				+" FROM TIPO_ABILITAZIONI ";


		Query query = entityManager.createNativeQuery(sql);

		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();

		return results.stream().map(row -> {
			TipoAbilitazioneBean bean = new TipoAbilitazioneBean();
			bean.setDescrizioneStampe((String)row[0]);
			bean.setCodiceAbilitazione((String)row[1]);
			bean.setTipoAbilitazione((String)row[2]);
			return bean;
		}).collect(Collectors.toList());
	}

	public  List<AmbitoBean> getDatiAmbitoApplicativo() {

		return  this.getDatiAmbitoApplicativo(null);


	}

	public List<AmbitoBean> getDatiAmbitoApplicativo(AmbitoFinder finder)  {
		log.info("IN: SIGADaoImplJpaNew -> getDatiAmbitoApplicativo");

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT AA.CODICE_AMBITO, AA.DESCRIZIONE, AA.SINCRONIZZATA_CAU, AA.DESCRIZIONE_CONCETTUALE " +
					"FROM AMBITO_APPLICATIVO AA " +
					"WHERE 1=1");

			if (finder !=null && finder.getCodAmbito() != null && !finder.getCodAmbito().trim().isEmpty()) {
				sql.append(" AND AA.CODICE_AMBITO = ?");
			}

			sql.append(" ORDER BY AA.CODICE_AMBITO");

			Query query = entityManager.createNativeQuery(sql.toString());

			int paramIndex = 1;
			if (finder !=null && finder.getCodAmbito() != null && !finder.getCodAmbito().trim().isEmpty()) {
				query.setParameter(paramIndex++, finder.getCodAmbito());
			}

			List<Object[]> results = query.getResultList();
			List<AmbitoBean> ambitoList = new ArrayList<>();
			for (Object[] row : results) {
				AmbitoBean bean = new AmbitoBean();
				bean.setCodiceAmbito((String) row[0]);
				bean.setDescrizione((String) row[1]);
				bean.setSincronizzatoCAU((String) row[2]);
				bean.setDescrizioneConcettuale((String) row[3]);
				ambitoList.add(bean);
			}
			return ambitoList;
		} catch (Exception e) {
			log.error("Error in getDatiAmbitoApplicativo: " + e.getMessage());
			throw e;
		}
	}

	public List<BatchAbilitazioniRichiestaEntity> getRichiesteAbilitazioniBatch(
			BatchAbilitazioniRichiestaFinder finder)  {
		log.info("IN: getRichiesteAbilitazioniBatch");
		try {
			String sql = "SELECT ID_RICHIESTA, NOME_RICHIESTA, DATA_RICHIESTA, DATA_PRODUZIONE, " +
					"DATA_ANNULLAMENTO, STATO_RICHIESTA, CF_UTENTE, FLAG_TUTTI_II_LIV, II_LIVELLO, " +
					"STRUTTURA, AMBITO_APPLICATIVO, TIPO_ABILITAZIONE " +
					"FROM BATCH_ABILITAZIONI_RICHIESTA " +
					"WHERE FUNZIONE = ? AND STATO_RICHIESTA = ? AND FLAG_ELAB_FALLITA <> 'SI'";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, finder.getFunzione());
			query.setParameter(2, finder.getStatoRichiesta());

			List<Object[]> results = query.getResultList();
			List<BatchAbilitazioniRichiestaEntity> entityList = new ArrayList<>();
			for (Object[] row : results) {
				entityList.add(mapToBatchAbilitazioniRichiestaEntity(row));
			}
			return entityList;
		} catch (Exception e) {
			log.error("Error in getRichiesteAbilitazioniBatch: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	private BatchAbilitazioniRichiestaEntity mapToBatchAbilitazioniRichiestaEntity(Object[] row) {
		BatchAbilitazioniRichiestaEntity entity = new BatchAbilitazioniRichiestaEntity();
		entity.setIdRichiesta(row[0] != null ? ((Number) row[0]).intValue() : null);
		entity.setNomeRichiesta((String) row[1]);
		entity.setDataRichesta((Timestamp) row[2]);
		entity.setDataProduzione((Timestamp) row[3]);
		entity.setDataAnnullamento((java.sql.Timestamp) row[4]);
		entity.setStatoRichiesta((String) row[5]);
		entity.setCfUtente((String) row[6]);
		entity.setFlagIILiv((String) row[7]);
		entity.setSecondoLiv((String) row[8]);
		entity.setStruttura((String) row[9]);
		entity.setAmbitoApplicativo(row[10] != null ? String.valueOf(((Number) row[0]).intValue()) : null);
		entity.setTipoAbilitazione((String) row[11]);
		return entity;
	}


	public String getValoreCostante(String nome)  {
		String result = "";

		String sql = "SELECT VALORE_COSTANTE FROM COSTANTI_SIGA WHERE NOME_COSTANTE = ?1";

		try {
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, nome);
			List<Object> resultList = query.getResultList();
			if (!resultList.isEmpty()) {
				result = (String) resultList.get(0);
			}
		} catch (Exception e) {
			log.error("Errore nel metodo getValoreCostante", e);
		}
		return result;
	}



	public List<CDRBean> getElencoCDRResp(CDRFinder finder)  {
		log.info("SigaDaoJpaImpl.getElencoCDRResp() - Implementing JPA version");

		try {
			String sql = "SELECT " +
					"2 AS PIPPO, " +
					"CDRESP.CODICE_CDR, " +
					"1 AS FLAG, " +
					"'CDR FIGLIO DEL CDR DI CUI E` RESPONSABILE' AS FLAG_DESCRIZIONE " +
					"FROM ( " +
					"    SELECT " +
					"        DISTINCT C.CODICE_CDR " +
					"    FROM CDR C, ( " +
					"        SELECT " +
					"            DISTINCT CODICE_CDR " +
					"        FROM RICHIEDENTE_CDR " +
					"        WHERE CF_RESPONSABILE_HR = ?1 " +
					"        AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE) " +
					"    ) RESP, " +
					"    RELAZIONE_CDR_UFFICIO_STRUT PADRE, " +
					"    RELAZIONE_CDR_UFFICIO_STRUT FIGLIO " +
					"    WHERE " +
					"        RESP.CODICE_CDR = PADRE.CODICE_CDR " +
					"        AND FIGLIO.CODICE_STRUTTURA = PADRE.CODICE_STRUTTURA " +
					"        AND (FIGLIO.DATA_FINE_VAL IS NULL OR FIGLIO.DATA_FINE_VAL > SYSDATE) " +
					"        AND C.CODICE_CDR = FIGLIO.CODICE_CDR " +
					"        AND (C.DATA_FINE_VAL IS NULL OR C.DATA_FINE_VAL > SYSDATE) " +
					"        START WITH C.CODICE_CDR_PADRE_GERARCHIA = RESP.CODICE_CDR " +
					"        CONNECT BY C.CODICE_CDR_PADRE_GERARCHIA = PRIOR C.CODICE_CDR " +
					"    MINUS " +
					"    SELECT DISTINCT CODICE_CDR " +
					"    FROM RICHIEDENTE_CDR " +
					"    WHERE " +
					"        CF_RICHIEDENTE = ?1 " +
					"        AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE) " +
					") CDRESP";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, finder.getCodFisUser());

			@SuppressWarnings("unchecked")
			List<Object[]> results = query.getResultList();

			List<CDRBean> resultList = new ArrayList<>();

			for (Object[] row : results) {
				CDRBean bean = new CDRBean();
				bean.setCodiceCDR((String) row[1]);
				bean.setFlag((String) row[2]);
				bean.setFlagDescrizione((String) row[3]);
				resultList.add(bean);
			}

			log.info("getElencoCDRResp() executed successfully with " + resultList.size() + " results");
			return resultList;

		} catch (NoResultException nre) {
			log.info("getElencoCDRResp() - No results found");
			return new ArrayList<>();
		} catch (Exception e) {
			log.error("getElencoCDRResp() - Exception: " + e.getMessage(), e);
			throw new RuntimeException("Error executing getElencoCDRResp", e);
		}
	}

	public List<String> getServiziUtente(String key)  {
		log.info("IN: SIGAServiceJpaImpl -> getAmmCentraleApp");

		try {
			String sql = "SELECT SERVIZI FROM RUOLO_SERVIZI WHERE  RUOLO = ?1";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, key);

			@SuppressWarnings("unchecked")
			List<String> result = query.getResultList();

			return result != null ? result : new ArrayList<String>();

		} catch (Exception e) {
			log.error("=>ERRORE metodo getEmailAmmCentrali() : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public OperatoreBean getDatiUtente(OperatoreFinder finder)  {
		OperatoreBean result = new OperatoreBean();

		String sql = "SELECT CODICE_FISCALE, NOME,COGNOME,TIPO_UTENTE, E_MAIL, CODICE_CDR,\n"
				+ "		AUTORIZZATORE_I_LIV, AUTORIZZATORE_II_LIV,\n"
				+ "		RICHIEDENTE,REVOCA_CAU,REVOCA_POSTAZIONE, CDR_DISALL_CAU_SIGA\n"
				+ "		FROM UTENTI\n"
				+ "		WHERE CODICE_FISCALE= ?1 AND DATA_FINE_VAL IS NULL";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, finder.getCfUtente());
			List<Object[]> resultList = query.getResultList();
			if (!resultList.isEmpty()) {
				Object[] row = resultList.get(0);
				result = mapToOperatoreBean(row);
			}
		return result;
	}
	private OperatoreBean mapToOperatoreBean(Object[] row) {
		OperatoreBean bean = new OperatoreBean();
		if (row != null && row.length > 0) {
			bean.setCf((String) row[0]);
			bean.setNome((String) row[1]);
			bean.setCognome((String) row[3]);
			bean.setTipoUtente((String) row[4]);
			bean.setEmail((String) row[5]);
			bean.setCdr((String) row[6]);
			bean.setAutorizzazioneLivelloI((String) row[7]);
			bean.setAutorizzazioneLivelloII((String) row[8]);
			bean.setRichiedente((String) row[9]);
			bean.setFlagRevocaCAU((String) row[10]);
			bean.setFlagRevocaPostazione((String) row[11]);
			bean.setFlagCdrDisallCauSiga((String) row[12]);
		}
		return bean;
	}

	
	public String getStrutturaIILiv(String cf)  {
		log.info("IN: SIGAServiceJpaImpl -> getStrutturaIILiv");

		try {
			String sql = "SELECT"
					+ "		A.CODICE_CDR_II_LIV_GERARCHICO"
					+ "		FROM"
					+ "		STRUTTURA A, UTENTI B, RELAZIONE_CDR_UFFICIO_STRUT RCUS"
					+ "		WHERE"
					+ "		B.CODICE_FISCALE = ?1"
					+ "		AND (B.DATA_FINE_VAL IS NULL OR"
					+ "		B.DATA_FINE_VAL > SYSDATE)"
					+ "		AND B.CODICE_CDR = RCUS.CODICE_CDR"
					+ "		AND"
					+ "		(RCUS.DATA_FINE_VAL IS NULL OR RCUS.DATA_FINE_VAL > SYSDATE)"
					+ "		AND"
					+ "		RCUS.CODICE_STRUTTURA = A.CODICE_STRUTTURA"
					+ "		AND (A.DATA_FINE_VAL IS NULL"
					+ "		OR A.DATA_FINE_VAL > SYSDATE)";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);

			@SuppressWarnings("unchecked")
			String result = (String) query.getSingleResult();

			return result ;

		} catch (Exception e) {
			log.error("=>ERRORE metodo getStrutturaIILiv() : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}


	public boolean abilitatoCambioUfficio(String cf)  {
		log.info("IN: SIGAServiceJpaImpl -> abilitatoCambioUfficio");

		try {
			String sql = "SELECT"
					+ "		A.CODICE_CDR_II_LIV_GERARCHICO"
					+ "		FROM"
					+ "		STRUTTURA A, UTENTI B, RELAZIONE_CDR_UFFICIO_STRUT RCUS"
					+ "		WHERE"
					+ "		B.CODICE_FISCALE = ?1 "
					+ "		AND (B.DATA_FINE_VAL IS NULL OR"
					+ "		B.DATA_FINE_VAL > SYSDATE)"
					+ "		AND B.CODICE_CDR = RCUS.CODICE_CDR"
					+ "		AND"
					+ "		(RCUS.DATA_FINE_VAL IS NULL OR RCUS.DATA_FINE_VAL > SYSDATE)"
					+ "		AND"
					+ "		RCUS.CODICE_STRUTTURA = A.CODICE_STRUTTURA"
					+ "		AND (A.DATA_FINE_VAL IS NULL"
					+ "		OR A.DATA_FINE_VAL > SYSDATE)";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);

			@SuppressWarnings("unchecked")
			Integer result = (Integer) query.getSingleResult();

			return result > 0 ? true : false ;

		} catch (Exception e) {
			log.error("=>ERRORE metodo abilitatoCambioUfficio() : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}


	public List<String> getProfiliAttivi(String cf)  {
		log.info("IN: SIGAServiceJpaImpl -> getProfiliAttivi");

		try {
			String sql = "SELECT"
					+ "		PAU.CODICE_PROFILO"
					+ "		FROM"
					+ "		PROFILI_ATTIVI_UTENTE PAU"
					+ "		WHERE"
					+ "		PAU.CF_UTENTE = ?1 "
					+ "		AND (PAU.DATA_FINE_VAL IS NULL OR"
					+ "		PAU.DATA_FINE_VAL > SYSDATE)";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);

			@SuppressWarnings("unchecked")
			List<String> result = query.getResultList();

			return result != null ? result : new ArrayList<String>();

		} catch (Exception e) {
			log.error("=>ERRORE metodo getProfiliAttivi() : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}


	public List<String> getRuoliGestoriOperatori(String cf)  {
		log.info("IN: SIGAServiceJpaImpl -> getProfiliAttivi");

		try {
			String sql = "SELECT CODICE_APPLICAZIONE, CODICE_STRUTTURA, CODICE_CDR"
					+ "		FROM GESTORI_OPERATORI"
					+ "		WHERE CODICE_FISCALE = ?1 "
					+ "		AND DATA_FINE_VAL IS NULL";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);

			@SuppressWarnings("unchecked")
			List<String> result = query.getResultList();

			return result != null ? result : new ArrayList<String>();

		} catch (Exception e) {
			log.error("=>ERRORE metodo getRuoliGestoriOperatori() : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}



	public List<CDRBean> getResponsabileByCF(OperatoreFinder finder)  {
		log.info("IN: SIGAServiceJpaImpl -> getResponsabileByCF");
		String sql = "SELECT"
				+ "      		CDR.CODICE_CDR, "
				+ "      		CDR.DESCRIZIONE "
				+ "		FROM"
				+ "			RICHIEDENTE_CDR RCDR "
				+ "      		LEFT JOIN CDR ON RCDR.CODICE_CDR = CDR.CODICE_CDR"
				+ "		WHERE"
				+ "			CF_RESPONSABILE_HR = ?1 "
				+ "			AND (RCDR.DATA_FINE_VAL IS NULL  OR RCDR.DATA_FINE_VAL > SYSDATE)"
				+ "    	  	AND (CDR.DATA_FINE_VAL IS NULL  OR CDR.DATA_FINE_VAL > SYSDATE)"
				+ "		ORDER BY CDR.DESCRIZIONE 	";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, finder.getCfUtente());


		List<Object[]> results = query.getResultList();

		List<CDRBean> entities = new ArrayList();
		for (Object[] row : results) {
			CDRBean entity = new CDRBean();
			entity.setCodiceCDR(row[0] != null ? row[0].toString() : "");
			entity.setDescrizioneCDR(row[1] != null ? row[1].toString() : "");
			entities.add(entity);
		}
		return entities;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserisciDelega(DelegaFinder delegaFinder)  {
		log.info("SigaDaoJpaImpl.inserisciDelegaBatch() - Implementing JPA version");

		try {
			String sql = "INSERT INTO DELEGHE (" +
					"CODICE_FISCALE_DELEGANTE, " +
					"CODICE_FISCALE_DELEGATO, " +
					"CDR_DEL_DELEGANTE, " +
					"CDR_DEL_DELEGATO, " +
					"RUOLO_DELEGATO, " +
					"DATA_SCADENZA, " +
					"DATA_INIZIO_VAL, " +
					"ID_AUDIT_INIZIO, " +
					"CODICE_FISCALE_INIZIO" +
					") VALUES (?1, ?2, ?3, ?4, ?5, ?6, CURRENT_TIMESTAMP, ?7, ?8)";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, delegaFinder.getCodiceFiscaleDelegante());
			query.setParameter(2, delegaFinder.getCodiceFiscaleDelegato());
			query.setParameter(3, delegaFinder.getCdrDelegante());
			query.setParameter(4, delegaFinder.getCdrDelegato());
			query.setParameter(5, delegaFinder.getRuoloDelegato());
			query.setParameter(6, delegaFinder.getDataScadenza());
			query.setParameter(7, delegaFinder.getIdAuditInizio());
			query.setParameter(8, delegaFinder.getCfInizio());

			query.executeUpdate();
			log.info("inserisciDelegaBatch() executed successfully");

		} catch (Exception e) {
			log.error("inserisciDelegaBatch() - Exception: " + e.getMessage(), e);
			throw new RuntimeException("Error executing inserisciDelegaBatch", e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void rimozioneDelega(DelegaFinder delegaFinder)  {
		log.info("SigaDaoJpaImpl.rimozioneDelega() - Implementing JPA version");

		try {
			StringBuilder sql = new StringBuilder(
					"UPDATE DELEGHE DE SET " +
							"DE.DATA_FINE_VAL = CURRENT_TIMESTAMP, " +
							"DE.MOTIVAZIONE_REVOCA = ?1, " +
							"DE.CODICE_FISCALE_FINE = ?2, " +
							"DE.ID_AUDIT_FINE = ?3 " +
							"WHERE (DE.DATA_FINE_VAL IS NULL OR DE.DATA_FINE_VAL > SYSDATE)");

			int paramIndex = 4;

			if (delegaFinder.getCodiceFiscaleDelegante() != null && !delegaFinder.getCodiceFiscaleDelegante().isEmpty()) {
				sql.append(" AND DE.CODICE_FISCALE_DELEGANTE = ?").append(paramIndex);
				paramIndex++;
			}

			if (delegaFinder.getCodiceFiscaleDelegato() != null && !delegaFinder.getCodiceFiscaleDelegato().isEmpty()) {
				sql.append(" AND DE.CODICE_FISCALE_DELEGATO = ?").append(paramIndex);
				paramIndex++;
			}

			if (delegaFinder.getRuoloDelegato() != null && !delegaFinder.getRuoloDelegato().isEmpty()) {
				sql.append(" AND DE.RUOLO_DELEGATO = ?").append(paramIndex);
				paramIndex++;
			}

			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter(1, delegaFinder.getMotivoRevoca());
			query.setParameter(2, delegaFinder.getCfFine());
			query.setParameter(3, delegaFinder.getIdAuditFine());

			paramIndex = 4;
			if (delegaFinder.getCodiceFiscaleDelegante() != null && !delegaFinder.getCodiceFiscaleDelegante().isEmpty()) {
				query.setParameter(paramIndex, delegaFinder.getCodiceFiscaleDelegante());
				paramIndex++;
			}

			if (delegaFinder.getCodiceFiscaleDelegato() != null && !delegaFinder.getCodiceFiscaleDelegato().isEmpty()) {
				query.setParameter(paramIndex, delegaFinder.getCodiceFiscaleDelegato());
				paramIndex++;
			}

			if (delegaFinder.getRuoloDelegato() != null && !delegaFinder.getRuoloDelegato().isEmpty()) {
				query.setParameter(paramIndex, delegaFinder.getRuoloDelegato());
				paramIndex++;
			}

			query.executeUpdate();
			log.info("rimozioneDelega() executed successfully");

		} catch (Exception e) {
			log.error("rimozioneDelega() - Exception: " + e.getMessage(), e);
			throw new RuntimeException("Error executing rimozioneDelega", e);
		}
	}

	public int countAltreDeleghe(DelegaFinder finder)  {
		log.info("SigaDaoJpaImpl.countAltreDeleghe() - Implementing JPA version");

		try {
			StringBuilder sql = new StringBuilder(
					"SELECT COUNT(*) FROM DELEGHE DE " +
							"WHERE (DE.DATA_FINE_VAL IS NULL OR DE.DATA_FINE_VAL > SYSDATE)");

			int paramIndex = 1;

			if (finder.getCodiceFiscaleDelegante() != null && !finder.getCodiceFiscaleDelegante().isEmpty()) {
				if (finder.getCodiceFiscaleDelegato() != null && !finder.getCodiceFiscaleDelegato().isEmpty()) {
					sql.append(" AND (");
					sql.append("(DE.CODICE_FISCALE_DELEGANTE <> ?").append(paramIndex).append(" AND DE.CODICE_FISCALE_DELEGATO = ?").append(paramIndex + 1).append(")");
					sql.append(" OR ");
					sql.append("(DE.CODICE_FISCALE_DELEGANTE = ?").append(paramIndex).append(" AND DE.CODICE_FISCALE_DELEGATO = ?").append(paramIndex + 1);

					if (finder.getRuoloDelegato() != null && !finder.getRuoloDelegato().isEmpty()) {
						sql.append(" AND DE.RUOLO_DELEGATO <> ?").append(paramIndex + 2);
						paramIndex += 3;
					} else {
						paramIndex += 2;
					}

					sql.append(")");
					sql.append(")");
				}
			} else {
				if (finder.getCodiceFiscaleDelegato() != null && !finder.getCodiceFiscaleDelegato().isEmpty()) {
					sql.append(" AND DE.CODICE_FISCALE_DELEGATO = ?").append(paramIndex);
					paramIndex++;
				}
			}

			Query query = entityManager.createNativeQuery(sql.toString());

			paramIndex = 1;
			if (finder.getCodiceFiscaleDelegante() != null && !finder.getCodiceFiscaleDelegante().isEmpty()) {
				query.setParameter(paramIndex, finder.getCodiceFiscaleDelegante());
				paramIndex++;
			}
			if (finder.getCodiceFiscaleDelegato() != null && !finder.getCodiceFiscaleDelegato().isEmpty()) {
				query.setParameter(paramIndex, finder.getCodiceFiscaleDelegato());
				paramIndex++;
			}
			if (finder.getRuoloDelegato() != null && !finder.getRuoloDelegato().isEmpty() &&
					finder.getCodiceFiscaleDelegante() != null && !finder.getCodiceFiscaleDelegante().isEmpty() &&
					finder.getCodiceFiscaleDelegato() != null && !finder.getCodiceFiscaleDelegato().isEmpty()) {
				query.setParameter(paramIndex, finder.getRuoloDelegato());
			}

			Object result = query.getSingleResult();
			int count = ((Number) result).intValue();

			log.info("countAltreDeleghe() executed successfully with count: " + count);
			return count;

		} catch (NoResultException nre) {
			log.info("countAltreDeleghe() - No results found");
			return 0;
		} catch (Exception e) {
			log.error("countAltreDeleghe() - Exception: " + e.getMessage(), e);
			throw new RuntimeException("Error executing countAltreDeleghe", e);
		}
	}

	

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int inserisciAUDIT(AuditFinder auditFinder)  {
		log.info("SigaDaoJpaImpl.inserisciAUDIT() - Implementing JPA version");

		try {
			String sql = "INSERT INTO AUDIT_OPERAZIONI (" +
					"ID_AUDIT, " +
					"CODICE_FISCALE, " +
					"CDR_AMMINISTRATORE, " +
					"DATA_ORA, " +
					"TABELLA_AGGIORNATA, " +
					"TESTO, " +
					"RUOLO_OPERAZIONE, " +
					"CDR_RUOLO) " +
					"VALUES (?1, ?2, ?3, SYSDATE, ?4, ?5, ?6, ?7)";

			String ruoloOperazione = auditFinder.getRuoloOperazione();
			if (ruoloOperazione == null || ruoloOperazione.isEmpty()) {
				ruoloOperazione = "AMMINISTRATORE_CENTRALE";
			}

			Query query = entityManager.createNativeQuery(sql);

			int progAudit = getNextAuditSequence();
			query.setParameter(1, progAudit);
			query.setParameter(2, auditFinder.getCf());
			query.setParameter(3, auditFinder.getCDRAmministratore());
			query.setParameter(4, auditFinder.getTabAggiornata());
			query.setParameter(5, auditFinder.getTesto());
			query.setParameter(6, ruoloOperazione);
			query.setParameter(7, auditFinder.getCdrRuolo());

			query.executeUpdate();
			log.info("inserisciAUDIT() executed successfully with progAudit: " + progAudit);
			return progAudit;

		} catch (Exception e) {
			log.error("inserisciAUDIT() - Exception: " + e.getMessage(), e);
			throw new RuntimeException("Error executing inserisciAUDIT", e);
		}
	}

	private int getNextAuditSequence()  {
		try {
			String sql = "SELECT PROG_AUDIT.nextval AS progAudit FROM dual";
			Query query = entityManager.createNativeQuery(sql);
			Object result = query.getSingleResult();
			return ((Number) result).intValue();
		} catch (Exception e) {
			log.error( "getNextAuditSequence() - Exception: " + e.getMessage(), e);
			throw new RuntimeException("Error getting next audit sequence", e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void aggiornaRichiesteDeleghe(String cfDelegato)  {
		try {
			String sql = "UPDATE REGISTRO_RICHIESTE SET DELEGA_RIMOSSA = 'SI' " +
					"WHERE CF_RICHIEDENTE_EFFETTIVO = ?1 " +
					"AND CF_RICHIEDENTE != ?1 " +
					"AND DATA_CHIUSURA_RICHIESTA IS NULL";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cfDelegato);
			query.executeUpdate();
			log.info("aggiornaRichiesteDeleghe() - Successfully updated REGISTRO_RICHIESTE for cfDelegato: " + cfDelegato);
		} catch (Exception e) {
			log.error("aggiornaRichiesteDeleghe() - Exception: " + e.getMessage(), e);
			throw new RuntimeException("Error executing aggiornaRichiesteDeleghe", e);
		}
	}

	public List<OperatoreBean> getDelegabiliByCdr(Map<String, Object> map)  {
		try {
			StringBuilder sql = new StringBuilder(
					"SELECT UTENTE.NOME, UTENTE.COGNOME, UTENTE.CODICE_FISCALE, UTENTE.CODICE_CDR, " +
							"CDR.DESCRIZIONE, UTENTE.TIPO_UTENTE " +
							"FROM UTENTI UTENTE, CDR " +
							"WHERE UTENTE.CODICE_CDR = ?1"
			);

			            /* TODO MCE TO IMPL
			            int paramIndex = 2;
			            if (map.get("cdr") != null && !map.get("cdr").isEmpty()) {
			                sql.append(" OR UTENTE.CODICE_CDR IN (");
			                for (int i = 0; i < codiciCdr.size(); i++) {
			                    if (i > 0) sql.append(", ");
			                    sql.append("?").append(paramIndex++);
			                }
			                sql.append(")");
			            }
			            */
			if (map.get("isInDeleghe") != null && !((String)map.get("isInDeleghe")).isEmpty()) {
				sql.append(" AND UTENTE.TIPO_UTENTE != 'E'");
			}

			sql.append(" AND CDR.CODICE_CDR = UTENTE.CODICE_CDR " +
					"AND (CDR.DATA_FINE_VAL IS NULL OR CDR.DATA_FINE_VAL > SYSDATE) " +
					"AND (UTENTE.DATA_FINE_VAL IS NULL OR UTENTE.DATA_FINE_VAL > SYSDATE)");

			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter(1, map.get("cdr"));
			            /* TODO MCE TO IMPL
			            paramIndex = 2;
			            if (codiciCdr != null && !codiciCdr.isEmpty()) {
			                for (String codiceCdr : codiciCdr) {
			                    query.setParameter(paramIndex++, codiceCdr);
			                }
			            }*/

			@SuppressWarnings("unchecked")
			List<Object[]> rawResults = query.getResultList();
			List<OperatoreBean> results = new ArrayList<>();

			for (Object[] row : rawResults) {
				OperatoreBean bean = new OperatoreBean();
				bean.setNome((String) row[0]);
				bean.setCognome((String) row[1]);
				bean.setCf((String) row[2]);
				bean.setCdr((String) row[3]);
				bean.setDescrizioneCDR((String) row[4]);
				bean.setTipoUtente((String) row[5]);
				results.add(bean);
			}

			log.info("getDelegabiliByCDR() - Retrieved " + results.size() + " delegable users for cdr:");
			return results;
		} catch (Exception e) {
			log.error("getDelegabiliByCDR() - Exception: " + e.getMessage(), e);
			throw new RuntimeException("Error executing getDelegabiliByCDR", e);
		}
	}

	public List<String> getElencoStruttRegionali(String strutturaIILiv)  {
		try {
			String sql = "SELECT S.CODICE_STRUTTURA FROM STRUTTURA S " +
					"WHERE S.CODICE_CDR_II_LIV_GERARCHICO = ?1 " +
					"AND (S.DATA_FINE_VAL IS NULL OR S.DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, strutturaIILiv);
			@SuppressWarnings("unchecked")
			List<String> results = query.getResultList();
			log.info("getElencoStruttRegionali() - Retrieved " + results.size() + " regional structures for strutturaIILiv: " + strutturaIILiv);
			return results;
		} catch (Exception e) {
			log.error("getElencoStruttRegionali() - Exception: " + e.getMessage(), e);
			throw new RuntimeException("Error executing getElencoStruttRegionali", e);
		}
	}

	public List<OperatoreBean> getDelegabiliByCdrORA(String cdr)  {
		log.info("IN: SiglaDaoImpl -> getDelegabiliByCdrORA");

		String sql = "SELECT " +
				"UTENTE.NOME, UTENTE.COGNOME, UTENTE.CODICE_FISCALE, UTENTE.CODICE_CDR, CDR.DESCRIZIONE, UTENTE.TIPO_UTENTE " +
				"FROM " +
				"UTENTI UTENTE, " +
				"CDR " +
				"WHERE " +
				"UTENTE.CODICE_CDR IN (?1) " +
				"AND CDR.CODICE_CDR = UTENTE.CODICE_CDR " +
				"AND (CDR.DATA_FINE_VAL IS NULL OR CDR.DATA_FINE_VAL > SYSDATE) " +
				"AND (UTENTE.DATA_FINE_VAL IS NULL OR UTENTE.DATA_FINE_VAL > SYSDATE) " +
				"AND UTENTE.TIPO_UTENTE = 'I'";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cdr);

		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		List<OperatoreBean> lista = new ArrayList<>();

		for (Object[] row : results) {
			OperatoreBean operatore = new OperatoreBean();
			operatore.setNome((String) row[0]);
			operatore.setCognome((String) row[1]);
			operatore.setCf((String) row[2]);
			operatore.setCdr((String) row[3]);
			operatore.setDescrizioneCDR((String) row[4]);
			operatore.setTipoUtente((String) row[5]);
			lista.add(operatore);
		}

		return lista;
	}

	public String getStrutturaByCDR(String cdr)  {
		log.info("IN: SIGAServiceJpaImpl -> getStrutturaIILiv");

		try {
			String sql = "SELECT"
					+ "		R.CODICE_STRUTTURA"
					+ "	FROM"
					+ "		RELAZIONE_CDR_UFFICIO_STRUT R"
					+ "	WHERE"
					+ "		R.CODICE_CDR = ?1 "
					+ "		AND (R.DATA_FINE_VAL IS NULL OR R.DATA_FINE_VAL > SYSDATE)";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cdr);

			@SuppressWarnings("unchecked")
			String result = (String) query.getSingleResult();

			return result ;

		} catch (Exception e) {
			log.error("=>ERRORE metodo getStrutturaIILiv() : " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public List<OperatoreBean> getDelegantiByCDR(String cdr)  {
		log.info("SigaDaoJpaImpl.getDelegantiByCDR() - Implementing JPA version");

		try {
			String sql =
					"SELECT " +
							"    UTENTE.NOME, UTENTE.COGNOME, UTENTE.CODICE_FISCALE, UTENTE.CODICE_CDR, 'RI' AS RUOLO, " +
							"    DELEGATO.NOME AS NOME_DELEGATO, DELEGATO.COGNOME AS COGNOME_DELEGATO, " +
							"    DE.DATA_FINE_VAL, DE.DATA_INIZIO_VAL, DE.DATA_SCADENZA, DE.CODICE_FISCALE_DELEGATO AS CF_DELEGATO " +
							"FROM UTENTI UTENTE " +
							"LEFT JOIN ( " +
							"    DELEGHE DE " +
							"    INNER JOIN UTENTI DELEGATO " +
							"    ON DE.CODICE_FISCALE_DELEGATO = DELEGATO.CODICE_FISCALE " +
							"    AND (DE.DATA_FINE_VAL IS NULL OR DE.DATA_FINE_VAL > SYSDATE) " +
							") ON UTENTE.CODICE_FISCALE = DE.CODICE_FISCALE_DELEGANTE " +
							"    AND DE.RUOLO_DELEGATO = 'RI' " +
							"WHERE UTENTE.CODICE_CDR = ? " +
							"    AND UTENTE.RICHIEDENTE = 'SI' " +
							"    AND (UTENTE.DATA_FINE_VAL IS NULL OR UTENTE.DATA_FINE_VAL > SYSDATE) " +
							"UNION " +
							"SELECT " +
							"    UTENTE.NOME, UTENTE.COGNOME, UTENTE.CODICE_FISCALE, UTENTE.CODICE_CDR, 'A1' AS RUOLO, " +
							"    DELEGATO.NOME AS NOME_DELEGATO, DELEGATO.COGNOME AS COGNOME_DELEGATO, " +
							"    DE.DATA_FINE_VAL, DE.DATA_INIZIO_VAL, DE.DATA_SCADENZA, DE.CODICE_FISCALE_DELEGATO AS CF_DELEGATO " +
							"FROM UTENTI UTENTE " +
							"LEFT JOIN ( " +
							"    DELEGHE DE " +
							"    INNER JOIN UTENTI DELEGATO " +
							"    ON DE.CODICE_FISCALE_DELEGATO = DELEGATO.CODICE_FISCALE " +
							"    AND (DE.DATA_FINE_VAL IS NULL OR DE.DATA_FINE_VAL > SYSDATE) " +
							") ON UTENTE.CODICE_FISCALE = DE.CODICE_FISCALE_DELEGANTE " +
							"    AND DE.RUOLO_DELEGATO = 'A1' " +
							"WHERE UTENTE.CODICE_CDR = ? " +
							"    AND UTENTE.AUTORIZZATORE_I_LIV = 'SI' " +
							"    AND (UTENTE.DATA_FINE_VAL IS NULL OR UTENTE.DATA_FINE_VAL > SYSDATE) " +
							"UNION " +
							"SELECT " +
							"    UTENTE.NOME, UTENTE.COGNOME, UTENTE.CODICE_FISCALE, UTENTE.CODICE_CDR, 'A2' AS RUOLO, " +
							"    DELEGATO.NOME AS NOME_DELEGATO, DELEGATO.COGNOME AS COGNOME_DELEGATO, " +
							"    DE.DATA_FINE_VAL, DE.DATA_INIZIO_VAL, DE.DATA_SCADENZA, DE.CODICE_FISCALE_DELEGATO AS CF_DELEGATO " +
							"FROM UTENTI UTENTE " +
							"LEFT JOIN ( " +
							"    DELEGHE DE " +
							"    INNER JOIN UTENTI DELEGATO " +
							"    ON DE.CODICE_FISCALE_DELEGATO = DELEGATO.CODICE_FISCALE " +
							"    AND (DE.DATA_FINE_VAL IS NULL OR DE.DATA_FINE_VAL > SYSDATE) " +
							") ON UTENTE.CODICE_FISCALE = DE.CODICE_FISCALE_DELEGANTE " +
							"    AND DE.RUOLO_DELEGATO = 'A2' " +
							"WHERE UTENTE.CODICE_CDR = ? " +
							"    AND UTENTE.AUTORIZZATORE_II_LIV = 'SI' " +
							"    AND (UTENTE.DATA_FINE_VAL IS NULL OR UTENTE.DATA_FINE_VAL > SYSDATE)";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cdr);
			query.setParameter(2, cdr);
			query.setParameter(3, cdr);

			@SuppressWarnings("unchecked")
			List<Object[]> rawResults = query.getResultList();
			List<OperatoreBean> results = new ArrayList<>();

			for (Object[] row : rawResults) {
				OperatoreBean bean = new OperatoreBean();
				bean.setNome((String) row[0]);
				bean.setCognome((String) row[1]);
				bean.setCf((String) row[2]);
				bean.setCdr((String) row[3]);
				bean.setRuolo((String) row[4]);
				bean.setNomeDelegato((String) row[5]);
				bean.setCognomeDelegato((String) row[6]);
				bean.setDataFineDelega((java.util.Date) row[7]);
				bean.setDataInizioVal((java.util.Date) row[8]);
				bean.setDataScadenza((java.util.Date) row[9]);
				bean.setCfDelegato((String) row[10]);
				results.add(bean);
			}

			log.info("getDelegantiByCDR() - Retrieved " + results.size() + " delegating users for cdr: " + cdr);
			return results;
		} catch (Exception e) {
			log.error("getDelegantiByCDR() - Exception: " + e.getMessage(), e);
			throw new RuntimeException("Error executing getDelegantiByCDR", e);
		}
	}

	public List<Utenti> countRegistroRichieste(HashMap<String, String> input)  {
		String cfResponsabile = input.get("cfResponsabile");

		String sql = "SELECT DISTINCT UT.cognome AS cognome, UT.nome AS nome, UT.codice_fiscale AS codiceFiscale " +
				"FROM REGISTRO_RICHIESTE RR, STATO_RICHIESTA SR, UTENTI UT " +
				"WHERE RR.STATO_RICHIESTA = SR.STATO " +
				"AND SR.CANCELLABILE = 'SI' " +
				"AND UT.CODICE_FISCALE = RR.CF_UTENTE " +
				"AND ((cdr_autorizzatore_1 IS NULL AND cf_autorizzatore_1 = ?1) " +
				"OR (cdr_autorizzatore_2 IS NULL AND cf_autorizzatore_2 = ?2))";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cfResponsabile);
		query.setParameter(2, cfResponsabile);

		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		List<Utenti> utentiList = new ArrayList<>();

		for (Object[] row : results) {
			Utenti utente = new Utenti();
			utente.setCognome((String) row[0]);
			utente.setNome((String) row[1]);
			utente.setCodiceFiscale((String) row[2]);
			utentiList.add(utente);
		}

		return utentiList;
	}

	public RichiestaBean getDescrizioneCdr(RichiestaBean finder)  {
		log.info( "SigaDaoJpaImpl.getDescrizioneCdr() - Implementing JPA version");

		try {
			String sql = "SELECT " +
					"DESCRIZIONE, " +
					"(SELECT DESCRIZIONE FROM CDR WHERE CODICE_CDR = ?) AS DESCR_CDR_AUT_I, " +
					"(SELECT DESCRIZIONE FROM CDR WHERE CODICE_CDR = ?) AS DESCR_CDR_AUT_II, " +
					"(SELECT DESCRIZIONE FROM CDR WHERE CODICE_CDR = ?) AS DESCR_CDR_RICHIEDENTE, " +
					"(SELECT DESCRIZIONE FROM CDR WHERE CODICE_CDR = ?) AS DESCR_CDR_GESTORE, " +
					"(SELECT DESCRIZIONE FROM CDR WHERE CODICE_CDR = ?) AS DESCR_STRUTT_GESTORE " +
					"FROM CDR WHERE CODICE_CDR = ?";

			Query query = entityManager.createNativeQuery(sql);

			int paramIndex = 1;
			query.setParameter(paramIndex++, finder.getCdrAutorizzatoreI());
			query.setParameter(paramIndex++, finder.getCdrAutorizzatoreII());
			query.setParameter(paramIndex++, finder.getCdrRichiedente());
			query.setParameter(paramIndex++, finder.getCdrGestoreOperatori());
			query.setParameter(paramIndex++, finder.getCodiceStruttGestore());
			query.setParameter(paramIndex++, finder.getCdrUtente());

			Object[] result = (Object[]) query.getSingleResult();

			if (result != null) {
				if (result[0] != null) {
					finder.setDescrCdrUtente((String) result[0]);
				}
				if (result[1] != null) {
					finder.setDescrCdrAutorizzatoreI((String) result[1]);
				}
				if (result[2] != null) {
					finder.setDescrCdrAutorizzatoreII((String) result[2]);
				}
				if (result[3] != null) {
					finder.setDescrCdrRichiedente((String) result[3]);
				}
				if (result[4] != null) {
					finder.setDescrCdrGestoreOperatori((String) result[4]);
				}
				if (result[5] != null) {
					finder.setDescrStruttGestoreOperatori((String) result[5]);
				}
			}

			log.info( "getDescrizioneCdr() returned successfully");
			return finder;

		} catch (NoResultException e) {
			log.info( "getDescrizioneCdr() - No results found");
			return finder;
		} catch (Exception e) {
			log.error("getDescrizioneCdr() - Exception: " + e.getMessage(), e);
			throw new RuntimeException("Error executing getDescrizioneCdr query", e);
		}
	}

	public String getDescrCdrByCodCDR(String codiceCdr)  {
		log.info("IN SigaDaoImplNew.getDescrCdrByCodCDR(codiceCdr)");

		String sql = "SELECT DESCRIZIONE FROM CDR WHERE CODICE_CDR = ? AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, codiceCdr);

		Object result = query.getSingleResult();
		return result != null ? (String) result : null;
	}

	public String getDescrizioneCdrBatch(String codiceCdr)  {
		log.info("IN SigaDaoImplNew.getDescrizioneCdrBatch(codiceCdr)");

		String sql = "SELECT DESCRIZIONE FROM CDR WHERE CODICE_CDR = ? ";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, codiceCdr);

		Object result = query.getSingleResult();
		return result != null ? (String) result : null;
	}

	public List<String> selectEmailAmministratoriCentrali()  {
		String sql = "SELECT e_mail AS eMail FROM PROFILI_ATTIVI_UTENTE a, UTENTI b " +
				"WHERE a.codice_profilo = 'ESG_AMM_Centrale_3' " +
				"AND a.cf_utente = b.codice_fiscale " +
				"AND (a.DATA_FINE_VAL IS NULL OR a.DATA_FINE_VAL > SYSDATE) " +
				"AND (b.DATA_FINE_VAL IS NULL OR b.DATA_FINE_VAL > SYSDATE)";

		Query query = entityManager.createNativeQuery(sql);

		@SuppressWarnings("unchecked")
		List<String> results = query.getResultList();

		return results;
	}

	public List<String> selectEmailUtenteByCdRIILivGerarchico(String cdr)  {
		String sql = "select E_MAIL as eMail"
				+ "		from UTENTI a, RELAZIONE_CDR_UFFICIO_STRUT b,"
				+ "		STRUTTURA c,"
				+ "		PROFILI_ATTIVI_UTENTE d"
				+ "		where a.codice_cdr = b.codice_cdr"
				+ "		and b.codice_struttura = c.codice_struttura"
				+ "		and"
				+ "		c.codice_cdr_II_liv_gerarchico = ?1 "
				+ "		and a.codice_fiscale ="
				+ "		d.cf_utente"
				+ "		and d.codice_profilo = 'ESG_AMM_Regionale_3'"
				+ "		and ("
				+ "		a.DATA_FINE_VAL is null"
				+ "		or a.DATA_FINE_VAL > "
				+ "		SYSDATE )"
				+ "		and ( b.DATA_FINE_VAL is null"
				+ "		or b.DATA_FINE_VAL > "
				+ "		SYSDATE )"
				+ "		and ( c.DATA_FINE_VAL is null"
				+ "		or c.DATA_FINE_VAL  > "
				+ "		SYSDATE )"
				+ "		and ( d.DATA_FINE_VAL is null"
				+ "		or d.DATA_FINE_VAL >"
				+ "		SYSDATE )";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cdr);

		@SuppressWarnings("unchecked")
		List<String> results = query.getResultList();

		return results;
	}


	public TabellaBatchHrBean selectSettingsBatch()  {
		log.info("IN SigaDaoImplNew.selectSettingsBatch()");

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("BH.DATA_INIZIO_RESPONSABILI_CDR, ");
		sql.append("BH.DATA_FINE_RESPONSABILI_CDR, ");
		sql.append("BH.DATA_INIZIO_UTENTI, ");
		sql.append("BH.DATA_FINE_UTENTI, ");
		sql.append("BH.DATA_INIZIO_EMAIL_RUOLI, ");
		sql.append("BH.DATA_FINE_EMAIL_RUOLI, ");
		sql.append("B.VALORE_COSTANTE AS EMAIL_SEGNALAZIONI_SOGEI ");
		sql.append("FROM BATCH_HR BH, COSTANTI_SIGA B ");
		sql.append("WHERE B.NOME_COSTANTE = 'emailSegnalazioniSogei'");

		Query query = entityManager.createNativeQuery(sql.toString());

		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();

		if (results.isEmpty()) {
			return null;
		}

		Object[] row = results.get(0);
		TabellaBatchHrBean bean = new TabellaBatchHrBean();

		if (row.length >= 1 && row[0] != null) bean.setDataInizioResponsabiliCdr((java.util.Date) row[0]);
		if (row.length >= 2 && row[1] != null) bean.setDataFineResponsabiliCdr((java.util.Date) row[1]);
		if (row.length >= 3 && row[2] != null) bean.setDataInizioUtenti((java.util.Date) row[2]);
		if (row.length >= 4 && row[3] != null) bean.setDataFineUtenti((java.util.Date) row[3]);
		if (row.length >= 5 && row[4] != null) bean.setDataInizioEmailRuoli((java.util.Date) row[4]);
		if (row.length >= 6 && row[5] != null) bean.setDataFineEmailRuoli((java.util.Date) row[5]);
		if (row.length >= 7 && row[6] != null) bean.setEmailSegnalazioniSogei((String) row[6]);

		return bean;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateFlagUtenteCdrDisallienato(OperatoreFinder finder)  {
		String sql = "UPDATE"
				+ "		UTENTI"
				+ "		SET"
				+ "		CDR_DISALL_CAU_SIGA = ?1"
				+ "		WHERE"
				+ "		CODICE_FISCALE = ?2";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, finder.getFlagCdrDisallCauSiga());
		query.setParameter(2, finder.getCfUtente());
		query.executeUpdate();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void aggiornaFlagUtente(Utenti ute)  {
		try {
			StringBuilder sql = new StringBuilder("UPDATE UTENTI SET ");
			boolean first = true;
			int paramIndex = 1;

			if (ute.getRichiedente() != null && !ute.getRichiedente().isEmpty()) {
				sql.append("RICHIEDENTE = ?").append(paramIndex++);
				first = false;
			}
			if (ute.getAutorizzatoreIliv() != null && !ute.getAutorizzatoreIliv().isEmpty()) {
				if (!first) sql.append(", ");
				sql.append("AUTORIZZATORE_I_LIV = ?").append(paramIndex++);
				first = false;
			}
			if (ute.getAutorizzatoreIIliv() != null && !ute.getAutorizzatoreIIliv().isEmpty()) {
				if (!first) sql.append(", ");
				sql.append("AUTORIZZATORE_II_LIV = ?").append(paramIndex++);
			}
			sql.append(" WHERE CODICE_FISCALE = ?").append(paramIndex);

			Query query = entityManager.createNativeQuery(sql.toString());
			paramIndex = 1;
			if (ute.getRichiedente() != null && !ute.getRichiedente().isEmpty()) {
				query.setParameter(paramIndex++, ute.getRichiedente());
			}
			if (ute.getAutorizzatoreIliv() != null && !ute.getAutorizzatoreIliv().isEmpty()) {
				query.setParameter(paramIndex++, ute.getAutorizzatoreIliv());
			}
			if (ute.getAutorizzatoreIIliv() != null && !ute.getAutorizzatoreIIliv().isEmpty()) {
				query.setParameter(paramIndex++, ute.getAutorizzatoreIIliv());
			}
			query.setParameter(paramIndex, ute.getCodiceFiscale());
			query.executeUpdate();
		} catch (Exception e) {
			log.error("Errore nel metodo updateFlagUtente", e);
			throw e;
		}/*
			try {
				StringBuilder sql = new StringBuilder("UPDATE UTENTI SET ");
				int paramIndex = 1;

				if (utente.getRichiedente() != null && !utente.getRichiedente().isEmpty()) {
					sql.append("RICHIEDENTE = ?").append(paramIndex++);
				}
				if (utente.getAutorizzatoreIliv() != null && !utente.getAutorizzatoreIliv().isEmpty()) {
					if (paramIndex > 2) sql.append(", ");
					sql.append("AUTORIZZATORE_I_LIV = ?").append(paramIndex++);
				}
				if (utente.getAutorizzatoreIIliv() != null && !utente.getAutorizzatoreIIliv().isEmpty()) {
					if (paramIndex > 2) sql.append(", ");
					sql.append("AUTORIZZATORE_II_LIV = ?").append(paramIndex++);
				}
				sql.append(" WHERE CODICE_FISCALE = ?").append(paramIndex);

				Query query = entityManager.createNativeQuery(sql.toString());
				paramIndex = 1;
				if (utente.getRichiedente() != null && !utente.getRichiedente().isEmpty()) {
					query.setParameter(paramIndex++, utente.getRichiedente());
				}
				if (utente.getAutorizzatoreIliv() != null && !utente.getAutorizzatoreIliv().isEmpty()) {
					query.setParameter(paramIndex++, utente.getAutorizzatoreIliv());
				}
				if (utente.getAutorizzatoreIIliv() != null && !utente.getAutorizzatoreIIliv().isEmpty()) {
					query.setParameter(paramIndex++, utente.getAutorizzatoreIIliv());
				}
				query.setParameter(paramIndex, utente.getCodiceFiscale());
				query.executeUpdate();
			} catch (Exception e) {
				log.error("Errore nel metodo aggiornaFlagUtente", e);
			} */
	}

	public String getCFRichiedenteWFM(RichiestaBean input)  {
		log.info("IN SigaDaoImplNew.getCFRichiedenteWFM(input)");

		String sql = "SELECT cf_richiedente_effettivo " +
				"FROM REGISTRO_RICHIESTE " +
				"WHERE id_richiesta = ?1 " +
				"AND cf_richiedente = cf_archiviazione " +
				"UNION " +
				"SELECT codice_fiscale_delegato " +
				"FROM REGISTRO_RICHIESTE, deleghe del " +
				"WHERE id_richiesta = ?1 " +
				"AND cf_richiedente <> cf_archiviazione " +
				"AND cf_archiviazione = codice_fiscale_delegante " +
				"AND ruolo_delegato = 'RI' " +
				"AND del.data_fine_val IS NULL " +
				"UNION " +
				"SELECT cf_archiviazione " +
				"FROM REGISTRO_RICHIESTE " +
				"WHERE id_richiesta = ?1 " +
				"AND cf_richiedente <> cf_archiviazione " +
				"AND NOT EXISTS " +
				"(SELECT 1 " +
				"FROM deleghe del " +
				"WHERE cf_archiviazione = codice_fiscale_delegante " +
				"AND ruolo_delegato = 'RI' " +
				"AND del.data_fine_val IS NULL)";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.getIdRichiesta());

		Object result = query.getSingleResult();
		return result != null ? (String) result : null;
	}

	public List<String> getCFRichiedentiECAAltriCDRWFM(RichiestaBean input)  {
		log.info("IN SigaDaoImplNew.getCFRichiedentiECAAltriCDRWFM(input)");


		String sql = "SELECT ric.cf_richiedente " +
				"FROM richiedente_cdr ric, utente_in_visibilita utvis " +
				"WHERE utvis.codice_cdr = ric.codice_cdr " +
				"AND ric.data_fine_val IS NULL " +
				"AND utvis.id_richiesta_visibilita IN " +
				"(SELECT DISTINCT id_richiesta_visibilita " +
				"FROM profili_attivi_utente pau " +
				"WHERE pau.id_richiesta <> ?1 " +
				"AND pau.id_richiesta_visibilita IS NOT NULL " +
				"AND pau.cf_utente = ?2 " +
				"AND pau.codice_applicazione IN " +
				"(SELECT DISTINCT codice_applicazione " +
				"FROM profili_richiesta " +
				"WHERE id_richiesta = ?1 " +
				"AND codice_profilo LIKE 'ECA%')) " +
				"UNION " +
				"SELECT ric.cf_richiedente " +
				"FROM profili_attivi_utente pau, richiedente_cdr ric, utenti ut " +
				"WHERE pau.id_richiesta <> ?1 " +
				"AND pau.cf_utente = ut.codice_fiscale " +
				"AND ut.codice_cdr = ric.codice_cdr " +
				"AND ric.data_fine_val IS NULL " +
				"AND pau.id_richiesta_visibilita IS NULL " +
				"AND pau.cf_utente = ?2 " +
				"AND pau.codice_applicazione IN " +
				"(SELECT DISTINCT codice_applicazione " +
				"FROM profili_richiesta " +
				"WHERE id_richiesta = ?1 " +
				"AND codice_profilo LIKE 'ECA%')";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.getIdRichiesta());
		query.setParameter(2, input.getCfUtente());

		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<String> cfList = new ArrayList<>();
		for (Object obj : results) {
			if (obj != null) {
				cfList.add((String) obj);
			}
		}
		return cfList;
	}


	public List<String> getEmailAltroUfficioInteressato(RichiestaBean input)  {
		log.info("IN SigaDaoImplNew.getEmailAltroUfficioInteressato(input)");
		String sql = "SELECT E_MAIL " +
				"FROM UFFICIO UFF, RICHIEDENTE_CDR RIC, UTENTI UT, REGISTRO_RICHIESTE RR, STATO_RICHIESTA SR, DELEGHE DEL " +
				"WHERE RR.ID_RICHIESTA = ?1 " +
				"AND ALTRO_UFF_INTERESSATO IS NOT NULL " +
				"AND RR.STATO_RICHIESTA = SR.STATO " +
				"AND SR.EMAIL_DELEGANTE = 'SI' " +
				"AND RUOLO_DELEGATO = 'RI' " +
				"AND UFF.CODICE_UFFICIO = ALTRO_UFF_INTERESSATO " +
				"AND UFF.CODICE_CDR = RIC.CODICE_CDR " +
				"AND RIC.CF_RICHIEDENTE = CODICE_FISCALE_DELEGANTE " +
				"AND RIC.CF_RICHIEDENTE IS NOT NULL " +
				"AND CODICE_FISCALE_DELEGANTE = UT.CODICE_FISCALE " +
				"AND RIC.CF_RICHIEDENTE = UT.CODICE_FISCALE " +
				"AND RIC.DATA_FINE_VAL IS NULL " +
				"AND UFF.DATA_FINE_VAL IS NULL " +
				"AND UT.DATA_FINE_VAL IS NULL " +
				"AND DEL.DATA_FINE_VAL IS NULL " +
				"UNION " +
				"SELECT E_MAIL " +
				"FROM UFFICIO UFF, RICHIEDENTE_CDR RIC, UTENTI UT, REGISTRO_RICHIESTE RR " +
				"WHERE RR.ID_RICHIESTA = ?1 " +
				"AND ALTRO_UFF_INTERESSATO IS NOT NULL " +
				"AND UFF.CODICE_UFFICIO = ALTRO_UFF_INTERESSATO " +
				"AND UFF.CODICE_CDR = RIC.CODICE_CDR " +
				"AND RIC.CF_RICHIEDENTE IS NOT NULL " +
				"AND RIC.CF_RICHIEDENTE = UT.CODICE_FISCALE " +
				"AND NOT EXISTS " +
				"(SELECT 1 FROM DELEGHE DEL " +
				"WHERE CODICE_FISCALE_DELEGANTE = RIC.CF_RICHIEDENTE " +
				"AND RUOLO_DELEGATO = 'RI' " +
				"AND DEL.DATA_FINE_VAL IS NULL) " +
				"AND RIC.DATA_FINE_VAL IS NULL " +
				"AND UFF.DATA_FINE_VAL IS NULL " +
				"AND UT.DATA_FINE_VAL IS NULL " +
				"UNION " +
				"SELECT E_MAIL " +
				"FROM UFFICIO UFF, RICHIEDENTE_CDR RIC, UTENTI UT, REGISTRO_RICHIESTE RR, STATO_RICHIESTA SR, DELEGHE DEL " +
				"WHERE RR.ID_RICHIESTA = ?1 " +
				"AND ALTRO_UFF_INTERESSATO IS NOT NULL " +
				"AND RR.STATO_RICHIESTA = SR.STATO " +
				"AND EMAIL_DELEGATO = 'SI' " +
				"AND UFF.CODICE_UFFICIO = ALTRO_UFF_INTERESSATO " +
				"AND UFF.CODICE_CDR = RIC.CODICE_CDR " +
				"AND RIC.CF_RICHIEDENTE IS NOT NULL " +
				"AND RUOLO_DELEGATO = 'RI' " +
				"AND RIC.CF_RICHIEDENTE = CODICE_FISCALE_DELEGANTE " +
				"AND CODICE_FISCALE_DELEGATO = UT.CODICE_FISCALE " +
				"AND RIC.CF_RICHIEDENTE = UT.CODICE_FISCALE " +
				"AND RIC.DATA_FINE_VAL IS NULL " +
				"AND UFF.DATA_FINE_VAL IS NULL " +
				"AND UT.DATA_FINE_VAL IS NULL " +
				"AND DEL.DATA_FINE_VAL IS NULL";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.getIdRichiesta());

		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<String> emailList = new ArrayList<>();
		for (Object obj : results) {
			if (obj != null) {
				emailList.add((String) obj);
			}
		}
		return emailList;
	}

	public String getCFRichiedenteVisibilitaWFM(RichiestaBean input)  {
		log.info("IN SigaDaoImplNew.getCFRichiedenteVisibilitaWFM(input)");

		String sql = "SELECT CASE " +
				"WHEN ruolo='DEL' AND ?1 = cf_richiedente_effettivo " +
				"THEN cf_richiedente_effettivo " +
				"ELSE codice_fiscale " +
				"END AS CF_RICHIEDENTE " +
				"FROM (SELECT DISTINCT codice_fiscale, cf_richiedente_effettivo, 'TIT' AS ruolo " +
				"FROM utenti ut, registro_richieste regr " +
				"WHERE ut.codice_fiscale = ?1 " +
				"AND id_richiesta = ?2 " +
				"AND NOT EXISTS (SELECT DISTINCT e_mail " +
				"FROM utenti ut, deleghe del, registro_richieste regr " +
				"WHERE ut.codice_fiscale = codice_fiscale_delegato " +
				"AND del.data_fine_val IS NULL " +
				"AND ruolo_delegato = 'RI' " +
				"AND codice_fiscale_delegante = ?1 " +
				"AND id_richiesta = ?2) " +
				"UNION " +
				"SELECT DISTINCT codice_fiscale, cf_richiedente_effettivo, 'DEL' AS ruolo " +
				"FROM utenti ut, deleghe del, registro_richieste regr " +
				"WHERE ut.codice_fiscale = codice_fiscale_delegato " +
				"AND del.data_fine_val IS NULL " +
				"AND ruolo_delegato = 'RI' " +
				"AND codice_fiscale_delegante = ?1 " +
				"AND id_richiesta = ?2)";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, input.getCfArchiviazione());
		query.setParameter(2, input.getIdRichiesta());

		Object result = query.getSingleResult();
		return result != null ? (String) result : null;
	}


	public Integer countProfiliAsGestoreDiRete(Integer idRichiesta)  {
		log.info("IN SigaDaoImplNew.countProfiliAsGestoreDiRete(idRichiesta)");

		String sql = "SELECT COUNT(*) " +
				"FROM PROFILO P, PROFILI_RICHIESTA PR " +
				"WHERE PR.ID_RICHIESTA = ?1 " +
				"AND P.CODICE_PROFILO = PR.CODICE_PROFILO " +
				"AND P.GESTORE_RETE = 'SI'";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, idRichiesta);

		Object result = query.getSingleResult();
		return result != null ? ((Number) result).intValue() : 0;
	}


	public List<RichiedenteCDRBean> getRichiedentiAC(RichiedenteCDRFinder richiedenteCDRFinder)  {
		log.info("IN SigaDaoImplNew.getRichiedentiAC(richiedenteCDRFinder)");

		String sql = "SELECT codice_fiscale AS CF_RICHIEDENTE " +
				"FROM utenti, richiedente_cdr ric " +
				"WHERE codice_fiscale = cf_richiedente " +
				"AND ric.codice_cdr = ?1 " +
				"AND ric.data_fine_val IS NULL " +
				"AND NOT EXISTS " +
				"(SELECT 1 FROM deleghe del " +
				"WHERE codice_fiscale_delegante = cf_richiedente " +
				"AND del.data_fine_val IS NULL " +
				"AND ruolo_delegato = 'RI') " +
				"UNION " +
				"SELECT codice_fiscale AS CF_RICHIEDENTE " +
				"FROM utenti, richiedente_cdr ric, deleghe del " +
				"WHERE codice_fiscale = codice_fiscale_delegato " +
				"AND codice_fiscale_delegante = cf_richiedente " +
				"AND ric.codice_cdr = ?1 " +
				"AND del.data_fine_val IS NULL " +
				"AND ric.data_fine_val IS NULL " +
				"AND ruolo_delegato = 'RI'";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, richiedenteCDRFinder.getCodiceCDR());

		@SuppressWarnings("unchecked")
		List<Object> results = query.getResultList();
		List<RichiedenteCDRBean> beanList = new ArrayList<>();
		for (Object obj : results) {
			if (obj != null) {
				RichiedenteCDRBean bean = new RichiedenteCDRBean();
				bean.setCfRichiedente((String) obj);
				beanList.add(bean);
			}
		}
		return beanList;
	}

	public List<RichiedenteCDRBean> getRichiedentePuntualeCdr(RichiedenteCDRFinder finder)  {
		log.info("IN SigaDaoImplNew.getRichiedentePuntualeCdr(finder)");

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		int paramIndex = 1;

		sql.append("SELECT CODICE_FISCALE_RICHIEDENTE AS CF_RICHIEDENTE, ");
		sql.append("CODICE_FISCALE_INIZIO AS cfInizio, ");
		sql.append("DATA_INIZIO_VAL AS dataInizioVal, ");
		sql.append("CODICE_CDR_RICHIEDENTE AS cdrRichiedente, ");
		sql.append("CODICE_CDR_AUTORIZZATORE AS cdrAutorizzatore, ");
		sql.append("CODICE_FISCALE_AUTORIZZATORE AS cfAutorizzatoreILiv ");
		sql.append("FROM ASSOCIAZ_OPER_RICHI_AUTOR AORA ");
		sql.append("WHERE (AORA.DATA_FINE_VAL IS NULL OR AORA.DATA_FINE_VAL > SYSDATE) ");

		if (finder.getCodiceCDR() != null && !finder.getCodiceCDR().isEmpty()) {
			sql.append("AND AORA.CODICE_CDR_OPERATORE = ?").append(paramIndex);
			params.add(finder.getCodiceCDR());
			paramIndex++;
		}

		if (finder.getCfOperatore() != null && !finder.getCfOperatore().isEmpty()) {
			sql.append("AND AORA.CODICE_FISCALE_OPERATORE = ?").append(paramIndex);
			params.add(finder.getCfOperatore());
			paramIndex++;
		}

		Query query = entityManager.createNativeQuery(sql.toString());
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i + 1, params.get(i));
		}

		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		List<RichiedenteCDRBean> beanList = new ArrayList<>();

		for (Object[] row : results) {
			if (row != null && row.length >= 6) {
				RichiedenteCDRBean bean = new RichiedenteCDRBean();
				bean.setCfRichiedente((String) row[0]);
				bean.setCfInizio((String) row[1]);
				if (row[2] != null) {
					bean.setDataInizioVal(new java.sql.Date(((java.util.Date) row[2]).getTime()));
				}
				bean.setCdrRichiedente((String) row[3]);
				bean.setCdrAutorizzatore((String) row[4]);
				bean.setCfAutorizzatoreILiv((String) row[5]);
				beanList.add(bean);
			}
		}
		return beanList;
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserisciRichiestaBatchDaElab(RichiestaBatchDaElaborareBean richiestaBatch)  {
		log.info("inserisciRichiestaBatchDaElab() - Implementing JPA version");

		try {
			String sql = "INSERT INTO RIC_BATCH_DA_ELAB " +
					"(ID_RICHIESTA, PRIORITA, CODICE_EVENTO, AMBIENTE_DI_DESTINAZIONE, " +
					"AGGIORNAMENTO_AMB_DI_DEST, DATA_CREAZIONE, NUMERO_PROFILI, " +
					"ORARIO_INIZIO_ELABORAZIONE, ORARIO_FINE_ELABORAZIONE, SEQUENCE, IN_LAVORAZIONE) " +
					"VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11)";

			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, richiestaBatch.getIdRichiesta());
			query.setParameter(2, richiestaBatch.getPriorita());
			query.setParameter(3, richiestaBatch.getCodiceEvento());
			query.setParameter(4, richiestaBatch.getAmbienteDestinazione());
			query.setParameter(5, richiestaBatch.getAggAmbienteDestinazione());
			query.setParameter(6, richiestaBatch.getDataCreazione());
			query.setParameter(7, richiestaBatch.getNumeroProfili());
			query.setParameter(8, richiestaBatch.getOrarioInizioElab());
			query.setParameter(9, richiestaBatch.getOrarioFineElab());
			query.setParameter(10, richiestaBatch.getSequence());
			query.setParameter(11, richiestaBatch.getInLavorazione());

			query.executeUpdate();
			log.info( "inserisciRichiestaBatchDaElab() executed successfully");

		} catch (Exception e) {
			log.error("inserisciRichiestaBatchDaElab() - Exception: " + e.getMessage(), e);
			throw new RuntimeException("Error executing inserisciRichiestaBatchDaElab", e);
		}
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void setDataElaborazioneBatch(CostantiSigaPT costante) {
		String sql = "UPDATE COSTANTI_SIGA SET DATA_ULTIMA_ELABORAZIONE = ?1 WHERE NOME_COSTANTE = ?2";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, costante.getDataUltimaElaborazione());
		query.setParameter(2, costante.getNome());
		query.executeUpdate();
	}

	public long getIdRichFromSequenceIdRichiesta()  {
		String sql = "SELECT ID_RICHIESTA.nextval FROM dual";
		Query query = entityManager.createNativeQuery(sql);
		Object result = query.getSingleResult();
		return result != null ? ((Number) result).longValue() : 1L;
	}

	public Long getSequenceFromRichiesteBatch()  {
		String sql = "SELECT SEQUENCE_RICH_BATCH.nextval FROM dual";
		Query query = entityManager.createNativeQuery(sql);
		return ((Number) query.getSingleResult()).longValue();
	}

	public ElencoBean getElencoOperatoriMonitoraggio(OperatoreFinder finder)  {
		log.info("IN SigaDaoImplNew.getElencoOperatoriMonitoraggio(finder)");

		List<Object> listaCompleta = new ArrayList<>();

		if (finder.getCodiceCDR() != null && !finder.getCodiceCDR().equals("")) {
			// Logic for single CDR
			for (CDRBean cdr : finder.getCdrList()) {
				if (cdr.getCodiceCDR().equalsIgnoreCase(finder.getCodiceCDR())) {
					finder.setFlag(cdr.getFlag());
					break;
				}
			}

			if (finder.getFlag() != null && (finder.getFlag().equals("3") || finder.getFlag().equals("6"))) {
				listaCompleta.addAll(execGetElencoOperatoriAORA(finder));
			} else {
				listaCompleta.addAll(execGetElencoOperatoriDiUnoOPiuCDR(finder));
				listaCompleta.addAll(execGetElencoOperatoriInVisibilitaOld(finder));
			}
		} else {
			// Logic for multiple CDRs
			List<CDRBean> cdrBeans = finder.getCdrList();
			List<String> codiciCDR = new ArrayList<>(finder.getCodiceCDRList());
			List<String> removedCodici = new ArrayList<>();

			// Handle ORA CDRs
			listaCompleta.addAll(execGetElencoOperatoriInVisibilitaOld(finder));

			for (CDRBean cdr : cdrBeans) {
				if (cdr.getFlag().equals("3") || cdr.getFlag().equals("6")) {
					finder.setCodiceCDR(cdr.getCodiceCDR());
					finder.setFlag(cdr.getFlag());
					listaCompleta.addAll(execGetElencoOperatoriAORA(finder));

					Iterator<String> it = codiciCDR.iterator();
					while (it.hasNext()) {
						String cod = it.next();
						if (cod.equals(cdr.getCodiceCDR())) {
							removedCodici.add(cod);
							it.remove();
						}
					}
				}
			}

			finder.setCodiceCDR("");
			finder.setCodiceCDRList(codiciCDR);
			listaCompleta.addAll(execGetElencoOperatoriDiUnoOPiuCDR(finder));

			// Restore finder state
			codiciCDR.addAll(removedCodici);
			finder.setCodiceCDRList(codiciCDR);
		}

		ElencoBean risultato = new ElencoBean();
		risultato.setNumeroElementi(listaCompleta.size());
		List<Serializable> listaRisultato = new ArrayList<>();
		for (Object o : listaCompleta) {
			listaRisultato.add((Serializable) o);
		}
		risultato.setElenco(listaRisultato);

		return risultato;
	}

	private List<OperatoriInVisibilitaBean> execGetElencoOperatoriAORA(OperatoreFinder finder) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		int idx = 1;

		sql.append("SELECT DISTINCT U.NOME, U.COGNOME, U.CODICE_CDR CDR_DI_APPARTENENZA, U.CODICE_FISCALE, U.DATA_FINE_VAL, U.TIPO_UTENTE, ")
				.append("RCUS.CODICE_UFFICIO, RCUS.CODICE_STRUTTURA, '' CDR_IN_VISIBILITA, '' DESC_CDR_IN_VISIBILITA, ")
				.append("CDR.DESCRIZIONE as DESC_CDR_DI_APPARTENENZA, NULL as ID_RICHIESTA_VISIBILITA ")
				.append("FROM UTENTI U, ASSOCIAZ_OPER_RICHI_AUTOR AORA, CDR, RELAZIONE_CDR_UFFICIO_STRUT RCUS ")
				.append("WHERE (?").append(idx++).append(" = '3') ")
				.append("AND (U.CODICE_CDR = ?").append(idx++).append(" AND (U.DATA_FINE_VAL IS NULL OR U.DATA_FINE_VAL > SYSDATE)) ")
				.append("AND CDR.CODICE_CDR = U.CODICE_CDR AND RCUS.CODICE_CDR = CDR.CODICE_CDR ")
				.append("AND (AORA.CODICE_FISCALE_RICHIEDENTE = ?").append(idx++).append(" ")
				.append("AND U.CODICE_FISCALE = AORA.CODICE_FISCALE_OPERATORE ")
				.append("AND (AORA.DATA_FINE_VAL IS NULL OR AORA.DATA_FINE_VAL > SYSDATE) ")
				.append("AND (CDR.DATA_FINE_VAL is null OR CDR.DATA_FINE_VAL > SYSDATE) ")
				.append("AND (RCUS.DATA_FINE_VAL is null OR RCUS.DATA_FINE_VAL > SYSDATE)) ");

		params.add(finder.getFlag());
		params.add(finder.getCodiceCDR());
		params.add(finder.getCodFisUser());

		appendSearchFilters(sql, finder, params, idx);
		idx = params.size() + 1;

		sql.append(" UNION ")
				.append("SELECT DISTINCT U.NOME, U.COGNOME, U.CODICE_CDR CDR_DI_APPARTENENZA, U.CODICE_FISCALE, U.DATA_FINE_VAL, U.TIPO_UTENTE, ")
				.append("RCUS.CODICE_UFFICIO, RCUS.CODICE_STRUTTURA, '' CDR_IN_VISIBILITA, '' DESC_CDR_IN_VISIBILITA, ")
				.append("CDR.DESCRIZIONE as DESC_CDR_DI_APPARTENENZA, NULL as ID_RICHIESTA_VISIBILITA ")
				.append("FROM UTENTI U, ASSOCIAZ_OPER_RICHI_AUTOR AORA, DELEGHE D, CDR, RELAZIONE_CDR_UFFICIO_STRUT RCUS ")
				.append("WHERE ?").append(idx++).append(" = '6' ")
				.append("AND (AORA.CODICE_CDR_OPERATORE = ?").append(idx++).append(" AND (AORA.DATA_FINE_VAL IS NULL OR AORA.DATA_FINE_VAL > SYSDATE)) ")
				.append("AND (U.CODICE_FISCALE = AORA.CODICE_FISCALE_OPERATORE AND (D.DATA_FINE_VAL IS NULL OR D.DATA_FINE_VAL > SYSDATE)) ")
				.append("AND (D.CODICE_FISCALE_DELEGATO = ?").append(idx++).append(" AND D.RUOLO_DELEGATO = 'RI' AND (D.DATA_FINE_VAL IS NULL OR D.DATA_FINE_VAL > SYSDATE)) ")
				.append("AND AORA.CODICE_FISCALE_RICHIEDENTE = D.CODICE_FISCALE_DELEGANTE AND RCUS.CODICE_CDR = CDR.CODICE_CDR ")
				.append("AND CDR.CODICE_CDR = ?").append(idx++).append(" AND (CDR.DATA_FINE_VAL is null OR CDR.DATA_FINE_VAL > SYSDATE) ")
				.append("AND (RCUS.DATA_FINE_VAL is null OR RCUS.DATA_FINE_VAL > SYSDATE) ");

		params.add(finder.getFlag());
		params.add(finder.getCodiceCDR());
		params.add(finder.getCodFisUser());
		params.add(finder.getCodiceCDR());

		appendSearchFilters(sql, finder, params, idx);

		List<Object[]> results = executeQuery(sql.toString(), params);
		List<OperatoriInVisibilitaBean> mappingResults = new ArrayList<>();
		for (Object[] row : results) {
			mappingResults.add(mapOperatoriInVisibilitaAORA(row));
		}
		return mappingResults;
	}

	private List<OperatoriInVisibilitaBean> execGetElencoOperatoriDiUnoOPiuCDR(OperatoreFinder finder) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		int idx = 1;

		sql.append("SELECT DISTINCT U.NOME, U.COGNOME, U.CODICE_CDR CDR_DI_APPARTENENZA, U.CODICE_FISCALE, U.TIPO_UTENTE, ")
				.append("RCUS.CODICE_UFFICIO, RCUS.CODICE_STRUTTURA, '' CDR_IN_VISIBILITA, NULL as ID_RICHIESTA_VISIBILITA, ")
				.append("to_char(U.DATA_FINE_VAL,'dd/MM/yyyy') DATA_FINE_VAL, ")
				.append("CDR_APP.DESCRIZIONE DESC_CDR_DI_APPARTENENZA, '' DESC_CDR_IN_VISIBILITA ")
				.append("FROM UTENTI U, CDR CDR_APP, RELAZIONE_CDR_UFFICIO_STRUT RCUS ")
				.append("WHERE U.CODICE_CDR = CDR_APP.CODICE_CDR AND RCUS.CODICE_CDR = U.CODICE_CDR ")
				.append("AND (U.DATA_FINE_VAL IS NULL OR U.DATA_FINE_VAL > SYSDATE) ")
				.append("AND (RCUS.DATA_FINE_VAL IS NULL OR RCUS.DATA_FINE_VAL > SYSDATE) ")
				.append("AND (CDR_APP.DATA_FINE_VAL IS NULL OR CDR_APP.DATA_FINE_VAL > SYSDATE) ");

		if (finder.getCodiceCDRList() != null && !finder.getCodiceCDRList().isEmpty()) {
			sql.append("AND U.CODICE_CDR IN (");
			idx = appendInListWithParams(sql, finder.getCodiceCDRList(), params, idx);
			sql.append(") ");
		}

		if (finder.getCodiceCDR() != null && !finder.getCodiceCDR().isEmpty()) {
			sql.append("AND U.CODICE_CDR = ?").append(idx++);
			params.add(finder.getCodiceCDR());
		}

		appendSearchFilters(sql, finder, params, idx);

		List<Object[]> results = executeQuery(sql.toString(), params);
		List<OperatoriInVisibilitaBean> mappingResults = new ArrayList<>();
		for (Object[] row : results) {
			mappingResults.add(mapOperatoriInVisibilitaDiUnoOPiuCDR(row));
		}
		return mappingResults;
	}

	private List<OperatoriInVisibilitaBean> execGetElencoOperatoriInVisibilitaOld(OperatoreFinder finder) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		int idx = 1;

		String baseSql = "SELECT DISTINCT U.NOME, U.COGNOME, U.CODICE_CDR CDR_DI_APPARTENENZA, U.CODICE_FISCALE, U.TIPO_UTENTE, " +
				"RCUS.CODICE_UFFICIO, RCUS.CODICE_STRUTTURA, UV.CODICE_CDR CDR_IN_VISIBILITA, NULL as ID_RICHIESTA_VISIBILITA, " +
				"to_char(UV.DATA_FINE_VAL,'dd/MM/yyyy') DATA_FINE_VAL, " +
				"CDR_APP.DESCRIZIONE DESC_CDR_DI_APPARTENENZA, CDR_VISIB.DESCRIZIONE DESC_CDR_IN_VISIBILITA " +
				"FROM UTENTI U, UTENTE_IN_VISIBILITA UV, CDR CDR_APP, CDR CDR_VISIB, RELAZIONE_CDR_UFFICIO_STRUT RCUS " +
				"WHERE ((UV.DATA_FINE_VAL IS NULL OR UV.DATA_FINE_VAL > SYSDATE) ";

		// Part 1
		sql.append(baseSql);
		if (finder.getCodiceCDRList() != null && !finder.getCodiceCDRList().isEmpty()) {
			sql.append("AND UV.CODICE_CDR IN (");
			idx = appendInListWithParams(sql, finder.getCodiceCDRList(), params, idx);
			sql.append(") ");
		}
		if (finder.getCodiceCDR() != null && !finder.getCodiceCDR().isEmpty()) {
			sql.append("AND UV.CODICE_CDR = ?").append(idx++);
			params.add(finder.getCodiceCDR());
		}
		sql.append(") AND U.CODICE_FISCALE = UV.CF_UTENTE AND (U.DATA_FINE_VAL IS NULL OR U.DATA_FINE_VAL > SYSDATE) " +
				"AND CDR_APP.CODICE_CDR = U.CODICE_CDR AND CDR_VISIB.CODICE_CDR = UV.CODICE_CDR AND RCUS.CODICE_CDR = CDR_VISIB.CODICE_CDR " +
				"AND (RCUS.DATA_FINE_VAL IS NULL OR RCUS.DATA_FINE_VAL > SYSDATE) AND (CDR_APP.DATA_FINE_VAL IS NULL OR CDR_APP.DATA_FINE_VAL > SYSDATE) ");

		appendSearchFilters(sql, finder, params, idx);
		idx = params.size() + 1;
		sql.append(" AND UV.DATA_INIZIO_VAL IS NOT NULL ");

		sql.append(" UNION ");

		// Part 2
		sql.append(baseSql);
		if (finder.getCodiceCDRList() != null && !finder.getCodiceCDRList().isEmpty()) {
			sql.append("AND U.CODICE_CDR IN (");
			idx = appendInListWithParams(sql, finder.getCodiceCDRList(), params, idx);
			sql.append(") ");
		}
		if (finder.getCodiceCDR() != null && !finder.getCodiceCDR().isEmpty()) {
			sql.append("AND U.CODICE_CDR = ?").append(idx++);
			params.add(finder.getCodiceCDR());
		}
		sql.append(") AND UV.CF_UTENTE = U.CODICE_FISCALE AND (UV.DATA_FINE_VAL IS NULL OR UV.DATA_FINE_VAL > SYSDATE) " +
				"AND CDR_APP.CODICE_CDR = U.CODICE_CDR AND CDR_VISIB.CODICE_CDR = UV.CODICE_CDR AND RCUS.CODICE_CDR = CDR_VISIB.CODICE_CDR " +
				"AND (RCUS.DATA_FINE_VAL IS NULL OR RCUS.DATA_FINE_VAL > SYSDATE) AND (CDR_APP.DATA_FINE_VAL IS NULL OR CDR_APP.DATA_FINE_VAL > SYSDATE) ");

		appendSearchFilters(sql, finder, params, idx);
		sql.append(" AND UV.DATA_INIZIO_VAL IS NOT NULL ");

		List<Object[]> results = executeQuery(sql.toString(), params);
		List<OperatoriInVisibilitaBean> mappingResults = new ArrayList<>();
		for (Object[] row : results) {
			mappingResults.add(mapOperatoriInVisibilitaDiUnoOPiuCDR(row));
		}
		return mappingResults;
	}

	private OperatoriInVisibilitaBean mapOperatoriInVisibilitaAORA(Object[] row) {
		OperatoriInVisibilitaBean bean = new OperatoriInVisibilitaBean();
		bean.setNome((String) row[0]);
		bean.setCognome((String) row[1]);
		bean.setCdr_di_appartenenza((String) row[2]);
		bean.setCf_utente((String) row[3]);
		// DATA_FINE_VAL is at row[4], but in AORA query it seems it's used for filtering.
		// Let's check the SQL select list again.
		// SELECT U.NOME, U.COGNOME, U.CODICE_CDR, U.CODICE_FISCALE, U.DATA_FINE_VAL, U.TIPO_UTENTE, ...
		// OperatoriInVisibilitaBean.setData_fine_val takes a String.
		if (row[4] != null) {
			bean.setData_fine_val(new SimpleDateFormat("yyyy-MM-dd").format((Date) row[4]));
		}
		bean.setTipoUtente((String) row[5]);
		bean.setCodiceUfficio((String) row[6]);
		bean.setCodiceStruttura((String) row[7]);
		bean.setCdr_in_visibilita((String) row[8]);
		bean.setDesc_cdr_in_visibilita((String) row[9]);
		bean.setDesc_cdr_di_appartenenza((String) row[10]);
		if (row[11] != null) {
			bean.setIdRichiestaVisibilita(((Number) row[11]).longValue());
		}
		return bean;
	}

	private OperatoriInVisibilitaBean mapOperatoriInVisibilitaDiUnoOPiuCDR(Object[] row) {
		OperatoriInVisibilitaBean bean = new OperatoriInVisibilitaBean();
		bean.setNome((String) row[0]);
		bean.setCognome((String) row[1]);
		bean.setCdr_di_appartenenza((String) row[2]);
		bean.setCf_utente((String) row[3]);
		bean.setTipoUtente((String) row[4]);
		bean.setCodiceUfficio((String) row[5]);
		bean.setCodiceStruttura((String) row[6]);
		bean.setCdr_in_visibilita((String) row[7]);
		if (row[8] != null) {
			bean.setIdRichiestaVisibilita(((Number) row[8]).longValue());
		}
		bean.setData_fine_val((String) row[9]); // In this query it's already to_char
		bean.setDesc_cdr_di_appartenenza((String) row[10]);
		bean.setDesc_cdr_in_visibilita((String) row[11]);
		return bean;
	}

	private void appendSearchFilters(StringBuilder sql, OperatoreFinder finder, List<Object> params, int startIdx) {
		int idx = startIdx;
		if (finder.getNome() != null && !finder.getNome().isEmpty()) {
			sql.append("AND UPPER(U.NOME) LIKE UPPER('%'||?").append(idx++).append("||'%') ");
			params.add(finder.getNome());
		}
		if (finder.getCognome() != null && !finder.getCognome().isEmpty()) {
			sql.append("AND UPPER(U.COGNOME) LIKE UPPER('%'||?").append(idx++).append("||'%') ");
			params.add(finder.getCognome());
		}
		if (finder.getCodFis() != null && !finder.getCodFis().isEmpty()) {
			sql.append("AND UPPER(U.CODICE_FISCALE) LIKE UPPER('%'||?").append(idx++).append("||'%') ");
			params.add(finder.getCodFis());
		}
	}

	private List<Object[]> executeQuery(String sql, List<Object> params) {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i + 1, params.get(i));
		}
		return query.getResultList();
	}

	public List<RuoloRichiedenteBean> getRuoliRichiedente(String cfRichiedente) {
		String sql = "SELECT CODICE_CDR, CDR_DESC, CF_RICHIEDENTE, COGNOME_RICHIEDENTE, NOME_RICHIEDENTE, " +
				"CF_AUTORIZZATORE_I_LIV, COGNOME_AUTORIZZATORE_I_LIV, NOME_AUTORIZZATORE_I_LIV " +
				"FROM RUOLI_CDR_1 WHERE CF_RICHIEDENTE = ?1";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cfRichiedente);

		List<Object[]> results = query.getResultList();
		List<RuoloRichiedenteBean> list = new ArrayList<>();

		for (Object[] row : results) {
			list.add(mapRuoloRichiedente(row));
		}
		return list;
	}

	private RuoloRichiedenteBean mapRuoloRichiedente(Object[] row) {
		RuoloRichiedenteBean bean = new RuoloRichiedenteBean();
		bean.setCodiceCdr((String) row[0]);
		bean.setCdrDesc((String) row[1]);
		bean.setCfRichiedente((String) row[2]);
		bean.setCognomeRichiedente((String) row[3]);
		bean.setNomeRichiedente((String) row[4]);
		bean.setCfAutorizzatoreILiv((String) row[5]);
		bean.setCognomeAutorizzatoreILiv((String) row[6]);
		bean.setNomeAutorizzatoreILiv((String) row[7]);
		return bean;
	}

	public List<RuoloAutorizzatore_II_LivelloBean> getRuoliAutorizzatoreIILiv(String cfAutorizzatore) {
		String sql = "SELECT IDENTIFICATIVO_ITER, DESCRIZIONE_ITER, TIPO_CDR_RIC, DESCRIZIONE_TIPO_CDR_RIC, " +
				"CF_AUTORIZZATORE_II_LIV, COGNOME, NOME, CODICE_PROFILO, DESCRIZIONE_PROFILO, " +
				"CODICE_ROLE_GROUP, DESCRIZIONE_ROLE_GROUP " +
				"FROM RUOLI_CDR_AUT_II WHERE CF_AUTORIZZATORE_II_LIV = ?1";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cfAutorizzatore);

		List<Object[]> results = query.getResultList();
		List<RuoloAutorizzatore_II_LivelloBean> list = new ArrayList<>();

		for (Object[] row : results) {
			list.add(mapRuoloAutorizzatoreIILiv(row));
		}
		return list;
	}

	public List<RuoloDelegheBean> getRuoliDeleghe(String cfDelegatoDelegante) {
		String sql = "SELECT CODICE_FISCALE_DELEGANTE, COGNOME_DELEGANTE, NOME_DELEGANTE, NOME_RUOLO, " +
				"CODICE_FISCALE_DELEGATO, COGNOME_DELEGATO, NOME_DELEGATO " +
				"FROM RUOLI_CDR_DELEGHE_IV WHERE CODICE_FISCALE_DELEGANTE = ?1 OR CODICE_FISCALE_DELEGATO = ?1";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cfDelegatoDelegante);

		List<Object[]> results = query.getResultList();
		List<RuoloDelegheBean> list = new ArrayList<>();

		for (Object[] row : results) {
			list.add(mapRuoloDeleghe(row));
		}
		return list;
	}

	private RuoloDelegheBean mapRuoloDeleghe(Object[] row) {
		RuoloDelegheBean bean = new RuoloDelegheBean();
		bean.setCodiceFiscaleDelegante((String) row[0]);
		bean.setCognomeDelegante((String) row[1]);
		bean.setNomeDelegante((String) row[2]);
		bean.setNomeRuolo((String) row[3]);
		bean.setCodiceFiscaleDelegato((String) row[4]);
		bean.setCognomeDelegato((String) row[5]);
		bean.setNomeDelegato((String) row[6]);
		return bean;
	}

	private RuoloAutorizzatore_II_LivelloBean mapRuoloAutorizzatoreIILiv(Object[] row) {
		RuoloAutorizzatore_II_LivelloBean bean = new RuoloAutorizzatore_II_LivelloBean();
		bean.setIdentificativoIter((String) row[0]);
		bean.setDescrizioneIter((String) row[1]);
		bean.setTipoCdrRic((String) row[2]);
		bean.setDescrizioneTipoCdrRic((String) row[3]);
		bean.setCfAutorizzatoreIILiv((String) row[4]);
		bean.setCognome((String) row[5]);
		bean.setNome((String) row[6]);
		bean.setCodiceProfilo((String) row[7]);
		bean.setDescrizioneProfilo((String) row[8]);
		bean.setCodiceRoleGroup((String) row[9]);
		bean.setDescrizioneRoleGroup((String) row[10]);
		return bean;
	}

	public List<RuoloAmministratoreAuditorBean> getRuoliAmministratoreAuditor(String cfAmministratoreAuditor) {
		String sql = "SELECT CODICE_FISCALE, COGNOME, NOME, RUOLO, CODICE_CDR, DESCRIZIONE_CDR " +
				"FROM RUOLI_CDR_AMM_III WHERE CODICE_FISCALE = ?1";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, cfAmministratoreAuditor);

		List<Object[]> results = query.getResultList();
		List<RuoloAmministratoreAuditorBean> list = new ArrayList<>();

		for (Object[] row : results) {
			list.add(mapRuoloAmministratoreAuditor(row));
		}
		return list;
	}

	private RuoloAmministratoreAuditorBean mapRuoloAmministratoreAuditor(Object[] row) {
		RuoloAmministratoreAuditorBean bean = new RuoloAmministratoreAuditorBean();
		bean.setCodiceFiscaleAmmAud((String) row[0]);
		bean.setCognomeAmmAud((String) row[1]);
		bean.setNomeAmmAud((String) row[2]);
		bean.setRuoloAmmAud((String) row[3]);
		bean.setCodiceCdrAmmAud((String) row[4]);
		bean.setDescrizioneCdrAmmAud((String) row[5]);
		return bean;
	}

	public List<StrutturaUfficioCDRBean> getElencoStruttureDaIILiv(String codiceIILiv) {
		String sql = "SELECT S.CODICE_CDR, CD.DESCRIZIONE " +
				"FROM STRUTTURA S, CDR CD " +
				"WHERE S.CODICE_CDR_II_LIV_GERARCHICO = ?1 " +
				"AND CD.CODICE_CDR = S.CODICE_CDR " +
				"AND (S.DATA_FINE_VAL IS NULL OR S.DATA_FINE_VAL > SYSDATE) " +
				"ORDER BY CD.DESCRIZIONE";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, codiceIILiv);

		List<Object[]> results = query.getResultList();
		List<StrutturaUfficioCDRBean> list = new ArrayList<>();

		for (Object[] row : results) {
			list.add(mapStrutturaUfficioCDR(row));
		}
		return list;
	}

	private StrutturaUfficioCDRBean mapStrutturaUfficioCDR(Object[] row) {
		StrutturaUfficioCDRBean bean = new StrutturaUfficioCDRBean();
		bean.setCodiceCDR((String) row[0]);
		bean.setStrutturaDesc((String) row[1]);
		return bean;
	}

	public List<StrutturaUfficioCDRBean> getElencoCdRDaStrutt(CDRBaseFinder finderCdr) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		int idx = 1;

		sql.append("SELECT CD.DESCRIZIONE AS CDR_DESC, RCUS.CODICE_CDR ")
				.append("FROM CDR CD, cdr cdriiliv, cdr cdrstrut, RELAZIONE_CDR_UFFICIO_STRUT RCUS, STRUTTURA S, tipo_cdr tiiliv, TIPO_STRUTTURA TS, UFFICIO U ")
				.append("WHERE CD.CODICE_CDR = RCUS.CODICE_CDR ")
				.append("AND (CD.DATA_FINE_VAL IS NULL OR CD.DATA_FINE_VAL > SYSDATE) ")
				.append("AND (RCUS.DATA_FINE_VAL IS NULL OR RCUS.DATA_FINE_VAL > SYSDATE) ")
				.append("AND (S.DATA_FINE_VAL IS NULL OR S.DATA_FINE_VAL > SYSDATE) ")
				.append("AND (U.DATA_FINE_VAL IS NULL OR U.DATA_FINE_VAL > SYSDATE) ")
				.append("AND S.CODICE_CDR = RCUS.VERTICE_STRUTTURA ")
				.append("AND RCUS.II_LIV_GERARCHICO = cdriiliv.CODICE_CDR ")
				.append("AND cdriiliv.TIPO_CDR = tiiliv.TIPO_CDR ")
				.append("AND RCUS.VERTICE_STRUTTURA = cdrstrut.CODICE_CDR ")
				.append("AND TS.TIPO_STRUTTURA = S.TIPO_STRUTTURA ")
				.append("AND U.CODICE_CDR = RCUS.VERTICE_UFFICIO ");

		if (finderCdr.getCodiceStrutturaList() != null && !finderCdr.getCodiceStrutturaList().isEmpty()) {
			sql.append("AND RCUS.VERTICE_STRUTTURA IN (");
			for (int i = 0; i < finderCdr.getCodiceStrutturaList().size(); i++) {
				sql.append("?").append(idx++);
				if (i < finderCdr.getCodiceStrutturaList().size() - 1) {
					sql.append(", ");
				}
				params.add(finderCdr.getCodiceStrutturaList().get(i));
			}
			sql.append(") ");
		}

		sql.append("ORDER BY tiiliv.CAMPO_ORDINAMENTO, cdriiliv.DESCRIZIONE, TS.CAMPO_ORDINAMENTO, cdrstrut.DESCRIZIONE, CDR_DESC");

		Query query = entityManager.createNativeQuery(sql.toString());
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i + 1, params.get(i));
		}

		List<Object[]> results = query.getResultList();
		List<StrutturaUfficioCDRBean> list = new ArrayList<>();
		for (Object[] row : results) {
			StrutturaUfficioCDRBean bean = new StrutturaUfficioCDRBean();
			bean.setStrutturaDesc((String) row[0]);
			bean.setCodiceCDR((String) row[1]);
			list.add(bean);
		}
		return list;
	}

	public EventoBean getEventoCM(String codiceEvento)  {
		log.info("IN SigaDaoImplNew.getEventoCM(codiceEvento)");

		String sql = "SELECT PRIORITA, ORA_INIZIO_ELAB_RICHIESTE,"
				+ " ORA_FINE_ELAB_RICHIESTE, NUM_MAX_REC_CONT_IMMEDIATI,"
				+ " NUM_MAX_REC_ELAB_IMMEDIATA, RICHIESTA_VISIBILE_RUOLI_SIGA,"
				+ " PRESA_VISIONE, ARCHIVIAZIONE, "
				+ "AGGIORNA_SIST_AUTORIZZAZIONE, NUM_MAX_REC,"
				+ " ORA_INIZIO_CREA_RICHIESTE, ORA_FINE_CREA_RICHIESTE "
				+ "FROM "
				+ "EVENTI_SISTEMA "
				+ "WHERE "
				+ "CODICE_EVENTO = ?";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, codiceEvento);

		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();

		if (results == null || results.isEmpty()) {
			return null;
		}

		Object[] row = results.get(0);
		EventoBean bean = new EventoBean();

		if (row != null && row.length > 0) {
			if (row[0] != null) bean.setPriorita(String.valueOf(((Number) row[0]).intValue()));
			if (row.length > 1 && row[1] != null) bean.setOrarioInElab((String) row[1]);
			if (row.length > 2 && row[2] != null) bean.setOrarioFineElab((String) row[2]);
			if (row.length > 3 && row[3] != null) bean.setNumMaxRecordControlliImmediati(((Number) row[3]).longValue());
			if (row.length > 4 && row[4] != null) bean.setNumMaxRecordElaborazioneImmediata(((Number) row[4]).longValue());
			if (row.length > 5 && row[5] != null) bean.setRichiestaVisibileRuoliSiga((String) row[5]);
			if (row.length > 6 && row[6] != null) bean.setPresaVisione((String) row[6]);
			if (row.length > 7 && row[7] != null) bean.setArchiviazione((String) row[7]);
			if (row.length > 8 && row[8] != null) bean.setAggiornaSistAutorizzazione((String) row[8]);
			if (row.length > 9 && row[9] != null) bean.setNumMaxRecord(((Number) row[9]).intValue());
			if (row.length > 10 && row[10] != null) bean.setOrarioInizioRichieste((String) row[10]);
			if (row.length > 11 && row[11] != null) bean.setOrarioFineRichieste((String) row[11]);
		}

		return bean;
	}


	public Integer verificaEsistenzaCf(String cf)  {
		try {
			String sql = "SELECT COUNT(*) FROM UTENTI WHERE CODICE_FISCALE = ?1 AND DATA_FINE_VAL IS NULL";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);
			Object result = query.getSingleResult();
			return ((Number) result).intValue();
		} catch (NoResultException e) {
			return 0;
		} catch (Exception e) {
			throw new RuntimeException("Error verifying CF: " + e.getMessage(), e);
		}
	}

	public int verificaDisallineamentoCf(String cf)  {
		try {
			String sql = "SELECT "
					+ "			COUNT(*)"
					+ "		FROM "
					+ "			UTENTI "
					+ "		WHERE "
					+ "			CODICE_FISCALE = ?1 "
					+ "			AND CDR_DISALL_CAU_SIGA = 'SI'"
					+ "			AND DATA_FINE_VAL IS NULL";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);
			Object result = query.getSingleResult();
			return ((Number) result).intValue();
		} catch (Exception e) {
			throw new RuntimeException("Error verifying disallineamento: " + e.getMessage(), e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserimentoProfiloRichiesta(ProfiloRichiestaBean bean)  {

		String sql = "INSERT INTO PROFILI_RICHIESTA("
				+ "			ID_RICHIESTA,"
				+ "			CODICE_APPLICAZIONE,"
				+ "			CODICE_ROLE_GROUP,"
				+ "			CODICE_PROFILO,"
				+ "			CODICE_UFFICIO,"
				+ "			CODICE_CDR,"
				+ "			TIPO_ABILITAZIONE,"
				+ "			OPERAZIONE_SUL_PROFILO,"
				+ "			OPERAZIONE_ESEGUITA,"
				+ "			DATA_SCADENZA,"
				+ "			ID_RICHIESTA_VISIBILITA,"
				+ "			ORIGINE_ABILITAZIONE"
				+ "		)"
				+ "		VALUES("
				+ "			?1,"
				+ "			?2,"
				+ "			?3,"
				+ "			?4,"
				+ "			?5,"
				+ "			?6,"
				+ "			?7,"
				+ "			?8,"
				+ "			?9,"
				+ "			?10,"
				+ "			?11,"
				+ "			?12"
				+ "		)";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, bean.getIdRichiesta());
		query.setParameter(2, bean.getCodiceApplicazione());
		query.setParameter(3, bean.getCodiceRoleGroup());
		query.setParameter(4, bean.getCodiceProfilo());
		query.setParameter(5, bean.getCodiceUfficio());
		query.setParameter(6, bean.getCodiceCdr());
		query.setParameter(7, bean.getTipoAbilitazione());
		query.setParameter(8, bean.getOperazioneSuProfilo());
		query.setParameter(9, bean.getOperazioneEseguita());
		query.setParameter(10, bean.getDataScadenza());
		query.setParameter(11, bean.getIdRichiestaVisibilita());
		query.setParameter(12, bean.getOrigineAbilitazione());
		query.executeUpdate();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminaRichiesteBatch(String sequence)  {
		String sql = "DELETE FROM RICHIESTE_BATCH WHERE SEQUENCE = ?1";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, Long.parseLong(sequence));
		query.executeUpdate();
	}

	public String getEmailAmminCaricamento(String idCaricamento)  {
		try {
			String sql = "SELECT "
					+ "  			UT.E_MAIL"
					+ "		FROM "
					+ "  			UTENTI UT, "
					+ "  			CM_RICHIESTA CMR"
					+ "		WHERE"
					+ "  			CMR.ID_CARICAMENTO = ?1 "
					+ "  			AND CMR.CF_AMMINISTRATORE = UT.CODICE_FISCALE";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, idCaricamento);
			try {
				Object result = query.getSingleResult();
				return result != null ? result.toString() : "";
			} catch (NoResultException e) {
				return "";
			}
		} catch (Exception e) {
			throw new RuntimeException("Error in getEmailAmminCaricamento: " + e.getMessage(), e);
		}
	}

	public RoleGroupBean getDatiRoleGroupById(RoleGroupFinder finder)  {
		try {
			String sql = "SELECT "
					+ "		  CODICE_ROLE_GROUP,"
					+ "		  DISPONIBILE_VISIBILITA,"
					+ "		  IDONEO_VISIBILITA,"
					+ "		  CODICE_APPLICAZIONE"
					+ "		FROM"
					+ "		  ROLE_GROUP"
					+ "		WHERE"
					+ "		  CODICE_ROLE_GROUP = ?1 ";
			if(!"Rimozione".equals(finder.getAzione())) {
				sql += " AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
			}
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, finder.getCodRoleGroup());

			Object[] result = (Object[]) query.getSingleResult();
			RoleGroupBean bean = new RoleGroupBean();
			if (result[0] != null) bean.setCodiceRoleGroup(result[0].toString());
			if (result[1] != null) bean.setDisponibileVisibilita(result[1].toString());
			if (result[1] != null) bean.setIdoneoVisibilita(result[2].toString());
			if (result[1] != null) bean.setCodiceApplicazione(result[3].toString());
			return bean;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new RuntimeException("Error fetching RoleGroup: " + e.getMessage(), e);
		}
	}

	public Profilo getDatiProfiloById(String codiceProfilo)  {
		try {
			String sql = "SELECT CODICE_PROFILO, EXPLICIT_ENTITLEMENT, DISPONIBILE_VISIBILITA, CODICE_APPLICAZIONE"
					+ " FROM PROFILO WHERE CODICE_PROFILO = ?1 "
					+ " AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, codiceProfilo);

			Object[] result = (Object[]) query.getSingleResult();
			Profilo bean = new Profilo();
			if (result[0] != null) bean.setCodiceProfilo(result[0].toString());
			if (result[1] != null) bean.setExplicitEntitlement(result[1].toString());
			if (result[2] != null) bean.setDisponibileVisibilita(result[2].toString());
			if (result[3] != null) bean.setCodiceApplicazione(result[3].toString());
			return bean;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new RuntimeException("Error fetching profilo: " + e.getMessage(), e);
		}
	}

	public String getCfRichAutor(String cf)  {
		try {
			String sql = "SELECT "
					+ "			CODICE_FISCALE_RICHIEDENTE"
					+ "		FROM "
					+ "			ASSOCIAZ_OPER_RICHI_AUTOR "
					+ "		WHERE "
					+ "			CODICE_FISCALE_OPERATORE = ?1"
					+ "			AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE)";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);
			Object result = query.getSingleResult();
			return result != null ? result.toString() : null;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new RuntimeException("Error fetching getCfRichAutor: " + e.getMessage(), e);
		}
	}

	public RichiedenteCdR getDatiRichiedenteCdr(String cf)  {
		RichiedenteCdR richiedente = new RichiedenteCdR();
		try {
			String sql = "SELECT "
					+ "  			UT.CODICE_CDR,"
					+ "  			RC.CF_RICHIEDENTE"
					+ "		FROM "
					+ "  			UTENTI UT,"
					+ "  			RICHIEDENTE_CDR RC"
					+ "		WHERE "
					+ "  			UT.CODICE_FISCALE = ?1 "
					+ "  			AND (RC.DATA_FINE_VAL IS NULL OR RC.DATA_FINE_VAL > SYSDATE)"
					+ "  			AND (UT.DATA_FINE_VAL IS NULL OR UT.DATA_FINE_VAL > SYSDATE)"
					+ "  			AND UT.CODICE_CDR = RC.CODICE_CDR";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);
			Object result = query.getSingleResult();
			if (result instanceof Object[]) {
				Object[] row = (Object[]) result;
				if (row[0] != null) richiedente.setCodiceCdR(row[0].toString());
				if (row[1] != null) richiedente.setCfRichiedente(row[1].toString());
			}
			return richiedente;
		} catch (NoResultException e) {
			return richiedente;
		} catch (Exception e) {
			throw new RuntimeException("Error fetching getDatiRichiedenteCdr: " + e.getMessage(), e);
		}
	}

	public String getCodCdrByCf(String cf)  {
		try {
			String sql = "SELECT"
					+ "			CODICE_CDR"
					+ "		FROM"
					+ "			UTENTI"
					+ "		WHERE"
					+ "			CODICE_FISCALE = ?1 "
					+ "			AND (DATA_FINE_VAL IS NULL OR DATA_FINE_VAL > SYSDATE) ";
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, cf);
			Object result = query.getSingleResult();
			return result != null ? result.toString() : "";
		} catch (NoResultException e) {
			return "";
		} catch (Exception e) {
			throw new RuntimeException("Error fetching getCodCdrByCf: " + e.getMessage(), e);
		}
	}

	public int verificaEsistenzaProfiloEca(InterrogazioneCaricamentiFinder finder)  {
		try {
			StringBuilder sql = new StringBuilder("SELECT "
					+ "			COUNT(*)"
					+ "		FROM "
					+ "			CM_FILE_ABILITAZIONI "
					+ "		WHERE "
					+ "			ID_CARICAMENTO = ?1 "
					+ "			AND CODICE_FISCALE = ?2 "
					+ "			AND CODICE_ROLE_GROUP = ?3 "
					+ "  			AND CODICE_PROFILO = ?4 "
					+ "  		AND CODICE_CDR = ?5 ");

			Query query = entityManager.createNativeQuery(sql.toString());
			query.setParameter(1, finder.getIdCaricamento());
			query.setParameter(2, finder.getCodiceFiscaleUtente());
			query.setParameter(3, finder.getCodiceRoleGroup());
			query.setParameter(4, finder.getCodiceProfilo());
			query.setParameter(5, finder.getCodiceCdr());

			Object result = query.getSingleResult();
			return ((Number) result).intValue();
		} catch (NoResultException e) {
			return 0;
		} catch (Exception e) {
			throw new RuntimeException("Error verifying profilo eca: " + e.getMessage(), e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateCostanteSiga(CostantiSigaPT costante)  {
		log.info("IN BatchImportResponsabilitaNewDaoImpl.updateCostanteSiga(costante)");

		StringBuilder sql = new StringBuilder("UPDATE costanti_siga SET data_ultima_elaborazione = SYSDATE");
		int paramIndex = 1;

		if (costante.getValore() != null && !costante.getValore().isEmpty()) {
			sql.append(", valore_costante = ?").append(paramIndex++);
		}

		sql.append(" WHERE nome_costante = ?").append(paramIndex);

		Query query = entityManager.createNativeQuery(sql.toString());
		paramIndex = 1;
		if (costante.getValore() != null && !costante.getValore().isEmpty()) {
			query.setParameter(paramIndex++, costante.getValore());
		}
		query.setParameter(paramIndex, costante.getNome());

		query.executeUpdate();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserisciRegistroRichiesta(RegistroRichiesteBatchBean regRichBetchBean)  {

		StringBuilder sql = new StringBuilder();
		StringBuilder values = new StringBuilder();
		List<Object> params = new ArrayList<>();
		int paramIndex = 1;

		sql.append("INSERT INTO REGISTRO_RICHIESTE ( ID_CM, ");
		sql.append("ID_RICHIESTA, TIPO_RICHIESTA, CODICE_AMBITO, CF_UTENTE, CDR_UTENTE, ");
		sql.append("CF_RICHIEDENTE, CF_RICHIEDENTE_EFFETTIVO, NOTE_RICHIEDENTE, DATA_RICHIESTA, ");
		sql.append("CF_AUTORIZZATORE_1, CF_AUTORIZZATORE_1_EFFETTIVO");

		values.append("?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?")
		.append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++);
		values.append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++);
		values.append(", ?").append(paramIndex++).append(", ?").append(paramIndex++);

		params.add(regRichBetchBean.getIdCm());
		params.add(regRichBetchBean.getIdRichiesta());
		params.add(regRichBetchBean.getTipoRichiesta());
		params.add(regRichBetchBean.getCodiceAmbito());
		params.add(regRichBetchBean.getCfUtente());
		params.add(regRichBetchBean.getCdrUtente());
		params.add(regRichBetchBean.getCfRichiedente());
		params.add(regRichBetchBean.getCfRichiedenteEffettivo());
		params.add(regRichBetchBean.getNoteRichiedente());
		params.add(regRichBetchBean.getDataRichiesta());
		params.add(regRichBetchBean.getCfAutorizzatore1());
		params.add(regRichBetchBean.getCfAutorizzatore1Effettivo());

		if (regRichBetchBean.getNotaAutorizzatore1() != null && !regRichBetchBean.getNotaAutorizzatore1().isEmpty()) {
			sql.append(", NOTE_AUTORIZZATORE_1");
			values.append(", ?").append(paramIndex++);
			params.add(regRichBetchBean.getNotaAutorizzatore1());
		}

		sql.append(", STATO_RICHIESTA, RICHIEDENTE_AC, PRESA_VISIONE");
		values.append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++);
		params.add(regRichBetchBean.getStatoRichiesta());
		params.add(regRichBetchBean.getRichiedenteAc());
		params.add(regRichBetchBean.getPresaVisione());

		if (regRichBetchBean.getCfArchiviazione() != null && !regRichBetchBean.getCfArchiviazione().isEmpty()) {
			sql.append(", CF_ARCHIVIAZIONE");
			values.append(", ?").append(paramIndex++);
			params.add(regRichBetchBean.getCfArchiviazione());
		}

		if (regRichBetchBean.getCfArchiviazioneEffettivo() != null && !regRichBetchBean.getCfArchiviazioneEffettivo().isEmpty()) {
			sql.append(", CF_ARCHIVIAZIONE_EFFETTIVO");
			values.append(", ?").append(paramIndex++);
			params.add(regRichBetchBean.getCfArchiviazioneEffettivo());
		}

		if (regRichBetchBean.getDataEsitoFinale() != null) {
			sql.append(", DATA_ESITO_FINALE");
			values.append(", ?").append(paramIndex++);
			params.add(regRichBetchBean.getDataEsitoFinale());
		}

		if (regRichBetchBean.getEsitoFinale() != null && !regRichBetchBean.getEsitoFinale().isEmpty()) {
			sql.append(", ESITO_FINALE");
			values.append(", ?").append(paramIndex++);
			params.add(regRichBetchBean.getEsitoFinale());
		}

		sql.append(", AGGIORNA_SISTEMA_AUTORIZZ, CODICE_EVENTO, RICHIESTA_VISIBILE_RUOLI_SIGA, ");
		sql.append("CANCELLA_UTENTE_ESTERNO, NOTA_GENERAZIONE_RICHIESTA, CODICE_AMBITO_AUTORIZZAZIONE, ");
		sql.append("DATA_AUTORIZZAZIONE_1");

		values.append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++);
		values.append(", ?").append(paramIndex++).append(", ?").append(paramIndex++).append(", ?").append(paramIndex++);
		values.append(", ?").append(paramIndex++);

		params.add(regRichBetchBean.getAggiornaSistemaAutorizzazione());
		params.add(regRichBetchBean.getCodiceEvento());
		params.add(regRichBetchBean.getRichiestaVisibileRuoliSiga());
		params.add(regRichBetchBean.getCancellaUtenteEsterno());
		params.add(regRichBetchBean.getNotaGenerazioneRichiesta());
		params.add(regRichBetchBean.getCodiceAmbitoAutorizzazione());
		params.add(regRichBetchBean.getDataAutorizzazione1());

		if (regRichBetchBean.getIdIter() != null) {
			sql.append(", ITER_ID");
			values.append(", ?").append(paramIndex++);
			params.add(regRichBetchBean.getIdIter());
		}

		sql.append(", CDR_ARCHIVIAZIONE) VALUES (");
		values.append(", ?").append(paramIndex++);
		params.add(regRichBetchBean.getCdrArchiviazione());

		sql.append(values).append(")");

		try {
			Query query = entityManager.createNativeQuery(sql.toString());
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i + 1, params.get(i));
			}
			query.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	protected int appendInListWithParams(StringBuilder sql, List<String> list, List<Object> params, int startIndex) {
		int currentIndex = startIndex;
		for (int i = 0; i < list.size(); i++) {
			if (i > 0) {
				sql.append(", ");
			}
			sql.append("?").append(currentIndex);
			params.add(list.get(i));
			currentIndex++;
		}
		return currentIndex;
	}


	public static <T> List<List<T>> chunk(List<T> list, int chunkSize) {
		List<List<T>> chunks = new ArrayList<>();
		for (int i = 0; i < list.size(); i += chunkSize) {
			chunks.add(list.subList(i, Math.min(list.size(), i + chunkSize)));
		}
		return chunks;
	}
}