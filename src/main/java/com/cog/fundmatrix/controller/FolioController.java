package com.cog.fundmatrix.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
		FolioDto response=folioService.createFolio(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<FolioDto>> getFolios()
	{
		List<FolioDto> response=folioService.getFoliosList();
		return ResponseEntity.ok(response);
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
