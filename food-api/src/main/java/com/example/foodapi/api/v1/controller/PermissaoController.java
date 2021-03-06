package com.example.foodapi.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.v1.assembler.PermissaoResponseAssembler;
import com.example.foodapi.api.v1.model.response.PermissaoResponse;
import com.example.foodapi.api.v1.openapi.controller.PermissaoControllerOpenApi;
import com.example.foodapi.core.security.CheckSecurity;
import com.example.foodapi.domain.model.Permissao;
import com.example.foodapi.domain.repository.PermissaoRepository;

@RestController
@RequestMapping(path = "/v1/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

    @Autowired
    private PermissaoRepository permissaoRepository;
    
    @Autowired
    private PermissaoResponseAssembler permissaoResponseAssembler;
    
    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<PermissaoResponse> listar() {
        List<Permissao> todasPermissoes = permissaoRepository.findAll();
        
        return permissaoResponseAssembler.toCollectionModel(todasPermissoes);
    }   
}