package com.stage.projet.service.impl;

import com.stage.projet.dto.PointConnexionDTO;
import com.stage.projet.dto.VilleDTO;
import com.stage.projet.model.PointConnexion;
import com.stage.projet.model.Ville;
import com.stage.projet.repository.VilleRepository;
import com.stage.projet.repository.PointConnexionRepository;
import com.stage.projet.service.VilleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class VilleServiceimpl implements VilleService {

    private VilleRepository villeRepository;

    private PointConnexionRepository pointConnexionRepository;

    public VilleServiceimpl(VilleRepository villeRepository, PointConnexionRepository pointConnexionRepository) {
        this.villeRepository = villeRepository;
        this.pointConnexionRepository= pointConnexionRepository;
    }

    @Override
    public VilleDTO create(VilleDTO villeDTO) {
        Ville save = villeRepository.save(VilleDTO.toEntity(villeDTO));
        return VilleDTO.toDTO(save);
    }

    @Override
    public List<VilleDTO> findAll() {
        List<VilleDTO> villeDTOS = this.villeRepository.findAll().stream().map(VilleDTO::toDTO).toList();
        villeDTOS.forEach((
                element->{
                    List<PointConnexionDTO> pointConnexionDTOS = this.pointConnexionRepository.findPointConnexionListByidVille(element.getId()).stream().map(PointConnexionDTO::toDTO).toList();
                    element.setPointConnexions(pointConnexionDTOS);
                }
                ));
        return villeDTOS;

    }

    @Override
    public VilleDTO findById(Integer id) {
        if (villeRepository.findById(id).isPresent()) {
            VilleDTO villeDTO = VilleDTO.toDTO(villeRepository.findById(id).get());
            List<PointConnexion> pointConnexions = this.pointConnexionRepository.findPointConnexionListByidVille(villeDTO.getId());
            List<PointConnexionDTO> pointConnexionDTOS=new ArrayList<>();
            pointConnexions.forEach(
                    element->{
                        pointConnexionDTOS.add(PointConnexionDTO.toDTO(element));
                    }
            );
            villeDTO.setPointConnexions(pointConnexionDTOS);
            return villeDTO;
        }
        return null;
    }

    @Override
    public void update(Integer identifiant, VilleDTO villeDTO) {
        villeDTO.setId(identifiant);
        Ville save = villeRepository.save(VilleDTO.toEntity(villeDTO));

        villeRepository.save(save);
    }

    @Override
    public void deleteById(Integer id) {
        this.villeRepository.deleteById(id);
    }
}
