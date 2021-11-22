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

import com.example.foodapi.api.assembler.RestauranteRequestDisassembler;
import com.example.foodapi.api.assembler.RestauranteResponseAssembler;
import com.example.foodapi.api.model.RestauranteView;
import com.example.foodapi.api.model.request.RestauranteRequest;
import com.example.foodapi.api.model.response.RestauranteResponse;
import com.example.foodapi.api.openapi.controller.RestauranteControllerOpenApi;
import com.example.foodapi.domain.exception.CidadeNaoEncontradaException;
import com.example.foodapi.domain.exception.CozinhaNaoEncontradaException;
import com.example.foodapi.domain.exception.NegocioException;
import com.example.foodapi.domain.exception.RestauranteNaoEncontradoException;
import com.example.foodapi.domain.model.Restaurante;
import com.example.foodapi.domain.repository.RestauranteRepository;
import com.example.foodapi.domain.service.RestauranteService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(path = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteResponseAssembler restauranteResponseAssembler;
	
	@Autowired
	private RestauranteRequestDisassembler restauranteRequestDisassembler;
	
	@Override
	@JsonView(RestauranteView.Resumo.class)
	@GetMapping
	public List<RestauranteResponse> listar() {
		return restauranteResponseAssembler.toCollectionModel(restauranteRepository.findAll());
	}
	
	@Override
	@JsonView(RestauranteView.ApenasNome.class)
	@GetMapping(params = "projecao=apenas-nome")
	public List<RestauranteResponse> listarApenasNomes() {
		return listar();
	}
	
	@Override
	@GetMapping("/{restauranteId}")
	public RestauranteResponse buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		
		return restauranteResponseAssembler.toModel(restaurante);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteResponse adicionar(@RequestBody @Valid RestauranteRequest restauranteRequest) {
		try {
			Restaurante restaurante = restauranteRequestDisassembler.toDomainObject(restauranteRequest);
			
			return restauranteResponseAssembler.toModel(restauranteService.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@Override
	@PutMapping("/{restauranteId}")
	public RestauranteResponse atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteRequest restauranteRequest) {
		try {
			Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);
			
			restauranteRequestDisassembler.copyToDomainObject(restauranteRequest, restauranteAtual);
			
			return restauranteResponseAssembler.toModel(restauranteService.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@Override
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId) {
		restauranteService.ativar(restauranteId);
	}
	
	@Override
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId) {
		restauranteService.inativar(restauranteId);
	}
	
	@Override
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			restauranteService.ativar(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@Override
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		try {
			restauranteService.inativar(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@Override
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long restauranteId) {
		restauranteService.abrir(restauranteId);
	}

	@Override
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long restauranteId) {
		restauranteService.fechar(restauranteId);
	}  

}
