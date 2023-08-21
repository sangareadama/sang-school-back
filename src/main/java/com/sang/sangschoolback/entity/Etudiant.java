package com.sang.sangschoolback.entity;

import com.sang.sangschoolback.entity.enums.Role;
import com.sang.sangschoolback.entity.enums.StatutUtilisateur;
import jakarta.persistence.*;

import static com.sang.sangschoolback.entity.Personnel.TABLE_NAME;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Entity
@Table(name = TABLE_NAME )
public class Etudiant extends  Utilisateur {
    public static final String TABLE_NAME = "etudiant";
    public static final String TABLE_ID = TABLE_NAME + "_id";
    public static final String TABLE_SEQ = TABLE_ID + "_seq";

    @Id
    @SequenceGenerator(name =TABLE_SEQ, sequenceName = TABLE_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_SEQ)
    private Long id;
    @Column(name = "classe", nullable = false, unique = true)
    protected String classe;


    public Etudiant(String nom, String prenoms, String email, String username, String password, Role role, StatutUtilisateur statut, String classe) {
        super(nom,  prenoms,  email, username,  password,  role,  statut);
        this.classe = classe;
    }

    public void mettreAJour(String nom, String prenoms, String email, Role role, StatutUtilisateur statut, String classe) {
        super.update(nom,  prenoms,  email,  role,  statut);
        if (!isBlank(String.valueOf(classe))) {
            this.classe = classe;
        }
    }

    public Long getId() {
        return id;
    }

    public String getClasse() {
        return classe;
    }
}
