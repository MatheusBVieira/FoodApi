package com.example.foodapi.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.assembler.ProdutoRequestDissasembler;
import com.example.foodapi.api.v1.assembler.ProdutoResponseAssembler;
import com.example.foodapi.api.v1.model.request.ProdutoRequest;
import com.example.foodapi.api.v1.model.response.ProdutoResponse;
import com.example.foodapi.api.v1.openapi.controller.RestauranteProdutoControllerOpenApi;
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
    private AlgaLinks algaLinks;
    
    @Autowired
    private RestauranteService restauranteService;
    
    @Autowired
    private ProdutoResponseAssembler produtoResponseAssembler;
    
    @Autowired
    private ProdutoRequestDissasembler produtoRequestDisassembler;
    
    @Override
    @GetMapping
    public CollectionModel<ProdutoResponse> listar(@PathVariable Long restauranteId,
            @RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        
        List<Produto> todosProdutos = null;
        
        if (incluirInativos) {
            todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
        } else {
            todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
        }
        
        return produtoResponseAssembler.toCollectionModel(todosProdutos)
                .add(algaLinks.linkToProdutos(restauranteId));
    }
    
    @Override
	@GetMapping("/{produtoId}")
    public ProdutoResponse buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
        
        return produtoResponseAssembler.toModel(produto);
    }
    
    @Override
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse adicionar(@PathVariable Long restauranteId,
            @RequestBody @Valid ProdutoRequest produtoRequest) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        
        Produto produto = produtoRequestDisassembler.toDomainObject(produtoRequest);
        produto.setRestaurante(restaurante);
        
        produto = cadastroProduto.salvar(produto);
        
        return produtoResponseAssembler.toModel(produto);
    }
    
    @Override
	@PutMapping("/{produtoId}")
    public ProdutoResponse atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
            @RequestBody @Valid ProdutoRequest produtoRequest) {
        Produto produtoAtual = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
        
        produtoRequestDisassembler.copyToDomainObject(produtoRequest, produtoAtual);
        
        produtoAtual = cadastroProduto.salvar(produtoAtual);
        
        return produtoResponseAssembler.toModel(produtoAtual);
    }   
}
