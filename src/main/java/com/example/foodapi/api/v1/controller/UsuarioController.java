package com.example.foodapi.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.v1.assembler.UsuarioRequestDissasembler;
import com.example.foodapi.api.v1.assembler.UsuarioResponseAssembler;
import com.example.foodapi.api.v1.model.request.SenhaRequest;
import com.example.foodapi.api.v1.model.request.UsuarioComSenhaRequest;
import com.example.foodapi.api.v1.model.request.UsuarioRequest;
import com.example.foodapi.api.v1.model.response.UsuarioResponse;
import com.example.foodapi.api.v1.openapi.controller.UsuarioControllerOpenApi;
import com.example.foodapi.domain.model.Usuario;
import com.example.foodapi.domain.repository.UsuarioRepository;
import com.example.foodapi.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioResponseAssembler usuarioResponseAssembler;
    
    @Autowired
    private UsuarioRequestDissasembler usuarioRequestDisassembler;
    
    @Override
	@GetMapping
    public CollectionModel<UsuarioResponse> listar() {
        List<Usuario> todasUsuarios = usuarioRepository.findAll();
        
        return usuarioResponseAssembler.toCollectionModel(todasUsuarios);
    }
    
    @Override
	@GetMapping("/{usuarioId}")
    public UsuarioResponse buscar(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        
        return usuarioResponseAssembler.toModel(usuario);
    }
    
    @Override
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse adicionar(@RequestBody @Valid UsuarioComSenhaRequest usuarioRequest) {
        Usuario usuario = usuarioRequestDisassembler.toDomainObject(usuarioRequest);
        usuario = usuarioService.salvar(usuario);
        
        return usuarioResponseAssembler.toModel(usuario);
    }
    
    @Override
	@PutMapping("/{usuarioId}")
    public UsuarioResponse atualizar(@PathVariable Long usuarioId,
            @RequestBody @Valid UsuarioRequest usuarioRequest) {
        Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
        usuarioRequestDisassembler.copyToDomainObject(usuarioRequest, usuarioAtual);
        usuarioAtual = usuarioService.salvar(usuarioAtual);
        
        return usuarioResponseAssembler.toModel(usuarioAtual);
    }
    
    @Override
	@PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaRequest senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }            
}
