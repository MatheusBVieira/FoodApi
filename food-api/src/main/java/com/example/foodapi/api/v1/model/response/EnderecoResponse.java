package com.example.foodapi.api.v1.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoResponse {

	@Schema(example = "38400-000")
	private String cep;

	@Schema(example = "Rua Floriano Peixoto")
	private String logradouro;

	@Schema(example = "\"1500\"")
	private String numero;

	@Schema(example = "Apto 901")
	private String complemento;

	@Schema(example = "Centro")
	private String bairro;
	
	private CidadeResumoResponse cidade;
}
