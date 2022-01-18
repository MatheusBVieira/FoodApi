package com.example.foodapi.core.springfox;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.http.HttpStatus;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.foodapi.api.exceptionhandler.Problem;
import com.example.foodapi.api.model.response.CidadeResponse;
import com.example.foodapi.api.model.response.CozinhaResponse;
import com.example.foodapi.api.model.response.EstadoResponse;
import com.example.foodapi.api.model.response.FormaPagamentoResponse;
import com.example.foodapi.api.model.response.GrupoResponse;
import com.example.foodapi.api.model.response.PedidoResumoResponse;
import com.example.foodapi.api.model.response.PermissaoResponse;
import com.example.foodapi.api.openapi.controller.PedidosResumoModelOpenApi;
import com.example.foodapi.api.openapi.model.CidadesModelOpenApi;
import com.example.foodapi.api.openapi.model.CozinhasModelOpenApi;
import com.example.foodapi.api.openapi.model.EstadosModelOpenApi;
import com.example.foodapi.api.openapi.model.FormasPagamentoModelOpenApi;
import com.example.foodapi.api.openapi.model.GruposModelOpenApi;
import com.example.foodapi.api.openapi.model.LinksModelOpenApi;
import com.example.foodapi.api.openapi.model.PageableModelOpenApi;
import com.example.foodapi.api.openapi.model.PermissoesModelOpenApi;
import com.fasterxml.classmate.TypeResolver;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {
	
	@Primary
	@Bean
	public LinkDiscoverers discoverers() {
	    List<LinkDiscoverer> plugins = new ArrayList<>();
	    plugins.add(new CollectionJsonLinkDiscoverer());
	    return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}
	
	@Bean
	public Docket apiDocket() {
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.example.foodapi.api"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
	            .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
	            .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
//	            .globalOperationParameters(Arrays.asList(
//						new ParameterBuilder()
//							.name("campos")
//							.description("Nomes das propriedades para filtrar na resposta, separados por vírgula")
//							.parameterType("query")
//							.modelRef(new ModelRef("string"))
//							.build())) adiciona o squiggly em todos os endpoints
	            .additionalModels(typeResolver.resolve(Problem.class))
	            .ignoredParameterTypes(ServletWebRequest.class,
						URL.class, URI.class, URLStreamHandler.class, Resource.class,
						File.class, InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(Page.class, CozinhaResponse.class),
						CozinhasModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
	                    typeResolver.resolve(Page.class, PedidoResumoResponse.class),
	                    PedidosResumoModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, CidadeResponse.class),
						CidadesModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
				        typeResolver.resolve(CollectionModel.class, EstadoResponse.class),
				        EstadosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
					    typeResolver.resolve(CollectionModel.class, FormaPagamentoResponse.class),
					    FormasPagamentoModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
					    typeResolver.resolve(CollectionModel.class, GrupoResponse.class),
					    GruposModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
				        typeResolver.resolve(CollectionModel.class, PermissaoResponse.class),
				        PermissoesModelOpenApi.class))
				.apiInfo(apiInfo())
				.tags(new Tag("Cidades", "Gerencia as cidades"),
				        new Tag("Grupos", "Gerencia os grupos de usuários"),
				        new Tag("Cozinhas", "Gerencia as cozinhas"),
				        new Tag("Formas de pagamento", "Gerencia as formas de pagamento"),
				        new Tag("Pedidos", "Gerencia os pedidos"),
				        new Tag("Restaurantes", "Gerencia os restaurantes"),
				        new Tag("Estados", "Gerencia os estados"),
				        new Tag("Produtos", "Gerencia os produtos de restaurantes"),
				        new Tag("Usuários", "Gerencia os usuários"),
				        new Tag("Estatísticas", "Estatísticas da AlgaFood"),
				        new Tag("Permissões", "Gerencia as permissões"));
	}
	
	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno do servidor")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build()
			);
	}
	
	private List<ResponseMessage> globalPostPutResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message("Requisição inválida (erro do cliente)")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno no servidor")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.NOT_ACCEPTABLE.value())
					.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
					.message("Requisição recusada porque o corpo está em um formato não suportado")
					.responseModel(new ModelRef("Problema"))
					.build()
			);
	}

	private List<ResponseMessage> globalDeleteResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message("Requisição inválida (erro do cliente)")
					.responseModel(new ModelRef("Problema"))
					.build(),
				new ResponseMessageBuilder()
					.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.message("Erro interno no servidor")
					.responseModel(new ModelRef("Problema"))
					.build()
			);
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("FoodApi")
				.description("Api aberta para clientes e restaurantes")
				.version("1")
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
