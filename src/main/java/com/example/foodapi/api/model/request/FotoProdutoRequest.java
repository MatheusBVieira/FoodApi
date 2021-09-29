package com.example.foodapi.api.model.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoRequest {

	private MultipartFile arquivo;
	private String descricao;
	
}
