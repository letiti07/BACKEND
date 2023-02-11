package com.stage.projet.controller;


import com.stage.projet.dto.DemandeurDTO;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.model.Demandeur;
import com.stage.projet.model.Role;
import com.stage.projet.model.Utilisateur;
import com.stage.projet.service.DemandeurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_DEMANDEURS;

@Slf4j
@RestController
@RequestMapping(ENDPOINT_DEMANDEURS)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DemandeurController {

    private DemandeurService demandeurService;

    public DemandeurController(DemandeurService demandeurService) {
        this.demandeurService = demandeurService;
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer create(@RequestBody DemandeurDTO demandeurDTO){
        return this.demandeurService.create(demandeurDTO);
    }

    @GetMapping
    public List<DemandeurDTO> findAll(){
        return demandeurService.findAll();
    }

    @GetMapping("/{id}")
    public DemandeurDTO findById(@PathVariable("id") Integer id){

        DemandeurDTO reponse= demandeurService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody DemandeurDTO demandeurDTO) {
        if(demandeurService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        demandeurService.update(identifiant,demandeurDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        demandeurService.deleteById(id);

    }
}
