package com.stage.projet.service.impl;

import com.stage.projet.model.Permission;
import com.stage.projet.repository.PermissionRepository;
import com.stage.projet.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceimpl implements PermissionService {

    private PermissionRepository permissionRepository;

    public PermissionServiceimpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> findAll() {
        List<Permission> liste=new ArrayList<>();
        permissionRepository.findAll().forEach(liste::add);
        return liste;
    }

    @Override
    public Permission findById(Integer id) {
        return permissionRepository.findById(id).get();
    }

    @Override
    public Integer create(Permission permission) {
        return permissionRepository.save(permission).getId();
    }

    @Override
    public void update(Integer identifiant, Permission permission) {
        permission.setId(identifiant);
        permissionRepository.save(permission);
    }

    @Override
    public void deleteById(Integer id) {
        permissionRepository.deleteById(id);
    }
}
