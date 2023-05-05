package com.sang.sangschoolback.controller;

import com.sang.sangschoolback.controller.dto.TableauUtilisateurDto;
import com.sang.sangschoolback.controller.dto.UtilisateurDto;
import com.sang.sangschoolback.entity.Utilisateur;
import com.sang.sangschoolback.facade.UtilisateurFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
    private final UtilisateurFacade utilisateurFacade;

    public UtilisateurController(UtilisateurFacade utilisateurFacade) {
        this.utilisateurFacade = utilisateurFacade;
    }
    @GetMapping("/liste")
    public TableauUtilisateurDto ListeUtilisateur() {
        return utilisateurFacade.listeUtilisateur();
    }
    @PostMapping("/enregistrerOuModifier")
    public void enregistrerUtilisateur(@RequestBody UtilisateurDto utilisateurDto){

        this.utilisateurFacade.enregistrerOuModifierUtilisateur(utilisateurDto);
    }
}
