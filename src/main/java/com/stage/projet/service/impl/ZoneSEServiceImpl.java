package com.stage.projet.service.impl;

import com.stage.projet.dto.LiaisonFONDTO;
import com.stage.projet.dto.ZoneSEDTO;
import com.stage.projet.model.LiaisonFON;
import com.stage.projet.model.LocationFON;
import com.stage.projet.model.LocationSE;
import com.stage.projet.model.ZoneSE;
import com.stage.projet.repository.LocationFONRepository;
import com.stage.projet.repository.LocationSERepository;
import com.stage.projet.repository.ZoneSERepository;
import com.stage.projet.service.LocationFONService;
import com.stage.projet.service.ZoneSEService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ZoneSEServiceImpl implements ZoneSEService {

    ZoneSERepository zoneSERepository;

    LocationSERepository locationSERepository;

    public ZoneSEServiceImpl(ZoneSERepository zoneSERepository, LocationSERepository locationSERepository) {
        this.zoneSERepository = zoneSERepository;
        this.locationSERepository = locationSERepository;
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

        //recuperer la location
        // log.info(String.valueOf(liaisonFONDTO));
        int id=zoneSEDTO.getLocationSEDTO().getId();
        Optional<LocationSE> locationSE = locationSERepository.findById(id);
        ZoneSE save= ZoneSEDTO.toEntity(zoneSEDTO);

        //inserer la location
        save.setLocationse(locationSE.get());
        //Enregistrer la liaison
        ZoneSE entite=zoneSERepository.save(save);


       // ZoneSE save;
     //   save = (ZoneSE) zoneSERepository.save(ZoneSEDTO.toEntity(zoneSEDTO));
        return ZoneSEDTO.toDTO(save);
    }

    public List<ZoneSEDTO> findzonesOfLocation(Integer identifiant) {
        return this.zoneSERepository.findAllByLocationseId(identifiant).stream().map(ZoneSEDTO::toDTO).toList();
    }
}
