package com.example.foodapi.api.v2.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.example.foodapi.api.v2.model.response.CidadeResponseV2;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("CidadesModel")
@Data
public class CidadesModelV2OpenApi {

    private CidadesEmbeddedModelOpenApi _embedded;
    private Links _links;
    
    @ApiModel("CidadesEmbeddedModel")
    @Data
    public class CidadesEmbeddedModelOpenApi {
        
        private List<CidadeResponseV2> cidades;
        
    }
    
}
