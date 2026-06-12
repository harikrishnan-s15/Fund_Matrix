package com.cog.fundmatrix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cog.fundmatrix.dto.KycRecordDto;
import com.cog.fundmatrix.dto.SubmitKycRequest;
import com.cog.fundmatrix.service.KycService;

@Controller
@RequestMapping("/api/kyc")
public class KycController {

	private KycService kycService;
	
	
	public KycController(KycService kycService) {
		super();
		this.kycService = kycService;
	}

	@PostMapping("/")
	public ResponseEntity<KycRecordDto > submitKyc(@RequestBody SubmitKycRequest kyc)
	{
		KycRecordDto response=kycService.submitKyc(kyc);
		return ResponseEntity.ok(response);
		
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
