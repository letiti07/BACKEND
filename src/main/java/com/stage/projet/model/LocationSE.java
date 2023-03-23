package com.stage.projet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="locationse")
public class LocationSE extends AbstractEntity{

    @Column(name="periodedebut")
    private Date periodeDebut;

    @Column(name="periodedefin")
    private Date periodeFin;

    @Column(name="duree")
    private Integer duree;

    @Column(name="coutmetrelineaire")
    private double coutLocationUnitaire;

    @Column(name="etat")
    private String etat;


    @OneToMany(mappedBy = "locationse")
    private List<ZoneSE> zones;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name="idDemande")
    private Demande demande;


    @OneToMany(mappedBy = "locationse")
    private List<FactureFON> facturefons;


}
