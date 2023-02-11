package com.stage.projet.model;

import lombok.*;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="ville")
public class Ville extends AbstractEntity {

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "ville")
    private List<PointConnexion> pointConnexions;

}
