package com.example.foodapi.api.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteApenasNomeResponse extends RepresentationModel<RestauranteApenasNomeResponse> {

    @ApiModelProperty(example = "1")
    private Long id;
    
    @ApiModelProperty(example = "Thai Gourmet")
    private String nome;
    
}   
