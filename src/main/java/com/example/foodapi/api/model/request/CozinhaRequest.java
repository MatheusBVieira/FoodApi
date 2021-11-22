package com.example.foodapi.api.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaRequest {

	@ApiModelProperty(example = "Brasileira", required = true)
	@NotBlank
	private String nome;
	
}
