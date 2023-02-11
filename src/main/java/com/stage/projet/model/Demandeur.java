package com.stage.projet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="demandeur")
public class Demandeur extends AbstractEntity {

	

	@Column(name="nom")
	private String nom;
	
	@Column(name = "rccm")
	private String rccm;
	
	@Column(name = "ifu")
	private String ifu;
	
	@Column(name = "email")
	private String email;
	
	@Column(name= "boitePostale")
	private String boitePostale;

	@Column(name = "compte")
	private String virementCompte;

	@Column(name = "tel")
	private String tel;

	//depositaire
	@Column(name = "nomDepositaire")
	private String nomDepositaire;

	@Column(name = "prenomDepositaire")
	private String prenomDepositaire;

	@Column(name = "telDepositaire")
	private String telDepositaire;

	@Column(name = "cnibDepositaire")
	private String cnibDepositaire;
	//fin depositaire


	@OneToMany(mappedBy = "demandeur")
	private List<Demande> demande;


	
}
