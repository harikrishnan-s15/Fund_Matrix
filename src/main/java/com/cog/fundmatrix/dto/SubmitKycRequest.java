package com.cog.fundmatrix.dto;

import com.cog.fundmatrix.domain.enums.KycType;

/**
 * Investor self-submission of KYC details. The investor is taken from the authenticated
 * user, so no investorId is accepted here — investors submit their own KYC only.
 */
public record SubmitKycRequest(
        KycType kycType,
        String documentType,
        String documentRef
) {
}
