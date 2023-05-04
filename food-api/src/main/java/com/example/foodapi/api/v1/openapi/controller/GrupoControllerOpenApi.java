package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.example.foodapi.api.v1.model.request.GrupoRequest;
import com.example.foodapi.api.v1.model.response.GrupoResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface GrupoControllerOpenApi {

	CollectionModel<GrupoResponse> listar();

	GrupoResponse buscar(Long grupoId);

	GrupoResponse adicionar(GrupoRequest grupoRequest);

	GrupoResponse atualizar(Long grupoId, GrupoRequest grupoRequest);

	void remover(Long grupoId);

}
