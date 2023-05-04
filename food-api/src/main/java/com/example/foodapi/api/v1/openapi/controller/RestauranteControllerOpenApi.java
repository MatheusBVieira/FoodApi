package com.example.foodapi.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.example.foodapi.api.v1.model.request.RestauranteRequest;
import com.example.foodapi.api.v1.model.response.RestauranteApenasNomeResponse;
import com.example.foodapi.api.v1.model.response.RestauranteBasicoResponse;
import com.example.foodapi.api.v1.model.response.RestauranteResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name="security_auth")
public interface RestauranteControllerOpenApi {

	CollectionModel<RestauranteBasicoResponse> listar();

	CollectionModel<RestauranteApenasNomeResponse> listarApenasNomes();

	RestauranteResponse buscar(Long restauranteId);

	RestauranteResponse adicionar(RestauranteRequest restauranteRequest);

	RestauranteResponse atualizar(Long restauranteId, RestauranteRequest restauranteRequest);

	ResponseEntity<Void> ativar(Long restauranteId);

	ResponseEntity<Void> inativar(Long restauranteId);

	void ativarMultiplos(List<Long> restauranteIds);

	void inativarMultiplos(List<Long> restauranteIds);

	ResponseEntity<Void> abrir(Long restauranteId);

	ResponseEntity<Void> fechar(Long restauranteId);

}
