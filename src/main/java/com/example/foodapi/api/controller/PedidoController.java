package com.example.foodapi.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.assembler.PedidoRequestDisassembler;
import com.example.foodapi.api.assembler.PedidoResponseAssembler;
import com.example.foodapi.api.assembler.PedidoResumoResponseAssembler;
import com.example.foodapi.api.model.request.PedidoRequest;
import com.example.foodapi.api.model.response.PedidoResponse;
import com.example.foodapi.api.model.response.PedidoResumoResponse;
import com.example.foodapi.core.data.PageableTranslator;
import com.example.foodapi.domain.exception.EntidadeNaoEncontradaException;
import com.example.foodapi.domain.exception.NegocioException;
import com.example.foodapi.domain.model.Pedido;
import com.example.foodapi.domain.model.Usuario;
import com.example.foodapi.domain.repository.PedidoRepository;
import com.example.foodapi.domain.repository.filter.PedidoFilter;
import com.example.foodapi.domain.service.EmissaoPedidoService;
import com.example.foodapi.infrastructure.repository.spec.PedidoSpecs;

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
	private PedidoResumoResponseAssembler pedidoResumoResponseAssembler;

	@Autowired
	private PedidoRequestDisassembler pedidoRequestDisassembler;

	@GetMapping
	public Page<PedidoResumoResponse> pesquisar(PedidoFilter filtro, 
	        @PageableDefault(size = 10) Pageable pageable) {
		pageable = traduzirPageable(pageable);
		
	    Page<Pedido> pedidosPage = pedidoRepository.findAll(
	            PedidoSpecs.usandoFiltro(filtro), pageable);
	    
	    List<PedidoResumoResponse> pedidosResumoResponse = pedidoResumoResponseAssembler
	            .toCollectionResponse(pedidosPage.getContent());
	    
	    Page<PedidoResumoResponse> pedidosResumoResponsePage = new PageImpl<>(
	            pedidosResumoResponse, pageable, pedidosPage.getTotalElements());
	    
	    return pedidosResumoResponsePage;
	}

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