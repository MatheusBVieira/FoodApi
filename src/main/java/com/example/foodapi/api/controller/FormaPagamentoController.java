package com.example.foodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.assembler.FormaPagamentoRequestDisassembler;
import com.example.foodapi.api.assembler.FormaPagamentoResponseAssembler;
import com.example.foodapi.api.model.request.FormaPagamentoRequest;
import com.example.foodapi.api.model.response.FormaPagamentoResponse;
import com.example.foodapi.domain.model.FormaPagamento;
import com.example.foodapi.domain.repository.FormaPagamentoRepository;
import com.example.foodapi.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Autowired
	private FormaPagamentoResponseAssembler formaPagamentoResponseAssembler;

	@Autowired
	private FormaPagamentoRequestDisassembler formaPagamentoRequestDisassembler;

	@GetMapping
	public List<FormaPagamentoResponse> listar() {
		List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();

		return formaPagamentoResponseAssembler.toCollectionResponse(todasFormasPagamentos);
	}

	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoResponse buscar(@PathVariable Long formaPagamentoId) {
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

		return formaPagamentoResponseAssembler.toModel(formaPagamento);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoResponse adicionar(@RequestBody @Valid FormaPagamentoRequest formaPagamentoRequest) {
		FormaPagamento formaPagamento = formaPagamentoRequestDisassembler.toDomainObject(formaPagamentoRequest);

		formaPagamento = formaPagamentoService.salvar(formaPagamento);

		return formaPagamentoResponseAssembler.toModel(formaPagamento);
	}

	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoResponse atualizar(@PathVariable Long formaPagamentoId,
			@RequestBody @Valid FormaPagamentoRequest formaPagamentoRequest) {
		FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

		formaPagamentoRequestDisassembler.copyToDomainObject(formaPagamentoRequest, formaPagamentoAtual);

		formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);

		return formaPagamentoResponseAssembler.toModel(formaPagamentoAtual);
	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) {
		formaPagamentoService.excluir(formaPagamentoId);
	}
}
