package org.branimirkostic.muzickistudio.controller;

import org.apache.log4j.Logger;
import org.branimirkostic.muzickistudio.model.LogovaniKorisnik;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {
    protected static Logger logger = Logger.getLogger("mainController");

    @RequestMapping(value = "/pocetna.htm", method = RequestMethod.GET)
    public String vratiPocetnuStranicu(Model model) {
        logger.debug("Zahtev za prikaz pocetne stranice");
        model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        return "pocetnapage";
    }

}
