package com.example.foodapi.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v2.AlgaLinksV2;
import com.example.foodapi.api.v2.controller.CidadeControllerV2;
import com.example.foodapi.api.v2.model.response.CidadeResponseV2;
import com.example.foodapi.domain.model.Cidade;

@Component
public class CidadeModelAssemblerV2 
		extends RepresentationModelAssemblerSupport<Cidade, CidadeResponseV2> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinksV2 algaLinks;
	
	public CidadeModelAssemblerV2() {
		super(CidadeControllerV2.class, CidadeResponseV2.class);
	}
	
	@Override
	public CidadeResponseV2 toModel(Cidade cidade) {
		CidadeResponseV2 cidadeModel = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeModel);
		
		cidadeModel.add(algaLinks.linkToCidades("cidades"));
		
		return cidadeModel;
	}
	
	@Override
	public CollectionModel<CidadeResponseV2> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities)
				.add(algaLinks.linkToCidades());
	}
	
}
