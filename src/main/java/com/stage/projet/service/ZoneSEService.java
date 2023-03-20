package com.stage.projet.service;

import com.stage.projet.dto.ZoneSEDTO;

import java.util.List;

public interface ZoneSEService {
    void deleteById(Integer id);

    void update(Integer identifiant, ZoneSEDTO zoneSEDTO);

    ZoneSEDTO findById(Integer identifiant);

    List<ZoneSEDTO> findAll();

    ZoneSEDTO create(ZoneSEDTO zoneSEDTO);
}
