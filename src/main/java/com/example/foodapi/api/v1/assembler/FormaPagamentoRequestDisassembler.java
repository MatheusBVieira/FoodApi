package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.model.request.FormaPagamentoRequest;
import com.example.foodapi.domain.model.FormaPagamento;

@Component
public class FormaPagamentoRequestDisassembler {

	@Autowired
    private ModelMapper modelMapper;
    
    public FormaPagamento toDomainObject(FormaPagamentoRequest formaPagamentoRequest) {
        return modelMapper.map(formaPagamentoRequest, FormaPagamento.class);
    }
    
    public void copyToDomainObject(FormaPagamentoRequest formaPagamentoRequest, FormaPagamento formaPagamento) {
        modelMapper.map(formaPagamentoRequest, formaPagamento);
    }  
	
}
