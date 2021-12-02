package com.example.foodapi.api.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cozinhas")
@Setter
@Getter
public class CozinhaResponse extends RepresentationModel<CozinhaResponse> {
	
	@ApiModelProperty(example = "1")
//	@JsonView(RestauranteView.Resumo.class)
	private Long id;

	@ApiModelProperty(example = "Brasileira")
//	@JsonView(RestauranteView.Resumo.class)
	private String nome;

}
