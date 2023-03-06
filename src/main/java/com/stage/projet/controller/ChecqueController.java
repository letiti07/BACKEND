package com.stage.projet.controller;

import com.stage.projet.dto.ChecqueDTO;
import com.stage.projet.dto.VirementDTO;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.service.ChecqueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_CHECQUE;
import static com.stage.projet.utils.Constants.ENDPOINT_VIREMENT;

@Slf4j
@RestController
@RequestMapping(value = ENDPOINT_CHECQUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChecqueController {

    ChecqueService checqueService;

    public ChecqueController(ChecqueService checqueService) {
        this.checqueService = checqueService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ChecqueDTO create(@RequestBody ChecqueDTO checqueDTO){
        return this.checqueService.create(checqueDTO);
    }

    @GetMapping
    public List<ChecqueDTO> findAll(){
        return checqueService.findAll();
    }

    @GetMapping("/{id}")
    public ChecqueDTO findById(@PathVariable("id") Integer id){

        ChecqueDTO reponse= checqueService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody ChecqueDTO checqueDTO) {
        if(checqueService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        checqueService.update(identifiant,checqueDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        checqueService.deleteById(id);

    }


}
