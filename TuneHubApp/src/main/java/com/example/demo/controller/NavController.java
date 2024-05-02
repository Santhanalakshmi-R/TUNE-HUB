package com.example.demo.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NavController {
	@GetMapping("/map-register")
	public String registerMapping() {
		return "register";
	}
	@GetMapping("/map-login")
	public String loginMapping() {
		return "login";
	}
	@GetMapping("/map-songs")
	public String songMapping() {
		return "addsongs";
	}
	@GetMapping("/SamplePayment")
	public String samplePayment() {
		return "samplepayment";
	}
}