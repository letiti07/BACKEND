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
        List<VirementDTO> virementDTOS = this.virementRepository.findAll().stream().map(VirementDTO::toDTO).toList();

        return virementDTOS;
    }

    @Override
    public VirementDTO findById(Integer id) {
        if(virementRepository.findById(id).isPresent()){
            VirementDTO virementDTO = VirementDTO.toDTO(virementRepository.findById(id).get());
            return virementDTO;
        }
        return null;
    }

    @Override
    public void update(Integer identifiant, VirementDTO virementDTO) {
        virementDTO.setId(identifiant);
        Virement save = virementRepository.save(VirementDTO.toEntity(virementDTO));

        virementRepository.save(save);

    }

    @Override
    public void deleteById(Integer id) {
        this.virementRepository.deleteById(id);
    }
}
