package com.cog.fundmatrix.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cog.fundmatrix.domain.InvestorFolio;
import com.cog.fundmatrix.domain.KycRecord;
import com.cog.fundmatrix.domain.enums.KycStatus;
import com.cog.fundmatrix.dto.CreateFolioRequest;
import com.cog.fundmatrix.dto.FolioDto;
import com.cog.fundmatrix.dto.investorFolio.UpdateFolioRequest;
import com.cog.fundmatrix.repository.InvestorFolioRepository;
import com.cog.fundmatrix.repository.KycRecordRepository;

import tools.jackson.databind.ObjectMapper;


@Service
public class FolioService {

	private InvestorFolioRepository folioRepo;
	private KycRecordRepository kycRepo;
	
	public FolioService(InvestorFolioRepository folioRepo, KycRecordRepository kycRepo) {
		super();
		this.folioRepo = folioRepo;
		this.kycRepo = kycRepo;
	}
	
	private FolioDto maptoFolio(InvestorFolio folio)
	{
		return new FolioDto(folio.getFolioId(),folio.getInvestor(),folio.getDistibutor(),folio.getTaxStatus()
				,folio.getModeOfHolding(),folio.getNomineeDetails(),folio.getBankAccountRef(),folio.getStatus());
	}
	
	public FolioDto createFolio(CreateFolioRequest dto)
	{
		InvestorFolio folio=new InvestorFolio();
		
		KycRecord kycRecord=kycRepo.findByInvestor(dto.investorId()).orElseThrow(()->
			new RuntimeException("Kyc record not found for the investor")
		);
		if(kycRecord.getKycStatus()!= KycStatus.COMPLIANT)
		{
			throw new RuntimeException("Kyc is not completed for the investor");
		}
		
		if(dto.distributorId()!=null)
		{
			folio.setDistibutor(dto.distributorId());
		}
		else
		{
			folio.setInvestor(null);
		}
		
		folio.setInvestor(dto.investorId());
		folio.setBankAccountRef(dto.bankAccountRef());
		folio.setModeOfHolding(dto.modeOfHolding());
		folio.setTaxStatus(dto.taxStatus());
		folio.setStatus(dto.status());
		
		folio.setNomineeDetails(dto.nomineeDetails());
		
		InvestorFolio savedFolio=folioRepo.save(folio);
		
		
		return maptoFolio(savedFolio);
		
	}
	
	
	public List<FolioDto> getFoliosList()
	{
		List<InvestorFolio> folios=folioRepo.findAll();
		return folios.stream().map((folio)->maptoFolio(folio)).toList();
	}
	
	
	public FolioDto updateFolio(UUID folioId,UpdateFolioRequest dto)
	{
		InvestorFolio folio=folioRepo.findById(folioId).orElseThrow(()->new RuntimeException("no folio found"));
		
		if(dto.taxStatus()!=null)
		{
			folio.setTaxStatus(dto.taxStatus());
		}
		if(dto.modeOfHolding()!=null)
		{
			folio.setModeOfHolding(dto.modeOfHolding());
		}
		if(dto.bankAccountRef()!=null)
		{
			folio.setBankAccountRef(dto.bankAccountRef());
		}
		
		if(dto.nomineeDetails()!=null)
		{
			folio.setNomineeDetails(dto.nomineeDetails());
		}
		
		InvestorFolio savedFolio=folioRepo.save(folio);
		
		
		return maptoFolio(savedFolio);
		
	}
	
	
	
	public FolioDto getFolioById(UUID folioId)
	{
		InvestorFolio folio=folioRepo.findById(folioId).orElseThrow(()->new RuntimeException("no folio found"));
		
		return maptoFolio(folio);
		
	}
	
	
	
	
}
