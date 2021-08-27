package com.example.foodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.response.EstadoResponse;
import com.example.foodapi.domain.model.Estado;

@Component
public class EstadoResponseAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoResponse toModel(Estado estado) {
		return modelMapper.map(estado, EstadoResponse.class);
	}
	
	public List<EstadoResponse> toCollectionModel(List<Estado> estados) {
		return estados.stream()
				.map(estado -> toModel(estado))
				.collect(Collectors.toList());
	}
	
}
