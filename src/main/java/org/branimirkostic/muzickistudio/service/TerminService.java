package org.branimirkostic.muzickistudio.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.branimirkostic.muzickistudio.model.Korisnik;
import org.branimirkostic.muzickistudio.model.Termin;
import org.branimirkostic.muzickistudio.model.TerminSequenceNum;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("terminService")
@Transactional
public class TerminService {
	
    protected static Logger logger = Logger.getLogger("terminService");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public void rezervisiTermin(Termin termin) {
        try {
            logger.debug("Rezervacija termina");
            Session session = sessionFactory.getCurrentSession();
            session.save(termin);
            termin.setId(new TerminSequenceNum());
            session.save(termin.getId());
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
    }
	
    @SuppressWarnings("unchecked")
    public List<Termin> vratiTerminePoSobiIKorisniku(Long sobaId, String korIme) {
        List<Termin> termini = null;
        try {
            logger.debug("Vracanje termina korisnika za sobu");
            Session session = sessionFactory.getCurrentSession();
            Query upit = session.createQuery("FROM Termin AS T " +
                "LEFT JOIN FETCH T.id LEFT JOIN FETCH T.soba AS S LEFT JOIN FETCH T.korisnik AS K " +
                "WHERE S.id = " + sobaId + " AND K.korIme = '" + korIme + "'");
            termini = (List<Termin>) upit.list();
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
        return termini;
    }

    @SuppressWarnings("unchecked")
    public List<Termin> vratiSveTermine() {
        List<Termin> termini = null;
        try {
            logger.debug("Vracanje termina");
            Session session = sessionFactory.getCurrentSession();
            Query upit = session.createQuery("FROM Termin LEFT JOIN FETCH T.id");
            termini = (List<Termin>) upit.list();
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
        return termini;
    }

    public List<Termin> vratiSveTermineZaSobu(Long sobaId) {
        List<Termin> termini = null;
        try {
            logger.debug("Vracanje termina korisnika");
            Session session = sessionFactory.getCurrentSession();
            Query upit = session.createQuery("FROM Termin AS T " +
                "LEFT JOIN FETCH T.id LEFT JOIN FETCH T.soba AS S WHERE S.id = " + sobaId);
            Korisnik korisnik = (Korisnik) upit.uniqueResult();
            termini = korisnik.getTermini();
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
        return termini;
    }

    public void ponistiRezervaciju(Long id) {
        logger.debug("Ponistavanje termina");
        Session session = sessionFactory.getCurrentSession();
        Query upit = session.createQuery("FROM Termin AS T " +
            "LEFT JOIN FETCH T.id WHERE T.id.id = " + id);
        Termin termin = (Termin) upit.uniqueResult();
        session.delete(termin);
    }

    public Boolean daLiJeKorisnikovTermin(Long sobaId, String korIme, Long terminId){
        boolean ok = false;
        List<Termin> termini = vratiTerminePoSobiIKorisniku(sobaId, korIme);
        if(termini == null || termini.size() == 0){
            return ok;
        }
        else{
            for(Termin termin : termini){
                if(termin.getId().getId().equals(terminId)){
                    ok = true;
                    break;
                }
            }
        }
        return ok;
    }
}
