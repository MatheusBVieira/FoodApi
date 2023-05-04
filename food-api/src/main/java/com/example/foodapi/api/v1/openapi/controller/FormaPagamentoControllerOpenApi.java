package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.example.foodapi.api.v1.model.request.FormaPagamentoRequest;
import com.example.foodapi.api.v1.model.response.FormaPagamentoResponse;

public interface FormaPagamentoControllerOpenApi {

	ResponseEntity<CollectionModel<FormaPagamentoResponse>> listar(ServletWebRequest request);

	ResponseEntity<FormaPagamentoResponse> buscar(Long formaPagamentoId, ServletWebRequest request);

	FormaPagamentoResponse adicionar(FormaPagamentoRequest formaPagamentoRequest);

	FormaPagamentoResponse atualizar(Long formaPagamentoId, FormaPagamentoRequest formaPagamentoRequest);

	void remover(Long formaPagamentoId);
}
