package com.stage.projet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="role")
public class Role  extends AbstractEntity {


	
	@Column(name = "nom")
	private String nom;


	@ManyToMany(mappedBy="roles")
	@JsonIgnore
	private Collection<Utilisateur> utilisateurs;

	public Collection<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}


	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "role_permission",
			joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="permission_id",referencedColumnName = "id"))
	private Collection<Permission> permissions;

	public Collection<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}



}
