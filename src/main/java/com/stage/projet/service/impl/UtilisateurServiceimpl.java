package com.stage.projet.service.impl;

import com.stage.projet.exception.RequeteFailed;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.model.Permission;
import com.stage.projet.model.Role;
import com.stage.projet.model.Utilisateur;
import com.stage.projet.repository.RoleRepository;
import com.stage.projet.repository.UtilisateurRepository;
import com.stage.projet.service.UtilisateurService;
import com.sun.xml.bind.v2.runtime.output.Encoded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class UtilisateurServiceimpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    private  PasswordEncoder passwordEncoder;


    @Autowired
    private RoleRepository roleRepository;

    public UtilisateurServiceimpl(UtilisateurRepository utilisateurRepository,PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder=passwordEncoder;

    }


    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> liste = new ArrayList<Utilisateur>();
        utilisateurRepository.findAll().forEach(liste::add);
        return liste;
    }

    @Override
    public Utilisateur findById(Integer id) {
        if (utilisateurRepository.findById(id).isPresent()) {
            return utilisateurRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Integer create(Utilisateur utilisateur) {
        utilisateur.setMotdepasse(passwordEncoder.encode(utilisateur.getMotdepasse()));
        return utilisateurRepository.save(utilisateur).getId();


    }

    @Override
    public void update(Integer identifiant, Utilisateur utilisateur) {
        utilisateur.setMotdepasse(passwordEncoder.encode(utilisateur.getMotdepasse()));
        utilisateur.setId(identifiant);
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public void deleteById(Integer identifiant) {
        this.utilisateurRepository.deleteById(identifiant);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findUtilisateurByEmail(email).orElseThrow(
                () -> new EntityNotFoundException()
        );
    }

    @Override
    public Optional<Utilisateur> findUtilisateurByEmail(String email) {
        return utilisateurRepository.findUtilisateurByEmail(email);
    }

    //ajouter un role à l'utilisateur et modifier le role de l'utilisateur par un autre
    @Override
    public void addRole(Integer idUtilisateur, Integer idRole) {

        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur).orElseThrow(
                ()->new RessourceNotFoundException()
        );

        Role role = roleRepository.findById(idRole).orElseThrow(
                ()->new RessourceNotFoundException()
        );

            Collection<Role> list= utilisateur.getRoles();
            //lever une exception si le role existe deja
            list.forEach(role1 -> {
                if(role==role1){
                    throw new RequeteFailed();
                }
            });

            list.add(role);
             utilisateur.setRole(list);
            utilisateurRepository.save(utilisateur);


    }

    //Supprimer un role un utilisateur
    @Override
    public void removeRole(Integer idUtilisateur, Integer idRole) {

        Utilisateur utilisateur=utilisateurRepository.findById(idUtilisateur).orElseThrow(
                ()->new RessourceNotFoundException()
        );
        Role role=roleRepository.findById(idRole).orElseThrow(
                ()->new RessourceNotFoundException()
        );

        Collection<Role> list=utilisateur.getRoles();
        //Si le role existe bel et bien , on supprime
        boolean response=list.contains(role);
        if(response){
            list.remove(role);
            utilisateur.setRoles(list);
            utilisateurRepository.save(utilisateur);
        }
        else {
            throw new RequeteFailed();
        }

    }



}

