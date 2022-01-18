package com.example.foodapi.api.v1.openapi.controller;

import java.math.BigDecimal;

import com.example.foodapi.api.v1.model.response.CozinhaResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("RestauranteBasicoModel")
@Setter
@Getter
public class RestauranteBasicoModelOpenApi {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Thai Gourmet")
	private String nome;
	
	@ApiModelProperty(example = "12.00")
	private BigDecimal taxaFrete;
	
	private CozinhaResponse cozinha;
	
}
