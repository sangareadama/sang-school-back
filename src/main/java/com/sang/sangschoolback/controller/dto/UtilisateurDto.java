package com.sang.sangschoolback.controller.dto;

import com.sang.sangschoolback.entity.Utilisateur;
import com.sang.sangschoolback.entity.enums.Role;
import com.sang.sangschoolback.entity.enums.StatutUtilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



public class UtilisateurDto {
    private String nom;
    private String prenoms;
    private String email;
    private String username;
    private String role;
    private String statut;

    public UtilisateurDto() {
    }
    public UtilisateurDto(Utilisateur utilisateur) {
        this.nom = utilisateur.getNom();
        this.prenoms = utilisateur.getPrenoms();
        this.username = utilisateur.getUsername();
        this.role = utilisateur.getRole().getDesignation();
        this.email = utilisateur.getEmail();
        this.statut = utilisateur.getStatut().getDesignation();
    }

    public UtilisateurDto(String nom, String prenoms, String email, String username, Role role, StatutUtilisateur statut) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.email = email;
        this.username = username;
        this.role = role.getDesignation();
        this.statut = statut.getDesignation();
    }

    public String getNom() {
        return nom;
    }


    public String getEmail() {
        return email;
    }
    public String getPrenoms() {
        return prenoms;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getStatut() {
        return statut;
    }
}
