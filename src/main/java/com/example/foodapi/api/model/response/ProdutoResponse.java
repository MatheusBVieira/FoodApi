package com.example.foodapi.api.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoResponse {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;   
}  
