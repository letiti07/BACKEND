package com.stage.projet.service;

import com.stage.projet.dto.DemandeDTO;
import com.stage.projet.dto.LocationSEDTO;

import java.util.List;

public interface LocationSEService {
    LocationSEDTO create(LocationSEDTO locationSEDTO);

    List<LocationSEDTO> findAll();

    LocationSEDTO findById(Integer id);

    void update(Integer identifiant, LocationSEDTO locationSEDTO);

    void deleteById(Integer id);

    void ValidateEnInstanceLocationSe(Integer id);

    void ValidateValidéLocationSe(Integer id);

    void ValidateNonValidéLocationSe(Integer id);

    double getCoutTotalLocationHTVA(Integer IdLocation);

    DemandeDTO DemandeByIdLocationse(Integer idLocation);
}
