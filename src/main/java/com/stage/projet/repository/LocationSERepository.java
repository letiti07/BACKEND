package com.stage.projet.repository;

import com.stage.projet.model.LocationSE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationSERepository extends JpaRepository<LocationSE,Integer> {
}
