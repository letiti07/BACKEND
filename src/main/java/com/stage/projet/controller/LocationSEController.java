package com.stage.projet.controller;

import com.stage.projet.dto.LocationSEDTO;
import com.stage.projet.dto.ZoneSEDTO;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.service.LocationSEService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_LOCATIONSE;

@Slf4j
@RestController
@RequestMapping(value = ENDPOINT_LOCATIONSE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LocationSEController {

    LocationSEService locationSEService ;

    public LocationSEController(LocationSEService locationSEService) {
        this.locationSEService = locationSEService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public LocationSEDTO create(@RequestBody LocationSEDTO locationSEDTO){
        return this.locationSEService.create(locationSEDTO);
    }

    @GetMapping
    public List<LocationSEDTO> findAll(){
        return locationSEService.findAll();
    }

    @GetMapping("/{id}")
    public LocationSEDTO findById(@PathVariable("id") Integer id){

        LocationSEDTO reponse= locationSEService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody LocationSEDTO locationSEDTO) {
        if(locationSEService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        locationSEService.update(identifiant,locationSEDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        locationSEService.deleteById(id);

    }

}
