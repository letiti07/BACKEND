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
	
	@OneToMany(mappedBy = "facturefon")
	private List<RecuPaiementFON> recupaiementfon;

	@ManyToOne
	@JoinColumn(name = "idtva")
	private Tva tva;


	@OneToOne
	@JoinColumn(name="idvirement")
	private Virement virement;






}
