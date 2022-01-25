package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.controller.CidadeController;
import com.example.foodapi.api.v1.model.response.CidadeResponse;
import com.example.foodapi.core.security.AlgaSecurity;
import com.example.foodapi.domain.model.Cidade;

@Component
public class CidadeResponseAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeResponse> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public CidadeResponseAssembler() {
		super(CidadeController.class, CidadeResponse.class);
	}
	
	@Override
	public CidadeResponse toModel(Cidade cidade) {
		CidadeResponse cidadeModel = createModelWithId(cidade.getId(), cidade);
	    
	    modelMapper.map(cidade, cidadeModel);
	    
	    if (algaSecurity.podeConsultarCidades()) {
	        cidadeModel.add(algaLinks.linkToCidades("cidades"));
	    }
	    
	    if (algaSecurity.podeConsultarEstados()) {
	        cidadeModel.getEstado().add(algaLinks.linkToEstado(cidadeModel.getEstado().getId()));
	    }
	    
	    return cidadeModel;
	}

	@Override
	public CollectionModel<CidadeResponse> toCollectionModel(Iterable<? extends Cidade> entities) {
	    CollectionModel<CidadeResponse> collectionModel = super.toCollectionModel(entities);
	    
	    if (algaSecurity.podeConsultarCidades()) {
	        collectionModel.add(algaLinks.linkToCidades());
	    }
	    
	    return collectionModel;
	}
	
}
