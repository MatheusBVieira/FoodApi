package com.example.foodapi.api.v1.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Setter
@Getter
public class PedidoResponse extends RepresentationModel<PedidoResponse> {

	@Schema(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
	private String codigo;

	@Schema(example = "298.90")
	private BigDecimal subtotal;

	@Schema(example = "10.00")
	private BigDecimal taxaFrete;

	@Schema(example = "308.90")
	private BigDecimal valorTotal;

	@Schema(example = "CRIADO")
	private String status;

	@Schema(example = "2022-12-01T20:34:04Z")
	private OffsetDateTime dataCriacao;

	@Schema(example = "2022-12-01T20:35:10Z")
	private OffsetDateTime dataConfirmacao;

	@Schema(example = "2022-12-01T20:55:30Z")
	private OffsetDateTime dataEntrega;

	@Schema(example = "2022-12-01T20:35:00Z")
	private OffsetDateTime dataCancelamento;
	
    private RestauranteApenasNomeResponse restaurante;
    private UsuarioResponse cliente;
    private FormaPagamentoResponse formaPagamento;
    private EnderecoResponse enderecoEntrega;
    private List<ItemPedidoResponse> itens;   
} 
