package com.stage.projet.dto;

import com.stage.projet.dto.DemandeDTO;
import com.stage.projet.dto.DemandeurDTO;
import com.stage.projet.model.LiaisonFON;
import com.stage.projet.model.LocationFON;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class LocationFONDTO {

    private int id;

    private Date periodeDebut;


    private Date periodeFin;


    private Integer duree;


    private double coutMetreLineaire;


    private double fraisConnexion;


    private String etat;


    private List<LiaisonFONDTO> liaisonfons;

    private List<FactureFONDTO> facturefons;

    private DemandeDTO demandeDTO;






    public static LocationFONDTO toDTO(LocationFON entity){
        if (entity == null) return null;

        return LocationFONDTO.builder()
                .id(entity.getId())
                .periodeDebut(entity.getPeriodeDebut())
                .periodeFin(entity.getPeriodeFin())
                .duree(entity.getDuree())
                .coutMetreLineaire(entity.getCoutMetreLineaire())
                .fraisConnexion(entity.getFraisConnexion())
                .etat(entity.getEtat())
              //  .liaisonFONDTO(entity.getLiaisonfon().stream().map(LiaisonFONDTO::toDTO).toList())
                //.demandeDTO(DemandeDTO.toDTO(entity.getDemande()))
                .build();
    }

    public static LocationFON toEntity(LocationFONDTO dto){
        if (dto == null) return null;
        LocationFON locationFON=new LocationFON();
        locationFON.setId(locationFON.getId());
        locationFON.setPeriodeDebut(dto.getPeriodeDebut());
        locationFON.setPeriodeFin(dto.getPeriodeFin());
        locationFON.setDuree(dto.getDuree());
        locationFON.setCoutMetreLineaire(dto.getCoutMetreLineaire());
        locationFON.setFraisConnexion(dto.getFraisConnexion());
        locationFON.setEtat(dto.getEtat());
        locationFON.setDemande(DemandeDTO.toEntity(dto.getDemandeDTO()));
        //locationFON.setLiaisonfon(dto.getLiaisonFONDTO().stream().map(LiaisonFONDTO::toEntity).toList());
        return locationFON;
    }


}
