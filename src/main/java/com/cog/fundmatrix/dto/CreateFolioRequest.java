package com.cog.fundmatrix.dto;

import java.util.List;
import java.util.UUID;

import com.cog.fundmatrix.domain.enums.FolioStatus;
import com.cog.fundmatrix.domain.enums.ModeOfHolding;
import com.cog.fundmatrix.domain.enums.TaxStatus;
import com.cog.fundmatrix.dto.investorFolio.NomineeDetails;

public record CreateFolioRequest(
        /** Optional — when omitted, the folio is created for the authenticated investor. */
         UUID investorId,
         UUID distributorId,
         TaxStatus taxStatus,
         ModeOfHolding modeOfHolding,
         List<NomineeDetails> nomineeDetails,
         String bankAccountRef
) {
}
