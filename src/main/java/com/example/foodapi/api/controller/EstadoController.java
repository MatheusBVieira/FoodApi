package com.example.foodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.assembler.EstadoRequestDisassembler;
import com.example.foodapi.api.assembler.EstadoResponseAssembler;
import com.example.foodapi.api.model.request.EstadoRequest;
import com.example.foodapi.api.model.response.EstadoResponse;
import com.example.foodapi.domain.model.Estado;
import com.example.foodapi.domain.repository.EstadoRepository;
import com.example.foodapi.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoResponseAssembler estadoResponseAssembler;
	
	@Autowired
	private EstadoRequestDisassembler estadoRequestDisassembler;
	
	@GetMapping
	public List<EstadoResponse> listar() {
		List<Estado> todosEstados = estadoRepository.findAll();
		
		return estadoResponseAssembler.toCollectionModel(todosEstados);
	}
	
	@GetMapping("/{estadoId}")
	public EstadoResponse buscar(@PathVariable Long estadoId) {
		Estado estado = estadoService.buscarOuFalhar(estadoId);
		
		return estadoResponseAssembler.toModel(estado);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoResponse adicionar(@RequestBody @Valid EstadoRequest estadoInput) {
		Estado estado = estadoRequestDisassembler.toDomainObject(estadoInput);
		
		estado = estadoService.salvar(estado);
		
		return estadoResponseAssembler.toModel(estado);
	}
	
	@PutMapping("/{estadoId}")
	public EstadoResponse atualizar(@PathVariable Long estadoId,
			@RequestBody @Valid EstadoRequest estadoInput) {
		Estado estadoAtual = estadoService.buscarOuFalhar(estadoId);
		
		estadoRequestDisassembler.copyToDomainObject(estadoInput, estadoAtual);
		
		estadoAtual = estadoService.salvar(estadoAtual);
		
		return estadoResponseAssembler.toModel(estadoAtual);
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		estadoService.excluir(estadoId);	
	}

}
