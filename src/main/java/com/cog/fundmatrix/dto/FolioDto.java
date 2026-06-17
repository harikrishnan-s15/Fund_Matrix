package com.cog.fundmatrix.dto;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.cog.fundmatrix.domain.enums.FolioStatus;
import com.cog.fundmatrix.domain.enums.ModeOfHolding;
import com.cog.fundmatrix.domain.enums.TaxStatus;
import com.cog.fundmatrix.dto.investorFolio.NomineeDetails;

public record FolioDto(
        UUID id,
        UUID investorId,
        UUID distributorId,
        TaxStatus taxStatus,
        ModeOfHolding modeOfHolding,
        List<NomineeDetails> nomineeDetails,
        String bankAccountRef,
        FolioStatus status
//        BigDecimal currentValue
) {
}
