package com.sang.sangschoolback.entity.enums;

public enum StatutUtilisateur {
    ACTIF("Actif"), INACTIF("Inactif");
    private final String designation;
    StatutUtilisateur(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }
}
