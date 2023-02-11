package com.stage.projet.dto;

import com.stage.projet.model.Utilisateur;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
public class AuthenticateResponse {

    private String access_token;

    private Utilisateur utilisateur;
}
