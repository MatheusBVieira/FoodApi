package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.example.foodapi.api.v1.model.response.PermissaoResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface GrupoPermissaoControllerOpenApi {

	CollectionModel<PermissaoResponse> listar(Long grupoId);

	ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId);

	ResponseEntity<Void> associar(Long grupoId, Long permissaoId);
}
