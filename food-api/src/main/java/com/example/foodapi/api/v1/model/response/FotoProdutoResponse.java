package com.example.foodapi.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "fotos")
@Setter
@Getter
public class FotoProdutoResponse extends RepresentationModel<FotoProdutoResponse> {

	private String nomeArquivo;

	private String descricao;

	private String contentType;

	private Long tamanho;  
	
}