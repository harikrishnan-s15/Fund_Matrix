package com.cog.fundmatrix.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cog.fundmatrix.dto.KycRecordDto;
import com.cog.fundmatrix.dto.SubmitKycRequest;
import com.cog.fundmatrix.dto.kyc.KycStatusRequestDto;
import com.cog.fundmatrix.dto.kyc.KycStatusResposeDto;
import com.cog.fundmatrix.service.KycService;

@RestController
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
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	@GetMapping("/investor/{investorId}")
	public ResponseEntity<KycStatusResposeDto> getKycStatus(@PathVariable UUID investorId)
	{
		KycStatusResposeDto response=kycService.getKycStatus(investorId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{kycId}")
	public ResponseEntity<KycRecordDto> getKycDetails(@PathVariable String kycId)
	{
		UUID uuid=UUID.fromString(kycId);
		KycRecordDto response=kycService.getKycDetails(uuid);
		return ResponseEntity.ok(response);
	}
	
	
	@PutMapping("/{kycId}/verify")
	public ResponseEntity<KycRecordDto> verifyKyc(@PathVariable String kycId,@RequestBody KycStatusRequestDto dto)
	{
		UUID kycUuid=UUID.fromString(kycId);
		KycRecordDto response=kycService.kycStatusChange(kycUuid,dto.kycStatus());
		return ResponseEntity.ok(response);
	}
	
}
