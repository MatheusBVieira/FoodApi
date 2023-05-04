package com.example.foodapi.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "produtos")
@Setter
@Getter
public class ProdutoResponse extends RepresentationModel<ProdutoResponse> {

	private Long id;

	private String nome;

	private String descricao;

	private BigDecimal preco;

	private Boolean ativo; 
}  
