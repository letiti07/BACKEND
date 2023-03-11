package com.stage.projet.service.impl;

import com.stage.projet.dto.*;
import com.stage.projet.model.Demande;
import com.stage.projet.model.LocationFON;
import com.stage.projet.repository.LocationFONRepository;
import com.stage.projet.service.FactureFONService;
import com.stage.projet.service.LiaisonFONService;
import com.stage.projet.service.LocationFONService;
import com.stage.projet.repository.DemandeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class LocationFONServiceimpl implements LocationFONService {


    private LocationFONRepository locationFONRepository;
    private LiaisonFONService liaisonFONService;

    private FactureFONService factureFONService;

    private DemandeRepository demandeRepository;

    public LocationFONServiceimpl(LocationFONRepository locationFONRepository,LiaisonFONService liaisonFONService,FactureFONService factureFONService,DemandeRepository demandeRepository) {


        this.locationFONRepository=locationFONRepository;
        this.liaisonFONService=liaisonFONService;
        this.factureFONService=factureFONService;
        this.demandeRepository=demandeRepository;



    }

    @Override
    public LocationFONDTO create(LocationFONDTO locationFONDTO) {
        LocationFON save = locationFONRepository.save(locationFONDTO.toEntity(locationFONDTO));

        List<LiaisonFONDTO> liaisonFONDTOS=locationFONDTO.getLiaisonfons();

        liaisonFONDTOS.forEach(element->{
            element.setLocationFONDTO(LocationFONDTO.toDTO(save));
            this.liaisonFONService.create(element);
        });
        return LocationFONDTO.toDTO(save);
    }

    @Override
    public List<LocationFONDTO> findAll() {
        List<LocationFONDTO> locationFONDTOS = this.locationFONRepository.findAll().stream().map(LocationFONDTO::toDTO).toList();
        locationFONDTOS.forEach(element->{

            List<LiaisonFONDTO> liaisons = this.getLiaisons(element.getId());
            element.setLiaisonfons(liaisons);
        });

        return locationFONDTOS;
    }

    @Override
    public LocationFONDTO findById(Integer identifiant) {
        if (locationFONRepository.findById(identifiant).isPresent()) {
            List<LiaisonFONDTO> liaisons = this.getLiaisons(identifiant);
            List<FactureFONDTO> facturefons=this.getFactureFons(identifiant);

            LocationFONDTO locationFONDTO = LocationFONDTO.toDTO(locationFONRepository.findById(identifiant).get());
            locationFONDTO.setLiaisonfons(liaisons);
            locationFONDTO.setFacturefons(facturefons);
            return locationFONDTO;

        }

        return null;
    }

    @Override
    public void update(Integer identifiant, LocationFONDTO locationFONDTO) {
        LocationFON save=LocationFONDTO.toEntity(locationFONDTO);
        //la liste des liaisons reçues
       List<LiaisonFONDTO> liaisonFONDTOS=locationFONDTO.getLiaisonfons();
        log.info(String.valueOf(liaisonFONDTOS));
        if(liaisonFONDTOS!=null) {
         liaisonFONDTOS.forEach(element -> {
        log.info(String.valueOf(element));
        element.setLocationFONDTO(locationFONDTO);
        this.liaisonFONService.update(element.getId(), element);
    });
}

        save.setId(identifiant);
        locationFONRepository.save(save);


    }

    @Override
    public void deleteById(Integer id) {
        this.locationFONRepository.deleteById(id);
    }


    public List<LiaisonFONDTO> getLiaisons(Integer idLocation) {
        List<LiaisonFONDTO> liaisonsOfLocation = this.liaisonFONService.findLiaisonsOfLocation(idLocation);
        liaisonsOfLocation.forEach(element->{
            List<PointConnexionDTO> pointsConnexions = this.liaisonFONService.getPointsConnexion(element.getId());
            element.setPointconnexions(pointsConnexions);
        });
        return liaisonsOfLocation;
    }

    @Override
    public double getFraisHebergementTotalHTVA(Integer IdLocation){
        AtomicReference<Double> FraisHebergementTotal = new AtomicReference<>((double) 0);
        LocationFONDTO locationFONDTO = this.findById(IdLocation);
        List<LiaisonFONDTO> liaisonfons = locationFONDTO.getLiaisonfons();
        liaisonfons.forEach(liaisonFONDTO->{
            List<PointConnexionDTO> pointConnexionDTOS=liaisonFONDTO.getPointconnexions();
            pointConnexionDTOS.forEach(pointConnexionDTO->{
                FraisHebergementTotal.set(FraisHebergementTotal.get() + pointConnexionDTO.getFraisHebergement());
            });
        });
        return FraisHebergementTotal.get();
    }

    public double getFraisHebergementTotalWithTva(Integer IdLocation,Integer idFacture){
       double FraisHebergementTotalHT;
        LocationFONDTO locationFONDTO = this.findById(IdLocation);
        log.info(String.valueOf(locationFONDTO));
        FactureFONDTO factureFONDTO=this.factureFONService.findById(idFacture);
        log.info(String.valueOf(factureFONDTO));
        TvaDTO tvaDTO=factureFONDTO.getTvaDTO();
        log.info(String.valueOf(tvaDTO));
        double fraisHebergementTotal = this.getFraisHebergementTotalHTVA((locationFONDTO.getId()));
        FraisHebergementTotalHT = fraisHebergementTotal * tvaDTO.getTva();
        log.info(String.valueOf(FraisHebergementTotalHT));

        return FraisHebergementTotalHT;
    }

    public double getFraisHebergementTotal(Integer idLocation,Integer idFacture){
        double fraisHebergementTotalHTVA = this.getFraisHebergementTotalHTVA(idLocation);
        double fraisHebergementTotalWithTva = this.getFraisHebergementTotalWithTva(idLocation, idFacture);
        double FraisHebergementTotal = fraisHebergementTotalHTVA + fraisHebergementTotalWithTva;
        return  FraisHebergementTotal;
    }

    public double getPrixTotalMetreLineaireHTVA(Integer idLocation){
        AtomicReference<Double> PrixTotalMetreLienaire= new AtomicReference<>(0.0);

        LocationFONDTO locationFONDTO =this.findById(idLocation);
        List<LiaisonFONDTO> liaisonFONDTOS=locationFONDTO.getLiaisonfons();
        liaisonFONDTOS.forEach(element->{
            Double coutLiaison;
            coutLiaison=element.getDistance() * locationFONDTO.getCoutMetreLineaire();
            PrixTotalMetreLienaire.set(coutLiaison + PrixTotalMetreLienaire.get());
        });
        return PrixTotalMetreLienaire.get();
    }

    public double getPrixTotalMetreLineaireWithTVA(Integer idLocation,Integer idFacture){
        double CoutMetreLineaireTotalHT;
        LocationFONDTO locationFONDTO = this.findById(idLocation);
        log.info(String.valueOf(locationFONDTO));
        FactureFONDTO factureFONDTO=this.factureFONService.findById(idFacture);
        log.info(String.valueOf(factureFONDTO));
        TvaDTO tvaDTO=factureFONDTO.getTvaDTO();
        log.info(String.valueOf(tvaDTO));
        double PrixTotalMetreLineaire = this.getPrixTotalMetreLineaireHTVA((locationFONDTO.getId()));
        CoutMetreLineaireTotalHT = PrixTotalMetreLineaire * tvaDTO.getTva();
        log.info(String.valueOf(CoutMetreLineaireTotalHT));

        return CoutMetreLineaireTotalHT;
    }

    public double getPrixTotalMetreLineaire(Integer idLocation,Integer idFacture){
        double prixTotalMetreLineaireWithTVA = this.getPrixTotalMetreLineaireWithTVA(idLocation, idFacture);
        double prixTotalMetreLineaireHTVA = this.getPrixTotalMetreLineaireHTVA(idLocation);

        double PrixTotalMetreLineaire = prixTotalMetreLineaireHTVA + prixTotalMetreLineaireWithTVA;
        return  PrixTotalMetreLineaire;
    }

    public double getPrixTotalMetreLineaireLiaison(Integer idLocation,Integer idLiaison){
        Double PrixTotalMetreLienaire;
        log.info(String.valueOf(idLiaison));
        log.info(String.valueOf(idLocation));
        LocationFONDTO locationFONDTO =this.findById(idLocation);
        LiaisonFONDTO liaisonFONDTO=this.liaisonFONService.findById(idLiaison);
        PrixTotalMetreLienaire= liaisonFONDTO.getDistance() * locationFONDTO.getCoutMetreLineaire();
        return PrixTotalMetreLienaire;
    }




    public void ValidateEnInstanceLocationFon(Integer id){
        log.info(String.valueOf(id));
        Optional<LocationFON> entite = locationFONRepository.findById(id);
        String etat= "en instance";
        entite.get().setEtat(etat);
        locationFONRepository.save(entite.get());
    }

    @Override
    public void ValidateValidéLocationFon(Integer id) {
        Optional<LocationFON> entite = locationFONRepository.findById(id);
        String etat= "validé";
        entite.get().setEtat(etat);
        locationFONRepository.save(entite.get());
    }

    @Override
    public void ValidateNonValidéLocationFon(Integer id) {
        Optional<LocationFON> entite = locationFONRepository.findById(id);
        String etat= "non-validé";
        entite.get().setEtat(etat);
        locationFONRepository.save(entite.get());
    }

    @Override
    public List<FactureFONDTO> getFactureFons(Integer idLocation) {
        List<FactureFONDTO> factureFons = this.factureFONService.findFactureFonsOfLocation(idLocation);
        return factureFons;
    }

    public DemandeDTO DemandeByIdLocationfon(Integer idLocationfon){
       Integer idDemandeFon= this.locationFONRepository.findIdDemandeOfLocationFon(idLocationfon);
       log.info(String.valueOf(idDemandeFon));
        Optional<Demande> demande = this.demandeRepository.findById(idDemandeFon);
        DemandeDTO demandeDTO= DemandeDTO.toDTO(demande.get());
        log.info(String.valueOf(demandeDTO));
        return demandeDTO;
    }


}
