package com.stage.projet.service.impl;

import com.stage.projet.dto.*;
import com.stage.projet.model.LocationFON;
import com.stage.projet.model.LocationSE;
import com.stage.projet.model.ZoneSE;
import com.stage.projet.repository.LocationSERepository;
import com.stage.projet.repository.ZoneSERepository;
import com.stage.projet.service.LocationFONService;
import com.stage.projet.service.LocationSEService;
import com.stage.projet.service.ZoneSEService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LocationSEServiceimpl implements LocationSEService {

    LocationSERepository locationSERepository;

    ZoneSEService zoneSEService;

    public LocationSEServiceimpl(LocationSERepository locationSERepository , ZoneSEService zoneSEService) {
        this.locationSERepository = locationSERepository;
        this.zoneSEService =  zoneSEService;
    }

    @Override
    public LocationSEDTO create(LocationSEDTO locationSEDTO) {
        LocationSE save= locationSERepository.save(LocationSEDTO.toEntity(locationSEDTO));

        List<ZoneSEDTO> zoneSEDTOList = locationSEDTO.getZones();

                zoneSEDTOList.forEach(element->{
                    element.setLocationSEDTO(LocationSEDTO.toDTO(save));
                    this.zoneSEService.create(element);
                });

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
    public LocationSEDTO findById(Integer identifiant) {

        if (locationSERepository.findById(identifiant).isPresent()) {
            List<ZoneSEDTO> zoneSEDTOList = this.getZones(identifiant);
          //  List<FactureFONDTO> facturefons=this.getFactureFons(identifiant);

            LocationSEDTO locationSE = LocationSEDTO.toDTO(locationSERepository.findById(identifiant).get());
            locationSE.setZones(zoneSEDTOList);
          //  locationSE.setFacturefons(facturefons);
            return locationSE;

        }

        return null;
    }

    public List<ZoneSEDTO> getZones(Integer idLocation) {
        List<ZoneSEDTO> zonesOfLocation = this.zoneSEService.findzonesOfLocation(idLocation);
        return zonesOfLocation;
    }

    @Override
    public void update(Integer identifiant, LocationSEDTO locationSEDTO) {
        //log.info(String.valueOf(locationSEDTO));
        LocationSE save = LocationSEDTO.toEntity(locationSEDTO);
        //la liste des zones reçues
        List<ZoneSEDTO> zoneSEDTOList = locationSEDTO.getZones();
        //  log.info(String.valueOf(liaisonFONDTOS));
        if (zoneSEDTOList != null) {
            zoneSEDTOList.forEach(element -> {
                //  log.info(String.valueOf(element));
                element.setLocationSEDTO(locationSEDTO);
                this.zoneSEService.update(element.getId(), element);
            });

        }
      //  log.info(String.valueOf(save));
        save.setId(identifiant);
        locationSERepository.save(save);
    }

    @Override
    public void deleteById(Integer id) {
        this.locationSERepository.deleteById(id);
    }
}
