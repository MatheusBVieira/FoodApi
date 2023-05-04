package com.example.foodapi.api.v1.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Setter
@Getter
public class PedidoResponse extends RepresentationModel<PedidoResponse> {

	private String codigo;

	private BigDecimal subtotal;

	private BigDecimal taxaFrete;

	private BigDecimal valorTotal;

	private String status;

	private OffsetDateTime dataCriacao;

	private OffsetDateTime dataConfirmacao;

	private OffsetDateTime dataEntrega;

	private OffsetDateTime dataCancelamento;
	
    private RestauranteApenasNomeResponse restaurante;
    private UsuarioResponse cliente;
    private FormaPagamentoResponse formaPagamento;
    private EnderecoResponse enderecoEntrega;
    private List<ItemPedidoResponse> itens;   
} 
