package com.stage.projet.dto;
import com.stage.projet.model.LocationSE;
import com.stage.projet.model.Ville;
import com.stage.projet.model.ZoneSE;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class ZoneSEDTO {

    private int id;

    private String nom;


    private Integer nbrePoteauxLoues;


  //  private LocationSEDTO locationSEDTO;


    private Date creationDate = new Date();

    private Date lastUpdateDate=new Date();

    public static ZoneSEDTO toDTO(ZoneSE entity){
        if (entity == null) return null;

        return ZoneSEDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .nbrePoteauxLoues(entity.getNbrePoteauxLoues())
             //   .locationSEDTO(LocationSEDTO.toDTO(entity.getLocationse()))
                .creationDate(entity.getCreationDate())
                .lastUpdateDate(entity.getLastUpdateDate())
                .build();
    }

    public static ZoneSE toEntity(ZoneSEDTO dto){
        if (dto == null) return null;
        ZoneSE zoneSE = new ZoneSE();
        zoneSE.setId(dto.getId());
        zoneSE.setNom(dto.getNom());
        zoneSE.setNbrePoteauxLoues(dto.getNbrePoteauxLoues());

        return zoneSE;
    }

}
