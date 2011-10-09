package org.branimirkostic.muzickistudio.controller;

import org.apache.log4j.Logger;
import org.branimirkostic.muzickistudio.model.LogovaniKorisnik;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AutentikacijaController {
    protected static Logger logger = Logger.getLogger("autentikacijaController");

    @RequestMapping(value = "/prijava.htm", method = RequestMethod.GET)
    public String vratiPrijavaStranicu(@RequestParam(value="error", required=false) boolean error,
        Model model) {
        logger.debug("Zahtev za prijava stranicom");
        if(LogovaniKorisnik.daLiJeLogovan()){
            return "greskapage";
        }
        if (error == true) {
            model.addAttribute("error", "Korisnicko ime ili lozinka nisu pravilno uneti.");
        } else {
            model.addAttribute("error", "");
        }
        model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        return "prijavapage";
    }

    
}
