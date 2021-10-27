package com.example.foodapi.api.assembler;

import java.util.Collection;
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
    
    public FormaPagamentoResponse toResponse(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoResponse.class);
    }
    
    public List<FormaPagamentoResponse> toCollectionResponse(Collection<FormaPagamento> formasPagamentos) {
        return formasPagamentos.stream()
                .map(formaPagamento -> toResponse(formaPagamento))
                .collect(Collectors.toList());
    }
	
}
