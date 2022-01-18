package com.example.foodapi.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.v1.assembler.RestauranteApenasNomeResponseAssembler;
import com.example.foodapi.api.v1.assembler.RestauranteBasicoResponseAssembler;
import com.example.foodapi.api.v1.assembler.RestauranteRequestDisassembler;
import com.example.foodapi.api.v1.assembler.RestauranteResponseAssembler;
import com.example.foodapi.api.v1.model.request.RestauranteRequest;
import com.example.foodapi.api.v1.model.response.RestauranteApenasNomeResponse;
import com.example.foodapi.api.v1.model.response.RestauranteBasicoResponse;
import com.example.foodapi.api.v1.model.response.RestauranteResponse;
import com.example.foodapi.api.v1.openapi.controller.RestauranteControllerOpenApi;
import com.example.foodapi.domain.exception.CidadeNaoEncontradaException;
import com.example.foodapi.domain.exception.CozinhaNaoEncontradaException;
import com.example.foodapi.domain.exception.NegocioException;
import com.example.foodapi.domain.exception.RestauranteNaoEncontradoException;
import com.example.foodapi.domain.model.Restaurante;
import com.example.foodapi.domain.repository.RestauranteRepository;
import com.example.foodapi.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteBasicoResponseAssembler restauranteBasicoResponseAssembler;

	@Autowired
	private RestauranteApenasNomeResponseAssembler restauranteApenasNomeResponseAssembler;
	
	@Autowired
	private RestauranteResponseAssembler restauranteResponseAssembler;
	
	@Autowired
	private RestauranteRequestDisassembler restauranteRequestDisassembler;
	
	@Override
//	@JsonView(RestauranteView.Resumo.class)
    @GetMapping
    public CollectionModel<RestauranteBasicoResponse> listar() {
        return restauranteBasicoResponseAssembler
                .toCollectionModel(restauranteRepository.findAll());
    }
	
    @Override
//	@JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome")
    public CollectionModel<RestauranteApenasNomeResponse> listarApenasNomes() {
        return restauranteApenasNomeResponseAssembler
                .toCollectionModel(restauranteRepository.findAll());
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
	public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
		restauranteService.ativar(restauranteId);
	    
	    return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
		restauranteService.inativar(restauranteId);
	    
	    return ResponseEntity.noContent().build();
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
	public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
		restauranteService.abrir(restauranteId);
	    
	    return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
		restauranteService.fechar(restauranteId);
	    
	    return ResponseEntity.noContent().build();
	}

}
