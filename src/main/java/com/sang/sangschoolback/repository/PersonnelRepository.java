package com.sang.sangschoolback.repository;

import com.sang.sangschoolback.entity.Personnel;
import com.sang.sangschoolback.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Optional<Personnel> findByUsername(String username);
}
