package com.example.food.auth.core;
import java.util.Collections;

import org.springframework.security.core.userdetails.User;

import com.example.food.auth.domain.Usuario;

import lombok.Getter;

@Getter
public class AuthUser extends User {

	private static final long serialVersionUID = 1L;
	
	private String fullName;
	
	public AuthUser(Usuario usuario) {
		super(usuario.getEmail(), usuario.getSenha(), Collections.emptyList());
		
		this.fullName = usuario.getNome();
	}
	
}