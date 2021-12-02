package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.AlgaLinks;
import com.example.foodapi.api.controller.RestauranteProdutoController;
import com.example.foodapi.api.model.response.ProdutoResponse;
import com.example.foodapi.domain.model.Produto;

@Component
public class ProdutoResponseAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoResponse> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public ProdutoResponseAssembler() {
		super(RestauranteProdutoController.class, ProdutoResponse.class);
	}
	
	@Override
	public ProdutoResponse toModel(Produto produto) {
		ProdutoResponse produtoModel = createModelWithId(
				produto.getId(), produto, produto.getRestaurante().getId());
		
		modelMapper.map(produto, produtoModel);
		
		produtoModel.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));
		
		return produtoModel;
	}
	
}
