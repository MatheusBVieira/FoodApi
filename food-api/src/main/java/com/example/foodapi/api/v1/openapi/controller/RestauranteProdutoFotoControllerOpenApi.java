package com.example.foodapi.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.example.foodapi.api.v1.model.request.FotoProdutoRequest;
import com.example.foodapi.api.v1.model.response.FotoProdutoResponse;

public interface RestauranteProdutoFotoControllerOpenApi {

	FotoProdutoResponse atualizarFoto(Long restauranteId, Long produtoId, FotoProdutoRequest fotoProdutoRequest,
			MultipartFile arquivo) throws IOException;

	void excluir(Long restauranteId, Long produtoId);

	FotoProdutoResponse buscar(Long restauranteId, Long produtoId);

	ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;
}
