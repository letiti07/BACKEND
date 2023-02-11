package com.stage.projet.service;

import com.stage.projet.dto.TvaDTO;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface TvaService {
    Integer create(TvaDTO tvaDTO);

    List<TvaDTO> findAll();

    TvaDTO findById(Integer id);

    void update(Integer identifiant, TvaDTO tvaDTO);

    void deleteById(Integer id);

    AtomicReference<TvaDTO> getTvaActif();
}
