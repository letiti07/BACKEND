package com.stage.projet.repository;

import com.stage.projet.model.Utilisateur;
import com.stage.projet.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville,Integer> {
}
