package com.stage.projet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.time.Instant;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="utilisateur")

public class Utilisateur  extends AbstractEntity{


	@Column(name="matricule")
	private String matricule;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="tel")
	private String tel;
	
	@Column(name="sexe")
	private String sexe;
	
	@Column(name="adresse")
	private String adresse;
	
	@Column(name="email",unique = true)
	private String email;

	@Column(name="dateDeNaissance")
	private Date dateDeNaissance=new Date();

	@Column(name="motdepasse")
	private String motdepasse;

	@Column(name="active")
	private boolean active;


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "utilisateur_role",
			joinColumns = @JoinColumn(name = "utilisateur_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
	private Collection<Role> roles= new ArrayList<>();

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRole(Collection<Role> roles) {
		this.roles = roles;
	}

}
