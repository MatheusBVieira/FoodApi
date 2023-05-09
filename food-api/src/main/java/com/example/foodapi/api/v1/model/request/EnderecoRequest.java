package com.example.foodapi.api.v1.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoRequest {

	@Schema(example = "38400-000")
	@NotBlank
	private String cep;

	@Schema(example = "Rua Floriano Peixoto")
	@NotBlank
	private String logradouro;

	@Schema(example = "\"1500\"")
	@NotBlank
	private String numero;

	@Schema(example = "Apto 901")
	private String complemento;

	@Schema(example = "Centro")
	@NotBlank
	private String bairro;
	
}
