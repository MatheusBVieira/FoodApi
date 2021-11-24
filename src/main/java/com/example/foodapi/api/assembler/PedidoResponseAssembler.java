package com.example.foodapi.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.controller.CidadeController;
import com.example.foodapi.api.controller.FormaPagamentoController;
import com.example.foodapi.api.controller.PedidoController;
import com.example.foodapi.api.controller.RestauranteController;
import com.example.foodapi.api.controller.RestauranteProdutoController;
import com.example.foodapi.api.controller.UsuarioController;
import com.example.foodapi.api.model.response.PedidoResponse;
import com.example.foodapi.domain.model.Pedido;

@Component
public class PedidoResponseAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResponse> {

    @Autowired
    private ModelMapper modelMapper;
    
    public PedidoResponseAssembler() {
        super(PedidoController.class, PedidoResponse.class);
    }
    
    @Override
    public PedidoResponse toModel(Pedido pedido) {
    	PedidoResponse pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);
        
        pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));
        
        pedidoModel.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedido.getRestaurante().getId())).withSelfRel());
        
        pedidoModel.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedido.getCliente().getId())).withSelfRel());
        
        // Passamos null no segundo argumento, porque é indiferente para a
        // construção da URL do recurso de forma de pagamento
        pedidoModel.getFormaPagamento().add(linkTo(methodOn(FormaPagamentoController.class)
                .buscar(pedido.getFormaPagamento().getId(), null)).withSelfRel());
        
        pedidoModel.getEnderecoEntrega().getCidade().add(linkTo(methodOn(CidadeController.class)
                .buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());
        
        pedidoModel.getItens().forEach(item -> {
            item.add(linkTo(methodOn(RestauranteProdutoController.class)
                    .buscar(pedidoModel.getRestaurante().getId(), item.getProdutoId()))
                    .withRel("produto"));
        });
        
        return pedidoModel;
    }
    
}