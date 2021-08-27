package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.request.RestauranteRequest;
import com.example.foodapi.domain.model.Cozinha;
import com.example.foodapi.domain.model.Restaurante;

@Component
public class RestauranteRequestDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteRequest restauranteRequest) {
		return modelMapper.map(restauranteRequest, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteRequest restauranteRequest, Restaurante restaurante) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		
		modelMapper.map(restauranteRequest, restaurante);
	}
	
}
