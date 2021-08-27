package com.example.foodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.response.RestauranteResponse;
import com.example.foodapi.domain.model.Restaurante;

@Component
public class RestauranteResponseAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public RestauranteResponse toModel(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteResponse.class);
	}
	
	public List<RestauranteResponse> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
	}
	
}
