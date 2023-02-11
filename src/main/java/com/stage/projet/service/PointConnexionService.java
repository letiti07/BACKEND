package com.stage.projet.service;

import com.stage.projet.dto.LiaisonFONDTO;
import com.stage.projet.dto.PointConnexionDTO;

import java.util.List;

public interface PointConnexionService {
    PointConnexionDTO create(PointConnexionDTO pointConnexionDTO);

    List<PointConnexionDTO> findAll();

    PointConnexionDTO findById(Integer id);

    void update(Integer identifiant, PointConnexionDTO pointConnexionDTO);

    void deleteById(Integer id);

    public List<PointConnexionDTO> findPointConnexionListByidVille(Integer idVille);

}
