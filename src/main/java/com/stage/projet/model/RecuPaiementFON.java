package com.stage.projet.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name= "recuPaiementfon.jrxml")
public class RecuPaiementFON  extends AbstractEntity{



	@ManyToOne
	@JoinColumn(name = "idfacturefon")
	private FactureFON facturefon;
}
