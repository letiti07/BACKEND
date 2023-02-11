package com.stage.projet.controller;

import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.model.Role;
import com.stage.projet.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_ROLES;


@RestController
@RequestMapping(ENDPOINT_ROLES)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {

    private RoleService roleService ;

    public  RoleController(RoleService roleService){ this.roleService=roleService;}


    @GetMapping
    public List<Role> findAll(){
        return roleService.findAll();
    }


    @GetMapping("/{id}")
    public Role findById(@PathVariable("id") Integer id){

        Role reponse= roleService.findById(id);
        if(reponse==null){
            throw new RessourceNotFoundException();
        }
        return reponse;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer create(@RequestBody Role role){

        return this.roleService.create(role);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody Role role) {
        if(roleService.findById(identifiant) == null) {
            throw new RessourceNotFoundException();
        }
        roleService.update(identifiant,role);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        roleService.deleteById(id);

    }

    @GetMapping(value = "/{idRole}/addPermission/{idPermission}")
    public void addPermission(@PathVariable("idRole") Integer idRole,@PathVariable("idPermission") Integer idPermission){
        roleService.addPermission(idRole,idPermission);
    }

    @GetMapping(value = "/{idRole}/removePermission/{idPermission}")
    public void removePermission(@PathVariable("idRole") Integer idRole,@PathVariable("idPermission") Integer idPermission){
        roleService.removePermission(idRole,idPermission);
    }


}
