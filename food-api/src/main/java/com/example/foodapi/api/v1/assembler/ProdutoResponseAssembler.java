package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.controller.RestauranteProdutoController;
import com.example.foodapi.api.v1.model.response.ProdutoResponse;
import com.example.foodapi.core.security.AlgaSecurity;
import com.example.foodapi.domain.model.Produto;

@Component
public class ProdutoResponseAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoResponse> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity; 
	
	public ProdutoResponseAssembler() {
		super(RestauranteProdutoController.class, ProdutoResponse.class);
	}
	
	@Override
	public ProdutoResponse toModel(Produto produto) {
		ProdutoResponse produtoModel = createModelWithId(
	            produto.getId(), produto, produto.getRestaurante().getId());
	    
	    modelMapper.map(produto, produtoModel);
	    
	    if (algaSecurity.podeConsultarRestaurantes()) {
	    	produtoModel.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));

	    	produtoModel.add(algaLinks.linkToFotoProduto(
	    			produto.getRestaurante().getId(), produto.getId(), "foto"));
	    }
	    
	    return produtoModel;
	}    
	
}
