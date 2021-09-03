package com.example.foodapi.api.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioRequest {

    @NotBlank
    private String nome;
    
    @NotBlank
    @Email
    private String email;
}   
