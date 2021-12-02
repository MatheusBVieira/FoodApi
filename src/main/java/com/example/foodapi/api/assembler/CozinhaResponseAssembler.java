package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.AlgaLinks;
import com.example.foodapi.api.controller.CozinhaController;
import com.example.foodapi.api.model.response.CozinhaResponse;
import com.example.foodapi.domain.model.Cozinha;

@Component
public class CozinhaResponseAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaResponse> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public CozinhaResponseAssembler() {
		super(CozinhaController.class, CozinhaResponse.class);
	}
	
	@Override
	public CozinhaResponse toModel(Cozinha cozinha) {
		CozinhaResponse cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaModel);
		
		cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));
		
		return cozinhaModel;
	}
	
}
