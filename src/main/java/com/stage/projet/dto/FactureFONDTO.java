package com.stage.projet.dto;

import com.stage.projet.model.*;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class FactureFONDTO {

    private int id;

    private String code;


    private Date debutPeriode;


    private Date finPeriode;


    private String etat;

    private TvaDTO tvaDTO;

    private Integer duree;

    private Date creationDate = new Date();

    private Date lastUpdateDate=new Date();

    private LocationFONDTO locationFONDTO;


    public  static  FactureFONDTO toDTO(FactureFON entity){
        if(entity==null) return null;

        return FactureFONDTO.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .debutPeriode(entity.getDebutPeriode())
                .finPeriode(entity.getFinPeriode())
                .etat(entity.getEtat())
                .tvaDTO(TvaDTO.toDTO(entity.getTva()))
                .duree(entity.getDuree())
                .creationDate(entity.getCreationDate())
                .lastUpdateDate(entity.getLastUpdateDate())
                .build();

    }

    public static FactureFON toEntity(FactureFONDTO dto) {
        if(dto==null) return null;

       FactureFON factureFON=new FactureFON();
       factureFON.setId(dto.getId());
       factureFON.setCode(dto.getCode());
       factureFON.setDebutPeriode(dto.getDebutPeriode());
       factureFON.setFinPeriode(dto.getFinPeriode());
       factureFON.setEtat(dto.getEtat());
       factureFON.setTva(TvaDTO.toEntity(dto.getTvaDTO()));
       factureFON.setDuree(dto.getDuree());
       factureFON.setLocationfon(LocationFONDTO.toEntity(dto.getLocationFONDTO()));
        return factureFON;

    }


}
