package com.example.foodapi.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.foodapi.api.v1.AlgaLinks;
import com.example.foodapi.api.v1.controller.RestauranteController;
import com.example.foodapi.api.v1.model.response.RestauranteResponse;
import com.example.foodapi.core.security.AlgaSecurity;
import com.example.foodapi.domain.model.Restaurante;

@Component
public class RestauranteResponseAssembler 
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteResponse> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AlgaLinks algaLinks;
    
    @Autowired
    private AlgaSecurity algaSecurity; 
    
    public RestauranteResponseAssembler() {
        super(RestauranteController.class, RestauranteResponse.class);
    }
    
    @Override
    public RestauranteResponse toModel(Restaurante restaurante) {
    	RestauranteResponse restauranteModel = createModelWithId(restaurante.getId(), restaurante);
        modelMapper.map(restaurante, restauranteModel);
        
        if (algaSecurity.podeConsultarRestaurantes()) {
            restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
        }
        
        if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
            if (restaurante.ativacaoPermitida()) {
                restauranteModel.add(
                        algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
            }

            if (restaurante.inativacaoPermitida()) {
                restauranteModel.add(
                        algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
            }
        }
        
        if (algaSecurity.podeGerenciarFuncionamentoRestaurantes(restaurante.getId())) {
            if (restaurante.aberturaPermitida()) {
                restauranteModel.add(
                        algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
            }

            if (restaurante.fechamentoPermitido()) {
                restauranteModel.add(
                        algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
            }
        }
        
        if (algaSecurity.podeConsultarRestaurantes()) {
            restauranteModel.add(algaLinks.linkToProdutos(restaurante.getId(), "produtos"));
        }
        
        if (algaSecurity.podeConsultarCozinhas()) {
            restauranteModel.getCozinha().add(
                    algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
        }
        
        if (algaSecurity.podeConsultarCidades()) {
            if (restauranteModel.getEndereco() != null 
                    && restauranteModel.getEndereco().getCidade() != null) {
                restauranteModel.getEndereco().getCidade().add(
                        algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
            }
        }
        
        if (algaSecurity.podeConsultarRestaurantes()) {
            restauranteModel.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(), 
                    "formas-pagamento"));
        }
        
        if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
            restauranteModel.add(algaLinks.linkToRestauranteResponsaveis(restaurante.getId(), 
                    "responsaveis"));
        }
        
        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteResponse> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteResponse> collectionModel = super.toCollectionModel(entities);
        
        if (algaSecurity.podeConsultarRestaurantes()) {
            collectionModel.add(algaLinks.linkToRestaurantes());
        }
        
        return collectionModel;
    }   
}
