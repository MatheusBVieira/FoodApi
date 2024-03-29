package com.example.foodapi.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "grupos")
@Setter
@Getter
public class GrupoResponse extends RepresentationModel<GrupoResponse> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Gerente")
	private String nome;
    
}   