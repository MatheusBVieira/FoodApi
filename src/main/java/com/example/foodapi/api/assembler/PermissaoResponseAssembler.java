package com.example.foodapi.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.response.PermissaoResponse;
import com.example.foodapi.domain.model.Permissao;

@Component
public class PermissaoResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public PermissaoResponse toModel(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoResponse.class);
    }
    
    public List<PermissaoResponse> toCollectionModel(Collection<Permissao> permissoes) {
        return permissoes.stream()
                .map(permissao -> toModel(permissao))
                .collect(Collectors.toList());
    }
    
}
