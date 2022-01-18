package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.example.foodapi.api.exceptionhandler.Problem;
import com.example.foodapi.api.v1.model.request.CozinhaRequest;
import com.example.foodapi.api.v1.model.response.CozinhaResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @ApiOperation("Lista as cozinhas com paginação")
    PagedModel<CozinhaResponse> listar(Pageable pageable);
    
    @ApiOperation("Busca uma cozinha por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    CozinhaResponse buscar(
    		@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
    		Long cozinhaId);
    
    @ApiOperation("Cadastra uma cozinha")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Cozinha cadastrada"),
    })
    CozinhaResponse adicionar(
    		@ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true)
            CozinhaRequest cozinhaRequest);
    
    @ApiOperation("Atualiza uma cozinha por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Cozinha atualizada"),
        @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    CozinhaResponse atualizar(
    		@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
            Long cozinhaId,
            
            @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados")
            CozinhaRequest cozinhaRequest);
    
    @ApiOperation("Exclui uma cozinha por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Cozinha excluída"),
        @ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
    })
    void remover(
    		@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
            Long cozinhaId);   
}
