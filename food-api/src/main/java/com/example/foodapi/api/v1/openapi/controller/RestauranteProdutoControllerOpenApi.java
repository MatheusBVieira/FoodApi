package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.example.foodapi.api.v1.model.request.ProdutoRequest;
import com.example.foodapi.api.v1.model.response.ProdutoResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface RestauranteProdutoControllerOpenApi {

	CollectionModel<ProdutoResponse> listar(Long restauranteId, Boolean incluirInativos);

	ProdutoResponse buscar(Long restauranteId, Long produtoId);

	ProdutoResponse adicionar(Long restauranteId, ProdutoRequest produtoRequest);

	ProdutoResponse atualizar(Long restauranteId, Long produtoId, ProdutoRequest produtoRequest);
}
