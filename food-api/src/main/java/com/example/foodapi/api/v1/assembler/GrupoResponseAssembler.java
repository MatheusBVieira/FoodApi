package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.controller.GrupoController;
import com.example.foodapi.api.v1.model.response.GrupoResponse;
import com.example.foodapi.core.security.AlgaSecurity;
import com.example.foodapi.domain.model.Grupo;

@Component
public class GrupoResponseAssembler 
        extends RepresentationModelAssemblerSupport<Grupo, GrupoResponse> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    @Autowired
    private AlgaSecurity algaSecurity;
    
    public GrupoResponseAssembler() {
        super(GrupoController.class, GrupoResponse.class);
    }
    
    @Override
    public GrupoResponse toModel(Grupo grupo) {
    	GrupoResponse grupoModel = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoModel);
        
        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            grupoModel.add(algaLinks.linkToGrupos("grupos"));
            
            grupoModel.add(algaLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
        }
        
        return grupoModel;
    }

    @Override
    public CollectionModel<GrupoResponse> toCollectionModel(Iterable<? extends Grupo> entities) {
        CollectionModel<GrupoResponse> collectionModel = super.toCollectionModel(entities);
        
        if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
            collectionModel.add(algaLinks.linkToGrupos());
        }
        
        return collectionModel;
    }           
}
