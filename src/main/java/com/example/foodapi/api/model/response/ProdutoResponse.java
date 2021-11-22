package com.example.foodapi.api.model.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoResponse {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Espetinho de Cupim")
	private String nome;

	@ApiModelProperty(example = "Acompanha farinha, mandioca e vinagrete")
	private String descricao;

	@ApiModelProperty(example = "12.50")
	private BigDecimal preco;

	@ApiModelProperty(example = "true")
	private Boolean ativo; 
}  
