package com.stage.projet.controller;


import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.model.Permission;
import com.stage.projet.model.Role;
import com.stage.projet.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_PERMISSIONS;

@CrossOrigin("*")
@RestController
@RequestMapping(ENDPOINT_PERMISSIONS)
public class PermissionController {


    private PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public List<Permission> findAll(){
        return permissionService.findAll();
    }

    @GetMapping("/{id}")
    public Permission findById(@PathVariable("id") Integer id){
        return permissionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer create(@RequestBody Permission permission){

        return this.permissionService.create(permission);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody Permission permission) {
        if(permissionService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        permissionService.update(identifiant,permission);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        permissionService.deleteById(id);

    }
}
