package com.example.foodapi.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.example.foodapi.api.v1.model.response.PedidoResumoResponse;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PedidosResumoModel")
@Getter
@Setter
public class PedidosResumoModelOpenApi {
    
    private PedidosResumoEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;
    
    @ApiModel("PedidosResumoEmbeddedModel")
    @Data
    public class PedidosResumoEmbeddedModelOpenApi {
        
        private List<PedidoResumoResponse> pedidos;
        
    }   
}  
