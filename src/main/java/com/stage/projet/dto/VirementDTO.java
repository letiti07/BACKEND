package com.stage.projet.dto;

import com.stage.projet.model.Virement;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Builder
@Slf4j
public class VirementDTO {

    private Integer id;

    private String numeroVirement;

    private Date creationDate = new Date();

    private Date lastUpdateDate=new Date();

    public static VirementDTO toDTO(Virement entity){
        if (entity == null) return null;

        return VirementDTO.builder()
                .id(entity.getId())
                .numeroVirement(entity.getNumeroVirement())
                .creationDate(entity.getCreationDate())
                .lastUpdateDate(entity.getLastUpdateDate())
                .build();
    }

    public static Virement toEntity(VirementDTO dto){
        if (dto == null) return null;
        Virement virement = new Virement();
        virement.setId(dto.getId());
        virement.setNumeroVirement(dto.getNumeroVirement());

        return virement;
    }




}
