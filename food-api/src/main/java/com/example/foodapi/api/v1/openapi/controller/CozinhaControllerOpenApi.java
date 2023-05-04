package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.example.foodapi.api.v1.model.request.CozinhaRequest;
import com.example.foodapi.api.v1.model.response.CozinhaResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface CozinhaControllerOpenApi {

	PagedModel<CozinhaResponse> listar(Pageable pageable);

	CozinhaResponse buscar(Long cozinhaId);

	CozinhaResponse adicionar(CozinhaRequest cozinhaRequest);

	CozinhaResponse atualizar(Long cozinhaId, CozinhaRequest cozinhaRequest);

	void remover(Long cozinhaId);
}
