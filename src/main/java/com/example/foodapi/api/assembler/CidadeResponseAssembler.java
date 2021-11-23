package com.example.foodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.response.CidadeResponse;
import com.example.foodapi.domain.model.Cidade;

@Component
public class CidadeResponseAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeResponse toResponse(Cidade cidade) {
		return modelMapper.map(cidade, CidadeResponse.class);
	}
	
	public List<CidadeResponse> toCollectionModel(List<Cidade> cidades) {
		return cidades.stream()
				.map(cidade -> toResponse(cidade))
				.collect(Collectors.toList());
	}
	
}
