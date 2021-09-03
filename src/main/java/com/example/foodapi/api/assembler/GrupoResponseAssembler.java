package com.example.foodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.response.GrupoResponse;
import com.example.foodapi.domain.model.Grupo;

@Component
public class GrupoResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public GrupoResponse toResponse(Grupo grupo) {
        return modelMapper.map(grupo, GrupoResponse.class);
    }
    
    public List<GrupoResponse> toCollectionModel(List<Grupo> grupos) {
        return grupos.stream()
                .map(grupo -> toResponse(grupo))
                .collect(Collectors.toList());
    }   
}
