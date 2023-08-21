package com.sang.sangschoolback.service;

import com.sang.sangschoolback.controller.dto.AuthDto;
import com.sang.sangschoolback.controller.dto.TokenDto;
import com.sang.sangschoolback.entity.Personnel;
import com.sang.sangschoolback.repository.PersonnelRepository;
import com.sang.sangschoolback.security.JwtTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

import static java.util.List.of;

@Service
public class SecurityService  implements UserDetailsService {

    @Autowired
    private  JwtTokenUtils jwtTokenUtils;
    @Autowired
    private  PersonnelRepository utilisateurRepository;



    /**
     * Génère le Token JWT de l'utilisateur.
     *
     * @param utilisateur l'utilisateur.
     * @return le Token JWT de l'utilisateur.
     */
    private String genererTokenJwt(Personnel utilisateur) {


        return jwtTokenUtils.generateToken(utilisateur);
    }

    /**
     * Récupère un utilisateur à partir de son username.
     *
     * @param username le username de l'utilisateur.
     * @return L'utilisateur.
     * @throws UsernameNotFoundException Exception levé lorsqu'aucun utilisateur ne correspond à ce username.
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Personnel> utilisateurOptional = utilisateurRepository.findByUsername(username);
        if (utilisateurOptional.isPresent()) {
            return utilisateurOptional.get().buildUser();
        }
        else {
            throw new UsernameNotFoundException("Aucun utilisateur trouvé pour le username: " + username);
        }
    }

    /**
     * Récupère un utilisateur à partir de son username et de son password.
     *
     * @param username le username de l'utilisateur.
     * @param password le password de l'utilisateur.
     * @return L'utilisateur.
     * @throws UsernameNotFoundException Exception levé lorsqu'aucun utilisateur ne correspond à ce username.
     */
    public Personnel rechercherUtilisateurParUsernameEtPassword(String username, String password) throws UsernameNotFoundException {
        Optional<Personnel> utilisateurOptional = utilisateurRepository.findByUsername(username);
        if (utilisateurOptional.isPresent()) {
            Personnel utilisateur = utilisateurOptional.get();
            if (SecurityService.comparerPassword(password, utilisateur.getPassword())) {
                return utilisateur;
            }
        }

        throw new UsernameNotFoundException("Aucun utilisateur trouvé pour le username: " + username);
    }

    /**
     * Authentifie l'utilisateur.
     *
     * @param authDto l'utilisateur.
     * @return le Tokent JWT de l'utilisateur authentifié.
     */
    public TokenDto autentifierUtilisateur(AuthDto authDto) {
        Personnel utilisateur = rechercherUtilisateurParUsernameEtPassword(authDto.getUsername(), authDto.getPassword());
        SecurityContextHolder.clearContext();
        String token = genererTokenJwt(utilisateur);

        return new TokenDto(StringUtils.join(of("Bearer", token), " "));
    }

    /**
     * Compare les mots de passe.
     *
     * @param password le mot de passe.
     * @param encodePassword le mot de passe crypté.
     * @return le mot de passe crypté.
     */
    public static boolean comparerPassword(String password, String encodePassword) {
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.matches(password, encodePassword);
    }

    /**
     * Crypte le mot de passe de l'utilisateur.
     *
     * @param password le mot de passe.
     * @return le mot de passe crypté.
     */
    public static String crypterPassword(String password) {
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.encode(password);
    }

}
