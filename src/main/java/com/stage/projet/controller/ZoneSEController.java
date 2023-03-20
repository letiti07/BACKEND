package com.stage.projet.controller;

import com.stage.projet.dto.ZoneSEDTO;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.service.ZoneSEService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_ZONESE;

@Slf4j
@RestController
@RequestMapping(value = ENDPOINT_ZONESE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ZoneSEController {

    ZoneSEService zoneSEService;

    public ZoneSEController(ZoneSEService zoneSEService) {
        this.zoneSEService = zoneSEService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ZoneSEDTO create(@RequestBody ZoneSEDTO zoneSEDTO){
        return this.zoneSEService.create(zoneSEDTO);
    }

    @GetMapping
    public List<ZoneSEDTO> findAll(){
        return zoneSEService.findAll();
    }

    @GetMapping("/{id}")
    public ZoneSEDTO findById(@PathVariable("id") Integer id){

        ZoneSEDTO reponse= zoneSEService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody ZoneSEDTO zoneSEDTO) {
        if(zoneSEService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        zoneSEService.update(identifiant,zoneSEDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        zoneSEService.deleteById(id);

    }

}
