package com.stage.projet.service;

import com.stage.projet.dto.LiaisonFONDTO;
import com.stage.projet.dto.PointConnexionDTO;
import com.stage.projet.model.LiaisonFON;

import java.util.Collection;
import java.util.List;

public interface LiaisonFONService {
    LiaisonFONDTO create(LiaisonFONDTO liaisonFONDTO);

    List<LiaisonFONDTO> findAll();

    LiaisonFONDTO findById(Integer id);

    void update(Integer identifiant, LiaisonFONDTO liaisonFONDTO);

    void deleteById(Integer id);

    List<LiaisonFONDTO> findLiaisonsOfLocation(Integer identifiant);


    void addPointConnexion(Integer idLiaison, Integer idPointConnexion);


    List<PointConnexionDTO> getPointsConnexion(Integer idLiaison);

    void removePointConnexion(Integer idLiaison, Integer idPointConnexion);
}
