package com.example.foodapi.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.AlgaLinks;
import com.example.foodapi.api.controller.CidadeController;
import com.example.foodapi.api.model.response.CidadeResponse;
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
				.add(linkTo(CidadeController.class).withSelfRel());
	}
	
}
