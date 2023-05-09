package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.example.foodapi.api.v1.model.request.GrupoRequest;
import com.example.foodapi.api.v1.model.response.GrupoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface GrupoControllerOpenApi {

	@Operation(summary = "Lista os grupos")
	CollectionModel<GrupoResponse> listar();

	@Operation(summary = "Busca um grupo por ID")
	GrupoResponse buscar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

	@Operation(summary = "Cadastra um grupo")
	GrupoResponse adicionar(@RequestBody(description = "Representação de um novo grupo", required = true) GrupoRequest grupoRequest);

	@Operation(summary = "Atualiza um grupo por ID")
	GrupoResponse atualizar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId, 
			@RequestBody(description = "Representação de um grupo com os novos dados", required = true) GrupoRequest grupoRequest);

	@Operation(summary = "Exclui um grupo por ID")
	void remover(Long grupoId);

}
