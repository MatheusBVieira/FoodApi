package com.example.foodapi.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.foodapi.api.v1.model.request.ItemPedidoRequest;
import com.example.foodapi.api.v1.model.response.EnderecoResponse;
import com.example.foodapi.api.v2.model.request.CidadeRequestV2;
import com.example.foodapi.domain.model.Cidade;
import com.example.foodapi.domain.model.Endereco;
import com.example.foodapi.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(CidadeRequestV2.class, Cidade.class)
		.addMappings(mapper -> mapper.skip(Cidade::setId));
		
//		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//			.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);
		
		var enderecoToEnderecoResponseTypeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoResponse.class);
		
		enderecoToEnderecoResponseTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoResponseDest, value) -> enderecoResponseDest.getCidade().setEstado(value));
		
		modelMapper.createTypeMap(ItemPedidoRequest.class, ItemPedido.class)
	    .addMappings(mapper -> mapper.skip(ItemPedido::setId));  
		
		return modelMapper;
	}
	
}

