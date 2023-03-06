package com.stage.projet.service.impl;

import com.stage.projet.dto.VirementDTO;
import com.stage.projet.model.Virement;
import com.stage.projet.repository.VirementRepository;
import com.stage.projet.service.VirementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VirementServiceimpl implements VirementService {

    private VirementRepository virementRepository;

    public VirementServiceimpl(VirementRepository virementRepository) {
        this.virementRepository = virementRepository;
    }

    @Override
    public VirementDTO create(VirementDTO virementDTO) {
        Virement save = virementRepository.save(VirementDTO.toEntity(virementDTO));
        return VirementDTO.toDTO(save);
    }

    @Override
    public List<VirementDTO> findAll() {
        return null;
    }

    @Override
    public VirementDTO findById(Integer id) {
        return null;
    }

    @Override
    public void update(Integer identifiant, VirementDTO virementDTO) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
