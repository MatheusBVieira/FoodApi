package com.example.foodapi.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoProdutoResponse {

	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;
	
}