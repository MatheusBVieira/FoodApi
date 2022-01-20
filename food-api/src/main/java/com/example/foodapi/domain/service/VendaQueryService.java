package com.example.foodapi.domain.service;

import java.util.List;

import com.example.foodapi.domain.filter.VendaDiariaFilter;
import com.example.foodapi.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}
