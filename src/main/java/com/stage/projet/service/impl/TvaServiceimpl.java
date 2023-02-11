package com.stage.projet.service.impl;

import com.stage.projet.dto.TvaDTO;
import com.stage.projet.model.Tva;
import com.stage.projet.repository.TvaRepository;
import com.stage.projet.service.TvaService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class TvaServiceimpl implements TvaService {

    TvaRepository tvaRepository;

    public TvaServiceimpl(TvaRepository tvaRepository) {
        this.tvaRepository = tvaRepository;
    }

    @Override
    public Integer create(TvaDTO tvaDTO) {
        return tvaRepository.save(TvaDTO.toEntity(tvaDTO)).getId();
    }

    @Override
    public List<TvaDTO> findAll() {
        return  this.tvaRepository.findAll().stream().map(TvaDTO::toDTO).toList();
    }

    @Override
    public TvaDTO findById(Integer id) {
        if (tvaRepository.findById(id).isPresent()) {
            return TvaDTO.toDTO(tvaRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public void update(Integer identifiant, TvaDTO tvaDTO) {
        tvaDTO.setId(identifiant);
        Tva save = tvaRepository.save(TvaDTO.toEntity(tvaDTO));

        tvaRepository.save(save);
    }

    @Override
    public void deleteById(Integer id) {
        this.tvaRepository.deleteById(id);
    }

    @Override
    public AtomicReference<TvaDTO> getTvaActif() {
        AtomicReference<TvaDTO> tvaActif = new AtomicReference<>();
        List<TvaDTO> liste=findAll();
        liste.forEach(
                element->{
                    if(element.isActif()){
                        tvaActif.set(element);
                    }
                }
        );
        log.info(String.valueOf(tvaActif));
        return tvaActif;
    }


}
