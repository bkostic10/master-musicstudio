package org.branimirkostic.muzickistudio.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.branimirkostic.muzickistudio.model.Korisnik;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("korisnikService")
@Transactional
public class KorisnikService {
    protected static Logger logger = Logger.getLogger("korisnikService");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public Korisnik vratiKorisnikaPoKorImenu(String korIme) {
        List<?> list = null;
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Korisnik.class).
            add(Restrictions.eq("korIme", korIme));
            list = criteria.list();
            if(list == null || list.isEmpty())
                return null;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return (Korisnik) list.get(0);
    }
    public void registracijaKorisnika(Korisnik korisnik) {
        try{
            logger.debug("Dodavanje korisnika");
            Session session = sessionFactory.getCurrentSession();
            PasswordEncoder encoder = new Md5PasswordEncoder();
            String lozinka = encoder.encodePassword(korisnik.getLozinka(), null);
            korisnik.setLozinka(lozinka);
            korisnik.setLozinka2(lozinka);
            korisnik.setPrivilegija(2);
            session.save(korisnik);
        } catch (HibernateException e) {
            throw new HibernateException("Greska: "+e.getMessage());
        }
    }
}