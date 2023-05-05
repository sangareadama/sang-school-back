package com.sang.sangschoolback.repository;

import com.sang.sangschoolback.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
     Optional<Utilisateur> findByUsername(String username);
     
}
