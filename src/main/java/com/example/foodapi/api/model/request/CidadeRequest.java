package com.example.foodapi.api.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeRequest {

	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIdRequest estado;
	
}
