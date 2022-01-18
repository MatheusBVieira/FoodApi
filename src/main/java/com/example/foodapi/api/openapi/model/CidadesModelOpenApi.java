package com.example.foodapi.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.example.foodapi.api.model.response.CidadeResponse;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApi {

	private CidadeEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("CidadesEmbeddedModel")
	@Data
	public class CidadeEmbeddedModelOpenApi {
		
		private List<CidadeResponse> cidades;
		
	}
	
}
