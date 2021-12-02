package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.AlgaLinks;
import com.example.foodapi.api.controller.RestauranteController;
import com.example.foodapi.api.model.response.RestauranteBasicoResponse;
import com.example.foodapi.domain.model.Restaurante;

@Component
public class RestauranteBasicoResponseAssembler 
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoResponse> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    public RestauranteBasicoResponseAssembler() {
        super(RestauranteController.class, RestauranteBasicoResponse.class);
    }
    
    @Override
    public RestauranteBasicoResponse toModel(Restaurante restaurante) {
    	RestauranteBasicoResponse restauranteModel = createModelWithId(
                restaurante.getId(), restaurante);
        
        modelMapper.map(restaurante, restauranteModel);
        
        restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
        
        restauranteModel.getCozinha().add(
                algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
        
        return restauranteModel;
    }
    
    @Override
    public CollectionModel<RestauranteBasicoResponse> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToRestaurantes());
    }   
}
