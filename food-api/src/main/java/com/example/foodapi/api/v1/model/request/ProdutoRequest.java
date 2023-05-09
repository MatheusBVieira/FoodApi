package com.example.foodapi.api.v1.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoRequest {

	@Schema(example = "Espetinho de Cupim")
	@NotBlank
	private String nome;

	@Schema(example = "Acompanha farinha, mandioca e vinagrete")
	@NotBlank
	private String descricao;

	@Schema(example = "12.50")
	@PositiveOrZero
	@NotNull
	private BigDecimal preco;

	@Schema(example = "true")
	@NotNull
	private Boolean ativo;
    
} 
