package com.example.foodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.assembler.FormaPagamentoResponseAssembler;
import com.example.foodapi.api.model.response.FormaPagamentoResponse;
import com.example.foodapi.api.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.example.foodapi.domain.model.Restaurante;
import com.example.foodapi.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoResponseAssembler formaPagamentoModelAssembler;
	
	@Override
	@GetMapping
	public List<FormaPagamentoResponse> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		
		return formaPagamentoModelAssembler.toCollectionResponse(restaurante.getFormasPagamento());
	}
	
	@Override
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
	}
	
	@Override
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
	}

}
