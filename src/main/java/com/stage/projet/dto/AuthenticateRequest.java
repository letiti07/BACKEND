package com.stage.projet.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class AuthenticateRequest {

    private String login;

    private String password;



}
