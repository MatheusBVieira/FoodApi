package com.example.foodapi.domain.repository;

import org.springframework.stereotype.Repository;

import com.example.foodapi.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

}
