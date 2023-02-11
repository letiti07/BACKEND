package com.stage.projet.repository;

import com.stage.projet.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {
}
