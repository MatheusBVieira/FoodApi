package com.example.foodapi.api.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioRequest {

	@ApiModelProperty(example = "João da Silva", required = true)
	@NotBlank
	private String nome;

	@ApiModelProperty(example = "joao.ger@algafood.com.br", required = true)
	@NotBlank
	@Email
	private String email;
	
}   
