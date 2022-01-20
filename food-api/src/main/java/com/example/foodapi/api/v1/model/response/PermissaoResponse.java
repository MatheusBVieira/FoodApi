package com.example.foodapi.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permissoes")
@Setter
@Getter
public class PermissaoResponse extends RepresentationModel<PermissaoResponse> {
	
	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "CONSULTAR_COZINHAS")
	private String nome;

	@ApiModelProperty(example = "Permite consultar cozinhas")
	private String descricao;	
	
}
