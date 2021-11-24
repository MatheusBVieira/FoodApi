package com.example.foodapi.api.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "formasPagamento")
@Setter
@Getter
public class FormaPagamentoResponse extends RepresentationModel<FormaPagamentoResponse> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Cartão de crédito")
	private String descricao;
	
}
