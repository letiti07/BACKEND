package com.stage.projet.service;

import com.stage.projet.dto.LocationSEDTO;

import java.util.List;

public interface LocationSEService {
    LocationSEDTO create(LocationSEDTO locationSEDTO);

    List<LocationSEDTO> findAll();

    LocationSEDTO findById(Integer id);

    void update(Integer identifiant, LocationSEDTO locationSEDTO);

    void deleteById(Integer id);
}
