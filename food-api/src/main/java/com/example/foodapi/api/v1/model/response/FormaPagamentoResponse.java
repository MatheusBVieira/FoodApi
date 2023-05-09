package com.example.foodapi.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "formasPagamento")
@Setter
@Getter
public class FormaPagamentoResponse extends RepresentationModel<FormaPagamentoResponse> {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Cartão de crédito")
	private String descricao;
	
}
