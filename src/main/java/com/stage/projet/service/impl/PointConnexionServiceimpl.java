package com.stage.projet.service.impl;

import com.stage.projet.dto.PointConnexionDTO;
import com.stage.projet.model.PointConnexion;
import com.stage.projet.repository.PointConnexionRepository;
import com.stage.projet.service.LiaisonFONService;
import com.stage.projet.service.PointConnexionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PointConnexionServiceimpl implements PointConnexionService {

    private PointConnexionRepository pointConnexionRepository;



    public PointConnexionServiceimpl(PointConnexionRepository pointConnexionRepository) {
        this.pointConnexionRepository = pointConnexionRepository;

    }

    @Override
    public PointConnexionDTO create(PointConnexionDTO pointConnexionDTO) {
        PointConnexion save = pointConnexionRepository.save(PointConnexionDTO.toEntity(pointConnexionDTO));
        return PointConnexionDTO.toDTO(save);
    }

    @Override
    public List<PointConnexionDTO> findAll() {
        return  this.pointConnexionRepository.findAll().stream().map(PointConnexionDTO::toDTO).toList();
    }

    @Override
    public PointConnexionDTO findById(Integer id) {
        if (pointConnexionRepository.findById(id).isPresent()) {
            return PointConnexionDTO.toDTO(pointConnexionRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public void update(Integer identifiant, PointConnexionDTO pointConnexionDTO) {
        pointConnexionDTO.setId(identifiant);
        PointConnexion save = pointConnexionRepository.save(PointConnexionDTO.toEntity(pointConnexionDTO));

        pointConnexionRepository.save(save);
    }

    @Override
    public void deleteById(Integer id) {
        this.pointConnexionRepository.deleteById(id);

    }

    public List<PointConnexionDTO> findPointConnexionListByidVille(Integer idVille){
    List<PointConnexion> liste = this.pointConnexionRepository.findPointConnexionListByidVille(idVille);
    List<PointConnexionDTO> listeDTO=new ArrayList<>();
    liste.forEach(
            element->{
             listeDTO.add(PointConnexionDTO.toDTO(element));
            }
    );
    return  listeDTO;
}


}
