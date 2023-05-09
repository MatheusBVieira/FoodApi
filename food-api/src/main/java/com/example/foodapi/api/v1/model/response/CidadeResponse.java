package com.example.foodapi.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeResponse extends RepresentationModel<CidadeResponse> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Uberlândia")
	private String nome;
	
	private EstadoResponse estado;
	
}
