package com.stage.projet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tva")
public class Tva extends AbstractEntity{

    @Column(name="tva")
    private double tva;

    @Column(name = "actif")
    private boolean actif;


    @OneToMany(mappedBy = "tva")
    @JsonIgnore
    private List<FactureFON> facturefons;
}
