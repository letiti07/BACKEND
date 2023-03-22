package com.stage.projet.repository;

import com.stage.projet.model.LiaisonFON;
import com.stage.projet.model.ZoneSE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZoneSERepository extends JpaRepository<ZoneSE,Integer> {

    List<ZoneSE> findAllByLocationseId(Integer id);
}
