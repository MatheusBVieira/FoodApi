package com.example.foodapi.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "usuarios")
@Setter
@Getter
public class UsuarioResponse extends RepresentationModel<UsuarioResponse>{

	@Schema(example = "1")
	private Long id;

	@Schema(example = "João da Silva")
	private String nome;

	@Schema(example = "joao.ger@algafood.com.br")
	private String email;

}
