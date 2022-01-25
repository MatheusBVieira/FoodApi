package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.model.response.PermissaoResponse;
import com.example.foodapi.core.security.AlgaSecurity;
import com.example.foodapi.domain.model.Permissao;

@Component
public class PermissaoResponseAssembler implements RepresentationModelAssembler<Permissao, PermissaoResponse> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    @Autowired
    private AlgaSecurity algaSecurity; 

    @Override
    public PermissaoResponse toModel(Permissao permissao) {
    	PermissaoResponse permissaoModel = modelMapper.map(permissao, PermissaoResponse.class);
        return permissaoModel;
    }
    
    @Override
    public CollectionModel<PermissaoResponse> toCollectionModel(Iterable<? extends Permissao> entities) {
        CollectionModel<PermissaoResponse> collectionModel 
            = RepresentationModelAssembler.super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            collectionModel.add(algaLinks.linkToPermissoes());
        }
        
        return collectionModel;            
    }  
}