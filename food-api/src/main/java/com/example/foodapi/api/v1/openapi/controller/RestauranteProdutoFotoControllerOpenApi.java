package com.example.foodapi.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.example.foodapi.api.v1.model.request.FotoProdutoRequest;
import com.example.foodapi.api.v1.model.response.FotoProdutoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface RestauranteProdutoFotoControllerOpenApi {

	FotoProdutoResponse atualizarFoto(Long restauranteId, Long produtoId, FotoProdutoRequest fotoProdutoRequest,
			MultipartFile arquivo) throws IOException;

	void excluir(Long restauranteId, Long produtoId);

	@Operation(summary = "Busca a foto do produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = FotoProdutoResponse.class)),
					@Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
					@Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
			})
	})
	FotoProdutoResponse buscar(Long restauranteId, Long produtoId);

	@Operation(hidden = true)
	ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;
}
