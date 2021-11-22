package com.example.foodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodapi.api.assembler.ProdutoRequestDissasembler;
import com.example.foodapi.api.assembler.ProdutoResponseAssembler;
import com.example.foodapi.api.model.request.ProdutoRequest;
import com.example.foodapi.api.model.response.ProdutoResponse;
import com.example.foodapi.api.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.example.foodapi.domain.model.Produto;
import com.example.foodapi.domain.model.Restaurante;
import com.example.foodapi.domain.repository.ProdutoRepository;
import com.example.foodapi.domain.service.ProdutoService;
import com.example.foodapi.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private ProdutoService cadastroProduto;
    
    @Autowired
    private RestauranteService cadastroRestaurante;
    
    @Autowired
    private ProdutoResponseAssembler produtoResponseAssembler;
    
    @Autowired
    private ProdutoRequestDissasembler produtoRequestDisassembler;
    
    @Override
	@GetMapping
	public List<ProdutoResponse> listar(@PathVariable Long restauranteId,
			@RequestParam(required = false) boolean incluirInativos) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		 List<Produto> todosProdutos = incluirInativos ? produtoRepository.findTodosByRestaurante(restaurante)
				: produtoRepository.findAtivosByRestaurante(restaurante);
		
		return produtoResponseAssembler.toCollectionModel(todosProdutos);
	}
    
    @Override
	@GetMapping("/{produtoId}")
    public ProdutoResponse buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
        
        return produtoResponseAssembler.toResponse(produto);
    }
    
    @Override
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse adicionar(@PathVariable Long restauranteId,
            @RequestBody @Valid ProdutoRequest produtoRequest) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        
        Produto produto = produtoRequestDisassembler.toDomainObject(produtoRequest);
        produto.setRestaurante(restaurante);
        
        produto = cadastroProduto.salvar(produto);
        
        return produtoResponseAssembler.toResponse(produto);
    }
    
    @Override
	@PutMapping("/{produtoId}")
    public ProdutoResponse atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
            @RequestBody @Valid ProdutoRequest produtoRequest) {
        Produto produtoAtual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
        
        produtoRequestDisassembler.copyToDomainObject(produtoRequest, produtoAtual);
        
        produtoAtual = cadastroProduto.salvar(produtoAtual);
        
        return produtoResponseAssembler.toResponse(produtoAtual);
    }   
}
