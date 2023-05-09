package com.example.foodapi.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissoes")
@Setter
@Getter
public class PermissaoResponse extends RepresentationModel<PermissaoResponse> {
	
	@Schema(example = "1")
	private Long id;

	@Schema(example = "CONSULTAR_COZINHAS")
	private String nome;

	@Schema(example = "Permite consultar cozinhas")
	private String descricao;	
	
}
