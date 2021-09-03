package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.request.ProdutoRequest;
import com.example.foodapi.domain.model.Produto;

@Component
public class ProdutoRequestDissasembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Produto toDomainObject(ProdutoRequest produtoRequest) {
        return modelMapper.map(produtoRequest, Produto.class);
    }
    
    public void copyToDomainObject(ProdutoRequest produtoRequest, Produto produto) {
        modelMapper.map(produtoRequest, produto);
    }   
}
