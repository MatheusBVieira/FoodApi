package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.example.foodapi.api.v1.model.request.CidadeRequest;
import com.example.foodapi.api.v1.model.response.CidadeResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name="security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Lista as cidades")
	CollectionModel<CidadeResponse> listar();
	
	@Operation(summary = "Busca uma cidade por Id")
	CidadeResponse buscar(Long cidadeId);
	
	@Operation(summary = "Cadastra uma cidade", description = "Cadastro de uma cidade, " +
			"necessita de um estado e um nome válido")
	CidadeResponse adicionar(CidadeRequest cidadeRequest);

	@Operation(summary = "Atualizado uma cidade por ID")
	CidadeResponse atualizar(Long cidadeId,CidadeRequest cidadeRequest);
	
	@Operation(summary = "Excluir uma cidade por ID")
	void remover(Long cidadeId);
	
}
