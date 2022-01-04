package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.AlgaLinks;
import com.example.foodapi.api.model.response.PermissaoResponse;
import com.example.foodapi.domain.model.Permissao;

@Component
public class PermissaoResponseAssembler implements RepresentationModelAssembler<Permissao, PermissaoResponse> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;

    @Override
    public PermissaoResponse toModel(Permissao permissao) {
    	PermissaoResponse permissaoModel = modelMapper.map(permissao, PermissaoResponse.class);
        return permissaoModel;
    }
    
    @Override
    public CollectionModel<PermissaoResponse> toCollectionModel(Iterable<? extends Permissao> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(algaLinks.linkToPermissoes());
    }   
}