package com.sang.sangschoolback.entity;


import com.sang.sangschoolback.entity.enums.Role;
import com.sang.sangschoolback.entity.enums.StatutUtilisateur;
import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import static com.sang.sangschoolback.entity.Personnel.TABLE_NAME;
import static org.apache.logging.log4j.util.Strings.isBlank;


@Entity
@Table(name = TABLE_NAME )
public class Personnel extends Utilisateur {

	public static final String TABLE_NAME = "personnel";
	public static final String TABLE_ID = TABLE_NAME + "_id";
	public static final String TABLE_SEQ = TABLE_ID + "_seq";

	@Id
	@SequenceGenerator(name ="personnel_seq", sequenceName = "personnel_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personnel_seq")
	private Long id;
	@Column(name = "age", nullable = false, unique = true)
	protected int age;

	public Personnel(){

	}
	public Personnel(String nom, String prenoms, String email, String username, String password, Role role, StatutUtilisateur statut, int age) {
		super(nom,  prenoms,  email, username,  password,  role,  statut);
		this.age = age;
	}

	public void mettreAJour(String nom, String prenoms, String email, Role role, StatutUtilisateur statut, int age) {
		super.update(nom,  prenoms,  email,  role,  statut);
		if (!isBlank(String.valueOf(age))) {
			this.age = age;
		}
	}

	public Long getId() {
		return id;
	}

	public int getAge() {
		return age;
	}
}
