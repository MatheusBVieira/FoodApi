package com.example.foodapi.api.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormaPagamentoIdRequest {

    @NotNull
    private Long id;            
} 
