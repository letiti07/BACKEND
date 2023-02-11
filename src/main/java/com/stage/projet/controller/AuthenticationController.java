package com.stage.projet.controller;

import com.stage.projet.dto.AuthenticateRequest;
import com.stage.projet.dto.AuthenticateResponse;
import com.stage.projet.model.ExtendedUser;
import com.stage.projet.service.auth.ApplicationUserDetailsService;
import com.stage.projet.utils.JwUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.stage.projet.utils.Constants.ENDPOINT_AUTH;

@RestController
@CrossOrigin("*")
@RequestMapping(ENDPOINT_AUTH)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Autowired
    private JwUtil jwUtil;



    public AuthenticationController(AuthenticationManager authenticationManager, ApplicationUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request){
      authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails=userDetailsService.loadUserByUsername(request.getLogin());

        final String jwt= jwUtil.generateToken((ExtendedUser) userDetails);

        return ResponseEntity.ok(AuthenticateResponse.builder().access_token(jwt).utilisateur(userDetailsService.utilisateur).build());
    }
}
