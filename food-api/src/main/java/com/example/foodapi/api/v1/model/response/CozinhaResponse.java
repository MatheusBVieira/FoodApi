package com.example.foodapi.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cozinhas")
@Setter
@Getter
public class CozinhaResponse extends RepresentationModel<CozinhaResponse> {
	
//	@JsonView(RestauranteView.Resumo.class)
	private Long id;

//	@JsonView(RestauranteView.Resumo.class)
	private String nome;

}
