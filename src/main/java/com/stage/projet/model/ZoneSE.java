package com.stage.projet.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="zonese")
public class ZoneSE extends AbstractEntity{

    @Column(name="nom")
    private String nom;

    @Column(name="nbrePoteauxLoues")
    private Integer nbrePoteauxLoues;

    @ManyToOne
    @JoinColumn(name = "idlocationse")
    private LocationSE locationse;


}
