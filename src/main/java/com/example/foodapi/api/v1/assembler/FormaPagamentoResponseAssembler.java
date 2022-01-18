package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.controller.FormaPagamentoController;
import com.example.foodapi.api.v1.model.response.FormaPagamentoResponse;
import com.example.foodapi.domain.model.FormaPagamento;

@Component
public class FormaPagamentoResponseAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoResponse> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    public FormaPagamentoResponseAssembler() {
        super(FormaPagamentoController.class, FormaPagamentoResponse.class);
    }
    
    @Override
    public FormaPagamentoResponse toModel(FormaPagamento formaPagamento) {
    	FormaPagamentoResponse formaPagamentoModel = 
                createModelWithId(formaPagamento.getId(), formaPagamento);
        
        modelMapper.map(formaPagamento, formaPagamentoModel);
        
        formaPagamentoModel.add(algaLinks.linkToFormasPagamento("formasPagamento"));
        
        return formaPagamentoModel;
    }
    
    @Override
    public CollectionModel<FormaPagamentoResponse> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        return super.toCollectionModel(entities)
            .add(algaLinks.linkToFormasPagamento());
    }   
}
