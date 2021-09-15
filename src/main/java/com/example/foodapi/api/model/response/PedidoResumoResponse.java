package com.example.foodapi.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Getter;
import lombok.Setter;

@JsonFilter("pedidoFilter")
@Setter
@Getter
public class PedidoResumoResponse {

	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String status;
	private OffsetDateTime dataCriacao;
	private RestauranteResumoResponse restaurante;
	private UsuarioResponse cliente;
	
}
