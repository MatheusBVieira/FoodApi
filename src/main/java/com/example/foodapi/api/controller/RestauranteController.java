package com.example.foodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.foodapi.api.model.request.RestauranteRequest;
import com.example.foodapi.api.model.response.RestauranteResponse;
import com.example.foodapi.domain.exception.CozinhaNaoEncontradaException;
import com.example.foodapi.domain.exception.NegocioException;
import com.example.foodapi.domain.model.Restaurante;
import com.example.foodapi.domain.repository.RestauranteRepository;
import com.example.foodapi.domain.service.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteResponseAssembler restauranteResponseAssembler;
	
	@Autowired
	private RestauranteRequestDisassembler restauranteRequestDisassembler;
	
	@GetMapping
	public List<RestauranteResponse> listar() {
		return restauranteResponseAssembler.toCollectionModel(restauranteRepository.findAll());
	}
	
	@GetMapping("/{restauranteId}")
	public RestauranteResponse buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		
		return restauranteResponseAssembler.toModel(restaurante);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteResponse adicionar(@RequestBody @Valid RestauranteRequest restauranteRequest) {
		try {
			Restaurante restaurante = restauranteRequestDisassembler.toDomainObject(restauranteRequest);
			
			return restauranteResponseAssembler.toModel(restauranteService.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public RestauranteResponse atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteRequest restauranteRequest) {
		try {
			Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);
			
			restauranteRequestDisassembler.copyToDomainObject(restauranteRequest, restauranteAtual);
			
			return restauranteResponseAssembler.toModel(restauranteService.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

}
