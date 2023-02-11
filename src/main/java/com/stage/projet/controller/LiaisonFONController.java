package com.stage.projet.controller;

import com.stage.projet.dto.DemandeDTO;
import com.stage.projet.dto.LiaisonFONDTO;
import com.stage.projet.dto.LocationFONDTO;
import com.stage.projet.dto.PointConnexionDTO;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.model.LiaisonFON;
import com.stage.projet.service.LiaisonFONService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_LIAISONFON;
import static com.stage.projet.utils.Constants.ENDPOINT_POINTCONNEXION;

@Slf4j
@RestController
@RequestMapping(ENDPOINT_LIAISONFON)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LiaisonFONController {

    private LiaisonFONService liaisonFONService;

    public LiaisonFONController(LiaisonFONService liaisonFONService) {
        this.liaisonFONService = liaisonFONService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public LiaisonFONDTO create(@RequestBody LiaisonFONDTO liaisonFONDTO){
        return this.liaisonFONService.create(liaisonFONDTO);
    }

    @GetMapping
    public List<LiaisonFONDTO> findAll(){
        return liaisonFONService.findAll();
    }

    @GetMapping("/{id}")
    public LiaisonFONDTO findById(@PathVariable("id") Integer id){

        LiaisonFONDTO reponse= liaisonFONService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody LiaisonFONDTO liaisonFONDTO) {
        log.info(String.valueOf(liaisonFONDTO));
        log.info(String.valueOf(identifiant));
        if(liaisonFONService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        liaisonFONService.update(identifiant,liaisonFONDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
       liaisonFONService.deleteById(id);

    }

    @GetMapping("/liaisonsOfLocation/{id}")
    public List<LiaisonFONDTO> findLiaisonsOfLocation(@PathVariable("id") Integer identifiant){
         return liaisonFONService.findLiaisonsOfLocation(identifiant);

    }


    @GetMapping(value = "/{idLiaison}/addPointConnexion/{idPointConnexion}")
    public void addPointConnexion(@PathVariable("idLiaison") Integer idLiaison,@PathVariable("idPointConnexion") Integer idPointConnexion) {

        liaisonFONService.addPointConnexion(idLiaison, idPointConnexion);
    }

    @GetMapping(value = "/{idLiaison}/removePointConnexion/{idPointConnexion}")
    public void removePointConnexion(@PathVariable("idLiaison") Integer idLiaison,@PathVariable("idPointConnexion") Integer idPointConnexion) {

        liaisonFONService.removePointConnexion(idLiaison, idPointConnexion);
    }

    @GetMapping(value = "/listePointConnexion/{idLiaison}")
    public Collection<PointConnexionDTO> getPointsConnexion(@PathVariable("idLiaison") Integer idLiaison) {

       return liaisonFONService.getPointsConnexion(idLiaison);
    }

}
