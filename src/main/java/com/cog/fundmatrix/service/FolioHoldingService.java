package com.cog.fundmatrix.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cog.fundmatrix.domain.FolioHolding;
import com.cog.fundmatrix.domain.InvestorFolio;
import com.cog.fundmatrix.dto.folioHolding.FolioHoldingDto;
import com.cog.fundmatrix.exception.ResouceNotFoundException;
import com.cog.fundmatrix.repository.FolioHoldingRepository;
import com.cog.fundmatrix.repository.InvestorFolioRepository;

@Service
public class FolioHoldingService {

	
	InvestorFolioRepository folioRepo;
	FolioHoldingRepository holdingRepo;
	Mapper mapper;
	public FolioHoldingService(InvestorFolioRepository folioRepo, FolioHoldingRepository holdingRepo,Mapper mapper) {
		super();
		this.folioRepo = folioRepo;
		this.holdingRepo = holdingRepo;
		this.mapper=mapper;
	}
	
	public List<FolioHoldingDto> getAllFolioHoldings(UUID folioId)
	{
		InvestorFolio folio=folioRepo.findById(folioId).orElseThrow(()-> new ResouceNotFoundException("folio not found"));
		
		List<FolioHolding> holdings=holdingRepo.findAllByFolio_FolioId(folioId);
		
		
		return holdings.stream().map((holding)->mapper.toFolioHolding(holding)).toList();
		
	}
	
	
}
