package com.cog.fundmatrix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cog.fundmatrix.dto.CreateFolioRequest;
import com.cog.fundmatrix.dto.FolioDto;
import com.cog.fundmatrix.service.FolioService;

@RestController
@RequestMapping("/api/folios")
public class FolioController {
	private FolioService folioService;
	
	
	public FolioController(FolioService folioService) {
		super();
		this.folioService = folioService;
	}


	@PostMapping("/")
	public ResponseEntity<FolioDto> createFolio(@RequestBody CreateFolioRequest dto)
	{
		return ResponseEntity.ok(folioService.createFolio(dto));
	}
	
	
	@GetMapping("/")
	public ResponseEntity<FolioDto> getFolios()
	{
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/{folioId}")
	public ResponseEntity<String> getFolioDetails()
	{
		return ResponseEntity.ok(null);
	}
	
	@PutMapping("/{folioId}")
	public ResponseEntity<String> updateFolio()
	{
		return ResponseEntity.ok(null);
	}
	
	@PutMapping("/{folioId}/status")
	public ResponseEntity<String> updateFolioStatus()
	{
		return ResponseEntity.ok(null);
	}
	
	
	
	
}
