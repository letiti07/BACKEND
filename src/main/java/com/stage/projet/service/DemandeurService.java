package com.stage.projet.service;

import com.stage.projet.dto.DemandeurDTO;
import com.stage.projet.model.Demandeur;

import java.util.List;

public interface DemandeurService {
    Integer create(DemandeurDTO demandeurDTO);

    List<DemandeurDTO> findAll();

    DemandeurDTO findById(Integer id);

    void deleteById(Integer id);

    void update(Integer identifiant, DemandeurDTO demandeurDTO);
}
