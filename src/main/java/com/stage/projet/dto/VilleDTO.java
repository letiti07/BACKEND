package com.stage.projet.dto;
import com.stage.projet.model.PointConnexion;
import com.stage.projet.model.Ville;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class VilleDTO {

    private int id;

    private String nom;

    private List<PointConnexionDTO> pointConnexions;

    private Date creationDate = new Date();

    private Date lastUpdateDate=new Date();

    public static VilleDTO toDTO(Ville entity){
        if (entity == null) return null;

        return VilleDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .creationDate(entity.getCreationDate())
                .lastUpdateDate(entity.getLastUpdateDate())
                .build();
    }

    public static Ville toEntity(VilleDTO dto){
        if (dto == null) return null;
        Ville ville=new Ville();
        ville.setId(dto.getId());
        ville.setNom(dto.getNom());
        return ville;
    }

}
