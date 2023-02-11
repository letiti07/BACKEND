package com.stage.projet.repository;


import com.stage.projet.model.LiaisonFON;
import com.stage.projet.model.PointConnexion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointConnexionRepository extends JpaRepository<PointConnexion,Integer> {

    @Query(value = "select * from pointConnexion where idliaisonfon= :id",nativeQuery = true)
    List<PointConnexion> findPointsConnexionOfLiaison(@Param("id") Integer id);
    @Query(value = "select * from pointConnexion where idville= :id",nativeQuery = true)
    List<PointConnexion> findPointConnexionListByidVille(@Param("id") Integer id);
}
