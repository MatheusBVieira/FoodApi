package com.example.foodapi.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.example.foodapi.api.model.response.PermissaoResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

    @ApiOperation("Lista as permissões")
    CollectionModel<PermissaoResponse> listar();
    
}
