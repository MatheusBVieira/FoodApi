package com.example.foodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodapi.domain.exception.EntidadeNaoEncontradaException;
import com.example.foodapi.domain.model.Cozinha;
import com.example.foodapi.domain.model.Restaurante;
import com.example.foodapi.domain.repository.CozinhaRepository;
import com.example.foodapi.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}

}
