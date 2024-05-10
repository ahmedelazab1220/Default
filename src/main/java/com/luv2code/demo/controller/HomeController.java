package com.luv2code.demo.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
public class HomeController {

	@GetMapping("/admin")
	public String admin() {
		return "Hi ADMIN!";
	}

	@GetMapping("/user")
	@PreAuthorize("hasAuthority('USER')")
	public String user() {
		return "Hi user!";
	}

	@GetMapping("/root")
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public String root() {
		return "Hi root!";
	}

	@GetMapping("")
	public String demo() {
		return "Hi EveryBody!";
	}

	@PostMapping("/action")
	public String action() {
		return "post mapping";
	}

}
