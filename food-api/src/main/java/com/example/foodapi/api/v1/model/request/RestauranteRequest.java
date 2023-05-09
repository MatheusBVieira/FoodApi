package com.example.foodapi.api.v1.model.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteRequest {
	
	@Schema(example = "Thai Gourmet")
	@NotBlank
	private String nome;

	@Schema(example = "12.00")
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private CozinhaIdRequest cozinha;
	
	@Valid
	@NotNull
	private EnderecoRequest endereco;

}
