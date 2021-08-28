package com.example.foodapi.api.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormaPagamentoRequest {
	
	@NotBlank
    private String descricao;

}
