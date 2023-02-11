package com.stage.projet.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="liaisonfon")
public class LiaisonFON  extends AbstractEntity{


	@Column(name="debut")
	private String debut;
	
	@Column(name="fin")
	private String fin;
	
	@Column(name="nbrefibre")
	private Integer nbreFibre;
	
	@Column(name="distance")
	private float distance;
	
@ManyToOne
@JoinColumn(name = "idlocationfon")
private LocationFON locationfon;



	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "liaisonfon_pointconnexionfon",
			joinColumns = @JoinColumn(name = "liaisonfon_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="pointconnexion_id",referencedColumnName = "id"))
	private List<PointConnexion> pointconnexions= new ArrayList<>();
}
