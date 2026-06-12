package com.cog.fundmatrix.service;

import org.springframework.stereotype.Service;

import com.cog.fundmatrix.domain.KycRecord;
import com.cog.fundmatrix.domain.enums.KycStatus;
import com.cog.fundmatrix.dto.KycRecordDto;
import com.cog.fundmatrix.dto.SubmitKycRequest;
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
	
	
}
