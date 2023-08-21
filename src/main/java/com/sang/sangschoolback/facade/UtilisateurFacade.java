package com.sang.sangschoolback.facade;

import com.sang.sangschoolback.controller.dto.TableauUtilisateurDto;
import com.sang.sangschoolback.controller.dto.UtilisateurDto;
import com.sang.sangschoolback.entity.Personnel;
import com.sang.sangschoolback.entity.enums.Role;
import com.sang.sangschoolback.entity.Utilisateur;
import com.sang.sangschoolback.entity.enums.StatutUtilisateur;
import com.sang.sangschoolback.repository.PersonnelRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;

@Service
public class UtilisateurFacade {


    private final PersonnelRepository personnelRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String DEFAULT_PASSWORD = "123";

    public UtilisateurFacade( PersonnelRepository personnelRepository, PasswordEncoder passwordEncoder) {
        this.personnelRepository = personnelRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     * @return  Liste touts les utilisateurs
     */
    public TableauUtilisateurDto listeUtilisateur() {
        List<Personnel>  listeDespersonnels = personnelRepository.findAll();

        Long totalUtilisateurs = (long) listeDespersonnels.size();
        Map<Boolean, Long> map = listeDespersonnels.stream()
                .collect(partitioningBy((Utilisateur utilisateur) -> ((StatutUtilisateur.ACTIF).equals(utilisateur.getStatut())), counting()));
        long utilisateurActifs = map.get(true);
        long UtilisateurInactif = map.get(false);
        List<UtilisateurDto> utilisateurs = listeDespersonnels.stream()
                .map(UtilisateurDto::new)
                .collect(Collectors.toList());

        return new TableauUtilisateurDto(totalUtilisateurs, utilisateurActifs, UtilisateurInactif, utilisateurs);
    }

    /***
     * Enregistrer utilisateur
     * @param utilisateurDto
     */
    @Transactional
    public void enregistrerOuModifierUtilisateur(UtilisateurDto utilisateurDto) {
        Personnel personnel = personnelRepository.findByUsername(utilisateurDto.getUsername().trim()).orElse(null);
        if (personnel == null ){
            personnel = new Personnel(utilisateurDto.getNom(), utilisateurDto.getPrenoms(), utilisateurDto.getEmail(),
                        utilisateurDto.getUsername(), passwordEncoder.encode(DEFAULT_PASSWORD), Role.valueOf(utilisateurDto.getRole()), StatutUtilisateur.valueOf(utilisateurDto.getStatut()),20);
        }else {
            personnel.mettreAJour(utilisateurDto.getNom(), utilisateurDto.getPrenoms(), utilisateurDto.getEmail(),Role.valueOf(utilisateurDto.getRole()), StatutUtilisateur.valueOf(utilisateurDto.getStatut()),20);
        }
        personnelRepository.save(personnel);
    }

    /**
     * Supprimer utilisateur
     * @param utilisateur
     */

    @Transactional
    public void supprimerUtilisateurParId(Utilisateur utilisateur) {

        // System.out.println("okkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        // repository.deleteById(utilisateur.getId());
    }
}
