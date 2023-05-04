package com.example.foodapi.api.v1.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Setter
@Getter
public class PedidoResumoResponse extends RepresentationModel<PedidoResumoResponse> {

	private String codigo;

	private BigDecimal subtotal;

	private BigDecimal taxaFrete;

	private BigDecimal valorTotal;

	private String status;

	private OffsetDateTime dataCriacao;
	
	private RestauranteApenasNomeResponse restaurante;
	private UsuarioResponse cliente;
	
}
