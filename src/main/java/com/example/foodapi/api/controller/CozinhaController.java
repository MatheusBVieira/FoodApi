package com.example.foodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.example.foodapi.api.assembler.CozinhaRequestDisassembler;
import com.example.foodapi.api.assembler.CozinhaResponseAssembler;
import com.example.foodapi.api.model.request.CozinhaRequest;
import com.example.foodapi.api.model.response.CozinhaResponse;
import com.example.foodapi.domain.model.Cozinha;
import com.example.foodapi.domain.repository.CozinhaRepository;
import com.example.foodapi.domain.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaService cadastroCozinha;
	
	@Autowired
	private CozinhaResponseAssembler cozinhaModelAssembler;
	
	@Autowired
	private CozinhaRequestDisassembler cozinhaInputDisassembler;
	
	@GetMapping
	public Page<CozinhaResponse> listar(@PageableDefault(size = 10) Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
		
		List<CozinhaResponse> cozinhasResponse = cozinhaModelAssembler
				.toCollectionModel(cozinhasPage.getContent());
		
		Page<CozinhaResponse> cozinhasResponsePage = new PageImpl<>(cozinhasResponse, pageable, 
				cozinhasPage.getTotalElements());
		
		return cozinhasResponsePage;
	}
	
	@GetMapping("/{cozinhaId}")
	public CozinhaResponse buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
		
		return cozinhaModelAssembler.toModel(cozinha);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaResponse adicionar(@RequestBody @Valid CozinhaRequest cozinhaRequest) {
		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaRequest);
		cozinha = cadastroCozinha.salvar(cozinha);
		
		return cozinhaModelAssembler.toModel(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public CozinhaResponse atualizar(@PathVariable Long cozinhaId,
			@RequestBody @Valid CozinhaRequest cozinhaRequest) {
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
		cozinhaInputDisassembler.copyToDomainObject(cozinhaRequest, cozinhaAtual);
		cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
		
		return cozinhaModelAssembler.toModel(cozinhaAtual);
	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinha.excluir(cozinhaId);
	}

}
