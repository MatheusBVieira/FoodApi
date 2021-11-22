package com.example.foodapi.api.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioComSenhaRequest extends UsuarioRequest {

	@ApiModelProperty(example = "123", required = true)
	@NotBlank
	private String senha;
}