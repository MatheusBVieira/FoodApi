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

import com.example.foodapi.api.assembler.CidadeRequestDisassembler;
import com.example.foodapi.api.assembler.CidadeResponseAssembler;
import com.example.foodapi.api.model.request.CidadeRequest;
import com.example.foodapi.api.model.response.CidadeResponse;
import com.example.foodapi.api.openapi.controller.CidadeControllerOpenApi;
import com.example.foodapi.domain.exception.EstadoNaoEncontradoException;
import com.example.foodapi.domain.exception.NegocioException;
import com.example.foodapi.domain.model.Cidade;
import com.example.foodapi.domain.repository.CidadeRepository;
import com.example.foodapi.domain.service.CidadeService;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeResponseAssembler cidadeModelAssembler;
	
	@Autowired
	private CidadeRequestDisassembler cidadeInputDisassembler;
	
	@Override
	@GetMapping
	public List<CidadeResponse> listar() {
		List<Cidade> todasCidades = cidadeRepository.findAll();
		
		return cidadeModelAssembler.toCollectionModel(todasCidades);
	}
	
	@Override
	@GetMapping("/{cidadeId}")
	public CidadeResponse buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
		
		return cidadeModelAssembler.toModel(cidade);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeResponse adicionar(@RequestBody @Valid CidadeRequest cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			
			cidade = cidadeService.salvar(cidade);
			
			return cidadeModelAssembler.toModel(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@Override
	@PutMapping("/{cidadeId}")
	public CidadeResponse atualizar(@PathVariable Long cidadeId,
			@RequestBody @Valid CidadeRequest cidadeInput) {
		try {
			Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
			
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			
			cidadeAtual = cidadeService.salvar(cidadeAtual);
			
			return cidadeModelAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@Override
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cidadeService.excluir(cidadeId);	
	}

}
