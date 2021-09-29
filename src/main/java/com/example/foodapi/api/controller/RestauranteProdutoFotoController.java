package com.example.foodapi.api.controller;

import java.nio.file.Path;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.model.request.FotoProdutoRequest;


@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarFoto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId, @Valid FotoProdutoRequest fotoProdutoRequest) {
		
		var nomeArquivo = UUID.randomUUID().toString() 
				+ "_" + fotoProdutoRequest.getArquivo().getOriginalFilename();
		
		var arquivoFoto = Path.of("D:\\catalogo", nomeArquivo);
		
		System.out.println(fotoProdutoRequest.getDescricao());
		System.out.println(arquivoFoto);
		System.out.println(fotoProdutoRequest.getArquivo().getContentType());
		
		try {
			fotoProdutoRequest.getArquivo().transferTo(arquivoFoto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
