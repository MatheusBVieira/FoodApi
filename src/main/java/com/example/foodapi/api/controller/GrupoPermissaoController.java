package com.example.foodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.assembler.PermissaoResponseAssembler;
import com.example.foodapi.api.model.response.PermissaoResponse;
import com.example.foodapi.domain.model.Grupo;
import com.example.foodapi.domain.service.GrupoService;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

	@Autowired
	private GrupoService GrupoService;
	
	@Autowired
	private PermissaoResponseAssembler permissaoResponseAssembler;
	
	@GetMapping
	public List<PermissaoResponse> listar(@PathVariable Long grupoId) {
		Grupo grupo = GrupoService.buscarOuFalhar(grupoId);
		
		return permissaoResponseAssembler.toCollectionModel(grupo.getPermissoes());
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		GrupoService.desassociarPermissao(grupoId, permissaoId);
	}
	
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		GrupoService.associarPermissao(grupoId, permissaoId);
	}

}
