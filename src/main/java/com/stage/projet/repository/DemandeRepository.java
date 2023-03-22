package com.stage.projet.repository;

import com.stage.projet.dto.DemandeDTO;
import com.stage.projet.model.Demande;
import com.stage.projet.model.LiaisonFON;
import com.stage.projet.model.LocationFON;
import com.stage.projet.model.Utilisateur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface DemandeRepository extends JpaRepository<Demande,Integer> {


     @Query(value = "select * from demande where objet= :objet and etat= :etat",nativeQuery = true)
     List<Demande> findAllByObjetAndEtat(@Param("objet") String objet,@Param("etat") String etat);


}
