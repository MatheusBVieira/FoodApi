package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.example.foodapi.api.v1.model.response.GrupoResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface UsuarioGrupoControllerOpenApi {

	CollectionModel<GrupoResponse> listar(Long usuarioId);

	ResponseEntity<Void> desassociar(Long usuarioId, Long grupoId);

	ResponseEntity<Void> associar(Long usuarioId, Long grupoId);
}
