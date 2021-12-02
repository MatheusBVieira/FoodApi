package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.AlgaLinks;
import com.example.foodapi.api.controller.RestauranteController;
import com.example.foodapi.api.model.response.RestauranteApenasNomeResponse;
import com.example.foodapi.domain.model.Restaurante;

@Component
public class RestauranteApenasNomeResponseAssembler 
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeResponse> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    public RestauranteApenasNomeResponseAssembler() {
        super(RestauranteController.class, RestauranteApenasNomeResponse.class);
    }
    
    @Override
    public RestauranteApenasNomeResponse toModel(Restaurante restaurante) {
    	RestauranteApenasNomeResponse restauranteModel = createModelWithId(
                restaurante.getId(), restaurante);
        
        modelMapper.map(restaurante, restauranteModel);
        
        restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
        
        return restauranteModel;
    }
    
    @Override
    public CollectionModel<RestauranteApenasNomeResponse> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToRestaurantes());
    }   
}
