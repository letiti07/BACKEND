package com.stage.projet.repository;

import com.stage.projet.dto.DemandeDTO;
import com.stage.projet.model.Demande;
import com.stage.projet.model.LocationFON;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationFONRepository extends JpaRepository<LocationFON,Integer> {

    @Query(value = "select id_demande from locationfon where id=:idLocationfon",nativeQuery = true)
    Integer findIdDemandeOfLocationFon(@Param("idLocationfon") Integer idLocation);
}
