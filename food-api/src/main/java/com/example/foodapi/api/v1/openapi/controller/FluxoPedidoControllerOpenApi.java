package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface FluxoPedidoControllerOpenApi {

	ResponseEntity<Void> confirmar(String codigoPedido);

	ResponseEntity<Void> cancelar(String codigoPedido);

	ResponseEntity<Void> entregar(String codigoPedido);
}
