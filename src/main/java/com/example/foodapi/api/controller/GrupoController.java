package com.example.foodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.foodapi.api.assembler.GrupoRequestDissasember;
import com.example.foodapi.api.assembler.GrupoResponseAssembler;
import com.example.foodapi.api.model.request.GrupoRequest;
import com.example.foodapi.api.model.response.GrupoResponse;
import com.example.foodapi.api.openapi.controller.GrupoControllerOpenApi;
import com.example.foodapi.domain.model.Grupo;
import com.example.foodapi.domain.repository.GrupoRepository;
import com.example.foodapi.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoRepository grupoRepository;
    
    @Autowired
    private GrupoService grupoService;
    
    @Autowired
    private GrupoResponseAssembler GrupoResponseAssembler;
    
    @Autowired
    private GrupoRequestDissasember grupoRequestDisassembler;
    
    @Override
	@GetMapping
    public List<GrupoResponse> listar() {
        List<Grupo> todosGrupos = grupoRepository.findAll();
        
        return GrupoResponseAssembler.toCollectionModel(todosGrupos);
    }
    
    @Override
	@GetMapping("/{grupoId}")
    public GrupoResponse buscar(@PathVariable Long grupoId) {
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);
        
        return GrupoResponseAssembler.toResponse(grupo);
    }
    
    @Override
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoResponse adicionar(@RequestBody @Valid GrupoRequest grupoRequest) {
        Grupo grupo = grupoRequestDisassembler.toDomainObject(grupoRequest);
        
        grupo = grupoService.salvar(grupo);
        
        return GrupoResponseAssembler.toResponse(grupo);
    }
    
    @Override
	@PutMapping("/{grupoId}")
    public GrupoResponse atualizar(@PathVariable Long grupoId,
            @RequestBody @Valid GrupoRequest grupoRequest) {
        Grupo grupoAtual = grupoService.buscarOuFalhar(grupoId);
        
        grupoRequestDisassembler.copyToDomainObject(grupoRequest, grupoAtual);
        
        grupoAtual = grupoService.salvar(grupoAtual);
        
        return GrupoResponseAssembler.toResponse(grupoAtual);
    }
    
    @Override
	@DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        grupoService.excluir(grupoId);	
    }   
} 
