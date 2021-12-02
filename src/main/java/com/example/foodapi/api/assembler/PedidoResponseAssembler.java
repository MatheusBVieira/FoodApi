package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.AlgaLinks;
import com.example.foodapi.api.controller.PedidoController;
import com.example.foodapi.api.model.response.PedidoResponse;
import com.example.foodapi.domain.model.Pedido;

@Component
public class PedidoResponseAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResponse> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
	private AlgaLinks algaLinks;

    
    public PedidoResponseAssembler() {
        super(PedidoController.class, PedidoResponse.class);
    }
    
    @Override
	public PedidoResponse toModel(Pedido pedido) {
    	PedidoResponse pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);
		
		pedidoModel.add(algaLinks.linkToPedidos());
		
		pedidoModel.getRestaurante().add(
				algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
		
		pedidoModel.getCliente().add(
				algaLinks.linkToUsuario(pedido.getCliente().getId()));
		
		pedidoModel.getFormaPagamento().add(
				algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));
		
		pedidoModel.getEnderecoEntrega().getCidade().add(
				algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));
		
		pedidoModel.getItens().forEach(item -> {
			item.add(algaLinks.linkToProduto(
					pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
		});
		
		return pedidoModel;
	}
    
}