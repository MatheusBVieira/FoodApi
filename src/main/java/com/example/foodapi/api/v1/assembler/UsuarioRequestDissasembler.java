package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.model.request.UsuarioRequest;
import com.example.foodapi.domain.model.Usuario;

@Component
public class UsuarioRequestDissasembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Usuario toDomainObject(UsuarioRequest usuarioResquest) {
        return modelMapper.map(usuarioResquest, Usuario.class);
    }
    
    public void copyToDomainObject(UsuarioRequest usuarioResquest, Usuario usuario) {
        modelMapper.map(usuarioResquest, usuario);
    }            
}
