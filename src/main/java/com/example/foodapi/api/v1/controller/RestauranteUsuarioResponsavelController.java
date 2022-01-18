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
import com.example.foodapi.api.v1.assembler.UsuarioResponseAssembler;
import com.example.foodapi.api.v1.model.response.UsuarioResponse;
import com.example.foodapi.domain.model.Restaurante;
import com.example.foodapi.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

    @Autowired
    private RestauranteService cadastroRestaurante;
    
    @Autowired
    private UsuarioResponseAssembler usuarioModelAssembler;
    
    @Autowired
	private AlgaLinks algaLinks;
    
    @Override
	@GetMapping
	public CollectionModel<UsuarioResponse> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		CollectionModel<UsuarioResponse> usuariosModel = usuarioModelAssembler
				.toCollectionModel(restaurante.getResponsaveis())
					.removeLinks()
					.add(algaLinks.linkToRestauranteResponsaveis(restauranteId))
					.add(algaLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));

		usuariosModel.getContent().stream().forEach(usuarioModel -> {
			usuarioModel.add(algaLinks.linkToRestauranteResponsavelDesassociacao(
					restauranteId, usuarioModel.getId(), "desassociar"));
		});
		
		return usuariosModel;
	}

	@Override
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.desassociarResponsavel(restauranteId, usuarioId);
		
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.associarResponsavel(restauranteId, usuarioId);
		
		return ResponseEntity.noContent().build();
	}
}
