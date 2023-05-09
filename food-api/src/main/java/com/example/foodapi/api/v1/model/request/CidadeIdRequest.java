package com.example.foodapi.api.v1.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeIdRequest {

	@Schema(example = "1")
	@NotNull
	private Long id;
	
}
