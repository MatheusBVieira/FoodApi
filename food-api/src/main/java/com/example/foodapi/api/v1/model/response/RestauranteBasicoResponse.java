package com.example.foodapi.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteBasicoResponse extends RepresentationModel<RestauranteBasicoResponse> {

    private Long id;
    
    private String nome;
    
    private BigDecimal taxaFrete;
    
    private CozinhaResponse cozinha;
}
