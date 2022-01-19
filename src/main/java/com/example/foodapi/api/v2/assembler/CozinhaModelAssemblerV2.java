package com.example.foodapi.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v2.AlgaLinksV2;
import com.example.foodapi.api.v2.controller.CozinhaControllerV2;
import com.example.foodapi.api.v2.model.response.CozinhaResponseV2;
import com.example.foodapi.domain.model.Cozinha;

@Component
public class CozinhaModelAssemblerV2 
        extends RepresentationModelAssemblerSupport<Cozinha, CozinhaResponseV2> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinksV2 algaLinks;
    
    public CozinhaModelAssemblerV2() {
        super(CozinhaControllerV2.class, CozinhaResponseV2.class);
    }
    
    @Override
    public CozinhaResponseV2 toModel(Cozinha cozinha) {
        CozinhaResponseV2 cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModel);
        
        cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));
        
        return cozinhaModel;
    }   
}
