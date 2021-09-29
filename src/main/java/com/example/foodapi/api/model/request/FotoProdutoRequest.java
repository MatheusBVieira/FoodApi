package com.example.foodapi.api.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.example.foodapi.core.validation.FileSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoRequest {

	@NotNull
	@FileSize(max = "500KB")
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;
	
}
