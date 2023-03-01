package com.stage.projet.controller;


import com.stage.projet.dto.DemandeDTO;
import com.stage.projet.dto.LocationFONDTO;
import com.stage.projet.dto.PointConnexionDTO;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.model.Demande;
import com.stage.projet.model.LocationFON;
import com.stage.projet.service.DemandeService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_DEMANDES;

@Slf4j
@RestController
@RequestMapping(value = ENDPOINT_DEMANDES)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DemandeController {

    private DemandeService demandeService;

    public DemandeController(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer create(@RequestBody DemandeDTO demandeDTO){
        return this.demandeService.create(demandeDTO);
    }

    @GetMapping
    public List<DemandeDTO> findAll(){
        return demandeService.findAll();
    }

    @GetMapping("/{id}")
    public DemandeDTO findById(@PathVariable("id") Integer id){

        DemandeDTO reponse= demandeService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody DemandeDTO demandeDTO) {
        log.info(String.valueOf(demandeDTO));
        if(demandeService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        demandeService.update(identifiant,demandeDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        demandeService.deleteById(id);

    }

    //une demandefon à executer doit etre accepté et/ou avoir une locationfon non-validé
    @GetMapping("/objet/{objet}/{etat}")
    public List<DemandeDTO> listeDemandesFonATraiter(@PathVariable("objet") String objet,@PathVariable("etat") String etat){
        List<DemandeDTO> reponse= demandeService.findAllByObjetAndEtat(objet,etat);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;

    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport()throws FileNotFoundException, JRException{
        return demandeService.exportReport() ;
    }
    @GetMapping("/facturefon/report")
    public ResponseEntity<byte[]> exportReportFacturefon() throws FileNotFoundException, JRException{
        return demandeService.exportReportFacturefon();
    }


    

}
