package com.stage.projet.repository;

import com.stage.projet.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {


    Optional<Utilisateur> findUtilisateurByEmail(String email);


}
