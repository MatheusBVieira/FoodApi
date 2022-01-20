package com.example.foodapi.api.v1.model.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoRequest {

    @Valid
    @NotNull
    private RestauranteIdRequest restaurante;
    
    @Valid
    @NotNull
    private EnderecoRequest enderecoEntrega;
    
    @Valid
    @NotNull
    private FormaPagamentoIdRequest formaPagamento;
    
    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoRequest> itens;
    
}    
