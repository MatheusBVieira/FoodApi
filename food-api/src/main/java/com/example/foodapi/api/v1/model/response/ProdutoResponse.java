package com.example.foodapi.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "produtos")
@Setter
@Getter
public class ProdutoResponse extends RepresentationModel<ProdutoResponse> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Espetinho de Cupim")
	private String nome;

	@Schema(example = "Acompanha farinha, mandioca e vinagrete")
	private String descricao;

	@Schema(example = "12.50")
	private BigDecimal preco;

	@Schema(example = "true")
	private Boolean ativo;
}  
