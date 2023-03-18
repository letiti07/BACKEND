package com.stage.projet.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteDTO {

    private String designation;

    private String periode;

    private String duree;

    private String site;

    private String type;

    private int prix_total;
}
