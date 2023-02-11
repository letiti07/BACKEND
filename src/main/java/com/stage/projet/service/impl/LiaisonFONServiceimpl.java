package com.stage.projet.service.impl;

import com.stage.projet.dto.LiaisonFONDTO;
import com.stage.projet.dto.LocationFONDTO;
import com.stage.projet.dto.PointConnexionDTO;
import com.stage.projet.exception.RequeteFailed;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.model.*;
import com.stage.projet.repository.LiaisonFONRepository;
import com.stage.projet.repository.LocationFONRepository;
import com.stage.projet.repository.PointConnexionRepository;
import com.stage.projet.service.LiaisonFONService;
import com.stage.projet.service.LocationFONService;
import com.stage.projet.service.PointConnexionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@Transactional
public class LiaisonFONServiceimpl implements LiaisonFONService {

    private LiaisonFONRepository liaisonFONRepository;

    private LocationFONRepository locationFONRepository;

    private PointConnexionRepository pointConnexionRepository;


    private LocationFONService locationFONService;

    public LiaisonFONServiceimpl(LiaisonFONRepository liaisonFONRepository,
                                 LocationFONRepository locationFONRepository,
                                 PointConnexionRepository pointConnexionRepository



                                ) {
        this.liaisonFONRepository = liaisonFONRepository;
        this.locationFONRepository=locationFONRepository;
        this.pointConnexionRepository=pointConnexionRepository;

    }

    @Override
    public LiaisonFONDTO create(LiaisonFONDTO liaisonFONDTO) {

        //recuperer la location
        log.info(String.valueOf(liaisonFONDTO));
        int id=liaisonFONDTO.getLocationFONDTO().getId();
        Optional<LocationFON> locationFON = locationFONRepository.findById(id);
        LiaisonFON save=LiaisonFONDTO.toEntity(liaisonFONDTO);

        //inserer la location
        save.setLocationfon(locationFON.get());
        //Enregistrer la liaison
        LiaisonFON entite=liaisonFONRepository.save(save);

        //recuperer la liste des point de connexions
        List<PointConnexionDTO> liste=liaisonFONDTO.getPointconnexions();
        List<PointConnexion> liste1=new ArrayList<>();
        //Coversion en PointConnexion
        liste.forEach( element->{
                    liste1.add(PointConnexionDTO.toEntity(element));
                }
        );
        //Affecter chaque point de connexion à la liste à la liaison
        liste1.forEach(element->{
            this.addPointConnexion(entite.getId(),element.getId());
        });

        return LiaisonFONDTO.toDTO(entite);
    }

    @Override
    public List<LiaisonFONDTO> findAll() {
        List<LiaisonFONDTO> liaisonFONDTOS = this.liaisonFONRepository.findAll().stream().map(LiaisonFONDTO::toDTO).toList();
        liaisonFONDTOS.forEach(element->{
            List<PointConnexionDTO> pointConnexionDTOS=this.getPointsConnexion(element.getId());
            element.setPointconnexions(pointConnexionDTOS);
        });
        return liaisonFONDTOS;
    }

    @Override
    public LiaisonFONDTO findById(Integer id) {

        if (liaisonFONRepository.findById(id).isPresent()) {
            List<PointConnexionDTO> pointsConnexions = this.getPointsConnexion(id);
            LiaisonFONDTO liaisonFONDTO = LiaisonFONDTO.toDTO(liaisonFONRepository.findById(id).get());
            liaisonFONDTO.setPointconnexions(pointsConnexions);
            log.info(String.valueOf(liaisonFONDTO));
            return liaisonFONDTO;
        }
        return null;
    }

    @Override
    public void update(Integer identifiant, LiaisonFONDTO liaisonFONDTO) {
        //rcuperer la location et l'inserer
        int id = liaisonFONDTO.getLocationFONDTO().getId();
        log.info(String.valueOf(id));
        Optional<LocationFON> locationFON = locationFONRepository.findById(id);
        LiaisonFON save=LiaisonFONDTO.toEntity(liaisonFONDTO);
        save.setLocationfon(locationFON.get());

        //recuperer la liste des liaisons et les modifier
        List<PointConnexionDTO> pointConnexionDTOS=liaisonFONDTO.getPointconnexions();
        List<PointConnexion> liste=new ArrayList<>();
        pointConnexionDTOS.forEach(element->{
            liste.add(PointConnexionDTO.toEntity(element));
        });
        save.setPointconnexions(liste);

        save.setId(identifiant);
        liaisonFONRepository.save(save);

    }

    @Override
    public void deleteById(Integer id) {
        this.liaisonFONRepository.deleteById(id);
    }

    public void addPointConnexion(Integer idLiaison, Integer idPointConnexion) {

        log.info(String.valueOf(idLiaison),idPointConnexion);
        LiaisonFON liaisonFON= liaisonFONRepository.findById(idLiaison).orElseThrow(
                ()->new RessourceNotFoundException()
        );

        PointConnexion pointConnexion = pointConnexionRepository.findById(idPointConnexion).orElseThrow(
                ()->new RessourceNotFoundException()
        );

        List<PointConnexion> list= liaisonFON.getPointconnexions();
        //lever une exception si le role existe deja
        list.forEach(pointConnexion1 -> {
            if(pointConnexion==pointConnexion1){
                throw new RequeteFailed();
            }
        });

        list.add(pointConnexion);
        liaisonFON.setPointconnexions(list);
        liaisonFONRepository.save(liaisonFON);


    }

    @Override
    public void removePointConnexion(Integer idLiaison, Integer idPointConnexion) {
        LiaisonFON liaisonFON= liaisonFONRepository.findById(idLiaison).orElseThrow(
                ()->new RessourceNotFoundException()
        );

        PointConnexion pointConnexion = pointConnexionRepository.findById(idPointConnexion).orElseThrow(
                ()->new RessourceNotFoundException()
        );

        List<PointConnexion> list= liaisonFON.getPointconnexions();

        list.remove(pointConnexion);
        liaisonFON.setPointconnexions(list);
        liaisonFONRepository.save(liaisonFON);

    }

    @Override
    public List<PointConnexionDTO> getPointsConnexion(Integer idLiaison) {



        LiaisonFON liaisonFON= liaisonFONRepository.findById(idLiaison).orElseThrow(
                ()->new RessourceNotFoundException()
        );

        List<PointConnexion> liste = liaisonFON.getPointconnexions();
        List<PointConnexionDTO> liste1 =new ArrayList<PointConnexionDTO>();
        liste.forEach(pointConnexion -> {
            PointConnexionDTO pointConnexionDTO = PointConnexionDTO.toDTO(pointConnexion);
            liste1.add(pointConnexionDTO);
        });
        
        return  liste1;
    }



    @Override
    public List<LiaisonFONDTO> findLiaisonsOfLocation(Integer identifiant) {
        return this.liaisonFONRepository.findAllByLocationfonId(identifiant).stream().map(LiaisonFONDTO::toDTO).toList();
    }







}
