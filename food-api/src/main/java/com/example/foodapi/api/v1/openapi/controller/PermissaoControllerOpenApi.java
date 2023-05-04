package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.example.foodapi.api.v1.model.response.PermissaoResponse;

public interface PermissaoControllerOpenApi {

    CollectionModel<PermissaoResponse> listar();
    
}
