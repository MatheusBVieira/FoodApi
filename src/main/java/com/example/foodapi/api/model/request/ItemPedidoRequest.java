package com.example.foodapi.api.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPedidoRequest {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long produtoId;

	@ApiModelProperty(example = "2", required = true)
	@NotNull
	@PositiveOrZero
	private Integer quantidade;

	@ApiModelProperty(example = "Menos picante, por favor")
	private String observacao;  
}  