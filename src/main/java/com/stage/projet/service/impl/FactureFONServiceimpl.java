package com.stage.projet.service.impl;

import com.stage.projet.dto.*;
import com.stage.projet.model.*;
import com.stage.projet.repository.*;
import com.stage.projet.service.DemandeService;
import com.stage.projet.service.FactureFONService;
import com.stage.projet.service.LocationFONService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;


@Service
@Slf4j
public class FactureFONServiceimpl implements FactureFONService {

    FactureFONRepository factureFONRepository;

    LocationFONRepository locationFONRepository;

    LocationSERepository locationSERepository;

    DemandeRepository demandeRepository;

    TvaRepository tvaRepository;

    VirementRepository virementRepository;

    ChecqueRepository checqueRepository;

    public FactureFONServiceimpl(FactureFONRepository factureFONRepository,LocationFONRepository locationFONRepository,
                                 TvaRepository tvaRepository,VirementRepository virementRepository,ChecqueRepository checqueRepository,
                                 LocationSERepository locationSERepository) {
        this.factureFONRepository = factureFONRepository;
        this.locationFONRepository= locationFONRepository;
        this.tvaRepository=tvaRepository;
        this.virementRepository=virementRepository;
        this.checqueRepository=checqueRepository;
        this.locationSERepository=locationSERepository;
    }

    @Override
    public FactureFONDTO create(FactureFONDTO factureFONDTO) {



        FactureFON factureFON = FactureFONDTO.toEntity(factureFONDTO);
        factureFON.setEtat("émise");
        FactureFON save = factureFONRepository.save(factureFON);
        return factureFONDTO.toDTO(save);
    }

    @Override
    public List<FactureFONDTO> findAll() {
        return  this.factureFONRepository.findAll().stream().map(FactureFONDTO::toDTO).toList();

    }

    @Override
    public FactureFONDTO findById(Integer id) {
        if (factureFONRepository.findById(id).isPresent()) {
            return FactureFONDTO.toDTO(factureFONRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public void update(Integer identifiant, FactureFONDTO factureFONDTO) {
        int d1=factureFONDTO.getTvaDTO().getId();
        Optional<Tva> tva = tvaRepository.findById(d1);
        FactureFON factureFON = FactureFONDTO.toEntity(factureFONDTO);
        
        if(factureFONDTO.getLocationFONDTO() != null) {
            int id=factureFONDTO.getLocationFONDTO().getId();
            Optional<LocationFON> locationFON = locationFONRepository.findById(id);
            factureFON.setLocationfon(locationFON.get());
        }
        if(factureFONDTO.getLocationSEDTO() != null){
            int id1=factureFONDTO.getLocationSEDTO().getId();
            Optional<LocationSE> locationSE = locationSERepository.findById(id1);
            factureFON.setLocationse(locationSE.get());
        }

        factureFON.setTva(tva.get());

        factureFON.setId(identifiant);
        factureFONRepository.save(factureFON);


    }

    @Override
    public void deleteById(Integer id) {
        this.factureFONRepository.deleteById(id);
    }


    @Override
    public List<FactureFONDTO>findFactureFonsOfLocation(Integer identifiant) {
        return this.factureFONRepository.findAllByLocationfonId(identifiant).stream().map(FactureFONDTO::toDTO).toList();
    }

    public List<FactureFONDTO> findFacturesesOfLocation(Integer identifiant){
        return this.factureFONRepository.findAllByLocationseId(identifiant).stream().map(FactureFONDTO::toDTO).toList();
    }

    public void validerEnInstance(FactureFONDTO factureFONDTO,Integer identifiant) {
        Optional<LocationFON> locationfon = this.locationFONRepository.findById(identifiant);
        String etat="en instance";
        locationfon.get().setEtat(etat);
        this.locationFONRepository.save(locationfon.get());

    }

    public void validerLitigieux(FactureFONDTO factureFONDTO,Integer identifiant) {
        int id=factureFONDTO.getLocationFONDTO().getId();
        int d1=factureFONDTO.getTvaDTO().getId();
        log.info(String.valueOf(id));
        Optional<LocationFON> locationFON = locationFONRepository.findById(id);
        Optional<Tva> tva = tvaRepository.findById(d1);
        FactureFON factureFON = FactureFONDTO.toEntity(factureFONDTO);
        factureFON.setLocationfon(locationFON.get());
        factureFON.setTva(tva.get());

        String etat="litigieux";
        factureFON.setId(identifiant);
        factureFON.setEtat(etat);
        factureFONRepository.save(factureFON);

    }

    public void validerAnnule(FactureFONDTO factureFONDTO,Integer identifiant) {


        int d1=factureFONDTO.getTvaDTO().getId();
        Optional<Tva> tva = tvaRepository.findById(d1);
        FactureFON factureFON = FactureFONDTO.toEntity(factureFONDTO);
        factureFON.setTva(tva.get());

        if(factureFONDTO.getLocationFONDTO() != null){
            int id=factureFONDTO.getLocationFONDTO().getId();
            Optional<LocationFON> locationFON = locationFONRepository.findById(id);
            factureFON.setLocationfon(locationFON.get());
        }
        if(factureFONDTO.getLocationSEDTO() != null){
            int id=factureFONDTO.getLocationSEDTO().getId();
            Optional<LocationSE> locationSE = locationSERepository.findById(id);
            factureFON.setLocationse(locationSE.get());
        }


        factureFON.setCommentaires(factureFONDTO.getCommentaires());

      //  log.info(String.valueOf(factureFON));

        String etat="annulee";
        factureFON.setId(identifiant);
        factureFON.setEtat(etat);
        factureFONRepository.save(factureFON);


    }

    public void validerPaye(FactureFONDTO factureFONDTO,Integer identifiant) {

        int d1=factureFONDTO.getTvaDTO().getId();
        Optional<Tva> tva = tvaRepository.findById(d1);
        FactureFON factureFON = FactureFONDTO.toEntity(factureFONDTO);

        factureFON.setTva(tva.get());

        log.info(String.valueOf(factureFONDTO.getVirementDTO()));
        log.info(String.valueOf(factureFONDTO.getChecqueDTO()));

        if(factureFONDTO.getLocationFONDTO() != null){
            int id=factureFONDTO.getLocationFONDTO().getId();
            Optional<LocationFON> locationFON = locationFONRepository.findById(id);
            factureFON.setLocationfon(locationFON.get());
        }
        if(factureFONDTO.getLocationSEDTO() != null){
            int id=factureFONDTO.getLocationSEDTO().getId();
            Optional<LocationSE> locationSE = locationSERepository.findById(id);
            factureFON.setLocationse(locationSE.get());
        }

        if(factureFONDTO.getVirementDTO()!=null){
            VirementDTO virementDTO = factureFONDTO.getVirementDTO();
            Virement save = this.virementRepository.save(VirementDTO.toEntity(virementDTO));
            factureFON.setVirement(save);
        }

        if(factureFONDTO.getChecqueDTO()!=null){
            ChecqueDTO checqueDTO = factureFONDTO.getChecqueDTO();
            Checque save = this.checqueRepository.save(ChecqueDTO.toEntity(checqueDTO));
            factureFON.setChecque(save);
        }

        String etat="payee";
        factureFON.setId(identifiant);
        factureFON.setEtat(etat);
        factureFONRepository.save(factureFON);


    }






}
