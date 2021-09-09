package com.example.foodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.response.PedidoResponse;
import com.example.foodapi.domain.model.Pedido;

@Component
public class PedidoResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public PedidoResponse toResponse(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResponse.class);
    }
    
    public List<PedidoResponse> toCollectionResponse(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(pedido -> toResponse(pedido))
                .collect(Collectors.toList());
    }
    
}