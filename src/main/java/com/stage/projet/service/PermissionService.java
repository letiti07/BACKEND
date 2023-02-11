package com.stage.projet.service;

import com.stage.projet.model.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    Permission findById(Integer id);

    Integer create(Permission permission);

    void update(Integer identifiant, Permission permission);

    void deleteById(Integer id);
}
