package com.stage.projet.controller;

import com.stage.projet.dto.TvaDTO;
import com.stage.projet.exception.RequeteFailed;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.service.TvaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.stage.projet.utils.Constants.ENDPOINT_TVA;

@Slf4j
@RestController
@RequestMapping(value = ENDPOINT_TVA)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TvaController {

    TvaService tvaService;

    public TvaController(TvaService tvaService) {
        this.tvaService = tvaService;
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer create(@RequestBody TvaDTO tvaDTO){
        AtomicReference<Integer> compteur= new AtomicReference<>(0);
        List<TvaDTO> all = this.findAll();
        all.forEach(
                element->{
                    if(element.isActif()){
                       compteur.set(compteur.get() + 1);
                    }
                }
        );
        log.info(String.valueOf(compteur));
        if(compteur.equals(0)==false) {
           throw new RequeteFailed();
        }
        return this.tvaService.create(tvaDTO);
    }

    @GetMapping
    public List<TvaDTO> findAll(){
        return tvaService.findAll();
    }

    @GetMapping("/{id}")
    public TvaDTO findById(@PathVariable("id") Integer id){

        TvaDTO reponse= tvaService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody TvaDTO tvaDTO) {
        if(tvaService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        tvaService.update(identifiant,tvaDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        tvaService.deleteById(id);

    }

    @GetMapping("/tvaActif")
    public AtomicReference<TvaDTO> getTvaActif(){
        return tvaService.getTvaActif();
    }
}
