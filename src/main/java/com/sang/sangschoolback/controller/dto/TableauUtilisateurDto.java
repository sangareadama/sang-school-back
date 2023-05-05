package com.sang.sangschoolback.controller.dto;

import java.util.List;

public class TableauUtilisateurDto {
    private Long totalUtilisateurs;
    private long utilisateurActifs;
    private long utilisateurInactif;
    private List<UtilisateurDto> utilisateurs;

    public TableauUtilisateurDto(Long totalUtilisateurs, long utilisateurActifs, long utilisateurInactif, List<UtilisateurDto> utilisateurs) {
        this.totalUtilisateurs = totalUtilisateurs;
        this.utilisateurActifs = utilisateurActifs;
        this.utilisateurInactif = utilisateurInactif;
        this.utilisateurs = utilisateurs;
    }

    public TableauUtilisateurDto() {
    }
    public Long getTotalUtilisateurs() {
        return totalUtilisateurs;
    }

    public long getUtilisateurActifs() {
        return utilisateurActifs;
    }

    public long getUtilisateurInactif() {
        return utilisateurInactif;
    }

    public List<UtilisateurDto> getUtilisateurs() {
        return utilisateurs;
    }
}
