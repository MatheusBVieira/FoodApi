package com.example.foodapi.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.example.foodapi.api.v1.model.response.FormaPagamentoResponse;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("FormasPagamentoModel")
@Data
public class FormasPagamentoModelOpenApi {

    private FormasPagamentoEmbeddedModelOpenApi _embedded;
    private Links _links;
    
    @ApiModel("FormasPagamentoEmbeddedModel")
    @Data
    public class FormasPagamentoEmbeddedModelOpenApi {
        
        private List<FormaPagamentoResponse> formasPagamento;
        
    }   
}
