package com.stage.projet.dto;

import com.stage.projet.model.FactureFON;
import com.stage.projet.model.LiaisonFON;
import com.stage.projet.model.Tva;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
@Data
@Builder
public class TvaDTO {

    private  int id;

    private boolean actif;

    private double tva;

    private Date creationDate = new Date();

    private Date lastUpdateDate=new Date();

    public  static TvaDTO toDTO(Tva entity){
        if(entity==null) return null;

            return TvaDTO.builder()
                    .id(entity.getId())
                    .actif(entity.isActif())
                    .tva(entity.getTva())
                    .creationDate(entity.getCreationDate())
                    .lastUpdateDate(entity.getLastUpdateDate())
                    .build();
    }

    public  static Tva toEntity(TvaDTO dto){
        if(dto==null) return null;

        Tva tva=new Tva();
        tva.setId(dto.getId());
        tva.setActif(dto.isActif());
        tva.setTva(dto.getTva());
        return tva;
    }


}
