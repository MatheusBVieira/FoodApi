package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.controller.PedidoController;
import com.example.foodapi.api.v1.model.response.PedidoResumoResponse;
import com.example.foodapi.core.security.AlgaSecurity;
import com.example.foodapi.domain.model.Pedido;

@Component
public class PedidoResumoResponseAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoResponse> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public PedidoResumoResponseAssembler() {
        super(PedidoController.class, PedidoResumoResponse.class);
    }
	
	@Override
	public PedidoResumoResponse toModel(Pedido pedido) {
		PedidoResumoResponse pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
	    modelMapper.map(pedido, pedidoModel);
	    
	    if (algaSecurity.podePesquisarPedidos()) {
	        pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
	    }
	    
	    if (algaSecurity.podeConsultarRestaurantes()) {
	        pedidoModel.getRestaurante().add(
	                algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
	    }

	    if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
	        pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));
	    }
	    
	    return pedidoModel;
	}
	
}
