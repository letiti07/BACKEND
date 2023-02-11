package com.stage.projet.repository;

import com.stage.projet.model.RecuPaiementFON;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecuPaiementRepository extends JpaRepository<RecuPaiementFON,Integer> {
}
