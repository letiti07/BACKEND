package com.stage.projet.service;


import com.stage.projet.model.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findAll();

    Integer create(Role role);

    Role findById(Integer id);

    void update(Integer identifiant, Role role);

    void deleteById(Integer id);

    void addPermission(Integer idRole, Integer idPermission);

    void removePermission(Integer idRole, Integer idPermission);
}
