package com.example.foodapi.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoResponse extends RepresentationModel<ItemPedidoResponse> {

	private Long produtoId;

	private String produtoNome;

	private Integer quantidade;

	private BigDecimal precoUnitario;

	private BigDecimal precoTotal;

	private String observacao;
	
}    
