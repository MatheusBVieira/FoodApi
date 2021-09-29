package com.example.foodapi.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.foodapi.api.assembler.FotoProdutoResponseAssembler;
import com.example.foodapi.api.model.request.FotoProdutoRequest;
import com.example.foodapi.api.model.response.FotoProdutoResponse;
import com.example.foodapi.domain.exception.EntidadeNaoEncontradaException;
import com.example.foodapi.domain.model.FotoProduto;
import com.example.foodapi.domain.model.Produto;
import com.example.foodapi.domain.service.CatalogoFotoProdutoService;
import com.example.foodapi.domain.service.FotoStorageService;
import com.example.foodapi.domain.service.ProdutoService;


@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Autowired
	private FotoProdutoResponseAssembler fotoProdutoResponseAssembler;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoResponse atualizarFoto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId, @Valid FotoProdutoRequest fotoProdutoRequest) throws IOException {
		Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
		
		MultipartFile arquivo = fotoProdutoRequest.getArquivo();
		
		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoRequest.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto, arquivo.getInputStream());
		
		return fotoProdutoResponseAssembler.toResponse(fotoSalva);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoResponse buscar(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId) {
		FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);
		
		return fotoProdutoResponseAssembler.toResponse(fotoProduto);
	}
	
	@GetMapping
	public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId, @RequestHeader(name = "accept") String acceptHeader) 
					throws HttpMediaTypeNotAcceptableException {
		try {
			FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);
			
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
			
			InputStream inputStream = fotoStorage.recuperar(fotoProduto.getNomeArquivo());
			
			return ResponseEntity.ok()
					.contentType(mediaTypeFoto)
					.body(new InputStreamResource(inputStream));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, 
			List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
		
		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
		
		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}
	
}
