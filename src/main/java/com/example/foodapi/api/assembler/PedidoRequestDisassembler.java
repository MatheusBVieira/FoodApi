package com.example.foodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.request.PedidoRequest;
import com.example.foodapi.domain.model.Pedido;

@Component
public class PedidoRequestDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Pedido toDomainObject(PedidoRequest pedidoRequest) {
        return modelMapper.map(pedidoRequest, Pedido.class);
    }
    
    public void copyToDomainObject(PedidoRequest pedidoRequest, Pedido pedido) {
        modelMapper.map(pedidoRequest, pedido);
    }            
}
