package com.example.foodapi.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.foodapi.api.v1.controller.EstatisticasController.EstatisticasResponse;
import com.example.foodapi.domain.filter.VendaDiariaFilter;
import com.example.foodapi.domain.model.dto.VendaDiaria;

public interface EstatisticasControllerOpenApi {

	EstatisticasResponse estatisticas();

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

	ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);
}