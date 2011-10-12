package org.branimirkostic.muzickistudio.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.branimirkostic.muzickistudio.model.Korisnik;
import org.branimirkostic.muzickistudio.model.LogovaniKorisnik;
import org.branimirkostic.muzickistudio.service.KorisnikService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/korisnik")
public class KorisnikController {
    protected static Logger logger = Logger.getLogger("korisnikController");

    @Resource(name="korisnikService")
    private KorisnikService korisnikService;

    @RequestMapping(value = "/registracija.htm", method = RequestMethod.GET)
    public String vratiRegistracijaStranicu(Model model) {
        logger.debug("Zahtev za prikaz registracija stranice");
        if(LogovaniKorisnik.daLiJeLogovan()){
            return "greskapage";
        }
        Korisnik korisnik = new Korisnik();
        model.addAttribute("korisnikAttribute", korisnik);
        model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        return "registracijapage";
    }
	
    @RequestMapping(value = "/registracija.htm", method = RequestMethod.POST)
    public String registracijaKorisnika(@ModelAttribute("korisnikAttribute") @Valid Korisnik korisnik,
        BindingResult rezultat, Model model) {
    	logger.debug("Zahtev za registraciju korisnika");
    	try {
            if(rezultat.hasErrors()){
                return "registracijapage";
            }
            else{
                if(!korisnik.getLozinka().equals(korisnik.getLozinka2())){
                    model.addAttribute("registracija2Poruka", "Lozinke moraju biti iste.");
                    return "registracijapage";
                }
                else{
                    korisnikService.registracijaKorisnika(korisnik);
                    model.addAttribute("korisnikAttribute", korisnik);
                    model.addAttribute("registracijaPoruka", korisnik.getIme()+" "+korisnik.getPrezime()+" je uspesno registrovan.");
                }
            }
        } catch (Exception e) {
            model.addAttribute("registracija2Poruka", "Korisnicko ime vec postoji.");
            return "registracijapage";
        }
        model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        return vratiRegistracijaStranicu(model);
    }
    
    @RequestMapping(value = "/aktorinfo.htm", method = RequestMethod.GET)
    public String vratiAktorInfoStranicu(Model model) {
        logger.debug("Zahtev za prikaz korisnika");
        try {
            if(!LogovaniKorisnik.daLiJeLogovan()){
                return "greskapage";
            }
            Korisnik korisnik = korisnikService.vratiKorisnikaPoKorImenu(
                LogovaniKorisnik.vratiLogovanogKorisnika().getUsername());
            model.addAttribute("korisnikAttribute", korisnik);
            model.addAttribute("privilegija", korisnik.getPrivilegija() == 1 ? "Administrator" : "Korisnik");
            model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        } catch (Exception e) {
            e.printStackTrace();
            return "greskapage";
        }
        return "aktorinfopage";
    }
}
