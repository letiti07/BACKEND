package com.stage.projet.dto;

import com.stage.projet.model.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Slf4j

public class DemandeDTO {

	private int id;

	private String objet;

	private String fichier;


	private String etat;

	private String commentaire;

	private DemandeurDTO demandeurDTO;

	private LocationFONDTO locationFONDTO;

	private LocationSEDTO locationSEDTO;

	private Utilisateur utilisateur;

	private Date creationDate = new Date();

	private Date lastUpdateDate=new Date();

	public static DemandeDTO toDTO(Demande entity){
		if (entity == null) return null;

		return DemandeDTO.builder()
				.id(entity.getId())
				.objet(entity.getObjet())
				.fichier(entity.getFichier())
				.etat(entity.getEtat())
				.fichier(entity.getFichier())
				.commentaire(entity.getCommentaire())
				.demandeurDTO(DemandeurDTO.toDTO(entity.getDemandeur()))
				.locationFONDTO(LocationFONDTO.toDTO(entity.getLocationFON()))
				.locationSEDTO(LocationSEDTO.toDTO(entity.getLocationSE()))
				.creationDate(entity.getCreationDate())
				.lastUpdateDate(entity.getLastUpdateDate())
				.build();
	}

	public static Demande toEntity(DemandeDTO dto){
		if (dto == null) return null;
		Demande demande = new Demande();
		demande.setId(dto.getId());
		demande.setObjet(dto.getObjet());
		demande.setFichier(dto.getFichier());
		demande.setEtat(dto.getEtat());
		demande.setCommentaire(dto.getCommentaire());
		demande.setFichier(dto.getFichier());
		demande.setDemandeur(DemandeurDTO.toEntity(dto.getDemandeurDTO()));
		//demande.setLocationFON(LocationFONDTO.toEntity(dto.getLocationFONDTO()));

		return demande;
	}

	
	
}
