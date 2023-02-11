package com.stage.projet.dto;

import com.stage.projet.model.AbstractEntity;
import com.stage.projet.model.Demande;
import com.stage.projet.model.Demandeur;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


@Data
@Builder
public class DemandeurDTO  {

	
	private int id;

	private String nom;
	

	private String rccm;
	

	private String ifu;
	

	private String email;
	

	private String boitePostale;

	private String virementCompte;

	private String tel;

	private String nomDepositaire;

	private String prenomDepositaire;

	private String telDepositaire;

	private String cnibDepositaire;


	private List<DemandeDTO> demandeDTO;

	public static DemandeurDTO toDTO(Demandeur entity){
		if (entity == null) return null;

		return DemandeurDTO.builder()
				.id(entity.getId())
				.nom(entity.getNom())
				.ifu(entity.getIfu())
				.rccm(entity.getRccm())
				.email(entity.getEmail())
				.boitePostale(entity.getBoitePostale())
				.virementCompte(entity.getVirementCompte())
				.tel(entity.getTel())
				.nomDepositaire(entity.getNomDepositaire())
				.prenomDepositaire(entity.getPrenomDepositaire())
				.telDepositaire(entity.getTelDepositaire())
				.cnibDepositaire(entity.getCnibDepositaire())
				.build();
	}

	public static Demandeur toEntity(DemandeurDTO dto){
		if (dto == null) return null;
		Demandeur demandeur = new Demandeur();
		demandeur.setId(dto.getId());
		demandeur.setNom(dto.getNom());
		demandeur.setIfu(dto.getIfu());
		demandeur.setRccm(dto.getRccm());
		demandeur.setEmail(dto.getEmail());
		demandeur.setBoitePostale(dto.getBoitePostale());
		demandeur.setVirementCompte(dto.getVirementCompte());
		demandeur.setTel(dto.getTel());
		demandeur.setNomDepositaire(dto.getNomDepositaire());
		demandeur.setPrenomDepositaire(dto.getPrenomDepositaire());
		demandeur.setTelDepositaire(dto.getTelDepositaire());
		demandeur.setCnibDepositaire(dto.getCnibDepositaire());
		return demandeur;
	}

	
}
