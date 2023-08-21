package com.sang.sangschoolback.controller;

import com.sang.sangschoolback.controller.dto.TableauUtilisateurDto;
import com.sang.sangschoolback.controller.dto.UtilisateurDto;
import com.sang.sangschoolback.facade.EtudiantFacade;
import com.sang.sangschoolback.facade.UtilisateurFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/etudiant")
public class EtudiantController {
    private final EtudiantFacade etudiantFacade;
    public EtudiantController(EtudiantFacade etudiantFacade) {
        this.etudiantFacade = etudiantFacade;
    }

    @GetMapping("/liste")
    public TableauUtilisateurDto ListeEtudiant() {
        return etudiantFacade.listeEdutiant();
    }
    @PostMapping("/enregistrerOuModifier")
    public void enregistrerEtudiant(@RequestBody UtilisateurDto utilisateurDto){

        this.etudiantFacade.enregistrerOuModifierEtudiant(utilisateurDto);
    }
}
