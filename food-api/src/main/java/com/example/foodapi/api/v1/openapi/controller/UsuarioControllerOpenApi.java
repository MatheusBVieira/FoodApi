package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.example.foodapi.api.v1.model.request.SenhaRequest;
import com.example.foodapi.api.v1.model.request.UsuarioComSenhaRequest;
import com.example.foodapi.api.v1.model.request.UsuarioRequest;
import com.example.foodapi.api.v1.model.response.UsuarioResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface UsuarioControllerOpenApi {

	CollectionModel<UsuarioResponse> listar();

	UsuarioResponse buscar(Long usuarioId);

	UsuarioResponse adicionar(UsuarioComSenhaRequest usuarioRequest);

	UsuarioResponse atualizar(Long usuarioId, UsuarioRequest usuarioRequest);

	void alterarSenha(Long usuarioId, SenhaRequest senha);
}
