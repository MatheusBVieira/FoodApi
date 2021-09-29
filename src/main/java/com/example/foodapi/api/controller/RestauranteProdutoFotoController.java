package com.example.foodapi.api.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.foodapi.api.assembler.FotoProdutoResponseAssembler;
import com.example.foodapi.api.model.request.FotoProdutoRequest;
import com.example.foodapi.api.model.response.FotoProdutoResponse;
import com.example.foodapi.domain.model.FotoProduto;
import com.example.foodapi.domain.model.Produto;
import com.example.foodapi.domain.service.CatalogoFotoProdutoService;
import com.example.foodapi.domain.service.ProdutoService;


@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
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
	
}
