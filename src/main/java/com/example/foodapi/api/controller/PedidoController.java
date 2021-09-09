package com.example.foodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.assembler.PedidoResponseAssembler;
import com.example.foodapi.api.model.response.PedidoResponse;
import com.example.foodapi.domain.model.Pedido;
import com.example.foodapi.domain.repository.PedidoRepository;
import com.example.foodapi.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private EmissaoPedidoService emissaoPedido;
    
    @Autowired
    private PedidoResponseAssembler pedidoResponseAssembler;
    
    @GetMapping
    public List<PedidoResponse> listar() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();
        
        return pedidoResponseAssembler.toCollectionResponse(todosPedidos);
    }
    
    @GetMapping("/{pedidoId}")
    public PedidoResponse buscar(@PathVariable Long pedidoId) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
        
        return pedidoResponseAssembler.toResponse(pedido);
    }            
}  