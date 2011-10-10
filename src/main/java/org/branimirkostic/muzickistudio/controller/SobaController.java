package org.branimirkostic.muzickistudio.controller;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.branimirkostic.muzickistudio.model.Korisnik;
import org.branimirkostic.muzickistudio.model.LogovaniKorisnik;
import org.branimirkostic.muzickistudio.model.Soba;
import org.branimirkostic.muzickistudio.model.Termin;
import org.branimirkostic.muzickistudio.model.TipSobe;
import org.branimirkostic.muzickistudio.model.Util;
import org.branimirkostic.muzickistudio.model.Vreme;
import org.branimirkostic.muzickistudio.service.KorisnikService;
import org.branimirkostic.muzickistudio.service.SobaService;
import org.branimirkostic.muzickistudio.service.TerminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import  java.util.List;

@Controller
@RequestMapping("/soba")
public class SobaController {
    protected static Logger logger = Logger.getLogger("sobaController");
    private static final String opis = "Soba ima: \nGuitarsko pojacalo: \nBass pojacalo: \nMikseta: \nPojacalo za glas: \nBubnjevi: ";
    @Resource(name="sobaService")
    private SobaService sobaService;

    @Resource(name="terminService")
    private TerminService terminService;

    @Resource(name="korisnikService")
    private KorisnikService korisnikService;

    @RequestMapping(value = "/dodajsobu.htm", method = RequestMethod.GET)
    public String vratiDodajSobuStranicu(@RequestParam(value="studioId", required=true) Long studioId,
        Model model, boolean validation) {
        logger.debug("Zahtev za prikaz dodajsobu stranice");
        if(!LogovaniKorisnik.daLiJeAdmin()){
            return "greskapage";
        }
        if (!validation) {
            Soba soba = new Soba();
            soba.setTip(TipSobe.Probe);
            soba.setOpis(opis);
            model.addAttribute("studioId", studioId);
            model.addAttribute("sobaAttribute", soba);
        }
        model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        return "dodajsobupage";
    }
  
    @RequestMapping(value = "/dodajsobu.htm", method = RequestMethod.POST)
    public String sacuvajSobu(@RequestParam(value="studioId", required=true) Long studioId,
        @ModelAttribute("sobaAttribute") @Valid Soba soba,
        BindingResult rezultat, Model model) {
    	logger.debug("Zahtev za dodavanje sobe");
    	try {
            if(!LogovaniKorisnik.daLiJeAdmin()){
                return "greskapage";
            }
            if(rezultat.hasErrors()){
                model.addAttribute("studioId", studioId);
                return vratiDodajSobuStranicu(studioId, model, true);
            }
            else{
                sobaService.sacuvajSobu(studioId, soba);
                model.addAttribute("dodavanjeSobePoruka",
                    soba.getNaziv()+" je uspesno dodata.");
            }
    	} catch (Exception e) {
            e.printStackTrace();
        }
    	model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        return vratiDodajSobuStranicu(studioId, model, false);
    }
    
