package com.example.foodapi.api.v1.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormaPagamentoRequest {
	
	@NotBlank
	@Schema(example = "Cartão de crédito")
    private String descricao;

}
