package com.example.foodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.assembler.PedidoRequestDisassembler;
import com.example.foodapi.api.assembler.PedidoResponseAssembler;
import com.example.foodapi.api.assembler.PedidoResumoResponseAssembler;
import com.example.foodapi.api.model.request.PedidoRequest;
import com.example.foodapi.api.model.response.PedidoResponse;
import com.example.foodapi.api.model.response.PedidoResumoResponse;
import com.example.foodapi.domain.exception.EntidadeNaoEncontradaException;
import com.example.foodapi.domain.exception.NegocioException;
import com.example.foodapi.domain.model.Pedido;
import com.example.foodapi.domain.model.Usuario;
import com.example.foodapi.domain.repository.PedidoRepository;
import com.example.foodapi.domain.service.EmissaoPedidoService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Autowired
	private PedidoResponseAssembler pedidoResponseAssembler;

	@Autowired
	private PedidoResumoResponseAssembler PedidoResumoResponseAssembler;

	@Autowired
	private PedidoRequestDisassembler pedidoRequestDisassembler;

	@GetMapping
	public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
		List<Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoResumoResponse> pedidosModel = PedidoResumoResponseAssembler.toCollectionResponse(pedidos);  
		
		MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);
		
		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
		
		if (StringUtils.isNotBlank(campos)) {
			filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
		}
		
		pedidosWrapper.setFilters(filterProvider);
		
		return pedidosWrapper;
	}
	
//	@GetMapping
//	public List<PedidoResumoResponse> listar() {
//		List<Pedido> todosPedidos = pedidoRepository.findAll();
//
//		return PedidoResumoResponseAssembler.toCollectionResponse(todosPedidos);
//	}

	@GetMapping("/{codigoPedido}")
	public PedidoResponse buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
		
		return pedidoResponseAssembler.toResponse(pedido);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoResponse adicionar(@Valid @RequestBody PedidoRequest pedidoRequest) {
		try {
			Pedido novoPedido = pedidoRequestDisassembler.toDomainObject(pedidoRequest);

			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedido.emitir(novoPedido);

			return pedidoResponseAssembler.toResponse(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

}