    @RequestMapping(value = "/sobainfo.htm", method = RequestMethod.GET)
    public String vratiSobaInfoStranicu(@RequestParam(value="studioId", required=true) Long studioId,
        @RequestParam(value="sobaId", required=true) Long sobaId,
        Model model, boolean validation) {
        logger.debug("Zahtev za prikaz sobainfo stranice");
        try {
            if(!LogovaniKorisnik.daLiJeLogovan()){
                return "greskapage";
            }
            Soba soba = sobaService.pronadjiSobuPoId(sobaId);
            if(soba == null){
                return "greskapage";
            }
            model.addAttribute("studioId", studioId);
            model.addAttribute("sobaAttribute", soba);
            if(LogovaniKorisnik.daLiJeKorisnik()){
                if (!validation) {
                    Termin termin = new Termin();
                    model.addAttribute("terminAttribute", termin);
                }
                model.addAttribute("satnica", Vreme.class);
                model.addAttribute("terminiAttribute",
                terminService.vratiTerminePoSobiIKorisniku(sobaId,
                    LogovaniKorisnik.vratiLogovanogKorisnika().getUsername()));
            }
            if(LogovaniKorisnik.daLiJeAdmin()){
                Korisnik korisnik = null;
                if (!validation) {
                    korisnik = new Korisnik();
                    model.addAttribute("korisnikAttribute", korisnik);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> datumi = Util.getDatum();
        //List<Vreme> vremena = Util.getVreme();
        for(int i = 0; i < 7; i++){
            model.addAttribute("datum_"+i,datumi.get(i));
            for(int j = 0; j < 7; j++){
                model.addAttribute("termin_"+i+"_"+j+"", terminService.vratiStatusTermina(sobaId,
                        datumi.get(i), j));
            }

        }
        model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        model.addAttribute("daLiJeKorisnik", LogovaniKorisnik.daLiJeKorisnik());
        model.addAttribute("daLiJeAdmin", LogovaniKorisnik.daLiJeAdmin());
        return "sobainfopage";
    }

    @RequestMapping(value = "/sobainfo.htm", method = RequestMethod.POST)
    public String rezervisiTermin(@RequestParam(value="studioId", required=true) Long studioId,
        @RequestParam(value="sobaId", required=true) Long sobaId,
        @ModelAttribute("terminAttribute") @Valid Termin termin,
        BindingResult rezultat, Model model) {
    	logger.debug("Zahtev za dodavanje sobe");
    	try {
            if(!LogovaniKorisnik.daLiJeKorisnik()){
                    return "greskapage";
            }
            if(rezultat.hasErrors()){
                model.addAttribute("studioId", studioId);
                model.addAttribute("sobaId", sobaId);
                return vratiSobaInfoStranicu(studioId, sobaId, model, true);
            }
            else{
                Soba soba = sobaService.pronadjiSobuPoId(sobaId);
                Korisnik korisnik = korisnikService.vratiKorisnikaPoKorImenu(LogovaniKorisnik.vratiLogovanogKorisnika().getUsername());
                termin.setSoba(soba);
                termin.setKorisnik(korisnik);
                termin.setIzdat(false);
                terminService.rezervisiTermin(termin);
                model.addAttribute("rezervacijaTerminaPoruka",
                    "Termin je rezervisan.");
            }
        } catch (Exception e) {
            System.out.println("ERROR............"+e.getMessage());
            model.addAttribute("rezervacijaTermina2Poruka", "Dodavanje neuspesno: Termin postoji.");
            return vratiSobaInfoStranicu(studioId, sobaId, model, true);
        }
        model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        return vratiSobaInfoStranicu(studioId, sobaId, model, false);
    }
    
    @RequestMapping(value = "/sobainfo/ponistitermin.htm", method = RequestMethod.GET)
    public String ponistiRezervaciju(@RequestParam(value="studioId", required=true) Long studioId,
        @RequestParam(value="sobaId", required=true) Long sobaId,
        @RequestParam(value="terminId", required=true) Long terminId,
        Model model) {
    	logger.debug("Zahtev za ponistavanje termina");
    	try{
            if(Util.daLiJeKorisnikovTermin(
                terminService.vratiTerminePoSobiIKorisniku(sobaId,
                    LogovaniKorisnik.vratiLogovanogKorisnika().getUsername()),
                    terminId)) {
                terminService.ponistiRezervaciju(terminId);
                model.addAttribute("studioId", studioId);
                model.addAttribute("sobaId", sobaId);
                model.addAttribute("terminId", terminId);
                model.addAttribute("ponistavanjeTerminaPoruka",
                    "Rezervacija uspesno ponistena.");
            }
            else{
                return "greskapage";
            }
        }
    	catch(Exception ex){
            return "greskapage";
    	}
        return vratiSobaInfoStranicu(studioId, sobaId, model, false);
    }

    @RequestMapping(value = "/sobainfo/pronadjitermin.htm", method = RequestMethod.POST)
    public String pronadjiTermineKorisnika(@RequestParam(value="studioId", required=true) Long studioId,
        @RequestParam(value="sobaId", required=true) Long sobaId,
        @ModelAttribute("korisnikAttribute") @Valid Korisnik korisnik,
        BindingResult rezultat, Model model) {
    	logger.debug("Zahtev za vracanje termina korisnika");
        try {
            if(!LogovaniKorisnik.daLiJeLogovan()){
                return "greskapage";
            }
            model.addAttribute("studioId", studioId);
            model.addAttribute("sobaAttribute", sobaId);
            if(LogovaniKorisnik.daLiJeAdmin()){
                model.addAttribute("terminiRacunAttribute",
                terminService.vratiTerminePoSobiIKorisniku(sobaId,
                    korisnik.getKorIme()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        model.addAttribute("daLiJeKorisnik", LogovaniKorisnik.daLiJeKorisnik());
        model.addAttribute("daLiJeAdmin", LogovaniKorisnik.daLiJeAdmin());
        return vratiSobaInfoStranicu(studioId, sobaId, model, false);
    }

   @RequestMapping(value = "/sobainfo/izdavanjeracuna.htm", method = RequestMethod.GET)
    public String izdajRacun(@RequestParam(value="studioId", required=true) Long studioId,
        @RequestParam(value="sobaId", required=true) Long sobaId,
        @RequestParam(value="terminId", required=true) Long terminId,
        Model model) {
    	logger.debug("Zahtev za izdavanje racuna");
    	try{
            terminService.izdajRacun(terminId);
            model.addAttribute("studioId", studioId);
            model.addAttribute("sobaId", sobaId);
            model.addAttribute("terminId", terminId);
            model.addAttribute("izdavanjeRacunaPoruka",
                "Racun je izdat.");
        }
    	catch(Exception ex){
            return "greskapage";
    	}
        return vratiSobaInfoStranicu(studioId, sobaId, model, false);
    }
}
