package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.example.foodapi.api.v1.model.request.CozinhaRequest;
import com.example.foodapi.api.v1.model.response.CozinhaResponse;
import com.example.foodapi.core.springdoc.PageableParameter;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface CozinhaControllerOpenApi {

	@PageableParameter
	PagedModel<CozinhaResponse> listar(@Parameter(hidden = true) Pageable pageable);

	CozinhaResponse buscar(Long cozinhaId);

	CozinhaResponse adicionar(CozinhaRequest cozinhaRequest);

	CozinhaResponse atualizar(Long cozinhaId, CozinhaRequest cozinhaRequest);

	void remover(Long cozinhaId);
}
