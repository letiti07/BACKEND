package com.stage.projet.service;

import com.stage.projet.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    public List<Utilisateur> findAll();

    public Utilisateur findById(Integer id);

    public Integer create(Utilisateur utilisateur);

    public void update(Integer identifiant,Utilisateur utilisateur);

    void deleteById(Integer id);

    Utilisateur findByEmail(String email);

    Optional<Utilisateur> findUtilisateurByEmail(String email);

    void addRole(Integer idUtilisateur, Integer idRole);

    void removeRole(Integer idUtilisateur, Integer idRole);
}
