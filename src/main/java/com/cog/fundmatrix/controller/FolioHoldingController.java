package com.cog.fundmatrix.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cog.fundmatrix.dto.folioHolding.FolioHoldingDto;
import com.cog.fundmatrix.service.FolioHoldingService;

@RestController
@RequestMapping("/holdings")
public class FolioHoldingController {
	
	private FolioHoldingService holdingService;
	
	
	
	public FolioHoldingController(FolioHoldingService holdingService) {
		super();
		this.holdingService = holdingService;
	}



	@GetMapping("/folios/{folioId}")
	public ResponseEntity<List<FolioHoldingDto>> getAllFolioHoldings(@PathVariable UUID folioId)
	{
		List<FolioHoldingDto> holdings=holdingService.getAllFolioHoldings(folioId);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(holdings);
	}

}
