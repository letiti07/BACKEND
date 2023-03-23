package com.stage.projet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.projet.model.Demande;
import com.stage.projet.model.LocationFON;
import com.stage.projet.model.LocationSE;
import com.stage.projet.model.ZoneSE;
import com.stage.projet.service.LocationFONService;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class LocationSEDTO {

    private Integer id;


    private Date periodeDebut;


    private Date periodeFin;


    private Integer duree;


    private double coutLocationUnitaire;


    private String etat;


    private List<ZoneSEDTO> zones;

    private List<FactureFONDTO> facturefons;


    private DemandeDTO demandeDTO;


    private Date creationDate = new Date();

    private Date lastUpdateDate=new Date();


    public static LocationSEDTO toDTO(LocationSE entity){
        if (entity == null) return null;

        return LocationSEDTO.builder()
                .id(entity.getId())
                .periodeDebut(entity.getPeriodeDebut())
                .periodeFin(entity.getPeriodeFin())
                .duree(entity.getDuree())
                .coutLocationUnitaire(entity.getCoutLocationUnitaire())
                .etat(entity.getEtat())
                .creationDate(entity.getCreationDate())
                .lastUpdateDate(entity.getLastUpdateDate())
                .build();
    }

    public static LocationSE toEntity(LocationSEDTO dto){
        if (dto == null) return null;
        LocationSE locationSE=new LocationSE();
        locationSE.setId(locationSE.getId());
        locationSE.setPeriodeDebut(dto.getPeriodeDebut());
        locationSE.setPeriodeFin(dto.getPeriodeFin());
        locationSE.setDuree(dto.getDuree());
        locationSE.setCoutLocationUnitaire(dto.getCoutLocationUnitaire());
        locationSE.setEtat(dto.getEtat());
        locationSE.setDemande(DemandeDTO.toEntity(dto.getDemandeDTO()));

        return locationSE;
    }

}
