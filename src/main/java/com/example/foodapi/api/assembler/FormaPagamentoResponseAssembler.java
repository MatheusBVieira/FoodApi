package com.example.foodapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.model.response.FormaPagamentoResponse;
import com.example.foodapi.domain.model.FormaPagamento;

@Component
public class FormaPagamentoResponseAssembler {

	@Autowired
    private ModelMapper modelMapper;
    
    public FormaPagamentoResponse toModel(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoResponse.class);
    }
    
    public List<FormaPagamentoResponse> toCollectionModel(List<FormaPagamento> formasPagamentos) {
        return formasPagamentos.stream()
                .map(formaPagamento -> toModel(formaPagamento))
                .collect(Collectors.toList());
    }
	
}