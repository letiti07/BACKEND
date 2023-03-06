package com.stage.projet.repository;

import com.stage.projet.model.Virement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirementRepository extends JpaRepository<Virement,Integer> {
}
