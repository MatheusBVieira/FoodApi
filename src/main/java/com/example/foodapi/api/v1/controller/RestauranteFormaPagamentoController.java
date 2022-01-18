package com.example.foodapi.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.assembler.FormaPagamentoResponseAssembler;
import com.example.foodapi.api.v1.model.response.FormaPagamentoResponse;
import com.example.foodapi.api.v1.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.example.foodapi.domain.model.Restaurante;
import com.example.foodapi.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private FormaPagamentoResponseAssembler formaPagamentoModelAssembler;
	
	@Override
	@GetMapping
	public CollectionModel<FormaPagamentoResponse> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		
		CollectionModel<FormaPagamentoResponse> formasPagamentoModel 
			= formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamento())
				.removeLinks()
				.add(algaLinks.linkToRestauranteFormasPagamento(restauranteId))
				.add(algaLinks.linkToRestauranteFormaPagamentoAssociacao(restauranteId, "associar"));
		
		formasPagamentoModel.getContent().forEach(formaPagamentoModel -> {
			formaPagamentoModel.add(algaLinks.linkToRestauranteFormaPagamentoDesassociacao(
					restauranteId, formaPagamentoModel.getId(), "desassociar"));
		});
		
		return formasPagamentoModel;
	}
	
	@Override
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
		
		return ResponseEntity.noContent().build();
	}

}
