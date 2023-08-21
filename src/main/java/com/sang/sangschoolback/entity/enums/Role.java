package com.sang.sangschoolback.entity.enums;

public enum Role {
    ADMIN("Administrateur"),
    SUPER_ADMIN ("Super administrateur"),
    UTILISATEUR("Utilisateur"),
    ETUDIANT("Etudiant");

    private final String designation;

    Role(String designation) {

        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }
}
