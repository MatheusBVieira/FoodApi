package com.example.foodapi.api.v1.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeRequest {

	@Schema(example = "Uberlândia")
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIdRequest estado;
	
}
