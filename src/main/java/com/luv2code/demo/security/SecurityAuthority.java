package com.luv2code.demo.security;

import org.springframework.security.core.GrantedAuthority;

import com.luv2code.demo.entity.Role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;
	private final Role role;
	
	@Override
	public String getAuthority() {
		return role.getName();
	}

}
