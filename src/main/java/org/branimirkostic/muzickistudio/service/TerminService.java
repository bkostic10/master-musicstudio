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
                "WHERE S.id = " + sobaId + " AND K.korIme = '" + korIme + "' AND T.izdat = "+false);
            termini = (List<Termin>) upit.list();
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
        return termini;
    }

    public String vratiStatusTermina(Long sobaId, String datum, int vreme) {
        Termin termin = null;
        String rezervisan = "";
        try {
            logger.debug("Vracanje termina korisnika za sobu");
            Session session = sessionFactory.getCurrentSession();
            Query upit = session.createQuery("FROM Termin AS T " +
                "LEFT JOIN FETCH T.id LEFT JOIN FETCH T.soba AS S " +
                "WHERE S.id = " + sobaId + " AND T.datum = '"+datum+"' AND T.satnica = "+vreme);
            termin = (Termin) upit.uniqueResult();
            if(termin == null)
                rezervisan = "O";
            else
                rezervisan = "X";
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
        return rezervisan;
    }

    public void izdajRacun(Long id) {
        try {
            logger.debug("Izdavanje racuna");
            Session session = sessionFactory.getCurrentSession();
            Query upit = session.createQuery("FROM Termin AS T " +
                "LEFT JOIN FETCH T.id WHERE T.id.id = " + id);
            Termin termin = (Termin) upit.uniqueResult();
            termin.setIzdat(true);
            session.update(termin);
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
    }

    public void ponistiRezervaciju(Long id) {
        logger.debug("Ponistavanje termina");
        Session session = sessionFactory.getCurrentSession();
        Query upit = session.createQuery("FROM Termin AS T " +
            "LEFT JOIN FETCH T.id WHERE T.id.id = " + id);
        Termin termin = (Termin) upit.uniqueResult();
        session.delete(termin);
    }

}
