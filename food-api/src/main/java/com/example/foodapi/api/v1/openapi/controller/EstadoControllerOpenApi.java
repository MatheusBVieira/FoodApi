package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.example.foodapi.api.v1.model.request.EstadoRequest;
import com.example.foodapi.api.v1.model.response.EstadoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name="security_auth")
@Tag(name = "Estados")
public interface EstadoControllerOpenApi {

	@Operation(summary = "Lista os estados")
	CollectionModel<EstadoResponse> listar();

	@Operation(summary = "Busca um estado por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID do estado inválido", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
			@ApiResponse(responseCode = "404", description = "Estado não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) })
	})
	EstadoResponse buscar(@Parameter(description = "ID de um estado", example = "1", required = true) Long estadoId);

	@Operation(summary = "Cadastra um estado", responses = {
			@ApiResponse(responseCode = "201", description = "Estado cadastrado")
	})
	EstadoResponse adicionar(@RequestBody(description = "Representação de um novo estado", required = true) EstadoRequest estadoRequest);

	@Operation(summary = "Atualiza um estado por ID", responses = {
			@ApiResponse(responseCode = "200", description = "Estado atualizado"),
			@ApiResponse(responseCode = "404", description = "Estado não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) })
	})
	EstadoResponse atualizar(@Parameter(description = "ID de um estado", example = "1", required = true) Long estadoId,
			@RequestBody(description = "Representação de um estado com os novos dados", required = true) EstadoRequest estadoRequest);

	@Operation(summary = "Exclui um estado por ID", responses = {
			@ApiResponse(responseCode = "204", description = "Estado excluído"),
			@ApiResponse(responseCode = "404", description = "Estado não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) })
	})
	void remover(@Parameter(description = "ID de um estado", example = "1", required = true) Long estadoId);
}
