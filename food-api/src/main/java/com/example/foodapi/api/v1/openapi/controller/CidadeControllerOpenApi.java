package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.example.foodapi.api.v1.model.request.CidadeRequest;
import com.example.foodapi.api.v1.model.response.CidadeResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name="security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Lista as cidades")
	CollectionModel<CidadeResponse> listar();
	
	@Operation(summary = "Busca uma cidade por Id", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID da cidade inválido",
					content = @Content(schema = @Schema(ref = "Problema"))
			),
			@ApiResponse(responseCode = "404", description = "Cidade não encontrada",
			content = @Content(schema = @Schema(ref = "Problema"))
			)
	})
	CidadeResponse buscar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);
	
	@Operation(summary = "Cadastra uma cidade", description = "Cadastro de uma cidade, " +
			"necessita de um estado e um nome válido")
	CidadeResponse adicionar(@RequestBody(description = "Representação de uma nova cidade", required = true) CidadeRequest cidadeRequest);

	@Operation(summary = "Atualizado uma cidade por ID",
			responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID da cidade inválido",
					content = @Content(schema = @Schema(ref = "Problema"))
			),
			@ApiResponse(responseCode = "404", description = "Cidade não encontrada",
					content = @Content(schema = @Schema(ref = "Problema"))
			)
		})
	CidadeResponse atualizar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId,
			  @RequestBody(description = "Representação de uma cidade com dados atualizados", required = true) CidadeRequest cidadeRequest);
	
	@Operation(summary = "Excluir uma cidade por ID",responses = {
			@ApiResponse(responseCode = "204"),
			@ApiResponse(responseCode = "400", description = "ID da cidade inválido",
					content = @Content(schema = @Schema(ref = "Problema"))
			),
			@ApiResponse(responseCode = "404", description = "Cidade não encontrada",
					content = @Content(schema = @Schema(ref = "Problema"))
			)
	})
	void remover(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);
	
}
