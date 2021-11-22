package com.example.foodapi.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GrupoResponse {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Gerente")
	private String nome;
    
}   