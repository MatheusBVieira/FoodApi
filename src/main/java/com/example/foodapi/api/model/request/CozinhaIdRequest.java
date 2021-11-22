package com.example.foodapi.api.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaIdRequest {
	
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;

}
