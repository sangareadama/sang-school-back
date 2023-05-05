package com.sang.sangschoolback.entity;

import com.sang.sangschoolback.entity.enums.Role;
import com.sang.sangschoolback.entity.enums.StatutUtilisateur;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static com.sang.sangschoolback.entity.Utilisateur.TABLE_NAME;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Entity
@Table(name = TABLE_NAME )
public class Utilisateur implements UserDetails {

    public static final String TABLE_NAME = "utilisateur";
    public static final String TABLE_ID = TABLE_NAME + "_id";
    public static final String TABLE_SEQ = TABLE_ID + "_seq";

    @Id
    @SequenceGenerator(name = TABLE_SEQ, sequenceName = TABLE_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_SEQ)
    private Long id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenoms")
    private String prenoms;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutUtilisateur statut;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenoms, String email,String username, String password, Role role, StatutUtilisateur statut) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.statut = statut;
    }

    public void update(String nom, String prenoms, String email, Role role, StatutUtilisateur statut){
        if (!isBlank(nom)) {
            this.nom = nom.toLowerCase().trim();
        }
        if (!isBlank(email)) {
            this.email = email.toLowerCase().trim();
        }
        if (!isBlank(prenoms)) {
            this.prenoms = prenoms.toLowerCase().trim();
        }

        if (statut != null) {
            this.statut = statut;
        }

        if (role != null) {
            this.role = role;
        }
    }

    public void updatePassWord(String password) {
        this.password = password;
    }

    //generate by userDatails
    @Override
    public String getPassword() {
        return password;
    }
    //generate by userDatails
    @Override
    public String getUsername() {
        return username;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenoms() {
        return prenoms;
    }
    public Role getRole() {
        return role;
    }
    public String getEmail() {
        return email;
    }
    public StatutUtilisateur getStatut() {
        return statut;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
