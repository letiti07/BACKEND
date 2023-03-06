package com.stage.projet.dto;

import com.stage.projet.model.Checque;
import com.stage.projet.model.Demande;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Builder
@Slf4j
public class ChecqueDTO {

    private int id;

    private  Long numeroChecque;

    private Date creationDate = new Date();

    private Date lastUpdateDate=new Date();

    public static ChecqueDTO toDTO(Checque entity){
        if (entity == null) return null;

        return ChecqueDTO.builder()
                .id(entity.getId())
                .numeroChecque(entity.getNumeroChecque())
                .creationDate(entity.getCreationDate())
                .lastUpdateDate(entity.getLastUpdateDate())
                .build();
    }

    public static Checque toEntity(ChecqueDTO dto){
        if (dto == null) return null;
        Checque checque = new Checque();
        checque.setId(dto.getId());
        checque.setNumeroChecque(dto.getNumeroChecque());

        return checque;
    }


}
