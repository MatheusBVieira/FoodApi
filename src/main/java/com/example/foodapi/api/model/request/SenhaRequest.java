package com.example.foodapi.api.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SenhaRequest {
    
    @NotBlank
    private String senhaAtual;
    
    @NotBlank
    private String novaSenha;
} 
