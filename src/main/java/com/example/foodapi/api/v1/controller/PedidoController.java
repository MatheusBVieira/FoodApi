package com.example.foodapi.api.v1.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.v1.assembler.PedidoRequestDisassembler;
import com.example.foodapi.api.v1.assembler.PedidoResponseAssembler;
import com.example.foodapi.api.v1.assembler.PedidoResumoResponseAssembler;
import com.example.foodapi.api.v1.model.request.PedidoRequest;
import com.example.foodapi.api.v1.model.response.PedidoResponse;
import com.example.foodapi.api.v1.model.response.PedidoResumoResponse;
import com.example.foodapi.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.example.foodapi.core.data.PageWrapper;
import com.example.foodapi.core.data.PageableTranslator;
import com.example.foodapi.domain.exception.EntidadeNaoEncontradaException;
import com.example.foodapi.domain.exception.NegocioException;
import com.example.foodapi.domain.filter.PedidoFilter;
import com.example.foodapi.domain.model.Pedido;
import com.example.foodapi.domain.model.Usuario;
import com.example.foodapi.domain.repository.PedidoRepository;
import com.example.foodapi.domain.service.EmissaoPedidoService;
import com.example.foodapi.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(path = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Autowired
	private PedidoResponseAssembler pedidoResponseAssembler;

	@Autowired
	private PedidoResumoResponseAssembler pedidoResumoResponseAssembler;

	@Autowired
	private PedidoRequestDisassembler pedidoRequestDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

	@Override
	@GetMapping
	public PagedModel<PedidoResumoResponse> pesquisar(PedidoFilter filtro, 
	        @PageableDefault(size = 10) Pageable pageable) {
		Pageable pageableTraduzido = traduzirPageable(pageable);
		
		Page<Pedido> pedidosPage = pedidoRepository.findAll(
				PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);
		
		pedidosPage = new PageWrapper<>(pedidosPage, pageable);
		
		return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoResponseAssembler);
	}

	@Override
	@GetMapping("/{codigoPedido}")
	public PedidoResponse buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
		
		return pedidoResponseAssembler.toModel(pedido);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoResponse adicionar(@Valid @RequestBody PedidoRequest pedidoRequest) {
		try {
			Pedido novoPedido = pedidoRequestDisassembler.toDomainObject(pedidoRequest);

			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedido.emitir(novoPedido);

			return pedidoResponseAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"codigo", "codigo",
				"subtotal", "subtotal",
				"taxaFrete", "taxaFrete",
				"valorTotal", "valorTotal",
				"dataCriacao", "dataCriacao",
				"restaurante.nome", "restaurante.nome",
				"restaurante.id", "restaurante.id",
				"cliente.id", "cliente.id",
				"cliente.nome", "cliente.nome"
			);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}

}