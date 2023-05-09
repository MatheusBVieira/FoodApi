package com.example.foodapi.api.v1.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPedidoRequest {

	@Schema(example = "1")
	@NotNull
	private Long produtoId;

	@Schema(example = "2")
	@Min(1)
	@NotNull
	private Integer quantidade;

	@Schema(example = "Menos picante, por favor")
	private String observacao;
}  