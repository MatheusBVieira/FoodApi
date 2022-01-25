package com.example.foodapi.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.example.foodapi.api.v1.assembler.GrupoRequestDissasember;
import com.example.foodapi.api.v1.assembler.GrupoResponseAssembler;
import com.example.foodapi.api.v1.model.request.GrupoRequest;
import com.example.foodapi.api.v1.model.response.GrupoResponse;
import com.example.foodapi.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.example.foodapi.core.security.CheckSecurity;
import com.example.foodapi.domain.model.Grupo;
import com.example.foodapi.domain.repository.GrupoRepository;
import com.example.foodapi.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoRepository grupoRepository;
    
    @Autowired
    private GrupoService grupoService;
    
    @Autowired
    private GrupoResponseAssembler grupoResponseAssembler;
    
    @Autowired
    private GrupoRequestDissasember grupoRequestDisassembler;
    
    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<GrupoResponse> listar() {
        List<Grupo> todosGrupos = grupoRepository.findAll();
        
        return grupoResponseAssembler.toCollectionModel(todosGrupos);
    }
    
    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
	@GetMapping("/{grupoId}")
    public GrupoResponse buscar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        
        return grupoResponseAssembler.toModel(grupo);
    }
    
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoResponse adicionar(@RequestBody @Valid GrupoRequest grupoRequest) {
        Grupo grupo = grupoRequestDisassembler.toDomainObject(grupoRequest);
        
        grupo = grupoService.salvar(grupo);
        
        return grupoResponseAssembler.toModel(grupo);
    }
    
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
	@PutMapping("/{grupoId}")
    public GrupoResponse atualizar(@PathVariable Long grupoId,
            @RequestBody @Valid GrupoRequest grupoRequest) {
        Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);
        
        grupoRequestDisassembler.copyToDomainObject(grupoRequest, grupoAtual);
        
        grupoAtual = grupoService.salvar(grupoAtual);
        
        return grupoResponseAssembler.toModel(grupoAtual);
    }
    
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
	@DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);	
    }   
} 
