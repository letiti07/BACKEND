package com.stage.projet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "virement")
public class Virement extends AbstractEntity{


    private String numeroCompteVirement;


    @OneToOne(mappedBy ="virement")
    private FactureFON factureFON;
}
