package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.example.foodapi.api.v1.model.request.EstadoRequest;
import com.example.foodapi.api.v1.model.response.EstadoResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface EstadoControllerOpenApi {

	CollectionModel<EstadoResponse> listar();

	EstadoResponse buscar(Long estadoId);

	EstadoResponse adicionar(EstadoRequest estadoRequest);

	EstadoResponse atualizar(Long estadoId,EstadoRequest estadoRequest);

	void remover(Long estadoId);
}
