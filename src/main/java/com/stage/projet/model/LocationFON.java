package com.stage.projet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="locationfon")
public class LocationFON extends AbstractEntity{



	@Column(name="periodedebut")
	private Date periodeDebut;
	
	@Column(name="periodedefin")
	private Date periodeFin;
	
	@Column(name="duree")
	private Integer duree;
	
	@Column(name="coutmetrelineaire")
	private double coutMetreLineaire;
	
	@Column(name="fraisconnexion")
	private double fraisConnexion;
	
	@Column(name="etat")
	private String etat;


	@OneToOne
	@JsonIgnore
	@JoinColumn(name="idDemande")
	private Demande demande;
	
	@OneToMany(mappedBy = "locationfon")
	private List<LiaisonFON> liaisonfons;
	
	@OneToMany(mappedBy = "locationfon")
	private List<FactureFON> facturefons;
	
	
	
}
