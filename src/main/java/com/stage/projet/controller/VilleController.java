package com.stage.projet.controller;

import com.stage.projet.dto.PointConnexionDTO;
import com.stage.projet.dto.VilleDTO;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.service.VilleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_POINTCONNEXION;
import static com.stage.projet.utils.Constants.ENDPOINT_VILLE;

@Slf4j
@RestController
@RequestMapping(ENDPOINT_VILLE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VilleController {

    private VilleService villeService;

    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public VilleDTO create(@RequestBody VilleDTO villeDTO){
        return this.villeService.create(villeDTO);
    }

    @GetMapping
    public List<VilleDTO> findAll(){
        return villeService.findAll();
    }

    @GetMapping("/{id}")
    public VilleDTO findById(@PathVariable("id") Integer id){

        VilleDTO reponse= villeService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody VilleDTO villeDTO) {
        if(villeService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        villeService.update(identifiant,villeDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        villeService.deleteById(id);

    }


}
