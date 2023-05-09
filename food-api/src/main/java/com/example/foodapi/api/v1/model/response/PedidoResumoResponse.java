package com.example.foodapi.api.v1.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Setter
@Getter
public class PedidoResumoResponse extends RepresentationModel<PedidoResumoResponse> {

	@Schema(example = "04813f77-79b5-11ec-9a17-0242ac1b0002")
	private UUID codigo;

	@Schema(example = "298.90")
	private BigDecimal subtotal;

	@Schema(example = "10.00")
	private BigDecimal taxaFrete;

	@Schema(example = "308.90")
	private BigDecimal valorTotal;

	@Schema(example = "CRIADO")
	private String status;

	@Schema(example = "2019-12-01T20:34:04Z")
	private OffsetDateTime dataCriacao;
	
}
