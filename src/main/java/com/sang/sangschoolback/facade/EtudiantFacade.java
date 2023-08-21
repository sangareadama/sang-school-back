package com.sang.sangschoolback.facade;

import com.sang.sangschoolback.controller.dto.TableauUtilisateurDto;
import com.sang.sangschoolback.controller.dto.UtilisateurDto;
import com.sang.sangschoolback.entity.Etudiant;
import com.sang.sangschoolback.entity.Personnel;
import com.sang.sangschoolback.entity.Utilisateur;
import com.sang.sangschoolback.entity.enums.Role;
import com.sang.sangschoolback.entity.enums.StatutUtilisateur;
import com.sang.sangschoolback.repository.EtudiantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.partitioningBy;

@Service
public class EtudiantFacade {
    private final EtudiantRepository etudiantRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String DEFAULT_PASSWORD = "123";

    public EtudiantFacade(EtudiantRepository etudiantRepository, PasswordEncoder passwordEncoder) {
        this.etudiantRepository = etudiantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     * @return  Liste touts les Etudiants
     */
    public TableauUtilisateurDto listeEdutiant() {
        List<Etudiant> listeEtudiants = etudiantRepository.findAll();

        Long totalUtilisateurs = (long) listeEtudiants.size();
        Map<Boolean, Long> map = listeEtudiants.stream()
                .collect(partitioningBy((Etudiant etudiant) -> ((StatutUtilisateur.ACTIF).equals(etudiant.getStatut())), counting()));
        long utilisateurActifs = map.get(true);
        long UtilisateurInactif = map.get(false);
        List<UtilisateurDto> etudiants = listeEtudiants.stream()
                .map(UtilisateurDto::new)
                .collect(Collectors.toList());

        return new TableauUtilisateurDto(totalUtilisateurs, utilisateurActifs, UtilisateurInactif, etudiants);
    }

    /***
     * Enregistrer Etudiant
     * @param utilisateurDto
     */
    @Transactional
    public void enregistrerOuModifierEtudiant(UtilisateurDto utilisateurDto) {
        Etudiant etudiant = etudiantRepository.findByUsername(utilisateurDto.getUsername().trim()).orElse(null);
        if (etudiant == null ){
            etudiant = new Etudiant(utilisateurDto.getNom(), utilisateurDto.getPrenoms(), utilisateurDto.getEmail(),
                    utilisateurDto.getUsername(), passwordEncoder.encode(DEFAULT_PASSWORD), Role.valueOf(utilisateurDto.getRole()), StatutUtilisateur.valueOf(utilisateurDto.getStatut()),"Troisième");
        }else {
            etudiant.mettreAJour(utilisateurDto.getNom(), utilisateurDto.getPrenoms(), utilisateurDto.getEmail(),Role.valueOf(utilisateurDto.getRole()), StatutUtilisateur.valueOf(utilisateurDto.getStatut()),"Troisième-update");
        }
        etudiantRepository.save(etudiant);
    }
}
