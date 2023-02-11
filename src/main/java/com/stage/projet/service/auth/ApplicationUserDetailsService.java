package com.stage.projet.service.auth;

import com.stage.projet.exception.RessourceNotFoundException;
import com.stage.projet.model.ExtendedUser;
import com.stage.projet.model.Utilisateur;
import com.stage.projet.repository.UtilisateurRepository;
import com.stage.projet.service.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurService service;

    public   Utilisateur utilisateur;

    public ApplicationUserDetailsService(UtilisateurService service) {
        this.service = service;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

         this.utilisateur=service.findUtilisateurByEmail(email).orElseThrow(
                ()->new RessourceNotFoundException());

        Set<SimpleGrantedAuthority> authorities=new HashSet<>();

        utilisateur.getRoles().forEach(role -> {
            role.getPermissions().forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission.getNom()));
            });
        });
        log.info(authorities.toString());


        return new ExtendedUser(utilisateur.getEmail(),utilisateur.getMotdepasse(),utilisateur.isActive(),
                true,true,true,authorities);
    }
}
