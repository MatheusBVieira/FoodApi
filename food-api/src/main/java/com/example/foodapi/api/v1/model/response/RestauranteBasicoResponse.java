package com.example.foodapi.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteBasicoResponse extends RepresentationModel<RestauranteBasicoResponse> {

    @ApiModelProperty(example = "1")
    private Long id;
    
    @ApiModelProperty(example = "Thai Gourmet")
    private String nome;
    
    @ApiModelProperty(example = "12.00")
    private BigDecimal taxaFrete;
    
    private CozinhaResponse cozinha;
}
