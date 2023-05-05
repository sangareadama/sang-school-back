package com.sang.sangschoolback.facade;

import com.sang.sangschoolback.controller.dto.TableauUtilisateurDto;
import com.sang.sangschoolback.controller.dto.UtilisateurDto;
import com.sang.sangschoolback.entity.enums.Role;
import com.sang.sangschoolback.entity.Utilisateur;
import com.sang.sangschoolback.entity.enums.StatutUtilisateur;
import com.sang.sangschoolback.repository.UtilisateurRepository;
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
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String DEFAULT_PASSWORD = "123";

    public UtilisateurFacade(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     * @return  Liste touts les utilisateurs
     */
    public TableauUtilisateurDto listeUtilisateur() {
        List<Utilisateur>  listeDesUtilisateurs = utilisateurRepository.findAll();

        Long totalUtilisateurs = (long) listeDesUtilisateurs.size();
        Map<Boolean, Long> map = listeDesUtilisateurs.stream()
                .collect(partitioningBy((Utilisateur utilisateur) -> ((StatutUtilisateur.ACTIF).equals(utilisateur.getStatut())), counting()));
        long utilisateurActifs = map.get(true);
        long UtilisateurInactif = map.get(false);
        List<UtilisateurDto> utilisateurs = listeDesUtilisateurs.stream()
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
        Utilisateur utilisateur = utilisateurRepository.findByUsername(utilisateurDto.getUsername().trim()).orElse(null);
        if (utilisateur == null ){
            utilisateur = new Utilisateur(utilisateurDto.getNom(), utilisateurDto.getPrenoms(), utilisateurDto.getEmail(),
                        utilisateurDto.getUsername(), passwordEncoder.encode(DEFAULT_PASSWORD), Role.valueOf(utilisateurDto.getRole()), StatutUtilisateur.valueOf(utilisateurDto.getStatut()));
        }else {
            utilisateur.update(utilisateurDto.getNom(), utilisateurDto.getPrenoms(), utilisateurDto.getEmail(),Role.valueOf(utilisateurDto.getRole()), StatutUtilisateur.valueOf(utilisateurDto.getStatut()));
        }
        utilisateurRepository.save(utilisateur);
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
