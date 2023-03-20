package com.stage.projet.service.impl;

import com.stage.projet.dto.ZoneSEDTO;
import com.stage.projet.model.ZoneSE;
import com.stage.projet.repository.ZoneSERepository;
import com.stage.projet.service.ZoneSEService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZoneSEServiceImpl implements ZoneSEService {

    ZoneSERepository zoneSERepository;

    public ZoneSEServiceImpl(ZoneSERepository zoneSERepository) {
        this.zoneSERepository = zoneSERepository;
    }

    @Override
    public void deleteById(Integer id) {
        this.zoneSERepository.deleteById(id);
    }

    @Override
    public void update(Integer identifiant, ZoneSEDTO zoneSEDTO) {
        zoneSEDTO.setId(identifiant);
        ZoneSE save = (ZoneSE) zoneSERepository.save(ZoneSEDTO.toEntity(zoneSEDTO));
        zoneSERepository.save(save);

    }

    @Override
    public ZoneSEDTO findById(Integer identifiant) {
        if(zoneSERepository.findById(identifiant).isPresent()){
            ZoneSEDTO zoneSEDTO = ZoneSEDTO.toDTO((ZoneSE) zoneSERepository.findById(identifiant).get());
            return zoneSEDTO;
        }
        return null;
    }

    @Override
    public List<ZoneSEDTO> findAll() {
        List<ZoneSE> zoneSEList=this.zoneSERepository.findAll();
        List<ZoneSEDTO> zoneSEDTOS = new ArrayList<>();

        zoneSEList.forEach(
                element -> {
                     ZoneSE zoneSE = new ZoneSE();
                     zoneSEDTOS.add(ZoneSEDTO.toDTO(element));
                }
        );

        return zoneSEDTOS;
    }

    @Override
    public ZoneSEDTO create(ZoneSEDTO zoneSEDTO) {
        ZoneSE save;
        save = (ZoneSE) zoneSERepository.save(ZoneSEDTO.toEntity(zoneSEDTO));
        return ZoneSEDTO.toDTO(save);
    }
}
