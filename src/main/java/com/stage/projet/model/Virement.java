package com.stage.projet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "virement")
public class Virement extends AbstractEntity{


    @Column(name="numeroCompteVirement")
    private Long numeroCompteVirement;


    @Column(name = "nomCompteBancaire")
    private String nomCompteBancaire;


    @OneToOne(mappedBy ="virement")
    private FactureFON factureFON;
}
