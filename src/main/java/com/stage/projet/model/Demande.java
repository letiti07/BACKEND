package com.stage.projet.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="demande")
public class Demande extends AbstractEntity {


	@Column(name = "objet")
	private String objet;
	
	@Column(name = "fichier")
	private String fichier;

	@Column(name = "etat")
    private String etat;

	@Column(name = "commentaire")
	private String commentaire;

	@ManyToOne
	@JoinColumn(name="iddemandeur")
	private Demandeur demandeur;
	
	@OneToOne(mappedBy ="demande")
	private LocationFON locationFON;

	@OneToOne(mappedBy ="demande")
	private LocationSE locationSE;
	
	@ManyToOne
	@JoinColumn(name = "idutilisateur")
	private Utilisateur utilisateur;
	
	
}
