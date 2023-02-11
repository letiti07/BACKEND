package com.stage.projet.service.impl;

import com.stage.projet.dto.DemandeDTO;
import com.stage.projet.dto.DemandeurDTO;
import com.stage.projet.model.Demandeur;
import com.stage.projet.model.Utilisateur;
import com.stage.projet.repository.DemandeurRepository;
import com.stage.projet.service.DemandeurService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemandeurServiceimpl implements DemandeurService {

    private DemandeurRepository demandeurRepository;

    public DemandeurServiceimpl(DemandeurRepository demandeurRepository) {
        this.demandeurRepository = demandeurRepository;
    }

    @Override
    public Integer create(DemandeurDTO demandeurDTO) {
        return demandeurRepository.save(DemandeurDTO.toEntity(demandeurDTO)).getId();
    }

    @Override
    public List<DemandeurDTO> findAll() {
       return this.demandeurRepository.findAll().stream().map(DemandeurDTO::toDTO).toList();

    }

    @Override
    public DemandeurDTO findById(Integer id) {
        if (demandeurRepository.findById(id).isPresent()) {
            return DemandeurDTO.toDTO(demandeurRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public void deleteById(Integer identifiant) {
        this.demandeurRepository.deleteById(identifiant);
    }

    @Override
    public void update(Integer identifiant, DemandeurDTO demandeurDTO) {
        demandeurDTO.setId(identifiant);
        demandeurRepository.save(DemandeurDTO.toEntity(demandeurDTO));
    }
}
