package com.cog.fundmatrix.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cog.fundmatrix.domain.NAVRecord;
import com.cog.fundmatrix.domain.enums.NAVStatus;
import com.cog.fundmatrix.dto.NAVRecordDto.CreateNAVRequest;
import com.cog.fundmatrix.dto.NAVRecordDto.LatestNAVSummary;
import com.cog.fundmatrix.dto.NAVRecordDto.NAVResponse;
import com.cog.fundmatrix.dto.NAVRecordDto.ReviseNAVRequest;
import com.cog.fundmatrix.exception.NAVException;
import com.cog.fundmatrix.exception.NAVException.InvalidNAVStateTransitionException;
import com.cog.fundmatrix.exception.NAVException.NAVAlreadyExistsException;
import com.cog.fundmatrix.exception.NAVException.NAVNotFoundException;
import com.cog.fundmatrix.repository.NAVRecordRepository;

import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@NoArgsConstructor
public class NAVService {
	@Autowired
    private NAVRecordRepository navRepository;
    
    
    
    public BigDecimal computeAUM(BigDecimal navValue,BigDecimal units) {
    	if(navValue==null||units==null) return BigDecimal.ZERO;
    	return navValue.multiply(units).setScale(2, RoundingMode.HALF_DOWN);
    }
    
	public NAVResponse CreateNAV(CreateNAVRequest record) {
		
		log.info("Creating NAV for scheme={} option={} date={}",
			record.schemeId(),record.optionId(),record.navDate());
		boolean exists=navRepository.existsBySchemeIdAndOptionIdAndNavDateAndStatusNot(record.schemeId(), record.optionId(), record.navDate(), NAVStatus.REVISED);
		if(exists) {
			throw new NAVAlreadyExistsException(
				String.format(
			           "An active NAV already exists for scheme %s, option %s on %s. " +
			           "To correct a published NAV, use the revise endpoint.",
			                record.schemeId(), record.optionId(), record.navDate()
			     )       
			);
		}
		
		
		BigDecimal aum=computeAUM(record.navValue(),record.totalUnitsOutstanding());
		
		
		NAVRecord rec=new NAVRecord();
		rec.setSchemeId(record.schemeId());
		rec.setOptionId(record.optionId());
		rec.setNavDate(record.navDate());
		rec.setNavValue(record.navValue());
		rec.setTotalAUM(aum);
		rec.setTotalUnitsOutstanding(record.totalUnitsOutstanding());
		rec.setPublishedByID("NA");
		rec.setStatus(NAVStatus.PROVISIONAL);
		
		NAVRecord saved=navRepository.save(rec);
		
		
		return toResponse(saved);
	}
	
	
	public NAVResponse toResponse(NAVRecord record) {
        return NAVResponse.builder()
            .navRecordId(record.getNavRecordId())
            .schemeId(record.getSchemeId())
            .optionId(record.getOptionId())
            .navDate(record.getNavDate())
            .navValue(record.getNavValue())
            .totalAUM(record.getTotalAUM())
            .totalUnitsOutstanding(record.getTotalUnitsOutstanding())
            .status(record.getStatus())
            .publishedByID(record.getPublishedByID())
            .createdAt(record.getCreatedAt())
            .updatedAt(record.getUpdatedAt())
            .build();
    }

	public List<NAVResponse> getNAVHistory(String schemeId) {
		return navRepository.findBySchemeIdOrderByNavDateDesc(schemeId).stream().map(this::toResponse).toList();
	}
	
	public List<LatestNAVSummary> getLatestNAV(){
		return navRepository.findLatestPublishedNAVForAllSchemes(NAVStatus.PUBLISHED);
	}
	
	public NAVResponse publishNAV(UUID navRecordId,String userId) {
		NAVRecord record=findRecordOrThrow(navRecordId);
		
		if (record.getStatus() != NAVStatus.PROVISIONAL) {
            throw new InvalidNAVStateTransitionException(
                String.format(
                    "Only PROVISIONAL NAV can be published. Current status: %s",
                    record.getStatus()
                )
            );
        }
		
		record.setStatus(NAVStatus.PUBLISHED);
		record.setPublishedByID(userId);
		NAVRecord saved=navRepository.save(record);
		
		
		return toResponse(saved);
		
	}
	
	public NAVResponse reviseNAV(UUID navRecordId,ReviseNAVRequest request,String userId) {
		log.info("Revising NAV id={}",navRecordId);
		
		NAVRecord original = findRecordOrThrow(navRecordId);

		if (original.getStatus() != NAVStatus.PUBLISHED) {
		     throw new InvalidNAVStateTransitionException(
		    		 String.format(
		    				 "Only PUBLISHED NAV can be revised. Current status: %s. %s",
		    	             original.getStatus(),
		    	             original.getStatus() == NAVStatus.PROVISIONAL
		    	             ? "To edit a PROVISIONAL record, use PUT /api/nav/{id}."
		    	             : "A REVISED record cannot be revised again. Check the latest record."
		    	    )
		    );
		}
		
		boolean correctionAlreadyExists = navRepository
		        .existsBySchemeIdAndOptionIdAndNavDateAndStatusNot(
		            original.getSchemeId(), original.getOptionId(),
		            original.getNavDate(), NAVStatus.REVISED
		        );
		
		if (correctionAlreadyExists) {
	        // This means someone already created a correction for this date
	        // that hasn't been published yet — don't allow a second one
	        boolean isAnotherRecord = navRepository.findBySchemeIdAndOptionIdAndNavDateAndStatusNot(
	                original.getSchemeId(), original.getOptionId(),
	                original.getNavDate(), NAVStatus.REVISED
	            )
	            .map(r -> !r.getNavRecordId().equals(navRecordId))
	            .orElse(false);

	        if (isAnotherRecord) {
	            throw new NAVAlreadyExistsException(
	                "A correction for this scheme-option-date is already in PROVISIONAL state. " +
	                "Publish or delete it before creating another revision."
	            );
	        }
	    }
		
		original.setStatus(NAVStatus.REVISED);
		navRepository.save(original);
		
		log.info("Original NAV id={} stamped as REVISED (navValue={}, units={})",
		        navRecordId, original.getNavValue(), original.getTotalUnitsOutstanding());
		    
		BigDecimal newAum = computeAUM(request.navValue(), request.totalUnitsOutstanding());

		NAVRecord correction = NAVRecord.builder()
		    .schemeId(original.getSchemeId())
		    .optionId(original.getOptionId())
		    .navDate(original.getNavDate())
		    .navValue(request.navValue())     
		    .totalUnitsOutstanding(request.totalUnitsOutstanding())
		    .totalAUM(newAum)
		    .publishedByID(userId)
		    .status(NAVStatus.PROVISIONAL)        
		    .build();

		NAVRecord saved = navRepository.save(correction);
		log.info("Correction NAV created id={} navValue={} aum={} status=PROVISIONAL",
		        saved.getNavRecordId(), request.navValue(), newAum);

		return toResponse(saved);
	}
	
	private NAVRecord findRecordOrThrow(UUID navRecordId) {
        return navRepository.findById(navRecordId)
            .orElseThrow(()-> new NAVNotFoundException(
            		"NAV record not found with id: " + navRecordId
            ));
    }
}
