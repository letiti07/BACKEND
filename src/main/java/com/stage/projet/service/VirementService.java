package com.stage.projet.service;

import com.stage.projet.dto.VilleDTO;
import com.stage.projet.dto.VirementDTO;

import java.util.List;

public interface VirementService {

        VirementDTO create(VirementDTO virementDTO);

        List<VirementDTO> findAll();

        VirementDTO findById(Integer id);

        void update(Integer identifiant, VirementDTO virementDTO);

        void deleteById(Integer id);

}
