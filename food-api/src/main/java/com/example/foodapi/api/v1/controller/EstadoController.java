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

import com.example.foodapi.api.v1.assembler.EstadoRequestDisassembler;
import com.example.foodapi.api.v1.assembler.EstadoResponseAssembler;
import com.example.foodapi.api.v1.model.request.EstadoRequest;
import com.example.foodapi.api.v1.model.response.EstadoResponse;
import com.example.foodapi.api.v1.openapi.controller.EstadoControllerOpenApi;
import com.example.foodapi.core.security.CheckSecurity;
import com.example.foodapi.domain.model.Estado;
import com.example.foodapi.domain.repository.EstadoRepository;
import com.example.foodapi.domain.service.EstadoService;

@RestController
@RequestMapping(path = "/v1/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoResponseAssembler estadoResponseAssembler;
	
	@Autowired
	private EstadoRequestDisassembler estadoRequestDisassembler;
	
	@CheckSecurity.Estados.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<EstadoResponse> listar() {
		List<Estado> todosEstados = estadoRepository.findAll();
		
		return estadoResponseAssembler.toCollectionModel(todosEstados);
	}
	
	@CheckSecurity.Estados.PodeConsultar
	@Override
	@GetMapping("/{estadoId}")
	public EstadoResponse buscar(@PathVariable Long estadoId) {
		Estado estado = estadoService.buscarOuFalhar(estadoId);
		
		return estadoResponseAssembler.toModel(estado);
	}
	
	@CheckSecurity.Estados.PodeEditar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoResponse adicionar(@RequestBody @Valid EstadoRequest estadoInput) {
		Estado estado = estadoRequestDisassembler.toDomainObject(estadoInput);
		
		estado = estadoService.salvar(estado);
		
		return estadoResponseAssembler.toModel(estado);
	}
	
	@CheckSecurity.Estados.PodeEditar
	@Override
	@PutMapping("/{estadoId}")
	public EstadoResponse atualizar(@PathVariable Long estadoId,
			@RequestBody @Valid EstadoRequest estadoInput) {
		Estado estadoAtual = estadoService.buscarOuFalhar(estadoId);
		
		estadoRequestDisassembler.copyToDomainObject(estadoInput, estadoAtual);
		
		estadoAtual = estadoService.salvar(estadoAtual);
		
		return estadoResponseAssembler.toModel(estadoAtual);
	}
	
	@CheckSecurity.Estados.PodeEditar
	@Override
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		estadoService.excluir(estadoId);	
	}

}
