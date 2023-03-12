package com.stage.projet.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiaisonFactureDTO {

    private String designation;

    private String periode;

    private String duree;

    private String longueur;

    private BigDecimal prix_unitaire;

    private BigDecimal prix_total;
}
