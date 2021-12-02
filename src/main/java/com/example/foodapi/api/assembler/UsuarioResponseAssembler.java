package com.example.foodapi.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.AlgaLinks;
import com.example.foodapi.api.controller.UsuarioController;
import com.example.foodapi.api.model.response.UsuarioResponse;
import com.example.foodapi.domain.model.Usuario;

@Component
public class UsuarioResponseAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioResponse>{

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
	private AlgaLinks algaLinks;
    
    public UsuarioResponseAssembler() {
        super(UsuarioController.class, UsuarioResponse.class);
    }
    
    @Override
	public UsuarioResponse toModel(Usuario usuario) {
    	UsuarioResponse usuarioModel = createModelWithId(usuario.getId(), usuario);
		modelMapper.map(usuario, usuarioModel);
		
		usuarioModel.add(algaLinks.linkToUsuarios("usuarios"));
		
		usuarioModel.add(algaLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));
		
		return usuarioModel;
	}
    
    @Override
    public CollectionModel<UsuarioResponse> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities)
            .add(linkTo(UsuarioController.class).withSelfRel());
    }            
} 
