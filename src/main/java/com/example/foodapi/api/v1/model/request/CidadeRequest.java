package com.example.foodapi.api.v1.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeRequest {

	@ApiModelProperty(example = "Uberlândia", required = true)
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private EstadoIdRequest estado;
	
}
