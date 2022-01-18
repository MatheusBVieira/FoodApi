package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.model.request.GrupoRequest;
import com.example.foodapi.domain.model.Grupo;

@Component
public class GrupoRequestDissasember {

    @Autowired
    private ModelMapper modelMapper;
    
    public Grupo toDomainObject(GrupoRequest grupoRequest) {
        return modelMapper.map(grupoRequest, Grupo.class);
    }
    
    public void copyToDomainObject(GrupoRequest grupoRequest, Grupo grupo) {
        modelMapper.map(grupoRequest, grupo);
    }   
}   
