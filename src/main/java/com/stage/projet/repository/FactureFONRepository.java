package com.stage.projet.repository;

import com.stage.projet.model.FactureFON;
import com.stage.projet.model.LiaisonFON;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FactureFONRepository extends JpaRepository<FactureFON,Integer> {
    List<FactureFON> findAllByLocationfonId(Integer id);

    List<FactureFON> findAllByLocationseId(Integer id);
}
