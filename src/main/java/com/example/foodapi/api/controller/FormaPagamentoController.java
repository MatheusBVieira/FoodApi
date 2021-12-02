package com.example.foodapi.api.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.example.foodapi.api.assembler.FormaPagamentoRequestDisassembler;
import com.example.foodapi.api.assembler.FormaPagamentoResponseAssembler;
import com.example.foodapi.api.model.request.FormaPagamentoRequest;
import com.example.foodapi.api.model.response.FormaPagamentoResponse;
import com.example.foodapi.api.openapi.controller.FormaPagamentoControllerOpenApi;
import com.example.foodapi.domain.model.FormaPagamento;
import com.example.foodapi.domain.repository.FormaPagamentoRepository;
import com.example.foodapi.domain.service.FormaPagamentoService;

@RestController
@RequestMapping(path = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Autowired
	private FormaPagamentoResponseAssembler formaPagamentoResponseAssembler;

	@Autowired
	private FormaPagamentoRequestDisassembler formaPagamentoRequestDisassembler;

	@Override
	@GetMapping
	public ResponseEntity<CollectionModel<FormaPagamentoResponse>> listar(ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag = "0";
		
		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();
		
		if (dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		
		if (request.checkNotModified(eTag)) {
			return null;
		}
		
		List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();

		CollectionModel<FormaPagamentoResponse> formasPagamentosModel = formaPagamentoResponseAssembler.toCollectionModel(todasFormasPagamentos);
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.eTag(eTag)
				.body(formasPagamentosModel);
	}

	@Override
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoResponse> buscar(@PathVariable Long formaPagamentoId, 
			ServletWebRequest request) {
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
		
		String eTag = "0";
		
		OffsetDateTime dataAtualizacao = formaPagamentoRepository
	            .getDataAtualizacaoById(formaPagamentoId);
	    
		if (dataAtualizacao != null) {
	        eTag = String.valueOf(dataAtualizacao.toEpochSecond());
	    }
	    
	    if (request.checkNotModified(eTag)) {
	        return null;
	    }
		
		FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

		FormaPagamentoResponse formaPagamentoModel =  formaPagamentoResponseAssembler.toModel(formaPagamento);
		
		return ResponseEntity.ok()
			      .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
			      .eTag(eTag)
			      .body(formaPagamentoModel);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoResponse adicionar(@RequestBody @Valid FormaPagamentoRequest formaPagamentoRequest) {
		FormaPagamento formaPagamento = formaPagamentoRequestDisassembler.toDomainObject(formaPagamentoRequest);

		formaPagamento = formaPagamentoService.salvar(formaPagamento);

		return formaPagamentoResponseAssembler.toModel(formaPagamento);
	}

	@Override
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoResponse atualizar(@PathVariable Long formaPagamentoId,
			@RequestBody @Valid FormaPagamentoRequest formaPagamentoRequest) {
		FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

		formaPagamentoRequestDisassembler.copyToDomainObject(formaPagamentoRequest, formaPagamentoAtual);

		formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);

		return formaPagamentoResponseAssembler.toModel(formaPagamentoAtual);
	}

	@Override
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) {
		formaPagamentoService.excluir(formaPagamentoId);
	}
}
