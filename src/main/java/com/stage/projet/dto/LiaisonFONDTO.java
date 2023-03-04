package com.stage.projet.dto;

import com.stage.projet.model.AbstractEntity;
import com.stage.projet.model.LiaisonFON;
import com.stage.projet.model.LocationFON;
import com.stage.projet.model.PointConnexion;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@Slf4j
public class LiaisonFONDTO {

	private  int id;

	private String debut;
	

	private String fin;
	

	private Integer nbreFibre;

	private float distance;

	double coutMetreLineaireLiaison;

	private LocationFONDTO locationFONDTO;

	private List<PointConnexionDTO> pointconnexions;


	public static LiaisonFONDTO toDTO(LiaisonFON entity){
		if (entity == null) return null;

		return LiaisonFONDTO.builder()
				.id(entity.getId())
				.debut(entity.getDebut())
				.fin(entity.getFin())
				.nbreFibre(entity.getNbreFibre())
				.distance(entity.getDistance())
				.coutMetreLineaireLiaison(entity.getCoutMetreLineaireLiaison())
				.locationFONDTO(LocationFONDTO.toDTO(entity.getLocationfon()))
				.build();
	}

	public static LiaisonFON toEntity(LiaisonFONDTO dto){
		if (dto == null) return null;
		LiaisonFON liaisonFON=new LiaisonFON();
		liaisonFON.setId(dto.getId());
		liaisonFON.setDebut(dto.getDebut());
		liaisonFON.setFin(dto.getFin());
		liaisonFON.setNbreFibre(dto.getNbreFibre());
		liaisonFON.setDistance(dto.getDistance());
//		liaisonFON.setCoutMetreLineaireLiaison(dto.getCoutMetreLineaireLiaison());
		liaisonFON.setLocationfon(LocationFONDTO.toEntity(dto.getLocationFONDTO()));
		log.info(String.valueOf(liaisonFON));
		return liaisonFON;
	}


}
