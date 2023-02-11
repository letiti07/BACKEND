package com.stage.projet.service.impl;

import com.stage.projet.exception.RequeteFailed;
import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.model.Permission;
import com.stage.projet.model.Role;
import com.stage.projet.repository.PermissionRepository;
import com.stage.projet.repository.RoleRepository;
import com.stage.projet.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class RoleServiceimpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public RoleServiceimpl(RoleRepository roleRepository,PermissionRepository permissionRepository){
        this.roleRepository=roleRepository;
        this.permissionRepository=permissionRepository;}

    @Override
    public List<Role> findAll() {
       List<Role> liste=new ArrayList<>();
        roleRepository.findAll().forEach(liste::add);
        return liste;
    }

    @Override
    public Integer create(Role role) {
        return roleRepository.save(role).getId();
    }

    @Override
    public Role findById(Integer id) {
        if(roleRepository.findById(id).isPresent()){
            return roleRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void update(Integer identifiant, Role role) {
        role.setId(identifiant);
        log.info(String.valueOf(role));
        roleRepository.save(role);

    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void addPermission(Integer idRole, Integer idPermission) {

        Role role=roleRepository.findById(idRole).orElseThrow(
                ()->new RessourceNotFoundException()
        );

        Permission permission=permissionRepository.findById(idPermission).orElseThrow(
                ()->new RessourceNotFoundException()
        );

        Collection<Permission> list =role.getPermissions();
        //lever une exception si la permission existe deja dans la liste des permissions du role
        list.forEach(permission1 -> {
            if(permission==permission1){
                throw new RequeteFailed();
            }
        });
        list.add(permission);
        role.setPermissions(list);
        roleRepository.save(role);
    }

    @Override
    public void removePermission(Integer idRole, Integer idPermission) {
        Role role=roleRepository.findById(idRole).orElseThrow(
                ()->new RessourceNotFoundException()
        );

        Permission permission=permissionRepository.findById(idPermission).orElseThrow(
                ()->new RessourceNotFoundException()
        );



        Collection<Permission> list=role.getPermissions();
        //Si la permission existe bel et bien , on supprime
        boolean response=list.contains(permission);
        if(response){
            list.remove(permission);
            role.setPermissions(list);
            roleRepository.save(role);
        }
        else {
            throw new RequeteFailed();
        }

    }
}
