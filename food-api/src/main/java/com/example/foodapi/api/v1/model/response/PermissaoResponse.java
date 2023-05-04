package com.example.foodapi.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissoes")
@Setter
@Getter
public class PermissaoResponse extends RepresentationModel<PermissaoResponse> {
	
	private Long id;

	private String nome;

	private String descricao;	
	
}
