package com.example.foodapi.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.controller.PedidoController;
import com.example.foodapi.api.controller.RestauranteController;
import com.example.foodapi.api.controller.UsuarioController;
import com.example.foodapi.api.model.response.PedidoResumoResponse;
import com.example.foodapi.domain.model.Pedido;

@Component
public class PedidoResumoResponseAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoResponse> {

	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoResumoResponseAssembler() {
        super(PedidoController.class, PedidoResumoResponse.class);
    }
	
	 @Override
	    public PedidoResumoResponse toModel(Pedido pedido) {
		 	PedidoResumoResponse pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
	        modelMapper.map(pedido, pedidoModel);
	        
	        pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));
	        
	        pedidoModel.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
	                .buscar(pedido.getRestaurante().getId())).withSelfRel());
	        
	        pedidoModel.getCliente().add(linkTo(methodOn(UsuarioController.class)
	                .buscar(pedido.getCliente().getId())).withSelfRel());
	        
	        return pedidoModel;
	    }
	
}
