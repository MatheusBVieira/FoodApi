package com.example.foodapi.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.controller.UsuarioController;
import com.example.foodapi.api.controller.UsuarioGrupoController;
import com.example.foodapi.api.model.response.UsuarioResponse;
import com.example.foodapi.domain.model.Usuario;

@Component
public class UsuarioResponseAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioResponse>{

    @Autowired
    private ModelMapper modelMapper;
    
    public UsuarioResponseAssembler() {
        super(UsuarioController.class, UsuarioResponse.class);
    }
    
    @Override
    public UsuarioResponse toModel(Usuario usuario) {
    	UsuarioResponse usuarioModel = createModelWithId(usuario.getId(), usuario);
         modelMapper.map(usuario, usuarioModel);
         
         usuarioModel.add(linkTo(UsuarioController.class).withRel("usuarios"));
         
         usuarioModel.add(linkTo(methodOn(UsuarioGrupoController.class)
                 .listar(usuario.getId())).withRel("grupos-usuario"));
         
         return usuarioModel;
    }
    
    @Override
    public CollectionModel<UsuarioResponse> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities)
            .add(linkTo(UsuarioController.class).withSelfRel());
    }            
} 
