package com.stage.projet.repository;

import com.stage.projet.model.Demandeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeurRepository extends JpaRepository<Demandeur,Integer> {
}
