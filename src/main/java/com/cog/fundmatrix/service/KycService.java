package com.cog.fundmatrix.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cog.fundmatrix.domain.KycRecord;
import com.cog.fundmatrix.domain.enums.KycStatus;
import com.cog.fundmatrix.dto.KycRecordDto;
import com.cog.fundmatrix.dto.SubmitKycRequest;
import com.cog.fundmatrix.dto.kyc.KycStatusResposeDto;
import com.cog.fundmatrix.repository.KycRecordRepository;

@Service
public class KycService {

	
	private KycRecordRepository kycRepo;

	public KycService(KycRecordRepository kycRepo) {
		super();
		this.kycRepo = kycRepo;
	}
	
	public KycRecordDto mapToKycDto(KycRecord k) {
          return new KycRecordDto(k.getId(),k.getInvestor(),k.getKycType(),k.getDocumentType(),k.getDocumentRef(),k.getVerifiedDate(),k.getKycStatus());
    }
	
	public KycRecordDto submitKyc(SubmitKycRequest dto)
	{
		KycRecord kycRecord=new KycRecord();
		kycRecord.setDocumentRef(dto.documentRef());
		kycRecord.setDocumentType(dto.documentType());
		kycRecord.setInvestor(dto.investorId());
		kycRecord.setKycType(dto.kycType());
		kycRecord.setKycStatus(KycStatus.PENDING);
		
		
		KycRecord savedKyc=kycRepo.save(kycRecord);
		
		return mapToKycDto(savedKyc);
	}
	
	
	public KycRecordDto getKycDetails(UUID id)
	{
		KycRecord record=kycRepo.findById(id).orElseThrow(()->
		new RuntimeException("no record found")
		);
		return mapToKycDto(record);
	}
	
	public KycStatusResposeDto getKycStatus(UUID investorId)
	{
		KycRecord record=kycRepo.findByInvestor(investorId).orElseThrow(()->
		new RuntimeException("no record found")
		);
		KycStatusResposeDto response=new KycStatusResposeDto(record.getId(),record.getKycStatus());
		
		return response;
	}
	
	public KycRecordDto verifyKyc(UUID kycId)
	{
		KycRecord record=kycRepo.findById(kycId).orElseThrow(()->
		new RuntimeException("no record for id "+kycId)
		);
		record.setKycStatus(KycStatus.COMPLIANT);
		record.setVerifiedDate(LocalDate.now());
		KycRecord saved=kycRepo.save(record);
		
		return mapToKycDto(saved);
		
	}
	
}
