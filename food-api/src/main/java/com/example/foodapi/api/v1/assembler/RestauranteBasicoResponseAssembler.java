package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.controller.RestauranteController;
import com.example.foodapi.api.v1.model.response.RestauranteBasicoResponse;
import com.example.foodapi.core.security.AlgaSecurity;
import com.example.foodapi.domain.model.Restaurante;

@Component
public class RestauranteBasicoResponseAssembler 
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoResponse> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    @Autowired
    private AlgaSecurity algaSecurity;
    
    public RestauranteBasicoResponseAssembler() {
        super(RestauranteController.class, RestauranteBasicoResponse.class);
    }
    
    @Override
    public RestauranteBasicoResponse toModel(Restaurante restaurante) {
    	RestauranteBasicoResponse restauranteModel = createModelWithId(
                restaurante.getId(), restaurante);
        
        modelMapper.map(restaurante, restauranteModel);
        
        if (algaSecurity.podeConsultarRestaurantes()) {
            restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
        }
        
        if (algaSecurity.podeConsultarCozinhas()) {
            restauranteModel.getCozinha().add(
                    algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
        }
        
        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteBasicoResponse> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteBasicoResponse> collectionModel = super.toCollectionModel(entities);
        
        if (algaSecurity.podeConsultarRestaurantes()) {
            collectionModel.add(algaLinks.linkToRestaurantes());
        }
                
        return collectionModel;
    } 
}
