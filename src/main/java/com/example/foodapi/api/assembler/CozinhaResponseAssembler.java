package com.example.foodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.response.CozinhaResponse;
import com.example.foodapi.domain.model.Cozinha;

@Component
public class CozinhaResponseAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CozinhaResponse toModel(Cozinha cozinha) {
		return modelMapper.map(cozinha, CozinhaResponse.class);
	}
	
	public List<CozinhaResponse> toCollectionModel(List<Cozinha> cozinhas) {
		return cozinhas.stream()
				.map(cozinha -> toModel(cozinha))
				.collect(Collectors.toList());
	}
	
}
