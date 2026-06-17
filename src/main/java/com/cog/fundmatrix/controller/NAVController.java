package com.cog.fundmatrix.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cog.fundmatrix.domain.NAVRecord;
import com.cog.fundmatrix.dto.NAVRecordDto.CreateNAVRequest;
import com.cog.fundmatrix.dto.NAVRecordDto.LatestNAVSummary;
import com.cog.fundmatrix.dto.NAVRecordDto.NAVResponse;
import com.cog.fundmatrix.dto.NAVRecordDto.ReviseNAVRequest;
import com.cog.fundmatrix.service.NAVService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/nav")
@SecurityRequirement(name = "bearerAuth")
public class NAVController {
	
	
//	POST /api/nav – Enter daily NAV
//
//	GET /api/nav/scheme/{schemeId} – Get NAV history
//
//	GET /api/nav/latest – Latest NAV for all schemes
//
//	PUT /api/nav/{navRecordId} – Revise NAV
//
//	PUT /api/nav/{navRecordId}/publish – Publish NAV
	
	private  NAVService navService;
	

	public NAVController(NAVService navService) {
		super();
		this.navService = navService;
	}

	@PostMapping
	public ResponseEntity<NAVResponse> createNAV(@RequestBody CreateNAVRequest request){
		return  ResponseEntity.status(HttpStatus.CREATED).body(navService.CreateNAV(request)); 
	}
	
	@GetMapping("/scheme/{schemeId}")
	public ResponseEntity<List<NAVResponse>> getNAVHistory(@PathVariable String schemeId){
		return ResponseEntity.ok(navService.getNAVHistory(schemeId));
	}
	
	@GetMapping("/latest")
	public ResponseEntity<List<LatestNAVSummary>> getLatestNAV(){
		return ResponseEntity.ok(navService.getLatestNAV());
	}
	
	@PutMapping("/{navRecordId}/{userId}/publish")
	public ResponseEntity<NAVResponse> publishNAV(@PathVariable UUID navRecordId,@PathVariable String userId){
		return ResponseEntity.status(HttpStatus.CREATED).body(navService.publishNAV(navRecordId,userId));
	}
	
	@PutMapping("/{navRecordId}/{userId}")
	public ResponseEntity<NAVResponse> reviseNAV(@PathVariable UUID navRecordId,@RequestBody ReviseNAVRequest request,@PathVariable String userId){
		return ResponseEntity.status(HttpStatus.CREATED).body(navService.reviseNAV(navRecordId,request,userId));
	}
}
