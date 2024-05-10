package com.luv2code.demo.controller;

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
	public String user() {
		return "Hi user!";
	}

	@GetMapping("/root")
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
