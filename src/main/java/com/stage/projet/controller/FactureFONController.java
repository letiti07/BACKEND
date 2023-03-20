package com.stage.projet.controller;

import com.lowagie.text.DocumentException;
import com.stage.projet.dto.FactureFONDTO;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.service.FactureFONService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_FACTUREFON;

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

    @PutMapping("/validerEnInstance/{id}")
    public void validerEnInstance(@RequestBody FactureFONDTO factureFONDTO,@PathVariable("id") Integer identifiant){
        this.factureFONService.validerEnInstance(factureFONDTO,identifiant);
    }

    @PutMapping("/validerLitigieux/{id}")
    public void validerLitigieux(@RequestBody FactureFONDTO factureFONDTO,@PathVariable("id") Integer identifiant){

        this.factureFONService.validerLitigieux(factureFONDTO,identifiant);
    }

    @PutMapping("/validerAnnule/{id}")
    public void validerAnnule(@RequestBody FactureFONDTO factureFONDTO,@PathVariable("id") Integer identifiant){
        log.info(String.valueOf(factureFONDTO));
        this.factureFONService.validerAnnule(factureFONDTO,identifiant);
    }

    @PutMapping("/validerPaye/{id}")
    public void validerPaye(@RequestBody FactureFONDTO factureFONDTO,@PathVariable("id") Integer identifiant){

        this.factureFONService.validerPaye(factureFONDTO,identifiant);
    }


}
