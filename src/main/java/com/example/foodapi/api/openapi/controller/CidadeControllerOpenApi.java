package com.example.foodapi.api.openapi.controller;

import java.util.List;

import com.example.foodapi.api.exceptionhandler.Problem;
import com.example.foodapi.api.model.request.CidadeRequest;
import com.example.foodapi.api.model.response.CidadeResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation("Lista as cidades")
	List<CidadeResponse> listar();
	
	@ApiOperation("Busca uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	CidadeResponse buscar(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true)
			Long cidadeId);
	
	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada"),
	})
	CidadeResponse adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true)
			CidadeRequest cidadeRequest);
	
	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizada"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	CidadeResponse atualizar(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true) 
			Long cidadeId,
			
			@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
			CidadeRequest cidadeRequest);
	
	@ApiOperation("Exclui uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cidade excluída"),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma cidade", example = "1", required = true)
			Long cidadeId);
	
}
