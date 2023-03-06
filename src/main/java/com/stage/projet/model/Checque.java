package com.stage.projet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "checque")
public class Checque extends AbstractEntity{

    private String NumeroChecque;

    @OneToOne(mappedBy ="checque")
    private FactureFON factureFON;
}
