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
import com.example.foodapi.api.v1.assembler.PermissaoResponseAssembler;
import com.example.foodapi.api.v1.model.response.PermissaoResponse;
import com.example.foodapi.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.example.foodapi.domain.model.Grupo;
import com.example.foodapi.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private GrupoService GrupoService;
	
	@Autowired
	private PermissaoResponseAssembler permissaoResponseAssembler;
	
	@Override
	@GetMapping
	public CollectionModel<PermissaoResponse> listar(@PathVariable Long grupoId) {
	    Grupo grupo = GrupoService.buscarOuFalhar(grupoId);
	    
	    CollectionModel<PermissaoResponse> permissoesModel 
	        = permissaoResponseAssembler.toCollectionModel(grupo.getPermissoes())
	            .removeLinks()
	            .add(algaLinks.linkToGrupoPermissoes(grupoId))
	            .add(algaLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));
	    
	    permissoesModel.getContent().forEach(permissaoModel -> {
	        permissaoModel.add(algaLinks.linkToGrupoPermissaoDesassociacao(
	                grupoId, permissaoModel.getId(), "desassociar"));
	    });
	    
	    return permissoesModel;
	}    
	
	@Override
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		GrupoService.desassociarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		GrupoService.associarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}

}
