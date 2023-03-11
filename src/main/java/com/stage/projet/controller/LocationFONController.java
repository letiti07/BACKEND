package com.stage.projet.controller;

import com.stage.projet.dto.DemandeDTO;
import com.stage.projet.dto.FactureFONDTO;
import com.stage.projet.dto.LiaisonFONDTO;
import com.stage.projet.dto.LocationFONDTO;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.model.LiaisonFON;
import com.stage.projet.model.LocationFON;
import com.stage.projet.repository.LocationFONRepository;
import com.stage.projet.service.DemandeService;
import com.stage.projet.service.FactureFONService;
import com.stage.projet.service.LocationFONService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_DEMANDES;
import static com.stage.projet.utils.Constants.ENDPOINT_LOCATIONFON;

@Slf4j
@RestController
@RequestMapping(ENDPOINT_LOCATIONFON)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LocationFONController {

    private LocationFONService locationFONService;

    private FactureFONService factureFONService;

    public LocationFONController(LocationFONService locationFONService,FactureFONService factureFONService) {
        this.locationFONService=locationFONService;
        this.factureFONService=factureFONService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public LocationFONDTO create(@RequestBody LocationFONDTO locationFONDTO){
        log.info(String.valueOf(locationFONDTO));
    return this.locationFONService.create(locationFONDTO);

    }

    @GetMapping
    public List<LocationFONDTO> findAll(){
        return locationFONService.findAll();
    }

    @GetMapping("/{id}")
    public LocationFONDTO findById(@PathVariable("id") Integer id){

        LocationFONDTO reponse= locationFONService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody LocationFONDTO locationFONDTO) {
        log.info(String.valueOf(locationFONDTO));
        log.info(String.valueOf(identifiant));
        if(locationFONService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        locationFONService.update(identifiant,locationFONDTO);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        locationFONService.deleteById(id);
    }

    @GetMapping(value = "/listeLiaisons/{idLocation}")
    public List<LiaisonFONDTO> getLiaisons(@PathVariable("idLocation") Integer idLocation){
       return this.locationFONService.getLiaisons(idLocation);
    }

    @GetMapping(value = "/FraisHebergementTotal/{idLocation}")
    public double getFraisHebergementTotalHTVA(@PathVariable("idLocation") Integer idLocation){
        log.info(String.valueOf(idLocation));
        if(this.locationFONService.findById(idLocation)==null){
            throw new RessourceNotFoundException();
        }
        return this.locationFONService.getFraisHebergementTotalHTVA(idLocation);
    }

    @GetMapping(value = "/FraisHebergementTotalWithTva/{idLocation}/{idFacture}")
    public double getFraisHebergementTotalWithTva(@PathVariable("idLocation") Integer idLocation,@PathVariable("idFacture") Integer idFacture){
        log.info(String.valueOf(idLocation));
        if(this.locationFONService.findById(idLocation)==null){
            throw new RessourceNotFoundException();
        }

        if(this.factureFONService.findById(idFacture)==null){
            throw new RessourceNotFoundException();
        }
        return this.locationFONService.getFraisHebergementTotalWithTva(idLocation,idFacture);
    }


    @GetMapping(value = "/FraisHebergementTotal/{idLocation}/{idFacture}")
    public double getFraisHebergementTotal(@PathVariable("idLocation") Integer idLocation,@PathVariable("idFacture") Integer idFacture){
        if(this.locationFONService.findById(idLocation)==null){
            throw new RessourceNotFoundException();
        }

        if(this.factureFONService.findById(idFacture)==null){
            throw new RessourceNotFoundException();
        }

        return this.locationFONService.getFraisHebergementTotal(idLocation,idFacture);

    }

    @GetMapping(value = "/PixTotalMetreLineaire/{idLocation}")
    public double getPrixTotalMetreLineaireHTVA(@PathVariable("idLocation") Integer idLocation){
        log.info(String.valueOf(idLocation));
        if(this.locationFONService.findById(idLocation)==null){
            throw new RessourceNotFoundException();
        }
        return this.locationFONService.getPrixTotalMetreLineaireHTVA(idLocation);
    }

    @GetMapping(value = "/PixTotalMetreLineaireWithTva/{idLocation}/{idFacture}")
    public double getPrixTotalMetreLineaireWithTVA(@PathVariable("idLocation") Integer idLocation,@PathVariable("idFacture") Integer idFacture){
        log.info(String.valueOf(idLocation));
        if(this.locationFONService.findById(idLocation)==null){
            throw new RessourceNotFoundException();
        }

        if(this.factureFONService.findById(idFacture)==null){
            throw new RessourceNotFoundException();
        }
        return this.locationFONService.getPrixTotalMetreLineaireWithTVA(idLocation,idFacture);
    }

    @GetMapping(value = "/PixTotalMetreLineaire/{idLocation}/{idFacture}")
    public double getPrixTotalMetreLineaire(@PathVariable("idLocation") Integer idLocation,@PathVariable("idFacture") Integer idFacture){
        if(this.locationFONService.findById(idLocation)==null){
            throw new RessourceNotFoundException();
        }

        if(this.factureFONService.findById(idFacture)==null){
            throw new RessourceNotFoundException();
        }

       return this.locationFONService.getPrixTotalMetreLineaire(idLocation,idFacture);

    }

    @GetMapping(value = "/validateEnInstance/{idLocation}")
    public void ValidateEnInstanceLocationFon(@PathVariable("idLocation") Integer id){
        log.info(String.valueOf(id));
        if(locationFONService.findById(id)==null){
            throw new RessourceNotFoundException();
        }
        this.locationFONService.ValidateEnInstanceLocationFon(id);

    }

    @GetMapping(value = "/validateValidé/{idLocation}")
    public void ValidateValidéLocationFon(@PathVariable("idLocation") Integer id){
        if(locationFONService.findById(id)==null){
            throw new RessourceNotFoundException();
        }
        this.locationFONService.ValidateValidéLocationFon(id);
    }

    @GetMapping(value = "/validateNonValidé/{idLocation}")
    public void ValidateNonValidéLocationFon(@PathVariable("idLocation") Integer id){
        if(locationFONService.findById(id)==null){
            throw new RessourceNotFoundException();
        }
        this.locationFONService.ValidateNonValidéLocationFon(id);
    }

    @GetMapping(value = "/listeFactureFons/{idLocation}")
    public List<FactureFONDTO> getFactureFons(@PathVariable("idLocation") Integer idLocation){
        return this.locationFONService.getFactureFons(idLocation);
    }

    @GetMapping(value = "/DemandeOfLocationfon/{idLocation}")
    public DemandeDTO getDemandeByIdLocationFon(@PathVariable("idLocation") Integer idLocation){
        return this.locationFONService.DemandeByIdLocationfon(idLocation);
    }

    @GetMapping(value = "/CoutMetreLineaireLiaison/{idLocation}/{idLiaison}")
    double getPrixTotalMetreLineaireLiaison(@PathVariable("idLocation") Integer idLocation,@PathVariable("idLiaison") Integer idLiaison){

        return  this.locationFONService.getPrixTotalMetreLineaireLiaison(idLocation,idLiaison);
    }

}
