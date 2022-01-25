package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.controller.EstadoController;
import com.example.foodapi.api.v1.model.response.EstadoResponse;
import com.example.foodapi.core.security.AlgaSecurity;
import com.example.foodapi.domain.model.Estado;

@Component
public class EstadoResponseAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoResponse> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
    public EstadoResponseAssembler() {
        super(EstadoController.class, EstadoResponse.class);
    }
	
    @Override
    public EstadoResponse toModel(Estado estado) {
    	EstadoResponse estadoModel = createModelWithId(estado.getId(), estado);
        modelMapper.map(estado, estadoModel);
        
        if (algaSecurity.podeConsultarEstados()) {
            estadoModel.add(algaLinks.linkToEstados("estados"));
        }
        
        return estadoModel;
    }

    @Override
    public CollectionModel<EstadoResponse> toCollectionModel(Iterable<? extends Estado> entities) {
        CollectionModel<EstadoResponse> collectionModel = super.toCollectionModel(entities);
        
        if (algaSecurity.podeConsultarEstados()) {
            collectionModel.add(algaLinks.linkToEstados());
        }
        
        return collectionModel;
    }  
	
}
