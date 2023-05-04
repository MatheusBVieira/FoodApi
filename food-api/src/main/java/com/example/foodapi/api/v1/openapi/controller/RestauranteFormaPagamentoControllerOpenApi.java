package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.example.foodapi.api.v1.model.response.FormaPagamentoResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface RestauranteFormaPagamentoControllerOpenApi {

	CollectionModel<FormaPagamentoResponse> listar(Long restauranteId);

	ResponseEntity<Void> desassociar(Long restauranteId, Long formaPagamentoId);

	ResponseEntity<Void> associar(Long restauranteId, Long formaPagamentoId);
}
