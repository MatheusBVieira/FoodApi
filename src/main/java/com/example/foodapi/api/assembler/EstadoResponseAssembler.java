package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.AlgaLinks;
import com.example.foodapi.api.controller.EstadoController;
import com.example.foodapi.api.model.response.EstadoResponse;
import com.example.foodapi.domain.model.Estado;

@Component
public class EstadoResponseAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoResponse> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
    public EstadoResponseAssembler() {
        super(EstadoController.class, EstadoResponse.class);
    }
	
    @Override
	public EstadoResponse toModel(Estado estado) {
    	EstadoResponse estadoModel = createModelWithId(estado.getId(), estado);
		modelMapper.map(estado, estadoModel);
		
		estadoModel.add(algaLinks.linkToEstados("estados"));
		
		return estadoModel;
	}
	
    @Override
    public CollectionModel<EstadoResponse> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities)
            .add(algaLinks.linkToEstados());
    }  
	
}
