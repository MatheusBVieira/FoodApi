package com.example.foodapi.api.model.response;

import java.math.BigDecimal;

import com.example.foodapi.api.model.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteResponse {

	@ApiModelProperty(example = "1")
	@JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
	private Long id;

	@ApiModelProperty(example = "Thai Gourmet")
	@JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
	private String nome;

	@ApiModelProperty(example = "12.00")
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaResponse cozinha;
	
	private Boolean ativo;
	private Boolean aberto;
	private EnderecoResponse endereco;
	
}
