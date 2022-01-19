package com.example.foodapi.api.v2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.v2.assembler.CozinhaInputDisassemblerV2;
import com.example.foodapi.api.v2.assembler.CozinhaModelAssemblerV2;
import com.example.foodapi.api.v2.model.request.CozinhaRequestV2;
import com.example.foodapi.api.v2.model.response.CozinhaResponseV2;
import com.example.foodapi.domain.model.Cozinha;
import com.example.foodapi.domain.repository.CozinhaRepository;
import com.example.foodapi.domain.service.CozinhaService;

@RestController
@RequestMapping(value = "/v2/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaControllerV2 {

    @Autowired
    private CozinhaRepository cozinhaRepository;
    
    @Autowired
    private CozinhaService cozinhaService;
    
    @Autowired
    private CozinhaModelAssemblerV2 cozinhaModelAssembler;
    
    @Autowired
    private CozinhaInputDisassemblerV2 cozinhaInputDisassembler;
    
    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;
    
    @GetMapping
    public PagedModel<CozinhaResponseV2> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
        
        PagedModel<CozinhaResponseV2> cozinhasPagedModel = pagedResourcesAssembler
                .toModel(cozinhasPage, cozinhaModelAssembler);
        
        return cozinhasPagedModel;
    }
    
    @GetMapping("/{cozinhaId}")
    public CozinhaResponseV2 buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
        
        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaResponseV2 adicionar(@RequestBody @Valid CozinhaRequestV2 cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        cozinha = cozinhaService.salvar(cozinha);
        
        return cozinhaModelAssembler.toModel(cozinha);
    }
    
    @PutMapping("/{cozinhaId}")
    public CozinhaResponseV2 atualizar(@PathVariable Long cozinhaId,
            @RequestBody @Valid CozinhaRequestV2 cozinhaInput) {
        Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(cozinhaId);
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
        cozinhaAtual = cozinhaService.salvar(cozinhaAtual);
        
        return cozinhaModelAssembler.toModel(cozinhaAtual);
    }
    
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }   
}
