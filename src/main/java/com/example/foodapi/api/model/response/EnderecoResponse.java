package com.example.foodapi.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoResponse {

	@ApiModelProperty(example = "38400-000")
	private String cep;

	@ApiModelProperty(example = "Rua Floriano Peixoto")
	private String logradouro;

	@ApiModelProperty(example = "\"1500\"")
	private String numero;

	@ApiModelProperty(example = "Apto 901")
	private String complemento;

	@ApiModelProperty(example = "Centro")
	private String bairro;
	
	private CidadeResumoResponse cidade;
	
}
