package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.AlgaLinks;
import com.example.foodapi.api.controller.GrupoController;
import com.example.foodapi.api.model.response.GrupoResponse;
import com.example.foodapi.domain.model.Grupo;

@Component
public class GrupoResponseAssembler 
        extends RepresentationModelAssemblerSupport<Grupo, GrupoResponse> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    public GrupoResponseAssembler() {
        super(GrupoController.class, GrupoResponse.class);
    }
    
    @Override
    public GrupoResponse toModel(Grupo grupo) {
    	GrupoResponse grupoModel = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoModel);
        
        grupoModel.add(algaLinks.linkToGrupos("grupos"));
        
        grupoModel.add(algaLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
        
        return grupoModel;
    }
    
    @Override
    public CollectionModel<GrupoResponse> toCollectionModel(Iterable<? extends Grupo> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToGrupos());
    }            
}
