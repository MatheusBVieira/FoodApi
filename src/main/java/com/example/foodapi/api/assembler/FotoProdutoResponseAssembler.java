package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.AlgaLinks;
import com.example.foodapi.api.controller.RestauranteProdutoFotoController;
import com.example.foodapi.api.model.response.FotoProdutoResponse;
import com.example.foodapi.domain.model.FotoProduto;

@Component
public class FotoProdutoResponseAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoResponse> {

	@Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    public FotoProdutoResponseAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoResponse.class);
    }
	
    @Override
    public FotoProdutoResponse toModel(FotoProduto foto) {
    	FotoProdutoResponse fotoProdutoModel = modelMapper.map(foto, FotoProdutoResponse.class);
        
        fotoProdutoModel.add(algaLinks.linkToFotoProduto(
                foto.getRestauranteId(), foto.getProduto().getId()));
        
        fotoProdutoModel.add(algaLinks.linkToProduto(
                foto.getRestauranteId(), foto.getProduto().getId(), "produto"));
        
        return fotoProdutoModel;
    }
	
}

