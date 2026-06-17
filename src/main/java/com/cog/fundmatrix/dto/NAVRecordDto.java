package com.cog.fundmatrix.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import com.cog.fundmatrix.domain.enums.NAVStatus;

import lombok.Builder;

public class NAVRecordDto {

	@Builder
	public record CreateNAVRequest(
	        String schemeId,
	        String optionId,
	        LocalDate navDate,
	        BigDecimal navValue,
	        BigDecimal totalUnitsOutstanding
	        )
	{}
	
	@Builder
    public record ReviseNAVRequest(
        BigDecimal navValue,
        BigDecimal totalUnitsOutstanding,
        String revisionReason
    ) {}
	
	
	@Builder
    public record NAVResponse(
        UUID navRecordId,
        String schemeId,
        String optionId,
        LocalDate navDate,
        BigDecimal navValue,
        BigDecimal totalAUM,
        BigDecimal totalUnitsOutstanding,
        NAVStatus status,
        String publishedByID,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {}
	
	
	@Builder
    public record LatestNAVSummary(
        String schemeId,
        String optionId,
        LocalDate navDate,
        BigDecimal navValue,
        BigDecimal totalAum,
        NAVStatus status
    ) {}
	
}
