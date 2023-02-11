package com.stage.projet.controller;

import com.stage.projet.dto.DemandeDTO;
import com.stage.projet.dto.LiaisonFONDTO;
import com.stage.projet.dto.PointConnexionDTO;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.service.DemandeService;
import com.stage.projet.service.PointConnexionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_DEMANDES;
import static com.stage.projet.utils.Constants.ENDPOINT_POINTCONNEXION;

@Slf4j
@RestController
@RequestMapping(ENDPOINT_POINTCONNEXION)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PointConnexionController {

    private PointConnexionService pointConnexionService;

    public PointConnexionController(PointConnexionService pointConnexionService) {
        this.pointConnexionService = pointConnexionService;
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PointConnexionDTO create(@RequestBody PointConnexionDTO pointConnexionDTO){
        return this.pointConnexionService.create(pointConnexionDTO);
    }

    @GetMapping
    public List<PointConnexionDTO> findAll(){
        return pointConnexionService.findAll();
    }

    @GetMapping("/{id}")
    public PointConnexionDTO findById(@PathVariable("id") Integer id){

        PointConnexionDTO reponse= pointConnexionService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody PointConnexionDTO pointConnexionDTO) {
        if(pointConnexionService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        pointConnexionService.update(identifiant,pointConnexionDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        pointConnexionService.deleteById(id);

    }

    @GetMapping("/PointConnexionListOfVille/{id}")
    public List<PointConnexionDTO> findPointConnexionListByidVille(@PathVariable("id") Integer idVille){
        return this.pointConnexionService.findPointConnexionListByidVille(idVille);
    }



}
