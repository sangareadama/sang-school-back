package com.sang.sangschoolback.repository;

import com.sang.sangschoolback.entity.Etudiant;
import com.sang.sangschoolback.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findByUsername(String username);
}
