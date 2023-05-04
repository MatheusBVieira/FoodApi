package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.example.foodapi.api.v1.model.request.CidadeRequest;
import com.example.foodapi.api.v1.model.response.CidadeResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name="security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	CollectionModel<CidadeResponse> listar();
	
	CidadeResponse buscar(Long cidadeId);
	
	CidadeResponse adicionar(CidadeRequest cidadeRequest);
	
	CidadeResponse atualizar(Long cidadeId,CidadeRequest cidadeRequest);
	
	void remover(Long cidadeId);
	
}
