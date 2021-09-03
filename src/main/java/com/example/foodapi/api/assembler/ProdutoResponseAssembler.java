package com.example.foodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.response.ProdutoResponse;
import com.example.foodapi.domain.model.Produto;

@Component
public class ProdutoResponseAssembler {

	@Autowired
    private ModelMapper modelMapper;
    
    public ProdutoResponse toResponse(Produto produto) {
        return modelMapper.map(produto, ProdutoResponse.class);
    }
    
    public List<ProdutoResponse> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
                .map(produto -> toResponse(produto))
                .collect(Collectors.toList());
    }  
	
}
