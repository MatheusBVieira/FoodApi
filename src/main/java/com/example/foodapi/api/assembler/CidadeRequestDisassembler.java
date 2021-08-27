package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.request.CidadeRequest;
import com.example.foodapi.domain.model.Cidade;
import com.example.foodapi.domain.model.Estado;

@Component
public class CidadeRequestDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeRequest cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeRequest cidadeInput, Cidade cidade) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.algaworks.algafood.domain.model.Estado was altered from 1 to 2
		cidade.setEstado(new Estado());
		
		modelMapper.map(cidadeInput, cidade);
	}
	
}
