package org.branimirkostic.muzickistudio.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.branimirkostic.muzickistudio.model.Korisnik;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

@Transactional(readOnly = true)
public class AutentikacijaService implements UserDetailsService{
    protected static Logger logger = Logger.getLogger("autentikacijaService");

    @Resource(name="korisnikService")
    private KorisnikService korisnikService;

    public UserDetails loadUserByUsername(String korIme)
                throws UsernameNotFoundException, DataAccessException {
        UserDetails user = null;
        try {
            if(korisnikService.vratiKorisnikaPoKorImenu(korIme) != null){
                Korisnik korisnik = korisnikService.vratiKorisnikaPoKorImenu(korIme);
                user = new User(korisnik.getKorIme(),
                    korisnik.getLozinka(), true, true, true, true,
                    getAuthorities(korisnik.getPrivilegija()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    private Collection<GrantedAuthority> getAuthorities(Integer access) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        if (access.compareTo(2) == 0) {
            logger.debug("Dodavanje ROLA_KORISNIK korisniku");
            authList.add(new GrantedAuthorityImpl("ROLA_KORISNIK"));
        }
        if (access.compareTo(1) == 0) {
            logger.debug("Dodavanje ROLE_ADMIN korisniku");
            authList.add(new GrantedAuthorityImpl("ROLA_ADMIN"));
        }
        return authList;
      }
}
