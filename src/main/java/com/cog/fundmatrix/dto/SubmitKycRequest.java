package com.cog.fundmatrix.dto;

import java.util.UUID;

import com.cog.fundmatrix.domain.enums.KycType;

import lombok.Data;

/**
 * Investor self-submission of KYC details. The investor is taken from the authenticated
 * user, so no investorId is accepted here — investors submit their own KYC only.
 */

public record SubmitKycRequest(
		UUID investorId,
        KycType kycType,
        String documentType,
        String documentRef
) {
}
