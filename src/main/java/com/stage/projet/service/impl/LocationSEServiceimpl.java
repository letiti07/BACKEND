package com.stage.projet.service.impl;

import com.stage.projet.dto.LocationSEDTO;
import com.stage.projet.dto.ZoneSEDTO;
import com.stage.projet.model.LocationSE;
import com.stage.projet.model.ZoneSE;
import com.stage.projet.repository.LocationSERepository;
import com.stage.projet.service.LocationFONService;
import com.stage.projet.service.LocationSEService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LocationSEServiceimpl implements LocationSEService {

    LocationSERepository locationSERepository;

    public LocationSEServiceimpl(LocationSERepository locationSERepository) {
        this.locationSERepository = locationSERepository;
    }

    @Override
    public LocationSEDTO create(LocationSEDTO locationSEDTO) {
        LocationSE save;
        save = locationSERepository.save(LocationSEDTO.toEntity(locationSEDTO));
        return LocationSEDTO.toDTO(save);

    }

    @Override
    public List<LocationSEDTO> findAll() {
        List<LocationSE> locationSEList=this.locationSERepository.findAll();
        List<LocationSEDTO> locationSEDTOS = new ArrayList<>();

        locationSEList.forEach(
                element -> {
                    LocationSE locationSE = new LocationSE();
                    locationSEDTOS.add(LocationSEDTO.toDTO(element));
                }
        );

        return locationSEDTOS;
    }

    @Override
    public LocationSEDTO findById(Integer id) {
        if(locationSERepository.findById(id).isPresent()){
            LocationSEDTO locationSEDTO = LocationSEDTO.toDTO(locationSERepository.findById(id).get());
            return locationSEDTO;
        }
        return null;
    }

    @Override
    public void update(Integer identifiant, LocationSEDTO locationSEDTO) {

        locationSEDTO.setId(identifiant);
        LocationSE save =  locationSERepository.save(LocationSEDTO.toEntity(locationSEDTO));
        log.info(String.valueOf(save));
       // locationSERepository.save(save);
    }

    @Override
    public void deleteById(Integer id) {
        this.locationSERepository.deleteById(id);
    }
}
