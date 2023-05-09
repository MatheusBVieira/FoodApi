package com.example.foodapi.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cozinhas")
@Setter
@Getter
public class CozinhaResponse extends RepresentationModel<CozinhaResponse> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Brasileira")
	private String nome;

}
