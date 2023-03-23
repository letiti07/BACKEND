package com.stage.projet.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="facturefon")

public class FactureFON  extends AbstractEntity{
	

	@Column(name="code")
	private String code;

	@Column(name = "debutPeriode")
	private Date debutPeriode;

	@Column(name = "finPeriode")
	private Date finPeriode;

	@Column(name = "duree")
	private Integer duree;
	
	@Column(name = "etat")
	private String etat;

	@ManyToOne
	@JoinColumn(name = "idlocationfon")
	private LocationFON locationfon;

	@ManyToOne
	@JoinColumn(name = "idlocationse")
	private LocationSE locationse;
	
	@OneToMany(mappedBy = "facturefon")
	private List<RecuPaiementFON> recupaiementfon;

	@ManyToOne
	@JoinColumn(name = "idtva")
	private Tva tva;

	@Column(name="commentaires")
	private String commentaires;

	@OneToOne
	@JoinColumn(name="idvirement")
	private Virement virement;

	@OneToOne
	@JoinColumn(name="idcheque")
	private Checque checque;






}
