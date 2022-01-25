package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.controller.RestauranteController;
import com.example.foodapi.api.v1.model.response.RestauranteApenasNomeResponse;
import com.example.foodapi.core.security.AlgaSecurity;
import com.example.foodapi.domain.model.Restaurante;

@Component
public class RestauranteApenasNomeResponseAssembler 
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeResponse> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    @Autowired
    private AlgaSecurity algaSecurity;
    
    public RestauranteApenasNomeResponseAssembler() {
        super(RestauranteController.class, RestauranteApenasNomeResponse.class);
    }
    
    @Override
    public RestauranteApenasNomeResponse toModel(Restaurante restaurante) {
    	RestauranteApenasNomeResponse restauranteModel = createModelWithId(
                restaurante.getId(), restaurante);
        
        modelMapper.map(restaurante, restauranteModel);
        
        if (algaSecurity.podeConsultarRestaurantes()) {
            restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
        }
        
        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteApenasNomeResponse> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteApenasNomeResponse> collectionModel = super.toCollectionModel(entities);
        
        if (algaSecurity.podeConsultarRestaurantes()) {
            collectionModel.add(algaLinks.linkToRestaurantes());
        }
                
        return collectionModel;
    }   
}
