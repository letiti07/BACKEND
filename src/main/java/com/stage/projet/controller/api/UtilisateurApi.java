package com.stage.projet.controller.api;

import com.stage.projet.model.Role;
import com.stage.projet.model.Utilisateur;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static com.stage.projet.utils.Constants.ENDPOINT_UTILISATEURS;


@CrossOrigin(origins = "*")
@RequestMapping(value = ENDPOINT_UTILISATEURS)

public interface UtilisateurApi {


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Utilisateur> findAll();


    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Utilisateur findById(@PathVariable("id") Integer id);


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id);

   /* @PreAuthorize("hasAuthority('U01')") */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer create(@RequestBody Utilisateur utilisateur);



    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("id") Integer identifiant, @RequestBody Utilisateur utilisateur) ;


    @GetMapping(value = "/{idUtilisateur}/addRole/{idRole}")
    public void addRole(@PathVariable("idUtilisateur") Integer idUtilisateur, @PathVariable("idRole") Integer idRole);


    @GetMapping("/{idUtilisateur}/removeRole/{idRole}")
    public void removeRole(@PathVariable("idUtilisateur") Integer idUtilisateur, @PathVariable("idRole") Integer idRole);


    }
