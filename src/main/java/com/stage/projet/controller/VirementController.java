package com.stage.projet.controller;

import com.stage.projet.dto.VilleDTO;
import com.stage.projet.dto.VirementDTO;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.service.VirementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_VIREMENT;

@Slf4j
@RestController
@RequestMapping(value = ENDPOINT_VIREMENT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VirementController {

    VirementService virementService;

    public VirementController(VirementService virementService) {
        this.virementService = virementService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public VirementDTO create(@RequestBody VirementDTO virementDTO){
        return this.virementService.create(virementDTO);
    }

    @GetMapping
    public List<VirementDTO> findAll(){
        return virementService.findAll();
    }

    @GetMapping("/{id}")
    public VirementDTO findById(@PathVariable("id") Integer id){

        VirementDTO reponse= virementService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody VirementDTO virementDTO) {
        if(virementService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        virementService.update(identifiant,virementDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        virementService.deleteById(id);

    }

}
