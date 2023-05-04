package com.example.foodapi.api.v1.model.request;

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
