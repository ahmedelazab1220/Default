package com.luv2code.demo.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.luv2code.demo.dto.request.LoginRequest;
import com.luv2code.demo.dto.request.RegisterRequest;
import com.luv2code.demo.dto.response.LoginResponse;
import com.luv2code.demo.entity.User;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemMapper {

	User registerRequstToUser(RegisterRequest registerRequest);
	
	LoginResponse loginRequestToLoginResponse(LoginRequest loginRequest);
	
}
