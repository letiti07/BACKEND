package com.stage.projet.service;

import com.stage.projet.dto.ChecqueDTO;
import com.stage.projet.dto.VirementDTO;

import java.util.List;

public interface ChecqueService {

    ChecqueDTO create(ChecqueDTO checqueDTO);

    List<ChecqueDTO> findAll();

    ChecqueDTO findById(Integer id);

    void update(Integer identifiant, ChecqueDTO checqueDTO);

    void deleteById(Integer id);

}
