package com.example.foodapi.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.example.foodapi.api.v1.model.request.PedidoRequest;
import com.example.foodapi.api.v1.model.response.PedidoResponse;
import com.example.foodapi.api.v1.model.response.PedidoResumoResponse;
import com.example.foodapi.domain.filter.PedidoFilter;

public interface PedidoControllerOpenApi {

	PagedModel<PedidoResumoResponse> pesquisar(PedidoFilter filtro, Pageable pageable);

	PedidoResponse adicionar(PedidoRequest pedidoRequest);

	PedidoResponse buscar(String codigoPedido);
}
