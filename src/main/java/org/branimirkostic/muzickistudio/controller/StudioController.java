package org.branimirkostic.muzickistudio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;
 
import org.apache.log4j.Logger;
import org.branimirkostic.muzickistudio.model.LogovaniKorisnik;
import org.branimirkostic.muzickistudio.model.Soba;
import org.branimirkostic.muzickistudio.model.Studio;
import org.branimirkostic.muzickistudio.service.SobaService;
import org.branimirkostic.muzickistudio.service.StudioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
@RequestMapping("/studio")
public class StudioController {
 
    protected static Logger logger = Logger.getLogger("studioController");

    @Resource(name="studioService")
    private StudioService studioService;

    @Resource(name="sobaService")
    private SobaService sobaService;

    @RequestMapping(value = "/studios.htm", method = RequestMethod.GET)
    public String vratiSveStudije(Model model) {
        logger.debug("Primljen zahtev za pregled studija");

        List<Studio> studios = studioService.vratiSveStudije();

        if(!LogovaniKorisnik.daLiJeLogovan()){
            return "greskapage";
        }
        model.addAttribute("studios", studios);
        model.addAttribute("daLiJeAdmin", LogovaniKorisnik.daLiJeAdmin());
        model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        return "studiospage";
    }

    @RequestMapping(value = "/dodajstudio.htm", method = RequestMethod.GET)
    public String vratiDodajStudioStranicu(Model model) {
        logger.debug("Zahtev za prikaz dodajstudio stranice");
        if(!LogovaniKorisnik.daLiJeAdmin()){
            return "greskapage";
        }
        Studio studio = new Studio();
        List<Soba> sobe = new ArrayList<Soba>();
        studio.setSobe(sobe);
        model.addAttribute("studioAttribute", studio);
        model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        return "dodajstudiopage";
    }

    @RequestMapping(value = "/dodajstudio.htm", method = RequestMethod.POST)
    public String sacuvajStudio(@ModelAttribute("studioAttribute") @Valid Studio studio,
            BindingResult rezultat, Model model) {
        logger.debug("Zahtev za dodavanje studija");
        if(!LogovaniKorisnik.daLiJeAdmin()){
            return "greskapage";
        }
        if(rezultat.hasErrors()){
            return "dodajstudiopage";
        }
        else{
            studioService.sacuvajStudio(studio);
            model.addAttribute("studioAttribute", studio);
            model.addAttribute("dodavanjeStudijaPoruka", studio.getNaziv()+" je uspesno dodat.");
        }
        model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        return vratiDodajStudioStranicu(model);
    }

    @RequestMapping(value = "/studioinfo.htm", method = RequestMethod.GET)
    public String vratiStudioInfoStranicu(@RequestParam(value="studioId", required=true) Long studioId,
            Model model) {
        logger.debug("Zahtev za prikaz studioinfo stranice");
        try {
            if(!LogovaniKorisnik.daLiJeLogovan()){
                    return "greskapage";
            }
            Studio studio = studioService.pronadjiStudioPoId(studioId);
            if(studio == null){
                return "greskapage";
            }
            model.addAttribute("studioAttribute", studio);
            model.addAttribute("probe", studio.vratiSobeZaProbe());
            model.addAttribute("snimanje", studio.vratiSobeZaSnimanje());
            model.addAttribute("daLiJeAdmin", LogovaniKorisnik.daLiJeAdmin());
            model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "studioinfopage";
    }
    @RequestMapping(value = "/studioinfo/obrisanasoba.htm", method = RequestMethod.GET)
    public String obrisiSobu(@RequestParam(value="studioId", required=true) Long studioId,
            @RequestParam(value="sobaId", required=true) Long sobaId,
            Model model){
        logger.debug("Zahtev za brisanje sobe");
        try {
            if(!LogovaniKorisnik.daLiJeLogovan()){
                return "greskapage";
            }
            Studio studio = studioService.pronadjiStudioPoId(studioId);
            Soba soba = sobaService.pronadjiSobuPoId(sobaId);
            if(studio == null || soba == null){
                    return "greskapage";
            }
            sobaService.obrisiSobu(studioId, sobaId);
            model.addAttribute("studioId", studioId);
            model.addAttribute("sobaId", sobaId);
            //model.addAttribute("probe", studio.vratiSobeZaProbe());
            //model.addAttribute("snimanje", studio.vratiSobeZaSnimanje());
            //model.addAttribute("daLiJeAdmin", LogovaniKorisnik.daLiJeAdmin());
            //model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
            model.addAttribute("obrisanaSobaPoruka", soba.getNaziv()+" je uspesno obrisana.");
        } catch (Exception e) {
            e.printStackTrace();
            return "greskapage";
        }
        return vratiStudioInfoStranicu(studioId, model);
    }
    @RequestMapping(value = "/studios/obrisanstudio.htm", method = RequestMethod.GET)
    public String obrisiStudio(@RequestParam(value="studioId", required=true) Long studioId,
            Model model){
        logger.debug("Zahtev za brisanje sobe");
        try {
            if(!LogovaniKorisnik.daLiJeLogovan()){
                    return "greskapage";
            }
            Studio studio = studioService.pronadjiStudioPoId(studioId);
            if(studio == null){
                return "greskapage";
            }
            studioService.obrisiStudio(studioId);
            model.addAttribute("studioId", studioId);
            //model.addAttribute("probe", studio.vratiSobeZaProbe());
            //model.addAttribute("snimanje", studio.vratiSobeZaSnimanje());
            //model.addAttribute("daLiJeAdmin", LogovaniKorisnik.daLiJeAdmin());
            //model.addAttribute("daLiJeLogovan", LogovaniKorisnik.daLiJeLogovan());
            model.addAttribute("obrisanStudioPoruka", studio.getNaziv()+" je uspesno obrisan.");
        } catch (Exception e) {
            e.printStackTrace();
            return "greskapage";
        }
        return vratiSveStudije(model);
    }
}
