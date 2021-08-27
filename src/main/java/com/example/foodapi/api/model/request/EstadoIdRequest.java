package com.example.foodapi.api.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoIdRequest {

	@NotNull
	private Long id;
	
}
