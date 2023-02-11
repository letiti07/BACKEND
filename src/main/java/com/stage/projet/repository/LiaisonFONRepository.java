package com.stage.projet.repository;

import com.stage.projet.model.Demande;
import com.stage.projet.model.LiaisonFON;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.Integer;
import java.util.List;
import java.util.Optional;


public interface LiaisonFONRepository extends JpaRepository< LiaisonFON,Integer> {

    List<LiaisonFON> findAllByLocationfonId(Integer id);


}
