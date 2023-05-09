package com.example.foodapi.api.v1.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.example.foodapi.core.validation.FileContentType;
import com.example.foodapi.core.validation.FileSize;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoRequest {

	@Schema(description = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)")
	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile arquivo;

	@Schema(description = "Descrição da foto do produto")
	@NotBlank
	private String descricao;

}
