package com.example.foodapi.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.foodapi.api.model.response.EnderecoResponse;
import com.example.foodapi.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
//		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//			.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);
		
		var enderecoToEnderecoResponseTypeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoResponse.class);
		
		enderecoToEnderecoResponseTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoResponseDest, value) -> enderecoResponseDest.getCidade().setEstado(value));
		
		return modelMapper;
	}
	
}

