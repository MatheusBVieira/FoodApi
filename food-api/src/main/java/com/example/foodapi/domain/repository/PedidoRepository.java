package com.example.foodapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.foodapi.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

	Optional<Pedido> findByCodigo(String codigo);

	@Override
	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
	List<Pedido> findAll();
	
	@Query("select case when count(1) > 0 then true else false end "
			+ "from Pedido ped "
			+ "join ped.restaurante rest "
			+ "join rest.responsaveis resp "
			+ "where ped.codigo = :codigoPedido "
			+ "and resp.id = :usuarioId")
	boolean isPedidoGerenciadoPor(String codigoPedido, Long usuarioId);

}