package com.stage.projet.dto;

import com.stage.projet.model.*;
import lombok.*;
import org.apache.catalina.mapper.Mapper;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@Builder
public class PointConnexionDTO  {

	private int id;

	private String nom;

	private String typeHebergement;

	private double fraisHebergement;

	private boolean disponible;

	private Date creationDate = new Date();

	private Date lastUpdateDate=new Date();

	private Collection<LiaisonFONDTO> liaisonFONDTOS;

	private VilleDTO villeDTO;


	public static PointConnexionDTO toDTO(PointConnexion entity){
		if (entity == null) return null;

		return PointConnexionDTO.builder()
				.id(entity.getId())
				.nom(entity.getNom())
				.typeHebergement(entity.getTypeHebergement())
				.fraisHebergement(entity.getFraisHebergement())
				.disponible(entity.isDisponible())
				.villeDTO(VilleDTO.toDTO(entity.getVille()))
				.creationDate(entity.getCreationDate())
				.lastUpdateDate(entity.getLastUpdateDate())
				.build();
	}

	public static PointConnexion toEntity(PointConnexionDTO dto){
		if (dto == null) return null;
	PointConnexion pointConnexion=new PointConnexion();
	pointConnexion.setId(dto.getId());
	pointConnexion.setNom(dto.getNom());
	pointConnexion.setTypeHebergement(dto.getTypeHebergement());
	pointConnexion.setFraisHebergement(dto.getFraisHebergement());
	pointConnexion.setDisponible(dto.isDisponible());
	pointConnexion.setVille(VilleDTO.toEntity(dto.getVilleDTO()));

		return pointConnexion;
	}





}
