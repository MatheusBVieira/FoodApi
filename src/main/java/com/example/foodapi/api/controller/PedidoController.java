package com.example.foodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.foodapi.domain.exception.EntidadeNaoEncontradaException;
import com.example.foodapi.domain.exception.NegocioException;
import com.example.foodapi.domain.model.Pedido;
import com.example.foodapi.domain.model.Usuario;
import com.example.foodapi.domain.repository.PedidoRepository;
import com.example.foodapi.domain.service.EmissaoPedidoService;

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
	public List<PedidoResumoResponse> listar() {
		List<Pedido> todosPedidos = pedidoRepository.findAll();

		return PedidoResumoResponseAssembler.toCollectionResponse(todosPedidos);
	}

	@GetMapping("/{pedidoId}")
	public PedidoResponse buscar(@PathVariable Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);

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