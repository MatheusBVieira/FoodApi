package com.example.foodapi.domain.repository;

import org.springframework.stereotype.Repository;

import com.example.foodapi.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

}