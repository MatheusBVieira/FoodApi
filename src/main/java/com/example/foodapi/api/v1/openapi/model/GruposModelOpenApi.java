package com.example.foodapi.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.example.foodapi.api.v1.model.response.GrupoResponse;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("GruposModel")
@Data
public class GruposModelOpenApi {

    private GruposEmbeddedModelOpenApi _embedded;
    private Links _links;
    
    @ApiModel("GruposEmbeddedModel")
    @Data
    public class GruposEmbeddedModelOpenApi {
        
        private List<GrupoResponse> grupos;
        
    }   
}
