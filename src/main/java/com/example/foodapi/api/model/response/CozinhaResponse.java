package com.example.foodapi.api.model.response;

import com.example.foodapi.api.model.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaResponse {
	
	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	@JsonView(RestauranteView.Resumo.class)
	private String nome;

}
