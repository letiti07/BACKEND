package com.stage.projet.controller;

import com.stage.projet.dto.FactureFONDTO;
import com.stage.projet.dto.LiaisonFONDTO;
import com.stage.projet.dto.TvaDTO;
import com.stage.projet.exception.RequeteFailed;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.service.FactureFONService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.stage.projet.utils.Constants.ENDPOINT_FACTUREFON;
import static com.stage.projet.utils.Constants.ENDPOINT_LOCATIONFON;

@Slf4j
@RestController
@RequestMapping(ENDPOINT_FACTUREFON)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FactureFONController {

    FactureFONService factureFONService;

    public FactureFONController(FactureFONService factureFONService) {
        this.factureFONService = factureFONService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public FactureFONDTO create(@RequestBody FactureFONDTO factureFONDTO){

        log.info(String.valueOf(factureFONDTO));
        return this.factureFONService.create(factureFONDTO);
    }

    @GetMapping
    public List<FactureFONDTO> findAll(){
        return factureFONService.findAll();
    }

    @GetMapping("/{id}")
    public FactureFONDTO findById(@PathVariable("id") Integer id){

        FactureFONDTO reponse= factureFONService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody FactureFONDTO factureFONDTO) {
        if(factureFONService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        factureFONService.update(identifiant,factureFONDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        factureFONService.deleteById(id);

    }

    @GetMapping("/factureFonsOfLocation/{id}")
    public List<FactureFONDTO> findFactureFonsOfLocation(@PathVariable("id") Integer identifiant){
        return factureFONService.findFactureFonsOfLocation(identifiant);

    }

    @GetMapping("/validerEnInstance/{id}")
    public void validerEnInstance(Integer identifiant){
        if(factureFONService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        this.factureFONService.validerEnInstance(identifiant);
    }

    @GetMapping("/validerLitigieux/{id}")
    public void validerLitigieux(Integer identifiant){
        if(factureFONService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        this.factureFONService.validerLitigieux(identifiant);
    }

    @GetMapping("/validerAnnule/{id}")
    public void validerAnnule(Integer identifiant){
        if(factureFONService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        this.factureFONService.validerAnnule(identifiant);
    }

    @GetMapping("/validerPaye/{id}")
    public void validerPaye(Integer identifiant){
        if(factureFONService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        this.factureFONService.validerPaye(identifiant);
    }


}