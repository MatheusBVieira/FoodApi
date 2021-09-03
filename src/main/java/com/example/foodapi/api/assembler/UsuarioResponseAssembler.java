package com.example.foodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.response.UsuarioResponse;
import com.example.foodapi.domain.model.Usuario;

@Component
public class UsuarioResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public UsuarioResponse toResponse(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioResponse.class);
    }
    
    public List<UsuarioResponse> toCollectionModel(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(usuario -> toResponse(usuario))
                .collect(Collectors.toList());
    }            
} 
