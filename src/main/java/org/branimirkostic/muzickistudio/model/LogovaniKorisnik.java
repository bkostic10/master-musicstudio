package org.branimirkostic.muzickistudio.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class LogovaniKorisnik {
	
    public static User vratiLogovanogKorisnika() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails)
            return (User)principal;
        return null;
    }

    public static boolean daLiJeLogovan() {
        return vratiLogovanogKorisnika() != null;
    }

    public static boolean daLiJeAdmin(){
        boolean check = false;
        if (daLiJeLogovan()) {
            for (GrantedAuthority g : vratiLogovanogKorisnika()
                    .getAuthorities()) {
                if (g.getAuthority().equals("ROLA_ADMIN")) {
                    check = true;
                }
            }
        }
        return check;
    }

    public static boolean daLiJeKorisnik(){
        boolean check = false;
        if (daLiJeLogovan()) {
            for(GrantedAuthority g : vratiLogovanogKorisnika().getAuthorities()){
                if(g.getAuthority().equals("ROLA_KORISNIK")){
                    check = true;
                }
            }
        }
        return check;
    }
}
