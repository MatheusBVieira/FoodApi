package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.AlgaLinks;
import com.example.foodapi.api.controller.PedidoController;
import com.example.foodapi.api.model.response.PedidoResumoResponse;
import com.example.foodapi.domain.model.Pedido;

@Component
public class PedidoResumoResponseAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoResponse> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	public PedidoResumoResponseAssembler() {
        super(PedidoController.class, PedidoResumoResponse.class);
    }
	
	@Override
	public PedidoResumoResponse toModel(Pedido pedido) {
		PedidoResumoResponse pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);
		
		pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
		
		pedidoModel.getRestaurante().add(
				algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

		pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));
		
		return pedidoModel;
	}
	
}
