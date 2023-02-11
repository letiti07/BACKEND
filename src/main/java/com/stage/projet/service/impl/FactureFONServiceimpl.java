package com.stage.projet.service.impl;

import com.stage.projet.dto.FactureFONDTO;
import com.stage.projet.dto.LiaisonFONDTO;
import com.stage.projet.dto.TvaDTO;
import com.stage.projet.model.FactureFON;
import com.stage.projet.model.LocationFON;
import com.stage.projet.model.Tva;
import com.stage.projet.repository.FactureFONRepository;
import com.stage.projet.service.FactureFONService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.stage.projet.repository.LocationFONRepository;
import com.stage.projet.repository.TvaRepository;


@Service
@Slf4j
public class FactureFONServiceimpl implements FactureFONService {

    FactureFONRepository factureFONRepository;

    LocationFONRepository locationFONRepository;

    TvaRepository tvaRepository;

    public FactureFONServiceimpl(FactureFONRepository factureFONRepository,LocationFONRepository locationFONRepository,TvaRepository tvaRepository) {
        this.factureFONRepository = factureFONRepository;
        this.locationFONRepository= locationFONRepository;
        this.tvaRepository=tvaRepository;
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
        int id=factureFONDTO.getLocationFONDTO().getId();
        int d1=factureFONDTO.getTvaDTO().getId();
        log.info(String.valueOf(id));
        Optional<LocationFON> locationFON = locationFONRepository.findById(id);
        Optional<Tva> tva = tvaRepository.findById(d1);
        FactureFON factureFON = FactureFONDTO.toEntity(factureFONDTO);
        factureFON.setLocationfon(locationFON.get());
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

    public void validerEnInstance(Integer identifiant) {
        Optional<LocationFON> locationfon = this.locationFONRepository.findById(identifiant);
        String etat="en instance";
        locationfon.get().setEtat(etat);
        this.locationFONRepository.save(locationfon.get());

    }

    public void validerLitigieux(Integer identifiant) {
        Optional<LocationFON> locationfon = this.locationFONRepository.findById(identifiant);
        String etat="litigieux";
        locationfon.get().setEtat(etat);
        this.locationFONRepository.save(locationfon.get());

    }

    public void validerAnnule(Integer identifiant) {
        Optional<LocationFON> locationfon = this.locationFONRepository.findById(identifiant);
        String etat="annulee";
        locationfon.get().setEtat(etat);
        this.locationFONRepository.save(locationfon.get());

    }

    public void validerPaye(Integer identifiant) {
        Optional<LocationFON> locationfon = this.locationFONRepository.findById(identifiant);
        String etat="payee";
        locationfon.get().setEtat(etat);
        this.locationFONRepository.save(locationfon.get());

    }


}
