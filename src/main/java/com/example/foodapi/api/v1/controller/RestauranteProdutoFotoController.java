package com.example.foodapi.api.v1.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.foodapi.api.v1.assembler.FotoProdutoResponseAssembler;
import com.example.foodapi.api.v1.model.request.FotoProdutoRequest;
import com.example.foodapi.api.v1.model.response.FotoProdutoResponse;
import com.example.foodapi.api.v1.openapi.controller.RestauranteProdutoFotoControllerOpenApi;
import com.example.foodapi.domain.exception.EntidadeNaoEncontradaException;
import com.example.foodapi.domain.model.FotoProduto;
import com.example.foodapi.domain.model.Produto;
import com.example.foodapi.domain.service.CatalogoFotoProdutoService;
import com.example.foodapi.domain.service.FotoStorageService;
import com.example.foodapi.domain.service.FotoStorageService.FotoRecuperada;
import com.example.foodapi.domain.service.ProdutoService;


@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenApi {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Autowired
	private FotoProdutoResponseAssembler fotoProdutoResponseAssembler;
	
	@Override
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoResponse atualizarFoto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId, @Valid FotoProdutoRequest fotoProdutoRequest,
			@RequestPart(required = true) MultipartFile arquivo) throws IOException {
		Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
		
//		MultipartFile arquivo = fotoProdutoRequest.getArquivo();
		
		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoRequest.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto, arquivo.getInputStream());
		
		return fotoProdutoResponseAssembler.toModel(fotoSalva);
	}
	
	@Override
	@GetMapping()
	public FotoProdutoResponse buscar(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId) {
		FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);
		
		return fotoProdutoResponseAssembler.toModel(fotoProduto);
	}
	
	@Override
	@GetMapping(produces = MediaType.ALL_VALUE)
	public ResponseEntity<?> servir(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId, @RequestHeader(name = "accept") String acceptHeader) 
					throws HttpMediaTypeNotAcceptableException {
		try {
			FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);
			
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
			
			FotoRecuperada fotoRecuperada = fotoStorage.recuperar(fotoProduto.getNomeArquivo());
			
			if (fotoRecuperada.temUrl()) {
				return ResponseEntity
						.status(HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
						.build();
			} else {
				return ResponseEntity.ok()
						.contentType(mediaTypeFoto)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));
			}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Override
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long restauranteId, 
			@PathVariable Long produtoId) {
		catalogoFotoProduto.excluir(restauranteId, produtoId);
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
