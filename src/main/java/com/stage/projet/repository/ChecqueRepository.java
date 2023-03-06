package com.stage.projet.repository;

import com.stage.projet.model.Checque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecqueRepository  extends JpaRepository<Checque,Integer> {
}
