package com.stage.projet.repository;

import com.stage.projet.model.LocationSE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationSERepository extends JpaRepository<LocationSE,Integer> {

    @Query(value = "select id_demande from locationse where id=:idLocation",nativeQuery = true)
    Integer findIdDemandeOfLocationSe(@Param("idLocation") Integer idLocation);
}
