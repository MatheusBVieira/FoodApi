package com.example.foodapi.api.model.response;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoResponse extends RepresentationModel<EstadoResponse> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Minas Gerais")
	private String nome;
	
}
