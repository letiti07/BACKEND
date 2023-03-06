package com.stage.projet.service.impl;

import com.stage.projet.dto.ChecqueDTO;
import com.stage.projet.dto.VirementDTO;
import com.stage.projet.model.Checque;
import com.stage.projet.model.Virement;
import com.stage.projet.repository.ChecqueRepository;
import com.stage.projet.service.ChecqueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChecqueServiceimpl implements ChecqueService {

    private ChecqueRepository checqueRepository;

    public ChecqueServiceimpl(ChecqueRepository checqueRepository) {
        this.checqueRepository = checqueRepository;
    }


    @Override
    public ChecqueDTO create(ChecqueDTO checqueDTO) {
        Checque save = checqueRepository.save(ChecqueDTO.toEntity(checqueDTO));
        return ChecqueDTO.toDTO(save);
    }

    @Override
    public List<ChecqueDTO> findAll() {
        List<ChecqueDTO> checqueDTOS = this.checqueRepository.findAll().stream().map(ChecqueDTO::toDTO).toList();

        return checqueDTOS;
    }

    @Override
    public ChecqueDTO findById(Integer id) {
        if(checqueRepository.findById(id).isPresent()){
            ChecqueDTO checqueDTO = ChecqueDTO.toDTO(checqueRepository.findById(id).get());
            return checqueDTO;
        }
        return null;
    }

    @Override
    public void update(Integer identifiant, ChecqueDTO checqueDTO) {
        checqueDTO.setId(identifiant);
        Checque save = checqueRepository.save(ChecqueDTO.toEntity(checqueDTO));

        checqueRepository.save(save);
    }

    @Override
    public void deleteById(Integer id) {
        this.checqueRepository.deleteById(id);
    }
}
