package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.response.FotoProdutoResponse;
import com.example.foodapi.domain.model.FotoProduto;

@Component
public class FotoProdutoResponseAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FotoProdutoResponse toResponse(FotoProduto foto) {
		return modelMapper.map(foto, FotoProdutoResponse.class);
	}
	
}

