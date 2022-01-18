package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.controller.CidadeController;
import com.example.foodapi.api.v1.model.response.CidadeResponse;
import com.example.foodapi.domain.model.Cidade;

@Component
public class CidadeResponseAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeResponse> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public CidadeResponseAssembler() {
		super(CidadeController.class, CidadeResponse.class);
	}
	
	@Override
	public CidadeResponse toModel(Cidade cidade) {
		CidadeResponse cidadeModel = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeModel);
		
		cidadeModel.add(algaLinks.linkToCidades("cidades"));
		
		cidadeModel.getEstado().add(algaLinks.linkToEstado(cidadeModel.getEstado().getId()));
		
		return cidadeModel;
	}
	
	@Override
	public CollectionModel<CidadeResponse> toCollectionModel(Iterable<? extends Cidade> entities) {
	    return super.toCollectionModel(entities)
	            .add(algaLinks.linkToCidades());
	}
	
}
