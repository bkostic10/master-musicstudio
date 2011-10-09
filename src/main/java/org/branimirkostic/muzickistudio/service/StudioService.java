package org.branimirkostic.muzickistudio.service;

import java.util.List;

import javax.annotation.Resource;
 
import org.apache.log4j.Logger;
import org.branimirkostic.muzickistudio.model.Studio;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studioService")
@Transactional
public class StudioService {
 
    protected static Logger logger = Logger.getLogger("studioService");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Studio> vratiSveStudije() {
        Query upit;
        try {
            logger.debug("Vracanje svih studija");
            Session session = sessionFactory.getCurrentSession();
            upit = session.createQuery("FROM Studio");
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
        return upit.list();
    }

    public Studio pronadjiStudioPoId(Long id) {
        Studio studio;
        try {
            Session session = sessionFactory.getCurrentSession();
            Query upit = session.createQuery("FROM Studio AS S " +
                "LEFT JOIN FETCH S.sobe WHERE S.id = " + id);
            studio = (Studio) upit.uniqueResult();
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
        return studio;
    }

    public void sacuvajStudio(Studio studio) {
        try {
            logger.debug("Dodavanje studija");
            Session session = sessionFactory.getCurrentSession();
            session.save(studio);
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
    }
    public void obrisiStudio(Long studioId){
        try{
            logger.debug("Brisanje studija");
            Session session = sessionFactory.getCurrentSession();
            Studio studio = (Studio) session.get(Studio.class, studioId);
            session.delete(studio);
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
    }
}
