package org.branimirkostic.muzickistudio.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.branimirkostic.muzickistudio.model.Soba;
import org.branimirkostic.muzickistudio.model.Studio;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sobaService")
@Transactional
public class SobaService {
    protected static Logger logger = Logger.getLogger("sobaService");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<Soba> vratiSobeStudija(Long id) {
        Query upit;
        Studio studio;
        try {
            logger.debug("Vracanje svih soba studija");
            Session session = sessionFactory.getCurrentSession();
            upit = session.createQuery("FROM Studio AS S " +
                "LEFT JOIN FETCH S.Sobe WHERE S.ID = " + id);
            studio = (Studio) upit.uniqueResult();

        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
        return  studio.getSobe();
    }

    public Soba pronadjiSobuPoId(Long id) {
        Soba soba;
        try {
            logger.debug("Vracanje sobe po id-u");
            Session session = sessionFactory.getCurrentSession();
            soba = (Soba)session.get(Soba.class, id);
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
        return soba;
    }
    public void sacuvajSobu(Long studioId, Soba soba) {
        try{
            logger.debug("Dodavanje sobe");
            Session session = sessionFactory.getCurrentSession();
            //session.save(soba);
            Studio studio = (Studio) session.get(Studio.class, studioId);
            studio.getSobe().add(soba);
            session.save(studio);
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
    }

    public void obrisiSobu(Long studioId, Long sobaId){
        try{
            logger.debug("Brisanje sobe");
            Session session = sessionFactory.getCurrentSession();
            Studio studio = (Studio) session.get(Studio.class, studioId);
            Soba soba = pronadjiSobuPoId(sobaId);
            studio.getSobe().remove(soba);
            session.save(studio);
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
    }
}
