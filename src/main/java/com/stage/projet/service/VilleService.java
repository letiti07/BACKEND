package com.stage.projet.service;

import com.stage.projet.dto.VilleDTO;

import java.util.List;

public interface VilleService {
    VilleDTO create(VilleDTO villeDTO);

    List<VilleDTO> findAll();

    VilleDTO findById(Integer id);

    void update(Integer identifiant, VilleDTO villeDTO);

    void deleteById(Integer id);
}
