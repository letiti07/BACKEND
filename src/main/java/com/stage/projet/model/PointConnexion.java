package com.stage.projet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="pointconnexion")
public class PointConnexion  extends AbstractEntity{


	@Column(name="nom")
	private String nom;

	@Column(name="typehebergement")
	private String typeHebergement;

	@Column(name="fraishebergement")
	private double fraisHebergement;

	@Column(name="disponible")
	private boolean disponible;


	@ManyToMany(mappedBy="pointconnexions")
	private Collection<LiaisonFON> liaisonFONS;

	@ManyToOne
	@JoinColumn(name = "idville")
	private Ville ville;

	
}
