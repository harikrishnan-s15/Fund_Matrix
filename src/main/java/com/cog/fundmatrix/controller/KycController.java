package com.cog.fundmatrix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cog.fundmatrix.dto.SubmitKycRequest;

@Controller
@RequestMapping("/api/kyc")
public class KycController {

	@PostMapping("/")
	public ResponseEntity<String > submitKyc(SubmitKycRequest kyc)
	{
		return null;
		
	}
	
	@GetMapping("/investor/{id}")
	public ResponseEntity<String> getKycStatus(@PathVariable String id)
	{
		return null;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<String> getKycDetails(@PathVariable String id)
	{
		return null;
	}
	
	
	@PutMapping("/{id}/verify")
	public ResponseEntity<String> verifyKyc(@PathVariable String id)
	{
		return null;
	}
	
}
