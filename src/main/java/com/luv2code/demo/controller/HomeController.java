package com.luv2code.demo.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
public class HomeController {

	@GetMapping("/admin")
	public String admin() {
		return "Hi ADMIN!";
	}
	
	@GetMapping("/admin/write")
	public String write() {
		return "Hi ADMIN , you can write!";
	}

	@GetMapping("/user")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public String user() {
		return "Hi user!";
	}

	@GetMapping("/root")
	@PreAuthorize("hasRole('ADMIN') and hasAuthority('ADMIN_READ') and hasAuthority('ADMIN_WRITE')")
	public String root() {
		return "Hi root!";
	}
	
	@GetMapping("/subroot")
	@PostAuthorize("hasRole('ADMIN') and hasAnyAuthority('ADMIN_READ' , 'ADMIN_WRITE')")
	public String subroot() {
		return "Hi subroot!";
	}

	@GetMapping("")
	public String demo() {
		return "Hi EveryBody!";
	}

}
