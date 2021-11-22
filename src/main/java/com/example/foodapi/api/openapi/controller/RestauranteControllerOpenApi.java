package com.example.foodapi.api.openapi.controller;

import java.util.List;

import com.example.foodapi.api.exceptionhandler.Problem;
import com.example.foodapi.api.model.RestauranteView;
import com.example.foodapi.api.model.request.RestauranteRequest;
import com.example.foodapi.api.model.response.RestauranteResponse;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

    @ApiOperation(value = "Lista restaurantes", response = RestauranteBasicoModelOpenApi.class)
    @ApiImplicitParams({
        @ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
                name = "projecao", paramType = "query", type = "string")
    })
    @JsonView(RestauranteView.Resumo.class)
    List<RestauranteResponse> listar();
    
    @ApiOperation(value = "Lista restaurantes", hidden = true)
    List<RestauranteResponse> listarApenasNomes();
    
    @ApiOperation("Busca um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    RestauranteResponse buscar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
            Long restauranteId);
    
    @ApiOperation("Cadastra um restaurante")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Restaurante cadastrado"),
    })
    RestauranteResponse adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo restaurante", required = true)
            RestauranteRequest restauranteRequest);
    
    @ApiOperation("Atualiza um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Restaurante atualizado"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    RestauranteResponse atualizar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
            Long restauranteId,
            
            @ApiParam(name = "corpo", value = "Representação de um restaurante com os novos dados", 
                required = true)
            RestauranteRequest restauranteRequest);
    
    @ApiOperation("Ativa um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void ativar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
            Long restauranteId);
    
    @ApiOperation("Inativa um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void inativar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
            Long restauranteId);
    
    @ApiOperation("Ativa múltiplos restaurantes")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
    })
    void ativarMultiplos(
            @ApiParam(name = "corpo", value = "IDs de restaurantes", required = true)
            List<Long> restauranteIds);
    
    @ApiOperation("Inativa múltiplos restaurantes")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
    })
    void inativarMultiplos(
            @ApiParam(name = "corpo", value = "IDs de restaurantes", required = true)
            List<Long> restauranteIds);

    @ApiOperation("Abre um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void abrir(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
            Long restauranteId);
    
    @ApiOperation("Fecha um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    void fechar(
            @ApiParam(value = "ID de um restaurante", example = "1", required = true)
            Long restauranteId);

}
