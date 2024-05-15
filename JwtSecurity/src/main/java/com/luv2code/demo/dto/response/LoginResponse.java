package com.luv2code.demo.dto.response;

import java.util.Set;

import com.luv2code.demo.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {

    private String username;
	
	private String password;
	
	private Set<Role> roles;
	
	private String accessToken;

}
