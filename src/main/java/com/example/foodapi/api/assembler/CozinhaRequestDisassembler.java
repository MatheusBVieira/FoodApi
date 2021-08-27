package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.request.CozinhaRequest;
import com.example.foodapi.domain.model.Cozinha;

@Component
public class CozinhaRequestDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cozinha toDomainObject(CozinhaRequest cozinhaInput) {
		return modelMapper.map(cozinhaInput, Cozinha.class);
	}
	
	public void copyToDomainObject(CozinhaRequest cozinhaInput, Cozinha cozinha) {
		modelMapper.map(cozinhaInput, cozinha);
	}
	
}
