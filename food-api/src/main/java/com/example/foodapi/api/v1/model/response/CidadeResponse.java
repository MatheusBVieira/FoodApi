package com.example.foodapi.api.v1.model.response;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResponse extends RepresentationModel<CidadeResponse> {

	private Long id;
	
	private String nome;
	
	private EstadoResponse estado;
	
}
