package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.request.EstadoRequest;
import com.example.foodapi.domain.model.Estado;

@Component
public class EstadoRequestDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Estado toDomainObject(EstadoRequest estadoInput) {
		return modelMapper.map(estadoInput, Estado.class);
	}
	
	public void copyToDomainObject(EstadoRequest estadoInput, Estado estado) {
		modelMapper.map(estadoInput, estado);
	}
	
}
