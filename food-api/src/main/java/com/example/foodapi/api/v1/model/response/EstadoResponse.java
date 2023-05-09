package com.example.foodapi.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@Setter
@Getter
public class EstadoResponse extends RepresentationModel<EstadoResponse> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Minas Gerais")
	private String nome;
	
}
