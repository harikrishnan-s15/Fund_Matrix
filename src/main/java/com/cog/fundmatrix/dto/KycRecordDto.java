package com.cog.fundmatrix.dto;


import java.time.LocalDate;
import java.util.UUID;

import com.cog.fundmatrix.domain.enums.KycStatus;
import com.cog.fundmatrix.domain.enums.KycType;

public record KycRecordDto(
        UUID id,
        UUID investorId,
//        String investorName,
        KycType kycType,
        String documentType,
        String documentRef,
        LocalDate verifiedDate,
        KycStatus kycStatus
) {
}
