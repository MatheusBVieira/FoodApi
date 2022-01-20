package com.example.foodapi.api.v1.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoRequest {

	@ApiModelProperty(example = "38400-000", required = true)
	@NotBlank
	private String cep;

	@ApiModelProperty(example = "Rua Floriano Peixoto", required = true)
	@NotBlank
	private String logradouro;

	@ApiModelProperty(example = "\"1500\"", required = true)
	@NotBlank
	private String numero;

	@ApiModelProperty(example = "Apto 901")
	private String complemento;

	@ApiModelProperty(example = "Centro", required = true)
	@NotBlank
	private String bairro;
	
}
