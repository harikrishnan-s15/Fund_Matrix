package com.cog.fundmatrix.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cog.fundmatrix.domain.FolioHolding;
import com.cog.fundmatrix.domain.InvestorFolio;
import com.cog.fundmatrix.dto.folioHolding.FolioHoldingDto;
import com.cog.fundmatrix.dto.folioHolding.HoldingSummary;
import com.cog.fundmatrix.dto.folioHolding.PortfolioResponse;
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
	
	
	public FolioHoldingDto getFolioHolding(UUID holdingId)
	{
		FolioHolding holding=holdingRepo.findById(holdingId).orElseThrow(()-> new ResouceNotFoundException("holding not found"));
		
		return mapper.toFolioHolding(holding);
	}
	
	public PortfolioResponse getPortfolio(UUID investorId)
	{
		List<InvestorFolio> folios=folioRepo.findAllByInvestor_Id(investorId);
		
		List<UUID> folioIds=folios.stream().map((folio)->folio.getFolioId()).toList();
		
		List<FolioHolding> holdings=holdingRepo.findAllByFolio_FolioIdIn(folioIds);
		
		BigDecimal totalValue=holdings.stream().map((holding)->holding.getCurrentValue()).reduce(BigDecimal.ZERO,(sum,value)->sum.add(value));
		BigDecimal totalGainOrLoss=holdings.stream().map((holding)->holding.getUnrealisedGainLoss()).reduce(BigDecimal.ZERO,(sum,value)->sum.add(value));
		
		List<HoldingSummary> holdingSummaries=holdings.stream().map((holding)->
		new HoldingSummary(holding.getSchemeId(),"xxx",holding.getUnits(),holding.getCurrentValue(),holding.getUnrealisedGainLoss())
				).toList();
		return new PortfolioResponse(investorId, totalValue, totalGainOrLoss, holdingSummaries);
	}
	
	
	
	
	
}
