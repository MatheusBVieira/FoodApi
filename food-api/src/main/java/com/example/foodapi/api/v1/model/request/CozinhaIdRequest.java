package com.example.foodapi.api.v1.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaIdRequest {
	
	@NotNull
	private Long id;

}
