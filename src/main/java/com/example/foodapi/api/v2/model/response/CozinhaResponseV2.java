package com.example.foodapi.api.v2.model.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cozinhas")
@Setter
@Getter
public class CozinhaResponseV2 extends RepresentationModel<CozinhaResponseV2> {

    @ApiModelProperty(example = "1")
    private Long idCozinha;
    
    @ApiModelProperty(example = "Brasileira")
    private String nomeCozinha;
    
} 
