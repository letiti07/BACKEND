package com.stage.projet.controller;

import com.stage.projet.controller.api.UtilisateurApi;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.model.Utilisateur;

import com.stage.projet.service.UtilisateurService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurService utilisateurService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService=utilisateurService;
    }


    public List<Utilisateur> findAll(){
        return utilisateurService.findAll();
    }


    public Utilisateur findById( Integer id){

        Utilisateur reponse= utilisateurService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    public void deleteById( Integer id){

        utilisateurService.deleteById(id);

    }


    public Integer create( Utilisateur utilisateur){

       /* String pwd= passwordEncoder.encode(utilisateur.getMotdepasse());
        utilisateur.setMotdepasse(pwd);

        */
        return this.utilisateurService.create(utilisateur);
    }


    public void update( Integer identifiant,Utilisateur utilisateur) {

        if(utilisateurService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
            utilisateurService.update(identifiant,utilisateur);
        log.info("Utilisateur id: " + identifiant + " modifié");

    }

    @Override
    public void addRole(Integer idUtilisateur, Integer idRole) {

        utilisateurService.addRole(idUtilisateur, idRole);
    }

    @Override
    public void removeRole(Integer idUtilisateur, Integer idRole) {

        utilisateurService.removeRole(idUtilisateur, idRole);

    }


}
